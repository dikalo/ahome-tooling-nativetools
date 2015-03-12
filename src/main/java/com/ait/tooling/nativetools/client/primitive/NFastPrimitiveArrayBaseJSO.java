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

package com.ait.tooling.nativetools.client.primitive;

import com.ait.tooling.nativetools.client.NArrayBaseJSO;

public class NFastPrimitiveArrayBaseJSO<T extends NFastPrimitiveArrayBaseJSO<T>> extends NArrayBaseJSO<T>
{
    protected NFastPrimitiveArrayBaseJSO()
    {
    }

    public final native T sort()
    /*-{
		return this.slice().sort(function(a, b) {
			return a - b
		});
    }-*/;

    public final native T uniq()
    /*-{
		return this.slice().sort(function(a, b) {
			return a - b
		}).reduce(function(a, b) {
			if (a.slice(-1)[0] !== b) {
				a.push(b);
			}
			return a;
		}, []);
    }-*/;
}
