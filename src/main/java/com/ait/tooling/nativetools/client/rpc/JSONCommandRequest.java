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

package com.ait.tooling.nativetools.client.rpc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.java.util.function.Supplier;
import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.common.api.model.AbstractModelRepresentation;
import com.ait.tooling.common.api.types.Activatable;
import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NArrayJSO;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NValue;
import com.ait.tooling.nativetools.client.datamodel.AbstractJSONDataModel;
import com.ait.tooling.nativetools.client.datamodel.IJSONModel;
import com.ait.tooling.nativetools.client.security.XSS;
import com.ait.tooling.nativetools.client.storage.ClientStorage;
import com.ait.tooling.nativetools.client.storage.LocalStorage;
import com.ait.tooling.nativetools.client.storage.SessionStorage;
import com.ait.tooling.nativetools.client.util.Client;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JSONCommandRequest extends Activatable implements IJSONCommandRequest
{
    private static final long    serialVersionUID = 1493983316974589122L;

    private static long          s_counter;

    private final RequestBuilder m_builder;

    private final String         m_postURL;

    private boolean              m_usexsrf;

    public JSONCommandRequest(final String url)
    {
        this(url, true);
    }

    public JSONCommandRequest(final String url, final boolean usexsrf)
    {
        super(true);

        m_usexsrf = usexsrf;

        m_postURL = URL.encode(StringOps.requireTrimOrNull(url));

        m_builder = new RequestBuilder(Objects.requireNonNull(RequestBuilder.POST), m_postURL);

        doPrepareBuilderInit(m_builder);
    }

    @Override
    public boolean isCommandInBody()
    {
        return true;
    }

    @Override
    public final String getURL()
    {
        return m_postURL;
    }

    @Override
    public final boolean isXSRFActive()
    {
        return m_usexsrf;
    }

    @Override
    public Request call(final String command, final AbstractJSONDataModel request, final AsyncCallback<NObject> callback)
    {
        return call(command, request.getModel(), callback);
    }

    @Override
    public Request call(final String command, final AbstractModelRepresentation<NArray> request, final String name, final AsyncCallback<NObject> callback)
    {
        return call(command, request.getModel(), name, callback);
    }

    @Override
    public Request call(final String command, final AbstractModelRepresentation<NArray> request, final AsyncCallback<NObject> callback)
    {
        return call(command, request.getModel(), callback);
    }

    @Override
    public <T extends IJSONModel<?>> Request call(final String command, final Collection<T> request, final AsyncCallback<NObject> callback)
    {
        return call(command, request, getDefaultListName(command), callback);
    }

    @Override
    public <T extends IJSONModel<?>> Request call(final String command, final Collection<T> request, String name, final AsyncCallback<NObject> callback)
    {
        name = StringOps.requireTrimOrNull(name);

        final NArray list = new NArray();

        for (T model : request)
        {
            list.push(model.getModel());
        }
        return call(command, list, name, callback);
    }

    @Override
    public Request call(final String command, final NArray request, final String name, final AsyncCallback<NObject> callback)
    {
        return call(command, new NObject(StringOps.requireTrimOrNull(name), Objects.requireNonNull(request)), callback);
    }

    @Override
    public Request call(final String command, final NArray request, final AsyncCallback<NObject> callback)
    {
        return call(command, request, getDefaultListName(command), callback);
    }

    @Override
    public Request call(final String command, final JSONArray request, final AsyncCallback<NObject> callback)
    {
        final NArrayJSO jso = Objects.requireNonNull(Objects.requireNonNull(request).getJavaScriptObject()).cast();

        return call(command, new NArray(jso), callback);
    }

    @Override
    public Request call(final String command, final JSONArray request, final String name, final AsyncCallback<NObject> callback)
    {
        final NArrayJSO jso = Objects.requireNonNull(Objects.requireNonNull(request).getJavaScriptObject()).cast();

        return call(command, new NArray(jso), name, callback);
    }

    @Override
    public Request call(final String command, final AsyncCallback<NObject> callback)
    {
        return call(command, new NObject(), callback);
    }

    @Override
    public Request call(final String command, final NObject request, final AsyncCallback<NObject> callback)
    {
        return call(command, request, callback, isXSRFActive() ? getXSRFTokenQueueForURL(getURL()) : null);
    }

    @Override
    public Request call(final String command, final JSONObject request, final AsyncCallback<NObject> callback)
    {
        final NObjectJSO jso = Objects.requireNonNull(Objects.requireNonNull(request).getJavaScriptObject()).cast();

        return call(command, new NObject(jso), callback);
    }

    protected final Request call(final String command, NObject request, final AsyncCallback<NObject> callback, final IXSRFTokenQueue supplier)
    {
        Objects.requireNonNull(callback);

        StringOps.requireTrimOrNull(command);

        request = Objects.requireNonNull(request);

        final long counter = ++s_counter;

        final long reqtime = System.currentTimeMillis();

        if (false == isActive())
        {
            logFailure(command, counter, reqtime, new Exception("Not active"));

            return null;
        }
        try
        {
            doPrepareBuilderCall(m_builder, command, counter, request, isXSRFActive() ? supplier : null);

            logRequest(command, counter, reqtime, request);

            final NObject send = new NObject("request", request);

            if (isCommandInBody())
            {
                send.put("command", command);
            }
            return m_builder.sendRequest(toJSONString(send), new RequestCallback()
            {
                @Override
                public void onResponseReceived(final Request request, final Response response)
                {
                    final int code = response.getStatusCode();

                    if (Response.SC_OK != code)
                    {
                        onBadStatusCode(m_builder, command, counter, reqtime, callback, code);

                        return;
                    }
                    try
                    {
                        NObject result = parseJSONString(response.getText());

                        if (null == result)
                        {
                            doFailure(command, callback, counter, reqtime, new Exception("JSON is not NObject"));

                            return;
                        }
                        if (result.isObject("piggyback"))
                        {
                            final NObject piggyback = result.getAsObject("piggyback");

                            if (null != piggyback)
                            {
                                onPiggyBack(m_builder, command, counter, piggyback);
                            }
                        }
                        if (result.isArray("xsrftokens"))
                        {
                            final NArray xsrftokens = result.getAsArray("xsrftokens");

                            if (null != xsrftokens)
                            {
                                final int size = xsrftokens.size();

                                if (size > 0)
                                {
                                    final IXSRFTokenQueue xsrf = getXSRFTokenQueueForURL(getURL());

                                    if (null != xsrf)
                                    {
                                        for (int i = 0; i < size; i++)
                                        {
                                            final String token = StringOps.toTrimOrNull(xsrftokens.getAsString(i));

                                            if (null != token)
                                            {
                                                xsrf.push(new XSRFToken(token));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (result.isObject("result"))
                        {
                            result = result.getAsObject("result");

                            if (null != result)
                            {
                                logResult(command, counter, reqtime, result);

                                callback.onSuccess(result);

                                return;
                            }
                            doFailure(command, callback, counter, reqtime, new Exception("JSON field result not NObject or null"));

                            return;
                        }
                        else if (result.isString("error"))
                        {
                            final String error = result.getAsString("error");

                            if (null != error)
                            {
                                doFailure(command, callback, counter, reqtime, new Exception(error));

                                return;
                            }
                            doFailure(command, callback, counter, reqtime, new Exception("JSON field error not String or null"));

                            return;
                        }
                        doFailure(command, callback, counter, reqtime, new Exception("JSON did not contain result or error"));

                        return;
                    }
                    catch (Exception e)
                    {
                        doFailure(command, callback, counter, reqtime, e);
                    }
                }

                @Override
                public void onError(final Request request, final Throwable e)
                {
                    doFailure(command, callback, counter, reqtime, e);
                }
            });
        }
        catch (RequestException e)
        {
            doFailure(command, callback, counter, reqtime, e);
        }
        return null;
    }

    protected IXSRFTokenQueue getXSRFTokenQueueForURL(final String url)
    {
        return getXSRFTokenQueueDictionary().getXSRFTokenQueue(url);
    }

    protected IXSRFTokenQueueDictionary getXSRFTokenQueueDictionary()
    {
        return DefaultXSRFTokenQueueDictionary.get();
    }

    protected void doFailure(final String command, final AsyncCallback<NObject> callback, final long counter, long reqtime, final Throwable e)
    {
        logFailure(command, counter, reqtime, e);

        callback.onFailure(e);
    }

    protected NObject parseJSONString(final String json) throws Exception
    {
        final NValue<?> parsed = NUtils.JSON.parse(json);

        if (null == parsed)
        {
            throw new Exception("Error parsing JSON");
        }
        return parsed.asNObject();
    }

    protected String toJSONString(final NObject object)
    {
        return object.toJSONString();
    }

    protected void doPrepareBuilderInit(final RequestBuilder builder)
    {
        builder.setHeader(ACCEPT_HEADER, CONTENT_TYPE_APPLICATION_JSON);

        builder.setHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_APPLICATION_JSON);

        builder.setHeader(X_STRICT_JSON_FORMAT_HEADER, "true");

        builder.setHeader(X_CLIENT_UUID_HEADER, Client.get().getClientUUID());
    }

    protected List<String> getBuilderCallHeaderNames()
    {
        return Arrays.asList(X_USER_ID_HEADER, X_USER_NAME_HEADER, X_SESSION_ID_HEADER, X_SESSION_UUID_HEADER, X_CLIENT_NAME_HEADER, X_CLIENT_API_TOKEN_HEADER);
    }

    protected String getHeaderFromStorage(String name)
    {
        name = StringOps.toTrimOrNull(name);

        if (null == name)
        {
            return null;
        }
        String header = null;

        if (LocalStorage.get().isSupported())
        {
            header = LocalStorage.get().getString(name);
        }
        if (null == header)
        {
            if (SessionStorage.get().isSupported())
            {
                header = SessionStorage.get().getString(name);
            }
        }
        if (null == header)
        {
            if (ClientStorage.get().isSupported())
            {
                header = ClientStorage.get().getString(name);
            }
        }
        return XSS.get().clean(header);
    }

    protected void doPrepareBuilderCall(final RequestBuilder builder, final String command, final long counter, final NObject request, final IXSRFTokenQueue tokens)
    {
        String value = null;

        if (null != tokens)
        {
            final Supplier<XSRFToken> supplier = tokens.take();

            if (null != supplier)
            {
                final XSRFToken token = supplier.get();

                if (null != token)
                {
                    value = StringOps.toTrimOrNull(token.getValue());
                }
            }
        }
        if (null != value)
        {
            builder.setHeader(X_XSRF_TOKEN_HEADER, value);
        }
        else
        {
            builder.setHeader(X_XSRF_TOKEN_HEADER, JSONType.UNDEFINED.getValue());
        }
        for (String name : getBuilderCallHeaderNames())
        {
            final String head = StringOps.toTrimOrNull(name);

            final String valu = StringOps.toTrimOrNull(getHeaderFromStorage(head));

            if ((null != head) && (null != valu))
            {
                builder.setHeader(head, valu);
            }
        }
    }

    protected void onPiggyBack(RequestBuilder builder, String command, long counter, NObject piggyback)
    {
    }

    protected void onBadStatusCode(final RequestBuilder builder, final String command, final long counter, final long reqtime, final AsyncCallback<NObject> callback, final int code)
    {
        if (Response.SC_NOT_FOUND == code)
        {
            doFailure(command, callback, counter, reqtime, new Exception("Code [" + code + "]: Command [" + command + "] not found "));
        }
        else if (Response.SC_FORBIDDEN == code)
        {
            doFailure(command, callback, counter, reqtime, new Exception("Code [" + code + "]: No permission or Session expired"));
        }
        else if (Response.SC_BAD_GATEWAY == code)
        {
            doFailure(command, callback, counter, reqtime, new Exception("Code [" + code + "]: Misconfigured Gateway"));
        }
        else if ((0 == code) || (Response.SC_SERVICE_UNAVAILABLE == code))
        {
            doFailure(command, callback, counter, reqtime, new Exception("Code [" + code + "]: Server appears to be down"));
        }
        else
        {
            doFailure(command, callback, counter, reqtime, new Exception("Bad status code ]" + code + "] for command [" + command + "]"));
        }
    }

    protected void logRequest(String command, long counter, long reqtime, NObject request)
    {
    }

    protected void logResult(String command, long counter, long reqtime, NObject result)
    {
    }

    protected void logFailure(final String command, final long counter, final long reqtime, final Throwable e)
    {
        Client.get().error("JSONCommandRequest(command: " + command + ",counter" + counter + ",url:" + getURL() + ") :", e);
    }

    protected String getDefaultListName(final String command)
    {
        return "list";
    }
}
