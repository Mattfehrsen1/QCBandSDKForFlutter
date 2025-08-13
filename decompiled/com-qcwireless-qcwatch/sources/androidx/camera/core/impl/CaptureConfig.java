package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class CaptureConfig {
    final List<CameraCaptureCallback> mCameraCaptureCallbacks;
    final Config mImplementationOptions;
    final List<DeferrableSurface> mSurfaces;
    private final TagBundle mTagBundle;
    final int mTemplateType;
    private final boolean mUseRepeatingSurface;
    public static final Config.Option<Integer> OPTION_ROTATION = Config.Option.create("camerax.core.captureConfig.rotation", Integer.TYPE);
    public static final Config.Option<Integer> OPTION_JPEG_QUALITY = Config.Option.create("camerax.core.captureConfig.jpegQuality", Integer.class);

    public interface OptionUnpacker {
        void unpack(UseCaseConfig<?> config, Builder builder);
    }

    CaptureConfig(List<DeferrableSurface> surfaces, Config implementationOptions, int templateType, List<CameraCaptureCallback> cameraCaptureCallbacks, boolean useRepeatingSurface, TagBundle tagBundle) {
        this.mSurfaces = surfaces;
        this.mImplementationOptions = implementationOptions;
        this.mTemplateType = templateType;
        this.mCameraCaptureCallbacks = Collections.unmodifiableList(cameraCaptureCallbacks);
        this.mUseRepeatingSurface = useRepeatingSurface;
        this.mTagBundle = tagBundle;
    }

    public static CaptureConfig defaultEmptyCaptureConfig() {
        return new Builder().build();
    }

    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.mSurfaces);
    }

    public Config getImplementationOptions() {
        return this.mImplementationOptions;
    }

    public int getTemplateType() {
        return this.mTemplateType;
    }

    public boolean isUseRepeatingSurface() {
        return this.mUseRepeatingSurface;
    }

    public List<CameraCaptureCallback> getCameraCaptureCallbacks() {
        return this.mCameraCaptureCallbacks;
    }

    public TagBundle getTagBundle() {
        return this.mTagBundle;
    }

    public static final class Builder {
        private List<CameraCaptureCallback> mCameraCaptureCallbacks;
        private MutableConfig mImplementationOptions;
        private MutableTagBundle mMutableTagBundle;
        private final Set<DeferrableSurface> mSurfaces;
        private int mTemplateType;
        private boolean mUseRepeatingSurface;

        public Builder() {
            this.mSurfaces = new HashSet();
            this.mImplementationOptions = MutableOptionsBundle.create();
            this.mTemplateType = -1;
            this.mCameraCaptureCallbacks = new ArrayList();
            this.mUseRepeatingSurface = false;
            this.mMutableTagBundle = MutableTagBundle.create();
        }

        private Builder(CaptureConfig base) {
            HashSet hashSet = new HashSet();
            this.mSurfaces = hashSet;
            this.mImplementationOptions = MutableOptionsBundle.create();
            this.mTemplateType = -1;
            this.mCameraCaptureCallbacks = new ArrayList();
            this.mUseRepeatingSurface = false;
            this.mMutableTagBundle = MutableTagBundle.create();
            hashSet.addAll(base.mSurfaces);
            this.mImplementationOptions = MutableOptionsBundle.from(base.mImplementationOptions);
            this.mTemplateType = base.mTemplateType;
            this.mCameraCaptureCallbacks.addAll(base.getCameraCaptureCallbacks());
            this.mUseRepeatingSurface = base.isUseRepeatingSurface();
            this.mMutableTagBundle = MutableTagBundle.from(base.getTagBundle());
        }

        public static Builder createFrom(UseCaseConfig<?> config) {
            OptionUnpacker captureOptionUnpacker = config.getCaptureOptionUnpacker(null);
            if (captureOptionUnpacker == null) {
                throw new IllegalStateException("Implementation is missing option unpacker for " + config.getTargetName(config.toString()));
            }
            Builder builder = new Builder();
            captureOptionUnpacker.unpack(config, builder);
            return builder;
        }

        public static Builder from(CaptureConfig base) {
            return new Builder(base);
        }

        public int getTemplateType() {
            return this.mTemplateType;
        }

        public void setTemplateType(int templateType) {
            this.mTemplateType = templateType;
        }

        public void addCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            if (this.mCameraCaptureCallbacks.contains(cameraCaptureCallback)) {
                throw new IllegalArgumentException("duplicate camera capture callback");
            }
            this.mCameraCaptureCallbacks.add(cameraCaptureCallback);
        }

        public void addAllCameraCaptureCallbacks(Collection<CameraCaptureCallback> cameraCaptureCallbacks) {
            Iterator<CameraCaptureCallback> it = cameraCaptureCallbacks.iterator();
            while (it.hasNext()) {
                addCameraCaptureCallback(it.next());
            }
        }

        public void addSurface(DeferrableSurface surface) {
            this.mSurfaces.add(surface);
        }

        public void removeSurface(DeferrableSurface surface) {
            this.mSurfaces.remove(surface);
        }

        public void clearSurfaces() {
            this.mSurfaces.clear();
        }

        public Set<DeferrableSurface> getSurfaces() {
            return this.mSurfaces;
        }

        public void setImplementationOptions(Config config) {
            this.mImplementationOptions = MutableOptionsBundle.from(config);
        }

        public void addImplementationOptions(Config config) {
            for (Config.Option<?> option : config.listOptions()) {
                Object objRetrieveOption = this.mImplementationOptions.retrieveOption(option, null);
                Object objRetrieveOption2 = config.retrieveOption(option);
                if (objRetrieveOption instanceof MultiValueSet) {
                    ((MultiValueSet) objRetrieveOption).addAll(((MultiValueSet) objRetrieveOption2).getAllItems());
                } else {
                    if (objRetrieveOption2 instanceof MultiValueSet) {
                        objRetrieveOption2 = ((MultiValueSet) objRetrieveOption2).mo2clone();
                    }
                    this.mImplementationOptions.insertOption(option, config.getOptionPriority(option), objRetrieveOption2);
                }
            }
        }

        public <T> void addImplementationOption(Config.Option<T> option, T value) {
            this.mImplementationOptions.insertOption(option, value);
        }

        public Config getImplementationOptions() {
            return this.mImplementationOptions;
        }

        boolean isUseRepeatingSurface() {
            return this.mUseRepeatingSurface;
        }

        public void setUseRepeatingSurface(boolean useRepeatingSurface) {
            this.mUseRepeatingSurface = useRepeatingSurface;
        }

        public Integer getTag(String key) {
            return this.mMutableTagBundle.getTag(key);
        }

        public void addTag(String key, Integer tag) {
            this.mMutableTagBundle.putTag(key, tag);
        }

        public void addAllTags(TagBundle bundle) {
            this.mMutableTagBundle.addTagBundle(bundle);
        }

        public CaptureConfig build() {
            return new CaptureConfig(new ArrayList(this.mSurfaces), OptionsBundle.from(this.mImplementationOptions), this.mTemplateType, this.mCameraCaptureCallbacks, this.mUseRepeatingSurface, TagBundle.from(this.mMutableTagBundle));
        }
    }
}
