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

import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NValue;
import com.ait.tooling.nativetools.client.util.Client;

public final class NRestingResponse implements IRestingResponse
{
    private NObject               m_json;

    private final int             m_code;

    private final long            m_time;

    private final String          m_body;

    private final boolean         m_good;

    private final NMethod         m_type;

    private final NRestingHeaders m_head;

    private final IRestingRequest m_requ;

    public NRestingResponse(final int code, final String body, final NRestingHeaders head, final NMethod type, final IRestingRequest requ, final long time)
    {
        m_code = code;

        m_body = body;

        m_head = head;

        m_type = type;

        m_requ = requ;

        m_time = time;

        m_good = ((code >= 200) && (code < 300));
    }

    @Override
    public final int code()
    {
        return m_code;
    }

    @Override
    public final String body()
    {
        return m_body;
    }

    @Override
    public final NObject json()
    {
        if (null == m_json)
        {
            if (good())
            {
                final String body = Operations.clean(body());

                if (null != body)
                {
                    try
                    {
                        final NValue<?> parsed = NUtils.JSON.parse(body);

                        if (null != parsed)
                        {
                            m_json = parsed.asNObject();
                        }
                    }
                    catch (Exception e)
                    {
                        Client.get().error("NResponse.json()", e);
                    }
                }
            }
        }
        return m_json;
    }

    @Override
    public final NRestingHeaders headers()
    {
        return m_head;
    }

    @Override
    public final NMethod method()
    {
        return m_type;
    }

    @Override
    public final IRestingRequest getRequest()
    {
        return m_requ;
    }

    @Override
    public final boolean good()
    {
        return m_good;
    }

    @Override
    public final long time()
    {
        return m_time;
    }
}
