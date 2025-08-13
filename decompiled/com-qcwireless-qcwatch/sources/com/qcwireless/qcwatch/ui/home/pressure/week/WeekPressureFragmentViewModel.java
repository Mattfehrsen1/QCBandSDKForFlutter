package com.qcwireless.qcwatch.ui.home.pressure.week;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.view.QPressureWeekHistoryBarChart;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeekPressureFragmentViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "pressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragmentViewModel$WeekPressureUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "queryHistoryPressureByDate", "", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "end", "WeekPressureUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekPressureFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<WeekPressureUI> _uiState;
    private final PressureRepository pressureRepository;

    public WeekPressureFragmentViewModel(PressureRepository pressureRepository) {
        Intrinsics.checkNotNullParameter(pressureRepository, "pressureRepository");
        this.pressureRepository = pressureRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<WeekPressureUI> getUiState() {
        return this._uiState;
    }

    public final void queryHistoryPressureByDate(final DateUtil start, final DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        ThreadExtKt.ktxRunOnBgFix(this, new Function1<WeekPressureFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragmentViewModel.queryHistoryPressureByDate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WeekPressureFragmentViewModel weekPressureFragmentViewModel) {
                invoke2(weekPressureFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WeekPressureFragmentViewModel ktxRunOnBgFix) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                ktxRunOnBgFix._uiState.postValue(new WeekPressureUI(ktxRunOnBgFix.pressureRepository.queryWeekHistoryPressureByDate(start, end)));
            }
        });
    }

    /* compiled from: WeekPressureFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragmentViewModel$WeekPressureUI;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureWeekHistoryBarChart$StepDataBean;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class WeekPressureUI {
        private final List<QPressureWeekHistoryBarChart.StepDataBean> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ WeekPressureUI copy$default(WeekPressureUI weekPressureUI, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = weekPressureUI.data;
            }
            return weekPressureUI.copy(list);
        }

        public final List<QPressureWeekHistoryBarChart.StepDataBean> component1() {
            return this.data;
        }

        public final WeekPressureUI copy(List<? extends QPressureWeekHistoryBarChart.StepDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new WeekPressureUI(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WeekPressureUI) && Intrinsics.areEqual(this.data, ((WeekPressureUI) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "WeekPressureUI(data=" + this.data + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public WeekPressureUI(List<? extends QPressureWeekHistoryBarChart.StepDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<QPressureWeekHistoryBarChart.StepDataBean> getData() {
            return this.data;
        }
    }
}
