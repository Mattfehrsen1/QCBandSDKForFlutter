package com.qcwireless.qcwatch.ui.home.step.month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.databinding.FragmentMonthStepBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;
import com.qcwireless.qcwatch.ui.base.view.QStepMonthHistoryBarChart;
import com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragmentViewModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MonthStepFragment.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/month/MonthStepFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentMonthStepBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "df", "Ljava/text/DecimalFormat;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/step/month/MonthStepFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/step/month/MonthStepFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonthStepFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentMonthStepBinding binding;
    private DateUtil date;
    private DecimalFormat df;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MonthStepFragment() {
        final MonthStepFragment monthStepFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MonthStepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MonthStepFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(monthStepFragment, Reflection.getOrCreateKotlinClass(MonthStepFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MonthStepFragmentViewModel getViewModel() {
        return (MonthStepFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMonthStepBinding fragmentMonthStepBindingInflate = FragmentMonthStepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentMonthStepBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentMonthStepBindingInflate;
        EventBus.getDefault().register(this);
        FragmentMonthStepBinding fragmentMonthStepBinding = this.binding;
        if (fragmentMonthStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthStepBinding = null;
        }
        return fragmentMonthStepBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        DateUtil firstDay = DateUtil.getFirstDayOfMonth(new DateUtil());
        DateUtil lastDay = DateUtil.getLastDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(firstDay, "firstDay");
        this.date = firstDay;
        MonthStepFragmentViewModel viewModel = getViewModel();
        Intrinsics.checkNotNullExpressionValue(lastDay, "lastDay");
        viewModel.queryHistoryStepsByDate(firstDay, lastDay);
        FragmentMonthStepBinding fragmentMonthStepBinding = this.binding;
        if (fragmentMonthStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthStepBinding = null;
        }
        fragmentMonthStepBinding.qcDateChange.setMonth(new DateUtil());
        fragmentMonthStepBinding.qcDateChange.setDateListener(new QDateMonthSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                DateUtil first = DateUtil.getFirstDayOfMonth(dateUtil);
                DateUtil last = DateUtil.getLastDayOfMonth(dateUtil);
                MonthStepFragmentViewModel viewModel2 = this.this$0.getViewModel();
                Intrinsics.checkNotNullExpressionValue(first, "first");
                Intrinsics.checkNotNullExpressionValue(last, "last");
                viewModel2.queryHistoryStepsByDate(first, last);
            }
        });
        fragmentMonthStepBinding.monthStepBarView.setListener(new QStepMonthHistoryBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QStepMonthHistoryBarChart.OnSelectedListener
            public final void onSelected(QStepMonthHistoryBarChart.StepDataBean stepDataBean) {
                MonthStepFragment.m863loadDataOnce$lambda2$lambda1(this.f$0, stepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MonthStepFragment.m864loadDataOnce$lambda3(this.f$0, (MonthStepFragmentViewModel.MonthStepUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2$lambda-1, reason: not valid java name */
    public static final void m863loadDataOnce$lambda2$lambda1(MonthStepFragment this$0, QStepMonthHistoryBarChart.StepDataBean stepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (stepDataBean != null) {
            FragmentMonthStepBinding fragmentMonthStepBinding = this$0.binding;
            if (fragmentMonthStepBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthStepBinding = null;
            }
            try {
                fragmentMonthStepBinding.tvDayStep.setText(String.valueOf(stepDataBean.getSteps()));
                fragmentMonthStepBinding.tvStepRange.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(stepDataBean.getTimeStamp(), true)));
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m864loadDataOnce$lambda3(MonthStepFragment this$0, MonthStepFragmentViewModel.MonthStepUI monthStepUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (monthStepUI != null) {
            FragmentMonthStepBinding fragmentMonthStepBinding = this$0.binding;
            FragmentMonthStepBinding fragmentMonthStepBinding2 = null;
            if (fragmentMonthStepBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthStepBinding = null;
            }
            fragmentMonthStepBinding.monthStepBarView.setData(monthStepUI.getData());
            float fFloatValue = 0.0f;
            int steps = 0;
            int i = 0;
            int calorie = 0;
            for (QStepMonthHistoryBarChart.StepDataBean stepDataBean : monthStepUI.getData()) {
                if (stepDataBean.getSteps() > 0) {
                    i++;
                }
                steps += stepDataBean.getSteps();
                fFloatValue += new BigDecimal(String.valueOf((stepDataBean.getDistance() * 1.0f) / 1000)).setScale(2, RoundingMode.HALF_UP).floatValue();
                calorie += stepDataBean.getCalorie() / 1000;
            }
            FragmentMonthStepBinding fragmentMonthStepBinding3 = this$0.binding;
            if (fragmentMonthStepBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthStepBinding3 = null;
            }
            fragmentMonthStepBinding3.stepTotal.setTitleValue(String.valueOf(steps));
            if (i > 0) {
                FragmentMonthStepBinding fragmentMonthStepBinding4 = this$0.binding;
                if (fragmentMonthStepBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthStepBinding4 = null;
                }
                fragmentMonthStepBinding4.stepAvg.setTitleValue(String.valueOf(MathKt.roundToInt(steps / (i * 1.0f))));
            } else {
                FragmentMonthStepBinding fragmentMonthStepBinding5 = this$0.binding;
                if (fragmentMonthStepBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthStepBinding5 = null;
                }
                fragmentMonthStepBinding5.stepAvg.setTitleValue("0");
            }
            FragmentMonthStepBinding fragmentMonthStepBinding6 = this$0.binding;
            if (fragmentMonthStepBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthStepBinding6 = null;
            }
            fragmentMonthStepBinding6.stepCalorie.setTitleValue(String.valueOf(calorie));
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                float fFloatValue2 = new BigDecimal(String.valueOf(fFloatValue)).setScale(2, RoundingMode.HALF_UP).floatValue();
                FragmentMonthStepBinding fragmentMonthStepBinding7 = this$0.binding;
                if (fragmentMonthStepBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthStepBinding7 = null;
                }
                fragmentMonthStepBinding7.stepDistance.setTitleValue(String.valueOf(fFloatValue2));
                FragmentMonthStepBinding fragmentMonthStepBinding8 = this$0.binding;
                if (fragmentMonthStepBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMonthStepBinding2 = fragmentMonthStepBinding8;
                }
                QStepComponentView qStepComponentView = fragmentMonthStepBinding2.stepDistance;
                String string = this$0.getString(R.string.qc_text_88);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
                qStepComponentView.setTitleUnit(string);
                return;
            }
            float fFloatValue3 = new BigDecimal(String.valueOf(MetricUtilsKt.kmToIn(fFloatValue))).setScale(2, RoundingMode.HALF_UP).floatValue();
            FragmentMonthStepBinding fragmentMonthStepBinding9 = this$0.binding;
            if (fragmentMonthStepBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthStepBinding9 = null;
            }
            fragmentMonthStepBinding9.stepDistance.setTitleValue(String.valueOf(fFloatValue3));
            FragmentMonthStepBinding fragmentMonthStepBinding10 = this$0.binding;
            if (fragmentMonthStepBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMonthStepBinding2 = fragmentMonthStepBinding10;
            }
            QStepComponentView qStepComponentView2 = fragmentMonthStepBinding2.stepDistance;
            String string2 = this$0.getString(R.string.qc_text_358);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
            qStepComponentView2.setTitleUnit(string2);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof ManualRefreshEvent) {
            DateUtil first = DateUtil.getFirstDayOfMonth(this.date);
            DateUtil last = DateUtil.getLastDayOfMonth(this.date);
            MonthStepFragmentViewModel viewModel = getViewModel();
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Intrinsics.checkNotNullExpressionValue(last, "last");
            viewModel.queryHistoryStepsByDate(first, last);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: MonthStepFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/month/MonthStepFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/step/month/MonthStepFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MonthStepFragment newInstance() {
            return new MonthStepFragment();
        }
    }
}
