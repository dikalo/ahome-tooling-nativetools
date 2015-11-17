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

package com.ait.tooling.nativetools.client.util;

import java.util.Objects;

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

    private static final native void clear_0()
    /*-{
		if (!!$wnd.console) {
			$wnd.console.clear();
		}
    }-*/;

    private static final native void count_0(String message)
    /*-{
		if (!!$wnd.console) {
			$wnd.console.count(message);
		}
    }-*/;

    private static final native void group_beg_0(String message)
    /*-{
		if (!!$wnd.console) {
			$wnd.console.group(message);
		}
    }-*/;

    private static final native void group_end_0()
    /*-{
		if (!!$wnd.console) {
			$wnd.console.groupEnd();
		}
    }-*/;

    private static final native void profile_beg_0(String message)
    /*-{
		if (!!$wnd.console) {
			if ($wnd.console.profile !== undefined) {
				$wnd.console.profile(message);
			}
		}
    }-*/;

    private static final native void profile_end_0()
    /*-{
		if (!!$wnd.console) {
			if ($wnd.console.profileEnd !== undefined) {
				$wnd.console.profileEnd();
			}
		}
    }-*/;

    private static final native void time_beg_0(String message)
    /*-{
		if (!!$wnd.console) {
			if ($wnd.console.time !== undefined) {
				$wnd.console.time(message);
			}
		}
    }-*/;

    private static final native void time_end_0(String message)
    /*-{
		if (!!$wnd.console) {
			if ($wnd.console.timeEnd !== undefined) {
				$wnd.console.timeEnd(message);
			}
		}
    }-*/;

    private static final native void timestamp_0(String message)
    /*-{
		if (!!$wnd.console) {
			if ($wnd.console.timestamp !== undefined) {
				$wnd.console.timestamp(message);
			}
		}
    }-*/;

    private static final native void trace_0(String message)
    /*-{
		if (!!$wnd.console) {
			if ($wnd.console.trace !== undefined) {
				$wnd.console.trace(message);
			}
		}
    }-*/;

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

    public final void clear()
    {
        clear_0();
    }

    public final void count(final String message)
    {
        count_0(Objects.requireNonNull(message));
    }

    public final void groupBeg(final String message)
    {
        group_beg_0(Objects.requireNonNull(message));
    }

    public final void groupEnd()
    {
        group_end_0();
    }

    public final void profileBeg(final String message)
    {
        profile_beg_0(Objects.requireNonNull(message));
    }

    public final void profileEnd()
    {
        profile_end_0();
    }

    public final void timeBeg(final String message)
    {
        time_beg_0(Objects.requireNonNull(message));
    }

    public final void timeEnd(final String message)
    {
        time_end_0(Objects.requireNonNull(message));
    }

    public final void timeStamp(final String message)
    {
        timestamp_0(Objects.requireNonNull(message));
    }

    public final void trace(final String message)
    {
        trace_0(Objects.requireNonNull(message));
    }

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
