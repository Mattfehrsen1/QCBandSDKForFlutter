package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFaceIndexEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcWatchFaceIndexDao_Impl implements QcWatchFaceIndexDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<WatchFaceIndexEntity> __deletionAdapterOfWatchFaceIndexEntity;
    private final EntityInsertionAdapter<WatchFaceIndexEntity> __insertionAdapterOfWatchFaceIndexEntity;
    private final EntityDeletionOrUpdateAdapter<WatchFaceIndexEntity> __updateAdapterOfWatchFaceIndexEntity;

    public QcWatchFaceIndexDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWatchFaceIndexEntity = new EntityInsertionAdapter<WatchFaceIndexEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `watch_face_index` (`name`,`hardware_version`,`pre_image_url`,`bin_url`,`price`,`local_image_url`,`local_bin_url`,`type_id`,`position`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WatchFaceIndexEntity value) {
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
                stmt.bindLong(8, value.getTypeId());
                stmt.bindLong(9, value.getPosition());
            }
        };
        this.__deletionAdapterOfWatchFaceIndexEntity = new EntityDeletionOrUpdateAdapter<WatchFaceIndexEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `watch_face_index` WHERE `hardware_version` = ? AND `type_id` = ? AND `position` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchFaceIndexEntity value) {
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getHardwareVersion());
                }
                stmt.bindLong(2, value.getTypeId());
                stmt.bindLong(3, value.getPosition());
            }
        };
        this.__updateAdapterOfWatchFaceIndexEntity = new EntityDeletionOrUpdateAdapter<WatchFaceIndexEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `watch_face_index` SET `name` = ?,`hardware_version` = ?,`pre_image_url` = ?,`bin_url` = ?,`price` = ?,`local_image_url` = ?,`local_bin_url` = ?,`type_id` = ?,`position` = ? WHERE `hardware_version` = ? AND `type_id` = ? AND `position` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, WatchFaceIndexEntity value) {
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
                stmt.bindLong(8, value.getTypeId());
                stmt.bindLong(9, value.getPosition());
                if (value.getHardwareVersion() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getHardwareVersion());
                }
                stmt.bindLong(11, value.getTypeId());
                stmt.bindLong(12, value.getPosition());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<WatchFaceIndexEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchFaceIndexEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final WatchFaceIndexEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWatchFaceIndexEntity.insert((EntityInsertionAdapter<WatchFaceIndexEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final WatchFaceIndexEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchFaceIndexEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<WatchFaceIndexEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchFaceIndexEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final WatchFaceIndexEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfWatchFaceIndexEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final WatchFaceIndexEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWatchFaceIndexEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao
    public List<WatchFaceIndexEntity> queryWatchFaceIndexOrderByPosition(final int typeId, final String hdVersion) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face_index where type_id=? and hardware_version=? order by position asc", 2);
        roomSQLiteQueryAcquire.bindLong(1, typeId);
        if (hdVersion == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, hdVersion);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "position");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new WatchFaceIndexEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao
    public WatchFaceIndexEntity queryWatchFaceIndexByPosition(int i, String str, int i2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face_index where type_id=? and hardware_version=? and position=?", 3);
        roomSQLiteQueryAcquire.bindLong(1, i);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        roomSQLiteQueryAcquire.bindLong(3, i2);
        this.__db.assertNotSuspendingTransaction();
        WatchFaceIndexEntity watchFaceIndexEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "position");
            if (cursorQuery.moveToFirst()) {
                watchFaceIndexEntity = new WatchFaceIndexEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9));
            }
            return watchFaceIndexEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao
    public WatchFaceIndexEntity queryWatchFaceByName(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face_index where name=?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        WatchFaceIndexEntity watchFaceIndexEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "position");
            if (cursorQuery.moveToFirst()) {
                watchFaceIndexEntity = new WatchFaceIndexEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9));
            }
            return watchFaceIndexEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceIndexDao
    public List<WatchFaceIndexEntity> queryAllByHardVersion(final String hdName) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from watch_face_index where position>0 and hardware_version=?", 1);
        if (hdName == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, hdName);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hardware_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pre_image_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bin_url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "price");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_bin_url");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type_id");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "position");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new WatchFaceIndexEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9)));
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
