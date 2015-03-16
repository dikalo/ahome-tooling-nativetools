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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.gwt.core.client.JavaScriptObject;

public class NObjectBaseJSO<T extends NObjectBaseJSO<T>> extends JavaScriptObject
{
    public static final <T extends NObjectBaseJSO<T>> T createNObjectBaseJSO()
    {
        return JavaScriptObject.createObject().cast();
    }

    protected NObjectBaseJSO()
    {
    }

    public final JavaScriptObject asJavaScriptObject()
    {
        return this;
    }

    public final String toJSONString()
    {
        return NUtils.JSON.toJSONString(asJavaScriptObject());
    }

    public final boolean isEmpty()
    {
        return (0 == size());
    }

    public final NNativeType getNativeTypeOf(final String name)
    {
        return NUtils.Native.getNativeTypeOfJSO(this, NUtils.doKeyRepair(name, true));
    }

    public final boolean is(final String name, final NNativeType type)
    {
        return (type == getNativeTypeOf(name));
    }

    public final Collection<String> keys()
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
        return isDefined_0(NUtils.doKeyRepair(name, true));
    }

    public final boolean isNull(final String name)
    {
        return isNull_0(NUtils.doKeyRepair(name, true));
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
        remove_0(NUtils.doKeyRepair(name, true));
    }

    private final native void remove_0(String name)
    /*-{
		delete this[name];
    }-*/;
}
