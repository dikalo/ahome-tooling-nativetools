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

package com.ait.tooling.nativetools.client.config;

import com.ait.tooling.common.api.config.CommonConfig;
import com.ait.tooling.nativetools.client.util.Logging;
import com.google.gwt.core.client.EntryPoint;

public final class NativeToolsEntryPoint implements EntryPoint
{
    @Override
    public final void onModuleLoad()
    {
        CommonConfig.get().setLogging(Logging.get());

        CommonConfig.get().getPluginRegistry().addPlugin(new NativeToolsPlugin());
    }
}
