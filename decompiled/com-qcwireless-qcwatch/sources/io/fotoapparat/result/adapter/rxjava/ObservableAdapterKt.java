package io.fotoapparat.result.adapter.rxjava;

import androidx.exifinterface.media.ExifInterface;
import io.fotoapparat.result.PendingResult;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;

/* compiled from: ObservableAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, d2 = {"toObservable", "Lrx/Observable;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/fotoapparat/result/PendingResult;", "adapter-rxjava_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ObservableAdapterKt {
    public static final <T> Observable<T> toObservable(PendingResult<T> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Object objAdapt = receiver$0.adapt(new Function1<Future<T>, Observable<T>>() { // from class: io.fotoapparat.result.adapter.rxjava.ObservableAdapterKt.toObservable.1
            @Override // kotlin.jvm.functions.Function1
            public final Observable<T> invoke(Future<T> future) {
                Intrinsics.checkParameterIsNotNull(future, "future");
                Observable<T> observableFrom = Observable.from(future);
                Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Observable.from(future)");
                return observableFrom;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(objAdapt, "adapt { future -> Observable.from(future) }");
        return (Observable) objAdapt;
    }
}
