package androidx.camera.core.impl.utils;

import androidx.camera.core.Logger;

/* loaded from: classes.dex */
public final class CameraOrientationUtil {
    private static final String TAG = "CameraOrientationUtil";

    private CameraOrientationUtil() {
    }

    public static int getRelativeImageRotation(int destRotationDegrees, int sourceRotationDegrees, boolean isOppositeFacing) {
        int i;
        if (isOppositeFacing) {
            i = ((sourceRotationDegrees - destRotationDegrees) + 360) % 360;
        } else {
            i = (sourceRotationDegrees + destRotationDegrees) % 360;
        }
        if (Logger.isDebugEnabled(TAG)) {
            Logger.d(TAG, String.format("getRelativeImageRotation: destRotationDegrees=%s, sourceRotationDegrees=%s, isOppositeFacing=%s, result=%s", Integer.valueOf(destRotationDegrees), Integer.valueOf(sourceRotationDegrees), Boolean.valueOf(isOppositeFacing), Integer.valueOf(i)));
        }
        return i;
    }

    public static int surfaceRotationToDegrees(int rotation) {
        if (rotation == 0) {
            return 0;
        }
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return 180;
        }
        if (rotation == 3) {
            return 270;
        }
        throw new IllegalArgumentException("Unsupported surface rotation: " + rotation);
    }

    public static int degreesToSurfaceRotation(int degrees) {
        if (degrees == 0) {
            return 0;
        }
        if (degrees == 90) {
            return 1;
        }
        if (degrees == 180) {
            return 2;
        }
        if (degrees == 270) {
            return 3;
        }
        throw new IllegalStateException("Invalid sensor rotation: " + degrees);
    }
}
