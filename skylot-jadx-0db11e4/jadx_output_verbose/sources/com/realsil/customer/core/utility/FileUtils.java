package com.realsil.customer.core.utility;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import com.realsil.customer.core.logger.ZLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/utility/FileUtils.class */
public final class FileUtils {
    /* JADX WARN: Type inference failed for: r0v4, types: [boolean, java.lang.Throwable] */
    public static boolean exists(String str) {
        ?? Exists;
        try {
            Exists = new File(str).exists();
            return Exists;
        } catch (Exception unused) {
            ZLogger.e(Exists.getMessage());
            return false;
        }
    }

    public static File getSaveFile(String str, String str2) {
        return getSaveFile(str, str2, true);
    }

    public static String getSavePath(String str) {
        return getSaveFolder(str).getAbsolutePath();
    }

    public static String getSavePathCompat(String str) {
        return getSaveFolder(str).getAbsolutePath();
    }

    public static File getSaveFolder(String str) {
        StringBuilder sbAppend = new StringBuilder().append(getSDCardAbsPath());
        String str2 = File.separator;
        File file = new File(sbAppend.append(str2).append(str).append(str2).toString());
        file.mkdirs();
        return file;
    }

    public static String getSDCardAbsPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getPath(Context context, Uri uri) {
        try {
            if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
                return b(context, uri);
            }
            if (!"content".equalsIgnoreCase(uri.getScheme())) {
                if (!"file".equalsIgnoreCase(uri.getScheme())) {
                    return "";
                }
                ZLogger.v("file:" + uri.getPath());
                return uri.getPath();
            }
            String strA = a(context, uri);
            String lastPathSegment = strA;
            if (TextUtils.isEmpty(strA)) {
                lastPathSegment = uri.getLastPathSegment();
                File file = new File(lastPathSegment);
                if (file.exists()) {
                    Locale locale = Locale.US;
                    Object[] objArr = new Object[3];
                    objArr[0] = file.getPath();
                    objArr[1] = file.getName();
                    objArr[2] = Long.valueOf(file.length());
                    ZLogger.v(String.format(locale, "> %s\n%s\n%d", objArr));
                } else {
                    String path = uri.getPath();
                    lastPathSegment = path;
                    if (TextUtils.isEmpty(path)) {
                        lastPathSegment = uri.getPath();
                        File file2 = new File(lastPathSegment);
                        if (file2.exists()) {
                            Locale locale2 = Locale.US;
                            Object[] objArr2 = new Object[3];
                            objArr2[0] = file2.getPath();
                            objArr2[1] = file2.getName();
                            objArr2[2] = Long.valueOf(file2.length());
                            ZLogger.v(String.format(locale2, ">>> %s\n%s\n%d", objArr2));
                        } else {
                            ZLogger.d(">>>file not exist");
                        }
                    } else {
                        File file3 = new File(lastPathSegment);
                        if (file3.exists()) {
                            Locale locale3 = Locale.US;
                            Object[] objArr3 = new Object[3];
                            objArr3[0] = file3.getPath();
                            objArr3[1] = file3.getName();
                            objArr3[2] = Long.valueOf(file3.length());
                            ZLogger.v(String.format(locale3, ">>>> %s\n%s\n%d", objArr3));
                        } else {
                            lastPathSegment = lastPathSegment.replace("/file_share", "");
                            File file4 = new File(lastPathSegment);
                            if (file4.exists()) {
                                Locale locale4 = Locale.US;
                                Object[] objArr4 = new Object[3];
                                objArr4[0] = file4.getPath();
                                objArr4[1] = file4.getName();
                                objArr4[2] = Long.valueOf(file4.length());
                                ZLogger.v(String.format(locale4, ">>> %s\n%s\n%d", objArr4));
                            } else {
                                lastPathSegment = lastPathSegment.replace("/file_share", "");
                            }
                        }
                    }
                }
            } else {
                File file5 = new File(lastPathSegment);
                if (file5.exists()) {
                    Locale locale5 = Locale.US;
                    Object[] objArr5 = new Object[3];
                    objArr5[0] = file5.getPath();
                    objArr5[1] = file5.getName();
                    objArr5[2] = Long.valueOf(file5.length());
                    ZLogger.v(String.format(locale5, ">> %s\n%s\n%d", objArr5));
                } else {
                    ZLogger.d(">> file not exist");
                }
            }
            return lastPathSegment;
        } catch (Exception e) {
            e.printStackTrace();
            ZLogger.w("file:".toString());
            return "";
        }
    }

    public static String a(Context context, Uri uri) {
        if (isGooglePhotosUri(uri)) {
            ZLogger.v("isGooglePhotosUri");
            return uri.getLastPathSegment();
        }
        if (isOppoUri(uri)) {
            ZLogger.v("isOppoUri");
            String path = uri.getPath();
            File file = new File(path);
            if (file.exists()) {
                ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file.getPath(), file.getName(), Long.valueOf(file.length())));
            } else if (path.contains("/file_share/")) {
                path = path.replace("/file_share", "");
            }
            return path;
        }
        if (isHuaweiUri(uri) || isHuaweiShareUri(uri)) {
            ZLogger.v("isHuaweiUri");
            String path2 = uri.getPath();
            File file2 = new File(path2);
            if (file2.exists()) {
                ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file2.getPath(), file2.getName(), Long.valueOf(file2.length())));
            } else if (path2.contains("/root/")) {
                path2 = path2.replace("/root", "");
            }
            return path2;
        }
        if (isNokia(uri)) {
            ZLogger.v("isNokia");
            String path3 = uri.getPath();
            File file3 = new File(path3);
            if (file3.exists()) {
                ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file3.getPath(), file3.getName(), Long.valueOf(file3.length())));
            } else if (path3.contains("/shared_files/")) {
                path3 = path3.replace("/shared_files", "");
            }
            return path3;
        }
        if (!isTencentProvider(uri)) {
            String dataColumn = getDataColumn(context, uri, null, null);
            ZLogger.v("getDataColumn:" + dataColumn);
            return !TextUtils.isEmpty(dataColumn) ? dataColumn : uri.getPath();
        }
        ZLogger.v("TencentProvider");
        String path4 = uri.getPath();
        File file4 = new File(path4);
        if (file4.exists()) {
            ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file4.getPath(), file4.getName(), Long.valueOf(file4.length())));
        } else if (path4.contains("/QQBrowser/")) {
            path4 = Environment.getExternalStorageDirectory() + path4.replace("/QQBrowser", "");
        }
        return path4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v73, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r0v74, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v93, types: [java.lang.String] */
    public static String b(Context context, Uri uri) throws IOException {
        InputStream inputStreamOpenInputStream;
        FileOutputStream fileOutputStream;
        byte[] bArr;
        String authority = uri.getAuthority();
        int i = "com.android.externalstorage.documents".equals(authority) ? 1 : "com.android.providers.downloads.documents".equals(authority) ? 2 : "com.android.providers.media.documents".equals(authority) ? 3 : "com.google.android.apps.docs.storage".equals(authority) ? 4 : 0;
        int i2 = i;
        String documentId = DocumentsContract.getDocumentId(uri);
        String[] strArrSplit = documentId.split(":");
        ZLogger.v(String.format("getPathFromDocumentUri, type=0x%02X, docId=%s", Integer.valueOf(i), documentId));
        switch (i2) {
            case 1:
                if (strArrSplit.length <= 0) {
                    return documentId;
                }
                String str = strArrSplit[0];
                ZLogger.v(String.format("type=%s", str));
                return "primary".equalsIgnoreCase(str) ? Environment.getExternalStorageDirectory() + "/" + strArrSplit[1] : Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
            case 2:
                int length = strArrSplit.length;
                if (length < 2) {
                    if (length == 1) {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId)), null, null);
                    }
                    return null;
                }
                String str2 = strArrSplit[0];
                ZLogger.v("type=" + str2);
                if ("raw".equalsIgnoreCase(str2)) {
                    return strArrSplit[1];
                }
                if ("msf".equalsIgnoreCase(str2)) {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(strArrSplit[1])), null, null);
                }
                getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), ContentUris.parseId(uri)), null, null);
                return null;
            case 3:
                String str3 = strArrSplit[0];
                Uri uri2 = null;
                if ("image".equalsIgnoreCase(str3)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equalsIgnoreCase(str3)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equalsIgnoreCase(str3)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit[1]});
            case 4:
                ?? r0 = context;
                Cursor cursorQuery = r0.getContentResolver().query(uri, null, null, null, null);
                int columnIndex = cursorQuery.getColumnIndex("_display_name");
                int columnIndex2 = cursorQuery.getColumnIndex("_size");
                cursorQuery.moveToFirst();
                String string = cursorQuery.getString(columnIndex);
                ZLogger.e("name:" + string);
                ZLogger.e("size:" + Long.toString(cursorQuery.getLong(columnIndex2)));
                File file = new File(context.getCacheDir(), string);
                try {
                    inputStreamOpenInputStream = r0.getContentResolver().openInputStream(uri);
                    fileOutputStream = new FileOutputStream(file);
                    bArr = new byte[Math.min(inputStreamOpenInputStream.available(), 1048576)];
                } catch (Exception unused) {
                    ZLogger.e(r0.getMessage());
                }
                while (true) {
                    int i3 = inputStreamOpenInputStream.read(bArr);
                    if (i3 == -1) {
                        inputStreamOpenInputStream.close();
                        fileOutputStream.close();
                        ZLogger.d("File Path: " + file.getPath());
                        r0 = "File Size: " + file.length();
                        ZLogger.d(r0);
                        return file.getPath();
                    }
                    fileOutputStream.write(bArr, 0, i3);
                }
            default:
                return null;
        }
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
                cursor = cursorQuery;
                if (cursorQuery != null && cursor.moveToFirst()) {
                    int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_data");
                    if (columnIndexOrThrow >= 0) {
                        String string = cursor.getString(columnIndexOrThrow);
                        cursor.close();
                        return string;
                    }
                    ZLogger.w("column '_data' does not exist. ");
                }
                if (cursor == null) {
                    return "";
                }
            } catch (Exception unused) {
                ZLogger.w(context.toString());
                if (0 == 0) {
                    return "";
                }
            }
            cursor.close();
            return "";
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
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

    public static boolean isGoogleCloudDocument(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isHuaweiUri(Uri uri) {
        return "com.huawei.hidisk.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isHuaweiShareUri(Uri uri) {
        return "com.huawei.filemanager.share.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isOppoUri(Uri uri) {
        return "com.coloros.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isNokia(Uri uri) {
        return "com.fihtdc.filemanager.provider".equals(uri.getAuthority());
    }

    public static boolean isTencentProvider(Uri uri) {
        return "com.tencent.mtt.fileprovider".equals(uri.getAuthority());
    }

    public static boolean makeDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:46:0x00d1
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.content.Context, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v28, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.io.FileOutputStream, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v37, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.FileNotFoundException] */
    public static void copyFromAssetsToSdcard(android.content.Context r5, boolean r6, java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.utility.FileUtils.copyFromAssetsToSdcard(android.content.Context, boolean, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v19, types: [int] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Exception] */
    public static void copyFileStream(File file, Uri uri, Context context) throws Throwable {
        ?? OpenInputStream = context;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            try {
                OpenInputStream = OpenInputStream.getContentResolver().openInputStream(uri);
                inputStream = OpenInputStream;
                ?? fileOutputStream = new FileOutputStream(file);
                if (OpenInputStream != 0) {
                    try {
                        byte[] bArr = new byte[EqConstants.CodeIndex.REALTIME_EQ_2];
                        while (true) {
                            OpenInputStream = inputStream.read(bArr);
                            if (OpenInputStream <= 0) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, OpenInputStream);
                            }
                        }
                    } catch (Exception e) {
                        outputStream = e;
                        OpenInputStream.printStackTrace();
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        outputStream = fileOutputStream;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw th;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                fileOutputStream.close();
            } catch (Exception unused) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fa A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v37, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v48 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFile(java.lang.String r10, java.lang.String r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.utility.FileUtils.copyFile(java.lang.String, java.lang.String):void");
    }

    public static String getSuffix(File file) {
        return (file == null || !file.exists() || file.isDirectory()) ? "" : getSuffix(file.getName());
    }

    public static String getFileExtensionFromUrl(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf2 = str.lastIndexOf(35);
        if (iLastIndexOf2 > 0) {
            str = str.substring(0, iLastIndexOf2);
        }
        int iLastIndexOf3 = str.lastIndexOf(63);
        if (iLastIndexOf3 > 0) {
            str = str.substring(0, iLastIndexOf3);
        }
        int iLastIndexOf4 = str.lastIndexOf(47);
        if (iLastIndexOf4 >= 0) {
            str = str.substring(iLastIndexOf4 + 1);
        }
        return (str.isEmpty() || (iLastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(iLastIndexOf + 1);
    }

    public static String getFileExtensionFromUrl2(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf2 = str.lastIndexOf(35);
        ZLogger.d("fragment=" + iLastIndexOf2);
        if (iLastIndexOf2 > 0) {
            str = str.substring(0, iLastIndexOf2);
        }
        int iLastIndexOf3 = str.lastIndexOf(63);
        ZLogger.d("query=" + iLastIndexOf3);
        if (iLastIndexOf3 > 0) {
            str = str.substring(0, iLastIndexOf3);
        }
        int iLastIndexOf4 = str.lastIndexOf(47);
        if (iLastIndexOf4 >= 0) {
            str = str.substring(iLastIndexOf4 + 1);
        }
        return (str.isEmpty() || !Pattern.matches("[a-zA-Z_0-9\\+\\.\\-\\(\\)\\%]+", str) || (iLastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(iLastIndexOf + 1);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [int, java.lang.Object] */
    public static int getUrlFileSize(String str) {
        ?? contentLength;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            contentLength = httpURLConnection.getContentLength();
            httpURLConnection.disconnect();
            return contentLength;
        } catch (Exception unused) {
            ZLogger.e(contentLength.toString());
            return 0;
        }
    }

    public static long getFileSize(String str) {
        File file = new File(str);
        long length = 0;
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                length = getFileSize(file2.getAbsolutePath()) + length;
            }
        } else {
            length = file.length();
        }
        return length;
    }

    public static File createDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getSaveFile(String str, String str2, boolean z) {
        return getSaveFile(getSavePath(str) + File.separator + str2, z);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File, java.lang.Throwable] */
    public static File getSaveFile(String str, boolean z) throws IOException {
        ?? file = new File(str);
        try {
            if (!file.exists() && z) {
                file.createNewFile();
            }
        } catch (IOException unused) {
            file.printStackTrace();
        }
        return file;
    }

    public static String getSuffix(String str) {
        if (TextUtils.isEmpty(str) || str.endsWith(".")) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf != -1) {
            return str.substring(iLastIndexOf + 1).toLowerCase(Locale.US);
        }
        return "";
    }

    public static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        byte[] bArr = new byte[EqConstants.CodeIndex.REALTIME_EQ_2];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:46:0x00ce
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.content.res.AssetManager, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v27, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.io.FileOutputStream, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.FileNotFoundException] */
    public static void copyFromAssetsToSdcard(android.content.res.AssetManager r5, boolean r6, java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.utility.FileUtils.copyFromAssetsToSdcard(android.content.res.AssetManager, boolean, java.lang.String, java.lang.String):void");
    }
}
