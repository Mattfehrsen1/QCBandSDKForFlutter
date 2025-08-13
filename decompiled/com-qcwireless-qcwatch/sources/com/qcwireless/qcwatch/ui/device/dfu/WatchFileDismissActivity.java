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
import com.qcwireless.qc_utils.app.AppUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.OTAFileStatusEvent;
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityWatchFileDismissBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.response.device.DeviceMissFileResp;
import com.qcwireless.qcwatch.ui.base.repository.device.OTAFileStatus;
import com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.squareup.moshi.Types;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WatchFileDismissActivity.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u001a\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!H\u0017J\b\u0010\"\u001a\u00020\u0017H\u0014J\b\u0010#\u001a\u00020\u0017H\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\b\u0018\u00010\u0014R\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/WatchFileDismissActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityWatchFileDismissBinding;", "downloading", "", "handler", "Landroid/os/Handler;", "progressValue", "", "runnable", "Lcom/qcwireless/qcwatch/ui/device/dfu/WatchFileDismissActivity$MyRunnable;", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "MyRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFileDismissActivity extends BaseActivity {
    private ActivityWatchFileDismissBinding binding;
    private boolean downloading;
    private final Handler handler;
    private String progressValue;
    private MyRunnable runnable;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private PowerManager.WakeLock wakeLock;

    /* JADX WARN: Multi-variable type inference failed */
    public WatchFileDismissActivity() {
        final WatchFileDismissActivity watchFileDismissActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WatchFileDismissViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WatchFileDismissViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(watchFileDismissActivity, Reflection.getOrCreateKotlinClass(WatchFileDismissViewModel.class), qualifier, objArr);
            }
        });
        this.progressValue = "0";
        this.runnable = new MyRunnable();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WatchFileDismissViewModel getViewModel() {
        return (WatchFileDismissViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWatchFileDismissBinding activityWatchFileDismissBindingInflate = ActivityWatchFileDismissBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWatchFileDismissBindingInflate, "inflate(layoutInflater)");
        this.binding = activityWatchFileDismissBindingInflate;
        if (activityWatchFileDismissBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFileDismissBindingInflate = null;
        }
        ConstraintLayout root = activityWatchFileDismissBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        getWindow().addFlags(128);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        ArrayList arrayList;
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        ActivityWatchFileDismissBinding activityWatchFileDismissBinding = null;
        String string = extras != null ? extras.getString("fileNames") : null;
        Bundle extras2 = getIntent().getExtras();
        Integer numValueOf = extras2 != null ? Integer.valueOf(extras2.getInt("background")) : null;
        if (string != null) {
            MoshiUtils moshiUtils = MoshiUtils.INSTANCE;
            ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, String.class);
            Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
            arrayList = (List) moshiUtils.fromJson(string, parameterizedTypeNewParameterizedType);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
        } else {
            arrayList = null;
        }
        XLog.i(arrayList);
        if (arrayList != null && arrayList.size() > 0 && ((numValueOf == null || numValueOf.intValue() != 1) && !this.downloading)) {
            XLog.i("--------下载UI文件数据");
            this.downloading = true;
            getViewModel().downloadFile((ArrayList) arrayList);
        }
        if (numValueOf != null && numValueOf.intValue() == 1) {
            getViewModel().initFileHandler();
        }
        ActivityWatchFileDismissBinding activityWatchFileDismissBinding2 = this.binding;
        if (activityWatchFileDismissBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFileDismissBinding = activityWatchFileDismissBinding2;
        }
        activityWatchFileDismissBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_294));
        ViewKt.gone(activityWatchFileDismissBinding.titleBar.ivNavigateBefore);
        WatchFileDismissActivity watchFileDismissActivity = this;
        if (SystemServiceExtKt.getPowerManager(watchFileDismissActivity) != null) {
            PowerManager powerManager = SystemServiceExtKt.getPowerManager(watchFileDismissActivity);
            Intrinsics.checkNotNull(powerManager);
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(536870913, "DeviceFirmwareUpdateActivity");
            this.wakeLock = wakeLockNewWakeLock;
            if (wakeLockNewWakeLock != null) {
                wakeLockNewWakeLock.acquire(600000L);
            }
        }
        this.handler.removeCallbacks(this.runnable);
        this.handler.postDelayed(this.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
        WatchFileDismissActivity watchFileDismissActivity2 = this;
        getViewModel().getUiState().observe(watchFileDismissActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFileDismissActivity.m437setupViews$lambda3(this.f$0, (OTAFileStatus) obj);
            }
        });
        getViewModel().getDismissFileState().observe(watchFileDismissActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFileDismissActivity.m439setupViews$lambda4(this.f$0, (WatchFileDismissViewModel.DismissFileFirmwareUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m437setupViews$lambda3(final WatchFileDismissActivity this$0, OTAFileStatus oTAFileStatus) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (oTAFileStatus.getOtaSuccess()) {
            this$0.handler.removeCallbacks(this$0.runnable);
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            String string = this$0.getString(R.string.qc_text_239);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_239)");
            GlobalKt.showToast$default(string, 0, 1, null);
            this$0.getViewModel().cancelTask();
            this$0.finish();
        } else {
            this$0.handler.removeCallbacks(this$0.runnable);
            this$0.handler.postDelayed(this$0.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
            this$0.progressValue = String.valueOf(oTAFileStatus.getProgressBar());
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding = this$0.binding;
            if (activityWatchFileDismissBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFileDismissBinding = null;
            }
            activityWatchFileDismissBinding.tvProgressValue.setText(String.valueOf(oTAFileStatus.getProgressBar()));
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding2 = this$0.binding;
            if (activityWatchFileDismissBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFileDismissBinding2 = null;
            }
            activityWatchFileDismissBinding2.progressValue.setPercentage(oTAFileStatus.getProgressBar());
            QCApplication.INSTANCE.getGetInstance().setUpdating(2);
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding3 = this$0.binding;
            if (activityWatchFileDismissBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFileDismissBinding3 = null;
            }
            activityWatchFileDismissBinding3.tvError.setText(this$0.getString(R.string.qc_text_360));
        }
        if (oTAFileStatus.getFileExists()) {
            return;
        }
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        String string2 = this$0.getString(R.string.qc_text_240);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_240)");
        GlobalKt.showToast$default(string2, 0, 1, null);
        this$0.handler.removeCallbacks(this$0.runnable);
        this$0.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                WatchFileDismissActivity.m438setupViews$lambda3$lambda2(this.f$0);
            }
        }, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m438setupViews$lambda3$lambda2(WatchFileDismissActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().cancelTask();
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m439setupViews$lambda4(WatchFileDismissActivity this$0, WatchFileDismissViewModel.DismissFileFirmwareUI dismissFileFirmwareUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (dismissFileFirmwareUI != null) {
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding = this$0.binding;
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding2 = null;
            if (activityWatchFileDismissBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFileDismissBinding = null;
            }
            activityWatchFileDismissBinding.tvProgressValue.setText(String.valueOf(dismissFileFirmwareUI.getProgressBar()));
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding3 = this$0.binding;
            if (activityWatchFileDismissBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFileDismissBinding3 = null;
            }
            activityWatchFileDismissBinding3.progressValue.setPercentage((int) dismissFileFirmwareUI.getProgressBar());
            this$0.handler.removeCallbacks(this$0.runnable);
            this$0.handler.postDelayed(this$0.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding4 = this$0.binding;
            if (activityWatchFileDismissBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFileDismissBinding2 = activityWatchFileDismissBinding4;
            }
            activityWatchFileDismissBinding2.tvError.setText(this$0.getString(R.string.qc_text_359));
            if (dismissFileFirmwareUI.getSuccess()) {
                XLog.i(dismissFileFirmwareUI.getFileName());
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DeviceMissFileResp(dismissFileFirmwareUI.getFileName(), "", "", "", null, 16, null));
                dismissFileFirmwareUI.getFileName();
                this$0.getViewModel().startDismissFileOTA(arrayList);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 || keyCode == 3) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!BleOperateManager.getInstance().isConnected()) {
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            String string = getString(R.string.qc_text_238);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_238)");
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding = null;
            GlobalKt.showToast$default(string, 0, 1, null);
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding2 = this.binding;
            if (activityWatchFileDismissBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFileDismissBinding = activityWatchFileDismissBinding2;
            }
            activityWatchFileDismissBinding.tvError.setText(getString(R.string.qc_text_238));
            getViewModel().cancelTask();
            finish();
            return;
        }
        QCApplication.INSTANCE.getGetInstance().setUpdating(2);
    }

    /* compiled from: WatchFileDismissActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/WatchFileDismissActivity$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/dfu/WatchFileDismissActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = WatchFileDismissActivity.this.getString(R.string.qc_text_238);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_238)");
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding = null;
            GlobalKt.showToast$default(string, 0, 1, null);
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding2 = WatchFileDismissActivity.this.binding;
            if (activityWatchFileDismissBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFileDismissBinding = activityWatchFileDismissBinding2;
            }
            activityWatchFileDismissBinding.tvError.setText(WatchFileDismissActivity.this.getString(R.string.qc_text_238));
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            WatchFileDismissActivity.this.getViewModel().cancelTask();
            WatchFileDismissActivity.this.finish();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        ActivityWatchFileDismissBinding activityWatchFileDismissBinding = null;
        if (messageEvent instanceof BluetoothEvent) {
            if (((BluetoothEvent) messageEvent).getConnect()) {
                return;
            }
            this.handler.removeCallbacks(this.runnable);
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            String string = getString(R.string.qc_text_238);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_238)");
            GlobalKt.showToast$default(string, 0, 1, null);
            ActivityWatchFileDismissBinding activityWatchFileDismissBinding2 = this.binding;
            if (activityWatchFileDismissBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFileDismissBinding = activityWatchFileDismissBinding2;
            }
            activityWatchFileDismissBinding.tvError.setText(getString(R.string.qc_text_238));
            getViewModel().cancelTask();
            finish();
            return;
        }
        if (messageEvent instanceof OTAFileStatusEvent) {
            try {
                if (AppUtil.isBackground(this) || AppUtil.isApplicationBroughtToBackground(this)) {
                    this.handler.removeCallbacks(this.runnable);
                    this.handler.postDelayed(this.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
                    if (((OTAFileStatusEvent) messageEvent).getType() == 2 && ((OTAFileStatusEvent) messageEvent).getSuccess()) {
                        this.handler.removeCallbacks(this.runnable);
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        String string2 = getString(R.string.qc_text_239);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_239)");
                        GlobalKt.showToast$default(string2, 0, 1, null);
                        getViewModel().cancelTask();
                        finish();
                    } else if (((OTAFileStatusEvent) messageEvent).getType() == 1 && ((OTAFileStatusEvent) messageEvent).getSuccess()) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new DeviceMissFileResp(((OTAFileStatusEvent) messageEvent).getFileName(), "", "", "", null, 16, null));
                        ((OTAFileStatusEvent) messageEvent).getFileName();
                        getViewModel().startDismissFileOTA(arrayList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
