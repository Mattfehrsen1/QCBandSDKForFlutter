package com.luck.picture.lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.luck.picture.lib.basic.PictureContentResolver;
import com.luck.picture.lib.config.PictureMimeType;
import com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class BitmapUtils {
    private static final int ARGB_8888_MEMORY_BYTE = 4;
    private static final int MAX_BITMAP_SIZE = 104857600;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void rotateImage(android.content.Context r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.utils.BitmapUtils.rotateImage(android.content.Context, java.lang.String):void");
    }

    public static Bitmap rotatingImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static void saveBitmapFile(Bitmap bitmap, FileOutputStream fileOutputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            PictureFileUtils.close(fileOutputStream);
            PictureFileUtils.close(byteArrayOutputStream);
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            e.printStackTrace();
            PictureFileUtils.close(fileOutputStream);
            PictureFileUtils.close(byteArrayOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            PictureFileUtils.close(fileOutputStream);
            PictureFileUtils.close(byteArrayOutputStream2);
            throw th;
        }
    }

    public static int readPictureDegree(Context context, String str) {
        ExifInterface exifInterface;
        int i;
        InputStream inputStreamOpenInputStream = null;
        try {
            if (PictureMimeType.isContent(str)) {
                inputStreamOpenInputStream = PictureContentResolver.openInputStream(context, Uri.parse(str));
                exifInterface = new ExifInterface(inputStreamOpenInputStream);
            } else {
                exifInterface = new ExifInterface(str);
            }
            int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 6) {
                i = 90;
            } else {
                if (attributeInt != 8) {
                    return 0;
                }
                i = 270;
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            PictureFileUtils.close(inputStreamOpenInputStream);
        }
    }

    public static int[] getMaxImageSize(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return new int[]{-1, -1};
        }
        int iComputeSize = computeSize(i, i2);
        long totalMemory = getTotalMemory();
        int i3 = -1;
        int i4 = -1;
        boolean z = false;
        while (!z) {
            i3 = i / iComputeSize;
            i4 = i2 / iComputeSize;
            if (i3 * i4 * 4 > totalMemory) {
                iComputeSize *= 2;
            } else {
                z = true;
            }
        }
        return new int[]{i3, i4};
    }

    public static long getTotalMemory() {
        long j = Runtime.getRuntime().totalMemory();
        if (j > 104857600) {
            return 104857600L;
        }
        return j;
    }

    public static int computeSize(int i, int i2) {
        if (i % 2 == 1) {
            i++;
        }
        if (i2 % 2 == 1) {
            i2++;
        }
        int iMax = Math.max(i, i2);
        float fMin = Math.min(i, i2) / iMax;
        if (fMin > 1.0f || fMin <= 0.5625d) {
            double d = fMin;
            if (d <= 0.5625d && d > 0.5d) {
                int i3 = iMax / BluetoothClassImpl.Device.PERIPHERAL_NON_KEYBOARD_NON_POINTING;
                if (i3 == 0) {
                    return 1;
                }
                return i3;
            }
            return (int) Math.ceil(iMax / (1280.0d / d));
        }
        if (iMax < 1664) {
            return 1;
        }
        if (iMax < 4990) {
            return 2;
        }
        if (iMax <= 4990 || iMax >= 10240) {
            return iMax / BluetoothClassImpl.Device.PERIPHERAL_NON_KEYBOARD_NON_POINTING;
        }
        return 4;
    }
}
