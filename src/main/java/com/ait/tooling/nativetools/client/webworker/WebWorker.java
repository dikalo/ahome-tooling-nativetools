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

package com.ait.tooling.nativetools.client.webworker;

import java.util.Objects;

import com.google.gwt.core.client.JavaScriptObject;

public class WebWorker<T>
{
    private final WebWorkerJSO m_jso;

    public WebWorker(final WebWorkerJSO jso)
    {
        m_jso = Objects.requireNonNull(jso);
    }

    public WebWorker(final String script)
    {
        this(WebWorkerJSO.make(Objects.requireNonNull(script)));
    }

    public WebWorker(final IWebWorkerContext context)
    {
        this(context.getScriptSource());
    }

    public final WebWorker<T> terminate()
    {
        m_jso.terminate();

        return this;
    }

    public final WebWorker<T> setOnMessageHandler(final OnMessageHandler<T> handler)
    {
        m_jso.setOnMessageHandler(this, Objects.requireNonNull(handler));

        return this;
    }

    protected static final class WebWorkerJSO extends JavaScriptObject
    {
        public static final native boolean isSupported()
        /*-{
			return (typeof ($wnd.Worker) !== "undefined");
        }-*/;

        public static final native WebWorkerJSO make(String script)
        /*-{
			if (typeof ($wnd.Worker) !== "undefined") {
				return new $wnd.Worker(script);
			}
			return null;
        }-*/;

        protected WebWorkerJSO()
        {
        }

        public final native void terminate()
        /*-{
			this.terminate();
        }-*/;

        public final native void setOnMessageHandler(WebWorker<?> worker, OnMessageHandler<?> handler)
        /*-{
			this.onmessage = function(e) {
				handler.@com.ait.tooling.nativetools.client.webworker.OnMessageHandler::onMessage(Lcom/ait/tooling/nativetools/client/webworker/WebWorker;Ljava/lang/Object;)(worker, e.data);
			};
        }-*/;
    }
}
