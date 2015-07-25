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

package com.ait.tooling.nativetools.client.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.ait.tooling.nativetools.client.NUtils;
import com.ait.tooling.nativetools.client.NUtils.JSON;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class NFastStringCounter implements NHasJSO<NFastStringCounter.NFastStringCounterJSO>, NJSONStringify
{
    private static final long           serialVersionUID = -8270903993910958084L;

    private final NFastStringCounterJSO m_jso            = NFastStringCounterJSO.make();

    public NFastStringCounter()
    {
    }

    public NFastStringCounter(final String key)
    {
        inc(key);
    }

    public NFastStringCounter(final String key, final String... keys)
    {
        inc(key, keys);
    }

    public NFastStringCounter(final Iterable<String> keys)
    {
        inc(keys);
    }

    public final NFastStringCounter inc(final String key)
    {
        if (null != key)
        {
            m_jso.inc(NUtils.doStringRepair(key));
        }
        return this;
    }

    public final JSONObject toJSONObject()
    {
        return new JSONObject(m_jso);
    }

    public final NFastStringCounter inc(final String key, final String... keys)
    {
        inc(key);

        for (String k : keys)
        {
            inc(k);
        }
        return this;
    }

    public final NFastStringCounter inc(final Iterable<String> keys)
    {
        for (String k : keys)
        {
            inc(k);
        }
        return this;
    }

    public final NFastStringCounter dec(final String key)
    {
        if (null != key)
        {
            m_jso.dec(NUtils.doStringRepair(key));
        }
        return this;
    }

    public final NFastStringCounter dec(final String key, final String... keys)
    {
        dec(key);

        for (String k : keys)
        {
            dec(k);
        }
        return this;
    }

    public final NFastStringCounter dec(final Iterable<String> keys)
    {
        for (String k : keys)
        {
            dec(k);
        }
        return this;
    }

    public final boolean contains(final String key)
    {
        if (null != key)
        {
            return m_jso.contains(NUtils.doStringRepair(key));
        }
        return false;
    }

    public final int total()
    {
        return m_jso.total();
    }

    public final int total(final String key)
    {
        if (null != key)
        {
            return m_jso.total(NUtils.doStringRepair(key));
        }
        return 0;
    }

    public final <T extends Collection<String>> T into(final T coll)
    {
        coll.addAll(keys());

        return coll;
    }

    public final Collection<String> keys()
    {
        final ArrayList<String> keys = new ArrayList<String>();

        m_jso.keys_0(keys);

        return Collections.unmodifiableList(keys);
    }

    public final int size()
    {
        return m_jso.size();
    }

    public final NFastStringCounter clear()
    {
        m_jso.clear();

        return this;
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final int indent)
    {
        return JSON.toJSONString(m_jso, replacer, indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final String indent)
    {
        return JSON.toJSONString(m_jso, replacer, indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer)
    {
        return JSON.toJSONString(m_jso, replacer);
    }

    @Override
    public final String toJSONString(final int indent)
    {
        return JSON.toJSONString(m_jso, indent);
    }

    @Override
    public final String toJSONString(final String indent)
    {
        return JSON.toJSONString(m_jso, indent);
    }

    @Override
    public final String toJSONString()
    {
        return JSON.toJSONString(m_jso);
    }

    @Override
    public final String toString()
    {
        return toJSONString();
    }

    @Override
    public final NFastStringCounterJSO getJSO()
    {
        return m_jso;
    }

    public static final class NFastStringCounterJSO extends JavaScriptObject
    {
        protected NFastStringCounterJSO()
        {
        }

        static final NFastStringCounterJSO make()
        {
            return JavaScriptObject.createObject().cast();
        }

        private final native boolean contains(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				var val = this[key];
				return ((val !== undefined) && (val > 0));
			}
			return false;
        }-*/;

        private final native void inc(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				var val = this[key];
				if (val !== undefined) {
					this[key] = val + 1;
				} else {
					this[key] = 1;
				}
			} else {
				this[key] = 1;
			}
        }-*/;

        private final native void dec(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				var val = this[key];
				if (val !== undefined) {
					val = val - 1;
					if (val < 1) {
						delete this[key];
					} else {
						this[key] = val;
					}
				}
			}
        }-*/;

        private final native void keys_0(Collection<String> keys)
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					var val = this[name];
					if ((val !== undefined) && (val > 0)) {
						keys.@java.util.Collection::add(Ljava/lang/Object;)(name);
					}
				}
			}
        }-*/;

        private final native int size()
        /*-{
			var i = 0;

			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					var val = this[name];
					if ((val !== undefined) && (val > 0)) {
						++i;
					}
				}
			}
			return i;
        }-*/;

        private final native int total()
        /*-{
			var i = 0;

			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					var val = this[name];
					if ((val !== undefined) && (val > 0)) {
						i = i + val;
					}
				}
			}
			return i;
        }-*/;

        private final native int total(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				var val = this[key];
				if ((val !== undefined) && (val > 0)) {
					return val;
				}
			}
			return 0;
        }-*/;

        private final native void clear()
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					delete this[name];
				}
			}
        }-*/;

        private final native boolean isEmpty()
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					var val = this[name];
					if ((val !== undefined) && (val > 0)) {
						return false;
					}
				}
			}
			return true;
        }-*/;
    }
}
