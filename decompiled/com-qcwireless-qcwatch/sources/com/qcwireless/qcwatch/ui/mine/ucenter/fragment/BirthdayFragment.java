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
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import com.qcwireless.qcwatch.base.dialog.adapter.MonthArrayWheelAdapter;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.FragmentBirthdayBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import skin.support.content.res.SkinCompatResources;

/* compiled from: BirthdayFragment.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0016\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/BirthdayFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/base/dialog/adapter/ArrayWheelAdapter;", "", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentBirthdayBinding;", "currMonth", "", "currYear", "map", "", "minYear", "monthAdapter", "Lcom/qcwireless/qcwatch/base/dialog/adapter/MonthArrayWheelAdapter;", "monthData", "", "yearData", "initData", "", "context", "Landroid/content/Context;", "loadDataOnce", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setCurrItem", "year", "month", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BirthdayFragment extends BaseFragment {
    private ArrayWheelAdapter<String> adapter;
    private FragmentBirthdayBinding binding;
    private MonthArrayWheelAdapter<String> monthAdapter;
    private List<String> yearData = new ArrayList();
    private List<String> monthData = new ArrayList();
    private final int minYear = 1920;
    private int currYear = 1995;
    private int currMonth = 1;
    private final Map<Integer, String> map = new LinkedHashMap();

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentBirthdayBinding fragmentBirthdayBindingInflate = FragmentBirthdayBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentBirthdayBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentBirthdayBindingInflate;
        if (fragmentBirthdayBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBindingInflate = null;
        }
        return fragmentBirthdayBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        Map<Integer, String> map = this.map;
        String string = getString(R.string.qc_text_435);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_435)");
        map.put(1, string);
        Map<Integer, String> map2 = this.map;
        String string2 = getString(R.string.qc_text_436);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_436)");
        map2.put(2, string2);
        Map<Integer, String> map3 = this.map;
        String string3 = getString(R.string.qc_text_437);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_437)");
        map3.put(3, string3);
        Map<Integer, String> map4 = this.map;
        String string4 = getString(R.string.qc_text_438);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_438)");
        map4.put(4, string4);
        Map<Integer, String> map5 = this.map;
        String string5 = getString(R.string.qc_text_439);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_439)");
        map5.put(5, string5);
        Map<Integer, String> map6 = this.map;
        String string6 = getString(R.string.qc_text_440);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_440)");
        map6.put(6, string6);
        Map<Integer, String> map7 = this.map;
        String string7 = getString(R.string.qc_text_441);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_441)");
        map7.put(7, string7);
        Map<Integer, String> map8 = this.map;
        String string8 = getString(R.string.qc_text_442);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_442)");
        map8.put(8, string8);
        Map<Integer, String> map9 = this.map;
        String string9 = getString(R.string.qc_text_443);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.qc_text_443)");
        map9.put(9, string9);
        Map<Integer, String> map10 = this.map;
        String string10 = getString(R.string.qc_text_444);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.qc_text_444)");
        map10.put(10, string10);
        Map<Integer, String> map11 = this.map;
        String string11 = getString(R.string.qc_text_445);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.qc_text_445)");
        map11.put(11, string11);
        Map<Integer, String> map12 = this.map;
        String string12 = getString(R.string.qc_text_446);
        Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.qc_text_446)");
        map12.put(12, string12);
        FragmentBirthdayBinding fragmentBirthdayBinding = this.binding;
        FragmentBirthdayBinding fragmentBirthdayBinding2 = null;
        if (fragmentBirthdayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding = null;
        }
        fragmentBirthdayBinding.btnNext.setEnabled(true);
        initData(getActivity());
        View[] viewArr = new View[1];
        FragmentBirthdayBinding fragmentBirthdayBinding3 = this.binding;
        if (fragmentBirthdayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentBirthdayBinding2 = fragmentBirthdayBinding3;
        }
        viewArr[0] = fragmentBirthdayBinding2.btnNext;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.BirthdayFragment.loadDataOnce.1
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
                FragmentBirthdayBinding fragmentBirthdayBinding4 = BirthdayFragment.this.binding;
                if (fragmentBirthdayBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentBirthdayBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentBirthdayBinding4.btnNext)) {
                    if (new DateUtil(BirthdayFragment.this.currYear, BirthdayFragment.this.currMonth, 1).getUnixTimestamp() > new DateUtil().getUnixTimestamp()) {
                        String string13 = BirthdayFragment.this.getString(R.string.qc_text_229);
                        Intrinsics.checkNotNullExpressionValue(string13, "getString(R.string.qc_text_229)");
                        GlobalKt.showToast$default(string13, 0, 1, null);
                        return;
                    }
                    FragmentActivity activity = BirthdayFragment.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    FirstProfileActivity firstProfileActivity = (FirstProfileActivity) activity;
                    UserEntity userEntity = firstProfileActivity.getViewModel().getUserEntity();
                    String yyyyMMDate = new DateUtil(BirthdayFragment.this.currYear, BirthdayFragment.this.currMonth, 1).getYyyyMMDate();
                    Intrinsics.checkNotNullExpressionValue(yyyyMMDate, "DateUtil(currYear,currMonth,1).yyyyMMDate");
                    userEntity.setBirthday(yyyyMMDate);
                    firstProfileActivity.setCurrItem(3);
                }
            }
        });
        setCurrItem(1995, 1);
    }

    public final void setCurrItem(int year, int month) {
        int size = this.yearData.size();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            if (StringsKt.equals(year + "", this.yearData.get(i2), true)) {
                this.currYear = Integer.parseInt(this.yearData.get(i2));
                break;
            }
            i2++;
        }
        FragmentBirthdayBinding fragmentBirthdayBinding = this.binding;
        FragmentBirthdayBinding fragmentBirthdayBinding2 = null;
        if (fragmentBirthdayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding = null;
        }
        fragmentBirthdayBinding.wheelViewYear.setCurrentItem(this.currYear - this.minYear);
        int size2 = this.monthData.size();
        while (true) {
            if (i >= size2) {
                break;
            }
            if (month == Integer.parseInt(this.monthData.get(i))) {
                this.currMonth = Integer.parseInt(this.monthData.get(i));
                break;
            }
            i++;
        }
        FragmentBirthdayBinding fragmentBirthdayBinding3 = this.binding;
        if (fragmentBirthdayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentBirthdayBinding2 = fragmentBirthdayBinding3;
        }
        fragmentBirthdayBinding2.wheelViewMonth.setCurrentItem(this.currMonth - 1);
    }

    public final void initData(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = this.minYear;
        int year = new DateUtil().getYear();
        if (i <= year) {
            while (true) {
                this.yearData.add(i + "");
                if (i == year) {
                    break;
                } else {
                    i++;
                }
            }
        }
        this.adapter = new ArrayWheelAdapter<>(this.yearData);
        FragmentBirthdayBinding fragmentBirthdayBinding = this.binding;
        FragmentBirthdayBinding fragmentBirthdayBinding2 = null;
        if (fragmentBirthdayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding = null;
        }
        WheelView wheelView = fragmentBirthdayBinding.wheelViewYear;
        ArrayWheelAdapter<String> arrayWheelAdapter = this.adapter;
        if (arrayWheelAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            arrayWheelAdapter = null;
        }
        wheelView.setAdapter(arrayWheelAdapter);
        FragmentBirthdayBinding fragmentBirthdayBinding3 = this.binding;
        if (fragmentBirthdayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding3 = null;
        }
        fragmentBirthdayBinding3.wheelViewYear.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        FragmentBirthdayBinding fragmentBirthdayBinding4 = this.binding;
        if (fragmentBirthdayBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding4 = null;
        }
        fragmentBirthdayBinding4.wheelViewYear.setTextSize(30.0f);
        FragmentBirthdayBinding fragmentBirthdayBinding5 = this.binding;
        if (fragmentBirthdayBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding5 = null;
        }
        fragmentBirthdayBinding5.wheelViewYear.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        FragmentBirthdayBinding fragmentBirthdayBinding6 = this.binding;
        if (fragmentBirthdayBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding6 = null;
        }
        fragmentBirthdayBinding6.wheelViewYear.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        FragmentBirthdayBinding fragmentBirthdayBinding7 = this.binding;
        if (fragmentBirthdayBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding7 = null;
        }
        fragmentBirthdayBinding7.wheelViewYear.setItemsVisibleCount(5);
        FragmentBirthdayBinding fragmentBirthdayBinding8 = this.binding;
        if (fragmentBirthdayBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding8 = null;
        }
        fragmentBirthdayBinding8.wheelViewYear.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.BirthdayFragment$$ExternalSyntheticLambda1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public final void onItemSelected(int i2) {
                BirthdayFragment.m1010initData$lambda0(this.f$0, i2);
            }
        });
        this.monthData = new ArrayList();
        for (int i2 = 1; i2 < 13; i2++) {
            if (i2 < 10) {
                List<String> list = this.monthData;
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(i2);
                list.add(sb.toString());
            } else {
                this.monthData.add(i2 + "");
            }
        }
        this.monthAdapter = new MonthArrayWheelAdapter<>(this.monthData, this.map);
        FragmentBirthdayBinding fragmentBirthdayBinding9 = this.binding;
        if (fragmentBirthdayBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding9 = null;
        }
        WheelView wheelView2 = fragmentBirthdayBinding9.wheelViewMonth;
        MonthArrayWheelAdapter<String> monthArrayWheelAdapter = this.monthAdapter;
        if (monthArrayWheelAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("monthAdapter");
            monthArrayWheelAdapter = null;
        }
        wheelView2.setAdapter(monthArrayWheelAdapter);
        FragmentBirthdayBinding fragmentBirthdayBinding10 = this.binding;
        if (fragmentBirthdayBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding10 = null;
        }
        fragmentBirthdayBinding10.wheelViewMonth.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        FragmentBirthdayBinding fragmentBirthdayBinding11 = this.binding;
        if (fragmentBirthdayBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding11 = null;
        }
        fragmentBirthdayBinding11.wheelViewMonth.setTextSize(30.0f);
        FragmentBirthdayBinding fragmentBirthdayBinding12 = this.binding;
        if (fragmentBirthdayBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding12 = null;
        }
        fragmentBirthdayBinding12.wheelViewMonth.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        FragmentBirthdayBinding fragmentBirthdayBinding13 = this.binding;
        if (fragmentBirthdayBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding13 = null;
        }
        fragmentBirthdayBinding13.wheelViewMonth.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        FragmentBirthdayBinding fragmentBirthdayBinding14 = this.binding;
        if (fragmentBirthdayBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBirthdayBinding14 = null;
        }
        fragmentBirthdayBinding14.wheelViewMonth.setItemsVisibleCount(5);
        FragmentBirthdayBinding fragmentBirthdayBinding15 = this.binding;
        if (fragmentBirthdayBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentBirthdayBinding2 = fragmentBirthdayBinding15;
        }
        fragmentBirthdayBinding2.wheelViewMonth.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.BirthdayFragment$$ExternalSyntheticLambda0
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public final void onItemSelected(int i3) {
                BirthdayFragment.m1011initData$lambda1(this.f$0, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0, reason: not valid java name */
    public static final void m1010initData$lambda0(BirthdayFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currYear = Integer.parseInt(this$0.yearData.get(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1, reason: not valid java name */
    public static final void m1011initData$lambda1(BirthdayFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currMonth = Integer.parseInt(this$0.monthData.get(i));
    }
}
