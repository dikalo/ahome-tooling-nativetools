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

import com.google.gwt.http.client.Request;

public class NRestingRequest implements IRestingRequest
{
    private final String          m_rurl;

    private final Request         m_rqst;

    private final NMethod          m_type;

    private final NRestingHeaders m_head;

    public NRestingRequest(final String rurl, final NRestingHeaders head, final NMethod type, final Request rqst)
    {
        m_rqst = rqst;

        m_rurl = rurl;

        m_head = head;

        m_type = type;
    }

    @Override
    public String url()
    {
        return m_rurl;
    }

    @Override
    public void cancel()
    {
        m_rqst.cancel();
    }

    @Override
    public boolean isPending()
    {
        return m_rqst.isPending();
    }

    @Override
    public NMethod method()
    {
        return m_type;
    }

    @Override
    public NRestingHeaders headers()
    {
        return m_head;
    }
}
