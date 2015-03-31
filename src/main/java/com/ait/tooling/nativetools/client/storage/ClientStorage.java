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

package com.ait.tooling.nativetools.client.storage;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.ait.tooling.nativetools.client.storage.event.ClientStorageEvent;
import com.ait.tooling.nativetools.client.storage.event.ClientStorageHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public final class ClientStorage implements IClientStorage
{
    private static ClientStorage         INSTANCE;

    private HandlerManager               m_events;

    private final NFastStringMap<String> m_storage = new NFastStringMap<String>();

    private ClientStorage()
    {
    }

    public static final ClientStorage get()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new ClientStorage();
        }
        return INSTANCE;
    }

    @Override
    public final CacheType getType()
    {
        return CacheType.CLIENT;
    }

    @Override
    public final boolean isSupported()
    {
        return true;
    }

    @Override
    public boolean copyFrom(final NObject properties)
    {
        if ((null != properties) && (isSupported()))
        {
            for (String key : properties.keys())
            {
                switch (properties.getNativeTypeOf(key))
                {
                    case ARRAY:
                    {
                        final NArray value = properties.getAsArray(key);

                        if (null != value)
                        {
                            putNArray(key, value);
                        }
                    }
                        break;
                    case OBJECT:
                    {
                        final NObject value = properties.getAsObject(key);

                        if (null != value)
                        {
                            putNObject(key, value);
                        }
                    }
                        break;
                    case STRING:
                    {
                        final String value = properties.getAsString(key);

                        if (null != value)
                        {
                            putString(key, value);
                        }
                    }
                        break;
                    case NUMBER:
                    {
                        putString(key, String.valueOf(properties.getAsDouble(key)));
                    }
                        break;
                    case BOOLEAN:
                    {
                        putString(key, String.valueOf(properties.getAsBoolean(key)));
                    }
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean copyFrom(final Map<String, ?> map)
    {
        if ((null != map) && (isSupported()))
        {
            for (String key : map.keySet())
            {
                final Object val = map.get(key);

                if (val != null)
                {
                    putString(key, val.toString());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean copyFrom(final IClientStorage storage)
    {
        if ((null != storage) && (isSupported()) && (storage.isSupported()) && (storage.getType() != getType()))
        {
            for (String key : storage.keys())
            {
                putString(key, storage.getString(key));
            }
            return true;
        }
        return false;
    }

    @Override
    public final Collection<String> keys()
    {
        return m_storage.keys();
    }

    @Override
    public final void clear()
    {
        m_storage.clear();
    }

    @Override
    public final void remove(final String key)
    {
        remAndDispatch(Objects.requireNonNull(key));
    }

    @Override
    public final String getString(final String key)
    {
        return m_storage.get(Objects.requireNonNull(key));
    }

    @Override
    public final void putString(final String key, final String value)
    {
        if (null == value)
        {
            remAndDispatch(Objects.requireNonNull(key));
        }
        else
        {
            putAndDispatch(Objects.requireNonNull(key), value);
        }
    }

    @Override
    public final NObject getNObject(final String key)
    {
        return AbstractStorage.toNObject(getString(Objects.requireNonNull(key)));
    }

    @Override
    public final void putNObject(final String key, final NObject value)
    {
        if (null == value)
        {
            remAndDispatch(Objects.requireNonNull(key));
        }
        else
        {
            putAndDispatch(Objects.requireNonNull(key), value.toJSONString());
        }
    }

    @Override
    public final NArray getNArray(final String key)
    {
        return AbstractStorage.toNArray(getString(Objects.requireNonNull(key)));
    }

    @Override
    public final void putNArray(String key, NArray value)
    {
        if (null == value)
        {
            remAndDispatch(Objects.requireNonNull(key));
        }
        else
        {
            putAndDispatch(Objects.requireNonNull(key), value.toJSONString());
        }
    }

    public final HandlerRegistration addClientStorageHandler(final ClientStorageHandler handler)
    {
        if (null == m_events)
        {
            m_events = new HandlerManager(this);
        }
        return m_events.addHandler(ClientStorageEvent.TYPE, Objects.requireNonNull(handler));
    }

    private final void putAndDispatch(final String key, final String value)
    {
        if ((null != m_events) && (m_events.getHandlerCount(ClientStorageEvent.TYPE) > 0))
        {
            final String old = m_storage.get(key);

            m_storage.put(key, value);

            m_events.fireEvent(new ClientStorageEvent(get(), key, old, value));
        }
        else
        {
            m_storage.put(key, value);
        }
    }

    private final void remAndDispatch(final String key)
    {
        if ((null != m_events) && (m_events.getHandlerCount(ClientStorageEvent.TYPE) > 0))
        {
            final String old = m_storage.get(key);

            m_storage.remove(key);

            m_events.fireEvent(new ClientStorageEvent(get(), key, old, null));
        }
        else
        {
            m_storage.remove(key);
        }
    }
}
