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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ait.tooling.common.api.logging.ILogging;
import com.google.gwt.core.client.GWT;

public final class Logging implements ILogging
{
    private static Logging INSTANCE;

    private final Logger   m_logger;

    public static final Logging get()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new Logging();
        }
        return INSTANCE;
    }

    private Logging()
    {
        m_logger = Logger.getLogger(GWT.getModuleName() + "_logger");
    }

    @Override
    public final void info(String message)
    {
        m_logger.log(Level.INFO, message);
    }

    @Override
    public final void severe(String message)
    {
        m_logger.log(Level.SEVERE, "SEVERE: " + message);
    }

    @Override
    public final void error(String message)
    {
        m_logger.log(Level.SEVERE, "ERROR: " + message);
    }

    @Override
    public final void error(String message, Throwable e)
    {
        m_logger.log(Level.SEVERE, "ERROR: " + message + " " + e.getMessage());
    }

    @Override
    public final void fine(String message)
    {
        m_logger.log(Level.FINE, message);
    }

    @Override
    public final void warn(String message)
    {
        m_logger.log(Level.WARNING, message);
    }

    @Override
    public final void severe(String message, Throwable e)
    {
        m_logger.log(Level.SEVERE, "SEVERE: " + message + " " + e.getMessage());
    }
}
