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

import com.ait.tooling.nativetools.client.datamodel.AbstractDataModelWithIDList;
import com.ait.tooling.nativetools.client.datamodel.ModelIDList;

public interface IDataModelWithIDListController<T extends AbstractDataModelWithIDList<T>> extends IDataModelIDController<T>
{
    public void findFullIDList(String id, Consumer<ModelIDList> callback);

    public void findFullIDList(ModelIDList list, Consumer<ModelIDList> callback);

    public void findFullIDCollection(String id, Consumer<Collection<T>> callback);

    public void findFullIDCollection(ModelIDList list, Consumer<Collection<T>> callback);
}
