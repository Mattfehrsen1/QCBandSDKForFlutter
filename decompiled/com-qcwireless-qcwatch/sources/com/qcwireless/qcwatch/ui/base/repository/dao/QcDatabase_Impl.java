package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.database.SQLException;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.baidu.location.LocationConst;
import com.google.android.gms.fitness.data.Field;
import com.qcwireless.qcwatch.ui.mine.ai.bean.ChatEnumAction;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class QcDatabase_Impl extends QcDatabase {
    private volatile ManualHrvDao _manualHrvDao;
    private volatile ManualPressureDao _manualPressureDao;
    private volatile QcBloodOxygenDao _qcBloodOxygenDao;
    private volatile QcBloodPressureDao _qcBloodPressureDao;
    private volatile QcBloodSugarDao _qcBloodSugarDao;
    private volatile QcContactsDao _qcContactsDao;
    private volatile QcCustomFaceDao _qcCustomFaceDao;
    private volatile QcDeviceSettingDao _qcDeviceSettingDao;
    private volatile QcEbookDao _qcEbookDao;
    private volatile QcFeedbackDao _qcFeedbackDao;
    private volatile QcGpsDetailDao _qcGpsDetailDao;
    private volatile QcHeartRateDetailDao _qcHeartRateDetailDao;
    private volatile QcHrvDetailDao _qcHrvDetailDao;
    private volatile QcManualHeartDao _qcManualHeartDao;
    private volatile QcMenstruationDao _qcMenstruationDao;
    private volatile QcMessagePushDao _qcMessagePushDao;
    private volatile QcMusicManagerDao _qcMusicManagerDao;
    private volatile QcMusicMenuDao _qcMusicMenuDao;
    private volatile QcPressureDetailDao _qcPressureDetailDao;
    private volatile QcSleepDetailDao _qcSleepDetailDao;
    private volatile QcSleepLunchProtocolDao _qcSleepLunchProtocolDao;
    private volatile QcSleepNewProtocolDao _qcSleepNewProtocolDao;
    private volatile QcSleepTotalDao _qcSleepTotalDao;
    private volatile QcSportPlusDetailDao _qcSportPlusDetailDao;
    private volatile QcStepDetailDao _qcStepDetailDao;
    private volatile QcStepTotalDao _qcStepTotalDao;
    private volatile QcSyncDao _qcSyncDao;
    private volatile QcTargetDao _qcTargetDao;
    private volatile QcTemperatureDao _qcTemperatureDao;
    private volatile QcUserDao _qcUserDao;
    private volatile QcWatchFaceDao _qcWatchFaceDao;
    private volatile QcWatchFaceIndexDao _qcWatchFaceIndexDao;
    private volatile QcWatchThemeDao _qcWatchThemeDao;
    private volatile QcWatchWallpaperDao _qcWatchWallpaperDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(36) { // from class: com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) throws SQLException {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`uid` INTEGER NOT NULL, `email` TEXT NOT NULL, `nick` TEXT NOT NULL, `gender` INTEGER NOT NULL, `weight` REAL NOT NULL, `weight_lbs` INTEGER NOT NULL, `height` REAL NOT NULL, `birthday` TEXT NOT NULL, `avatar_url` TEXT NOT NULL, `local_avatar_url` TEXT NOT NULL, `goal_steps` INTEGER NOT NULL, `goal_calorie` REAL NOT NULL, `goal_distance` REAL NOT NULL, `goal_sport_time` REAL NOT NULL, `goal_sleep_time` REAL NOT NULL, `register_date` TEXT NOT NULL, `update` INTEGER NOT NULL, PRIMARY KEY(`uid`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `device_setting` (`mac` TEXT NOT NULL, `setting_action` TEXT NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`mac`, `setting_action`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `step_detail` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `interval` INTEGER NOT NULL, `total_active_time` INTEGER NOT NULL, `index_str` TEXT NOT NULL, `counts` TEXT NOT NULL, `miles` TEXT NOT NULL, `calories` TEXT NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sleep_detail` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `interval` INTEGER NOT NULL, `index_str` TEXT NOT NULL, `quality` TEXT NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `step_total` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `step` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `calorie` INTEGER NOT NULL, `unix_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sleep_total` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `total_sleep` INTEGER NOT NULL, `deep_sleep` INTEGER NOT NULL, `light_sleep` INTEGER NOT NULL, `rapid_sleep` INTEGER NOT NULL, `awake` INTEGER NOT NULL, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL, `unix_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `heart_rate_detail` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `interval` INTEGER NOT NULL, `index_str` TEXT NOT NULL, `value` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sport_plus_detail` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `start_time` INTEGER NOT NULL, `sport_type` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `distance` REAL NOT NULL, `calories` REAL NOT NULL, `steps` INTEGER NOT NULL, `rate_value` TEXT NOT NULL, `avg_rate` INTEGER NOT NULL, `sync` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `start_time`, `sport_type`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `watch_face` (`name` TEXT NOT NULL, `hardware_version` TEXT NOT NULL, `pre_image_url` TEXT NOT NULL, `bin_url` TEXT NOT NULL, `price` REAL NOT NULL, `face_type` INTEGER NOT NULL, `face_desc` TEXT NOT NULL, `local_image_url` TEXT NOT NULL, `local_bin_url` TEXT NOT NULL, `market_version` INTEGER NOT NULL, `type_id` INTEGER NOT NULL, PRIMARY KEY(`name`, `hardware_version`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `menstruation` (`mid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `month_date` TEXT, `year` INTEGER NOT NULL, `month` INTEGER NOT NULL, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL, `duration` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `message_push` (`package_name` TEXT NOT NULL, `open` INTEGER NOT NULL, PRIMARY KEY(`package_name`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sync_entity` (`uid` INTEGER NOT NULL, `data_action` TEXT NOT NULL, `last_sync_id` INTEGER NOT NULL, PRIMARY KEY(`uid`, `data_action`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `gps_detail` (`start_time` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `distance` REAL NOT NULL, `calorie` REAL NOT NULL, `locations` TEXT NOT NULL, `date_str` TEXT NOT NULL, PRIMARY KEY(`start_time`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `blood_pressure` (`device_address` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `sbp` INTEGER NOT NULL, `dbp` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `unix_time`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `blood_oxygen` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `min_array` TEXT NOT NULL, `max_array` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `feedback` (`type_id` INTEGER NOT NULL, `feedback_id` INTEGER NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`type_id`, `feedback_id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `custom_face` (`address` TEXT NOT NULL, `hd_version` TEXT NOT NULL, `type` INTEGER NOT NULL, `x` INTEGER NOT NULL, `y` INTEGER NOT NULL, `image_url` TEXT NOT NULL, `local_url` TEXT NOT NULL, PRIMARY KEY(`hd_version`, `type`, `address`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `temperature_entity` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `temperature` REAL NOT NULL, `min` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `manual_flag` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`, `min`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sleep_new_protocol` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `detail` TEXT NOT NULL, `st` INTEGER NOT NULL, `et` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `contact_entity` (`mac` TEXT NOT NULL, `phone_number` TEXT NOT NULL, `contact_name` TEXT NOT NULL, `status` INTEGER NOT NULL, `first_name` TEXT NOT NULL, PRIMARY KEY(`mac`, `phone_number`, `contact_name`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `manual_heart_entity` (`mac` TEXT NOT NULL, `date_str` TEXT NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`mac`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `pay_watch_face` (`uid` INTEGER NOT NULL, `name` TEXT NOT NULL, `hardware_version` TEXT NOT NULL, `pre_image_url` TEXT NOT NULL, `bin_url` TEXT NOT NULL, `pay_status` INTEGER NOT NULL, `order_number` TEXT NOT NULL, `google_order_id` TEXT NOT NULL, PRIMARY KEY(`uid`, `name`, `hardware_version`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `watch_face_index` (`name` TEXT NOT NULL, `hardware_version` TEXT NOT NULL, `pre_image_url` TEXT NOT NULL, `bin_url` TEXT NOT NULL, `price` REAL NOT NULL, `local_image_url` TEXT NOT NULL, `local_bin_url` TEXT NOT NULL, `type_id` INTEGER NOT NULL, `position` INTEGER NOT NULL, PRIMARY KEY(`hardware_version`, `type_id`, `position`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `music_to_device` (`device_address` TEXT NOT NULL, `music_name` TEXT NOT NULL, `singer` TEXT NOT NULL, `size` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `path` TEXT NOT NULL, `album_id` INTEGER NOT NULL, `song_menu_name` TEXT NOT NULL, `song_menu_id` INTEGER NOT NULL, PRIMARY KEY(`music_name`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `song_menu` (`device_address` TEXT NOT NULL, `menu_id` INTEGER NOT NULL, `menu_name` TEXT NOT NULL, PRIMARY KEY(`menu_id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `ebook_entity` (`book_name` TEXT NOT NULL, `first_name` TEXT NOT NULL, `file_path` TEXT NOT NULL, PRIMARY KEY(`book_name`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `blood_sugar` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `min_array` TEXT NOT NULL, `max_array` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `target_entity` (`device_address` TEXT NOT NULL, `goal_steps` INTEGER NOT NULL, `goal_calorie` REAL NOT NULL, `goal_distance` REAL NOT NULL, `goal_sport_time` REAL NOT NULL, `goal_sleep_time` REAL NOT NULL, PRIMARY KEY(`device_address`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `hrv_detail` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `interval` INTEGER NOT NULL, `index_str` TEXT NOT NULL, `value` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `hrv_manual` (`mac` TEXT NOT NULL, `date_str` TEXT NOT NULL, `hrv` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`mac`, `timestamp`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `pressure_detail` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `interval` INTEGER NOT NULL, `index_str` TEXT NOT NULL, `value` TEXT NOT NULL, `unix_time` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `pressure_manual` (`mac` TEXT NOT NULL, `date_str` TEXT NOT NULL, `pressure` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`mac`, `timestamp`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sleep_lunch_protocol` (`device_address` TEXT NOT NULL, `date_str` TEXT NOT NULL, `detail` TEXT NOT NULL, `lunch_st` INTEGER NOT NULL, `lunch_et` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `last_sync_time` INTEGER NOT NULL, PRIMARY KEY(`device_address`, `date_str`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `watch_theme_face` (`theme_name` TEXT NOT NULL, `hardware_version` TEXT NOT NULL, `pre_image_url` TEXT NOT NULL, `bin_url` TEXT NOT NULL, `theme_desc` TEXT NOT NULL, `local_image_url` TEXT NOT NULL, `local_bin_url` TEXT NOT NULL, `theme_type` INTEGER NOT NULL, `app_home_index` INTEGER NOT NULL, PRIMARY KEY(`theme_name`, `hardware_version`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `watch_wallpaper_face` (`wallpaper_name` TEXT NOT NULL, `hardware_version` TEXT NOT NULL, `wallpaper_desc` TEXT NOT NULL, `wallpaper_url` TEXT NOT NULL, `wallpaper_file_url` TEXT NOT NULL, `wallpaper_type_app_index` INTEGER NOT NULL, `wallpaper_app_index` INTEGER NOT NULL, `local_image_url` TEXT NOT NULL, `local_bin_url` TEXT NOT NULL, `wallpaper_type` INTEGER NOT NULL, `wallpaper_type_name` TEXT NOT NULL, PRIMARY KEY(`wallpaper_name`, `hardware_version`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fda683251526fbca740b7dbbbd5f82a9')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) throws SQLException {
                _db.execSQL("DROP TABLE IF EXISTS `user`");
                _db.execSQL("DROP TABLE IF EXISTS `device_setting`");
                _db.execSQL("DROP TABLE IF EXISTS `step_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `sleep_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `step_total`");
                _db.execSQL("DROP TABLE IF EXISTS `sleep_total`");
                _db.execSQL("DROP TABLE IF EXISTS `heart_rate_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `sport_plus_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `watch_face`");
                _db.execSQL("DROP TABLE IF EXISTS `menstruation`");
                _db.execSQL("DROP TABLE IF EXISTS `message_push`");
                _db.execSQL("DROP TABLE IF EXISTS `sync_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `gps_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `blood_pressure`");
                _db.execSQL("DROP TABLE IF EXISTS `blood_oxygen`");
                _db.execSQL("DROP TABLE IF EXISTS `feedback`");
                _db.execSQL("DROP TABLE IF EXISTS `custom_face`");
                _db.execSQL("DROP TABLE IF EXISTS `temperature_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `sleep_new_protocol`");
                _db.execSQL("DROP TABLE IF EXISTS `contact_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `manual_heart_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `pay_watch_face`");
                _db.execSQL("DROP TABLE IF EXISTS `watch_face_index`");
                _db.execSQL("DROP TABLE IF EXISTS `music_to_device`");
                _db.execSQL("DROP TABLE IF EXISTS `song_menu`");
                _db.execSQL("DROP TABLE IF EXISTS `ebook_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `blood_sugar`");
                _db.execSQL("DROP TABLE IF EXISTS `target_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `hrv_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `hrv_manual`");
                _db.execSQL("DROP TABLE IF EXISTS `pressure_detail`");
                _db.execSQL("DROP TABLE IF EXISTS `pressure_manual`");
                _db.execSQL("DROP TABLE IF EXISTS `sleep_lunch_protocol`");
                _db.execSQL("DROP TABLE IF EXISTS `watch_theme_face`");
                _db.execSQL("DROP TABLE IF EXISTS `watch_wallpaper_face`");
                if (QcDatabase_Impl.this.mCallbacks != null) {
                    int size = QcDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) QcDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (QcDatabase_Impl.this.mCallbacks != null) {
                    int size = QcDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) QcDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                QcDatabase_Impl.this.mDatabase = _db;
                QcDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (QcDatabase_Impl.this.mCallbacks != null) {
                    int size = QcDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) QcDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) throws SQLException {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(17);
                map.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                map.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, 1));
                map.put("nick", new TableInfo.Column("nick", "TEXT", true, 0, null, 1));
                map.put("gender", new TableInfo.Column("gender", "INTEGER", true, 0, null, 1));
                map.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, 1));
                map.put("weight_lbs", new TableInfo.Column("weight_lbs", "INTEGER", true, 0, null, 1));
                map.put("height", new TableInfo.Column("height", "REAL", true, 0, null, 1));
                map.put("birthday", new TableInfo.Column("birthday", "TEXT", true, 0, null, 1));
                map.put("avatar_url", new TableInfo.Column("avatar_url", "TEXT", true, 0, null, 1));
                map.put("local_avatar_url", new TableInfo.Column("local_avatar_url", "TEXT", true, 0, null, 1));
                map.put("goal_steps", new TableInfo.Column("goal_steps", "INTEGER", true, 0, null, 1));
                map.put("goal_calorie", new TableInfo.Column("goal_calorie", "REAL", true, 0, null, 1));
                map.put("goal_distance", new TableInfo.Column("goal_distance", "REAL", true, 0, null, 1));
                map.put("goal_sport_time", new TableInfo.Column("goal_sport_time", "REAL", true, 0, null, 1));
                map.put("goal_sleep_time", new TableInfo.Column("goal_sleep_time", "REAL", true, 0, null, 1));
                map.put("register_date", new TableInfo.Column("register_date", "TEXT", true, 0, null, 1));
                map.put("update", new TableInfo.Column("update", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo(ChatEnumAction.USER, map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, ChatEnumAction.USER);
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "user(com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(3);
                map2.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, null, 1));
                map2.put("setting_action", new TableInfo.Column("setting_action", "TEXT", true, 2, null, 1));
                map2.put(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, new TableInfo.Column(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "TEXT", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("device_setting", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, "device_setting");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "device_setting(com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(10);
                map3.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map3.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map3.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
                map3.put("total_active_time", new TableInfo.Column("total_active_time", "INTEGER", true, 0, null, 1));
                map3.put("index_str", new TableInfo.Column("index_str", "TEXT", true, 0, null, 1));
                map3.put("counts", new TableInfo.Column("counts", "TEXT", true, 0, null, 1));
                map3.put("miles", new TableInfo.Column("miles", "TEXT", true, 0, null, 1));
                map3.put(Field.NUTRIENT_CALORIES, new TableInfo.Column(Field.NUTRIENT_CALORIES, "TEXT", true, 0, null, 1));
                map3.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map3.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("step_detail", map3, new HashSet(0), new HashSet(0));
                TableInfo tableInfo6 = TableInfo.read(_db, "step_detail");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "step_detail(com.qcwireless.qcwatch.ui.base.repository.entity.StepDetail).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(7);
                map4.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map4.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map4.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
                map4.put("index_str", new TableInfo.Column("index_str", "TEXT", true, 0, null, 1));
                map4.put("quality", new TableInfo.Column("quality", "TEXT", true, 0, null, 1));
                map4.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map4.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("sleep_detail", map4, new HashSet(0), new HashSet(0));
                TableInfo tableInfo8 = TableInfo.read(_db, "sleep_detail");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "sleep_detail(com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                HashMap map5 = new HashMap(6);
                map5.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map5.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map5.put("step", new TableInfo.Column("step", "INTEGER", true, 0, null, 1));
                map5.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
                map5.put("calorie", new TableInfo.Column("calorie", "INTEGER", true, 0, null, 1));
                map5.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo9 = new TableInfo("step_total", map5, new HashSet(0), new HashSet(0));
                TableInfo tableInfo10 = TableInfo.read(_db, "step_total");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenHelper.ValidationResult(false, "step_total(com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                HashMap map6 = new HashMap(10);
                map6.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map6.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map6.put("total_sleep", new TableInfo.Column("total_sleep", "INTEGER", true, 0, null, 1));
                map6.put("deep_sleep", new TableInfo.Column("deep_sleep", "INTEGER", true, 0, null, 1));
                map6.put("light_sleep", new TableInfo.Column("light_sleep", "INTEGER", true, 0, null, 1));
                map6.put("rapid_sleep", new TableInfo.Column("rapid_sleep", "INTEGER", true, 0, null, 1));
                map6.put("awake", new TableInfo.Column("awake", "INTEGER", true, 0, null, 1));
                map6.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 0, null, 1));
                map6.put("end_time", new TableInfo.Column("end_time", "INTEGER", true, 0, null, 1));
                map6.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo11 = new TableInfo("sleep_total", map6, new HashSet(0), new HashSet(0));
                TableInfo tableInfo12 = TableInfo.read(_db, "sleep_total");
                if (!tableInfo11.equals(tableInfo12)) {
                    return new RoomOpenHelper.ValidationResult(false, "sleep_total(com.qcwireless.qcwatch.ui.base.repository.entity.SleepTotalHistory).\n Expected:\n" + tableInfo11 + "\n Found:\n" + tableInfo12);
                }
                HashMap map7 = new HashMap(8);
                map7.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map7.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map7.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
                map7.put("index_str", new TableInfo.Column("index_str", "TEXT", true, 0, null, 1));
                map7.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, 1));
                map7.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                map7.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map7.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo13 = new TableInfo("heart_rate_detail", map7, new HashSet(0), new HashSet(0));
                TableInfo tableInfo14 = TableInfo.read(_db, "heart_rate_detail");
                if (!tableInfo13.equals(tableInfo14)) {
                    return new RoomOpenHelper.ValidationResult(false, "heart_rate_detail(com.qcwireless.qcwatch.ui.base.repository.entity.HeartRateDetail).\n Expected:\n" + tableInfo13 + "\n Found:\n" + tableInfo14);
                }
                HashMap map8 = new HashMap(11);
                map8.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map8.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 0, null, 1));
                map8.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 2, null, 1));
                map8.put("sport_type", new TableInfo.Column("sport_type", "INTEGER", true, 3, null, 1));
                map8.put(TypedValues.TransitionType.S_DURATION, new TableInfo.Column(TypedValues.TransitionType.S_DURATION, "INTEGER", true, 0, null, 1));
                map8.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, 1));
                map8.put(Field.NUTRIENT_CALORIES, new TableInfo.Column(Field.NUTRIENT_CALORIES, "REAL", true, 0, null, 1));
                map8.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, 1));
                map8.put("rate_value", new TableInfo.Column("rate_value", "TEXT", true, 0, null, 1));
                map8.put("avg_rate", new TableInfo.Column("avg_rate", "INTEGER", true, 0, null, 1));
                map8.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo15 = new TableInfo("sport_plus_detail", map8, new HashSet(0), new HashSet(0));
                TableInfo tableInfo16 = TableInfo.read(_db, "sport_plus_detail");
                if (!tableInfo15.equals(tableInfo16)) {
                    return new RoomOpenHelper.ValidationResult(false, "sport_plus_detail(com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail).\n Expected:\n" + tableInfo15 + "\n Found:\n" + tableInfo16);
                }
                HashMap map9 = new HashMap(11);
                map9.put("name", new TableInfo.Column("name", "TEXT", true, 1, null, 1));
                map9.put("hardware_version", new TableInfo.Column("hardware_version", "TEXT", true, 2, null, 1));
                map9.put("pre_image_url", new TableInfo.Column("pre_image_url", "TEXT", true, 0, null, 1));
                map9.put("bin_url", new TableInfo.Column("bin_url", "TEXT", true, 0, null, 1));
                map9.put("price", new TableInfo.Column("price", "REAL", true, 0, null, 1));
                map9.put("face_type", new TableInfo.Column("face_type", "INTEGER", true, 0, null, 1));
                map9.put("face_desc", new TableInfo.Column("face_desc", "TEXT", true, 0, null, 1));
                map9.put("local_image_url", new TableInfo.Column("local_image_url", "TEXT", true, 0, null, 1));
                map9.put("local_bin_url", new TableInfo.Column("local_bin_url", "TEXT", true, 0, null, 1));
                map9.put("market_version", new TableInfo.Column("market_version", "INTEGER", true, 0, null, 1));
                map9.put("type_id", new TableInfo.Column("type_id", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo17 = new TableInfo("watch_face", map9, new HashSet(0), new HashSet(0));
                TableInfo tableInfo18 = TableInfo.read(_db, "watch_face");
                if (!tableInfo17.equals(tableInfo18)) {
                    return new RoomOpenHelper.ValidationResult(false, "watch_face(com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace).\n Expected:\n" + tableInfo17 + "\n Found:\n" + tableInfo18);
                }
                HashMap map10 = new HashMap(7);
                map10.put("mid", new TableInfo.Column("mid", "INTEGER", true, 1, null, 1));
                map10.put("month_date", new TableInfo.Column("month_date", "TEXT", false, 0, null, 1));
                map10.put("year", new TableInfo.Column("year", "INTEGER", true, 0, null, 1));
                map10.put("month", new TableInfo.Column("month", "INTEGER", true, 0, null, 1));
                map10.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 0, null, 1));
                map10.put("end_time", new TableInfo.Column("end_time", "INTEGER", true, 0, null, 1));
                map10.put(TypedValues.TransitionType.S_DURATION, new TableInfo.Column(TypedValues.TransitionType.S_DURATION, "INTEGER", true, 0, null, 1));
                TableInfo tableInfo19 = new TableInfo("menstruation", map10, new HashSet(0), new HashSet(0));
                TableInfo tableInfo20 = TableInfo.read(_db, "menstruation");
                if (!tableInfo19.equals(tableInfo20)) {
                    return new RoomOpenHelper.ValidationResult(false, "menstruation(com.qcwireless.qcwatch.ui.base.repository.entity.MenstruationEntity).\n Expected:\n" + tableInfo19 + "\n Found:\n" + tableInfo20);
                }
                HashMap map11 = new HashMap(2);
                map11.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 1, null, 1));
                map11.put("open", new TableInfo.Column("open", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo21 = new TableInfo("message_push", map11, new HashSet(0), new HashSet(0));
                TableInfo tableInfo22 = TableInfo.read(_db, "message_push");
                if (!tableInfo21.equals(tableInfo22)) {
                    return new RoomOpenHelper.ValidationResult(false, "message_push(com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity).\n Expected:\n" + tableInfo21 + "\n Found:\n" + tableInfo22);
                }
                HashMap map12 = new HashMap(3);
                map12.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                map12.put("data_action", new TableInfo.Column("data_action", "TEXT", true, 2, null, 1));
                map12.put("last_sync_id", new TableInfo.Column("last_sync_id", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo23 = new TableInfo("sync_entity", map12, new HashSet(0), new HashSet(0));
                TableInfo tableInfo24 = TableInfo.read(_db, "sync_entity");
                if (!tableInfo23.equals(tableInfo24)) {
                    return new RoomOpenHelper.ValidationResult(false, "sync_entity(com.qcwireless.qcwatch.ui.base.repository.entity.SyncDataEntity).\n Expected:\n" + tableInfo23 + "\n Found:\n" + tableInfo24);
                }
                HashMap map13 = new HashMap(6);
                map13.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 1, null, 1));
                map13.put(TypedValues.TransitionType.S_DURATION, new TableInfo.Column(TypedValues.TransitionType.S_DURATION, "INTEGER", true, 0, null, 1));
                map13.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, 1));
                map13.put("calorie", new TableInfo.Column("calorie", "REAL", true, 0, null, 1));
                map13.put("locations", new TableInfo.Column("locations", "TEXT", true, 0, null, 1));
                map13.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 0, null, 1));
                TableInfo tableInfo25 = new TableInfo("gps_detail", map13, new HashSet(0), new HashSet(0));
                TableInfo tableInfo26 = TableInfo.read(_db, "gps_detail");
                if (!tableInfo25.equals(tableInfo26)) {
                    return new RoomOpenHelper.ValidationResult(false, "gps_detail(com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail).\n Expected:\n" + tableInfo25 + "\n Found:\n" + tableInfo26);
                }
                HashMap map14 = new HashMap(6);
                map14.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map14.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 2, null, 1));
                map14.put("sbp", new TableInfo.Column("sbp", "INTEGER", true, 0, null, 1));
                map14.put("dbp", new TableInfo.Column("dbp", "INTEGER", true, 0, null, 1));
                map14.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map14.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo27 = new TableInfo("blood_pressure", map14, new HashSet(0), new HashSet(0));
                TableInfo tableInfo28 = TableInfo.read(_db, "blood_pressure");
                if (!tableInfo27.equals(tableInfo28)) {
                    return new RoomOpenHelper.ValidationResult(false, "blood_pressure(com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity).\n Expected:\n" + tableInfo27 + "\n Found:\n" + tableInfo28);
                }
                HashMap map15 = new HashMap(7);
                map15.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map15.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map15.put("min_array", new TableInfo.Column("min_array", "TEXT", true, 0, null, 1));
                map15.put("max_array", new TableInfo.Column("max_array", "TEXT", true, 0, null, 1));
                map15.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                map15.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map15.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo29 = new TableInfo("blood_oxygen", map15, new HashSet(0), new HashSet(0));
                TableInfo tableInfo30 = TableInfo.read(_db, "blood_oxygen");
                if (!tableInfo29.equals(tableInfo30)) {
                    return new RoomOpenHelper.ValidationResult(false, "blood_oxygen(com.qcwireless.qcwatch.ui.base.repository.entity.BloodOxygenEntity).\n Expected:\n" + tableInfo29 + "\n Found:\n" + tableInfo30);
                }
                HashMap map16 = new HashMap(3);
                map16.put("type_id", new TableInfo.Column("type_id", "INTEGER", true, 1, null, 1));
                map16.put("feedback_id", new TableInfo.Column("feedback_id", "INTEGER", true, 2, null, 1));
                map16.put(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, new TableInfo.Column(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "TEXT", true, 0, null, 1));
                TableInfo tableInfo31 = new TableInfo("feedback", map16, new HashSet(0), new HashSet(0));
                TableInfo tableInfo32 = TableInfo.read(_db, "feedback");
                if (!tableInfo31.equals(tableInfo32)) {
                    return new RoomOpenHelper.ValidationResult(false, "feedback(com.qcwireless.qcwatch.ui.base.repository.entity.FeedbackEntity).\n Expected:\n" + tableInfo31 + "\n Found:\n" + tableInfo32);
                }
                HashMap map17 = new HashMap(7);
                map17.put("address", new TableInfo.Column("address", "TEXT", true, 3, null, 1));
                map17.put("hd_version", new TableInfo.Column("hd_version", "TEXT", true, 1, null, 1));
                map17.put("type", new TableInfo.Column("type", "INTEGER", true, 2, null, 1));
                map17.put("x", new TableInfo.Column("x", "INTEGER", true, 0, null, 1));
                map17.put("y", new TableInfo.Column("y", "INTEGER", true, 0, null, 1));
                map17.put("image_url", new TableInfo.Column("image_url", "TEXT", true, 0, null, 1));
                map17.put("local_url", new TableInfo.Column("local_url", "TEXT", true, 0, null, 1));
                TableInfo tableInfo33 = new TableInfo("custom_face", map17, new HashSet(0), new HashSet(0));
                TableInfo tableInfo34 = TableInfo.read(_db, "custom_face");
                if (!tableInfo33.equals(tableInfo34)) {
                    return new RoomOpenHelper.ValidationResult(false, "custom_face(com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity).\n Expected:\n" + tableInfo33 + "\n Found:\n" + tableInfo34);
                }
                HashMap map18 = new HashMap(8);
                map18.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map18.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map18.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                map18.put("temperature", new TableInfo.Column("temperature", "REAL", true, 0, null, 1));
                map18.put("min", new TableInfo.Column("min", "INTEGER", true, 3, null, 1));
                map18.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map18.put("manual_flag", new TableInfo.Column("manual_flag", "INTEGER", true, 0, null, 1));
                map18.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo35 = new TableInfo("temperature_entity", map18, new HashSet(0), new HashSet(0));
                TableInfo tableInfo36 = TableInfo.read(_db, "temperature_entity");
                if (!tableInfo35.equals(tableInfo36)) {
                    return new RoomOpenHelper.ValidationResult(false, "temperature_entity(com.qcwireless.qcwatch.ui.base.repository.entity.BodyTemperatureEntity).\n Expected:\n" + tableInfo35 + "\n Found:\n" + tableInfo36);
                }
                HashMap map19 = new HashMap(7);
                map19.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map19.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map19.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, 1));
                map19.put("st", new TableInfo.Column("st", "INTEGER", true, 0, null, 1));
                map19.put("et", new TableInfo.Column("et", "INTEGER", true, 0, null, 1));
                map19.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map19.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo37 = new TableInfo("sleep_new_protocol", map19, new HashSet(0), new HashSet(0));
                TableInfo tableInfo38 = TableInfo.read(_db, "sleep_new_protocol");
                if (!tableInfo37.equals(tableInfo38)) {
                    return new RoomOpenHelper.ValidationResult(false, "sleep_new_protocol(com.qcwireless.qcwatch.ui.base.repository.entity.SleepNewProtocol).\n Expected:\n" + tableInfo37 + "\n Found:\n" + tableInfo38);
                }
                HashMap map20 = new HashMap(5);
                map20.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, null, 1));
                map20.put("phone_number", new TableInfo.Column("phone_number", "TEXT", true, 2, null, 1));
                map20.put("contact_name", new TableInfo.Column("contact_name", "TEXT", true, 3, null, 1));
                map20.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "INTEGER", true, 0, null, 1));
                map20.put("first_name", new TableInfo.Column("first_name", "TEXT", true, 0, null, 1));
                TableInfo tableInfo39 = new TableInfo("contact_entity", map20, new HashSet(0), new HashSet(0));
                TableInfo tableInfo40 = TableInfo.read(_db, "contact_entity");
                if (!tableInfo39.equals(tableInfo40)) {
                    return new RoomOpenHelper.ValidationResult(false, "contact_entity(com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity).\n Expected:\n" + tableInfo39 + "\n Found:\n" + tableInfo40);
                }
                HashMap map21 = new HashMap(3);
                map21.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, null, 1));
                map21.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map21.put(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, new TableInfo.Column(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "TEXT", true, 0, null, 1));
                TableInfo tableInfo41 = new TableInfo("manual_heart_entity", map21, new HashSet(0), new HashSet(0));
                TableInfo tableInfo42 = TableInfo.read(_db, "manual_heart_entity");
                if (!tableInfo41.equals(tableInfo42)) {
                    return new RoomOpenHelper.ValidationResult(false, "manual_heart_entity(com.qcwireless.qcwatch.ui.base.repository.entity.ManualHeartEntity).\n Expected:\n" + tableInfo41 + "\n Found:\n" + tableInfo42);
                }
                HashMap map22 = new HashMap(8);
                map22.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                map22.put("name", new TableInfo.Column("name", "TEXT", true, 2, null, 1));
                map22.put("hardware_version", new TableInfo.Column("hardware_version", "TEXT", true, 3, null, 1));
                map22.put("pre_image_url", new TableInfo.Column("pre_image_url", "TEXT", true, 0, null, 1));
                map22.put("bin_url", new TableInfo.Column("bin_url", "TEXT", true, 0, null, 1));
                map22.put("pay_status", new TableInfo.Column("pay_status", "INTEGER", true, 0, null, 1));
                map22.put("order_number", new TableInfo.Column("order_number", "TEXT", true, 0, null, 1));
                map22.put("google_order_id", new TableInfo.Column("google_order_id", "TEXT", true, 0, null, 1));
                TableInfo tableInfo43 = new TableInfo("pay_watch_face", map22, new HashSet(0), new HashSet(0));
                TableInfo tableInfo44 = TableInfo.read(_db, "pay_watch_face");
                if (!tableInfo43.equals(tableInfo44)) {
                    return new RoomOpenHelper.ValidationResult(false, "pay_watch_face(com.qcwireless.qcwatch.ui.base.repository.entity.PayWatchFace).\n Expected:\n" + tableInfo43 + "\n Found:\n" + tableInfo44);
                }
                HashMap map23 = new HashMap(9);
                map23.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                map23.put("hardware_version", new TableInfo.Column("hardware_version", "TEXT", true, 1, null, 1));
                map23.put("pre_image_url", new TableInfo.Column("pre_image_url", "TEXT", true, 0, null, 1));
                map23.put("bin_url", new TableInfo.Column("bin_url", "TEXT", true, 0, null, 1));
                map23.put("price", new TableInfo.Column("price", "REAL", true, 0, null, 1));
                map23.put("local_image_url", new TableInfo.Column("local_image_url", "TEXT", true, 0, null, 1));
                map23.put("local_bin_url", new TableInfo.Column("local_bin_url", "TEXT", true, 0, null, 1));
                map23.put("type_id", new TableInfo.Column("type_id", "INTEGER", true, 2, null, 1));
                map23.put("position", new TableInfo.Column("position", "INTEGER", true, 3, null, 1));
                TableInfo tableInfo45 = new TableInfo("watch_face_index", map23, new HashSet(0), new HashSet(0));
                TableInfo tableInfo46 = TableInfo.read(_db, "watch_face_index");
                if (!tableInfo45.equals(tableInfo46)) {
                    return new RoomOpenHelper.ValidationResult(false, "watch_face_index(com.qcwireless.qcwatch.ui.base.repository.entity.WatchFaceIndexEntity).\n Expected:\n" + tableInfo45 + "\n Found:\n" + tableInfo46);
                }
                HashMap map24 = new HashMap(9);
                map24.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 0, null, 1));
                map24.put("music_name", new TableInfo.Column("music_name", "TEXT", true, 1, null, 1));
                map24.put("singer", new TableInfo.Column("singer", "TEXT", true, 0, null, 1));
                map24.put("size", new TableInfo.Column("size", "INTEGER", true, 0, null, 1));
                map24.put(TypedValues.TransitionType.S_DURATION, new TableInfo.Column(TypedValues.TransitionType.S_DURATION, "INTEGER", true, 0, null, 1));
                map24.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, 1));
                map24.put("album_id", new TableInfo.Column("album_id", "INTEGER", true, 0, null, 1));
                map24.put("song_menu_name", new TableInfo.Column("song_menu_name", "TEXT", true, 0, null, 1));
                map24.put("song_menu_id", new TableInfo.Column("song_menu_id", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo47 = new TableInfo("music_to_device", map24, new HashSet(0), new HashSet(0));
                TableInfo tableInfo48 = TableInfo.read(_db, "music_to_device");
                if (!tableInfo47.equals(tableInfo48)) {
                    return new RoomOpenHelper.ValidationResult(false, "music_to_device(com.qcwireless.qcwatch.ui.base.repository.entity.MusicToDeviceEntity).\n Expected:\n" + tableInfo47 + "\n Found:\n" + tableInfo48);
                }
                HashMap map25 = new HashMap(3);
                map25.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 0, null, 1));
                map25.put("menu_id", new TableInfo.Column("menu_id", "INTEGER", true, 1, null, 1));
                map25.put("menu_name", new TableInfo.Column("menu_name", "TEXT", true, 0, null, 1));
                TableInfo tableInfo49 = new TableInfo("song_menu", map25, new HashSet(0), new HashSet(0));
                TableInfo tableInfo50 = TableInfo.read(_db, "song_menu");
                if (!tableInfo49.equals(tableInfo50)) {
                    return new RoomOpenHelper.ValidationResult(false, "song_menu(com.qcwireless.qcwatch.ui.base.repository.entity.SongMenuEntity).\n Expected:\n" + tableInfo49 + "\n Found:\n" + tableInfo50);
                }
                HashMap map26 = new HashMap(3);
                map26.put("book_name", new TableInfo.Column("book_name", "TEXT", true, 1, null, 1));
                map26.put("first_name", new TableInfo.Column("first_name", "TEXT", true, 0, null, 1));
                map26.put("file_path", new TableInfo.Column("file_path", "TEXT", true, 0, null, 1));
                TableInfo tableInfo51 = new TableInfo("ebook_entity", map26, new HashSet(0), new HashSet(0));
                TableInfo tableInfo52 = TableInfo.read(_db, "ebook_entity");
                if (!tableInfo51.equals(tableInfo52)) {
                    return new RoomOpenHelper.ValidationResult(false, "ebook_entity(com.qcwireless.qcwatch.ui.base.repository.entity.EbookEntity).\n Expected:\n" + tableInfo51 + "\n Found:\n" + tableInfo52);
                }
                HashMap map27 = new HashMap(7);
                map27.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map27.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map27.put("min_array", new TableInfo.Column("min_array", "TEXT", true, 0, null, 1));
                map27.put("max_array", new TableInfo.Column("max_array", "TEXT", true, 0, null, 1));
                map27.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                map27.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map27.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo53 = new TableInfo("blood_sugar", map27, new HashSet(0), new HashSet(0));
                TableInfo tableInfo54 = TableInfo.read(_db, "blood_sugar");
                if (!tableInfo53.equals(tableInfo54)) {
                    return new RoomOpenHelper.ValidationResult(false, "blood_sugar(com.qcwireless.qcwatch.ui.base.repository.entity.BloodSugarEntity).\n Expected:\n" + tableInfo53 + "\n Found:\n" + tableInfo54);
                }
                HashMap map28 = new HashMap(6);
                map28.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map28.put("goal_steps", new TableInfo.Column("goal_steps", "INTEGER", true, 0, null, 1));
                map28.put("goal_calorie", new TableInfo.Column("goal_calorie", "REAL", true, 0, null, 1));
                map28.put("goal_distance", new TableInfo.Column("goal_distance", "REAL", true, 0, null, 1));
                map28.put("goal_sport_time", new TableInfo.Column("goal_sport_time", "REAL", true, 0, null, 1));
                map28.put("goal_sleep_time", new TableInfo.Column("goal_sleep_time", "REAL", true, 0, null, 1));
                TableInfo tableInfo55 = new TableInfo("target_entity", map28, new HashSet(0), new HashSet(0));
                TableInfo tableInfo56 = TableInfo.read(_db, "target_entity");
                if (!tableInfo55.equals(tableInfo56)) {
                    return new RoomOpenHelper.ValidationResult(false, "target_entity(com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity).\n Expected:\n" + tableInfo55 + "\n Found:\n" + tableInfo56);
                }
                HashMap map29 = new HashMap(8);
                map29.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map29.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map29.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
                map29.put("index_str", new TableInfo.Column("index_str", "TEXT", true, 0, null, 1));
                map29.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, 1));
                map29.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                map29.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map29.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo57 = new TableInfo("hrv_detail", map29, new HashSet(0), new HashSet(0));
                TableInfo tableInfo58 = TableInfo.read(_db, "hrv_detail");
                if (!tableInfo57.equals(tableInfo58)) {
                    return new RoomOpenHelper.ValidationResult(false, "hrv_detail(com.qcwireless.qcwatch.ui.base.repository.entity.HRVDetail).\n Expected:\n" + tableInfo57 + "\n Found:\n" + tableInfo58);
                }
                HashMap map30 = new HashMap(4);
                map30.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, null, 1));
                map30.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 0, null, 1));
                map30.put("hrv", new TableInfo.Column("hrv", "INTEGER", true, 0, null, 1));
                map30.put(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, new TableInfo.Column(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, "INTEGER", true, 2, null, 1));
                TableInfo tableInfo59 = new TableInfo("hrv_manual", map30, new HashSet(0), new HashSet(0));
                TableInfo tableInfo60 = TableInfo.read(_db, "hrv_manual");
                if (!tableInfo59.equals(tableInfo60)) {
                    return new RoomOpenHelper.ValidationResult(false, "hrv_manual(com.qcwireless.qcwatch.ui.base.repository.entity.HRVManualEntity).\n Expected:\n" + tableInfo59 + "\n Found:\n" + tableInfo60);
                }
                HashMap map31 = new HashMap(8);
                map31.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map31.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map31.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
                map31.put("index_str", new TableInfo.Column("index_str", "TEXT", true, 0, null, 1));
                map31.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, 1));
                map31.put("unix_time", new TableInfo.Column("unix_time", "INTEGER", true, 0, null, 1));
                map31.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map31.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo61 = new TableInfo("pressure_detail", map31, new HashSet(0), new HashSet(0));
                TableInfo tableInfo62 = TableInfo.read(_db, "pressure_detail");
                if (!tableInfo61.equals(tableInfo62)) {
                    return new RoomOpenHelper.ValidationResult(false, "pressure_detail(com.qcwireless.qcwatch.ui.base.repository.entity.PressureDetail).\n Expected:\n" + tableInfo61 + "\n Found:\n" + tableInfo62);
                }
                HashMap map32 = new HashMap(4);
                map32.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, null, 1));
                map32.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 0, null, 1));
                map32.put("pressure", new TableInfo.Column("pressure", "INTEGER", true, 0, null, 1));
                map32.put(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, new TableInfo.Column(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, "INTEGER", true, 2, null, 1));
                TableInfo tableInfo63 = new TableInfo("pressure_manual", map32, new HashSet(0), new HashSet(0));
                TableInfo tableInfo64 = TableInfo.read(_db, "pressure_manual");
                if (!tableInfo63.equals(tableInfo64)) {
                    return new RoomOpenHelper.ValidationResult(false, "pressure_manual(com.qcwireless.qcwatch.ui.base.repository.entity.PressureManualEntity).\n Expected:\n" + tableInfo63 + "\n Found:\n" + tableInfo64);
                }
                HashMap map33 = new HashMap(7);
                map33.put("device_address", new TableInfo.Column("device_address", "TEXT", true, 1, null, 1));
                map33.put("date_str", new TableInfo.Column("date_str", "TEXT", true, 2, null, 1));
                map33.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, 1));
                map33.put("lunch_st", new TableInfo.Column("lunch_st", "INTEGER", true, 0, null, 1));
                map33.put("lunch_et", new TableInfo.Column("lunch_et", "INTEGER", true, 0, null, 1));
                map33.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, 1));
                map33.put("last_sync_time", new TableInfo.Column("last_sync_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo65 = new TableInfo("sleep_lunch_protocol", map33, new HashSet(0), new HashSet(0));
                TableInfo tableInfo66 = TableInfo.read(_db, "sleep_lunch_protocol");
                if (!tableInfo65.equals(tableInfo66)) {
                    return new RoomOpenHelper.ValidationResult(false, "sleep_lunch_protocol(com.qcwireless.qcwatch.ui.base.repository.entity.SleepLunchProtocol).\n Expected:\n" + tableInfo65 + "\n Found:\n" + tableInfo66);
                }
                HashMap map34 = new HashMap(9);
                map34.put("theme_name", new TableInfo.Column("theme_name", "TEXT", true, 1, null, 1));
                map34.put("hardware_version", new TableInfo.Column("hardware_version", "TEXT", true, 2, null, 1));
                map34.put("pre_image_url", new TableInfo.Column("pre_image_url", "TEXT", true, 0, null, 1));
                map34.put("bin_url", new TableInfo.Column("bin_url", "TEXT", true, 0, null, 1));
                map34.put("theme_desc", new TableInfo.Column("theme_desc", "TEXT", true, 0, null, 1));
                map34.put("local_image_url", new TableInfo.Column("local_image_url", "TEXT", true, 0, null, 1));
                map34.put("local_bin_url", new TableInfo.Column("local_bin_url", "TEXT", true, 0, null, 1));
                map34.put("theme_type", new TableInfo.Column("theme_type", "INTEGER", true, 0, null, 1));
                map34.put("app_home_index", new TableInfo.Column("app_home_index", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo67 = new TableInfo("watch_theme_face", map34, new HashSet(0), new HashSet(0));
                TableInfo tableInfo68 = TableInfo.read(_db, "watch_theme_face");
                if (!tableInfo67.equals(tableInfo68)) {
                    return new RoomOpenHelper.ValidationResult(false, "watch_theme_face(com.qcwireless.qcwatch.ui.base.repository.entity.WatchThemeFace).\n Expected:\n" + tableInfo67 + "\n Found:\n" + tableInfo68);
                }
                HashMap map35 = new HashMap(11);
                map35.put("wallpaper_name", new TableInfo.Column("wallpaper_name", "TEXT", true, 1, null, 1));
                map35.put("hardware_version", new TableInfo.Column("hardware_version", "TEXT", true, 2, null, 1));
                map35.put("wallpaper_desc", new TableInfo.Column("wallpaper_desc", "TEXT", true, 0, null, 1));
                map35.put("wallpaper_url", new TableInfo.Column("wallpaper_url", "TEXT", true, 0, null, 1));
                map35.put("wallpaper_file_url", new TableInfo.Column("wallpaper_file_url", "TEXT", true, 0, null, 1));
                map35.put("wallpaper_type_app_index", new TableInfo.Column("wallpaper_type_app_index", "INTEGER", true, 0, null, 1));
                map35.put("wallpaper_app_index", new TableInfo.Column("wallpaper_app_index", "INTEGER", true, 0, null, 1));
                map35.put("local_image_url", new TableInfo.Column("local_image_url", "TEXT", true, 0, null, 1));
                map35.put("local_bin_url", new TableInfo.Column("local_bin_url", "TEXT", true, 0, null, 1));
                map35.put("wallpaper_type", new TableInfo.Column("wallpaper_type", "INTEGER", true, 0, null, 1));
                map35.put("wallpaper_type_name", new TableInfo.Column("wallpaper_type_name", "TEXT", true, 0, null, 1));
                TableInfo tableInfo69 = new TableInfo("watch_wallpaper_face", map35, new HashSet(0), new HashSet(0));
                TableInfo tableInfo70 = TableInfo.read(_db, "watch_wallpaper_face");
                if (!tableInfo69.equals(tableInfo70)) {
                    return new RoomOpenHelper.ValidationResult(false, "watch_wallpaper_face(com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace).\n Expected:\n" + tableInfo69 + "\n Found:\n" + tableInfo70);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "fda683251526fbca740b7dbbbd5f82a9", "8d932097939a3f97cd9f52c0daf99570")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), ChatEnumAction.USER, "device_setting", "step_detail", "sleep_detail", "step_total", "sleep_total", "heart_rate_detail", "sport_plus_detail", "watch_face", "menstruation", "message_push", "sync_entity", "gps_detail", "blood_pressure", "blood_oxygen", "feedback", "custom_face", "temperature_entity", "sleep_new_protocol", "contact_entity", "manual_heart_entity", "pay_watch_face", "watch_face_index", "music_to_device", "song_menu", "ebook_entity", "blood_sugar", "target_entity", "hrv_detail", "hrv_manual", "pressure_detail", "pressure_manual", "sleep_lunch_protocol", "watch_theme_face", "watch_wallpaper_face");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() throws SQLException {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `user`");
            writableDatabase.execSQL("DELETE FROM `device_setting`");
            writableDatabase.execSQL("DELETE FROM `step_detail`");
            writableDatabase.execSQL("DELETE FROM `sleep_detail`");
            writableDatabase.execSQL("DELETE FROM `step_total`");
            writableDatabase.execSQL("DELETE FROM `sleep_total`");
            writableDatabase.execSQL("DELETE FROM `heart_rate_detail`");
            writableDatabase.execSQL("DELETE FROM `sport_plus_detail`");
            writableDatabase.execSQL("DELETE FROM `watch_face`");
            writableDatabase.execSQL("DELETE FROM `menstruation`");
            writableDatabase.execSQL("DELETE FROM `message_push`");
            writableDatabase.execSQL("DELETE FROM `sync_entity`");
            writableDatabase.execSQL("DELETE FROM `gps_detail`");
            writableDatabase.execSQL("DELETE FROM `blood_pressure`");
            writableDatabase.execSQL("DELETE FROM `blood_oxygen`");
            writableDatabase.execSQL("DELETE FROM `feedback`");
            writableDatabase.execSQL("DELETE FROM `custom_face`");
            writableDatabase.execSQL("DELETE FROM `temperature_entity`");
            writableDatabase.execSQL("DELETE FROM `sleep_new_protocol`");
            writableDatabase.execSQL("DELETE FROM `contact_entity`");
            writableDatabase.execSQL("DELETE FROM `manual_heart_entity`");
            writableDatabase.execSQL("DELETE FROM `pay_watch_face`");
            writableDatabase.execSQL("DELETE FROM `watch_face_index`");
            writableDatabase.execSQL("DELETE FROM `music_to_device`");
            writableDatabase.execSQL("DELETE FROM `song_menu`");
            writableDatabase.execSQL("DELETE FROM `ebook_entity`");
            writableDatabase.execSQL("DELETE FROM `blood_sugar`");
            writableDatabase.execSQL("DELETE FROM `target_entity`");
            writableDatabase.execSQL("DELETE FROM `hrv_detail`");
            writableDatabase.execSQL("DELETE FROM `hrv_manual`");
            writableDatabase.execSQL("DELETE FROM `pressure_detail`");
            writableDatabase.execSQL("DELETE FROM `pressure_manual`");
            writableDatabase.execSQL("DELETE FROM `sleep_lunch_protocol`");
            writableDatabase.execSQL("DELETE FROM `watch_theme_face`");
            writableDatabase.execSQL("DELETE FROM `watch_wallpaper_face`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(QcUserDao.class, QcUserDao_Impl.getRequiredConverters());
        map.put(QcDeviceSettingDao.class, QcDeviceSettingDao_Impl.getRequiredConverters());
        map.put(QcStepDetailDao.class, QcStepDetailDao_Impl.getRequiredConverters());
        map.put(QcSleepDetailDao.class, QcSleepDetailDao_Impl.getRequiredConverters());
        map.put(QcStepTotalDao.class, QcStepTotalDao_Impl.getRequiredConverters());
        map.put(QcSleepTotalDao.class, QcSleepTotalDao_Impl.getRequiredConverters());
        map.put(QcHeartRateDetailDao.class, QcHeartRateDetailDao_Impl.getRequiredConverters());
        map.put(QcSportPlusDetailDao.class, QcSportPlusDetailDao_Impl.getRequiredConverters());
        map.put(QcWatchFaceDao.class, QcWatchFaceDao_Impl.getRequiredConverters());
        map.put(QcMenstruationDao.class, QcMenstruationDao_Impl.getRequiredConverters());
        map.put(QcMessagePushDao.class, QcMessagePushDao_Impl.getRequiredConverters());
        map.put(QcSyncDao.class, QcSyncDao_Impl.getRequiredConverters());
        map.put(QcGpsDetailDao.class, QcGpsDetailDao_Impl.getRequiredConverters());
        map.put(QcBloodPressureDao.class, QcBloodPressureDao_Impl.getRequiredConverters());
        map.put(QcBloodOxygenDao.class, QcBloodOxygenDao_Impl.getRequiredConverters());
        map.put(QcFeedbackDao.class, QcFeedbackDao_Impl.getRequiredConverters());
        map.put(QcCustomFaceDao.class, QcCustomFaceDao_Impl.getRequiredConverters());
        map.put(QcTemperatureDao.class, QcTemperatureDao_Impl.getRequiredConverters());
        map.put(QcSleepNewProtocolDao.class, QcSleepNewProtocolDao_Impl.getRequiredConverters());
        map.put(QcContactsDao.class, QcContactsDao_Impl.getRequiredConverters());
        map.put(QcManualHeartDao.class, QcManualHeartDao_Impl.getRequiredConverters());
        map.put(QcWatchFaceIndexDao.class, QcWatchFaceIndexDao_Impl.getRequiredConverters());
        map.put(QcMusicManagerDao.class, QcMusicManagerDao_Impl.getRequiredConverters());
        map.put(QcMusicMenuDao.class, QcMusicMenuDao_Impl.getRequiredConverters());
        map.put(QcEbookDao.class, QcEbookDao_Impl.getRequiredConverters());
        map.put(QcBloodSugarDao.class, QcBloodSugarDao_Impl.getRequiredConverters());
        map.put(QcTargetDao.class, QcTargetDao_Impl.getRequiredConverters());
        map.put(QcSleepLunchProtocolDao.class, QcSleepLunchProtocolDao_Impl.getRequiredConverters());
        map.put(QcPressureDetailDao.class, QcPressureDetailDao_Impl.getRequiredConverters());
        map.put(QcHrvDetailDao.class, QcHrvDetailDao_Impl.getRequiredConverters());
        map.put(ManualPressureDao.class, ManualPressureDao_Impl.getRequiredConverters());
        map.put(ManualHrvDao.class, ManualHrvDao_Impl.getRequiredConverters());
        map.put(QcWatchThemeDao.class, QcWatchThemeDao_Impl.getRequiredConverters());
        map.put(QcWatchWallpaperDao.class, QcWatchWallpaperDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcUserDao qcUserDao() {
        QcUserDao qcUserDao;
        if (this._qcUserDao != null) {
            return this._qcUserDao;
        }
        synchronized (this) {
            if (this._qcUserDao == null) {
                this._qcUserDao = new QcUserDao_Impl(this);
            }
            qcUserDao = this._qcUserDao;
        }
        return qcUserDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcDeviceSettingDao qcDeviceSettingDao() {
        QcDeviceSettingDao qcDeviceSettingDao;
        if (this._qcDeviceSettingDao != null) {
            return this._qcDeviceSettingDao;
        }
        synchronized (this) {
            if (this._qcDeviceSettingDao == null) {
                this._qcDeviceSettingDao = new QcDeviceSettingDao_Impl(this);
            }
            qcDeviceSettingDao = this._qcDeviceSettingDao;
        }
        return qcDeviceSettingDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcStepDetailDao qcStepDetailDao() {
        QcStepDetailDao qcStepDetailDao;
        if (this._qcStepDetailDao != null) {
            return this._qcStepDetailDao;
        }
        synchronized (this) {
            if (this._qcStepDetailDao == null) {
                this._qcStepDetailDao = new QcStepDetailDao_Impl(this);
            }
            qcStepDetailDao = this._qcStepDetailDao;
        }
        return qcStepDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcSleepDetailDao qcSleepDetailDao() {
        QcSleepDetailDao qcSleepDetailDao;
        if (this._qcSleepDetailDao != null) {
            return this._qcSleepDetailDao;
        }
        synchronized (this) {
            if (this._qcSleepDetailDao == null) {
                this._qcSleepDetailDao = new QcSleepDetailDao_Impl(this);
            }
            qcSleepDetailDao = this._qcSleepDetailDao;
        }
        return qcSleepDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcStepTotalDao qcStepTotalDao() {
        QcStepTotalDao qcStepTotalDao;
        if (this._qcStepTotalDao != null) {
            return this._qcStepTotalDao;
        }
        synchronized (this) {
            if (this._qcStepTotalDao == null) {
                this._qcStepTotalDao = new QcStepTotalDao_Impl(this);
            }
            qcStepTotalDao = this._qcStepTotalDao;
        }
        return qcStepTotalDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcSleepTotalDao qcSleepTotalDao() {
        QcSleepTotalDao qcSleepTotalDao;
        if (this._qcSleepTotalDao != null) {
            return this._qcSleepTotalDao;
        }
        synchronized (this) {
            if (this._qcSleepTotalDao == null) {
                this._qcSleepTotalDao = new QcSleepTotalDao_Impl(this);
            }
            qcSleepTotalDao = this._qcSleepTotalDao;
        }
        return qcSleepTotalDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcHeartRateDetailDao qcHeartRateDao() {
        QcHeartRateDetailDao qcHeartRateDetailDao;
        if (this._qcHeartRateDetailDao != null) {
            return this._qcHeartRateDetailDao;
        }
        synchronized (this) {
            if (this._qcHeartRateDetailDao == null) {
                this._qcHeartRateDetailDao = new QcHeartRateDetailDao_Impl(this);
            }
            qcHeartRateDetailDao = this._qcHeartRateDetailDao;
        }
        return qcHeartRateDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcSportPlusDetailDao qcSportPlusDao() {
        QcSportPlusDetailDao qcSportPlusDetailDao;
        if (this._qcSportPlusDetailDao != null) {
            return this._qcSportPlusDetailDao;
        }
        synchronized (this) {
            if (this._qcSportPlusDetailDao == null) {
                this._qcSportPlusDetailDao = new QcSportPlusDetailDao_Impl(this);
            }
            qcSportPlusDetailDao = this._qcSportPlusDetailDao;
        }
        return qcSportPlusDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcWatchFaceDao qcWatchFaceDao() {
        QcWatchFaceDao qcWatchFaceDao;
        if (this._qcWatchFaceDao != null) {
            return this._qcWatchFaceDao;
        }
        synchronized (this) {
            if (this._qcWatchFaceDao == null) {
                this._qcWatchFaceDao = new QcWatchFaceDao_Impl(this);
            }
            qcWatchFaceDao = this._qcWatchFaceDao;
        }
        return qcWatchFaceDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcMenstruationDao qcMenstruationDao() {
        QcMenstruationDao qcMenstruationDao;
        if (this._qcMenstruationDao != null) {
            return this._qcMenstruationDao;
        }
        synchronized (this) {
            if (this._qcMenstruationDao == null) {
                this._qcMenstruationDao = new QcMenstruationDao_Impl(this);
            }
            qcMenstruationDao = this._qcMenstruationDao;
        }
        return qcMenstruationDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcMessagePushDao qcMessagePushDao() {
        QcMessagePushDao qcMessagePushDao;
        if (this._qcMessagePushDao != null) {
            return this._qcMessagePushDao;
        }
        synchronized (this) {
            if (this._qcMessagePushDao == null) {
                this._qcMessagePushDao = new QcMessagePushDao_Impl(this);
            }
            qcMessagePushDao = this._qcMessagePushDao;
        }
        return qcMessagePushDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcSyncDao qcSyncDao() {
        QcSyncDao qcSyncDao;
        if (this._qcSyncDao != null) {
            return this._qcSyncDao;
        }
        synchronized (this) {
            if (this._qcSyncDao == null) {
                this._qcSyncDao = new QcSyncDao_Impl(this);
            }
            qcSyncDao = this._qcSyncDao;
        }
        return qcSyncDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcGpsDetailDao qcGpsDao() {
        QcGpsDetailDao qcGpsDetailDao;
        if (this._qcGpsDetailDao != null) {
            return this._qcGpsDetailDao;
        }
        synchronized (this) {
            if (this._qcGpsDetailDao == null) {
                this._qcGpsDetailDao = new QcGpsDetailDao_Impl(this);
            }
            qcGpsDetailDao = this._qcGpsDetailDao;
        }
        return qcGpsDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcBloodPressureDao qcBloodPressureDao() {
        QcBloodPressureDao qcBloodPressureDao;
        if (this._qcBloodPressureDao != null) {
            return this._qcBloodPressureDao;
        }
        synchronized (this) {
            if (this._qcBloodPressureDao == null) {
                this._qcBloodPressureDao = new QcBloodPressureDao_Impl(this);
            }
            qcBloodPressureDao = this._qcBloodPressureDao;
        }
        return qcBloodPressureDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcBloodOxygenDao qcBloodOxygenDao() {
        QcBloodOxygenDao qcBloodOxygenDao;
        if (this._qcBloodOxygenDao != null) {
            return this._qcBloodOxygenDao;
        }
        synchronized (this) {
            if (this._qcBloodOxygenDao == null) {
                this._qcBloodOxygenDao = new QcBloodOxygenDao_Impl(this);
            }
            qcBloodOxygenDao = this._qcBloodOxygenDao;
        }
        return qcBloodOxygenDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcFeedbackDao qcFeedbackDao() {
        QcFeedbackDao qcFeedbackDao;
        if (this._qcFeedbackDao != null) {
            return this._qcFeedbackDao;
        }
        synchronized (this) {
            if (this._qcFeedbackDao == null) {
                this._qcFeedbackDao = new QcFeedbackDao_Impl(this);
            }
            qcFeedbackDao = this._qcFeedbackDao;
        }
        return qcFeedbackDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcCustomFaceDao qcCustomFaceDao() {
        QcCustomFaceDao qcCustomFaceDao;
        if (this._qcCustomFaceDao != null) {
            return this._qcCustomFaceDao;
        }
        synchronized (this) {
            if (this._qcCustomFaceDao == null) {
                this._qcCustomFaceDao = new QcCustomFaceDao_Impl(this);
            }
            qcCustomFaceDao = this._qcCustomFaceDao;
        }
        return qcCustomFaceDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcTemperatureDao qcTemperatureDao() {
        QcTemperatureDao qcTemperatureDao;
        if (this._qcTemperatureDao != null) {
            return this._qcTemperatureDao;
        }
        synchronized (this) {
            if (this._qcTemperatureDao == null) {
                this._qcTemperatureDao = new QcTemperatureDao_Impl(this);
            }
            qcTemperatureDao = this._qcTemperatureDao;
        }
        return qcTemperatureDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcSleepNewProtocolDao qcSleepNewProtocolDao() {
        QcSleepNewProtocolDao qcSleepNewProtocolDao;
        if (this._qcSleepNewProtocolDao != null) {
            return this._qcSleepNewProtocolDao;
        }
        synchronized (this) {
            if (this._qcSleepNewProtocolDao == null) {
                this._qcSleepNewProtocolDao = new QcSleepNewProtocolDao_Impl(this);
            }
            qcSleepNewProtocolDao = this._qcSleepNewProtocolDao;
        }
        return qcSleepNewProtocolDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcContactsDao qcContactDao() {
        QcContactsDao qcContactsDao;
        if (this._qcContactsDao != null) {
            return this._qcContactsDao;
        }
        synchronized (this) {
            if (this._qcContactsDao == null) {
                this._qcContactsDao = new QcContactsDao_Impl(this);
            }
            qcContactsDao = this._qcContactsDao;
        }
        return qcContactsDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcManualHeartDao qcManualHeartDao() {
        QcManualHeartDao qcManualHeartDao;
        if (this._qcManualHeartDao != null) {
            return this._qcManualHeartDao;
        }
        synchronized (this) {
            if (this._qcManualHeartDao == null) {
                this._qcManualHeartDao = new QcManualHeartDao_Impl(this);
            }
            qcManualHeartDao = this._qcManualHeartDao;
        }
        return qcManualHeartDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcWatchFaceIndexDao qcWatchFaceIndexDao() {
        QcWatchFaceIndexDao qcWatchFaceIndexDao;
        if (this._qcWatchFaceIndexDao != null) {
            return this._qcWatchFaceIndexDao;
        }
        synchronized (this) {
            if (this._qcWatchFaceIndexDao == null) {
                this._qcWatchFaceIndexDao = new QcWatchFaceIndexDao_Impl(this);
            }
            qcWatchFaceIndexDao = this._qcWatchFaceIndexDao;
        }
        return qcWatchFaceIndexDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcMusicManagerDao qcMusicManagerDao() {
        QcMusicManagerDao qcMusicManagerDao;
        if (this._qcMusicManagerDao != null) {
            return this._qcMusicManagerDao;
        }
        synchronized (this) {
            if (this._qcMusicManagerDao == null) {
                this._qcMusicManagerDao = new QcMusicManagerDao_Impl(this);
            }
            qcMusicManagerDao = this._qcMusicManagerDao;
        }
        return qcMusicManagerDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcMusicMenuDao qcMusicMenuDao() {
        QcMusicMenuDao qcMusicMenuDao;
        if (this._qcMusicMenuDao != null) {
            return this._qcMusicMenuDao;
        }
        synchronized (this) {
            if (this._qcMusicMenuDao == null) {
                this._qcMusicMenuDao = new QcMusicMenuDao_Impl(this);
            }
            qcMusicMenuDao = this._qcMusicMenuDao;
        }
        return qcMusicMenuDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcEbookDao qcEbookDao() {
        QcEbookDao qcEbookDao;
        if (this._qcEbookDao != null) {
            return this._qcEbookDao;
        }
        synchronized (this) {
            if (this._qcEbookDao == null) {
                this._qcEbookDao = new QcEbookDao_Impl(this);
            }
            qcEbookDao = this._qcEbookDao;
        }
        return qcEbookDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcBloodSugarDao qcBloodSugar() {
        QcBloodSugarDao qcBloodSugarDao;
        if (this._qcBloodSugarDao != null) {
            return this._qcBloodSugarDao;
        }
        synchronized (this) {
            if (this._qcBloodSugarDao == null) {
                this._qcBloodSugarDao = new QcBloodSugarDao_Impl(this);
            }
            qcBloodSugarDao = this._qcBloodSugarDao;
        }
        return qcBloodSugarDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcTargetDao qcTargetDao() {
        QcTargetDao qcTargetDao;
        if (this._qcTargetDao != null) {
            return this._qcTargetDao;
        }
        synchronized (this) {
            if (this._qcTargetDao == null) {
                this._qcTargetDao = new QcTargetDao_Impl(this);
            }
            qcTargetDao = this._qcTargetDao;
        }
        return qcTargetDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcSleepLunchProtocolDao qcSleepLunchProtocolDao() {
        QcSleepLunchProtocolDao qcSleepLunchProtocolDao;
        if (this._qcSleepLunchProtocolDao != null) {
            return this._qcSleepLunchProtocolDao;
        }
        synchronized (this) {
            if (this._qcSleepLunchProtocolDao == null) {
                this._qcSleepLunchProtocolDao = new QcSleepLunchProtocolDao_Impl(this);
            }
            qcSleepLunchProtocolDao = this._qcSleepLunchProtocolDao;
        }
        return qcSleepLunchProtocolDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcPressureDetailDao qcPressureDao() {
        QcPressureDetailDao qcPressureDetailDao;
        if (this._qcPressureDetailDao != null) {
            return this._qcPressureDetailDao;
        }
        synchronized (this) {
            if (this._qcPressureDetailDao == null) {
                this._qcPressureDetailDao = new QcPressureDetailDao_Impl(this);
            }
            qcPressureDetailDao = this._qcPressureDetailDao;
        }
        return qcPressureDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcHrvDetailDao qcHrvDetailDao() {
        QcHrvDetailDao qcHrvDetailDao;
        if (this._qcHrvDetailDao != null) {
            return this._qcHrvDetailDao;
        }
        synchronized (this) {
            if (this._qcHrvDetailDao == null) {
                this._qcHrvDetailDao = new QcHrvDetailDao_Impl(this);
            }
            qcHrvDetailDao = this._qcHrvDetailDao;
        }
        return qcHrvDetailDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public ManualPressureDao manualPressureDao() {
        ManualPressureDao manualPressureDao;
        if (this._manualPressureDao != null) {
            return this._manualPressureDao;
        }
        synchronized (this) {
            if (this._manualPressureDao == null) {
                this._manualPressureDao = new ManualPressureDao_Impl(this);
            }
            manualPressureDao = this._manualPressureDao;
        }
        return manualPressureDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public ManualHrvDao manualHrvDao() {
        ManualHrvDao manualHrvDao;
        if (this._manualHrvDao != null) {
            return this._manualHrvDao;
        }
        synchronized (this) {
            if (this._manualHrvDao == null) {
                this._manualHrvDao = new ManualHrvDao_Impl(this);
            }
            manualHrvDao = this._manualHrvDao;
        }
        return manualHrvDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcWatchThemeDao qcWatchThemeDao() {
        QcWatchThemeDao qcWatchThemeDao;
        if (this._qcWatchThemeDao != null) {
            return this._qcWatchThemeDao;
        }
        synchronized (this) {
            if (this._qcWatchThemeDao == null) {
                this._qcWatchThemeDao = new QcWatchThemeDao_Impl(this);
            }
            qcWatchThemeDao = this._qcWatchThemeDao;
        }
        return qcWatchThemeDao;
    }

    @Override // com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase
    public QcWatchWallpaperDao qcWatchWallpaperDao() {
        QcWatchWallpaperDao qcWatchWallpaperDao;
        if (this._qcWatchWallpaperDao != null) {
            return this._qcWatchWallpaperDao;
        }
        synchronized (this) {
            if (this._qcWatchWallpaperDao == null) {
                this._qcWatchWallpaperDao = new QcWatchWallpaperDao_Impl(this);
            }
            qcWatchWallpaperDao = this._qcWatchWallpaperDao;
        }
        return qcWatchWallpaperDao;
    }
}
