package com.qcwireless.qcwatch.ui.base.imagepicker;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;

/* loaded from: classes3.dex */
public class Utils {
    public static Uri getIntentUri(Context context, Uri uri) {
        return Build.VERSION.SDK_INT >= 24 ? getContentUri(context, uri) : uri;
    }

    public static Uri getContentUri(Context context, Uri fileUri) {
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".imagePicker.provider", new File(fileUri.getPath()));
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
            cursorQuery.moveToFirst();
            return cursorQuery.getString(columnIndexOrThrow);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }
}
