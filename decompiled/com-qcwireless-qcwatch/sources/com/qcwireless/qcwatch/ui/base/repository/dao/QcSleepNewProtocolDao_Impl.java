package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepNewProtocol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcSleepNewProtocolDao_Impl implements QcSleepNewProtocolDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SleepNewProtocol> __deletionAdapterOfSleepNewProtocol;
    private final EntityInsertionAdapter<SleepNewProtocol> __insertionAdapterOfSleepNewProtocol;
    private final EntityDeletionOrUpdateAdapter<SleepNewProtocol> __updateAdapterOfSleepNewProtocol;

    public QcSleepNewProtocolDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSleepNewProtocol = new EntityInsertionAdapter<SleepNewProtocol>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `sleep_new_protocol` (`device_address`,`date_str`,`detail`,`st`,`et`,`sync`,`last_sync_time`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, SleepNewProtocol sleepNewProtocol) {
                if (sleepNewProtocol.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, sleepNewProtocol.getDeviceAddress());
                }
                if (sleepNewProtocol.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, sleepNewProtocol.getDateStr());
                }
                if (sleepNewProtocol.getDetail() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, sleepNewProtocol.getDetail());
                }
                supportSQLiteStatement.bindLong(4, sleepNewProtocol.getSt());
                supportSQLiteStatement.bindLong(5, sleepNewProtocol.getEt());
                supportSQLiteStatement.bindLong(6, sleepNewProtocol.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, sleepNewProtocol.getLastSyncTime());
            }
        };
        this.__deletionAdapterOfSleepNewProtocol = new EntityDeletionOrUpdateAdapter<SleepNewProtocol>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `sleep_new_protocol` WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SleepNewProtocol value) {
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
        this.__updateAdapterOfSleepNewProtocol = new EntityDeletionOrUpdateAdapter<SleepNewProtocol>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `sleep_new_protocol` SET `device_address` = ?,`date_str` = ?,`detail` = ?,`st` = ?,`et` = ?,`sync` = ?,`last_sync_time` = ? WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, SleepNewProtocol sleepNewProtocol) {
                if (sleepNewProtocol.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, sleepNewProtocol.getDeviceAddress());
                }
                if (sleepNewProtocol.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, sleepNewProtocol.getDateStr());
                }
                if (sleepNewProtocol.getDetail() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, sleepNewProtocol.getDetail());
                }
                supportSQLiteStatement.bindLong(4, sleepNewProtocol.getSt());
                supportSQLiteStatement.bindLong(5, sleepNewProtocol.getEt());
                supportSQLiteStatement.bindLong(6, sleepNewProtocol.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, sleepNewProtocol.getLastSyncTime());
                if (sleepNewProtocol.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, sleepNewProtocol.getDeviceAddress());
                }
                if (sleepNewProtocol.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, sleepNewProtocol.getDateStr());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<SleepNewProtocol> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSleepNewProtocol.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final SleepNewProtocol element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSleepNewProtocol.insert((EntityInsertionAdapter<SleepNewProtocol>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final SleepNewProtocol element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSleepNewProtocol.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<SleepNewProtocol> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSleepNewProtocol.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final SleepNewProtocol... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSleepNewProtocol.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final SleepNewProtocol element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSleepNewProtocol.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao
    public SleepNewProtocol queryByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sleep_new_protocol where date_str=? and device_address=? order by date_str desc limit 1", 2);
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
        SleepNewProtocol sleepNewProtocol = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "detail");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "st");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "et");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                sleepNewProtocol = new SleepNewProtocol(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getLong(columnIndexOrThrow7));
            }
            return sleepNewProtocol;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao
    public SleepNewProtocol queryLastSyncDate(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sleep_new_protocol where  device_address=? order by date_str desc LIMIT 1 ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SleepNewProtocol sleepNewProtocol = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "detail");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "st");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "et");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                sleepNewProtocol = new SleepNewProtocol(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getLong(columnIndexOrThrow7));
            }
            return sleepNewProtocol;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao
    public List<SleepNewProtocol> queryBySync() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `sleep_new_protocol`.`device_address` AS `device_address`, `sleep_new_protocol`.`date_str` AS `date_str`, `sleep_new_protocol`.`detail` AS `detail`, `sleep_new_protocol`.`st` AS `st`, `sleep_new_protocol`.`et` AS `et`, `sleep_new_protocol`.`sync` AS `sync`, `sleep_new_protocol`.`last_sync_time` AS `last_sync_time` from sleep_new_protocol where sync= 0 ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SleepNewProtocol(cursorQuery.isNull(0) ? null : cursorQuery.getString(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2), cursorQuery.getInt(3), cursorQuery.getInt(4), cursorQuery.getInt(5) != 0, cursorQuery.getLong(6)));
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
