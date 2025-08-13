package com.qcwireless.qcwatch.ui.base.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public class AppCacheDataManager {
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/databases"));
    }

    public static void cleanAppFiles(Context context) {
        deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/"));
    }

    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
    }

    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    public static void cleanApplicationData(Context context, String... filepath) {
        try {
            cleanInternalCache(context);
            cleanExternalCache(context);
            cleanDatabases(context);
            cleanSharedPreference(context);
            cleanFiles(context);
            for (String str : filepath) {
                cleanCustomCache(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }
    }

    public static String getTotalCacheSize(Context context) throws Exception {
        long folderSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            folderSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(folderSize);
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (String str : dir.list()) {
                if (!deleteDir(new File(dir, str))) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static long getFolderSize(File file) throws Exception {
        long length;
        long j = 0;
        try {
            File[] fileArrListFiles = file.listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    length = getFolderSize(fileArrListFiles[i]);
                } else {
                    length = fileArrListFiles[i].length();
                }
                j += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static String getFormatSize(double size) {
        double d = size / 1024.0d;
        if (d < 1.0d) {
            return "0K";
        }
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return new BigDecimal(Double.toString(d)).setScale(2, 4).toPlainString() + "KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "MB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d4).setScale(2, 4).toPlainString() + "TB";
    }
}
