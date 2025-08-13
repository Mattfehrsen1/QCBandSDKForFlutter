package androidx.camera.core;

import android.graphics.PointF;
import android.util.Rational;

/* loaded from: classes.dex */
public abstract class MeteringPointFactory {
    private Rational mSurfaceAspectRatio;

    public static float getDefaultPointSize() {
        return 0.15f;
    }

    protected abstract PointF convertPoint(float x, float y);

    public MeteringPointFactory() {
        this(null);
    }

    public MeteringPointFactory(Rational surfaceAspectRatio) {
        this.mSurfaceAspectRatio = surfaceAspectRatio;
    }

    public final MeteringPoint createPoint(float x, float y) {
        return createPoint(x, y, getDefaultPointSize());
    }

    public final MeteringPoint createPoint(float x, float y, float size) {
        PointF pointFConvertPoint = convertPoint(x, y);
        return new MeteringPoint(pointFConvertPoint.x, pointFConvertPoint.y, size, this.mSurfaceAspectRatio);
    }
}
