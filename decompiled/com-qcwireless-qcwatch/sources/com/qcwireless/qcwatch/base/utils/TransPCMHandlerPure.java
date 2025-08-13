package com.qcwireless.qcwatch.base.utils;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.Surface;
import com.luck.picture.lib.config.PictureMimeType;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class TransPCMHandlerPure {
    private OnProgressListener listener;
    private String outFile;
    private long rangeEnd;
    private long rangeStart;
    private String srcFile;

    public interface OnProgressListener {
        void onFail();

        void onProgress(int max, int progress);

        void onStart();

        void onSuccess();
    }

    public TransPCMHandlerPure(String srcFile, String outFile) {
        this(srcFile, outFile, null);
    }

    public TransPCMHandlerPure(String srcFile, String outFile, OnProgressListener listener) {
        this(srcFile, outFile, -1L, -1L, listener);
    }

    public TransPCMHandlerPure(String srcFile, String outFile, long rangeStart, long rangeEnd, OnProgressListener listener) {
        this.rangeStart = -1L;
        this.rangeEnd = -1L;
        this.srcFile = srcFile;
        this.outFile = outFile;
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.listener = listener;
    }

    public void start() {
        DecodeTask decodeTask = new DecodeTask(this.srcFile, this.outFile, this.listener);
        decodeTask.setRangeTime(this.rangeStart, this.rangeEnd);
        new Thread(decodeTask).start();
    }

    public void setRangeTime(long rangeStart, long rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public void setListener(OnProgressListener listener) {
        this.listener = listener;
    }

    private static class DecodeTask implements Runnable {
        private static final long TIME_OUT = 5000;
        private MediaCodec codec;
        private int duration = 0;
        private MediaExtractor extractor;
        private OnProgressListener listener;
        private OutputStream mOutput;
        private String outFile;
        private long rangeEnd;
        private long rangeStart;
        private String srcFile;

        public void setRangeTime(long rangeStart, long rangeEnd) {
            this.rangeStart = rangeStart;
            this.rangeEnd = rangeEnd;
        }

        public DecodeTask(String srcFile, String outFile, OnProgressListener listener) {
            this.srcFile = srcFile;
            this.outFile = outFile;
            this.listener = listener;
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, MediaCodec.CryptoException, SecurityException, IOException, IllegalArgumentException {
            OnProgressListener onProgressListener;
            OnProgressListener onProgressListener2 = this.listener;
            if (onProgressListener2 != null) {
                onProgressListener2.onStart();
            }
            boolean z = false;
            try {
                prepare();
                z = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (z) {
                output();
            }
            release();
            if (z || (onProgressListener = this.listener) == null) {
                return;
            }
            onProgressListener.onFail();
        }

        private void release() {
            MediaExtractor mediaExtractor = this.extractor;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.extractor = null;
            }
            MediaCodec mediaCodec = this.codec;
            if (mediaCodec != null) {
                mediaCodec.stop();
                this.codec.release();
                this.codec = null;
            }
        }

        private void prepare() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            MediaExtractor mediaExtractor = new MediaExtractor();
            this.extractor = mediaExtractor;
            mediaExtractor.setDataSource(this.srcFile);
            int trackCount = this.extractor.getTrackCount();
            int i = 0;
            while (true) {
                if (i >= trackCount) {
                    break;
                }
                MediaFormat trackFormat = this.extractor.getTrackFormat(i);
                String string = trackFormat.getString("mime");
                if (!TextUtils.isEmpty(string) && string.startsWith(PictureMimeType.MIME_TYPE_PREFIX_AUDIO)) {
                    this.extractor.selectTrack(i);
                    try {
                        this.duration = trackFormat.getInteger("durationUs") / 1000;
                    } catch (Exception e) {
                        e.printStackTrace();
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(this.srcFile);
                        mediaPlayer.prepare();
                        this.duration = mediaPlayer.getDuration();
                        mediaPlayer.release();
                    }
                    MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(string);
                    this.codec = mediaCodecCreateDecoderByType;
                    mediaCodecCreateDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
                    this.codec.start();
                    break;
                }
                i++;
            }
            TransPCMHandlerPure.createFile(this.outFile + ".pcm", true);
            this.mOutput = new DataOutputStream(new FileOutputStream(this.outFile + ".pcm"));
        }

        private void output() throws MediaCodec.CryptoException, IOException {
            boolean z;
            long j;
            int iDequeueInputBuffer;
            ByteBuffer[] inputBuffers = this.codec.getInputBuffers();
            ByteBuffer[] outputBuffers = this.codec.getOutputBuffers();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            long j2 = this.rangeStart;
            long j3 = 1000;
            if (j2 > 0) {
                this.extractor.seekTo(j2 * 1000, 2);
            }
            ByteBuffer[] outputBuffers2 = outputBuffers;
            boolean z2 = false;
            while (true) {
                if (z2 || (iDequeueInputBuffer = this.codec.dequeueInputBuffer(5000L)) < 0) {
                    z = z2;
                    j = 0;
                } else {
                    int sampleData = this.extractor.readSampleData(inputBuffers[iDequeueInputBuffer], 0);
                    long sampleTime = this.extractor.getSampleTime();
                    long j4 = sampleTime / j3;
                    long j5 = this.rangeEnd;
                    int i = (j5 <= 0 || j4 <= j5) ? sampleData : -1;
                    if (i <= 0) {
                        this.codec.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                        j = j4;
                        z = true;
                    } else {
                        this.codec.queueInputBuffer(iDequeueInputBuffer, 0, i, sampleTime, 0);
                        this.extractor.advance();
                        z = z2;
                        j = j4;
                    }
                }
                int iDequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, 5000L);
                if (iDequeueOutputBuffer == -3) {
                    outputBuffers2 = this.codec.getOutputBuffers();
                } else if (iDequeueOutputBuffer != -2 && iDequeueOutputBuffer != -1) {
                    ByteBuffer byteBuffer = outputBuffers2[iDequeueOutputBuffer];
                    byte[] bArr = new byte[bufferInfo.size];
                    byteBuffer.get(bArr, 0, bufferInfo.size);
                    this.codec.releaseOutputBuffer(iDequeueOutputBuffer, true);
                    try {
                        this.mOutput.write(bArr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    OnProgressListener onProgressListener = this.listener;
                    if (onProgressListener != null) {
                        long j6 = this.rangeEnd;
                        int i2 = j6 > 0 ? (int) j6 : this.duration;
                        long j7 = this.rangeStart;
                        if (j7 > 0) {
                            j -= j7;
                        }
                        onProgressListener.onProgress(i2, (int) j);
                    }
                }
                z2 = z;
                j3 = 1000;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean createFile(String filePath, boolean recreate) throws IOException {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (!recreate) {
                    return true;
                }
                file.delete();
                file.createNewFile();
                return true;
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
