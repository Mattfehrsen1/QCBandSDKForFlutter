package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.MusicToDeviceEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcMusicManagerDao_Impl implements QcMusicManagerDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<MusicToDeviceEntity> __deletionAdapterOfMusicToDeviceEntity;
    private final EntityInsertionAdapter<MusicToDeviceEntity> __insertionAdapterOfMusicToDeviceEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByMusicName;
    private final SharedSQLiteStatement __preparedStmtOfDeleteMusics;
    private final SharedSQLiteStatement __preparedStmtOfUpdateMenuName;
    private final SharedSQLiteStatement __preparedStmtOfUpdateMusicMenu;
    private final EntityDeletionOrUpdateAdapter<MusicToDeviceEntity> __updateAdapterOfMusicToDeviceEntity;

    public QcMusicManagerDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMusicToDeviceEntity = new EntityInsertionAdapter<MusicToDeviceEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `music_to_device` (`device_address`,`music_name`,`singer`,`size`,`duration`,`path`,`album_id`,`song_menu_name`,`song_menu_id`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, MusicToDeviceEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                if (value.getMusicName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getMusicName());
                }
                if (value.getSinger() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getSinger());
                }
                stmt.bindLong(4, value.getSize());
                stmt.bindLong(5, value.getDuration());
                if (value.getPath() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getPath());
                }
                stmt.bindLong(7, value.getAlbumId());
                if (value.getSongMenuName() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getSongMenuName());
                }
                stmt.bindLong(9, value.getSongMenuId());
            }
        };
        this.__deletionAdapterOfMusicToDeviceEntity = new EntityDeletionOrUpdateAdapter<MusicToDeviceEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `music_to_device` WHERE `music_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, MusicToDeviceEntity value) {
                if (value.getMusicName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMusicName());
                }
            }
        };
        this.__updateAdapterOfMusicToDeviceEntity = new EntityDeletionOrUpdateAdapter<MusicToDeviceEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `music_to_device` SET `device_address` = ?,`music_name` = ?,`singer` = ?,`size` = ?,`duration` = ?,`path` = ?,`album_id` = ?,`song_menu_name` = ?,`song_menu_id` = ? WHERE `music_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, MusicToDeviceEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                if (value.getMusicName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getMusicName());
                }
                if (value.getSinger() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getSinger());
                }
                stmt.bindLong(4, value.getSize());
                stmt.bindLong(5, value.getDuration());
                if (value.getPath() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getPath());
                }
                stmt.bindLong(7, value.getAlbumId());
                if (value.getSongMenuName() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getSongMenuName());
                }
                stmt.bindLong(9, value.getSongMenuId());
                if (value.getMusicName() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getMusicName());
                }
            }
        };
        this.__preparedStmtOfUpdateMenuName = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update  music_to_device set  song_menu_name= ? where song_menu_id = ? and device_address=?";
            }
        };
        this.__preparedStmtOfUpdateMusicMenu = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update  music_to_device set  song_menu_id=0 ,song_menu_name=? where music_name = ? and device_address=?";
            }
        };
        this.__preparedStmtOfDeleteMusics = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from  music_to_device  where device_address =?";
            }
        };
        this.__preparedStmtOfDeleteByMusicName = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from music_to_device where music_name=?";
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<MusicToDeviceEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMusicToDeviceEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final MusicToDeviceEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMusicToDeviceEntity.insert((EntityInsertionAdapter<MusicToDeviceEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final MusicToDeviceEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMusicToDeviceEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<MusicToDeviceEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMusicToDeviceEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final MusicToDeviceEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMusicToDeviceEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final MusicToDeviceEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMusicToDeviceEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public void updateMenuName(final String address, final String menuName, final long menuId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateMenuName.acquire();
        if (menuName == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, menuName);
        }
        supportSQLiteStatementAcquire.bindLong(2, menuId);
        if (address == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, address);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateMenuName.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public void updateMusicMenu(final String address, final String musicName, final String menuName) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateMusicMenu.acquire();
        if (menuName == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, menuName);
        }
        if (musicName == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, musicName);
        }
        if (address == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, address);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateMusicMenu.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public void deleteMusics(final String address) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteMusics.acquire();
        if (address == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, address);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteMusics.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public void deleteByMusicName(final String musicName) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByMusicName.acquire();
        if (musicName == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, musicName);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByMusicName.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public List<MusicToDeviceEntity> queryMusicNoSongList(final String address) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from music_to_device where  device_address=? and song_menu_id >0", 1);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "music_name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "singer");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_id");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_name");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new MusicToDeviceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getLong(columnIndexOrThrow9)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public List<MusicToDeviceEntity> queryMusicsByMenuId(final String address, final long menuId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from music_to_device where device_address=? and  song_menu_id =?", 2);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        roomSQLiteQueryAcquire.bindLong(2, menuId);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "music_name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "singer");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_id");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_name");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new MusicToDeviceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getLong(columnIndexOrThrow9)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public List<MusicToDeviceEntity> queryAllMusicNoMenuId(final String address) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from music_to_device where device_address=? and song_menu_id<=0", 1);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "music_name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "singer");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_id");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_name");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new MusicToDeviceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getLong(columnIndexOrThrow9)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public List<MusicToDeviceEntity> queryAll(final String address) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from music_to_device where device_address=?", 1);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "music_name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "singer");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_id");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_name");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new MusicToDeviceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getLong(columnIndexOrThrow9)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicManagerDao
    public MusicToDeviceEntity queryMusicByName(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from music_to_device where music_name=? and device_address=?", 2);
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str2);
        }
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        MusicToDeviceEntity musicToDeviceEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "music_name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "singer");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_id");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_name");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "song_menu_id");
            if (cursorQuery.moveToFirst()) {
                musicToDeviceEntity = new MusicToDeviceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getLong(columnIndexOrThrow9));
            }
            return musicToDeviceEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
