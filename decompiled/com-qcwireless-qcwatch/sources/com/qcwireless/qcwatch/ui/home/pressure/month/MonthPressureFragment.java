package com.qcwireless.qcwatch.ui.home.pressure.month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.databinding.FragmentMonthPressureBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QPressureMonthHistoryBarChart;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;
import com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragmentViewModel;
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

/* compiled from: MonthPressureFragment.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/month/MonthPressureFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentMonthPressureBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "maxPressure", "", "minPressure", "totalIndex", "totalPressure", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/pressure/month/MonthPressureFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/pressure/month/MonthPressureFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonthPressureFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentMonthPressureBinding binding;
    private DateUtil date;
    private int maxPressure;
    private int minPressure;
    private int totalIndex;
    private int totalPressure;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MonthPressureFragment() {
        final MonthPressureFragment monthPressureFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MonthPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MonthPressureFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(monthPressureFragment, Reflection.getOrCreateKotlinClass(MonthPressureFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.minPressure = 100;
        this.maxPressure = 1;
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MonthPressureFragmentViewModel getViewModel() {
        return (MonthPressureFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMonthPressureBinding fragmentMonthPressureBindingInflate = FragmentMonthPressureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentMonthPressureBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentMonthPressureBindingInflate;
        if (fragmentMonthPressureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthPressureBindingInflate = null;
        }
        return fragmentMonthPressureBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        DateUtil firstDay = DateUtil.getFirstDayOfMonth(new DateUtil());
        DateUtil lastDay = DateUtil.getLastDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(firstDay, "firstDay");
        this.date = firstDay;
        MonthPressureFragmentViewModel viewModel = getViewModel();
        Intrinsics.checkNotNullExpressionValue(lastDay, "lastDay");
        viewModel.queryHistoryPressureByDate(firstDay, lastDay);
        FragmentMonthPressureBinding fragmentMonthPressureBinding = this.binding;
        if (fragmentMonthPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthPressureBinding = null;
        }
        fragmentMonthPressureBinding.qcDateChange.setMonth(new DateUtil());
        fragmentMonthPressureBinding.qcDateChange.setDateListener(new QDateMonthSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                DateUtil first = DateUtil.getFirstDayOfMonth(dateUtil);
                DateUtil last = DateUtil.getLastDayOfMonth(dateUtil);
                MonthPressureFragmentViewModel viewModel2 = this.this$0.getViewModel();
                Intrinsics.checkNotNullExpressionValue(first, "first");
                Intrinsics.checkNotNullExpressionValue(last, "last");
                viewModel2.queryHistoryPressureByDate(first, last);
            }
        });
        fragmentMonthPressureBinding.qcPressureChart.setListener(new QPressureMonthHistoryBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QPressureMonthHistoryBarChart.OnSelectedListener
            public final void onSelected(QPressureMonthHistoryBarChart.StepDataBean stepDataBean) {
                MonthPressureFragment.m813loadDataOnce$lambda2$lambda1(this.f$0, stepDataBean);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MonthPressureFragment.m814loadDataOnce$lambda4(this.f$0, (MonthPressureFragmentViewModel.MonthPressureUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2$lambda-1, reason: not valid java name */
    public static final void m813loadDataOnce$lambda2$lambda1(MonthPressureFragment this$0, QPressureMonthHistoryBarChart.StepDataBean stepDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (stepDataBean != null) {
            FragmentMonthPressureBinding fragmentMonthPressureBinding = this$0.binding;
            FragmentMonthPressureBinding fragmentMonthPressureBinding2 = null;
            if (fragmentMonthPressureBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding = null;
            }
            try {
                fragmentMonthPressureBinding.tvDayPressure.setText(String.valueOf(stepDataBean.getSteps()));
                fragmentMonthPressureBinding.tvPressureRange.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(stepDataBean.getTimeStamp(), true)));
                if (stepDataBean.getSteps() > 0) {
                    FragmentMonthPressureBinding fragmentMonthPressureBinding3 = this$0.binding;
                    if (fragmentMonthPressureBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding3 = null;
                    }
                    fragmentMonthPressureBinding3.tvDayPressure.setText(String.valueOf(stepDataBean.getSteps()));
                    FragmentMonthPressureBinding fragmentMonthPressureBinding4 = this$0.binding;
                    if (fragmentMonthPressureBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentMonthPressureBinding2 = fragmentMonthPressureBinding4;
                    }
                    fragmentMonthPressureBinding2.tvDayPressureUnit.setText(this$0.getString(R.string.qc_text_6666562));
                    return;
                }
                FragmentMonthPressureBinding fragmentMonthPressureBinding5 = this$0.binding;
                if (fragmentMonthPressureBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding5 = null;
                }
                fragmentMonthPressureBinding5.tvDayPressure.setText("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding6 = this$0.binding;
                if (fragmentMonthPressureBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMonthPressureBinding2 = fragmentMonthPressureBinding6;
                }
                fragmentMonthPressureBinding2.tvDayPressureUnit.setText("");
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4, reason: not valid java name */
    public static final void m814loadDataOnce$lambda4(MonthPressureFragment this$0, MonthPressureFragmentViewModel.MonthPressureUI monthPressureUI) {
        FragmentMonthPressureBinding fragmentMonthPressureBinding;
        FragmentMonthPressureBinding fragmentMonthPressureBinding2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (monthPressureUI != null) {
            FragmentMonthPressureBinding fragmentMonthPressureBinding3 = this$0.binding;
            if (fragmentMonthPressureBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding3 = null;
            }
            fragmentMonthPressureBinding3.qcPressureChart.setData(monthPressureUI.getData());
            this$0.totalIndex = 0;
            this$0.totalPressure = 0;
            this$0.minPressure = 100;
            this$0.maxPressure = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (QPressureMonthHistoryBarChart.StepDataBean stepDataBean : monthPressureUI.getData()) {
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
            if (!monthPressureUI.getData().isEmpty()) {
                if (this$0.totalIndex > 0) {
                    FragmentMonthPressureBinding fragmentMonthPressureBinding4 = this$0.binding;
                    if (fragmentMonthPressureBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding4 = null;
                    }
                    fragmentMonthPressureBinding4.tvAvgValue.setText(String.valueOf(MathKt.roundToInt((this$0.totalPressure * 1.0f) / this$0.totalIndex)));
                    FragmentMonthPressureBinding fragmentMonthPressureBinding5 = this$0.binding;
                    if (fragmentMonthPressureBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding5 = null;
                    }
                    fragmentMonthPressureBinding5.tvMinValue.setText(String.valueOf(this$0.minPressure));
                    FragmentMonthPressureBinding fragmentMonthPressureBinding6 = this$0.binding;
                    if (fragmentMonthPressureBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding6 = null;
                    }
                    fragmentMonthPressureBinding6.tvMaxValue.setText(String.valueOf(this$0.maxPressure));
                } else {
                    FragmentMonthPressureBinding fragmentMonthPressureBinding7 = this$0.binding;
                    if (fragmentMonthPressureBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding7 = null;
                    }
                    fragmentMonthPressureBinding7.tvAvgValue.setText("--");
                    FragmentMonthPressureBinding fragmentMonthPressureBinding8 = this$0.binding;
                    if (fragmentMonthPressureBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding8 = null;
                    }
                    fragmentMonthPressureBinding8.tvMinValue.setText("--");
                    FragmentMonthPressureBinding fragmentMonthPressureBinding9 = this$0.binding;
                    if (fragmentMonthPressureBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding9 = null;
                    }
                    fragmentMonthPressureBinding9.tvMaxValue.setText("--");
                }
                FragmentMonthPressureBinding fragmentMonthPressureBinding10 = this$0.binding;
                if (fragmentMonthPressureBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding10 = null;
                }
                QStepComponentView qStepComponentView = fragmentMonthPressureBinding10.pressureTotal;
                String string = this$0.getString(R.string.qc_text_6666562);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_6666562)");
                qStepComponentView.setTitleUnit(string);
                FragmentMonthPressureBinding fragmentMonthPressureBinding11 = this$0.binding;
                if (fragmentMonthPressureBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding11 = null;
                }
                QStepComponentView qStepComponentView2 = fragmentMonthPressureBinding11.pressureAvg;
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
                    FragmentMonthPressureBinding fragmentMonthPressureBinding12 = this$0.binding;
                    if (fragmentMonthPressureBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding12 = null;
                    }
                    fragmentMonthPressureBinding12.heartCircleView.dataPressureInit(iArr);
                    float f = i6;
                    float f2 = 100;
                    int iRound = Math.round(((i3 * 1.0f) / f) * f2);
                    int iRound2 = Math.round(((i2 * 1.0f) / f) * f2);
                    int iRound3 = Math.round(((i * 1.0f) / f) * f2);
                    int i7 = ((100 - iRound) - iRound2) - iRound3;
                    FragmentMonthPressureBinding fragmentMonthPressureBinding13 = this$0.binding;
                    if (fragmentMonthPressureBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding13 = null;
                    }
                    TextView textView = fragmentMonthPressureBinding13.tvValue1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(iRound);
                    sb.append('%');
                    textView.setText(sb.toString());
                    FragmentMonthPressureBinding fragmentMonthPressureBinding14 = this$0.binding;
                    if (fragmentMonthPressureBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding14 = null;
                    }
                    TextView textView2 = fragmentMonthPressureBinding14.tvValue2;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i7);
                    sb2.append('%');
                    textView2.setText(sb2.toString());
                    FragmentMonthPressureBinding fragmentMonthPressureBinding15 = this$0.binding;
                    if (fragmentMonthPressureBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding15 = null;
                    }
                    TextView textView3 = fragmentMonthPressureBinding15.tvValue4;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(iRound2);
                    sb3.append('%');
                    textView3.setText(sb3.toString());
                    FragmentMonthPressureBinding fragmentMonthPressureBinding16 = this$0.binding;
                    if (fragmentMonthPressureBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMonthPressureBinding16 = null;
                    }
                    TextView textView4 = fragmentMonthPressureBinding16.tvValue5;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(iRound3);
                    sb4.append('%');
                    textView4.setText(sb4.toString());
                    return;
                }
                int[] iArr2 = {0, 0, 0, 0, 0};
                FragmentMonthPressureBinding fragmentMonthPressureBinding17 = this$0.binding;
                if (fragmentMonthPressureBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding17 = null;
                }
                fragmentMonthPressureBinding17.heartCircleView.dataPressureInit(iArr2);
                FragmentMonthPressureBinding fragmentMonthPressureBinding18 = this$0.binding;
                if (fragmentMonthPressureBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding18 = null;
                }
                fragmentMonthPressureBinding18.tvValue1.setText("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding19 = this$0.binding;
                if (fragmentMonthPressureBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding19 = null;
                }
                fragmentMonthPressureBinding19.tvValue2.setText("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding20 = this$0.binding;
                if (fragmentMonthPressureBinding20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding20 = null;
                }
                fragmentMonthPressureBinding20.tvValue4.setText("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding21 = this$0.binding;
                if (fragmentMonthPressureBinding21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding21 = null;
                }
                fragmentMonthPressureBinding21.tvValue5.setText("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding22 = this$0.binding;
                if (fragmentMonthPressureBinding22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding22 = null;
                }
                fragmentMonthPressureBinding22.pressureTotal.setTitleValue("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding23 = this$0.binding;
                if (fragmentMonthPressureBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding23 = null;
                }
                fragmentMonthPressureBinding23.pressureTotal.setTitleUnit("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding24 = this$0.binding;
                if (fragmentMonthPressureBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding24 = null;
                }
                fragmentMonthPressureBinding24.pressureAvg.setTitleValue("--");
                FragmentMonthPressureBinding fragmentMonthPressureBinding25 = this$0.binding;
                if (fragmentMonthPressureBinding25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMonthPressureBinding2 = null;
                } else {
                    fragmentMonthPressureBinding2 = fragmentMonthPressureBinding25;
                }
                fragmentMonthPressureBinding2.pressureAvg.setTitleUnit("--");
                return;
            }
            int[] iArr3 = {0, 0, 0, 0, 0};
            FragmentMonthPressureBinding fragmentMonthPressureBinding26 = this$0.binding;
            if (fragmentMonthPressureBinding26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding26 = null;
            }
            fragmentMonthPressureBinding26.heartCircleView.dataPressureInit(iArr3);
            FragmentMonthPressureBinding fragmentMonthPressureBinding27 = this$0.binding;
            if (fragmentMonthPressureBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding27 = null;
            }
            fragmentMonthPressureBinding27.qcPressureChart.setData(new ArrayList());
            FragmentMonthPressureBinding fragmentMonthPressureBinding28 = this$0.binding;
            if (fragmentMonthPressureBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding28 = null;
            }
            fragmentMonthPressureBinding28.tvValue1.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding29 = this$0.binding;
            if (fragmentMonthPressureBinding29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding29 = null;
            }
            fragmentMonthPressureBinding29.tvValue2.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding30 = this$0.binding;
            if (fragmentMonthPressureBinding30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding30 = null;
            }
            fragmentMonthPressureBinding30.tvValue4.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding31 = this$0.binding;
            if (fragmentMonthPressureBinding31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding31 = null;
            }
            fragmentMonthPressureBinding31.tvValue5.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding32 = this$0.binding;
            if (fragmentMonthPressureBinding32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding32 = null;
            }
            fragmentMonthPressureBinding32.tvAvgValue.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding33 = this$0.binding;
            if (fragmentMonthPressureBinding33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding33 = null;
            }
            fragmentMonthPressureBinding33.tvMinValue.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding34 = this$0.binding;
            if (fragmentMonthPressureBinding34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding34 = null;
            }
            fragmentMonthPressureBinding34.tvMaxValue.setText("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding35 = this$0.binding;
            if (fragmentMonthPressureBinding35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding35 = null;
            }
            fragmentMonthPressureBinding35.pressureTotal.setTitleValue("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding36 = this$0.binding;
            if (fragmentMonthPressureBinding36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding36 = null;
            }
            fragmentMonthPressureBinding36.pressureTotal.setTitleUnit("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding37 = this$0.binding;
            if (fragmentMonthPressureBinding37 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding37 = null;
            }
            fragmentMonthPressureBinding37.pressureAvg.setTitleValue("--");
            FragmentMonthPressureBinding fragmentMonthPressureBinding38 = this$0.binding;
            if (fragmentMonthPressureBinding38 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthPressureBinding = null;
            } else {
                fragmentMonthPressureBinding = fragmentMonthPressureBinding38;
            }
            fragmentMonthPressureBinding.pressureAvg.setTitleUnit("--");
        }
    }

    /* compiled from: MonthPressureFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/month/MonthPressureFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/pressure/month/MonthPressureFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MonthPressureFragment newInstance() {
            return new MonthPressureFragment();
        }
    }
}
