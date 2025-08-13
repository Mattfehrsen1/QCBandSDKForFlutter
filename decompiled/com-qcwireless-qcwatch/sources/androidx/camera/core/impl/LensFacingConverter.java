package androidx.camera.core.impl;

import java.util.Objects;

/* loaded from: classes.dex */
public class LensFacingConverter {
    private LensFacingConverter() {
    }

    public static Integer[] values() {
        return new Integer[]{0, 1};
    }

    public static int valueOf(final String name) {
        Objects.requireNonNull(name, "name cannot be null");
        name.hashCode();
        if (name.equals("BACK")) {
            return 1;
        }
        if (name.equals("FRONT")) {
            return 0;
        }
        throw new IllegalArgumentException("Unknown len facing name " + name);
    }

    public static String nameOf(final int lensFacing) {
        if (lensFacing == 0) {
            return "FRONT";
        }
        if (lensFacing == 1) {
            return "BACK";
        }
        throw new IllegalArgumentException("Unknown lens facing " + lensFacing);
    }
}
