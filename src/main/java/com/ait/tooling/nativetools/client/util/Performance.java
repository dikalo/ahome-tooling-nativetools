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

package com.ait.tooling.nativetools.client.util;

import java.util.Objects;

import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.ait.tooling.nativetools.client.NUtils;
import com.google.gwt.core.client.JavaScriptObject;

public final class Performance
{
    private static final Performance INSTANCE = new Performance();

    private final PerformanceJSO     m_jso;

    public static final Performance get()
    {
        return INSTANCE;
    }

    private Performance()
    {
        m_jso = PerformanceJSO.make().init();
    }

    public final double now()
    {
        return m_jso.now_0();
    }

    public final void mark(final String name)
    {
        m_jso.mark_0(Objects.requireNonNull(name));
    }

    public final PerformanceEntryList getEntries()
    {
        return new PerformanceEntryList(m_jso.getEntries_0());
    }

    public final PerformanceEntryList getEntriesByName(final String name)
    {
        return new PerformanceEntryList(m_jso.getEntriesByName_0(Objects.requireNonNull(name)));
    }

    public final PerformanceEntryList getEntriesByType(final String type)
    {
        return new PerformanceEntryList(m_jso.getEntriesByType_0(Objects.requireNonNull(type)));
    }

    public final void clearMarks()
    {
        m_jso.clearMarks_0();
    }

    public final void clearMarks(final String name)
    {
        m_jso.clearMarks_0(Objects.requireNonNull(name));
    }

    @SuppressWarnings("serial")
    public static final class PerformanceEntry implements NJSONStringify
    {
        private final PerformanceEntryJSO m_jso;

        protected PerformanceEntry(final PerformanceEntryJSO jso)
        {
            m_jso = jso;
        }
        
        public final String getName()
        {
            return m_jso.getName();
        }

        public final String getEntryType()
        {
            return m_jso.getEntryType();
        }

        public final double getStartTime()
        {
            return m_jso.getStartTime();
        }

        public final double getDuration()
        {
            return m_jso.getDuration();
        }

        @Override
        public String toJSONString()
        {
            return NUtils.JSON.toJSONString(m_jso);
        }

        @Override
        public String toJSONString(int indent)
        {
            return NUtils.JSON.toJSONString(m_jso, indent);
        }

        @Override
        public String toJSONString(String indent)
        {
            return NUtils.JSON.toJSONString(m_jso, indent);
        }

        @Override
        public String toJSONString(NJSONReplacer replacer)
        {
            return NUtils.JSON.toJSONString(m_jso, replacer);
        }

        @Override
        public String toJSONString(NJSONReplacer replacer, int indent)
        {
            return NUtils.JSON.toJSONString(m_jso, replacer, indent);
        }

        @Override
        public String toJSONString(NJSONReplacer replacer, String indent)
        {
            return NUtils.JSON.toJSONString(m_jso, replacer, indent);
        }
    }

    private static final class PerformanceEntryJSO extends JavaScriptObject
    {
        protected PerformanceEntryJSO()
        {
        }

        public final native String getName()
        /*-{
			return this.name;
        }-*/;

        public final native String getEntryType()
        /*-{
			return this.entryType;
        }-*/;

        public final native double getStartTime()
        /*-{
			return this.startTime;
        }-*/;

        public final native double getDuration()
        /*-{
			return this.duration;
        }-*/;
    }

    @SuppressWarnings("serial")
    public static final class PerformanceEntryList implements NJSONStringify
    {
        private final PerformanceEntryListJSO m_jso;

        protected PerformanceEntryList(final PerformanceEntryListJSO jso)
        {
            m_jso = jso;
        }

        public final int size()
        {
            return m_jso.size();
        }

        public final PerformanceEntry get(final int index)
        {
            final PerformanceEntryJSO jso = m_jso.get(index);

            if (null != jso)
            {
                return new PerformanceEntry(jso);
            }
            return null;
        }

        @Override
        public String toJSONString()
        {
            return NUtils.JSON.toJSONString(m_jso);
        }

        @Override
        public String toJSONString(int indent)
        {
            return NUtils.JSON.toJSONString(m_jso, indent);
        }

        @Override
        public String toJSONString(String indent)
        {
            return NUtils.JSON.toJSONString(m_jso, indent);
        }

        @Override
        public String toJSONString(NJSONReplacer replacer)
        {
            return NUtils.JSON.toJSONString(m_jso, replacer);
        }

        @Override
        public String toJSONString(NJSONReplacer replacer, int indent)
        {
            return NUtils.JSON.toJSONString(m_jso, replacer, indent);
        }

        @Override
        public String toJSONString(NJSONReplacer replacer, String indent)
        {
            return NUtils.JSON.toJSONString(m_jso, replacer, indent);
        }
    }

    private static final class PerformanceEntryListJSO extends JavaScriptObject
    {
        protected PerformanceEntryListJSO()
        {
        }

        public final native int size()
        /*-{
			return this.length;
        }-*/;

        public final native PerformanceEntryJSO get(int index)
        /*-{
			return this[index];
        }-*/;
    }

    private static final class PerformanceJSO extends JavaScriptObject
    {
        protected static final native PerformanceJSO make()
        /*-{
			return $wnd.performance || {};
        }-*/;

        protected PerformanceJSO()
        {
        }

        protected final native PerformanceJSO init()
        /*-{
			if (!this.now) {
				this.now = Date.now || function() {
					return (new Date()).getTime();
				};
			}
			return this;
        }-*/;

        protected final native double now_0()
        /*-{
			return this.now();
        }-*/;

        protected final native void mark_0(String name)
        /*-{
			if (this.mark) {
				this.mark(name);
			}
        }-*/;

        protected final native void clearMarks_0()
        /*-{
			if (this.clearMarks) {
				this.clearMarks();
			}
        }-*/;

        protected final native void clearMarks_0(String name)
        /*-{
			if (this.clearMarks) {
				this.clearMarks(name);
			}
        }-*/;

        protected final native PerformanceEntryListJSO getEntries_0()
        /*-{
			if (this.getEntries) {
				var p = this.getEntries();
				if (p) {
					return p;
				}
			}
			return [];
        }-*/;

        protected final native PerformanceEntryListJSO getEntriesByName_0(String name)
        /*-{
			if (this.getEntriesByName) {
				var p = this.getEntriesByName(name);
				if (p) {
					return p;
				}
			}
			return [];
        }-*/;

        protected final native PerformanceEntryListJSO getEntriesByType_0(String type)
        /*-{
			if (this.getEntriesByType) {
				var p = this.getEntriesByType(type);
				if (p) {
					return p;
				}
			}
			return [];
        }-*/;
    }
}
