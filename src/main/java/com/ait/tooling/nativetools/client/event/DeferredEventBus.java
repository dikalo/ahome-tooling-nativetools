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

package com.ait.tooling.nativetools.client.event;

import com.google.gwt.core.client.Scheduler;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.Event.Type;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class DeferredEventBus extends EventBus
{
    private final EventBus m_wrapped;

    public DeferredEventBus(EventBus wrapped)
    {
        m_wrapped = wrapped;
    }

    @Override
    public <H> HandlerRegistration addHandler(Type<H> type, H handler)
    {
        return m_wrapped.addHandler(type, handler);
    }

    @Override
    public <H> HandlerRegistration addHandlerToSource(Type<H> type, Object source, H handler)
    {
        return m_wrapped.addHandlerToSource(type, source, handler);
    }

    @Override
    public void fireEvent(Event<?> event)
    {
        Scheduler.get().scheduleDeferred(() -> m_wrapped.fireEvent(event));
    }

    @Override
    public void fireEventFromSource(Event<?> event, Object source)
    {
        Scheduler.get().scheduleDeferred(() -> m_wrapped.fireEventFromSource(event, source));
    }
}