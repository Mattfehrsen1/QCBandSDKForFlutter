package androidx.camera.core.internal.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class ImageUtil {
    private static final String TAG = "ImageUtil";

    private ImageUtil() {
    }

    public static float[] sizeToVertexes(Size size) {
        return new float[]{0.0f, 0.0f, size.getWidth(), 0.0f, size.getWidth(), size.getHeight(), 0.0f, size.getHeight()};
    }

    public static float min(float value1, float value2, float value3, float value4) {
        return Math.min(Math.min(value1, value2), Math.min(value3, value4));
    }

    public static Rational getRotatedAspectRatio(int rotationDegrees, Rational aspectRatio) {
        if (rotationDegrees == 90 || rotationDegrees == 270) {
            return inverseRational(aspectRatio);
        }
        return new Rational(aspectRatio.getNumerator(), aspectRatio.getDenominator());
    }

    public static byte[] imageToJpegByteArray(ImageProxy image) throws CodecFailedException {
        if (image.getFormat() == 256) {
            return jpegImageToJpegByteArray(image);
        }
        if (image.getFormat() == 35) {
            return yuvImageToJpegByteArray(image);
        }
        Logger.w(TAG, "Unrecognized image format: " + image.getFormat());
        return null;
    }

    public static byte[] yuv_420_888toNv21(ImageProxy image) {
        ImageProxy.PlaneProxy planeProxy = image.getPlanes()[0];
        ImageProxy.PlaneProxy planeProxy2 = image.getPlanes()[1];
        ImageProxy.PlaneProxy planeProxy3 = image.getPlanes()[2];
        ByteBuffer buffer = planeProxy.getBuffer();
        ByteBuffer buffer2 = planeProxy2.getBuffer();
        ByteBuffer buffer3 = planeProxy3.getBuffer();
        buffer.rewind();
        buffer2.rewind();
        buffer3.rewind();
        int iRemaining = buffer.remaining();
        byte[] bArr = new byte[((image.getWidth() * image.getHeight()) / 2) + iRemaining];
        int width = 0;
        for (int i = 0; i < image.getHeight(); i++) {
            buffer.get(bArr, width, image.getWidth());
            width += image.getWidth();
            buffer.position(Math.min(iRemaining, (buffer.position() - image.getWidth()) + planeProxy.getRowStride()));
        }
        int height = image.getHeight() / 2;
        int width2 = image.getWidth() / 2;
        int rowStride = planeProxy3.getRowStride();
        int rowStride2 = planeProxy2.getRowStride();
        int pixelStride = planeProxy3.getPixelStride();
        int pixelStride2 = planeProxy2.getPixelStride();
        byte[] bArr2 = new byte[rowStride];
        byte[] bArr3 = new byte[rowStride2];
        for (int i2 = 0; i2 < height; i2++) {
            buffer3.get(bArr2, 0, Math.min(rowStride, buffer3.remaining()));
            buffer2.get(bArr3, 0, Math.min(rowStride2, buffer2.remaining()));
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < width2; i5++) {
                int i6 = width + 1;
                bArr[width] = bArr2[i3];
                width = i6 + 1;
                bArr[i6] = bArr3[i4];
                i3 += pixelStride;
                i4 += pixelStride2;
            }
        }
        return bArr;
    }

    public static byte[] cropByteArray(byte[] data, Rect cropRect) throws IOException, CodecFailedException {
        if (cropRect == null) {
            return data;
        }
        try {
            BitmapRegionDecoder bitmapRegionDecoderNewInstance = BitmapRegionDecoder.newInstance(data, 0, data.length, false);
            Bitmap bitmapDecodeRegion = bitmapRegionDecoderNewInstance.decodeRegion(cropRect, new BitmapFactory.Options());
            bitmapRegionDecoderNewInstance.recycle();
            if (bitmapDecodeRegion == null) {
                throw new CodecFailedException("Decode byte array failed.", CodecFailedException.FailureType.DECODE_FAILED);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (!bitmapDecodeRegion.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)) {
                throw new CodecFailedException("Encode bitmap failed.", CodecFailedException.FailureType.ENCODE_FAILED);
            }
            bitmapDecodeRegion.recycle();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new CodecFailedException("Decode byte array failed.", CodecFailedException.FailureType.DECODE_FAILED);
        } catch (IllegalArgumentException e) {
            throw new CodecFailedException("Decode byte array failed with illegal argument." + e, CodecFailedException.FailureType.DECODE_FAILED);
        }
    }

    public static boolean isAspectRatioValid(Rational aspectRatio) {
        return (aspectRatio == null || aspectRatio.floatValue() <= 0.0f || aspectRatio.isNaN()) ? false : true;
    }

    public static boolean isAspectRatioValid(Size sourceSize, Rational aspectRatio) {
        return aspectRatio != null && aspectRatio.floatValue() > 0.0f && isCropAspectRatioHasEffect(sourceSize, aspectRatio) && !aspectRatio.isNaN();
    }

    public static Rect computeCropRectFromAspectRatio(Size sourceSize, Rational aspectRatio) {
        int i;
        if (!isAspectRatioValid(aspectRatio)) {
            Logger.w(TAG, "Invalid view ratio.");
            return null;
        }
        int width = sourceSize.getWidth();
        int height = sourceSize.getHeight();
        float f = width;
        float f2 = height;
        float f3 = f / f2;
        int numerator = aspectRatio.getNumerator();
        int denominator = aspectRatio.getDenominator();
        int i2 = 0;
        if (aspectRatio.floatValue() > f3) {
            int iRound = Math.round((f / numerator) * denominator);
            i = (height - iRound) / 2;
            height = iRound;
        } else {
            int iRound2 = Math.round((f2 / denominator) * numerator);
            i2 = (width - iRound2) / 2;
            width = iRound2;
            i = 0;
        }
        return new Rect(i2, i, width + i2, height + i);
    }

    private static byte[] nv21ToJpeg(byte[] nv21, int width, int height, Rect cropRect) throws CodecFailedException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        YuvImage yuvImage = new YuvImage(nv21, 17, width, height, null);
        if (cropRect == null) {
            cropRect = new Rect(0, 0, width, height);
        }
        if (!yuvImage.compressToJpeg(cropRect, 100, byteArrayOutputStream)) {
            throw new CodecFailedException("YuvImage failed to encode jpeg.", CodecFailedException.FailureType.ENCODE_FAILED);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static boolean isCropAspectRatioHasEffect(Size sourceSize, Rational aspectRatio) {
        int width = sourceSize.getWidth();
        int height = sourceSize.getHeight();
        float numerator = aspectRatio.getNumerator();
        float denominator = aspectRatio.getDenominator();
        return (height == Math.round((((float) width) / numerator) * denominator) && width == Math.round((((float) height) / denominator) * numerator)) ? false : true;
    }

    private static Rational inverseRational(Rational rational) {
        return rational == null ? rational : new Rational(rational.getDenominator(), rational.getNumerator());
    }

    private static boolean shouldCropImage(ImageProxy image) {
        return !new Size(image.getCropRect().width(), image.getCropRect().height()).equals(new Size(image.getWidth(), image.getHeight()));
    }

    private static byte[] jpegImageToJpegByteArray(ImageProxy image) throws CodecFailedException {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bArr = new byte[buffer.capacity()];
        buffer.rewind();
        buffer.get(bArr);
        return shouldCropImage(image) ? cropByteArray(bArr, image.getCropRect()) : bArr;
    }

    private static byte[] yuvImageToJpegByteArray(ImageProxy image) throws CodecFailedException {
        return nv21ToJpeg(yuv_420_888toNv21(image), image.getWidth(), image.getHeight(), shouldCropImage(image) ? image.getCropRect() : null);
    }

    public static final class CodecFailedException extends Exception {
        private FailureType mFailureType;

        public enum FailureType {
            ENCODE_FAILED,
            DECODE_FAILED,
            UNKNOWN
        }

        CodecFailedException(String message) {
            super(message);
            this.mFailureType = FailureType.UNKNOWN;
        }

        CodecFailedException(String message, FailureType failureType) {
            super(message);
            this.mFailureType = failureType;
        }

        public FailureType getFailureType() {
            return this.mFailureType;
        }
    }
}
