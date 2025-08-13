package com.qcwireless.qcwatch.base.permission;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionUtil.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u000e\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u001a"}, d2 = {"hasBluetooth", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "hasCallPermission", "hasCallPhonePermission", "hasCameraPermission", "context", "Landroid/content/Context;", "hasContactPermission", "hasLocationPermission", "hasRecordAudioPermission", "hasSMSPermission", "requestAlertWindowPermission", "", "requestBgLocation", "requestCallback", "Lcom/hjq/permissions/OnPermissionCallback;", "requestBluetoothPermission", "requestCallPermission", "requestCallPhonePermission", "requestCameraPermission", "requestContactPermission", "requestLocationPermission", "requestRecordAudioPermission", "requestSMSPermission", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PermissionUtilKt {
    public static final void requestCallPhonePermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.READ_PHONE_STATE).permission(Permission.READ_CALL_LOG).permission(Permission.CALL_PHONE).permission(Permission.READ_CONTACTS).permission(Permission.ANSWER_PHONE_CALLS).request(requestCallback);
    }

    public static final boolean hasCallPhonePermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Permission.READ_PHONE_STATE);
        arrayList.add(Permission.READ_CALL_LOG);
        arrayList.add(Permission.CALL_PHONE);
        arrayList.add(Permission.READ_CONTACTS);
        arrayList.add(Permission.ANSWER_PHONE_CALLS);
        return XXPermissions.isGranted(activity, arrayList);
    }

    public static final boolean hasRecordAudioPermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.READ_MEDIA_AUDIO");
        return XXPermissions.isGranted(activity, arrayList);
    }

    public static final void requestRecordAudioPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission("android.permission.READ_MEDIA_AUDIO").request(requestCallback);
    }

    public static final boolean hasCameraPermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Permission.CAMERA);
        return XXPermissions.isGranted(context, arrayList);
    }

    public static final boolean hasSMSPermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Permission.READ_SMS);
        arrayList.add(Permission.RECEIVE_SMS);
        return XXPermissions.isGranted(activity, arrayList);
    }

    public static final boolean hasContactPermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return XXPermissions.isGranted(activity, Permission.READ_CONTACTS);
    }

    public static final boolean hasLocationPermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return XXPermissions.isGranted(activity, Permission.ACCESS_FINE_LOCATION);
    }

    public static final boolean hasCallPermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Permission.READ_PHONE_STATE);
        arrayList.add(Permission.READ_CALL_LOG);
        arrayList.add(Permission.CALL_PHONE);
        arrayList.add(Permission.ANSWER_PHONE_CALLS);
        return XXPermissions.isGranted(activity, arrayList);
    }

    public static final boolean hasBluetooth(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Permission.BLUETOOTH_SCAN);
        arrayList.add(Permission.BLUETOOTH_CONNECT);
        arrayList.add(Permission.BLUETOOTH_ADVERTISE);
        return XXPermissions.isGranted(activity, arrayList);
    }

    public static final void requestSMSPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.READ_SMS).permission(Permission.RECEIVE_SMS).request(requestCallback);
    }

    public static final void requestLocationPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.ACCESS_COARSE_LOCATION).permission(Permission.ACCESS_FINE_LOCATION).request(requestCallback);
    }

    public static final void requestBluetoothPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.BLUETOOTH_SCAN).permission(Permission.BLUETOOTH_CONNECT).permission(Permission.BLUETOOTH_ADVERTISE).permission(Permission.ACCESS_FINE_LOCATION).request(requestCallback);
    }

    public static final void requestCallPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.READ_PHONE_STATE).permission(Permission.READ_CALL_LOG).permission(Permission.CALL_PHONE).permission(Permission.ANSWER_PHONE_CALLS).request(requestCallback);
    }

    public static final void requestContactPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.READ_CONTACTS).request(requestCallback);
    }

    public static final void requestBgLocation(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.ACCESS_COARSE_LOCATION).permission(Permission.ACCESS_FINE_LOCATION).request(requestCallback);
    }

    public static final void requestAlertWindowPermission(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        XXPermissions.with(activity).permission(Permission.SYSTEM_ALERT_WINDOW);
    }

    public static final void requestCameraPermission(FragmentActivity activity, OnPermissionCallback requestCallback) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(requestCallback, "requestCallback");
        XXPermissions.with(activity).permission(Permission.CAMERA).request(requestCallback);
    }
}
