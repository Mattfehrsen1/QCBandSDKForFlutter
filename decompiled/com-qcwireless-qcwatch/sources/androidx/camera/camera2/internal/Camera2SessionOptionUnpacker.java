package androidx.camera.camera2.internal;

import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.CameraEventCallbacks;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;

/* loaded from: classes.dex */
final class Camera2SessionOptionUnpacker implements SessionConfig.OptionUnpacker {
    static final Camera2SessionOptionUnpacker INSTANCE = new Camera2SessionOptionUnpacker();

    Camera2SessionOptionUnpacker() {
    }

    @Override // androidx.camera.core.impl.SessionConfig.OptionUnpacker
    public void unpack(UseCaseConfig<?> config, final SessionConfig.Builder builder) {
        SessionConfig defaultSessionConfig = config.getDefaultSessionConfig(null);
        Config configEmptyBundle = OptionsBundle.emptyBundle();
        int templateType = SessionConfig.defaultEmptySessionConfig().getTemplateType();
        if (defaultSessionConfig != null) {
            templateType = defaultSessionConfig.getTemplateType();
            builder.addAllDeviceStateCallbacks(defaultSessionConfig.getDeviceStateCallbacks());
            builder.addAllSessionStateCallbacks(defaultSessionConfig.getSessionStateCallbacks());
            builder.addAllRepeatingCameraCaptureCallbacks(defaultSessionConfig.getRepeatingCameraCaptureCallbacks());
            configEmptyBundle = defaultSessionConfig.getImplementationOptions();
        }
        builder.setImplementationOptions(configEmptyBundle);
        Camera2ImplConfig camera2ImplConfig = new Camera2ImplConfig(config);
        builder.setTemplateType(camera2ImplConfig.getCaptureRequestTemplate(templateType));
        builder.addDeviceStateCallback(camera2ImplConfig.getDeviceStateCallback(CameraDeviceStateCallbacks.createNoOpCallback()));
        builder.addSessionStateCallback(camera2ImplConfig.getSessionStateCallback(CameraCaptureSessionStateCallbacks.createNoOpCallback()));
        builder.addCameraCaptureCallback(CaptureCallbackContainer.create(camera2ImplConfig.getSessionCaptureCallback(Camera2CaptureCallbacks.createNoOpCallback())));
        MutableOptionsBundle mutableOptionsBundleCreate = MutableOptionsBundle.create();
        mutableOptionsBundleCreate.insertOption(Camera2ImplConfig.CAMERA_EVENT_CALLBACK_OPTION, camera2ImplConfig.getCameraEventCallback(CameraEventCallbacks.createEmptyCallback()));
        builder.addImplementationOptions(mutableOptionsBundleCreate);
        builder.addImplementationOptions(camera2ImplConfig.getCaptureRequestOptions());
    }
}
