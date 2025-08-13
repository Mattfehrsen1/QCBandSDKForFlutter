package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.BodyTemperatureEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcTemperatureDao_Impl implements QcTemperatureDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<BodyTemperatureEntity> __deletionAdapterOfBodyTemperatureEntity;
    private final EntityInsertionAdapter<BodyTemperatureEntity> __insertionAdapterOfBodyTemperatureEntity;
    private final EntityDeletionOrUpdateAdapter<BodyTemperatureEntity> __updateAdapterOfBodyTemperatureEntity;

    public QcTemperatureDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBodyTemperatureEntity = new EntityInsertionAdapter<BodyTemperatureEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `temperature_entity` (`device_address`,`date_str`,`unix_time`,`temperature`,`min`,`sync`,`manual_flag`,`last_sync_time`) VALUES (?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BodyTemperatureEntity bodyTemperatureEntity) {
                if (bodyTemperatureEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, bodyTemperatureEntity.getDeviceAddress());
                }
                if (bodyTemperatureEntity.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, bodyTemperatureEntity.getDateStr());
                }
                supportSQLiteStatement.bindLong(3, bodyTemperatureEntity.getUnixTime());
                supportSQLiteStatement.bindDouble(4, bodyTemperatureEntity.getTemperature());
                supportSQLiteStatement.bindLong(5, bodyTemperatureEntity.getMin());
                supportSQLiteStatement.bindLong(6, bodyTemperatureEntity.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, bodyTemperatureEntity.getManualFlag());
                supportSQLiteStatement.bindLong(8, bodyTemperatureEntity.getLastSyncTime());
            }
        };
        this.__deletionAdapterOfBodyTemperatureEntity = new EntityDeletionOrUpdateAdapter<BodyTemperatureEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `temperature_entity` WHERE `device_address` = ? AND `date_str` = ? AND `min` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, BodyTemperatureEntity value) {
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
                stmt.bindLong(3, value.getMin());
            }
        };
        this.__updateAdapterOfBodyTemperatureEntity = new EntityDeletionOrUpdateAdapter<BodyTemperatureEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `temperature_entity` SET `device_address` = ?,`date_str` = ?,`unix_time` = ?,`temperature` = ?,`min` = ?,`sync` = ?,`manual_flag` = ?,`last_sync_time` = ? WHERE `device_address` = ? AND `date_str` = ? AND `min` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BodyTemperatureEntity bodyTemperatureEntity) {
                if (bodyTemperatureEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, bodyTemperatureEntity.getDeviceAddress());
                }
                if (bodyTemperatureEntity.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, bodyTemperatureEntity.getDateStr());
                }
                supportSQLiteStatement.bindLong(3, bodyTemperatureEntity.getUnixTime());
                supportSQLiteStatement.bindDouble(4, bodyTemperatureEntity.getTemperature());
                supportSQLiteStatement.bindLong(5, bodyTemperatureEntity.getMin());
                supportSQLiteStatement.bindLong(6, bodyTemperatureEntity.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, bodyTemperatureEntity.getManualFlag());
                supportSQLiteStatement.bindLong(8, bodyTemperatureEntity.getLastSyncTime());
                if (bodyTemperatureEntity.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, bodyTemperatureEntity.getDeviceAddress());
                }
                if (bodyTemperatureEntity.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, bodyTemperatureEntity.getDateStr());
                }
                supportSQLiteStatement.bindLong(11, bodyTemperatureEntity.getMin());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<BodyTemperatureEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBodyTemperatureEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final BodyTemperatureEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBodyTemperatureEntity.insert((EntityInsertionAdapter<BodyTemperatureEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final BodyTemperatureEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBodyTemperatureEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<BodyTemperatureEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBodyTemperatureEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final BodyTemperatureEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfBodyTemperatureEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final BodyTemperatureEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfBodyTemperatureEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao
    public List<BodyTemperatureEntity> queryTemperatureList(final String mac, final String date) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from temperature_entity  where device_address=? and date_str=? order by min asc", 2);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, date);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "temperature");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "manual_flag");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BodyTemperatureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getLong(columnIndexOrThrow8)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao
    public List<BodyTemperatureEntity> queryTemperatureListDesc(final String mac, final String date) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from temperature_entity  where device_address=? and date_str=? order by min desc", 2);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, date);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "temperature");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "manual_flag");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BodyTemperatureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getLong(columnIndexOrThrow8)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao
    public BodyTemperatureEntity queryTemperatureLast(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from temperature_entity where device_address=? order by date_str desc limit 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        BodyTemperatureEntity bodyTemperatureEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "temperature");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "manual_flag");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                bodyTemperatureEntity = new BodyTemperatureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getLong(columnIndexOrThrow8));
            }
            return bodyTemperatureEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao
    public BodyTemperatureEntity queryTemperatureLastByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from temperature_entity where device_address=? and date_str=? order by date_str desc limit 1", 2);
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
        BodyTemperatureEntity bodyTemperatureEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "temperature");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "manual_flag");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                bodyTemperatureEntity = new BodyTemperatureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getLong(columnIndexOrThrow8));
            }
            return bodyTemperatureEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao
    public List<BodyTemperatureEntity> queryTemperatureListByFlag(final String mac, final String date, final int flag) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from temperature_entity  where device_address=? and date_str=?  and manual_flag=? ", 3);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, date);
        }
        roomSQLiteQueryAcquire.bindLong(3, flag);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "temperature");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "manual_flag");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BodyTemperatureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getLong(columnIndexOrThrow8)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao
    public List<BodyTemperatureEntity> queryUploadToServer(final String mac, final long startTime) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from temperature_entity where device_address=? and unix_time>= ?", 2);
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "temperature");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "min");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "manual_flag");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BodyTemperatureEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getLong(columnIndexOrThrow8)));
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
