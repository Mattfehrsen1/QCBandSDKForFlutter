package io.fotoapparat.hardware;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.Surface;
import android.view.SurfaceHolder;
import io.fotoapparat.capability.Capabilities;
import io.fotoapparat.hardware.orientation.Orientation;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.result.Photo;
import io.fotoapparat.view.Preview;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraDevice.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000*\n\u0010\u0012\"\u00020\u00062\u00020\u0006¨\u0006\u0013"}, d2 = {"AUTOFOCUS_TIMEOUT_SECONDS", "", "canSetFocusingAreas", "", "Lio/fotoapparat/capability/Capabilities;", "getPreviewResolution", "Lio/fotoapparat/parameter/Resolution;", "Landroid/hardware/Camera;", "previewOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "setDisplaySurface", "Landroid/view/Surface;", "preview", "Lio/fotoapparat/view/Preview;", "takePhoto", "Lio/fotoapparat/result/Photo;", "imageRotation", "", "PreviewSize", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraDeviceKt {
    private static final long AUTOFOCUS_TIMEOUT_SECONDS = 3;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Photo takePhoto(Camera camera, final int i) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        camera.takePicture(null, null, null, new Camera.PictureCallback() { // from class: io.fotoapparat.hardware.CameraDeviceKt.takePhoto.1
            @Override // android.hardware.Camera.PictureCallback
            public final void onPictureTaken(byte[] data, Camera camera2) {
                AtomicReference atomicReference2 = atomicReference;
                Intrinsics.checkExpressionValueIsNotNull(data, "data");
                atomicReference2.set(new Photo(data, i));
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        Object obj = atomicReference.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "photoReference.get()");
        return (Photo) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Surface setDisplaySurface(Camera camera, Preview preview) throws IOException {
        if (preview instanceof Preview.Texture) {
            SurfaceTexture surfaceTexture = ((Preview.Texture) preview).getSurfaceTexture();
            camera.setPreviewTexture(surfaceTexture);
            return new Surface(surfaceTexture);
        }
        if (!(preview instanceof Preview.Surface)) {
            throw new NoWhenBranchMatchedException();
        }
        SurfaceHolder surfaceHolder = ((Preview.Surface) preview).getSurfaceHolder();
        camera.setPreviewDisplay(surfaceHolder);
        Surface surface = surfaceHolder.getSurface();
        Intrinsics.checkExpressionValueIsNotNull(surface, "preview.surfaceHolder\n  …lay)\n            .surface");
        return surface;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Resolution getPreviewResolution(Camera camera, Orientation orientation) {
        Camera.Parameters parameters = camera.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
        Camera.Size previewSize = parameters.getPreviewSize();
        Resolution resolution = new Resolution(previewSize.width, previewSize.height);
        if (orientation instanceof Orientation.Vertical) {
            return resolution;
        }
        if (orientation instanceof Orientation.Horizontal) {
            return resolution.flipDimensions();
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean canSetFocusingAreas(Capabilities capabilities) {
        return capabilities.getMaxMeteringAreas() > 0 || capabilities.getMaxFocusAreas() > 0;
    }
}
