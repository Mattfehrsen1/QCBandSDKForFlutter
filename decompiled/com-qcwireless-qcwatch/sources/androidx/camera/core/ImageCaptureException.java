package androidx.camera.core;

/* loaded from: classes.dex */
public class ImageCaptureException extends Exception {
    private final int mImageCaptureError;

    public ImageCaptureException(final int imageCaptureError, final String message, final Throwable cause) {
        super(message, cause);
        this.mImageCaptureError = imageCaptureError;
    }

    public int getImageCaptureError() {
        return this.mImageCaptureError;
    }
}
