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

package com.ait.tooling.nativetools.client.resting;

import com.ait.tooling.common.api.types.IActivatable;
import com.ait.tooling.nativetools.client.NObjectOnWire;

public interface IResting extends IRestingCommon, IActivatable
{
    public String getPrefix();
    
    public IRestingRequest get(String url, IRestingResponseCallback callback);

    public IRestingRequest get(String url, NRestingHeaders headers, IRestingResponseCallback callback);

    public IRestingRequest put(String url, NObjectOnWire body, IRestingResponseCallback callback);

    public IRestingRequest put(String url, NObjectOnWire body, NRestingHeaders headers, IRestingResponseCallback callback);

    public IRestingRequest post(String url, NObjectOnWire body, IRestingResponseCallback callback);

    public IRestingRequest post(String url, NObjectOnWire body, NRestingHeaders headers, IRestingResponseCallback callback);

    public IRestingRequest patch(String url, NObjectOnWire body, IRestingResponseCallback callback);

    public IRestingRequest patch(String url, NObjectOnWire body, NRestingHeaders headers, IRestingResponseCallback callback);

    public IRestingRequest delete(String url, IRestingResponseCallback callback);

    public IRestingRequest delete(String url, NRestingHeaders headers, IRestingResponseCallback callback);
}