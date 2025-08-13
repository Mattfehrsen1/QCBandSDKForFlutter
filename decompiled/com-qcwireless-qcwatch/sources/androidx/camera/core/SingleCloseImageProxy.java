package androidx.camera.core;

/* loaded from: classes.dex */
final class SingleCloseImageProxy extends ForwardingImageProxy {
    private boolean mClosed;

    SingleCloseImageProxy(ImageProxy image) {
        super(image);
        this.mClosed = false;
    }

    @Override // androidx.camera.core.ForwardingImageProxy, androidx.camera.core.ImageProxy, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.mClosed) {
            this.mClosed = true;
            super.close();
        }
    }
}
