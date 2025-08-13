package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcWatchFaceDao_Impl implements QcWatchFaceDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<WatchFace> __deletionAdapterOfWatchFace;
    private final EntityInsertionAdapter<WatchFace> __insertionAdapterOfWatchFace;
    private final EntityDeletionOrUpdateAdapter<WatchFace> __updateAdapterOfWatchFace;

    public QcWatchFaceDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWatchFace = new EntityInsertionAdapter<WatchFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `watch_face` (`name`,`hardware_version`,`pre_image_url`,`bin_url`,`price`,`face_type`,`face_desc`,`local_image_url`,`local_bin_url`,`market_version`,`type_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WatchFace value) {
                if (value.getName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getName());
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
                stmt.bindDouble(5, value.getPrice());
                stmt.bindLong(6, value.getFaceType());
                if (value.getFaceDesc() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getFaceDesc());
                }
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
                stmt.bindLong(10, value.getMarketVersion());
                stmt.bindLong(11, value.getTypeId());
            }
        };
        this.__deletionAdapterOfWatchFace = new EntityDeletionOrUpdateAdapter<WatchFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `watch_face` WHERE `name` = ? AND `hardware_version` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchFace value) {
                if (value.getName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getName());
                }
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHardwareVersion());
                }
            }
        };
        this.__updateAdapterOfWatchFace = new EntityDeletionOrUpdateAdapter<WatchFace>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `watch_face` SET `name` = ?,`hardware_version` = ?,`pre_image_url` = ?,`bin_url` = ?,`price` = ?,`face_type` = ?,`face_desc` = ?,`local_image_url` = ?,`local_bin_url` = ?,`market_version` = ?,`type_id` = ? WHERE `name` = ? AND `hardware_version` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchFace value) {
                if (value.getName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getName());
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
                stmt.bindDouble(5, value.getPrice());
                stmt.bindLong(6, value.getFaceType());
                if (value.getFaceDesc() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getFaceDesc());
                }
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
                stmt.bindLong(10, value.getMarketVersion());
                stmt.bindLong(11, value.getTypeId());
                if (value.getName() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getName());
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
    public void insertAll(final List<WatchFace> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchFace.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final WatchFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchFace.insert((EntityInsertionAdapter<WatchFace>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final WatchFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchFace.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<WatchFace> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchFace.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final WatchFace... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchFace.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final WatchFace element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWatchFace.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao
    public WatchFace queryWatchFaceByName(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face where  name=? and hardware_version=? ", 2);
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
        WatchFace watchFace = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_desc");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "market_version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            if (cursorQuery.moveToFirst()) {
                watchFace = new WatchFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11));
            }
            return watchFace;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao
    public WatchFace queryWatchFaceByNameAndHdVersion(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face where  name=? and hardware_version=? ", 2);
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
        WatchFace watchFace = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_desc");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "market_version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            if (cursorQuery.moveToFirst()) {
                watchFace = new WatchFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11));
            }
            return watchFace;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao
    public List<WatchFace> queryWatchFaceByHardwareVersion(final String version, final long marketVersion) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face where  hardware_version=? and market_version =? order by name asc", 2);
        if (version == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, version);
        }
        roomSQLiteQueryAcquire.bindLong(2, marketVersion);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_desc");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "market_version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new WatchFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao
    public List<WatchFace> queryAll(final String version) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face where hardware_version=?", 1);
        if (version == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, version);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "face_desc");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "market_version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new WatchFace(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11)));
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
