package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcWatchWallpaperDao_Impl implements QcWatchWallpaperDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<WatchWallpaperFace> __deletionAdapterOfWatchWallpaperFace;
    private final EntityInsertionAdapter<WatchWallpaperFace> __insertionAdapterOfWatchWallpaperFace;
    private final EntityDeletionOrUpdateAdapter<WatchWallpaperFace> __updateAdapterOfWatchWallpaperFace;

    public QcWatchWallpaperDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWatchWallpaperFace = new EntityInsertionAdapter<WatchWallpaperFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `watch_wallpaper_face` (`wallpaper_name`,`hardware_version`,`wallpaper_desc`,`wallpaper_url`,`wallpaper_file_url`,`wallpaper_type_app_index`,`wallpaper_app_index`,`local_image_url`,`local_bin_url`,`wallpaper_type`,`wallpaper_type_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WatchWallpaperFace value) {
                if (value.getWallpaperName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getWallpaperName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
                if (value.getWallpaperDesc() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getWallpaperDesc());
                }
                if (value.getWallpaperUrl() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getWallpaperUrl());
                }
                if (value.getWallpaperFileUrl() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getWallpaperFileUrl());
                }
                stmt.bindLong(6, value.getWallpaperTypeAppIndex());
                stmt.bindLong(7, value.getWallpaperAppIndex());
                if (value.getLocalImageUrl() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getLocalImageUrl());
                }
                if (value.getLocalBinUrl() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getLocalBinUrl());
                }
                stmt.bindLong(10, value.getWallpaperType());
                if (value.getWallpaperTypeName() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getWallpaperTypeName());
                }
            }
        };
        this.__deletionAdapterOfWatchWallpaperFace = new EntityDeletionOrUpdateAdapter<WatchWallpaperFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `watch_wallpaper_face` WHERE `wallpaper_name` = ? AND `hardware_version` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchWallpaperFace value) {
                if (value.getWallpaperName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getWallpaperName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
            }
        };
        this.__updateAdapterOfWatchWallpaperFace = new EntityDeletionOrUpdateAdapter<WatchWallpaperFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `watch_wallpaper_face` SET `wallpaper_name` = ?,`hardware_version` = ?,`wallpaper_desc` = ?,`wallpaper_url` = ?,`wallpaper_file_url` = ?,`wallpaper_type_app_index` = ?,`wallpaper_app_index` = ?,`local_image_url` = ?,`local_bin_url` = ?,`wallpaper_type` = ?,`wallpaper_type_name` = ? WHERE `wallpaper_name` = ? AND `hardware_version` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchWallpaperFace value) {
                if (value.getWallpaperName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getWallpaperName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
                if (value.getWallpaperDesc() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getWallpaperDesc());
                }
                if (value.getWallpaperUrl() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getWallpaperUrl());
                }
                if (value.getWallpaperFileUrl() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getWallpaperFileUrl());
                }
                stmt.bindLong(6, value.getWallpaperTypeAppIndex());
                stmt.bindLong(7, value.getWallpaperAppIndex());
                if (value.getLocalImageUrl() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getLocalImageUrl());
                }
                if (value.getLocalBinUrl() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getLocalBinUrl());
                }
                stmt.bindLong(10, value.getWallpaperType());
                if (value.getWallpaperTypeName() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getWallpaperTypeName());
                }
                if (value.getWallpaperName() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getWallpaperName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getHardwareVersion());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<WatchWallpaperFace> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchWallpaperFace.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final WatchWallpaperFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchWallpaperFace.insert((EntityInsertionAdapter<WatchWallpaperFace>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final WatchWallpaperFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchWallpaperFace.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<WatchWallpaperFace> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchWallpaperFace.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final WatchWallpaperFace... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchWallpaperFace.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final WatchWallpaperFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWatchWallpaperFace.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao
    public WatchWallpaperFace queryWatchWallpaperByName(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_wallpaper_face where  wallpaper_name=? and hardware_version=? ", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        WatchWallpaperFace watchWallpaperFace = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_desc");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_file_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type_app_index");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_app_index");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type_name");
            if (cursorQuery.moveToFirst()) {
                watchWallpaperFace = new WatchWallpaperFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
            }
            return watchWallpaperFace;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao
    public WatchWallpaperFace queryWatchFaceByNameAndHdVersion(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_wallpaper_face where  wallpaper_name=? and hardware_version=? ", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        WatchWallpaperFace watchWallpaperFace = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_desc");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_file_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type_app_index");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_app_index");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type_name");
            if (cursorQuery.moveToFirst()) {
                watchWallpaperFace = new WatchWallpaperFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
            }
            return watchWallpaperFace;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao
    public List<WatchWallpaperFace> queryAll(final String version) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_wallpaper_face where hardware_version=?", 1);
        if (version == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, version);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_desc");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_file_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type_app_index");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_app_index");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "wallpaper_type_name");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new WatchWallpaperFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
