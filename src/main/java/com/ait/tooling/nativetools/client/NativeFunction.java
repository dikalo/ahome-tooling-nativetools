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

public final class NativeFunction implements NHasJSO<NativeFunctionJSO>
{
    private final NativeFunctionJSO m_jso;

    public NativeFunction(final NativeFunctionJSO jso)
    {
        m_jso = jso;
    }

    @Override
    public final NativeFunctionJSO getJSO()
    {
        return m_jso;
    }

    public final JavaScriptObject call()
    {
        return m_jso.call();
    }

    public final JavaScriptObject call(final NArray args)
    {
        return m_jso.call(args);
    }

    public final JavaScriptObject call(final JavaScriptObject args)
    {
        return m_jso.call(args);
    }
}
