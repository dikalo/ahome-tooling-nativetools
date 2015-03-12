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

import com.ait.tooling.common.api.types.IMixedListDefinition;
import com.google.gwt.core.client.JavaScriptObject;

public final class NArray implements NValue<NArrayJSO>, IMixedListDefinition<NArray, NObject>
{
    private final NArrayJSO m_jso;

    public NArray(final NArrayJSO jso)
    {
        if (null == jso)
        {
            m_jso = NArrayJSO.create();
        }
        else
        {
            m_jso = jso;
        }
    }

    public NArray()
    {
        m_jso = NArrayJSO.create();
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

    public NArray(double value)
    {
        this();

        push(value);
    }

    public NArray(double value, double... values)
    {
        this();

        push(value, values);
    }

    public NArray(boolean value)
    {
        this();

        push(value);
    }

    public NArray(boolean value, boolean... values)
    {
        this();

        push(value, values);
    }

    public NArray(String value)
    {
        this();

        push(value);
    }

    public NArray(String value, String... values)
    {
        this();

        push(value, values);
    }

    public NArray(NHasJSO<? extends JavaScriptObject> value)
    {
        this();

        push(value);
    }

    @SuppressWarnings("unchecked")
    public NArray(NHasJSO<? extends JavaScriptObject> value, NHasJSO<? extends JavaScriptObject>... values)
    {
        this();

        push(value, values);
    }

    @Override
    public final int size()
    {
        return m_jso.size();
    }

    public final NArray setSize(int length)
    {
        m_jso.setSize(length);

        return this;
    }

    public final NArray push(int value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(int value, int... values)
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

    public final NArray push(double value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(double value, double... values)
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

    public final NArray push(boolean value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(boolean value, boolean... values)
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

    public final NArray push(String value)
    {
        m_jso.push(value);

        return this;
    }

    public final NArray push(String value, String... values)
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

    public final NArray push(NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.push(value);

        return this;
    }

    @SuppressWarnings("unchecked")
    public final NArray push(NHasJSO<? extends JavaScriptObject> value, NHasJSO<? extends JavaScriptObject>... values)
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

    public final NArray set(int index, int value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(int index, double value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(int index, boolean value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(int index, String value)
    {
        m_jso.set(index, value);

        return this;
    }

    public final NArray set(int index, NHasJSO<? extends JavaScriptObject> value)
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

    public final NArray unshift(int value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(int value, int... values)
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

    public final NArray unshift(double value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(double value, double... values)
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

    public final NArray unshift(boolean value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(boolean value, boolean... values)
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

    public final NArray unshift(String value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray unshift(String value, String... values)
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

    public final NArray unshift(NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NArray remove(NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.remove(value);

        return this;
    }

    public final NArray splice(int beg, int removed, int value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(int beg, int removed, double value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(int beg, int removed, boolean value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(int beg, int removed, String value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(int beg, int removed, NHasJSO<? extends JavaScriptObject> value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NArray splice(int beg, int removed)
    {
        m_jso.splice(beg, removed);

        return this;
    }

    public final NArray spliceValueOf(int beg, int removed, NArray value)
    {
        if (null == value)
        {
            m_jso.spliceValueOf(beg, removed, NArrayJSO.JS_NULL);
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

    public final NArray concat(NArray value)
    {
        if (null == value)
        {
            return new NArray(m_jso.copy());
        }
        return new NArray(m_jso.concat(value.getJSO()));
    }

    public final NArray slice(int beg)
    {
        return new NArray(m_jso.slice(beg));
    }

    public final NArray slice(int beg, int end)
    {
        return new NArray(m_jso.slice(beg, end));
    }

    public final NNativeType getNativeTypeOf(int index)
    {
        return m_jso.getNativeTypeOf(index);
    }

    public final boolean is(int index, NNativeType type)
    {
        return m_jso.is(index, type);
    }

    @Override
    public final Integer getAsInteger(int index)
    {
        if (isInteger(index))
        {
            return m_jso.getAsInteger(index);
        }
        return null;
    }

    @Override
    public final Double getAsDouble(int index)
    {
        if (isDouble(index))
        {
            return m_jso.getAsDouble(index);
        }
        return null;
    }

    @Override
    public final String getAsString(int index)
    {
        if (isString(index))
        {
            return m_jso.getAsString(index);
        }
        return null;
    }

    @Override
    public final Boolean getAsBoolean(int index)
    {
        if (isBoolean(index))
        {
            return m_jso.getAsBoolean(index);
        }
        return null;
    }

    @Override
    public final NArray getAsArray(int index)
    {
        if (isArray(index))
        {
            return new NArray((NArrayJSO) getAsJSO(index).cast());
        }
        return null;
    }

    @Override
    public final NObject getAsObject(int index)
    {
        if (isObject(index))
        {
            return new NObject((NObjectJSO) getAsJSO(index).cast());
        }
        return null;
    }

    public final NValue<?> getAsNValue(int index)
    {
        return m_jso.getAsNValue(index);
    }

    public final JavaScriptObject getAsJSO(int index)
    {
        if ((index >= 0) && (index < size()))
        {
            return m_jso.getAsJSO(index);
        }
        return null;
    }

    @Override
    public final boolean isNull(int index)
    {
        return m_jso.isNull(index);
    }

    public final String join()
    {
        return m_jso.join();
    }

    public final String join(String separator)
    {
        return m_jso.join(separator);
    }

    @Override
    public final NArrayJSO getJSO()
    {
        return m_jso;
    }

    @Override
    public final NNativeType getNativeTypeOf()
    {
        return NNativeType.ARRAY;
    }

    @Override
    public final boolean is(NNativeType type)
    {
        return (NNativeType.ARRAY == type);
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
    public final String toJSONString()
    {
        return m_jso.toJSONString();
    }

    @Override
    public final boolean equals(Object other)
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
        setSize(0);
    }

    @Override
    public final boolean isEmpty()
    {
        return (0 == size());
    }

    @Override
    public final boolean isString(int index)
    {
        return is(index, NNativeType.STRING);
    }

    @Override
    public final boolean isBoolean(int index)
    {
        return is(index, NNativeType.BOOLEAN);
    }

    @Override
    public final boolean isObject(int index)
    {
        return is(index, NNativeType.OBJECT);
    }

    @Override
    public final boolean isArray(int index)
    {
        return is(index, NNativeType.ARRAY);
    }

    @Override
    public final boolean isNumber(int index)
    {
        return is(index, NNativeType.NUMBER);
    }

    @Override
    public final boolean isDouble(int index)
    {
        return isNumber(index);
    }

    @Override
    public final boolean isInteger(int index)
    {
        return isNumber(index);
    }

    @Override
    public final Number getAsNumber(int index)
    {
        return getAsDouble(index);
    }
}
