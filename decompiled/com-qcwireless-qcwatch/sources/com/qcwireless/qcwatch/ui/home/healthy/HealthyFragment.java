package com.qcwireless.qcwatch.ui.home.healthy;

import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.work.WorkRequest;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.req.TargetSettingReq;
import com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener;
import com.oudmon.ble.base.communication.responseImpl.DeviceSportNotifyListener;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.oudmon.ble.base.communication.rsp.TargetSettingRsp;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.event.BatteryLowEvent;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.BluetoothSyncEvent;
import com.qcwireless.qcwatch.base.event.DataSyncEvent;
import com.qcwireless.qcwatch.base.event.DeviceNotifyTypeEvent;
import com.qcwireless.qcwatch.base.event.DeviceSyncTodayStepsAndDetailEvent;
import com.qcwireless.qcwatch.base.event.DeviceSyncTodayStepsEvent;
import com.qcwireless.qcwatch.base.event.DeviceToAppSyncEvent;
import com.qcwireless.qcwatch.base.event.FirmUpdateEvent;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshAiAnalyzeEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.TodayDataSyncEvent;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceDownloadEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.AndroidVersion;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentHealthyBinding;
import com.qcwireless.qcwatch.ui.activity.MainActivity;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.bean.event.HealthItemRefreshEvent;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSupportRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal;
import com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import com.qcwireless.qcwatch.ui.base.view.healthy.MarqueeView;
import com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity;
import com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity;
import com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity;
import com.qcwireless.qcwatch.ui.home.drag.DragSelectActivity;
import com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity;
import com.qcwireless.qcwatch.ui.home.gps.GpsActivity;
import com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideOneActivity;
import com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment;
import com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBloodOxygenItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBloodPressureItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBloodSugarItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBodyTemperatureItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastGpsSportItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastHeartItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastHrvItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastMenstruationItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastOneKeyItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastSportItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.MeasureBean;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivity;
import com.qcwireless.qcwatch.ui.home.heart.HrvActivity;
import com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity;
import com.qcwireless.qcwatch.ui.home.menstruation.MenstruationSettingActivity;
import com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity;
import com.qcwireless.qcwatch.ui.home.pressure.RingPressureActivity;
import com.qcwireless.qcwatch.ui.home.sleep.SleepActivity;
import com.qcwireless.qcwatch.ui.home.sport.SportActivity;
import com.qcwireless.qcwatch.ui.home.sport.SportGoActivity;
import com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity;
import com.qcwireless.qcwatch.ui.home.step.StepActivity;
import com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity;
import com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity;
import com.qcwireless.qcwatch.ui.mine.login.LoginActivity;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: HealthyFragment.kt */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 T2\u00020\u0001:\u0006TUVWXYB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010.\u001a\u00020\u001aH\u0002J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0002J\b\u00102\u001a\u00020\u001aH\u0002J\b\u00103\u001a\u000200H\u0016J\b\u00104\u001a\u000200H\u0002J\"\u00105\u001a\u0002002\u0006\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\u001d2\b\u00108\u001a\u0004\u0018\u000109H\u0016J&\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\b\u0010B\u001a\u000200H\u0016J\u0010\u0010C\u001a\u0002002\u0006\u0010D\u001a\u00020\u001aH\u0016J\u0010\u0010E\u001a\u0002002\u0006\u0010F\u001a\u00020GH\u0007J\b\u0010H\u001a\u000200H\u0016J\b\u0010I\u001a\u000200H\u0016J\b\u0010J\u001a\u000200H\u0016J\u0010\u0010K\u001a\u0002002\u0006\u0010L\u001a\u00020MH\u0002J\u0006\u0010N\u001a\u000200J\b\u0010O\u001a\u000200H\u0002J\b\u0010P\u001a\u000200H\u0002J\b\u0010Q\u001a\u000200H\u0002J\b\u0010R\u001a\u000200H\u0002J\b\u0010S\u001a\u000200H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00060\u000eR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00060\u0016R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00060\u0018R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00060 R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'R\u000e\u0010*\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "barChart", "Lcom/qcwireless/qcwatch/ui/base/view/QStepBarChart;", "batteryDialog", "Lcom/qcwireless/qcwatch/base/dialog/CenterDialog;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentHealthyBinding;", "bleStatus", "Landroid/widget/ImageView;", "clsAiAnalyze", "Landroidx/constraintlayout/widget/ConstraintLayout;", "deviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$MyDeviceNotifyListener;", "handler", "Landroid/os/Handler;", "healthyAdapter", "Lcom/qcwireless/qcwatch/ui/home/healthy/adapter/MultipleItemQuickAdapter;", "marqueeView", "Lcom/qcwireless/qcwatch/ui/base/view/healthy/MarqueeView;", "myRunnable", "Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$MyRunnable;", "mySportDeviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$MySportDeviceNotifyListener;", "needShowLowerBatter", "", "sportRunning", "sportStatus", "", "sportType", "tenSecondsRefreshRunnable", "Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$TenSecondsRefreshRunnable;", "todayStep", "Landroid/widget/TextView;", "tvAiAnalyzeValue", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "warmingLayout", "warmingList", "", "", "checkPermission", "healthyModule", "", "initWarming", "isNotificationListenerEnabled", "loadDataOnce", "observe", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onHiddenChanged", "hidden", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onPause", "onResume", "onStop", "openNotificationSettings", "context", "Landroid/content/Context;", "refreshAiAnalyze", "showLocationWarningDialog", "showNotificationDialog", "showRingBatteryLowDialog", "startSyncData", "tenSecondsRefresh", "Companion", "LocationPermissionCallback", "MyDeviceNotifyListener", "MyRunnable", "MySportDeviceNotifyListener", "TenSecondsRefreshRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HealthyFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private QStepBarChart barChart;
    private CenterDialog batteryDialog;
    private FragmentHealthyBinding binding;
    private ImageView bleStatus;
    private ConstraintLayout clsAiAnalyze;
    private MyDeviceNotifyListener deviceNotifyListener;
    private final Handler handler;
    private MultipleItemQuickAdapter healthyAdapter;
    private MarqueeView marqueeView;
    private final MyRunnable myRunnable;
    private final MySportDeviceNotifyListener mySportDeviceNotifyListener;
    private boolean needShowLowerBatter;
    private boolean sportRunning;
    private int sportStatus;
    private int sportType;
    private final TenSecondsRefreshRunnable tenSecondsRefreshRunnable;
    private TextView todayStep;
    private TextView tvAiAnalyzeValue;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private ConstraintLayout warmingLayout;
    private List<String> warmingList;

    /* JADX WARN: Multi-variable type inference failed */
    public HealthyFragment() {
        final HealthyFragment healthyFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HealthyViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HealthyViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(healthyFragment, Reflection.getOrCreateKotlinClass(HealthyViewModel.class), qualifier, objArr);
            }
        });
        this.myRunnable = new MyRunnable();
        this.warmingList = new ArrayList();
        this.tenSecondsRefreshRunnable = new TenSecondsRefreshRunnable();
        this.mySportDeviceNotifyListener = new MySportDeviceNotifyListener();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HealthyViewModel getViewModel() {
        return (HealthyViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentHealthyBinding fragmentHealthyBindingInflate = FragmentHealthyBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentHealthyBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentHealthyBindingInflate;
        EventBus.getDefault().register(this);
        FragmentHealthyBinding fragmentHealthyBinding = this.binding;
        if (fragmentHealthyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHealthyBinding = null;
        }
        return fragmentHealthyBinding.getRoot();
    }

    private final void observe() {
        HealthyViewModel viewModel = getViewModel();
        viewModel.getUiState().observe(getViewLifecycleOwner(), new Observer() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthyFragment.m717observe$lambda3$lambda0(this.f$0, (HealthyViewModel.HealthUI) obj);
            }
        });
        viewModel.getFileDismissStatue().observe(getViewLifecycleOwner(), new Observer() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthyFragment.m718observe$lambda3$lambda2(this.f$0, (ArrayList) obj);
            }
        });
        viewModel.queryInitHealthData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observe$lambda-3$lambda-0, reason: not valid java name */
    public static final void m717observe$lambda3$lambda0(HealthyFragment this$0, HealthyViewModel.HealthUI healthUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QStepBarChart qStepBarChart = null;
        TextView textView = null;
        if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) {
            TextView textView2 = this$0.todayStep;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todayStep");
                textView2 = null;
            }
            textView2.setText("0");
        }
        if (healthUI.getRefreshType() == 1) {
            SyncStatus.INSTANCE.getGetInstance().setSync(false);
            FragmentHealthyBinding fragmentHealthyBinding = this$0.binding;
            if (fragmentHealthyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHealthyBinding = null;
            }
            fragmentHealthyBinding.syncRefresh.finishRefresh();
        }
        if (healthUI.getRefreshType() == 3) {
            if (healthUI.getTotalSteps() != null) {
                TextView textView3 = this$0.todayStep;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("todayStep");
                } else {
                    textView = textView3;
                }
                StepTotal totalSteps = healthUI.getTotalSteps();
                Intrinsics.checkNotNull(totalSteps);
                textView.setText(String.valueOf(totalSteps.getStep()));
                return;
            }
            return;
        }
        if (healthUI.getRefreshType() == 4) {
            if (healthUI.getTotalSteps() != null) {
                TextView textView4 = this$0.todayStep;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("todayStep");
                    textView4 = null;
                }
                StepTotal totalSteps2 = healthUI.getTotalSteps();
                Intrinsics.checkNotNull(totalSteps2);
                textView4.setText(String.valueOf(totalSteps2.getStep()));
            }
            if (healthUI.getStepHourData() != null) {
                QStepBarChart qStepBarChart2 = this$0.barChart;
                if (qStepBarChart2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("barChart");
                    qStepBarChart2 = null;
                }
                qStepBarChart2.setData(healthUI.getStepHourData());
            }
            if ((healthUI != null ? healthUI.getTotalSteps() : null) != null) {
                StepTotal totalSteps3 = healthUI.getTotalSteps();
                Intrinsics.checkNotNull(totalSteps3);
                PreUtil.putInt(PreUtil.Action_Today_Steps, totalSteps3.getStep());
            }
            new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            return;
        }
        if (healthUI.getTotalSteps() != null) {
            TextView textView5 = this$0.todayStep;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todayStep");
                textView5 = null;
            }
            StepTotal totalSteps4 = healthUI.getTotalSteps();
            Intrinsics.checkNotNull(totalSteps4);
            textView5.setText(String.valueOf(totalSteps4.getStep()));
            StepTotal totalSteps5 = healthUI.getTotalSteps();
            Intrinsics.checkNotNull(totalSteps5);
            PreUtil.putInt(PreUtil.Action_Today_Steps, totalSteps5.getStep());
            new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            UserConfig.INSTANCE.getInstance().setLastSyncTodaySteps(new DateUtil().getUnixTimestamp() + 180);
            UserConfig.INSTANCE.getInstance().save();
        } else {
            TextView textView6 = this$0.todayStep;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todayStep");
                textView6 = null;
            }
            textView6.setText("0");
        }
        if (healthUI.getStepHourData() != null) {
            QStepBarChart qStepBarChart3 = this$0.barChart;
            if (qStepBarChart3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("barChart");
            } else {
                qStepBarChart = qStepBarChart3;
            }
            qStepBarChart.setData(healthUI.getStepHourData());
        }
        this$0.healthyModule();
        this$0.getViewModel().syncTodayStepNotification();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observe$lambda-3$lambda-2, reason: not valid java name */
    public static final void m718observe$lambda3$lambda2(HealthyFragment this$0, ArrayList it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        bundle.putString("fileNames", MoshiUtilsKt.toJson(it));
        bundle.putInt("background", 0);
        HealthyFragment healthyFragment = this$0;
        FragmentActivity it2 = healthyFragment.getActivity();
        if (it2 != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            Intent intent = new Intent(it2, (Class<?>) WatchFileDismissActivity.class);
            intent.setFlags(1);
            intent.putExtras(bundle);
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
            healthyFragment.startActivity(intent);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        String battery;
        super.onResume();
        if (!UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            if (UserConfig.INSTANCE.getInstance().getUserToken().length() == 0) {
                getViewModel().getTokenNotLogin();
            }
        }
        if (!BleOperateManager.getInstance().isConnected()) {
            ThreadManager.getInstance().wakeUpNotWait();
        }
        if (!BleOperateManager.getInstance().isConnected()) {
            FragmentHealthyBinding fragmentHealthyBinding = this.binding;
            if (fragmentHealthyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHealthyBinding = null;
            }
            if (fragmentHealthyBinding.syncRefresh.isRefreshing()) {
                FragmentHealthyBinding fragmentHealthyBinding2 = this.binding;
                if (fragmentHealthyBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHealthyBinding2 = null;
                }
                fragmentHealthyBinding2.syncRefresh.finishRefresh();
            }
            ImageView imageView = this.bleStatus;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
                imageView = null;
            }
            imageView.setImageResource(R.mipmap.ble_disconnect);
        } else {
            ImageView imageView2 = this.bleStatus;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
                imageView2 = null;
            }
            imageView2.setImageResource(R.mipmap.ble_connect);
        }
        try {
            if (Build.VERSION.SDK_INT >= 24 && this.needShowLowerBatter) {
                this.needShowLowerBatter = false;
                new NotificationUtils(QJavaApplication.getInstance().getApplication()).initLowBatteryNotification();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshAiAnalyze();
        healthyModule();
        initWarming();
        this.handler.removeCallbacks(this.tenSecondsRefreshRunnable);
        this.handler.postDelayed(this.tenSecondsRefreshRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
        try {
            if (BleOperateManager.getInstance().isConnected() && UserConfig.INSTANCE.getInstance().getLastTenMinSyncTime() < new DateUtil().getUnixTimestamp()) {
                startSyncData();
            }
        } catch (Exception unused) {
        }
        this.deviceNotifyListener = new MyDeviceNotifyListener();
        BleOperateManager bleOperateManager = BleOperateManager.getInstance();
        MyDeviceNotifyListener myDeviceNotifyListener = this.deviceNotifyListener;
        if (myDeviceNotifyListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceNotifyListener");
            myDeviceNotifyListener = null;
        }
        bleOperateManager.addOutDeviceListener(100, myDeviceNotifyListener);
        BleOperateManager.getInstance().addSportDeviceListener(120, this.mySportDeviceNotifyListener);
        if (StringsKt.equals(Build.BRAND, "xiaomi", true) || StringsKt.equals(Build.BRAND, "redmi", true)) {
            this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    HealthyFragment.m719onResume$lambda4(this.f$0);
                }
            }, WorkRequest.MIN_BACKOFF_MILLIS);
        }
        if (!UserConfig.INSTANCE.getInstance().getDeviceNotScreen() || (battery = UserConfig.INSTANCE.getInstance().getBattery()) == null) {
            return;
        }
        int i = Integer.parseInt(battery);
        if (i >= 0 && i < 11) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResume$lambda-4, reason: not valid java name */
    public static final void m719onResume$lambda4(HealthyFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initWarming();
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$onResume$2", f = "HealthyFragment.kt", i = {0}, l = {270, 271}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$onResume$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = HealthyFragment.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final HealthyFragment healthyFragment = HealthyFragment.this;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.onResume.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null && deviceSetting.getBatteryWarming() && !UserConfig.INSTANCE.getInstance().getBatteryLow()) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final HealthyFragment healthyFragment2 = healthyFragment;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.onResume.2.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                healthyFragment2.showRingBatteryLowDialog();
                            }
                        });
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showRingBatteryLowDialog() {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_battery_low);
        CenterDialog centerDialog = this.batteryDialog;
        if (centerDialog != null) {
            Intrinsics.checkNotNull(centerDialog);
            if (centerDialog.isShowing()) {
                XLog.i("battery dialog dismiss");
                return;
            }
        }
        CenterDialog centerDialog2 = this.batteryDialog;
        if (centerDialog2 != null) {
            Intrinsics.checkNotNull(centerDialog2);
            centerDialog2.dismiss();
        }
        CenterDialog centerDialogCreate = builder.create();
        this.batteryDialog = centerDialogCreate;
        Intrinsics.checkNotNull(centerDialogCreate);
        centerDialogCreate.show();
        CenterDialog centerDialog3 = this.batteryDialog;
        Intrinsics.checkNotNull(centerDialog3);
        View contentView = centerDialog3.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "batteryDialog!!.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthyFragment.m723showRingBatteryLowDialog$lambda5(this.f$0, view);
            }
        });
        try {
            Object systemService = getActivity().getSystemService("notification");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.NotificationManager");
            }
            NotificationManager notificationManager = (NotificationManager) systemService;
            if (Build.VERSION.SDK_INT >= 24) {
                if (!notificationManager.areNotificationsEnabled()) {
                    UserConfig.INSTANCE.getInstance().setBatteryLow(false);
                    UserConfig.INSTANCE.getInstance().save();
                    this.needShowLowerBatter = true;
                    openNotificationSettings(getActivity());
                    return;
                }
                if (NotificationUtils.lowBatteryNotification) {
                    return;
                }
                new NotificationUtils(QJavaApplication.getInstance().getApplication()).initLowBatteryNotification();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showRingBatteryLowDialog$lambda-5, reason: not valid java name */
    public static final void m723showRingBatteryLowDialog$lambda5(HealthyFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserConfig.INSTANCE.getInstance().setBatteryLow(true);
        UserConfig.INSTANCE.getInstance().save();
        CenterDialog centerDialog = this$0.batteryDialog;
        Intrinsics.checkNotNull(centerDialog);
        centerDialog.dismiss();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [androidx.constraintlayout.widget.ConstraintLayout] */
    private final void initWarming() {
        try {
            if (getActivity() == null) {
                return;
            }
            this.warmingList.clear();
            if (!isNotificationListenerEnabled() || !QCApplication.INSTANCE.getGetInstance().isNotificationServiceRunning(getActivity())) {
                List<String> list = this.warmingList;
                String string = getString(R.string.qc_text_389);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_389)");
                list.add(string);
            }
            if (!checkPermission()) {
                List<String> list2 = this.warmingList;
                String string2 = getString(R.string.qc_text_390);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_390)");
                list2.add(string2);
            }
            if (!UserConfig.INSTANCE.getInstance().getWarmingAutoStart()) {
                List<String> list3 = this.warmingList;
                String string3 = getString(R.string.qc_text_391);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_391)");
                list3.add(string3);
            }
            if (!UserConfig.INSTANCE.getInstance().getWarmingLock()) {
                List<String> list4 = this.warmingList;
                String string4 = getString(R.string.qc_text_392);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_392)");
                list4.add(string4);
            }
            if (!UserConfig.INSTANCE.getInstance().getWarmingBatteryAllow()) {
                List<String> list5 = this.warmingList;
                String string5 = getString(R.string.qc_text_393);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_393)");
                list5.add(string5);
            }
            if (BleOperateManager.getInstance().isConnected()) {
                if (!PermissionUtilKt.hasCallPhonePermission((FragmentActivity) getActivity())) {
                    List<String> list6 = this.warmingList;
                    String string6 = getString(R.string.qc_text_451);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_451)");
                    list6.add(string6);
                }
                if (!PermissionUtilKt.hasSMSPermission((FragmentActivity) getActivity())) {
                    List<String> list7 = this.warmingList;
                    String string7 = getString(R.string.qc_text_452);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_452)");
                    list7.add(string7);
                }
            }
            MarqueeView marqueeView = null;
            if (this.warmingList.size() > 0) {
                ConstraintLayout constraintLayout = this.warmingLayout;
                if (constraintLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("warmingLayout");
                    constraintLayout = null;
                }
                ViewKt.visible(constraintLayout);
                MarqueeView marqueeView2 = this.marqueeView;
                if (marqueeView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marqueeView");
                } else {
                    marqueeView = marqueeView2;
                }
                marqueeView.startWithList(this.warmingList);
                return;
            }
            ?? r0 = this.warmingLayout;
            if (r0 == 0) {
                Intrinsics.throwUninitializedPropertyAccessException("warmingLayout");
            } else {
                marqueeView = r0;
            }
            ViewKt.gone(marqueeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        MarqueeView marqueeView = this.marqueeView;
        if (marqueeView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marqueeView");
            marqueeView = null;
        }
        marqueeView.stopFlipping();
        this.handler.removeCallbacks(this.tenSecondsRefreshRunnable);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            XLog.i("----hidden---");
            this.handler.removeCallbacks(this.tenSecondsRefreshRunnable);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.tenSecondsRefreshRunnable);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        this.healthyAdapter = new MultipleItemQuickAdapter(getActivity(), getViewModel().getItemList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        MultipleItemQuickAdapter multipleItemQuickAdapter = null;
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycleview_item_footer, (ViewGroup) null);
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.recycleview_item_header, (ViewGroup) null);
        ConstraintLayout constraintLayout = (ConstraintLayout) headerView.findViewById(R.id.today_step_layout);
        View viewFindViewById = headerView.findViewById(R.id.tv_today_steps);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "headerView.findViewById(R.id.tv_today_steps)");
        this.todayStep = (TextView) viewFindViewById;
        View viewFindViewById2 = headerView.findViewById(R.id.tv_ai_analyze_value);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "headerView.findViewById(R.id.tv_ai_analyze_value)");
        this.tvAiAnalyzeValue = (TextView) viewFindViewById2;
        View viewFindViewById3 = headerView.findViewById(R.id.cls_ai_analyze);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "headerView.findViewById(R.id.cls_ai_analyze)");
        this.clsAiAnalyze = (ConstraintLayout) viewFindViewById3;
        View viewFindViewById4 = headerView.findViewById(R.id.qc_step_chart);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "headerView.findViewById(R.id.qc_step_chart)");
        this.barChart = (QStepBarChart) viewFindViewById4;
        View viewFindViewById5 = headerView.findViewById(R.id.image_ble_status);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "headerView.findViewById(R.id.image_ble_status)");
        this.bleStatus = (ImageView) viewFindViewById5;
        View viewFindViewById6 = headerView.findViewById(R.id.marquee_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "headerView.findViewById(R.id.marquee_view)");
        this.marqueeView = (MarqueeView) viewFindViewById6;
        View viewFindViewById7 = headerView.findViewById(R.id.warming_info);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "headerView.findViewById(R.id.warming_info)");
        this.warmingLayout = (ConstraintLayout) viewFindViewById7;
        ((ImageView) headerView.findViewById(R.id.image_right)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthyFragment.m713loadDataOnce$lambda6(this.f$0, view2);
            }
        });
        ConstraintLayout constraintLayout2 = this.clsAiAnalyze;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clsAiAnalyze");
            constraintLayout2 = null;
        }
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthyFragment.m714loadDataOnce$lambda7(this.f$0, view2);
            }
        });
        MarqueeView marqueeView = this.marqueeView;
        if (marqueeView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marqueeView");
            marqueeView = null;
        }
        marqueeView.startFlipping();
        MarqueeView marqueeView2 = this.marqueeView;
        if (marqueeView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marqueeView");
            marqueeView2 = null;
        }
        marqueeView2.setOnItemClickListener(new MarqueeView.OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.ui.base.view.healthy.MarqueeView.OnItemClickListener
            public final void onItemClick(int i, TextView textView) throws NoSuchMethodException, SecurityException {
                HealthyFragment.m715loadDataOnce$lambda9(this.f$0, i, textView);
            }
        });
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthyFragment.m707loadDataOnce$lambda10(this.f$0, view2);
            }
        });
        ImageView imageView = this.bleStatus;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthyFragment.m708loadDataOnce$lambda11(view2);
            }
        });
        ((TextView) view.findViewById(R.id.healthy_tv_bottom_edit)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthyFragment.m709loadDataOnce$lambda12(this.f$0, view2);
            }
        });
        MultipleItemQuickAdapter multipleItemQuickAdapter2 = this.healthyAdapter;
        if (multipleItemQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("healthyAdapter");
            multipleItemQuickAdapter2 = null;
        }
        Intrinsics.checkNotNullExpressionValue(view, "view");
        BaseQuickAdapter.addFooterView$default(multipleItemQuickAdapter2, view, 0, 0, 6, null);
        MultipleItemQuickAdapter multipleItemQuickAdapter3 = this.healthyAdapter;
        if (multipleItemQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("healthyAdapter");
            multipleItemQuickAdapter3 = null;
        }
        Intrinsics.checkNotNullExpressionValue(headerView, "headerView");
        BaseQuickAdapter.addHeaderView$default(multipleItemQuickAdapter3, headerView, 0, 0, 6, null);
        final FragmentHealthyBinding fragmentHealthyBinding = this.binding;
        if (fragmentHealthyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHealthyBinding = null;
        }
        fragmentHealthyBinding.healthyCardRcv.setLayoutManager(gridLayoutManager);
        MyRecycleView myRecycleView = fragmentHealthyBinding.healthyCardRcv;
        MultipleItemQuickAdapter multipleItemQuickAdapter4 = this.healthyAdapter;
        if (multipleItemQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("healthyAdapter");
            multipleItemQuickAdapter4 = null;
        }
        myRecycleView.setAdapter(multipleItemQuickAdapter4);
        fragmentHealthyBinding.syncRefresh.setOnRefreshListener(new OnRefreshListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda6
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) throws Throwable {
                HealthyFragment.m710loadDataOnce$lambda14$lambda13(this.f$0, fragmentHealthyBinding, refreshLayout);
            }
        });
        MultipleItemQuickAdapter multipleItemQuickAdapter5 = this.healthyAdapter;
        if (multipleItemQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("healthyAdapter");
            multipleItemQuickAdapter5 = null;
        }
        multipleItemQuickAdapter5.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) throws NoSuchMethodException, SecurityException {
                HealthyFragment.m711loadDataOnce$lambda15(this.f$0, baseQuickAdapter, view2, i);
            }
        });
        observe();
        getViewModel().syncTodayStepNotification();
        try {
            if (BleOperateManager.getInstance().isConnected()) {
                startSyncData();
            }
        } catch (Exception unused) {
        }
        this.handler.postDelayed(this.tenSecondsRefreshRunnable, 0L);
        MultipleItemQuickAdapter multipleItemQuickAdapter6 = this.healthyAdapter;
        if (multipleItemQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("healthyAdapter");
        } else {
            multipleItemQuickAdapter = multipleItemQuickAdapter6;
        }
        multipleItemQuickAdapter.getClickType().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda16
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthyFragment.m712loadDataOnce$lambda17(this.f$0, (MeasureBean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-6, reason: not valid java name */
    public static final void m713loadDataOnce$lambda6(HealthyFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HealthyFragment healthyFragment = this$0;
        FragmentActivity it = healthyFragment.getActivity();
        if (it != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) BackgroundRunningGuideOneActivity.class);
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
            healthyFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7, reason: not valid java name */
    public static final void m714loadDataOnce$lambda7(HealthyFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            String string = this$0.getString(R.string.qc_text_245);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_245)");
            GlobalKt.showToast$default(string, 0, 1, null);
            HealthyFragment healthyFragment = this$0;
            FragmentActivity it = healthyFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) LoginActivity.class);
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
                healthyFragment.startActivity(intent);
                return;
            }
            return;
        }
        NetWorkUtils.Companion companion = NetWorkUtils.INSTANCE;
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        if (!companion.isNetworkAvailable(contextRequireContext)) {
            String string2 = this$0.getString(R.string.qc_text_223);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_223)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        if (!BleOperateManager.getInstance().isConnected()) {
            String string3 = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string3, 0, 1, null);
            return;
        }
        HealthyFragment healthyFragment2 = this$0;
        FragmentActivity it2 = healthyFragment2.getActivity();
        if (it2 != null) {
            ArrayList<Pair> arrayList2 = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            Intent intent2 = new Intent(it2, (Class<?>) AiHealthAnalysisResultActivity.class);
            for (Pair pair2 : arrayList2) {
                if (pair2 != null) {
                    String str2 = (String) pair2.getFirst();
                    Object second2 = pair2.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                    } else {
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            healthyFragment2.startActivity(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-9, reason: not valid java name */
    public static final void m715loadDataOnce$lambda9(HealthyFragment this$0, int i, TextView textView) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (StringsKt.equals(this$0.getString(R.string.qc_text_452), textView.getText().toString(), false)) {
            if (!this$0.isNotificationListenerEnabled()) {
                this$0.showNotificationDialog();
                return;
            }
            HealthyFragment healthyFragment = this$0;
            FragmentActivity it = healthyFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) MessagePushActivity.class);
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
                healthyFragment.startActivity(intent);
                return;
            }
            return;
        }
        if (StringsKt.equals(this$0.getString(R.string.qc_text_451), textView.getText().toString(), false)) {
            PermissionUtilKt.requestCallPhonePermission((FragmentActivity) this$0.getActivity(), new OnPermissionCallback() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda4
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    HealthyFragment.m716loadDataOnce$lambda9$lambda8(list, z);
                }
            });
            return;
        }
        HealthyFragment healthyFragment2 = this$0;
        FragmentActivity it2 = healthyFragment2.getActivity();
        if (it2 != null) {
            ArrayList<Pair> arrayList2 = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            Intent intent2 = new Intent(it2, (Class<?>) BackgroundRunningGuideOneActivity.class);
            for (Pair pair2 : arrayList2) {
                if (pair2 != null) {
                    String str2 = (String) pair2.getFirst();
                    Object second2 = pair2.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                    } else {
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            healthyFragment2.startActivity(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-9$lambda-8, reason: not valid java name */
    public static final void m716loadDataOnce$lambda9$lambda8(List list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "<anonymous parameter 0>");
        if (z) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HealthyFragment$loadDataOnce$3$1$1(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-10, reason: not valid java name */
    public static final void m707loadDataOnce$lambda10(HealthyFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HealthyFragment healthyFragment = this$0;
        FragmentActivity it = healthyFragment.getActivity();
        if (it != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) StepActivity.class);
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
            healthyFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-11, reason: not valid java name */
    public static final void m708loadDataOnce$lambda11(View view) {
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        ThreadManager.getInstance().reSetLastConnectTime(0);
        ThreadManager.getInstance().wakeUpNotWait();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-12, reason: not valid java name */
    public static final void m709loadDataOnce$lambda12(HealthyFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DragSelectActivity.INSTANCE.start(this$0.getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-14$lambda-13, reason: not valid java name */
    public static final void m710loadDataOnce$lambda14$lambda13(HealthyFragment this$0, FragmentHealthyBinding this_run, RefreshLayout it) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(it, "it");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            this_run.syncRefresh.finishRefresh(false);
            return;
        }
        XLog.i("下拉刷新,开始同步数据...");
        this$0.getViewModel().syncWatchData();
        SyncStatus.INSTANCE.getGetInstance().setSync(true);
        this$0.handler.removeCallbacks(this$0.myRunnable);
        this$0.handler.postDelayed(this$0.myRunnable, 13000L);
        LogToFile.getInstance().wtf("开始同步所有数据");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-15, reason: not valid java name */
    public static final void m711loadDataOnce$lambda15(HealthyFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        switch (this$0.getViewModel().getItemList().get(i).getItemType()) {
            case 1:
                HealthyFragment healthyFragment = this$0;
                FragmentActivity it = healthyFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) HeartActivity.class);
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
                    Unit unit2 = Unit.INSTANCE;
                    Unit unit3 = Unit.INSTANCE;
                    Unit unit4 = Unit.INSTANCE;
                    healthyFragment.startActivity(intent);
                    Unit unit5 = Unit.INSTANCE;
                    Unit unit6 = Unit.INSTANCE;
                    break;
                }
                break;
            case 2:
                HealthyFragment healthyFragment2 = this$0;
                FragmentActivity it2 = healthyFragment2.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) SleepActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit7 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit8 = Unit.INSTANCE;
                    Unit unit9 = Unit.INSTANCE;
                    Unit unit10 = Unit.INSTANCE;
                    healthyFragment2.startActivity(intent2);
                    Unit unit11 = Unit.INSTANCE;
                    Unit unit12 = Unit.INSTANCE;
                    break;
                }
                break;
            case 3:
                HealthyFragment healthyFragment3 = this$0;
                FragmentActivity it3 = healthyFragment3.getActivity();
                if (it3 != null) {
                    ArrayList<Pair> arrayList3 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it3, "it");
                    Intent intent3 = new Intent(it3, (Class<?>) SportActivity.class);
                    for (Pair pair3 : arrayList3) {
                        if (pair3 != null) {
                            String str3 = (String) pair3.getFirst();
                            Object second3 = pair3.getSecond();
                            if (second3 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).intValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).byteValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Character) second3).charValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).shortValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Boolean) second3).booleanValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).longValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).floatValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).doubleValue()), "putExtra(name, value)");
                            } else if (second3 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (String) second3), "putExtra(name, value)");
                            } else if (second3 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (CharSequence) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (boolean[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (byte[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (short[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (char[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (int[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (long[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (float[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (double[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Bundle) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(name, value)");
                            } else {
                                Unit unit13 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit14 = Unit.INSTANCE;
                    Unit unit15 = Unit.INSTANCE;
                    Unit unit16 = Unit.INSTANCE;
                    healthyFragment3.startActivity(intent3);
                    Unit unit17 = Unit.INSTANCE;
                    Unit unit18 = Unit.INSTANCE;
                    break;
                }
                break;
            case 4:
                HealthyFragment healthyFragment4 = this$0;
                FragmentActivity it4 = healthyFragment4.getActivity();
                if (it4 != null) {
                    ArrayList<Pair> arrayList4 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it4, "it");
                    Intent intent4 = new Intent(it4, (Class<?>) BloodOxygenActivity.class);
                    for (Pair pair4 : arrayList4) {
                        if (pair4 != null) {
                            String str4 = (String) pair4.getFirst();
                            Object second4 = pair4.getSecond();
                            if (second4 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).intValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).byteValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Character) second4).charValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).shortValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Boolean) second4).booleanValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).longValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).floatValue()), "putExtra(name, value)");
                            } else if (second4 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).doubleValue()), "putExtra(name, value)");
                            } else if (second4 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (String) second4), "putExtra(name, value)");
                            } else if (second4 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (CharSequence) second4), "putExtra(name, value)");
                            } else if (second4 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Parcelable) second4), "putExtra(name, value)");
                            } else if (second4 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(name, value)");
                            } else if (second4 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(name, value)");
                            } else if (second4 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(name, value)");
                            } else if (second4 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (boolean[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (byte[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (short[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (char[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (int[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (long[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (float[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (double[]) second4), "putExtra(name, value)");
                            } else if (second4 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Bundle) second4), "putExtra(name, value)");
                            } else if (second4 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Parcelable) second4), "putExtra(name, value)");
                            } else {
                                Unit unit19 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit20 = Unit.INSTANCE;
                    Unit unit21 = Unit.INSTANCE;
                    Unit unit22 = Unit.INSTANCE;
                    healthyFragment4.startActivity(intent4);
                    Unit unit23 = Unit.INSTANCE;
                    Unit unit24 = Unit.INSTANCE;
                    break;
                }
                break;
            case 5:
                if (UserConfig.INSTANCE.getInstance().getMenstruationSetting()) {
                    HealthyFragment healthyFragment5 = this$0;
                    FragmentActivity it5 = healthyFragment5.getActivity();
                    if (it5 != null) {
                        ArrayList<Pair> arrayList5 = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it5, "it");
                        Intent intent5 = new Intent(it5, (Class<?>) MenstruationActivity.class);
                        for (Pair pair5 : arrayList5) {
                            if (pair5 != null) {
                                String str5 = (String) pair5.getFirst();
                                Object second5 = pair5.getSecond();
                                if (second5 instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).intValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).byteValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Character) second5).charValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).shortValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Boolean) second5).booleanValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).longValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).floatValue()), "putExtra(name, value)");
                                } else if (second5 instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).doubleValue()), "putExtra(name, value)");
                                } else if (second5 instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (String) second5), "putExtra(name, value)");
                                } else if (second5 instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (CharSequence) second5), "putExtra(name, value)");
                                } else if (second5 instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Parcelable) second5), "putExtra(name, value)");
                                } else if (second5 instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Serializable) second5), "putExtra(name, value)");
                                } else if (second5 instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Serializable) second5), "putExtra(name, value)");
                                } else if (second5 instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Serializable) second5), "putExtra(name, value)");
                                } else if (second5 instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (boolean[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (byte[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (short[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (char[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (int[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (long[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (float[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (double[]) second5), "putExtra(name, value)");
                                } else if (second5 instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Bundle) second5), "putExtra(name, value)");
                                } else if (second5 instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Parcelable) second5), "putExtra(name, value)");
                                } else {
                                    Unit unit25 = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit26 = Unit.INSTANCE;
                        Unit unit27 = Unit.INSTANCE;
                        Unit unit28 = Unit.INSTANCE;
                        healthyFragment5.startActivity(intent5);
                        Unit unit29 = Unit.INSTANCE;
                        Unit unit30 = Unit.INSTANCE;
                        break;
                    }
                } else {
                    HealthyFragment healthyFragment6 = this$0;
                    FragmentActivity it6 = healthyFragment6.getActivity();
                    if (it6 != null) {
                        ArrayList<Pair> arrayList6 = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it6, "it");
                        Intent intent6 = new Intent(it6, (Class<?>) MenstruationSettingActivity.class);
                        for (Pair pair6 : arrayList6) {
                            if (pair6 != null) {
                                String str6 = (String) pair6.getFirst();
                                Object second6 = pair6.getSecond();
                                if (second6 instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Number) second6).intValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Number) second6).byteValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Character) second6).charValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Number) second6).shortValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Boolean) second6).booleanValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Number) second6).longValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Number) second6).floatValue()), "putExtra(name, value)");
                                } else if (second6 instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, ((Number) second6).doubleValue()), "putExtra(name, value)");
                                } else if (second6 instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (String) second6), "putExtra(name, value)");
                                } else if (second6 instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (CharSequence) second6), "putExtra(name, value)");
                                } else if (second6 instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (Parcelable) second6), "putExtra(name, value)");
                                } else if (second6 instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (Serializable) second6), "putExtra(name, value)");
                                } else if (second6 instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (Serializable) second6), "putExtra(name, value)");
                                } else if (second6 instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (Serializable) second6), "putExtra(name, value)");
                                } else if (second6 instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (boolean[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (byte[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (short[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (char[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (int[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (long[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (float[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (double[]) second6), "putExtra(name, value)");
                                } else if (second6 instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (Bundle) second6), "putExtra(name, value)");
                                } else if (second6 instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent6.putExtra(str6, (Parcelable) second6), "putExtra(name, value)");
                                } else {
                                    Unit unit31 = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit32 = Unit.INSTANCE;
                        Unit unit33 = Unit.INSTANCE;
                        Unit unit34 = Unit.INSTANCE;
                        healthyFragment6.startActivity(intent6);
                        Unit unit35 = Unit.INSTANCE;
                        Unit unit36 = Unit.INSTANCE;
                        break;
                    }
                }
                break;
            case 6:
                HealthyFragment healthyFragment7 = this$0;
                FragmentActivity it7 = healthyFragment7.getActivity();
                if (it7 != null) {
                    ArrayList<Pair> arrayList7 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it7, "it");
                    Intent intent7 = new Intent(it7, (Class<?>) TemperatureActivity.class);
                    for (Pair pair7 : arrayList7) {
                        if (pair7 != null) {
                            String str7 = (String) pair7.getFirst();
                            Object second7 = pair7.getSecond();
                            if (second7 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Number) second7).intValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Number) second7).byteValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Character) second7).charValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Number) second7).shortValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Boolean) second7).booleanValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Number) second7).longValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Number) second7).floatValue()), "putExtra(name, value)");
                            } else if (second7 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, ((Number) second7).doubleValue()), "putExtra(name, value)");
                            } else if (second7 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (String) second7), "putExtra(name, value)");
                            } else if (second7 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (CharSequence) second7), "putExtra(name, value)");
                            } else if (second7 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (Parcelable) second7), "putExtra(name, value)");
                            } else if (second7 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (Serializable) second7), "putExtra(name, value)");
                            } else if (second7 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (Serializable) second7), "putExtra(name, value)");
                            } else if (second7 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (Serializable) second7), "putExtra(name, value)");
                            } else if (second7 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (boolean[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (byte[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (short[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (char[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (int[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (long[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (float[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (double[]) second7), "putExtra(name, value)");
                            } else if (second7 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (Bundle) second7), "putExtra(name, value)");
                            } else if (second7 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent7.putExtra(str7, (Parcelable) second7), "putExtra(name, value)");
                            } else {
                                Unit unit37 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit38 = Unit.INSTANCE;
                    Unit unit39 = Unit.INSTANCE;
                    Unit unit40 = Unit.INSTANCE;
                    healthyFragment7.startActivity(intent7);
                    Unit unit41 = Unit.INSTANCE;
                    Unit unit42 = Unit.INSTANCE;
                    break;
                }
                break;
            case 7:
                if (UserConfig.INSTANCE.getInstance().getGpsWarmingDialog()) {
                    PermissionUtilKt.requestBgLocation((FragmentActivity) this$0.getActivity(), this$0.new LocationPermissionCallback());
                    break;
                } else {
                    this$0.showLocationWarningDialog();
                    UserConfig.INSTANCE.getInstance().setGpsWarmingDialog(true);
                    UserConfig.INSTANCE.getInstance().save();
                    break;
                }
            case 9:
                HealthyFragment healthyFragment8 = this$0;
                FragmentActivity it8 = healthyFragment8.getActivity();
                if (it8 != null) {
                    ArrayList<Pair> arrayList8 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it8, "it");
                    Intent intent8 = new Intent(it8, (Class<?>) BloodPressureActivity.class);
                    for (Pair pair8 : arrayList8) {
                        if (pair8 != null) {
                            String str8 = (String) pair8.getFirst();
                            Object second8 = pair8.getSecond();
                            if (second8 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Number) second8).intValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Number) second8).byteValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Character) second8).charValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Number) second8).shortValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Boolean) second8).booleanValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Number) second8).longValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Number) second8).floatValue()), "putExtra(name, value)");
                            } else if (second8 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, ((Number) second8).doubleValue()), "putExtra(name, value)");
                            } else if (second8 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (String) second8), "putExtra(name, value)");
                            } else if (second8 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (CharSequence) second8), "putExtra(name, value)");
                            } else if (second8 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (Parcelable) second8), "putExtra(name, value)");
                            } else if (second8 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (Serializable) second8), "putExtra(name, value)");
                            } else if (second8 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (Serializable) second8), "putExtra(name, value)");
                            } else if (second8 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (Serializable) second8), "putExtra(name, value)");
                            } else if (second8 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (boolean[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (byte[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (short[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (char[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (int[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (long[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (float[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (double[]) second8), "putExtra(name, value)");
                            } else if (second8 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (Bundle) second8), "putExtra(name, value)");
                            } else if (second8 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent8.putExtra(str8, (Parcelable) second8), "putExtra(name, value)");
                            } else {
                                Unit unit43 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit44 = Unit.INSTANCE;
                    Unit unit45 = Unit.INSTANCE;
                    Unit unit46 = Unit.INSTANCE;
                    healthyFragment8.startActivity(intent8);
                    Unit unit47 = Unit.INSTANCE;
                    Unit unit48 = Unit.INSTANCE;
                    break;
                }
                break;
            case 10:
                HealthyFragment healthyFragment9 = this$0;
                FragmentActivity it9 = healthyFragment9.getActivity();
                if (it9 != null) {
                    ArrayList<Pair> arrayList9 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it9, "it");
                    Intent intent9 = new Intent(it9, (Class<?>) OneKeyCheckActivity.class);
                    for (Pair pair9 : arrayList9) {
                        if (pair9 != null) {
                            String str9 = (String) pair9.getFirst();
                            Object second9 = pair9.getSecond();
                            if (second9 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Number) second9).intValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Number) second9).byteValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Character) second9).charValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Number) second9).shortValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Boolean) second9).booleanValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Number) second9).longValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Number) second9).floatValue()), "putExtra(name, value)");
                            } else if (second9 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, ((Number) second9).doubleValue()), "putExtra(name, value)");
                            } else if (second9 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (String) second9), "putExtra(name, value)");
                            } else if (second9 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (CharSequence) second9), "putExtra(name, value)");
                            } else if (second9 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (Parcelable) second9), "putExtra(name, value)");
                            } else if (second9 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (Serializable) second9), "putExtra(name, value)");
                            } else if (second9 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (Serializable) second9), "putExtra(name, value)");
                            } else if (second9 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (Serializable) second9), "putExtra(name, value)");
                            } else if (second9 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (boolean[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (byte[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (short[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (char[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (int[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (long[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (float[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (double[]) second9), "putExtra(name, value)");
                            } else if (second9 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (Bundle) second9), "putExtra(name, value)");
                            } else if (second9 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent9.putExtra(str9, (Parcelable) second9), "putExtra(name, value)");
                            } else {
                                Unit unit49 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit50 = Unit.INSTANCE;
                    Unit unit51 = Unit.INSTANCE;
                    Unit unit52 = Unit.INSTANCE;
                    healthyFragment9.startActivity(intent9);
                    Unit unit53 = Unit.INSTANCE;
                    Unit unit54 = Unit.INSTANCE;
                    break;
                }
                break;
            case 12:
                HealthyFragment healthyFragment10 = this$0;
                FragmentActivity it10 = healthyFragment10.getActivity();
                if (it10 != null) {
                    ArrayList<Pair> arrayList10 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it10, "it");
                    Intent intent10 = new Intent(it10, (Class<?>) BloodSugarActivity.class);
                    for (Pair pair10 : arrayList10) {
                        if (pair10 != null) {
                            String str10 = (String) pair10.getFirst();
                            Object second10 = pair10.getSecond();
                            if (second10 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Number) second10).intValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Number) second10).byteValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Character) second10).charValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Number) second10).shortValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Boolean) second10).booleanValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Number) second10).longValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Number) second10).floatValue()), "putExtra(name, value)");
                            } else if (second10 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, ((Number) second10).doubleValue()), "putExtra(name, value)");
                            } else if (second10 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (String) second10), "putExtra(name, value)");
                            } else if (second10 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (CharSequence) second10), "putExtra(name, value)");
                            } else if (second10 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (Parcelable) second10), "putExtra(name, value)");
                            } else if (second10 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (Serializable) second10), "putExtra(name, value)");
                            } else if (second10 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (Serializable) second10), "putExtra(name, value)");
                            } else if (second10 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (Serializable) second10), "putExtra(name, value)");
                            } else if (second10 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (boolean[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (byte[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (short[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (char[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (int[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (long[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (float[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (double[]) second10), "putExtra(name, value)");
                            } else if (second10 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (Bundle) second10), "putExtra(name, value)");
                            } else if (second10 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent10.putExtra(str10, (Parcelable) second10), "putExtra(name, value)");
                            } else {
                                Unit unit55 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit56 = Unit.INSTANCE;
                    Unit unit57 = Unit.INSTANCE;
                    Unit unit58 = Unit.INSTANCE;
                    healthyFragment10.startActivity(intent10);
                    Unit unit59 = Unit.INSTANCE;
                    Unit unit60 = Unit.INSTANCE;
                    break;
                }
                break;
            case 13:
                HealthyFragment healthyFragment11 = this$0;
                FragmentActivity it11 = healthyFragment11.getActivity();
                if (it11 != null) {
                    ArrayList<Pair> arrayList11 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it11, "it");
                    Intent intent11 = new Intent(it11, (Class<?>) HrvActivity.class);
                    for (Pair pair11 : arrayList11) {
                        if (pair11 != null) {
                            String str11 = (String) pair11.getFirst();
                            Object second11 = pair11.getSecond();
                            if (second11 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Number) second11).intValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Number) second11).byteValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Character) second11).charValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Number) second11).shortValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Boolean) second11).booleanValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Number) second11).longValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Number) second11).floatValue()), "putExtra(name, value)");
                            } else if (second11 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, ((Number) second11).doubleValue()), "putExtra(name, value)");
                            } else if (second11 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (String) second11), "putExtra(name, value)");
                            } else if (second11 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (CharSequence) second11), "putExtra(name, value)");
                            } else if (second11 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (Parcelable) second11), "putExtra(name, value)");
                            } else if (second11 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (Serializable) second11), "putExtra(name, value)");
                            } else if (second11 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (Serializable) second11), "putExtra(name, value)");
                            } else if (second11 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (Serializable) second11), "putExtra(name, value)");
                            } else if (second11 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (boolean[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (byte[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (short[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (char[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (int[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (long[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (float[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (double[]) second11), "putExtra(name, value)");
                            } else if (second11 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (Bundle) second11), "putExtra(name, value)");
                            } else if (second11 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent11.putExtra(str11, (Parcelable) second11), "putExtra(name, value)");
                            } else {
                                Unit unit61 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit62 = Unit.INSTANCE;
                    Unit unit63 = Unit.INSTANCE;
                    Unit unit64 = Unit.INSTANCE;
                    healthyFragment11.startActivity(intent11);
                    Unit unit65 = Unit.INSTANCE;
                    Unit unit66 = Unit.INSTANCE;
                    break;
                }
                break;
            case 14:
                HealthyFragment healthyFragment12 = this$0;
                FragmentActivity it12 = healthyFragment12.getActivity();
                if (it12 != null) {
                    ArrayList<Pair> arrayList12 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it12, "it");
                    Intent intent12 = new Intent(it12, (Class<?>) RingPressureActivity.class);
                    for (Pair pair12 : arrayList12) {
                        if (pair12 != null) {
                            String str12 = (String) pair12.getFirst();
                            Object second12 = pair12.getSecond();
                            if (second12 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Number) second12).intValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Number) second12).byteValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Character) second12).charValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Number) second12).shortValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Boolean) second12).booleanValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Number) second12).longValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Number) second12).floatValue()), "putExtra(name, value)");
                            } else if (second12 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, ((Number) second12).doubleValue()), "putExtra(name, value)");
                            } else if (second12 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (String) second12), "putExtra(name, value)");
                            } else if (second12 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (CharSequence) second12), "putExtra(name, value)");
                            } else if (second12 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (Parcelable) second12), "putExtra(name, value)");
                            } else if (second12 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (Serializable) second12), "putExtra(name, value)");
                            } else if (second12 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (Serializable) second12), "putExtra(name, value)");
                            } else if (second12 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (Serializable) second12), "putExtra(name, value)");
                            } else if (second12 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (boolean[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (byte[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (short[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (char[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (int[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (long[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (float[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (double[]) second12), "putExtra(name, value)");
                            } else if (second12 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (Bundle) second12), "putExtra(name, value)");
                            } else if (second12 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent12.putExtra(str12, (Parcelable) second12), "putExtra(name, value)");
                            } else {
                                Unit unit67 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit68 = Unit.INSTANCE;
                    Unit unit69 = Unit.INSTANCE;
                    Unit unit70 = Unit.INSTANCE;
                    healthyFragment12.startActivity(intent12);
                    Unit unit71 = Unit.INSTANCE;
                    Unit unit72 = Unit.INSTANCE;
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-17, reason: not valid java name */
    public static final void m712loadDataOnce$lambda17(HealthyFragment this$0, MeasureBean measureBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        measureBean.getStart();
        if (measureBean.getType() == 3) {
            if (this$0.sportRunning) {
                Bundle bundle = new Bundle();
                bundle.putInt("sportType", this$0.sportType);
                bundle.putInt("sportStatus", this$0.sportStatus);
                bundle.putInt(FitnessActivities.RUNNING, -1);
                this$0.sportRunning = false;
                HealthyFragment healthyFragment = this$0;
                FragmentActivity it = healthyFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) SportRunningActivity.class);
                    intent.setFlags(1);
                    intent.putExtras(bundle);
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
                    healthyFragment.startActivity(intent);
                    return;
                }
                return;
            }
            this$0.sportRunning = false;
            HealthyFragment healthyFragment2 = this$0;
            FragmentActivity it2 = healthyFragment2.getActivity();
            if (it2 != null) {
                ArrayList<Pair> arrayList2 = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                Intent intent2 = new Intent(it2, (Class<?>) SportGoActivity.class);
                for (Pair pair2 : arrayList2) {
                    if (pair2 != null) {
                        String str2 = (String) pair2.getFirst();
                        Object second2 = pair2.getSecond();
                        if (second2 instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                        } else if (second2 instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                        } else if (second2 instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
                healthyFragment2.startActivity(intent2);
            }
        }
    }

    private final void startSyncData() {
        FragmentHealthyBinding fragmentHealthyBinding = null;
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        FragmentHealthyBinding fragmentHealthyBinding2 = this.binding;
        if (fragmentHealthyBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHealthyBinding = fragmentHealthyBinding2;
        }
        if (fragmentHealthyBinding.syncRefresh.isRefreshing()) {
            return;
        }
        SyncStatus.INSTANCE.getGetInstance().setSync(true);
        fragmentHealthyBinding.syncRefresh.autoRefresh();
    }

    public final void refreshAiAnalyze() {
        if (this.tvAiAnalyzeValue != null) {
            TextView textView = null;
            if (UserConfig.INSTANCE.getInstance().getSupportAiAnalyze()) {
                ConstraintLayout constraintLayout = this.clsAiAnalyze;
                if (constraintLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("clsAiAnalyze");
                    constraintLayout = null;
                }
                ViewKt.visible(constraintLayout);
            } else {
                ConstraintLayout constraintLayout2 = this.clsAiAnalyze;
                if (constraintLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("clsAiAnalyze");
                    constraintLayout2 = null;
                }
                ViewKt.gone(constraintLayout2);
            }
            if (BleOperateManager.getInstance().isConnected()) {
                if (UserConfig.INSTANCE.getInstance().getLastAiAnalysisScore() != 0) {
                    TextView textView2 = this.tvAiAnalyzeValue;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tvAiAnalyzeValue");
                    } else {
                        textView = textView2;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(UserConfig.INSTANCE.getInstance().getLastAiAnalysisScore());
                    sb.append('%');
                    textView.setText(sb.toString());
                    return;
                }
                TextView textView3 = this.tvAiAnalyzeValue;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvAiAnalyzeValue");
                } else {
                    textView = textView3;
                }
                textView.setText("--");
                return;
            }
            TextView textView4 = this.tvAiAnalyzeValue;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvAiAnalyzeValue");
            } else {
                textView = textView4;
            }
            textView.setText("--");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) throws NumberFormatException {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        boolean z = messageEvent instanceof RefreshEvent;
        if (z && !Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            this.handler.removeCallbacks(this.tenSecondsRefreshRunnable);
        }
        ImageView imageView = null;
        if (z && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            this.handler.removeCallbacks(this.tenSecondsRefreshRunnable);
            this.handler.postDelayed(this.tenSecondsRefreshRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
            if (BleOperateManager.getInstance().isConnected()) {
                ImageView imageView2 = this.bleStatus;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
                    imageView2 = null;
                }
                imageView2.setImageResource(R.mipmap.ble_connect);
                healthyModule();
            } else {
                ImageView imageView3 = this.bleStatus;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
                    imageView3 = null;
                }
                imageView3.setImageResource(R.mipmap.ble_disconnect);
                UserConfig.INSTANCE.getInstance().setLastSyncTodaySteps(0L);
                UserConfig.INSTANCE.getInstance().save();
            }
        }
        if (messageEvent instanceof RefreshAiAnalyzeEvent) {
            refreshAiAnalyze();
        }
        if (messageEvent instanceof HealthItemRefreshEvent) {
            healthyModule();
            return;
        }
        if (messageEvent instanceof BluetoothSyncEvent) {
            startSyncData();
            refreshAiAnalyze();
            return;
        }
        if (messageEvent instanceof DataSyncEvent) {
            refreshAiAnalyze();
            return;
        }
        if (messageEvent instanceof BluetoothEvent) {
            if (!((BluetoothEvent) messageEvent).getConnect()) {
                FileHandle.getInstance().clearCallback();
                FragmentHealthyBinding fragmentHealthyBinding = this.binding;
                if (fragmentHealthyBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHealthyBinding = null;
                }
                fragmentHealthyBinding.syncRefresh.finishRefresh();
                ImageView imageView4 = this.bleStatus;
                if (imageView4 != null) {
                    if (imageView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
                    } else {
                        imageView = imageView4;
                    }
                    imageView.setImageResource(R.mipmap.ble_disconnect);
                    UserConfig.INSTANCE.getInstance().setLastSyncTodaySteps(0L);
                    UserConfig.INSTANCE.getInstance().save();
                    return;
                }
                return;
            }
            ImageView imageView5 = this.bleStatus;
            if (imageView5 != null) {
                if (imageView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleStatus");
                } else {
                    imageView = imageView5;
                }
                imageView.setImageResource(R.mipmap.ble_connect);
                return;
            }
            return;
        }
        if (messageEvent instanceof ManualRefreshEvent ? true : messageEvent instanceof TodayDataSyncEvent) {
            XLog.i("---ManualRefreshEvent");
            healthyModule();
            return;
        }
        if (messageEvent instanceof WatchFaceDownloadEvent) {
            if (!UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                if (UserConfig.INSTANCE.getInstance().getUserToken().length() == 0) {
                    getViewModel().getTokenNotLogin();
                }
            }
            getViewModel().checkWatchFaceDownload(((WatchFaceDownloadEvent) messageEvent).getHardwareVersion());
            return;
        }
        try {
            if (messageEvent instanceof DeviceSyncTodayStepsEvent) {
                if (getActivity() instanceof MainActivity) {
                    getViewModel().syncTodayStepNotification();
                    return;
                } else if (UserConfig.INSTANCE.getInstance().getLastSyncTodaySteps() < new DateUtil().getUnixTimestamp()) {
                    XLog.i("三分钟限制");
                    return;
                } else {
                    getViewModel().syncTodayStepNotification();
                    return;
                }
            }
            if (messageEvent instanceof DeviceSyncTodayStepsAndDetailEvent) {
                getViewModel().queryStepAndDetail();
                return;
            }
            if (messageEvent instanceof UnbindDeviceEvent) {
                getViewModel().queryStepAndDetail();
                return;
            }
            if ((messageEvent instanceof BatteryLowEvent) && UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
                try {
                    String battery = UserConfig.INSTANCE.getInstance().getBattery();
                    if (battery != null) {
                        int i = Integer.parseInt(battery);
                        if (i < 0 || i >= 11) {
                            z = false;
                        }
                        if (z) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass3(null), 3, null);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception unused) {
        }
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$onMessageEvent$3", f = "HealthyFragment.kt", i = {0}, l = {727, 728}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$onMessageEvent$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = HealthyFragment.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final HealthyFragment healthyFragment = HealthyFragment.this;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.onMessageEvent.3.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null && deviceSetting.getBatteryWarming() && !UserConfig.INSTANCE.getInstance().getBatteryLow()) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final HealthyFragment healthyFragment2 = healthyFragment;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.onMessageEvent.3.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                healthyFragment2.showRingBatteryLowDialog();
                            }
                        });
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$TenSecondsRefreshRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TenSecondsRefreshRunnable implements Runnable {
        public TenSecondsRefreshRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (HealthyFragment.this.isVisible() && BleOperateManager.getInstance().isConnected()) {
                XLog.i("---10秒同步--");
                HealthyFragment.this.handler.postDelayed(HealthyFragment.this.tenSecondsRefreshRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
                HealthyFragment.this.tenSecondsRefresh();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tenSecondsRefresh() {
        try {
            getViewModel().syncTodayStepNotification();
        } catch (Exception unused) {
        }
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$healthyModule$1", f = "HealthyFragment.kt", i = {0}, l = {769, 770}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$healthyModule$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = HealthyFragment.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = DeviceSupportRepository.INSTANCE.getGetInstance().getDeviceSupport(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final HealthyFragment healthyFragment = HealthyFragment.this;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.healthyModule.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Map<Integer, ItemEntity>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(final Map<Integer, ItemEntity> map, Continuation<? super Unit> continuation) {
                    CoroutineScope coroutineScope2 = coroutineScope;
                    final HealthyFragment healthyFragment2 = healthyFragment;
                    ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.healthyModule.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                            invoke2(coroutineScope3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CoroutineScope ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            healthyFragment2.getViewModel().getItemList().clear();
                            Map<Integer, ItemEntity> map2 = map;
                            if (map2 == null || map2.isEmpty()) {
                                HealthyViewModel viewModel = healthyFragment2.getViewModel();
                                viewModel.getItemList().clear();
                                if (!UserConfig.INSTANCE.getInstance().getSleepSupport()) {
                                    viewModel.getItemList().add(new LastSleepItem());
                                }
                                if (!UserConfig.INSTANCE.getInstance().getHeartSupport()) {
                                    viewModel.getItemList().add(new LastHeartItem());
                                }
                                viewModel.getItemList().add(new LastSportItem());
                            } else {
                                Map<Integer, ItemEntity> map3 = map;
                                HealthyFragment healthyFragment3 = healthyFragment2;
                                for (Map.Entry<Integer, ItemEntity> entry : map3.entrySet()) {
                                    if (entry.getValue().isChecked()) {
                                        switch (entry.getValue().getModelType()) {
                                            case 1:
                                                healthyFragment3.getViewModel().getItemList().add(new LastHeartItem());
                                                break;
                                            case 2:
                                                healthyFragment3.getViewModel().getItemList().add(new LastSleepItem());
                                                break;
                                            case 3:
                                                healthyFragment3.getViewModel().getItemList().add(new LastSportItem());
                                                break;
                                            case 4:
                                                healthyFragment3.getViewModel().getItemList().add(new LastBloodOxygenItem());
                                                break;
                                            case 5:
                                                if (entry.getValue().getShowOrNot()) {
                                                    healthyFragment3.getViewModel().getItemList().add(new LastMenstruationItem());
                                                    break;
                                                } else {
                                                    break;
                                                }
                                            case 6:
                                                healthyFragment3.getViewModel().getItemList().add(new LastBodyTemperatureItem());
                                                break;
                                            case 7:
                                                healthyFragment3.getViewModel().getItemList().add(new LastGpsSportItem());
                                                break;
                                            case 9:
                                                healthyFragment3.getViewModel().getItemList().add(new LastBloodPressureItem());
                                                break;
                                            case 10:
                                                healthyFragment3.getViewModel().getItemList().add(new LastOneKeyItem());
                                                break;
                                            case 12:
                                                healthyFragment3.getViewModel().getItemList().add(new LastBloodSugarItem());
                                                break;
                                            case 13:
                                                healthyFragment3.getViewModel().getItemList().add(new LastHrvItem());
                                                break;
                                            case 14:
                                                healthyFragment3.getViewModel().getItemList().add(new LastPressureItem());
                                                break;
                                        }
                                    }
                                }
                            }
                            MultipleItemQuickAdapter multipleItemQuickAdapter = healthyFragment2.healthyAdapter;
                            if (multipleItemQuickAdapter == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("healthyAdapter");
                                multipleItemQuickAdapter = null;
                            }
                            multipleItemQuickAdapter.notifyDataSetChanged();
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    private final void healthyModule() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SyncStatus.INSTANCE.getGetInstance().setSync(false);
            FragmentHealthyBinding fragmentHealthyBinding = HealthyFragment.this.binding;
            FragmentHealthyBinding fragmentHealthyBinding2 = null;
            if (fragmentHealthyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHealthyBinding = null;
            }
            if (fragmentHealthyBinding.syncRefresh.isRefreshing()) {
                FragmentHealthyBinding fragmentHealthyBinding3 = HealthyFragment.this.binding;
                if (fragmentHealthyBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHealthyBinding2 = fragmentHealthyBinding3;
                }
                fragmentHealthyBinding2.syncRefresh.finishRefresh();
            }
            if (UserConfig.INSTANCE.getInstance().getNotificationGoTo()) {
                return;
            }
            Object systemService = HealthyFragment.this.getActivity().getSystemService("notification");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager notificationManager = (NotificationManager) systemService;
            if (Build.VERSION.SDK_INT < 24 || notificationManager.areNotificationsEnabled()) {
                return;
            }
            HealthyFragment healthyFragment = HealthyFragment.this;
            healthyFragment.openNotificationSettings(healthyFragment.getActivity());
            UserConfig.INSTANCE.getInstance().setNotificationGoTo(true);
            UserConfig.INSTANCE.getInstance().save();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openNotificationSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        context.startActivity(intent);
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HealthyFragment newInstance() {
            return new HealthyFragment();
        }
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$LocationPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LocationPermissionCallback implements OnPermissionCallback {
        public LocationPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (!all) {
                String string = HealthyFragment.this.getString(R.string.qc_text_269);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_269)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
            HealthyFragment healthyFragment = HealthyFragment.this;
            FragmentActivity it = healthyFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) GpsActivity.class);
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
                healthyFragment.startActivity(intent);
            }
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity(HealthyFragment.this.getActivity(), permissions);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    private final void showLocationWarningDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_location_warning);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthyFragment.m720showLocationWarningDialog$lambda20(objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                HealthyFragment.m721showLocationWarningDialog$lambda21(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showLocationWarningDialog$lambda-20, reason: not valid java name */
    public static final void m720showLocationWarningDialog$lambda20(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showLocationWarningDialog$lambda-21, reason: not valid java name */
    public static final void m721showLocationWarningDialog$lambda21(HealthyFragment this$0, Ref.ObjectRef bottomDialog, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        if (!StringsKt.equals(Build.BRAND, "oppo", true) || !AndroidVersion.isAndroid12()) {
            PermissionUtilKt.requestLocationPermission((FragmentActivity) this$0.getActivity(), this$0.new LocationPermissionCallback());
        }
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    private final boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        Object systemService = getActivity().getSystemService("power");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(getActivity().getPackageName());
    }

    private final void showNotificationDialog() {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_notification);
        final CenterDialog centerDialogCreate = builder.create();
        centerDialogCreate.show();
        View contentView = centerDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthyFragment.m722showNotificationDialog$lambda22(centerDialogCreate, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showNotificationDialog$lambda-22, reason: not valid java name */
    public static final void m722showNotificationDialog$lambda22(CenterDialog centerDialog, HealthyFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        centerDialog.dismiss();
        try {
            this$0.startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final boolean isNotificationListenerEnabled() {
        String packageName = QCApplication.INSTANCE.getCONTEXT().getPackageName();
        String flat = Settings.Secure.getString(QCApplication.INSTANCE.getCONTEXT().getContentResolver(), "enabled_notification_listeners");
        String str = flat;
        if (!TextUtils.isEmpty(str)) {
            Intrinsics.checkNotNullExpressionValue(flat, "flat");
            Object[] array = new Regex(":").split(str, 0).toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            for (String str2 : (String[]) array) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str2);
                if (componentNameUnflattenFromString != null && TextUtils.equals(packageName, componentNameUnflattenFromString.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        HealthyFragment healthyFragment;
        FragmentActivity it;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && isNotificationListenerEnabled() && (it = (healthyFragment = this).getActivity()) != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) MessagePushActivity.class);
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
            healthyFragment.startActivity(intent);
        }
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$MySportDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceSportNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MySportDeviceNotifyListener extends DeviceSportNotifyListener {
        public MySportDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceSportNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) {
            super.onDataResponse(resultEntity);
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0) {
                HealthyFragment.this.sportRunning = true;
                HealthyFragment.this.sportType = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[0]});
                HealthyFragment.this.sportStatus = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[1]});
                XLog.i(Integer.valueOf(HealthyFragment.this.sportType));
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeOutDeviceListener(100);
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: HealthyFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) throws Throwable {
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0) {
                if (SyncStatus.INSTANCE.getGetInstance().getSync() || SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus() || QCApplication.INSTANCE.getGetInstance().getUpdating() == 1 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 2 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 3) {
                    XLog.i(Integer.valueOf(QCApplication.INSTANCE.getGetInstance().getUpdating()));
                    XLog.i(Boolean.valueOf(SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()));
                    LogToFile.getInstance().wtf("设备刷新返回，没有同步");
                    XLog.i("正在数据同步或者固件升级...");
                }
                XLog.i("设备触发刷新，数据类型:" + resultEntity.getDataType());
                LogToFile.getInstance().wtf("设备触发刷新，数据类型:" + resultEntity.getDataType());
                BleOperateManager.getInstance().removeOthersListener();
                switch (resultEntity.getDataType()) {
                    case 1:
                        HealthyFragment.this.getViewModel().syncTodayHeartSingleData();
                        break;
                    case 2:
                        HealthyFragment.this.getViewModel().syncBpSingle();
                        break;
                    case 3:
                        HealthyFragment.this.getViewModel().syncTodaySpo2Single(0);
                        break;
                    case 4:
                        HealthyFragment.this.getViewModel().syncTodayStepDetailSingle();
                        break;
                    case 5:
                        HealthyFragment.this.getViewModel().syncTodayTemperatureSingle();
                        break;
                    case 7:
                        HealthyFragment.this.sportRunning = false;
                        HealthyFragment.this.sportType = 0;
                        HealthyFragment.this.sportStatus = 0;
                        HealthyFragment.this.getViewModel().syncTodaySportPlusDetailSingle();
                        break;
                    case 9:
                    case 11:
                        EventBus.getDefault().post(new DeviceToAppSyncEvent(resultEntity.getDataType()));
                        break;
                    case 12:
                        try {
                            CommandHandle commandHandle = CommandHandle.getInstance();
                            SimpleKeyReq simpleKeyReq = new SimpleKeyReq((byte) 3);
                            final HealthyFragment healthyFragment = HealthyFragment.this;
                            commandHandle.executeReqCmd(simpleKeyReq, new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$MyDeviceNotifyListener$$ExternalSyntheticLambda1
                                @Override // com.oudmon.ble.base.communication.ICommandResponse
                                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                                    HealthyFragment.MyDeviceNotifyListener.m725onDataResponse$lambda0(healthyFragment, (BatteryRsp) baseRspCmd);
                                }
                            });
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    case 13:
                        HealthyFragment.this.getViewModel().syncTodayBloodSugar(0);
                        break;
                    case 16:
                        CommandHandle.getInstance().executeReqCmd(TargetSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$MyDeviceNotifyListener$$ExternalSyntheticLambda0
                            @Override // com.oudmon.ble.base.communication.ICommandResponse
                            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                                HealthyFragment.MyDeviceNotifyListener.m726onDataResponse$lambda1(this.f$0, (TargetSettingRsp) baseRspCmd);
                            }
                        });
                        break;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onDataResponse$lambda-0, reason: not valid java name */
        public static final void m725onDataResponse$lambda0(HealthyFragment this$0, BatteryRsp batteryRsp) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            UserConfig.INSTANCE.getInstance().setBattery(String.valueOf(batteryRsp.getBatteryValue()));
            UserConfig.INSTANCE.getInstance().save();
            if (batteryRsp.getBatteryValue() <= 10) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1(this$0, null), 3, null);
            } else {
                UserConfig.INSTANCE.getInstance().setBatteryLow(false);
                NotificationUtils.lowBatteryNotification = false;
                UserConfig.INSTANCE.getInstance().save();
            }
            EventBus.getDefault().post(new FirmUpdateEvent());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onDataResponse$lambda-1, reason: not valid java name */
        public static final void m726onDataResponse$lambda1(MyDeviceNotifyListener this$0, final TargetSettingRsp targetSettingRsp) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            XLog.i(targetSettingRsp);
            if (targetSettingRsp.getAction() == 1) {
                ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<MyDeviceNotifyListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$MyDeviceNotifyListener$onDataResponse$2$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HealthyFragment.MyDeviceNotifyListener myDeviceNotifyListener) {
                        invoke2(myDeviceNotifyListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HealthyFragment.MyDeviceNotifyListener ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        if (targetSettingRsp.getStep() > 0) {
                            UserProfileRepository.INSTANCE.getGetInstance().insertTarget(new TargetEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), targetSettingRsp.getStep(), targetSettingRsp.getCalorie() / 1000.0f, targetSettingRsp.getDistance() / 1000.0f, 3.0f, 8.0f));
                            EventBus.getDefault().post(new DeviceNotifyTypeEvent(16));
                        }
                    }
                });
            }
        }
    }
}
