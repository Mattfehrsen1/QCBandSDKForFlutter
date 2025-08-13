package com.qcwireless.qcwatch.ui.base.watch;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.req.BindAncsReq;
import com.oudmon.ble.base.communication.req.BloodOxygenSettingReq;
import com.oudmon.ble.base.communication.req.BpSettingReq;
import com.oudmon.ble.base.communication.req.DegreeSwitchReq;
import com.oudmon.ble.base.communication.req.DeviceAvatarReq;
import com.oudmon.ble.base.communication.req.DeviceSupportReq;
import com.oudmon.ble.base.communication.req.DndReq;
import com.oudmon.ble.base.communication.req.HeartRateSettingReq;
import com.oudmon.ble.base.communication.req.HrvSettingReq;
import com.oudmon.ble.base.communication.req.PalmScreenReq;
import com.oudmon.ble.base.communication.req.PressureSettingReq;
import com.oudmon.ble.base.communication.req.SetANCSReq;
import com.oudmon.ble.base.communication.req.SetMessagePushReq;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.req.TargetSettingReq;
import com.oudmon.ble.base.communication.req.TimeFormatReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.oudmon.ble.base.communication.rsp.BloodOxygenSettingRsp;
import com.oudmon.ble.base.communication.rsp.BpSettingRsp;
import com.oudmon.ble.base.communication.rsp.DegreeSwitchRsp;
import com.oudmon.ble.base.communication.rsp.DeviceAvatarRsp;
import com.oudmon.ble.base.communication.rsp.DeviceSupportFunctionRsp;
import com.oudmon.ble.base.communication.rsp.DndRsp;
import com.oudmon.ble.base.communication.rsp.HRVSettingRsp;
import com.oudmon.ble.base.communication.rsp.HeartRateSettingRsp;
import com.oudmon.ble.base.communication.rsp.PalmScreenRsp;
import com.oudmon.ble.base.communication.rsp.PressureSettingRsp;
import com.oudmon.ble.base.communication.rsp.ReadMessagePushRsp;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.oudmon.ble.base.communication.rsp.TargetSettingRsp;
import com.oudmon.ble.base.communication.rsp.TimeFormatRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.BatteryLowEvent;
import com.qcwireless.qcwatch.base.event.BluetoothSyncEvent;
import com.qcwireless.qcwatch.base.event.DataSyncEvent;
import com.qcwireless.qcwatch.base.event.DeviceNoScreenEvent;
import com.qcwireless.qcwatch.base.event.DeviceNotifyTypeEvent;
import com.qcwireless.qcwatch.base.event.WatchSupportEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit;
import com.qcwireless.qcwatch.ui.device.push.call.PhoneNotifyListener;
import java.util.Date;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DeviceCmdInit.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\t\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/watch/DeviceCmdInit;", "", "()V", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "init", "", "initDeviceSetting", "saveDeviceSetting", "syncDeviceSetting", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceCmdInit {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DeviceCmdInit> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DeviceCmdInit>() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeviceCmdInit invoke() {
            return new DeviceCmdInit();
        }
    });
    private DeviceSetting deviceSetting;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-12$lambda-11, reason: not valid java name */
    public static final void m347syncDeviceSetting$lambda12$lambda11(TimeFormatRsp timeFormatRsp) {
    }

    /* compiled from: DeviceCmdInit.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$initDeviceSetting$1", f = "DeviceCmdInit.kt", i = {}, l = {93, 94}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$initDeviceSetting$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceCmdInit.this.new AnonymousClass1(continuation);
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
            final DeviceCmdInit deviceCmdInit = DeviceCmdInit.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit.initDeviceSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null) {
                        deviceCmdInit.deviceSetting = deviceSetting;
                        deviceCmdInit.init();
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void initDeviceSetting() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(null), 3, null);
    }

    public final void init() {
        FileHandle.getInstance().clearCallback();
        BleOperateManager.getInstance().addNotifyListener(17, new PhoneNotifyListener(QCApplication.INSTANCE.getCONTEXT()));
        EventBus.getDefault().post(new DataSyncEvent(true));
        CommandHandle.getInstance().executeReqCmd(new SetTimeReq(1), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda5
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m341init$lambda0((SetTimeRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(DeviceSupportReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m342init$lambda1((DeviceSupportFunctionRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(new SetMessagePushReq(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda4
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m343init$lambda2((ReadMessagePushRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 3), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m344init$lambda3((BatteryRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmdNoCallback(new BindAncsReq());
        syncDeviceSetting();
        QCApplication.INSTANCE.getGetInstance().getLocationOnce(QCApplication.INSTANCE.getApplication());
        EventBus.getDefault().post(new BluetoothSyncEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-0, reason: not valid java name */
    public static final void m341init$lambda0(SetTimeRsp setTimeRsp) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DeviceCmdInit$init$1$1(setTimeRsp, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-1, reason: not valid java name */
    public static final void m342init$lambda1(DeviceSupportFunctionRsp deviceSupportFunctionRsp) {
        XLog.i(deviceSupportFunctionRsp);
        UserConfig.INSTANCE.getInstance().setDeviceThemeSupport(deviceSupportFunctionRsp.supportWatchTheme);
        UserConfig.INSTANCE.getInstance().setDeviceWallpaperSupport(deviceSupportFunctionRsp.supportMenuWallpaper);
        EventBus.getDefault().post(new WatchSupportEvent());
        UserConfig.INSTANCE.getInstance().setDeviceNotScreen(deviceSupportFunctionRsp.deviceNoScreen);
        UserConfig.INSTANCE.getInstance().setSupportAiAnalyze(deviceSupportFunctionRsp.supportAiAnalyze);
        UserConfig.INSTANCE.getInstance().setNotSupportTakePhoto(deviceSupportFunctionRsp.notSupportTakePhoto);
        UserConfig.INSTANCE.getInstance().save();
        if (deviceSupportFunctionRsp.deviceNoScreen) {
            Map<String, Integer> screenMap = QJavaApplication.getInstance().getScreenMap();
            Intrinsics.checkNotNullExpressionValue(screenMap, "getInstance().screenMap");
            screenMap.put(UserConfig.INSTANCE.getInstance().getDeviceAddress(), 1);
            UserConfig.INSTANCE.getInstance().setShowPlate(false);
        } else {
            Map<String, Integer> screenMap2 = QJavaApplication.getInstance().getScreenMap();
            Intrinsics.checkNotNullExpressionValue(screenMap2, "getInstance().screenMap");
            screenMap2.put(UserConfig.INSTANCE.getInstance().getDeviceAddress(), 2);
            UserConfig.INSTANCE.getInstance().setShowPlate(true);
        }
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new DeviceNoScreenEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-2, reason: not valid java name */
    public static final void m343init$lambda2(ReadMessagePushRsp readMessagePushRsp) {
        XLog.i(Integer.valueOf(readMessagePushRsp.getDeviceSupport1()));
        UserConfig.INSTANCE.getInstance().setMessagePushSupport(readMessagePushRsp.getDeviceSupport1());
        UserConfig.INSTANCE.getInstance().save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-3, reason: not valid java name */
    public static final void m344init$lambda3(BatteryRsp batteryRsp) {
        UserConfig.INSTANCE.getInstance().setBattery(String.valueOf(batteryRsp.getBatteryValue()));
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new BatteryLowEvent());
    }

    private final void syncDeviceSetting() {
        CommandHandle.getInstance().executeReqCmd(HeartRateSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda12
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m351syncDeviceSetting$lambda4(this.f$0, (HeartRateSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(HrvSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda11
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m352syncDeviceSetting$lambda5(this.f$0, (HRVSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(BloodOxygenSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m353syncDeviceSetting$lambda6(this.f$0, (BloodOxygenSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(PressureSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda14
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m354syncDeviceSetting$lambda7(this.f$0, (PressureSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(BpSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda7
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m355syncDeviceSetting$lambda8(this.f$0, (BpSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(PalmScreenReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda13
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m356syncDeviceSetting$lambda9(this.f$0, (PalmScreenRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(DegreeSwitchReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda8
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m345syncDeviceSetting$lambda10(this.f$0, (DegreeSwitchRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m346syncDeviceSetting$lambda12(this.f$0, (TimeFormatRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(DndReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda10
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m348syncDeviceSetting$lambda13(this.f$0, (DndRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(TargetSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda15
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m349syncDeviceSetting$lambda14(this.f$0, (TargetSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmdNoCallback(new SetANCSReq());
        CommandHandle.getInstance().executeReqCmd(new DeviceAvatarReq(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda9
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m350syncDeviceSetting$lambda15(this.f$0, (DeviceAvatarRsp) baseRspCmd);
            }
        });
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceCmdInit, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit.syncDeviceSetting.12
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceCmdInit deviceCmdInit) {
                invoke2(deviceCmdInit);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceCmdInit ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserEntity userEntityQueryUserByUid = UserProfileRepository.INSTANCE.getGetInstance().queryUserByUid(UserConfig.INSTANCE.getInstance().getUid());
                if (userEntityQueryUserByUid != null) {
                    Date dateString2Date = DateUtil.String2Date("yyyy-MM-dd", userEntityQueryUserByUid.getBirthday() + "-01");
                    Ref.IntRef intRef = new Ref.IntRef();
                    intRef.element = 28;
                    try {
                        intRef.element = DateUtil.getAgeByBirthday(dateString2Date);
                    } catch (Exception unused) {
                    }
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new AnonymousClass1(userEntityQueryUserByUid, intRef));
                }
            }

            /* compiled from: DeviceCmdInit.kt */
            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/qcwireless/qcwatch/ui/base/watch/DeviceCmdInit;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$syncDeviceSetting$12$1, reason: invalid class name */
            static final class AnonymousClass1 extends Lambda implements Function1<DeviceCmdInit, Unit> {
                final /* synthetic */ Ref.IntRef $age;
                final /* synthetic */ UserEntity $userEntity;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(UserEntity userEntity, Ref.IntRef intRef) {
                    super(1);
                    this.$userEntity = userEntity;
                    this.$age = intRef;
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* renamed from: invoke$lambda-0, reason: not valid java name */
                public static final void m357invoke$lambda0(TimeFormatRsp timeFormatRsp) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DeviceCmdInit deviceCmdInit) throws InterruptedException {
                    invoke2(deviceCmdInit);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DeviceCmdInit ktxRunOnUi) throws InterruptedException {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    CommandHandle commandHandle = CommandHandle.getInstance();
                    DeviceSetting deviceSetting = ktxRunOnUi.deviceSetting;
                    DeviceSetting deviceSetting2 = null;
                    if (deviceSetting == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                        deviceSetting = null;
                    }
                    boolean z = deviceSetting.getTimeFormat() == 0;
                    DeviceSetting deviceSetting3 = ktxRunOnUi.deviceSetting;
                    if (deviceSetting3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    } else {
                        deviceSetting2 = deviceSetting3;
                    }
                    commandHandle.executeReqCmd(TimeFormatReq.getWriteInstance(z, deviceSetting2.getMetricUnit(), this.$userEntity.getGender() - 1, this.$age.element, (int) this.$userEntity.getHeight(), (int) this.$userEntity.getWeight(), 120, 90, 0), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$syncDeviceSetting$12$1$$ExternalSyntheticLambda0
                        @Override // com.oudmon.ble.base.communication.ICommandResponse
                        public final void onDataResponse(BaseRspCmd baseRspCmd) {
                            DeviceCmdInit.AnonymousClass12.AnonymousClass1.m357invoke$lambda0((TimeFormatRsp) baseRspCmd);
                        }
                    });
                    XLog.i(this.$userEntity.getNickName());
                    LargeDataHandler.getInstance().setDeviceNickName(this.$userEntity.getNickName());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-4, reason: not valid java name */
    public static final void m351syncDeviceSetting$lambda4(DeviceCmdInit this$0, HeartRateSettingRsp heartRateSettingRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setHrDetection(heartRateSettingRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-5, reason: not valid java name */
    public static final void m352syncDeviceSetting$lambda5(DeviceCmdInit this$0, HRVSettingRsp hRVSettingRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setHrvEnable(hRVSettingRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-6, reason: not valid java name */
    public static final void m353syncDeviceSetting$lambda6(DeviceCmdInit this$0, BloodOxygenSettingRsp bloodOxygenSettingRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setBo2Detection(bloodOxygenSettingRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-7, reason: not valid java name */
    public static final void m354syncDeviceSetting$lambda7(DeviceCmdInit this$0, PressureSettingRsp pressureSettingRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setPressureDetection(pressureSettingRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-8, reason: not valid java name */
    public static final void m355syncDeviceSetting$lambda8(DeviceCmdInit this$0, BpSettingRsp bpSettingRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setBpSwitch(bpSettingRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-9, reason: not valid java name */
    public static final void m356syncDeviceSetting$lambda9(DeviceCmdInit this$0, PalmScreenRsp palmScreenRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setWristSense(palmScreenRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-10, reason: not valid java name */
    public static final void m345syncDeviceSetting$lambda10(DeviceCmdInit this$0, DegreeSwitchRsp degreeSwitchRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (degreeSwitchRsp.isCelsius()) {
            UserConfig.INSTANCE.getInstance().setTemperature(false);
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setWeatherFormat(0);
        } else {
            UserConfig.INSTANCE.getInstance().setTemperature(true);
            DeviceSetting deviceSetting3 = this$0.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting3 = null;
            }
            deviceSetting3.setWeatherFormat(1);
        }
        UserConfig.INSTANCE.getInstance().save();
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting4;
        }
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-12, reason: not valid java name */
    public static final void m346syncDeviceSetting$lambda12(DeviceCmdInit this$0, TimeFormatRsp timeFormatRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (timeFormatRsp.is24()) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setTimeFormat(0);
        } else {
            DeviceSetting deviceSetting3 = this$0.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting3 = null;
            }
            deviceSetting3.setTimeFormat(1);
        }
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            DeviceSetting deviceSetting4 = this$0.deviceSetting;
            if (deviceSetting4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting4 = null;
            }
            deviceSetting4.setMetricUnit(0);
        } else {
            DeviceSetting deviceSetting5 = this$0.deviceSetting;
            if (deviceSetting5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting5 = null;
            }
            deviceSetting5.setMetricUnit(1);
        }
        DeviceSetting deviceSetting6 = this$0.deviceSetting;
        if (deviceSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting6 = null;
        }
        this$0.saveDeviceSetting(deviceSetting6);
        CommandHandle commandHandle = CommandHandle.getInstance();
        DeviceSetting deviceSetting7 = this$0.deviceSetting;
        if (deviceSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting7 = null;
        }
        boolean z = deviceSetting7.getTimeFormat() == 0;
        DeviceSetting deviceSetting8 = this$0.deviceSetting;
        if (deviceSetting8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting8;
        }
        commandHandle.executeReqCmd(TimeFormatReq.getWriteInstance(z, (byte) deviceSetting.getMetricUnit()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$$ExternalSyntheticLambda6
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceCmdInit.m347syncDeviceSetting$lambda12$lambda11((TimeFormatRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-13, reason: not valid java name */
    public static final void m348syncDeviceSetting$lambda13(DeviceCmdInit this$0, DndRsp dndRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (dndRsp == null || dndRsp.getDndEntity() == null) {
            return;
        }
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setDisturbSwitch(dndRsp.isEnable());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting3 = null;
        }
        deviceSetting3.setDisturbStart((dndRsp.getDndEntity().getStartHour() * 60) + dndRsp.getDndEntity().getStartMinute());
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        deviceSetting4.setDisturbEnd((dndRsp.getDndEntity().getEndHour() * 60) + dndRsp.getDndEntity().getEndMinute());
        DeviceSetting deviceSetting5 = this$0.deviceSetting;
        if (deviceSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting5 = null;
        }
        deviceSetting5.setDisturbManualSwitch(dndRsp.isManualDND());
        UserConfig.INSTANCE.getInstance().setManualDND(dndRsp.isManualDND());
        UserConfig.INSTANCE.getInstance().save();
        DeviceSetting deviceSetting6 = this$0.deviceSetting;
        if (deviceSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting6;
        }
        this$0.saveDeviceSetting(deviceSetting2);
        EventBus.getDefault().post(new DataSyncEvent(false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-14, reason: not valid java name */
    public static final void m349syncDeviceSetting$lambda14(DeviceCmdInit this$0, final TargetSettingRsp targetSettingRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(targetSettingRsp);
        if (targetSettingRsp.getAction() == 1) {
            ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<DeviceCmdInit, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$syncDeviceSetting$10$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DeviceCmdInit deviceCmdInit) {
                    invoke2(deviceCmdInit);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DeviceCmdInit ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    if (targetSettingRsp.getStep() > 0) {
                        UserProfileRepository.INSTANCE.getGetInstance().insertTarget(new TargetEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), targetSettingRsp.getStep(), targetSettingRsp.getCalorie() / 1000.0f, targetSettingRsp.getDistance() / 1000.0f, 3.0f, 8.0f));
                        EventBus.getDefault().post(new DeviceNotifyTypeEvent(16));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSetting$lambda-15, reason: not valid java name */
    public static final void m350syncDeviceSetting$lambda15(DeviceCmdInit this$0, DeviceAvatarRsp deviceAvatarRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i("-----------------------------");
        XLog.i(deviceAvatarRsp);
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setAvatarScreen(deviceAvatarRsp.getScreenType());
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting3 = null;
        }
        deviceSetting3.setAvatarWidth(deviceAvatarRsp.getAvatarWidth());
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        deviceSetting4.setAvatarHeight(deviceAvatarRsp.getAvatarHeight());
        DeviceSetting deviceSetting5 = this$0.deviceSetting;
        if (deviceSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting5;
        }
        this$0.saveDeviceSetting(deviceSetting2);
    }

    public final void saveDeviceSetting(DeviceSetting deviceSetting) {
        Intrinsics.checkNotNullParameter(deviceSetting, "deviceSetting");
        DeviceSettingRepository.INSTANCE.getGetInstance().saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting)));
    }

    /* compiled from: DeviceCmdInit.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/watch/DeviceCmdInit$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/watch/DeviceCmdInit;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/watch/DeviceCmdInit;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeviceCmdInit getGetInstance() {
            return (DeviceCmdInit) DeviceCmdInit.getInstance$delegate.getValue();
        }
    }
}
