package com.qcwireless.qcwatch.ui.device.remind.drink;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomTimeDialog;
import com.qcwireless.qcwatch.base.dialog.bean.WeekRepeat;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityDrinkBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.bean.device.DrinkBean;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;
import com.qcwireless.qcwatch.ui.base.view.QSwitchView;
import com.qcwireless.qcwatch.ui.device.week.adapter.ModeAdapter;
import com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DrinkActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0014J\u001e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0014J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/drink/DrinkActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/week/adapter/ModeAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDrinkBinding;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "drinkViewModel", "Lcom/qcwireless/qcwatch/ui/device/remind/drink/DrinkViewModel;", "getDrinkViewModel", "()Lcom/qcwireless/qcwatch/ui/device/remind/drink/DrinkViewModel;", "drinkViewModel$delegate", "Lkotlin/Lazy;", "drinkClickListener", "", "clickWith", "Lcom/qcwireless/qcwatch/ui/base/view/QSettingItemWithClick;", "drinkIndex", "", "modifyWeek", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "showTimeDialog", "title", "", "time", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "weekInit", "repeat", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DrinkActivity extends BaseActivity {
    private ModeAdapter adapter;
    private ActivityDrinkBinding binding;
    private DeviceSetting deviceSetting;

    /* renamed from: drinkViewModel$delegate, reason: from kotlin metadata */
    private final Lazy drinkViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public DrinkActivity() {
        final DrinkActivity drinkActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.drinkViewModel = LazyKt.lazy(new Function0<DrinkViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DrinkViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(drinkActivity, Reflection.getOrCreateKotlinClass(DrinkViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DrinkViewModel getDrinkViewModel() {
        return (DrinkViewModel) this.drinkViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDrinkBinding activityDrinkBindingInflate = ActivityDrinkBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDrinkBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDrinkBindingInflate;
        if (activityDrinkBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkBindingInflate = null;
        }
        ConstraintLayout root = activityDrinkBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        DrinkActivity drinkActivity = this;
        ModeAdapter modeAdapter = new ModeAdapter(drinkActivity, getDrinkViewModel().getWeekList());
        this.adapter = modeAdapter;
        modeAdapter.setSelectMode(WeekDayAdapter.SelectMode.MULTI_SELECT);
        getDrinkViewModel().getDrinkSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        final ActivityDrinkBinding activityDrinkBinding = this.binding;
        ActivityDrinkBinding activityDrinkBinding2 = null;
        if (activityDrinkBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkBinding = null;
        }
        activityDrinkBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_51));
        ViewKt.visible(activityDrinkBinding.titleBar.tvRightText);
        activityDrinkBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        activityDrinkBinding.drinkSwitch.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DrinkActivity.m579setupViews$lambda1$lambda0(this.f$0, activityDrinkBinding, compoundButton, z);
            }
        });
        QSettingItemWithClick drink0 = activityDrinkBinding.drink0;
        Intrinsics.checkNotNullExpressionValue(drink0, "drink0");
        drinkClickListener(drink0, 0);
        QSettingItemWithClick drink1 = activityDrinkBinding.drink1;
        Intrinsics.checkNotNullExpressionValue(drink1, "drink1");
        drinkClickListener(drink1, 1);
        QSettingItemWithClick drink2 = activityDrinkBinding.drink2;
        Intrinsics.checkNotNullExpressionValue(drink2, "drink2");
        drinkClickListener(drink2, 2);
        QSettingItemWithClick drink3 = activityDrinkBinding.drink3;
        Intrinsics.checkNotNullExpressionValue(drink3, "drink3");
        drinkClickListener(drink3, 3);
        QSettingItemWithClick drink4 = activityDrinkBinding.drink4;
        Intrinsics.checkNotNullExpressionValue(drink4, "drink4");
        drinkClickListener(drink4, 4);
        QSettingItemWithClick drink5 = activityDrinkBinding.drink5;
        Intrinsics.checkNotNullExpressionValue(drink5, "drink5");
        drinkClickListener(drink5, 5);
        QSettingItemWithClick drink6 = activityDrinkBinding.drink6;
        Intrinsics.checkNotNullExpressionValue(drink6, "drink6");
        drinkClickListener(drink6, 6);
        QSettingItemWithClick drink7 = activityDrinkBinding.drink7;
        Intrinsics.checkNotNullExpressionValue(drink7, "drink7");
        drinkClickListener(drink7, 7);
        activityDrinkBinding.weekDayRecycler.setLayoutManager(new LinearLayoutManager(drinkActivity));
        RecyclerView recyclerView = activityDrinkBinding.weekDayRecycler;
        ModeAdapter modeAdapter2 = this.adapter;
        if (modeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter2 = null;
        }
        recyclerView.setAdapter(modeAdapter2);
        getDrinkViewModel().getUiStatue().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DrinkActivity.m580setupViews$lambda3(this.f$0, (DeviceSetting) obj);
            }
        });
        ModeAdapter modeAdapter3 = this.adapter;
        if (modeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter3 = null;
        }
        modeAdapter3.setOnItemMultiSelectListener(new WeekDayAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$$ExternalSyntheticLambda4
            @Override // com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter.OnItemMultiSelectListener
            public final void onSelected(WeekDayAdapter.Operation operation, int i, boolean z) {
                DrinkActivity.m581setupViews$lambda5(this.f$0, operation, i, z);
            }
        });
        View[] viewArr = new View[1];
        ActivityDrinkBinding activityDrinkBinding3 = this.binding;
        if (activityDrinkBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDrinkBinding2 = activityDrinkBinding3;
        }
        viewArr[0] = activityDrinkBinding2.titleBar.tvRightText;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.setupViews.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
            
                if (r5.getDrinkWeek() == 128) goto L22;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2(android.view.View r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "$this$setOnClickListener"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r0 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    com.qcwireless.qcwatch.databinding.ActivityDrinkBinding r0 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.access$getBinding$p(r0)
                    r1 = 0
                    if (r0 != 0) goto L14
                    java.lang.String r0 = "binding"
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r0 = r1
                L14:
                    com.qcwireless.qcwatch.databinding.LayoutTitleBarBinding r0 = r0.titleBar
                    android.widget.TextView r0 = r0.tvRightText
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
                    if (r5 == 0) goto L93
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.access$getDeviceSetting$p(r5)
                    java.lang.String r0 = "deviceSetting"
                    if (r5 != 0) goto L2c
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r5 = r1
                L2c:
                    boolean r5 = r5.getDrinkSwitch()
                    if (r5 == 0) goto L6e
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.access$getDeviceSetting$p(r5)
                    if (r5 != 0) goto L3e
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r5 = r1
                L3e:
                    int r5 = r5.getDrinkWeek()
                    if (r5 == 0) goto L58
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.access$getDeviceSetting$p(r5)
                    if (r5 != 0) goto L50
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r5 = r1
                L50:
                    int r5 = r5.getDrinkWeek()
                    r2 = 128(0x80, float:1.794E-43)
                    if (r5 != r2) goto L6e
                L58:
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    r0 = 2131821135(0x7f11024f, float:1.9275005E38)
                    java.lang.String r5 = r5.getString(r0)
                    java.lang.String r0 = "getString(R.string.qc_text_282)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                    java.lang.CharSequence r5 = (java.lang.CharSequence) r5
                    r0 = 0
                    r2 = 1
                    com.qcwireless.qcwatch.base.view.GlobalKt.showToast$default(r5, r0, r2, r1)
                    return
                L6e:
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.access$getDrinkViewModel(r5)
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r2 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r2 = r2.getInstance()
                    java.lang.String r2 = r2.getDeviceAddressNoClear()
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r3 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r3 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.access$getDeviceSetting$p(r3)
                    if (r3 != 0) goto L8a
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    goto L8b
                L8a:
                    r1 = r3
                L8b:
                    r5.saveDrinkSetting(r2, r1)
                    com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity r5 = com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.this
                    r5.finish()
                L93:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity.AnonymousClass4.invoke2(android.view.View):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m579setupViews$lambda1$lambda0(DrinkActivity this$0, ActivityDrinkBinding this_run, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setDrinkSwitch(z);
        if (z) {
            ViewKt.visible(this_run.drinkGroup);
            return;
        }
        ViewKt.gone(this_run.drinkGroup);
        for (int i = 0; i < 8; i++) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.getDrinkArray()[i].setSwitch(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m580setupViews$lambda3(DrinkActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        ActivityDrinkBinding activityDrinkBinding = this$0.binding;
        if (activityDrinkBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkBinding = null;
        }
        activityDrinkBinding.drinkSwitch.setChecked(it.getDrinkSwitch());
        DrinkBean[] drinkArray = it.getDrinkArray();
        int length = drinkArray.length;
        for (int i = 0; i < length; i++) {
            DrinkBean drinkBean = drinkArray[i];
            switch (i) {
                case 0:
                    activityDrinkBinding.drink0.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink0.setChecked(drinkBean.getSwitch());
                    break;
                case 1:
                    activityDrinkBinding.drink1.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink1.setChecked(drinkBean.getSwitch());
                    break;
                case 2:
                    activityDrinkBinding.drink2.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink2.setChecked(drinkBean.getSwitch());
                    break;
                case 3:
                    activityDrinkBinding.drink3.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink3.setChecked(drinkBean.getSwitch());
                    break;
                case 4:
                    activityDrinkBinding.drink4.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink4.setChecked(drinkBean.getSwitch());
                    break;
                case 5:
                    activityDrinkBinding.drink5.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink5.setChecked(drinkBean.getSwitch());
                    break;
                case 6:
                    activityDrinkBinding.drink6.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink6.setChecked(drinkBean.getSwitch());
                    break;
                case 7:
                    activityDrinkBinding.drink7.setLeftText(DateUtil.dayMinToStr(drinkBean.getTime()));
                    activityDrinkBinding.drink7.setChecked(drinkBean.getSwitch());
                    break;
            }
        }
        this$0.weekInit(it.getDrinkWeek());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m581setupViews$lambda5(DrinkActivity this$0, WeekDayAdapter.Operation operation, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDrinkViewModel().getWeekList().get(i).setCheck(!this$0.getDrinkViewModel().getWeekList().get(i).isCheck());
        ModeAdapter modeAdapter = this$0.adapter;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.notifyDataSetChanged();
        this$0.modifyWeek();
    }

    private final void drinkClickListener(QSettingItemWithClick clickWith, final int drinkIndex) {
        clickWith.setmOnLSettingItemWithClick(new QSettingItemWithClick.OnLSettingItemWithClick() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$drinkClickListener$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick.OnLSettingItemWithClick
            public void click(int id, boolean isChecked) {
                DeviceSetting deviceSetting = null;
                switch (drinkIndex) {
                    case 0:
                        DrinkActivity drinkActivity = this;
                        String string = drinkActivity.getString(R.string.qc_text_63);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_63)");
                        DeviceSetting deviceSetting2 = this.deviceSetting;
                        if (deviceSetting2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting2;
                        }
                        drinkActivity.showTimeDialog(string, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 1:
                        DrinkActivity drinkActivity2 = this;
                        String string2 = drinkActivity2.getString(R.string.qc_text_64);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_64)");
                        DeviceSetting deviceSetting3 = this.deviceSetting;
                        if (deviceSetting3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting3;
                        }
                        drinkActivity2.showTimeDialog(string2, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 2:
                        DrinkActivity drinkActivity3 = this;
                        String string3 = drinkActivity3.getString(R.string.qc_text_65);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_65)");
                        DeviceSetting deviceSetting4 = this.deviceSetting;
                        if (deviceSetting4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting4;
                        }
                        drinkActivity3.showTimeDialog(string3, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 3:
                        DrinkActivity drinkActivity4 = this;
                        String string4 = drinkActivity4.getString(R.string.qc_text_66);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_66)");
                        DeviceSetting deviceSetting5 = this.deviceSetting;
                        if (deviceSetting5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting5;
                        }
                        drinkActivity4.showTimeDialog(string4, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 4:
                        DrinkActivity drinkActivity5 = this;
                        String string5 = drinkActivity5.getString(R.string.qc_text_67);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_67)");
                        DeviceSetting deviceSetting6 = this.deviceSetting;
                        if (deviceSetting6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting6;
                        }
                        drinkActivity5.showTimeDialog(string5, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 5:
                        DrinkActivity drinkActivity6 = this;
                        String string6 = drinkActivity6.getString(R.string.qc_text_68);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_68)");
                        DeviceSetting deviceSetting7 = this.deviceSetting;
                        if (deviceSetting7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting7;
                        }
                        drinkActivity6.showTimeDialog(string6, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 6:
                        DrinkActivity drinkActivity7 = this;
                        String string7 = drinkActivity7.getString(R.string.qc_text_69);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_69)");
                        DeviceSetting deviceSetting8 = this.deviceSetting;
                        if (deviceSetting8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting8;
                        }
                        drinkActivity7.showTimeDialog(string7, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                    case 7:
                        DrinkActivity drinkActivity8 = this;
                        String string8 = drinkActivity8.getString(R.string.qc_text_70);
                        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_70)");
                        DeviceSetting deviceSetting9 = this.deviceSetting;
                        if (deviceSetting9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting9;
                        }
                        drinkActivity8.showTimeDialog(string8, deviceSetting.getDrinkArray()[drinkIndex].getTime(), drinkIndex);
                        break;
                }
            }
        });
        clickWith.setQSettingItemCheckListener(new QSwitchView.OnSwitchStateChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchView.OnSwitchStateChangeListener
            public final void onSwitchStateChange(boolean z) {
                DrinkActivity.m578drinkClickListener$lambda7$lambda6(this.f$0, drinkIndex, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drinkClickListener$lambda-7$lambda-6, reason: not valid java name */
    public static final void m578drinkClickListener$lambda7$lambda6(DrinkActivity this$0, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.getDrinkArray()[i].setSwitch(z);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    public final void showTimeDialog(String title, int time, final int index) {
        Intrinsics.checkNotNullParameter(title, "title");
        BottomTimeDialog.Builder builder = new BottomTimeDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_time_selector);
        BottomTimeDialog bottomTimeDialogCreate = builder.create();
        bottomTimeDialogCreate.setListener(new BottomTimeDialog.TimeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity$$ExternalSyntheticLambda2
            @Override // com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.TimeListener
            public final void min(int i) {
                DrinkActivity.m582showTimeDialog$lambda8(this.f$0, index, i);
            }
        });
        bottomTimeDialogCreate.initData(this);
        bottomTimeDialogCreate.setTitle(title);
        bottomTimeDialogCreate.setCurrTime(time / 60, time % 60);
        bottomTimeDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTimeDialog$lambda-8, reason: not valid java name */
    public static final void m582showTimeDialog$lambda8(DrinkActivity this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.getDrinkArray()[i].setTime(i2);
        DrinkViewModel drinkViewModel = this$0.getDrinkViewModel();
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        drinkViewModel.saveDrinkSettingNotExecCmd(deviceAddressNoClear, deviceSetting2);
    }

    private final void modifyWeek() {
        int i;
        List<WeekRepeat> weekList = getDrinkViewModel().getWeekList();
        int size = weekList.size();
        byte b = 0;
        for (int i2 = 0; i2 < size; i2++) {
            switch (i2) {
                case 0:
                    if (weekList.get(0).isCheck()) {
                        i = b | 1;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (weekList.get(1).isCheck()) {
                        i = b | 2;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (weekList.get(2).isCheck()) {
                        i = b | 4;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (weekList.get(3).isCheck()) {
                        i = b | 8;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (weekList.get(4).isCheck()) {
                        i = b | 16;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (weekList.get(5).isCheck()) {
                        i = b | 32;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (weekList.get(6).isCheck()) {
                        i = b | 64;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
            }
        }
        DeviceSetting deviceSetting = this.deviceSetting;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setDrinkWeek(b);
    }

    private final void weekInit(int repeat) {
        DrinkViewModel drinkViewModel = getDrinkViewModel();
        drinkViewModel.getWeekList().clear();
        if ((repeat & 1) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_208), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_208), false));
        }
        if ((repeat & 2) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_202), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_202), false));
        }
        if ((repeat & 4) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_203), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_203), false));
        }
        if ((repeat & 8) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_204), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_204), false));
        }
        if ((repeat & 16) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_205), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_205), false));
        }
        if ((repeat & 32) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_206), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_206), false));
        }
        if ((repeat & 64) != 0) {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_207), true));
        } else {
            drinkViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_207), false));
        }
        ModeAdapter modeAdapter = this.adapter;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.notifyDataSetChanged();
    }
}
