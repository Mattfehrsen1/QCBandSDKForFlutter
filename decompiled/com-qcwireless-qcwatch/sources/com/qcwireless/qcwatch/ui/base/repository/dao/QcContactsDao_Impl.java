package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcContactsDao_Impl implements QcContactsDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<ContactsEntity> __deletionAdapterOfContactsEntity;
    private final EntityInsertionAdapter<ContactsEntity> __insertionAdapterOfContactsEntity;
    private final EntityDeletionOrUpdateAdapter<ContactsEntity> __updateAdapterOfContactsEntity;

    public QcContactsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfContactsEntity = new EntityInsertionAdapter<ContactsEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcContactsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `contact_entity` (`mac`,`phone_number`,`contact_name`,`status`,`first_name`) VALUES (?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ContactsEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getPhoneNumber() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPhoneNumber());
                }
                if (value.getContactName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContactName());
                }
                stmt.bindLong(4, value.getStatus());
                if (value.getFirstName() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getFirstName());
                }
            }
        };
        this.__deletionAdapterOfContactsEntity = new EntityDeletionOrUpdateAdapter<ContactsEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcContactsDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `contact_entity` WHERE `mac` = ? AND `phone_number` = ? AND `contact_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ContactsEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getPhoneNumber() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPhoneNumber());
                }
                if (value.getContactName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContactName());
                }
            }
        };
        this.__updateAdapterOfContactsEntity = new EntityDeletionOrUpdateAdapter<ContactsEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcContactsDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `contact_entity` SET `mac` = ?,`phone_number` = ?,`contact_name` = ?,`status` = ?,`first_name` = ? WHERE `mac` = ? AND `phone_number` = ? AND `contact_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ContactsEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getPhoneNumber() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPhoneNumber());
                }
                if (value.getContactName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContactName());
                }
                stmt.bindLong(4, value.getStatus());
                if (value.getFirstName() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getFirstName());
                }
                if (value.getMac() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getMac());
                }
                if (value.getPhoneNumber() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPhoneNumber());
                }
                if (value.getContactName() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getContactName());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<ContactsEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfContactsEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final ContactsEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfContactsEntity.insert((EntityInsertionAdapter<ContactsEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final ContactsEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfContactsEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<ContactsEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfContactsEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final ContactsEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfContactsEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final ContactsEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfContactsEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcContactsDao
    public List<ContactsEntity> queryAll(final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from contact_entity where mac=?", 1);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "phone_number");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contact_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "first_name");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new ContactsEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5)));
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
