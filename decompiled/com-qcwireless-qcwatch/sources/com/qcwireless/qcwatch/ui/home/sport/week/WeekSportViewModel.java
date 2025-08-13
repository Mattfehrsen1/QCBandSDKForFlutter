package com.qcwireless.qcwatch.ui.home.sport.week;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeekSportViewModel.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "sportPlusRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportViewModel$WeekSportUI;", "df", "Ljava/text/DecimalFormat;", "sportDetailList", "", "Lcom/qcwireless/qcwatch/ui/home/sport/bean/SportDetail;", "getSportDetailList", "()Ljava/util/List;", "setSportDetailList", "(Ljava/util/List;)V", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "calcSportTotal", "", "", "weekSportPlusDetail", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "WeekSportUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekSportViewModel extends BaseViewModel {
    private final MutableLiveData<WeekSportUI> _uiState;
    private DecimalFormat df;
    private List<SportDetail> sportDetailList;
    private final SportPlusRepository sportPlusRepository;

    public WeekSportViewModel(SportPlusRepository sportPlusRepository) {
        Intrinsics.checkNotNullParameter(sportPlusRepository, "sportPlusRepository");
        this.sportPlusRepository = sportPlusRepository;
        this.sportDetailList = new ArrayList();
        this.df = new DecimalFormat("0");
        this._uiState = new MutableLiveData<>();
        this.df.setRoundingMode(RoundingMode.DOWN);
    }

    public final List<SportDetail> getSportDetailList() {
        return this.sportDetailList;
    }

    public final void setSportDetailList(List<SportDetail> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.sportDetailList = list;
    }

    public final LiveData<WeekSportUI> getUiState() {
        return this._uiState;
    }

    public final void weekSportPlusDetail(final DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WeekSportViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.week.WeekSportViewModel.weekSportPlusDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WeekSportViewModel weekSportViewModel) {
                invoke2(weekSportViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WeekSportViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
                dateUtil.addDay(6);
                List<SportDetail> listQuerySportPlus = ktxRunOnBgSingle.sportPlusRepository.querySportPlus(start, dateUtil);
                List<SportDetail> list = listQuerySportPlus;
                if (!list.isEmpty()) {
                    ktxRunOnBgSingle.getSportDetailList().clear();
                    ktxRunOnBgSingle.getSportDetailList().addAll(list);
                    ktxRunOnBgSingle.calcSportTotal(listQuerySportPlus);
                } else {
                    ktxRunOnBgSingle.getSportDetailList().clear();
                    ktxRunOnBgSingle._uiState.postValue(new WeekSportUI("00:00:00", "0", "0", "0"));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calcSportTotal(List<SportDetail> sportDetailList) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int duration = 0;
        float calorie = 0.0f;
        for (SportDetail sportDetail : sportDetailList) {
            duration += sportDetail.getDuration();
            calorie += ((int) sportDetail.getCalorie()) / 1000;
            String y_m_d = new DateUtil(sportDetail.getStartTime(), true).getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(item.startTime,true).y_M_D");
            linkedHashMap.put(y_m_d, 1);
        }
        MutableLiveData<WeekSportUI> mutableLiveData = this._uiState;
        String strMinsToHHmmdd = DateUtil.minsToHHmmdd(duration);
        Intrinsics.checkNotNullExpressionValue(strMinsToHHmmdd, "minsToHHmmdd(sportTime)");
        String strValueOf = String.valueOf(sportDetailList.size());
        String str = this.df.format(Float.valueOf(calorie));
        Intrinsics.checkNotNullExpressionValue(str, "df.format(calories)");
        mutableLiveData.postValue(new WeekSportUI(strMinsToHHmmdd, strValueOf, str, String.valueOf(linkedHashMap.keySet().size())));
    }

    /* compiled from: WeekSportViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportViewModel$WeekSportUI;", "", "sportTimes", "", "sportCounts", "sportCalorie", "days", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDays", "()Ljava/lang/String;", "getSportCalorie", "getSportCounts", "getSportTimes", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class WeekSportUI {
        private final String days;
        private final String sportCalorie;
        private final String sportCounts;
        private final String sportTimes;

        public static /* synthetic */ WeekSportUI copy$default(WeekSportUI weekSportUI, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = weekSportUI.sportTimes;
            }
            if ((i & 2) != 0) {
                str2 = weekSportUI.sportCounts;
            }
            if ((i & 4) != 0) {
                str3 = weekSportUI.sportCalorie;
            }
            if ((i & 8) != 0) {
                str4 = weekSportUI.days;
            }
            return weekSportUI.copy(str, str2, str3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final String getSportTimes() {
            return this.sportTimes;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSportCounts() {
            return this.sportCounts;
        }

        /* renamed from: component3, reason: from getter */
        public final String getSportCalorie() {
            return this.sportCalorie;
        }

        /* renamed from: component4, reason: from getter */
        public final String getDays() {
            return this.days;
        }

        public final WeekSportUI copy(String sportTimes, String sportCounts, String sportCalorie, String days) {
            Intrinsics.checkNotNullParameter(sportTimes, "sportTimes");
            Intrinsics.checkNotNullParameter(sportCounts, "sportCounts");
            Intrinsics.checkNotNullParameter(sportCalorie, "sportCalorie");
            Intrinsics.checkNotNullParameter(days, "days");
            return new WeekSportUI(sportTimes, sportCounts, sportCalorie, days);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WeekSportUI)) {
                return false;
            }
            WeekSportUI weekSportUI = (WeekSportUI) other;
            return Intrinsics.areEqual(this.sportTimes, weekSportUI.sportTimes) && Intrinsics.areEqual(this.sportCounts, weekSportUI.sportCounts) && Intrinsics.areEqual(this.sportCalorie, weekSportUI.sportCalorie) && Intrinsics.areEqual(this.days, weekSportUI.days);
        }

        public int hashCode() {
            return (((((this.sportTimes.hashCode() * 31) + this.sportCounts.hashCode()) * 31) + this.sportCalorie.hashCode()) * 31) + this.days.hashCode();
        }

        public String toString() {
            return "WeekSportUI(sportTimes=" + this.sportTimes + ", sportCounts=" + this.sportCounts + ", sportCalorie=" + this.sportCalorie + ", days=" + this.days + ')';
        }

        public WeekSportUI(String sportTimes, String sportCounts, String sportCalorie, String days) {
            Intrinsics.checkNotNullParameter(sportTimes, "sportTimes");
            Intrinsics.checkNotNullParameter(sportCounts, "sportCounts");
            Intrinsics.checkNotNullParameter(sportCalorie, "sportCalorie");
            Intrinsics.checkNotNullParameter(days, "days");
            this.sportTimes = sportTimes;
            this.sportCounts = sportCounts;
            this.sportCalorie = sportCalorie;
            this.days = days;
        }

        public final String getSportTimes() {
            return this.sportTimes;
        }

        public final String getSportCounts() {
            return this.sportCounts;
        }

        public final String getSportCalorie() {
            return this.sportCalorie;
        }

        public final String getDays() {
            return this.days;
        }
    }
}
