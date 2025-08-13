package com.qcwireless.qcwatch.ui.device.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.req.BloodOxygenSettingReq;
import com.oudmon.ble.base.communication.req.BpSettingReq;
import com.oudmon.ble.base.communication.req.DegreeSwitchReq;
import com.oudmon.ble.base.communication.req.DndReq;
import com.oudmon.ble.base.communication.req.FindDeviceReq;
import com.oudmon.ble.base.communication.req.HeartRateSettingReq;
import com.oudmon.ble.base.communication.req.PalmScreenReq;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.req.TimeFormatReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.oudmon.ble.base.communication.rsp.BloodOxygenSettingRsp;
import com.oudmon.ble.base.communication.rsp.BpSettingRsp;
import com.oudmon.ble.base.communication.rsp.DegreeSwitchRsp;
import com.oudmon.ble.base.communication.rsp.DndRsp;
import com.oudmon.ble.base.communication.rsp.HeartRateSettingRsp;
import com.oudmon.ble.base.communication.rsp.PalmScreenRsp;
import com.oudmon.ble.base.communication.rsp.TimeFormatRsp;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DeviceSettingViewModel.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u0006\u0010!\u001a\u00020\u001aJ\u000e\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0007J\u000e\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u0007J\u0016\u0010'\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001b\u001a\u00020\u0007J\u0018\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020\u0007H\u0002J\u0010\u0010,\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u0007H\u0002J\u0006\u0010-\u001a\u00020\u001aJ\u000e\u0010.\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/vm/DeviceSettingViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "timeList", "", "Lcom/qcwireless/qcwatch/base/dialog/bean/ListDataBean;", "getTimeList", "()Ljava/util/List;", "setTimeList", "(Ljava/util/List;)V", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "unitList", "getUnitList", "setUnitList", "weatherUnitList", "getWeatherUnitList", "setWeatherUnitList", "execBO2", "", "setting", "execBpCmd", "execGesture", "execHeart", "execUnit", "execWeather", "findWatch", "initData", "mac", "", "initDialogData", "it", "saveDeviceSetting", "deviceSetting", "startSyncDeviceSettingByType", "type", "", "startSyncDeviceSettings", "syncDeviceSettings", "syncDeviceSettingsFromDevice", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceSettingViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;
    private List<ListDataBean> timeList;
    private List<ListDataBean> unitList;
    private List<ListDataBean> weatherUnitList;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execBO2$lambda-2, reason: not valid java name */
    public static final void m618execBO2$lambda2(BloodOxygenSettingRsp bloodOxygenSettingRsp) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execBpCmd$lambda-5, reason: not valid java name */
    public static final void m619execBpCmd$lambda5(BpSettingRsp bpSettingRsp) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execGesture$lambda-0, reason: not valid java name */
    public static final void m620execGesture$lambda0(PalmScreenRsp palmScreenRsp) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execHeart$lambda-1, reason: not valid java name */
    public static final void m621execHeart$lambda1(HeartRateSettingRsp heartRateSettingRsp) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execUnit$lambda-4, reason: not valid java name */
    public static final void m622execUnit$lambda4(TimeFormatRsp timeFormatRsp) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execWeather$lambda-3, reason: not valid java name */
    public static final void m623execWeather$lambda3(DegreeSwitchRsp degreeSwitchRsp) {
    }

    public DeviceSettingViewModel(DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this._uiState = new MutableLiveData<>();
        this.unitList = new ArrayList();
        this.weatherUnitList = new ArrayList();
        this.timeList = new ArrayList();
    }

    public final LiveData<DeviceSetting> getUiState() {
        return this._uiState;
    }

    public final List<ListDataBean> getUnitList() {
        return this.unitList;
    }

    public final void setUnitList(List<ListDataBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.unitList = list;
    }

    public final List<ListDataBean> getWeatherUnitList() {
        return this.weatherUnitList;
    }

    public final void setWeatherUnitList(List<ListDataBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.weatherUnitList = list;
    }

    public final List<ListDataBean> getTimeList() {
        return this.timeList;
    }

    public final void setTimeList(List<ListDataBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.timeList = list;
    }

    public final void saveDeviceSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    /* compiled from: DeviceSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$initData$1", f = "DeviceSettingViewModel.kt", i = {}, l = {61, 61}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$initData$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceSettingViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                DeviceSettingViewModel.this.getUnitList().clear();
                DeviceSettingViewModel.this.getWeatherUnitList().clear();
                DeviceSettingViewModel.this.getTimeList().clear();
                this.label = 1;
                obj = DeviceSettingViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final DeviceSettingViewModel deviceSettingViewModel = DeviceSettingViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel.initData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    deviceSettingViewModel._uiState.postValue(deviceSetting);
                    if (deviceSetting != null) {
                        deviceSettingViewModel.initDialogData(deviceSetting);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void initData(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(mac, null), 3, null);
    }

    public final void initDialogData(DeviceSetting it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.unitList.clear();
        this.weatherUnitList.clear();
        this.timeList.clear();
        if (it.getMetricUnit() == 0) {
            this.unitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_35), true));
            this.unitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_36), false));
        } else {
            this.unitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_35), false));
            this.unitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_36), true));
        }
        if (it.getWeatherFormat() == 0) {
            this.weatherUnitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_27), true));
            this.weatherUnitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_28), false));
        } else {
            this.weatherUnitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_27), false));
            this.weatherUnitList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_28), true));
        }
        if (it.getTimeFormat() == 0) {
            this.timeList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_26), true));
            this.timeList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_25), false));
        } else {
            this.timeList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_26), false));
            this.timeList.add(new ListDataBean(GlobalKt.getString(R.string.qc_text_25), true));
        }
    }

    public final void execGesture(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(PalmScreenReq.getWriteInstance(setting.getWristSense(), true), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda5
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m620execGesture$lambda0((PalmScreenRsp) baseRspCmd);
            }
        });
        launchOnUI(new AnonymousClass2(setting, null));
    }

    /* compiled from: DeviceSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$execGesture$2", f = "DeviceSettingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$execGesture$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DeviceSetting $setting;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DeviceSetting deviceSetting, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$setting = deviceSetting;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceSettingViewModel.this.new AnonymousClass2(this.$setting, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DeviceSettingViewModel.this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(this.$setting)));
            return Unit.INSTANCE;
        }
    }

    public final void execHeart(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(HeartRateSettingReq.getWriteInstance(setting.getHrDetection()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda4
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m621execHeart$lambda1((HeartRateSettingRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    public final void execBO2(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(BloodOxygenSettingReq.getWriteInstance(setting.getBo2Detection()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m618execBO2$lambda2((BloodOxygenSettingRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    public final void findWatch() {
        CommandHandle.getInstance().executeReqCmdNoCallback(new FindDeviceReq());
    }

    public final void execWeather(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(DegreeSwitchReq.getWriteInstance(true, setting.getWeatherFormat() == 0), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m623execWeather$lambda3((DegreeSwitchRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    public final void execUnit(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getWriteInstance(setting.getTimeFormat() == 0, (byte) setting.getMetricUnit()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda6
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m622execUnit$lambda4((TimeFormatRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    public final void execBpCmd(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        StartEndTimeEntity startEndTimeEntity = new StartEndTimeEntity(0, 0, 23, 59);
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
        CommandHandle.getInstance().executeReqCmd(BpSettingReq.getWriteInstance(setting.getBpSwitch(), startEndTimeEntity, 60), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m619execBpCmd$lambda5((BpSettingRsp) baseRspCmd);
            }
        });
    }

    public final void syncDeviceSettings() {
        if (UserConfig.INSTANCE.getInstance().getHwVersion().length() == 0) {
            CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadHwRequest());
        }
        if (UserConfig.INSTANCE.getInstance().getFmVersion().length() == 0) {
            CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadFmRequest());
        }
        CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 3), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda15
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m633syncDeviceSettings$lambda6((BatteryRsp) baseRspCmd);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05592(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSettings$lambda-6, reason: not valid java name */
    public static final void m633syncDeviceSettings$lambda6(BatteryRsp batteryRsp) {
        XLog.i(Integer.valueOf(batteryRsp.getBatteryValue()));
        UserConfig.INSTANCE.getInstance().setBattery(String.valueOf(batteryRsp.getBatteryValue()));
        UserConfig.INSTANCE.getInstance().save();
    }

    /* compiled from: DeviceSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$syncDeviceSettings$2", f = "DeviceSettingViewModel.kt", i = {}, l = {222, 223}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$syncDeviceSettings$2, reason: invalid class name and case insensitive filesystem */
    static final class C05592 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05592(Continuation<? super C05592> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceSettingViewModel.this.new C05592(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05592) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            final DeviceSettingViewModel deviceSettingViewModel = DeviceSettingViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel.syncDeviceSettings.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null) {
                        deviceSettingViewModel.startSyncDeviceSettings(deviceSetting);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSyncDeviceSettings(final DeviceSetting deviceSetting) {
        CommandHandle.getInstance().executeReqCmd(HeartRateSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda11
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m630startSyncDeviceSettings$lambda7(deviceSetting, this, (HeartRateSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(BloodOxygenSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m631startSyncDeviceSettings$lambda8(deviceSetting, this, (BloodOxygenSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(BpSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda7
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m632startSyncDeviceSettings$lambda9(deviceSetting, this, (BpSettingRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(PalmScreenReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda12
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m626startSyncDeviceSettings$lambda10(deviceSetting, this, (PalmScreenRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(DegreeSwitchReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda8
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m627startSyncDeviceSettings$lambda11(deviceSetting, this, (DegreeSwitchRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda13
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m628startSyncDeviceSettings$lambda12(deviceSetting, this, (TimeFormatRsp) baseRspCmd);
            }
        });
        CommandHandle.getInstance().executeReqCmd(DndReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda9
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DeviceSettingViewModel.m629startSyncDeviceSettings$lambda13(deviceSetting, this, (DndRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-7, reason: not valid java name */
    public static final void m630startSyncDeviceSettings$lambda7(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, HeartRateSettingRsp heartRateSettingRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        deviceSetting.setHrDetection(heartRateSettingRsp.isEnable());
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-8, reason: not valid java name */
    public static final void m631startSyncDeviceSettings$lambda8(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, BloodOxygenSettingRsp bloodOxygenSettingRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        deviceSetting.setBo2Detection(bloodOxygenSettingRsp.isEnable());
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-9, reason: not valid java name */
    public static final void m632startSyncDeviceSettings$lambda9(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, BpSettingRsp bpSettingRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        deviceSetting.setBpSwitch(bpSettingRsp.isEnable());
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-10, reason: not valid java name */
    public static final void m626startSyncDeviceSettings$lambda10(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, PalmScreenRsp palmScreenRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        deviceSetting.setWristSense(palmScreenRsp.isEnable());
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-11, reason: not valid java name */
    public static final void m627startSyncDeviceSettings$lambda11(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, DegreeSwitchRsp degreeSwitchRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Boolean.valueOf(degreeSwitchRsp.isCelsius()));
        if (degreeSwitchRsp.isCelsius()) {
            deviceSetting.setWeatherFormat(0);
            UserConfig.INSTANCE.getInstance().setTemperature(false);
        } else {
            deviceSetting.setWeatherFormat(1);
            UserConfig.INSTANCE.getInstance().setTemperature(true);
        }
        UserConfig.INSTANCE.getInstance().save();
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-12, reason: not valid java name */
    public static final void m628startSyncDeviceSettings$lambda12(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, TimeFormatRsp timeFormatRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (timeFormatRsp.is24()) {
            deviceSetting.setTimeFormat(0);
        } else {
            deviceSetting.setTimeFormat(1);
        }
        if (timeFormatRsp.isMetric()) {
            UserConfig.INSTANCE.getInstance().setMetric(true);
            deviceSetting.setMetricUnit(0);
        } else {
            UserConfig.INSTANCE.getInstance().setMetric(false);
            deviceSetting.setMetricUnit(1);
        }
        UserConfig.INSTANCE.getInstance().save();
        this$0.saveDeviceSetting(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettings$lambda-13, reason: not valid java name */
    public static final void m629startSyncDeviceSettings$lambda13(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, DndRsp dndRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(dndRsp);
        if (dndRsp == null || dndRsp.getDndEntity() == null) {
            return;
        }
        deviceSetting.setDisturbSwitch(dndRsp.isEnable());
        deviceSetting.setDisturbStart((dndRsp.getDndEntity().getStartHour() * 60) + dndRsp.getDndEntity().getStartMinute());
        deviceSetting.setDisturbEnd((dndRsp.getDndEntity().getEndHour() * 60) + dndRsp.getDndEntity().getEndMinute());
        deviceSetting.setDisturbManualSwitch(dndRsp.isManualDND());
        UserConfig.INSTANCE.getInstance().setManualDND(dndRsp.isManualDND());
        UserConfig.INSTANCE.getInstance().save();
        this$0.saveDeviceSetting(deviceSetting);
        this$0._uiState.postValue(deviceSetting);
    }

    public final void saveDeviceSetting(DeviceSetting deviceSetting) {
        Intrinsics.checkNotNullParameter(deviceSetting, "deviceSetting");
        DeviceSettingRepository.INSTANCE.getGetInstance().saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting)));
    }

    /* compiled from: DeviceSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$syncDeviceSettingsFromDevice$1", f = "DeviceSettingViewModel.kt", i = {}, l = {335, 336}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$syncDeviceSettingsFromDevice$1, reason: invalid class name and case insensitive filesystem */
    static final class C05601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05601(int i, Continuation<? super C05601> continuation) {
            super(2, continuation);
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceSettingViewModel.this.new C05601(this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            final DeviceSettingViewModel deviceSettingViewModel = DeviceSettingViewModel.this;
            final int i2 = this.$type;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel.syncDeviceSettingsFromDevice.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null) {
                        deviceSettingViewModel.startSyncDeviceSettingByType(i2, deviceSetting);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void syncDeviceSettingsFromDevice(int type) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05601(type, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettingByType$lambda-14, reason: not valid java name */
    public static final void m624startSyncDeviceSettingByType$lambda14(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, DndRsp dndRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(dndRsp);
        if (dndRsp == null || dndRsp.getDndEntity() == null) {
            return;
        }
        deviceSetting.setDisturbSwitch(dndRsp.isEnable());
        deviceSetting.setDisturbStart((dndRsp.getDndEntity().getStartHour() * 60) + dndRsp.getDndEntity().getStartMinute());
        deviceSetting.setDisturbEnd((dndRsp.getDndEntity().getEndHour() * 60) + dndRsp.getDndEntity().getEndMinute());
        deviceSetting.setDisturbManualSwitch(dndRsp.isManualDND());
        UserConfig.INSTANCE.getInstance().setManualDND(dndRsp.isManualDND());
        UserConfig.INSTANCE.getInstance().save();
        this$0.saveDeviceSetting(deviceSetting);
        this$0._uiState.postValue(deviceSetting);
        XLog.i(deviceSetting);
        EventBus.getDefault().post(new ManualRefreshEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSyncDeviceSettingByType(int type, final DeviceSetting deviceSetting) {
        if (type == 9) {
            CommandHandle.getInstance().executeReqCmd(DndReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda10
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    DeviceSettingViewModel.m624startSyncDeviceSettingByType$lambda14(deviceSetting, this, (DndRsp) baseRspCmd);
                }
            });
        } else {
            if (type != 11) {
                return;
            }
            CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel$$ExternalSyntheticLambda14
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    DeviceSettingViewModel.m625startSyncDeviceSettingByType$lambda15(deviceSetting, this, (TimeFormatRsp) baseRspCmd);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSyncDeviceSettingByType$lambda-15, reason: not valid java name */
    public static final void m625startSyncDeviceSettingByType$lambda15(DeviceSetting deviceSetting, DeviceSettingViewModel this$0, TimeFormatRsp timeFormatRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (timeFormatRsp.is24()) {
            deviceSetting.setTimeFormat(0);
        } else {
            deviceSetting.setTimeFormat(1);
        }
        if (timeFormatRsp.isMetric()) {
            UserConfig.INSTANCE.getInstance().setMetric(true);
            deviceSetting.setMetricUnit(0);
        } else {
            UserConfig.INSTANCE.getInstance().setMetric(false);
            deviceSetting.setMetricUnit(1);
        }
        UserConfig.INSTANCE.getInstance().save();
        this$0.saveDeviceSetting(deviceSetting);
        this$0._uiState.postValue(deviceSetting);
    }
}
