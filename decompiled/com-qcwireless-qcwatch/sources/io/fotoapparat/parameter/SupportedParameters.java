package io.fotoapparat.parameter;

import android.hardware.Camera;
import io.fotoapparat.parameter.Zoom;
import io.fotoapparat.parameter.extract.RawValuesExtractorKt;
import io.fotoapparat.util.ListConvertionsKt;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.DebugKt;

/* compiled from: SupportedParameters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u000f\u0010\u0010R!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0013\u0010\u0010R\u001b\u0010\u0015\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0016\u0010\tR\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u000b\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b\u001e\u0010\u001bR%\u0010 \u001a\f\u0012\b\u0012\u00060!R\u00020\u00040\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b\"\u0010\u0010R%\u0010$\u001a\f\u0012\b\u0012\u00060!R\u00020\u00040\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u000b\u001a\u0004\b%\u0010\u0010R!\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00190\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u000b\u001a\u0004\b(\u0010\u0010R)\u0010*\u001a\u0010\u0012\f\u0012\n +*\u0004\u0018\u00010\u000e0\u000e0\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u000b\u001a\u0004\b,\u0010\u0010R!\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u000b\u001a\u0004\b0\u0010\u0010R\u001b\u00102\u001a\u0002038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u000b\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u000b\u001a\u0004\b9\u0010:¨\u0006<"}, d2 = {"Lio/fotoapparat/parameter/SupportedParameters;", "", "cameraParameters", "Landroid/hardware/Camera$Parameters;", "Landroid/hardware/Camera;", "(Landroid/hardware/Camera$Parameters;)V", "exposureCompensationRange", "Lkotlin/ranges/IntRange;", "getExposureCompensationRange", "()Lkotlin/ranges/IntRange;", "exposureCompensationRange$delegate", "Lkotlin/Lazy;", "flashModes", "", "", "getFlashModes", "()Ljava/util/List;", "flashModes$delegate", "focusModes", "getFocusModes", "focusModes$delegate", "jpegQualityRange", "getJpegQualityRange", "jpegQualityRange$delegate", "maxNumFocusAreas", "", "getMaxNumFocusAreas", "()I", "maxNumFocusAreas$delegate", "maxNumMeteringAreas", "getMaxNumMeteringAreas", "maxNumMeteringAreas$delegate", "pictureResolutions", "Landroid/hardware/Camera$Size;", "getPictureResolutions", "pictureResolutions$delegate", "previewResolutions", "getPreviewResolutions", "previewResolutions$delegate", "sensorSensitivities", "getSensorSensitivities", "sensorSensitivities$delegate", "supportedAutoBandingModes", "kotlin.jvm.PlatformType", "getSupportedAutoBandingModes", "supportedAutoBandingModes$delegate", "supportedPreviewFpsRanges", "", "getSupportedPreviewFpsRanges", "supportedPreviewFpsRanges$delegate", "supportedSmoothZoom", "", "getSupportedSmoothZoom", "()Z", "supportedSmoothZoom$delegate", "supportedZoom", "Lio/fotoapparat/parameter/Zoom;", "getSupportedZoom", "()Lio/fotoapparat/parameter/Zoom;", "supportedZoom$delegate", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class SupportedParameters {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "flashModes", "getFlashModes()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "focusModes", "getFocusModes()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "previewResolutions", "getPreviewResolutions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "pictureResolutions", "getPictureResolutions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "supportedPreviewFpsRanges", "getSupportedPreviewFpsRanges()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "sensorSensitivities", "getSensorSensitivities()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "supportedZoom", "getSupportedZoom()Lio/fotoapparat/parameter/Zoom;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "supportedSmoothZoom", "getSupportedSmoothZoom()Z")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "supportedAutoBandingModes", "getSupportedAutoBandingModes()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "jpegQualityRange", "getJpegQualityRange()Lkotlin/ranges/IntRange;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "exposureCompensationRange", "getExposureCompensationRange()Lkotlin/ranges/IntRange;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "maxNumFocusAreas", "getMaxNumFocusAreas()I")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SupportedParameters.class), "maxNumMeteringAreas", "getMaxNumMeteringAreas()I"))};
    private final Camera.Parameters cameraParameters;

    /* renamed from: exposureCompensationRange$delegate, reason: from kotlin metadata */
    private final Lazy exposureCompensationRange;

    /* renamed from: flashModes$delegate, reason: from kotlin metadata */
    private final Lazy flashModes;

    /* renamed from: focusModes$delegate, reason: from kotlin metadata */
    private final Lazy focusModes;

    /* renamed from: jpegQualityRange$delegate, reason: from kotlin metadata */
    private final Lazy jpegQualityRange;

    /* renamed from: maxNumFocusAreas$delegate, reason: from kotlin metadata */
    private final Lazy maxNumFocusAreas;

    /* renamed from: maxNumMeteringAreas$delegate, reason: from kotlin metadata */
    private final Lazy maxNumMeteringAreas;

    /* renamed from: pictureResolutions$delegate, reason: from kotlin metadata */
    private final Lazy pictureResolutions;

    /* renamed from: previewResolutions$delegate, reason: from kotlin metadata */
    private final Lazy previewResolutions;

    /* renamed from: sensorSensitivities$delegate, reason: from kotlin metadata */
    private final Lazy sensorSensitivities;

    /* renamed from: supportedAutoBandingModes$delegate, reason: from kotlin metadata */
    private final Lazy supportedAutoBandingModes;

    /* renamed from: supportedPreviewFpsRanges$delegate, reason: from kotlin metadata */
    private final Lazy supportedPreviewFpsRanges;

    /* renamed from: supportedSmoothZoom$delegate, reason: from kotlin metadata */
    private final Lazy supportedSmoothZoom;

    /* renamed from: supportedZoom$delegate, reason: from kotlin metadata */
    private final Lazy supportedZoom;

    public final IntRange getExposureCompensationRange() {
        Lazy lazy = this.exposureCompensationRange;
        KProperty kProperty = $$delegatedProperties[10];
        return (IntRange) lazy.getValue();
    }

    public final List<String> getFlashModes() {
        Lazy lazy = this.flashModes;
        KProperty kProperty = $$delegatedProperties[0];
        return (List) lazy.getValue();
    }

    public final List<String> getFocusModes() {
        Lazy lazy = this.focusModes;
        KProperty kProperty = $$delegatedProperties[1];
        return (List) lazy.getValue();
    }

    public final IntRange getJpegQualityRange() {
        Lazy lazy = this.jpegQualityRange;
        KProperty kProperty = $$delegatedProperties[9];
        return (IntRange) lazy.getValue();
    }

    public final int getMaxNumFocusAreas() {
        Lazy lazy = this.maxNumFocusAreas;
        KProperty kProperty = $$delegatedProperties[11];
        return ((Number) lazy.getValue()).intValue();
    }

    public final int getMaxNumMeteringAreas() {
        Lazy lazy = this.maxNumMeteringAreas;
        KProperty kProperty = $$delegatedProperties[12];
        return ((Number) lazy.getValue()).intValue();
    }

    public final List<Camera.Size> getPictureResolutions() {
        Lazy lazy = this.pictureResolutions;
        KProperty kProperty = $$delegatedProperties[3];
        return (List) lazy.getValue();
    }

    public final List<Camera.Size> getPreviewResolutions() {
        Lazy lazy = this.previewResolutions;
        KProperty kProperty = $$delegatedProperties[2];
        return (List) lazy.getValue();
    }

    public final List<Integer> getSensorSensitivities() {
        Lazy lazy = this.sensorSensitivities;
        KProperty kProperty = $$delegatedProperties[5];
        return (List) lazy.getValue();
    }

    public final List<String> getSupportedAutoBandingModes() {
        Lazy lazy = this.supportedAutoBandingModes;
        KProperty kProperty = $$delegatedProperties[8];
        return (List) lazy.getValue();
    }

    public final List<int[]> getSupportedPreviewFpsRanges() {
        Lazy lazy = this.supportedPreviewFpsRanges;
        KProperty kProperty = $$delegatedProperties[4];
        return (List) lazy.getValue();
    }

    public final boolean getSupportedSmoothZoom() {
        Lazy lazy = this.supportedSmoothZoom;
        KProperty kProperty = $$delegatedProperties[7];
        return ((Boolean) lazy.getValue()).booleanValue();
    }

    public final Zoom getSupportedZoom() {
        Lazy lazy = this.supportedZoom;
        KProperty kProperty = $$delegatedProperties[6];
        return (Zoom) lazy.getValue();
    }

    public SupportedParameters(Camera.Parameters cameraParameters) {
        Intrinsics.checkParameterIsNotNull(cameraParameters, "cameraParameters");
        this.cameraParameters = cameraParameters;
        this.flashModes = LazyKt.lazy(new Function0<List<? extends String>>() { // from class: io.fotoapparat.parameter.SupportedParameters$flashModes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends String> invoke() {
                List<String> supportedFlashModes = this.this$0.cameraParameters.getSupportedFlashModes();
                return supportedFlashModes != null ? supportedFlashModes : CollectionsKt.listOf(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            }
        });
        this.focusModes = LazyKt.lazy(new Function0<List<String>>() { // from class: io.fotoapparat.parameter.SupportedParameters$focusModes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<String> invoke() {
                return this.this$0.cameraParameters.getSupportedFocusModes();
            }
        });
        this.previewResolutions = LazyKt.lazy(new Function0<List<Camera.Size>>() { // from class: io.fotoapparat.parameter.SupportedParameters$previewResolutions$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<Camera.Size> invoke() {
                return this.this$0.cameraParameters.getSupportedPreviewSizes();
            }
        });
        this.pictureResolutions = LazyKt.lazy(new Function0<List<Camera.Size>>() { // from class: io.fotoapparat.parameter.SupportedParameters$pictureResolutions$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<Camera.Size> invoke() {
                return this.this$0.cameraParameters.getSupportedPictureSizes();
            }
        });
        this.supportedPreviewFpsRanges = LazyKt.lazy(new Function0<List<int[]>>() { // from class: io.fotoapparat.parameter.SupportedParameters$supportedPreviewFpsRanges$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<int[]> invoke() {
                return this.this$0.cameraParameters.getSupportedPreviewFpsRange();
            }
        });
        this.sensorSensitivities = LazyKt.lazy(new Function0<List<? extends Integer>>() { // from class: io.fotoapparat.parameter.SupportedParameters$sensorSensitivities$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends Integer> invoke() {
                return ListConvertionsKt.toInts(RawValuesExtractorKt.extractRawCameraValues(this.this$0.cameraParameters, SupportedParametersKt.supportedSensitivitiesKeys));
            }
        });
        this.supportedZoom = LazyKt.lazy(new Function0<Zoom>() { // from class: io.fotoapparat.parameter.SupportedParameters$supportedZoom$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Zoom invoke() {
                Zoom variableZoom;
                if (this.this$0.cameraParameters.isZoomSupported()) {
                    int maxZoom = this.this$0.cameraParameters.getMaxZoom();
                    List<Integer> zoomRatios = this.this$0.cameraParameters.getZoomRatios();
                    Intrinsics.checkExpressionValueIsNotNull(zoomRatios, "cameraParameters.zoomRatios");
                    variableZoom = new Zoom.VariableZoom(maxZoom, zoomRatios);
                } else {
                    variableZoom = Zoom.FixedZoom.INSTANCE;
                }
                return variableZoom;
            }
        });
        this.supportedSmoothZoom = LazyKt.lazy(new Function0<Boolean>() { // from class: io.fotoapparat.parameter.SupportedParameters$supportedSmoothZoom$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                return this.this$0.cameraParameters.isSmoothZoomSupported();
            }
        });
        this.supportedAutoBandingModes = LazyKt.lazy(new Function0<List<? extends String>>() { // from class: io.fotoapparat.parameter.SupportedParameters$supportedAutoBandingModes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends String> invoke() {
                List<String> supportedAntibanding = this.this$0.cameraParameters.getSupportedAntibanding();
                return supportedAntibanding != null ? supportedAntibanding : CollectionsKt.listOf(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            }
        });
        this.jpegQualityRange = LazyKt.lazy(new Function0<IntRange>() { // from class: io.fotoapparat.parameter.SupportedParameters$jpegQualityRange$2
            @Override // kotlin.jvm.functions.Function0
            public final IntRange invoke() {
                return new IntRange(0, 100);
            }
        });
        this.exposureCompensationRange = LazyKt.lazy(new Function0<IntRange>() { // from class: io.fotoapparat.parameter.SupportedParameters$exposureCompensationRange$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final IntRange invoke() {
                return new IntRange(this.this$0.cameraParameters.getMinExposureCompensation(), this.this$0.cameraParameters.getMaxExposureCompensation());
            }
        });
        this.maxNumFocusAreas = LazyKt.lazy(new Function0<Integer>() { // from class: io.fotoapparat.parameter.SupportedParameters$maxNumFocusAreas$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                return this.this$0.cameraParameters.getMaxNumFocusAreas();
            }
        });
        this.maxNumMeteringAreas = LazyKt.lazy(new Function0<Integer>() { // from class: io.fotoapparat.parameter.SupportedParameters$maxNumMeteringAreas$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                return this.this$0.cameraParameters.getMaxNumMeteringAreas();
            }
        });
    }
}
