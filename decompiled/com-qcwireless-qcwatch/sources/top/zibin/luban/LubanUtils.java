package top.zibin.luban;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.Locale;

/* loaded from: classes5.dex */
public class LubanUtils {
    private static final String TAG = "Luban";

    public static String getPath(Context context, Uri uri) {
        Context applicationContext = context.getApplicationContext();
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(applicationContext, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                if (!"primary".equalsIgnoreCase(strArrSplit[0])) {
                    return "";
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    return applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + strArrSplit[1];
                }
                return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
            }
            if (isDownloadsDocument(uri)) {
                return getDataColumn(applicationContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(DocumentsContract.getDocumentId(uri))), null, null);
            }
            if (!isMediaDocument(uri)) {
                return "";
            }
            String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = strArrSplit2[0];
            if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if (PictureMimeType.MIME_TYPE_PREFIX_VIDEO.equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if (PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(applicationContext, uri2, "_id=?", new String[]{strArrSplit2[1]});
        }
        if (!Constant.MODIFY_ACTIVITY_INTENT_CONTENT.equalsIgnoreCase(uri.getScheme())) {
            return "file".equalsIgnoreCase(uri.getScheme()) ? uri.getPath() : "";
        }
        if (isGooglePhotosUri(uri)) {
            return uri.getLastPathSegment();
        }
        return getDataColumn(applicationContext, uri, null, null);
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            } catch (IllegalArgumentException e) {
                Log.i(TAG, String.format(Locale.getDefault(), "getDataColumn: _data - [%s]", e.getMessage()));
                if (cursorQuery == null) {
                    return "";
                }
            }
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                if (cursorQuery == null) {
                    return "";
                }
                cursorQuery.close();
                return "";
            }
            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return string;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
