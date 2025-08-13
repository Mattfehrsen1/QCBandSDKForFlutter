package androidx.camera.camera2.internal;

import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.workaround.ImageCapturePixelHDRPlus;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.UseCaseConfig;

/* loaded from: classes.dex */
final class ImageCaptureOptionUnpacker extends Camera2CaptureOptionUnpacker {
    static final ImageCaptureOptionUnpacker INSTANCE = new ImageCaptureOptionUnpacker(new ImageCapturePixelHDRPlus());
    private final ImageCapturePixelHDRPlus mImageCapturePixelHDRPlus;

    private ImageCaptureOptionUnpacker(ImageCapturePixelHDRPlus imageCapturePixelHDRPlus) {
        this.mImageCapturePixelHDRPlus = imageCapturePixelHDRPlus;
    }

    @Override // androidx.camera.camera2.internal.Camera2CaptureOptionUnpacker, androidx.camera.core.impl.CaptureConfig.OptionUnpacker
    public void unpack(UseCaseConfig<?> config, final CaptureConfig.Builder builder) {
        super.unpack(config, builder);
        if (!(config instanceof ImageCaptureConfig)) {
            throw new IllegalArgumentException("config is not ImageCaptureConfig");
        }
        ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) config;
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        if (imageCaptureConfig.hasCaptureMode()) {
            this.mImageCapturePixelHDRPlus.toggleHDRPlus(imageCaptureConfig.getCaptureMode(), builder2);
        }
        builder.addImplementationOptions(builder2.build());
    }
}
