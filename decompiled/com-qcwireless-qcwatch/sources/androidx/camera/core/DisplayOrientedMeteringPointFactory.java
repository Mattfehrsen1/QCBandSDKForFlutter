package androidx.camera.core;

import android.graphics.PointF;
import android.view.Display;
import androidx.camera.core.impl.CameraInfoInternal;

/* loaded from: classes.dex */
public final class DisplayOrientedMeteringPointFactory extends MeteringPointFactory {
    private final CameraInfo mCameraInfo;
    private final Display mDisplay;
    private final float mHeight;
    private final float mWidth;

    public DisplayOrientedMeteringPointFactory(Display display, CameraInfo cameraInfo, float width, float height) {
        this.mWidth = width;
        this.mHeight = height;
        this.mDisplay = display;
        this.mCameraInfo = cameraInfo;
    }

    private Integer getLensFacing() {
        CameraInfo cameraInfo = this.mCameraInfo;
        if (cameraInfo instanceof CameraInfoInternal) {
            return ((CameraInfoInternal) cameraInfo).getLensFacing();
        }
        return null;
    }

    @Override // androidx.camera.core.MeteringPointFactory
    protected PointF convertPoint(float x, float y) {
        float f = this.mWidth;
        float f2 = this.mHeight;
        Integer lensFacing = getLensFacing();
        boolean z = lensFacing != null && lensFacing.intValue() == 0;
        int relativeCameraOrientation = getRelativeCameraOrientation(z);
        if (relativeCameraOrientation != 90 && relativeCameraOrientation != 270) {
            y = x;
            x = y;
            f2 = f;
            f = f2;
        }
        if (relativeCameraOrientation == 90) {
            x = f - x;
        } else if (relativeCameraOrientation == 180) {
            y = f2 - y;
            x = f - x;
        } else if (relativeCameraOrientation == 270) {
            y = f2 - y;
        }
        if (z) {
            y = f2 - y;
        }
        return new PointF(y / f2, x / f);
    }

    private int getRelativeCameraOrientation(boolean compensateForMirroring) {
        try {
            int sensorRotationDegrees = this.mCameraInfo.getSensorRotationDegrees(this.mDisplay.getRotation());
            return compensateForMirroring ? (360 - sensorRotationDegrees) % 360 : sensorRotationDegrees;
        } catch (Exception unused) {
            return 0;
        }
    }
}
