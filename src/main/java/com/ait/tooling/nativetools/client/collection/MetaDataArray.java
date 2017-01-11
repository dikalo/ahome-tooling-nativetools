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

package com.ait.tooling.nativetools.client.collection;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.nativetools.client.NArrayJSO;
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NObjectOnWire;
import com.ait.tooling.nativetools.client.NUtils.Native;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;

public final class MetaDataArray implements NHasJSO<NArrayJSO>, NJSONStringify, NObjectOnWire
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
        if ((null != jso) && (JSONType.ARRAY == Native.getNativeTypeOfJSO(jso)))
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
        return m_jso.toJSONArray();
    }

    public final JSONType getNativeTypeOf(final int index)
    {
        if ((index < 0) || (index >= size()))
        {
            return JSONType.UNDEFINED;
        }
        return Native.getNativeTypeOf(m_jso, index);
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
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
        if (isInteger(index))
        {
            return m_jso.getAsInteger(index);
        }
        return 0;
    }

    public final double getAsDouble(final int index)
    {
        if (isNumber(index))
        {
            return m_jso.getAsDouble(index);
        }
        return 0;
    }

    public final String getAsString(final int index)
    {
        if (isString(index))
        {
            return m_jso.getAsString(index);
        }
        return null;
    }

    public final boolean getAsBoolean(final int index)
    {
        if (isBoolean(index))
        {
            return m_jso.getAsBoolean(index);
        }
        return false;
    }

    public final MetaData getAsMetaData(final int index)
    {
        if (isObject(index))
        {
            return new MetaData(m_jso.getAsJSO(index));
        }
        return null;
    }

    public final MetaDataArray getAsMetaDataArray(final int index)
    {
        if (isArray(index))
        {
            return new MetaDataArray(m_jso.getAsJSO(index));
        }
        return null;
    }

    public final boolean isString(final int index)
    {
        return Native.isString(m_jso, index);
    }

    public final boolean isBoolean(final int index)
    {
        return Native.isBoolean(m_jso, index);
    }

    public final boolean isObject(final int index)
    {
        return Native.isObject(m_jso, index);
    }

    public final boolean isArray(final int index)
    {
        return Native.isArray(m_jso, index);
    }

    public final boolean isNumber(final int index)
    {
        return Native.isNumber(m_jso, index);
    }

    public final boolean isInteger(final int index)
    {
        return Native.isInteger(m_jso, index);
    }

    @Override
    public final NArrayJSO getJSO()
    {
        return m_jso;
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
    public final String toString()
    {
        return toJSONString();
    }

    @Override
    public final boolean equals(final Object other)
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
    public final int hashCode()
    {
        return toJSONString().hashCode();
    }

    @Override
    public final NObject onWire()
    {
        return new NObject("list", this);
    }
}
