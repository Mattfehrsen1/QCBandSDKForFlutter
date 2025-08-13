package com.king.zxing.manager;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Vibrator;
import com.king.zxing.R;
import com.king.zxing.util.LogUtils;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class BeepManager implements MediaPlayer.OnErrorListener, Closeable {
    private static final long VIBRATE_DURATION = 200;
    private final Context context;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean vibrate;
    private Vibrator vibrator;

    public BeepManager(Context context) {
        this.context = context;
        updatePrefs();
    }

    public void setVibrate(boolean z) {
        this.vibrate = z;
    }

    public void setPlayBeep(boolean z) {
        this.playBeep = z;
    }

    private synchronized void updatePrefs() {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = buildMediaPlayer(this.context);
        }
        if (this.vibrator == null) {
            this.vibrator = (Vibrator) this.context.getSystemService("vibrator");
        }
    }

    public synchronized void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer;
        if (this.playBeep && (mediaPlayer = this.mediaPlayer) != null) {
            mediaPlayer.start();
        }
        if (this.vibrate) {
            this.vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    private MediaPlayer buildMediaPlayer(Context context) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(R.raw.zxl_beep);
            mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (Exception e) {
            LogUtils.w(e);
            mediaPlayer.release();
            return null;
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        close();
        updatePrefs();
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                this.mediaPlayer = null;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
