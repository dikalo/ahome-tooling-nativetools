/*
   Copyright (c) 2017 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.tooling.nativetools.client.resting;

import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.types.Activatable;
import com.ait.tooling.nativetools.client.NObjectOnWire;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class NResting extends Activatable implements IResting
{
    private long   m_docntr;

    private String m_prefix;

    public NResting()
    {
        this(null);
    }

    public NResting(final String prefix)
    {
        super(true);

        m_prefix = doNormalizePrefix(prefix);
    }

    @Override
    public String getPrefix()
    {
        return m_prefix;
    }

    protected final String doNormalizePrefix(String prefix)
    {
        prefix = StringOps.toTrimOrElse(prefix, "");

        while (prefix.endsWith("/"))
        {
            if (prefix.length() > 0)
            {
                prefix = prefix.substring(0, prefix.length() - 1).trim();
            }
        }
        if (prefix.length() > 0)
        {
            prefix = prefix + "/";
        }
        return prefix;
    }

    @Override
    public IRestingRequest get(final String url, final IRestingResponseCallback callback)
    {
        return get(url, new NRestingHeaders(), callback);
    }

    @Override
    public IRestingRequest get(final String url, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        return call(NMethod.GET, url, "", headers, callback);
    }

    @Override
    public IRestingRequest put(final String url, final NObjectOnWire body, final IRestingResponseCallback callback)
    {
        return put(url, body, new NRestingHeaders(), callback);
    }

    @Override
    public IRestingRequest put(final String url, final NObjectOnWire body, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        return call(NMethod.PUT, url, body, headers, callback);
    }

    @Override
    public IRestingRequest post(final String url, final NObjectOnWire body, final IRestingResponseCallback callback)
    {
        return post(url, body, new NRestingHeaders(), callback);
    }

    @Override
    public IRestingRequest post(final String url, final NObjectOnWire body, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        return call(NMethod.POST, url, body, headers, callback);
    }

    @Override
    public IRestingRequest patch(final String url, final NObjectOnWire body, final IRestingResponseCallback callback)
    {
        return patch(url, body, new NRestingHeaders(), callback);
    }

    @Override
    public IRestingRequest patch(String url, final NObjectOnWire body, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        return call(NMethod.PATCH, url, body, headers, callback);
    }

    @Override
    public IRestingRequest delete(final String url, final IRestingResponseCallback callback)
    {
        return delete(url, new NRestingHeaders(), callback);
    }

    @Override
    public IRestingRequest delete(final String url, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        return call(NMethod.DELETE, url, "", headers, callback);
    }

    protected NRequestBuilder makeRequestBuilder(final NMethod type, String url)
    {
        while (url.startsWith("/"))
        {
            if (url.length() > 0)
            {
                url = url.substring(1).trim();
            }
        }
        while (url.endsWith("/"))
        {
            if (url.length() > 0)
            {
                url = url.substring(0, url.length() - 1).trim();
            }
        }
        url = StringOps.requireTrimOrNull(url);

        if ((url.startsWith("http://")) || (url.startsWith("https://")))
        {
            return new NRequestBuilder(Objects.requireNonNull(type), URL.encode(url));
        }
        return new NRequestBuilder(Objects.requireNonNull(type), URL.encode(doNormalizePrefix(getPrefix()) + url));
    }

    protected IRestingRequest call(final NMethod type, final String url, final NObjectOnWire data, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        return call(type, url, data.onWire().toJSONString(), headers, callback);
    }

    protected IRestingRequest call(final NMethod type, final String url, final String data, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        Objects.requireNonNull(callback);

        final long cntr = ++m_docntr;

        final long time = System.currentTimeMillis();

        final NRequestBuilder builder = makeRequestBuilder(type, url);

        if (false == isActive())
        {
            callback.onFailure(new InactiveRestingException(type, builder.getUrl(), cntr, time));

            return null;
        }
        final NRestingHeaders head = headers.doRESTHeaders();

        for (String k : head.keys())
        {
            builder.setHeader(k, head.get(k));
        }
        int mils = head.getTimeout();

        if (mils > 0)
        {
            builder.setTimeoutMillis(mils);
        }
        String user = head.getUsername();

        if ((null != user) && (false == user.isEmpty()))
        {
            builder.setUser(user);

            String pass = head.getPassword();

            if ((null != pass) && (false == pass.isEmpty()))
            {
                builder.setPassword(pass);
            }
            builder.setIncludeCredentials(head.getIncludeCredentials());
        }
        try
        {
            return new NRestingRequest(builder.getUrl(), head, type, cntr, time, builder.sendRequest(data, new RequestCallback()
            {
                @Override
                public void onResponseReceived(final Request request, final Response response)
                {
                    callback.onResponse(new NRestingResponse(response.getStatusCode(), response.getText(), new NRestingHeaders(response.getHeaders()).setOptions(head), type, new NRestingRequest(builder.getUrl(), head, type, cntr, time, request), System.currentTimeMillis() - time));
                }

                @Override
                public void onError(final Request request, final Throwable e)
                {
                    callback.onFailure(new RestingException(e, type, builder.getUrl(), cntr, time));
                }
            }));
        }
        catch (RequestException e)
        {
            callback.onFailure(new RestingException(e, type, builder.getUrl(), cntr, time));
        }
        return null;
    }
}
