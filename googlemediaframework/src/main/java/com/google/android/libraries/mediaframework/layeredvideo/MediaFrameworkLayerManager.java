package com.google.android.libraries.mediaframework.layeredvideo;

import android.app.Activity;
import android.graphics.Color;
import android.widget.FrameLayout;

import com.google.android.libraries.mediaframework.exoplayerextensions.ExoplayerWrapper;
import com.google.android.libraries.mediaframework.exoplayerextensions.ObservablePlayerControl;
import com.google.android.libraries.mediaframework.exoplayerextensions.RendererBuilderFactory;
import com.google.android.libraries.mediaframework.exoplayerextensions.Video;

import java.util.List;

/**
 * Created by clint on 30/11/15.
 */
public class MediaFrameworkLayerManager implements LayerManager {

    /**
     * The activity that will display the video.
     */
    private Activity activity;

    /**
     * All the views created by the {@link Layer} objects will be overlaid on this container.
     */
    private FrameLayout container;

    /**
     * Allows controlling video playback, reading video state, and registering callbacks for state
     * changes.
     */
    private ObservablePlayerControl control;

    /**
     * Wrapper around ExoPlayer, which is the underlying video player.
     */
    private ExoplayerWrapper exoplayerWrapper;

    /**
     * Given a container, create the video layers and add them to the container.
     * @param activity The activity which will display the video player.
     * @param container The frame layout which will contain the views.
     * @param video the video that will be played by this LayerManager.
     * @param layers The layers which should be displayed on top of the container.
     */
    public MediaFrameworkLayerManager(Activity activity,
                        FrameLayout container,
                        Video video,
                        List<Layer> layers) {
        this.activity = activity;
        this.container = container;
        container.setBackgroundColor(Color.BLACK);

        ExoplayerWrapper.RendererBuilder rendererBuilder =
                RendererBuilderFactory.createRendererBuilder(activity, video);

        exoplayerWrapper = new ExoplayerWrapper(rendererBuilder);
        exoplayerWrapper.prepare();

        this.control = exoplayerWrapper.getPlayerControl();

        // Put the layers into the container.
        container.removeAllViews();
        for (Layer layer : layers) {
            container.addView(layer.createView(this));
            layer.onLayerDisplayed(this);
        }
    }

    /**
     * Returns the activity which displays the video player created by this {@link LayerManager}.
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Returns the {@link FrameLayout} which contains the views of the {@link Layer}s that this
     * {@link LayerManager} manages.
     */
    public FrameLayout getContainer() {
        return container;
    }

    /**
     * Returns the {@link ObservablePlayerControl} which can be used to control the video playback
     * (ex. pause, play, seek).
     */
    public ObservablePlayerControl getControl() {
        return control;
    }

    /**
     * Returns the wrapper which ties the video player to
     * {@link com.google.android.exoplayer.ExoPlayer}.
     */
    public ExoplayerWrapper getExoplayerWrapper() {
        return exoplayerWrapper;
    }

    /**
     * When the video player is no longer needed, call this method.
     */
    public void release() {
        container.removeAllViews();
        if (exoplayerWrapper != null) {
            exoplayerWrapper.release();
            exoplayerWrapper = null;
        }
    }
}