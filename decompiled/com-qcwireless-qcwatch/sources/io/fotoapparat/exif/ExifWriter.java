package io.fotoapparat.exif;

import androidx.exifinterface.media.ExifInterface;
import io.fotoapparat.exception.FileSaveException;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExifWriter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\n"}, d2 = {"Lio/fotoapparat/exif/ExifWriter;", "Lio/fotoapparat/exif/ExifOrientationWriter;", "()V", "toExifOrientation", "", "rotationDegrees", "writeExifOrientation", "", "file", "Ljava/io/File;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ExifWriter implements ExifOrientationWriter {
    public static final ExifWriter INSTANCE = new ExifWriter();

    private ExifWriter() {
    }

    @Override // io.fotoapparat.exif.ExifOrientationWriter
    public void writeExifOrientation(File file, int rotationDegrees) throws Throwable {
        Intrinsics.checkParameterIsNotNull(file, "file");
        try {
            ExifInterface exifInterface = new ExifInterface(file.getPath());
            exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(INSTANCE.toExifOrientation(rotationDegrees)));
            exifInterface.saveAttributes();
        } catch (IOException e) {
            throw new FileSaveException(e);
        }
    }

    private final int toExifOrientation(int rotationDegrees) {
        int i = (360 - rotationDegrees) % 360;
        if (i == 90) {
            return 6;
        }
        if (i != 180) {
            return i != 270 ? 1 : 8;
        }
        return 3;
    }
}
