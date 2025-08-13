package com.qcwireless.qcwatch.ui.home.step.day;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.rsp.ReadDetailSportDataRsp;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DayStepFragmentViewModel.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "stepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragmentViewModel$DayStepUI;", "again", "", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "queryStepDetailByDate", "", "date", "", "syncStepDetailAgain", "DayStepUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DayStepFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<DayStepUI> _uiState;
    private boolean again;
    private final StepDetailRepository stepDetailRepository;

    public DayStepFragmentViewModel(StepDetailRepository stepDetailRepository) {
        Intrinsics.checkNotNullParameter(stepDetailRepository, "stepDetailRepository");
        this.stepDetailRepository = stepDetailRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<DayStepUI> getUiState() {
        return this._uiState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncStepDetailAgain(final String date) {
        this.stepDetailRepository.syncTodayStepDetail(0, new BaseDeviceResult<ReadDetailSportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.home.step.day.DayStepFragmentViewModel.syncStepDetailAgain.1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadDetailSportDataRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                DayStepFragmentViewModel.this.queryStepDetailByDate(date);
            }
        });
    }

    public final void queryStepDetailByDate(final String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DayStepFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.step.day.DayStepFragmentViewModel.queryStepDetailByDate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DayStepFragmentViewModel dayStepFragmentViewModel) {
                invoke2(dayStepFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DayStepFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<QStepBarChart.StepDataBean> listQueryStepDetail = ktxRunOnBgSingle.stepDetailRepository.queryStepDetail(date);
                StepTotal stepTotalQueryStepTotal = ktxRunOnBgSingle.stepDetailRepository.queryStepTotal(date);
                int steps = 0;
                int distance = 0;
                int carolie = 0;
                for (QStepBarChart.StepDataBean stepDataBean : listQueryStepDetail) {
                    steps += stepDataBean.getSteps();
                    distance += stepDataBean.getDistance();
                    carolie += stepDataBean.getCalorie();
                }
                if (stepTotalQueryStepTotal != null && steps != stepTotalQueryStepTotal.getStep() && !ktxRunOnBgSingle.again) {
                    ktxRunOnBgSingle.again = true;
                    ktxRunOnBgSingle.syncStepDetailAgain(date);
                }
                if (stepTotalQueryStepTotal != null && stepTotalQueryStepTotal.getCarolie() > 0 && stepTotalQueryStepTotal.getStep() != steps) {
                    stepTotalQueryStepTotal.setStep(steps);
                    stepTotalQueryStepTotal.setDistance(distance);
                    stepTotalQueryStepTotal.setCarolie(carolie);
                    ktxRunOnBgSingle.stepDetailRepository.saveTotalDate(stepTotalQueryStepTotal);
                    StepDetail stepDetailQueryStepDetailError = ktxRunOnBgSingle.stepDetailRepository.queryStepDetailError(date);
                    if (stepDetailQueryStepDetailError != null && !StringsKt.equals(stepDetailQueryStepDetailError.getDateStr(), new DateUtil().getY_M_D(), true)) {
                        ktxRunOnBgSingle.stepDetailRepository.deleteStepDetailError(stepDetailQueryStepDetailError);
                    }
                    DeviceSettingRepository.INSTANCE.getGetInstance().saveSyncHistoryDataInfo(0L);
                }
                if (stepTotalQueryStepTotal != null && stepTotalQueryStepTotal.getCarolie() != carolie && stepTotalQueryStepTotal.getCarolie() > carolie) {
                    carolie = stepTotalQueryStepTotal.getCarolie();
                }
                ktxRunOnBgSingle._uiState.postValue(new DayStepUI(listQueryStepDetail, String.valueOf(steps), String.valueOf(distance), String.valueOf(carolie)));
            }
        });
    }

    /* compiled from: DayStepFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragmentViewModel$DayStepUI;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QStepBarChart$StepDataBean;", "stepTotal", "", "distanceTotal", "calorieTotal", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCalorieTotal", "()Ljava/lang/String;", "getData", "()Ljava/util/List;", "getDistanceTotal", "getStepTotal", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class DayStepUI {
        private final String calorieTotal;
        private final List<QStepBarChart.StepDataBean> data;
        private final String distanceTotal;
        private final String stepTotal;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DayStepUI copy$default(DayStepUI dayStepUI, List list, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                list = dayStepUI.data;
            }
            if ((i & 2) != 0) {
                str = dayStepUI.stepTotal;
            }
            if ((i & 4) != 0) {
                str2 = dayStepUI.distanceTotal;
            }
            if ((i & 8) != 0) {
                str3 = dayStepUI.calorieTotal;
            }
            return dayStepUI.copy(list, str, str2, str3);
        }

        public final List<QStepBarChart.StepDataBean> component1() {
            return this.data;
        }

        /* renamed from: component2, reason: from getter */
        public final String getStepTotal() {
            return this.stepTotal;
        }

        /* renamed from: component3, reason: from getter */
        public final String getDistanceTotal() {
            return this.distanceTotal;
        }

        /* renamed from: component4, reason: from getter */
        public final String getCalorieTotal() {
            return this.calorieTotal;
        }

        public final DayStepUI copy(List<? extends QStepBarChart.StepDataBean> data, String stepTotal, String distanceTotal, String calorieTotal) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(stepTotal, "stepTotal");
            Intrinsics.checkNotNullParameter(distanceTotal, "distanceTotal");
            Intrinsics.checkNotNullParameter(calorieTotal, "calorieTotal");
            return new DayStepUI(data, stepTotal, distanceTotal, calorieTotal);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DayStepUI)) {
                return false;
            }
            DayStepUI dayStepUI = (DayStepUI) other;
            return Intrinsics.areEqual(this.data, dayStepUI.data) && Intrinsics.areEqual(this.stepTotal, dayStepUI.stepTotal) && Intrinsics.areEqual(this.distanceTotal, dayStepUI.distanceTotal) && Intrinsics.areEqual(this.calorieTotal, dayStepUI.calorieTotal);
        }

        public int hashCode() {
            return (((((this.data.hashCode() * 31) + this.stepTotal.hashCode()) * 31) + this.distanceTotal.hashCode()) * 31) + this.calorieTotal.hashCode();
        }

        public String toString() {
            return "DayStepUI(data=" + this.data + ", stepTotal=" + this.stepTotal + ", distanceTotal=" + this.distanceTotal + ", calorieTotal=" + this.calorieTotal + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DayStepUI(List<? extends QStepBarChart.StepDataBean> data, String stepTotal, String distanceTotal, String calorieTotal) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(stepTotal, "stepTotal");
            Intrinsics.checkNotNullParameter(distanceTotal, "distanceTotal");
            Intrinsics.checkNotNullParameter(calorieTotal, "calorieTotal");
            this.data = data;
            this.stepTotal = stepTotal;
            this.distanceTotal = distanceTotal;
            this.calorieTotal = calorieTotal;
        }

        public /* synthetic */ DayStepUI(List list, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, (i & 2) != 0 ? "0" : str, (i & 4) != 0 ? "0" : str2, str3);
        }

        public final List<QStepBarChart.StepDataBean> getData() {
            return this.data;
        }

        public final String getStepTotal() {
            return this.stepTotal;
        }

        public final String getDistanceTotal() {
            return this.distanceTotal;
        }

        public final String getCalorieTotal() {
            return this.calorieTotal;
        }
    }
}
