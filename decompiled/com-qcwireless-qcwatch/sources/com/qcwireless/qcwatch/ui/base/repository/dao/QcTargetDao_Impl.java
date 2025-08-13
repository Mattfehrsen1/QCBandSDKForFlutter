package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcTargetDao_Impl implements QcTargetDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<TargetEntity> __deletionAdapterOfTargetEntity;
    private final EntityInsertionAdapter<TargetEntity> __insertionAdapterOfTargetEntity;
    private final EntityDeletionOrUpdateAdapter<TargetEntity> __updateAdapterOfTargetEntity;

    public QcTargetDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTargetEntity = new EntityInsertionAdapter<TargetEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcTargetDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `target_entity` (`device_address`,`goal_steps`,`goal_calorie`,`goal_distance`,`goal_sport_time`,`goal_sleep_time`) VALUES (?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, TargetEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                stmt.bindLong(2, value.getGoalSteps());
                stmt.bindDouble(3, value.getGoalCalorie());
                stmt.bindDouble(4, value.getGoalDistance());
                stmt.bindDouble(5, value.getGoalSportTime());
                stmt.bindDouble(6, value.getGoalSleepTime());
            }
        };
        this.__deletionAdapterOfTargetEntity = new EntityDeletionOrUpdateAdapter<TargetEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcTargetDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `target_entity` WHERE `device_address` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, TargetEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
            }
        };
        this.__updateAdapterOfTargetEntity = new EntityDeletionOrUpdateAdapter<TargetEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcTargetDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `target_entity` SET `device_address` = ?,`goal_steps` = ?,`goal_calorie` = ?,`goal_distance` = ?,`goal_sport_time` = ?,`goal_sleep_time` = ? WHERE `device_address` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, TargetEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                stmt.bindLong(2, value.getGoalSteps());
                stmt.bindDouble(3, value.getGoalCalorie());
                stmt.bindDouble(4, value.getGoalDistance());
                stmt.bindDouble(5, value.getGoalSportTime());
                stmt.bindDouble(6, value.getGoalSleepTime());
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getDeviceAddress());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<TargetEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTargetEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final TargetEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTargetEntity.insert((EntityInsertionAdapter<TargetEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final TargetEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTargetEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<TargetEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTargetEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final TargetEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTargetEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final TargetEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTargetEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcTargetDao
    public TargetEntity queryTarget(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from target_entity where  device_address=? ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        TargetEntity targetEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_steps");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_calorie");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_distance");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_sport_time");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_sleep_time");
            if (cursorQuery.moveToFirst()) {
                targetEntity = new TargetEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getInt(columnIndexOrThrow2), cursorQuery.getFloat(columnIndexOrThrow3), cursorQuery.getFloat(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.getFloat(columnIndexOrThrow6));
            }
            return targetEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
