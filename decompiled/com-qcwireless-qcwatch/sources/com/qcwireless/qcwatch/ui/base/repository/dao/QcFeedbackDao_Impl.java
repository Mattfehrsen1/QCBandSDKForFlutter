package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.FeedbackEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcFeedbackDao_Impl implements QcFeedbackDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<FeedbackEntity> __deletionAdapterOfFeedbackEntity;
    private final EntityInsertionAdapter<FeedbackEntity> __insertionAdapterOfFeedbackEntity;
    private final EntityDeletionOrUpdateAdapter<FeedbackEntity> __updateAdapterOfFeedbackEntity;

    public QcFeedbackDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFeedbackEntity = new EntityInsertionAdapter<FeedbackEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcFeedbackDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `feedback` (`type_id`,`feedback_id`,`content`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FeedbackEntity value) {
                stmt.bindLong(1, value.getTypeId());
                stmt.bindLong(2, value.getFeedbackId());
                if (value.getContent() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContent());
                }
            }
        };
        this.__deletionAdapterOfFeedbackEntity = new EntityDeletionOrUpdateAdapter<FeedbackEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcFeedbackDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `feedback` WHERE `type_id` = ? AND `feedback_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, FeedbackEntity value) {
                stmt.bindLong(1, value.getTypeId());
                stmt.bindLong(2, value.getFeedbackId());
            }
        };
        this.__updateAdapterOfFeedbackEntity = new EntityDeletionOrUpdateAdapter<FeedbackEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcFeedbackDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `feedback` SET `type_id` = ?,`feedback_id` = ?,`content` = ? WHERE `type_id` = ? AND `feedback_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, FeedbackEntity value) {
                stmt.bindLong(1, value.getTypeId());
                stmt.bindLong(2, value.getFeedbackId());
                if (value.getContent() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContent());
                }
                stmt.bindLong(4, value.getTypeId());
                stmt.bindLong(5, value.getFeedbackId());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<FeedbackEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFeedbackEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final FeedbackEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFeedbackEntity.insert((EntityInsertionAdapter<FeedbackEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final FeedbackEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfFeedbackEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<FeedbackEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfFeedbackEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final FeedbackEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfFeedbackEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final FeedbackEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfFeedbackEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcFeedbackDao
    public List<FeedbackEntity> queryFeedbackList() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `feedback`.`type_id` AS `type_id`, `feedback`.`feedback_id` AS `feedback_id`, `feedback`.`content` AS `content` from feedback ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new FeedbackEntity(cursorQuery.getInt(0), cursorQuery.getInt(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2)));
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
