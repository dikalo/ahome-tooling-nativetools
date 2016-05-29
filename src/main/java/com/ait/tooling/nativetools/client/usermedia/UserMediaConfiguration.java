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

package com.ait.tooling.nativetools.client.usermedia;

import java.util.Objects;

import com.ait.tooling.common.api.json.JSONStringify;
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.collection.MetaData;
import com.google.gwt.core.client.JavaScriptObject;

public final class UserMediaConfiguration implements NHasJSO<JavaScriptObject>, JSONStringify
{
    private final MetaData m_meta;

    public UserMediaConfiguration()
    {
        this(new MetaData());
    }

    public UserMediaConfiguration(final boolean video, final boolean audio)
    {
        this(new MetaData().put("video", true).put("audio", audio));
    }

    public UserMediaConfiguration(final NObject nobj)
    {
        this(new MetaData(Objects.requireNonNull(nobj).getJSO()));
    }

    public UserMediaConfiguration(final JavaScriptObject meta)
    {
        this(new MetaData(Objects.requireNonNull(meta)));
    }

    public UserMediaConfiguration(final MetaData meta)
    {
        m_meta = Objects.requireNonNull(meta);
    }

    public final MetaData getAsMetaData()
    {
        return m_meta;
    }

    @Override
    public final String toJSONString()
    {
        return getAsMetaData().toJSONString();
    }

    @Override
    public final JavaScriptObject getJSO()
    {
        return getAsMetaData().getJSO();
    }

    @Override
    public final NObject onWire()
    {
        return getAsMetaData().onWire();
    }
}
