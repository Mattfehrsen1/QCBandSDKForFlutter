package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.SyncDataEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcSyncDao_Impl implements QcSyncDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SyncDataEntity> __deletionAdapterOfSyncDataEntity;
    private final EntityInsertionAdapter<SyncDataEntity> __insertionAdapterOfSyncDataEntity;
    private final EntityDeletionOrUpdateAdapter<SyncDataEntity> __updateAdapterOfSyncDataEntity;

    public QcSyncDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSyncDataEntity = new EntityInsertionAdapter<SyncDataEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSyncDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `sync_entity` (`uid`,`data_action`,`last_sync_id`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SyncDataEntity value) {
                stmt.bindLong(1, value.getUid());
                if (value.getAction() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getAction());
                }
                stmt.bindLong(3, value.getLastSyncTime());
            }
        };
        this.__deletionAdapterOfSyncDataEntity = new EntityDeletionOrUpdateAdapter<SyncDataEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSyncDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `sync_entity` WHERE `uid` = ? AND `data_action` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SyncDataEntity value) {
                stmt.bindLong(1, value.getUid());
                if (value.getAction() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getAction());
                }
            }
        };
        this.__updateAdapterOfSyncDataEntity = new EntityDeletionOrUpdateAdapter<SyncDataEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcSyncDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `sync_entity` SET `uid` = ?,`data_action` = ?,`last_sync_id` = ? WHERE `uid` = ? AND `data_action` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SyncDataEntity value) {
                stmt.bindLong(1, value.getUid());
                if (value.getAction() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getAction());
                }
                stmt.bindLong(3, value.getLastSyncTime());
                stmt.bindLong(4, value.getUid());
                if (value.getAction() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getAction());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<SyncDataEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSyncDataEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final SyncDataEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSyncDataEntity.insert((EntityInsertionAdapter<SyncDataEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final SyncDataEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSyncDataEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<SyncDataEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSyncDataEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final SyncDataEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSyncDataEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final SyncDataEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSyncDataEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcSyncDao
    public SyncDataEntity queryByUidAndAction(long j, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from sync_entity where uid= ? and data_action=? ", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SyncDataEntity syncDataEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data_action");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "last_sync_id");
            if (cursorQuery.moveToFirst()) {
                syncDataEntity = new SyncDataEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3));
            }
            return syncDataEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
