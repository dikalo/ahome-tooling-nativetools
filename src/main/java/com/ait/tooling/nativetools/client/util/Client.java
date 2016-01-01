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

import com.ait.tooling.common.api.java.util.IHTTPConstants;
import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.java.util.UUID;
import com.ait.tooling.nativetools.client.security.XSS;
import com.ait.tooling.nativetools.client.storage.ClientStorage;
import com.ait.tooling.nativetools.client.storage.LocalStorage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;

public final class Client implements ILogging, IHTTPConstants
{
    private static final Client INSTANCE = new Client();

    private ILogging            m_logging = ComboLogging.get();

    private String              m_cliuuid = null;

    private boolean             m_unwraps = true;

    private boolean             m_dostack = true;

    private Client()
    {
    }

    public static final Client get()
    {
        return INSTANCE;
    }

    public final Client setDefaultUncaughtExceptionHandler()
    {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler()
        {
            @Override
            public void onUncaughtException(final Throwable e)
            {
                error("Uncaught Exception:", e);
            }
        });
        return this;
    }

    public final Client setLogging(final ILogging logging)
    {
        if ((null != logging) && (logging != this))
        {
            m_logging = logging;
        }
        else
        {
            m_logging = ComboLogging.get();
        }
        return this;
    }

    public final Client setUmbrellaExceptionUnwrapped(final boolean unwraps)
    {
        m_unwraps = unwraps;

        return this;
    }

    public final boolean isUmbrellaExceptionUnwrapped()
    {
        return m_unwraps;
    }

    public final Client setLoggingStackElements(final boolean dostack)
    {
        m_dostack = dostack;

        return this;
    }

    public final boolean isLoggingStackElements()
    {
        return m_dostack;
    }

    /*
     * This should uniquely identify the browser by UUID in ClientStorage till cache is cleared.
     * There is no other way to do "user browser identification" without getting REALLY, REALLY,
     * REALLY hacky!!! ( zombie cookies, Canvas Finger printing, etc. ), which are all nefarious
     * and now well recieved, for good reason.
     * 
     * Extra checks to rule out LocalStorage cache poisoning ( OK Laugh, I have seen it happen )
     * 
     * DSJ
     * 
     */
    public final String getClientUUID()
    {
        if (null == m_cliuuid)
        {
            if (LocalStorage.get().isSupported())
            {
                m_cliuuid = StringOps.toTrimOrNull(LocalStorage.get().getString(X_CLIENT_UUID_HEADER));
            }
            if (null == m_cliuuid)
            {
                m_cliuuid = StringOps.toTrimOrNull(ClientStorage.get().getString(X_CLIENT_UUID_HEADER));
            }
            if (null == m_cliuuid)
            {
                m_cliuuid = UUID.uuid();

                if (LocalStorage.get().isSupported())
                {
                    LocalStorage.get().putString(X_CLIENT_UUID_HEADER, m_cliuuid);
                }
                else
                {
                    ClientStorage.get().putString(X_CLIENT_UUID_HEADER, m_cliuuid);
                }
            }
            else
            {
                final String temp = XSS.get().clean(m_cliuuid);

                if (false == temp.equals(m_cliuuid))
                {
                    m_cliuuid = temp;

                    if (LocalStorage.get().isSupported())
                    {
                        LocalStorage.get().putString(X_CLIENT_UUID_HEADER, m_cliuuid);
                    }
                    else
                    {
                        ClientStorage.get().putString(X_CLIENT_UUID_HEADER, m_cliuuid);
                    }
                }
            }
        }
        return m_cliuuid;
    }

    @Override
    public final void info(final String message)
    {
        m_logging.info(message);
    }

    @Override
    public final void severe(final String message)
    {
        m_logging.severe(message);
    }

    @Override
    public final void error(final String message)
    {
        m_logging.error(message);
    }

    @Override
    public final void error(final String message, final Throwable e)
    {
        if (isUmbrellaExceptionUnwrapped() && (e instanceof UmbrellaException))
        {
            final UmbrellaException u = ((UmbrellaException) e);

            for (Throwable t : u.getCauses())
            {
                error(message, t);
            }
            return;
        }
        m_logging.error(message, e);

        if (isLoggingStackElements())
        {
            for (StackTraceElement s : e.getStackTrace())
            {
                m_logging.error(s.toString());
            }
        }
    }

    @Override
    public final void fine(final String message)
    {
        m_logging.fine(message);
    }

    @Override
    public final void warn(final String message)
    {
        m_logging.warn(message);
    }

    @Override
    public final void severe(final String message, final Throwable e)
    {
        if (isUmbrellaExceptionUnwrapped() && (e instanceof UmbrellaException))
        {
            final UmbrellaException u = ((UmbrellaException) e);

            for (Throwable t : u.getCauses())
            {
                severe(message, t);
            }
            return;
        }
        m_logging.severe(message, e);

        if (isLoggingStackElements())
        {
            for (StackTraceElement s : e.getStackTrace())
            {
                m_logging.severe(s.toString());
            }
        }
    }

    public final Client injectJs(final TextResource js)
    {
        return injectJs(js.getText());
    }

    public final Client injectJs(final TextResource js, final boolean removeTag)
    {
        return injectJs(js.getText(), removeTag);
    }

    public final Client injectJs(final String js)
    {
        ScriptInjector.fromString(js).setWindow(ScriptInjector.TOP_WINDOW).inject();

        return this;
    }

    public final Client injectJs(final String js, final boolean removeTag)
    {
        ScriptInjector.fromString(js).setRemoveTag(removeTag).setWindow(ScriptInjector.TOP_WINDOW).inject();

        return this;
    }

    public final Client injectStyle(final String css)
    {
        StyleInjector.inject(css);

        return this;
    }

    public final Client injectStyle(final TextResource css)
    {
        return injectStyle(css.getText());
    }

    public final Client injectStyle(final CssResource css)
    {
        css.ensureInjected();

        return this;
    }
}
