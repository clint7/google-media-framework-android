package com.google.android.libraries.mediaframework.exoplayerextensions;

import android.os.Handler;
import android.os.Looper;
import android.view.Surface;

import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.metadata.MetadataTrackRenderer;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;

import java.util.Map;

/**
 * Created by clint on 30/11/15.
 */
public interface ExoPlayerWrapperBase extends ExoPlayer.Listener, ChunkSampleSource.EventListener,
        DefaultBandwidthMeter.EventListener, MediaCodecVideoTrackRenderer.EventListener,
        MediaCodecAudioTrackRenderer.EventListener, TextRenderer,
        StreamingDrmSessionManager.EventListener, DashChunkSource.EventListener,
        HlsSampleSource.EventListener, MetadataTrackRenderer.MetadataRenderer<Map<String, Object>> {

        interface TextListener {
                void onText(String text);
        }

        interface PlaybackListener {
                void onStateChanged(boolean playWhenReady, int playbackState);
                void onError(Exception e);
                void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees,
                                        float pixelWidthHeightRatio);
        }

        Handler getMainHandler();

        void onRenderersError(Exception e);

        void onRenderers(TrackRenderer[] renderers, BandwidthMeter bandwidthMeter);

        Looper getPlaybackLooper();

        void setTextListener(TextListener listener);

        void seekTo(int positionMs);

        void addListener(PlaybackListener playbackListener);

        void setSurface(Surface surface);

        Surface getSurface();

        int getStateForTrackType(int type);

        void setPlayWhenReady(boolean playWhenReady);

        void blockingClearSurface();

        void removeListener(PlaybackListener playbackListener);

        BandwidthMeter getBandwidthMeter();

        long getBufferedPosition();

        int getTrackBitrate();

}
