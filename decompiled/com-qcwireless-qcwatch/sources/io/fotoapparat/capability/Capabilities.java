package io.fotoapparat.capability;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.fitness.FitnessActivities;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.Zoom;
import io.fotoapparat.util.StringExtensionsKt;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: Capabilities.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0005\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0002\u0010\u0019J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00140\u0005HÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005HÆ\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\f0\u0005HÆ\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\fHÆ\u0003J\t\u00105\u001a\u00020\fHÆ\u0003J\t\u00106\u001a\u00020\u000fHÆ\u0003J\t\u00107\u001a\u00020\u000fHÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005HÆ\u0003Jµ\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00052\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00052\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00052\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00052\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0005HÆ\u0001J\u0013\u0010:\u001a\u00020\n2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\fHÖ\u0001J\b\u0010=\u001a\u00020>H\u0016R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001bR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001bR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006?"}, d2 = {"Lio/fotoapparat/capability/Capabilities;", "", "zoom", "Lio/fotoapparat/parameter/Zoom;", "flashModes", "", "Lio/fotoapparat/parameter/Flash;", "focusModes", "Lio/fotoapparat/parameter/FocusMode;", "canSmoothZoom", "", "maxFocusAreas", "", "maxMeteringAreas", "jpegQualityRange", "Lkotlin/ranges/IntRange;", "exposureCompensationRange", "previewFpsRanges", "Lio/fotoapparat/parameter/FpsRange;", "antiBandingModes", "Lio/fotoapparat/parameter/AntiBandingMode;", "pictureResolutions", "Lio/fotoapparat/parameter/Resolution;", "previewResolutions", "sensorSensitivities", "(Lio/fotoapparat/parameter/Zoom;Ljava/util/Set;Ljava/util/Set;ZIILkotlin/ranges/IntRange;Lkotlin/ranges/IntRange;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;)V", "getAntiBandingModes", "()Ljava/util/Set;", "getCanSmoothZoom", "()Z", "getExposureCompensationRange", "()Lkotlin/ranges/IntRange;", "getFlashModes", "getFocusModes", "getJpegQualityRange", "getMaxFocusAreas", "()I", "getMaxMeteringAreas", "getPictureResolutions", "getPreviewFpsRanges", "getPreviewResolutions", "getSensorSensitivities", "getZoom", "()Lio/fotoapparat/parameter/Zoom;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class Capabilities {
    private final Set<AntiBandingMode> antiBandingModes;
    private final boolean canSmoothZoom;
    private final IntRange exposureCompensationRange;
    private final Set<Flash> flashModes;
    private final Set<FocusMode> focusModes;
    private final IntRange jpegQualityRange;
    private final int maxFocusAreas;
    private final int maxMeteringAreas;
    private final Set<Resolution> pictureResolutions;
    private final Set<FpsRange> previewFpsRanges;
    private final Set<Resolution> previewResolutions;
    private final Set<Integer> sensorSensitivities;
    private final Zoom zoom;

    /* renamed from: component1, reason: from getter */
    public final Zoom getZoom() {
        return this.zoom;
    }

    public final Set<AntiBandingMode> component10() {
        return this.antiBandingModes;
    }

    public final Set<Resolution> component11() {
        return this.pictureResolutions;
    }

    public final Set<Resolution> component12() {
        return this.previewResolutions;
    }

    public final Set<Integer> component13() {
        return this.sensorSensitivities;
    }

    public final Set<Flash> component2() {
        return this.flashModes;
    }

    public final Set<FocusMode> component3() {
        return this.focusModes;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getCanSmoothZoom() {
        return this.canSmoothZoom;
    }

    /* renamed from: component5, reason: from getter */
    public final int getMaxFocusAreas() {
        return this.maxFocusAreas;
    }

    /* renamed from: component6, reason: from getter */
    public final int getMaxMeteringAreas() {
        return this.maxMeteringAreas;
    }

    /* renamed from: component7, reason: from getter */
    public final IntRange getJpegQualityRange() {
        return this.jpegQualityRange;
    }

    /* renamed from: component8, reason: from getter */
    public final IntRange getExposureCompensationRange() {
        return this.exposureCompensationRange;
    }

    public final Set<FpsRange> component9() {
        return this.previewFpsRanges;
    }

    public final Capabilities copy(Zoom zoom, Set<? extends Flash> flashModes, Set<? extends FocusMode> focusModes, boolean canSmoothZoom, int maxFocusAreas, int maxMeteringAreas, IntRange jpegQualityRange, IntRange exposureCompensationRange, Set<FpsRange> previewFpsRanges, Set<? extends AntiBandingMode> antiBandingModes, Set<Resolution> pictureResolutions, Set<Resolution> previewResolutions, Set<Integer> sensorSensitivities) {
        Intrinsics.checkParameterIsNotNull(zoom, "zoom");
        Intrinsics.checkParameterIsNotNull(flashModes, "flashModes");
        Intrinsics.checkParameterIsNotNull(focusModes, "focusModes");
        Intrinsics.checkParameterIsNotNull(jpegQualityRange, "jpegQualityRange");
        Intrinsics.checkParameterIsNotNull(exposureCompensationRange, "exposureCompensationRange");
        Intrinsics.checkParameterIsNotNull(previewFpsRanges, "previewFpsRanges");
        Intrinsics.checkParameterIsNotNull(antiBandingModes, "antiBandingModes");
        Intrinsics.checkParameterIsNotNull(pictureResolutions, "pictureResolutions");
        Intrinsics.checkParameterIsNotNull(previewResolutions, "previewResolutions");
        Intrinsics.checkParameterIsNotNull(sensorSensitivities, "sensorSensitivities");
        return new Capabilities(zoom, flashModes, focusModes, canSmoothZoom, maxFocusAreas, maxMeteringAreas, jpegQualityRange, exposureCompensationRange, previewFpsRanges, antiBandingModes, pictureResolutions, previewResolutions, sensorSensitivities);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof Capabilities) {
                Capabilities capabilities = (Capabilities) other;
                if (Intrinsics.areEqual(this.zoom, capabilities.zoom) && Intrinsics.areEqual(this.flashModes, capabilities.flashModes) && Intrinsics.areEqual(this.focusModes, capabilities.focusModes)) {
                    if (this.canSmoothZoom == capabilities.canSmoothZoom) {
                        if (this.maxFocusAreas == capabilities.maxFocusAreas) {
                            if (!(this.maxMeteringAreas == capabilities.maxMeteringAreas) || !Intrinsics.areEqual(this.jpegQualityRange, capabilities.jpegQualityRange) || !Intrinsics.areEqual(this.exposureCompensationRange, capabilities.exposureCompensationRange) || !Intrinsics.areEqual(this.previewFpsRanges, capabilities.previewFpsRanges) || !Intrinsics.areEqual(this.antiBandingModes, capabilities.antiBandingModes) || !Intrinsics.areEqual(this.pictureResolutions, capabilities.pictureResolutions) || !Intrinsics.areEqual(this.previewResolutions, capabilities.previewResolutions) || !Intrinsics.areEqual(this.sensorSensitivities, capabilities.sensorSensitivities)) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Zoom zoom = this.zoom;
        int iHashCode = (zoom != null ? zoom.hashCode() : 0) * 31;
        Set<Flash> set = this.flashModes;
        int iHashCode2 = (iHashCode + (set != null ? set.hashCode() : 0)) * 31;
        Set<FocusMode> set2 = this.focusModes;
        int iHashCode3 = (iHashCode2 + (set2 != null ? set2.hashCode() : 0)) * 31;
        boolean z = this.canSmoothZoom;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (((((iHashCode3 + i) * 31) + this.maxFocusAreas) * 31) + this.maxMeteringAreas) * 31;
        IntRange intRange = this.jpegQualityRange;
        int iHashCode4 = (i2 + (intRange != null ? intRange.hashCode() : 0)) * 31;
        IntRange intRange2 = this.exposureCompensationRange;
        int iHashCode5 = (iHashCode4 + (intRange2 != null ? intRange2.hashCode() : 0)) * 31;
        Set<FpsRange> set3 = this.previewFpsRanges;
        int iHashCode6 = (iHashCode5 + (set3 != null ? set3.hashCode() : 0)) * 31;
        Set<AntiBandingMode> set4 = this.antiBandingModes;
        int iHashCode7 = (iHashCode6 + (set4 != null ? set4.hashCode() : 0)) * 31;
        Set<Resolution> set5 = this.pictureResolutions;
        int iHashCode8 = (iHashCode7 + (set5 != null ? set5.hashCode() : 0)) * 31;
        Set<Resolution> set6 = this.previewResolutions;
        int iHashCode9 = (iHashCode8 + (set6 != null ? set6.hashCode() : 0)) * 31;
        Set<Integer> set7 = this.sensorSensitivities;
        return iHashCode9 + (set7 != null ? set7.hashCode() : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Capabilities(Zoom zoom, Set<? extends Flash> flashModes, Set<? extends FocusMode> focusModes, boolean z, int i, int i2, IntRange jpegQualityRange, IntRange exposureCompensationRange, Set<FpsRange> previewFpsRanges, Set<? extends AntiBandingMode> antiBandingModes, Set<Resolution> pictureResolutions, Set<Resolution> previewResolutions, Set<Integer> sensorSensitivities) {
        Intrinsics.checkParameterIsNotNull(zoom, "zoom");
        Intrinsics.checkParameterIsNotNull(flashModes, "flashModes");
        Intrinsics.checkParameterIsNotNull(focusModes, "focusModes");
        Intrinsics.checkParameterIsNotNull(jpegQualityRange, "jpegQualityRange");
        Intrinsics.checkParameterIsNotNull(exposureCompensationRange, "exposureCompensationRange");
        Intrinsics.checkParameterIsNotNull(previewFpsRanges, "previewFpsRanges");
        Intrinsics.checkParameterIsNotNull(antiBandingModes, "antiBandingModes");
        Intrinsics.checkParameterIsNotNull(pictureResolutions, "pictureResolutions");
        Intrinsics.checkParameterIsNotNull(previewResolutions, "previewResolutions");
        Intrinsics.checkParameterIsNotNull(sensorSensitivities, "sensorSensitivities");
        this.zoom = zoom;
        this.flashModes = flashModes;
        this.focusModes = focusModes;
        this.canSmoothZoom = z;
        this.maxFocusAreas = i;
        this.maxMeteringAreas = i2;
        this.jpegQualityRange = jpegQualityRange;
        this.exposureCompensationRange = exposureCompensationRange;
        this.previewFpsRanges = previewFpsRanges;
        this.antiBandingModes = antiBandingModes;
        this.pictureResolutions = pictureResolutions;
        this.previewResolutions = previewResolutions;
        this.sensorSensitivities = sensorSensitivities;
        if (flashModes.isEmpty()) {
            throw new IllegalArgumentException("Capabilities cannot have an empty Set<" + ExifInterface.TAG_FLASH + ">.");
        }
        if (focusModes.isEmpty()) {
            throw new IllegalArgumentException("Capabilities cannot have an empty Set<FocusMode>.");
        }
        if (antiBandingModes.isEmpty()) {
            throw new IllegalArgumentException("Capabilities cannot have an empty Set<AntiBandingMode>.");
        }
        if (previewFpsRanges.isEmpty()) {
            throw new IllegalArgumentException("Capabilities cannot have an empty Set<FpsRange>.");
        }
        if (pictureResolutions.isEmpty()) {
            throw new IllegalArgumentException("Capabilities cannot have an empty Set<Resolution>.");
        }
        if (previewResolutions.isEmpty()) {
            throw new IllegalArgumentException("Capabilities cannot have an empty Set<Resolution>.");
        }
    }

    public final Zoom getZoom() {
        return this.zoom;
    }

    public final Set<Flash> getFlashModes() {
        return this.flashModes;
    }

    public final Set<FocusMode> getFocusModes() {
        return this.focusModes;
    }

    public final boolean getCanSmoothZoom() {
        return this.canSmoothZoom;
    }

    public final int getMaxFocusAreas() {
        return this.maxFocusAreas;
    }

    public final int getMaxMeteringAreas() {
        return this.maxMeteringAreas;
    }

    public final IntRange getJpegQualityRange() {
        return this.jpegQualityRange;
    }

    public final IntRange getExposureCompensationRange() {
        return this.exposureCompensationRange;
    }

    public final Set<FpsRange> getPreviewFpsRanges() {
        return this.previewFpsRanges;
    }

    public final Set<AntiBandingMode> getAntiBandingModes() {
        return this.antiBandingModes;
    }

    public final Set<Resolution> getPictureResolutions() {
        return this.pictureResolutions;
    }

    public final Set<Resolution> getPreviewResolutions() {
        return this.previewResolutions;
    }

    public final Set<Integer> getSensorSensitivities() {
        return this.sensorSensitivities;
    }

    public String toString() {
        return "Capabilities" + StringExtensionsKt.getLineSeparator() + "zoom:" + StringExtensionsKt.wrap(this.zoom) + "flashModes:" + StringExtensionsKt.wrap((Set<? extends Object>) this.flashModes) + "focusModes:" + StringExtensionsKt.wrap((Set<? extends Object>) this.focusModes) + "canSmoothZoom:" + StringExtensionsKt.wrap(Boolean.valueOf(this.canSmoothZoom)) + "maxFocusAreas:" + StringExtensionsKt.wrap(Integer.valueOf(this.maxFocusAreas)) + "maxMeteringAreas:" + StringExtensionsKt.wrap(Integer.valueOf(this.maxMeteringAreas)) + "jpegQualityRange:" + StringExtensionsKt.wrap(this.jpegQualityRange) + "exposureCompensationRange:" + StringExtensionsKt.wrap(this.exposureCompensationRange) + "antiBandingModes:" + StringExtensionsKt.wrap((Set<? extends Object>) this.antiBandingModes) + "previewFpsRanges:" + StringExtensionsKt.wrap((Set<? extends Object>) this.previewFpsRanges) + "pictureResolutions:" + StringExtensionsKt.wrap((Set<? extends Object>) this.pictureResolutions) + "previewResolutions:" + StringExtensionsKt.wrap((Set<? extends Object>) this.previewResolutions) + "sensorSensitivities:" + StringExtensionsKt.wrap((Set<? extends Object>) this.sensorSensitivities);
    }
}
