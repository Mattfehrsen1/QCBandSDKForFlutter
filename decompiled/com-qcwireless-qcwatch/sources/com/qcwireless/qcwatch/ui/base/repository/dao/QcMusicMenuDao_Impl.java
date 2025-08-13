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
import com.qcwireless.qcwatch.ui.base.repository.entity.SongMenuEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcMusicMenuDao_Impl implements QcMusicMenuDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SongMenuEntity> __deletionAdapterOfSongMenuEntity;
    private final EntityInsertionAdapter<SongMenuEntity> __insertionAdapterOfSongMenuEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteMenu;
    private final SharedSQLiteStatement __preparedStmtOfUpdateMenuName;
    private final EntityDeletionOrUpdateAdapter<SongMenuEntity> __updateAdapterOfSongMenuEntity;

    public QcMusicMenuDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSongMenuEntity = new EntityInsertionAdapter<SongMenuEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `song_menu` (`device_address`,`menu_id`,`menu_name`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SongMenuEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                stmt.bindLong(2, value.getMenuId());
                if (value.getMenuName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getMenuName());
                }
            }
        };
        this.__deletionAdapterOfSongMenuEntity = new EntityDeletionOrUpdateAdapter<SongMenuEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `song_menu` WHERE `menu_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SongMenuEntity value) {
                stmt.bindLong(1, value.getMenuId());
            }
        };
        this.__updateAdapterOfSongMenuEntity = new EntityDeletionOrUpdateAdapter<SongMenuEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `song_menu` SET `device_address` = ?,`menu_id` = ?,`menu_name` = ? WHERE `menu_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SongMenuEntity value) {
                if (value.getDeviceAddress() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDeviceAddress());
                }
                stmt.bindLong(2, value.getMenuId());
                if (value.getMenuName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getMenuName());
                }
                stmt.bindLong(4, value.getMenuId());
            }
        };
        this.__preparedStmtOfUpdateMenuName = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update  song_menu set  menu_name= ? where menu_id =  ?";
            }
        };
        this.__preparedStmtOfDeleteMenu = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from  song_menu  where menu_id =  ?";
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<SongMenuEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSongMenuEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final SongMenuEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSongMenuEntity.insert((EntityInsertionAdapter<SongMenuEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final SongMenuEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSongMenuEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<SongMenuEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSongMenuEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final SongMenuEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSongMenuEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final SongMenuEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSongMenuEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao
    public void updateMenuName(final String menuName, final long menuId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateMenuName.acquire();
        if (menuName == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, menuName);
        }
        supportSQLiteStatementAcquire.bindLong(2, menuId);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateMenuName.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao
    public void deleteMenu(final long menuId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteMenu.acquire();
        supportSQLiteStatementAcquire.bindLong(1, menuId);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteMenu.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao
    public List<SongMenuEntity> queryMenuList(final String address) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from song_menu where  device_address=? and menu_id >0", 1);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_name");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SongMenuEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao
    public SongMenuEntity queryMusicMenuByMenuName(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from song_menu where  device_address=? and menu_name =?", 2);
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
        SongMenuEntity songMenuEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_name");
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                long j = cursorQuery.getLong(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                songMenuEntity = new SongMenuEntity(string2, j, string);
            }
            return songMenuEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao
    public SongMenuEntity queryMusicMenuByMenuId(String str, long j) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from song_menu where  device_address=? and menu_id =?", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        roomSQLiteQueryAcquire.bindLong(2, j);
        this.__db.assertNotSuspendingTransaction();
        SongMenuEntity songMenuEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_name");
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                long j2 = cursorQuery.getLong(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                songMenuEntity = new SongMenuEntity(string2, j2, string);
            }
            return songMenuEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcMusicMenuDao
    public List<SongMenuEntity> queryMenusList(final String address) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from song_menu where  device_address=?", 1);
        if (address == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, address);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "device_address");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "menu_name");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SongMenuEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)));
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
