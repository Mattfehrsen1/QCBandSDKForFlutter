package com.qcwireless.qcwatch.ui.plate.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class ImageUtils {
    private static final String TAG = "Jxr35";

    private static int clamp(int rgb) {
        if (rgb > 255) {
            return 255;
        }
        if (rgb < 0) {
            return 0;
        }
        return rgb;
    }

    public static void saveImage(String path, Bitmap bitmap) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(path);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public static byte[] getRgb565ByteArray(Bitmap bitmap, int width, int height) {
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        Log.i(TAG, "图片大小 -> width: " + width2 + ", height: " + height2);
        if (width2 != width || height2 != height) {
            bitmap = scaleBitmap(bitmap, width, height);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        byte[] bArrArray = byteBufferAllocate.array();
        int i = 4;
        byte[] bArr = new byte[(bArrArray.length / 2) + 4];
        System.arraycopy(DataTransferUtils.shortToBytes((short) width), 0, bArr, 0, 2);
        System.arraycopy(DataTransferUtils.shortToBytes((short) height), 0, bArr, 2, 2);
        int length = bArrArray.length;
        for (int i2 = 0; i2 < length; i2 += 4) {
            int i3 = (((bArrArray[i2] & 255) >>> 3) << 11) | (((bArrArray[i2 + 1] & 255) >>> 2) << 5) | ((bArrArray[i2 + 2] & 255) >>> 3);
            int i4 = i + 1;
            bArr[i] = (byte) (i3 & 255);
            i = i4 + 1;
            bArr[i4] = (byte) ((i3 >> 8) & 255);
        }
        return bArr;
    }

    public static byte[] getRgb565ByteArrayAlbum(Bitmap bitmap, int width, int height) {
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        Log.i(TAG, "图片大小 -> width: " + width2 + ", height: " + height2);
        if (width2 != width || height2 != height) {
            float f = (width * 1.0f) / width2;
            float f2 = (height * 1.0f) / height2;
            if (f < f2) {
                bitmap = scaleBitmap(bitmap, f);
            } else {
                bitmap = scaleBitmap(bitmap, f2);
            }
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        byte[] bArrArray = byteBufferAllocate.array();
        int i = 4;
        byte[] bArr = new byte[(bArrArray.length / 2) + 4];
        System.arraycopy(DataTransferUtils.shortToBytes((short) width), 0, bArr, 0, 2);
        System.arraycopy(DataTransferUtils.shortToBytes((short) height), 0, bArr, 2, 2);
        int length = bArrArray.length;
        for (int i2 = 0; i2 < length; i2 += 4) {
            int i3 = (((bArrArray[i2] & 255) >>> 3) << 11) | (((bArrArray[i2 + 1] & 255) >>> 2) << 5) | ((bArrArray[i2 + 2] & 255) >>> 3);
            int i4 = i + 1;
            bArr[i] = (byte) ((i3 >> 8) & 255);
            i = i4 + 1;
            bArr[i4] = (byte) (i3 & 255);
        }
        return bArr;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(width / width2, height / height2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width2, height2, matrix, true);
        Log.i(TAG, "放大后图片大小-> width: " + bitmapCreateBitmap.getWidth() + ", height: " + bitmapCreateBitmap.getHeight());
        return bitmapCreateBitmap;
    }

    public static Bitmap scaleAndCropImage(String imagePath, int targetWidth, int targetHeight) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imagePath);
        float width = bitmapDecodeFile.getWidth();
        float height = bitmapDecodeFile.getHeight();
        float fMax = Math.max(targetWidth / width, targetHeight / height);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeFile, (int) (width * fMax), (int) (height * fMax), true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int width2 = (bitmapCreateScaledBitmap.getWidth() - targetWidth) / 2;
        int height2 = (bitmapCreateScaledBitmap.getHeight() - targetHeight) / 2;
        canvas.drawBitmap(bitmapCreateScaledBitmap, new Rect(width2, height2, width2 + targetWidth, height2 + targetHeight), new Rect(0, 0, targetWidth, targetHeight), (Paint) null);
        return bitmapCreateBitmap;
    }

    public static int calculateInSampleSize(int bitmapWidth, int bitmapHeight, int reqWidth, int reqHeight) {
        if (bitmapWidth <= reqHeight && bitmapHeight <= reqWidth) {
            return 1;
        }
        int iRound = Math.round(bitmapWidth / reqHeight);
        int iRound2 = Math.round(bitmapHeight / reqWidth);
        return iRound < iRound2 ? iRound : iRound2;
    }

    public static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        Log.i(TAG, "处理后图片大小-> width: " + bitmapCreateBitmap.getWidth() + ", height: " + bitmapCreateBitmap.getHeight());
        if (bitmapCreateBitmap.equals(origin)) {
            return bitmapCreateBitmap;
        }
        origin.recycle();
        return bitmapCreateBitmap;
    }

    public static Bitmap getTransparentBitmap(Bitmap sourceImg, int number) {
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        if (number > 90) {
            number = 90;
        }
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i = 0; i < width; i++) {
            iArr[i] = (((iArr[i] & 255) * number) / 100) | (iArr[i] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i] >> 8) & 255) * number) / 100) << 8) | (((((iArr[i] >> 16) & 255) * number) / 100) << 16);
        }
        return Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
    }
}
