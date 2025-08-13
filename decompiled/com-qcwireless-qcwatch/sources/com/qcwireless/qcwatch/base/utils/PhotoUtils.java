package com.qcwireless.qcwatch.base.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import com.qcwireless.qcwatch.QCApplication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PhotoUtils.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ&\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/PhotoUtils;", "", "()V", "saveBitmap2Gallery", "", "context", "Landroid/content/Context;", "bitmap", "Landroid/graphics/Bitmap;", "file", "Ljava/io/File;", "saveBitmap2Gallery2", "saveBitmapGallery", "", "saveBitmapGalleryBitmap", "name", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PhotoUtils {
    public static final PhotoUtils INSTANCE = new PhotoUtils();

    private PhotoUtils() {
    }

    public final boolean saveBitmap2Gallery(Context context, Bitmap bitmap, File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(file, "file");
        if (Build.VERSION.SDK_INT >= 29) {
            Uri uriInsert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
            if (uriInsert == null) {
                return false;
            }
            OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uriInsert);
            try {
                OutputStream outputStream = outputStreamOpenOutputStream;
                if (outputStream == null) {
                    CloseableKt.closeFinally(outputStreamOpenOutputStream, null);
                    return false;
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                CloseableKt.closeFinally(outputStreamOpenOutputStream, null);
                QCApplication.INSTANCE.getCONTEXT().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file.getAbsolutePath())));
                return true;
            } finally {
            }
        } else {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "title", "desc");
            QCApplication.INSTANCE.getCONTEXT().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file.getAbsolutePath())));
            return true;
        }
    }

    public final boolean saveBitmap2Gallery2(Context context, Bitmap bitmap) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        String strValueOf = String.valueOf(System.currentTimeMillis());
        String str = Environment.DIRECTORY_DCIM + "/Camera";
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", strValueOf);
        contentValues.put("mime_type", "image/jpeg");
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", str);
            contentValues.put("is_pending", (Boolean) true);
        }
        Uri uriInsert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uriInsert == null) {
            return false;
        }
        OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uriInsert);
        try {
            OutputStream outputStream = outputStreamOpenOutputStream;
            if (outputStream == null) {
                CloseableKt.closeFinally(outputStreamOpenOutputStream, null);
                return false;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            CloseableKt.closeFinally(outputStreamOpenOutputStream, null);
            if (Build.VERSION.SDK_INT >= 29) {
                contentValues.put("is_pending", (Boolean) false);
            }
            return true;
        } finally {
        }
    }

    public final void saveBitmapGallery(Context context, File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), (String) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        QCApplication.INSTANCE.getCONTEXT().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file.getAbsolutePath())));
    }

    public final void saveBitmapGalleryBitmap(Context context, Bitmap bitmap, String name, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, name, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        QCApplication.INSTANCE.getCONTEXT().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file.getAbsolutePath())));
    }
}
