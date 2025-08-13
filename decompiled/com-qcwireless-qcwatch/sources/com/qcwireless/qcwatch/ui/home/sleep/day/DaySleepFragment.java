package com.qcwireless.qcwatch.ui.home.sleep.day;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentDaySleepBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSleepAnalysisView;
import com.qcwireless.qcwatch.ui.base.view.QSleepBarChart;
import com.qcwireless.qcwatch.ui.home.sleep.aigo.AlSleepUtil;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepLunchBean;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DaySleepFragment.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentDaySleepBinding;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "formatText", "", "minute", "", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "textView", "view", "Landroid/widget/TextView;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DaySleepFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentDaySleepBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public DaySleepFragment() {
        final DaySleepFragment daySleepFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<DaySleepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DaySleepFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(daySleepFragment, Reflection.getOrCreateKotlinClass(DaySleepFragmentViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DaySleepFragmentViewModel getViewModel() {
        return (DaySleepFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDaySleepBinding fragmentDaySleepBindingInflate = FragmentDaySleepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentDaySleepBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentDaySleepBindingInflate;
        if (fragmentDaySleepBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBindingInflate = null;
        }
        return fragmentDaySleepBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        getViewModel().queryLastData();
        final FragmentDaySleepBinding fragmentDaySleepBinding = this.binding;
        FragmentDaySleepBinding fragmentDaySleepBinding2 = null;
        if (fragmentDaySleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getSupportRem()) {
            ViewKt.visible(fragmentDaySleepBinding.tvSleepRapid);
            ViewKt.visible(fragmentDaySleepBinding.qcSleepRem);
        } else {
            ViewKt.gone(fragmentDaySleepBinding.tvSleepRapid);
            ViewKt.invisible(fragmentDaySleepBinding.qcSleepRem);
        }
        fragmentDaySleepBinding.qcDateChange.setDateUtil(new DateUtil());
        fragmentDaySleepBinding.qcDateChange.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                fragmentDaySleepBinding.qcDateChange.setDateUtil(dateUtil);
                this.getViewModel().showDaySleepView(dateUtil);
                this.getViewModel().showDaySleepLunch(dateUtil);
            }
        });
        fragmentDaySleepBinding.daySleepBarView.setListener(new QSleepBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSleepBarChart.OnSelectedListener
            public final void onSelected(QSleepBarChart.SleepDataBean sleepDataBean) {
                DaySleepFragment.m818loadDataOnce$lambda1$lambda0(fragmentDaySleepBinding, this, sleepDataBean);
            }
        });
        DaySleepFragment daySleepFragment = this;
        getViewModel().getLastDate().observe(daySleepFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DaySleepFragment.m820loadDataOnce$lambda2(this.f$0, (DateUtil) obj);
            }
        });
        getViewModel().getUiLunchState().observe(daySleepFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DaySleepFragment.m821loadDataOnce$lambda4(this.f$0, (SleepLunchBean) obj);
            }
        });
        getViewModel().getUiState().observe(daySleepFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DaySleepFragment.m819loadDataOnce$lambda14(this.f$0, (SleepViewBean) obj);
            }
        });
        FragmentDaySleepBinding fragmentDaySleepBinding3 = this.binding;
        if (fragmentDaySleepBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBinding3 = null;
        }
        TextView textView = fragmentDaySleepBinding3.tvSleepDeep;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvSleepDeep");
        textView(textView);
        FragmentDaySleepBinding fragmentDaySleepBinding4 = this.binding;
        if (fragmentDaySleepBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBinding4 = null;
        }
        TextView textView2 = fragmentDaySleepBinding4.tvSleepAwake;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvSleepAwake");
        textView(textView2);
        FragmentDaySleepBinding fragmentDaySleepBinding5 = this.binding;
        if (fragmentDaySleepBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDaySleepBinding2 = fragmentDaySleepBinding5;
        }
        TextView textView3 = fragmentDaySleepBinding2.tvLunchTitle;
        Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvLunchTitle");
        textView(textView3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1$lambda-0, reason: not valid java name */
    public static final void m818loadDataOnce$lambda1$lambda0(FragmentDaySleepBinding this_run, DaySleepFragment this$0, QSleepBarChart.SleepDataBean sleepDataBean) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (sleepDataBean != null) {
            DateUtil dateUtil = new DateUtil(sleepDataBean.getSleepStart(), true);
            DateUtil dateUtil2 = new DateUtil(sleepDataBean.getSleepEnd(), true);
            this_run.tvSleepRange.setText(dateUtil.getHHmmDate() + '~' + dateUtil2.getHHmmDate());
            this_run.tvSleepMin.setText(String.valueOf((sleepDataBean.getSleepEnd() - sleepDataBean.getSleepStart()) / ((long) 60)));
            int type = sleepDataBean.getType();
            if (type == 1) {
                this_run.tvSleepType.setText(this$0.getString(R.string.qc_text_114));
                return;
            }
            if (type == 2) {
                this_run.tvSleepType.setText(this$0.getString(R.string.qc_text_115));
                return;
            }
            if (type == 3) {
                this_run.tvSleepType.setText(this$0.getString(R.string.qc_text_116));
            } else if (type == 4) {
                this_run.tvSleepType.setText("");
            } else {
                if (type != 5) {
                    return;
                }
                this_run.tvSleepType.setText(this$0.getString(R.string.qc_text_80103));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m820loadDataOnce$lambda2(DaySleepFragment this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDaySleepBinding fragmentDaySleepBinding = this$0.binding;
        if (fragmentDaySleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBinding = null;
        }
        QDateSwitchView qDateSwitchView = fragmentDaySleepBinding.qcDateChange;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        qDateSwitchView.setDateUtil(it);
        this$0.getViewModel().showDaySleepView(it);
        this$0.getViewModel().showDaySleepLunch(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4, reason: not valid java name */
    public static final void m821loadDataOnce$lambda4(DaySleepFragment this$0, SleepLunchBean sleepLunchBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDaySleepBinding fragmentDaySleepBinding = this$0.binding;
        if (fragmentDaySleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBinding = null;
        }
        XLog.i(new Gson().toJson(sleepLunchBean));
        if (sleepLunchBean.getLunchSt() > 0 && sleepLunchBean.getLunchEt() > 0) {
            ViewKt.visible(fragmentDaySleepBinding.clsLunchSleep);
            fragmentDaySleepBinding.tvLunchRange.setText(new DateUtil(sleepLunchBean.getLunchSt(), true).getHHmmDate() + '~' + new DateUtil(sleepLunchBean.getLunchEt(), true).getHHmmDate());
            fragmentDaySleepBinding.tvLunchValue.setText(this$0.formatText(new DateUtil((long) sleepLunchBean.getLunchEt(), true).getTodayMin() - new DateUtil((long) sleepLunchBean.getLunchSt(), true).getTodayMin()));
            return;
        }
        ViewKt.gone(fragmentDaySleepBinding.clsLunchSleep);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-14, reason: not valid java name */
    public static final void m819loadDataOnce$lambda14(DaySleepFragment this$0, final SleepViewBean sleepViewBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDaySleepBinding fragmentDaySleepBinding = this$0.binding;
        if (fragmentDaySleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySleepBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getSupportRem()) {
            ViewKt.visible(fragmentDaySleepBinding.tvSleepRapid);
        } else {
            ViewKt.gone(fragmentDaySleepBinding.tvSleepRapid);
        }
        if ((sleepViewBean != null ? sleepViewBean.getData() : null) != null && (!sleepViewBean.getData().isEmpty())) {
            ThreadExtKt.ktxRunOnUiDelay(fragmentDaySleepBinding, 100L, new Function1<FragmentDaySleepBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment$loadDataOnce$4$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FragmentDaySleepBinding fragmentDaySleepBinding2) {
                    invoke2(fragmentDaySleepBinding2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FragmentDaySleepBinding ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    XLog.i(sleepViewBean.getData());
                    ktxRunOnUiDelay.daySleepBarView.setData(sleepViewBean.getStartTime(), sleepViewBean.getEndTime(), sleepViewBean.getData());
                }
            });
            fragmentDaySleepBinding.qcSleep1.setTitleValue(String.valueOf((sleepViewBean.getTotalSleep() / 60) / 60));
            fragmentDaySleepBinding.qcSleep1.setValue2(String.valueOf((sleepViewBean.getTotalSleep() / 60) % 60));
            float deepSleep = ((sleepViewBean.getDeepSleep() * 100) * 1.0f) / sleepViewBean.getTotalSleep();
            float awakeSleep = ((sleepViewBean.getAwakeSleep() * 100) * 1.0f) / sleepViewBean.getTotalSleep();
            int iCalcSleepScore = AlSleepUtil.calcSleepScore(sleepViewBean.getTotalSleep(), sleepViewBean.getDeepSleep(), sleepViewBean.getLightSleep(), 0);
            fragmentDaySleepBinding.qcSleep2.setTitleValue(String.valueOf(iCalcSleepScore));
            fragmentDaySleepBinding.qcSleep3.setTitleValue(String.valueOf(MathKt.roundToInt(deepSleep)));
            fragmentDaySleepBinding.qcSleep4.setTitleValue(String.valueOf(MathKt.roundToInt(((sleepViewBean.getLightSleep() * 100) * 1.0f) / sleepViewBean.getTotalSleep())));
            fragmentDaySleepBinding.qcSleepDeep.setTitleValue(String.valueOf((sleepViewBean.getDeepSleep() / 60) / 60));
            fragmentDaySleepBinding.qcSleepDeep.setValue2(String.valueOf((sleepViewBean.getDeepSleep() / 60) % 60));
            fragmentDaySleepBinding.qcSleepLight.setTitleValue(String.valueOf((sleepViewBean.getLightSleep() / 60) / 60));
            fragmentDaySleepBinding.qcSleepLight.setValue2(String.valueOf((sleepViewBean.getLightSleep() / 60) % 60));
            fragmentDaySleepBinding.qcSleepAwake.setTitleValue(String.valueOf((sleepViewBean.getAwakeSleep() / 60) / 60));
            fragmentDaySleepBinding.qcSleepAwake.setValue2(String.valueOf((sleepViewBean.getAwakeSleep() / 60) % 60));
            fragmentDaySleepBinding.qcSleepRem.setTitleValue(String.valueOf((sleepViewBean.getRapidSleep() / 60) / 60));
            fragmentDaySleepBinding.qcSleepRem.setValue2(String.valueOf((sleepViewBean.getRapidSleep() / 60) % 60));
            if (sleepViewBean.getTotalSleep() / 60 >= 360) {
                Drawable drawable = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                if (drawable != null) {
                    QSleepAnalysisView qSleepAnalysisView = fragmentDaySleepBinding.qcSleep1;
                    String string = this$0.getString(R.string.qc_text_126);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_126)");
                    qSleepAnalysisView.setStatus(string, drawable);
                }
            } else {
                Drawable drawable2 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                if (drawable2 != null) {
                    QSleepAnalysisView qSleepAnalysisView2 = fragmentDaySleepBinding.qcSleep1;
                    String string2 = this$0.getString(R.string.qc_text_127);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_127)");
                    qSleepAnalysisView2.setStatus(string2, drawable2);
                }
            }
            if (iCalcSleepScore < 70) {
                Drawable drawable3 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                if (drawable3 != null) {
                    QSleepAnalysisView qSleepAnalysisView3 = fragmentDaySleepBinding.qcSleep2;
                    String string3 = this$0.getString(R.string.qc_text_127);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_127)");
                    qSleepAnalysisView3.setStatus(string3, drawable3);
                }
            } else {
                Drawable drawable4 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                if (drawable4 != null) {
                    QSleepAnalysisView qSleepAnalysisView4 = fragmentDaySleepBinding.qcSleep2;
                    String string4 = this$0.getString(R.string.qc_text_126);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_126)");
                    qSleepAnalysisView4.setStatus(string4, drawable4);
                }
            }
            if (deepSleep < 20.0f) {
                Drawable drawable5 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                if (drawable5 != null) {
                    QSleepAnalysisView qSleepAnalysisView5 = fragmentDaySleepBinding.qcSleep3;
                    String string5 = this$0.getString(R.string.qc_text_127);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_127)");
                    qSleepAnalysisView5.setStatus(string5, drawable5);
                }
            } else {
                Drawable drawable6 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                if (drawable6 != null) {
                    QSleepAnalysisView qSleepAnalysisView6 = fragmentDaySleepBinding.qcSleep3;
                    String string6 = this$0.getString(R.string.qc_text_126);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_126)");
                    qSleepAnalysisView6.setStatus(string6, drawable6);
                }
            }
            if ((100 - deepSleep) - awakeSleep < 70.0f) {
                Drawable drawable7 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                if (drawable7 != null) {
                    QSleepAnalysisView qSleepAnalysisView7 = fragmentDaySleepBinding.qcSleep4;
                    String string7 = this$0.getString(R.string.qc_text_126);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_126)");
                    qSleepAnalysisView7.setStatus(string7, drawable7);
                    return;
                }
                return;
            }
            Drawable drawable8 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
            if (drawable8 != null) {
                QSleepAnalysisView qSleepAnalysisView8 = fragmentDaySleepBinding.qcSleep4;
                String string8 = this$0.getString(R.string.qc_text_514);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_514)");
                qSleepAnalysisView8.setStatus(string8, drawable8);
                return;
            }
            return;
        }
        fragmentDaySleepBinding.daySleepBarView.setData(1612886400L, 1612886400L, sleepViewBean.getData());
        fragmentDaySleepBinding.qcSleep1.setTitleValue("--");
        fragmentDaySleepBinding.qcSleep1.setValue2("--");
        fragmentDaySleepBinding.qcSleep3.setTitleValue("--");
        fragmentDaySleepBinding.qcSleep4.setTitleValue("--");
        fragmentDaySleepBinding.qcSleep2.setTitleValue("--");
        fragmentDaySleepBinding.tvSleepType.setText("");
        fragmentDaySleepBinding.tvSleepRange.setText("");
        fragmentDaySleepBinding.tvSleepMin.setText("--");
        fragmentDaySleepBinding.qcSleep1.setStatusNull("");
        fragmentDaySleepBinding.qcSleep2.setStatusNull("");
        fragmentDaySleepBinding.qcSleep3.setStatusNull("");
        fragmentDaySleepBinding.qcSleep4.setStatusNull("");
        fragmentDaySleepBinding.qcSleepDeep.setTitleValue("--");
        fragmentDaySleepBinding.qcSleepDeep.setValue2("--");
        fragmentDaySleepBinding.qcSleepLight.setTitleValue("--");
        fragmentDaySleepBinding.qcSleepLight.setValue2("--");
        fragmentDaySleepBinding.qcSleepAwake.setTitleValue("--");
        fragmentDaySleepBinding.qcSleepAwake.setValue2("--");
        fragmentDaySleepBinding.qcSleepRem.setTitleValue("--");
        fragmentDaySleepBinding.qcSleepRem.setValue2("--");
    }

    public final String formatText(int minute) {
        String string;
        String string2;
        String string3;
        int i = minute / 60;
        int i2 = minute % 60;
        if (i == 0) {
            if (i2 < 10) {
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(i2);
                string3 = sb.toString();
            } else {
                string3 = i2 + "";
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string4 = getString(R.string.qc_text_8053);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_8053)");
            String str = String.format(string4, Arrays.copyOf(new Object[]{string3}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            return str;
        }
        if (i < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(i);
            string = sb2.toString();
        } else {
            string = i + "";
        }
        if (i2 < 10) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append('0');
            sb3.append(i2);
            string2 = sb3.toString();
        } else {
            string2 = i2 + "";
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String string5 = getString(R.string.qc_text_8052);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_8052)");
        String str2 = String.format(string5, Arrays.copyOf(new Object[]{string, string2}, 2));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        return str2;
    }

    private final void textView(TextView view) {
        view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        view.setSingleLine();
        view.setSelected(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }

    /* compiled from: DaySleepFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DaySleepFragment newInstance() {
            return new DaySleepFragment();
        }
    }
}
