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

package com.ait.tooling.nativetools.client.security;

import com.ait.tooling.common.api.hash.Hasher;
import com.ait.tooling.common.api.hash.ISHA_512_HASH;
import com.ait.tooling.common.api.hash.ISHA_512_HASH_SALT;
import com.ait.tooling.nativetools.client.NUtils;

public final class Hashing implements ISHA_512_HASH_SALT, ISHA_512_HASH
{
    private static final Hashing INSTANCE = new Hashing();

    private final Hasher         m_hash   = new Hasher(this);

    public static final Hashing get()
    {
        return INSTANCE;
    }

    private Hashing()
    {
    }

    @Override
    public final String SHA512(final String text, final String salt)
    {
        return m_hash.SHA512(text, salt);
    }

    @Override
    public final String SHA512(final String text, final String salt, final int iter)
    {
        return m_hash.SHA512(text, salt, iter);
    }

    @Override
    public final String SHA512(final String string)
    {
        return NUtils.Native.SHA512(string);
    }
}
