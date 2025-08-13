package com.oudmon.ble.base.communication.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import com.oudmon.ble.base.util.DataTransferUtils;
import java.nio.ByteBuffer;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/utils/ImageUtils.class */
public class ImageUtils {
    private static final String TAG = "Jxr35";

    public static byte[] getRgb565ByteArray(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.i(TAG, "图片大小 -> width: " + w + ", height: " + h);
        if (w != width || h != height) {
            bitmap = scaleBitmap(bitmap, width, height);
        }
        ByteBuffer buf = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(buf);
        int index = 4;
        byte[] bytes = buf.array();
        byte[] arrays = new byte[(bytes.length / 2) + 4];
        System.arraycopy(DataTransferUtils.shortToBytes((short) width), 0, arrays, 0, 2);
        System.arraycopy(DataTransferUtils.shortToBytes((short) height), 0, arrays, 2, 2);
        int length = bytes.length;
        for (int i = 0; i < length; i += 4) {
            int r = bytes[i] & 255;
            int g = bytes[i + 1] & 255;
            int b = bytes[i + 2] & 255;
            int rr = r >>> 3;
            int gg = g >>> 2;
            int bb = b >>> 3;
            int rgb = (rr << 11) | (gg << 5) | bb;
            int high = rgb & 255;
            int low = (rgb >> 8) & 255;
            int i2 = index;
            int index2 = index + 1;
            arrays[i2] = (byte) high;
            index = index2 + 1;
            arrays[index2] = (byte) low;
        }
        return arrays;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap != null) {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            float sx = width / w;
            float sy = height / h;
            Matrix matrix = new Matrix();
            matrix.postScale(sx, sy);
            Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
            Log.i(TAG, "放大后图片大小-> width: " + bmp.getWidth() + ", height: " + bmp.getHeight());
            return bmp;
        }
        return null;
    }

    public static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    public static Bitmap getTransparentBitmap(Bitmap sourceImg, int number) {
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        int number2 = (number * 255) / 100;
        for (int i = 0; i < argb.length; i++) {
            argb[i] = (number2 << 24) | (argb[i] & 16777215);
        }
        return Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
    }
}
