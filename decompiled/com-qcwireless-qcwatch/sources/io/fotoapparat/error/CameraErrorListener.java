package io.fotoapparat.error;

import io.fotoapparat.exception.camera.CameraException;
import kotlin.Metadata;

/* compiled from: CameraErrorCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lio/fotoapparat/error/CameraErrorListener;", "", "onError", "", "e", "Lio/fotoapparat/exception/camera/CameraException;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface CameraErrorListener {
    void onError(CameraException e);
}
