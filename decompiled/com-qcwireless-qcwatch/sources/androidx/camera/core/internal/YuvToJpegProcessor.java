package androidx.camera.core.internal;

import android.graphics.Rect;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.utils.ExifData;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.core.util.Preconditions;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

/* loaded from: classes.dex */
public class YuvToJpegProcessor implements CaptureProcessor {
    private static final String TAG = "YuvToJpegProcessor";
    private static final Rect UNINITIALIZED_RECT = new Rect(0, 0, 0, 0);
    private ImageWriter mImageWriter;
    private final int mMaxImages;
    private final int mQuality;
    private final Object mLock = new Object();
    private boolean mClosed = false;
    private int mProcessingImages = 0;
    private Rect mImageRect = UNINITIALIZED_RECT;

    public YuvToJpegProcessor(int quality, int maxImages) {
        this.mQuality = quality;
        this.mMaxImages = maxImages;
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onOutputSurface(Surface surface, int imageFormat) {
        Preconditions.checkState(imageFormat == 256, "YuvToJpegProcessor only supports JPEG output format.");
        synchronized (this.mLock) {
            if (!this.mClosed) {
                if (this.mImageWriter != null) {
                    throw new IllegalStateException("Output surface already set.");
                }
                this.mImageWriter = ImageWriterCompat.newInstance(surface, this.mMaxImages, imageFormat);
            } else {
                Logger.w(TAG, "Cannot set output surface. Processor is closed.");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x013b A[Catch: all -> 0x015e, TRY_ENTER, TRY_LEAVE, TryCatch #18 {all -> 0x015e, blocks: (B:43:0x00e3, B:90:0x013b), top: B:151:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0162  */
    @Override // androidx.camera.core.impl.CaptureProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void process(androidx.camera.core.impl.ImageProxyBundle r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.YuvToJpegProcessor.process(androidx.camera.core.impl.ImageProxyBundle):void");
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mClosed = true;
                if (this.mProcessingImages == 0 && this.mImageWriter != null) {
                    Logger.d(TAG, "No processing in progress. Closing immediately.");
                    this.mImageWriter.close();
                } else {
                    Logger.d(TAG, "close() called while processing. Will close after completion.");
                }
            }
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onResolutionUpdate(Size size) {
        synchronized (this.mLock) {
            this.mImageRect = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
    }

    private static ExifData getExifData(ImageProxy imageProxy) {
        ExifData.Builder builderBuilderForDevice = ExifData.builderForDevice();
        imageProxy.getImageInfo().populateExifData(builderBuilderForDevice);
        return builderBuilderForDevice.setImageWidth(imageProxy.getWidth()).setImageHeight(imageProxy.getHeight()).build();
    }

    private static final class ByteBufferOutputStream extends OutputStream {
        private final ByteBuffer mByteBuffer;

        ByteBufferOutputStream(ByteBuffer buf) {
            this.mByteBuffer = buf;
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            if (!this.mByteBuffer.hasRemaining()) {
                throw new EOFException("Output ByteBuffer has no bytes remaining.");
            }
            this.mByteBuffer.put((byte) b);
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) throws IOException {
            int i;
            Objects.requireNonNull(b);
            if (off < 0 || off > b.length || len < 0 || (i = off + len) > b.length || i < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return;
            }
            if (this.mByteBuffer.remaining() < len) {
                throw new EOFException("Output ByteBuffer has insufficient bytes remaining.");
            }
            this.mByteBuffer.put(b, off, len);
        }
    }
}
