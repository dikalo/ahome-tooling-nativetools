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

package com.ait.tooling.nativetools.client.rpc;

import java.util.Collection;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.model.AbstractModelRepresentation;
import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.datamodel.AbstractJSONDataModel;
import com.ait.tooling.nativetools.client.datamodel.IJSONModel;
import com.google.gwt.http.client.Request;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JSONNamedCommandRequest extends JSONCommandRequest implements IJSONNamedCommandRequest
{
    private final String m_name;

    public JSONNamedCommandRequest(final String url, final String command)
    {
        super(url);

        m_name = StringOps.requireTrimOrNull(command);
    }

    public JSONNamedCommandRequest(final String url, final boolean usexsrf, final String command)
    {
        super(url, usexsrf);

        m_name = StringOps.requireTrimOrNull(command);
    }

    @Override
    public final String getName()
    {
        return m_name;
    }

    @Override
    public Request call(final AbstractJSONDataModel request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public Request call(final AbstractModelRepresentation<NArray> request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public <T extends IJSONModel<?>> Request call(final Collection<T> request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public <T extends IJSONModel<?>> Request call(final Collection<T> request, final String name, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, name, callback);
    }

    @Override
    public Request call(final NArray request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public Request call(final AsyncCallback<NObject> callback)
    {
        return call(getName(), callback);
    }

    @Override
    public Request call(final NObject request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public Request call(final JSONObject request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public Request call(final JSONArray request, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, callback);
    }

    @Override
    public Request call(final NArray request, final String name, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, name, callback);
    }

    @Override
    public Request call(final JSONArray request, final String name, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, name, callback);
    }

    @Override
    public Request call(final AbstractModelRepresentation<NArray> request, final String name, final AsyncCallback<NObject> callback)
    {
        return call(getName(), request, name, callback);
    }
}
