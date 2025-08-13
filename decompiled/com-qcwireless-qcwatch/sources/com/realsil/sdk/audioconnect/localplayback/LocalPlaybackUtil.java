package com.realsil.sdk.audioconnect.localplayback;

import android.text.TextUtils;
import com.realsil.sdk.audioconnect.localplayback.entity.PlaylistInfo;
import com.realsil.sdk.audioconnect.localplayback.entity.SongInfo;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class LocalPlaybackUtil {
    public static boolean checkAudioFormatWhetherSupport(int i, int i2) {
        if (i2 == 0) {
            return true;
        }
        return i2 == 1 ? (i & 1) == 1 : i2 == 2 ? ((i & 2) >> 1) == 1 : i2 == 4 ? ((i & 4) >> 2) == 1 : i2 == 8 && ((i & 8) >> 3) == 1;
    }

    public static File createNewFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ZLogger.e("create failed, input dir and name can not be null");
            return null;
        }
        File file = new File(str, str2);
        if (file.exists()) {
            file.delete();
        }
        try {
            if (file.createNewFile()) {
                return file;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PlaylistInfo> getPlaylistInfoList(List<SongInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 16; i++) {
            PlaylistInfo playlistInfo = new PlaylistInfo();
            playlistInfo.setPlaylistNo(i);
            playlistInfo.setContainSongList(new ArrayList());
            arrayList.add(playlistInfo);
        }
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                SongInfo songInfo = list.get(i2);
                if (!songInfo.isDeleted()) {
                    Iterator<Integer> it = songInfo.getRelatePlayList().iterator();
                    while (it.hasNext()) {
                        ((PlaylistInfo) arrayList.get(it.next().intValue())).getContainSongList().add(songInfo);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0165 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0173 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.realsil.sdk.audioconnect.localplayback.entity.SongInfo> getSongInfoList(java.io.File r13, java.io.File r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.audioconnect.localplayback.LocalPlaybackUtil.getSongInfoList(java.io.File, java.io.File):java.util.List");
    }

    public static boolean writeContentToFile(File file, byte[] bArr) throws IOException {
        if (file == null || bArr == null) {
            ZLogger.e("write failed, input file and content can not be null");
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
