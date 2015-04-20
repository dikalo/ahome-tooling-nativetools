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

import com.ait.tooling.common.api.java.util.UUID;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

public final class Client implements ILogging
{
    private static Client INSTANCE;

    private String        m_cliuuid;

    private ILogging      m_logging;

    private Client()
    {
    }

    public static final Client get()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new Client();

            INSTANCE.setLogging(ComboLogging.get());
        }
        return INSTANCE;
    }

    public final void setLogging(final ILogging logging)
    {
        if ((null != logging) && (logging != this))
        {
            m_logging = logging;
        }
        else
        {
            m_logging = ComboLogging.get();
        }
    }

    public final String getClientUUID()
    {
        if (null == m_cliuuid)
        {
            m_cliuuid = UUID.uuid();
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
        m_logging.error(message, e);
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
        m_logging.severe(message, e);
    }

    public final void injectJs(final TextResource js)
    {
        injectJs(js.getText());
    }

    public final void injectJs(final String js)
    {
        ScriptInjector.fromString(js).setWindow(ScriptInjector.TOP_WINDOW).inject();
    }

    public final void injectStyle(final String css)
    {
        StyleInjector.inject(css);
    }

    public final void injectStyle(final TextResource css)
    {
        StyleInjector.inject(css.getText());
    }
}
