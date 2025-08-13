package io.fotoapparat.exception.camera;

import kotlin.Metadata;

/* compiled from: UnsupportedLensException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/fotoapparat/exception/camera/UnsupportedLensException;", "Lio/fotoapparat/exception/camera/CameraException;", "()V", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UnsupportedLensException extends CameraException {
    public UnsupportedLensException() {
        super("Device has no camera for the desired lens position(s).", null, 2, null);
    }
}
