package com.qcwireless.qcwatch.base.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.loader.content.CursorLoader;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import io.reactivex.annotations.SchedulerSupport;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: FileUtils.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u001a\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001MB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004J\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0010\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\u0010\u0010!\u001a\u00020\u00182\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\"\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J \u0010#\u001a\u0004\u0018\u00010 2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010'\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020%J\u000e\u0010*\u001a\u00020 2\u0006\u0010)\u001a\u00020%J\u0006\u0010+\u001a\u00020 J\u0006\u0010,\u001a\u00020 J\u001c\u0010-\u001a\u0004\u0018\u00010 2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010\u0004J\u000e\u00101\u001a\u0002022\u0006\u0010\u0007\u001a\u00020\u0004J\u001b\u00103\u001a\b\u0012\u0004\u0012\u00020\u0004042\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u00105J\u0010\u00106\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004J\u0006\u00107\u001a\u00020 J\u000e\u00108\u001a\u00020\u00042\u0006\u0010)\u001a\u00020%J\u0006\u00109\u001a\u00020\u0004J\u0006\u0010:\u001a\u00020 J\u0006\u0010;\u001a\u00020 J\u0006\u0010<\u001a\u00020 J\u0006\u0010=\u001a\u00020 J\u0006\u0010>\u001a\u00020 J\u0006\u0010?\u001a\u00020 J\u001a\u0010@\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\u0012\u0010A\u001a\u0004\u0018\u00010/2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u001c\u0010B\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010C\u001a\u0004\u0018\u00010\u0004J \u0010D\u001a\u00020\u00042\u0006\u0010)\u001a\u00020%2\u0006\u0010E\u001a\u00020\u000b2\b\u0010C\u001a\u0004\u0018\u00010\u0004J\u0016\u0010F\u001a\u00020\u00062\u0006\u0010G\u001a\u00020 2\u0006\u0010H\u001a\u00020\u0004J\u001e\u0010I\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u001e\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020/2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/FileUtils;", "", "()V", "DFU_BIN_NAME", "", "changeFileDirName", "", "path", "oldName", "newName", "copy", "", TypedValues.TransitionType.S_FROM, "Ljava/io/InputStream;", TypedValues.TransitionType.S_TO, "Ljava/io/OutputStream;", "src", "dest", "copyFile", "oldPath", "newPath", "createDestDirectoryIfNecessary", "destParam", "createDirs", "", "folder", "createFile", "fileName", "deleteDir", "dir", "deleteDirWithFile", "file", "Ljava/io/File;", "deleteFile", "deleteOverdueFile", "drawableToFile", "mContext", "Landroid/content/Context;", "drawableId", "fileExists", "getAppCacheRootFile", "context", "getAppRootFile", "getBinDirFile", "getCustomDialDirFile", "getFileFromBytes", "bytes", "", "outputFile", "getFileLength", "", "getFilesList", "", "(Ljava/lang/String;)[Ljava/lang/String;", "getFilesNumber", "getGuideDirFile", "getInternalAppPath", "getInternalPath", "getLogDirFile", "getMusicDirFile", "getRecordDirFile", "getWatchFaceDirFile", "getWatchThemeDirFile", "getWatchWallpaperDirFile", "moveFile", "readBytes", "readContents", "format", "readResource", BreakpointSQLiteKey.ID, "saveFileToPath", "sourceFile", "destinationPath", "writeToFile", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "writeToFile1", "data", "UriUtil", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileUtils {
    public static final String DFU_BIN_NAME = "T91_Application.bin";
    public static final FileUtils INSTANCE = new FileUtils();

    private FileUtils() {
    }

    public final File getAppRootFile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (context.getExternalFilesDir("") != null) {
            File externalFilesDir = context.getExternalFilesDir("");
            Intrinsics.checkNotNull(externalFilesDir);
            Intrinsics.checkNotNullExpressionValue(externalFilesDir, "{\n            context.ge…lFilesDir(\"\")!!\n        }");
            return externalFilesDir;
        }
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = externalCacheDir == null ? context.getCacheDir() : externalCacheDir;
        Intrinsics.checkNotNullExpressionValue(cacheDir, "{\n            val extern…ontext.cacheDir\n        }");
        return cacheDir;
    }

    public final File getAppCacheRootFile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (context.getExternalCacheDir() != null) {
            File externalCacheDir = context.getExternalCacheDir();
            Intrinsics.checkNotNull(externalCacheDir);
            Intrinsics.checkNotNullExpressionValue(externalCacheDir, "{\n            context.externalCacheDir!!\n        }");
            return externalCacheDir;
        }
        File externalCacheDir2 = context.getExternalCacheDir();
        File cacheDir = externalCacheDir2 == null ? context.getCacheDir() : externalCacheDir2;
        Intrinsics.checkNotNullExpressionValue(cacheDir, "{\n            val extern…ontext.cacheDir\n        }");
        return cacheDir;
    }

    public final void deleteOverdueFile(String path) {
        File[] fileArrListFiles;
        try {
            DateUtil dateUtil = new DateUtil();
            dateUtil.addDay(-3);
            File file = new File(path);
            if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                int length = fileArrListFiles.length;
                for (int i = 0; i < length; i++) {
                    if (fileArrListFiles[i].lastModified() < dateUtil.getTimestamp()) {
                        deleteFile(fileArrListFiles[i]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final boolean deleteFile(File file) {
        return file != null && file.delete();
    }

    public final File getCustomDialDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), SchedulerSupport.CUSTOM);
    }

    public final File getWatchFaceDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "face");
    }

    public final File getBinDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "dfu");
    }

    public final File getGuideDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "guide");
    }

    public final File getMusicDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "music");
    }

    public final File getWatchThemeDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "theme");
    }

    public final File getWatchWallpaperDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "wallpaper");
    }

    public final File getLogDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "log");
    }

    public final File getRecordDirFile() {
        return new File(getAppRootFile(QCApplication.INSTANCE.getCONTEXT()), "record");
    }

    public final String getInternalPath() {
        String absolutePath = Environment.getDataDirectory().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getDataDirectory().absolutePath");
        return absolutePath;
    }

    public final String getInternalAppPath(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String absolutePath = context.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir.absolutePath");
        return absolutePath;
    }

    public final boolean createFile(String path, String fileName) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File file = new File(path + '/' + fileName);
        if (file.exists()) {
            return false;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public final boolean fileExists(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new File(path).exists();
    }

    public final boolean createDirs(String folder) {
        Intrinsics.checkNotNullParameter(folder, "folder");
        File file = new File(folder);
        if (file.exists()) {
            return false;
        }
        file.mkdirs();
        return true;
    }

    public final void deleteDir(String dir) {
        File file = new File(dir);
        if (file.exists()) {
            deleteDirWithFile(file);
        }
    }

    private final void deleteDirWithFile(File file) {
        if (file.isDirectory() && file.exists()) {
            String[] list = file.list();
            Intrinsics.checkNotNullExpressionValue(list, "file.list()");
            if (!(list.length == 0)) {
                File[] fileArrListFiles = file.listFiles();
                Intrinsics.checkNotNullExpressionValue(fileArrListFiles, "file.listFiles()");
                for (File f : fileArrListFiles) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        Intrinsics.checkNotNullExpressionValue(f, "f");
                        deleteDirWithFile(f);
                    }
                }
            }
            file.delete();
        }
    }

    public final boolean deleteFile(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        file.delete();
        return true;
    }

    public final void copyFile(String oldPath, String newPath) throws Throwable {
        getFileFromBytes(readBytes(oldPath), newPath);
    }

    public final void moveFile(String oldPath, String newPath) throws Throwable {
        copyFile(oldPath, newPath);
        deleteFile(oldPath);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int copy(java.lang.String r8, java.lang.String r9) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = -1
            r1 = 0
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            r3.<init>(r8)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            java.io.OutputStream r8 = (java.io.OutputStream) r8     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L72
        L15:
            r4 = r3
            java.io.FileInputStream r4 = (java.io.FileInputStream) r4     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L72
            int r4 = r4.read(r1)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L72
            if (r4 == r0) goto L25
            r5 = r8
            java.io.FileOutputStream r5 = (java.io.FileOutputStream) r5     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L72
            r5.write(r1, r2, r4)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L72
            goto L15
        L25:
            r3.close()     // Catch: java.io.IOException -> L29
            goto L2d
        L29:
            r0 = move-exception
            r0.printStackTrace()
        L2d:
            r8.close()     // Catch: java.io.IOException -> L31
            goto L35
        L31:
            r8 = move-exception
            r8.printStackTrace()
        L35:
            r0 = 0
            goto L5f
        L37:
            r1 = move-exception
            goto L48
        L39:
            r9 = move-exception
            r8 = r1
            goto L73
        L3c:
            r8 = move-exception
            r6 = r1
            r1 = r8
            r8 = r6
            goto L48
        L41:
            r9 = move-exception
            r8 = r1
            goto L74
        L44:
            r8 = move-exception
            r3 = r1
            r1 = r8
            r8 = r3
        L48:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L72
            if (r3 == 0) goto L55
            r3.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r1 = move-exception
            r1.printStackTrace()
        L55:
            if (r8 == 0) goto L5f
            r8.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r8 = move-exception
            r8.printStackTrace()
        L5f:
            java.io.File r8 = new java.io.File
            r8.<init>(r9)
            boolean r9 = r8.exists()
            if (r9 == 0) goto L71
            long r1 = java.lang.System.currentTimeMillis()
            r8.setLastModified(r1)
        L71:
            return r0
        L72:
            r9 = move-exception
        L73:
            r1 = r3
        L74:
            if (r1 == 0) goto L7e
            r1.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r0 = move-exception
            r0.printStackTrace()
        L7e:
            if (r8 == 0) goto L88
            r8.close()     // Catch: java.io.IOException -> L84
            goto L88
        L84:
            r8 = move-exception
            r8.printStackTrace()
        L88:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.base.utils.FileUtils.copy(java.lang.String, java.lang.String):int");
    }

    public final int copy(InputStream from, OutputStream to) throws IOException {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        try {
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = from.read(bArr);
                    if (i != -1) {
                        to.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                from.close();
                try {
                    to.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return 0;
            } catch (Exception e3) {
                e3.printStackTrace();
                try {
                    from.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                try {
                    to.close();
                    return -1;
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return -1;
                }
            }
        } finally {
        }
    }

    public final int getFilesNumber(String dir) {
        return new File(dir).listFiles().length;
    }

    public final String[] getFilesList(String dir) {
        String[] list = new File(dir).list();
        Intrinsics.checkNotNullExpressionValue(list, "file.list()");
        return list;
    }

    public final void changeFileDirName(String path, String oldName, String newName) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(oldName, "oldName");
        Intrinsics.checkNotNullParameter(newName, "newName");
        new File(path + '/' + oldName).renameTo(new File(path + '/' + newName));
    }

    public final void writeToFile(String content, String path, String fileName) throws IOException {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File file = new File(path + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            createFile(path, fileName);
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            byte[] bytes = content.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            randomAccessFile.write(bytes);
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public final void writeToFile1(byte[] data, String path, String fileName) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        File parentFile;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File file = new File(path + '/' + fileName);
        File parentFile2 = file.getParentFile();
        BufferedOutputStream bufferedOutputStream2 = null;
        Boolean boolValueOf = parentFile2 != null ? Boolean.valueOf(parentFile2.exists()) : null;
        Intrinsics.checkNotNull(boolValueOf);
        if (!boolValueOf.booleanValue() && (parentFile = file.getParentFile()) != null) {
            parentFile.mkdirs();
        }
        if (!file.exists()) {
            createFile(path, fileName);
        }
        try {
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            bufferedOutputStream.write(data);
            bufferedOutputStream.close();
        } catch (Exception e3) {
            e = e3;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public final File getFileFromBytes(byte[] bytes, String outputFile) throws Throwable {
        File file;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                try {
                    file = new File(outputFile);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                file = null;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        } catch (Exception e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            return file;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        return file;
    }

    public final String readContents(String path, String format) throws IOException {
        FileInputStream fileInputStream;
        if (format == null) {
            format = "utf-8";
        }
        File file = new File(path);
        InputStreamReader inputStreamReader = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        try {
            inputStreamReader = new InputStreamReader(fileInputStream, format);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder("");
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append("\n");
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return sb.toString();
    }

    public final String readResource(Context context, int id, String format) throws Resources.NotFoundException, IOException {
        InputStreamReader inputStreamReader;
        Intrinsics.checkNotNullParameter(context, "context");
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(id);
        Intrinsics.checkNotNullExpressionValue(inputStreamOpenRawResource, "context.getResources().openRawResource(id)");
        try {
            inputStreamReader = new InputStreamReader(inputStreamOpenRawResource, format);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            inputStreamReader = null;
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder("");
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append("\n");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }

    public final byte[] readBytes(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.getChannel().size() <= 0) {
                return null;
            }
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            return bArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final long getFileLength(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        File file = new File(path);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    private final void createDestDirectoryIfNecessary(String destParam) {
        File file;
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        if (StringsKt.endsWith$default(destParam, separator, false, 2, (Object) null)) {
            file = new File(destParam);
        } else {
            String separator2 = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator2, "separator");
            String strSubstring = destParam.substring(0, StringsKt.lastIndexOf$default((CharSequence) destParam, separator2, 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            file = new File(strSubstring);
        }
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public final File drawableToFile(Context mContext, int drawableId, String fileName) throws IOException {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(mContext.getResources(), drawableId);
        Intrinsics.checkNotNullExpressionValue(bitmapDecodeResource, "decodeResource(mContext.resources, drawableId)");
        String str = mContext.getFilesDir().getAbsolutePath() + "/feedback";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str + '/' + fileName);
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmapDecodeResource.compress(Bitmap.CompressFormat.PNG, 20, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file2;
    }

    /* compiled from: FileUtils.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J;\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\bH\u0002J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/FileUtils$UriUtil;", "", "()V", "getDataColumn", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getImagePath", "getRealPathApi11to18", "contentUri", "getRealPathApi19Above", "isDownloadsDocument", "", "isExternalStorageDocument", "isGooglePhotosUri", "isMediaDocument", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class UriUtil {
        public static final UriUtil INSTANCE = new UriUtil();

        private UriUtil() {
        }

        private final String getRealPathApi19Above(Context context, Uri uri) throws NumberFormatException {
            try {
                Uri uri2 = null;
                if (!DocumentsContract.isDocumentUri(context, uri)) {
                    return StringsKt.equals(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, uri.getScheme(), true) ? isGooglePhotosUri(uri) ? uri.getLastPathSegment() : getDataColumn(context, uri, null, null) : StringsKt.equals("file", uri.getScheme(), true) ? uri.getPath() : "";
                }
                if (isExternalStorageDocument(uri)) {
                    String docId = DocumentsContract.getDocumentId(uri);
                    Intrinsics.checkNotNullExpressionValue(docId, "docId");
                    Object[] array = StringsKt.split$default((CharSequence) docId, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    if (array != null) {
                        String[] strArr = (String[]) array;
                        if (!StringsKt.equals("primary", strArr[0], true)) {
                            return "";
                        }
                        return Environment.getExternalStorageDirectory().toString() + '/' + strArr[1];
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                if (isDownloadsDocument(uri)) {
                    String documentId = DocumentsContract.getDocumentId(uri);
                    Uri uri3 = Uri.parse("content://downloads/public_downloads");
                    Long lValueOf = Long.valueOf(documentId);
                    Intrinsics.checkNotNullExpressionValue(lValueOf, "valueOf(id)");
                    Uri uriWithAppendedId = ContentUris.withAppendedId(uri3, lValueOf.longValue());
                    Intrinsics.checkNotNullExpressionValue(uriWithAppendedId, "withAppendedId(\n        …                        )");
                    return getDataColumn(context, uriWithAppendedId, null, null);
                }
                if (!isMediaDocument(uri)) {
                    return "";
                }
                String docId2 = DocumentsContract.getDocumentId(uri);
                Intrinsics.checkNotNullExpressionValue(docId2, "docId");
                Object[] array2 = StringsKt.split$default((CharSequence) docId2, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array2 != null) {
                    String[] strArr2 = (String[]) array2;
                    String str = strArr2[0];
                    if (Intrinsics.areEqual(PictureMimeType.MIME_TYPE_PREFIX_IMAGE, str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if (Intrinsics.areEqual(PictureMimeType.MIME_TYPE_PREFIX_VIDEO, str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if (Intrinsics.areEqual(PictureMimeType.MIME_TYPE_PREFIX_AUDIO, str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{strArr2[1]});
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            } catch (Exception unused) {
                return "";
            }
        }

        private final String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) throws Throwable {
            Cursor cursor = null;
            try {
                ContentResolver contentResolver = context.getContentResolver();
                Intrinsics.checkNotNull(uri);
                Cursor cursorQuery = contentResolver.query(uri, new String[]{"_data"}, selection, selectionArgs, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                            cursorQuery.close();
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

        private final String getRealPathApi11to18(Context context, Uri contentUri) throws IllegalArgumentException {
            try {
                Cursor cursorLoadInBackground = new CursorLoader(context, contentUri, new String[]{"_data"}, null, null, null).loadInBackground();
                if (cursorLoadInBackground == null) {
                    return null;
                }
                int columnIndexOrThrow = cursorLoadInBackground.getColumnIndexOrThrow("_data");
                cursorLoadInBackground.moveToFirst();
                return cursorLoadInBackground.getString(columnIndexOrThrow);
            } catch (Exception unused) {
                return null;
            }
        }

        public final String getImagePath(Context context, Uri uri) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, "uri");
            return Build.VERSION.SDK_INT < 19 ? getRealPathApi11to18(context, uri) : getRealPathApi19Above(context, uri);
        }

        private final boolean isExternalStorageDocument(Uri uri) {
            return Intrinsics.areEqual("com.android.externalstorage.documents", uri.getAuthority());
        }

        private final boolean isDownloadsDocument(Uri uri) {
            return Intrinsics.areEqual("com.android.providers.downloads.documents", uri.getAuthority());
        }

        private final boolean isMediaDocument(Uri uri) {
            return Intrinsics.areEqual("com.android.providers.media.documents", uri.getAuthority());
        }

        private final boolean isGooglePhotosUri(Uri uri) {
            return Intrinsics.areEqual("com.google.android.apps.photos.content", uri.getAuthority());
        }
    }

    public final void saveFileToPath(File sourceFile, String destinationPath) throws IOException {
        Intrinsics.checkNotNullParameter(sourceFile, "sourceFile");
        Intrinsics.checkNotNullParameter(destinationPath, "destinationPath");
        try {
            File file = new File(destinationPath);
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "fileInputStream.channel");
            FileChannel channel2 = fileOutputStream.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel2, "fileOutputStream.channel");
            channel2.transferFrom(channel, 0L, channel.size());
            channel.close();
            channel2.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
