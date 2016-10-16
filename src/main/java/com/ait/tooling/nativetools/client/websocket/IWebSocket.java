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

public interface IWebSocket<T>
{
    public static final int STATE_CONNECTING = 0;

    public static final int STATE_OPEN       = 1;

    public static final int STATE_CLOSING    = 2;

    public static final int STATE_CLOSED     = 3;

    public void send(T message);

    public void close();

    public int getBufferedAmount();

    public int getReadyState();

    public String getURL();

    public String getProtocol();

    public boolean isOpen();
}
