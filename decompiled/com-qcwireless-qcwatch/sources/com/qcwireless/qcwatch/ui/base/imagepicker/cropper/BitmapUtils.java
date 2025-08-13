package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* loaded from: classes3.dex */
final class BitmapUtils {
    private static int mMaxTextureSize;
    static Pair<String, WeakReference<Bitmap>> mStateBitmap;
    static final Rect EMPTY_RECT = new Rect();
    static final RectF EMPTY_RECT_F = new RectF();
    static final RectF RECT = new RectF();
    static final float[] POINTS = new float[6];
    static final float[] POINTS2 = new float[6];

    BitmapUtils() {
    }

    static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, Context context, Uri uri) {
        try {
            File fileFromUri = getFileFromUri(context, uri);
            if (fileFromUri.exists()) {
                return rotateBitmapByExif(bitmap, new ExifInterface(fileFromUri.getAbsolutePath()));
            }
        } catch (Exception unused) {
        }
        return new RotateBitmapResult(bitmap, 0);
    }

    static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, ExifInterface exif) {
        int attributeInt = exif.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
        return new RotateBitmapResult(bitmap, attributeInt != 3 ? attributeInt != 6 ? attributeInt != 8 ? 0 : 270 : 90 : 180);
    }

    static BitmapSampled decodeSampledBitmap(Context context, Uri uri, int reqWidth, int reqHeight) throws Throwable {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            BitmapFactory.Options optionsDecodeImageForOption = decodeImageForOption(contentResolver, uri);
            optionsDecodeImageForOption.inSampleSize = Math.max(calculateInSampleSizeByReqestedSize(optionsDecodeImageForOption.outWidth, optionsDecodeImageForOption.outHeight, reqWidth, reqHeight), calculateInSampleSizeByMaxTextureSize(optionsDecodeImageForOption.outWidth, optionsDecodeImageForOption.outHeight));
            return new BitmapSampled(decodeImage(contentResolver, uri, optionsDecodeImageForOption), optionsDecodeImageForOption.inSampleSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e.getMessage(), e);
        }
    }

    static BitmapSampled cropBitmapObjectHandleOOM(Bitmap bitmap, float[] points, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        int i = 1;
        do {
            try {
                return new BitmapSampled(cropBitmapObjectWithScale(bitmap, points, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY, 1.0f / i), i);
            } catch (OutOfMemoryError e) {
                i *= 2;
            }
        } while (i <= 8);
        throw e;
    }

    private static Bitmap cropBitmapObjectWithScale(Bitmap bitmap, float[] points, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, float scale) {
        Rect rectFromPoints = getRectFromPoints(points, bitmap.getWidth(), bitmap.getHeight(), fixAspectRatio, aspectRatioX, aspectRatioY);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postRotate(degreesRotated, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, rectFromPoints.left, rectFromPoints.top, rectFromPoints.width(), rectFromPoints.height(), matrix, true);
        if (bitmapCreateBitmap == bitmap) {
            bitmapCreateBitmap = bitmap.copy(bitmap.getConfig(), false);
        }
        return degreesRotated % 90 != 0 ? cropForRotatedImage(bitmapCreateBitmap, points, rectFromPoints, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY) : bitmapCreateBitmap;
    }

    static BitmapSampled cropBitmap(Context context, Uri loadedImageUri, float[] points, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight) {
        int i = 1;
        do {
            try {
                return cropBitmap(context, loadedImageUri, points, degreesRotated, orgWidth, orgHeight, fixAspectRatio, aspectRatioX, aspectRatioY, reqWidth, reqHeight, i);
            } catch (OutOfMemoryError e) {
                i *= 2;
            }
        } while (i <= 16);
        throw new RuntimeException("Failed to handle OOM by sampling (" + i + "): " + loadedImageUri + "\r\n" + e.getMessage(), e);
    }

    static float getRectLeft(float[] points) {
        return Math.min(Math.min(Math.min(points[0], points[2]), points[4]), points[6]);
    }

    static float getRectTop(float[] points) {
        return Math.min(Math.min(Math.min(points[1], points[3]), points[5]), points[7]);
    }

    static float getRectRight(float[] points) {
        return Math.max(Math.max(Math.max(points[0], points[2]), points[4]), points[6]);
    }

    static float getRectBottom(float[] points) {
        return Math.max(Math.max(Math.max(points[1], points[3]), points[5]), points[7]);
    }

    static float getRectWidth(float[] points) {
        return getRectRight(points) - getRectLeft(points);
    }

    static float getRectHeight(float[] points) {
        return getRectBottom(points) - getRectTop(points);
    }

    static float getRectCenterX(float[] points) {
        return (getRectRight(points) + getRectLeft(points)) / 2.0f;
    }

    static float getRectCenterY(float[] points) {
        return (getRectBottom(points) + getRectTop(points)) / 2.0f;
    }

    static Rect getRectFromPoints(float[] points, int imageWidth, int imageHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        Rect rect = new Rect(Math.round(Math.max(0.0f, getRectLeft(points))), Math.round(Math.max(0.0f, getRectTop(points))), Math.round(Math.min(imageWidth, getRectRight(points))), Math.round(Math.min(imageHeight, getRectBottom(points))));
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY);
        }
        return rect;
    }

    private static void fixRectForAspectRatio(Rect rect, int aspectRatioX, int aspectRatioY) {
        if (aspectRatioX != aspectRatioY || rect.width() == rect.height()) {
            return;
        }
        if (rect.height() > rect.width()) {
            rect.bottom -= rect.height() - rect.width();
        } else {
            rect.right -= rect.width() - rect.height();
        }
    }

    static void writeBitmapToUri(Context context, Bitmap bitmap, Uri uri, Bitmap.CompressFormat compressFormat, int compressQuality) throws IOException {
        OutputStream outputStreamOpenOutputStream = null;
        try {
            outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(compressFormat, compressQuality, outputStreamOpenOutputStream);
        } finally {
            closeSafe(outputStreamOpenOutputStream);
        }
    }

    static Bitmap resizeBitmap(Bitmap bitmap, int reqWidth, int reqHeight, CropImageView.RequestSizeOptions options) {
        if (reqWidth > 0 && reqHeight > 0) {
            try {
                if (options == CropImageView.RequestSizeOptions.RESIZE_FIT || options == CropImageView.RequestSizeOptions.RESIZE_INSIDE || options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    Bitmap bitmapCreateScaledBitmap = null;
                    if (options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                        bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, false);
                    } else {
                        float width = bitmap.getWidth();
                        float height = bitmap.getHeight();
                        float fMax = Math.max(width / reqWidth, height / reqHeight);
                        if (fMax > 1.0f || options == CropImageView.RequestSizeOptions.RESIZE_FIT) {
                            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (width / fMax), (int) (height / fMax), false);
                        }
                    }
                    if (bitmapCreateScaledBitmap != null) {
                        if (bitmapCreateScaledBitmap != bitmap) {
                            bitmap.recycle();
                        }
                        return bitmapCreateScaledBitmap;
                    }
                }
            } catch (Exception e) {
                Log.w("AIC", "Failed to resize cropped image, return bitmap before resize", e);
            }
        }
        return bitmap;
    }

    private static BitmapSampled cropBitmap(Context context, Uri loadedImageUri, float[] points, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight, int sampleMulti) throws Throwable {
        Rect rectFromPoints = getRectFromPoints(points, orgWidth, orgHeight, fixAspectRatio, aspectRatioX, aspectRatioY);
        int iWidth = reqWidth > 0 ? reqWidth : rectFromPoints.width();
        int iHeight = reqHeight > 0 ? reqHeight : rectFromPoints.height();
        Bitmap bitmap = null;
        int i = 1;
        try {
            BitmapSampled bitmapSampledDecodeSampledBitmapRegion = decodeSampledBitmapRegion(context, loadedImageUri, rectFromPoints, iWidth, iHeight, sampleMulti);
            bitmap = bitmapSampledDecodeSampledBitmapRegion.bitmap;
            i = bitmapSampledDecodeSampledBitmapRegion.sampleSize;
        } catch (Exception unused) {
        }
        if (bitmap != null) {
            try {
                Bitmap bitmapRotateBitmapInt = rotateBitmapInt(bitmap, degreesRotated);
                try {
                    if (degreesRotated % 90 != 0) {
                        bitmapRotateBitmapInt = cropForRotatedImage(bitmapRotateBitmapInt, points, rectFromPoints, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY);
                    }
                    return new BitmapSampled(bitmapRotateBitmapInt, i);
                } catch (OutOfMemoryError e) {
                    e = e;
                    bitmap = bitmapRotateBitmapInt;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    throw e;
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
            }
        } else {
            return cropBitmap(context, loadedImageUri, points, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY, sampleMulti, rectFromPoints, iWidth, iHeight);
        }
    }

    private static BitmapSampled cropBitmap(Context context, Uri loadedImageUri, float[] points, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int sampleMulti, Rect rect, int width, int height) {
        Bitmap bitmapCropBitmapObjectWithScale = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int iCalculateInSampleSizeByReqestedSize = calculateInSampleSizeByReqestedSize(rect.width(), rect.height(), width, height) * sampleMulti;
            options.inSampleSize = iCalculateInSampleSizeByReqestedSize;
            Bitmap bitmapDecodeImage = decodeImage(context.getContentResolver(), loadedImageUri, options);
            if (bitmapDecodeImage != null) {
                try {
                    int length = points.length;
                    float[] fArr = new float[length];
                    System.arraycopy(points, 0, fArr, 0, points.length);
                    for (int i = 0; i < length; i++) {
                        fArr[i] = fArr[i] / options.inSampleSize;
                    }
                    bitmapCropBitmapObjectWithScale = cropBitmapObjectWithScale(bitmapDecodeImage, fArr, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY, 1.0f);
                    if (bitmapCropBitmapObjectWithScale != bitmapDecodeImage) {
                        bitmapDecodeImage.recycle();
                    }
                } catch (Throwable th) {
                    if (bitmapDecodeImage != null) {
                        bitmapDecodeImage.recycle();
                    }
                    throw th;
                }
            }
            return new BitmapSampled(bitmapCropBitmapObjectWithScale, iCalculateInSampleSizeByReqestedSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sampled bitmap: " + loadedImageUri + "\r\n" + e.getMessage(), e);
        } catch (OutOfMemoryError e2) {
            if (bitmapCropBitmapObjectWithScale != null) {
                bitmapCropBitmapObjectWithScale.recycle();
            }
            throw e2;
        }
    }

    private static BitmapFactory.Options decodeImageForOption(ContentResolver resolver, Uri uri) throws Throwable {
        InputStream inputStreamOpenInputStream;
        try {
            inputStreamOpenInputStream = resolver.openInputStream(uri);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStreamOpenInputStream, EMPTY_RECT, options);
                options.inJustDecodeBounds = false;
                closeSafe(inputStreamOpenInputStream);
                return options;
            } catch (Throwable th) {
                th = th;
                closeSafe(inputStreamOpenInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpenInputStream = null;
        }
    }

    private static Bitmap decodeImage(ContentResolver resolver, Uri uri, BitmapFactory.Options options) throws IOException {
        do {
            InputStream inputStreamOpenInputStream = null;
            try {
                try {
                    inputStreamOpenInputStream = resolver.openInputStream(uri);
                    return BitmapFactory.decodeStream(inputStreamOpenInputStream, EMPTY_RECT, options);
                } catch (OutOfMemoryError unused) {
                    options.inSampleSize *= 2;
                    closeSafe(inputStreamOpenInputStream);
                }
            } finally {
                closeSafe(inputStreamOpenInputStream);
            }
        } while (options.inSampleSize <= 512);
        throw new RuntimeException("Failed to decode image: " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils.BitmapSampled decodeSampledBitmapRegion(android.content.Context r4, android.net.Uri r5, android.graphics.Rect r6, int r7, int r8, int r9) throws java.lang.Throwable {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r1.<init>()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r2 = r6.width()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r3 = r6.height()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r7 = calculateInSampleSizeByReqestedSize(r2, r3, r7, r8)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r9 = r9 * r7
            r1.inSampleSize = r9     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            java.io.InputStream r4 = r4.openInputStream(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r7 = 0
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r4, r7)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5a
        L23:
            com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils$BitmapSampled r8 = new com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils$BitmapSampled     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            android.graphics.Bitmap r9 = r7.decodeRegion(r6, r1)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            int r2 = r1.inSampleSize     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            r8.<init>(r9, r2)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            closeSafe(r4)
            if (r7 == 0) goto L36
            r7.recycle()
        L36:
            return r8
        L37:
            r5 = move-exception
            goto L58
        L39:
            r6 = move-exception
            goto L5c
        L3b:
            int r8 = r1.inSampleSize     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            int r8 = r8 * 2
            r1.inSampleSize = r8     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            int r8 = r1.inSampleSize     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r9 = 512(0x200, float:7.175E-43)
            if (r8 <= r9) goto L23
            closeSafe(r4)
            if (r7 == 0) goto L4f
            r7.recycle()
        L4f:
            com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils$BitmapSampled r4 = new com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils$BitmapSampled
            r5 = 1
            r4.<init>(r0, r5)
            return r4
        L56:
            r5 = move-exception
            r7 = r0
        L58:
            r0 = r4
            goto L87
        L5a:
            r6 = move-exception
            r7 = r0
        L5c:
            r0 = r4
            goto L63
        L5e:
            r5 = move-exception
            r7 = r0
            goto L87
        L61:
            r6 = move-exception
            r7 = r0
        L63:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            r8.<init>()     // Catch: java.lang.Throwable -> L86
            java.lang.String r9 = "Failed to load sampled bitmap: "
            r8.append(r9)     // Catch: java.lang.Throwable -> L86
            r8.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = "\r\n"
            r8.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = r6.getMessage()     // Catch: java.lang.Throwable -> L86
            r8.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = r8.toString()     // Catch: java.lang.Throwable -> L86
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L86
            throw r4     // Catch: java.lang.Throwable -> L86
        L86:
            r5 = move-exception
        L87:
            closeSafe(r0)
            if (r7 == 0) goto L8f
            r7.recycle()
        L8f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils.decodeSampledBitmapRegion(android.content.Context, android.net.Uri, android.graphics.Rect, int, int, int):com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils$BitmapSampled");
    }

    private static Bitmap cropForRotatedImage(Bitmap bitmap, float[] points, Rect rect, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        int iAbs;
        int iAbs2;
        int iAbs3;
        if (degreesRotated % 90 == 0) {
            return bitmap;
        }
        double radians = Math.toRadians(degreesRotated);
        int i = (degreesRotated < 90 || (degreesRotated > 180 && degreesRotated < 270)) ? rect.left : rect.right;
        int iAbs4 = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= points.length) {
                iAbs = 0;
                iAbs2 = 0;
                iAbs3 = 0;
                break;
            }
            if (points[i2] >= i - 1 && points[i2] <= i + 1) {
                int i3 = i2 + 1;
                iAbs4 = (int) Math.abs(Math.sin(radians) * (rect.bottom - points[i3]));
                iAbs2 = (int) Math.abs(Math.cos(radians) * (points[i3] - rect.top));
                iAbs3 = (int) Math.abs((points[i3] - rect.top) / Math.sin(radians));
                iAbs = (int) Math.abs((rect.bottom - points[i3]) / Math.cos(radians));
                break;
            }
            i2 += 2;
        }
        rect.set(iAbs4, iAbs2, iAbs3 + iAbs4, iAbs + iAbs2);
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
        if (bitmap != bitmapCreateBitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    private static int calculateInSampleSizeByReqestedSize(int width, int height, int reqWidth, int reqHeight) {
        int i = 1;
        if (height > reqHeight || width > reqWidth) {
            while ((height / 2) / i > reqHeight && (width / 2) / i > reqWidth) {
                i *= 2;
            }
        }
        return i;
    }

    private static int calculateInSampleSizeByMaxTextureSize(int width, int height) {
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = getMaxTextureSize();
        }
        int i = 1;
        if (mMaxTextureSize > 0) {
            while (true) {
                int i2 = height / i;
                int i3 = mMaxTextureSize;
                if (i2 <= i3 && width / i <= i3) {
                    break;
                }
                i *= 2;
            }
        }
        return i;
    }

    private static File getFileFromUri(Context context, Uri uri) {
        File file = new File(uri.getPath());
        if (file.exists()) {
            return file;
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            if (cursorQuery != null) {
                int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
                cursorQuery.moveToFirst();
                file = new File(cursorQuery.getString(columnIndexOrThrow));
            }
        } catch (Exception unused) {
            if (cursorQuery != null) {
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return file;
    }

    private static Bitmap rotateBitmapInt(Bitmap bitmap, int degrees) {
        if (degrees <= 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    private static int getMaxTextureSize() {
        try {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
            int[] iArr = new int[1];
            egl10.eglGetConfigs(eGLDisplayEglGetDisplay, null, 0, iArr);
            EGLConfig[] eGLConfigArr = new EGLConfig[iArr[0]];
            egl10.eglGetConfigs(eGLDisplayEglGetDisplay, eGLConfigArr, iArr[0], iArr);
            int[] iArr2 = new int[1];
            int i = 0;
            for (int i2 = 0; i2 < iArr[0]; i2++) {
                egl10.eglGetConfigAttrib(eGLDisplayEglGetDisplay, eGLConfigArr[i2], 12332, iArr2);
                if (i < iArr2[0]) {
                    i = iArr2[0];
                }
            }
            egl10.eglTerminate(eGLDisplayEglGetDisplay);
            return Math.max(i, 2048);
        } catch (Exception unused) {
            return 2048;
        }
    }

    private static void closeSafe(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    static final class BitmapSampled {
        public final Bitmap bitmap;
        final int sampleSize;

        BitmapSampled(Bitmap bitmap, int sampleSize) {
            this.bitmap = bitmap;
            this.sampleSize = sampleSize;
        }
    }

    static final class RotateBitmapResult {
        public final Bitmap bitmap;
        final int degrees;

        RotateBitmapResult(Bitmap bitmap, int degrees) {
            this.bitmap = bitmap;
            this.degrees = degrees;
        }
    }
}
