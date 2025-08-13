package io.fotoapparat.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;
import android.widget.FrameLayout;
import io.fotoapparat.exception.camera.UnavailableSurfaceException;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.view.Preview;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: CameraView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0014J0\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bH\u0014J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u000bH\u0016J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010$\u001a\u0004\u0018\u00010\u000f*\u00020\u0013H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lio/fotoapparat/view/CameraView;", "Landroid/widget/FrameLayout;", "Lio/fotoapparat/view/CameraRenderer;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "previewResolution", "Lio/fotoapparat/parameter/Resolution;", "scaleType", "Lio/fotoapparat/parameter/ScaleType;", "surfaceTexture", "Landroid/graphics/SurfaceTexture;", "textureLatch", "Ljava/util/concurrent/CountDownLatch;", "textureView", "Landroid/view/TextureView;", "getPreview", "Lio/fotoapparat/view/Preview;", "getPreviewAfterLatch", "Lio/fotoapparat/view/Preview$Texture;", "onDetachedFromWindow", "", "onLayout", "changed", "", "left", "top", "right", "bottom", "setPreviewResolution", "resolution", "setScaleType", "tryInitialize", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraView extends FrameLayout implements CameraRenderer {
    private Resolution previewResolution;
    private ScaleType scaleType;
    private SurfaceTexture surfaceTexture;
    private final CountDownLatch textureLatch;
    private final TextureView textureView;

    public CameraView(Context context) {
        this(context, null, 0, 6, null);
    }

    public CameraView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public static final /* synthetic */ Resolution access$getPreviewResolution$p(CameraView cameraView) {
        Resolution resolution = cameraView.previewResolution;
        if (resolution == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewResolution");
        }
        return resolution;
    }

    public static final /* synthetic */ ScaleType access$getScaleType$p(CameraView cameraView) {
        ScaleType scaleType = cameraView.scaleType;
        if (scaleType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scaleType");
        }
        return scaleType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CameraView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.textureLatch = new CountDownLatch(1);
        TextureView textureView = new TextureView(context);
        this.textureView = textureView;
        this.surfaceTexture = tryInitialize(textureView);
        addView(textureView);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.textureLatch.countDown();
    }

    @Override // io.fotoapparat.view.CameraRenderer
    public void setScaleType(ScaleType scaleType) {
        Intrinsics.checkParameterIsNotNull(scaleType, "scaleType");
        this.scaleType = scaleType;
    }

    @Override // io.fotoapparat.view.CameraRenderer
    public void setPreviewResolution(final Resolution resolution) {
        Intrinsics.checkParameterIsNotNull(resolution, "resolution");
        post(new Runnable() { // from class: io.fotoapparat.view.CameraView.setPreviewResolution.1
            @Override // java.lang.Runnable
            public final void run() {
                CameraView.this.previewResolution = resolution;
                CameraView.this.requestLayout();
            }
        });
    }

    @Override // io.fotoapparat.view.CameraRenderer
    public Preview getPreview() throws InterruptedException {
        Preview.Texture previewAfterLatch;
        SurfaceTexture surfaceTexture = this.surfaceTexture;
        if (surfaceTexture == null || (previewAfterLatch = PreviewKt.toPreview(surfaceTexture)) == null) {
            previewAfterLatch = getPreviewAfterLatch();
        }
        return previewAfterLatch;
    }

    /* compiled from: CameraView.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.fotoapparat.view.CameraView$onLayout$1, reason: invalid class name */
    final class AnonymousClass1 extends MutablePropertyReference0 {
        AnonymousClass1(CameraView cameraView) {
            super(cameraView);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "previewResolution";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(CameraView.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getPreviewResolution()Lio/fotoapparat/parameter/Resolution;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return CameraView.access$getPreviewResolution$p((CameraView) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((CameraView) this.receiver).previewResolution = (Resolution) obj;
        }
    }

    /* compiled from: CameraView.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.fotoapparat.view.CameraView$onLayout$2, reason: invalid class name */
    final class AnonymousClass2 extends MutablePropertyReference0 {
        AnonymousClass2(CameraView cameraView) {
            super(cameraView);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "scaleType";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(CameraView.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getScaleType()Lio/fotoapparat/parameter/ScaleType;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return CameraView.access$getScaleType$p((CameraView) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((CameraView) this.receiver).scaleType = (ScaleType) obj;
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        if (isInEditMode() || this.previewResolution == null || this.scaleType == null) {
            super.onLayout(changed, left, top2, right, bottom);
            return;
        }
        Resolution resolution = this.previewResolution;
        if (resolution == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewResolution");
        }
        ScaleType scaleType = this.scaleType;
        if (scaleType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scaleType");
        }
        CameraViewKt.layoutTextureView(this, resolution, scaleType);
    }

    private final Preview.Texture getPreviewAfterLatch() throws InterruptedException {
        Preview.Texture preview;
        this.textureLatch.await();
        SurfaceTexture surfaceTexture = this.surfaceTexture;
        if (surfaceTexture == null || (preview = PreviewKt.toPreview(surfaceTexture)) == null) {
            throw new UnavailableSurfaceException();
        }
        return preview;
    }

    private final SurfaceTexture tryInitialize(final TextureView textureView) {
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        if (surfaceTexture != null) {
            return surfaceTexture;
        }
        textureView.setSurfaceTextureListener(new TextureAvailabilityListener(new Function1<SurfaceTexture, Unit>() { // from class: io.fotoapparat.view.CameraView$tryInitialize$$inlined$also$lambda$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SurfaceTexture surfaceTexture2) {
                invoke2(surfaceTexture2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SurfaceTexture receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                this.this$0.surfaceTexture = receiver$0;
                this.this$0.textureLatch.countDown();
            }
        }));
        return null;
    }
}
