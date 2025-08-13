package com.qcwireless.qcwatch.ui.mine.ucenter;

import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSupportRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: UserProfileActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$8$2", f = "UserProfileActivity.kt", i = {}, l = {146, 147}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class UserProfileActivity$setupViews$1$8$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ UserProfileActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UserProfileActivity$setupViews$1$8$2(UserProfileActivity userProfileActivity, Continuation<? super UserProfileActivity$setupViews$1$8$2> continuation) {
        super(2, continuation);
        this.this$0 = userProfileActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UserProfileActivity$setupViews$1$8$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UserProfileActivity$setupViews$1$8$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DeviceSupportRepository.INSTANCE.getGetInstance().getDeviceSupport(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
        final UserProfileActivity userProfileActivity = this.this$0;
        this.label = 2;
        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$8$2.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                return emit((Map<Integer, ItemEntity>) obj2, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(Map<Integer, ItemEntity> map, Continuation<? super Unit> continuation) {
                UserEntity userEntity = userProfileActivity.userEntity;
                UserEntity userEntity2 = null;
                if (userEntity == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                    userEntity = null;
                }
                if (userEntity.getGender() != 1) {
                    UserEntity userEntity3 = userProfileActivity.userEntity;
                    if (userEntity3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                    } else {
                        userEntity2 = userEntity3;
                    }
                    if (userEntity2.getGender() == 2 && map != null) {
                        ItemEntity itemEntity = map.get(Boxing.boxInt(5));
                        if (itemEntity != null) {
                            itemEntity.setChecked(true);
                        }
                        DeviceSupportRepository.INSTANCE.getGetInstance().saveDeviceSupport(map);
                    }
                } else if (map != null) {
                    ItemEntity itemEntity2 = map.get(Boxing.boxInt(5));
                    if (itemEntity2 != null) {
                        itemEntity2.setChecked(false);
                    }
                    DeviceSupportRepository.INSTANCE.getGetInstance().saveDeviceSupport(map);
                }
                return Unit.INSTANCE;
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
