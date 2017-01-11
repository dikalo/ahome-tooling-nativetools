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

package com.ait.tooling.nativetools.client.resting;

import com.google.gwt.http.client.Request;

public final class NRestingRequest implements IRestingRequest
{
    private final long            m_cntr;

    private final long            m_time;

    private final String          m_rurl;

    private final Request         m_rqst;

    private final NMethod         m_type;

    private final NRestingHeaders m_head;

    public NRestingRequest(final String rurl, final NRestingHeaders head, final NMethod type, final long cntr, final long time, final Request rqst)
    {
        m_rqst = rqst;

        m_rurl = rurl;

        m_head = head;

        m_type = type;

        m_cntr = cntr;

        m_time = time;
    }

    @Override
    public final String url()
    {
        return m_rurl;
    }

    @Override
    public final void cancel()
    {
        m_rqst.cancel();
    }

    @Override
    public final boolean pending()
    {
        return m_rqst.isPending();
    }

    @Override
    public final NMethod method()
    {
        return m_type;
    }

    @Override
    public final NRestingHeaders headers()
    {
        return m_head;
    }

    @Override
    public final long start()
    {
        return m_time;
    }

    @Override
    public final long count()
    {
        return m_cntr;
    }
}
