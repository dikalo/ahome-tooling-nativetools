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

package com.ait.tooling.nativetools.client.primitive;

import com.ait.tooling.common.api.json.JSONStringify;
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NNativeType;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.NUtils;
import com.google.gwt.core.client.JavaScriptObject;

public final class MetaData implements NHasJSO<NObjectJSO>, JSONStringify
{
    private final NObjectJSO m_jso;

    public MetaData()
    {
        m_jso = NObjectJSO.make();
    }

    public MetaData(final NObjectJSO jso)
    {
        if (null != jso)
        {
            m_jso = jso;
        }
        else
        {
            m_jso = NObjectJSO.make();
        }
    }

    public MetaData(final JavaScriptObject jso)
    {
        if ((null != jso) && (NNativeType.OBJECT == NUtils.Native.getNativeTypeOfJSO(jso)))
        {
            m_jso = jso.cast();
        }
        else
        {
            m_jso = NObjectJSO.make();
        }
    }

    @Override
    public final NObjectJSO getJSO()
    {
        return m_jso;
    }

    public final MetaData put(String name, String value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(String name, int value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(String name, double value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(String name, boolean value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(String name, MetaData value)
    {
        if (null != value)
        {
            m_jso.put(name, value.getJSO());
        }
        else
        {
            m_jso.remove(name);
        }
        return this;
    }

    public final MetaData put(String name, MetaDataArray value)
    {
        if (null != value)
        {
            m_jso.put(name, value.getJSO());
        }
        else
        {
            m_jso.remove(name);
        }
        return this;
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    public final NNativeType getNativeTypeOf(final String name)
    {
        return NUtils.Native.getNativeTypeOfJSO(m_jso, NUtils.doKeyRepair(name, true));
    }

    public final boolean is(final String name, final NNativeType type)
    {
        return (type == getNativeTypeOf(name));
    }

    public final int getAsInteger(final String name)
    {
        if (is(name, NNativeType.NUMBER))
        {
            return m_jso.getAsInteger(name);
        }
        return 0;
    }

    public final double getAsDouble(final String name)
    {
        if (is(name, NNativeType.NUMBER))
        {
            return m_jso.getAsDouble(name);
        }
        return 0;
    }

    public final String getAsString(final String name)
    {
        if (is(name, NNativeType.STRING))
        {
            return m_jso.getAsString(name);
        }
        return null;
    }

    public final boolean getAsBoolean(final String name)
    {
        if (is(name, NNativeType.BOOLEAN))
        {
            return m_jso.getAsBoolean(name);
        }
        return false;
    }

    public final MetaData getAsMetaData(final String name)
    {
        if (is(name, NNativeType.OBJECT))
        {
            return new MetaData(m_jso.getAsJSO(name));
        }
        return null;
    }

    public final MetaDataArray getAsMetaDataArray(final String name)
    {
        if (is(name, NNativeType.ARRAY))
        {
            return new MetaDataArray(m_jso.getAsJSO(name));
        }
        return null;
    }

    public final boolean isDefined(final String name)
    {
        if (null == name)
        {
            return false;
        }
        return m_jso.isDefined(name);
    }

    public final MetaData remove(final String name)
    {
        m_jso.remove(name);

        return this;
    }

    @Override
    public final String toJSONString()
    {
        return m_jso.toJSONString();
    }

    @Override
    public String toString()
    {
        return toJSONString();
    }

    @Override
    public boolean equals(final Object other)
    {
        if ((other == null) || (false == (other instanceof MetaData)))
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        return ((MetaData) other).toJSONString().equals(toJSONString());
    }

    @Override
    public int hashCode()
    {
        return toJSONString().hashCode();
    }
}
