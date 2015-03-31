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

public final class NFastIntegerArray extends NFastPrimitiveArrayBase<NFastIntegerArray, NFastIntegerArrayJSO>
{
    public NFastIntegerArray(final NFastIntegerArrayJSO jso)
    {
        super((null == jso) ? NFastIntegerArrayJSO.make() : jso);
    }

    public NFastIntegerArray()
    {
        super(NFastIntegerArrayJSO.make());
    }

    public NFastIntegerArray(final int d, final int... list)
    {
        super(NFastIntegerArrayJSO.make(d, list));
    }

    public final int[] toArray()
    {
        return getJSO().toArray();
    }

    public final NFastIntegerArray push(final int d, final int... list)
    {
        push(d);

        final int size = list.length;

        for (int i = 0; i < size; i++)
        {
            push(list[i]);
        }
        return this;
    }

    public final NFastIntegerArray push(final int d)
    {
        getJSO().push(d);

        return this;
    }

    public final NFastIntegerArray set(final int indx, final int value)
    {
        getJSO().set(indx, value);

        return this;
    }

    public final int get(final int indx)
    {
        return getJSO().get(indx);
    }

    public final int pop()
    {
        return getJSO().pop();
    }

    public final int shift()
    {
        return getJSO().shift();
    }

    public final boolean contains(final int value)
    {
        return getJSO().contains(value);
    }

    public final NFastIntegerArray sort()
    {
        return new NFastIntegerArray(getJSO().sort());
    }

    public final NFastIntegerArray uniq()
    {
        return new NFastIntegerArray(getJSO().uniq());
    }
}
