package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepTotalHistory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcSleepTotalDao_Impl implements QcSleepTotalDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SleepTotalHistory> __deletionAdapterOfSleepTotalHistory;
    private final EntityInsertionAdapter<SleepTotalHistory> __insertionAdapterOfSleepTotalHistory;
    private final EntityDeletionOrUpdateAdapter<SleepTotalHistory> __updateAdapterOfSleepTotalHistory;

    public QcSleepTotalDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSleepTotalHistory = new EntityInsertionAdapter<SleepTotalHistory>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepTotalDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `sleep_total` (`device_address`,`date_str`,`total_sleep`,`deep_sleep`,`light_sleep`,`rapid_sleep`,`awake`,`start_time`,`end_time`,`unix_time`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SleepTotalHistory value) {
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
                stmt.bindLong(3, value.getTotalSleep());
                stmt.bindLong(4, value.getDeepSleep());
                stmt.bindLong(5, value.getLightSleep());
                stmt.bindLong(6, value.getRapidSleep());
                stmt.bindLong(7, value.getAwake());
                stmt.bindLong(8, value.getStartTime());
                stmt.bindLong(9, value.getEndTime());
                stmt.bindLong(10, value.getUnixTime());
            }
        };
        this.__deletionAdapterOfSleepTotalHistory = new EntityDeletionOrUpdateAdapter<SleepTotalHistory>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepTotalDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `sleep_total` WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SleepTotalHistory value) {
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
        this.__updateAdapterOfSleepTotalHistory = new EntityDeletionOrUpdateAdapter<SleepTotalHistory>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepTotalDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `sleep_total` SET `device_address` = ?,`date_str` = ?,`total_sleep` = ?,`deep_sleep` = ?,`light_sleep` = ?,`rapid_sleep` = ?,`awake` = ?,`start_time` = ?,`end_time` = ?,`unix_time` = ? WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SleepTotalHistory value) {
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
                stmt.bindLong(3, value.getTotalSleep());
                stmt.bindLong(4, value.getDeepSleep());
                stmt.bindLong(5, value.getLightSleep());
                stmt.bindLong(6, value.getRapidSleep());
                stmt.bindLong(7, value.getAwake());
                stmt.bindLong(8, value.getStartTime());
                stmt.bindLong(9, value.getEndTime());
                stmt.bindLong(10, value.getUnixTime());
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getDeviceAddress());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getDateStr());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<SleepTotalHistory> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSleepTotalHistory.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final SleepTotalHistory element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSleepTotalHistory.insert((EntityInsertionAdapter<SleepTotalHistory>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final SleepTotalHistory element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSleepTotalHistory.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<SleepTotalHistory> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSleepTotalHistory.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final SleepTotalHistory... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSleepTotalHistory.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final SleepTotalHistory element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSleepTotalHistory.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepTotalDao
    public SleepTotalHistory queryTotalSleepByAddressAndDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sleep_total where  device_address=? and date_str=? order by date_str desc LIMIT 1 ", 2);
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
        SleepTotalHistory sleepTotalHistory = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "total_sleep");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "deep_sleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "light_sleep");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rapid_sleep");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "awake");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "end_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            if (cursorQuery.moveToFirst()) {
                sleepTotalHistory = new SleepTotalHistory(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10));
            }
            return sleepTotalHistory;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepTotalDao
    public List<SleepTotalHistory> queryByAddressAndDate(final String deviceAddress, final String start, final String end) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sleep_total where device_address=? and unix_time >= ? and unix_time<=? order by unix_time asc", 3);
        if (deviceAddress == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, deviceAddress);
        }
        if (start == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, start);
        }
        if (end == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, end);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "total_sleep");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "deep_sleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "light_sleep");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rapid_sleep");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "awake");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "end_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "unix_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SleepTotalHistory(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.getInt(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10)));
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
