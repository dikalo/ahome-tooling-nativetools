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

import java.util.Objects;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.google.gwt.json.client.JSONObject;

public abstract class NFastPrimitiveDictionaryBase<T extends NFastPrimitiveDictionarytBaseJSO<T>> implements NHasJSO<T>, NJSONStringify
{
    private final T m_jso;

    protected NFastPrimitiveDictionaryBase(final T jso)
    {
        m_jso = Objects.requireNonNull(jso);
    }

    public final JSONObject toJSONObject()
    {
        return m_jso.toJSONObject();
    }

    @Override
    public final String toString()
    {
        return toJSONString();
    }

    @Override
    public final String toJSONString(final int indent)
    {
        return m_jso.toJSONString(indent);
    }

    @Override
    public final String toJSONString(final String indent)
    {
        return m_jso.toJSONString(indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final int indent)
    {
        return m_jso.toJSONString(replacer, indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final String indent)
    {
        return m_jso.toJSONString(replacer, indent);
    }

    @Override
    public final String toJSONString()
    {
        return m_jso.toJSONString();
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer)
    {
        return m_jso.toJSONString(replacer);
    }

    @Override
    public final T getJSO()
    {
        return m_jso;
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    public final JSONType getNativeTypeOf(final String name)
    {
        return m_jso.getNativeTypeOf(name);
    }

    public final boolean is(final String name, final JSONType type)
    {
        return (type == getNativeTypeOf(name));
    }

    public final boolean isNull(final String name)
    {
        return m_jso.isNull(name);
    }

    public final boolean isDefined(final String name)
    {
        return m_jso.isDefined(name);
    }

    public final int size()
    {
        return m_jso.size();
    }

    public final void clear()
    {
        m_jso.clear();
    }
}
