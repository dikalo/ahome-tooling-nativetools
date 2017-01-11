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

public final class NFastDoubleArray extends NFastPrimitiveArrayBase<NFastDoubleArray, NFastDoubleArrayJSO>
{
    public NFastDoubleArray(final NFastDoubleArrayJSO jso)
    {
        super((null == jso) ? NFastDoubleArrayJSO.make() : jso);
    }

    public NFastDoubleArray()
    {
        super(NFastDoubleArrayJSO.make());
    }

    public NFastDoubleArray(final double d, final double... list)
    {
        super(NFastDoubleArrayJSO.make(d, list));
    }

    public final double[] toArray()
    {
        return getJSO().toArray();
    }

    public final NFastDoubleArray push(final double d, final double... list)
    {
        getJSO().push(d, list);

        return this;
    }

    public final NFastDoubleArray push(final double d)
    {
        getJSO().push(d);

        return this;
    }

    public final NFastDoubleArray set(final int indx, final double value)
    {
        getJSO().set(indx, value);

        return this;
    }

    public final double get(final int indx)
    {
        return getJSO().get(indx);
    }

    public final double pop()
    {
        return getJSO().pop();
    }

    public final double shift()
    {
        return getJSO().shift();
    }

    public final boolean contains(final double value)
    {
        return getJSO().contains(value);
    }

    public final NFastDoubleArray sort()
    {
        return new NFastDoubleArray(getJSO().sort());
    }

    public final NFastDoubleArray uniq()
    {
        return new NFastDoubleArray(getJSO().uniq());
    }
}
