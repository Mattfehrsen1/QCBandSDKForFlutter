package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.MenstruationEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcMenstruationDao_Impl implements QcMenstruationDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<MenstruationEntity> __deletionAdapterOfMenstruationEntity;
    private final EntityInsertionAdapter<MenstruationEntity> __insertionAdapterOfMenstruationEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final EntityDeletionOrUpdateAdapter<MenstruationEntity> __updateAdapterOfMenstruationEntity;

    public QcMenstruationDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMenstruationEntity = new EntityInsertionAdapter<MenstruationEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `menstruation` (`mid`,`month_date`,`year`,`month`,`start_time`,`end_time`,`duration`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, MenstruationEntity value) {
                stmt.bindLong(1, value.getMid());
                if (value.getMonthDate() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getMonthDate());
                }
                stmt.bindLong(3, value.getYear());
                stmt.bindLong(4, value.getMonth());
                stmt.bindLong(5, value.getStartTime());
                stmt.bindLong(6, value.getEndTime());
                stmt.bindLong(7, value.getDuration());
            }
        };
        this.__deletionAdapterOfMenstruationEntity = new EntityDeletionOrUpdateAdapter<MenstruationEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `menstruation` WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, MenstruationEntity value) {
                stmt.bindLong(1, value.getMid());
            }
        };
        this.__updateAdapterOfMenstruationEntity = new EntityDeletionOrUpdateAdapter<MenstruationEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `menstruation` SET `mid` = ?,`month_date` = ?,`year` = ?,`month` = ?,`start_time` = ?,`end_time` = ?,`duration` = ? WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, MenstruationEntity value) {
                stmt.bindLong(1, value.getMid());
                if (value.getMonthDate() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getMonthDate());
                }
                stmt.bindLong(3, value.getYear());
                stmt.bindLong(4, value.getMonth());
                stmt.bindLong(5, value.getStartTime());
                stmt.bindLong(6, value.getEndTime());
                stmt.bindLong(7, value.getDuration());
                stmt.bindLong(8, value.getMid());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from menstruation";
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<MenstruationEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMenstruationEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final MenstruationEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMenstruationEntity.insert((EntityInsertionAdapter<MenstruationEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final MenstruationEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMenstruationEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<MenstruationEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMenstruationEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final MenstruationEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMenstruationEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final MenstruationEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMenstruationEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao
    public List<MenstruationEntity> queryMenstruationByYearAndMonth(final int year, final int month) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from menstruation where year=? and month=? order by start_time  asc ", 2);
        roomSQLiteQueryAcquire.bindLong(1, year);
        roomSQLiteQueryAcquire.bindLong(2, month);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "month_date");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "year");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "month");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "start_time");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "end_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                MenstruationEntity menstruationEntity = new MenstruationEntity(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7));
                menstruationEntity.setMid(cursorQuery.getInt(columnIndexOrThrow));
                arrayList.add(menstruationEntity);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao
    public List<MenstruationEntity> queryAll() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `menstruation`.`mid` AS `mid`, `menstruation`.`month_date` AS `month_date`, `menstruation`.`year` AS `year`, `menstruation`.`month` AS `month`, `menstruation`.`start_time` AS `start_time`, `menstruation`.`end_time` AS `end_time`, `menstruation`.`duration` AS `duration` from menstruation", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                MenstruationEntity menstruationEntity = new MenstruationEntity(cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.getInt(2), cursorQuery.getInt(3), cursorQuery.getLong(4), cursorQuery.getLong(5), cursorQuery.getLong(6));
                menstruationEntity.setMid(cursorQuery.getInt(0));
                arrayList.add(menstruationEntity);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao
    public MenstruationEntity queryMaxByStartTime() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `menstruation`.`mid` AS `mid`, `menstruation`.`month_date` AS `month_date`, `menstruation`.`year` AS `year`, `menstruation`.`month` AS `month`, `menstruation`.`start_time` AS `start_time`, `menstruation`.`end_time` AS `end_time`, `menstruation`.`duration` AS `duration` from menstruation order by start_time desc LIMIT 1 ", 0);
        this.__db.assertNotSuspendingTransaction();
        MenstruationEntity menstruationEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            if (cursorQuery.moveToFirst()) {
                menstruationEntity = new MenstruationEntity(cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.getInt(2), cursorQuery.getInt(3), cursorQuery.getLong(4), cursorQuery.getLong(5), cursorQuery.getLong(6));
                menstruationEntity.setMid(cursorQuery.getInt(0));
            }
            return menstruationEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
