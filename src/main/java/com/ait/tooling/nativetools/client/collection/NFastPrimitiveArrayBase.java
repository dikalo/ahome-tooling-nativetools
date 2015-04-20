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

import java.util.Objects;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.google.gwt.json.client.JSONArray;

public abstract class NFastPrimitiveArrayBase<A extends NFastPrimitiveArrayBase<A, T>, T extends NFastPrimitiveArrayBaseJSO<T>> implements NHasJSO<T>, NJSONStringify
{
    private final T m_jso;

    protected NFastPrimitiveArrayBase(final T jso)
    {
        m_jso = Objects.requireNonNull(jso);
    }
    
    public final JSONArray toJSONArray()
    {
        return m_jso.toJSONArray();
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
    
    @SuppressWarnings("unchecked")
    protected final A cast()
    {
        return (A) this;
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    public final A clear()
    {
        m_jso.clear();
        
        return cast();
    }

    public final String join()
    {
        return m_jso.join();
    }

    public final JSONType getNativeTypeOf(final int index)
    {
        return m_jso.getNativeTypeOf(index);
    }

    public final boolean is(final int index, final JSONType type)
    {
        return (type == getNativeTypeOf(index));
    }

    public final boolean isNull(final int index)
    {
        return m_jso.isNull(index);
    }

    public final boolean isDefined(final int index)
    {
        return m_jso.isDefined(index);
    }

    public final int size()
    {
        return m_jso.size();
    }

    public final A setSize(final int size)
    {
        m_jso.setSize(size);
        
        return cast();
    }

    public final A splice(final int beg, final int removed)
    {
        m_jso.splice(beg, removed);
        
        return cast();
    }

    public final A reverse()
    {
        m_jso.reverse();
        
        return cast();
    }

    public final String join(final String separator)
    {
        return m_jso.join(separator);
    }
}
