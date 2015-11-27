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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ait.tooling.common.api.java.util.function.Consumer;
import com.ait.tooling.common.api.java.util.function.Predicate;
import com.ait.tooling.common.api.types.ISearchResult;
import com.ait.tooling.common.api.types.ISearcher;
import com.ait.tooling.nativetools.client.NArray;
import com.ait.tooling.nativetools.client.NObject;
import com.ait.tooling.nativetools.client.datamodel.AbstractDataModelID;
import com.ait.tooling.nativetools.client.datamodel.ModelIDList;
import com.ait.tooling.nativetools.client.rpc.IJSONNamedCommandRequest;
import com.ait.tooling.nativetools.client.rpc.JSONCommandCallback;

@SuppressWarnings("serial")
public abstract class AbstractDataModelIDController<T extends AbstractDataModelID<T> & Comparable<T>> extends AbstractModelController<T>implements IDataModelIDController<T>, ISearcher<T>
{
    protected AbstractDataModelIDController()
    {
    }

    @Override
    public void searchFor(final Predicate<T> predicate, final Consumer<ISearchResult<T>> callback)
    {
        values(new Consumer<Collection<T>>()
        {
            @Override
            public void accept(final Collection<T> result)
            {
            }
        });
    }

    @Override
    public void update(final T model, final Consumer<Boolean> callback)
    {
        updateByID(model.getId(), model, callback);
    }

    @Override
    public void delete(final T model, final Consumer<Boolean> callback)
    {
        deleteByID(model.getId(), callback);
    }

    @Override
    public void getIDSet(final Collection<T> list, final Consumer<Set<String>> callback)
    {
        final LinkedHashSet<String> iset = new LinkedHashSet<String>(list.size());

        for (T item : list)
        {
            final String id = item.getId();

            if (null != id)
            {
                iset.add(id);
            }
        }
        callback.accept(iset);
    }

    @Override
    protected void createModelInServerStorage(final IDataModelStorage<T> storage, final T model, final Consumer<T> callback)
    {
        getCreateModelCommand().call(model, new JSONCommandCallback()
        {
            @Override
            public void onFailure(final Throwable caught)
            {
                super.onFailure(caught);

                callback.accept(null);
            }

            @Override
            public void onSuccess(final NObject result)
            {
                final T make = apply(result);

                storage.put(make.getId(), make);

                callback.accept(make);
            }
        });
    }

    @Override
    protected void deleteModelInServerStorage(final IDataModelStorage<T> storage, final ModelIDList list, final Consumer<Boolean> callback)
    {
        if (list.isEmpty())
        {
            callback.accept(true);
        }
        else
        {
            storage.remove(list);

            if (isDeleteWaitingOn())
            {
                getDeleteModelCommand().call(list, new JSONCommandCallback()
                {
                    @Override
                    public void onFailure(final Throwable caught)
                    {
                        super.onFailure(caught);

                        callback.accept(false);
                    }

                    @Override
                    public void onSuccess(NObject result)
                    {
                        callback.accept(true);
                    }
                });
            }
            else
            {
                getDeleteModelCommand().call(list, new JSONCommandCallback()
                {
                    @Override
                    public void onSuccess(NObject result)
                    {
                    }
                });
                callback.accept(true);
            }
        }
    }

    @Override
    protected void queryModelsInServerStorage(final IDataModelStorage<T> storage, final Consumer<IDataModelStorage<T>> callback)
    {
        getQueryModelsCommand().call(new JSONCommandCallback()
        {
            @Override
            public void onFailure(final Throwable caught)
            {
                super.onFailure(caught);

                callback.accept(null);
            }

            @Override
            public void onSuccess(final NObject result)
            {
                final NArray list = result.getAsArray("list");

                if (null == list)
                {
                    super.onFailure(new Exception("ERROR: null list from " + getQueryModelsCommand().getName()));
                }
                else
                {
                    final int size = list.size();

                    for (int i = 0; i < size; i++)
                    {
                        final T make = apply(list.getAsObject(i));

                        storage.put(make.getId(), make);
                    }
                }
                callback.accept(storage);
            }
        });
    }

    @Override
    protected void updateModelInServerStorage(final IDataModelStorage<T> storage, final T model, final Consumer<Boolean> callback)
    {
        if (isUpdateToStorage())
        {
            storage.put(model.getId(), model);
        }
        if (isUpdateWaitingOn())
        {
            getUpdateModelCommand().call(model, new JSONCommandCallback()
            {
                @Override
                public void onFailure(final Throwable caught)
                {
                    super.onFailure(caught);

                    callback.accept(false);
                }

                @Override
                public void onSuccess(NObject result)
                {
                    callback.accept(true);
                }
            });
        }
        else
        {
            getUpdateModelCommand().call(model, new JSONCommandCallback()
            {
                @Override
                public void onSuccess(NObject result)
                {
                }
            });
            callback.accept(true);
        }
    }

    @Override
    public void update(final Collection<T> batch, final Consumer<Boolean> callback)
    {
        prime(new Consumer<Boolean>()
        {
            @Override
            public void accept(final Boolean value)
            {
                if (value)
                {
                    if (batch.isEmpty())
                    {
                        callback.accept(true);
                    }
                    else
                    {
                        if (isUpdateToStorage())
                        {
                            for (T item : batch)
                            {
                                setModelByID(item.getId(), item);
                            }
                        }
                        if (isUpdateWaitingOn())
                        {
                            getUpdateBatchCommand().call(batch, new JSONCommandCallback()
                            {
                                @Override
                                public void onFailure(final Throwable caught)
                                {
                                    super.onFailure(caught);

                                    callback.accept(false);
                                }

                                @Override
                                public void onSuccess(NObject result)
                                {
                                    callback.accept(true);
                                }
                            });
                        }
                        else
                        {
                            getUpdateBatchCommand().call(batch, new JSONCommandCallback()
                            {
                                @Override
                                public void onSuccess(NObject result)
                                {
                                }
                            });
                            callback.accept(true);
                        }
                    }
                }
                else
                {
                    callback.accept(false);
                }
            }
        });
    }

    @Override
    public boolean isUpdateToStorage()
    {
        return false;
    }

    @Override
    public boolean isUpdateWaitingOn()
    {
        return false;
    }

    @Override
    public boolean isDeleteWaitingOn()
    {
        return false;
    }

    abstract protected IJSONNamedCommandRequest getQueryModelsCommand();

    abstract protected IJSONNamedCommandRequest getUpdateModelCommand();

    abstract protected IJSONNamedCommandRequest getUpdateBatchCommand();

    abstract protected IJSONNamedCommandRequest getDeleteModelCommand();

    abstract protected IJSONNamedCommandRequest getCreateModelCommand();
}
