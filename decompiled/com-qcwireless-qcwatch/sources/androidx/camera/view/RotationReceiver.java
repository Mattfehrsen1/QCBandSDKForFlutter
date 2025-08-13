package androidx.camera.view;

import android.content.Context;
import android.view.OrientationEventListener;

/* loaded from: classes.dex */
public abstract class RotationReceiver {
    private static final int INVALID_SURFACE_ROTATION = -1;
    private final OrientationEventListener mOrientationEventListener;
    int mRotation = -1;

    public abstract void onRotationChanged(int rotation);

    public RotationReceiver(Context context) {
        this.mOrientationEventListener = new OrientationEventListener(context) { // from class: androidx.camera.view.RotationReceiver.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                if (orientation == -1) {
                    return;
                }
                int i = (orientation >= 315 || orientation < 45) ? 0 : orientation >= 225 ? 1 : orientation >= 135 ? 2 : 3;
                if (RotationReceiver.this.mRotation != i) {
                    RotationReceiver.this.mRotation = i;
                    RotationReceiver.this.onRotationChanged(i);
                }
            }
        };
    }

    public boolean canDetectOrientation() {
        return this.mOrientationEventListener.canDetectOrientation();
    }

    public void enable() {
        this.mOrientationEventListener.enable();
    }

    public void disable() {
        this.mOrientationEventListener.disable();
    }
}
