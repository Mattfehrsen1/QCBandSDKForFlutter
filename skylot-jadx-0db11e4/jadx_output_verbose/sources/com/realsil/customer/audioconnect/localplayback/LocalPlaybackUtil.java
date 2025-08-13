package com.realsil.customer.audioconnect.localplayback;

import android.text.TextUtils;
import com.realsil.customer.audioconnect.localplayback.entity.PlaylistInfo;
import com.realsil.customer.audioconnect.localplayback.entity.SongInfo;
import com.realsil.customer.core.logger.ZLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:libs/rtk-audioconnect-localplayback-1.0.9.jar:com/realsil/customer/audioconnect/localplayback/LocalPlaybackUtil.class */
public final class LocalPlaybackUtil {
    public static boolean checkAudioFormatWhetherSupport(int i, int i2) {
        if (i2 == 0) {
            return true;
        }
        return i2 == 1 ? (i & 1) == 1 : i2 == 2 ? ((i & 2) >> 1) == 1 : i2 == 4 ? ((i & 4) >> 2) == 1 : i2 == 8 && ((i & 8) >> 3) == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    public static File createNewFile(String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ZLogger.e("create failed, input dir and name can not be null");
            return null;
        }
        File file = new File(str, str2);
        boolean zExists = file.exists();
        ?? CreateNewFile = zExists;
        if (zExists) {
            CreateNewFile = file.delete();
        }
        try {
            CreateNewFile = file.createNewFile();
            if (CreateNewFile == 0) {
                return null;
            }
            return file;
        } catch (Exception unused) {
            CreateNewFile.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean writeContentToFile(File file, byte[] bArr) throws IOException {
        if (file == null || bArr == 0) {
            ZLogger.e("write failed, input file and content can not be null");
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (Exception unused) {
            bArr.printStackTrace();
            return false;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v69 ??, still in use, count: 1, list:
          (r0v69 ?? I:java.lang.String) from 0x0152: INVOKE (r0v70 ?? I:int) = (r0v69 ?? I:java.lang.String), (r1v37 ?? I:java.lang.String) VIRTUAL call: java.lang.String.lastIndexOf(java.lang.String):int A[Catch: all -> 0x01cb, Exception -> 0x01d6, MD:(java.lang.String):int (c)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static java.util.List<com.realsil.customer.audioconnect.localplayback.entity.SongInfo> getSongInfoList(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v69 ??, still in use, count: 1, list:
          (r0v69 ?? I:java.lang.String) from 0x0152: INVOKE (r0v70 ?? I:int) = (r0v69 ?? I:java.lang.String), (r1v37 ?? I:java.lang.String) VIRTUAL call: java.lang.String.lastIndexOf(java.lang.String):int A[Catch: all -> 0x01cb, Exception -> 0x01d6, MD:(java.lang.String):int (c)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:405)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

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
}
