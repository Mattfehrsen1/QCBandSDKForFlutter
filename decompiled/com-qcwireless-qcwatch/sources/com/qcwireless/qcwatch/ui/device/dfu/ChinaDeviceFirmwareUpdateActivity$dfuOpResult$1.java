package com.qcwireless.qcwatch.ui.device.dfu;

import android.os.Handler;
import androidx.work.WorkRequest;
import com.oudmon.ble.base.communication.DfuHandle;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityDeviceFirmwareUpdateChinaBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChinaDeviceFirmwareUpdateActivity.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"com/qcwireless/qcwatch/ui/device/dfu/ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1", "Lcom/oudmon/ble/base/communication/DfuHandle$IOpResult;", "onActionResult", "", "type", "", "errCode", "onProgress", "percent", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1 implements DfuHandle.IOpResult {
    final /* synthetic */ ChinaDeviceFirmwareUpdateActivity this$0;

    ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1(ChinaDeviceFirmwareUpdateActivity chinaDeviceFirmwareUpdateActivity) {
        this.this$0 = chinaDeviceFirmwareUpdateActivity;
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
            final ChinaDeviceFirmwareUpdateActivity chinaDeviceFirmwareUpdateActivity = this.this$0;
            chinaDeviceFirmwareUpdateActivity.runOnUiThread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1.m427onActionResult$lambda0(chinaDeviceFirmwareUpdateActivity);
                }
            });
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            UserConfig.INSTANCE.getInstance().setFmVersion("");
            UserConfig.INSTANCE.getInstance().save();
            Handler handler = this.this$0.handler;
            final ChinaDeviceFirmwareUpdateActivity chinaDeviceFirmwareUpdateActivity2 = this.this$0;
            handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1.m428onActionResult$lambda1(chinaDeviceFirmwareUpdateActivity2);
                }
            }, 1000L);
            return;
        }
        final ChinaDeviceFirmwareUpdateActivity chinaDeviceFirmwareUpdateActivity3 = this.this$0;
        ThreadExtKt.ktxRunOnUi(this, new Function1<ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.dfu.ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1$onActionResult$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1 chinaDeviceFirmwareUpdateActivity$dfuOpResult$1) {
                invoke2(chinaDeviceFirmwareUpdateActivity$dfuOpResult$1);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1 ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                String string = chinaDeviceFirmwareUpdateActivity3.getString(R.string.qc_text_238);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_238)");
                GlobalKt.showToast$default(string, 0, 1, null);
                chinaDeviceFirmwareUpdateActivity3.getViewModel().cancelTask();
                chinaDeviceFirmwareUpdateActivity3.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onActionResult$lambda-0, reason: not valid java name */
    public static final void m427onActionResult$lambda0(ChinaDeviceFirmwareUpdateActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding = this$0.binding;
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding2 = null;
        if (activityDeviceFirmwareUpdateChinaBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateChinaBinding = null;
        }
        activityDeviceFirmwareUpdateChinaBinding.tvProgressValue.setText("100");
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding3 = this$0.binding;
        if (activityDeviceFirmwareUpdateChinaBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceFirmwareUpdateChinaBinding2 = activityDeviceFirmwareUpdateChinaBinding3;
        }
        activityDeviceFirmwareUpdateChinaBinding2.progressValue.setPercentage(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onActionResult$lambda-1, reason: not valid java name */
    public static final void m428onActionResult$lambda1(ChinaDeviceFirmwareUpdateActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.appDisconnect();
    }

    @Override // com.oudmon.ble.base.communication.DfuHandle.IOpResult
    public void onProgress(final int percent) {
        final ChinaDeviceFirmwareUpdateActivity chinaDeviceFirmwareUpdateActivity = this.this$0;
        chinaDeviceFirmwareUpdateActivity.runOnUiThread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.dfu.ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ChinaDeviceFirmwareUpdateActivity$dfuOpResult$1.m429onProgress$lambda2(chinaDeviceFirmwareUpdateActivity, percent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onProgress$lambda-2, reason: not valid java name */
    public static final void m429onProgress$lambda2(ChinaDeviceFirmwareUpdateActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding = this$0.binding;
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding2 = null;
        if (activityDeviceFirmwareUpdateChinaBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateChinaBinding = null;
        }
        activityDeviceFirmwareUpdateChinaBinding.tvError.setText(this$0.getString(R.string.qc_text_360));
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding3 = this$0.binding;
        if (activityDeviceFirmwareUpdateChinaBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceFirmwareUpdateChinaBinding3 = null;
        }
        activityDeviceFirmwareUpdateChinaBinding3.tvProgressValue.setText(String.valueOf(i));
        ActivityDeviceFirmwareUpdateChinaBinding activityDeviceFirmwareUpdateChinaBinding4 = this$0.binding;
        if (activityDeviceFirmwareUpdateChinaBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceFirmwareUpdateChinaBinding2 = activityDeviceFirmwareUpdateChinaBinding4;
        }
        activityDeviceFirmwareUpdateChinaBinding2.progressValue.setPercentage(i);
        this$0.handler.removeCallbacks(this$0.runnable);
        this$0.handler.postDelayed(this$0.runnable, WorkRequest.MIN_BACKOFF_MILLIS);
    }
}
