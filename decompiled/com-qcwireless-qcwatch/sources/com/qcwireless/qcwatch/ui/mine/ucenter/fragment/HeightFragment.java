package com.qcwireless.qcwatch.ui.mine.ucenter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.FragmentHeightBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import skin.support.content.res.SkinCompatResources;

/* compiled from: HeightFragment.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J2\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010#\u001a\u00020\u0017J\b\u0010$\u001a\u00020\u001eH\u0002J\b\u0010%\u001a\u00020\u001eH\u0016J&\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0016\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\u0017R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0010R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/HeightFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/base/dialog/adapter/ArrayWheelAdapter;", "", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentHeightBinding;", "currNumber", "", "data", "", "iHeightList", "getIHeightList", "()Ljava/util/List;", "setIHeightList", "(Ljava/util/List;)V", "imperialList", "lastIndex", "mHeightList", "getMHeightList", "setMHeightList", "mOrI", "", "maxHeightImperial", "metricList", "minHeightImperial", "minInch", "minMetric", "initData", "", "context", "Landroid/content/Context;", "dataList", "dataList1", "metric", "initDialogData", "loadDataOnce", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setCurrItem", "number", "unit", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeightFragment extends BaseFragment {
    private ArrayWheelAdapter<String> adapter;
    private FragmentHeightBinding binding;
    private int currNumber;
    private boolean mOrI;
    private List<String> data = new ArrayList();
    private List<String> metricList = new ArrayList();
    private List<String> imperialList = new ArrayList();
    private final int minInch = 13;
    private final int minMetric = 33;
    private int lastIndex = -1;
    private List<String> iHeightList = new ArrayList();
    private List<String> mHeightList = new ArrayList();
    private final int minHeightImperial = 13;
    private final int maxHeightImperial = 100;

    public final List<String> getIHeightList() {
        return this.iHeightList;
    }

    public final void setIHeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.iHeightList = list;
    }

    public final List<String> getMHeightList() {
        return this.mHeightList;
    }

    public final void setMHeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mHeightList = list;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentHeightBinding fragmentHeightBindingInflate = FragmentHeightBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentHeightBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentHeightBindingInflate;
        if (fragmentHeightBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBindingInflate = null;
        }
        return fragmentHeightBindingInflate.getRoot();
    }

    private final void initDialogData() {
        int i = 33;
        while (true) {
            int i2 = i + 1;
            this.mHeightList.add(String.valueOf(i));
            if (i2 > 255) {
                break;
            } else {
                i = i2;
            }
        }
        int i3 = this.minHeightImperial;
        int i4 = this.maxHeightImperial;
        if (i3 <= i4) {
            while (true) {
                List<String> list = this.iHeightList;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%d'%d''", Arrays.copyOf(new Object[]{Integer.valueOf(i3 / 12), Integer.valueOf(i3 % 12)}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                list.add(str);
                if (i3 == i4) {
                    break;
                } else {
                    i3++;
                }
            }
        }
        initData(getActivity(), this.mHeightList, this.iHeightList, true);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        initDialogData();
        FragmentHeightBinding fragmentHeightBinding = this.binding;
        FragmentHeightBinding fragmentHeightBinding2 = null;
        if (fragmentHeightBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding = null;
        }
        fragmentHeightBinding.btnNext.setEnabled(true);
        View[] viewArr = new View[1];
        FragmentHeightBinding fragmentHeightBinding3 = this.binding;
        if (fragmentHeightBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHeightBinding2 = fragmentHeightBinding3;
        }
        viewArr[0] = fragmentHeightBinding2.btnNext;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.HeightFragment.loadDataOnce.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                FragmentHeightBinding fragmentHeightBinding4 = HeightFragment.this.binding;
                if (fragmentHeightBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHeightBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentHeightBinding4.btnNext)) {
                    FragmentActivity activity = HeightFragment.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    FirstProfileActivity firstProfileActivity = (FirstProfileActivity) activity;
                    firstProfileActivity.getViewModel().getUserEntity().setHeight(HeightFragment.this.currNumber);
                    firstProfileActivity.setCurrItem(4);
                }
            }
        });
        setCurrItem(175, true);
    }

    public final void initData(Context context, List<String> dataList, List<String> dataList1, boolean metric) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        Intrinsics.checkNotNullParameter(dataList1, "dataList1");
        this.metricList.clear();
        this.imperialList.clear();
        this.metricList.addAll(dataList);
        this.imperialList.addAll(dataList1);
        if (metric) {
            this.mOrI = true;
            this.data = dataList;
        } else {
            this.mOrI = false;
            this.data = dataList1;
        }
        initData();
        ArrayList arrayList = new ArrayList();
        arrayList.add("cm");
        arrayList.add("Ft-in");
        FragmentHeightBinding fragmentHeightBinding = this.binding;
        FragmentHeightBinding fragmentHeightBinding2 = null;
        if (fragmentHeightBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding = null;
        }
        fragmentHeightBinding.wheelViewUnit.setAdapter(new ArrayWheelAdapter(arrayList));
        FragmentHeightBinding fragmentHeightBinding3 = this.binding;
        if (fragmentHeightBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding3 = null;
        }
        fragmentHeightBinding3.wheelViewUnit.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        FragmentHeightBinding fragmentHeightBinding4 = this.binding;
        if (fragmentHeightBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding4 = null;
        }
        fragmentHeightBinding4.wheelViewUnit.setTextSize(30.0f);
        FragmentHeightBinding fragmentHeightBinding5 = this.binding;
        if (fragmentHeightBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding5 = null;
        }
        fragmentHeightBinding5.wheelViewUnit.setDividerColor(ContextCompat.getColor(requireContext(), R.color.dialog_wheel_driver));
        FragmentHeightBinding fragmentHeightBinding6 = this.binding;
        if (fragmentHeightBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding6 = null;
        }
        fragmentHeightBinding6.wheelViewUnit.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        FragmentHeightBinding fragmentHeightBinding7 = this.binding;
        if (fragmentHeightBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding7 = null;
        }
        fragmentHeightBinding7.wheelViewUnit.setItemsVisibleCount(2);
        FragmentHeightBinding fragmentHeightBinding8 = this.binding;
        if (fragmentHeightBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding8 = null;
        }
        fragmentHeightBinding8.wheelViewUnit.setCyclic(false);
        FragmentHeightBinding fragmentHeightBinding9 = this.binding;
        if (fragmentHeightBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHeightBinding2 = fragmentHeightBinding9;
        }
        fragmentHeightBinding2.wheelViewUnit.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.HeightFragment$$ExternalSyntheticLambda1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                HeightFragment.m1013initData$lambda0(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0, reason: not valid java name */
    public static final void m1013initData$lambda0(HeightFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Integer.valueOf(i));
        if (i == this$0.lastIndex) {
            return;
        }
        this$0.lastIndex = i;
        if (i == 0) {
            UserConfig.INSTANCE.getInstance().setMetric(true);
            UserConfig.INSTANCE.getInstance().save();
            this$0.mOrI = true;
            this$0.data = this$0.metricList;
            this$0.initData();
            return;
        }
        if (i != 1) {
            return;
        }
        UserConfig.INSTANCE.getInstance().setMetric(false);
        UserConfig.INSTANCE.getInstance().save();
        this$0.data = this$0.imperialList;
        this$0.mOrI = false;
        this$0.initData();
    }

    public final void setCurrItem(int number, boolean unit) {
        this.currNumber = number;
        XLog.i(Integer.valueOf(number));
        FragmentHeightBinding fragmentHeightBinding = null;
        if (unit) {
            FragmentHeightBinding fragmentHeightBinding2 = this.binding;
            if (fragmentHeightBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHeightBinding2 = null;
            }
            fragmentHeightBinding2.wheelView.setCurrentItem(number - this.minMetric);
            FragmentHeightBinding fragmentHeightBinding3 = this.binding;
            if (fragmentHeightBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentHeightBinding = fragmentHeightBinding3;
            }
            fragmentHeightBinding.wheelViewUnit.setCurrentItem(0);
            return;
        }
        int iCm2Inch = (int) BottomHeightWeightDialog.cm2Inch(number);
        FragmentHeightBinding fragmentHeightBinding4 = this.binding;
        if (fragmentHeightBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding4 = null;
        }
        fragmentHeightBinding4.wheelView.setCurrentItem(iCm2Inch - this.minInch);
        FragmentHeightBinding fragmentHeightBinding5 = this.binding;
        if (fragmentHeightBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHeightBinding = fragmentHeightBinding5;
        }
        fragmentHeightBinding.wheelViewUnit.setCurrentItem(1);
    }

    private final void initData() {
        this.adapter = new ArrayWheelAdapter<>(this.data);
        FragmentHeightBinding fragmentHeightBinding = this.binding;
        FragmentHeightBinding fragmentHeightBinding2 = null;
        if (fragmentHeightBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding = null;
        }
        WheelView wheelView = fragmentHeightBinding.wheelView;
        ArrayWheelAdapter<String> arrayWheelAdapter = this.adapter;
        if (arrayWheelAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            arrayWheelAdapter = null;
        }
        wheelView.setAdapter(arrayWheelAdapter);
        FragmentHeightBinding fragmentHeightBinding3 = this.binding;
        if (fragmentHeightBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding3 = null;
        }
        fragmentHeightBinding3.wheelView.setTextColorCenter(SkinCompatResources.getColor(getContext(), R.color.color_FF6A00));
        FragmentHeightBinding fragmentHeightBinding4 = this.binding;
        if (fragmentHeightBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding4 = null;
        }
        fragmentHeightBinding4.wheelView.setTextSize(30.0f);
        FragmentHeightBinding fragmentHeightBinding5 = this.binding;
        if (fragmentHeightBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding5 = null;
        }
        fragmentHeightBinding5.wheelView.setDividerColor(ContextCompat.getColor(requireContext(), R.color.dialog_wheel_driver));
        FragmentHeightBinding fragmentHeightBinding6 = this.binding;
        if (fragmentHeightBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding6 = null;
        }
        fragmentHeightBinding6.wheelView.setTextColorOut(ContextCompat.getColor(requireContext(), R.color.dialog_wheel_text_out));
        FragmentHeightBinding fragmentHeightBinding7 = this.binding;
        if (fragmentHeightBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding7 = null;
        }
        fragmentHeightBinding7.wheelView.setItemsVisibleCount(5);
        FragmentHeightBinding fragmentHeightBinding8 = this.binding;
        if (fragmentHeightBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHeightBinding8 = null;
        }
        fragmentHeightBinding8.wheelView.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.HeightFragment$$ExternalSyntheticLambda0
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                HeightFragment.m1014initData$lambda1(this.f$0, i);
            }
        });
        if (this.mOrI) {
            FragmentHeightBinding fragmentHeightBinding9 = this.binding;
            if (fragmentHeightBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentHeightBinding2 = fragmentHeightBinding9;
            }
            fragmentHeightBinding2.wheelView.setCurrentItem(this.currNumber - this.minMetric);
            return;
        }
        FragmentHeightBinding fragmentHeightBinding10 = this.binding;
        if (fragmentHeightBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHeightBinding2 = fragmentHeightBinding10;
        }
        fragmentHeightBinding2.wheelView.setCurrentItem(MetricUtilsKt.cmToIn(this.currNumber) - this.minInch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1, reason: not valid java name */
    public static final void m1014initData$lambda1(HeightFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mOrI) {
            this$0.currNumber = Integer.parseInt(this$0.data.get(i));
        } else {
            String str = this$0.data.get(i);
            if (str.length() == 5) {
                String strSubstring = str.substring(0, 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String strSubstring2 = str.substring(2, 3);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                XLog.i(Integer.valueOf((Integer.parseInt(strSubstring) * 12) + Integer.parseInt(strSubstring2)));
                this$0.currNumber = (int) BottomHeightWeightDialog.inch2Cm((Integer.parseInt(strSubstring) * 12) + Integer.parseInt(strSubstring2));
            } else if (str.length() == 6) {
                String strSubstring3 = str.substring(0, 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                String strSubstring4 = str.substring(2, 4);
                Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
                XLog.i(Integer.valueOf((Integer.parseInt(strSubstring3) * 12) + Integer.parseInt(strSubstring4)));
                this$0.currNumber = (int) BottomHeightWeightDialog.inch2Cm((Integer.parseInt(strSubstring3) * 12) + Integer.parseInt(strSubstring4));
            }
        }
        XLog.i(Integer.valueOf(this$0.currNumber));
    }
}
