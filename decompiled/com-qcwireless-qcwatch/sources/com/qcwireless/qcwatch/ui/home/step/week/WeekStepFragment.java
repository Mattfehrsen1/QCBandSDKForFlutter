package com.qcwireless.qcwatch.ui.home.step.week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.databinding.FragmentWeekStepBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;
import com.qcwireless.qcwatch.ui.base.view.QStepWeekHistoryBarChart;
import com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragmentViewModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WeekStepFragment.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006#"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWeekStepBinding;", "df", "Ljava/text/DecimalFormat;", "firstDay", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "weeks", "", "", "[Ljava/lang/String;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekStepFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentWeekStepBinding binding;
    private DecimalFormat df;
    private DateUtil firstDay;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String[] weeks;

    /* JADX WARN: Multi-variable type inference failed */
    public WeekStepFragment() {
        final WeekStepFragment weekStepFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WeekStepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WeekStepFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(weekStepFragment, Reflection.getOrCreateKotlinClass(WeekStepFragmentViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WeekStepFragmentViewModel getViewModel() {
        return (WeekStepFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWeekStepBinding fragmentWeekStepBindingInflate = FragmentWeekStepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWeekStepBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWeekStepBindingInflate;
        EventBus.getDefault().register(this);
        FragmentWeekStepBinding fragmentWeekStepBinding = this.binding;
        if (fragmentWeekStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekStepBinding = null;
        }
        return fragmentWeekStepBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String string = getString(R.string.qc_text_208);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_208)");
        String string2 = getString(R.string.qc_text_202);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_202)");
        String string3 = getString(R.string.qc_text_203);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_203)");
        String string4 = getString(R.string.qc_text_204);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_204)");
        String string5 = getString(R.string.qc_text_205);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_205)");
        String string6 = getString(R.string.qc_text_206);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_206)");
        String string7 = getString(R.string.qc_text_207);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_207)");
        this.weeks = new String[]{string, string2, string3, string4, string5, string6, string7};
        DateUtil end = DateUtil.firstDayOfWeek();
        end.addDay(7);
        WeekStepFragmentViewModel viewModel = getViewModel();
        DateUtil dateUtilFirstDayOfWeek = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek, "firstDayOfWeek()");
        Intrinsics.checkNotNullExpressionValue(end, "end");
        viewModel.queryHistoryStepsByDate(dateUtilFirstDayOfWeek, end);
        FragmentWeekStepBinding fragmentWeekStepBinding = this.binding;
        if (fragmentWeekStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekStepBinding = null;
        }
        QDateWeekSwitchView qDateWeekSwitchView = fragmentWeekStepBinding.qcDateChange;
        DateUtil dateUtilFirstDayOfWeek2 = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek2, "firstDayOfWeek()");
        qDateWeekSwitchView.setWeekDayStart(dateUtilFirstDayOfWeek2);
        fragmentWeekStepBinding.qcDateChange.setDateListener(new QDateWeekSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                dateUtil2.addDay(7);
                this.this$0.getViewModel().queryHistoryStepsByDate(dateUtil, dateUtil2);
                this.this$0.firstDay = dateUtil;
            }
        });
        fragmentWeekStepBinding.weekStepBarView.setListener(new QStepWeekHistoryBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QStepWeekHistoryBarChart.OnSelectedListener
            public final void onSelected(QStepWeekHistoryBarChart.StepDataBean stepDataBean) {
                WeekStepFragment.m866loadDataOnce$lambda1$lambda0(this.f$0, stepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WeekStepFragment.m867loadDataOnce$lambda2(this.f$0, (WeekStepFragmentViewModel.WeekStepUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1$lambda-0, reason: not valid java name */
    public static final void m866loadDataOnce$lambda1$lambda0(WeekStepFragment this$0, QStepWeekHistoryBarChart.StepDataBean stepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (stepDataBean != null) {
            try {
                FragmentWeekStepBinding fragmentWeekStepBinding = this$0.binding;
                String[] strArr = null;
                if (fragmentWeekStepBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekStepBinding = null;
                }
                fragmentWeekStepBinding.tvDayStep.setText(String.valueOf(stepDataBean.getSteps()));
                int whatDay = DateUtil.getWhatDay(stepDataBean.getTimeStamp());
                FragmentWeekStepBinding fragmentWeekStepBinding2 = this$0.binding;
                if (fragmentWeekStepBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekStepBinding2 = null;
                }
                TextView textView = fragmentWeekStepBinding2.tvStepRange;
                StringBuilder sb = new StringBuilder();
                sb.append(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(stepDataBean.getTimeStamp(), true)));
                sb.append(' ');
                String[] strArr2 = this$0.weeks;
                if (strArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weeks");
                } else {
                    strArr = strArr2;
                }
                sb.append(strArr[whatDay]);
                textView.setText(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m867loadDataOnce$lambda2(WeekStepFragment this$0, WeekStepFragmentViewModel.WeekStepUI weekStepUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWeekStepBinding fragmentWeekStepBinding = this$0.binding;
        FragmentWeekStepBinding fragmentWeekStepBinding2 = null;
        if (fragmentWeekStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekStepBinding = null;
        }
        fragmentWeekStepBinding.weekStepBarView.setData(weekStepUI.getData());
        float fFloatValue = 0.0f;
        int steps = 0;
        int i = 0;
        int calorie = 0;
        for (QStepWeekHistoryBarChart.StepDataBean stepDataBean : weekStepUI.getData()) {
            if (stepDataBean.getSteps() > 0) {
                i++;
            }
            steps += stepDataBean.getSteps();
            fFloatValue += new BigDecimal(String.valueOf((stepDataBean.getDistance() * 1.0f) / 1000)).setScale(2, RoundingMode.HALF_UP).floatValue();
            calorie += stepDataBean.getCalorie() / 1000;
        }
        FragmentWeekStepBinding fragmentWeekStepBinding3 = this$0.binding;
        if (fragmentWeekStepBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekStepBinding3 = null;
        }
        fragmentWeekStepBinding3.stepTotal.setTitleValue(String.valueOf(steps));
        if (i > 0) {
            FragmentWeekStepBinding fragmentWeekStepBinding4 = this$0.binding;
            if (fragmentWeekStepBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekStepBinding4 = null;
            }
            fragmentWeekStepBinding4.stepAvg.setTitleValue(String.valueOf(Math.round(steps / (i * 1.0f))));
        } else {
            FragmentWeekStepBinding fragmentWeekStepBinding5 = this$0.binding;
            if (fragmentWeekStepBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekStepBinding5 = null;
            }
            fragmentWeekStepBinding5.stepAvg.setTitleValue("0");
        }
        FragmentWeekStepBinding fragmentWeekStepBinding6 = this$0.binding;
        if (fragmentWeekStepBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekStepBinding6 = null;
        }
        fragmentWeekStepBinding6.stepCalorie.setTitleValue(String.valueOf(calorie));
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            float fFloatValue2 = new BigDecimal(String.valueOf(fFloatValue)).setScale(2, RoundingMode.HALF_UP).floatValue();
            FragmentWeekStepBinding fragmentWeekStepBinding7 = this$0.binding;
            if (fragmentWeekStepBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekStepBinding7 = null;
            }
            fragmentWeekStepBinding7.stepDistance.setTitleValue(String.valueOf(fFloatValue2));
            FragmentWeekStepBinding fragmentWeekStepBinding8 = this$0.binding;
            if (fragmentWeekStepBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWeekStepBinding2 = fragmentWeekStepBinding8;
            }
            QStepComponentView qStepComponentView = fragmentWeekStepBinding2.stepDistance;
            String string = this$0.getString(R.string.qc_text_88);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
            qStepComponentView.setTitleUnit(string);
            return;
        }
        float fFloatValue3 = new BigDecimal(String.valueOf(MetricUtilsKt.kmToIn(fFloatValue)).toString()).setScale(2, RoundingMode.HALF_UP).floatValue();
        FragmentWeekStepBinding fragmentWeekStepBinding9 = this$0.binding;
        if (fragmentWeekStepBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekStepBinding9 = null;
        }
        fragmentWeekStepBinding9.stepDistance.setTitleValue(String.valueOf(fFloatValue3));
        FragmentWeekStepBinding fragmentWeekStepBinding10 = this$0.binding;
        if (fragmentWeekStepBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWeekStepBinding2 = fragmentWeekStepBinding10;
        }
        QStepComponentView qStepComponentView2 = fragmentWeekStepBinding2.stepDistance;
        String string2 = this$0.getString(R.string.qc_text_358);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
        qStepComponentView2.setTitleUnit(string2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof ManualRefreshEvent) {
            try {
                DateUtil dateUtil = this.firstDay;
                DateUtil dateUtil2 = null;
                if (dateUtil == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firstDay");
                    dateUtil = null;
                }
                DateUtil dateUtil3 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                dateUtil3.addDay(7);
                WeekStepFragmentViewModel viewModel = getViewModel();
                DateUtil dateUtil4 = this.firstDay;
                if (dateUtil4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firstDay");
                } else {
                    dateUtil2 = dateUtil4;
                }
                viewModel.queryHistoryStepsByDate(dateUtil2, dateUtil3);
            } catch (Exception e) {
                XLog.i(e.getMessage());
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: WeekStepFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeekStepFragment newInstance() {
            return new WeekStepFragment();
        }
    }
}
