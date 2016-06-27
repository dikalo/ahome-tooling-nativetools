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

import com.google.gwt.core.client.GWT;

public final class WebSocketUtils
{
    private WebSocketUtils()
    {
    }

    public static final String path(String path)
    {
        path = path.trim();

        if (path.startsWith("ws://") || path.startsWith("wss://"))
        {
            return path;
        }
        String host = GWT.getHostPageBaseURL().replaceFirst("http", "ws").trim();

        while (host.endsWith("/"))
        {
            if (host.length() > 0)
            {
                host = host.substring(0, host.length() - 1).trim();
            }
        }
        while (path.endsWith("/"))
        {
            if (path.length() > 0)
            {
                path = path.substring(0, path.length() - 1).trim();
            }
        }
        while (path.startsWith("/"))
        {
            if (path.length() > 0)
            {
                path = path.substring(1).trim();
            }
        }
        return host + "/" + path;
    }
}