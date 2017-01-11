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

import java.util.Collection;

import com.ait.tooling.nativetools.client.NArray;

public final class HashTagList extends StringList
{
    public HashTagList()
    {
    }

    public HashTagList(final NArray array)
    {
        super(array);
    }

    public HashTagList(final String value)
    {
        super(value);
    }

    public HashTagList(final String value, final String... values)
    {
        super(value, values);
    }

    public HashTagList(final Collection<String> values)
    {
        super(values);
    }

    public HashTagList(final StringList list)
    {
        super(list);
    }

    @Override
    public HashTagList copy()
    {
        return new HashTagList(this);
    }
}
