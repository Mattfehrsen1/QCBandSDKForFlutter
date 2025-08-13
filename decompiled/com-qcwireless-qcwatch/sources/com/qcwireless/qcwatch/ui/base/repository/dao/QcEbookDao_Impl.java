package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.EbookEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcEbookDao_Impl implements QcEbookDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<EbookEntity> __deletionAdapterOfEbookEntity;
    private final EntityInsertionAdapter<EbookEntity> __insertionAdapterOfEbookEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBookName;
    private final EntityDeletionOrUpdateAdapter<EbookEntity> __updateAdapterOfEbookEntity;

    public QcEbookDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfEbookEntity = new EntityInsertionAdapter<EbookEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `ebook_entity` (`book_name`,`first_name`,`file_path`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, EbookEntity value) {
                if (value.getBookName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getBookName());
                }
                if (value.getFirstName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getFirstName());
                }
                if (value.getFilePath() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getFilePath());
                }
            }
        };
        this.__deletionAdapterOfEbookEntity = new EntityDeletionOrUpdateAdapter<EbookEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `ebook_entity` WHERE `book_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, EbookEntity value) {
                if (value.getBookName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getBookName());
                }
            }
        };
        this.__updateAdapterOfEbookEntity = new EntityDeletionOrUpdateAdapter<EbookEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `ebook_entity` SET `book_name` = ?,`first_name` = ?,`file_path` = ? WHERE `book_name` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, EbookEntity value) {
                if (value.getBookName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getBookName());
                }
                if (value.getFirstName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getFirstName());
                }
                if (value.getFilePath() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getFilePath());
                }
                if (value.getBookName() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getBookName());
                }
            }
        };
        this.__preparedStmtOfDeleteBookName = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from ebook_entity where book_name = ?";
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<EbookEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfEbookEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final EbookEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfEbookEntity.insert((EntityInsertionAdapter<EbookEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final EbookEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfEbookEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<EbookEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfEbookEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final EbookEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfEbookEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final EbookEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfEbookEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao
    public void deleteBookName(final String bookName) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteBookName.acquire();
        if (bookName == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, bookName);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteBookName.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao
    public List<EbookEntity> queryEbooks() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select `ebook_entity`.`book_name` AS `book_name`, `ebook_entity`.`first_name` AS `first_name`, `ebook_entity`.`file_path` AS `file_path` from ebook_entity  ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new EbookEntity(cursorQuery.isNull(0) ? null : cursorQuery.getString(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2)));
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
