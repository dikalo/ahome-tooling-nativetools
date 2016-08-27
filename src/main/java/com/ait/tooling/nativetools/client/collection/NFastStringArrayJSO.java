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

public class NFastStringArrayJSO extends NFastPrimitiveArrayBaseJSO<NFastStringArrayJSO>
{
    public static final NFastStringArrayJSO make(final String s, final String... list)
    {
        final NFastStringArrayJSO jso = make();

        jso.push(s, list);

        return jso;
    }

    public static final NFastStringArrayJSO make(final Iterable<String> list)
    {
        final NFastStringArrayJSO jso = make();

        jso.push(list);

        return jso;
    }

    public static final NFastStringArrayJSO make()
    {
        return createNArrayBaseJSO();
    }

    protected NFastStringArrayJSO()
    {
    }

    public final String[] toArray()
    {
        final int size = size();

        final String[] array = new String[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = get(i);
        }
        return array;
    }

    public final void push(final String s, final String... list)
    {
        push(s);

        final int size = list.length;

        for (int i = 0; i < size; i++)
        {
            push(list[i]);
        }
    }

    public final void push(final Iterable<String> list)
    {
        for (String s : list)
        {
            push(s);
        }
    }

    public final native void push(String value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native String get(int indx)
    /*-{
		return this[indx];
    }-*/;

    public final native void set(int indx, String value)
    /*-{
		this[indx] = value;
    }-*/;

    public final native String pop()
    /*-{
		return this.pop();
    }-*/;

    public final native String shift()
    /*-{
		return this.shift();
    }-*/;

    public final native boolean contains(String value)
    /*-{
		return (value in this);
    }-*/;
}
