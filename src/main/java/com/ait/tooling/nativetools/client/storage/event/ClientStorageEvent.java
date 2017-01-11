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

package com.ait.tooling.nativetools.client.storage.event;

import com.ait.tooling.nativetools.client.storage.IClientStorage;
import com.google.gwt.event.shared.GwtEvent;

public final class ClientStorageEvent extends GwtEvent<ClientStorageHandler>
{
    public static final Type<ClientStorageHandler> TYPE = new Type<ClientStorageHandler>();

    private final IClientStorage                   m_stg;

    private final String                           m_key;

    private final String                           m_old;

    private final String                           m_now;

    public ClientStorageEvent(final IClientStorage stg, final String key, final String old, final String now)
    {
        m_stg = stg;

        m_key = key;

        m_old = old;

        m_now = now;
    }

    public final IClientStorage getStorage()
    {
        return m_stg;
    }

    public final String getKey()
    {
        return m_key;
    }

    public final String getOld()
    {
        return m_old;
    }

    public final String getNow()
    {
        return m_now;
    }

    @Override
    public final Type<ClientStorageHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(final ClientStorageHandler handler)
    {
        handler.onClientStorageChange(this);
    }
}
