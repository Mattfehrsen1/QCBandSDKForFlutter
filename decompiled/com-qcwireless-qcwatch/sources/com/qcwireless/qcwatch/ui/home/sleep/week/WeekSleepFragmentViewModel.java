package com.qcwireless.qcwatch.ui.home.sleep.week;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepTotalHistory;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.base.view.QSleepWeekBarView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeekSleepFragmentViewModel.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0010H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "sleepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/qcwireless/qcwatch/ui/base/view/QSleepWeekBarView$SleepDataBean;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "queryWeekSleepHistory", "", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "showDaySleepView", "dateUtil", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekSleepFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<List<QSleepWeekBarView.SleepDataBean>> _uiState;
    private final SleepDetailRepository sleepDetailRepository;

    public WeekSleepFragmentViewModel(SleepDetailRepository sleepDetailRepository) {
        Intrinsics.checkNotNullParameter(sleepDetailRepository, "sleepDetailRepository");
        this.sleepDetailRepository = sleepDetailRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<List<QSleepWeekBarView.SleepDataBean>> getUiState() {
        return this._uiState;
    }

    public final void queryWeekSleepHistory(final DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WeekSleepFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragmentViewModel.queryWeekSleepHistory.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WeekSleepFragmentViewModel weekSleepFragmentViewModel) {
                invoke2(weekSleepFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WeekSleepFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (!UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                    DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
                    for (int i = 0; i < 7; i++) {
                        SleepDetailRepository sleepDetailRepository = ktxRunOnBgSingle.sleepDetailRepository;
                        String y_m_d = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "dayAdd.y_M_D");
                        SleepDetail sleepDetailQuerySleepByDate = sleepDetailRepository.querySleepByDate(y_m_d);
                        SleepDetailRepository sleepDetailRepository2 = ktxRunOnBgSingle.sleepDetailRepository;
                        String y_m_d2 = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d2, "dayAdd.y_M_D");
                        SleepTotalHistory sleepTotalHistoryQuerySleepTotalByDate = sleepDetailRepository2.querySleepTotalByDate(y_m_d2);
                        if (sleepDetailQuerySleepByDate != null && sleepTotalHistoryQuerySleepTotalByDate == null) {
                            ktxRunOnBgSingle.showDaySleepView(dateUtil);
                        }
                        dateUtil.addDay(1);
                    }
                }
                ktxRunOnBgSingle._uiState.postValue(ktxRunOnBgSingle.sleepDetailRepository.querySleepWeek(start));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDaySleepView(final DateUtil dateUtil) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WeekSleepFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragmentViewModel.showDaySleepView.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WeekSleepFragmentViewModel weekSleepFragmentViewModel) {
                invoke2(weekSleepFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WeekSleepFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                SleepDetailRepository sleepDetailRepository = ktxRunOnBgSingle.sleepDetailRepository;
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
                SleepDetail sleepDetailQuerySleepByDate = sleepDetailRepository.querySleepByDate(y_m_d);
                DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                dateUtil2.addDay(-1);
                SleepDetailRepository sleepDetailRepository2 = ktxRunOnBgSingle.sleepDetailRepository;
                String y_m_d2 = dateUtil2.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "yesDate.y_M_D");
                SleepDetail sleepDetailQuerySleepByDate2 = sleepDetailRepository2.querySleepByDate(y_m_d2);
                if (sleepDetailQuerySleepByDate2 == null) {
                    sleepDetailQuerySleepByDate2 = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                }
                if (sleepDetailQuerySleepByDate != null) {
                    ktxRunOnBgSingle.sleepDetailRepository.calcSleepViewData(sleepDetailQuerySleepByDate, sleepDetailQuerySleepByDate2);
                }
            }
        });
    }
}
