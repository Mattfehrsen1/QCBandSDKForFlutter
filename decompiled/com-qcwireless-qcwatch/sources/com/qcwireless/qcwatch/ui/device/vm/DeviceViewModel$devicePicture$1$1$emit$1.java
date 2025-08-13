package com.qcwireless.qcwatch.ui.device.vm;

import com.qcwireless.qcwatch.ui.base.bean.device.DevicePictureBean;
import com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DeviceViewModel.kt */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1", f = "DeviceViewModel.kt", i = {0}, l = {190, 191}, m = "emit", n = {"this"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class DeviceViewModel$devicePicture$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DeviceViewModel.C05611.C01381<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DeviceViewModel$devicePicture$1$1$emit$1(DeviceViewModel.C05611.C01381<? super T> c01381, Continuation<? super DeviceViewModel$devicePicture$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c01381;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((DevicePictureBean) null, (Continuation<? super Unit>) this);
    }
}
