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
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class QcDeviceSettingDao_Impl implements QcDeviceSettingDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<DeviceSettingEntity> __deletionAdapterOfDeviceSettingEntity;
    private final EntityInsertionAdapter<DeviceSettingEntity> __insertionAdapterOfDeviceSettingEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteDataWhereMacNotNull;
    private final EntityDeletionOrUpdateAdapter<DeviceSettingEntity> __updateAdapterOfDeviceSettingEntity;

    public QcDeviceSettingDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDeviceSettingEntity = new EntityInsertionAdapter<DeviceSettingEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `device_setting` (`mac`,`setting_action`,`content`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, DeviceSettingEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getSettingAction() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getSettingAction());
                }
                if (value.getContent() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getContent());
                }
            }
        };
        this.__deletionAdapterOfDeviceSettingEntity = new EntityDeletionOrUpdateAdapter<DeviceSettingEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `device_setting` WHERE `mac` = ? AND `setting_action` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, DeviceSettingEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getSettingAction() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getSettingAction());
                }
            }
        };
        this.__updateAdapterOfDeviceSettingEntity = new EntityDeletionOrUpdateAdapter<DeviceSettingEntity>(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `device_setting` SET `mac` = ?,`setting_action` = ?,`content` = ? WHERE `mac` = ? AND `setting_action` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, DeviceSettingEntity value) {
                if (value.getMac() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getMac());
                }
                if (value.getSettingAction() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getSettingAction());
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
                if (value.getSettingAction() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getSettingAction());
                }
            }
        };
        this.__preparedStmtOfDeleteDataWhereMacNotNull = new SharedSQLiteStatement(__db) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM device_setting where mac =? ";
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insertAll(final List<DeviceSettingEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDeviceSettingEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void insert(final DeviceSettingEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDeviceSettingEntity.insert((EntityInsertionAdapter<DeviceSettingEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void delete(final DeviceSettingEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDeviceSettingEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteList(final List<DeviceSettingEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDeviceSettingEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void deleteSome(final DeviceSettingEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDeviceSettingEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.BaseDao
    public void update(final DeviceSettingEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfDeviceSettingEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao
    public void deleteDataWhereMacNotNull(final String mac) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteDataWhereMacNotNull.acquire();
        if (mac == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, mac);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteDataWhereMacNotNull.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao
    public DeviceSettingEntity queryByMacAndAction(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from device_setting where mac= ? and setting_action=?", 2);
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
        DeviceSettingEntity deviceSettingEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "setting_action");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                deviceSettingEntity = new DeviceSettingEntity(string2, string3, string);
            }
            return deviceSettingEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
