package com.qcwireless.qcwatch.ui.home.sport.month;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.home.sport.bean.DataListTree;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MonthSportFragmentViewModel.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0 H\u0002J\u0016\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R#\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0\u00140\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R#\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0\u00140\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006&"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "sportPlusRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragmentViewModel$MonthSportUI;", "df", "Ljava/text/DecimalFormat;", "listDataChild", "", "", "", "Lcom/qcwireless/qcwatch/ui/home/sport/bean/SportDetail;", "getListDataChild", "()Ljava/util/Map;", "setListDataChild", "(Ljava/util/Map;)V", "mDataListTrees", "Lcom/qcwireless/qcwatch/ui/home/sport/bean/DataListTree;", "getMDataListTrees", "()Ljava/util/List;", "mDataListTreesBackup", "getMDataListTreesBackup", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "calcSportTotal", "", "sportDetailList", "", "showMonthData", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "end", "MonthSportUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonthSportFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<MonthSportUI> _uiState;
    private DecimalFormat df;
    private Map<String, List<SportDetail>> listDataChild;
    private final List<DataListTree<String, SportDetail>> mDataListTrees;
    private final List<DataListTree<String, SportDetail>> mDataListTreesBackup;
    private final SportPlusRepository sportPlusRepository;

    public MonthSportFragmentViewModel(SportPlusRepository sportPlusRepository) {
        Intrinsics.checkNotNullParameter(sportPlusRepository, "sportPlusRepository");
        this.sportPlusRepository = sportPlusRepository;
        this.listDataChild = new LinkedHashMap();
        this.mDataListTrees = new ArrayList();
        this.mDataListTreesBackup = new ArrayList();
        this.df = new DecimalFormat("0");
        this._uiState = new MutableLiveData<>();
        this.df.setRoundingMode(RoundingMode.DOWN);
    }

    public final Map<String, List<SportDetail>> getListDataChild() {
        return this.listDataChild;
    }

    public final void setListDataChild(Map<String, List<SportDetail>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.listDataChild = map;
    }

    public final List<DataListTree<String, SportDetail>> getMDataListTrees() {
        return this.mDataListTrees;
    }

    public final List<DataListTree<String, SportDetail>> getMDataListTreesBackup() {
        return this.mDataListTreesBackup;
    }

    public final LiveData<MonthSportUI> getUiState() {
        return this._uiState;
    }

    public final void showMonthData(final DateUtil start, final DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MonthSportFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragmentViewModel.showMonthData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MonthSportFragmentViewModel monthSportFragmentViewModel) {
                invoke2(monthSportFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MonthSportFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<SportDetail> listQuerySportPlus = ktxRunOnBgSingle.sportPlusRepository.querySportPlus(start, end);
                ktxRunOnBgSingle.getListDataChild().clear();
                ktxRunOnBgSingle.getMDataListTrees().clear();
                for (SportDetail sportDetail : listQuerySportPlus) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                    long j = 1000;
                    List<SportDetail> list = ktxRunOnBgSingle.getListDataChild().get(simpleDateFormat.format(new Date(sportDetail.getStartTime() * j)));
                    if (list != null) {
                        list.add(sportDetail);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(sportDetail);
                        Map<String, List<SportDetail>> listDataChild = ktxRunOnBgSingle.getListDataChild();
                        String str = simpleDateFormat.format(new Date(sportDetail.getStartTime() * j));
                        Intrinsics.checkNotNullExpressionValue(str, "sdf.format(Date(item.startTime*1000))");
                        listDataChild.put(str, arrayList);
                    }
                }
                for (Map.Entry<String, List<SportDetail>> entry : ktxRunOnBgSingle.getListDataChild().entrySet()) {
                    ktxRunOnBgSingle.getMDataListTrees().add(new DataListTree<>(entry.getKey(), entry.getValue()));
                }
                ktxRunOnBgSingle.calcSportTotal(listQuerySportPlus);
                if (listQuerySportPlus.isEmpty()) {
                    ktxRunOnBgSingle._uiState.postValue(new MonthSportUI("00:00:00", "0", "0", "0"));
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
            Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(item.startTime, true).y_M_D");
            linkedHashMap.put(y_m_d, 1);
        }
        MutableLiveData<MonthSportUI> mutableLiveData = this._uiState;
        String strMinsToHHmmdd = DateUtil.minsToHHmmdd(duration);
        Intrinsics.checkNotNullExpressionValue(strMinsToHHmmdd, "minsToHHmmdd(sportTime)");
        String strValueOf = String.valueOf(sportDetailList.size());
        String str = this.df.format(Float.valueOf(calorie));
        Intrinsics.checkNotNullExpressionValue(str, "df.format(calories)");
        mutableLiveData.postValue(new MonthSportUI(strMinsToHHmmdd, strValueOf, str, String.valueOf(linkedHashMap.keySet().size())));
    }

    /* compiled from: MonthSportFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragmentViewModel$MonthSportUI;", "", "sportTimes", "", "sportCounts", "sportCalorie", "days", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDays", "()Ljava/lang/String;", "getSportCalorie", "getSportCounts", "getSportTimes", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class MonthSportUI {
        private final String days;
        private final String sportCalorie;
        private final String sportCounts;
        private final String sportTimes;

        public static /* synthetic */ MonthSportUI copy$default(MonthSportUI monthSportUI, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = monthSportUI.sportTimes;
            }
            if ((i & 2) != 0) {
                str2 = monthSportUI.sportCounts;
            }
            if ((i & 4) != 0) {
                str3 = monthSportUI.sportCalorie;
            }
            if ((i & 8) != 0) {
                str4 = monthSportUI.days;
            }
            return monthSportUI.copy(str, str2, str3, str4);
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

        public final MonthSportUI copy(String sportTimes, String sportCounts, String sportCalorie, String days) {
            Intrinsics.checkNotNullParameter(sportTimes, "sportTimes");
            Intrinsics.checkNotNullParameter(sportCounts, "sportCounts");
            Intrinsics.checkNotNullParameter(sportCalorie, "sportCalorie");
            Intrinsics.checkNotNullParameter(days, "days");
            return new MonthSportUI(sportTimes, sportCounts, sportCalorie, days);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MonthSportUI)) {
                return false;
            }
            MonthSportUI monthSportUI = (MonthSportUI) other;
            return Intrinsics.areEqual(this.sportTimes, monthSportUI.sportTimes) && Intrinsics.areEqual(this.sportCounts, monthSportUI.sportCounts) && Intrinsics.areEqual(this.sportCalorie, monthSportUI.sportCalorie) && Intrinsics.areEqual(this.days, monthSportUI.days);
        }

        public int hashCode() {
            return (((((this.sportTimes.hashCode() * 31) + this.sportCounts.hashCode()) * 31) + this.sportCalorie.hashCode()) * 31) + this.days.hashCode();
        }

        public String toString() {
            return "MonthSportUI(sportTimes=" + this.sportTimes + ", sportCounts=" + this.sportCounts + ", sportCalorie=" + this.sportCalorie + ", days=" + this.days + ')';
        }

        public MonthSportUI(String sportTimes, String sportCounts, String sportCalorie, String days) {
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
