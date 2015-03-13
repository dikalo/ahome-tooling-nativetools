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
import com.ait.tooling.nativetools.client.NArrayJSO;
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NNativeType;
import com.ait.tooling.nativetools.client.NUtils;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;

public final class MetaDataArray implements NHasJSO<NArrayJSO>, JSONStringify
{
    private final NArrayJSO m_jso;

    public MetaDataArray()
    {
        m_jso = NArrayJSO.make();
    }

    public MetaDataArray(final NArrayJSO jso)
    {
        if (null != jso)
        {
            m_jso = jso;
        }
        else
        {
            m_jso = NArrayJSO.make();
        }
    }

    public MetaDataArray(final JavaScriptObject jso)
    {
        if ((null != jso) && (NNativeType.ARRAY == NUtils.Native.getNativeTypeOfJSO(jso)))
        {
            m_jso = jso.cast();
        }
        else
        {
            m_jso = NArrayJSO.make();
        }
    }
    
    public final JSONArray toJSONArray()
    {
        return new JSONArray(m_jso);
    }

    public final NNativeType getNativeTypeOf(final int index)
    {
        return NUtils.Native.getNativeTypeOfJSO(m_jso, index);
    }

    public final boolean is(final int index, final NNativeType type)
    {
        return (type == getNativeTypeOf(index));
    }

    public final int size()
    {
        return m_jso.size();
    }

    public final MetaDataArray push(final String value)
    {
        m_jso.push(value);

        return this;
    }

    public final MetaDataArray push(final int value)
    {
        m_jso.push(value);

        return this;
    }

    public final MetaDataArray push(final double value)
    {
        m_jso.push(value);

        return this;
    }

    public final MetaDataArray push(final boolean value)
    {
        m_jso.push(value);

        return this;
    }

    public final MetaDataArray push(final MetaData value)
    {
        m_jso.push(value);

        return this;
    }

    public final MetaDataArray push(final MetaDataArray value)
    {
        m_jso.push(value);

        return this;
    }

    public final MetaDataArray set(final int index, final String value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final MetaDataArray set(final int index, final int value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final MetaDataArray set(final int index, final double value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final MetaDataArray set(final int index, final boolean value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final MetaDataArray set(final int index, final MetaData value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final MetaDataArray set(final int index, final MetaDataArray value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final int getAsInteger(final int index)
    {
        if (is(index, NNativeType.NUMBER))
        {
            return m_jso.getAsInteger(index);
        }
        return 0;
    }

    public final double getAsDouble(final int index)
    {
        if (is(index, NNativeType.NUMBER))
        {
            return m_jso.getAsDouble(index);
        }
        return 0;
    }

    public final String getAsString(final int index)
    {
        if (is(index, NNativeType.STRING))
        {
            return m_jso.getAsString(index);
        }
        return null;
    }

    public final boolean getAsBoolean(final int index)
    {
        if (is(index, NNativeType.BOOLEAN))
        {
            return m_jso.getAsBoolean(index);
        }
        return false;
    }

    public final MetaData getAsMetaData(final int index)
    {
        if (is(index, NNativeType.OBJECT))
        {
            return new MetaData(m_jso.getAsJSO(index));
        }
        return null;
    }

    public final MetaDataArray getAsMetaDataArray(final int index)
    {
        if (is(index, NNativeType.ARRAY))
        {
            return new MetaDataArray(m_jso.getAsJSO(index));
        }
        return null;
    }

    @Override
    public final NArrayJSO getJSO()
    {
        return m_jso;
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
        if ((other == null) || (false == (other instanceof MetaDataArray)))
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        return ((MetaDataArray) other).toJSONString().equals(toJSONString());
    }

    @Override
    public int hashCode()
    {
        return toJSONString().hashCode();
    }
}
