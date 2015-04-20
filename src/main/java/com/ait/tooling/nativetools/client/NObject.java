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

package com.ait.tooling.nativetools.client;

import java.util.Collection;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.common.api.types.IMixedStringHashDefinition;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class NObject implements NValue<NObjectJSO>, IMixedStringHashDefinition<NArray, NObject>
{
    private final NObjectJSO m_jso;

    public NObject(final NObjectJSO jso)
    {
        if (null == jso)
        {
            m_jso = NObjectJSO.make();
        }
        else
        {
            m_jso = jso;
        }
    }

    public NObject()
    {
        m_jso = NObjectJSO.make();
    }

    public NObject(final String name, final int value)
    {
        this();

        put(name, value);
    }

    public NObject(final String name, final double value)
    {
        this();

        put(name, value);
    }

    public NObject(final String name, final boolean value)
    {
        this();

        put(name, value);
    }

    public NObject(final String name, final String value)
    {
        this();

        put(name, value);
    }

    public NObject(final String name, final NHasJSO<? extends JavaScriptObject> value)
    {
        this();

        put(name, value);
    }

    public final JSONObject toJSONObject()
    {
        return m_jso.toJSONObject();
    }

    public final NObject put(final String name, final int value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final NObject put(final String name, final double value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final NObject put(final String name, final boolean value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final NObject put(final String name, final String value)
    {
        m_jso.put(name, value);

        return this;
    }

    public final NObject put(final String name, final NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.put(name, value);

        return this;
    }

    @Override
    public final void remove(final String name)
    {
        m_jso.remove(name);
    }

    @Override
    public final Collection<String> keys()
    {
        return m_jso.keys();
    }

    public final NObject deep() throws Exception
    {
        NValue<?> value = NUtils.JSON.parse(NUtils.JSON.toJSONString(m_jso));

        return value.asNObject();
    }

    @Override
    public final int size()
    {
        return m_jso.size();
    }

    public final JSONType getNativeTypeOf(final String name)
    {
        return m_jso.getNativeTypeOf(name);
    }

    public final boolean is(final String name, final JSONType type)
    {
        return m_jso.is(name, type);
    }

    @Override
    public final boolean isDefined(final String name)
    {
        return m_jso.isDefined(name);
    }

    @Override
    public final Integer getAsInteger(final String name)
    {
        if (isInteger(name))
        {
            return m_jso.getAsInteger(name);
        }
        return null;
    }

    @Override
    public final Double getAsDouble(final String name)
    {
        if (isDouble(name))
        {
            return m_jso.getAsDouble(name);
        }
        return null;
    }

    @Override
    public final String getAsString(final String name)
    {
        if (isString(name))
        {
            return m_jso.getAsString(name);
        }
        return null;
    }

    @Override
    public final Boolean getAsBoolean(final String name)
    {
        if (isBoolean(name))
        {
            return m_jso.getAsBoolean(name);
        }
        return null;
    }

    @Override
    public final NArray getAsArray(final String name)
    {
        if (isArray(name))
        {
            NArrayJSO jso = getAsJSO(name).cast();

            return new NArray(jso);
        }
        return null;
    }

    @Override
    public final NObject getAsObject(final String name)
    {
        if (isObject(name))
        {
            NObjectJSO jso = getAsJSO(name).cast();

            return new NObject(jso);
        }
        return null;
    }

    public final NValue<?> getAsNValue(final String name)
    {
        return m_jso.getAsNValue(name);
    }

    public final <T extends JavaScriptObject> T getAsJSO(final String name)
    {
        return getAsJSO(name).cast();
    }

    @Override
    public final NObjectJSO getJSO()
    {
        return m_jso;
    }

    @Override
    public final JSONType getNativeTypeOf()
    {
        return JSONType.OBJECT;
    }

    @Override
    public final boolean is(final JSONType type)
    {
        return (JSONType.OBJECT == type);
    }

    @Override
    public final NArray asNArray()
    {
        return null;
    }

    @Override
    public final NObject asNObject()
    {
        return this;
    }

    @Override
    public final NValue<NObjectJSO> asNValue()
    {
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
    public final boolean equals(final Object other)
    {
        if ((null != other) && (other instanceof NObject))
        {
            return m_jso.equals(((NObject) other).getJSO());
        }
        return false;
    }

    @Override
    public final int hashCode()
    {
        return m_jso.hashCode();
    }

    @Override
    public final String toString()
    {
        return toJSONString();
    }

    @Override
    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    @Override
    public final boolean isNull(final String name)
    {
        return m_jso.isNull(name);
    }

    @Override
    public final boolean isString(final String name)
    {
        return m_jso.isString(name);
    }

    @Override
    public final boolean isBoolean(final String name)
    {
        return m_jso.isBoolean(name);
    }

    @Override
    public final boolean isObject(final String name)
    {
        return m_jso.isObject(name);
    }

    @Override
    public final boolean isArray(final String name)
    {
        return m_jso.isArray(name);
    }

    @Override
    public final boolean isNumber(final String name)
    {
        return m_jso.isNumber(name);
    }

    @Override
    public final boolean isNativeFunction(final String name)
    {
        return m_jso.isNativeFunction(name);
    }

    @Override
    public final void clear()
    {
        m_jso.clear();
    }

    @Override
    public final boolean isDouble(final String name)
    {
        return isNumber(name);
    }

    @Override
    public final boolean isInteger(final String name)
    {
        return isNumber(name);
    }

    @Override
    public final Number getAsNumber(final String name)
    {
        return getAsDouble(name);
    }
}
