package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.ManualHeartEntity;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcManualHeartDao_Impl implements QcManualHeartDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<ManualHeartEntity> __deletionAdapterOfManualHeartEntity;
    private final EntityInsertionAdapter<ManualHeartEntity> __insertionAdapterOfManualHeartEntity;
    private final EntityDeletionOrUpdateAdapter<ManualHeartEntity> __updateAdapterOfManualHeartEntity;

    public QcManualHeartDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfManualHeartEntity = new EntityInsertionAdapter<ManualHeartEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `manual_heart_entity` (`mac`,`date_str`,`content`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ManualHeartEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDateStr());
                }
                if (value.getContent() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContent());
                }
            }
        };
        this.__deletionAdapterOfManualHeartEntity = new EntityDeletionOrUpdateAdapter<ManualHeartEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `manual_heart_entity` WHERE `mac` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ManualHeartEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDateStr());
                }
            }
        };
        this.__updateAdapterOfManualHeartEntity = new EntityDeletionOrUpdateAdapter<ManualHeartEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `manual_heart_entity` SET `mac` = ?,`date_str` = ?,`content` = ? WHERE `mac` = ? AND `date_str` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ManualHeartEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDateStr());
                }
                if (value.getContent() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContent());
                }
                if (value.getMac() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getMac());
                }
                if (value.getDateStr() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getDateStr());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<ManualHeartEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfManualHeartEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final ManualHeartEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfManualHeartEntity.insert((EntityInsertionAdapter<ManualHeartEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final ManualHeartEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfManualHeartEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<ManualHeartEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfManualHeartEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final ManualHeartEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfManualHeartEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final ManualHeartEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfManualHeartEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao
    public ManualHeartEntity queryByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from manual_heart_entity where mac=? and date_str =?", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        ManualHeartEntity manualHeartEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                manualHeartEntity = new ManualHeartEntity(string2, string3, string);
            }
            return manualHeartEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao
    public List<ManualHeartEntity> queryAllData(final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from manual_heart_entity where mac=?", 1);
        if (mac == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, mac);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new ManualHeartEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao
    public ManualHeartEntity queryDataDate(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from manual_heart_entity where mac=? and content !='[]' order by date_str desc LIMIT 1 ", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        ManualHeartEntity manualHeartEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date_str");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                manualHeartEntity = new ManualHeartEntity(string2, string3, string);
            }
            return manualHeartEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
