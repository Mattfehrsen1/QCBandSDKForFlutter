package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.fitness.data.Field;
import com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcSportPlusDetailDao_Impl implements QcSportPlusDetailDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SportPlusDetail> __deletionAdapterOfSportPlusDetail;
    private final EntityInsertionAdapter<SportPlusDetail> __insertionAdapterOfSportPlusDetail;
    private final EntityDeletionOrUpdateAdapter<SportPlusDetail> __updateAdapterOfSportPlusDetail;

    public QcSportPlusDetailDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSportPlusDetail = new EntityInsertionAdapter<SportPlusDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `sport_plus_detail` (`device_address`,`date_str`,`start_time`,`sport_type`,`duration`,`distance`,`calories`,`steps`,`rate_value`,`avg_rate`,`sync`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, SportPlusDetail sportPlusDetail) {
                if (sportPlusDetail.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, sportPlusDetail.getDeviceAddress());
                }
                if (sportPlusDetail.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, sportPlusDetail.getDateStr());
                }
                supportSQLiteStatement.bindLong(3, sportPlusDetail.getStartTime());
                supportSQLiteStatement.bindLong(4, sportPlusDetail.getSportType());
                supportSQLiteStatement.bindLong(5, sportPlusDetail.getDuration());
                supportSQLiteStatement.bindDouble(6, sportPlusDetail.getDistance());
                supportSQLiteStatement.bindDouble(7, sportPlusDetail.getCalories());
                supportSQLiteStatement.bindLong(8, sportPlusDetail.getSteps());
                if (sportPlusDetail.getRateValue() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, sportPlusDetail.getRateValue());
                }
                supportSQLiteStatement.bindLong(10, sportPlusDetail.getAvgRate());
                supportSQLiteStatement.bindLong(11, sportPlusDetail.getSync() ? 1L : 0L);
            }
        };
        this.__deletionAdapterOfSportPlusDetail = new EntityDeletionOrUpdateAdapter<SportPlusDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `sport_plus_detail` WHERE `device_address` = ? AND `start_time` = ? AND `sport_type` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SportPlusDetail value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                stmt.bindLong(2, value.getStartTime());
                stmt.bindLong(3, value.getSportType());
            }
        };
        this.__updateAdapterOfSportPlusDetail = new EntityDeletionOrUpdateAdapter<SportPlusDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `sport_plus_detail` SET `device_address` = ?,`date_str` = ?,`start_time` = ?,`sport_type` = ?,`duration` = ?,`distance` = ?,`calories` = ?,`steps` = ?,`rate_value` = ?,`avg_rate` = ?,`sync` = ? WHERE `device_address` = ? AND `start_time` = ? AND `sport_type` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, SportPlusDetail sportPlusDetail) {
                if (sportPlusDetail.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, sportPlusDetail.getDeviceAddress());
                }
                if (sportPlusDetail.getDateStr() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, sportPlusDetail.getDateStr());
                }
                supportSQLiteStatement.bindLong(3, sportPlusDetail.getStartTime());
                supportSQLiteStatement.bindLong(4, sportPlusDetail.getSportType());
                supportSQLiteStatement.bindLong(5, sportPlusDetail.getDuration());
                supportSQLiteStatement.bindDouble(6, sportPlusDetail.getDistance());
                supportSQLiteStatement.bindDouble(7, sportPlusDetail.getCalories());
                supportSQLiteStatement.bindLong(8, sportPlusDetail.getSteps());
                if (sportPlusDetail.getRateValue() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, sportPlusDetail.getRateValue());
                }
                supportSQLiteStatement.bindLong(10, sportPlusDetail.getAvgRate());
                supportSQLiteStatement.bindLong(11, sportPlusDetail.getSync() ? 1L : 0L);
                if (sportPlusDetail.getDeviceAddress() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, sportPlusDetail.getDeviceAddress());
                }
                supportSQLiteStatement.bindLong(13, sportPlusDetail.getStartTime());
                supportSQLiteStatement.bindLong(14, sportPlusDetail.getSportType());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<SportPlusDetail> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSportPlusDetail.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final SportPlusDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSportPlusDetail.insert((EntityInsertionAdapter<SportPlusDetail>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final SportPlusDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSportPlusDetail.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<SportPlusDetail> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSportPlusDetail.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final SportPlusDetail... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSportPlusDetail.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final SportPlusDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSportPlusDetail.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao
    public List<SportPlusDetail> queryByDate(final String dateStr, final String deviceAddress) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sport_plus_detail where date_str=? and device_address=? ", 2);
        if (dateStr == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, dateStr);
        }
        if (deviceAddress == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, deviceAddress);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sport_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "steps");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rate_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avg_rate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SportPlusDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getFloat(columnIndexOrThrow6), cursorQuery.getFloat(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao
    public List<SportPlusDetail> querySportPlusByStartTime(final String device, final long start, final long end) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sport_plus_detail where device_address=? and start_time>=? and start_time <=? order by start_time desc", 3);
        if (device == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, device);
        }
        roomSQLiteQueryAcquire.bindLong(2, start);
        roomSQLiteQueryAcquire.bindLong(3, end);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sport_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "steps");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rate_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avg_rate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SportPlusDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getFloat(columnIndexOrThrow6), cursorQuery.getFloat(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao
    public SportPlusDetail queryLastSyncDate(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sport_plus_detail where device_address=? order by date_str desc LIMIT 1 ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SportPlusDetail sportPlusDetail = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sport_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "steps");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rate_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avg_rate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            if (cursorQuery.moveToFirst()) {
                sportPlusDetail = new SportPlusDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getFloat(columnIndexOrThrow6), cursorQuery.getFloat(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0);
            }
            return sportPlusDetail;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao
    public List<SportPlusDetail> queryBySync() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `sport_plus_detail`.`device_address` AS `device_address`, `sport_plus_detail`.`date_str` AS `date_str`, `sport_plus_detail`.`start_time` AS `start_time`, `sport_plus_detail`.`sport_type` AS `sport_type`, `sport_plus_detail`.`duration` AS `duration`, `sport_plus_detail`.`distance` AS `distance`, `sport_plus_detail`.`calories` AS `calories`, `sport_plus_detail`.`steps` AS `steps`, `sport_plus_detail`.`rate_value` AS `rate_value`, `sport_plus_detail`.`avg_rate` AS `avg_rate`, `sport_plus_detail`.`sync` AS `sync` from sport_plus_detail where sync= 0 ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SportPlusDetail(cursorQuery.isNull(0) ? null : cursorQuery.getString(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.getLong(2), cursorQuery.getInt(3), cursorQuery.getInt(4), cursorQuery.getFloat(5), cursorQuery.getFloat(6), cursorQuery.getInt(7), cursorQuery.isNull(8) ? null : cursorQuery.getString(8), cursorQuery.getInt(9), cursorQuery.getInt(10) != 0));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao
    public SportPlusDetail querySportByStartTimeAndType(String str, int i, long j) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sport_plus_detail where device_address=? and sport_type=? and start_time=?  LIMIT 1 ", 3);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        roomSQLiteQueryAcquire.bindLong(2, i);
        roomSQLiteQueryAcquire.bindLong(3, j);
        this.__db.assertNotSuspendingTransaction();
        SportPlusDetail sportPlusDetail = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sport_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "steps");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rate_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avg_rate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            if (cursorQuery.moveToFirst()) {
                sportPlusDetail = new SportPlusDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getFloat(columnIndexOrThrow6), cursorQuery.getFloat(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0);
            }
            return sportPlusDetail;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao
    public SportPlusDetail queryByAddressOrderByStartTime(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sport_plus_detail where device_address=? order by start_time desc LIMIT 1 ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SportPlusDetail sportPlusDetail = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sport_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Field.NUTRIENT_CALORIES);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "steps");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rate_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avg_rate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sync");
            if (cursorQuery.moveToFirst()) {
                sportPlusDetail = new SportPlusDetail(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getFloat(columnIndexOrThrow6), cursorQuery.getFloat(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0);
            }
            return sportPlusDetail;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
