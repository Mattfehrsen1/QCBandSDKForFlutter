package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchThemeFace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcWatchThemeDao_Impl implements QcWatchThemeDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<WatchThemeFace> __deletionAdapterOfWatchThemeFace;
    private final EntityInsertionAdapter<WatchThemeFace> __insertionAdapterOfWatchThemeFace;
    private final EntityDeletionOrUpdateAdapter<WatchThemeFace> __updateAdapterOfWatchThemeFace;

    public QcWatchThemeDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWatchThemeFace = new EntityInsertionAdapter<WatchThemeFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `watch_theme_face` (`theme_name`,`hardware_version`,`pre_image_url`,`bin_url`,`theme_desc`,`local_image_url`,`local_bin_url`,`theme_type`,`app_home_index`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WatchThemeFace value) {
                if (value.getThemeName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getThemeName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
                if (value.getPreImageUrl() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPreImageUrl());
                }
                if (value.getBinUrl() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getBinUrl());
                }
                if (value.getThemeDesc() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getThemeDesc());
                }
                if (value.getLocalImageUrl() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getLocalImageUrl());
                }
                if (value.getLocalBinUrl() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getLocalBinUrl());
                }
                stmt.bindLong(8, value.getThemeType());
                stmt.bindLong(9, value.getAppHomeIndex());
            }
        };
        this.__deletionAdapterOfWatchThemeFace = new EntityDeletionOrUpdateAdapter<WatchThemeFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `watch_theme_face` WHERE `theme_name` = ? AND `hardware_version` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchThemeFace value) {
                if (value.getThemeName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getThemeName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
            }
        };
        this.__updateAdapterOfWatchThemeFace = new EntityDeletionOrUpdateAdapter<WatchThemeFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `watch_theme_face` SET `theme_name` = ?,`hardware_version` = ?,`pre_image_url` = ?,`bin_url` = ?,`theme_desc` = ?,`local_image_url` = ?,`local_bin_url` = ?,`theme_type` = ?,`app_home_index` = ? WHERE `theme_name` = ? AND `hardware_version` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchThemeFace value) {
                if (value.getThemeName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getThemeName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
                if (value.getPreImageUrl() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPreImageUrl());
                }
                if (value.getBinUrl() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getBinUrl());
                }
                if (value.getThemeDesc() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getThemeDesc());
                }
                if (value.getLocalImageUrl() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getLocalImageUrl());
                }
                if (value.getLocalBinUrl() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getLocalBinUrl());
                }
                stmt.bindLong(8, value.getThemeType());
                stmt.bindLong(9, value.getAppHomeIndex());
                if (value.getThemeName() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getThemeName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getHardwareVersion());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<WatchThemeFace> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchThemeFace.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final WatchThemeFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchThemeFace.insert((EntityInsertionAdapter<WatchThemeFace>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final WatchThemeFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchThemeFace.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<WatchThemeFace> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchThemeFace.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final WatchThemeFace... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchThemeFace.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final WatchThemeFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWatchThemeFace.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao
    public WatchThemeFace queryWatchThemeByName(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_theme_face where  theme_name=? and hardware_version=? ", 2);
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
        WatchThemeFace watchThemeFace = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_desc");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_type");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "app_home_index");
            if (cursorQuery.moveToFirst()) {
                watchThemeFace = new WatchThemeFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9));
            }
            return watchThemeFace;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao
    public WatchThemeFace queryWatchThemeByNameAndHdVersion(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_theme_face where  theme_name=? and hardware_version=? ", 2);
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
        WatchThemeFace watchThemeFace = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_desc");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_type");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "app_home_index");
            if (cursorQuery.moveToFirst()) {
                watchThemeFace = new WatchThemeFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9));
            }
            return watchThemeFace;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao
    public List<WatchThemeFace> queryAll(final String version) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_theme_face where hardware_version=?", 1);
        if (version == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, version);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_desc");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "theme_type");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "app_home_index");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new WatchThemeFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9)));
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
