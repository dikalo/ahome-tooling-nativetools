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

package com.ait.tooling.nativetools.client.websocket;

import java.util.Objects;

import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NValue;

public class JSONWebSocket implements IWebSocket<NObject>
{
    private final WebSocket             m_wssocket;

    private final JSONWebSocketCallback m_callback;

    public JSONWebSocket(final String url, final JSONWebSocketCallback callback)
    {
        final JSONWebSocket self = this;

        m_callback = Objects.requireNonNull(callback, "JSONWebSocketCallback is null");

        m_wssocket = new WebSocket(url, new WebSocketCallback()
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
            public void onError(WebSocket ws, final String error)
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
                        m_callback.onError(self, "Error parsing JSON");
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
                                final int size = array.size();

                                for (int i = 0; i < size; i++)
                                {
                                    final NObject value = array.getAsObject(i);

                                    if (null != value)
                                    {
                                        m_callback.onMessage(self, value);
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    m_callback.onError(self, "Error parsing JSON");
                }
            }
        });
    }

    @Override
    public void send(final NObject message)
    {
        m_wssocket.send(toJSONString(Objects.requireNonNull(message)));
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

    protected String toJSONString(final NObject message)
    {
        return Objects.requireNonNull(message).toJSONString();
    }
}
