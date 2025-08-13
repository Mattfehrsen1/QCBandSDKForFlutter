package io.fotoapparat.result;

import android.graphics.Bitmap;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapPhoto.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\t\u0010\b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/fotoapparat/result/BitmapPhoto;", "", "bitmap", "Landroid/graphics/Bitmap;", "rotationDegrees", "", "(Landroid/graphics/Bitmap;I)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class BitmapPhoto {
    public final Bitmap bitmap;
    public final int rotationDegrees;

    public static /* synthetic */ BitmapPhoto copy$default(BitmapPhoto bitmapPhoto, Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bitmap = bitmapPhoto.bitmap;
        }
        if ((i2 & 2) != 0) {
            i = bitmapPhoto.rotationDegrees;
        }
        return bitmapPhoto.copy(bitmap, i);
    }

    /* renamed from: component1, reason: from getter */
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    /* renamed from: component2, reason: from getter */
    public final int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public final BitmapPhoto copy(Bitmap bitmap, int rotationDegrees) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        return new BitmapPhoto(bitmap, rotationDegrees);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof BitmapPhoto) {
                BitmapPhoto bitmapPhoto = (BitmapPhoto) other;
                if (Intrinsics.areEqual(this.bitmap, bitmapPhoto.bitmap)) {
                    if (this.rotationDegrees == bitmapPhoto.rotationDegrees) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        Bitmap bitmap = this.bitmap;
        return ((bitmap != null ? bitmap.hashCode() : 0) * 31) + this.rotationDegrees;
    }

    public String toString() {
        return "BitmapPhoto(bitmap=" + this.bitmap + ", rotationDegrees=" + this.rotationDegrees + ")";
    }

    public BitmapPhoto(Bitmap bitmap, int i) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        this.bitmap = bitmap;
        this.rotationDegrees = i;
    }
}
