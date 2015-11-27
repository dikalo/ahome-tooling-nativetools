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
import java.util.Comparator;

import com.ait.tooling.common.api.java.util.IAsyncFilter;
import com.ait.tooling.common.api.java.util.IFilter;
import com.ait.tooling.common.api.java.util.NoOpConsumer;
import com.ait.tooling.common.api.java.util.function.Consumer;
import com.ait.tooling.common.api.java.util.function.Predicate;
import com.ait.tooling.nativetools.client.datamodel.AbstractJSONDataModel;
import com.ait.tooling.nativetools.client.datamodel.ModelIDList;

public interface IDataModelController<T extends AbstractJSONDataModel>
{
    public static final Consumer<Boolean> NO_OP_CALLBACK = new NoOpConsumer<Boolean>();

    public Collection<T> getEmpty();

    public void getEmpty(Consumer<Collection<T>> callback);

    public void prime(Consumer<Boolean> callback);

    public void filter(IAsyncFilter<T> filter, Consumer<Collection<T>> callback);

    public void filter(Predicate<T> predicate, Consumer<Collection<T>> callback);

    public void filter(Predicate<T> predicate, Comparator<T> compareit, Consumer<Collection<T>> callback);

    public void filter(Collection<T> collection, IAsyncFilter<T> filter, Consumer<Collection<T>> callback);

    public void filter(Collection<T> collection, Predicate<T> predicate, Consumer<Collection<T>> callback);

    public void filter(Collection<T> collection, Predicate<T> predicate, Comparator<T> compareit, Consumer<Collection<T>> callback);

    public Collection<T> filter(Collection<T> collection, IFilter<T> filter);

    public Collection<T> filter(Collection<T> collection, Predicate<T> predicate);

    public void values(Consumer<Collection<T>> callback);

    public void create(T model, Consumer<T> callback);

    public void findByID(String id, Consumer<T> callback);

    public void findByID(ModelIDList list, Consumer<Collection<T>> callback);

    public void updateByID(String id, T model, Consumer<Boolean> callback);

    public void deleteByID(String id, Consumer<Boolean> callback);

    public void deleteByID(ModelIDList list, Consumer<Boolean> callback);

    public String getName();
}