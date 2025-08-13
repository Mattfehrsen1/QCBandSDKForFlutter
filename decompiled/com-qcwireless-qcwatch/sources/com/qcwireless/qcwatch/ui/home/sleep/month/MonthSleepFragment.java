package com.qcwireless.qcwatch.ui.home.sleep.month;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentMonthSleepBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSleepAnalysisView;
import com.qcwireless.qcwatch.ui.base.view.QSleepMonthBarView;
import com.qcwireless.qcwatch.ui.home.sleep.aigo.AlSleepUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MonthSleepFragment.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/month/MonthSleepFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentMonthSleepBinding;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/sleep/month/MonthSleepFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sleep/month/MonthSleepFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonthSleepFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentMonthSleepBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MonthSleepFragment() {
        final MonthSleepFragment monthSleepFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MonthSleepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MonthSleepFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(monthSleepFragment, Reflection.getOrCreateKotlinClass(MonthSleepFragmentViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MonthSleepFragmentViewModel getViewModel() {
        return (MonthSleepFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMonthSleepBinding fragmentMonthSleepBindingInflate = FragmentMonthSleepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentMonthSleepBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentMonthSleepBindingInflate;
        if (fragmentMonthSleepBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSleepBindingInflate = null;
        }
        return fragmentMonthSleepBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        MonthSleepFragmentViewModel viewModel = getViewModel();
        DateUtil firstDayOfMonth = DateUtil.getFirstDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(firstDayOfMonth, "getFirstDayOfMonth(DateUtil())");
        viewModel.queryWeekSleepHistory(firstDayOfMonth);
        FragmentMonthSleepBinding fragmentMonthSleepBinding = this.binding;
        if (fragmentMonthSleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSleepBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getSupportRem()) {
            ViewKt.visible(fragmentMonthSleepBinding.tvSleepRapid);
        } else {
            ViewKt.gone(fragmentMonthSleepBinding.tvSleepRapid);
        }
        fragmentMonthSleepBinding.qcDateChange.setMonth(new DateUtil());
        fragmentMonthSleepBinding.qcDateChange.setDateListener(new QDateMonthSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                MonthSleepFragmentViewModel viewModel2 = this.this$0.getViewModel();
                DateUtil firstDayOfMonth2 = DateUtil.getFirstDayOfMonth(dateUtil);
                Intrinsics.checkNotNullExpressionValue(firstDayOfMonth2, "getFirstDayOfMonth(dateUtil)");
                viewModel2.queryWeekSleepHistory(firstDayOfMonth2);
            }
        });
        fragmentMonthSleepBinding.monthSleepBarView.setListener(new QSleepMonthBarView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSleepMonthBarView.OnSelectedListener
            public final void onSelected(QSleepMonthBarView.SleepDataBean sleepDataBean) {
                MonthSleepFragment.m824loadDataOnce$lambda2$lambda1(this.f$0, sleepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MonthSleepFragment.m823loadDataOnce$lambda14(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2$lambda-1, reason: not valid java name */
    public static final void m824loadDataOnce$lambda2$lambda1(MonthSleepFragment this$0, QSleepMonthBarView.SleepDataBean sleepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentMonthSleepBinding fragmentMonthSleepBinding = this$0.binding;
        if (fragmentMonthSleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSleepBinding = null;
        }
        if (sleepDataBean == null) {
            fragmentMonthSleepBinding.tvSleepH.setText("--");
            fragmentMonthSleepBinding.tvSleepMin.setText("--");
            return;
        }
        fragmentMonthSleepBinding.tvSleepRange.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(sleepDataBean.getUnixTime(), true)));
        if (sleepDataBean.getTotalTime() > 0) {
            fragmentMonthSleepBinding.tvSleepH.setText(String.valueOf(sleepDataBean.getTotalTime() / 60));
            fragmentMonthSleepBinding.tvSleepMin.setText(String.valueOf(sleepDataBean.getTotalTime() % 60));
        } else {
            fragmentMonthSleepBinding.tvSleepH.setText("--");
            fragmentMonthSleepBinding.tvSleepMin.setText("--");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-14, reason: not valid java name */
    public static final void m823loadDataOnce$lambda14(MonthSleepFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentMonthSleepBinding fragmentMonthSleepBinding = null;
        if (list != null && (!list.isEmpty())) {
            FragmentMonthSleepBinding fragmentMonthSleepBinding2 = this$0.binding;
            if (fragmentMonthSleepBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthSleepBinding2 = null;
            }
            fragmentMonthSleepBinding2.monthSleepBarView.setData(list);
            Iterator it = list.iterator();
            int totalTime = 0;
            int i = 0;
            int deepTime = 0;
            int awake = 0;
            int rapid = 0;
            while (it.hasNext()) {
                QSleepMonthBarView.SleepDataBean sleepDataBean = (QSleepMonthBarView.SleepDataBean) it.next();
                if (sleepDataBean.getTotalTime() > 0) {
                    totalTime += sleepDataBean.getTotalTime();
                    deepTime += sleepDataBean.getDeepTime();
                    awake += sleepDataBean.getAwake();
                    rapid += sleepDataBean.getRapid();
                    i++;
                }
            }
            if (totalTime > 0 && i > 0) {
                int i2 = totalTime / i;
                fragmentMonthSleepBinding2.qcSleep1.setTitleValue(String.valueOf(i2 / 60));
                fragmentMonthSleepBinding2.qcSleep1.setValue2(String.valueOf(i2 % 60));
                float f = ((r15 * 100) * 1.0f) / i2;
                int i3 = ((awake / i) * 100) / i2;
                fragmentMonthSleepBinding2.qcSleep3.setTitleValue(String.valueOf(MathKt.roundToInt(f)));
                fragmentMonthSleepBinding2.qcSleep4.setTitleValue(String.valueOf(MathKt.roundToInt(((((r8 - rapid) - awake) * 100) * 1.0f) / totalTime)));
                int iCalcSleepScore = AlSleepUtil.calcSleepScore(i2 * 60, (deepTime / i) * 60, ((((totalTime - deepTime) - awake) - rapid) / i) * 60, 0);
                fragmentMonthSleepBinding2.qcSleep2.setTitleValue(String.valueOf(iCalcSleepScore));
                if (i2 >= 360) {
                    Drawable drawable = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable != null) {
                        QSleepAnalysisView qSleepAnalysisView = fragmentMonthSleepBinding2.qcSleep1;
                        String string = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView.setStatus(string, drawable);
                    }
                } else {
                    Drawable drawable2 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                    if (drawable2 != null) {
                        QSleepAnalysisView qSleepAnalysisView2 = fragmentMonthSleepBinding2.qcSleep1;
                        String string2 = this$0.getString(R.string.qc_text_127);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_127)");
                        qSleepAnalysisView2.setStatus(string2, drawable2);
                    }
                }
                if (iCalcSleepScore < 70) {
                    Drawable drawable3 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                    if (drawable3 != null) {
                        QSleepAnalysisView qSleepAnalysisView3 = fragmentMonthSleepBinding2.qcSleep2;
                        String string3 = this$0.getString(R.string.qc_text_127);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_127)");
                        qSleepAnalysisView3.setStatus(string3, drawable3);
                    }
                } else {
                    Drawable drawable4 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable4 != null) {
                        QSleepAnalysisView qSleepAnalysisView4 = fragmentMonthSleepBinding2.qcSleep2;
                        String string4 = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView4.setStatus(string4, drawable4);
                    }
                }
                if (f < 20.0f) {
                    Drawable drawable5 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                    if (drawable5 != null) {
                        QSleepAnalysisView qSleepAnalysisView5 = fragmentMonthSleepBinding2.qcSleep3;
                        String string5 = this$0.getString(R.string.qc_text_127);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_127)");
                        qSleepAnalysisView5.setStatus(string5, drawable5);
                    }
                } else {
                    Drawable drawable6 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable6 != null) {
                        QSleepAnalysisView qSleepAnalysisView6 = fragmentMonthSleepBinding2.qcSleep3;
                        String string6 = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView6.setStatus(string6, drawable6);
                    }
                }
                if ((100 - f) - i3 < 70.0f) {
                    Drawable drawable7 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable7 != null) {
                        QSleepAnalysisView qSleepAnalysisView7 = fragmentMonthSleepBinding2.qcSleep4;
                        String string7 = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView7.setStatus(string7, drawable7);
                        return;
                    }
                    return;
                }
                Drawable drawable8 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                if (drawable8 != null) {
                    QSleepAnalysisView qSleepAnalysisView8 = fragmentMonthSleepBinding2.qcSleep4;
                    String string8 = this$0.getString(R.string.qc_text_514);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_514)");
                    qSleepAnalysisView8.setStatus(string8, drawable8);
                    return;
                }
                return;
            }
            FragmentMonthSleepBinding fragmentMonthSleepBinding3 = this$0.binding;
            if (fragmentMonthSleepBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMonthSleepBinding = fragmentMonthSleepBinding3;
            }
            fragmentMonthSleepBinding.monthSleepBarView.setData(list);
            fragmentMonthSleepBinding.qcSleep1.setTitleValue("--");
            fragmentMonthSleepBinding.qcSleep1.setValue2("--");
            fragmentMonthSleepBinding.qcSleep3.setTitleValue("--");
            fragmentMonthSleepBinding.qcSleep4.setTitleValue("--");
            fragmentMonthSleepBinding.qcSleep2.setTitleValue("--");
            fragmentMonthSleepBinding.qcSleep1.setStatusNull("");
            fragmentMonthSleepBinding.qcSleep2.setStatusNull("");
            fragmentMonthSleepBinding.qcSleep3.setStatusNull("");
            fragmentMonthSleepBinding.qcSleep4.setStatusNull("");
            return;
        }
        FragmentMonthSleepBinding fragmentMonthSleepBinding4 = this$0.binding;
        if (fragmentMonthSleepBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMonthSleepBinding = fragmentMonthSleepBinding4;
        }
        fragmentMonthSleepBinding.monthSleepBarView.setData(list);
        fragmentMonthSleepBinding.qcSleep1.setTitleValue("--");
        fragmentMonthSleepBinding.qcSleep1.setValue2("--");
        fragmentMonthSleepBinding.qcSleep3.setTitleValue("--");
        fragmentMonthSleepBinding.qcSleep4.setTitleValue("--");
        fragmentMonthSleepBinding.qcSleep2.setTitleValue("--");
    }

    /* compiled from: MonthSleepFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/month/MonthSleepFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/sleep/month/MonthSleepFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MonthSleepFragment newInstance() {
            return new MonthSleepFragment();
        }
    }
}
