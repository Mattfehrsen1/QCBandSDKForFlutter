package io.fotoapparat.result;

import android.graphics.BitmapFactory;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: Photo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001c"}, d2 = {"Lio/fotoapparat/result/Photo;", "", "encodedImage", "", "rotationDegrees", "", "([BI)V", "decodedBounds", "Landroid/graphics/BitmapFactory$Options;", "getDecodedBounds", "()Landroid/graphics/BitmapFactory$Options;", "decodedBounds$delegate", "Lkotlin/Lazy;", "height", "getHeight", "()I", "width", "getWidth", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "Companion", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class Photo {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Photo.class), "decodedBounds", "getDecodedBounds()Landroid/graphics/BitmapFactory$Options;"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: decodedBounds$delegate, reason: from kotlin metadata */
    private final Lazy decodedBounds;
    public final byte[] encodedImage;
    public final int rotationDegrees;

    public static /* synthetic */ Photo copy$default(Photo photo, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr = photo.encodedImage;
        }
        if ((i2 & 2) != 0) {
            i = photo.rotationDegrees;
        }
        return photo.copy(bArr, i);
    }

    private final BitmapFactory.Options getDecodedBounds() {
        Lazy lazy = this.decodedBounds;
        KProperty kProperty = $$delegatedProperties[0];
        return (BitmapFactory.Options) lazy.getValue();
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getEncodedImage() {
        return this.encodedImage;
    }

    /* renamed from: component2, reason: from getter */
    public final int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public final Photo copy(byte[] encodedImage, int rotationDegrees) {
        Intrinsics.checkParameterIsNotNull(encodedImage, "encodedImage");
        return new Photo(encodedImage, rotationDegrees);
    }

    public Photo(byte[] encodedImage, int i) {
        Intrinsics.checkParameterIsNotNull(encodedImage, "encodedImage");
        this.encodedImage = encodedImage;
        this.rotationDegrees = i;
        this.decodedBounds = LazyKt.lazy(new Function0<BitmapFactory.Options>() { // from class: io.fotoapparat.result.Photo$decodedBounds$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BitmapFactory.Options invoke() {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(this.this$0.encodedImage, 0, this.this$0.encodedImage.length, options);
                return options;
            }
        });
    }

    public final int getHeight() {
        return getDecodedBounds().outHeight;
    }

    public final int getWidth() {
        return getDecodedBounds().outWidth;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            Photo photo = (Photo) other;
            return Arrays.equals(this.encodedImage, photo.encodedImage) && this.rotationDegrees == photo.rotationDegrees;
        }
        throw new TypeCastException("null cannot be cast to non-null type io.fotoapparat.result.Photo");
    }

    public int hashCode() {
        return (Arrays.hashCode(this.encodedImage) * 31) + this.rotationDegrees;
    }

    public String toString() {
        return "Photo(encodedImage=ByteArray(" + this.encodedImage.length + ") rotationDegrees=" + this.rotationDegrees + ')';
    }

    /* compiled from: Photo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lio/fotoapparat/result/Photo$Companion;", "", "()V", "empty", "Lio/fotoapparat/result/Photo;", "empty$fotoapparat_release", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Photo empty$fotoapparat_release() {
            return new Photo(new byte[0], 0);
        }
    }
}
