package com.qcwireless.qcwatch.ui.mine.goal;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.baidu.location.LocationClientOption;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog;
import com.qcwireless.qcwatch.base.event.DeviceNotifyTypeEvent;
import com.qcwireless.qcwatch.base.event.FinishFirstSettingEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityGoalSettingBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: GoalSettingActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0017J\b\u0010\u001e\u001a\u00020\u0017H\u0014J\u0006\u0010\u001f\u001a\u00020\u0017J\u0006\u0010 \u001a\u00020\u0017J\u0006\u0010!\u001a\u00020\u0017J\u0006\u0010\"\u001a\u00020\u0017J\u0006\u0010#\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGoalSettingBinding;", "calorieList", "", "", "distanceList", "hList", "hSleepList", "stepsList", TypedValues.AttributesType.S_TARGET, "Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;", "userEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "showCalorieDialog", "showDistanceDialog", "showSleepDialog", "showSportDialog", "showStepDialog", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GoalSettingActivity extends BaseActivity {
    private ActivityGoalSettingBinding binding;
    private TargetEntity target;
    private UserEntity userEntity;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private List<String> stepsList = new ArrayList();
    private List<String> calorieList = new ArrayList();
    private List<String> distanceList = new ArrayList();
    private List<String> hList = new ArrayList();
    private List<String> hSleepList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m953setupViews$lambda0(SetTimeRsp setTimeRsp) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GoalSettingActivity() {
        final GoalSettingActivity goalSettingActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<GoalSettingViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final GoalSettingViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(goalSettingActivity, Reflection.getOrCreateKotlinClass(GoalSettingViewModel.class), qualifier, objArr);
            }
        });
        long uid = UserConfig.INSTANCE.getInstance().getUid();
        String y_m_d = new DateUtil().getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
        this.userEntity = new UserEntity(uid, "", "", 1, 60.0f, 120, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, y_m_d, 0);
        this.target = new TargetEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f);
    }

    private final GoalSettingViewModel getViewModel() {
        return (GoalSettingViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGoalSettingBinding activityGoalSettingBindingInflate = ActivityGoalSettingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGoalSettingBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGoalSettingBindingInflate;
        if (activityGoalSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBindingInflate = null;
        }
        ConstraintLayout root = activityGoalSettingBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialog();
        if (BleOperateManager.getInstance().isConnected()) {
            CommandHandle.getInstance().executeReqCmd(new SetTimeReq(0), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda3
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    GoalSettingActivity.m953setupViews$lambda0((SetTimeRsp) baseRspCmd);
                }
            });
        }
        getViewModel().queryUserByUid();
        getViewModel().queryTargetByMac();
        initData();
        ActivityGoalSettingBinding activityGoalSettingBinding = this.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        activityGoalSettingBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_101));
        ViewKt.visible(activityGoalSettingBinding.titleBar.tvRightText);
        activityGoalSettingBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        activityGoalSettingBinding.goalSettingStep.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$setupViews$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showStepDialog();
            }
        });
        activityGoalSettingBinding.goalSettingDistance.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$setupViews$2$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showDistanceDialog();
            }
        });
        activityGoalSettingBinding.goalSettingCalorie.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$setupViews$2$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showCalorieDialog();
            }
        });
        activityGoalSettingBinding.goalSettingSportTime.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$setupViews$2$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showSportDialog();
            }
        });
        activityGoalSettingBinding.goalSettingSleepTime.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$setupViews$2$5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showSleepDialog();
            }
        });
        activityGoalSettingBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoalSettingActivity.m954setupViews$lambda2$lambda1(this.f$0, view);
            }
        });
        GoalSettingActivity goalSettingActivity = this;
        getViewModel().getUiTargetState().observe(goalSettingActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GoalSettingActivity.m955setupViews$lambda4(this.f$0, (GoalSettingViewModel.GoalSettingMacUI) obj);
            }
        });
        getViewModel().getUiState().observe(goalSettingActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GoalSettingActivity.m956setupViews$lambda5(this.f$0, (GoalSettingViewModel.GoalSettingUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m954setupViews$lambda2$lambda1(GoalSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().saveUserEntity(this$0.userEntity);
        this$0.getViewModel().saveUserEntityTarget(this$0.target);
        UserConfig.INSTANCE.getInstance().setFirstUse(true);
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new FinishFirstSettingEvent());
        EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
        this$0.getViewModel().updateGoalSettingToServer(this$0.userEntity);
        this$0.getViewModel().sendToDevice(this$0.userEntity);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m955setupViews$lambda4(GoalSettingActivity this$0, GoalSettingViewModel.GoalSettingMacUI goalSettingMacUI) {
        String string;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(goalSettingMacUI.getTarget());
        this$0.target = goalSettingMacUI.getTarget();
        this$0.dismissLoadingDialog();
        ActivityGoalSettingBinding activityGoalSettingBinding = this$0.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            string = this$0.getString(R.string.qc_text_88);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                    ge…ext_88)\n                }");
        } else {
            string = this$0.getString(R.string.qc_text_358);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                    ge…xt_358)\n                }");
        }
        activityGoalSettingBinding.goalSettingStep.setRightText(goalSettingMacUI.getTarget().getGoalSteps() + this$0.getString(R.string.qc_text_82));
        activityGoalSettingBinding.goalSettingDistance.setRightText(((int) this$0.target.getGoalDistance()) + string);
        activityGoalSettingBinding.goalSettingCalorie.setRightText(((int) this$0.target.getGoalCalorie()) + "kcal");
        QSettingItem qSettingItem = activityGoalSettingBinding.goalSettingSportTime;
        StringBuilder sb = new StringBuilder();
        sb.append(this$0.target.getGoalSportTime());
        sb.append('h');
        qSettingItem.setRightText(sb.toString());
        QSettingItem qSettingItem2 = activityGoalSettingBinding.goalSettingSleepTime;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this$0.target.getGoalSleepTime());
        sb2.append('h');
        qSettingItem2.setRightText(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m956setupViews$lambda5(GoalSettingActivity this$0, GoalSettingViewModel.GoalSettingUI goalSettingUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userEntity = goalSettingUI.getUserEntity();
    }

    public final void initData() {
        int i = 1000;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(1000, 30000, 1000);
        if (1000 <= progressionLastElement) {
            while (true) {
                this.stepsList.add(String.valueOf(i));
                if (i == progressionLastElement) {
                    break;
                } else {
                    i += 1000;
                }
            }
        }
        int progressionLastElement2 = ProgressionUtilKt.getProgressionLastElement(100, 4000, 100);
        if (100 <= progressionLastElement2) {
            int i2 = 100;
            while (true) {
                this.calorieList.add(String.valueOf(i2));
                if (i2 == progressionLastElement2) {
                    break;
                } else {
                    i2 += 100;
                }
            }
        }
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            this.distanceList.add(String.valueOf(i3));
            if (i4 > 100) {
                break;
            } else {
                i3 = i4;
            }
        }
        Iterator it = SequencesKt.takeWhile(SequencesKt.generateSequence(Double.valueOf(0.5d), new Function1<Double, Double>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity.initData.1
            public final Double invoke(double d) {
                return Double.valueOf(d + 0.5d);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Double invoke(Double d) {
                return invoke(d.doubleValue());
            }
        }), new Function1<Double, Boolean>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity.initData.2
            public final Boolean invoke(double d) {
                return Boolean.valueOf(d < 12.0d);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Double d) {
                return invoke(d.doubleValue());
            }
        }).iterator();
        while (it.hasNext()) {
            this.hList.add(String.valueOf(((Number) it.next()).doubleValue()));
        }
        int i5 = 5;
        while (true) {
            int i6 = i5 + 1;
            this.hSleepList.add(String.valueOf(i5));
            if (i6 > 12) {
                return;
            } else {
                i5 = i6;
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof DeviceNotifyTypeEvent) {
            getViewModel().queryUserByUid();
            getViewModel().queryTargetByMac();
        }
    }

    public final void showStepDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, this.stepsList);
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_136));
        bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_82));
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                GoalSettingActivity.m961showStepDialog$lambda6(this.f$0, i);
            }
        });
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf(this.target.getGoalSteps()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showStepDialog$lambda-6, reason: not valid java name */
    public static final void m961showStepDialog$lambda6(GoalSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Integer.valueOf(i));
        this$0.userEntity.setGoalSteps(Integer.parseInt(this$0.stepsList.get(i)));
        this$0.target.setGoalSteps(Integer.parseInt(this$0.stepsList.get(i)));
        ActivityGoalSettingBinding activityGoalSettingBinding = this$0.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        activityGoalSettingBinding.goalSettingStep.setRightText(Integer.parseInt(this$0.stepsList.get(i)) + this$0.getString(R.string.qc_text_82));
    }

    public final void showCalorieDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, this.calorieList);
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_137));
        bottomWheelViewDialogCreate.setUnitText("kcal");
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda4
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                GoalSettingActivity.m957showCalorieDialog$lambda7(this.f$0, i);
            }
        });
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf((int) this.target.getGoalCalorie()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCalorieDialog$lambda-7, reason: not valid java name */
    public static final void m957showCalorieDialog$lambda7(GoalSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userEntity.setGoalCalorie(Float.parseFloat(this$0.calorieList.get(i)));
        this$0.target.setGoalCalorie(Float.parseFloat(this$0.calorieList.get(i)));
        ActivityGoalSettingBinding activityGoalSettingBinding = this$0.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        activityGoalSettingBinding.goalSettingCalorie.setRightText(Integer.parseInt(this$0.calorieList.get(i)) + "kcal");
    }

    public final void showDistanceDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, this.distanceList);
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_138));
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_88));
        } else {
            bottomWheelViewDialogCreate.setUnitText(getString(R.string.qc_text_358));
        }
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda8
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) throws NumberFormatException {
                GoalSettingActivity.m958showDistanceDialog$lambda8(this.f$0, i);
            }
        });
        XLog.i(Integer.valueOf((int) this.userEntity.getGoalDistance()));
        bottomWheelViewDialogCreate.setCurrItem(((int) this.target.getGoalDistance()) > 0 ? ((int) this.target.getGoalDistance()) - 1 : 5);
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDistanceDialog$lambda-8, reason: not valid java name */
    public static final void m958showDistanceDialog$lambda8(GoalSettingActivity this$0, int i) throws NumberFormatException {
        String string;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Integer.valueOf(i));
        XLog.i(Integer.valueOf(Integer.parseInt(this$0.distanceList.get(i))));
        int i2 = Integer.parseInt(this$0.distanceList.get(i));
        float f = i2;
        this$0.userEntity.setGoalDistance(f);
        this$0.target.setGoalDistance(f);
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            string = this$0.getString(R.string.qc_text_88);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                    ge…ext_88)\n                }");
        } else {
            string = this$0.getString(R.string.qc_text_358);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                    ge…xt_358)\n                }");
        }
        ActivityGoalSettingBinding activityGoalSettingBinding = this$0.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        activityGoalSettingBinding.goalSettingDistance.setRightText(i2 + string);
    }

    public final void showSportDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, this.hList);
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_134));
        bottomWheelViewDialogCreate.setUnitText("h");
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                GoalSettingActivity.m960showSportDialog$lambda9(this.f$0, i);
            }
        });
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf(this.target.getGoalSportTime()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSportDialog$lambda-9, reason: not valid java name */
    public static final void m960showSportDialog$lambda9(GoalSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userEntity.setGoalSportTime(Float.parseFloat(this$0.hList.get(i)));
        this$0.target.setGoalSportTime(Float.parseFloat(this$0.hList.get(i)));
        ActivityGoalSettingBinding activityGoalSettingBinding = this$0.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        QSettingItem qSettingItem = activityGoalSettingBinding.goalSettingSportTime;
        StringBuilder sb = new StringBuilder();
        sb.append(Float.parseFloat(this$0.hList.get(i)));
        sb.append('h');
        qSettingItem.setRightText(sb.toString());
    }

    public final void showSleepDialog() {
        BottomWheelViewDialog bottomWheelViewDialogCreate = new BottomWheelViewDialog.Builder(getActivity()).create();
        bottomWheelViewDialogCreate.initData(this, this.hSleepList);
        bottomWheelViewDialogCreate.setDialogTitle(getString(R.string.qc_text_139));
        bottomWheelViewDialogCreate.setUnitText("h");
        bottomWheelViewDialogCreate.setListener(new BottomWheelViewDialog.SaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.SaveClickListener
            public final void itemClick(int i) {
                GoalSettingActivity.m959showSleepDialog$lambda10(this.f$0, i);
            }
        });
        bottomWheelViewDialogCreate.setCurrItemText(String.valueOf((int) this.target.getGoalSleepTime()));
        bottomWheelViewDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSleepDialog$lambda-10, reason: not valid java name */
    public static final void m959showSleepDialog$lambda10(GoalSettingActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userEntity.setGoalSleepTime(Float.parseFloat(this$0.hSleepList.get(i)));
        this$0.target.setGoalSleepTime(Float.parseFloat(this$0.hSleepList.get(i)));
        ActivityGoalSettingBinding activityGoalSettingBinding = this$0.binding;
        if (activityGoalSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGoalSettingBinding = null;
        }
        QSettingItem qSettingItem = activityGoalSettingBinding.goalSettingSleepTime;
        StringBuilder sb = new StringBuilder();
        sb.append(Float.parseFloat(this$0.hSleepList.get(i)));
        sb.append('h');
        qSettingItem.setRightText(sb.toString());
    }
}
