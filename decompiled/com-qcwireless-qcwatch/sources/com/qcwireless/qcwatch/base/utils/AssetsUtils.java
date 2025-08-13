package com.qcwireless.qcwatch.base.utils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes3.dex */
public class AssetsUtils {
    public static String getFromAssets(Context context, String fileName) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return str;
                }
                str = str + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void copyFilesAssets(Context context, String oldPath, String newPath) throws IOException {
        try {
            String[] list = context.getAssets().list(oldPath);
            if (list.length > 0) {
                new File(newPath).mkdirs();
                for (String str : list) {
                    copyFilesAssets(context, oldPath + File.separator + str, newPath + File.separator + str);
                }
                return;
            }
            InputStream inputStreamOpen = context.getAssets().open(oldPath);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(newPath));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.flush();
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkFilePathExists(String path) {
        return new File(path).exists();
    }
}
