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

package com.ait.tooling.nativetools.client.datamodel;

import java.util.List;

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.common.api.model.AbstractModelRepresentation;
import com.ait.tooling.common.api.types.IMixedStringHash;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NObject;

public abstract class AbstractJSONDataModel extends AbstractModelRepresentation<NObject>implements IJSONDataModel<NObject>, IMixedStringHash
{
    protected AbstractJSONDataModel()
    {
        super(new NObject());
    }

    protected AbstractJSONDataModel(final NObject object)
    {
        super((null == object) ? (new NObject()) : (object));
    }

    @Override
    public NObject asNValue()
    {
        return getModel();
    }

    @Override
    public final String toJSONString()
    {
        return getModel().toJSONString();
    }

    @Override
    public final boolean isEmpty()
    {
        return getModel().isEmpty();
    }

    @Override
    public final boolean isDefined(final String name)
    {
        return getModel().isDefined(name);
    }

    @Override
    public final boolean isNull(final String name)
    {
        return getModel().isNull(name);
    }

    @Override
    public final boolean isString(final String name)
    {
        return getModel().isString(name);
    }

    @Override
    public final boolean isBoolean(final String name)
    {
        return getModel().isBoolean(name);
    }

    @Override
    public final boolean isObject(final String name)
    {
        return getModel().isObject(name);
    }

    @Override
    public final boolean isArray(final String name)
    {
        return getModel().isArray(name);
    }

    @Override
    public final boolean isNumber(final String name)
    {
        return getModel().isNumber(name);
    }

    @Override
    public final Object remove(final String name)
    {
        return getModel().remove(name);
    }

    @Override
    public final List<String> keys()
    {
        return getModel().keys();
    }

    @Override
    public final int size()
    {
        return getModel().size();
    }

    @Override
    public final void clear()
    {
        getModel().clear();
    }

    @Override
    public final boolean isDouble(final String name)
    {
        return getModel().isDouble(name);
    }

    @Override
    public final boolean isInteger(final String name)
    {
        return getModel().isInteger(name);
    }

    @Override
    public final String toJSONString(final int indent)
    {
        return getModel().toJSONString(indent);
    }

    @Override
    public final String toJSONString(final String indent)
    {
        return getModel().toJSONString(indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer)
    {
        return getModel().toJSONString(replacer);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final int indent)
    {
        return getModel().toJSONString(replacer, indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final String indent)
    {
        return getModel().toJSONString(replacer, indent);
    }

    @Override
    public final boolean isNativeFunction(final String name)
    {
        return getModel().isNativeFunction(name);
    }

    @Override
    public final JSONType getNativeTypeOf(final String name)
    {
        return getModel().getNativeTypeOf(name);
    }

    @Override
    public final JSONType getNativeTypeOf()
    {
        return JSONType.OBJECT;
    }
}
