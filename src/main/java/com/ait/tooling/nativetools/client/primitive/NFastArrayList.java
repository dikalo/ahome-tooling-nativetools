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

package com.ait.tooling.nativetools.client.primitive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ait.tooling.nativetools.client.NArrayBaseJSO;

/**
 * A facade implementation in JavaScript for fast Lists.
 */
public final class NFastArrayList<M> implements Iterable<M>
{
    private final FastArrayListJSO<M> m_jso;

    public NFastArrayList()
    {
        m_jso = FastArrayListJSO.make().cast();
    }

    @SuppressWarnings("unchecked")
    public NFastArrayList(final M value, final M... values)
    {
        this();

        add(value);

        if ((null != values) && (values.length > 0))
        {
            for (int i = 0; i < values.length; i++)
            {
                add(values[i]);
            }
        }
    }

    private NFastArrayList(final FastArrayListJSO<M> jso)
    {
        if (null == jso)
        {
            m_jso = FastArrayListJSO.make().cast();
        }
        else
        {
            m_jso = jso;
        }
    }

    /**
     * Return the List's size.
     * @return int
     */
    public final int size()
    {
        return m_jso.size();
    }

    /**
     * Return the primitive found at the specified index.
     * @param index
     * @return
     */
    public final M get(final int index)
    {
        if ((index >= 0) && (index < size()))
        {
            return m_jso.get(index);
        }
        return null;
    }

    /**
     * Add a value to the List
     * @param value
     */
    public final NFastArrayList<M> add(final M value)
    {
        m_jso.add(value);

        return this;
    }

    /**
     * Add a value to the List
     * @param value
     */
    public final NFastArrayList<M> set(final int i, final M value)
    {
        m_jso.set(i, value);

        return this;
    }

    /**
     * Return true if the List contains the passed in value.
     * 
     * @param value
     * @return boolean
     */
    public final boolean contains(final M value)
    {
        return m_jso.contains(value);
    }

    /**
     * Clear all values from the List.
     */
    public final NFastArrayList<M> clear()
    {
        m_jso.clear();

        return this;
    }

    /**
     * Remove the value passed in as argument from the List.
     * @param value
     */
    public final NFastArrayList<M> remove(final M value)
    {
        m_jso.remove(value);

        return this;
    }

    public final NFastArrayList<M> unshift(final M value)
    {
        m_jso.unshift(value);

        return this;
    }

    public final NFastArrayList<M> moveUp(final M value)
    {
        m_jso.moveUp(value);

        return this;
    }

    public final NFastArrayList<M> moveDown(final M value)
    {
        m_jso.moveDown(value);

        return this;
    }

    public final NFastArrayList<M> moveToTop(final M value)
    {
        if ((size() < 2) || (false == contains(value)))
        {
            return this;
        }
        remove(value);

        add(value);

        return this;
    }

    public final NFastArrayList<M> moveToBottom(final M value)
    {
        if ((size() < 2) || (false == contains(value)))
        {
            return this;
        }
        remove(value);

        unshift(value);

        return this;
    }

    public final M pop()
    {
        return m_jso.pop();
    }

    public final M shift()
    {
        return m_jso.shift();
    }

    public final NFastArrayList<M> splice(final int beg, final int removed, final M value)
    {
        m_jso.splice(beg, removed, value);

        return this;
    }

    public final NFastArrayList<M> splice(final int beg, final int removed)
    {
        m_jso.splice(beg, removed);

        return this;
    }

    public final NFastArrayList<M> reverse()
    {
        m_jso.reverse();

        return this;
    }

    @SuppressWarnings("unchecked")
    public final NFastArrayList<M> push(final M v, final M... values)
    {
        add(v);

        for (int i = 0; i < values.length; i++)
        {
            add(values[i]);
        }
        return this;
    }

    public final NFastArrayList<M> copy()
    {
        return new NFastArrayList<M>(m_jso.copy());
    }

    public final NFastArrayList<M> concat(final NFastArrayList<M> value)
    {
        if (null == value)
        {
            return new NFastArrayList<M>(m_jso.copy());
        }
        return new NFastArrayList<M>(m_jso.concat(value.m_jso));
    }

    public final NFastArrayList<M> slice(final int beg)
    {
        return new NFastArrayList<M>(m_jso.slice(beg));
    }

    public final NFastArrayList<M> slice(final int beg, final int end)
    {
        return new NFastArrayList<M>(m_jso.slice(beg, end));
    }

    public final List<M> toList()
    {
        final int size = size();

        final ArrayList<M> list = new ArrayList<M>(size);

        for (int i = 0; i < size; i++)
        {
            list.add(get(i));
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public final Iterator<M> iterator()
    {
        return toList().iterator();
    }

    private static final class FastArrayListJSO<M> extends NArrayBaseJSO<FastArrayListJSO<M>>
    {
        protected FastArrayListJSO()
        {
        }

        static final <M> FastArrayListJSO<M> make()
        {
            return NArrayBaseJSO.createNArrayBaseJSO().cast();
        }

        final native M get(int indx)
        /*-{
			return this[indx];
        }-*/;

        final native void add(M value)
        /*-{
			this[this.length] = value;
        }-*/;

        final native void set(int i, M value)
        /*-{
			this[i] = value;
        }-*/;

        final native boolean contains(M value)
        /*-{
			for (var i = 0; i < this.length; i++) {
				if (this[i] == value) {
					return true;
				}
			}
			return false;
        }-*/;

        final native void remove(M value)
        /*-{
			for (var i = 0; i < this.length; i++) {
				if (this[i] == value) {
					this.splice(i, 1);
					break;
				}
			}
        }-*/;

        final native void moveUp(M value)
        /*-{
			var leng = this.length;
			if (leng < 2) {
				return;
			}
			for (var i = 0; i < leng; i++) {
				if (this[i] == value) {
					var j = i + 1;
					if (j != leng) {
						this[i] = this[j];
						this[j] = value;
					}
					break;
				}
			}
        }-*/;

        final native void moveDown(M value)
        /*-{
			var leng = this.length;
			if (leng < 2) {
				return;
			}
			for (var i = 0; i < leng; i++) {
				if (this[i] == value) {
					if (i != 0) {
						var j = i - 1;
						this[i] = this[j];
						this[j] = value;
					}
					break;
				}
			}
        }-*/;

        final native void unshift(M value)
        /*-{
			this.unshift(value);
        }-*/;

        final native void splice(int beg, int removed, M value)
        /*-{
			this.splice(beg, removed, value);
        }-*/;

        final native M shift()
        /*-{
			return this.shift();
        }-*/;

        final native M pop()
        /*-{
			return this.pop();
        }-*/;

        final native void push(M value)
        /*-{
			this[this.length] = value;
        }-*/;
    }
}
