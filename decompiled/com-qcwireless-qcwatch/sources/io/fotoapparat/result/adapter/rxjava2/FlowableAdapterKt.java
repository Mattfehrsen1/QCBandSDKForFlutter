package io.fotoapparat.result.adapter.rxjava2;

import androidx.exifinterface.media.ExifInterface;
import io.fotoapparat.result.PendingResult;
import io.reactivex.Flowable;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowableAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"toFlowable", "Lio/reactivex/Flowable;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/fotoapparat/result/PendingResult;", "adapter-rxjava2_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class FlowableAdapterKt {
    public static final <T> Flowable<T> toFlowable(PendingResult<T> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Object objAdapt = receiver$0.adapt(new Function1<Future<T>, Flowable<T>>() { // from class: io.fotoapparat.result.adapter.rxjava2.FlowableAdapterKt.toFlowable.1
            @Override // kotlin.jvm.functions.Function1
            public final Flowable<T> invoke(Future<T> future) {
                Intrinsics.checkParameterIsNotNull(future, "future");
                Flowable<T> flowableFromFuture = Flowable.fromFuture(future);
                Intrinsics.checkExpressionValueIsNotNull(flowableFromFuture, "Flowable.fromFuture(future)");
                return flowableFromFuture;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(objAdapt, "adapt { future -> Flowable.fromFuture(future) }");
        return (Flowable) objAdapt;
    }
}
