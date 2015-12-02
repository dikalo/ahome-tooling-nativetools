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

import com.google.gwt.core.client.JavaScriptObject;

public class NArrayJSO extends NArrayBaseJSO<NArrayJSO>
{
    public static final NArrayJSO make()
    {
        return createNArrayBaseJSO();
    }

    protected NArrayJSO()
    {
    }

    public final native void push(int value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native void push(double value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native void push(boolean value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native void push(String value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final void push(final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            push(value.getJSO());
        }
        else
        {
            push((JavaScriptObject) null);
        }
    }

    public final native void push(JavaScriptObject value)
    /*-{
		this[this.length] = value;
    }-*/;

    public final native void set(int index, int value)
    /*-{
		this[index] = value;
    }-*/;

    public final native void set(int index, double value)
    /*-{
		this[index] = value;
    }-*/;

    public final native void set(int index, boolean value)
    /*-{
		this[index] = value;
    }-*/;

    public final native void set(int index, String value)
    /*-{
		this[index] = value;
    }-*/;

    public final void set(final int index, final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            set(index, value.getJSO());
        }
        else
        {
            set(index, (JavaScriptObject) null);
        }
    }

    public final native void set(int index, JavaScriptObject value)
    /*-{
		this[index] = value;
    }-*/;

    public final native void pop()
    /*-{
		this.pop();
    }-*/;

    public final native void shift()
    /*-{
		this.shift();
    }-*/;

    public final native void unshift(int value)
    /*-{
		this.unshift(value);
    }-*/;

    public final native void unshift(double value)
    /*-{
		this.unshift(value);
    }-*/;

    public final native void unshift(boolean value)
    /*-{
		this.unshift(value);
    }-*/;

    public final native void unshift(String value)
    /*-{
		this.unshift(value);
    }-*/;

    public final native void unshift(JavaScriptObject value)
    /*-{
		this.unshift(value);
    }-*/;

    public final void unshift(final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            unshift(value.getJSO());
        }
        else
        {
            unshift((JavaScriptObject) null);
        }
    }

    public final native void remove(JavaScriptObject value)
    /*-{
		var list = this;

		for (var i = list.length - 1; i >= 0; i--) {
			if (list[i] === value) {
				list.splice(i, 1);
				return;
			}
		}
    }-*/;

    public final void remove(final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            remove(value.getJSO());
        }
    }

    public final native void splice(int beg, int removed, int value)
    /*-{
		this.splice(beg, removed, value);
    }-*/;

    public final native void splice(int beg, int removed, double value)
    /*-{
		this.splice(beg, removed, value);
    }-*/;

    public final native void splice(int beg, int removed, boolean value)
    /*-{
		this.splice(beg, removed, value);
    }-*/;

    public final native void splice(int beg, int removed, String value)
    /*-{
		this.splice(beg, removed, value);
    }-*/;

    public final void splice(final int beg, final int removed, final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            splice(beg, removed, value.getJSO());
        }
        else
        {
            splice(beg, removed, (JavaScriptObject) null);
        }
    }

    public final native void splice(int beg, int removed, JavaScriptObject value)
    /*-{
		this.splice(beg, removed, value);
    }-*/;

    public final native void spliceValueOf(int beg, int removed, NArrayJSO value)
    /*-{
		if (null == value) {
			this.splice(beg, removed, null);
		} else {
			this.splice(beg, removed, value.valueOf());
		}
    }-*/;

    public final NValue<?> getAsNValue(final int index)
    {
        return NUtils.Native.getAsNValue(this, index);
    }

    public final native JavaScriptObject getAsJSO(int index)
    /*-{
		return this[index];
    }-*/;

    public final native int getAsInteger(int index)
    /*-{
		return (this[index] | 0);
    }-*/;

    public final native double getAsDouble(int index)
    /*-{
		return this[index];
    }-*/;

    public final native String getAsString(int index)
    /*-{
		return this[index];
    }-*/;

    public final native boolean getAsBoolean(int index)
    /*-{
		return this[index];
    }-*/;
}
