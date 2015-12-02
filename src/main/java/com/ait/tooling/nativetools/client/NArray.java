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

package com.ait.tooling.nativetools.client;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.common.api.types.IMixedListDefinition;
import com.ait.tooling.nativetools.client.NUtils.Native;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;

public final class NArray implements NValue<NArrayJSO>, IMixedListDefinition<NArray, NObject>
{
    private static final long serialVersionUID = 2148774350319842890L;

    private final NArrayJSO   m_jso;

    public NArray(final NArrayJSO jso)
    {
        if (null == jso)
        {
            m_jso = NArrayJSO.make();
        }
        else
        {
            m_jso = jso;
        }
    }

    public NArray()
    {
        m_jso = NArrayJSO.make();
    }

    public NArray(final int value)
    {
        this();

        push(value);
    }

    public NArray(final int value, final int... values)
    {
        this();

        push(value, values);
    }

    public NArray(final double value)
    {
        this();

        push(value);
    }

    public NArray(final double value, final double... values)
    {
        this();

        push(value, values);
    }

    public NArray(final boolean value)
    {
        this();

        push(value);
    }

    public NArray(final boolean value, final boolean... values)
    {
        this();

        push(value, values);
    }

    public NArray(final String value)
    {
        this();

        push(value);
    }

    public NArray(final String value, final String... values)
    {
        this();

        push(value, values);
    }

    public NArray(final NHasJSO<? extends JavaScriptObject> value)
    {
        this();

        push(value);
    }

    @SuppressWarnings("unchecked")
    public NArray(final NHasJSO<? extends JavaScriptObject> value, final NHasJSO<? extends JavaScriptObject>... values)
    {
        this();

        push(value, values);
    }

    public final JSONArray toJSONArray()
    {
        return m_jso.toJSONArray();
    }

    @Override
    public final int size()
    {
        return m_jso.size();
    }

    public final NArray setSize(final int length)
    {
        m_jso.setSize(length);

        return this;
    }

    public final NArray push(final int value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(final int value, final int... values)
    {
        m_jso.push(value);

        if ((null != values) && (values.length > 0))
        {
            for (int i = 0, l = values.length; i < l; i++)
            {
                m_jso.push(values[i]);
            }
        }
        return this;
    }

    public final NArray push(final double value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(final double value, final double... values)
    {
        m_jso.push(value);

        if ((null != values) && (values.length > 0))
        {
            for (int i = 0, l = values.length; i < l; i++)
            {
                m_jso.push(values[i]);
            }
        }
        return this;
    }

    public final NArray push(final boolean value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(final boolean value, final boolean... values)
    {
        m_jso.push(value);

        if ((null != values) && (values.length > 0))
        {
            for (int i = 0, l = values.length; i < l; i++)
            {
                m_jso.push(values[i]);
            }
        }
        return this;
    }

    public final NArray push(final String value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(final String value, final String... values)
    {
        m_jso.push(value);

        if ((null != values) && (values.length > 0))
        {
            for (int i = 0, l = values.length; i < l; i++)
            {
                m_jso.push(values[i]);
            }
        }
        return this;
    }

    public final NArray push(final NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.push(value);

        return this;
    }

    @SuppressWarnings("unchecked")
    public final NArray push(final NHasJSO<? extends JavaScriptObject> value, final NHasJSO<? extends JavaScriptObject>... values)
    {
        m_jso.push(value);

        if ((null != values) && (values.length > 0))
        {
            for (int i = 0, l = values.length; i < l; i++)
            {
                m_jso.push(values[i]);
            }
        }
        return this;
    }

    public final NArray set(final int index, final int value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(final int index, final double value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(final int index, final boolean value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(final int index, final String value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(final int index, final NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray pop()
    {
        m_jso.pop();

        return this;
    }

    public final NArray shift()
    {
        m_jso.shift();

        return this;
    }

    public final NArray unshift(final int value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final int value, final int... values)
    {
        if ((null != values) && (values.length > 0))
        {
            for (int i = values.length; i > 0; i--)
            {
                m_jso.unshift(values[i - 1]);
            }
        }
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final double value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final double value, final double... values)
    {
        if ((null != values) && (values.length > 0))
        {
            for (int i = values.length; i > 0; i--)
            {
                m_jso.unshift(values[i - 1]);
            }
        }
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final boolean value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final boolean value, final boolean... values)
    {
        if ((null != values) && (values.length > 0))
        {
            for (int i = values.length; i > 0; i--)
            {
                m_jso.unshift(values[i - 1]);
            }
        }
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final String value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final String value, final String... values)
    {
        if ((null != values) && (values.length > 0))
        {
            for (int i = values.length; i > 0; i--)
            {
                m_jso.unshift(values[i - 1]);
            }
        }
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(final NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray remove(final NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.remove(value);

        return this;
    }

    public final NArray splice(final int beg, final int removed, final int value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(final int beg, final int removed, final double value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(final int beg, final int removed, final boolean value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(final int beg, final int removed, final String value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(final int beg, final int removed, final NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(final int beg, final int removed)
    {
        m_jso.splice(beg, removed);

        return this;
    }

    public final NArray spliceValueOf(final int beg, final int removed, final NArray value)
    {
        if (null == value)
        {
            m_jso.spliceValueOf(beg, removed, (NArrayJSO) null);
        }
        else
        {
            m_jso.spliceValueOf(beg, removed, value.getJSO());
        }
        return this;
    }

    public final NArray reverse()
    {
        m_jso.reverse();

        return this;
    }

    public final NArray copy()
    {
        return new NArray(m_jso.copy());
    }

    public final NArray deep() throws Exception
    {
        NValue<?> value = NUtils.JSON.parse(NUtils.JSON.toJSONString(m_jso));

        return value.asNArray();
    }

    public final NArray concat(final NArray value)
    {
        if (null == value)
        {
            return new NArray(m_jso.copy());
        }
        return new NArray(m_jso.concat(value.getJSO()));
    }

    public final NArray slice(final int beg)
    {
        return new NArray(m_jso.slice(beg));
    }

    public final NArray slice(final int beg, final int end)
    {
        return new NArray(m_jso.slice(beg, end));
    }

    public final JSONType getNativeTypeOf(final int index)
    {
        return m_jso.getNativeTypeOf(index);
    }

    @Override
    public final Integer getAsInteger(final int index)
    {
        if (isInteger(index))
        {
            return m_jso.getAsInteger(index);
        }
        return null;
    }

    @Override
    public final Double getAsDouble(final int index)
    {
        if (isDouble(index))
        {
            return m_jso.getAsDouble(index);
        }
        return null;
    }

    @Override
    public final String getAsString(final int index)
    {
        if (isString(index))
        {
            return m_jso.getAsString(index);
        }
        return null;
    }

    @Override
    public final Boolean getAsBoolean(final int index)
    {
        if (isBoolean(index))
        {
            return m_jso.getAsBoolean(index);
        }
        return null;
    }

    @Override
    public final NArray getAsArray(final int index)
    {
        if (isArray(index))
        {
            final NArrayJSO mjso = getAsJSO(index);

            if (null != mjso)
            {
                return new NArray(mjso);
            }
        }
        return null;
    }

    @Override
    public final NObject getAsObject(final int index)
    {
        if (isObject(index))
        {
            final NObjectJSO mjso = getAsJSO(index);

            if (null != mjso)
            {
                return new NObject(mjso);
            }
        }
        return null;
    }

    public final NValue<?> getAsNValue(final int index)
    {
        return m_jso.getAsNValue(index);
    }

    public final <T extends JavaScriptObject> T getAsJSO(final int index)
    {
        if ((index >= 0) && (index < size()))
        {
            final JavaScriptObject mjso = m_jso.getAsJSO(index);

            if (null != mjso)
            {
                return mjso.cast();
            }
        }
        return null;
    }

    @Override
    public final boolean isNull(final int index)
    {
        return m_jso.isNull(index);
    }

    public final String join()
    {
        return m_jso.join();
    }

    public final String join(final String separator)
    {
        return m_jso.join(separator);
    }

    @Override
    public final NArrayJSO getJSO()
    {
        return m_jso;
    }

    @Override
    public final JSONType getNativeTypeOf()
    {
        return JSONType.ARRAY;
    }

    @Override
    public final boolean is(final JSONType type)
    {
        return (JSONType.ARRAY == type);
    }

    @Override
    public final NArray asNArray()
    {
        return this;
    }

    @Override
    public final NObject asNObject()
    {
        return null;
    }

    @Override
    public final NValue<NArrayJSO> asNValue()
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
        if ((null != other) && (other instanceof NArray))
        {
            return m_jso.equals(((NArray) other).getJSO());
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
    public final void clear()
    {
        m_jso.clear();
    }

    @Override
    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    @Override
    public final boolean isString(final int index)
    {
        return Native.isString(m_jso, index);
    }

    @Override
    public final boolean isBoolean(final int index)
    {
        return Native.isBoolean(m_jso, index);
    }

    @Override
    public final boolean isObject(final int index)
    {
        return Native.isObject(m_jso, index);
    }

    @Override
    public final boolean isArray(final int index)
    {
        return Native.isArray(m_jso, index);
    }

    @Override
    public final boolean isNumber(final int index)
    {
        return Native.isNumber(m_jso, index);
    }

    @Override
    public final boolean isDouble(final int index)
    {
        return isNumber(index);
    }

    @Override
    public final boolean isInteger(final int index)
    {
        return Native.isInteger(m_jso, index);
    }

    @Override
    public final boolean isNativeFunction(final int index)
    {
        return Native.isNativeFunction(m_jso, index);
    }

    @Override
    public final Number getAsNumber(final int index)
    {
        return getAsDouble(index);
    }
}
