package com.qcwireless.qcwatch.ui.home.sleep.week;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentWeekSleepBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSleepAnalysisView;
import com.qcwireless.qcwatch.ui.base.view.QSleepWeekBarView;
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

/* compiled from: WeekSleepFragment.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWeekSleepBinding;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "weeks", "", "", "[Ljava/lang/String;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekSleepFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentWeekSleepBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String[] weeks;

    /* JADX WARN: Multi-variable type inference failed */
    public WeekSleepFragment() {
        final WeekSleepFragment weekSleepFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WeekSleepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WeekSleepFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(weekSleepFragment, Reflection.getOrCreateKotlinClass(WeekSleepFragmentViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WeekSleepFragmentViewModel getViewModel() {
        return (WeekSleepFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWeekSleepBinding fragmentWeekSleepBindingInflate = FragmentWeekSleepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWeekSleepBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWeekSleepBindingInflate;
        if (fragmentWeekSleepBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekSleepBindingInflate = null;
        }
        return fragmentWeekSleepBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        WeekSleepFragmentViewModel viewModel = getViewModel();
        DateUtil dateUtilFirstDayOfWeek = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek, "firstDayOfWeek()");
        viewModel.queryWeekSleepHistory(dateUtilFirstDayOfWeek);
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
        FragmentWeekSleepBinding fragmentWeekSleepBinding = this.binding;
        if (fragmentWeekSleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekSleepBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getSupportRem()) {
            ViewKt.visible(fragmentWeekSleepBinding.tvSleepRapid);
        } else {
            ViewKt.gone(fragmentWeekSleepBinding.tvSleepRapid);
        }
        QDateWeekSwitchView qDateWeekSwitchView = fragmentWeekSleepBinding.qcDateChange;
        DateUtil dateUtilFirstDayOfWeek2 = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek2, "firstDayOfWeek()");
        qDateWeekSwitchView.setWeekDayStart(dateUtilFirstDayOfWeek2);
        fragmentWeekSleepBinding.qcDateChange.setDateListener(new QDateWeekSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                this.this$0.getViewModel().queryWeekSleepHistory(dateUtil);
            }
        });
        fragmentWeekSleepBinding.weekSleepBarView.setListener(new QSleepWeekBarView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSleepWeekBarView.OnSelectedListener
            public final void onSelected(QSleepWeekBarView.SleepDataBean sleepDataBean) {
                WeekSleepFragment.m827loadDataOnce$lambda2$lambda1(this.f$0, sleepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WeekSleepFragment.m826loadDataOnce$lambda14(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2$lambda-1, reason: not valid java name */
    public static final void m827loadDataOnce$lambda2$lambda1(WeekSleepFragment this$0, QSleepWeekBarView.SleepDataBean sleepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWeekSleepBinding fragmentWeekSleepBinding = this$0.binding;
        String[] strArr = null;
        if (fragmentWeekSleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekSleepBinding = null;
        }
        int whatDay = DateUtil.getWhatDay(sleepDataBean.getUnixTime());
        DateUtil dateUtil = new DateUtil(sleepDataBean.getUnixTime(), true);
        TextView textView = fragmentWeekSleepBinding.tvSleepRange;
        StringBuilder sb = new StringBuilder();
        sb.append(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(dateUtil));
        sb.append(' ');
        String[] strArr2 = this$0.weeks;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weeks");
        } else {
            strArr = strArr2;
        }
        sb.append(strArr[whatDay]);
        textView.setText(sb.toString());
        if (sleepDataBean.getTotalTime() > 0) {
            fragmentWeekSleepBinding.tvSleepH.setText(String.valueOf(sleepDataBean.getTotalTime() / 60));
            fragmentWeekSleepBinding.tvSleepMin.setText(String.valueOf(sleepDataBean.getTotalTime() % 60));
        } else {
            fragmentWeekSleepBinding.tvSleepH.setText("--");
            fragmentWeekSleepBinding.tvSleepMin.setText("--");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-14, reason: not valid java name */
    public static final void m826loadDataOnce$lambda14(WeekSleepFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWeekSleepBinding fragmentWeekSleepBinding = null;
        if (list != null && (!list.isEmpty())) {
            FragmentWeekSleepBinding fragmentWeekSleepBinding2 = this$0.binding;
            if (fragmentWeekSleepBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekSleepBinding2 = null;
            }
            fragmentWeekSleepBinding2.weekSleepBarView.setData(list);
            Iterator it = list.iterator();
            int totalTime = 0;
            int i = 0;
            int deepTime = 0;
            int awake = 0;
            int rapid = 0;
            while (it.hasNext()) {
                QSleepWeekBarView.SleepDataBean sleepDataBean = (QSleepWeekBarView.SleepDataBean) it.next();
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
                fragmentWeekSleepBinding2.qcSleep1.setTitleValue(String.valueOf(i2 / 60));
                fragmentWeekSleepBinding2.qcSleep1.setValue2(String.valueOf(i2 % 60));
                float f = ((r15 * 100) * 1.0f) / i2;
                int i3 = ((awake / i) * 100) / i2;
                fragmentWeekSleepBinding2.qcSleep3.setTitleValue(String.valueOf(MathKt.roundToInt(f)));
                fragmentWeekSleepBinding2.qcSleep4.setTitleValue(String.valueOf(MathKt.roundToInt(((((r8 - rapid) - awake) * 100) * 1.0f) / totalTime)));
                int iCalcSleepScore = AlSleepUtil.calcSleepScore(i2 * 60, (deepTime / i) * 60, ((((totalTime - deepTime) - awake) - rapid) / i) * 60, 0);
                fragmentWeekSleepBinding2.qcSleep2.setTitleValue(String.valueOf(iCalcSleepScore));
                if (i2 >= 360) {
                    Drawable drawable = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable != null) {
                        QSleepAnalysisView qSleepAnalysisView = fragmentWeekSleepBinding2.qcSleep1;
                        String string = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView.setStatus(string, drawable);
                    }
                } else {
                    Drawable drawable2 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                    if (drawable2 != null) {
                        QSleepAnalysisView qSleepAnalysisView2 = fragmentWeekSleepBinding2.qcSleep1;
                        String string2 = this$0.getString(R.string.qc_text_127);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_127)");
                        qSleepAnalysisView2.setStatus(string2, drawable2);
                    }
                }
                if (iCalcSleepScore < 70) {
                    Drawable drawable3 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                    if (drawable3 != null) {
                        QSleepAnalysisView qSleepAnalysisView3 = fragmentWeekSleepBinding2.qcSleep2;
                        String string3 = this$0.getString(R.string.qc_text_127);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_127)");
                        qSleepAnalysisView3.setStatus(string3, drawable3);
                    }
                } else {
                    Drawable drawable4 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable4 != null) {
                        QSleepAnalysisView qSleepAnalysisView4 = fragmentWeekSleepBinding2.qcSleep2;
                        String string4 = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView4.setStatus(string4, drawable4);
                    }
                }
                if (f < 20.0f) {
                    Drawable drawable5 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep_1);
                    if (drawable5 != null) {
                        QSleepAnalysisView qSleepAnalysisView5 = fragmentWeekSleepBinding2.qcSleep3;
                        String string5 = this$0.getString(R.string.qc_text_127);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_127)");
                        qSleepAnalysisView5.setStatus(string5, drawable5);
                    }
                } else {
                    Drawable drawable6 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable6 != null) {
                        QSleepAnalysisView qSleepAnalysisView6 = fragmentWeekSleepBinding2.qcSleep3;
                        String string6 = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView6.setStatus(string6, drawable6);
                    }
                }
                if ((100 - f) - i3 < 70.0f) {
                    Drawable drawable7 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                    if (drawable7 != null) {
                        QSleepAnalysisView qSleepAnalysisView7 = fragmentWeekSleepBinding2.qcSleep4;
                        String string7 = this$0.getString(R.string.qc_text_126);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_126)");
                        qSleepAnalysisView7.setStatus(string7, drawable7);
                        return;
                    }
                    return;
                }
                Drawable drawable8 = ContextCompat.getDrawable(this$0.getActivity(), R.drawable.bg_rect_corner_8_sleep);
                if (drawable8 != null) {
                    QSleepAnalysisView qSleepAnalysisView8 = fragmentWeekSleepBinding2.qcSleep4;
                    String string8 = this$0.getString(R.string.qc_text_514);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_514)");
                    qSleepAnalysisView8.setStatus(string8, drawable8);
                    return;
                }
                return;
            }
            FragmentWeekSleepBinding fragmentWeekSleepBinding3 = this$0.binding;
            if (fragmentWeekSleepBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWeekSleepBinding = fragmentWeekSleepBinding3;
            }
            fragmentWeekSleepBinding.weekSleepBarView.setData(list);
            fragmentWeekSleepBinding.qcSleep1.setTitleValue("--");
            fragmentWeekSleepBinding.qcSleep1.setValue2("--");
            fragmentWeekSleepBinding.qcSleep3.setTitleValue("--");
            fragmentWeekSleepBinding.qcSleep4.setTitleValue("--");
            fragmentWeekSleepBinding.qcSleep2.setTitleValue("--");
            fragmentWeekSleepBinding.qcSleep1.setStatusNull("");
            fragmentWeekSleepBinding.qcSleep2.setStatusNull("");
            fragmentWeekSleepBinding.qcSleep3.setStatusNull("");
            fragmentWeekSleepBinding.qcSleep4.setStatusNull("");
            return;
        }
        FragmentWeekSleepBinding fragmentWeekSleepBinding4 = this$0.binding;
        if (fragmentWeekSleepBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWeekSleepBinding = fragmentWeekSleepBinding4;
        }
        fragmentWeekSleepBinding.weekSleepBarView.setData(list);
        fragmentWeekSleepBinding.qcSleep1.setTitleValue("--");
        fragmentWeekSleepBinding.qcSleep1.setValue2("--");
        fragmentWeekSleepBinding.qcSleep3.setTitleValue("--");
        fragmentWeekSleepBinding.qcSleep4.setTitleValue("--");
        fragmentWeekSleepBinding.qcSleep2.setTitleValue("--");
    }

    /* compiled from: WeekSleepFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeekSleepFragment newInstance() {
            return new WeekSleepFragment();
        }
    }
}
