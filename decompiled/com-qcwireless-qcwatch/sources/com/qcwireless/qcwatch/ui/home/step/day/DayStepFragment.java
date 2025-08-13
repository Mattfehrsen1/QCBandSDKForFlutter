package com.qcwireless.qcwatch.ui.home.step.day;

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
import com.qcwireless.qcwatch.databinding.FragmentDayStepBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;
import com.qcwireless.qcwatch.ui.home.step.day.DayStepFragmentViewModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
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

/* compiled from: DayStepFragment.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentDayStepBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "df", "Ljava/text/DecimalFormat;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DayStepFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentDayStepBinding binding;
    private DateUtil date;
    private DecimalFormat df;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public DayStepFragment() {
        final DayStepFragment dayStepFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<DayStepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.step.day.DayStepFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.step.day.DayStepFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DayStepFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(dayStepFragment, Reflection.getOrCreateKotlinClass(DayStepFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DayStepFragmentViewModel getViewModel() {
        return (DayStepFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDayStepBinding fragmentDayStepBindingInflate = FragmentDayStepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentDayStepBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentDayStepBindingInflate;
        if (fragmentDayStepBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayStepBindingInflate = null;
        }
        return fragmentDayStepBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() throws SecurityException {
        super.loadDataOnce();
        EventBus.getDefault().register(this);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        DayStepFragmentViewModel viewModel = getViewModel();
        String y_m_d = new DateUtil().getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
        viewModel.queryStepDetailByDate(y_m_d);
        final FragmentDayStepBinding fragmentDayStepBinding = this.binding;
        if (fragmentDayStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayStepBinding = null;
        }
        fragmentDayStepBinding.qcDateChange.setDateUtil(new DateUtil());
        fragmentDayStepBinding.qcDateChange.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.step.day.DayStepFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                fragmentDayStepBinding.qcDateChange.setDateUtil(dateUtil);
                DayStepFragmentViewModel viewModel2 = this.getViewModel();
                String y_m_d2 = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "dateUtil.y_M_D");
                viewModel2.queryStepDetailByDate(y_m_d2);
                this.date = dateUtil;
            }
        });
        fragmentDayStepBinding.qcStepChart.setListener(new QStepBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.step.day.DayStepFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QStepBarChart.OnSelectedListener
            public final void onSelected(QStepBarChart.StepDataBean stepDataBean) {
                DayStepFragment.m860loadDataOnce$lambda1$lambda0(this.f$0, fragmentDayStepBinding, stepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.step.day.DayStepFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DayStepFragment.m861loadDataOnce$lambda3(this.f$0, (DayStepFragmentViewModel.DayStepUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1$lambda-0, reason: not valid java name */
    public static final void m860loadDataOnce$lambda1$lambda0(DayStepFragment this$0, FragmentDayStepBinding this_run, QStepBarChart.StepDataBean stepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (stepDataBean != null) {
            FragmentDayStepBinding fragmentDayStepBinding = this$0.binding;
            if (fragmentDayStepBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayStepBinding = null;
            }
            fragmentDayStepBinding.tvStepRange.setText(DateUtil.dayMinToStr((int) (stepDataBean.getTimeStamp() / 60)));
            this_run.tvDayStep.setText(String.valueOf(stepDataBean.getSteps()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m861loadDataOnce$lambda3(DayStepFragment this$0, DayStepFragmentViewModel.DayStepUI dayStepUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDayStepBinding fragmentDayStepBinding = this$0.binding;
        if (fragmentDayStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayStepBinding = null;
        }
        fragmentDayStepBinding.qcStepChart.setData(dayStepUI.getData());
        fragmentDayStepBinding.stepTotal.setTitleValue(dayStepUI.getStepTotal());
        int i = 0;
        Iterator<QStepBarChart.StepDataBean> it = dayStepUI.getData().iterator();
        while (it.hasNext()) {
            if (it.next().getSteps() > 0) {
                i++;
            }
        }
        if (i > 0) {
            fragmentDayStepBinding.stepAvg.setTitleValue(String.valueOf(MathKt.roundToInt(Float.parseFloat(dayStepUI.getStepTotal()) / i)));
        } else {
            fragmentDayStepBinding.stepAvg.setTitleValue("0");
        }
        try {
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                String str = String.valueOf(new BigDecimal(String.valueOf((Float.parseFloat(dayStepUI.getDistanceTotal()) * 1.0f) / 1000)).setScale(2, RoundingMode.HALF_UP));
                QStepComponentView qStepComponentView = fragmentDayStepBinding.stepDistance;
                Intrinsics.checkNotNullExpressionValue(str, "str");
                qStepComponentView.setTitleValue(str);
                QStepComponentView qStepComponentView2 = fragmentDayStepBinding.stepDistance;
                String string = this$0.getString(R.string.qc_text_88);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
                qStepComponentView2.setTitleUnit(string);
            } else {
                String str2 = String.valueOf(new BigDecimal(String.valueOf(MetricUtilsKt.kmToIn((Float.parseFloat(dayStepUI.getDistanceTotal()) * 1.0f) / 1000))).setScale(2, RoundingMode.HALF_UP));
                QStepComponentView qStepComponentView3 = fragmentDayStepBinding.stepDistance;
                Intrinsics.checkNotNullExpressionValue(str2, "str");
                qStepComponentView3.setTitleValue(str2);
                QStepComponentView qStepComponentView4 = fragmentDayStepBinding.stepDistance;
                String string2 = this$0.getString(R.string.qc_text_358);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
                qStepComponentView4.setTitleUnit(string2);
            }
        } catch (Exception unused) {
        }
        fragmentDayStepBinding.stepCalorie.setTitleValue(String.valueOf(Integer.parseInt(dayStepUI.getCalorieTotal()) / 1000));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof ManualRefreshEvent) {
            DayStepFragmentViewModel viewModel = getViewModel();
            String y_m_d = this.date.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
            viewModel.queryStepDetailByDate(y_m_d);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: DayStepFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DayStepFragment newInstance() {
            return new DayStepFragment();
        }
    }
}
