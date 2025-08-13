package com.qcwireless.qcwatch.ui.base.repository.device;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.elvishew.xlog.XLog;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.FinishMusicAddFirstEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.MusicToDeviceEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.SongMenuEntity;
import com.qcwireless.qcwatch.ui.device.contact.bean.PinYinStringHelper;
import com.qcwireless.qcwatch.ui.device.music.bean.MusicSortComparator;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: MusicRepository.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 I2\u00020\u0001:\u0001IB\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0016\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\bJ\u000e\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0012J\u000e\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0012J\u000e\u0010*\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0012J\u000e\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\bJ\u0018\u0010-\u001a\u0004\u0018\u00010\u00122\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0006J\"\u00101\u001a\u0004\u0018\u0001022\u0006\u0010.\u001a\u00020/2\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u0006H\u0002J\f\u00105\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\f\u00106\u001a\b\u0012\u0004\u0012\u00020807J\f\u00109\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0006\u0010:\u001a\u00020\bJ\u0010\u0010;\u001a\u0004\u0018\u0001082\u0006\u0010<\u001a\u00020\u0012J\u0010\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010'\u001a\u00020\u0012J\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020>072\u0006\u0010@\u001a\u00020\u0006J\u001c\u0010A\u001a\u00020 2\u0006\u0010@\u001a\u00020\u00062\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u000e\u0010C\u001a\u00020\b2\u0006\u0010D\u001a\u000208J\u0016\u0010E\u001a\u00020 2\u0006\u0010<\u001a\u00020\u00122\u0006\u0010F\u001a\u00020\u0006J\u000e\u0010G\u001a\u00020 2\u0006\u0010H\u001a\u00020>R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006J"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;", "", "()V", "albumArtUri", "Landroid/net/Uri;", "albumId", "", TypedValues.TransitionType.S_DURATION, "", BreakpointSQLiteKey.ID, "list", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "name", "", "path", "qcMusicManagerDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMusicManagerDao;", "qcMusicMenuDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMusicMenuDao;", "singer", "size", "song", "getSong", "()Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "setSong", "(Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;)V", "addMusicList", "", "data", "computeSampleSize", "options", "Landroid/graphics/BitmapFactory$Options;", TypedValues.AttributesType.S_TARGET, "deleteMusic", "musicName", "deleteMusicByAddress", "address", "deleteMusicByName", "formatTime", "time", "getAlbumArt", "context", "Landroid/content/Context;", "album_id", "getArtworkFromFile", "Landroid/graphics/Bitmap;", "songid", "albumid", "getMusic", "queryAllMenus", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SongMenuEntity;", "queryAllMusic", "queryExistsMusic", "queryMenByName", "menuName", "queryMusicByNameAndId", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/MusicToDeviceEntity;", "queryMusicsByMenusId", "menusId", "removeMenu", "songs", "saveMusicMenu", "musicMenu", "updateMenuName", "menuId", "updateMusic", "entity", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicRepository {
    private final Uri albumArtUri;
    private long albumId;
    private int duration;
    private long id;
    private long size;
    private Song song;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static List<String> songName = new ArrayList();
    private static final Lazy<MusicRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MusicRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MusicRepository invoke() {
            return new MusicRepository();
        }
    });
    private final QcMusicManagerDao qcMusicManagerDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcMusicManagerDao();
    private final QcMusicMenuDao qcMusicMenuDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcMusicMenuDao();
    private List<Song> list = new ArrayList();
    private String name = "";
    private String singer = "";
    private String path = "";

    public MusicRepository() {
        Uri uri = Uri.parse("content://media/external/audio/albumart");
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"content://media/external/audio/albumart\")");
        this.albumArtUri = uri;
    }

    public final List<Song> getList() {
        return this.list;
    }

    public final void setList(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public final Song getSong() {
        return this.song;
    }

    public final void setSong(Song song) {
        this.song = song;
    }

    public final List<Song> getMusic() {
        try {
            this.list.clear();
            StringBuffer stringBuffer = new StringBuffer();
            XLog.i(songName);
            Iterator<String> it = songName.iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next());
            }
            Intrinsics.checkNotNullExpressionValue(stringBuffer.toString(), "stringBuffer.toString()");
            Cursor cursorQuery = QCApplication.INSTANCE.getCONTEXT().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, "title_key");
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    this.song = new Song();
                    this.name = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                    this.id = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_id"));
                    String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                    Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…aStore.Audio.Media.DATA))");
                    this.path = string;
                    this.duration = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(TypedValues.TransitionType.S_DURATION));
                    this.size = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_size"));
                    this.albumId = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("album_id"));
                    Song song = this.song;
                    Intrinsics.checkNotNull(song);
                    song.setSinger("");
                    Song song2 = this.song;
                    Intrinsics.checkNotNull(song2);
                    song2.setPath(this.path);
                    Song song3 = this.song;
                    Intrinsics.checkNotNull(song3);
                    song3.setDuration(this.duration);
                    Song song4 = this.song;
                    Intrinsics.checkNotNull(song4);
                    song4.setSize(this.size);
                    Song song5 = this.song;
                    Intrinsics.checkNotNull(song5);
                    song5.setAlbumId(this.albumId);
                    Song song6 = this.song;
                    Intrinsics.checkNotNull(song6);
                    song6.setName(String.valueOf(this.name));
                    boolean z = true;
                    if (!(String.valueOf(this.name).length() == 0)) {
                        Song song7 = this.song;
                        Intrinsics.checkNotNull(song7);
                        if (StringsKt.endsWith$default(song7.getName(), PictureMimeType.MP3, false, 2, (Object) null)) {
                            Song song8 = this.song;
                            Intrinsics.checkNotNull(song8);
                            String name = song8.getName();
                            Intrinsics.checkNotNull(this.song);
                            String strSubstring = name.substring(0, r6.getName().length() - 4);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                            String strReplace$default = StringsKt.replace$default(strSubstring, " ", "", false, 4, (Object) null);
                            if (strReplace$default.length() > 15) {
                                strReplace$default = strReplace$default.substring(0, 15);
                                Intrinsics.checkNotNullExpressionValue(strReplace$default, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Song song9 = this.song;
                            Intrinsics.checkNotNull(song9);
                            song9.setName(strReplace$default + PictureMimeType.MP3);
                            Song song10 = this.song;
                            Intrinsics.checkNotNull(song10);
                            XLog.i(song10.getName());
                            Song song11 = this.song;
                            Intrinsics.checkNotNull(song11);
                            if (song11.getPath().length() != 0) {
                                z = false;
                            }
                            if (!z) {
                                Song song12 = this.song;
                                Intrinsics.checkNotNull(song12);
                                Song song13 = this.song;
                                Intrinsics.checkNotNull(song13);
                                String headChar = PinYinStringHelper.getHeadChar(StringsKt.replace$default(song13.getName(), " ", "", false, 4, (Object) null));
                                Intrinsics.checkNotNullExpressionValue(headChar, "getHeadChar(song!!.name.replace(\" \", \"\"))");
                                song12.setFirstName(headChar);
                                Song song14 = this.song;
                                Intrinsics.checkNotNull(song14);
                                if (!PinYinStringHelper.isLetter(song14.getFirstName())) {
                                    Song song15 = this.song;
                                    Intrinsics.checkNotNull(song15);
                                    song15.setFirstName("#");
                                }
                                Song song16 = this.song;
                                Intrinsics.checkNotNull(song16);
                                XLog.i(song16.getName());
                                Song song17 = this.song;
                                Intrinsics.checkNotNull(song17);
                                if (queryMusicByNameAndId(song17.getName()) == null) {
                                    List<Song> list = this.list;
                                    Song song18 = this.song;
                                    Intrinsics.checkNotNull(song18);
                                    list.add(song18);
                                }
                            }
                        }
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(this.list, new MusicSortComparator());
        return this.list;
    }

    public final String formatTime(int time) {
        int i = time / 1000;
        int i2 = i % 60;
        if (i2 < 10) {
            return (i / 60) + ":0" + i2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i / 60);
        sb.append(':');
        sb.append(i2);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.graphics.Bitmap getArtworkFromFile(android.content.Context r5, long r6, long r8) throws java.io.FileNotFoundException {
        /*
            r4 = this;
            r0 = 0
            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r2 >= 0) goto L13
            int r3 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r3 < 0) goto Lb
            goto L13
        Lb:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Must specify an album or a song id"
            r5.<init>(r6)
            throw r5
        L13:
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch: java.io.FileNotFoundException -> L83
            r1.<init>()     // Catch: java.io.FileNotFoundException -> L83
            java.lang.String r3 = "r"
            if (r2 >= 0) goto L4d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L83
            r8.<init>()     // Catch: java.io.FileNotFoundException -> L83
            java.lang.String r9 = "content://media/external/audio/media/"
            r8.append(r9)     // Catch: java.io.FileNotFoundException -> L83
            r8.append(r6)     // Catch: java.io.FileNotFoundException -> L83
            java.lang.String r6 = "/albumart"
            r8.append(r6)     // Catch: java.io.FileNotFoundException -> L83
            java.lang.String r6 = r8.toString()     // Catch: java.io.FileNotFoundException -> L83
            android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch: java.io.FileNotFoundException -> L83
            java.lang.String r7 = "parse(\n                 …bumart\"\n                )"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.io.FileNotFoundException -> L83
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.io.FileNotFoundException -> L83
            android.os.ParcelFileDescriptor r5 = r5.openFileDescriptor(r6, r3)     // Catch: java.io.FileNotFoundException -> L83
            if (r5 == 0) goto L68
            java.io.FileDescriptor r5 = r5.getFileDescriptor()     // Catch: java.io.FileNotFoundException -> L83
            goto L69
        L4d:
            android.net.Uri r6 = r4.albumArtUri     // Catch: java.io.FileNotFoundException -> L83
            android.net.Uri r6 = android.content.ContentUris.withAppendedId(r6, r8)     // Catch: java.io.FileNotFoundException -> L83
            java.lang.String r7 = "withAppendedId(albumArtUri, albumid)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.io.FileNotFoundException -> L83
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.io.FileNotFoundException -> L83
            android.os.ParcelFileDescriptor r5 = r5.openFileDescriptor(r6, r3)     // Catch: java.io.FileNotFoundException -> L83
            if (r5 == 0) goto L68
            java.io.FileDescriptor r5 = r5.getFileDescriptor()     // Catch: java.io.FileNotFoundException -> L83
            goto L69
        L68:
            r5 = r0
        L69:
            r6 = 1
            r1.inSampleSize = r6     // Catch: java.io.FileNotFoundException -> L83
            r1.inJustDecodeBounds = r6     // Catch: java.io.FileNotFoundException -> L83
            android.graphics.BitmapFactory.decodeFileDescriptor(r5, r0, r1)     // Catch: java.io.FileNotFoundException -> L83
            r6 = 100
            r1.inSampleSize = r6     // Catch: java.io.FileNotFoundException -> L83
            r6 = 0
            r1.inJustDecodeBounds = r6     // Catch: java.io.FileNotFoundException -> L83
            r1.inDither = r6     // Catch: java.io.FileNotFoundException -> L83
            android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.ARGB_8888     // Catch: java.io.FileNotFoundException -> L83
            r1.inPreferredConfig = r6     // Catch: java.io.FileNotFoundException -> L83
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFileDescriptor(r5, r0, r1)     // Catch: java.io.FileNotFoundException -> L83
            goto L87
        L83:
            r5 = move-exception
            r5.printStackTrace()
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository.getArtworkFromFile(android.content.Context, long, long):android.graphics.Bitmap");
    }

    public final int computeSampleSize(BitmapFactory.Options options, int target) {
        Intrinsics.checkNotNullParameter(options, "options");
        int i = options.outWidth;
        int i2 = options.outHeight;
        int iMax = Math.max(i / target, i2 / target);
        if (iMax == 0) {
            return 1;
        }
        if (iMax > 1 && i > target && i / iMax < target) {
            iMax--;
        }
        return (iMax <= 1 || i2 <= target || i2 / iMax >= target) ? iMax : iMax - 1;
    }

    public final String getAlbumArt(Context context, long album_id) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://media/external/audio/albums/" + album_id), new String[]{"album_art"}, null, null, null);
        if (cursorQuery == null || cursorQuery.getCount() <= 0 || cursorQuery.getColumnCount() <= 0) {
            string = null;
        } else {
            cursorQuery.moveToNext();
            string = cursorQuery.getString(0);
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if (string != null) {
            return string;
        }
        return null;
    }

    public final int saveMusicMenu(SongMenuEntity musicMenu) {
        Intrinsics.checkNotNullParameter(musicMenu, "musicMenu");
        if (queryMenByName(musicMenu.getMenuName()) != null) {
            return -1;
        }
        this.qcMusicMenuDao.insert(musicMenu);
        return 1;
    }

    public final SongMenuEntity queryMenByName(String menuName) {
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        return this.qcMusicMenuDao.queryMusicMenuByMenuName(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), menuName);
    }

    public final List<SongMenuEntity> queryAllMenus() {
        return this.qcMusicMenuDao.queryMenusList(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final List<MusicToDeviceEntity> queryMusicsByMenusId(long menusId) {
        return this.qcMusicManagerDao.queryMusicsByMenuId(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), menusId);
    }

    public final void addMusicList(List<Song> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        for (Song song : data) {
            MusicToDeviceEntity musicToDeviceEntity = new MusicToDeviceEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), song.getName(), song.getSinger(), song.getSize(), song.getDuration(), song.getPath(), song.getAlbumId(), "", 0L);
            XLog.i(musicToDeviceEntity);
            this.qcMusicManagerDao.insert(musicToDeviceEntity);
        }
        EventBus.getDefault().post(new FinishMusicAddFirstEvent());
    }

    public final void updateMusic(MusicToDeviceEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        this.qcMusicManagerDao.insert(entity);
    }

    public final List<Song> queryAllMusic() {
        ArrayList arrayList = new ArrayList();
        for (MusicToDeviceEntity musicToDeviceEntity : this.qcMusicManagerDao.queryAllMusicNoMenuId(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear())) {
            Song song = new Song();
            song.setSize(musicToDeviceEntity.getSize());
            song.setName(musicToDeviceEntity.getMusicName());
            song.setSinger(musicToDeviceEntity.getSinger());
            song.setDuration(musicToDeviceEntity.getDuration());
            song.setPath(musicToDeviceEntity.getPath());
            song.setAlbumId(musicToDeviceEntity.getAlbumId());
            song.setSelect(false);
            arrayList.add(song);
        }
        return arrayList;
    }

    public final int queryExistsMusic() {
        return this.qcMusicManagerDao.queryAll(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear()).size();
    }

    public final MusicToDeviceEntity queryMusicByNameAndId(String musicName) {
        Intrinsics.checkNotNullParameter(musicName, "musicName");
        return this.qcMusicManagerDao.queryMusicByName(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), musicName);
    }

    public final void deleteMusic(String musicName) {
        Intrinsics.checkNotNullParameter(musicName, "musicName");
        MusicToDeviceEntity musicToDeviceEntityQueryMusicByNameAndId = queryMusicByNameAndId(musicName);
        if (musicToDeviceEntityQueryMusicByNameAndId != null) {
            this.qcMusicManagerDao.delete(musicToDeviceEntityQueryMusicByNameAndId);
        }
    }

    public final void deleteMusicByAddress(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        this.qcMusicManagerDao.deleteMusics(address);
    }

    public final void updateMenuName(String menuName, long menuId) {
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        this.qcMusicMenuDao.updateMenuName(menuName, menuId);
        this.qcMusicManagerDao.updateMenuName(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), menuName, menuId);
    }

    public final void removeMenu(long menusId, List<Song> songs) {
        Intrinsics.checkNotNullParameter(songs, "songs");
        this.qcMusicMenuDao.deleteMenu(menusId);
        Iterator<Song> it = songs.iterator();
        while (it.hasNext()) {
            this.qcMusicManagerDao.updateMusicMenu(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), it.next().getName(), "");
        }
    }

    public final void deleteMusicByName(String musicName) {
        Intrinsics.checkNotNullParameter(musicName, "musicName");
        this.qcMusicManagerDao.deleteByMusicName(musicName);
    }

    /* compiled from: MusicRepository.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "songName", "", "", "getSongName", "()Ljava/util/List;", "setSongName", "(Ljava/util/List;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getSongName() {
            return MusicRepository.songName;
        }

        public final void setSongName(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            MusicRepository.songName = list;
        }

        public final MusicRepository getGetInstance() {
            return (MusicRepository) MusicRepository.getInstance$delegate.getValue();
        }
    }
}
