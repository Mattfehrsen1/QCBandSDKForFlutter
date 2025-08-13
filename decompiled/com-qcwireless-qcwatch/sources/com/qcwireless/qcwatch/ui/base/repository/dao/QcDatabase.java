package com.qcwireless.qcwatch.ui.base.repository.dao;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QcDatabase.kt */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 G2\u00020\u0001:\u0001GB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\b\u0010#\u001a\u00020$H&J\b\u0010%\u001a\u00020&H&J\b\u0010'\u001a\u00020(H&J\b\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020,H&J\b\u0010-\u001a\u00020.H&J\b\u0010/\u001a\u000200H&J\b\u00101\u001a\u000202H&J\b\u00103\u001a\u000204H&J\b\u00105\u001a\u000206H&J\b\u00107\u001a\u000208H&J\b\u00109\u001a\u00020:H&J\b\u0010;\u001a\u00020<H&J\b\u0010=\u001a\u00020>H&J\b\u0010?\u001a\u00020@H&J\b\u0010A\u001a\u00020BH&J\b\u0010C\u001a\u00020DH&J\b\u0010E\u001a\u00020FH&¨\u0006H"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDatabase;", "Landroidx/room/RoomDatabase;", "()V", "manualHrvDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/ManualHrvDao;", "manualPressureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/ManualPressureDao;", "qcBloodOxygenDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodOxygenDao;", "qcBloodPressureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodPressureDao;", "qcBloodSugar", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodSugarDao;", "qcContactDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcContactsDao;", "qcCustomFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcCustomFaceDao;", "qcDeviceSettingDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDeviceSettingDao;", "qcEbookDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcEbookDao;", "qcFeedbackDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcFeedbackDao;", "qcGpsDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcGpsDetailDao;", "qcHeartRateDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcHeartRateDetailDao;", "qcHrvDetailDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcHrvDetailDao;", "qcManualHeartDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcManualHeartDao;", "qcMenstruationDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMenstruationDao;", "qcMessagePushDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMessagePushDao;", "qcMusicManagerDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMusicManagerDao;", "qcMusicMenuDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMusicMenuDao;", "qcPressureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcPressureDetailDao;", "qcSleepDetailDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepDetailDao;", "qcSleepLunchProtocolDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepLunchProtocolDao;", "qcSleepNewProtocolDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepNewProtocolDao;", "qcSleepTotalDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepTotalDao;", "qcSportPlusDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSportPlusDetailDao;", "qcStepDetailDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcStepDetailDao;", "qcStepTotalDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcStepTotalDao;", "qcSyncDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSyncDao;", "qcTargetDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcTargetDao;", "qcTemperatureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcTemperatureDao;", "qcUserDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcUserDao;", "qcWatchFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchFaceDao;", "qcWatchFaceIndexDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchFaceIndexDao;", "qcWatchThemeDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchThemeDao;", "qcWatchWallpaperDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchWallpaperDao;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class QcDatabase extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile QcDatabase INSTANCE;

    public abstract ManualHrvDao manualHrvDao();

    public abstract ManualPressureDao manualPressureDao();

    public abstract QcBloodOxygenDao qcBloodOxygenDao();

    public abstract QcBloodPressureDao qcBloodPressureDao();

    public abstract QcBloodSugarDao qcBloodSugar();

    public abstract QcContactsDao qcContactDao();

    public abstract QcCustomFaceDao qcCustomFaceDao();

    public abstract QcDeviceSettingDao qcDeviceSettingDao();

    public abstract QcEbookDao qcEbookDao();

    public abstract QcFeedbackDao qcFeedbackDao();

    public abstract QcGpsDetailDao qcGpsDao();

    public abstract QcHeartRateDetailDao qcHeartRateDao();

    public abstract QcHrvDetailDao qcHrvDetailDao();

    public abstract QcManualHeartDao qcManualHeartDao();

    public abstract QcMenstruationDao qcMenstruationDao();

    public abstract QcMessagePushDao qcMessagePushDao();

    public abstract QcMusicManagerDao qcMusicManagerDao();

    public abstract QcMusicMenuDao qcMusicMenuDao();

    public abstract QcPressureDetailDao qcPressureDao();

    public abstract QcSleepDetailDao qcSleepDetailDao();

    public abstract QcSleepLunchProtocolDao qcSleepLunchProtocolDao();

    public abstract QcSleepNewProtocolDao qcSleepNewProtocolDao();

    public abstract QcSleepTotalDao qcSleepTotalDao();

    public abstract QcSportPlusDetailDao qcSportPlusDao();

    public abstract QcStepDetailDao qcStepDetailDao();

    public abstract QcStepTotalDao qcStepTotalDao();

    public abstract QcSyncDao qcSyncDao();

    public abstract QcTargetDao qcTargetDao();

    public abstract QcTemperatureDao qcTemperatureDao();

    public abstract QcUserDao qcUserDao();

    public abstract QcWatchFaceDao qcWatchFaceDao();

    public abstract QcWatchFaceIndexDao qcWatchFaceIndexDao();

    public abstract QcWatchThemeDao qcWatchThemeDao();

    public abstract QcWatchWallpaperDao qcWatchWallpaperDao();

    /* compiled from: QcDatabase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QcDatabase getDatabase(Context context) {
            QcDatabase qcDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            QcDatabase qcDatabase2 = QcDatabase.INSTANCE;
            if (qcDatabase2 != null) {
                return qcDatabase2;
            }
            synchronized (this) {
                RoomDatabase roomDatabaseBuild = Room.databaseBuilder(context.getApplicationContext(), QcDatabase.class, "qc_database.db").fallbackToDestructiveMigration().build();
                Intrinsics.checkNotNullExpressionValue(roomDatabaseBuild, "databaseBuilder(\n       …                 .build()");
                qcDatabase = (QcDatabase) roomDatabaseBuild;
                Companion companion = QcDatabase.INSTANCE;
                QcDatabase.INSTANCE = qcDatabase;
            }
            return qcDatabase;
        }
    }
}
