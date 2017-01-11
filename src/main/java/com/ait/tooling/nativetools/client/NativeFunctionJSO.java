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

package com.ait.tooling.nativetools.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class NativeFunctionJSO extends JavaScriptObject
{
    public static final NativeFunctionJSO make(final JavaScriptObject func)
    {
        if (NUtils.Native.isNativeFunction(func))
        {
            return func.cast();
        }
        return null;
    }

    public static final native NativeFunctionJSO make()
    /*-{
		return function(args) {
			return null;
		};
    }-*/;

    protected NativeFunctionJSO()
    {
    }

    public final JavaScriptObject call()
    {
        return call_0(this);
    }

    public final JavaScriptObject call(final NArray args)
    {
        return call_0(this, args.getJSO());
    }

    public final JavaScriptObject call(final JavaScriptObject args)
    {
        if (NUtils.Native.isArray(args))
        {
            return call_0(this, args.cast());
        }
        final NArrayJSO list = NArrayJSO.make();

        list.push(args);

        return call_0(this, list);
    }

    private static final native JavaScriptObject call_0(NativeFunctionJSO func)
    /*-{
		return func.apply(null, null);
    }-*/;

    private static final native JavaScriptObject call_0(NativeFunctionJSO func, NArrayJSO args)
    /*-{
		return func.apply(null, args);
    }-*/;
}
