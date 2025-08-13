package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcCustomFaceDao_Impl implements QcCustomFaceDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<CustomFaceEntity> __deletionAdapterOfCustomFaceEntity;
    private final EntityInsertionAdapter<CustomFaceEntity> __insertionAdapterOfCustomFaceEntity;
    private final EntityDeletionOrUpdateAdapter<CustomFaceEntity> __updateAdapterOfCustomFaceEntity;

    public QcCustomFaceDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCustomFaceEntity = new EntityInsertionAdapter<CustomFaceEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcCustomFaceDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `custom_face` (`address`,`hd_version`,`type`,`x`,`y`,`image_url`,`local_url`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, CustomFaceEntity value) {
                if (value.getAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getAddress());
                }
                if (value.getHdVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHdVersion());
                }
                stmt.bindLong(3, value.getType());
                stmt.bindLong(4, value.getX());
                stmt.bindLong(5, value.getY());
                if (value.getImageUrl() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getImageUrl());
                }
                if (value.getLocalUrl() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getLocalUrl());
                }
            }
        };
        this.__deletionAdapterOfCustomFaceEntity = new EntityDeletionOrUpdateAdapter<CustomFaceEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcCustomFaceDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `custom_face` WHERE `hd_version` = ? AND `type` = ? AND `address` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, CustomFaceEntity value) {
                if (value.getHdVersion() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getHdVersion());
                }
                stmt.bindLong(2, value.getType());
                if (value.getAddress() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getAddress());
                }
            }
        };
        this.__updateAdapterOfCustomFaceEntity = new EntityDeletionOrUpdateAdapter<CustomFaceEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcCustomFaceDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `custom_face` SET `address` = ?,`hd_version` = ?,`type` = ?,`x` = ?,`y` = ?,`image_url` = ?,`local_url` = ? WHERE `hd_version` = ? AND `type` = ? AND `address` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, CustomFaceEntity value) {
                if (value.getAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getAddress());
                }
                if (value.getHdVersion() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getHdVersion());
                }
                stmt.bindLong(3, value.getType());
                stmt.bindLong(4, value.getX());
                stmt.bindLong(5, value.getY());
                if (value.getImageUrl() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getImageUrl());
                }
                if (value.getLocalUrl() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getLocalUrl());
                }
                if (value.getHdVersion() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getHdVersion());
                }
                stmt.bindLong(9, value.getType());
                if (value.getAddress() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getAddress());
                }
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<CustomFaceEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCustomFaceEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final CustomFaceEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCustomFaceEntity.insert((EntityInsertionAdapter<CustomFaceEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final CustomFaceEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCustomFaceEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<CustomFaceEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCustomFaceEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final CustomFaceEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCustomFaceEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final CustomFaceEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfCustomFaceEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcCustomFaceDao
    public List<CustomFaceEntity> queryWatchFaceList(final String address) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from custom_face where address=?", 1);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hd_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_url");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new CustomFaceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcCustomFaceDao
    public CustomFaceEntity queryWatchFaceCustom(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from custom_face where hd_version=? and type =1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        CustomFaceEntity customFaceEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hd_version");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_url");
            if (cursorQuery.moveToFirst()) {
                customFaceEntity = new CustomFaceEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
            }
            return customFaceEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
