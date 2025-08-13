package io.fotoapparat.parameter.camera;

import com.google.android.gms.fitness.FitnessActivities;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.util.StringExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraParameters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\t\u0010(\u001a\u00020\fHÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010!J\t\u0010*\u001a\u00020\u000fHÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003Jj\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fHÆ\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\u0007HÖ\u0001J\b\u00102\u001a\u000203H\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!¨\u00064"}, d2 = {"Lio/fotoapparat/parameter/camera/CameraParameters;", "", "flashMode", "Lio/fotoapparat/parameter/Flash;", "focusMode", "Lio/fotoapparat/parameter/FocusMode;", "jpegQuality", "", "exposureCompensation", "previewFpsRange", "Lio/fotoapparat/parameter/FpsRange;", "antiBandingMode", "Lio/fotoapparat/parameter/AntiBandingMode;", "sensorSensitivity", "pictureResolution", "Lio/fotoapparat/parameter/Resolution;", "previewResolution", "(Lio/fotoapparat/parameter/Flash;Lio/fotoapparat/parameter/FocusMode;IILio/fotoapparat/parameter/FpsRange;Lio/fotoapparat/parameter/AntiBandingMode;Ljava/lang/Integer;Lio/fotoapparat/parameter/Resolution;Lio/fotoapparat/parameter/Resolution;)V", "getAntiBandingMode", "()Lio/fotoapparat/parameter/AntiBandingMode;", "getExposureCompensation", "()I", "getFlashMode", "()Lio/fotoapparat/parameter/Flash;", "getFocusMode", "()Lio/fotoapparat/parameter/FocusMode;", "getJpegQuality", "getPictureResolution", "()Lio/fotoapparat/parameter/Resolution;", "getPreviewFpsRange", "()Lio/fotoapparat/parameter/FpsRange;", "getPreviewResolution", "getSensorSensitivity", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lio/fotoapparat/parameter/Flash;Lio/fotoapparat/parameter/FocusMode;IILio/fotoapparat/parameter/FpsRange;Lio/fotoapparat/parameter/AntiBandingMode;Ljava/lang/Integer;Lio/fotoapparat/parameter/Resolution;Lio/fotoapparat/parameter/Resolution;)Lio/fotoapparat/parameter/camera/CameraParameters;", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class CameraParameters {
    private final AntiBandingMode antiBandingMode;
    private final int exposureCompensation;
    private final Flash flashMode;
    private final FocusMode focusMode;
    private final int jpegQuality;
    private final Resolution pictureResolution;
    private final FpsRange previewFpsRange;
    private final Resolution previewResolution;
    private final Integer sensorSensitivity;

    /* renamed from: component1, reason: from getter */
    public final Flash getFlashMode() {
        return this.flashMode;
    }

    /* renamed from: component2, reason: from getter */
    public final FocusMode getFocusMode() {
        return this.focusMode;
    }

    /* renamed from: component3, reason: from getter */
    public final int getJpegQuality() {
        return this.jpegQuality;
    }

    /* renamed from: component4, reason: from getter */
    public final int getExposureCompensation() {
        return this.exposureCompensation;
    }

    /* renamed from: component5, reason: from getter */
    public final FpsRange getPreviewFpsRange() {
        return this.previewFpsRange;
    }

    /* renamed from: component6, reason: from getter */
    public final AntiBandingMode getAntiBandingMode() {
        return this.antiBandingMode;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getSensorSensitivity() {
        return this.sensorSensitivity;
    }

    /* renamed from: component8, reason: from getter */
    public final Resolution getPictureResolution() {
        return this.pictureResolution;
    }

    /* renamed from: component9, reason: from getter */
    public final Resolution getPreviewResolution() {
        return this.previewResolution;
    }

    public final CameraParameters copy(Flash flashMode, FocusMode focusMode, int jpegQuality, int exposureCompensation, FpsRange previewFpsRange, AntiBandingMode antiBandingMode, Integer sensorSensitivity, Resolution pictureResolution, Resolution previewResolution) {
        Intrinsics.checkParameterIsNotNull(flashMode, "flashMode");
        Intrinsics.checkParameterIsNotNull(focusMode, "focusMode");
        Intrinsics.checkParameterIsNotNull(previewFpsRange, "previewFpsRange");
        Intrinsics.checkParameterIsNotNull(antiBandingMode, "antiBandingMode");
        Intrinsics.checkParameterIsNotNull(pictureResolution, "pictureResolution");
        Intrinsics.checkParameterIsNotNull(previewResolution, "previewResolution");
        return new CameraParameters(flashMode, focusMode, jpegQuality, exposureCompensation, previewFpsRange, antiBandingMode, sensorSensitivity, pictureResolution, previewResolution);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof CameraParameters) {
                CameraParameters cameraParameters = (CameraParameters) other;
                if (Intrinsics.areEqual(this.flashMode, cameraParameters.flashMode) && Intrinsics.areEqual(this.focusMode, cameraParameters.focusMode)) {
                    if (this.jpegQuality == cameraParameters.jpegQuality) {
                        if (!(this.exposureCompensation == cameraParameters.exposureCompensation) || !Intrinsics.areEqual(this.previewFpsRange, cameraParameters.previewFpsRange) || !Intrinsics.areEqual(this.antiBandingMode, cameraParameters.antiBandingMode) || !Intrinsics.areEqual(this.sensorSensitivity, cameraParameters.sensorSensitivity) || !Intrinsics.areEqual(this.pictureResolution, cameraParameters.pictureResolution) || !Intrinsics.areEqual(this.previewResolution, cameraParameters.previewResolution)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        Flash flash = this.flashMode;
        int iHashCode = (flash != null ? flash.hashCode() : 0) * 31;
        FocusMode focusMode = this.focusMode;
        int iHashCode2 = (((((iHashCode + (focusMode != null ? focusMode.hashCode() : 0)) * 31) + this.jpegQuality) * 31) + this.exposureCompensation) * 31;
        FpsRange fpsRange = this.previewFpsRange;
        int iHashCode3 = (iHashCode2 + (fpsRange != null ? fpsRange.hashCode() : 0)) * 31;
        AntiBandingMode antiBandingMode = this.antiBandingMode;
        int iHashCode4 = (iHashCode3 + (antiBandingMode != null ? antiBandingMode.hashCode() : 0)) * 31;
        Integer num = this.sensorSensitivity;
        int iHashCode5 = (iHashCode4 + (num != null ? num.hashCode() : 0)) * 31;
        Resolution resolution = this.pictureResolution;
        int iHashCode6 = (iHashCode5 + (resolution != null ? resolution.hashCode() : 0)) * 31;
        Resolution resolution2 = this.previewResolution;
        return iHashCode6 + (resolution2 != null ? resolution2.hashCode() : 0);
    }

    public CameraParameters(Flash flashMode, FocusMode focusMode, int i, int i2, FpsRange previewFpsRange, AntiBandingMode antiBandingMode, Integer num, Resolution pictureResolution, Resolution previewResolution) {
        Intrinsics.checkParameterIsNotNull(flashMode, "flashMode");
        Intrinsics.checkParameterIsNotNull(focusMode, "focusMode");
        Intrinsics.checkParameterIsNotNull(previewFpsRange, "previewFpsRange");
        Intrinsics.checkParameterIsNotNull(antiBandingMode, "antiBandingMode");
        Intrinsics.checkParameterIsNotNull(pictureResolution, "pictureResolution");
        Intrinsics.checkParameterIsNotNull(previewResolution, "previewResolution");
        this.flashMode = flashMode;
        this.focusMode = focusMode;
        this.jpegQuality = i;
        this.exposureCompensation = i2;
        this.previewFpsRange = previewFpsRange;
        this.antiBandingMode = antiBandingMode;
        this.sensorSensitivity = num;
        this.pictureResolution = pictureResolution;
        this.previewResolution = previewResolution;
    }

    public final Flash getFlashMode() {
        return this.flashMode;
    }

    public final FocusMode getFocusMode() {
        return this.focusMode;
    }

    public final int getJpegQuality() {
        return this.jpegQuality;
    }

    public final int getExposureCompensation() {
        return this.exposureCompensation;
    }

    public final FpsRange getPreviewFpsRange() {
        return this.previewFpsRange;
    }

    public final AntiBandingMode getAntiBandingMode() {
        return this.antiBandingMode;
    }

    public final Integer getSensorSensitivity() {
        return this.sensorSensitivity;
    }

    public final Resolution getPictureResolution() {
        return this.pictureResolution;
    }

    public final Resolution getPreviewResolution() {
        return this.previewResolution;
    }

    public String toString() {
        return "CameraParameters" + StringExtensionsKt.getLineSeparator() + "flashMode:" + StringExtensionsKt.wrap(this.flashMode) + "focusMode:" + StringExtensionsKt.wrap(this.focusMode) + "jpegQuality:" + StringExtensionsKt.wrap(Integer.valueOf(this.jpegQuality)) + "exposureCompensation:" + StringExtensionsKt.wrap(Integer.valueOf(this.exposureCompensation)) + "previewFpsRange:" + StringExtensionsKt.wrap(this.previewFpsRange) + "antiBandingMode:" + StringExtensionsKt.wrap(this.antiBandingMode) + "sensorSensitivity:" + StringExtensionsKt.wrap(this.sensorSensitivity) + "pictureResolution:" + StringExtensionsKt.wrap(this.pictureResolution) + "previewResolution:" + StringExtensionsKt.wrap(this.previewResolution);
    }
}
