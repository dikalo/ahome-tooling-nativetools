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

public class NObjectJSO extends NObjectBaseJSO<NObjectJSO>
{
    public static final NObjectJSO make()
    {
        return createNObjectBaseJSO();
    }

    protected NObjectJSO()
    {
    }

    public final void put(final String name, final int value)
    {
        put_0(NUtils.doKeyRepair(name), value);
    }

    public final void put(final String name, final double value)
    {
        put_0(NUtils.doKeyRepair(name), value);
    }

    public final void put(final String name, final boolean value)
    {
        put_0(NUtils.doKeyRepair(name), value);
    }

    public final void put(final String name, final String value)
    {
        put_0(NUtils.doKeyRepair(name), NUtils.doStringRepair(value));
    }

    public final void put(final String name, final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            put_0(NUtils.doKeyRepair(name), value.getJSO());
        }
        else
        {
            put_0(NUtils.doKeyRepair(name), (JavaScriptObject) null);
        }
    }

    public final void put(final String name, final NObjectBaseJSO<?> value)
    {
        put_0(NUtils.doKeyRepair(name), value);
    }

    public final void put(final String name, final NArrayBaseJSO<?> value)
    {
        put_0(NUtils.doKeyRepair(name), value);
    }

    private final native void put_0(String name, int value)
    /*-{
		this[name] = value;
    }-*/;

    private final native void put_0(String name, double value)
    /*-{
		this[name] = value;
    }-*/;

    private final native void put_0(String name, boolean value)
    /*-{
		this[name] = value;
    }-*/;

    private final native void put_0(String name, String value)
    /*-{
		this[name] = value;
    }-*/;

    private final native void put_0(String name, JavaScriptObject value)
    /*-{
		this[name] = value;
    }-*/;

    public final NValue<?> getAsNValue(final String name)
    {
        return getAsNValue_0(NUtils.doKeyRepair(name));
    }

    public final JavaScriptObject getAsJSO(final String name)
    {
        return getAsJSO_0(NUtils.doKeyRepair(name));
    }

    public final int getAsInteger(final String name)
    {
        return getAsInteger_0(NUtils.doKeyRepair(name));
    }

    public final double getAsDouble(final String name)
    {
        return getAsDouble_0(NUtils.doKeyRepair(name));
    }

    public final boolean getAsBoolean(final String name)
    {
        return getAsBoolean_0(NUtils.doKeyRepair(name));
    }

    public final String getAsString(final String name)
    {
        return getAsString_0(NUtils.doKeyRepair(name));
    }

    public final String getAsString(final String name, final String otherwise)
    {
        final String value = getAsString_0(NUtils.doKeyRepair(name));

        return ((null != value) ? value : otherwise);
    }

    private final NValue<?> getAsNValue_0(final String name)
    {
        return NUtils.Native.getAsNValue(this, name);
    }

    private final native JavaScriptObject getAsJSO_0(String name)
    /*-{
		return this[name];
    }-*/;

    private final native int getAsInteger_0(String name)
    /*-{
		return Math.round(this[name]);
    }-*/;

    private final native double getAsDouble_0(String name)
    /*-{
		return this[name];
    }-*/;

    private final native String getAsString_0(String name)
    /*-{
		return this[name];
    }-*/;

    private final native boolean getAsBoolean_0(String name)
    /*-{
		return this[name];
    }-*/;
}