package com.qcwireless.qcwatch.ui.home.step.week;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepHistoryRepository;
import com.qcwireless.qcwatch.ui.base.view.QStepWeekHistoryBarChart;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeekStepFragmentViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "stepHistoryRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepHistoryRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepHistoryRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragmentViewModel$WeekStepUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "queryHistoryStepsByDate", "", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "end", "WeekStepUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekStepFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<WeekStepUI> _uiState;
    private final StepHistoryRepository stepHistoryRepository;

    public WeekStepFragmentViewModel(StepHistoryRepository stepHistoryRepository) {
        Intrinsics.checkNotNullParameter(stepHistoryRepository, "stepHistoryRepository");
        this.stepHistoryRepository = stepHistoryRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<WeekStepUI> getUiState() {
        return this._uiState;
    }

    public final void queryHistoryStepsByDate(final DateUtil start, final DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WeekStepFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragmentViewModel.queryHistoryStepsByDate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WeekStepFragmentViewModel weekStepFragmentViewModel) {
                invoke2(weekStepFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WeekStepFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiState.postValue(new WeekStepUI(ktxRunOnBgSingle.stepHistoryRepository.queryWeekHistoryStepByDate(start, end)));
            }
        });
    }

    /* compiled from: WeekStepFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragmentViewModel$WeekStepUI;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QStepWeekHistoryBarChart$StepDataBean;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class WeekStepUI {
        private final List<QStepWeekHistoryBarChart.StepDataBean> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ WeekStepUI copy$default(WeekStepUI weekStepUI, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = weekStepUI.data;
            }
            return weekStepUI.copy(list);
        }

        public final List<QStepWeekHistoryBarChart.StepDataBean> component1() {
            return this.data;
        }

        public final WeekStepUI copy(List<? extends QStepWeekHistoryBarChart.StepDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new WeekStepUI(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WeekStepUI) && Intrinsics.areEqual(this.data, ((WeekStepUI) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "WeekStepUI(data=" + this.data + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public WeekStepUI(List<? extends QStepWeekHistoryBarChart.StepDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<QStepWeekHistoryBarChart.StepDataBean> getData() {
            return this.data;
        }
    }
}
