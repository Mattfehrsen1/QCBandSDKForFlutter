package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes.dex */
public class CameraAccessExceptionCompat extends Exception {
    public static final int CAMERA_CHARACTERISTICS_CREATION_ERROR = 10002;
    public static final int CAMERA_DEPRECATED_HAL = 1000;
    public static final int CAMERA_DISABLED = 1;
    public static final int CAMERA_DISCONNECTED = 2;
    public static final int CAMERA_ERROR = 3;
    public static final int CAMERA_IN_USE = 4;
    public static final int MAX_CAMERAS_IN_USE = 5;
    private final CameraAccessException mCameraAccessException;
    private final int mReason;
    static final Set<Integer> PLATFORM_ERRORS = Collections.unmodifiableSet(new HashSet(Arrays.asList(4, 5, 1, 2, 3)));
    public static final int CAMERA_UNAVAILABLE_DO_NOT_DISTURB = 10001;
    static final Set<Integer> COMPAT_ERRORS = Collections.unmodifiableSet(new HashSet(Arrays.asList(Integer.valueOf(CAMERA_UNAVAILABLE_DO_NOT_DISTURB), 10002)));

    @Retention(RetentionPolicy.SOURCE)
    public @interface AccessError {
    }

    private static String getDefaultMessage(int problem) {
        if (problem == 1) {
            return "The camera is disabled due to a device policy, and cannot be opened.";
        }
        if (problem == 2) {
            return "The camera device is removable and has been disconnected from the Android device, or the camera service has shut down the connection due to a higher-priority access request for the camera device.";
        }
        if (problem == 3) {
            return "The camera device is currently in the error state; no further calls to it will succeed.";
        }
        if (problem == 4) {
            return "The camera device is in use already";
        }
        if (problem == 5) {
            return "The system-wide limit for number of open cameras has been reached, and more camera devices cannot be opened until previous instances are closed.";
        }
        if (problem == 10001) {
            return "Some API 28 devices cannot access the camera when the device is in \"Do Not Disturb\" mode. The camera will not be accessible until \"Do Not Disturb\" mode is disabled.";
        }
        if (problem != 10002) {
            return null;
        }
        return "Failed to create CameraCharacteristics.";
    }

    private static String getProblemString(int problem) {
        return problem != 1 ? problem != 2 ? problem != 3 ? problem != 4 ? problem != 5 ? problem != 1000 ? problem != 10001 ? problem != 10002 ? "<UNKNOWN ERROR>" : "CAMERA_CHARACTERISTICS_CREATION_ERROR" : "CAMERA_UNAVAILABLE_DO_NOT_DISTURB" : "CAMERA_DEPRECATED_HAL" : "MAX_CAMERAS_IN_USE" : "CAMERA_IN_USE" : "CAMERA_ERROR" : "CAMERA_DISCONNECTED" : "CAMERA_DISABLED";
    }

    public CameraAccessExceptionCompat(int reason) {
        super(getDefaultMessage(reason));
        this.mReason = reason;
        this.mCameraAccessException = PLATFORM_ERRORS.contains(Integer.valueOf(reason)) ? new CameraAccessException(reason) : null;
    }

    public CameraAccessExceptionCompat(int reason, String message) {
        super(getCombinedMessage(reason, message));
        this.mReason = reason;
        this.mCameraAccessException = PLATFORM_ERRORS.contains(Integer.valueOf(reason)) ? new CameraAccessException(reason, message) : null;
    }

    public CameraAccessExceptionCompat(int reason, String message, Throwable cause) {
        super(getCombinedMessage(reason, message), cause);
        this.mReason = reason;
        this.mCameraAccessException = PLATFORM_ERRORS.contains(Integer.valueOf(reason)) ? new CameraAccessException(reason, message, cause) : null;
    }

    public CameraAccessExceptionCompat(int reason, Throwable cause) {
        super(getDefaultMessage(reason), cause);
        this.mReason = reason;
        this.mCameraAccessException = PLATFORM_ERRORS.contains(Integer.valueOf(reason)) ? new CameraAccessException(reason, null, cause) : null;
    }

    private CameraAccessExceptionCompat(CameraAccessException e) {
        super(e.getMessage(), e.getCause());
        this.mReason = e.getReason();
        this.mCameraAccessException = e;
    }

    public final int getReason() {
        return this.mReason;
    }

    public CameraAccessException toCameraAccessException() {
        return this.mCameraAccessException;
    }

    public static CameraAccessExceptionCompat toCameraAccessExceptionCompat(CameraAccessException cameraAccessException) {
        Objects.requireNonNull(cameraAccessException, "cameraAccessException should not be null");
        return new CameraAccessExceptionCompat(cameraAccessException);
    }

    private static String getCombinedMessage(int problem, String message) {
        return String.format("%s (%d): %s", getProblemString(problem), Integer.valueOf(problem), message);
    }
}
