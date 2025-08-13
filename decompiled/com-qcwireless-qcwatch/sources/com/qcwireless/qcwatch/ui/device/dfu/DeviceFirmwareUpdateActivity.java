package com.qcwireless.qcwatch.ui.device.dfu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.view.KeyEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.work.WorkRequest;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.DfuHandle;
import com.qcwireless.qc_utils.app.AppUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.FirmUpdateEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.OTAFileStatusEvent;
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityDeviceFirmwareUpdateBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DeviceFirmwareUpdateActivity.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u0012\u0010\u001d\u001a\u00020\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u001bH\u0014J\u001a\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)H\u0017J\b\u0010*\u001a\u00020\u001bH\u0014J\u0006\u0010+\u001a\u00020\u001bJ\b\u0010,\u001a\u00020\u001bH\u0015J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\b\u0018\u00010\u0018R\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/DeviceFirmwareUpdateActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "FLAG_HOMEKEY_DISPATCHED", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDeviceFirmwareUpdateBinding;", "dfuHandle", "Lcom/oudmon/ble/base/communication/DfuHandle;", "dfuOpResult", "Lcom/oudmon/ble/base/communication/DfuHandle$IOpResult;", "handler", "Landroid/os/Handler;", "progressValue", "", "runnable", "Lcom/qcwireless/qcwatch/ui/device/dfu/DeviceFirmwareUpdateActivity$MyRunnable;", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "appDisconnect", "", "onAttachedToWindow", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "", "keyCode", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "otaFail", "setupViews", "startOta", "name", "MyRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceFirmwareUpdateActivity extends BaseActivity {
    private final long FLAG_HOMEKEY_DISPATCHED = 2147483648L;
    private ActivityDeviceFirmwareUpdateBinding binding;
    private DfuHandle dfuHandle;
    private final DfuHandle.IOpResult dfuOpResult;
    private final Handler handler;
    private String progressValue;
    private MyRunnable runnable;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private PowerManager.WakeLock wakeLock;

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceFirmwareUpdateActivity() {
        final DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<DeviceFirmwareUpdateViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceFirmwareUpdateViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(deviceFirmwareUpdateActivity, Reflection.getOrCreateKotlinClass(DeviceFirmwareUpdateViewModel.class), qualifier, objArr);
            }
        });
        this.runnable = new MyRunnable();
        this.progressValue = "0";
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
        this.dfuOpResult = new DeviceFirmwareUpdateActivity$dfuOpResult$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceFirmwareUpdateViewModel getViewModel() {
        return (DeviceFirmwareUpdateViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBindingInflate = ActivityDeviceFirmwareUpdateBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDeviceFirmwareUpdateBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDeviceFirmwareUpdateBindingInflate;
        if (activityDeviceFirmwareUpdateBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateBindingInflate = null;
        }
        ConstraintLayout root = activityDeviceFirmwareUpdateBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        getWindow().addFlags(128);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        DfuHandle dfuHandle = DfuHandle.getInstance();
        Intrinsics.checkNotNullExpressionValue(dfuHandle, "getInstance()");
        this.dfuHandle = dfuHandle;
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding = null;
        if (dfuHandle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
            dfuHandle = null;
        }
        dfuHandle.initCallback();
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding2 = this.binding;
        if (activityDeviceFirmwareUpdateBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceFirmwareUpdateBinding = activityDeviceFirmwareUpdateBinding2;
        }
        activityDeviceFirmwareUpdateBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_29));
        ViewKt.gone(activityDeviceFirmwareUpdateBinding.titleBar.ivNavigateBefore);
        DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity = this;
        if (SystemServiceExtKt.getPowerManager(deviceFirmwareUpdateActivity) != null) {
            PowerManager powerManager = SystemServiceExtKt.getPowerManager(deviceFirmwareUpdateActivity);
            Intrinsics.checkNotNull(powerManager);
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(536870913, "DeviceFirmwareUpdateActivity");
            this.wakeLock = wakeLockNewWakeLock;
            if (wakeLockNewWakeLock != null) {
                wakeLockNewWakeLock.acquire(600000L);
            }
        }
        this.handler.removeCallbacks(this.runnable);
        this.handler.postDelayed(this.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
        this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DeviceFirmwareUpdateActivity.m430setupViews$lambda1(this.f$0);
            }
        }, 200L);
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DeviceFirmwareUpdateActivity.m431setupViews$lambda2(this.f$0, (DeviceFirmwareUpdateViewModel.FirmwareUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m430setupViews$lambda1(DeviceFirmwareUpdateActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().checkOta();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m431setupViews$lambda2(DeviceFirmwareUpdateActivity this$0, DeviceFirmwareUpdateViewModel.FirmwareUI firmwareUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (firmwareUI != null) {
            ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding = this$0.binding;
            ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding2 = null;
            if (activityDeviceFirmwareUpdateBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceFirmwareUpdateBinding = null;
            }
            activityDeviceFirmwareUpdateBinding.tvProgressValue.setText(String.valueOf(firmwareUI.getProgressBar()));
            ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding3 = this$0.binding;
            if (activityDeviceFirmwareUpdateBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceFirmwareUpdateBinding3 = null;
            }
            activityDeviceFirmwareUpdateBinding3.progressValue.setPercentage((int) firmwareUI.getProgressBar());
            this$0.handler.removeCallbacks(this$0.runnable);
            this$0.handler.postDelayed(this$0.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
            ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding4 = this$0.binding;
            if (activityDeviceFirmwareUpdateBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDeviceFirmwareUpdateBinding2 = activityDeviceFirmwareUpdateBinding4;
            }
            activityDeviceFirmwareUpdateBinding2.tvError.setText(this$0.getString(R.string.qc_text_359));
            if (firmwareUI.getSuccess()) {
                XLog.i(firmwareUI.getFileName());
                this$0.startOta(firmwareUI.getFileName());
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof BluetoothEvent) {
            if (((BluetoothEvent) messageEvent).getConnect()) {
                return;
            }
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            this.progressValue = "0";
            this.handler.removeCallbacks(this.runnable);
            getViewModel().cancelTask();
            finish();
            return;
        }
        if (messageEvent instanceof OTAFileStatusEvent) {
            try {
                if (AppUtil.isBackground(this) || AppUtil.isApplicationBroughtToBackground(this)) {
                    this.handler.removeCallbacks(this.runnable);
                    this.handler.postDelayed(this.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
                    if (((OTAFileStatusEvent) messageEvent).getType() == 3 && ((OTAFileStatusEvent) messageEvent).getSuccess()) {
                        startOta(((OTAFileStatusEvent) messageEvent).getFileName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!BleOperateManager.getInstance().isConnected()) {
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            this.progressValue = "0";
            this.handler.removeCallbacks(this.runnable);
            getViewModel().cancelTask();
            finish();
        } else {
            QCApplication.INSTANCE.getGetInstance().setUpdating(1);
        }
        if (NetWorkUtils.INSTANCE.isNetworkAvailable(this)) {
            return;
        }
        String string = getString(R.string.qc_text_223);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* compiled from: DeviceFirmwareUpdateActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/DeviceFirmwareUpdateActivity$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/dfu/DeviceFirmwareUpdateActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DeviceFirmwareUpdateActivity.this.otaFail();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        getWindow().addFlags((int) this.FLAG_HOMEKEY_DISPATCHED);
        super.onAttachedToWindow();
    }

    public final void otaFail() {
        ThreadExtKt.ktxRunOnUi(this, new Function1<DeviceFirmwareUpdateActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity.otaFail.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity) {
                invoke2(deviceFirmwareUpdateActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceFirmwareUpdateActivity ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                String string = ktxRunOnUi.getString(R.string.qc_text_238);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_238)");
                GlobalKt.showToast$default(string, 0, 1, null);
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                ktxRunOnUi.getViewModel().cancelTask();
                ktxRunOnUi.finish();
            }
        });
    }

    private final void startOta(String name) {
        File file = new File(FileUtils.INSTANCE.getBinDirFile(), name);
        if (file.exists()) {
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
            if (absolutePath.length() == 0) {
                otaFail();
            }
            String absolutePath2 = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath2, "file.absolutePath");
            DfuHandle dfuHandle = null;
            if (!StringsKt.contains$default((CharSequence) absolutePath2, (CharSequence) "bin", false, 2, (Object) null)) {
                otaFail();
            }
            if (!Intrinsics.areEqual(file.getAbsolutePath(), "")) {
                DfuHandle dfuHandle2 = this.dfuHandle;
                if (dfuHandle2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
                    dfuHandle2 = null;
                }
                if (dfuHandle2.checkFile(file.getAbsolutePath())) {
                    DfuHandle dfuHandle3 = this.dfuHandle;
                    if (dfuHandle3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
                    } else {
                        dfuHandle = dfuHandle3;
                    }
                    dfuHandle.start(this.dfuOpResult);
                    return;
                }
                return;
            }
            otaFail();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 || keyCode == 3) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public final void appDisconnect() {
        EventBus.getDefault().post(new FirmUpdateEvent());
        if (BleOperateManager.getInstance().isConnected()) {
            BleOperateManager.getInstance().disconnect();
        }
        String string = getString(R.string.qc_text_239);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_239)");
        GlobalKt.showToast$default(string, 0, 1, null);
        this.progressValue = "0";
        getViewModel().cancelTask();
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags((int) this.FLAG_HOMEKEY_DISPATCHED);
        getViewModel().cancelTask();
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null) {
            wakeLock.release();
        }
        this.handler.removeCallbacks(this.runnable);
    }
}
