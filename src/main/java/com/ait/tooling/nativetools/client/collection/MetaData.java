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
import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.NObjectOnWire;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NUtils.Native;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class MetaData implements NHasJSO<NObjectJSO>, NJSONStringify, NObjectOnWire
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
        if ((null != jso) && (JSONType.OBJECT == Native.getNativeTypeOfJSO(jso)))
        {
            m_jso = jso.cast();
        }
        else
        {
            m_jso = NObjectJSO.make();
        }
    }

    public final JSONObject toJSONObject()
    {
        return m_jso.toJSONObject();
    }

    @Override
    public final NObjectJSO getJSO()
    {
        return m_jso;
    }

    public final MetaData put(final String name, final String value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(final String name, final int value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(final String name, final double value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(final String name, final boolean value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(final String name, final MetaData value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final MetaData put(final String name, final MetaDataArray value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    public final JSONType getNativeTypeOf(final String name)
    {
        return Native.getNativeTypeOf(m_jso, NUtils.doKeyRepair(name));
    }

    public final boolean isString(final String name)
    {
        return Native.isString(m_jso, NUtils.doKeyRepair(name));
    }

    public final boolean isBoolean(final String name)
    {
        return Native.isBoolean(m_jso, NUtils.doKeyRepair(name));
    }

    public final boolean isObject(final String name)
    {
        return Native.isObject(m_jso, NUtils.doKeyRepair(name));
    }

    public final boolean isArray(final String name)
    {
        return Native.isArray(m_jso, NUtils.doKeyRepair(name));
    }

    public final boolean isNumber(final String name)
    {
        return Native.isNumber(m_jso, NUtils.doKeyRepair(name));
    }

    public final boolean isInteger(final String name)
    {
        return Native.isInteger(m_jso, NUtils.doKeyRepair(name));
    }

    public final int getAsInteger(final String name)
    {
        if (isInteger(name))
        {
            return m_jso.getAsInteger(name);
        }
        return 0;
    }

    public final double getAsDouble(final String name)
    {
        if (isNumber(name))
        {
            return m_jso.getAsDouble(name);
        }
        return 0;
    }

    public final String getAsString(final String name)
    {
        if (isString(name))
        {
            return m_jso.getAsString(name);
        }
        return null;
    }

    public final boolean getAsBoolean(final String name)
    {
        if (isBoolean(name))
        {
            return m_jso.getAsBoolean(name);
        }
        return false;
    }

    public final MetaData getAsMetaData(final String name)
    {
        if (isObject(name))
        {
            return new MetaData(m_jso.getAsJSO(name));
        }
        return null;
    }

    public final MetaDataArray getAsMetaDataArray(final String name)
    {
        if (isArray(name))
        {
            return new MetaDataArray(m_jso.getAsJSO(name));
        }
        return null;
    }

    public final boolean isDefined(final String name)
    {
        return m_jso.isDefined(name);
    }

    public final MetaData remove(final String name)
    {
        m_jso.remove(name);

        return this;
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
    public final int hashCode()
    {
        return toJSONString().hashCode();
    }

    @Override
    public final NObject onWire()
    {
        return new NObject(m_jso);
    }
}
