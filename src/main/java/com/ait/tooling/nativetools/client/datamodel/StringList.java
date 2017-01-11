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

package com.ait.tooling.nativetools.client.datamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.ait.tooling.nativetools.client.NArray;

public class StringList extends AbstractListDataModel<String>implements Iterable<String>
{
    public StringList()
    {
        super(new NArray());
    }

    public StringList(final NArray array)
    {
        super((null == array) ? (new NArray()) : (array));
    }

    public StringList(final String value)
    {
        super(new NArray());

        push(value);
    }

    public StringList(final String value, final String... values)
    {
        super(new NArray());

        push(value);

        for (String v : values)
        {
            push(v);
        }
    }

    public StringList(final Collection<String> values)
    {
        super(new NArray());

        if (null != values)
        {
            for (String s : values)
            {
                push(s);
            }
        }
    }

    public StringList(final StringList list)
    {
        super((list == null) ? new NArray() : list.getModel().copy());
    }

    public StringList copy()
    {
        return new StringList(this);
    }

    public final int indexOf(final String item)
    {
        if (null != item)
        {
            final NArray list = getModel();

            final int size = list.size();

            for (int i = 0; i < size; i++)
            {
                if (item.equals(list.getAsString(i)))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public final boolean contains(final String item)
    {
        if (null != item)
        {
            final NArray list = getModel();

            final int size = list.size();

            for (int i = 0; i < size; i++)
            {
                if (item.equals(list.getAsString(i)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public final String get(final int index)
    {
        return getModel().getAsString(index);
    }

    @Override
    public final void push(final String item)
    {
        getModel().push(item);
    }

    @Override
    public final void remove(final String item)
    {
        if (null != item)
        {
            final NArray list = getModel();

            final int size = list.size();

            for (int i = 0; i < size; i++)
            {
                if (item.equals(list.getAsString(i)))
                {
                    remove(i);

                    return;
                }
            }
        }
    }

    public final boolean different(final StringList other)
    {
        if (null == other)
        {
            return true;
        }
        if (this == other)
        {
            return false;
        }
        final NArray list = getModel();

        final NArray olst = other.getModel();

        final int size = olst.size();

        if (size != list.size())
        {
            return true;
        }
        for (int i = 0; i < size; i++)
        {
            final String lstr = list.getAsString(i);

            final String ostr = olst.getAsString(i);

            if (null == lstr)
            {
                if (null != ostr)
                {
                    return true;
                }
            }
            else if (false == lstr.equals(ostr))
            {
                return true;
            }
        }
        return false;
    }

    public final boolean same(final StringList other)
    {
        return (false == different(other));
    }

    @Override
    public final void set(final int index, final String item)
    {
        getModel().set(index, item);
    }

    @Override
    public final void unshift(final String item)
    {
        getModel().unshift(item);
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other instanceof StringList)
        {
            return same((StringList) other);
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return toJSONString().hashCode();
    }

    @Override
    public final String[] toArray()
    {
        final NArray list = getModel();

        final int size = list.size();

        final String[] array = new String[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = list.getAsString(i);
        }
        return array;
    }

    @Override
    public final Collection<String> toCollection()
    {
        final NArray list = getModel();

        final int size = list.size();

        final ArrayList<String> array = new ArrayList<String>(size);

        for (int i = 0; i < size; i++)
        {
            array.add(list.getAsString(i));
        }
        return Collections.unmodifiableList(array);
    }

    @Override
    public final Iterator<String> iterator()
    {
        return toCollection().iterator();
    }
}
