package io.fotoapparat.parameter.camera.apply;

import android.hardware.Camera;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.camera.CameraParameters;
import io.fotoapparat.parameter.camera.convert.AntiBandingConverterKt;
import io.fotoapparat.parameter.camera.convert.FlashConverterKt;
import io.fotoapparat.parameter.camera.convert.FocusModeConverterKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraParametersApplicator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0019\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u0019\u0010\t\u001a\u00020\u0004*\u00020\n2\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u0019\u0010\t\u001a\u00020\u0004*\u00020\u000b2\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u0019\u0010\t\u001a\u00020\u0004*\u00020\f2\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u0019\u0010\t\u001a\u00020\u0004*\u00020\r2\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u001c\u0010\t\u001a\u00060\u0007R\u00020\b*\u00020\u000e2\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0000\u001a\u0019\u0010\u000f\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u0019\u0010\u0010\u001a\u00020\u0004*\u00020\u00112\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a\u0019\u0010\u0012\u001a\u00020\u0004*\u00020\u00112\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\u001a \u0010\u0013\u001a\u00020\u0004*\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004¢\u0006\u0002\u0010\u0014\u001a\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002*\u00060\u0007R\u00020\bH\u0002\u001a\u0019\u0010\u0016\u001a\u00020\u0004*\u00020\u000e2\n\u0010\u0006\u001a\u00060\u0007R\u00020\bH\u0082\u0004\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"currentSensitivityKeys", "", "", "applyExposureCompensationInto", "", "", "parameters", "Landroid/hardware/Camera$Parameters;", "Landroid/hardware/Camera;", "applyInto", "Lio/fotoapparat/parameter/AntiBandingMode;", "Lio/fotoapparat/parameter/Flash;", "Lio/fotoapparat/parameter/FocusMode;", "Lio/fotoapparat/parameter/FpsRange;", "Lio/fotoapparat/parameter/camera/CameraParameters;", "applyJpegQualityInto", "applyPictureResolutionInto", "Lio/fotoapparat/parameter/Resolution;", "applyPreviewInto", "applySensitivityInto", "(Ljava/lang/Integer;Landroid/hardware/Camera$Parameters;)V", "findSensitivityKey", "tryApplyInto", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraParametersApplicatorKt {
    private static final List<String> currentSensitivityKeys = CollectionsKt.listOf((Object[]) new String[]{"iso", "iso-speed", "nv-picture-iso"});

    public static final Camera.Parameters applyInto(CameraParameters receiver$0, Camera.Parameters parameters) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        tryApplyInto(receiver$0, parameters);
        return parameters;
    }

    private static final void tryApplyInto(CameraParameters cameraParameters, Camera.Parameters parameters) {
        applyInto(cameraParameters.getFlashMode(), parameters);
        applyInto(cameraParameters.getFocusMode(), parameters);
        applyJpegQualityInto(cameraParameters.getJpegQuality(), parameters);
        applyExposureCompensationInto(cameraParameters.getExposureCompensation(), parameters);
        applyInto(cameraParameters.getAntiBandingMode(), parameters);
        applyInto(cameraParameters.getPreviewFpsRange(), parameters);
        applyPreviewInto(cameraParameters.getPreviewResolution(), parameters);
        applySensitivityInto(cameraParameters.getSensorSensitivity(), parameters);
        applyPictureResolutionInto(cameraParameters.getPictureResolution(), parameters);
    }

    private static final void applyInto(Flash flash, Camera.Parameters parameters) {
        parameters.setFlashMode(FlashConverterKt.toCode(flash));
    }

    private static final void applyInto(FocusMode focusMode, Camera.Parameters parameters) {
        parameters.setFocusMode(FocusModeConverterKt.toCode(focusMode));
    }

    private static final void applyJpegQualityInto(int i, Camera.Parameters parameters) {
        parameters.setJpegQuality(i);
    }

    private static final void applyExposureCompensationInto(int i, Camera.Parameters parameters) {
        parameters.setExposureCompensation(i);
    }

    private static final void applyInto(AntiBandingMode antiBandingMode, Camera.Parameters parameters) {
        parameters.setAntibanding(AntiBandingConverterKt.toCode(antiBandingMode));
    }

    private static final void applyInto(FpsRange fpsRange, Camera.Parameters parameters) {
        parameters.setPreviewFpsRange(fpsRange.getMin(), fpsRange.getMax());
    }

    private static final void applySensitivityInto(Integer num, Camera.Parameters parameters) {
        if (num != null) {
            int iIntValue = num.intValue();
            String strFindSensitivityKey = findSensitivityKey(parameters);
            if (strFindSensitivityKey != null) {
                parameters.set(strFindSensitivityKey, iIntValue);
            }
        }
    }

    private static final void applyPreviewInto(Resolution resolution, Camera.Parameters parameters) {
        parameters.setPreviewSize(resolution.width, resolution.height);
    }

    private static final void applyPictureResolutionInto(Resolution resolution, Camera.Parameters parameters) {
        parameters.setPictureSize(resolution.width, resolution.height);
    }

    private static final String findSensitivityKey(Camera.Parameters parameters) {
        Object next;
        Iterator<T> it = currentSensitivityKeys.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (parameters.get((String) next) != null) {
                break;
            }
        }
        return (String) next;
    }
}
