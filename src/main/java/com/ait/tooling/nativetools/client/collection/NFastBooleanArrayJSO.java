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

public class NFastBooleanArrayJSO extends NFastPrimitiveArrayBaseJSO<NFastBooleanArrayJSO>
{
    public static final NFastBooleanArrayJSO make(final boolean v, final boolean... list)
    {
        final NFastBooleanArrayJSO jso = make();

        jso.push(v, list);

        return jso;
    }

    public static final NFastBooleanArrayJSO make()
    {
        return createNArrayBaseJSO();
    }

    protected NFastBooleanArrayJSO()
    {
    }

    public final boolean[] toArray()
    {
        final int size = size();

        final boolean[] array = new boolean[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = get(i);
        }
        return array;
    }

    public final void push(final boolean v, final boolean... list)
    {
        push(v);

        final int size = list.length;

        for (int i = 0; i < size; i++)
        {
            push(list[i]);
        }
    }

    public final native void push(boolean value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native boolean get(int indx)
    /*-{
		return this[indx];
    }-*/;

    public final native void set(int indx, boolean value)
    /*-{
		this[indx] = value;
    }-*/;

    public final native boolean pop()
    /*-{
		return this.pop();
    }-*/;

    public final native boolean shift()
    /*-{
		return this.shift();
    }-*/;

    public final native boolean contains(boolean value)
    /*-{
		return (value in this);
    }-*/;
}
