package com.qcwireless.qcwatch.ui.mine.ucenter;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.TimeFormatReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.TimeFormatRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: FirstProfileViewModel.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J&\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013J\u0006\u0010\u0017\u001a\u00020\u000eJ\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/FirstProfileViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "userEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "getUserEntity", "()Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "setUserEntity", "(Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;)V", "execUnit", "", "setting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "execUserInfoToDevice", "boy", "", "age", "height", "weight", "initNotLoginUserEntity", "saveUserEntity", "entity", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FirstProfileViewModel extends BaseViewModel {
    private final DeviceSettingRepository deviceSettingRepository;
    public UserEntity userEntity;
    private final UserProfileRepository userProfileRepository;

    public FirstProfileViewModel(UserProfileRepository userProfileRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.userProfileRepository = userProfileRepository;
        this.deviceSettingRepository = deviceSettingRepository;
    }

    public final UserEntity getUserEntity() {
        UserEntity userEntity = this.userEntity;
        if (userEntity != null) {
            return userEntity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("userEntity");
        return null;
    }

    public final void setUserEntity(UserEntity userEntity) {
        Intrinsics.checkNotNullParameter(userEntity, "<set-?>");
        this.userEntity = userEntity;
    }

    public final void saveUserEntity(final UserEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<FirstProfileViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel.saveUserEntity.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FirstProfileViewModel firstProfileViewModel) {
                invoke2(firstProfileViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FirstProfileViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.userProfileRepository.insertUser(entity);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(entity, null), 3, null);
    }

    /* compiled from: FirstProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel$saveUserEntity$2", f = "FirstProfileViewModel.kt", i = {}, l = {37, 37}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel$saveUserEntity$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ UserEntity $entity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(UserEntity userEntity, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$entity = userEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FirstProfileViewModel.this.new AnonymousClass2(this.$entity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = FirstProfileViewModel.this.userProfileRepository.updateUserProfileToServer(this.$entity, this);
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
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel.saveUserEntity.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    XLog.i(netState);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FirstProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel$execUserInfoToDevice$1", f = "FirstProfileViewModel.kt", i = {}, l = {45, 46}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel$execUserInfoToDevice$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $age;
        final /* synthetic */ int $boy;
        final /* synthetic */ int $height;
        final /* synthetic */ int $weight;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, int i2, int i3, int i4, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$boy = i;
            this.$age = i2;
            this.$height = i3;
            this.$weight = i4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$boy, this.$age, this.$height, this.$weight, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            this.label = 2;
            if (((Flow) obj).collect(new C02121(this.$boy, this.$age, this.$height, this.$weight), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: FirstProfileViewModel.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "emit", "(Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel$execUserInfoToDevice$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02121<T> implements FlowCollector {
            final /* synthetic */ int $age;
            final /* synthetic */ int $boy;
            final /* synthetic */ int $height;
            final /* synthetic */ int $weight;

            C02121(int i, int i2, int i3, int i4) {
                this.$boy = i;
                this.$age = i2;
                this.$height = i3;
                this.$weight = i4;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: emit$lambda-0, reason: not valid java name */
            public static final void m990emit$lambda0(TimeFormatRsp timeFormatRsp) {
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((DeviceSetting) obj, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                if (deviceSetting != null) {
                    CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getWriteInstance(deviceSetting.getTimeFormat() == 0, deviceSetting.getMetricUnit(), this.$boy, this.$age, this.$height, this.$weight, 120, 90, BDLocation.TypeCoarseLocation), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel$execUserInfoToDevice$1$1$$ExternalSyntheticLambda0
                        @Override // com.oudmon.ble.base.communication.ICommandResponse
                        public final void onDataResponse(BaseRspCmd baseRspCmd) {
                            FirstProfileViewModel.AnonymousClass1.C02121.m990emit$lambda0((TimeFormatRsp) baseRspCmd);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void execUserInfoToDevice(int boy, int age, int height, int weight) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(boy, age, height, weight, null), 3, null);
    }

    public final void execUnit(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getWriteInstance(setting.getTimeFormat() == 0, (byte) setting.getMetricUnit()), null);
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    public final void initNotLoginUserEntity() {
        long uid = UserConfig.INSTANCE.getInstance().getUid();
        String y_m_d = new DateUtil().getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
        setUserEntity(new UserEntity(uid, "", "", 1, 70.0f, 140, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, y_m_d, 0));
    }
}
