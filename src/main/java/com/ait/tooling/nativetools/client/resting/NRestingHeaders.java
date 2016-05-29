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

package com.ait.tooling.nativetools.client.resting;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.IHTTPConstants;
import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.nativetools.client.collection.NFastStringDictionary;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.ait.tooling.nativetools.client.storage.ClientStorage;
import com.ait.tooling.nativetools.client.storage.LocalStorage;
import com.ait.tooling.nativetools.client.storage.SessionStorage;
import com.ait.tooling.nativetools.client.util.Client;
import com.google.gwt.http.client.Header;

public class NRestingHeaders extends LinkedHashMap<String, String> implements IRestingCommon, IHTTPConstants
{
    private static final long serialVersionUID = -4981722542774013096L;

    public NRestingHeaders()
    {
    }

    public NRestingHeaders(final Map<String, String> hmap)
    {
        Objects.requireNonNull(hmap);

        for (String s : hmap.keySet())
        {
            final String k = StringOps.toTrimOrNull(Operations.clean(s));

            if (null != k)
            {
                final String v = StringOps.toTrimOrNull(Operations.clean(hmap.get(k)));

                if (null != v)
                {
                    put(k, v);
                }
            }
        }
    }

    public NRestingHeaders(final NFastStringDictionary hmap)
    {
        Objects.requireNonNull(hmap);

        for (String s : hmap.keys())
        {
            final String k = StringOps.toTrimOrNull(Operations.clean(s));

            if (null != k)
            {
                final String v = StringOps.toTrimOrNull(Operations.clean(hmap.get(k)));

                if (null != v)
                {
                    put(k, v);
                }
            }
        }
    }

    public NRestingHeaders(final NFastStringMap<String> hmap)
    {
        Objects.requireNonNull(hmap);

        for (String s : hmap.keys())
        {
            final String k = StringOps.toTrimOrNull(Operations.clean(s));

            if (null != k)
            {
                final String v = StringOps.toTrimOrNull(Operations.clean(hmap.get(k)));

                if (null != v)
                {
                    put(k, v);
                }
            }
        }
    }

    public NRestingHeaders(final Header[] hmap)
    {
        Objects.requireNonNull(hmap);

        for (Header head : hmap)
        {
            final String k = StringOps.toTrimOrNull(Operations.clean(head.getName()));

            if (null != k)
            {
                final String v = StringOps.toTrimOrNull(Operations.clean(head.getValue()));

                if (null != v)
                {
                    put(k, v);
                }
            }
        }
    }

    public Collection<String> keys()
    {
        return keySet();
    }

    protected List<String> getStorageHeaderNames()
    {
        return Arrays.asList(X_USER_ID_HEADER, X_USER_NAME_HEADER, X_SESSION_ID_HEADER, X_SESSION_UUID_HEADER, X_CLIENT_NAME_HEADER, X_CLIENT_API_TOKEN_HEADER);
    }

    protected String getHeaderFromStorage(String name)
    {
        name = StringOps.toTrimOrNull(name);

        if (null == name)
        {
            return null;
        }
        String header = null;

        if (LocalStorage.get().isSupported())
        {
            header = LocalStorage.get().getString(name);
        }
        if (null == header)
        {
            if (SessionStorage.get().isSupported())
            {
                header = SessionStorage.get().getString(name);
            }
        }
        if (null == header)
        {
            if (ClientStorage.get().isSupported())
            {
                header = ClientStorage.get().getString(name);
            }
        }
        return header;
    }

    public static final NRestingHeaders makeDefaultRESTHeaders(final NRestingHeaders hmap)
    {
        Objects.requireNonNull(hmap);

        final NRestingHeaders make = new NRestingHeaders(hmap);

        make.put(ACCEPT_HEADER, CONTENT_TYPE_APPLICATION_JSON);

        make.put(CONTENT_TYPE_HEADER, CONTENT_TYPE_APPLICATION_JSON);

        make.put(X_STRICT_JSON_FORMAT_HEADER, "true");

        make.put(X_CLIENT_UUID_HEADER, Client.get().getClientUUID());

        for (String name : hmap.getStorageHeaderNames())
        {
            final String head = StringOps.toTrimOrNull(Operations.clean(name));

            if (null != head)
            {
                final String valu = StringOps.toTrimOrNull(Operations.clean(hmap.getHeaderFromStorage(head)));

                if (null != valu)
                {
                    make.put(head, valu);
                }
            }
        }
        return make;
    }
}
