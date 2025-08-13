package io.fotoapparat.view;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextureAvailabilityListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001e\u0012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0002\u0010\u0007J \u0010\u0002\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0004H\u0016J \u0010\u000e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0004H\u0016R\u001f\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/fotoapparat/view/TextureAvailabilityListener;", "Landroid/view/TextureView$SurfaceTextureListener;", "onSurfaceTextureAvailable", "Lkotlin/Function1;", "Landroid/graphics/SurfaceTexture;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)V", "surface", "width", "", "height", "onSurfaceTextureDestroyed", "", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class TextureAvailabilityListener implements TextureView.SurfaceTextureListener {
    private final Function1<SurfaceTexture, Unit> onSurfaceTextureAvailable;

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Intrinsics.checkParameterIsNotNull(surface, "surface");
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Intrinsics.checkParameterIsNotNull(surface, "surface");
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Intrinsics.checkParameterIsNotNull(surface, "surface");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TextureAvailabilityListener(Function1<? super SurfaceTexture, Unit> onSurfaceTextureAvailable) {
        Intrinsics.checkParameterIsNotNull(onSurfaceTextureAvailable, "onSurfaceTextureAvailable");
        this.onSurfaceTextureAvailable = onSurfaceTextureAvailable;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Intrinsics.checkParameterIsNotNull(surface, "surface");
        this.onSurfaceTextureAvailable.invoke(surface);
    }
}
