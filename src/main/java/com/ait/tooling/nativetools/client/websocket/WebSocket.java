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

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.nativetools.client.collection.NFastStringArray;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.URL;

public class WebSocket implements IWebSocket<String>
{
    private final WebSocketCallback m_callback;

    public WebSocket(final String url, final WebSocketCallback callback)
    {
        this(url, (NFastStringArray) null, callback);
    }

    public WebSocket(final String url, final String protocol, final WebSocketCallback callback)
    {
        this(url, new NFastStringArray(StringOps.requireTrimOrNull(protocol)), callback);
    }

    public WebSocket(final String url, final NFastStringArray protocols, final WebSocketCallback callback)
    {
        if (false == isSupported())
        {
            throw new IllegalStateException("WebSocket is not supported");
        }
        m_callback = Objects.requireNonNull(callback, "WebSocketCallback is null");

        if ((null == protocols) || (protocols.isEmpty()))
        {
            connect_0(URL.encode(StringOps.requireTrimOrNull(WebSocketUtils.path(url))), null);
        }
        else
        {
            connect_0(URL.encode(StringOps.requireTrimOrNull(WebSocketUtils.path(url))), protocols.getJSO());
        }
    }

    @Override
    public void send(final String message)
    {
        if (null == message)
        {
            throw new NullPointerException("message is null");
        }
        try
        {
            send_0(message);
        }
        catch (Exception e)
        {
            m_callback.onError(this, e);
        }
    }

    @Override
    public void close()
    {
        close_0();
    }

    @Override
    public int getBufferedAmount()
    {
        return getBufferedAmount_0();
    }

    @Override
    public int getReadyState()
    {
        return getReadyState_0();
    }

    @Override
    public String getURL()
    {
        return getURL_0();
    }

    @Override
    public String getProtocol()
    {
        return getProtocol_0();
    }

    private final void onOpenHelper()
    {
        m_callback.onOpen(this);
    }

    private final void onCloseHelper()
    {
        m_callback.onClose(this);
    }

    private final void onMessageHelper(String message)
    {
        m_callback.onMessage(this, message);
    }

    private final void onErrorHelper(String error)
    {
        m_callback.onError(this, new Exception(error));
    }

    public static final native boolean isSupported()
    /*-{
		if (!$wnd.WebSocket) {
			return false;
		}
		return true;
    }-*/;

    private final native void connect_0(String url, JavaScriptObject protocols)
    /*-{
		var self = this;

		var list = protocols;

		if ((list) && (list.length > 0)) {
			self.socket = new $wnd.WebSocket(url, list);
		} else {
			self.socket = new $wnd.WebSocket(url);
		}
		self.socket.onopen = function() {
			if (!self.socket) {
				self.@com.ait.tooling.nativetools.client.websocket.WebSocket::onErrorHelper(Ljava/lang/String;)("WebSocket connections not supported by this browser");
				return;
			}
			self.@com.ait.tooling.nativetools.client.websocket.WebSocket::onOpenHelper()();
		};
		self.socket.onmessage = function(response) {
			if (response.data) {
				self.@com.ait.tooling.nativetools.client.websocket.WebSocket::onMessageHelper(Ljava/lang/String;)(response.data);
			}
		};
		self.socket.onerror = function(error) {
			if (error) {
				self.@com.ait.tooling.nativetools.client.websocket.WebSocket::onErrorHelper(Ljava/lang/String;)("WebSocket ERROR: " + error);
			} else {
				self.@com.ait.tooling.nativetools.client.websocket.WebSocket::onErrorHelper(Ljava/lang/String;)("WebSocket ERROR: UNKNOWN");
			}
		};
		self.socket.onclose = function(m) {
			self.@com.ait.tooling.nativetools.client.websocket.WebSocket::onCloseHelper()();
		};
    }-*/;

    private final native void close_0()
    /*-{
		this.socket.close();
    }-*/;

    private final native int getReadyState_0()
    /*-{
		this.socket.readyState;
    }-*/;

    private final native int getBufferedAmount_0()
    /*-{
		this.socket.bufferedAmount;
    }-*/;

    private final native String getProtocol_0()
    /*-{
		this.socket.protocol;
    }-*/;

    private final native String getURL_0()
    /*-{
		this.socket.url;
    }-*/;

    private final native void send_0(String message)
    /*-{
		this.socket.send(message);
    }-*/;
}
