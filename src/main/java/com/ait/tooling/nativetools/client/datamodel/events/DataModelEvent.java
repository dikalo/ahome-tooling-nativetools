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

package com.ait.tooling.nativetools.client.datamodel.events;

import java.util.Objects;

import com.ait.tooling.nativetools.client.datamodel.IDataModel;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class DataModelEvent<M extends IDataModel<M>, H extends EventHandler> extends GwtEvent<H>
{
    private final M m_model;

    protected DataModelEvent(final M model)
    {
        m_model = Objects.requireNonNull(model);
    }

    public final M getModel()
    {
        return m_model;
    }

    public static class Type<H> extends GwtEvent.Type<H>
    {
    }
}
