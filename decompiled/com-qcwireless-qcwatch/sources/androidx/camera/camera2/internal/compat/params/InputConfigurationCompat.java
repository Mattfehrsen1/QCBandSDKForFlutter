package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.InputConfiguration;
import android.os.Build;
import java.util.Objects;

/* loaded from: classes.dex */
public final class InputConfigurationCompat {
    private final InputConfigurationCompatImpl mImpl;

    private interface InputConfigurationCompatImpl {
        int getFormat();

        int getHeight();

        Object getInputConfiguration();

        int getWidth();
    }

    public InputConfigurationCompat(int width, int height, int format) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mImpl = new InputConfigurationCompatApi23Impl(width, height, format);
        } else {
            this.mImpl = new InputConfigurationCompatBaseImpl(width, height, format);
        }
    }

    private InputConfigurationCompat(InputConfigurationCompatImpl impl) {
        this.mImpl = impl;
    }

    public static InputConfigurationCompat wrap(Object inputConfiguration) {
        if (inputConfiguration != null && Build.VERSION.SDK_INT >= 23) {
            return new InputConfigurationCompat(new InputConfigurationCompatApi23Impl(inputConfiguration));
        }
        return null;
    }

    public int getWidth() {
        return this.mImpl.getWidth();
    }

    public int getHeight() {
        return this.mImpl.getHeight();
    }

    public int getFormat() {
        return this.mImpl.getFormat();
    }

    public boolean equals(Object obj) {
        if (obj instanceof InputConfigurationCompat) {
            return this.mImpl.equals(((InputConfigurationCompat) obj).mImpl);
        }
        return false;
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }

    public Object unwrap() {
        return this.mImpl.getInputConfiguration();
    }

    static final class InputConfigurationCompatBaseImpl implements InputConfigurationCompatImpl {
        private final int mFormat;
        private final int mHeight;
        private final int mWidth;

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public Object getInputConfiguration() {
            return null;
        }

        InputConfigurationCompatBaseImpl(int width, int height, int format) {
            this.mWidth = width;
            this.mHeight = height;
            this.mFormat = format;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public int getWidth() {
            return this.mWidth;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public int getHeight() {
            return this.mHeight;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public int getFormat() {
            return this.mFormat;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof InputConfigurationCompatBaseImpl)) {
                return false;
            }
            InputConfigurationCompatBaseImpl inputConfigurationCompatBaseImpl = (InputConfigurationCompatBaseImpl) obj;
            return inputConfigurationCompatBaseImpl.getWidth() == this.mWidth && inputConfigurationCompatBaseImpl.getHeight() == this.mHeight && inputConfigurationCompatBaseImpl.getFormat() == this.mFormat;
        }

        public int hashCode() {
            int i = this.mWidth ^ 31;
            int i2 = this.mHeight ^ ((i << 5) - i);
            return this.mFormat ^ ((i2 << 5) - i2);
        }

        public String toString() {
            return String.format("InputConfiguration(w:%d, h:%d, format:%d)", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mFormat));
        }
    }

    private static final class InputConfigurationCompatApi23Impl implements InputConfigurationCompatImpl {
        private final InputConfiguration mObject;

        InputConfigurationCompatApi23Impl(Object inputConfiguration) {
            this.mObject = (InputConfiguration) inputConfiguration;
        }

        InputConfigurationCompatApi23Impl(int width, int height, int format) {
            this(new InputConfiguration(width, height, format));
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public int getWidth() {
            return this.mObject.getWidth();
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public int getHeight() {
            return this.mObject.getHeight();
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public int getFormat() {
            return this.mObject.getFormat();
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.InputConfigurationCompatImpl
        public Object getInputConfiguration() {
            return this.mObject;
        }

        public boolean equals(Object obj) {
            if (obj instanceof InputConfigurationCompatImpl) {
                return Objects.equals(this.mObject, ((InputConfigurationCompatImpl) obj).getInputConfiguration());
            }
            return false;
        }

        public int hashCode() {
            return this.mObject.hashCode();
        }

        public String toString() {
            return this.mObject.toString();
        }
    }
}
