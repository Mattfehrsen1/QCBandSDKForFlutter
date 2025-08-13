package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.baidu.location.LocationConst;
import com.qcwireless.qcwatch.ui.base.repository.entity.HRVManualEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class ManualHrvDao_Impl implements ManualHrvDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<HRVManualEntity> __deletionAdapterOfHRVManualEntity;
    private final EntityInsertionAdapter<HRVManualEntity> __insertionAdapterOfHRVManualEntity;
    private final EntityDeletionOrUpdateAdapter<HRVManualEntity> __updateAdapterOfHRVManualEntity;

    public ManualHrvDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfHRVManualEntity = new EntityInsertionAdapter<HRVManualEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `hrv_manual` (`mac`,`date_str`,`hrv`,`timestamp`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, HRVManualEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDateStr());
                }
                stmt.bindLong(3, value.getPressure());
                stmt.bindLong(4, value.getTimestamp());
            }
        };
        this.__deletionAdapterOfHRVManualEntity = new EntityDeletionOrUpdateAdapter<HRVManualEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `hrv_manual` WHERE `mac` = ? AND `timestamp` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, HRVManualEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                stmt.bindLong(2, value.getTimestamp());
            }
        };
        this.__updateAdapterOfHRVManualEntity = new EntityDeletionOrUpdateAdapter<HRVManualEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `hrv_manual` SET `mac` = ?,`date_str` = ?,`hrv` = ?,`timestamp` = ? WHERE `mac` = ? AND `timestamp` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, HRVManualEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDateStr());
                }
                stmt.bindLong(3, value.getPressure());
                stmt.bindLong(4, value.getTimestamp());
                if (value.getMac() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getMac());
                }
                stmt.bindLong(6, value.getTimestamp());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<HRVManualEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfHRVManualEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final HRVManualEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfHRVManualEntity.insert((EntityInsertionAdapter<HRVManualEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final HRVManualEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfHRVManualEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<HRVManualEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfHRVManualEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final HRVManualEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfHRVManualEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final HRVManualEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfHRVManualEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao
    public List<HRVManualEntity> queryByTimestamp(final String mac, final int start, final int end) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from hrv_manual where mac=? and timestamp >=? and timestamp <=?", 3);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        roomSQLiteQueryAcquire.bindLong(2, start);
        roomSQLiteQueryAcquire.bindLong(3, end);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hrv");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new HRVManualEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao
    public List<HRVManualEntity> queryDataLimit(final String mac, final String date) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from hrv_manual where mac=? and date_str=? order by timestamp desc LIMIT 7 ", 2);
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
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hrv");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new HRVManualEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao
    public List<HRVManualEntity> queryDataAll(final String mac, final String date) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from hrv_manual where mac=? and date_str=? order by timestamp desc ", 2);
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
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hrv");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new HRVManualEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao
    public HRVManualEntity queryDataLimitLast(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from hrv_manual where mac=? order by timestamp desc LIMIT 1 ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        HRVManualEntity hRVManualEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hrv");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP);
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                if (!cursorQuery.isNull(columnIndexOrThrow2)) {
                    string = cursorQuery.getString(columnIndexOrThrow2);
                }
                hRVManualEntity = new HRVManualEntity(string2, string, cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4));
            }
            return hRVManualEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao
    public HRVManualEntity queryByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from hrv_manual where mac=? and date_str =? LIMIT 1", 2);
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
        HRVManualEntity hRVManualEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hrv");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP);
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                if (!cursorQuery.isNull(columnIndexOrThrow2)) {
                    string = cursorQuery.getString(columnIndexOrThrow2);
                }
                hRVManualEntity = new HRVManualEntity(string2, string, cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4));
            }
            return hRVManualEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
