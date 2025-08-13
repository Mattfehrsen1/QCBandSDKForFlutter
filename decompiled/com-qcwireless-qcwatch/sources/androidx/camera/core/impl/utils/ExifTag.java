package androidx.camera.core.impl.utils;

/* loaded from: classes.dex */
class ExifTag {
    public final String name;
    public final int number;
    public final int primaryFormat;
    public final int secondaryFormat;

    ExifTag(String name, int number, int format) {
        this.name = name;
        this.number = number;
        this.primaryFormat = format;
        this.secondaryFormat = -1;
    }

    ExifTag(String name, int number, int primaryFormat, int secondaryFormat) {
        this.name = name;
        this.number = number;
        this.primaryFormat = primaryFormat;
        this.secondaryFormat = secondaryFormat;
    }

    boolean isFormatCompatible(int format) {
        int i;
        int i2 = this.primaryFormat;
        if (i2 == 7 || format == 7 || i2 == format || (i = this.secondaryFormat) == format) {
            return true;
        }
        if ((i2 == 4 || i == 4) && format == 3) {
            return true;
        }
        if ((i2 == 9 || i == 9) && format == 8) {
            return true;
        }
        return (i2 == 12 || i == 12) && format == 11;
    }
}
