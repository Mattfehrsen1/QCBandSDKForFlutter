package com.qcwireless.qcwatch.ui.device.remind;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.king.zxing.util.CodeUtils;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.entity.AlarmEntity;
import com.oudmon.ble.base.communication.req.SetAlarmReq;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomRemindDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityAiReminderBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.AlarmBean;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.recycleview.SwipeItemLayout;
import com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter;
import com.qcwireless.qcwatch.ui.device.remind.drink.DrinkActivity;
import com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: AiReminderActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0014J\b\u0010\u0017\u001a\u00020\u0010H\u0014J@\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/AiReminderActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/remind/adapter/AlarmListAdapter;", "aiReminderViewModel", "Lcom/qcwireless/qcwatch/ui/device/remind/AiReminderViewModel;", "getAiReminderViewModel", "()Lcom/qcwireless/qcwatch/ui/device/remind/AiReminderViewModel;", "aiReminderViewModel$delegate", "Lkotlin/Lazy;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityAiReminderBinding;", "settingDevice", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "showTimeDialog", "position", "", "title", "", "h", "m", "week", "alarmIndex", "addFlag", "", "MyAdapterListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AiReminderActivity extends BaseActivity {
    private AlarmListAdapter adapter;

    /* renamed from: aiReminderViewModel$delegate, reason: from kotlin metadata */
    private final Lazy aiReminderViewModel;
    private ActivityAiReminderBinding binding;
    private DeviceSetting settingDevice;

    /* JADX WARN: Multi-variable type inference failed */
    public AiReminderActivity() {
        final AiReminderActivity aiReminderActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.aiReminderViewModel = LazyKt.lazy(new Function0<AiReminderViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AiReminderViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(aiReminderActivity, Reflection.getOrCreateKotlinClass(AiReminderViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AiReminderViewModel getAiReminderViewModel() {
        return (AiReminderViewModel) this.aiReminderViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAiReminderBinding activityAiReminderBindingInflate = ActivityAiReminderBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiReminderBindingInflate, "inflate(layoutInflater)");
        this.binding = activityAiReminderBindingInflate;
        if (activityAiReminderBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiReminderBindingInflate = null;
        }
        ConstraintLayout root = activityAiReminderBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialogTimeout(5000);
        ActivityAiReminderBinding activityAiReminderBinding = null;
        if (UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
            ActivityAiReminderBinding activityAiReminderBinding2 = this.binding;
            if (activityAiReminderBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAiReminderBinding2 = null;
            }
            ViewKt.gone(activityAiReminderBinding2.qcDrink);
        } else {
            ActivityAiReminderBinding activityAiReminderBinding3 = this.binding;
            if (activityAiReminderBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAiReminderBinding3 = null;
            }
            ViewKt.visible(activityAiReminderBinding3.qcDrink);
        }
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            ActivityAiReminderBinding activityAiReminderBinding4 = this.binding;
            if (activityAiReminderBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAiReminderBinding4 = null;
            }
            ViewKt.visible(activityAiReminderBinding4.addAlarm);
        } else {
            ActivityAiReminderBinding activityAiReminderBinding5 = this.binding;
            if (activityAiReminderBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAiReminderBinding5 = null;
            }
            ViewKt.gone(activityAiReminderBinding5.addAlarm);
        }
        AiReminderActivity aiReminderActivity = this;
        this.adapter = new AlarmListAdapter(aiReminderActivity, getAiReminderViewModel().getAlarmList());
        ActivityAiReminderBinding activityAiReminderBinding6 = this.binding;
        if (activityAiReminderBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiReminderBinding6 = null;
        }
        activityAiReminderBinding6.titleBar.tvTitle.setText(getString(R.string.qc_text_12));
        activityAiReminderBinding6.qcLongSit.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                AiReminderActivity aiReminderActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(aiReminderActivity2, (Class<?>) LongSitActivity.class);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                aiReminderActivity2.startActivity(intent);
            }
        });
        activityAiReminderBinding6.qcDrink.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity$setupViews$1$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                AiReminderActivity aiReminderActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(aiReminderActivity2, (Class<?>) DrinkActivity.class);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                aiReminderActivity2.startActivity(intent);
            }
        });
        activityAiReminderBinding6.rcvAlarm.setLayoutManager(new LinearLayoutManager(aiReminderActivity));
        RecyclerView recyclerView = activityAiReminderBinding6.rcvAlarm;
        AlarmListAdapter alarmListAdapter = this.adapter;
        if (alarmListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            alarmListAdapter = null;
        }
        recyclerView.setAdapter(alarmListAdapter);
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            activityAiReminderBinding6.rcvAlarm.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(aiReminderActivity));
        }
        AlarmListAdapter alarmListAdapter2 = this.adapter;
        if (alarmListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            alarmListAdapter2 = null;
        }
        alarmListAdapter2.initListener(new MyAdapterListener());
        getAiReminderViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiReminderActivity.m568setupViews$lambda2(this.f$0, (DeviceSetting) obj);
            }
        });
        View[] viewArr = new View[1];
        ActivityAiReminderBinding activityAiReminderBinding7 = this.binding;
        if (activityAiReminderBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAiReminderBinding = activityAiReminderBinding7;
        }
        viewArr[0] = activityAiReminderBinding.addAlarm;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity.setupViews.3
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
                ActivityAiReminderBinding activityAiReminderBinding8 = AiReminderActivity.this.binding;
                if (activityAiReminderBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAiReminderBinding8 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityAiReminderBinding8.addAlarm)) {
                    if (AiReminderActivity.this.getAiReminderViewModel().getAlarmList().size() >= AiReminderActivity.this.getAiReminderViewModel().getMax()) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String string = AiReminderActivity.this.getString(R.string.qc_text_81);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_81)");
                        String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(AiReminderActivity.this.getAiReminderViewModel().getMax())}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                        GlobalKt.showToast$default(str, 0, 1, null);
                        return;
                    }
                    DateUtil dateUtil = new DateUtil();
                    AiReminderActivity aiReminderActivity2 = AiReminderActivity.this;
                    aiReminderActivity2.showTimeDialog(aiReminderActivity2.getAiReminderViewModel().getAlarmList().size(), "", dateUtil.getHour(), dateUtil.getMinute(), 128, AiReminderActivity.this.getAiReminderViewModel().getAlarmList().size(), true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m568setupViews$lambda2(final AiReminderActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThreadUtils.postDelay(new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity$setupViews$2$1
            @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
            protected void task() {
                this.this$0.dismissLoadingDialog();
            }
        }, 1000L);
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.settingDevice = it;
        this$0.getAiReminderViewModel().getAlarmList().clear();
        for (AlarmBean alarmBean : it.getAlarmList()) {
            if (alarmBean.getDefault()) {
                this$0.getAiReminderViewModel().getAlarmList().add(alarmBean);
            }
        }
        AlarmListAdapter alarmListAdapter = this$0.adapter;
        ActivityAiReminderBinding activityAiReminderBinding = null;
        if (alarmListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            alarmListAdapter = null;
        }
        alarmListAdapter.notifyDataSetChanged();
        ActivityAiReminderBinding activityAiReminderBinding2 = this$0.binding;
        if (activityAiReminderBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAiReminderBinding = activityAiReminderBinding2;
        }
        if (it.getLongSitSwitch()) {
            activityAiReminderBinding.qcLongSit.setRightText(this$0.getString(R.string.qc_text_22));
        } else {
            activityAiReminderBinding.qcLongSit.setRightText(this$0.getString(R.string.qc_text_21));
        }
        if (it.getDrinkSwitch()) {
            activityAiReminderBinding.qcDrink.setRightText(this$0.getString(R.string.qc_text_22));
        } else {
            activityAiReminderBinding.qcDrink.setRightText(this$0.getString(R.string.qc_text_21));
        }
    }

    /* compiled from: AiReminderActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/AiReminderActivity$MyAdapterListener;", "Lcom/qcwireless/qcwatch/ui/device/remind/adapter/AlarmListAdapter$InitListener;", "(Lcom/qcwireless/qcwatch/ui/device/remind/AiReminderActivity;)V", "onCheckChange", "", "position", "", "checked", "", "onDeleteItem", "onItemClickListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class MyAdapterListener implements AlarmListAdapter.InitListener {
        public MyAdapterListener() {
        }

        @Override // com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter.InitListener
        public void onItemClickListener(int position) {
            AlarmBean alarmBean = AiReminderActivity.this.getAiReminderViewModel().getAlarmList().get(position);
            XLog.i(alarmBean);
            AiReminderActivity.this.showTimeDialog(alarmBean.getIndex(), alarmBean.getTitle(), alarmBean.getTime() / 60, alarmBean.getTime() % 60, alarmBean.getWeek(), alarmBean.getIndex(), false);
        }

        @Override // com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter.InitListener
        public void onCheckChange(int position, boolean checked) throws InterruptedException {
            AlarmBean alarmBean = AiReminderActivity.this.getAiReminderViewModel().getAlarmList().get(position);
            DeviceSetting deviceSetting = null;
            if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                AiReminderActivity.this.getAiReminderViewModel().getAlarmList().get(position).setSwitch(checked);
                DeviceSetting deviceSetting2 = AiReminderActivity.this.settingDevice;
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                    deviceSetting2 = null;
                }
                deviceSetting2.getAlarmList().set(position, alarmBean);
                AiReminderViewModel aiReminderViewModel = AiReminderActivity.this.getAiReminderViewModel();
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                DeviceSetting deviceSetting3 = AiReminderActivity.this.settingDevice;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                    deviceSetting3 = null;
                }
                aiReminderViewModel.saveReminder(deviceAddressNoClear, deviceSetting3);
                AiReminderViewModel aiReminderViewModel2 = AiReminderActivity.this.getAiReminderViewModel();
                DeviceSetting deviceSetting4 = AiReminderActivity.this.settingDevice;
                if (deviceSetting4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                } else {
                    deviceSetting = deviceSetting4;
                }
                aiReminderViewModel2.execAlarm(deviceSetting);
                return;
            }
            AiReminderActivity.this.getAiReminderViewModel().getAlarmList().get(position).setSwitch(checked);
            DeviceSetting deviceSetting5 = AiReminderActivity.this.settingDevice;
            if (deviceSetting5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting5 = null;
            }
            deviceSetting5.getAlarmList().set(position, alarmBean);
            AiReminderViewModel aiReminderViewModel3 = AiReminderActivity.this.getAiReminderViewModel();
            String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            DeviceSetting deviceSetting6 = AiReminderActivity.this.settingDevice;
            if (deviceSetting6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting6 = null;
            }
            aiReminderViewModel3.saveReminder(deviceAddressNoClear2, deviceSetting6);
            CommandHandle.getInstance().executeReqCmd(new SetAlarmReq(new AlarmEntity(position, checked, alarmBean.getTime() / 60, alarmBean.getTime() % 60, (byte) alarmBean.getWeek())), null);
        }

        @Override // com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter.InitListener
        public void onDeleteItem(int position) throws InterruptedException {
            AlarmListAdapter alarmListAdapter = null;
            if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                AlarmBean alarmBean = AiReminderActivity.this.getAiReminderViewModel().getAlarmList().get(position);
                AiReminderActivity.this.getAiReminderViewModel().getAlarmList().remove(position);
                DeviceSetting deviceSetting = AiReminderActivity.this.settingDevice;
                if (deviceSetting == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                    deviceSetting = null;
                }
                deviceSetting.getAlarmList().remove(position);
                XLog.i(alarmBean);
                AiReminderViewModel aiReminderViewModel = AiReminderActivity.this.getAiReminderViewModel();
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                DeviceSetting deviceSetting2 = AiReminderActivity.this.settingDevice;
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                    deviceSetting2 = null;
                }
                aiReminderViewModel.saveReminder(deviceAddressNoClear, deviceSetting2);
                AiReminderViewModel aiReminderViewModel2 = AiReminderActivity.this.getAiReminderViewModel();
                DeviceSetting deviceSetting3 = AiReminderActivity.this.settingDevice;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                    deviceSetting3 = null;
                }
                aiReminderViewModel2.execAlarm(deviceSetting3);
            } else {
                String string = AiReminderActivity.this.getString(R.string.qwatchpro_text_15);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qwatchpro_text_15)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
            AlarmListAdapter alarmListAdapter2 = AiReminderActivity.this.adapter;
            if (alarmListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                alarmListAdapter = alarmListAdapter2;
            }
            alarmListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getAiReminderViewModel().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTimeDialog(final int position, String title, int h, int m, int week, final int alarmIndex, final boolean addFlag) {
        BottomRemindDialog.Builder builder = new BottomRemindDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_remind_selector);
        BottomRemindDialog bottomRemindDialogCreate = builder.create();
        bottomRemindDialogCreate.setListener(new BottomRemindDialog.RemindDialogListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.base.dialog.BottomRemindDialog.RemindDialogListener
            public final void time(int i, int i2, String str, int i3) {
                AiReminderActivity.m569showTimeDialog$lambda3(addFlag, alarmIndex, this, position, i, i2, str, i3);
            }
        });
        bottomRemindDialogCreate.initData(this, week);
        bottomRemindDialogCreate.setTitle(title);
        bottomRemindDialogCreate.setCurrTime(h, m);
        bottomRemindDialogCreate.show();
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            return;
        }
        bottomRemindDialogCreate.setTitleGone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTimeDialog$lambda-3, reason: not valid java name */
    public static final void m569showTimeDialog$lambda3(boolean z, int i, AiReminderActivity this$0, int i2, int i3, int i4, String title, int i5) {
        boolean z2;
        AlarmListAdapter alarmListAdapter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            try {
                XLog.i(Integer.valueOf(i));
                Iterator<AlarmBean> it = this$0.getAiReminderViewModel().getAlarmList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    AlarmBean next = it.next();
                    if (next.getWeek() == i5 && next.getTime() == (i3 * 60) + i4) {
                        z2 = true;
                        break;
                    }
                }
                if (z2) {
                    String string = this$0.getString(R.string.qc_text_362);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_362)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    return;
                } else {
                    this$0.getAiReminderViewModel().getAlarmList().add(new AlarmBean(i, CodeUtils.DEFAULT_REQ_WIDTH, "", true, 0, false));
                    DeviceSetting deviceSetting = this$0.settingDevice;
                    if (deviceSetting == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                        deviceSetting = null;
                    }
                    deviceSetting.getAlarmList().add(new AlarmBean(i, CodeUtils.DEFAULT_REQ_WIDTH, "", true, 0, false));
                    XLog.i(Integer.valueOf(this$0.getAiReminderViewModel().getAlarmList().size()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            if (i > this$0.getAiReminderViewModel().getAlarmList().size()) {
                return;
            }
            AlarmBean alarmBean = this$0.getAiReminderViewModel().getAlarmList().get(i);
            Intrinsics.checkNotNullExpressionValue(title, "title");
            alarmBean.setTitle(title);
            this$0.getAiReminderViewModel().getAlarmList().get(i).setWeek(i5);
            this$0.getAiReminderViewModel().getAlarmList().get(i).setTime((i3 * 60) + i4);
            this$0.getAiReminderViewModel().getAlarmList().get(i).setDefault(true);
            this$0.getAiReminderViewModel().getAlarmList().get(i).setIndex(i);
            DeviceSetting deviceSetting2 = this$0.settingDevice;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting2 = null;
            }
            deviceSetting2.getAlarmList().set(i, this$0.getAiReminderViewModel().getAlarmList().get(i));
            XLog.i(this$0.getAiReminderViewModel().getAlarmList());
            AiReminderViewModel aiReminderViewModel = this$0.getAiReminderViewModel();
            String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            DeviceSetting deviceSetting3 = this$0.settingDevice;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting3 = null;
            }
            aiReminderViewModel.saveReminder(deviceAddressNoClear, deviceSetting3);
            AiReminderViewModel aiReminderViewModel2 = this$0.getAiReminderViewModel();
            DeviceSetting deviceSetting4 = this$0.settingDevice;
            if (deviceSetting4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting4 = null;
            }
            aiReminderViewModel2.execAlarm(deviceSetting4);
        } else {
            XLog.i(Integer.valueOf(i));
            int i6 = this$0.getAiReminderViewModel().getAlarmList().get(i2).getSwitch() ? 1 : 0;
            AlarmBean alarmBean2 = this$0.getAiReminderViewModel().getAlarmList().get(i2);
            Intrinsics.checkNotNullExpressionValue(title, "title");
            alarmBean2.setTitle(title);
            this$0.getAiReminderViewModel().getAlarmList().get(i2).setWeek(i5);
            this$0.getAiReminderViewModel().getAlarmList().get(i2).setTime((i3 * 60) + i4);
            this$0.getAiReminderViewModel().getAlarmList().get(i2).setDefault(true);
            this$0.getAiReminderViewModel().getAlarmList().get(i2).setIndex(i2);
            DeviceSetting deviceSetting5 = this$0.settingDevice;
            if (deviceSetting5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting5 = null;
            }
            deviceSetting5.getAlarmList().set(i2, this$0.getAiReminderViewModel().getAlarmList().get(i2));
            XLog.i(this$0.getAiReminderViewModel().getAlarmList());
            SetAlarmReq setAlarmReq = new SetAlarmReq(new AlarmEntity(i2, i6, i3, i4, (byte) i5));
            AiReminderViewModel aiReminderViewModel3 = this$0.getAiReminderViewModel();
            String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            DeviceSetting deviceSetting6 = this$0.settingDevice;
            if (deviceSetting6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingDevice");
                deviceSetting6 = null;
            }
            aiReminderViewModel3.saveReminder(deviceAddressNoClear2, deviceSetting6);
            CommandHandle.getInstance().executeReqCmdNoCallback(setAlarmReq);
        }
        AlarmListAdapter alarmListAdapter2 = this$0.adapter;
        if (alarmListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            alarmListAdapter = null;
        } else {
            alarmListAdapter = alarmListAdapter2;
        }
        alarmListAdapter.notifyDataSetChanged();
    }
}
