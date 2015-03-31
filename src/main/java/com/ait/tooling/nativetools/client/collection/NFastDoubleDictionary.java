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

public final class NFastDoubleDictionary extends NFastPrimitiveDictionaryBase<NFastDoubleDictionaryJSO>
{
    public NFastDoubleDictionary(final NFastDoubleDictionaryJSO jso)
    {
        super((null == jso) ? NFastDoubleDictionaryJSO.make() : jso);
    }

    public NFastDoubleDictionary()
    {
        super(NFastDoubleDictionaryJSO.make());
    }

    public final NFastDoubleDictionary put(final String name, final double value)
    {
        getJSO().put(name, value);

        return this;
    }

    public final double get(final String name)
    {
        return getJSO().get(name);
    }
}
