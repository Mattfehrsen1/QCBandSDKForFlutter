package com.qcwireless.qcwatch.ui.home.gps.day;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.repository.gps.GpsRepository;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DayGpsFragmentViewModel.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "gpsRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragmentViewModel$GpsDetailUI;", "df", "Ljava/text/DecimalFormat;", "gpsDetailList", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "getGpsDetailList", "()Ljava/util/List;", "setGpsDetailList", "(Ljava/util/List;)V", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "calcTotalData", "", "data", "", "queryDetailByDate", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "GpsDetailUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DayGpsFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<GpsDetailUI> _uiState;
    private DecimalFormat df;
    private List<GpsDetail> gpsDetailList;
    private final GpsRepository gpsRepository;

    public DayGpsFragmentViewModel(GpsRepository gpsRepository) {
        Intrinsics.checkNotNullParameter(gpsRepository, "gpsRepository");
        this.gpsRepository = gpsRepository;
        this.gpsDetailList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    public final List<GpsDetail> getGpsDetailList() {
        return this.gpsDetailList;
    }

    public final void setGpsDetailList(List<GpsDetail> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.gpsDetailList = list;
    }

    public final LiveData<GpsDetailUI> getUiState() {
        return this._uiState;
    }

    public final void queryDetailByDate(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DayGpsFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragmentViewModel.queryDetailByDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DayGpsFragmentViewModel dayGpsFragmentViewModel) {
                invoke2(dayGpsFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DayGpsFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<GpsDetail> listQueryGpsDetailByDate = ktxRunOnBgSingle.gpsRepository.queryGpsDetailByDate(date);
                if (listQueryGpsDetailByDate != null) {
                    ktxRunOnBgSingle.getGpsDetailList().clear();
                    ktxRunOnBgSingle.getGpsDetailList().addAll(listQueryGpsDetailByDate);
                    ktxRunOnBgSingle.calcTotalData(listQueryGpsDetailByDate);
                } else {
                    ktxRunOnBgSingle.getGpsDetailList().clear();
                    ktxRunOnBgSingle._uiState.postValue(new GpsDetailUI("0", "0", "0", "0"));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calcTotalData(List<GpsDetail> data) {
        float calorie = 0.0f;
        float f = 0.0f;
        int duration = 0;
        for (GpsDetail gpsDetail : data) {
            duration += gpsDetail.getDuration() / 1000;
            calorie += gpsDetail.getCalorie();
            String str = this.df.format(Float.valueOf(gpsDetail.getDistance() / 1000));
            Intrinsics.checkNotNullExpressionValue(str, "df.format(item.distance/1000)");
            f += Float.parseFloat(str);
        }
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            MutableLiveData<GpsDetailUI> mutableLiveData = this._uiState;
            String str2 = this.df.format(Float.valueOf(f));
            Intrinsics.checkNotNullExpressionValue(str2, "df.format(totalDistance)");
            String strValueOf = String.valueOf(data.size());
            String strValueOf2 = String.valueOf((int) calorie);
            String strMinsToHHmmdd = DateUtil.minsToHHmmdd(duration);
            Intrinsics.checkNotNullExpressionValue(strMinsToHHmmdd, "minsToHHmmdd(totalDuring)");
            mutableLiveData.postValue(new GpsDetailUI(str2, strValueOf, strValueOf2, strMinsToHHmmdd));
            return;
        }
        MutableLiveData<GpsDetailUI> mutableLiveData2 = this._uiState;
        String string = this.df.format(Float.valueOf(MetricUtilsKt.kmToIn(f))).toString();
        String strValueOf3 = String.valueOf(data.size());
        String strValueOf4 = String.valueOf((int) calorie);
        String strMinsToHHmmdd2 = DateUtil.minsToHHmmdd(duration);
        Intrinsics.checkNotNullExpressionValue(strMinsToHHmmdd2, "minsToHHmmdd(totalDuring)");
        mutableLiveData2.postValue(new GpsDetailUI(string, strValueOf3, strValueOf4, strMinsToHHmmdd2));
    }

    /* compiled from: DayGpsFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragmentViewModel$GpsDetailUI;", "", "totalDistance", "", "totalTime", "totalCalorie", "totalDuring", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTotalCalorie", "()Ljava/lang/String;", "getTotalDistance", "getTotalDuring", "getTotalTime", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class GpsDetailUI {
        private final String totalCalorie;
        private final String totalDistance;
        private final String totalDuring;
        private final String totalTime;

        public GpsDetailUI(String totalDistance, String totalTime, String totalCalorie, String totalDuring) {
            Intrinsics.checkNotNullParameter(totalDistance, "totalDistance");
            Intrinsics.checkNotNullParameter(totalTime, "totalTime");
            Intrinsics.checkNotNullParameter(totalCalorie, "totalCalorie");
            Intrinsics.checkNotNullParameter(totalDuring, "totalDuring");
            this.totalDistance = totalDistance;
            this.totalTime = totalTime;
            this.totalCalorie = totalCalorie;
            this.totalDuring = totalDuring;
        }

        public final String getTotalCalorie() {
            return this.totalCalorie;
        }

        public final String getTotalDistance() {
            return this.totalDistance;
        }

        public final String getTotalDuring() {
            return this.totalDuring;
        }

        public final String getTotalTime() {
            return this.totalTime;
        }
    }
}
