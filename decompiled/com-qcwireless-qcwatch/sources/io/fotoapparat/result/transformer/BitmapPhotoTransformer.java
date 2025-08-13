package io.fotoapparat.result.transformer;

import android.graphics.Bitmap;
import io.fotoapparat.exception.UnableToDecodeBitmapException;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.result.BitmapPhoto;
import io.fotoapparat.result.Photo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapPhotoTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0001j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0096\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0001j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/fotoapparat/result/transformer/BitmapPhotoTransformer;", "Lkotlin/Function1;", "Lio/fotoapparat/result/Photo;", "Lio/fotoapparat/result/BitmapPhoto;", "sizeTransformer", "Lio/fotoapparat/parameter/Resolution;", "Lio/fotoapparat/result/transformer/ResolutionTransformer;", "(Lkotlin/jvm/functions/Function1;)V", "invoke", "input", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BitmapPhotoTransformer implements Function1<Photo, BitmapPhoto> {
    private final Function1<Resolution, Resolution> sizeTransformer;

    /* JADX WARN: Multi-variable type inference failed */
    public BitmapPhotoTransformer(Function1<? super Resolution, Resolution> sizeTransformer) {
        Intrinsics.checkParameterIsNotNull(sizeTransformer, "sizeTransformer");
        this.sizeTransformer = sizeTransformer;
    }

    @Override // kotlin.jvm.functions.Function1
    public BitmapPhoto invoke(Photo input) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Resolution resolution = BitmapPhotoTransformerKt.readResolution(input);
        Resolution resolutionInvoke = this.sizeTransformer.invoke(resolution);
        Bitmap bitmap = BitmapPhotoTransformerKt.decodeBitmap(input, BitmapPhotoTransformerKt.computeScaleFactor(resolution, resolutionInvoke));
        if (bitmap == null) {
            throw new UnableToDecodeBitmapException();
        }
        if (bitmap.getWidth() != resolutionInvoke.width || bitmap.getHeight() != resolutionInvoke.height) {
            bitmap = Bitmap.createScaledBitmap(bitmap, resolutionInvoke.width, resolutionInvoke.height, true);
        }
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return new BitmapPhoto(bitmap, input.rotationDegrees);
    }
}
