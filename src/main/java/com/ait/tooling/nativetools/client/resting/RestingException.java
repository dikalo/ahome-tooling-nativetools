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

package com.ait.tooling.nativetools.client.resting;

public class RestingException extends Exception
{
    private static final long serialVersionUID = 2272889457673600267L;

    private final long        m_cntr;

    private final long        m_time;

    private final String      m_rurl;

    private final NMethod     m_type;

    public RestingException(final String message, final NMethod type, final String rurl, final long cntr, final long time)
    {
        super(message);

        m_type = type;

        m_rurl = rurl;

        m_cntr = cntr;

        m_time = time;
    }

    public RestingException(final Throwable cause, final NMethod type, final String rurl, final long cntr, final long time)
    {
        super(cause);

        m_type = type;

        m_rurl = rurl;

        m_cntr = cntr;

        m_time = time;
    }

    public RestingException(final String message, final Throwable cause, final NMethod type, final String rurl, final long cntr, final long time)
    {
        super(message, cause);

        m_type = type;

        m_rurl = rurl;

        m_cntr = cntr;

        m_time = time;
    }

    public RestingException(final NMethod type, final String rurl, final long cntr, final long time)
    {
        m_type = type;

        m_rurl = rurl;

        m_cntr = cntr;

        m_time = time;
    }

    public final NMethod method()
    {
        return m_type;
    }

    public final String url()
    {
        return m_rurl;
    }

    public final long start()
    {
        return m_time;
    }

    public final long count()
    {
        return m_cntr;
    }
}
