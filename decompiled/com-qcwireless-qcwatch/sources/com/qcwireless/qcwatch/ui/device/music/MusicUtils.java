package com.qcwireless.qcwatch.ui.device.music;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import androidx.work.WorkRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class MusicUtils {
    private static final int BIT_RATE = 128000;
    private static final String MIME_TYPE = "audio/mpeg";
    private static final int SAMPLE_RATE = 44100;
    private static final String TAG = "aaaa";
    private static final int TIMEOUT_US = 10000;

    public interface ProgressCallback {
        void onProgress(float progress);
    }

    public static void convertMp3ToPcm(String inputPath, String outputPath, ProgressCallback progressCallback) throws MediaCodec.CryptoException, IOException {
        ByteBuffer[] byteBufferArr;
        long j;
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaExtractor.setDataSource(inputPath);
            int audioTrackIndex = getAudioTrackIndex(mediaExtractor);
            if (audioTrackIndex == -1) {
                return;
            }
            mediaExtractor.selectTrack(audioTrackIndex);
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(audioTrackIndex);
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType("audio/mpeg");
            int i = 0;
            mediaCodecCreateDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
            mediaCodecCreateDecoderByType.start();
            ByteBuffer[] inputBuffers = mediaCodecCreateDecoderByType.getInputBuffers();
            ByteBuffer[] outputBuffers = mediaCodecCreateDecoderByType.getOutputBuffers();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath));
            long sampleTime = 0;
            while (true) {
                int iDequeueInputBuffer = mediaCodecCreateDecoderByType.dequeueInputBuffer(WorkRequest.MIN_BACKOFF_MILLIS);
                if (iDequeueInputBuffer >= 0) {
                    int sampleData = mediaExtractor.readSampleData(inputBuffers[iDequeueInputBuffer], i);
                    if (sampleData < 0) {
                        mediaCodecCreateDecoderByType.queueInputBuffer(iDequeueInputBuffer, 0, 0, sampleTime, 4);
                        break;
                    }
                    byteBufferArr = inputBuffers;
                    j = 10000;
                    mediaCodecCreateDecoderByType.queueInputBuffer(iDequeueInputBuffer, 0, sampleData, mediaExtractor.getSampleTime(), 0);
                    mediaExtractor.advance();
                    sampleTime = mediaExtractor.getSampleTime();
                } else {
                    byteBufferArr = inputBuffers;
                    j = 10000;
                }
                int iDequeueOutputBuffer = mediaCodecCreateDecoderByType.dequeueOutputBuffer(bufferInfo, j);
                if (iDequeueOutputBuffer >= 0) {
                    ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                    byte[] bArr = new byte[bufferInfo.size];
                    byteBuffer.get(bArr);
                    fileOutputStream.write(bArr);
                    mediaCodecCreateDecoderByType.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    if (progressCallback != null) {
                        progressCallback.onProgress(bufferInfo.presentationTimeUs / trackFormat.getLong("durationUs"));
                    }
                    if ((bufferInfo.flags & 4) != 0) {
                        break;
                    }
                }
                inputBuffers = byteBufferArr;
                i = 0;
            }
            mediaExtractor.release();
            mediaCodecCreateDecoderByType.stop();
            mediaCodecCreateDecoderByType.release();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getAudioTrackIndex(MediaExtractor mediaExtractor) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            if (mediaExtractor.getTrackFormat(i).getString("mime").startsWith("audio/")) {
                return i;
            }
        }
        return -1;
    }
}
