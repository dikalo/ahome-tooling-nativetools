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

package com.ait.tooling.nativetools.client.usermedia;

import java.util.Objects;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.media.client.Video;

public final class UserMediaInstance
{
    private static final UserMediaInstance INSTANCE = new UserMediaInstance();

    public static final UserMediaInstance get()
    {
        return INSTANCE;
    }

    private UserMediaInstance()
    {
    }

    public final void getUserMedia(final Video video, final UserMediaCallback mediacb)
    {
        getUserMedia(Objects.requireNonNull(video).getVideoElement(), new UserMediaConfiguration(true, true), Objects.requireNonNull(mediacb));
    }

    public final void getUserMedia(final VideoElement element, final UserMediaCallback mediacb)
    {
        getUserMedia(Objects.requireNonNull(element), new UserMediaConfiguration(true, true), Objects.requireNonNull(mediacb));
    }

    public final void getUserMedia(final Video video, final UserMediaConfiguration configs, final UserMediaCallback mediacb)
    {
        getUserMedia(Objects.requireNonNull(video).getVideoElement(), Objects.requireNonNull(configs), Objects.requireNonNull(mediacb));
    }

    public final void getUserMedia(VideoElement element, UserMediaConfiguration configs, UserMediaCallback mediacb)
    {
        element = Objects.requireNonNull(element);

        configs = Objects.requireNonNull(configs);

        mediacb = Objects.requireNonNull(mediacb);

        if (isSupported())
        {
            getUserMedia_0(element, Objects.requireNonNull(configs.getJSO()), mediacb);
        }
        else
        {
            mediacb.onError(new UserMediaException("NOT_SUPPORTED"));
        }
    }

    public final native boolean isSupported()
    /*-{
		navigator.getUserMedia = navigator.getUserMedia
				|| navigator.webkitGetUserMedia || navigator.mozGetUserMedia
				|| navigator.msGetUserMedia;

		return !!navigator.getUserMedia;
    }-*/;

    private final native void getUserMedia_0(VideoElement element, JavaScriptObject config, UserMediaCallback mediacb)
    /*-{
		var doError = function(message) {
			var e = @com.ait.tooling.nativetools.client.usermedia.UserMediaException::new(Ljava/lang/String;)(message);
			mediacb.@com.ait.tooling.nativetools.client.usermedia.UserMediaCallback::onError(Lcom/ait/tooling/nativetools/client/usermedia/UserMediaException;)(e);
		}
		navigator.getUserMedia = navigator.getUserMedia
                || navigator.webkitGetUserMedia || navigator.mozGetUserMedia
                || navigator.msGetUserMedia;

		if (navigator.getUserMedia) {
			var onReady = function(stream) {
				element.src = $wnd.URL.createObjectURL(stream);
				element.volume = 0.0;
				element.play();
				mediacb.@com.ait.tooling.nativetools.client.usermedia.UserMediaCallback::onReady(Lcom/google/gwt/dom/client/MediaElement;Lcom/ait/tooling/nativetools/client/usermedia/UserMediaStream;)(element, stream);
			};
			var onError = function(error) {
				var type = typeof error;
				if (type === 'string') {
					if (error == 'PERMISSION_DENIED') {
						doError('PERMISSION_DENIED');
					} else {
						doError('CONSTRAINT_NOT_SATISFIED');
					}
				} else {
					if (error.name) {
						doError(error.name);
					} else {
						if (error['PERMISSION_DENIED']) {
							doError('PERMISSION_DENIED');
						} else {
							doError('CONSTRAINT_NOT_SATISFIED');
						}
					}
				}
			};
			navigator.getUserMedia(config, onReady, onError);
		} else {
			doError('NOT_SUPPORTED');
		}
    }-*/;
}
