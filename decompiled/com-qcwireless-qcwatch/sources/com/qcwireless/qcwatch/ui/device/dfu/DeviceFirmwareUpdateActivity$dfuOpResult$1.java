package com.qcwireless.qcwatch.ui.device.dfu;

import android.os.Handler;
import androidx.work.WorkRequest;
import com.oudmon.ble.base.communication.DfuHandle;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityDeviceFirmwareUpdateBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceFirmwareUpdateActivity.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"com/qcwireless/qcwatch/ui/device/dfu/DeviceFirmwareUpdateActivity$dfuOpResult$1", "Lcom/oudmon/ble/base/communication/DfuHandle$IOpResult;", "onActionResult", "", "type", "", "errCode", "onProgress", "percent", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceFirmwareUpdateActivity$dfuOpResult$1 implements DfuHandle.IOpResult {
    final /* synthetic */ DeviceFirmwareUpdateActivity this$0;

    DeviceFirmwareUpdateActivity$dfuOpResult$1(DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity) {
        this.this$0 = deviceFirmwareUpdateActivity;
    }

    @Override // com.oudmon.ble.base.communication.DfuHandle.IOpResult
    public void onActionResult(int type, int errCode) {
        if (errCode == 0) {
            QCApplication.INSTANCE.getGetInstance().setUpdating(1);
            DfuHandle dfuHandle = null;
            if (type == 1) {
                DfuHandle dfuHandle2 = this.this$0.dfuHandle;
                if (dfuHandle2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
                } else {
                    dfuHandle = dfuHandle2;
                }
                dfuHandle.init();
                return;
            }
            if (type == 2) {
                DfuHandle dfuHandle3 = this.this$0.dfuHandle;
                if (dfuHandle3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
                } else {
                    dfuHandle = dfuHandle3;
                }
                dfuHandle.sendPacket();
                return;
            }
            if (type == 3) {
                DfuHandle dfuHandle4 = this.this$0.dfuHandle;
                if (dfuHandle4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
                } else {
                    dfuHandle = dfuHandle4;
                }
                dfuHandle.check();
                return;
            }
            if (type != 4) {
                return;
            }
            DfuHandle dfuHandle5 = this.this$0.dfuHandle;
            if (dfuHandle5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
            } else {
                dfuHandle = dfuHandle5;
            }
            dfuHandle.endAndRelease();
            this.this$0.handler.removeCallbacks(this.this$0.runnable);
            final DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity = this.this$0;
            deviceFirmwareUpdateActivity.runOnUiThread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$dfuOpResult$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceFirmwareUpdateActivity$dfuOpResult$1.m433onActionResult$lambda0(deviceFirmwareUpdateActivity);
                }
            });
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            UserConfig.INSTANCE.getInstance().setFmVersion("");
            UserConfig.INSTANCE.getInstance().save();
            Handler handler = this.this$0.handler;
            final DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity2 = this.this$0;
            handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$dfuOpResult$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceFirmwareUpdateActivity$dfuOpResult$1.m434onActionResult$lambda1(deviceFirmwareUpdateActivity2);
                }
            }, 1000L);
            return;
        }
        final DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity3 = this.this$0;
        ThreadExtKt.ktxRunOnUi(this, new Function1<DeviceFirmwareUpdateActivity$dfuOpResult$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$dfuOpResult$1$onActionResult$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceFirmwareUpdateActivity$dfuOpResult$1 deviceFirmwareUpdateActivity$dfuOpResult$1) {
                invoke2(deviceFirmwareUpdateActivity$dfuOpResult$1);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceFirmwareUpdateActivity$dfuOpResult$1 ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                String string = deviceFirmwareUpdateActivity3.getString(R.string.qc_text_238);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_238)");
                GlobalKt.showToast$default(string, 0, 1, null);
                deviceFirmwareUpdateActivity3.getViewModel().cancelTask();
                deviceFirmwareUpdateActivity3.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onActionResult$lambda-0, reason: not valid java name */
    public static final void m433onActionResult$lambda0(DeviceFirmwareUpdateActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding = this$0.binding;
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding2 = null;
        if (activityDeviceFirmwareUpdateBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateBinding = null;
        }
        activityDeviceFirmwareUpdateBinding.tvProgressValue.setText("100");
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding3 = this$0.binding;
        if (activityDeviceFirmwareUpdateBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceFirmwareUpdateBinding2 = activityDeviceFirmwareUpdateBinding3;
        }
        activityDeviceFirmwareUpdateBinding2.progressValue.setPercentage(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onActionResult$lambda-1, reason: not valid java name */
    public static final void m434onActionResult$lambda1(DeviceFirmwareUpdateActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.appDisconnect();
    }

    @Override // com.oudmon.ble.base.communication.DfuHandle.IOpResult
    public void onProgress(final int percent) {
        final DeviceFirmwareUpdateActivity deviceFirmwareUpdateActivity = this.this$0;
        deviceFirmwareUpdateActivity.runOnUiThread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity$dfuOpResult$1$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DeviceFirmwareUpdateActivity$dfuOpResult$1.m435onProgress$lambda2(deviceFirmwareUpdateActivity, percent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onProgress$lambda-2, reason: not valid java name */
    public static final void m435onProgress$lambda2(DeviceFirmwareUpdateActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding = this$0.binding;
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding2 = null;
        if (activityDeviceFirmwareUpdateBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateBinding = null;
        }
        activityDeviceFirmwareUpdateBinding.tvError.setText(this$0.getString(R.string.qc_text_360));
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding3 = this$0.binding;
        if (activityDeviceFirmwareUpdateBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateBinding3 = null;
        }
        activityDeviceFirmwareUpdateBinding3.tvProgressValue.setText(String.valueOf(i));
        ActivityDeviceFirmwareUpdateBinding activityDeviceFirmwareUpdateBinding4 = this$0.binding;
        if (activityDeviceFirmwareUpdateBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceFirmwareUpdateBinding2 = activityDeviceFirmwareUpdateBinding4;
        }
        activityDeviceFirmwareUpdateBinding2.progressValue.setPercentage(i);
        this$0.handler.removeCallbacks(this$0.runnable);
        this$0.handler.postDelayed(this$0.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
    }
}
