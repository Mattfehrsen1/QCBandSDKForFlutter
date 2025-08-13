package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.impl.CameraInfoInternal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class CameraSelectionOptimizer {
    private CameraSelectionOptimizer() {
    }

    static List<String> getSelectedAvailableCameraIds(Camera2CameraFactory cameraFactory, CameraSelector availableCamerasSelector) throws InitializationException {
        String strDecideSkippedCameraIdByHeuristic;
        try {
            ArrayList arrayList = new ArrayList();
            List<String> listAsList = Arrays.asList(cameraFactory.getCameraManager().getCameraIdList());
            if (availableCamerasSelector == null) {
                Iterator it = listAsList.iterator();
                while (it.hasNext()) {
                    arrayList.add((String) it.next());
                }
                return arrayList;
            }
            try {
                strDecideSkippedCameraIdByHeuristic = decideSkippedCameraIdByHeuristic(cameraFactory.getCameraManager(), availableCamerasSelector.getLensFacing(), listAsList);
            } catch (IllegalStateException unused) {
                strDecideSkippedCameraIdByHeuristic = null;
            }
            ArrayList arrayList2 = new ArrayList();
            for (String str : listAsList) {
                if (!str.equals(strDecideSkippedCameraIdByHeuristic)) {
                    arrayList2.add(cameraFactory.getCameraInfo(str));
                }
            }
            try {
                Iterator<CameraInfo> it2 = availableCamerasSelector.filter(arrayList2).iterator();
                while (it2.hasNext()) {
                    arrayList.add(((CameraInfoInternal) it2.next()).getCameraId());
                }
            } catch (IllegalArgumentException unused2) {
            }
            return arrayList;
        } catch (CameraAccessExceptionCompat e) {
            throw new InitializationException(CameraUnavailableExceptionHelper.createFrom(e));
        } catch (CameraUnavailableException e2) {
            throw new InitializationException(e2);
        }
    }

    private static String decideSkippedCameraIdByHeuristic(CameraManagerCompat cameraManager, Integer lensFacingInteger, List<String> cameraIdList) throws CameraAccessExceptionCompat {
        if (lensFacingInteger == null || !cameraIdList.contains("0") || !cameraIdList.contains("1")) {
            return null;
        }
        if (lensFacingInteger.intValue() == 1) {
            if (((Integer) cameraManager.getCameraCharacteristicsCompat("0").get(CameraCharacteristics.LENS_FACING)).intValue() == 1) {
                return "1";
            }
            return null;
        }
        if (lensFacingInteger.intValue() == 0 && ((Integer) cameraManager.getCameraCharacteristicsCompat("1").get(CameraCharacteristics.LENS_FACING)).intValue() == 0) {
            return "0";
        }
        return null;
    }
}
