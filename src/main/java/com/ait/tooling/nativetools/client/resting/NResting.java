/*
   Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.

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
import com.ait.tooling.nativetools.client.NObject;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class NResting
{
    public NResting()
    {
    }
    
    public IRestingRequest get(String url, IRestingResponseCallback callback)
    {
        return get(url, new NRestingHeaders(), callback);
    }

    public IRestingRequest get(String url, NRestingHeaders headers, IRestingResponseCallback callback)
    {
        return call(NMethod.GET, url, "", headers, callback);
    }

    public IRestingRequest put(String url, NObject body, IRestingResponseCallback callback)
    {
        return put(url, body, new NRestingHeaders(), callback);
    }

    public IRestingRequest put(String url, NObject body, NRestingHeaders headers, IRestingResponseCallback callback)
    {
        return call(NMethod.PUT, url, body.toJSONString(), headers, callback);
    }

    public IRestingRequest post(String url, NObject body, IRestingResponseCallback callback)
    {
        return post(url, body, new NRestingHeaders(), callback);
    }

    public IRestingRequest post(String url, NObject body, NRestingHeaders headers, IRestingResponseCallback callback)
    {
        return call(NMethod.POST, url, body.toJSONString(), headers, callback);
    }

    public IRestingRequest patch(String url, NObject body, IRestingResponseCallback callback)
    {
        return patch(url, body, new NRestingHeaders(), callback);
    }

    public IRestingRequest patch(String url, NObject body, NRestingHeaders headers, IRestingResponseCallback callback)
    {
        return call(NMethod.PATCH, url, body.toJSONString(), headers, callback);
    }

    public IRestingRequest delete(String url, IRestingResponseCallback callback)
    {
        return delete(url, new NRestingHeaders(), callback);
    }

    public IRestingRequest delete(String url, NRestingHeaders headers, IRestingResponseCallback callback)
    {
        return call(NMethod.DELETE, url, "", headers, callback);
    }

    protected NRequestBuilder makeRequestBuilder(final NMethod type, final String url)
    {
        return new NRequestBuilder(Objects.requireNonNull(type), URL.encode(StringOps.requireTrimOrNull(url)));
    }

    protected IRestingRequest call(final NMethod type, final String url, final String data, final NRestingHeaders headers, final IRestingResponseCallback callback)
    {
        Objects.requireNonNull(callback);

        final NRequestBuilder builder = makeRequestBuilder(type, url);

        final NRestingHeaders head = NRestingHeaders.makeDefaultRESTHeaders(Objects.requireNonNull(headers));

        for (String k : head.keys())
        {
            builder.setHeader(k, head.get(k));
        }
        try
        {
            return new NRestingRequest(builder.getUrl(), headers, type, builder.sendRequest(data, new RequestCallback()
            {
                @Override
                public void onResponseReceived(final Request request, final Response response)
                {
                    callback.onResponse(new NRestingResponse(response.getStatusCode(), response.getText(), new NRestingHeaders(response.getHeaders()), type, new NRestingRequest(builder.getUrl(), headers, type, request)));
                }

                @Override
                public void onError(final Request request, final Throwable e)
                {
                    callback.onFailure(e);
                }
            }));
        }
        catch (RequestException e)
        {
            callback.onFailure(e);
        }
        return null;
    }
}
