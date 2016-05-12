/**
 Copyright 2014 Google Inc. All rights reserved.

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

package com.google.android.libraries.mediaframework.layeredvideo;

import android.app.Activity;
import android.graphics.Color;
import android.widget.FrameLayout;

import com.google.android.exoplayer.util.PlayerControl;
import com.google.android.libraries.mediaframework.exoplayerextensions.ExoPlayerWrapperBase;
import com.google.android.libraries.mediaframework.exoplayerextensions.ExoplayerWrapper;
import com.google.android.libraries.mediaframework.exoplayerextensions.ObservablePlayerControl;
import com.google.android.libraries.mediaframework.exoplayerextensions.RendererBuilderFactory;
import com.google.android.libraries.mediaframework.exoplayerextensions.Video;

import java.util.List;

/**
 * This is the basis for building a layered video player
 * (i.e. a video player with views overlaid on top of it).
 *
 * <p>Given a {@link FrameLayout}, a {@link Video}, and a set of {@link Layer} objects, the
 * {@link LayerManager} will create an {@link ExoplayerWrapper} for the {@link Video} object and
 * create each {@link Layer} object's view and overlay it on the {@link FrameLayout} object.
 *
 * <p>Look at {@link SimpleVideoPlayer} to see {@link LayerManager} in action.
 */
public interface LayerManager {

  /**
   * Returns the activity which displays the video player created by this {@link LayerManager}.
   */
  Activity getActivity();


  /**
   * Returns the {@link FrameLayout} which contains the views of the {@link Layer}s that this
   * {@link LayerManager} manages.
   */
  FrameLayout getContainer();

  /**
   * Returns the {@link ObservablePlayerControl} which can be used to control the video playback
   * (ex. pause, play, seek).
   */
  ObservablePlayerControl getControl();

  /**
   * Returns the wrapper which ties the video player to
   * {@link com.google.android.exoplayer.ExoPlayer}.
   */
  ExoPlayerWrapperBase getExoplayerWrapper();

  /**
   * When the video player is no longer needed, call this method.
   */
  void release();
}
