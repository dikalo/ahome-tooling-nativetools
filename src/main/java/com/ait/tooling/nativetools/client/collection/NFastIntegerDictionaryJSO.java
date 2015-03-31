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

import com.ait.tooling.nativetools.client.NUtils;

public class NFastIntegerDictionaryJSO extends NFastPrimitiveDictionarytBaseJSO<NFastIntegerDictionaryJSO>
{
    public static final NFastIntegerDictionaryJSO make()
    {
        return createNObjectBaseJSO();
    }

    protected NFastIntegerDictionaryJSO()
    {
    }

    public final void put(final String name, final int value)
    {
        put_0(NUtils.doKeyRepair(name, true), value);
    }

    public final int get(final String name)
    {
        return get_0(NUtils.doKeyRepair(name, true));
    }

    private final native void put_0(String name, int value)
    /*-{
		this[name] = value;
    }-*/;

    private final native int get_0(String name)
    /*-{
		return this[name];
    }-*/;
}
