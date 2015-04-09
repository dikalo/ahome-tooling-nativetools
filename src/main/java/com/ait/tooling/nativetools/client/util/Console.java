/*
   Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.

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

package com.ait.tooling.nativetools.client.util;

public final class Console implements ILogging
{
    private static Console INSTANCE;

    public static final Console get()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new Console();
        }
        return INSTANCE;
    }

    private Console()
    {
    }

    private static final native void log_0(String message)
    /*-{
		if (!!$wnd.console) {
			$wnd.console.log(message);
		}
    }-*/;

    private static final native void debug_0(String message)
    /*-{
		if (!!$wnd.console) {
			$wnd.console.debug(message);
		}
    }-*/;

    private static final native void error_0(String message)
    /*-{
		if (!!$wnd.console) {
			$wnd.console.error(message);
		}
    }-*/;
    
    private static final native void warn_0(String message)
    /*-{
        if (!!$wnd.console) {
            $wnd.console.warn(message);
        }
    }-*/;

    @Override
    public void info(String message)
    {
        log_0("" + message);
    }

    @Override
    public void severe(String message)
    {
        error_0("SEVERE: " + message);
    }

    @Override
    public final void error(final String message)
    {
        error_0("ERROR: " + message);
    }

    @Override
    public void error(String message, Throwable e)
    {
        error_0("ERROR: " + message + " " + e.getMessage());
    }

    @Override
    public void fine(String message)
    {
        debug_0("" + message);
    }

    @Override
    public void warn(String message)
    {
        warn_0("" + message);
    }

    @Override
    public void severe(String message, Throwable e)
    {
        error_0("SEVERE: " + message + " " + e.getMessage());
    }
}
