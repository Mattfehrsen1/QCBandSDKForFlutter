package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodOxygenEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcBloodOxygenDao_Impl implements QcBloodOxygenDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<BloodOxygenEntity> __deletionAdapterOfBloodOxygenEntity;
    private final EntityInsertionAdapter<BloodOxygenEntity> __insertionAdapterOfBloodOxygenEntity;
    private final EntityDeletionOrUpdateAdapter<BloodOxygenEntity> __updateAdapterOfBloodOxygenEntity;

    public QcBloodOxygenDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBloodOxygenEntity = new EntityInsertionAdapter<BloodOxygenEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `blood_oxygen` (`device_address`,`date_str`,`min_array`,`max_array`,`unix_time`,`sync`,`last_sync_time`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BloodOxygenEntity bloodOxygenEntity) {
                if (bloodOxygenEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, bloodOxygenEntity.getDeviceAddress());
                }
                if (bloodOxygenEntity.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, bloodOxygenEntity.getDateStr());
                }
                if (bloodOxygenEntity.getMinArray() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, bloodOxygenEntity.getMinArray());
                }
                if (bloodOxygenEntity.getMaxArray() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, bloodOxygenEntity.getMaxArray());
                }
                supportSQLiteStatement.bindLong(5, bloodOxygenEntity.getUnixTime());
                supportSQLiteStatement.bindLong(6, bloodOxygenEntity.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, bloodOxygenEntity.getLastSyncTime());
            }
        };
        this.__deletionAdapterOfBloodOxygenEntity = new EntityDeletionOrUpdateAdapter<BloodOxygenEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `blood_oxygen` WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, BloodOxygenEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDateStr());
                }
            }
        };
        this.__updateAdapterOfBloodOxygenEntity = new EntityDeletionOrUpdateAdapter<BloodOxygenEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `blood_oxygen` SET `device_address` = ?,`date_str` = ?,`min_array` = ?,`max_array` = ?,`unix_time` = ?,`sync` = ?,`last_sync_time` = ? WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BloodOxygenEntity bloodOxygenEntity) {
                if (bloodOxygenEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, bloodOxygenEntity.getDeviceAddress());
                }
                if (bloodOxygenEntity.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, bloodOxygenEntity.getDateStr());
                }
                if (bloodOxygenEntity.getMinArray() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, bloodOxygenEntity.getMinArray());
                }
                if (bloodOxygenEntity.getMaxArray() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, bloodOxygenEntity.getMaxArray());
                }
                supportSQLiteStatement.bindLong(5, bloodOxygenEntity.getUnixTime());
                supportSQLiteStatement.bindLong(6, bloodOxygenEntity.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, bloodOxygenEntity.getLastSyncTime());
                if (bloodOxygenEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, bloodOxygenEntity.getDeviceAddress());
                }
                if (bloodOxygenEntity.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, bloodOxygenEntity.getDateStr());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<BloodOxygenEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBloodOxygenEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final BloodOxygenEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBloodOxygenEntity.insert((EntityInsertionAdapter<BloodOxygenEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final BloodOxygenEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBloodOxygenEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<BloodOxygenEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBloodOxygenEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final BloodOxygenEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBloodOxygenEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final BloodOxygenEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfBloodOxygenEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao
    public BloodOxygenEntity queryBloodOxygenByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_oxygen where  device_address=?  and date_str=? limit 1", 2);
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
        BloodOxygenEntity bloodOxygenEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min_array");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "max_array");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                bloodOxygenEntity = new BloodOxygenEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getLong(columnIndexOrThrow7));
            }
            return bloodOxygenEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao
    public BloodOxygenEntity queryBloodOxygenByDateDesc(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_oxygen where  device_address=?  order by  date_str desc limit 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        BloodOxygenEntity bloodOxygenEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min_array");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "max_array");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                bloodOxygenEntity = new BloodOxygenEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getLong(columnIndexOrThrow7));
            }
            return bloodOxygenEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao
    public List<BloodOxygenEntity> queryUploadToServer(final String mac, final long startTime) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_oxygen where device_address=? and unix_time>= ?", 2);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        roomSQLiteQueryAcquire.bindLong(2, startTime);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min_array");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "max_array");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BloodOxygenEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getLong(columnIndexOrThrow7)));
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
