package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcBloodPressureDao_Impl implements QcBloodPressureDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<BloodPressureEntity> __deletionAdapterOfBloodPressureEntity;
    private final EntityInsertionAdapter<BloodPressureEntity> __insertionAdapterOfBloodPressureEntity;
    private final EntityDeletionOrUpdateAdapter<BloodPressureEntity> __updateAdapterOfBloodPressureEntity;

    public QcBloodPressureDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBloodPressureEntity = new EntityInsertionAdapter<BloodPressureEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `blood_pressure` (`device_address`,`unix_time`,`sbp`,`dbp`,`sync`,`last_sync_time`) VALUES (?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BloodPressureEntity bloodPressureEntity) {
                if (bloodPressureEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, bloodPressureEntity.getDeviceAddress());
                }
                supportSQLiteStatement.bindLong(2, bloodPressureEntity.getUnixTime());
                supportSQLiteStatement.bindLong(3, bloodPressureEntity.getSbp());
                supportSQLiteStatement.bindLong(4, bloodPressureEntity.getDbp());
                supportSQLiteStatement.bindLong(5, bloodPressureEntity.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(6, bloodPressureEntity.getLastSyncTime());
            }
        };
        this.__deletionAdapterOfBloodPressureEntity = new EntityDeletionOrUpdateAdapter<BloodPressureEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `blood_pressure` WHERE `device_address` = ? AND `unix_time` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, BloodPressureEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                stmt.bindLong(2, value.getUnixTime());
            }
        };
        this.__updateAdapterOfBloodPressureEntity = new EntityDeletionOrUpdateAdapter<BloodPressureEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `blood_pressure` SET `device_address` = ?,`unix_time` = ?,`sbp` = ?,`dbp` = ?,`sync` = ?,`last_sync_time` = ? WHERE `device_address` = ? AND `unix_time` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BloodPressureEntity bloodPressureEntity) {
                if (bloodPressureEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, bloodPressureEntity.getDeviceAddress());
                }
                supportSQLiteStatement.bindLong(2, bloodPressureEntity.getUnixTime());
                supportSQLiteStatement.bindLong(3, bloodPressureEntity.getSbp());
                supportSQLiteStatement.bindLong(4, bloodPressureEntity.getDbp());
                supportSQLiteStatement.bindLong(5, bloodPressureEntity.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(6, bloodPressureEntity.getLastSyncTime());
                if (bloodPressureEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, bloodPressureEntity.getDeviceAddress());
                }
                supportSQLiteStatement.bindLong(8, bloodPressureEntity.getUnixTime());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<BloodPressureEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBloodPressureEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final BloodPressureEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBloodPressureEntity.insert((EntityInsertionAdapter<BloodPressureEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final BloodPressureEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBloodPressureEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<BloodPressureEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBloodPressureEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final BloodPressureEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBloodPressureEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final BloodPressureEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfBloodPressureEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao
    public List<BloodPressureEntity> queryBloodPressureList(final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_pressure where  device_address=? ", 1);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sbp");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BloodPressureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5) != 0, cursorQuery.getLong(columnIndexOrThrow6)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao
    public List<BloodPressureEntity> queryByUnixTime(final String mac, final long startTime, final long endTime) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_pressure where device_address=? and unix_time>= ? and unix_time<= ? order by unix_time desc", 3);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        roomSQLiteQueryAcquire.bindLong(2, startTime);
        roomSQLiteQueryAcquire.bindLong(3, endTime);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sbp");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BloodPressureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5) != 0, cursorQuery.getLong(columnIndexOrThrow6)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao
    public BloodPressureEntity queryLastBpValue(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_pressure where device_address=? order by unix_time desc limit 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        BloodPressureEntity bloodPressureEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sbp");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                bloodPressureEntity = new BloodPressureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5) != 0, cursorQuery.getLong(columnIndexOrThrow6));
            }
            return bloodPressureEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao
    public List<BloodPressureEntity> queryUploadToServer(final String mac, final long startTime) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from blood_pressure where device_address=? and unix_time>= ?", 2);
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sbp");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BloodPressureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5) != 0, cursorQuery.getLong(columnIndexOrThrow6)));
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
