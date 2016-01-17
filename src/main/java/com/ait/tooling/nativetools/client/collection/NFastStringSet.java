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

package com.ait.tooling.nativetools.client.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

import com.ait.tooling.nativetools.client.NHasJSO;
import com.ait.tooling.nativetools.client.NJSONReplacer;
import com.ait.tooling.nativetools.client.NJSONStringify;
import com.ait.tooling.nativetools.client.NUtils;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class NFastStringSet implements Iterable<String>, NHasJSO<NFastStringSet.NFastStringSetJSO>, NJSONStringify
{
    private final NFastStringSetJSO m_jso;

    public NFastStringSet(final NFastStringSetJSO jso)
    {
        m_jso = ((null == jso) ? NFastStringSetJSO.make() : jso);
    }

    public NFastStringSet()
    {
        m_jso = NFastStringSetJSO.make();
    }

    public NFastStringSet(final String key)
    {
        this();

        add(key);
    }

    public NFastStringSet(final String key, final String... keys)
    {
        this();

        add(key, keys);
    }

    public NFastStringSet(final Iterable<String> keys)
    {
        this();

        add(keys);
    }

    public NFastStringSet(final NFastStringSet nset)
    {
        this();

        add(nset);
    }

    public final JSONObject toJSONObject()
    {
        return new JSONObject(m_jso);
    }

    @Override
    public final NFastStringSetJSO getJSO()
    {
        return m_jso;
    }

    public final NFastStringSet add(final String key)
    {
        m_jso.add(Objects.requireNonNull(key));

        return this;
    }

    public final NFastStringSet add(final String key, final String... keys)
    {
        add(key);

        for (String k : keys)
        {
            add(k);
        }
        return this;
    }

    public final NFastStringSet add(final Iterable<String> keys)
    {
        for (String key : keys)
        {
            add(key);
        }
        return this;
    }

    public final NFastStringSet add(final NFastStringSet nset)
    {
        m_jso.add(Objects.requireNonNull(nset).m_jso);

        return this;
    }

    public final boolean contains(final String key)
    {
        return m_jso.contains(Objects.requireNonNull(key));
    }

    public final NFastStringSet remove(final String key)
    {
        m_jso.remove(Objects.requireNonNull(key));

        return this;
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

    public final NFastStringSet clear()
    {
        m_jso.clear();

        return this;
    }

    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    @Override
    public final Iterator<String> iterator()
    {
        return keys().iterator();
    }

    @Override
    public final String toString()
    {
        return toJSONString();
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final int indent)
    {
        return NUtils.JSON.toJSONString(m_jso, replacer, indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer, final String indent)
    {
        return NUtils.JSON.toJSONString(m_jso, replacer, indent);
    }

    @Override
    public final String toJSONString(final NJSONReplacer replacer)
    {
        return NUtils.JSON.toJSONString(m_jso, replacer);
    }

    @Override
    public final String toJSONString(final int indent)
    {
        return NUtils.JSON.toJSONString(m_jso, indent);
    }

    @Override
    public final String toJSONString(final String indent)
    {
        return NUtils.JSON.toJSONString(m_jso, indent);
    }

    @Override
    public final String toJSONString()
    {
        return NUtils.JSON.toJSONString(m_jso);
    }

    public final <T extends Collection<String>> T into(final T coll)
    {
        coll.addAll(keys());

        return coll;
    }

    public final boolean any(final NFastStringSet look)
    {
        if (null == look)
        {
            return false;
        }
        return m_jso.any(look.m_jso);
    }

    public final boolean none(final NFastStringSet look)
    {
        if (null == look)
        {
            return false;
        }
        return m_jso.none(look.m_jso);
    }

    public final boolean all(final NFastStringSet look)
    {
        if (null == look)
        {
            return false;
        }
        return m_jso.all(look.m_jso);
    }

    public static final class NFastStringSetJSO extends JavaScriptObject
    {
        protected NFastStringSetJSO()
        {
        }

        public static final NFastStringSetJSO make()
        {
            return JavaScriptObject.createObject().cast();
        }

        final native boolean contains(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				return (this[key] == true);
			}
			return false;
        }-*/;

        final native boolean add(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				if (this[key] == true) {
					return true;
				}
			}
			this[key] = true;

			return false;
        }-*/;

        final native boolean remove(String key)
        /*-{
			if (this.hasOwnProperty(String(key))) {
				delete this[key];
				return true;
			}
			return false;
        }-*/;

        final native void add(NFastStringSetJSO nset)
        /*-{
			for ( var name in nset) {
				if (nset.hasOwnProperty(String(name))) {
					if (nset[name] == true) {
						this[name] = true;
					}
				}
			}
        }-*/;

        final native void keys_0(Collection<String> keys)
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					if (this[name] == true) {
						keys.@java.util.Collection::add(Ljava/lang/Object;)(name);
					}
				}
			}
        }-*/;

        final native int size()
        /*-{
			var i = 0;
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					if (this[name] == true) {
						++i;
					}
				}
			}
			return i;
        }-*/;

        final native void clear()
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					delete this[name];
				}
			}
        }-*/;

        final native boolean isEmpty()
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					if (this[name] == true) {
						return false;
					}
				}
			}
			return true;
        }-*/;

        final native boolean any(NFastStringSetJSO look)
        /*-{
			for ( var name in look) {
				if (look.hasOwnProperty(String(name))) {
					if (look[name] == true) {
						if (this.hasOwnProperty(String(name))) {
							if (this[name] == true) {
								return true;
							}
						}
					}
				}
			}
			return false;
        }-*/;

        final native boolean none(NFastStringSetJSO look)
        /*-{
			for ( var name in look) {
				if (look.hasOwnProperty(String(name))) {
					if (look[name] == true) {
						if (this.hasOwnProperty(String(name))) {
							if (this[name] == true) {
								return false;
							}
						}
					}
				}
			}
			return true;
        }-*/;

        final native boolean all(NFastStringSetJSO look)
        /*-{
			var seen = false;
			for ( var name in look) {
				if (look.hasOwnProperty(String(name))) {
					if (look[name] == true) {
						if (this.hasOwnProperty(String(name))) {
							if (this[name] == true) {
								seen = true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
			}
			return seen;
        }-*/;
    }
}
