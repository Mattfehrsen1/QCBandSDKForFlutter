package androidx.camera.core.impl;

import androidx.camera.core.Logger;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseAttachState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class UseCaseAttachState {
    private static final String TAG = "UseCaseAttachState";
    private final Map<String, UseCaseAttachInfo> mAttachedUseCasesToInfoMap = new HashMap();
    private final String mCameraId;

    /* JADX INFO: Access modifiers changed from: private */
    interface AttachStateFilter {
        boolean filter(UseCaseAttachInfo attachInfo);
    }

    public UseCaseAttachState(String cameraId) {
        this.mCameraId = cameraId;
    }

    public void setUseCaseActive(String useCaseName, SessionConfig sessionConfig) {
        getOrCreateUseCaseAttachInfo(useCaseName, sessionConfig).setActive(true);
    }

    public void setUseCaseInactive(String useCaseName) {
        if (this.mAttachedUseCasesToInfoMap.containsKey(useCaseName)) {
            UseCaseAttachInfo useCaseAttachInfo = this.mAttachedUseCasesToInfoMap.get(useCaseName);
            useCaseAttachInfo.setActive(false);
            if (useCaseAttachInfo.getAttached()) {
                return;
            }
            this.mAttachedUseCasesToInfoMap.remove(useCaseName);
        }
    }

    public void setUseCaseAttached(String useCaseName, SessionConfig sessionConfig) {
        getOrCreateUseCaseAttachInfo(useCaseName, sessionConfig).setAttached(true);
    }

    public void setUseCaseDetached(String useCaseName) {
        if (this.mAttachedUseCasesToInfoMap.containsKey(useCaseName)) {
            UseCaseAttachInfo useCaseAttachInfo = this.mAttachedUseCasesToInfoMap.get(useCaseName);
            useCaseAttachInfo.setAttached(false);
            if (useCaseAttachInfo.getActive()) {
                return;
            }
            this.mAttachedUseCasesToInfoMap.remove(useCaseName);
        }
    }

    public boolean isUseCaseAttached(String useCaseName) {
        if (this.mAttachedUseCasesToInfoMap.containsKey(useCaseName)) {
            return this.mAttachedUseCasesToInfoMap.get(useCaseName).getAttached();
        }
        return false;
    }

    public Collection<SessionConfig> getAttachedSessionConfigs() {
        return Collections.unmodifiableCollection(getSessionConfigs(new AttachStateFilter() { // from class: androidx.camera.core.impl.UseCaseAttachState$$ExternalSyntheticLambda1
            @Override // androidx.camera.core.impl.UseCaseAttachState.AttachStateFilter
            public final boolean filter(UseCaseAttachState.UseCaseAttachInfo useCaseAttachInfo) {
                return useCaseAttachInfo.getAttached();
            }
        }));
    }

    public Collection<SessionConfig> getActiveAndAttachedSessionConfigs() {
        return Collections.unmodifiableCollection(getSessionConfigs(new AttachStateFilter() { // from class: androidx.camera.core.impl.UseCaseAttachState$$ExternalSyntheticLambda0
            @Override // androidx.camera.core.impl.UseCaseAttachState.AttachStateFilter
            public final boolean filter(UseCaseAttachState.UseCaseAttachInfo useCaseAttachInfo) {
                return UseCaseAttachState.lambda$getActiveAndAttachedSessionConfigs$1(useCaseAttachInfo);
            }
        }));
    }

    static /* synthetic */ boolean lambda$getActiveAndAttachedSessionConfigs$1(UseCaseAttachInfo useCaseAttachInfo) {
        return useCaseAttachInfo.getActive() && useCaseAttachInfo.getAttached();
    }

    public void updateUseCase(String useCaseName, SessionConfig sessionConfig) {
        if (this.mAttachedUseCasesToInfoMap.containsKey(useCaseName)) {
            UseCaseAttachInfo useCaseAttachInfo = new UseCaseAttachInfo(sessionConfig);
            UseCaseAttachInfo useCaseAttachInfo2 = this.mAttachedUseCasesToInfoMap.get(useCaseName);
            useCaseAttachInfo.setAttached(useCaseAttachInfo2.getAttached());
            useCaseAttachInfo.setActive(useCaseAttachInfo2.getActive());
            this.mAttachedUseCasesToInfoMap.put(useCaseName, useCaseAttachInfo);
        }
    }

    public void removeUseCase(String useCaseName) {
        this.mAttachedUseCasesToInfoMap.remove(useCaseName);
    }

    public SessionConfig.ValidatingBuilder getActiveAndAttachedBuilder() {
        SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, UseCaseAttachInfo> entry : this.mAttachedUseCasesToInfoMap.entrySet()) {
            UseCaseAttachInfo value = entry.getValue();
            if (value.getActive() && value.getAttached()) {
                String key = entry.getKey();
                validatingBuilder.add(value.getSessionConfig());
                arrayList.add(key);
            }
        }
        Logger.d(TAG, "Active and attached use case: " + arrayList + " for camera: " + this.mCameraId);
        return validatingBuilder;
    }

    public SessionConfig.ValidatingBuilder getAttachedBuilder() {
        SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, UseCaseAttachInfo> entry : this.mAttachedUseCasesToInfoMap.entrySet()) {
            UseCaseAttachInfo value = entry.getValue();
            if (value.getAttached()) {
                validatingBuilder.add(value.getSessionConfig());
                arrayList.add(entry.getKey());
            }
        }
        Logger.d(TAG, "All use case: " + arrayList + " for camera: " + this.mCameraId);
        return validatingBuilder;
    }

    private UseCaseAttachInfo getOrCreateUseCaseAttachInfo(String useCaseName, SessionConfig sessionConfig) {
        UseCaseAttachInfo useCaseAttachInfo = this.mAttachedUseCasesToInfoMap.get(useCaseName);
        if (useCaseAttachInfo != null) {
            return useCaseAttachInfo;
        }
        UseCaseAttachInfo useCaseAttachInfo2 = new UseCaseAttachInfo(sessionConfig);
        this.mAttachedUseCasesToInfoMap.put(useCaseName, useCaseAttachInfo2);
        return useCaseAttachInfo2;
    }

    private Collection<SessionConfig> getSessionConfigs(AttachStateFilter attachStateFilter) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, UseCaseAttachInfo> entry : this.mAttachedUseCasesToInfoMap.entrySet()) {
            if (attachStateFilter == null || attachStateFilter.filter(entry.getValue())) {
                arrayList.add(entry.getValue().getSessionConfig());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class UseCaseAttachInfo {
        private final SessionConfig mSessionConfig;
        private boolean mAttached = false;
        private boolean mActive = false;

        UseCaseAttachInfo(SessionConfig sessionConfig) {
            this.mSessionConfig = sessionConfig;
        }

        SessionConfig getSessionConfig() {
            return this.mSessionConfig;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean getAttached() {
            return this.mAttached;
        }

        void setAttached(boolean attached) {
            this.mAttached = attached;
        }

        boolean getActive() {
            return this.mActive;
        }

        void setActive(boolean active) {
            this.mActive = active;
        }
    }
}
