package com.qcwireless.qcwatch.ui.home.healthy.vm;

import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: HealthyViewModel.kt */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1", f = "HealthyViewModel.kt", i = {0, 0}, l = {775, 776}, m = "emit", n = {"this", "version"}, s = {"L$0", "I$0"})
/* loaded from: classes3.dex */
final class HealthyViewModel$checkWatchFaceDownload$1$1$emit$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HealthyViewModel.AnonymousClass1.C01751<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    HealthyViewModel$checkWatchFaceDownload$1$1$emit$1(HealthyViewModel.AnonymousClass1.C01751<? super T> c01751, Continuation<? super HealthyViewModel$checkWatchFaceDownload$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c01751;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((WatchFaceState<String>) null, (Continuation<? super Unit>) this);
    }
}
