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

import com.ait.tooling.common.api.json.JSONStringify;
import com.google.gwt.core.client.JavaScriptObject;

public class NArrayBaseJSO<T extends NArrayBaseJSO<T>> extends JavaScriptObject implements JSONStringify
{
    public static final <T extends NArrayBaseJSO<T>> T create()
    {
        return JavaScriptObject.createArray().cast();
    }

    protected NArrayBaseJSO()
    {
    }

    @Override
    public final String toJSONString()
    {
        return NUtils.JSON.toJSONString(this);
    }

    public final boolean isEmpty()
    {
        return (0 == size());
    }

    public final void clear()
    {
        setSize(0);
    }

    public final String join()
    {
        return join(",");
    }

    public final NNativeType getNativeTypeOf(final int index)
    {
        if ((index < 0) || (index >= size()))
        {
            return NNativeType.UNDEFINED;
        }
        return NUtils.Native.getNativeTypeOfJSO(this, index);
    }

    public final boolean is(int index, NNativeType type)
    {
        return (type == getNativeTypeOf(index));
    }

    public final boolean isNull(final int index)
    {
        if ((index < 0) || (index >= size()))
        {
            return true;
        }
        return isNull_0(index);
    }

    public final boolean isDefined(final int index)
    {
        if ((index < 0) || (index >= size()))
        {
            return false;
        }
        return isDefined_0(index);
    }

    public final native int size()
    /*-{
		return this.length;
    }-*/;

    public final native void setSize(int size)
    /*-{
		if (size < 0) {
			size = 0;
		}
		if (this.length < size) {
			while (this.length < size) {
				this.pop();
			}
		} else {
			this.length = size;
		}
    }-*/;

    public final native void splice(int beg, int removed)
    /*-{
		this.splice(beg, removed);
    }-*/;

    public final native void reverse()
    /*-{
		this.reverse();
    }-*/;

    public final native String join(String separator)
    /*-{
		return this.join(separator);
    }-*/;

    public final native T concat(T value)
    /*-{
		return this.concat(value);
    }-*/;

    public final native T copy()
    /*-{
		return this.concat();
    }-*/;

    public final native T slice(int beg)
    /*-{
		return this.slice(beg);
    }-*/;

    public final native T slice(int beg, int end)
    /*-{
		return this.slice(beg, end);
    }-*/;

    private final native boolean isNull_0(int index)
    /*-{
		return this[index] == null;
    }-*/;

    private final native boolean isDefined_0(int index)
    /*-{
		return this[index] !== undefined;
    }-*/;
}
