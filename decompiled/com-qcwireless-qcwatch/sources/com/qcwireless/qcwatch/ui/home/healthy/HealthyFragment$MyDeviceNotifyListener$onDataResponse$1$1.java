package com.qcwireless.qcwatch.ui.home.healthy;

import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: HealthyFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1", f = "HealthyFragment.kt", i = {0}, l = {1068, 1071}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HealthyFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1(HealthyFragment healthyFragment, Continuation<? super HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1> continuation) {
        super(2, continuation);
        this.this$0 = healthyFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1 healthyFragment$MyDeviceNotifyListener$onDataResponse$1$1 = new HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1(this.this$0, continuation);
        healthyFragment$MyDeviceNotifyListener$onDataResponse$1$1.L$0 = obj;
        return healthyFragment$MyDeviceNotifyListener$onDataResponse$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        final HealthyFragment healthyFragment = this.this$0;
        this.L$0 = null;
        this.label = 2;
        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment$MyDeviceNotifyListener$onDataResponse$1$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                if (deviceSetting != null && deviceSetting.getBatteryWarming() && !UserConfig.INSTANCE.getInstance().getBatteryLow()) {
                    CoroutineScope coroutineScope2 = coroutineScope;
                    final HealthyFragment healthyFragment2 = healthyFragment;
                    ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment.MyDeviceNotifyListener.onDataResponse.1.1.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                            invoke2(coroutineScope3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CoroutineScope ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            healthyFragment2.showRingBatteryLowDialog();
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
