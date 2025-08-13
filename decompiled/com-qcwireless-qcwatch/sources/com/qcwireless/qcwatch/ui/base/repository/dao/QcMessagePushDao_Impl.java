package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcMessagePushDao_Impl implements QcMessagePushDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<MessagePushEntity> __deletionAdapterOfMessagePushEntity;
    private final EntityInsertionAdapter<MessagePushEntity> __insertionAdapterOfMessagePushEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByPackageName;
    private final EntityDeletionOrUpdateAdapter<MessagePushEntity> __updateAdapterOfMessagePushEntity;

    public QcMessagePushDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMessagePushEntity = new EntityInsertionAdapter<MessagePushEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `message_push` (`package_name`,`open`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, MessagePushEntity value) {
                if (value.getPackageName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getPackageName());
                }
                stmt.bindLong(2, value.getOpen());
            }
        };
        this.__deletionAdapterOfMessagePushEntity = new EntityDeletionOrUpdateAdapter<MessagePushEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `message_push` WHERE `package_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, MessagePushEntity value) {
                if (value.getPackageName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getPackageName());
                }
            }
        };
        this.__updateAdapterOfMessagePushEntity = new EntityDeletionOrUpdateAdapter<MessagePushEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `message_push` SET `package_name` = ?,`open` = ? WHERE `package_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, MessagePushEntity value) {
                if (value.getPackageName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getPackageName());
                }
                stmt.bindLong(2, value.getOpen());
                if (value.getPackageName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPackageName());
                }
            }
        };
        this.__preparedStmtOfDeleteByPackageName = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from message_push where package_name=?";
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<MessagePushEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMessagePushEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final MessagePushEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMessagePushEntity.insert((EntityInsertionAdapter<MessagePushEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final MessagePushEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMessagePushEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<MessagePushEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMessagePushEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final MessagePushEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMessagePushEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final MessagePushEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMessagePushEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao
    public void deleteByPackageName(final String name) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByPackageName.acquire();
        if (name == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, name);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByPackageName.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao
    public MessagePushEntity queryByName(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from message_push where  package_name=? ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        MessagePushEntity messagePushEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "package_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "open");
            if (cursorQuery.moveToFirst()) {
                if (!cursorQuery.isNull(columnIndexOrThrow)) {
                    string = cursorQuery.getString(columnIndexOrThrow);
                }
                messagePushEntity = new MessagePushEntity(string, cursorQuery.getInt(columnIndexOrThrow2));
            }
            return messagePushEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao
    public MessagePushEntity queryByNameAndOpen(String str, int i) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from message_push where  package_name=? and open<?", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        roomSQLiteQueryAcquire.bindLong(2, i);
        this.__db.assertNotSuspendingTransaction();
        MessagePushEntity messagePushEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "package_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "open");
            if (cursorQuery.moveToFirst()) {
                if (!cursorQuery.isNull(columnIndexOrThrow)) {
                    string = cursorQuery.getString(columnIndexOrThrow);
                }
                messagePushEntity = new MessagePushEntity(string, cursorQuery.getInt(columnIndexOrThrow2));
            }
            return messagePushEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao
    public List<MessagePushEntity> queryByOpen(final int open) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from message_push where open<?", 1);
        roomSQLiteQueryAcquire.bindLong(1, open);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "package_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "open");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new MessagePushEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getInt(columnIndexOrThrow2)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao
    public List<MessagePushEntity> queryByStatus() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `message_push`.`package_name` AS `package_name`, `message_push`.`open` AS `open` from message_push where open =1 ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new MessagePushEntity(cursorQuery.isNull(0) ? null : cursorQuery.getString(0), cursorQuery.getInt(1)));
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
