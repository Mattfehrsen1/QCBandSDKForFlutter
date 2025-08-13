package com.qcwireless.qcwatch.base.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class GetFilePathFromUri {
    public static String getFileAbsolutePath(Context context, Uri imageUri) {
        Uri uri = null;
        if (context != null && imageUri != null) {
            if (Build.VERSION.SDK_INT < 19) {
                return getRealFilePath(context, imageUri);
            }
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 29 && DocumentsContract.isDocumentUri(context, imageUri)) {
                if (isExternalStorageDocument(imageUri)) {
                    String[] strArrSplit = DocumentsContract.getDocumentId(imageUri).split(":");
                    if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                        return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                    }
                } else {
                    if (isDownloadsDocument(imageUri)) {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(imageUri)).longValue()), null, null);
                    }
                    if (isMediaDocument(imageUri)) {
                        String[] strArrSplit2 = DocumentsContract.getDocumentId(imageUri).split(":");
                        String str = strArrSplit2[0];
                        if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str)) {
                            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if (PictureMimeType.MIME_TYPE_PREFIX_VIDEO.equals(str)) {
                            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if (PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str)) {
                            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }
                        return getDataColumn(context, uri, "_id=?", new String[]{strArrSplit2[1]});
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 29) {
                return uriToFileApiQ(context, imageUri);
            }
            if (Constant.MODIFY_ACTIVITY_INTENT_CONTENT.equalsIgnoreCase(imageUri.getScheme())) {
                if (isGooglePhotosUri(imageUri)) {
                    return imageUri.getLastPathSegment();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    return getFilePathFromUri(context, imageUri);
                }
                return getDataColumn(context, imageUri, null, null);
            }
            if ("file".equalsIgnoreCase(imageUri.getScheme())) {
                return imageUri.getPath();
            }
        }
        return null;
    }

    private static String getRealFilePath(final Context context, final Uri uri) {
        int columnIndex;
        String string = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!Constant.MODIFY_ACTIVITY_INTENT_CONTENT.equals(scheme)) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_data")) > -1) {
            string = cursorQuery.getString(columnIndex);
        }
        cursorQuery.close();
        return string;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) throws Throwable {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private static String getFileFromContentUri(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] strArr = {"_data", "_display_name"};
        Cursor cursorQuery = context.getContentResolver().query(uri, strArr, null, null, null);
        if (cursorQuery == null) {
            return "";
        }
        cursorQuery.moveToFirst();
        try {
            return cursorQuery.getString(cursorQuery.getColumnIndex(strArr[0]));
        } catch (Exception unused) {
            return "";
        } finally {
            cursorQuery.close();
        }
    }

    private static String uriToFileApiQ(Context context, Uri uri) throws IOException {
        InputStream inputStreamOpenInputStream;
        File file;
        FileOutputStream fileOutputStream;
        File file2 = null;
        if (uri.getScheme().equals("file")) {
            file2 = new File(uri.getPath());
        } else if (uri.getScheme().equals(Constant.MODIFY_ACTIVITY_INTENT_CONTENT)) {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
                try {
                    inputStreamOpenInputStream = contentResolver.openInputStream(uri);
                    File file3 = new File(context.getExternalCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis());
                    if (!file3.exists()) {
                        file3.mkdir();
                    }
                    file = new File(file3.getPath(), string);
                    fileOutputStream = new FileOutputStream(file);
                    android.os.FileUtils.copy(inputStreamOpenInputStream, fileOutputStream);
                } catch (IOException e) {
                    e = e;
                }
                try {
                    fileOutputStream.close();
                    inputStreamOpenInputStream.close();
                    file2 = file;
                } catch (IOException e2) {
                    file2 = file;
                    e = e2;
                    e.printStackTrace();
                    return file2.getAbsolutePath();
                }
            }
        }
        return file2.getAbsolutePath();
    }

    private static String getFilePathFromUri(Context context, Uri uri) throws IOException {
        String realFilePath = getRealFilePath(context, uri);
        if (!TextUtils.isEmpty(realFilePath)) {
            return realFilePath;
        }
        File filesDir = context.getApplicationContext().getFilesDir();
        String fileName = getFileName(uri);
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        File file = new File(filesDir + File.separator + fileName);
        copyFile(context, uri, file);
        return file.getAbsolutePath();
    }

    private static String getFileName(Uri uri) {
        String path;
        int iLastIndexOf;
        if (uri == null || (iLastIndexOf = (path = uri.getPath()).lastIndexOf(47)) == -1) {
            return null;
        }
        return path.substring(iLastIndexOf + 1);
    }

    private static void copyFile(Context context, Uri srcUri, File dstFile) throws IOException {
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStreamOpenInputStream == null) {
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            copyStream(inputStreamOpenInputStream, fileOutputStream);
            inputStreamOpenInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] bArr = new byte[2048];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input, 2048);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output, 2048);
        int i = 0;
        while (true) {
            try {
                int i2 = bufferedInputStream.read(bArr, 0, 2048);
                if (i2 == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, i2);
                i += i2;
            } catch (Exception unused) {
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedOutputStream.close();
            bufferedInputStream.close();
        } catch (Exception unused3) {
        }
        return i;
    }
}
