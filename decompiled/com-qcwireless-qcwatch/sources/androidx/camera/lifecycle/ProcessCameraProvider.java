package androidx.camera.lifecycle;

import android.content.Context;
import androidx.arch.core.util.Function;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public final class ProcessCameraProvider implements LifecycleCameraProvider {
    private static final ProcessCameraProvider sAppInstance = new ProcessCameraProvider();
    private CameraX mCameraX;
    private final LifecycleCameraRepository mLifecycleCameraRepository = new LifecycleCameraRepository();

    public static ListenableFuture<ProcessCameraProvider> getInstance(Context context) {
        Preconditions.checkNotNull(context);
        return Futures.transform(CameraX.getOrCreateInstance(context), new Function() { // from class: androidx.camera.lifecycle.ProcessCameraProvider$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return ProcessCameraProvider.lambda$getInstance$0((CameraX) obj);
            }
        }, CameraXExecutors.directExecutor());
    }

    static /* synthetic */ ProcessCameraProvider lambda$getInstance$0(CameraX cameraX) {
        ProcessCameraProvider processCameraProvider = sAppInstance;
        processCameraProvider.setCameraX(cameraX);
        return processCameraProvider;
    }

    public static void configureInstance(CameraXConfig cameraXConfig) {
        CameraX.configureInstance(cameraXConfig);
    }

    public ListenableFuture<Void> shutdown() {
        this.mLifecycleCameraRepository.clear();
        return CameraX.shutdown();
    }

    private void setCameraX(CameraX cameraX) {
        this.mCameraX = cameraX;
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCase... useCases) {
        return bindToLifecycle(lifecycleOwner, cameraSelector, null, useCases);
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup) {
        return bindToLifecycle(lifecycleOwner, cameraSelector, useCaseGroup.getViewPort(), (UseCase[]) useCaseGroup.getUseCases().toArray(new UseCase[0]));
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, ViewPort viewPort, UseCase... useCases) {
        Threads.checkMainThread();
        CameraSelector.Builder builderFromSelector = CameraSelector.Builder.fromSelector(cameraSelector);
        for (UseCase useCase : useCases) {
            CameraSelector cameraSelector2 = useCase.getCurrentConfig().getCameraSelector(null);
            if (cameraSelector2 != null) {
                Iterator<CameraFilter> it = cameraSelector2.getCameraFilterSet().iterator();
                while (it.hasNext()) {
                    builderFromSelector.addCameraFilter(it.next());
                }
            }
        }
        LinkedHashSet<CameraInternal> linkedHashSetFilter = builderFromSelector.build().filter(this.mCameraX.getCameraRepository().getCameras());
        LifecycleCamera lifecycleCamera = this.mLifecycleCameraRepository.getLifecycleCamera(lifecycleOwner, CameraUseCaseAdapter.generateCameraId(linkedHashSetFilter));
        Collection<LifecycleCamera> lifecycleCameras = this.mLifecycleCameraRepository.getLifecycleCameras();
        for (UseCase useCase2 : useCases) {
            for (LifecycleCamera lifecycleCamera2 : lifecycleCameras) {
                if (lifecycleCamera2.isBound(useCase2) && lifecycleCamera2 != lifecycleCamera) {
                    throw new IllegalStateException(String.format("Use case %s already bound to a different lifecycle.", useCase2));
                }
            }
        }
        if (lifecycleCamera == null) {
            lifecycleCamera = this.mLifecycleCameraRepository.createLifecycleCamera(lifecycleOwner, new CameraUseCaseAdapter(linkedHashSetFilter, this.mCameraX.getCameraDeviceSurfaceManager(), this.mCameraX.getDefaultConfigFactory()));
        }
        if (useCases.length == 0) {
            return lifecycleCamera;
        }
        this.mLifecycleCameraRepository.bindToLifecycleCamera(lifecycleCamera, viewPort, Arrays.asList(useCases));
        return lifecycleCamera;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public boolean isBound(UseCase useCase) {
        Iterator<LifecycleCamera> it = this.mLifecycleCameraRepository.getLifecycleCameras().iterator();
        while (it.hasNext()) {
            if (it.next().isBound(useCase)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public void unbind(UseCase... useCases) {
        Threads.checkMainThread();
        this.mLifecycleCameraRepository.unbind(Arrays.asList(useCases));
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public void unbindAll() {
        Threads.checkMainThread();
        this.mLifecycleCameraRepository.unbindAll();
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        try {
            cameraSelector.select(this.mCameraX.getCameraRepository().getCameras());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    private ProcessCameraProvider() {
    }
}
