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

package com.ait.tooling.nativetools.client.storage;

import java.util.Objects;

import com.ait.tooling.nativetools.client.event.HandlerRegistrationManager;
import com.ait.tooling.nativetools.client.storage.event.LocalStorageEvent;
import com.ait.tooling.nativetools.client.storage.event.LocalStorageHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageEvent;

public final class LocalStorage extends AbstractStorage
{
    private static LocalStorage INSTANCE;

    private HandlerManager      m_events;

    private LocalStorage()
    {
        super(CacheType.LOCAL, Storage.getLocalStorageIfSupported());
    }

    public static final LocalStorage get()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new LocalStorage();
        }
        return INSTANCE;
    }

    public final HandlerRegistration addLocalStorageHandler(LocalStorageHandler handler)
    {
        handler = Objects.requireNonNull(handler);

        if (null == m_events)
        {
            m_events = new HandlerManager(this);
        }
        if (isSupported())
        {
            final HandlerRegistration proxy = Storage.addStorageEventHandler(new StorageEvent.Handler()
            {
                @Override
                public void onStorageChange(final StorageEvent event)
                {
                    if (isLocalStorage(event))
                    {
                        m_events.fireEvent(new LocalStorageEvent(get(), event.getKey(), event.getOldValue(), event.getNewValue()));
                    }
                }
            });
            return new HandlerRegistrationManager(m_events.addHandler(LocalStorageEvent.TYPE, handler), proxy);
        }
        return m_events.addHandler(LocalStorageEvent.TYPE, handler);
    }
}
