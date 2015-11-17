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

import com.ait.tooling.nativetools.client.NObjectBaseJSO;
import com.ait.tooling.nativetools.client.NUtils;

/**
 * Simple, super-fast minimal native Map that by default uses a String as a key, but does not fully implement the Map interface
 * 
 * For our purposes, in benchmarking, this is 50-60% faster than HashMap
 */
public final class NFastStringMap<V>
{
    private final NFastStringMapJSO<V> m_jso;

    public NFastStringMap()
    {
        m_jso = NFastStringMapJSO.make().cast();
    }

    /**
     * Add <key, value> to the map.
     * @param key
     * @param value
     */
    public final NFastStringMap<V> put(final String key, final V value)
    {
        m_jso.put(NUtils.doKeyRepair(key), value);

        return this;
    }

    /**
     * Get the value based on the key passed in.
     * @param key
     * @return
     */
    public final V get(final String key)
    {
        return m_jso.get(NUtils.doKeyRepair(key));
    }

    /**
     * Remove the value based on the key passed in as argument.
     * @param key
     */
    public final NFastStringMap<V> remove(final String key)
    {
        m_jso.remove(key);

        return this;
    }

    /**
     * Returns true if the map has a value for the specified key
     * @param key
     */
    public final boolean isDefined(final String key)
    {
        return m_jso.isDefined(key);
    }

    public final boolean isNull(final String key)
    {
        return m_jso.isNull(key);
    }

    /**
     * Returns the number of key-value mappings in this map
     */
    public final int size()
    {
        return m_jso.size();
    }

    public final NFastStringMap<V> clear()
    {
        m_jso.clear();

        return this;
    }

    public final Collection<String> keys()
    {
        return m_jso.keys();
    }

    public final Collection<V> values()
    {
        final ArrayList<V> list = new ArrayList<V>();

        m_jso.values_0(list);

        return Collections.unmodifiableList(list);
    }

    /**
     * Returns true if this map contains no key-value mappings
     */
    public final boolean isEmpty()
    {
        return m_jso.isEmpty();
    }

    private static final class NFastStringMapJSO<V> extends NObjectBaseJSO<NFastStringMapJSO<V>>
    {
        protected NFastStringMapJSO()
        {
        }

        private static final <V> NFastStringMapJSO<V> make()
        {
            return NObjectBaseJSO.createNObjectBaseJSO().cast();
        }

        public final native void put(String key, V value)
        /*-{
			this[key] = value;
        }-*/;

        public final native V get(String key)
        /*-{
			return this[key];
        }-*/;

        private final native void values_0(Collection<V> values)
        /*-{
			for ( var name in this) {
				if (this.hasOwnProperty(String(name))) {
					if (this[name] !== undefined) {
						values.@java.util.Collection::add(Ljava/lang/Object;)(this[name]);
					}
				}
			}
        }-*/;
    }
}
