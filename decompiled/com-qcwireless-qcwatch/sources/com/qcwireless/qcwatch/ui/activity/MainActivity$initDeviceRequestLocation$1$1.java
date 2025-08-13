package com.qcwireless.qcwatch.ui.activity;

import com.oudmon.ble.base.communication.LargeDataHandler;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.activity.MainActivity$initDeviceRequestLocation$1$1", f = "MainActivity.kt", i = {}, l = {285, 286}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class MainActivity$initDeviceRequestLocation$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$initDeviceRequestLocation$1$1(MainActivity mainActivity, Continuation<? super MainActivity$initDeviceRequestLocation$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$initDeviceRequestLocation$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$initDeviceRequestLocation$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceLocation(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final MainActivity mainActivity = this.this$0;
        this.label = 2;
        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$initDeviceRequestLocation$1$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                return emit((MyLocationBean) obj2, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(MyLocationBean myLocationBean, Continuation<? super Unit> continuation) {
                if (myLocationBean != null) {
                    String address = myLocationBean.getAddress();
                    byte[] bytes = address.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    if (bytes.length > 100) {
                        address = MetricUtilsKt.getWholeText(address, 98);
                    }
                    String str = address;
                    if (str.length() == 0) {
                        return Unit.INSTANCE;
                    }
                    if (PermissionUtilKt.hasLocationPermission(mainActivity)) {
                        LargeDataHandler.getInstance().writeLocation(myLocationBean.getLongitude(), myLocationBean.getLatitude(), str);
                    }
                }
                return Unit.INSTANCE;
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
