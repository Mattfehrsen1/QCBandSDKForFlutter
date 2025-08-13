package io.fotoapparat.result.adapter.rxjava;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import rx.Completable;

/* compiled from: CompletableAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0004\"\u0004\b\u0000\u0010\u0006H\u0007¨\u0006\b"}, d2 = {"Lio/fotoapparat/result/adapter/rxjava/CompletableAdapter;", "", "()V", "toCompletable", "Lkotlin/Function1;", "Ljava/util/concurrent/Future;", ExifInterface.GPS_DIRECTION_TRUE, "Lrx/Completable;", "adapter-rxjava_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CompletableAdapter {
    public static final CompletableAdapter INSTANCE = new CompletableAdapter();

    private CompletableAdapter() {
    }

    @JvmStatic
    public static final <T> Function1<Future<T>, Completable> toCompletable() {
        return new Function1<Future<T>, Completable>() { // from class: io.fotoapparat.result.adapter.rxjava.CompletableAdapter.toCompletable.1
            @Override // kotlin.jvm.functions.Function1
            public final Completable invoke(Future<T> future) {
                Intrinsics.checkParameterIsNotNull(future, "future");
                Completable completableFromFuture = Completable.fromFuture(future);
                Intrinsics.checkExpressionValueIsNotNull(completableFromFuture, "Completable.fromFuture(future)");
                return completableFromFuture;
            }
        };
    }
}
