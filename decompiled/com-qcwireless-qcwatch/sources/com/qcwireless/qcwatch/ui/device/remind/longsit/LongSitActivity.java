package com.qcwireless.qcwatch.ui.device.remind.longsit;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomTimeDialog;
import com.qcwireless.qcwatch.base.dialog.bean.WeekRepeat;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityLongSitBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.device.week.adapter.ModeAdapter;
import com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter;
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

/* compiled from: LongSitActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0014J\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cJ\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006 "}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/longsit/LongSitActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/week/adapter/ModeAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityLongSitBinding;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "longSitViewModel", "Lcom/qcwireless/qcwatch/ui/device/remind/longsit/LongSitViewModel;", "getLongSitViewModel", "()Lcom/qcwireless/qcwatch/ui/device/remind/longsit/LongSitViewModel;", "longSitViewModel$delegate", "Lkotlin/Lazy;", "modifyWeek", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "showTimeDialog", "title", "", "time", "", "type", "weekInit", "repeat", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LongSitActivity extends BaseActivity {
    private ModeAdapter adapter;
    private ActivityLongSitBinding binding;
    private DeviceSetting deviceSetting;

    /* renamed from: longSitViewModel$delegate, reason: from kotlin metadata */
    private final Lazy longSitViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public LongSitActivity() {
        final LongSitActivity longSitActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.longSitViewModel = LazyKt.lazy(new Function0<LongSitViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final LongSitViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(longSitActivity, Reflection.getOrCreateKotlinClass(LongSitViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LongSitViewModel getLongSitViewModel() {
        return (LongSitViewModel) this.longSitViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLongSitBinding activityLongSitBindingInflate = ActivityLongSitBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityLongSitBindingInflate, "inflate(layoutInflater)");
        this.binding = activityLongSitBindingInflate;
        if (activityLongSitBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLongSitBindingInflate = null;
        }
        ConstraintLayout root = activityLongSitBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        LongSitActivity longSitActivity = this;
        this.adapter = new ModeAdapter(longSitActivity, getLongSitViewModel().getWeekList());
        LongSitViewModel longSitViewModel = getLongSitViewModel();
        longSitViewModel.getLongSitSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        longSitViewModel.getUiStatue().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LongSitActivity.m584setupViews$lambda2$lambda1(this.f$0, (DeviceSetting) obj);
            }
        });
        ModeAdapter modeAdapter = this.adapter;
        ActivityLongSitBinding activityLongSitBinding = null;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.setSelectMode(WeekDayAdapter.SelectMode.MULTI_SELECT);
        final ActivityLongSitBinding activityLongSitBinding2 = this.binding;
        if (activityLongSitBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLongSitBinding2 = null;
        }
        activityLongSitBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_50));
        activityLongSitBinding2.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        ViewKt.visible(activityLongSitBinding2.titleBar.tvRightText);
        activityLongSitBinding2.weekDayRecycler.setLayoutManager(new LinearLayoutManager(longSitActivity));
        RecyclerView recyclerView = activityLongSitBinding2.weekDayRecycler;
        ModeAdapter modeAdapter2 = this.adapter;
        if (modeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter2 = null;
        }
        recyclerView.setAdapter(modeAdapter2);
        activityLongSitBinding2.weekDayRecycler.setNestedScrollingEnabled(false);
        activityLongSitBinding2.qcLongSwitch.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LongSitActivity.m585setupViews$lambda4$lambda3(activityLongSitBinding2, this, compoundButton, z);
            }
        });
        activityLongSitBinding2.qcLongSitStart.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$setupViews$2$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                LongSitActivity longSitActivity2 = this.this$0;
                String string = longSitActivity2.getString(R.string.qc_text_39);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_39)");
                DeviceSetting deviceSetting = this.this$0.deviceSetting;
                if (deviceSetting == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting = null;
                }
                longSitActivity2.showTimeDialog(string, deviceSetting.getLongSitStart(), 1);
            }
        });
        activityLongSitBinding2.qcLongSitEnd.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$setupViews$2$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                LongSitActivity longSitActivity2 = this.this$0;
                String string = longSitActivity2.getString(R.string.qc_text_40);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_40)");
                DeviceSetting deviceSetting = this.this$0.deviceSetting;
                if (deviceSetting == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting = null;
                }
                longSitActivity2.showTimeDialog(string, deviceSetting.getLongSitEnd(), 2);
            }
        });
        activityLongSitBinding2.longSitRadioGroup.setCheckedChangeListener(new ConstraintRadioGroup.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$setupViews$2$4
            @Override // com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(ConstraintRadioGroup group, CompoundButton checkedButton) {
                Intrinsics.checkNotNullParameter(group, "group");
                Intrinsics.checkNotNullParameter(checkedButton, "checkedButton");
                int id = checkedButton.getId();
                DeviceSetting deviceSetting = null;
                switch (id) {
                    case R.id.rb_text1 /* 2131297204 */:
                        DeviceSetting deviceSetting2 = this.this$0.deviceSetting;
                        if (deviceSetting2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting2;
                        }
                        deviceSetting.setLongSitDuring(30);
                        break;
                    case R.id.rb_text2 /* 2131297205 */:
                        DeviceSetting deviceSetting3 = this.this$0.deviceSetting;
                        if (deviceSetting3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting3;
                        }
                        deviceSetting.setLongSitDuring(60);
                        break;
                    case R.id.rb_text3 /* 2131297206 */:
                        DeviceSetting deviceSetting4 = this.this$0.deviceSetting;
                        if (deviceSetting4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        } else {
                            deviceSetting = deviceSetting4;
                        }
                        deviceSetting.setLongSitDuring(90);
                        break;
                }
            }
        });
        ModeAdapter modeAdapter3 = this.adapter;
        if (modeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter3 = null;
        }
        modeAdapter3.setOnItemMultiSelectListener(new WeekDayAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter.OnItemMultiSelectListener
            public final void onSelected(WeekDayAdapter.Operation operation, int i, boolean z) {
                LongSitActivity.m586setupViews$lambda6(this.f$0, operation, i, z);
            }
        });
        View[] viewArr = new View[1];
        ActivityLongSitBinding activityLongSitBinding3 = this.binding;
        if (activityLongSitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLongSitBinding = activityLongSitBinding3;
        }
        viewArr[0] = activityLongSitBinding.titleBar.tvRightText;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.setupViews.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:24:0x006f, code lost:
            
                if (r6.getLongSitWeek() == 128) goto L25;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2(android.view.View r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "$this$setOnClickListener"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r0 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.databinding.ActivityLongSitBinding r0 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getBinding$p(r0)
                    r1 = 0
                    if (r0 != 0) goto L14
                    java.lang.String r0 = "binding"
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r0 = r1
                L14:
                    com.qcwireless.qcwatch.databinding.LayoutTitleBarBinding r0 = r0.titleBar
                    android.widget.TextView r0 = r0.tvRightText
                    boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
                    if (r6 == 0) goto Le0
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r6)
                    java.lang.String r0 = "deviceSetting"
                    if (r6 != 0) goto L2c
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r6 = r1
                L2c:
                    boolean r6 = r6.getLongSitSwitch()
                    r2 = 1
                    r3 = 0
                    if (r6 == 0) goto L85
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r6)
                    if (r6 != 0) goto L40
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r6 = r1
                L40:
                    int r6 = r6.getLongSitWeek()
                    java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                    com.elvishew.xlog.XLog.i(r6)
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r6)
                    if (r6 != 0) goto L57
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r6 = r1
                L57:
                    int r6 = r6.getLongSitWeek()
                    if (r6 == 0) goto L71
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r6)
                    if (r6 != 0) goto L69
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r6 = r1
                L69:
                    int r6 = r6.getLongSitWeek()
                    r4 = 128(0x80, float:1.794E-43)
                    if (r6 != r4) goto L85
                L71:
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    r0 = 2131821135(0x7f11024f, float:1.9275005E38)
                    java.lang.String r6 = r6.getString(r0)
                    java.lang.String r0 = "getString(R.string.qc_text_282)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
                    java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                    com.qcwireless.qcwatch.base.view.GlobalKt.showToast$default(r6, r3, r2, r1)
                    return
                L85:
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r6)
                    if (r6 != 0) goto L91
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r6 = r1
                L91:
                    int r6 = r6.getLongSitStart()
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r4 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r4 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r4)
                    if (r4 != 0) goto La1
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r4 = r1
                La1:
                    int r4 = r4.getLongSitEnd()
                    if (r6 < r4) goto Lbb
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    r0 = 2131821147(0x7f11025b, float:1.9275029E38)
                    java.lang.String r6 = r6.getString(r0)
                    java.lang.String r0 = "getString(R.string.qc_text_293)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
                    java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                    com.qcwireless.qcwatch.base.view.GlobalKt.showToast$default(r6, r3, r2, r1)
                    return
                Lbb:
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getLongSitViewModel(r6)
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r2 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r2 = r2.getInstance()
                    java.lang.String r2 = r2.getDeviceAddressNoClear()
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r3 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting r3 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.access$getDeviceSetting$p(r3)
                    if (r3 != 0) goto Ld7
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    goto Ld8
                Ld7:
                    r1 = r3
                Ld8:
                    r6.saveLongSitSetting(r2, r1)
                    com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity r6 = com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.this
                    r6.finish()
                Le0:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity.AnonymousClass4.invoke2(android.view.View):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m584setupViews$lambda2$lambda1(LongSitActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        ActivityLongSitBinding activityLongSitBinding = this$0.binding;
        if (activityLongSitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLongSitBinding = null;
        }
        if (it.getLongSitSwitch()) {
            ViewKt.visible(activityLongSitBinding.longSitGroup);
        } else {
            ViewKt.gone(activityLongSitBinding.longSitGroup);
        }
        activityLongSitBinding.qcLongSwitch.setChecked(it.getLongSitSwitch());
        activityLongSitBinding.qcLongSitStart.setRightText(DateUtil.dayMinToStr(it.getLongSitStart()));
        activityLongSitBinding.qcLongSitEnd.setRightText(DateUtil.dayMinToStr(it.getLongSitEnd()));
        int longSitDuring = it.getLongSitDuring();
        if (longSitDuring == 30) {
            activityLongSitBinding.rbText1.setChecked(true);
        } else if (longSitDuring == 60) {
            activityLongSitBinding.rbText2.setChecked(true);
        } else if (longSitDuring == 90) {
            activityLongSitBinding.rbText3.setChecked(true);
        }
        int longSitWeek = it.getLongSitWeek();
        XLog.i(Integer.valueOf(longSitWeek));
        this$0.weekInit(longSitWeek);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-3, reason: not valid java name */
    public static final void m585setupViews$lambda4$lambda3(ActivityLongSitBinding this_run, LongSitActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            ViewKt.visible(this_run.longSitGroup);
        } else {
            ViewKt.gone(this_run.longSitGroup);
        }
        DeviceSetting deviceSetting = this$0.deviceSetting;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setLongSitSwitch(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m586setupViews$lambda6(LongSitActivity this$0, WeekDayAdapter.Operation operation, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getLongSitViewModel().getWeekList().get(i).setCheck(!this$0.getLongSitViewModel().getWeekList().get(i).isCheck());
        ModeAdapter modeAdapter = this$0.adapter;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.notifyDataSetChanged();
        this$0.modifyWeek();
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

    private final void modifyWeek() {
        int i;
        List<WeekRepeat> weekList = getLongSitViewModel().getWeekList();
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
        deviceSetting.setLongSitWeek(b);
        XLog.i(Integer.valueOf(b));
    }

    private final void weekInit(int repeat) {
        LongSitViewModel longSitViewModel = getLongSitViewModel();
        longSitViewModel.getWeekList().clear();
        if ((repeat & 1) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_208), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_208), false));
        }
        if ((repeat & 2) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_202), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_202), false));
        }
        if ((repeat & 4) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_203), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_203), false));
        }
        if ((repeat & 8) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_204), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_204), false));
        }
        if ((repeat & 16) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_205), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_205), false));
        }
        if ((repeat & 32) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_206), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_206), false));
        }
        if ((repeat & 64) != 0) {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_207), true));
        } else {
            longSitViewModel.getWeekList().add(new WeekRepeat(GlobalKt.getString(R.string.qc_text_207), false));
        }
        ModeAdapter modeAdapter = this.adapter;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.notifyDataSetChanged();
    }

    public final void showTimeDialog(String title, int time, final int type) {
        Intrinsics.checkNotNullParameter(title, "title");
        BottomTimeDialog.Builder builder = new BottomTimeDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_time_selector);
        BottomTimeDialog bottomTimeDialogCreate = builder.create();
        bottomTimeDialogCreate.setListener(new BottomTimeDialog.TimeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity$$ExternalSyntheticLambda2
            @Override // com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.TimeListener
            public final void min(int i) {
                LongSitActivity.m587showTimeDialog$lambda8(type, this, i);
            }
        });
        bottomTimeDialogCreate.initData(this);
        bottomTimeDialogCreate.setTitle(title);
        bottomTimeDialogCreate.setCurrTime(time / 60, time % 60);
        bottomTimeDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTimeDialog$lambda-8, reason: not valid java name */
    public static final void m587showTimeDialog$lambda8(int i, LongSitActivity this$0, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (i == 1) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setLongSitStart(i2);
        } else {
            DeviceSetting deviceSetting3 = this$0.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting3 = null;
            }
            deviceSetting3.setLongSitEnd(i2);
        }
        LongSitViewModel longSitViewModel = this$0.getLongSitViewModel();
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting4;
        }
        longSitViewModel.saveLongSitSettingNotExecCmd(deviceAddressNoClear, deviceSetting);
    }
}
