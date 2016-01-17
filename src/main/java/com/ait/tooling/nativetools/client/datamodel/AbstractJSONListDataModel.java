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

import com.ait.tooling.common.api.json.JSONType;
import com.ait.tooling.common.api.model.AbstractModelRepresentation;
import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NJSONReplacer;

public abstract class AbstractJSONListDataModel<T extends IJSONDataModel<?>> extends AbstractModelRepresentation<NArray>implements IJSONListDataModel<T>
{
    protected AbstractJSONListDataModel()
    {
        super(new NArray());
    }

    protected AbstractJSONListDataModel(final NArray list)
    {
        super((null == list) ? (new NArray()) : (list));
    }

    @Override
    public NArray asNValue()
    {
        return getModel();
    }

    @Override
    public final int size()
    {
        return getModel().size();
    }

    @Override
    public final void pop()
    {
        getModel().pop();
    }

    @Override
    public final void push(final T model)
    {
        getModel().push(model.getModel());
    }

    @Override
    public final void remove(final T item)
    {
        getModel().remove(item.getModel());
    }

    @Override
    public final void set(final int index, final T model)
    {
        getModel().set(index, model.getModel());
    }

    @Override
    public final void unshift(final T model)
    {
        getModel().unshift(model.getModel());
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
    public final void remove(final int index)
    {
        getModel().splice(index, 1);
    }

    @Override
    public final void clear()
    {
        getModel().clear();
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
    public final JSONType getNativeTypeOf(final int index)
    {
        return getModel().getNativeTypeOf(index);
    }

    @Override
    public final JSONType getNativeTypeOf()
    {
        return JSONType.ARRAY;
    }
}
