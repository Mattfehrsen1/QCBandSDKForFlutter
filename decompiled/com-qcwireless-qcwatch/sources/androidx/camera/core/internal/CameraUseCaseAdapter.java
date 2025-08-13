package androidx.camera.core.internal;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class CameraUseCaseAdapter implements Camera {
    private static final String TAG = "CameraUseCaseAdapter";
    private final CameraDeviceSurfaceManager mCameraDeviceSurfaceManager;
    private CameraInternal mCameraInternal;
    private final LinkedHashSet<CameraInternal> mCameraInternals;
    private final CameraId mId;
    private final UseCaseConfigFactory mUseCaseConfigFactory;
    private ViewPort mViewPort;
    private final List<UseCase> mUseCases = new ArrayList();
    private CameraConfig mCameraConfig = CameraConfigs.emptyConfig();
    private final Object mLock = new Object();
    private boolean mAttached = true;
    private Config mInteropConfig = null;

    public CameraUseCaseAdapter(LinkedHashSet<CameraInternal> cameras, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, UseCaseConfigFactory useCaseConfigFactory) {
        this.mCameraInternal = cameras.iterator().next();
        LinkedHashSet<CameraInternal> linkedHashSet = new LinkedHashSet<>(cameras);
        this.mCameraInternals = linkedHashSet;
        this.mId = new CameraId(linkedHashSet);
        this.mCameraDeviceSurfaceManager = cameraDeviceSurfaceManager;
        this.mUseCaseConfigFactory = useCaseConfigFactory;
    }

    public static CameraId generateCameraId(LinkedHashSet<CameraInternal> cameras) {
        return new CameraId(cameras);
    }

    public CameraId getCameraId() {
        return this.mId;
    }

    public boolean isEquivalent(CameraUseCaseAdapter cameraUseCaseAdapter) {
        return this.mId.equals(cameraUseCaseAdapter.getCameraId());
    }

    public void setViewPort(ViewPort viewPort) {
        synchronized (this.mLock) {
            this.mViewPort = viewPort;
        }
    }

    public void checkAttachUseCases(List<UseCase> useCases) throws CameraException {
        synchronized (this.mLock) {
            try {
                try {
                    calculateSuggestedResolutions(this.mCameraInternal.getCameraInfoInternal(), useCases, Collections.emptyList(), getConfigs(useCases, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory));
                } catch (IllegalArgumentException e) {
                    throw new CameraException(e.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addUseCases(Collection<UseCase> useCases) throws CameraException {
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList();
            for (UseCase useCase : useCases) {
                if (this.mUseCases.contains(useCase)) {
                    Logger.d(TAG, "Attempting to attach already attached UseCase");
                } else {
                    arrayList.add(useCase);
                }
            }
            Map<UseCase, ConfigPair> configs = getConfigs(arrayList, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory);
            try {
                Map<UseCase, Size> mapCalculateSuggestedResolutions = calculateSuggestedResolutions(this.mCameraInternal.getCameraInfoInternal(), arrayList, this.mUseCases, configs);
                updateViewPort(mapCalculateSuggestedResolutions, useCases);
                for (UseCase useCase2 : arrayList) {
                    ConfigPair configPair = configs.get(useCase2);
                    useCase2.onAttach(this.mCameraInternal, configPair.mExtendedConfig, configPair.mCameraConfig);
                    useCase2.updateSuggestedResolution((Size) Preconditions.checkNotNull(mapCalculateSuggestedResolutions.get(useCase2)));
                }
                this.mUseCases.addAll(arrayList);
                if (this.mAttached) {
                    this.mCameraInternal.attachUseCases(arrayList);
                }
                Iterator<UseCase> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().notifyState();
                }
            } catch (IllegalArgumentException e) {
                throw new CameraException(e.getMessage());
            }
        }
    }

    public void removeUseCases(Collection<UseCase> useCases) {
        synchronized (this.mLock) {
            this.mCameraInternal.detachUseCases(useCases);
            for (UseCase useCase : useCases) {
                if (this.mUseCases.contains(useCase)) {
                    useCase.onDetach(this.mCameraInternal);
                } else {
                    Logger.e(TAG, "Attempting to detach non-attached UseCase: " + useCase);
                }
            }
            this.mUseCases.removeAll(useCases);
        }
    }

    public List<UseCase> getUseCases() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mUseCases);
        }
        return arrayList;
    }

    public void attachUseCases() {
        synchronized (this.mLock) {
            if (!this.mAttached) {
                this.mCameraInternal.attachUseCases(this.mUseCases);
                restoreInteropConfig();
                Iterator<UseCase> it = this.mUseCases.iterator();
                while (it.hasNext()) {
                    it.next().notifyState();
                }
                this.mAttached = true;
            }
        }
    }

    public void detachUseCases() {
        synchronized (this.mLock) {
            if (this.mAttached) {
                cacheInteropConfig();
                this.mCameraInternal.detachUseCases(new ArrayList(this.mUseCases));
                this.mAttached = false;
            }
        }
    }

    private void restoreInteropConfig() {
        synchronized (this.mLock) {
            if (this.mInteropConfig != null) {
                this.mCameraInternal.getCameraControlInternal().addInteropConfig(this.mInteropConfig);
            }
        }
    }

    private void cacheInteropConfig() {
        synchronized (this.mLock) {
            CameraControlInternal cameraControlInternal = this.mCameraInternal.getCameraControlInternal();
            this.mInteropConfig = cameraControlInternal.getInteropConfig();
            cameraControlInternal.clearInteropConfig();
        }
    }

    private Map<UseCase, Size> calculateSuggestedResolutions(CameraInfoInternal cameraInfoInternal, List<UseCase> newUseCases, List<UseCase> currentUseCases, Map<UseCase, ConfigPair> configPairMap) {
        ArrayList arrayList = new ArrayList();
        String cameraId = cameraInfoInternal.getCameraId();
        HashMap map = new HashMap();
        for (UseCase useCase : currentUseCases) {
            arrayList.add(this.mCameraDeviceSurfaceManager.transformSurfaceConfig(cameraId, useCase.getImageFormat(), useCase.getAttachedSurfaceResolution()));
            map.put(useCase, useCase.getAttachedSurfaceResolution());
        }
        if (!newUseCases.isEmpty()) {
            HashMap map2 = new HashMap();
            for (UseCase useCase2 : newUseCases) {
                ConfigPair configPair = configPairMap.get(useCase2);
                map2.put(useCase2.mergeConfigs(cameraInfoInternal, configPair.mExtendedConfig, configPair.mCameraConfig), useCase2);
            }
            Map<UseCaseConfig<?>, Size> suggestedResolutions = this.mCameraDeviceSurfaceManager.getSuggestedResolutions(cameraId, arrayList, new ArrayList(map2.keySet()));
            for (Map.Entry entry : map2.entrySet()) {
                map.put((UseCase) entry.getValue(), suggestedResolutions.get(entry.getKey()));
            }
        }
        return map;
    }

    private void updateViewPort(Map<UseCase, Size> suggestedResolutionsMap, Collection<UseCase> useCases) {
        synchronized (this.mLock) {
            if (this.mViewPort != null) {
                Map<UseCase, Rect> mapCalculateViewPortRects = ViewPorts.calculateViewPortRects(this.mCameraInternal.getCameraControlInternal().getSensorRect(), this.mCameraInternal.getCameraInfoInternal().getLensFacing().intValue() == 0, this.mViewPort.getAspectRatio(), this.mCameraInternal.getCameraInfoInternal().getSensorRotationDegrees(this.mViewPort.getRotation()), this.mViewPort.getScaleType(), this.mViewPort.getLayoutDirection(), suggestedResolutionsMap);
                for (UseCase useCase : useCases) {
                    useCase.setViewPortCropRect((Rect) Preconditions.checkNotNull(mapCalculateViewPortRects.get(useCase)));
                }
            }
        }
    }

    private static class ConfigPair {
        UseCaseConfig<?> mCameraConfig;
        UseCaseConfig<?> mExtendedConfig;

        ConfigPair(UseCaseConfig<?> extendedConfig, UseCaseConfig<?> cameraConfig) {
            this.mExtendedConfig = extendedConfig;
            this.mCameraConfig = cameraConfig;
        }
    }

    private Map<UseCase, ConfigPair> getConfigs(List<UseCase> useCases, UseCaseConfigFactory extendedFactory, UseCaseConfigFactory cameraFactory) {
        HashMap map = new HashMap();
        for (UseCase useCase : useCases) {
            map.put(useCase, new ConfigPair(useCase.getDefaultConfig(false, extendedFactory), useCase.getDefaultConfig(true, cameraFactory)));
        }
        return map;
    }

    public static final class CameraId {
        private final List<String> mIds = new ArrayList();

        CameraId(LinkedHashSet<CameraInternal> cameraInternals) {
            Iterator<CameraInternal> it = cameraInternals.iterator();
            while (it.hasNext()) {
                this.mIds.add(it.next().getCameraInfoInternal().getCameraId());
            }
        }

        public boolean equals(Object cameraId) {
            if (cameraId instanceof CameraId) {
                return this.mIds.equals(((CameraId) cameraId).mIds);
            }
            return false;
        }

        public int hashCode() {
            return this.mIds.hashCode() * 53;
        }
    }

    public static final class CameraException extends Exception {
        public CameraException() {
        }

        public CameraException(String message) {
            super(message);
        }

        public CameraException(Throwable cause) {
            super(cause);
        }
    }

    @Override // androidx.camera.core.Camera
    public CameraControl getCameraControl() {
        return this.mCameraInternal.getCameraControlInternal();
    }

    @Override // androidx.camera.core.Camera
    public CameraInfo getCameraInfo() {
        return this.mCameraInternal.getCameraInfoInternal();
    }

    @Override // androidx.camera.core.Camera
    public LinkedHashSet<CameraInternal> getCameraInternals() {
        return this.mCameraInternals;
    }

    @Override // androidx.camera.core.Camera
    public CameraConfig getExtendedConfig() {
        CameraConfig cameraConfig;
        synchronized (this.mLock) {
            cameraConfig = this.mCameraConfig;
        }
        return cameraConfig;
    }

    @Override // androidx.camera.core.Camera
    public void setExtendedConfig(CameraConfig cameraConfig) throws CameraException {
        synchronized (this.mLock) {
            if (cameraConfig == null) {
                try {
                    cameraConfig = CameraConfigs.emptyConfig();
                } catch (Throwable th) {
                    throw th;
                }
            }
            CameraInternal cameraInternalSelect = new CameraSelector.Builder().addCameraFilter(cameraConfig.getCameraFilter()).build().select(this.mCameraInternals);
            Map<UseCase, ConfigPair> configs = getConfigs(this.mUseCases, cameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory);
            try {
                Map<UseCase, Size> mapCalculateSuggestedResolutions = calculateSuggestedResolutions(cameraInternalSelect.getCameraInfoInternal(), this.mUseCases, Collections.emptyList(), configs);
                updateViewPort(mapCalculateSuggestedResolutions, this.mUseCases);
                if (this.mAttached) {
                    this.mCameraInternal.detachUseCases(this.mUseCases);
                }
                Iterator<UseCase> it = this.mUseCases.iterator();
                while (it.hasNext()) {
                    it.next().onDetach(this.mCameraInternal);
                }
                for (UseCase useCase : this.mUseCases) {
                    ConfigPair configPair = configs.get(useCase);
                    useCase.onAttach(cameraInternalSelect, configPair.mExtendedConfig, configPair.mCameraConfig);
                    useCase.updateSuggestedResolution((Size) Preconditions.checkNotNull(mapCalculateSuggestedResolutions.get(useCase)));
                }
                if (this.mAttached) {
                    cameraInternalSelect.attachUseCases(this.mUseCases);
                }
                Iterator<UseCase> it2 = this.mUseCases.iterator();
                while (it2.hasNext()) {
                    it2.next().notifyState();
                }
                this.mCameraInternal = cameraInternalSelect;
                this.mCameraConfig = cameraConfig;
            } catch (IllegalArgumentException e) {
                throw new CameraException(e.getMessage());
            }
        }
    }
}
