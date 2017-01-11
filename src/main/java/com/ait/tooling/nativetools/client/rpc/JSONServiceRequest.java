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

package com.ait.tooling.nativetools.client.rpc;

import com.ait.tooling.common.api.java.util.StringOps;

public class JSONServiceRequest extends JSONNamedCommandRequest implements IJSONServiceRequest
{
    private static final String commandNameFromURL(String name)
    {
        name = StringOps.toTrimOrNull(name);

        if (null != name)
        {
            int indx = name.lastIndexOf("/");

            if (indx >= 0)
            {
                name = StringOps.toTrimOrNull(name.substring(indx + 1));
            }
            if (null != name)
            {
                if (name.endsWith(".rpc"))
                {
                    name = StringOps.toTrimOrNull(name.substring(0, name.length() - 4));
                }
            }
        }
        return StringOps.requireTrimOrNull(name);
    }

    public JSONServiceRequest(final String url)
    {
        super(StringOps.requireTrimOrNull(url), commandNameFromURL(url));
    }

    public JSONServiceRequest(final String url, final boolean usexsrf)
    {
        super(StringOps.requireTrimOrNull(url), usexsrf, commandNameFromURL(url));
    }

    @Override
    public final boolean isCommandInBody()
    {
        return false;
    }
}
