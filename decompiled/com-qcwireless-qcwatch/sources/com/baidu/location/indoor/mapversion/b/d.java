package com.baidu.location.indoor.mapversion.b;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class d {
    public static boolean a(File file, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                if (!zipEntryNextElement.isDirectory()) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream, 2048);
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int i = bufferedInputStream.read(bArr, 0, 2048);
                        if (i == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, i);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            zipFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
