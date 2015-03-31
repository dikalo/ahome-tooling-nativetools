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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NValue;
import com.ait.tooling.nativetools.client.util.Console;
import com.ait.tooling.nativetools.client.util.Logging;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageEvent;

abstract class AbstractStorage implements IClientStorage
{
    private final CacheType m_type;

    private final Storage   m_storage;

    protected AbstractStorage(final CacheType type, Storage storage)
    {
        m_type = type;

        if (null != storage)
        {
            try
            {
                final String test = "__" + type + "__";

                storage.setItem(test, test);

                storage.removeItem(test);
            }
            catch (Exception e)
            {
                storage = null;

                error(type + " is not enabled, Browser may be in private mode: " + e.getMessage());
            }
        }
        m_storage = storage;
    }

    @Override
    public final CacheType getType()
    {
        return m_type;
    }

    @Override
    public final boolean isSupported()
    {
        return (null != m_storage);
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
        if (isSupported())
        {
            final int size = m_storage.getLength();

            final ArrayList<String> keys = new ArrayList<String>(size);

            for (int i = 0; i < size; i++)
            {
                keys.add(m_storage.key(i));
            }
            return Collections.unmodifiableList(keys);
        }
        return Collections.unmodifiableList(new ArrayList<String>(0));
    }

    @Override
    public final void clear()
    {
        if (isSupported())
        {
            m_storage.clear();
        }
    }

    @Override
    public final void remove(final String key)
    {
        if (isSupported())
        {
            m_storage.removeItem(Objects.requireNonNull(key));
        }
    }

    @Override
    public final String getString(final String key)
    {
        if (isSupported())
        {
            return m_storage.getItem(Objects.requireNonNull(key));
        }
        return null;
    }

    @Override
    public final void putString(final String key, final String value)
    {
        if (isSupported())
        {
            if (null == value)
            {
                m_storage.removeItem(Objects.requireNonNull(key));
            }
            else
            {
                m_storage.setItem(Objects.requireNonNull(key), value);
            }
        }
    }

    @Override
    public final NObject getNObject(final String key)
    {
        if (isSupported())
        {
            return toNObject(getString(Objects.requireNonNull(key)));
        }
        return null;
    }

    @Override
    public final void putNObject(final String key, final NObject value)
    {
        if (isSupported())
        {
            if (null != value)
            {
                m_storage.setItem(Objects.requireNonNull(key), value.toJSONString());
            }
            else
            {
                remove(Objects.requireNonNull(key));
            }
        }
    }

    @Override
    public final NArray getNArray(final String key)
    {
        if (isSupported())
        {
            return toNArray(getString(Objects.requireNonNull(key)));
        }
        return null;
    }

    @Override
    public final void putNArray(final String key, final NArray value)
    {
        if (isSupported())
        {
            if (null != value)
            {
                m_storage.setItem(Objects.requireNonNull(key), value.toJSONString());
            }
            else
            {
                remove(Objects.requireNonNull(key));
            }
        }
    }

    private final void error(String message)
    {
        Console.get().error(message);

        Logging.get().error(message);
    }

    public static final NObject toNObject(String string)
    {
        string = StringOps.toTrimOrNull(string);

        if (null != string)
        {
            try
            {
                final NValue<?> parsed = NUtils.JSON.parse(string);

                if (null == parsed)
                {
                    return null;
                }
                return parsed.asNObject();
            }
            catch (Exception e)
            {
                return null;
            }
        }
        return null;
    }

    public static final NArray toNArray(String string)
    {
        string = StringOps.toTrimOrNull(string);

        if (null != string)
        {
            try
            {
                final NValue<?> parsed = NUtils.JSON.parse(string);

                if (null == parsed)
                {
                    return null;
                }
                return parsed.asNArray();
            }
            catch (Exception e)
            {
                return null;
            }
        }
        return null;
    }

    protected final native boolean isLocalStorage(StorageEvent event)
    /*-{
		if (event.storageArea === $wnd["localStorage"]) {
			return true;
		} else {
			return false;
		}
    }-*/;
}
