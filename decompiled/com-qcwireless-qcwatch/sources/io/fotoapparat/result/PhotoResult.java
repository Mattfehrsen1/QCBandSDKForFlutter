package io.fotoapparat.result;

import io.fotoapparat.exif.ExifWriter;
import io.fotoapparat.log.Logger;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.result.transformer.BitmapPhotoTransformer;
import io.fotoapparat.result.transformer.ResolutionTransformersKt;
import io.fotoapparat.result.transformer.SaveToFileTransformer;
import java.io.File;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PhotoResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\u0006\u0010\b\u001a\u00020\tJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rj\u0002`\u000fH\u0007J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lio/fotoapparat/result/PhotoResult;", "", "pendingResult", "Lio/fotoapparat/result/PendingResult;", "Lio/fotoapparat/result/Photo;", "(Lio/fotoapparat/result/PendingResult;)V", "saveToFile", "", "file", "Ljava/io/File;", "toBitmap", "Lio/fotoapparat/result/BitmapPhoto;", "sizeTransformer", "Lkotlin/Function1;", "Lio/fotoapparat/parameter/Resolution;", "Lio/fotoapparat/result/transformer/ResolutionTransformer;", "toPendingResult", "Companion", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class PhotoResult {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final PendingResult<Photo> pendingResult;

    public final PendingResult<BitmapPhoto> toBitmap() {
        return toBitmap$default(this, null, 1, null);
    }

    public PhotoResult(PendingResult<Photo> pendingResult) {
        Intrinsics.checkParameterIsNotNull(pendingResult, "pendingResult");
        this.pendingResult = pendingResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PendingResult toBitmap$default(PhotoResult photoResult, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = ResolutionTransformersKt.originalResolution();
        }
        return photoResult.toBitmap(function1);
    }

    public final PendingResult<BitmapPhoto> toBitmap(Function1<? super Resolution, Resolution> sizeTransformer) {
        Intrinsics.checkParameterIsNotNull(sizeTransformer, "sizeTransformer");
        return this.pendingResult.transform(new BitmapPhotoTransformer(sizeTransformer));
    }

    public final PendingResult<Unit> saveToFile(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return this.pendingResult.transform(new SaveToFileTransformer(file, ExifWriter.INSTANCE));
    }

    public final PendingResult<Photo> toPendingResult() {
        return this.pendingResult;
    }

    /* compiled from: PhotoResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lio/fotoapparat/result/PhotoResult$Companion;", "", "()V", "fromFuture", "Lio/fotoapparat/result/PhotoResult;", "photoFuture", "Ljava/util/concurrent/Future;", "Lio/fotoapparat/result/Photo;", "logger", "Lio/fotoapparat/log/Logger;", "fromFuture$fotoapparat_release", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PhotoResult fromFuture$fotoapparat_release(Future<Photo> photoFuture, Logger logger) {
            Intrinsics.checkParameterIsNotNull(photoFuture, "photoFuture");
            Intrinsics.checkParameterIsNotNull(logger, "logger");
            return new PhotoResult(PendingResult.INSTANCE.fromFuture$fotoapparat_release(photoFuture, logger));
        }
    }
}
