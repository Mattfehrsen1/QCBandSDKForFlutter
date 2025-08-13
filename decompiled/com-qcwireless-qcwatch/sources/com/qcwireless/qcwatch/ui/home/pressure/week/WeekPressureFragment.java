package com.qcwireless.qcwatch.ui.home.pressure.week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.databinding.FragmentWeekPressureBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QPressureWeekHistoryBarChart;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;
import com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragmentViewModel;
import java.util.ArrayList;
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

/* compiled from: WeekPressureFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWeekPressureBinding;", "firstDay", "Lcom/qcwireless/qc_utils/date/DateUtil;", "maxPressure", "", "minPressure", "totalIndex", "totalPressure", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "weeks", "", "", "[Ljava/lang/String;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekPressureFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentWeekPressureBinding binding;
    private DateUtil firstDay;
    private int maxPressure;
    private int minPressure;
    private int totalIndex;
    private int totalPressure;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String[] weeks;

    /* JADX WARN: Multi-variable type inference failed */
    public WeekPressureFragment() {
        final WeekPressureFragment weekPressureFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WeekPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WeekPressureFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(weekPressureFragment, Reflection.getOrCreateKotlinClass(WeekPressureFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.minPressure = 100;
        this.maxPressure = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WeekPressureFragmentViewModel getViewModel() {
        return (WeekPressureFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWeekPressureBinding fragmentWeekPressureBindingInflate = FragmentWeekPressureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWeekPressureBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWeekPressureBindingInflate;
        if (fragmentWeekPressureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBindingInflate = null;
        }
        return fragmentWeekPressureBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
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
        WeekPressureFragmentViewModel viewModel = getViewModel();
        DateUtil dateUtilFirstDayOfWeek = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek, "firstDayOfWeek()");
        Intrinsics.checkNotNullExpressionValue(end, "end");
        viewModel.queryHistoryPressureByDate(dateUtilFirstDayOfWeek, end);
        FragmentWeekPressureBinding fragmentWeekPressureBinding = this.binding;
        if (fragmentWeekPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding = null;
        }
        QDateWeekSwitchView qDateWeekSwitchView = fragmentWeekPressureBinding.qcDateChange;
        DateUtil dateUtilFirstDayOfWeek2 = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek2, "firstDayOfWeek()");
        qDateWeekSwitchView.setWeekDayStart(dateUtilFirstDayOfWeek2);
        fragmentWeekPressureBinding.qcDateChange.setDateListener(new QDateWeekSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                dateUtil2.addDay(7);
                this.this$0.getViewModel().queryHistoryPressureByDate(dateUtil, dateUtil2);
                this.this$0.firstDay = dateUtil;
            }
        });
        fragmentWeekPressureBinding.qcPressureChart.setListener(new QPressureWeekHistoryBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QPressureWeekHistoryBarChart.OnSelectedListener
            public final void onSelected(QPressureWeekHistoryBarChart.StepDataBean stepDataBean) {
                WeekPressureFragment.m815loadDataOnce$lambda1$lambda0(this.f$0, stepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WeekPressureFragment.m816loadDataOnce$lambda3(this.f$0, (WeekPressureFragmentViewModel.WeekPressureUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1$lambda-0, reason: not valid java name */
    public static final void m815loadDataOnce$lambda1$lambda0(WeekPressureFragment this$0, QPressureWeekHistoryBarChart.StepDataBean stepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (stepDataBean != null) {
            try {
                FragmentWeekPressureBinding fragmentWeekPressureBinding = this$0.binding;
                FragmentWeekPressureBinding fragmentWeekPressureBinding2 = null;
                if (fragmentWeekPressureBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding = null;
                }
                fragmentWeekPressureBinding.tvDayPressure.setText(String.valueOf(stepDataBean.getSteps()));
                int whatDay = DateUtil.getWhatDay(stepDataBean.getTimeStamp());
                FragmentWeekPressureBinding fragmentWeekPressureBinding3 = this$0.binding;
                if (fragmentWeekPressureBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding3 = null;
                }
                TextView textView = fragmentWeekPressureBinding3.tvPressureRange;
                StringBuilder sb = new StringBuilder();
                sb.append(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(stepDataBean.getTimeStamp(), true)));
                sb.append(' ');
                String[] strArr = this$0.weeks;
                if (strArr == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weeks");
                    strArr = null;
                }
                sb.append(strArr[whatDay]);
                textView.setText(sb.toString());
                if (stepDataBean.getSteps() > 0) {
                    FragmentWeekPressureBinding fragmentWeekPressureBinding4 = this$0.binding;
                    if (fragmentWeekPressureBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWeekPressureBinding4 = null;
                    }
                    fragmentWeekPressureBinding4.tvDayPressure.setText(String.valueOf(stepDataBean.getSteps()));
                    FragmentWeekPressureBinding fragmentWeekPressureBinding5 = this$0.binding;
                    if (fragmentWeekPressureBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentWeekPressureBinding2 = fragmentWeekPressureBinding5;
                    }
                    fragmentWeekPressureBinding2.tvDayPressureUnit.setText(this$0.getString(R.string.qc_text_6666562));
                    return;
                }
                FragmentWeekPressureBinding fragmentWeekPressureBinding6 = this$0.binding;
                if (fragmentWeekPressureBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding6 = null;
                }
                fragmentWeekPressureBinding6.tvDayPressure.setText("--");
                FragmentWeekPressureBinding fragmentWeekPressureBinding7 = this$0.binding;
                if (fragmentWeekPressureBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentWeekPressureBinding2 = fragmentWeekPressureBinding7;
                }
                fragmentWeekPressureBinding2.tvDayPressureUnit.setText("");
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m816loadDataOnce$lambda3(WeekPressureFragment this$0, WeekPressureFragmentViewModel.WeekPressureUI weekPressureUI) {
        FragmentWeekPressureBinding fragmentWeekPressureBinding;
        FragmentWeekPressureBinding fragmentWeekPressureBinding2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Integer.valueOf(weekPressureUI.getData().size()));
        FragmentWeekPressureBinding fragmentWeekPressureBinding3 = this$0.binding;
        if (fragmentWeekPressureBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding3 = null;
        }
        fragmentWeekPressureBinding3.qcPressureChart.setData(weekPressureUI.getData());
        this$0.totalIndex = 0;
        this$0.totalPressure = 0;
        this$0.minPressure = 100;
        this$0.maxPressure = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (QPressureWeekHistoryBarChart.StepDataBean stepDataBean : weekPressureUI.getData()) {
            int i5 = this$0.minPressure;
            int steps = stepDataBean.getSteps();
            if (1 <= steps && steps < i5) {
                this$0.minPressure = stepDataBean.getSteps();
            }
            if (stepDataBean.getSteps() > this$0.maxPressure) {
                this$0.maxPressure = stepDataBean.getSteps();
            }
            this$0.totalPressure += stepDataBean.getSteps();
            if (stepDataBean.getSteps() >= 80) {
                i++;
            } else {
                int steps2 = stepDataBean.getSteps();
                if (60 <= steps2 && steps2 < 80) {
                    i2++;
                } else {
                    int steps3 = stepDataBean.getSteps();
                    if (30 <= steps3 && steps3 < 60) {
                        i4++;
                    } else {
                        int steps4 = stepDataBean.getSteps();
                        if (1 <= steps4 && steps4 < 30) {
                            i3++;
                        }
                    }
                }
            }
            if (stepDataBean.getSteps() > 0) {
                this$0.totalIndex++;
            }
        }
        if (!weekPressureUI.getData().isEmpty()) {
            if (this$0.totalIndex > 0) {
                FragmentWeekPressureBinding fragmentWeekPressureBinding4 = this$0.binding;
                if (fragmentWeekPressureBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding4 = null;
                }
                fragmentWeekPressureBinding4.tvAvgValue.setText(String.valueOf(MathKt.roundToInt((this$0.totalPressure * 1.0f) / this$0.totalIndex)));
                FragmentWeekPressureBinding fragmentWeekPressureBinding5 = this$0.binding;
                if (fragmentWeekPressureBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding5 = null;
                }
                fragmentWeekPressureBinding5.tvMinValue.setText(String.valueOf(this$0.minPressure));
                FragmentWeekPressureBinding fragmentWeekPressureBinding6 = this$0.binding;
                if (fragmentWeekPressureBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding6 = null;
                }
                fragmentWeekPressureBinding6.tvMaxValue.setText(String.valueOf(this$0.maxPressure));
            } else {
                FragmentWeekPressureBinding fragmentWeekPressureBinding7 = this$0.binding;
                if (fragmentWeekPressureBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding7 = null;
                }
                fragmentWeekPressureBinding7.tvAvgValue.setText("--");
                FragmentWeekPressureBinding fragmentWeekPressureBinding8 = this$0.binding;
                if (fragmentWeekPressureBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding8 = null;
                }
                fragmentWeekPressureBinding8.tvMinValue.setText("--");
                FragmentWeekPressureBinding fragmentWeekPressureBinding9 = this$0.binding;
                if (fragmentWeekPressureBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding9 = null;
                }
                fragmentWeekPressureBinding9.tvMaxValue.setText("--");
            }
            FragmentWeekPressureBinding fragmentWeekPressureBinding10 = this$0.binding;
            if (fragmentWeekPressureBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding10 = null;
            }
            QStepComponentView qStepComponentView = fragmentWeekPressureBinding10.pressureTotal;
            String string = this$0.getString(R.string.qc_text_6666562);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_6666562)");
            qStepComponentView.setTitleUnit(string);
            FragmentWeekPressureBinding fragmentWeekPressureBinding11 = this$0.binding;
            if (fragmentWeekPressureBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding11 = null;
            }
            QStepComponentView qStepComponentView2 = fragmentWeekPressureBinding11.pressureAvg;
            String string2 = this$0.getString(R.string.qc_text_6666562);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_6666562)");
            qStepComponentView2.setTitleUnit(string2);
            int[] iArr = {0, 0, 0, 0, 0};
            iArr[0] = i3;
            iArr[1] = i4;
            iArr[2] = i2;
            iArr[3] = i;
            int i6 = i4 + i3 + i2 + i;
            if (i6 > 0) {
                FragmentWeekPressureBinding fragmentWeekPressureBinding12 = this$0.binding;
                if (fragmentWeekPressureBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding12 = null;
                }
                fragmentWeekPressureBinding12.heartCircleView.dataPressureInit(iArr);
                float f = i6;
                float f2 = 100;
                int iRound = Math.round(((i3 * 1.0f) / f) * f2);
                int iRound2 = Math.round(((i2 * 1.0f) / f) * f2);
                int iRound3 = Math.round(((i * 1.0f) / f) * f2);
                int i7 = ((100 - iRound) - iRound2) - iRound3;
                FragmentWeekPressureBinding fragmentWeekPressureBinding13 = this$0.binding;
                if (fragmentWeekPressureBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding13 = null;
                }
                TextView textView = fragmentWeekPressureBinding13.tvValue1;
                StringBuilder sb = new StringBuilder();
                sb.append(iRound);
                sb.append('%');
                textView.setText(sb.toString());
                FragmentWeekPressureBinding fragmentWeekPressureBinding14 = this$0.binding;
                if (fragmentWeekPressureBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding14 = null;
                }
                TextView textView2 = fragmentWeekPressureBinding14.tvValue2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i7);
                sb2.append('%');
                textView2.setText(sb2.toString());
                FragmentWeekPressureBinding fragmentWeekPressureBinding15 = this$0.binding;
                if (fragmentWeekPressureBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding15 = null;
                }
                TextView textView3 = fragmentWeekPressureBinding15.tvValue4;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(iRound2);
                sb3.append('%');
                textView3.setText(sb3.toString());
                FragmentWeekPressureBinding fragmentWeekPressureBinding16 = this$0.binding;
                if (fragmentWeekPressureBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeekPressureBinding16 = null;
                }
                TextView textView4 = fragmentWeekPressureBinding16.tvValue5;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(iRound3);
                sb4.append('%');
                textView4.setText(sb4.toString());
                return;
            }
            int[] iArr2 = {0, 0, 0, 0, 0};
            FragmentWeekPressureBinding fragmentWeekPressureBinding17 = this$0.binding;
            if (fragmentWeekPressureBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding17 = null;
            }
            fragmentWeekPressureBinding17.heartCircleView.dataPressureInit(iArr2);
            FragmentWeekPressureBinding fragmentWeekPressureBinding18 = this$0.binding;
            if (fragmentWeekPressureBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding18 = null;
            }
            fragmentWeekPressureBinding18.tvValue1.setText("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding19 = this$0.binding;
            if (fragmentWeekPressureBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding19 = null;
            }
            fragmentWeekPressureBinding19.tvValue2.setText("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding20 = this$0.binding;
            if (fragmentWeekPressureBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding20 = null;
            }
            fragmentWeekPressureBinding20.tvValue4.setText("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding21 = this$0.binding;
            if (fragmentWeekPressureBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding21 = null;
            }
            fragmentWeekPressureBinding21.tvValue5.setText("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding22 = this$0.binding;
            if (fragmentWeekPressureBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding22 = null;
            }
            fragmentWeekPressureBinding22.pressureTotal.setTitleValue("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding23 = this$0.binding;
            if (fragmentWeekPressureBinding23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding23 = null;
            }
            fragmentWeekPressureBinding23.pressureTotal.setTitleUnit("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding24 = this$0.binding;
            if (fragmentWeekPressureBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding24 = null;
            }
            fragmentWeekPressureBinding24.pressureAvg.setTitleValue("--");
            FragmentWeekPressureBinding fragmentWeekPressureBinding25 = this$0.binding;
            if (fragmentWeekPressureBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekPressureBinding2 = null;
            } else {
                fragmentWeekPressureBinding2 = fragmentWeekPressureBinding25;
            }
            fragmentWeekPressureBinding2.pressureAvg.setTitleUnit("--");
            return;
        }
        int[] iArr3 = {0, 0, 0, 0, 0};
        FragmentWeekPressureBinding fragmentWeekPressureBinding26 = this$0.binding;
        if (fragmentWeekPressureBinding26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding26 = null;
        }
        fragmentWeekPressureBinding26.heartCircleView.dataPressureInit(iArr3);
        FragmentWeekPressureBinding fragmentWeekPressureBinding27 = this$0.binding;
        if (fragmentWeekPressureBinding27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding27 = null;
        }
        fragmentWeekPressureBinding27.qcPressureChart.setData(new ArrayList());
        FragmentWeekPressureBinding fragmentWeekPressureBinding28 = this$0.binding;
        if (fragmentWeekPressureBinding28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding28 = null;
        }
        fragmentWeekPressureBinding28.tvValue1.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding29 = this$0.binding;
        if (fragmentWeekPressureBinding29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding29 = null;
        }
        fragmentWeekPressureBinding29.tvValue2.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding30 = this$0.binding;
        if (fragmentWeekPressureBinding30 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding30 = null;
        }
        fragmentWeekPressureBinding30.tvValue4.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding31 = this$0.binding;
        if (fragmentWeekPressureBinding31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding31 = null;
        }
        fragmentWeekPressureBinding31.tvValue5.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding32 = this$0.binding;
        if (fragmentWeekPressureBinding32 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding32 = null;
        }
        fragmentWeekPressureBinding32.tvAvgValue.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding33 = this$0.binding;
        if (fragmentWeekPressureBinding33 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding33 = null;
        }
        fragmentWeekPressureBinding33.tvMinValue.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding34 = this$0.binding;
        if (fragmentWeekPressureBinding34 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding34 = null;
        }
        fragmentWeekPressureBinding34.tvMaxValue.setText("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding35 = this$0.binding;
        if (fragmentWeekPressureBinding35 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding35 = null;
        }
        fragmentWeekPressureBinding35.pressureTotal.setTitleValue("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding36 = this$0.binding;
        if (fragmentWeekPressureBinding36 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding36 = null;
        }
        fragmentWeekPressureBinding36.pressureTotal.setTitleUnit("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding37 = this$0.binding;
        if (fragmentWeekPressureBinding37 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding37 = null;
        }
        fragmentWeekPressureBinding37.pressureAvg.setTitleValue("--");
        FragmentWeekPressureBinding fragmentWeekPressureBinding38 = this$0.binding;
        if (fragmentWeekPressureBinding38 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekPressureBinding = null;
        } else {
            fragmentWeekPressureBinding = fragmentWeekPressureBinding38;
        }
        fragmentWeekPressureBinding.pressureAvg.setTitleUnit("--");
    }

    /* compiled from: WeekPressureFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeekPressureFragment newInstance() {
            return new WeekPressureFragment();
        }
    }
}
