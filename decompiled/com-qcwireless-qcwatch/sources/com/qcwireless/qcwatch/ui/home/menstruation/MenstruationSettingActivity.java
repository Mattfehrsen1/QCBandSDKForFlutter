package com.qcwireless.qcwatch.ui.home.menstruation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.MenstruationReq;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomTimeDialog;
import com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog;
import com.qcwireless.qcwatch.base.dialog.BottomYMDDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMenstruationSettingBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel;
import com.qcwireless.qcwatch.ui.home.menstruation.bean.MenstruationBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MenstruationSettingActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\b\u0010\u0014\u001a\u00020\u000eH\u0014J\b\u0010\u0015\u001a\u00020\u000eH\u0014J\u0006\u0010\u0016\u001a\u00020\u000eJ\u0006\u0010\u0017\u001a\u00020\u000eJ\u0006\u0010\u0018\u001a\u00020\u000eJ\u0006\u0010\u0019\u001a\u00020\u000eJ\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010\u001b\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationSettingActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMenstruationSettingBinding;", "menstruationSettings", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "showAlert1Dialog", "showAlert2Dialog", "showCycleDialog", "showDateDialog", "showDuringDialog", "showTimeDialog", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MenstruationSettingActivity extends BaseActivity {
    private ActivityMenstruationSettingBinding binding;
    private MenstruationBean menstruationSettings;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MenstruationSettingActivity() {
        final MenstruationSettingActivity menstruationSettingActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MenstruationViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MenstruationViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(menstruationSettingActivity, Reflection.getOrCreateKotlinClass(MenstruationViewModel.class), qualifier, objArr);
            }
        });
    }

    private final MenstruationViewModel getViewModel() {
        return (MenstruationViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMenstruationSettingBinding activityMenstruationSettingBindingInflate = ActivityMenstruationSettingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMenstruationSettingBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMenstruationSettingBindingInflate;
        if (activityMenstruationSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationSettingBindingInflate = null;
        }
        ConstraintLayout root = activityMenstruationSettingBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getViewModel().initData();
        final ActivityMenstruationSettingBinding activityMenstruationSettingBinding = this.binding;
        if (activityMenstruationSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationSettingBinding = null;
        }
        activityMenstruationSettingBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_191));
        ViewKt.visible(activityMenstruationSettingBinding.titleBar.tvRightText);
        activityMenstruationSettingBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        activityMenstruationSettingBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenstruationSettingActivity.m788setupViews$lambda3$lambda0(this.f$0, view);
            }
        });
        activityMenstruationSettingBinding.qcMsSwitch.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MenstruationSettingActivity.m789setupViews$lambda3$lambda1(activityMenstruationSettingBinding, this, compoundButton, z);
            }
        });
        activityMenstruationSettingBinding.qcMsDuring.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$setupViews$1$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showDuringDialog();
            }
        });
        activityMenstruationSettingBinding.qcMsCycle.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$setupViews$1$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showCycleDialog();
            }
        });
        activityMenstruationSettingBinding.qcMsLast.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$setupViews$1$5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showDateDialog();
            }
        });
        activityMenstruationSettingBinding.qcMsAlarmSwitch.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MenstruationSettingActivity.m790setupViews$lambda3$lambda2(activityMenstruationSettingBinding, this, compoundButton, z);
            }
        });
        activityMenstruationSettingBinding.qcMsAlarm1.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$setupViews$1$7
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showAlert1Dialog();
            }
        });
        activityMenstruationSettingBinding.qcMsAlarm2.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$setupViews$1$8
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showAlert2Dialog();
            }
        });
        activityMenstruationSettingBinding.qcMsAlarm3.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$setupViews$1$9
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showTimeDialog();
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenstruationSettingActivity.m791setupViews$lambda5(this.f$0, (MenstruationViewModel.MenstruationSettingUI) obj);
            }
        });
        getViewModel().queryMenstruationSetting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-0, reason: not valid java name */
    public static final void m788setupViews$lambda3$lambda0(MenstruationSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenstruationBean menstruationBean = null;
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            this$0.finish();
            return;
        }
        if (this$0.menstruationSettings != null) {
            MenstruationViewModel viewModel = this$0.getViewModel();
            MenstruationBean menstruationBean2 = this$0.menstruationSettings;
            if (menstruationBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean2 = null;
            }
            viewModel.saveMenstruationSetting(menstruationBean2);
            MenstruationBean menstruationBean3 = this$0.menstruationSettings;
            if (menstruationBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean3 = null;
            }
            DateUtil dateUtil = new DateUtil(menstruationBean3.getLastTime(), true);
            DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
            MenstruationBean menstruationBean4 = this$0.menstruationSettings;
            if (menstruationBean4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean4 = null;
            }
            dateUtil2.addDay(-(menstruationBean4.getDuring() - 1));
            int iDaysBetweenMe = new DateUtil().daysBetweenMe(dateUtil2);
            int iDaysBetweenMe2 = new DateUtil().daysBetweenMe(dateUtil);
            CommandHandle commandHandle = CommandHandle.getInstance();
            MenstruationBean menstruationBean5 = this$0.menstruationSettings;
            if (menstruationBean5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean5 = null;
            }
            boolean menstruationSwitch = menstruationBean5.getMenstruationSwitch();
            MenstruationBean menstruationBean6 = this$0.menstruationSettings;
            if (menstruationBean6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean6 = null;
            }
            int during = menstruationBean6.getDuring();
            MenstruationBean menstruationBean7 = this$0.menstruationSettings;
            if (menstruationBean7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean7 = null;
            }
            int cycle = menstruationBean7.getCycle();
            MenstruationBean menstruationBean8 = this$0.menstruationSettings;
            if (menstruationBean8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean8 = null;
            }
            boolean menstruationAlarm = menstruationBean8.getMenstruationAlarm();
            MenstruationBean menstruationBean9 = this$0.menstruationSettings;
            if (menstruationBean9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean9 = null;
            }
            int alarm1 = menstruationBean9.getAlarm1();
            MenstruationBean menstruationBean10 = this$0.menstruationSettings;
            if (menstruationBean10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean10 = null;
            }
            int alarm2 = menstruationBean10.getAlarm2();
            MenstruationBean menstruationBean11 = this$0.menstruationSettings;
            if (menstruationBean11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
                menstruationBean11 = null;
            }
            int alarmMin = menstruationBean11.getAlarmMin() / 60;
            MenstruationBean menstruationBean12 = this$0.menstruationSettings;
            if (menstruationBean12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            } else {
                menstruationBean = menstruationBean12;
            }
            commandHandle.executeReqCmdNoCallback(new MenstruationReq(menstruationSwitch, during, cycle, iDaysBetweenMe, iDaysBetweenMe2, menstruationAlarm, alarm1, alarm2, alarmMin, menstruationBean.getAlarmMin() % 60));
            this$0.finish();
            if (UserConfig.INSTANCE.getInstance().getMenstruationSetting()) {
                return;
            }
            UserConfig.INSTANCE.getInstance().setMenstruationSetting(true);
            UserConfig.INSTANCE.getInstance().save();
            MenstruationSettingActivity menstruationSettingActivity = this$0;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(menstruationSettingActivity, (Class<?>) MenstruationActivity.class);
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
            menstruationSettingActivity.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-1, reason: not valid java name */
    public static final void m789setupViews$lambda3$lambda1(ActivityMenstruationSettingBinding this_run, MenstruationSettingActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            ViewKt.visible(this_run.menstruationGroup);
        } else {
            ViewKt.gone(this_run.menstruationGroup);
        }
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setMenstruationSwitch(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m790setupViews$lambda3$lambda2(ActivityMenstruationSettingBinding this_run, MenstruationSettingActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            ViewKt.visible(this_run.alarmGroup);
        } else {
            ViewKt.gone(this_run.alarmGroup);
        }
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setMenstruationAlarm(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m791setupViews$lambda5(MenstruationSettingActivity this$0, MenstruationViewModel.MenstruationSettingUI menstruationSettingUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (menstruationSettingUI != null) {
            this$0.menstruationSettings = menstruationSettingUI.getEntity();
            ActivityMenstruationSettingBinding activityMenstruationSettingBinding = this$0.binding;
            if (activityMenstruationSettingBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMenstruationSettingBinding = null;
            }
            boolean menstruationSwitch = menstruationSettingUI.getEntity().getMenstruationSwitch();
            activityMenstruationSettingBinding.qcMsSwitch.setChecked(menstruationSwitch);
            activityMenstruationSettingBinding.qcMsDuring.setRightText(menstruationSettingUI.getEntity().getDuring() + this$0.getString(R.string.qc_text_228));
            activityMenstruationSettingBinding.qcMsCycle.setRightText(menstruationSettingUI.getEntity().getCycle() + this$0.getString(R.string.qc_text_228));
            activityMenstruationSettingBinding.qcMsLast.setRightText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(menstruationSettingUI.getEntity().getLastTime(), true)));
            activityMenstruationSettingBinding.qcMsAlarmSwitch.setChecked(menstruationSettingUI.getEntity().getMenstruationAlarm());
            QSettingItem qSettingItem = activityMenstruationSettingBinding.qcMsAlarm1;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = this$0.getString(R.string.qc_text_246);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_246)");
            String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(menstruationSettingUI.getEntity().getAlarm1())}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            qSettingItem.setRightText(str);
            QSettingItem qSettingItem2 = activityMenstruationSettingBinding.qcMsAlarm2;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String string2 = this$0.getString(R.string.qc_text_246);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_246)");
            String str2 = String.format(string2, Arrays.copyOf(new Object[]{String.valueOf(menstruationSettingUI.getEntity().getAlarm2())}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            qSettingItem2.setRightText(str2);
            activityMenstruationSettingBinding.qcMsAlarm3.setRightText(DateUtil.dayMinToStr(menstruationSettingUI.getEntity().getAlarmMin()));
            if (menstruationSwitch) {
                ViewKt.visible(activityMenstruationSettingBinding.menstruationGroup);
                return;
            } else {
                ViewKt.gone(activityMenstruationSettingBinding.menstruationGroup);
                return;
            }
        }
        this$0.getViewModel().queryMenstruationSetting();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
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

    public final void showDuringDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, getViewModel().getDuringList());
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_195));
        bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_228));
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                MenstruationSettingActivity.m796showDuringDialog$lambda6(this.f$0, i);
            }
        });
        MenstruationBean menstruationBean = this.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf(menstruationBean.getDuring()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDuringDialog$lambda-6, reason: not valid java name */
    public static final void m796showDuringDialog$lambda6(MenstruationSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        MenstruationBean menstruationBean2 = null;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setDuring(Integer.parseInt(this$0.getViewModel().getDuringList().get(i)));
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding = this$0.binding;
        if (activityMenstruationSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationSettingBinding = null;
        }
        QSettingItem qSettingItem = activityMenstruationSettingBinding.qcMsDuring;
        StringBuilder sb = new StringBuilder();
        MenstruationBean menstruationBean3 = this$0.menstruationSettings;
        if (menstruationBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
        } else {
            menstruationBean2 = menstruationBean3;
        }
        sb.append(menstruationBean2.getDuring());
        sb.append(this$0.getString(R.string.qc_text_228));
        qSettingItem.setRightText(sb.toString());
    }

    public final void showCycleDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, getViewModel().getCycleList());
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_196));
        bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_228));
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                MenstruationSettingActivity.m794showCycleDialog$lambda7(this.f$0, i);
            }
        });
        MenstruationBean menstruationBean = this.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf(menstruationBean.getCycle()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCycleDialog$lambda-7, reason: not valid java name */
    public static final void m794showCycleDialog$lambda7(MenstruationSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        MenstruationBean menstruationBean2 = null;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setCycle(Integer.parseInt(this$0.getViewModel().getCycleList().get(i)));
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding = this$0.binding;
        if (activityMenstruationSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationSettingBinding = null;
        }
        QSettingItem qSettingItem = activityMenstruationSettingBinding.qcMsCycle;
        StringBuilder sb = new StringBuilder();
        MenstruationBean menstruationBean3 = this$0.menstruationSettings;
        if (menstruationBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
        } else {
            menstruationBean2 = menstruationBean3;
        }
        sb.append(menstruationBean2.getCycle());
        sb.append(this$0.getString(R.string.qc_text_228));
        qSettingItem.setRightText(sb.toString());
    }

    public final void showDateDialog() {
        MenstruationBean menstruationBean = this.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        DateUtil dateUtil = new DateUtil(menstruationBean.getLastTime(), true);
        BottomYMDDialog bottomYMDDialogCreate = new BottomYMDDialog.Builder(getActivity()).create();
        bottomYMDDialogCreate.initData(this);
        bottomYMDDialogCreate.setDialogTitle(getString(R.string.qc_text_197));
        bottomYMDDialogCreate.setListener(new BottomYMDDialog.DialogSaveClickListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda9
            @Override // com.qcwireless.qcwatch.base.dialog.BottomYMDDialog.DialogSaveClickListener
            public final void itemClick(int i, int i2, int i3) {
                MenstruationSettingActivity.m795showDateDialog$lambda8(this.f$0, i, i2, i3);
            }
        });
        bottomYMDDialogCreate.setCurrItem(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay());
        bottomYMDDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDateDialog$lambda-8, reason: not valid java name */
    public static final void m795showDateDialog$lambda8(MenstruationSettingActivity this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = new DateUtil(i, i2, i3);
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding = null;
        if (new DateUtil().daysBetweenMe(dateUtil) > 90) {
            String string = this$0.getString(R.string.qc_text_296);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_296)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (dateUtil.getUnixTimestamp() > new DateUtil().getUnixTimestamp()) {
            String string2 = this$0.getString(R.string.qc_text_229);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_229)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setLastTime(dateUtil.getUnixTimestamp());
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding2 = this$0.binding;
        if (activityMenstruationSettingBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMenstruationSettingBinding = activityMenstruationSettingBinding2;
        }
        activityMenstruationSettingBinding.qcMsLast.setRightText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(dateUtil));
    }

    public final void showAlert1Dialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, getViewModel().getAlarmList());
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_199));
        bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_228));
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda8
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                MenstruationSettingActivity.m792showAlert1Dialog$lambda9(this.f$0, i);
            }
        });
        MenstruationBean menstruationBean = this.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf(menstruationBean.getAlarm1()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAlert1Dialog$lambda-9, reason: not valid java name */
    public static final void m792showAlert1Dialog$lambda9(MenstruationSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        MenstruationBean menstruationBean2 = null;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setAlarm1(Integer.parseInt(this$0.getViewModel().getAlarmList().get(i)));
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding = this$0.binding;
        if (activityMenstruationSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationSettingBinding = null;
        }
        QSettingItem qSettingItem = activityMenstruationSettingBinding.qcMsAlarm1;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_246);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_246)");
        Object[] objArr = new Object[1];
        MenstruationBean menstruationBean3 = this$0.menstruationSettings;
        if (menstruationBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
        } else {
            menstruationBean2 = menstruationBean3;
        }
        objArr[0] = String.valueOf(menstruationBean2.getAlarm1());
        String str = String.format(string, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        qSettingItem.setRightText(str);
    }

    public final void showAlert2Dialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, getViewModel().getAlarmList());
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_200));
        bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_228));
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                MenstruationSettingActivity.m793showAlert2Dialog$lambda10(this.f$0, i);
            }
        });
        MenstruationBean menstruationBean = this.menstruationSettings;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf(menstruationBean.getAlarm2()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAlert2Dialog$lambda-10, reason: not valid java name */
    public static final void m793showAlert2Dialog$lambda10(MenstruationSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        MenstruationBean menstruationBean2 = null;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setAlarm2(Integer.parseInt(this$0.getViewModel().getAlarmList().get(i)));
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding = this$0.binding;
        if (activityMenstruationSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationSettingBinding = null;
        }
        QSettingItem qSettingItem = activityMenstruationSettingBinding.qcMsAlarm2;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_246);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_246)");
        Object[] objArr = new Object[1];
        MenstruationBean menstruationBean3 = this$0.menstruationSettings;
        if (menstruationBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
        } else {
            menstruationBean2 = menstruationBean3;
        }
        objArr[0] = String.valueOf(menstruationBean2.getAlarm2());
        String str = String.format(string, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        qSettingItem.setRightText(str);
    }

    public final void showTimeDialog() {
        BottomTimeDialog.Builder builder = new BottomTimeDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_time_selector);
        BottomTimeDialog bottomTimeDialogCreate = builder.create();
        bottomTimeDialogCreate.setListener(new BottomTimeDialog.TimeListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity$$ExternalSyntheticLambda4
            @Override // com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.TimeListener
            public final void min(int i) {
                MenstruationSettingActivity.m797showTimeDialog$lambda11(this.f$0, i);
            }
        });
        bottomTimeDialogCreate.initData(this);
        bottomTimeDialogCreate.setTitle(getString(R.string.qc_text_201));
        MenstruationBean menstruationBean = this.menstruationSettings;
        MenstruationBean menstruationBean2 = null;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        int alarmMin = menstruationBean.getAlarmMin() / 60;
        MenstruationBean menstruationBean3 = this.menstruationSettings;
        if (menstruationBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
        } else {
            menstruationBean2 = menstruationBean3;
        }
        bottomTimeDialogCreate.setCurrTime(alarmMin, menstruationBean2.getAlarmMin() % 60);
        bottomTimeDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTimeDialog$lambda-11, reason: not valid java name */
    public static final void m797showTimeDialog$lambda11(MenstruationSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenstruationBean menstruationBean = this$0.menstruationSettings;
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding = null;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menstruationSettings");
            menstruationBean = null;
        }
        menstruationBean.setAlarmMin(i);
        ActivityMenstruationSettingBinding activityMenstruationSettingBinding2 = this$0.binding;
        if (activityMenstruationSettingBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMenstruationSettingBinding = activityMenstruationSettingBinding2;
        }
        activityMenstruationSettingBinding.qcMsAlarm3.setRightText(DateUtil.dayMinToStr(i));
    }
}
