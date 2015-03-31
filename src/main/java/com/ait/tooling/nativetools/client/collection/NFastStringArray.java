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

package com.ait.tooling.nativetools.client.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public final class NFastStringArray extends NFastPrimitiveArrayBase<NFastStringArray, NFastStringArrayJSO> implements Iterable<String>
{
    public NFastStringArray(final NFastStringArrayJSO jso)
    {
        super((null == jso) ? NFastStringArrayJSO.make() : jso);
    }

    public NFastStringArray()
    {
        super(NFastStringArrayJSO.make());
    }

    public NFastStringArray(final String d, final String... list)
    {
        super(NFastStringArrayJSO.make(d, list));
    }

    public final String[] toArray()
    {
        return getJSO().toArray();
    }

    public final NFastStringArray push(final String d, final String... list)
    {
        push(d);

        final int size = list.length;

        for (int i = 0; i < size; i++)
        {
            push(list[i]);
        }
        return this;
    }

    public final NFastStringArray push(final String d)
    {
        getJSO().push(d);

        return this;
    }

    public final NFastStringArray set(final int indx, final String value)
    {
        getJSO().set(indx, value);

        return this;
    }

    public final String get(final int indx)
    {
        return getJSO().get(indx);
    }

    public final String pop()
    {
        return getJSO().pop();
    }

    public final String shift()
    {
        return getJSO().shift();
    }

    public final boolean contains(final String value)
    {
        return getJSO().contains(value);
    }

    public final NFastStringArray sort()
    {
        return new NFastStringArray(getJSO().sort());
    }

    public final NFastStringArray uniq()
    {
        return new NFastStringArray(getJSO().uniq());
    }

    @Override
    public final Iterator<String> iterator()
    {
        return Collections.unmodifiableList(Arrays.asList(toArray())).iterator();
    }
}
