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

package com.ait.tooling.nativetools.client.datamodel.controller;

import java.util.Collection;
import java.util.function.Consumer;

import com.ait.tooling.nativetools.client.collection.NFastStringSet;
import com.ait.tooling.nativetools.client.datamodel.AbstractDataModelWithIDList;
import com.ait.tooling.nativetools.client.datamodel.ModelIDList;

public abstract class AbstractDataModelWithIDListController<T extends AbstractDataModelWithIDList<T> & Comparable<T>> extends AbstractDataModelIDController<T> implements IDataModelWithIDListController<T>
{
    protected AbstractDataModelWithIDListController()
    {
    }

    @Override
    public void deleteByID(final ModelIDList list, final Consumer<Boolean> callback)
    {
        if (list.isEmpty())
        {
            callback.accept(true);
        }
        else
        {
            findFullIDList(list, new Consumer<ModelIDList>()
            {
                @Override
                public final void accept(final ModelIDList result)
                {
                    AbstractDataModelWithIDListController.super.deleteByID(result, callback);
                }
            });
        }
    }

    @Override
    public void findFullIDList(final String id, final Consumer<ModelIDList> callback)
    {
        findFullIDList(new ModelIDList(id), callback);
    }

    @Override
    public void findFullIDList(final ModelIDList list, final Consumer<ModelIDList> callback)
    {
        if (list.isEmpty())
        {
            callback.accept(list);
        }
        else
        {
            prime(new Consumer<Boolean>()
            {
                @Override
                public void accept(final Boolean result)
                {
                    if (result)
                    {
                        callback.accept(new ModelIDList(subsetByID(new NFastStringSet(), list).keys()));
                    }
                    else
                    {
                        callback.accept(new ModelIDList());
                    }
                }
            });
        }
    }

    final NFastStringSet subsetByID(final NFastStringSet find, final ModelIDList list)
    {
        final int size = list.size();

        if (size > 0)
        {
            for (int i = 0; i < size; i++)
            {
                final String id = list.get(i);

                if (null != id)
                {
                    final T item = getModelByID(id);

                    if (null != item)
                    {
                        find.add(id);

                        subsetByID(find, item.getIDList());
                    }
                }
            }
        }
        return find;
    }

    @Override
    public void findFullIDCollection(final String id, final Consumer<Collection<T>> callback)
    {
        findFullIDCollection(new ModelIDList(id), callback);
    }

    @Override
    public void findFullIDCollection(final ModelIDList list, final Consumer<Collection<T>> callback)
    {
        findFullIDList(list, new Consumer<ModelIDList>()
        {
            @Override
            public final void accept(final ModelIDList result)
            {
                findByID(result, new Consumer<Collection<T>>()
                {
                    @Override
                    public final void accept(final Collection<T> result)
                    {
                        callback.accept(result);
                    }
                });
            }
        });
    }
}
