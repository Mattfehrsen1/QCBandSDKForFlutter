package com.qcwireless.qcwatch.ui.mine;

import android.net.Uri;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.realsil.sdk.core.bluetooth.connection.le.GattClient;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: MineFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.MineFragment$callback$1$onCropImage$1", f = "MineFragment.kt", i = {}, l = {526, GattClient.STATE_PREPARED}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class MineFragment$callback$1$onCropImage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Uri $imageUri;
    int label;
    final /* synthetic */ MineFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MineFragment$callback$1$onCropImage$1(MineFragment mineFragment, Uri uri, Continuation<? super MineFragment$callback$1$onCropImage$1> continuation) {
        super(2, continuation);
        this.this$0 = mineFragment;
        this.$imageUri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MineFragment$callback$1$onCropImage$1(this.this$0, this.$imageUri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MineFragment$callback$1$onCropImage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
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
            ResultKt.throwOnFailure(obj);
        }
        final MineFragment mineFragment = this.this$0;
        final Uri uri = this.$imageUri;
        this.label = 2;
        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$callback$1$onCropImage$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                if (deviceSetting != null) {
                    mineFragment.setPictureToDevice(String.valueOf(uri.getPath()), deviceSetting);
                }
                return Unit.INSTANCE;
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
