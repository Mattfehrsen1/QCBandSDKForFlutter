package com.qcwireless.qcwatch.ui.device.record;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class ADAudioTrackPlayer {
    private AudioTrack aTrack;
    private boolean isBigendian;
    private boolean isStop;
    private Context mContext;
    private int mFormat;
    private InputStream mInputStream;
    private int mMinBufferSize;
    private Thread mThread;
    private String path;

    public ADAudioTrackPlayer(Context c, String path, int sampleRate, int format, int ch_layout, boolean isBig) {
        this.mContext = c;
        this.mFormat = format;
        this.isBigendian = isBig;
        this.path = path;
        initAudioTrackByBuilder(ch_layout, sampleRate, format);
        initInputStream(path);
    }

    private void initAudioTrack(int ch_layout, int sampleRate, int format) {
        this.mMinBufferSize = AudioTrack.getMinBufferSize(sampleRate, ch_layout, format);
        this.aTrack = new AudioTrack(3, sampleRate, ch_layout, format, this.mMinBufferSize, 1);
    }

    private void initAudioTrackByBuilder(int ch_layout, int sampleRate, int format) {
        this.mMinBufferSize = AudioTrack.getMinBufferSize(sampleRate, ch_layout, format);
        this.aTrack = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(format).setSampleRate(sampleRate).setChannelMask(ch_layout).build()).setBufferSizeInBytes(this.mMinBufferSize).build();
    }

    private void initInputStream(String path) {
        try {
            this.mInputStream = new FileInputStream(path);
        } catch (IOException unused) {
        }
    }

    public void play() throws IllegalStateException {
        this.isStop = false;
        initInputStream(this.path);
        XLog.i(Integer.valueOf(this.aTrack.getState()));
        AudioTrack audioTrack = this.aTrack;
        if (audioTrack == null || audioTrack.getState() == 0) {
            return;
        }
        XLog.i("start");
        this.aTrack.play();
        startAudioThread();
    }

    public void stop() throws IllegalStateException, InterruptedException {
        this.isStop = true;
        AudioTrack audioTrack = this.aTrack;
        if (audioTrack != null && audioTrack.getState() != 0) {
            this.aTrack.stop();
        }
        Thread thread = this.mThread;
        if (thread != null) {
            try {
                thread.join();
                this.mThread = null;
            } catch (InterruptedException unused) {
            }
        }
        release();
    }

    public void release() {
        this.aTrack.release();
        this.aTrack = null;
    }

    private void startAudioThread() {
        if (this.mThread == null) {
            Thread thread = new Thread(new AudioThread());
            this.mThread = thread;
            thread.start();
        }
    }

    private class AudioThread implements Runnable {
        private AudioThread() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("AudioThread start mMinBufferSize==> ");
            sb.append(ADAudioTrackPlayer.this.mMinBufferSize);
            sb.append("----");
            sb.append(!ADAudioTrackPlayer.this.isStop);
            XLog.i(sb.toString());
            while (!ADAudioTrackPlayer.this.isStop) {
                try {
                    byte[] bArr = new byte[1024];
                    int i = ADAudioTrackPlayer.this.mInputStream.read(bArr);
                    if (ADAudioTrackPlayer.this.mFormat == 4) {
                        float[] fArrBytesToFloats = ByteUtil.bytesToFloats(bArr, i, ADAudioTrackPlayer.this.isBigendian);
                        ADAudioTrackPlayer.this.aTrack.write(fArrBytesToFloats, 0, fArrBytesToFloats.length, 0);
                    } else if (ADAudioTrackPlayer.this.mFormat == 2) {
                        short[] sArrBytesToShorts = ByteUtil.bytesToShorts(bArr, i, ADAudioTrackPlayer.this.isBigendian);
                        if (sArrBytesToShorts.length == 0) {
                            ADAudioTrackPlayer.this.isStop = true;
                        }
                        ADAudioTrackPlayer.this.aTrack.write(sArrBytesToShorts, 0, sArrBytesToShorts.length);
                    } else {
                        ADAudioTrackPlayer.this.aTrack.write(bArr, 0, i);
                    }
                } catch (IOException unused) {
                }
            }
        }
    }
}
