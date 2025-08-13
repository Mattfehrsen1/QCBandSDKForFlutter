package io.fotoapparat.configuration;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.preview.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;

/* compiled from: Configuration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R3\u0010\u0002\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR-\u0010\n\u001a\u001d\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0003j\u0004\u0018\u0001`\r¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR3\u0010\u000f\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u0003j\u0004\u0018\u0001`\u0011¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\tR3\u0010\u0013\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u0003j\u0004\u0018\u0001`\u0015¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\tR5\u0010\u0017\u001a%\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0003j\u0004\u0018\u0001`\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\tR-\u0010\u001f\u001a\u001d\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0003j\u0004\u0018\u0001` ¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\tR3\u0010\"\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u0004\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010\u0003j\u0004\u0018\u0001`$¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\tR3\u0010&\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u0004\u0012\u0006\u0012\u0004\u0018\u00010'\u0018\u00010\u0003j\u0004\u0018\u0001`(¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\tR3\u0010*\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u0004\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010\u0003j\u0004\u0018\u0001`$¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\tR3\u0010,\u001a#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0003j\u0004\u0018\u0001`-¢\u0006\u0002\b\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\t¨\u0006/"}, d2 = {"Lio/fotoapparat/configuration/Configuration;", "", "antiBandingMode", "Lkotlin/Function1;", "", "Lio/fotoapparat/parameter/AntiBandingMode;", "Lio/fotoapparat/selector/AntiBandingModeSelector;", "Lkotlin/ExtensionFunctionType;", "getAntiBandingMode", "()Lkotlin/jvm/functions/Function1;", "exposureCompensation", "Lkotlin/ranges/IntRange;", "", "Lio/fotoapparat/selector/ExposureSelector;", "getExposureCompensation", "flashMode", "Lio/fotoapparat/parameter/Flash;", "Lio/fotoapparat/selector/FlashSelector;", "getFlashMode", "focusMode", "Lio/fotoapparat/parameter/FocusMode;", "Lio/fotoapparat/selector/FocusModeSelector;", "getFocusMode", "frameProcessor", "Lio/fotoapparat/preview/Frame;", "Lkotlin/ParameterName;", "name", TypedValues.AttributesType.S_FRAME, "", "Lio/fotoapparat/util/FrameProcessor;", "getFrameProcessor", "jpegQuality", "Lio/fotoapparat/selector/QualitySelector;", "getJpegQuality", "pictureResolution", "Lio/fotoapparat/parameter/Resolution;", "Lio/fotoapparat/selector/ResolutionSelector;", "getPictureResolution", "previewFpsRange", "Lio/fotoapparat/parameter/FpsRange;", "Lio/fotoapparat/selector/FpsRangeSelector;", "getPreviewFpsRange", "previewResolution", "getPreviewResolution", "sensorSensitivity", "Lio/fotoapparat/selector/SensorSensitivitySelector;", "getSensorSensitivity", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface Configuration {
    Function1<Iterable<? extends AntiBandingMode>, AntiBandingMode> getAntiBandingMode();

    Function1<IntRange, Integer> getExposureCompensation();

    Function1<Iterable<? extends Flash>, Flash> getFlashMode();

    Function1<Iterable<? extends FocusMode>, FocusMode> getFocusMode();

    Function1<Frame, Unit> getFrameProcessor();

    Function1<IntRange, Integer> getJpegQuality();

    Function1<Iterable<Resolution>, Resolution> getPictureResolution();

    Function1<Iterable<FpsRange>, FpsRange> getPreviewFpsRange();

    Function1<Iterable<Resolution>, Resolution> getPreviewResolution();

    Function1<Iterable<Integer>, Integer> getSensorSensitivity();
}
