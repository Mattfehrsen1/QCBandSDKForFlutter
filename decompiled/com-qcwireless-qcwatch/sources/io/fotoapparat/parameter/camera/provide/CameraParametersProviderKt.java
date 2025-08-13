package io.fotoapparat.parameter.camera.provide;

import androidx.exifinterface.media.ExifInterface;
import io.fotoapparat.capability.Capabilities;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.exception.camera.InvalidConfigurationException;
import io.fotoapparat.exception.camera.UnsupportedConfigurationException;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Parameter;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.camera.CameraParameters;
import io.fotoapparat.selector.AspectRatioSelectorsKt;
import io.fotoapparat.selector.SelectorsKt;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;

/* compiled from: CameraParametersProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001aN\u0010\u0006\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0002\b\n2\u0006\u0010\u000b\u001a\u00020\t2#\u0010\f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007j\u0002`\r¢\u0006\u0002\b\nH\u0002\u001a,\u0010\u000e\u001a\u0002H\u000f\"\n\b\u0000\u0010\u000f\u0018\u0001*\u00020\u0010*\u0002H\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0012H\u0082\b¢\u0006\u0002\u0010\u0013\u001a2\u0010\u000e\u001a\u0002H\u000f\"\u0010\b\u0000\u0010\u000f\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u000f0\u0014*\u0002H\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0016H\u0082\b¢\u0006\u0002\u0010\u0017\u001a.\u0010\u0018\u001a\u0002H\u000f\"\n\b\u0000\u0010\u000f\u0018\u0001*\u00020\u0010*\u0004\u0018\u0001H\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019H\u0082\b¢\u0006\u0002\u0010\u001a\u001a<\u0010\u0018\u001a\u0002H\u000f\"\u0010\b\u0000\u0010\u000f\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u000f0\u0014*\u0004\u0018\u0001H\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0082\b¢\u0006\u0002\u0010\u001d\u001a,\u0010\u001e\u001a\u00020\u001f*\u0019\u0012\u0004\u0012\u00020 \u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0007j\u0002`!¢\u0006\u0002\b\n2\u0006\u0010\u0011\u001a\u00020 H\u0082\u0004\u001aE\u0010\u001e\u001a\u0002H\"\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0010*\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"0\u0019\u0012\u0006\u0012\u0004\u0018\u0001H\"0\u0007¢\u0006\u0002\b\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\"0\u0012H\u0082\f¢\u0006\u0002\u0010#\u001aC\u0010$\u001a\u0004\u0018\u0001H\"\"\u0004\b\u0000\u0010\"*\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"0\u0019\u0012\u0006\u0012\u0004\u0018\u0001H\"\u0018\u00010\u0007¢\u0006\u0002\b\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\"0\u0012H\u0082\u0004¢\u0006\u0002\u0010%¨\u0006&"}, d2 = {"getCameraParameters", "Lio/fotoapparat/parameter/camera/CameraParameters;", "capabilities", "Lio/fotoapparat/capability/Capabilities;", "cameraConfiguration", "Lio/fotoapparat/configuration/CameraConfiguration;", "validPreviewSizeSelector", "Lkotlin/Function1;", "", "Lio/fotoapparat/parameter/Resolution;", "Lkotlin/ExtensionFunctionType;", "resolution", "original", "Lio/fotoapparat/selector/ResolutionSelector;", "ensureInCollection", "Param", "Lio/fotoapparat/parameter/Parameter;", "supportedParameters", "", "(Lio/fotoapparat/parameter/Parameter;Ljava/util/Set;)Lio/fotoapparat/parameter/Parameter;", "", "supportedRange", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "ensureSelected", "", "(Lio/fotoapparat/parameter/Parameter;Ljava/util/Collection;)Lio/fotoapparat/parameter/Parameter;", "configurationName", "", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;Ljava/lang/String;)Ljava/lang/Comparable;", "selectFrom", "", "Lkotlin/ranges/IntRange;", "Lio/fotoapparat/selector/QualitySelector;", ExifInterface.GPS_DIRECTION_TRUE, "(Lkotlin/jvm/functions/Function1;Ljava/util/Set;)Lio/fotoapparat/parameter/Parameter;", "selectOptionalFrom", "(Lkotlin/jvm/functions/Function1;Ljava/util/Set;)Ljava/lang/Object;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraParametersProviderKt {
    public static final CameraParameters getCameraParameters(Capabilities capabilities, CameraConfiguration cameraConfiguration) {
        Intrinsics.checkParameterIsNotNull(capabilities, "capabilities");
        Intrinsics.checkParameterIsNotNull(cameraConfiguration, "cameraConfiguration");
        Function1<Iterable<Resolution>, Resolution> pictureResolution = cameraConfiguration.getPictureResolution();
        Set<Resolution> pictureResolutions = capabilities.getPictureResolutions();
        Resolution resolutionInvoke = pictureResolution.invoke(pictureResolutions);
        if (resolutionInvoke == null) {
            throw new UnsupportedConfigurationException((Class<? extends Parameter>) Resolution.class, pictureResolutions);
        }
        if (pictureResolutions.contains(resolutionInvoke)) {
            Resolution resolution = resolutionInvoke;
            Function1<Iterable<Resolution>, Resolution> function1ValidPreviewSizeSelector = validPreviewSizeSelector(resolution, cameraConfiguration.getPreviewResolution());
            Function1<Iterable<? extends Flash>, Flash> flashMode = cameraConfiguration.getFlashMode();
            Set<Flash> flashModes = capabilities.getFlashModes();
            Flash flashInvoke = flashMode.invoke(flashModes);
            if (flashInvoke == null) {
                throw new UnsupportedConfigurationException((Class<? extends Parameter>) Flash.class, flashModes);
            }
            if (flashModes.contains(flashInvoke)) {
                Flash flash = flashInvoke;
                Function1<Iterable<? extends FocusMode>, FocusMode> focusMode = cameraConfiguration.getFocusMode();
                Set<FocusMode> focusModes = capabilities.getFocusModes();
                FocusMode focusModeInvoke = focusMode.invoke(focusModes);
                if (focusModeInvoke == null) {
                    throw new UnsupportedConfigurationException((Class<? extends Parameter>) FocusMode.class, focusModes);
                }
                if (focusModes.contains(focusModeInvoke)) {
                    FocusMode focusMode2 = focusModeInvoke;
                    int iSelectFrom = selectFrom(cameraConfiguration.getJpegQuality(), capabilities.getJpegQualityRange());
                    int iSelectFrom2 = selectFrom(cameraConfiguration.getExposureCompensation(), capabilities.getExposureCompensationRange());
                    Function1<Iterable<FpsRange>, FpsRange> previewFpsRange = cameraConfiguration.getPreviewFpsRange();
                    Set<FpsRange> previewFpsRanges = capabilities.getPreviewFpsRanges();
                    FpsRange fpsRangeInvoke = previewFpsRange.invoke(previewFpsRanges);
                    if (fpsRangeInvoke == null) {
                        throw new UnsupportedConfigurationException((Class<? extends Parameter>) FpsRange.class, previewFpsRanges);
                    }
                    if (previewFpsRanges.contains(fpsRangeInvoke)) {
                        FpsRange fpsRange = fpsRangeInvoke;
                        Function1<Iterable<? extends AntiBandingMode>, AntiBandingMode> antiBandingMode = cameraConfiguration.getAntiBandingMode();
                        Set<AntiBandingMode> antiBandingModes = capabilities.getAntiBandingModes();
                        AntiBandingMode antiBandingModeInvoke = antiBandingMode.invoke(antiBandingModes);
                        if (antiBandingModeInvoke == null) {
                            throw new UnsupportedConfigurationException((Class<? extends Parameter>) AntiBandingMode.class, antiBandingModes);
                        }
                        if (antiBandingModes.contains(antiBandingModeInvoke)) {
                            AntiBandingMode antiBandingMode2 = antiBandingModeInvoke;
                            Set<Resolution> previewResolutions = capabilities.getPreviewResolutions();
                            Resolution resolutionInvoke2 = function1ValidPreviewSizeSelector.invoke(previewResolutions);
                            if (resolutionInvoke2 == null) {
                                throw new UnsupportedConfigurationException((Class<? extends Parameter>) Resolution.class, previewResolutions);
                            }
                            if (previewResolutions.contains(resolutionInvoke2)) {
                                return new CameraParameters(flash, focusMode2, iSelectFrom, iSelectFrom2, fpsRange, antiBandingMode2, (Integer) selectOptionalFrom(cameraConfiguration.getSensorSensitivity(), capabilities.getSensorSensitivities()), resolution, resolutionInvoke2);
                            }
                            throw new InvalidConfigurationException(resolutionInvoke2, (Class<? extends Parameter>) Resolution.class, previewResolutions);
                        }
                        throw new InvalidConfigurationException(antiBandingModeInvoke, (Class<? extends Parameter>) AntiBandingMode.class, antiBandingModes);
                    }
                    throw new InvalidConfigurationException(fpsRangeInvoke, (Class<? extends Parameter>) FpsRange.class, previewFpsRanges);
                }
                throw new InvalidConfigurationException(focusModeInvoke, (Class<? extends Parameter>) FocusMode.class, focusModes);
            }
            throw new InvalidConfigurationException(flashInvoke, (Class<? extends Parameter>) Flash.class, flashModes);
        }
        throw new InvalidConfigurationException(resolutionInvoke, (Class<? extends Parameter>) Resolution.class, pictureResolutions);
    }

    private static final Function1<Iterable<Resolution>, Resolution> validPreviewSizeSelector(final Resolution resolution, Function1<? super Iterable<Resolution>, Resolution> function1) {
        return SelectorsKt.firstAvailable(SelectorsKt.filtered(AspectRatioSelectorsKt.aspectRatio$default(resolution.getAspectRatio(), function1, 0.0d, 4, null), new Function1<Resolution, Boolean>() { // from class: io.fotoapparat.parameter.camera.provide.CameraParametersProviderKt.validPreviewSizeSelector.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Resolution resolution2) {
                return Boolean.valueOf(invoke2(resolution2));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(Resolution it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getArea() <= resolution.getArea();
            }
        }), function1);
    }

    private static final <T> T selectOptionalFrom(Function1<? super Collection<? extends T>, ? extends T> function1, Set<? extends T> set) {
        if (function1 != null) {
            return function1.invoke(set);
        }
        return null;
    }

    private static final <T extends Parameter> T selectFrom(Function1<? super Collection<? extends T>, ? extends T> function1, Set<? extends T> set) {
        T tInvoke = function1.invoke(set);
        if (tInvoke == null) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            throw new UnsupportedConfigurationException((Class<? extends Parameter>) Parameter.class, set);
        }
        if (set.contains(tInvoke)) {
            return tInvoke;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        throw new InvalidConfigurationException(tInvoke, (Class<? extends Parameter>) Parameter.class, set);
    }

    private static final int selectFrom(Function1<? super IntRange, Integer> function1, IntRange intRange) {
        Integer numInvoke = function1.invoke(intRange);
        if (numInvoke == null) {
            throw new UnsupportedConfigurationException("Jpeg quality", intRange);
        }
        IntRange intRange2 = intRange;
        if (!intRange2.contains((IntRange) numInvoke)) {
            throw new InvalidConfigurationException(numInvoke, (Class<? extends Comparable<?>>) Integer.class, intRange2);
        }
        return numInvoke.intValue();
    }

    private static final <Param extends Parameter> Param ensureInCollection(Param param, Set<? extends Param> set) {
        if (set.contains(param)) {
            return param;
        }
        Intrinsics.reifiedOperationMarker(4, "Param");
        throw new InvalidConfigurationException(param, (Class<? extends Parameter>) Parameter.class, set);
    }

    private static final <Param extends Comparable<? super Param>> Param ensureInCollection(Param param, ClosedRange<Param> closedRange) {
        if (closedRange.contains(param)) {
            return param;
        }
        Intrinsics.reifiedOperationMarker(4, "Param");
        throw new InvalidConfigurationException((Object) param, (Class<? extends Comparable<?>>) Comparable.class, (ClosedRange<?>) closedRange);
    }

    private static final <Param extends Parameter> Param ensureSelected(Param param, Collection<? extends Parameter> collection) {
        if (param != null) {
            return param;
        }
        Intrinsics.reifiedOperationMarker(4, "Param");
        throw new UnsupportedConfigurationException((Class<? extends Parameter>) Parameter.class, collection);
    }

    private static final <Param extends Comparable<? super Param>> Param ensureSelected(Param param, ClosedRange<Param> closedRange, String str) {
        if (param != null) {
            return param;
        }
        throw new UnsupportedConfigurationException(str, (ClosedRange<?>) closedRange);
    }
}
