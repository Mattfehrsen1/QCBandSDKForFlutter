package com.qcwireless.qcwatch.ui.base.repository.gps;

import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcGpsDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.home.gps.bean.HomeGpsDetail;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GpsRepository.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;", "", "()V", "gpsDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcGpsDetailDao;", "queryGpsDetailByDate", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryGpsDetailByStartTime", "start", "", "queryLastGpsDetail", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/HomeGpsDetail;", "queryTotalDistance", "", "saveGpsDetail", "", "entity", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<GpsRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<GpsRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.gps.GpsRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GpsRepository invoke() {
            return new GpsRepository();
        }
    });
    private final QcGpsDetailDao gpsDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcGpsDao();

    public final void saveGpsDetail(GpsDetail entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        this.gpsDao.insert(entity);
    }

    public final List<GpsDetail> queryGpsDetailByDate(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        QcGpsDetailDao qcGpsDetailDao = this.gpsDao;
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        return qcGpsDetailDao.queryListByDate(y_m_d);
    }

    public final GpsDetail queryGpsDetailByStartTime(long start) {
        return this.gpsDao.queryByStartTime(start);
    }

    public final HomeGpsDetail queryLastGpsDetail() {
        GpsDetail gpsDetailQueryFirstByStartTime = this.gpsDao.queryFirstByStartTime();
        if (gpsDetailQueryFirstByStartTime != null) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                String str = decimalFormat.format(Float.valueOf(gpsDetailQueryFirstByStartTime.getDistance() / 1000));
                Intrinsics.checkNotNullExpressionValue(str, "df.format(detail.distance / 1000)");
                return new HomeGpsDetail(str, gpsDetailQueryFirstByStartTime.getStartTime());
            }
            String str2 = decimalFormat.format(Float.valueOf(MetricUtilsKt.kmToIn(gpsDetailQueryFirstByStartTime.getDistance() / 1000)));
            Intrinsics.checkNotNullExpressionValue(str2, "df.format(kmToIn(detail.distance / 1000))");
            return new HomeGpsDetail(str2, gpsDetailQueryFirstByStartTime.getStartTime());
        }
        return new HomeGpsDetail("--", 0L);
    }

    public final String queryTotalDistance() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        List<GpsDetail> listQueryAll = this.gpsDao.queryAll();
        float f = 0.0f;
        if (listQueryAll != null) {
            Iterator<T> it = listQueryAll.iterator();
            while (it.hasNext()) {
                String str = decimalFormat.format(Float.valueOf(((GpsDetail) it.next()).getDistance() / 1000));
                Intrinsics.checkNotNullExpressionValue(str, "df.format(it.distance/1000)");
                f += Float.parseFloat(str);
            }
        }
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            String str2 = decimalFormat.format(Float.valueOf(f));
            Intrinsics.checkNotNullExpressionValue(str2, "{\n            df.format(distance)\n        }");
            return str2;
        }
        String str3 = decimalFormat.format(Float.valueOf(MetricUtilsKt.kmToIn(f)));
        Intrinsics.checkNotNullExpressionValue(str3, "{\n            df.format(…ToIn(distance))\n        }");
        return str3;
    }

    /* compiled from: GpsRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GpsRepository getGetInstance() {
            return (GpsRepository) GpsRepository.getInstance$delegate.getValue();
        }
    }
}
