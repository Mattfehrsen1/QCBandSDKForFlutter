package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.fitness.data.Field;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepDetail;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcStepDetailDao_Impl implements QcStepDetailDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<StepDetail> __deletionAdapterOfStepDetail;
    private final EntityInsertionAdapter<StepDetail> __insertionAdapterOfStepDetail;
    private final EntityDeletionOrUpdateAdapter<StepDetail> __updateAdapterOfStepDetail;

    public QcStepDetailDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfStepDetail = new EntityInsertionAdapter<StepDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `step_detail` (`device_address`,`date_str`,`interval`,`total_active_time`,`index_str`,`counts`,`miles`,`calories`,`sync`,`last_sync_time`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, StepDetail stepDetail) {
                if (stepDetail.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, stepDetail.getDeviceAddress());
                }
                if (stepDetail.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, stepDetail.getDateStr());
                }
                supportSQLiteStatement.bindLong(3, stepDetail.getIntervar());
                supportSQLiteStatement.bindLong(4, stepDetail.getTotalActiveTime());
                if (stepDetail.getIndex_str() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, stepDetail.getIndex_str());
                }
                if (stepDetail.getCounts() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, stepDetail.getCounts());
                }
                if (stepDetail.getMiles() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, stepDetail.getMiles());
                }
                if (stepDetail.getCalories() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, stepDetail.getCalories());
                }
                supportSQLiteStatement.bindLong(9, stepDetail.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, stepDetail.getLastSyncTime());
            }
        };
        this.__deletionAdapterOfStepDetail = new EntityDeletionOrUpdateAdapter<StepDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `step_detail` WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, StepDetail value) {
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
        this.__updateAdapterOfStepDetail = new EntityDeletionOrUpdateAdapter<StepDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `step_detail` SET `device_address` = ?,`date_str` = ?,`interval` = ?,`total_active_time` = ?,`index_str` = ?,`counts` = ?,`miles` = ?,`calories` = ?,`sync` = ?,`last_sync_time` = ? WHERE `device_address` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, StepDetail stepDetail) {
                if (stepDetail.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, stepDetail.getDeviceAddress());
                }
                if (stepDetail.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, stepDetail.getDateStr());
                }
                supportSQLiteStatement.bindLong(3, stepDetail.getIntervar());
                supportSQLiteStatement.bindLong(4, stepDetail.getTotalActiveTime());
                if (stepDetail.getIndex_str() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, stepDetail.getIndex_str());
                }
                if (stepDetail.getCounts() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, stepDetail.getCounts());
                }
                if (stepDetail.getMiles() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, stepDetail.getMiles());
                }
                if (stepDetail.getCalories() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, stepDetail.getCalories());
                }
                supportSQLiteStatement.bindLong(9, stepDetail.getSync() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, stepDetail.getLastSyncTime());
                if (stepDetail.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, stepDetail.getDeviceAddress());
                }
                if (stepDetail.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, stepDetail.getDateStr());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<StepDetail> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfStepDetail.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final StepDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfStepDetail.insert((EntityInsertionAdapter<StepDetail>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final StepDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfStepDetail.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<StepDetail> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfStepDetail.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final StepDetail... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfStepDetail.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final StepDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfStepDetail.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao
    public List<StepDetail> queryLastSyncDate(final String deviceAddress) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from step_detail where  device_address=? order by date_str desc LIMIT 7 ", 1);
        if (deviceAddress == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, deviceAddress);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "total_active_time");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "index_str");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "counts");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "miles");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new StepDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getLong(columnIndexOrThrow10)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao
    public StepDetail queryByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from step_detail where device_address=? and date_str=? order by date_str desc limit 1", 2);
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str2);
        }
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        StepDetail stepDetail = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "total_active_time");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "index_str");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "counts");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "miles");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_time");
            if (cursorQuery.moveToFirst()) {
                stepDetail = new StepDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getLong(columnIndexOrThrow10));
            }
            return stepDetail;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao
    public List<StepDetail> queryBySync() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `step_detail`.`device_address` AS `device_address`, `step_detail`.`date_str` AS `date_str`, `step_detail`.`interval` AS `interval`, `step_detail`.`total_active_time` AS `total_active_time`, `step_detail`.`index_str` AS `index_str`, `step_detail`.`counts` AS `counts`, `step_detail`.`miles` AS `miles`, `step_detail`.`calories` AS `calories`, `step_detail`.`sync` AS `sync`, `step_detail`.`last_sync_time` AS `last_sync_time` from step_detail where sync= 0 ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new StepDetail(cursorQuery.isNull(0) ? null : cursorQuery.getString(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.getInt(2), cursorQuery.getInt(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : cursorQuery.getString(5), cursorQuery.isNull(6) ? null : cursorQuery.getString(6), cursorQuery.isNull(7) ? null : cursorQuery.getString(7), cursorQuery.getInt(8) != 0, cursorQuery.getLong(9)));
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
