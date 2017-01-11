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

package com.ait.tooling.nativetools.client.websocket;

import java.util.Collection;
import java.util.Objects;

import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NObjectOnWire;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NValue;
import com.ait.tooling.nativetools.client.collection.NFastStringArray;

public class JSONWebSocket implements IWebSocket<NObjectOnWire>
{
    private final WebSocket             m_wssocket;

    private final JSONWebSocketCallback m_callback;

    public JSONWebSocket(final String url, final JSONWebSocketCallback callback)
    {
        this(url, (NFastStringArray) null, callback);
    }

    public JSONWebSocket(final String url, final String protocol, final JSONWebSocketCallback callback)
    {
        this(url, new NFastStringArray(Objects.requireNonNull(protocol)), callback);
    }

    public JSONWebSocket(final String url, final Collection<String> protocols, final JSONWebSocketCallback callback)
    {
        this(url, new NFastStringArray(Objects.requireNonNull(protocols)), callback);
    }

    public JSONWebSocket(final String url, final NFastStringArray protocols, final JSONWebSocketCallback callback)
    {
        final JSONWebSocket self = this;

        m_callback = Objects.requireNonNull(callback, "JSONWebSocketCallback is null");

        m_wssocket = new WebSocket(url, protocols, new WebSocketCallback()
        {
            @Override
            public void onOpen(WebSocket ws)
            {
                m_callback.onOpen(self);
            }

            @Override
            public void onClose(WebSocket ws)
            {
                m_callback.onClose(self);
            }

            @Override
            public void onError(WebSocket ws, final Throwable error)
            {
                m_callback.onError(self, error);
            }

            @Override
            public void onMessage(WebSocket ws, final String message)
            {
                try
                {
                    final NValue<?> parsed = NUtils.JSON.parse(message);

                    if (null == parsed)
                    {
                        m_callback.onError(self, new Exception("Error parsing JSON"));
                    }
                    else
                    {
                        final NObject object = parsed.asNObject();

                        if (null != object)
                        {
                            m_callback.onMessage(self, object);
                        }
                        else
                        {
                            final NArray array = parsed.asNArray();

                            if (null != array)
                            {
                                m_callback.onMessage(self, array);
                            }
                            else
                            {
                                m_callback.onError(self, new Exception("Error parsing JSON"));
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    m_callback.onError(self, e);
                }
            }
        });
    }

    @Override
    public void send(final NObjectOnWire message)
    {
        m_wssocket.send(Objects.requireNonNull(message).onWire().toJSONString());
    }

    @Override
    public void close()
    {
        m_wssocket.close();
    }

    @Override
    public int getBufferedAmount()
    {
        return m_wssocket.getBufferedAmount();
    }

    @Override
    public int getReadyState()
    {
        return m_wssocket.getReadyState();
    }

    @Override
    public String getURL()
    {
        return m_wssocket.getURL();
    }

    @Override
    public String getProtocol()
    {
        return m_wssocket.getProtocol();
    }

    @Override
    public boolean isOpen()
    {
        return m_wssocket.isOpen();
    }

    @Override
    public String uuid()
    {
        return m_wssocket.uuid();
    }
}
