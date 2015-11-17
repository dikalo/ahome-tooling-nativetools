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

package com.ait.tooling.nativetools.client.collection;

public class NFastDoubleArrayJSO extends NFastPrimitiveArrayBaseJSO<NFastDoubleArrayJSO>
{
    public static final NFastDoubleArrayJSO make(final double d, final double... list)
    {
        final NFastDoubleArrayJSO jso = make();

        jso.push(d, list);

        return jso;
    }

    public static final NFastDoubleArrayJSO make()
    {
        return createNArrayBaseJSO();
    }

    protected NFastDoubleArrayJSO()
    {
    }

    public final double[] toArray()
    {
        final int size = size();

        final double[] array = new double[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = get(i);
        }
        return array;
    }

    public final void push(final double d, final double... list)
    {
        push(d);

        final int size = list.length;

        for (int i = 0; i < size; i++)
        {
            push(list[i]);
        }
    }

    public final native void push(double value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native void set(int indx, double value)
    /*-{
		this[indx] = value;
    }-*/;

    public final native double get(int indx)
    /*-{
		return this[indx];
    }-*/;

    public final native double pop()
    /*-{
		return this.pop();
    }-*/;

    public final native double shift()
    /*-{
		return this.shift();
    }-*/;

    public final native boolean contains(double value)
    /*-{
		return (value in this);
    }-*/;
}
