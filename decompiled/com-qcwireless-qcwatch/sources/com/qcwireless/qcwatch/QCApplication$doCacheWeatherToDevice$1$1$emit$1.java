package com.qcwireless.qcwatch;

import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QCApplication.kt */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1", f = "QCApplication.kt", i = {0}, l = {679, 680}, m = "emit", n = {"this"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class QCApplication$doCacheWeatherToDevice$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QCApplication.AnonymousClass1.C00331<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    QCApplication$doCacheWeatherToDevice$1$1$emit$1(QCApplication.AnonymousClass1.C00331<? super T> c00331, Continuation<? super QCApplication$doCacheWeatherToDevice$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c00331;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((MyLocationBean) null, (Continuation<? super Unit>) this);
    }
}
