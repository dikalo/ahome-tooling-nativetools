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

package com.ait.tooling.nativetools.client.collection;

public final class NFastBooleanArray extends NFastPrimitiveArrayBase<NFastBooleanArray, NFastBooleanArrayJSO>
{
    public NFastBooleanArray(final NFastBooleanArrayJSO jso)
    {
        super((null == jso) ? NFastBooleanArrayJSO.make() : jso);
    }

    public NFastBooleanArray()
    {
        super(NFastBooleanArrayJSO.make());
    }

    public NFastBooleanArray(final boolean d, final boolean... list)
    {
        super(NFastBooleanArrayJSO.make(d, list));
    }

    public final boolean[] toArray()
    {
        return getJSO().toArray();
    }

    public final NFastBooleanArray push(final boolean d, final boolean... list)
    {
        getJSO().push(d, list);

        return this;
    }

    public final NFastBooleanArray push(final boolean d)
    {
        getJSO().push(d);

        return this;
    }

    public final NFastBooleanArray set(final int indx, final boolean value)
    {
        getJSO().set(indx, value);

        return this;
    }

    public final boolean get(final int indx)
    {
        return getJSO().get(indx);
    }

    public final boolean pop()
    {
        return getJSO().pop();
    }

    public final boolean shift()
    {
        return getJSO().shift();
    }

    public final boolean contains(final boolean value)
    {
        return getJSO().contains(value);
    }

    public final NFastBooleanArray sort()
    {
        return new NFastBooleanArray(getJSO().sort());
    }

    public final NFastBooleanArray uniq()
    {
        return new NFastBooleanArray(getJSO().uniq());
    }
}
