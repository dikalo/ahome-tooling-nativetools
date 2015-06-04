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
import com.ait.tooling.common.api.hash.IHasher;
import com.ait.tooling.nativetools.client.NUtils;

public final class Hashing implements IHasher
{
    private static final long    serialVersionUID = -8916814073033942414L;

    private static final Hashing INSTANCE         = new Hashing();

    private final Hasher         m_hash           = new Hasher(this);

    public static final Hashing get()
    {
        return INSTANCE;
    }

    private Hashing()
    {
    }

    @Override
    public final String sha512(final String text, final String salt)
    {
        return m_hash.sha512(text, salt);
    }

    @Override
    public final String sha512(final String text, final String salt, final int iter)
    {
        return m_hash.sha512(text, salt, iter);
    }

    @Override
    public final String sha512(final String text)
    {
        return NUtils.Native.sha512(text);
    }
}
