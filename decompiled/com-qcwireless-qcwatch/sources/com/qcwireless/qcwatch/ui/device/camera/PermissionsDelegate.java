package com.qcwireless.qcwatch.ui.device.camera;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.hjq.permissions.Permission;

/* loaded from: classes3.dex */
class PermissionsDelegate {
    private static final int REQUEST_CODE = 10;
    private final Activity activity;

    PermissionsDelegate(Activity activity) {
        this.activity = activity;
    }

    boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this.activity, Permission.CAMERA) == 0;
    }

    void requestCameraPermission() {
        ActivityCompat.requestPermissions(this.activity, new String[]{Permission.CAMERA}, 10);
    }

    boolean resultGranted(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != 10 || grantResults.length < 1 || !permissions[0].equals(Permission.CAMERA)) {
            return false;
        }
        if (grantResults[0] == 0) {
            return true;
        }
        requestCameraPermission();
        return false;
    }
}
