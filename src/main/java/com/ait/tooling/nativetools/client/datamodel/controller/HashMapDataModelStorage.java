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

package com.ait.tooling.nativetools.client.datamodel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.java.util.function.Consumer;
import com.ait.tooling.common.api.java.util.function.Function;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.datamodel.AbstractJSONDataModel;
import com.ait.tooling.nativetools.client.datamodel.ModelIDList;

public final class HashMapDataModelStorage<T extends AbstractJSONDataModel> implements IDataModelStorage<T>
{
    private final HashMap<String, T>   m_storage = new HashMap<String, T>();

    private final String               m_name;

    private final Function<NObject, T> m_builder;

    public HashMapDataModelStorage(final String name, final Function<NObject, T> builder)
    {
        m_name = StringOps.requireTrimOrNull(name);

        m_builder = Objects.requireNonNull(builder);
    }

    @Override
    public final void put(final String id, final T value)
    {
        m_storage.put(id, value);
    }

    @Override
    public final void remove(final String id)
    {
        m_storage.remove(id);
    }

    @Override
    public final T get(final String id)
    {
        return m_storage.get(id);
    }

    @Override
    public final boolean exists(final String id)
    {
        if (m_storage.containsKey(id))
        {
            return (null != m_storage.get(id));
        }
        return false;
    }

    @Override
    public final Collection<String> keys()
    {
        return m_storage.keySet();
    }

    @Override
    public final Collection<T> values()
    {
        return m_storage.values();
    }

    @Override
    public final String getVersion()
    {
        return "0";
    }

    @Override
    public final boolean isPersistent()
    {
        return false;
    }

    @Override
    public final void persist(final Consumer<Boolean> callback)
    {
        callback.accept(false);
    }

    @Override
    public final void refresh(final Consumer<Boolean> callback)
    {
        callback.accept(false);
    }

    @Override
    public Collection<T> get(final ModelIDList list)
    {
        final int size = list.size();

        final ArrayList<T> make = new ArrayList<T>(size);

        for (int i = 0; i < size; i++)
        {
            final String ikey = list.get(i);

            if (null != ikey)
            {
                final T valu = get(ikey);

                if (null != valu)
                {
                    make.add(valu);
                }
            }
        }
        return make;
    }

    @Override
    public void remove(final ModelIDList list)
    {
        final int size = list.size();

        for (int i = 0; i < size; i++)
        {
            final String ikey = list.get(i);

            if (null != ikey)
            {
                remove(ikey);
            }
        }
    }

    @Override
    public String getName()
    {
        return m_name;
    }

    @Override
    public final Function<NObject, T> getObjectBuilder()
    {
        return m_builder;
    }
}
