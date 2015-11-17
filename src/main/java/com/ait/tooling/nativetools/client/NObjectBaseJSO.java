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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.nativetools.client.NUtils.JSON;
import com.ait.tooling.nativetools.client.NUtils.Native;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public class NObjectBaseJSO<T extends NObjectBaseJSO<T>> extends JavaScriptObject
{
    public static final <T extends NObjectBaseJSO<T>> T createNObjectBaseJSO()
    {
        return JavaScriptObject.createObject().cast();
    }

    protected NObjectBaseJSO()
    {
    }

    public final JSONObject toJSONObject()
    {
        return new JSONObject(this);
    }

    public final String toJSONString()
    {
        return JSON.toJSONString(this);
    }

    public final String toJSONString(final NJSONReplacer replacer)
    {
        return JSON.toJSONString(this, replacer);
    }

    public final String toJSONString(final String indent)
    {
        return JSON.toJSONString(this, indent);
    }

    public final String toJSONString(final NJSONReplacer replacer, final String indent)
    {
        return JSON.toJSONString(this, replacer, indent);
    }

    public final String toJSONString(final int indent)
    {
        return JSON.toJSONString(this, indent);
    }

    public final String toJSONString(final NJSONReplacer replacer, final int indent)
    {
        return JSON.toJSONString(this, replacer, indent);
    }

    public final boolean isEmpty()
    {
        return (0 == size());
    }

    public final JSONType getNativeTypeOf(final String name)
    {
        return Native.getNativeTypeOfJSO(this, NUtils.doKeyRepair(name));
    }

    public final boolean is(final String name, final JSONType type)
    {
        return (type == getNativeTypeOf(name));
    }

    public final boolean isString(final String name)
    {
        return is(name, JSONType.STRING);
    }

    public final boolean isNumber(final String name)
    {
        return is(name, JSONType.NUMBER);
    }

    public final boolean isBoolean(final String name)
    {
        return is(name, JSONType.BOOLEAN);
    }

    public final boolean isArray(final String name)
    {
        return is(name, JSONType.ARRAY);
    }

    public final boolean isObject(final String name)
    {
        return is(name, JSONType.OBJECT);
    }

    public final boolean isNativeFunction(final String name)
    {
        return is(name, JSONType.FUNCTION);
    }

    public final List<String> keys()
    {
        final ArrayList<String> list = new ArrayList<String>();

        keys_0(list);

        return Collections.unmodifiableList(list);
    }

    public final native int size()
    /*-{
		var i = 0;
		for ( var name in this) {
			if (this.hasOwnProperty(String(name))) {
				if (this[name] !== undefined) {
					++i;
				}
			}
		}
		return i;
    }-*/;

    public final native void clear()
    /*-{
		for ( var name in this) {
			if (this.hasOwnProperty(String(name))) {
				if (this[name] !== undefined) {
					delete this[name];
				}
			}
		}
    }-*/;

    private final native void keys_0(Collection<String> keys)
    /*-{
		for ( var name in this) {
			if (this.hasOwnProperty(String(name))) {
				if (this[name] !== undefined) {
					keys.@java.util.Collection::add(Ljava/lang/Object;)(name);
				}
			}
		}
    }-*/;

    public final boolean isDefined(final String name)
    {
        return isDefined_0(NUtils.doKeyRepair(name));
    }

    public final boolean isNull(final String name)
    {
        return isNull_0(NUtils.doKeyRepair(name));
    }

    private final native boolean isDefined_0(String name)
    /*-{
		if (this.hasOwnProperty(String(name))) {
			if (this[name] !== undefined) {
				return true;
			}
		}
		return false;
    }-*/;

    private final native boolean isNull_0(String name)
    /*-{
		if (this.hasOwnProperty(String(name))) {
			if (this[name] == null) {
				return true;
			}
		}
		return false;
    }-*/;

    public final void remove(final String name)
    {
        remove_0(NUtils.doKeyRepair(name));
    }

    private final native void remove_0(String name)
    /*-{
		delete this[name];
    }-*/;
}
