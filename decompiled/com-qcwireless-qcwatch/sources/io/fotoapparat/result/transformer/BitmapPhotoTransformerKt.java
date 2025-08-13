package io.fotoapparat.result.transformer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.result.Photo;
import kotlin.Metadata;

/* compiled from: BitmapPhotoTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0002\u001a\f\u0010\t\u001a\u00020\u0003*\u00020\u0007H\u0002¨\u0006\n"}, d2 = {"computeScaleFactor", "", "originalResolution", "Lio/fotoapparat/parameter/Resolution;", "desiredResolution", "decodeBitmap", "Landroid/graphics/Bitmap;", "Lio/fotoapparat/result/Photo;", "scaleFactor", "readResolution", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BitmapPhotoTransformerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap decodeBitmap(Photo photo, float f) {
        new BitmapFactory.Options().inSampleSize = (int) f;
        return BitmapFactory.decodeByteArray(photo.encodedImage, 0, photo.encodedImage.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Resolution readResolution(Photo photo) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(photo.encodedImage, 0, photo.encodedImage.length, options);
        return new Resolution(options.outWidth, options.outHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float computeScaleFactor(Resolution resolution, Resolution resolution2) {
        return Math.min(resolution.width / resolution2.width, resolution.height / resolution2.height);
    }
}
