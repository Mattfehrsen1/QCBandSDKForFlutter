package com.qcwireless.qcwatch.base.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.BatteryLowEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.base.watch.DeviceReconnect;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: QcLifeCycle.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/base/lifecycle/QcLifeCycle;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "mCount", "", "getMCount", "()I", "setMCount", "(I)V", "isBand", "", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "StaticParam", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QcLifeCycle implements Application.ActivityLifecycleCallbacks {

    /* renamed from: StaticParam, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isForeground;
    private int mCount;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final int getMCount() {
        return this.mCount;
    }

    public final void setMCount(int i) {
        this.mCount = i;
    }

    /* compiled from: QcLifeCycle.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/base/lifecycle/QcLifeCycle$StaticParam;", "", "()V", "isForeground", "", "()Z", "setForeground", "(Z)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.base.lifecycle.QcLifeCycle$StaticParam, reason: from kotlin metadata */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isForeground() {
            return QcLifeCycle.isForeground;
        }

        public final void setForeground(boolean z) {
            QcLifeCycle.isForeground = z;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        int i = this.mCount + 1;
        this.mCount = i;
        if (i == 1) {
            isForeground = true;
            if (BleOperateManager.getInstance().isConnected()) {
                CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 3), new ICommandResponse() { // from class: com.qcwireless.qcwatch.base.lifecycle.QcLifeCycle$$ExternalSyntheticLambda0
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public final void onDataResponse(BaseRspCmd baseRspCmd) {
                        QcLifeCycle.m251onActivityCreated$lambda0((BatteryRsp) baseRspCmd);
                    }
                });
                EventBus.getDefault().post(new BatteryLowEvent());
            }
            if (BleOperateManager.getInstance().isConnected()) {
                return;
            }
            BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
            DeviceReconnect.INSTANCE.getGetInstance().connectWithScanValidation(UserConfig.INSTANCE.getInstance().getDeviceAddress());
            ThreadManager.getInstance().reSetLastConnectTime(3);
            ThreadManager.getInstance().wakeUpNotWait();
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onActivityCreated$lambda-0, reason: not valid java name */
    public static final void m251onActivityCreated$lambda0(BatteryRsp batteryRsp) {
        UserConfig.INSTANCE.getInstance().setBattery(String.valueOf(batteryRsp.getBatteryValue()));
        UserConfig.INSTANCE.getInstance().save();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        int i = this.mCount - 1;
        this.mCount = i;
        if (i == 0) {
            isForeground = false;
            Log.i("QcLifeCycle", "前台到后台----当前是后台");
            ThreadManager.getInstance().setSleepMin();
        }
    }

    private final boolean isBand() {
        return !TextUtils.isEmpty(UserConfig.INSTANCE.getInstance().getDeviceAddress());
    }
}
