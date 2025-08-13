package androidx.camera.camera2.internal.compat.workaround;

import androidx.camera.camera2.internal.compat.quirk.CrashWhenTakingPhotoWithAutoFlashAEModeQuirk;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;

/* loaded from: classes.dex */
public class AutoFlashAEModeDisabler {
    public int getCorrectedAeMode(int aeMode) {
        if (((CrashWhenTakingPhotoWithAutoFlashAEModeQuirk) DeviceQuirks.get(CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.class)) == null || aeMode != 2) {
            return aeMode;
        }
        return 1;
    }
}
