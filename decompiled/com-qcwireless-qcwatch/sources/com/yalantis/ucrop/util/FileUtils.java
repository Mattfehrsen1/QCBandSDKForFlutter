package com.yalantis.ucrop.util;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import coil.util.Utils;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

/* loaded from: classes4.dex */
public class FileUtils {
    public static final String GIF = ".gif";
    public static final String JPEG = ".jpeg";
    private static final String TAG = "FileUtils";
    public static final String WEBP = ".webp";
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private FileUtils() {
    }

    public static boolean isContent(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("content://");
    }

    public static Uri replaceOutputUri(Context context, boolean z, Uri uri, Uri uri2) {
        try {
            String postfixDefaultEmpty = getPostfixDefaultEmpty(context, z, uri);
            if (TextUtils.isEmpty(postfixDefaultEmpty)) {
                return uri2;
            }
            String string = isContent(uri2.toString()) ? uri2.toString() : uri2.getPath();
            String strReplace = string.replace(string.substring(string.lastIndexOf(".")), postfixDefaultEmpty);
            return isContent(strReplace) ? Uri.parse(strReplace) : Uri.fromFile(new File(strReplace));
        } catch (Exception e) {
            e.printStackTrace();
            return uri2;
        }
    }

    public static String getPostfixDefaultJPEG(Context context, boolean z, Uri uri) {
        if (z) {
            String mimeTypeFromMediaContentUri = getMimeTypeFromMediaContentUri(context, uri);
            if (isGif(mimeTypeFromMediaContentUri)) {
                return ".gif";
            }
            if (isWebp(mimeTypeFromMediaContentUri)) {
                return ".webp";
            }
        }
        return ".jpeg";
    }

    public static String getPostfixDefaultEmpty(Context context, boolean z, Uri uri) {
        if (z) {
            String mimeTypeFromMediaContentUri = getMimeTypeFromMediaContentUri(context, uri);
            if (isGif(mimeTypeFromMediaContentUri)) {
                return ".gif";
            }
            if (isWebp(mimeTypeFromMediaContentUri)) {
                return ".webp";
            }
        }
        return "";
    }

    public static String getInputPath(Uri uri) {
        return (isContent(uri.toString()) || isHasHttp(uri.toString())) ? uri.toString() : uri.getPath();
    }

    public static boolean isUrlHasVideo(String str) {
        return !TextUtils.isEmpty(str) && str.toLowerCase().endsWith(PictureMimeType.MP4);
    }

    public static boolean isHasVideo(String str) {
        return str != null && str.startsWith(PictureMimeType.MIME_TYPE_PREFIX_VIDEO);
    }

    public static boolean isHasAudio(String str) {
        return str != null && str.startsWith(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
    }

    public static boolean isHasHttp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("http") || str.startsWith("https") || str.startsWith("/http") || str.startsWith("/https");
    }

    public static boolean isGif(String str) {
        return str != null && (str.equals("image/gif") || str.equals("image/GIF"));
    }

    public static boolean isWebp(String str) {
        return str != null && (str.equals(Utils.MIME_TYPE_WEBP) || str.equals("image/WEBP"));
    }

    public static String getMimeTypeFromMediaContentUri(Context context, Uri uri) {
        if (uri.getScheme().equals(Constant.MODIFY_ACTIVITY_INTENT_CONTENT)) {
            return context.getContentResolver().getType(uri);
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
    }

    public static String getCreateFileName(String str) {
        return str + sf.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String getCreateFileName() {
        return sf.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002e A[PHI: r8
      0x002e: PHI (r8v3 android.database.Cursor) = (r8v2 android.database.Cursor), (r8v4 android.database.Cursor) binds: [B:20:0x0050, B:13:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L32 java.lang.IllegalArgumentException -> L34
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L32 java.lang.IllegalArgumentException -> L34
            if (r8 == 0) goto L2c
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L2a java.lang.Throwable -> L54
            if (r9 == 0) goto L2c
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.IllegalArgumentException -> L2a java.lang.Throwable -> L54
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.IllegalArgumentException -> L2a java.lang.Throwable -> L54
            if (r8 == 0) goto L29
            r8.close()
        L29:
            return r9
        L2a:
            r9 = move-exception
            goto L36
        L2c:
            if (r8 == 0) goto L53
        L2e:
            r8.close()
            goto L53
        L32:
            r9 = move-exception
            goto L56
        L34:
            r9 = move-exception
            r8 = r7
        L36:
            java.lang.String r10 = "FileUtils"
            java.util.Locale r11 = java.util.Locale.getDefault()     // Catch: java.lang.Throwable -> L54
            java.lang.String r0 = "getDataColumn: _data - [%s]"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L54
            r2 = 0
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L54
            r1[r2] = r9     // Catch: java.lang.Throwable -> L54
            java.lang.String r9 = java.lang.String.format(r11, r0, r1)     // Catch: java.lang.Throwable -> L54
            android.util.Log.i(r10, r9)     // Catch: java.lang.Throwable -> L54
            if (r8 == 0) goto L53
            goto L2e
        L53:
            return r7
        L54:
            r9 = move-exception
            r7 = r8
        L56:
            if (r7 == 0) goto L5b
            r7.close()
        L5b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.util.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId)) {
                    try {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
                    } catch (NumberFormatException e) {
                        Log.i(TAG, e.getMessage());
                        return null;
                    }
                }
            } else if (isMediaDocument(uri)) {
                String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = strArrSplit2[0];
                if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (PictureMimeType.MIME_TYPE_PREFIX_VIDEO.equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
            }
        } else {
            if (Constant.MODIFY_ACTIVITY_INTENT_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    public static void copyFile(String str, String str2) throws Throwable {
        FileChannel fileChannel;
        if (str.equalsIgnoreCase(str2)) {
            return;
        }
        FileChannel channel = null;
        try {
            FileChannel channel2 = new FileInputStream(new File(str)).getChannel();
            try {
                channel = new FileOutputStream(new File(str2)).getChannel();
                channel2.transferTo(0L, channel2.size(), channel);
                if (channel2 != null) {
                    channel2.close();
                }
                if (channel != null) {
                    channel.close();
                }
            } catch (Throwable th) {
                th = th;
                FileChannel fileChannel2 = channel;
                channel = channel2;
                fileChannel = fileChannel2;
                if (channel != null) {
                    channel.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
        }
    }

    public static void copyFile(Context context, Uri uri, Uri uri2) throws Throwable {
        OutputStream outputStream;
        if (uri.equals(uri2)) {
            return;
        }
        InputStream inputStream = null;
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            try {
                OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri2);
                if ((inputStreamOpenInputStream instanceof FileInputStream) && (outputStreamOpenOutputStream instanceof FileOutputStream)) {
                    FileChannel channel = ((FileInputStream) inputStreamOpenInputStream).getChannel();
                    channel.transferTo(0L, channel.size(), ((FileOutputStream) outputStreamOpenOutputStream).getChannel());
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                    if (outputStreamOpenOutputStream != null) {
                        outputStreamOpenOutputStream.close();
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("The input or output URI don't represent a file. uCrop requires then to represent files in order to work properly.");
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStream;
                outputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
        }
    }

    public static boolean writeFileFromIS(InputStream inputStream, OutputStream outputStream) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                bufferedOutputStream = new BufferedOutputStream(outputStream);
            } catch (Exception e) {
                e = e;
                bufferedOutputStream = null;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = null;
            }
        } catch (Exception e2) {
            e = e2;
            bufferedOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i != -1) {
                    outputStream.write(bArr, 0, i);
                } else {
                    outputStream.flush();
                    BitmapLoadUtils.close(bufferedInputStream);
                    BitmapLoadUtils.close(bufferedOutputStream);
                    return true;
                }
            }
        } catch (Exception e3) {
            e = e3;
            bufferedInputStream2 = bufferedInputStream;
            try {
                e.printStackTrace();
                BitmapLoadUtils.close(bufferedInputStream2);
                BitmapLoadUtils.close(bufferedOutputStream);
                return false;
            } catch (Throwable th3) {
                th = th3;
                BitmapLoadUtils.close(bufferedInputStream2);
                BitmapLoadUtils.close(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream2 = bufferedInputStream;
            BitmapLoadUtils.close(bufferedInputStream2);
            BitmapLoadUtils.close(bufferedOutputStream);
            throw th;
        }
    }
}
