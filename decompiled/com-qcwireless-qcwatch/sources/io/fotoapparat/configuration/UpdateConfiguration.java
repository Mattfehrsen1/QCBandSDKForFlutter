package io.fotoapparat.configuration;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.PointerIconCompat;
import com.google.android.gms.fitness.FitnessActivities;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.preview.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: UpdateConfiguration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000 C2\u00020\u0001:\u0002BCB©\u0003\u0012)\b\u0002\u0010\u0002\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006¢\u0006\u0002\b\u0007\u0012)\b\u0002\u0010\b\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0003j\u0004\u0018\u0001`\n¢\u0006\u0002\b\u0007\u0012#\b\u0002\u0010\u000b\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u000e¢\u0006\u0002\b\u0007\u0012#\b\u0002\u0010\u000f\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u0010¢\u0006\u0002\b\u0007\u0012+\b\u0002\u0010\u0011\u001a%\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003j\u0004\u0018\u0001`\u0017\u0012)\b\u0002\u0010\u0018\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u0003j\u0004\u0018\u0001`\u001a¢\u0006\u0002\b\u0007\u0012)\b\u0002\u0010\u001b\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u0003j\u0004\u0018\u0001`\u001d¢\u0006\u0002\b\u0007\u0012)\b\u0002\u0010\u001e\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u001f¢\u0006\u0002\b\u0007\u0012)\b\u0002\u0010 \u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007\u0012)\b\u0002\u0010#\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007¢\u0006\u0002\u0010$J*\u00100\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006¢\u0006\u0002\b\u0007HÆ\u0003J*\u00101\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007HÆ\u0003J*\u00102\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0003j\u0004\u0018\u0001`\n¢\u0006\u0002\b\u0007HÆ\u0003J$\u00103\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u000e¢\u0006\u0002\b\u0007HÆ\u0003J$\u00104\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u0010¢\u0006\u0002\b\u0007HÆ\u0003J,\u00105\u001a%\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003j\u0004\u0018\u0001`\u0017HÆ\u0003J*\u00106\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u0003j\u0004\u0018\u0001`\u001a¢\u0006\u0002\b\u0007HÆ\u0003J*\u00107\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u0003j\u0004\u0018\u0001`\u001d¢\u0006\u0002\b\u0007HÆ\u0003J*\u00108\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u001f¢\u0006\u0002\b\u0007HÆ\u0003J*\u00109\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007HÆ\u0003J\u00ad\u0003\u0010:\u001a\u00020\u00002)\b\u0002\u0010\u0002\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006¢\u0006\u0002\b\u00072)\b\u0002\u0010\b\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0003j\u0004\u0018\u0001`\n¢\u0006\u0002\b\u00072#\b\u0002\u0010\u000b\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u000e¢\u0006\u0002\b\u00072#\b\u0002\u0010\u000f\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u0010¢\u0006\u0002\b\u00072+\b\u0002\u0010\u0011\u001a%\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003j\u0004\u0018\u0001`\u00172)\b\u0002\u0010\u0018\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u0003j\u0004\u0018\u0001`\u001a¢\u0006\u0002\b\u00072)\b\u0002\u0010\u001b\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u0003j\u0004\u0018\u0001`\u001d¢\u0006\u0002\b\u00072)\b\u0002\u0010\u001e\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u001f¢\u0006\u0002\b\u00072)\b\u0002\u0010 \u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u00072)\b\u0002\u0010#\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007HÆ\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>HÖ\u0003J\t\u0010?\u001a\u00020\rHÖ\u0001J\t\u0010@\u001a\u00020AHÖ\u0001R5\u0010\u001b\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u0003j\u0004\u0018\u0001`\u001d¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R/\u0010\u000f\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u0010¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R5\u0010\u0002\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R5\u0010\b\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0003j\u0004\u0018\u0001`\n¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R7\u0010\u0011\u001a%\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003j\u0004\u0018\u0001`\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R/\u0010\u000b\u001a\u001d\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u000e¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R5\u0010#\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010&R5\u0010\u0018\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u0003j\u0004\u0018\u0001`\u001a¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R5\u0010 \u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u0004\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0003j\u0004\u0018\u0001`\"¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010&R5\u0010\u001e\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0003j\u0004\u0018\u0001`\u001f¢\u0006\u0002\b\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&¨\u0006D"}, d2 = {"Lio/fotoapparat/configuration/UpdateConfiguration;", "Lio/fotoapparat/configuration/Configuration;", "flashMode", "Lkotlin/Function1;", "", "Lio/fotoapparat/parameter/Flash;", "Lio/fotoapparat/selector/FlashSelector;", "Lkotlin/ExtensionFunctionType;", "focusMode", "Lio/fotoapparat/parameter/FocusMode;", "Lio/fotoapparat/selector/FocusModeSelector;", "jpegQuality", "Lkotlin/ranges/IntRange;", "", "Lio/fotoapparat/selector/QualitySelector;", "exposureCompensation", "Lio/fotoapparat/selector/ExposureSelector;", "frameProcessor", "Lio/fotoapparat/preview/Frame;", "Lkotlin/ParameterName;", "name", TypedValues.AttributesType.S_FRAME, "", "Lio/fotoapparat/util/FrameProcessor;", "previewFpsRange", "Lio/fotoapparat/parameter/FpsRange;", "Lio/fotoapparat/selector/FpsRangeSelector;", "antiBandingMode", "Lio/fotoapparat/parameter/AntiBandingMode;", "Lio/fotoapparat/selector/AntiBandingModeSelector;", "sensorSensitivity", "Lio/fotoapparat/selector/SensorSensitivitySelector;", "previewResolution", "Lio/fotoapparat/parameter/Resolution;", "Lio/fotoapparat/selector/ResolutionSelector;", "pictureResolution", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getAntiBandingMode", "()Lkotlin/jvm/functions/Function1;", "getExposureCompensation", "getFlashMode", "getFocusMode", "getFrameProcessor", "getJpegQuality", "getPictureResolution", "getPreviewFpsRange", "getPreviewResolution", "getSensorSensitivity", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "Builder", "Companion", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class UpdateConfiguration implements Configuration {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Function1<Iterable<? extends AntiBandingMode>, AntiBandingMode> antiBandingMode;
    private final Function1<IntRange, Integer> exposureCompensation;
    private final Function1<Iterable<? extends Flash>, Flash> flashMode;
    private final Function1<Iterable<? extends FocusMode>, FocusMode> focusMode;
    private final Function1<Frame, Unit> frameProcessor;
    private final Function1<IntRange, Integer> jpegQuality;
    private final Function1<Iterable<Resolution>, Resolution> pictureResolution;
    private final Function1<Iterable<FpsRange>, FpsRange> previewFpsRange;
    private final Function1<Iterable<Resolution>, Resolution> previewResolution;
    private final Function1<Iterable<Integer>, Integer> sensorSensitivity;

    public UpdateConfiguration() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @JvmStatic
    public static final Builder builder() {
        return INSTANCE.builder();
    }

    public static /* synthetic */ UpdateConfiguration copy$default(UpdateConfiguration updateConfiguration, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, Function1 function17, Function1 function18, Function1 function19, Function1 function110, int i, Object obj) {
        return updateConfiguration.copy((i & 1) != 0 ? updateConfiguration.getFlashMode() : function1, (i & 2) != 0 ? updateConfiguration.getFocusMode() : function12, (i & 4) != 0 ? updateConfiguration.getJpegQuality() : function13, (i & 8) != 0 ? updateConfiguration.getExposureCompensation() : function14, (i & 16) != 0 ? updateConfiguration.getFrameProcessor() : function15, (i & 32) != 0 ? updateConfiguration.getPreviewFpsRange() : function16, (i & 64) != 0 ? updateConfiguration.getAntiBandingMode() : function17, (i & 128) != 0 ? updateConfiguration.getSensorSensitivity() : function18, (i & 256) != 0 ? updateConfiguration.getPreviewResolution() : function19, (i & 512) != 0 ? updateConfiguration.getPictureResolution() : function110);
    }

    public final Function1<Iterable<? extends Flash>, Flash> component1() {
        return getFlashMode();
    }

    public final Function1<Iterable<Resolution>, Resolution> component10() {
        return getPictureResolution();
    }

    public final Function1<Iterable<? extends FocusMode>, FocusMode> component2() {
        return getFocusMode();
    }

    public final Function1<IntRange, Integer> component3() {
        return getJpegQuality();
    }

    public final Function1<IntRange, Integer> component4() {
        return getExposureCompensation();
    }

    public final Function1<Frame, Unit> component5() {
        return getFrameProcessor();
    }

    public final Function1<Iterable<FpsRange>, FpsRange> component6() {
        return getPreviewFpsRange();
    }

    public final Function1<Iterable<? extends AntiBandingMode>, AntiBandingMode> component7() {
        return getAntiBandingMode();
    }

    public final Function1<Iterable<Integer>, Integer> component8() {
        return getSensorSensitivity();
    }

    public final Function1<Iterable<Resolution>, Resolution> component9() {
        return getPreviewResolution();
    }

    public final UpdateConfiguration copy(Function1<? super Iterable<? extends Flash>, ? extends Flash> flashMode, Function1<? super Iterable<? extends FocusMode>, ? extends FocusMode> focusMode, Function1<? super IntRange, Integer> jpegQuality, Function1<? super IntRange, Integer> exposureCompensation, Function1<? super Frame, Unit> frameProcessor, Function1<? super Iterable<FpsRange>, FpsRange> previewFpsRange, Function1<? super Iterable<? extends AntiBandingMode>, ? extends AntiBandingMode> antiBandingMode, Function1<? super Iterable<Integer>, Integer> sensorSensitivity, Function1<? super Iterable<Resolution>, Resolution> previewResolution, Function1<? super Iterable<Resolution>, Resolution> pictureResolution) {
        return new UpdateConfiguration(flashMode, focusMode, jpegQuality, exposureCompensation, frameProcessor, previewFpsRange, antiBandingMode, sensorSensitivity, previewResolution, pictureResolution);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateConfiguration)) {
            return false;
        }
        UpdateConfiguration updateConfiguration = (UpdateConfiguration) other;
        return Intrinsics.areEqual(getFlashMode(), updateConfiguration.getFlashMode()) && Intrinsics.areEqual(getFocusMode(), updateConfiguration.getFocusMode()) && Intrinsics.areEqual(getJpegQuality(), updateConfiguration.getJpegQuality()) && Intrinsics.areEqual(getExposureCompensation(), updateConfiguration.getExposureCompensation()) && Intrinsics.areEqual(getFrameProcessor(), updateConfiguration.getFrameProcessor()) && Intrinsics.areEqual(getPreviewFpsRange(), updateConfiguration.getPreviewFpsRange()) && Intrinsics.areEqual(getAntiBandingMode(), updateConfiguration.getAntiBandingMode()) && Intrinsics.areEqual(getSensorSensitivity(), updateConfiguration.getSensorSensitivity()) && Intrinsics.areEqual(getPreviewResolution(), updateConfiguration.getPreviewResolution()) && Intrinsics.areEqual(getPictureResolution(), updateConfiguration.getPictureResolution());
    }

    public int hashCode() {
        Function1<Iterable<? extends Flash>, Flash> flashMode = getFlashMode();
        int iHashCode = (flashMode != null ? flashMode.hashCode() : 0) * 31;
        Function1<Iterable<? extends FocusMode>, FocusMode> focusMode = getFocusMode();
        int iHashCode2 = (iHashCode + (focusMode != null ? focusMode.hashCode() : 0)) * 31;
        Function1<IntRange, Integer> jpegQuality = getJpegQuality();
        int iHashCode3 = (iHashCode2 + (jpegQuality != null ? jpegQuality.hashCode() : 0)) * 31;
        Function1<IntRange, Integer> exposureCompensation = getExposureCompensation();
        int iHashCode4 = (iHashCode3 + (exposureCompensation != null ? exposureCompensation.hashCode() : 0)) * 31;
        Function1<Frame, Unit> frameProcessor = getFrameProcessor();
        int iHashCode5 = (iHashCode4 + (frameProcessor != null ? frameProcessor.hashCode() : 0)) * 31;
        Function1<Iterable<FpsRange>, FpsRange> previewFpsRange = getPreviewFpsRange();
        int iHashCode6 = (iHashCode5 + (previewFpsRange != null ? previewFpsRange.hashCode() : 0)) * 31;
        Function1<Iterable<? extends AntiBandingMode>, AntiBandingMode> antiBandingMode = getAntiBandingMode();
        int iHashCode7 = (iHashCode6 + (antiBandingMode != null ? antiBandingMode.hashCode() : 0)) * 31;
        Function1<Iterable<Integer>, Integer> sensorSensitivity = getSensorSensitivity();
        int iHashCode8 = (iHashCode7 + (sensorSensitivity != null ? sensorSensitivity.hashCode() : 0)) * 31;
        Function1<Iterable<Resolution>, Resolution> previewResolution = getPreviewResolution();
        int iHashCode9 = (iHashCode8 + (previewResolution != null ? previewResolution.hashCode() : 0)) * 31;
        Function1<Iterable<Resolution>, Resolution> pictureResolution = getPictureResolution();
        return iHashCode9 + (pictureResolution != null ? pictureResolution.hashCode() : 0);
    }

    public String toString() {
        return "UpdateConfiguration(flashMode=" + getFlashMode() + ", focusMode=" + getFocusMode() + ", jpegQuality=" + getJpegQuality() + ", exposureCompensation=" + getExposureCompensation() + ", frameProcessor=" + getFrameProcessor() + ", previewFpsRange=" + getPreviewFpsRange() + ", antiBandingMode=" + getAntiBandingMode() + ", sensorSensitivity=" + getSensorSensitivity() + ", previewResolution=" + getPreviewResolution() + ", pictureResolution=" + getPictureResolution() + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UpdateConfiguration(Function1<? super Iterable<? extends Flash>, ? extends Flash> function1, Function1<? super Iterable<? extends FocusMode>, ? extends FocusMode> function12, Function1<? super IntRange, Integer> function13, Function1<? super IntRange, Integer> function14, Function1<? super Frame, Unit> function15, Function1<? super Iterable<FpsRange>, FpsRange> function16, Function1<? super Iterable<? extends AntiBandingMode>, ? extends AntiBandingMode> function17, Function1<? super Iterable<Integer>, Integer> function18, Function1<? super Iterable<Resolution>, Resolution> function19, Function1<? super Iterable<Resolution>, Resolution> function110) {
        this.flashMode = function1;
        this.focusMode = function12;
        this.jpegQuality = function13;
        this.exposureCompensation = function14;
        this.frameProcessor = function15;
        this.previewFpsRange = function16;
        this.antiBandingMode = function17;
        this.sensorSensitivity = function18;
        this.previewResolution = function19;
        this.pictureResolution = function110;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ UpdateConfiguration(Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, Function1 function17, Function1 function18, Function1 function19, Function1 function110, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Function1 function111;
        Function1 function112;
        Function1 function113;
        Function1 function114;
        Function1 function115;
        Function1 function116;
        Function1 function117;
        Function1 function118;
        Function1 function119;
        Function1 function120 = null;
        if ((i & 1) != 0) {
            function111 = null;
        } else {
            function111 = function1;
        }
        if ((i & 2) != 0) {
            function112 = null;
        } else {
            function112 = function12;
        }
        if ((i & 4) != 0) {
            function113 = null;
        } else {
            function113 = function13;
        }
        if ((i & 8) != 0) {
            function114 = null;
        } else {
            function114 = function14;
        }
        if ((i & 16) != 0) {
            function115 = null;
        } else {
            function115 = function15;
        }
        if ((i & 32) != 0) {
            function116 = null;
        } else {
            function116 = function16;
        }
        if ((i & 64) != 0) {
            function117 = null;
        } else {
            function117 = function17;
        }
        if ((i & 128) != 0) {
            function118 = null;
        } else {
            function118 = function18;
        }
        if ((i & 256) != 0) {
            function119 = null;
        } else {
            function119 = function19;
        }
        if ((i & 512) != 0) {
        } else {
            function120 = function110;
        }
        this(function111, function112, function113, function114, function115, function116, function117, function118, function119, function120);
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<? extends Flash>, Flash> getFlashMode() {
        return this.flashMode;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<? extends FocusMode>, FocusMode> getFocusMode() {
        return this.focusMode;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<IntRange, Integer> getJpegQuality() {
        return this.jpegQuality;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<IntRange, Integer> getExposureCompensation() {
        return this.exposureCompensation;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Frame, Unit> getFrameProcessor() {
        return this.frameProcessor;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<FpsRange>, FpsRange> getPreviewFpsRange() {
        return this.previewFpsRange;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<? extends AntiBandingMode>, AntiBandingMode> getAntiBandingMode() {
        return this.antiBandingMode;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<Integer>, Integer> getSensorSensitivity() {
        return this.sensorSensitivity;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<Resolution>, Resolution> getPreviewResolution() {
        return this.previewResolution;
    }

    @Override // io.fotoapparat.configuration.Configuration
    public Function1<Iterable<Resolution>, Resolution> getPictureResolution() {
        return this.pictureResolution;
    }

    /* compiled from: UpdateConfiguration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J+\u0010\u0005\u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007j\u0002`\n¢\u0006\u0002\b\u000bJ\u0006\u0010\f\u001a\u00020\u0004J%\u0010\r\u001a\u00020\u00002\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0007j\u0002`\u0010¢\u0006\u0002\b\u000bJ+\u0010\u0011\u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0007j\u0002`\u0013¢\u0006\u0002\b\u000bJ+\u0010\u0014\u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\b\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0007j\u0002`\u0016¢\u0006\u0002\b\u000bJ1\u0010\u0017\u001a\u00020\u00002)\u0010\u0017\u001a%\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0007j\u0004\u0018\u0001`\u001dJ%\u0010\u001e\u001a\u00020\u00002\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0007j\u0002`\u001f¢\u0006\u0002\b\u000bJ+\u0010 \u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\b\u0012\u0006\u0012\u0004\u0018\u00010!0\u0007j\u0002`\"¢\u0006\u0002\b\u000bJ+\u0010#\u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\b\u0012\u0006\u0012\u0004\u0018\u00010$0\u0007j\u0002`%¢\u0006\u0002\b\u000bJ+\u0010&\u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\b\u0012\u0006\u0012\u0004\u0018\u00010!0\u0007j\u0002`\"¢\u0006\u0002\b\u000bJ+\u0010'\u001a\u00020\u00002#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\b\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0007j\u0002`(¢\u0006\u0002\b\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lio/fotoapparat/configuration/UpdateConfiguration$Builder;", "", "()V", "configuration", "Lio/fotoapparat/configuration/UpdateConfiguration;", "antiBandingMode", "selector", "Lkotlin/Function1;", "", "Lio/fotoapparat/parameter/AntiBandingMode;", "Lio/fotoapparat/selector/AntiBandingModeSelector;", "Lkotlin/ExtensionFunctionType;", "build", "exposureCompensation", "Lkotlin/ranges/IntRange;", "", "Lio/fotoapparat/selector/ExposureSelector;", "flash", "Lio/fotoapparat/parameter/Flash;", "Lio/fotoapparat/selector/FlashSelector;", "focusMode", "Lio/fotoapparat/parameter/FocusMode;", "Lio/fotoapparat/selector/FocusModeSelector;", "frameProcessor", "Lio/fotoapparat/preview/Frame;", "Lkotlin/ParameterName;", "name", TypedValues.AttributesType.S_FRAME, "", "Lio/fotoapparat/util/FrameProcessor;", "jpegQuality", "Lio/fotoapparat/selector/QualitySelector;", "photoResolution", "Lio/fotoapparat/parameter/Resolution;", "Lio/fotoapparat/selector/ResolutionSelector;", "previewFpsRange", "Lio/fotoapparat/parameter/FpsRange;", "Lio/fotoapparat/selector/FpsRangeSelector;", "previewResolution", "sensorSensitivity", "Lio/fotoapparat/selector/SensorSensitivitySelector;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Builder {
        private UpdateConfiguration configuration = new UpdateConfiguration(null, null, null, null, null, null, null, null, null, null, 1023, null);

        public final Builder flash(Function1<? super Iterable<? extends Flash>, ? extends Flash> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, selector, null, null, null, null, null, null, null, null, null, 1022, null);
            return this;
        }

        public final Builder focusMode(Function1<? super Iterable<? extends FocusMode>, ? extends FocusMode> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, selector, null, null, null, null, null, null, null, null, PointerIconCompat.TYPE_GRABBING, null);
            return this;
        }

        public final Builder previewFpsRange(Function1<? super Iterable<FpsRange>, FpsRange> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, null, null, selector, null, null, null, null, 991, null);
            return this;
        }

        public final Builder sensorSensitivity(Function1<? super Iterable<Integer>, Integer> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, null, null, null, null, selector, null, null, 895, null);
            return this;
        }

        public final Builder antiBandingMode(Function1<? super Iterable<? extends AntiBandingMode>, ? extends AntiBandingMode> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, null, null, null, selector, null, null, null, 959, null);
            return this;
        }

        public final Builder jpegQuality(Function1<? super IntRange, Integer> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, selector, null, null, null, null, null, null, null, PointerIconCompat.TYPE_ZOOM_OUT, null);
            return this;
        }

        public final Builder exposureCompensation(Function1<? super IntRange, Integer> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, selector, null, null, null, null, null, null, 1015, null);
            return this;
        }

        public final Builder previewResolution(Function1<? super Iterable<Resolution>, Resolution> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, null, null, null, null, null, selector, null, 767, null);
            return this;
        }

        public final Builder photoResolution(Function1<? super Iterable<Resolution>, Resolution> selector) {
            Intrinsics.checkParameterIsNotNull(selector, "selector");
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, null, null, null, null, null, null, selector, FrameMetricsAggregator.EVERY_DURATION, null);
            return this;
        }

        public final Builder frameProcessor(Function1<? super Frame, Unit> frameProcessor) {
            this.configuration = UpdateConfiguration.copy$default(this.configuration, null, null, null, null, frameProcessor, null, null, null, null, null, 1007, null);
            return this;
        }

        /* renamed from: build, reason: from getter */
        public final UpdateConfiguration getConfiguration() {
            return this.configuration;
        }
    }

    /* compiled from: UpdateConfiguration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/configuration/UpdateConfiguration$Companion;", "", "()V", "builder", "Lio/fotoapparat/configuration/UpdateConfiguration$Builder;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }
    }
}
