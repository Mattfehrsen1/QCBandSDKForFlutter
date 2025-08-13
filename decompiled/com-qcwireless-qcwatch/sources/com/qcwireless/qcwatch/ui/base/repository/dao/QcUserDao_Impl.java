package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcUserDao_Impl implements QcUserDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<UserEntity> __deletionAdapterOfUserEntity;
    private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;
    private final EntityDeletionOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

    public QcUserDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcUserDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `user` (`uid`,`email`,`nick`,`gender`,`weight`,`weight_lbs`,`height`,`birthday`,`avatar_url`,`local_avatar_url`,`goal_steps`,`goal_calorie`,`goal_distance`,`goal_sport_time`,`goal_sleep_time`,`register_date`,`update`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, UserEntity value) {
                stmt.bindLong(1, value.getTitle());
                if (value.getEmail() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getEmail());
                }
                if (value.getNickName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getNickName());
                }
                stmt.bindLong(4, value.getGender());
                stmt.bindDouble(5, value.getWeight());
                stmt.bindLong(6, value.getWeightLbs());
                stmt.bindDouble(7, value.getHeight());
                if (value.getBirthday() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getBirthday());
                }
                if (value.getAvatarUrl() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getAvatarUrl());
                }
                if (value.getLocalAvatarUrl() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getLocalAvatarUrl());
                }
                stmt.bindLong(11, value.getGoalSteps());
                stmt.bindDouble(12, value.getGoalCalorie());
                stmt.bindDouble(13, value.getGoalDistance());
                stmt.bindDouble(14, value.getGoalSportTime());
                stmt.bindDouble(15, value.getGoalSleepTime());
                if (value.getRegisterDate() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getRegisterDate());
                }
                stmt.bindLong(17, value.getUpdate());
            }
        };
        this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcUserDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `user` WHERE `uid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, UserEntity value) {
                stmt.bindLong(1, value.getTitle());
            }
        };
        this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcUserDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `user` SET `uid` = ?,`email` = ?,`nick` = ?,`gender` = ?,`weight` = ?,`weight_lbs` = ?,`height` = ?,`birthday` = ?,`avatar_url` = ?,`local_avatar_url` = ?,`goal_steps` = ?,`goal_calorie` = ?,`goal_distance` = ?,`goal_sport_time` = ?,`goal_sleep_time` = ?,`register_date` = ?,`update` = ? WHERE `uid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, UserEntity value) {
                stmt.bindLong(1, value.getTitle());
                if (value.getEmail() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getEmail());
                }
                if (value.getNickName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getNickName());
                }
                stmt.bindLong(4, value.getGender());
                stmt.bindDouble(5, value.getWeight());
                stmt.bindLong(6, value.getWeightLbs());
                stmt.bindDouble(7, value.getHeight());
                if (value.getBirthday() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getBirthday());
                }
                if (value.getAvatarUrl() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getAvatarUrl());
                }
                if (value.getLocalAvatarUrl() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getLocalAvatarUrl());
                }
                stmt.bindLong(11, value.getGoalSteps());
                stmt.bindDouble(12, value.getGoalCalorie());
                stmt.bindDouble(13, value.getGoalDistance());
                stmt.bindDouble(14, value.getGoalSportTime());
                stmt.bindDouble(15, value.getGoalSleepTime());
                if (value.getRegisterDate() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getRegisterDate());
                }
                stmt.bindLong(17, value.getUpdate());
                stmt.bindLong(18, value.getTitle());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<UserEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUserEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final UserEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUserEntity.insert((EntityInsertionAdapter<UserEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final UserEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUserEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<UserEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUserEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final UserEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUserEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final UserEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfUserEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcUserDao
    public UserEntity queryUserByUid(final long uid) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        UserEntity userEntity;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from user where  uid=? ", 1);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "email");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "nick");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "gender");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "weight");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "weight_lbs");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "height");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "birthday");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avatar_url");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_avatar_url");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_steps");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_calorie");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_distance");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_sport_time");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "goal_sleep_time");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "register_date");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "update");
            if (cursorQuery.moveToFirst()) {
                userEntity = new UserEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getFloat(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.getFloat(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11), cursorQuery.getFloat(columnIndexOrThrow12), cursorQuery.getFloat(columnIndexOrThrow13), cursorQuery.getFloat(columnIndexOrThrow14), cursorQuery.getFloat(columnIndexOrThrow15), cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16), cursorQuery.getInt(columnIndexOrThrow17));
            } else {
                userEntity = null;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return userEntity;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
