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
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcGpsDetailDao_Impl implements QcGpsDetailDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<GpsDetail> __deletionAdapterOfGpsDetail;
    private final EntityInsertionAdapter<GpsDetail> __insertionAdapterOfGpsDetail;
    private final EntityDeletionOrUpdateAdapter<GpsDetail> __updateAdapterOfGpsDetail;

    public QcGpsDetailDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfGpsDetail = new EntityInsertionAdapter<GpsDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `gps_detail` (`start_time`,`duration`,`distance`,`calorie`,`locations`,`date_str`) VALUES (?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, GpsDetail value) {
                stmt.bindLong(1, value.getStartTime());
                stmt.bindLong(2, value.getDuration());
                stmt.bindDouble(3, value.getDistance());
                stmt.bindDouble(4, value.getCalorie());
                if (value.getLocations() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getLocations());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getDateStr());
                }
            }
        };
        this.__deletionAdapterOfGpsDetail = new EntityDeletionOrUpdateAdapter<GpsDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `gps_detail` WHERE `start_time` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, GpsDetail value) {
                stmt.bindLong(1, value.getStartTime());
            }
        };
        this.__updateAdapterOfGpsDetail = new EntityDeletionOrUpdateAdapter<GpsDetail>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `gps_detail` SET `start_time` = ?,`duration` = ?,`distance` = ?,`calorie` = ?,`locations` = ?,`date_str` = ? WHERE `start_time` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, GpsDetail value) {
                stmt.bindLong(1, value.getStartTime());
                stmt.bindLong(2, value.getDuration());
                stmt.bindDouble(3, value.getDistance());
                stmt.bindDouble(4, value.getCalorie());
                if (value.getLocations() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getLocations());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getDateStr());
                }
                stmt.bindLong(7, value.getStartTime());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<GpsDetail> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfGpsDetail.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final GpsDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfGpsDetail.insert((EntityInsertionAdapter<GpsDetail>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final GpsDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfGpsDetail.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<GpsDetail> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfGpsDetail.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final GpsDetail... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfGpsDetail.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final GpsDetail element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfGpsDetail.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao
    public List<GpsDetail> queryListByDate(final String date) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from gps_detail where date_str=? order by start_time desc", 1);
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, date);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "calorie");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "locations");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new GpsDetail(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getInt(columnIndexOrThrow2), cursorQuery.getFloat(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao
    public GpsDetail queryFirstByStartTime() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `gps_detail`.`start_time` AS `start_time`, `gps_detail`.`duration` AS `duration`, `gps_detail`.`distance` AS `distance`, `gps_detail`.`calorie` AS `calorie`, `gps_detail`.`locations` AS `locations`, `gps_detail`.`date_str` AS `date_str` from gps_detail order by start_time desc limit 1", 0);
        this.__db.assertNotSuspendingTransaction();
        GpsDetail gpsDetail = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            if (cursorQuery.moveToFirst()) {
                gpsDetail = new GpsDetail(cursorQuery.getLong(0), cursorQuery.getInt(1), cursorQuery.getFloat(2), cursorQuery.getFloat(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : cursorQuery.getString(5));
            }
            return gpsDetail;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao
    public List<GpsDetail> queryAll() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `gps_detail`.`start_time` AS `start_time`, `gps_detail`.`duration` AS `duration`, `gps_detail`.`distance` AS `distance`, `gps_detail`.`calorie` AS `calorie`, `gps_detail`.`locations` AS `locations`, `gps_detail`.`date_str` AS `date_str` from gps_detail where start_time > 0 ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new GpsDetail(cursorQuery.getLong(0), cursorQuery.getInt(1), cursorQuery.getFloat(2), cursorQuery.getFloat(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : cursorQuery.getString(5)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao
    public GpsDetail queryByStartTime(long j) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from gps_detail where start_time=? limit 1", 1);
        roomSQLiteQueryAcquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        GpsDetail gpsDetail = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "distance");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "calorie");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "locations");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            if (cursorQuery.moveToFirst()) {
                gpsDetail = new GpsDetail(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getInt(columnIndexOrThrow2), cursorQuery.getFloat(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
            }
            return gpsDetail;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
