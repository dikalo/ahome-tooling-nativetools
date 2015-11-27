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

package com.ait.tooling.nativetools.client.datamodel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.ait.tooling.common.api.java.util.Filters;
import com.ait.tooling.common.api.java.util.IAsyncFilter;
import com.ait.tooling.common.api.java.util.IFilter;
import com.ait.tooling.common.api.java.util.function.Consumer;
import com.ait.tooling.common.api.java.util.function.Function;
import com.ait.tooling.common.api.java.util.function.Predicate;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.datamodel.AbstractJSONDataModel;
import com.ait.tooling.nativetools.client.datamodel.ModelIDList;

public abstract class AbstractModelController<T extends AbstractJSONDataModel> implements IDataModelController<T>, Function<NObject, T>
{
    private IDataModelStorage<T>         m_storage = null;

    private final Collection<T>          m_emptied = Collections.unmodifiableCollection(new ArrayList<T>(0));

    private ArrayList<Consumer<Boolean>> m_waiting = null;

    protected AbstractModelController()
    {
    }

    @Override
    public final Collection<T> getEmpty()
    {
        return m_emptied;
    }

    @Override
    public final void getEmpty(final Consumer<Collection<T>> callback)
    {
        callback.accept(getEmpty());
    }

    @Override
    public final void prime(final Consumer<Boolean> callback)
    {
        queryModelsInCache(callback);
    }

    @Override
    public final void filter(final IAsyncFilter<T> filter, final Consumer<Collection<T>> callback)
    {
        values(new Consumer<Collection<T>>()
        {
            @Override
            public void accept(final Collection<T> results)
            {
                filter(results, filter, callback);
            }
        });
    }

    @Override
    public final void filter(final Predicate<T> predicate, final Comparator<T> compareit, final Consumer<Collection<T>> callback)
    {
        filter(Filters.async(predicate, compareit), callback);
    }

    @Override
    public final void filter(final Predicate<T> predicate, final Consumer<Collection<T>> callback)
    {
        filter(Filters.async(predicate), callback);
    }

    @Override
    public final void filter(final Collection<T> collection, final IAsyncFilter<T> filter, final Consumer<Collection<T>> callback)
    {
        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            filter.filter(collection, callback);
        }
    }

    @Override
    public final void filter(final Collection<T> collection, final Predicate<T> predicate, final Comparator<T> compareit, final Consumer<Collection<T>> callback)
    {
        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            filter(collection, Filters.async(predicate, compareit), callback);
        }
    }

    @Override
    public final void filter(final Collection<T> collection, final Predicate<T> predicate, final Consumer<Collection<T>> callback)
    {
        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            filter(collection, Filters.async(predicate), callback);
        }
    }

    @Override
    public final Collection<T> filter(final Collection<T> collection, final IFilter<T> filter)
    {
        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return filter.filter(collection);
        }
    }

    @Override
    public final Collection<T> filter(final Collection<T> collection, final Predicate<T> predicate)
    {
        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return filter(collection, Filters.make(predicate));
        }
    }

    @Override
    public final void values(final Consumer<Collection<T>> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    callback.accept(m_storage.values());
                }
                else
                {
                    getEmpty(callback);
                }
            }
        });
    }

    @Override
    public void create(final T model, final Consumer<T> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    createModelInServerStorage(m_storage, model, new Consumer<T>()
                    {
                        @Override
                        public void accept(final T result)
                        {
                            callback.accept(result);
                        }
                    });
                }
                else
                {
                    callback.accept(null);
                }
            }
        });
    }

    @Override
    public void findByID(final String id, final Consumer<T> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    callback.accept(m_storage.get(id));
                }
                else
                {
                    callback.accept(null);
                }
            }
        });
    }

    @Override
    public void findByID(final ModelIDList list, final Consumer<Collection<T>> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    final int size = list.size();

                    if (size > 0)
                    {
                        callback.accept(m_storage.get(list));
                    }
                    else
                    {
                        getEmpty(callback);
                    }
                }
                else
                {
                    callback.accept(null);
                }
            }
        });
    }

    private final void queryModelsInCache(final Consumer<Boolean> callback)
    {
        if (null != m_storage)
        {
            callback.accept(true);
        }
        else
        {
            if (null != m_waiting)
            {
                m_waiting.add(callback);
            }
            else
            {
                m_waiting = new ArrayList<Consumer<Boolean>>();

                m_waiting.add(callback);

                queryModelsInServerStorage(makeClientStorage(), new Consumer<IDataModelStorage<T>>()
                {
                    @Override
                    public void accept(final IDataModelStorage<T> result)
                    {
                        if (null == result)
                        {
                            for (Consumer<Boolean> entry : m_waiting)
                            {
                                entry.accept(false);
                            }
                            m_waiting.clear();

                            m_waiting = null;
                        }
                        else
                        {
                            m_storage = result;

                            for (Consumer<Boolean> entry : m_waiting)
                            {
                                entry.accept(true);
                            }
                            m_waiting.clear();

                            m_waiting = null;
                        }
                    }
                });
            }
        }
    }

    @Override
    public void updateByID(final String id, final T model, final Consumer<Boolean> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    updateModelInServerStorage(m_storage, model, callback);
                }
                else
                {
                    callback.accept(false);
                }
            }
        });
    }

    final void setModelByID(final String id, final T model)
    {
        m_storage.put(id, model);
    }

    final T getModelByID(final String id)
    {
        return m_storage.get(id);
    }

    @Override
    public void deleteByID(final String id, final Consumer<Boolean> callback)
    {
        deleteByID(new ModelIDList(id), callback);
    }

    @Override
    public void deleteByID(final ModelIDList list, final Consumer<Boolean> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    deleteModelInServerStorage(m_storage, list, callback);
                }
                else
                {
                    callback.accept(false);
                }
            }
        });
    }

    protected NFastStringMapDataModelStorage<T> makeClientStorage()
    {
        return new NFastStringMapDataModelStorage<T>(getName(), this);
    }

    abstract protected void createModelInServerStorage(IDataModelStorage<T> storage, T model, Consumer<T> callback);

    abstract protected void updateModelInServerStorage(IDataModelStorage<T> storage, T model, Consumer<Boolean> callback);

    abstract protected void deleteModelInServerStorage(IDataModelStorage<T> storage, ModelIDList list, Consumer<Boolean> callback);

    abstract protected void queryModelsInServerStorage(IDataModelStorage<T> storage, Consumer<IDataModelStorage<T>> callback);
}
