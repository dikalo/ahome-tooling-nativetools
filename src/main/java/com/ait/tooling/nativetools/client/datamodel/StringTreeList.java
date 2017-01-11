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

public final class StringTreeList extends AbstractJSONListDataModel<StringTree>implements Iterable<StringTree>
{
    public StringTreeList()
    {
        super(new NArray());
    }

    public StringTreeList(final NArray array)
    {
        super((null == array) ? (new NArray()) : (array));
    }

    @Override
    public final StringTree get(final int index)
    {
        return new StringTree(getModel().getAsObject(index));
    }

    @Override
    public final StringTree[] toArray()
    {
        final int size = size();

        final StringTree[] array = new StringTree[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = get(i);
        }
        return array;
    }

    @Override
    public final Collection<StringTree> toCollection()
    {
        final int size = size();

        final ArrayList<StringTree> array = new ArrayList<StringTree>(size);

        for (int i = 0; i < size; i++)
        {
            array.add(get(i));
        }
        return Collections.unmodifiableList(array);
    }

    @Override
    public final Iterator<StringTree> iterator()
    {
        return toCollection().iterator();
    }
}
