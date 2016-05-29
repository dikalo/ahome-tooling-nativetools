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

package com.ait.tooling.nativetools.client.resting;

public class InactiveRestingException extends RestingException
{
    private static final long serialVersionUID = 2891756861169701591L;

    public InactiveRestingException(final NMethod type, final String url, final long cntr, final long time)
    {
        super("Inative: " + type.toString() + " to url [" + url + "] count [" + cntr + "] time [" + time + "]", type, url, cntr, time);
    }
}
