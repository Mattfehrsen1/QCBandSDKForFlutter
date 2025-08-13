package com.qcwireless.qcwatch.ui.base.util;

import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.R;
import java.io.IOException;

/* loaded from: classes3.dex */
public class MediaUtil {
    private static final int MIN_TIME_OUT = 2000;
    private static MediaPlayer mediaPlayer = new MediaPlayer();
    private Application application;
    protected AudioManager audioManager;
    long lastNotificationTime;
    private Ringtone ringtone;
    protected Vibrator vibrator;

    public MediaUtil(Application application) {
        this.application = application;
        this.audioManager = (AudioManager) application.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        this.vibrator = (Vibrator) application.getSystemService("vibrator");
    }

    private void initMediaPlayer() throws IllegalStateException {
        try {
            MediaPlayer mediaPlayerCreate = MediaPlayer.create(this.application, R.raw.find);
            mediaPlayer = mediaPlayerCreate;
            mediaPlayerCreate.setLooping(true);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMediaSourceMp3(Context context, final String path) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        try {
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            mediaPlayer = mediaPlayer2;
            mediaPlayer2.setDataSource(path);
            XLog.i(path.toString());
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.qcwireless.qcwatch.ui.base.util.MediaUtil.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mp) throws IllegalStateException {
                    MediaUtil.mediaPlayer.start();
                    XLog.i("-----start" + path);
                }
            });
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Uri getMediaUriFromPath(Context context, String path) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursorQuery = context.getContentResolver().query(uri, null, "_display_name= ?", new String[]{path.substring(path.lastIndexOf("/") + 1)}, null);
        Uri uriWithAppendedId = cursorQuery.moveToFirst() ? ContentUris.withAppendedId(uri, cursorQuery.getLong(cursorQuery.getColumnIndex("_id"))) : null;
        cursorQuery.close();
        return uriWithAppendedId;
    }

    public void vibrateAndPlayTone() throws IllegalStateException {
        initMediaPlayer();
    }

    public void stopRing() throws IllegalStateException {
        try {
            MediaPlayer mediaPlayer2 = mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.reset();
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
