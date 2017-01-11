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

package com.ait.tooling.nativetools.client.usermedia;

import com.google.gwt.core.client.JavaScriptObject;

public final class UserMediaStream extends JavaScriptObject
{
    protected UserMediaStream()
    {
    }

    public final native void stop()
    /*-{
		return this.stop();
    }-*/;

    public final native boolean isActive()
    /*-{
		return this.active;
    }-*/;

    public final native String getID()
    /*-{
		return this.id;
    }-*/;
}
