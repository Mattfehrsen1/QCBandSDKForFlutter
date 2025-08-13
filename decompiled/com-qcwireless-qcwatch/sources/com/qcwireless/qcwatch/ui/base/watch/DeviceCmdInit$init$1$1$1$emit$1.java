package com.qcwireless.qcwatch.ui.base.watch;

import com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$init$1$1;
import com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DeviceCmdInit.kt */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$init$1$1$1", f = "DeviceCmdInit.kt", i = {0, 0, 1, 1}, l = {265, 267}, m = "emit", n = {"this", "map", "this", "map"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
final class DeviceCmdInit$init$1$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DeviceCmdInit$init$1$1.AnonymousClass1<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DeviceCmdInit$init$1$1$1$emit$1(DeviceCmdInit$init$1$1.AnonymousClass1<? super T> anonymousClass1, Continuation<? super DeviceCmdInit$init$1$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((Map<Integer, ItemEntity>) null, (Continuation<? super Unit>) this);
    }
}
