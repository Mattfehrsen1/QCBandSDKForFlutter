package io.fotoapparat.hardware;

import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.configuration.Configuration;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.preview.Frame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: Device.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a=\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032#\u0010\u0004\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b¢\u0006\u0002\b\tH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0000¨\u0006\u000f"}, d2 = {"selectCamera", "Lio/fotoapparat/hardware/CameraDevice;", "availableCameras", "", "lensPositionSelector", "Lkotlin/Function1;", "", "Lio/fotoapparat/characteristic/LensPosition;", "Lio/fotoapparat/selector/LensPositionSelector;", "Lkotlin/ExtensionFunctionType;", "updateConfiguration", "Lio/fotoapparat/configuration/CameraConfiguration;", "savedConfiguration", "newConfiguration", "Lio/fotoapparat/configuration/Configuration;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class DeviceKt {
    public static final CameraConfiguration updateConfiguration(CameraConfiguration savedConfiguration, Configuration newConfiguration) {
        Intrinsics.checkParameterIsNotNull(savedConfiguration, "savedConfiguration");
        Intrinsics.checkParameterIsNotNull(newConfiguration, "newConfiguration");
        Function1<Iterable<? extends Flash>, Flash> flashMode = newConfiguration.getFlashMode();
        if (flashMode == null) {
            flashMode = savedConfiguration.getFlashMode();
        }
        Function1<Iterable<? extends Flash>, Flash> function1 = flashMode;
        Function1<Iterable<? extends FocusMode>, FocusMode> focusMode = newConfiguration.getFocusMode();
        if (focusMode == null) {
            focusMode = savedConfiguration.getFocusMode();
        }
        Function1<Iterable<? extends FocusMode>, FocusMode> function12 = focusMode;
        Function1 function13 = null;
        Function1<IntRange, Integer> exposureCompensation = newConfiguration.getExposureCompensation();
        if (exposureCompensation == null) {
            exposureCompensation = savedConfiguration.getExposureCompensation();
        }
        Function1<IntRange, Integer> function14 = exposureCompensation;
        Function1<Frame, Unit> frameProcessor = newConfiguration.getFrameProcessor();
        if (frameProcessor == null) {
            frameProcessor = savedConfiguration.getFrameProcessor();
        }
        Function1<Frame, Unit> function15 = frameProcessor;
        Function1<Iterable<FpsRange>, FpsRange> previewFpsRange = newConfiguration.getPreviewFpsRange();
        if (previewFpsRange == null) {
            previewFpsRange = savedConfiguration.getPreviewFpsRange();
        }
        Function1<Iterable<FpsRange>, FpsRange> function16 = previewFpsRange;
        Function1 function17 = null;
        Function1<Iterable<Integer>, Integer> sensorSensitivity = newConfiguration.getSensorSensitivity();
        if (sensorSensitivity == null) {
            sensorSensitivity = savedConfiguration.getSensorSensitivity();
        }
        Function1<Iterable<Integer>, Integer> function18 = sensorSensitivity;
        Function1<Iterable<Resolution>, Resolution> pictureResolution = newConfiguration.getPictureResolution();
        if (pictureResolution == null) {
            pictureResolution = savedConfiguration.getPictureResolution();
        }
        Function1<Iterable<Resolution>, Resolution> function19 = pictureResolution;
        Function1<Iterable<Resolution>, Resolution> previewResolution = newConfiguration.getPreviewResolution();
        return new CameraConfiguration(function1, function12, function13, function14, function15, function16, function17, function18, function19, previewResolution != null ? previewResolution : savedConfiguration.getPreviewResolution(), 68, null);
    }

    public static final CameraDevice selectCamera(List<? extends CameraDevice> availableCameras, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> lensPositionSelector) {
        Object next;
        Intrinsics.checkParameterIsNotNull(availableCameras, "availableCameras");
        Intrinsics.checkParameterIsNotNull(lensPositionSelector, "lensPositionSelector");
        List<? extends CameraDevice> list = availableCameras;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((CameraDevice) it.next()).getCharacteristics().getLensPosition());
        }
        LensPosition lensPositionInvoke = lensPositionSelector.invoke(CollectionsKt.toSet(arrayList));
        Iterator<T> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            if (Intrinsics.areEqual(((CameraDevice) next).getCharacteristics().getLensPosition(), lensPositionInvoke)) {
                break;
            }
        }
        return (CameraDevice) next;
    }
}
