package com.qcwireless.qcwatch.ui.base.receiver;

import android.content.Intent;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.CameraToastEvent;
import com.qcwireless.qcwatch.base.event.DeviceConfigEvent;
import com.qcwireless.qcwatch.base.event.FirmCheckEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceDownloadEvent;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.device.camera.CameraActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.greenrobot.eventbus.EventBus;

/* compiled from: BleCommonDataParse.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0007"}, d2 = {"parseBleData", "", "data", "", "parseDeviceInfoData", "uuid", "", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BleCommonDataParseKt {
    public static final void parseBleData(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (((byte) (data[0] & ((byte) (~Constants.FLAG_MASK_ERROR)))) != 2 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 1 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 2 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 3 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 4 || data[1] != 1) {
            return;
        }
        if (!AppUtil.isBackground(QCApplication.INSTANCE.getCONTEXT()) && !AppUtil.isApplicationBroughtToBackground(QCApplication.INSTANCE.getCONTEXT())) {
            if (!PermissionUtilKt.hasCameraPermission(QCApplication.INSTANCE.getCONTEXT())) {
                EventBus.getDefault().post(new CameraToastEvent());
                return;
            }
            Intent intent = new Intent(QCApplication.INSTANCE.getCONTEXT(), (Class<?>) CameraActivity.class);
            intent.addFlags(268435456);
            QCApplication.INSTANCE.getCONTEXT().startActivity(intent);
            return;
        }
        new NotificationUtils(QJavaApplication.getInstance().getApplication()).initCameraNotification();
    }

    public static final void parseDeviceInfoData(String uuid, byte[] data) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(data, "data");
        String str = new String(data, Charsets.UTF_8);
        if (Intrinsics.areEqual(uuid, Constants.CHAR_FIRMWARE_REVISION.toString())) {
            XLog.i(str);
            UserConfig.INSTANCE.getInstance().setFmVersion(str);
            UserConfig.INSTANCE.getInstance().save();
            EventBus.getDefault().post(new FirmCheckEvent());
            return;
        }
        if (Intrinsics.areEqual(uuid, Constants.CHAR_HW_REVISION.toString())) {
            UserConfig.INSTANCE.getInstance().setHwVersion(str);
            UserConfig.INSTANCE.getInstance().save();
            EventBus.getDefault().post(new WatchFaceDownloadEvent(str));
            EventBus.getDefault().post(new DeviceConfigEvent(str));
            XLog.i(str);
            EventBus.getDefault().post(new FirmCheckEvent());
            BleOperateManager.getInstance().setReady(true);
        }
    }
}
