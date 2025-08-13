package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.impl.Quirk;

/* loaded from: classes.dex */
public class AeFpsRangeLegacyQuirk implements Quirk {
    private final Range<Integer> mAeFpsRange;

    public AeFpsRangeLegacyQuirk(final CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.mAeFpsRange = pickSuitableFpsRange((Range[]) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
    }

    static boolean load(final CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        return num != null && num.intValue() == 2;
    }

    public Range<Integer> getRange() {
        return this.mAeFpsRange;
    }

    private Range<Integer> pickSuitableFpsRange(final Range<Integer>[] availableFpsRanges) {
        Range<Integer> range = null;
        if (availableFpsRanges != null && availableFpsRanges.length != 0) {
            for (Range<Integer> range2 : availableFpsRanges) {
                Range<Integer> correctedFpsRange = getCorrectedFpsRange(range2);
                if (((Integer) correctedFpsRange.getUpper()).intValue() == 30 && (range == null || ((Integer) correctedFpsRange.getLower()).intValue() < ((Integer) range.getLower()).intValue())) {
                    range = correctedFpsRange;
                }
            }
        }
        return range;
    }

    private Range<Integer> getCorrectedFpsRange(final Range<Integer> fpsRange) {
        int iIntValue = ((Integer) fpsRange.getUpper()).intValue();
        int iIntValue2 = ((Integer) fpsRange.getLower()).intValue();
        if (((Integer) fpsRange.getUpper()).intValue() >= 1000) {
            iIntValue = ((Integer) fpsRange.getUpper()).intValue() / 1000;
        }
        if (((Integer) fpsRange.getLower()).intValue() >= 1000) {
            iIntValue2 = ((Integer) fpsRange.getLower()).intValue() / 1000;
        }
        return new Range<>(Integer.valueOf(iIntValue2), Integer.valueOf(iIntValue));
    }
}
