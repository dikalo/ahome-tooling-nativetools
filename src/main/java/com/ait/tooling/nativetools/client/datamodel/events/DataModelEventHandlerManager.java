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

package com.ait.tooling.nativetools.client.datamodel.events;

import java.util.Objects;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public class DataModelEventHandlerManager
{
    private final HandlerManager m_events         = new HandlerManager(this);

    public DataModelEventHandlerManager()
    {
    }

    public void fireEvent(final DataModelEvent<?, ?> event)
    {
        m_events.fireEvent(Objects.requireNonNull(event));
    }

    public void fireEventDeferred(final GwtEvent<?> event)
    {
        Objects.requireNonNull(event);

        Scheduler.get().scheduleDeferred(new ScheduledCommand()
        {
            @Override
            public void execute()
            {
                m_events.fireEvent(event);
            }
        });
    }

    public void fireEventFinally(final DataModelEvent<?, ?> event)
    {
        Objects.requireNonNull(event);

        Scheduler.get().scheduleFinally(new ScheduledCommand()
        {
            @Override
            public void execute()
            {
                m_events.fireEvent(event);
            }
        });
    }

    public <H extends EventHandler> HandlerRegistration addHandler(final DataModelEvent.Type<H> type, final H handler)
    {
        return m_events.addHandler(Objects.requireNonNull(type), Objects.requireNonNull(handler));
    }
}
