package com.qcwireless.qcwatch.ui.device.remind;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.AlarmNewEntity;
import com.oudmon.ble.base.communication.entity.AlarmEntity;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.req.ReadAlarmReq;
import com.oudmon.ble.base.communication.req.ReadDrinkAlarmReq;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.ReadAlarmRsp;
import com.oudmon.ble.base.communication.rsp.ReadSitLongRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.AlarmBean;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.bean.device.DrinkBean;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: AiReminderViewModel.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cJ6\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\t2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0002J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0018\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0016\u0010*\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/AiReminderViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "alarmList", "", "Lcom/qcwireless/qcwatch/ui/base/bean/device/AlarmBean;", "getAlarmList", "()Ljava/util/List;", "drinkIndexMap", "", "", "max", "getMax", "()I", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "execAlarm", "", "setting", "initData", "mac", "", "parseAlarm", "total", "list", "Lcom/oudmon/ble/base/communication/bigData/AlarmNewEntity$AlarmBean;", "data", "", "alarmLength", "alarmStart", "readAlarmOnly", "deviceSetting", "readDrinkData", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "readLongSit", "saveReminder", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AiReminderViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _uiState;
    private final List<AlarmBean> alarmList;
    private final DeviceSettingRepository deviceSettingRepository;
    private final Map<Integer, Integer> drinkIndexMap;
    private final int max;

    public AiReminderViewModel(DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this.alarmList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this.drinkIndexMap = new LinkedHashMap();
        this.max = 10;
    }

    public final List<AlarmBean> getAlarmList() {
        return this.alarmList;
    }

    public final LiveData<DeviceSetting> getUiState() {
        return this._uiState;
    }

    public final int getMax() {
        return this.max;
    }

    public final void initData(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        if (!UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
            this.drinkIndexMap.clear();
            for (int i = 0; i < 8; i++) {
                this.drinkIndexMap.put(Integer.valueOf(i), Integer.valueOf(i));
            }
        }
        launchOnUI(new AnonymousClass1(mac, null));
    }

    /* compiled from: AiReminderViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel$initData$1", f = "AiReminderViewModel.kt", i = {}, l = {53, 53}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel$initData$1, reason: invalid class name */
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
            return AiReminderViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = AiReminderViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final AiReminderViewModel aiReminderViewModel = AiReminderViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel.initData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) throws InterruptedException {
                    if (deviceSetting != null) {
                        aiReminderViewModel.readAlarmOnly(deviceSetting);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    private final void readDrinkData(final int index, final DeviceSetting deviceSetting) {
        this.drinkIndexMap.remove(Integer.valueOf(index));
        CommandHandle.getInstance().executeReqCmd(new ReadDrinkAlarmReq(index), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                AiReminderViewModel.m572readDrinkData$lambda0(deviceSetting, index, this, (ReadAlarmRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readDrinkData$lambda-0, reason: not valid java name */
    public static final void m572readDrinkData$lambda0(DeviceSetting deviceSetting, int i, AiReminderViewModel this$0, ReadAlarmRsp readAlarmRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlarmEntity alarmEntity = readAlarmRsp.getAlarmEntity();
        DrinkBean drinkBean = new DrinkBean("", (alarmEntity.getHour() * 60) + alarmEntity.getMinute(), alarmEntity.isEnable());
        if (alarmEntity.isEnable()) {
            deviceSetting.setDrinkSwitch(true);
        }
        if (alarmEntity.getWeekMask() == -1) {
            deviceSetting.setDrinkWeek(0);
            deviceSetting.setDrinkSwitch(false);
        } else {
            deviceSetting.setDrinkWeek(alarmEntity.getWeekMask());
        }
        if (alarmEntity.getWeekMask() == 0) {
            deviceSetting.setDrinkSwitch(false);
        }
        deviceSetting.getDrinkArray()[i] = drinkBean;
        if (!this$0.drinkIndexMap.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> it = this$0.drinkIndexMap.entrySet().iterator();
            if (it.hasNext()) {
                this$0.readDrinkData(it.next().getKey().intValue(), deviceSetting);
                return;
            }
            return;
        }
        this$0.saveReminder(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), deviceSetting);
        this$0._uiState.postValue(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void readAlarmOnly(final DeviceSetting deviceSetting) throws InterruptedException {
        deviceSetting.getAlarmList().clear();
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            LargeDataHandler.getInstance().readAlarm(new ILargeDataResponse() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel$$ExternalSyntheticLambda2
                @Override // com.oudmon.ble.base.communication.ILargeDataResponse
                public final void parseData(int i, byte[] bArr) {
                    AiReminderViewModel.m571readAlarmOnly$lambda1(this.f$0, deviceSetting, i, bArr);
                }
            });
            return;
        }
        for (final int i = 0; i < 3; i++) {
            CommandHandle.getInstance().executeReqCmd(new ReadAlarmReq(i), new ICommandResponse<ReadAlarmRsp>() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel.readAlarmOnly.2
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public void onDataResponse(ReadAlarmRsp resultEntity) {
                    Intrinsics.checkNotNullParameter(resultEntity, "resultEntity");
                    XLog.i(resultEntity);
                    AlarmEntity alarmEntity = resultEntity.getAlarmEntity();
                    deviceSetting.getAlarmList().add(new AlarmBean(alarmEntity.getAlarmIndex(), alarmEntity.getMinute() + (alarmEntity.getHour() * 60), "", resultEntity.getAlarmEntity().isEnable(), alarmEntity.getWeekMask(), true));
                    if (i == 2) {
                        this.readLongSit(deviceSetting);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readAlarmOnly$lambda-1, reason: not valid java name */
    public static final void m571readAlarmOnly$lambda1(AiReminderViewModel this$0, DeviceSetting deviceSetting, int i, byte[] data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        if (i == 44) {
            if (ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7)) == 1) {
                ArrayList arrayList = new ArrayList();
                AlarmNewEntity alarmNewEntity = new AlarmNewEntity();
                alarmNewEntity.setTotal(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 7, 8)));
                if (alarmNewEntity.getTotal() > 0) {
                    int total = alarmNewEntity.getTotal();
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    this$0.parseAlarm(total, arrayList, data, ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 9)), 8);
                    int i2 = 0;
                    for (AlarmNewEntity.AlarmBean alarmBean : arrayList) {
                        int i3 = i2 + 1;
                        if (alarmBean.getContent() == null) {
                            alarmBean.setContent("");
                        }
                        int min = alarmBean.getMin();
                        String content = alarmBean.getContent();
                        Intrinsics.checkNotNullExpressionValue(content, "item.content");
                        deviceSetting.getAlarmList().add(new AlarmBean(i2, min, content, (alarmBean.getRepeatAndEnable() & 128) == 128, alarmBean.getRepeatAndEnable(), true));
                        i2 = i3;
                    }
                } else {
                    deviceSetting.setAlarmList(new ArrayList());
                }
            }
            this$0.readLongSit(deviceSetting);
        }
    }

    private final void parseAlarm(int total, List<AlarmNewEntity.AlarmBean> list, byte[] data, int alarmLength, int alarmStart) {
        AlarmNewEntity.AlarmBean alarmBean = new AlarmNewEntity.AlarmBean();
        alarmBean.setAlarmLength(alarmLength);
        int i = alarmStart + 2;
        alarmBean.setRepeatAndEnable(ByteUtil.bytesToInt(ArraysKt.copyOfRange(data, alarmStart + 1, i)));
        int i2 = alarmStart + 4;
        alarmBean.setMin(ByteUtil.bytesToInt(ArraysKt.copyOfRange(data, i, i2)));
        if (alarmLength > 4) {
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(data, i2, alarmStart + alarmLength);
            Charset charsetDefaultCharset = Charset.defaultCharset();
            Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "defaultCharset()");
            alarmBean.setContent(new String(bArrCopyOfRange, charsetDefaultCharset));
        }
        list.add(alarmBean);
        if (list.size() < total) {
            int i3 = alarmStart + alarmLength;
            parseAlarm(total, list, data, ByteUtil.bytesToInt(ArraysKt.copyOfRange(data, i3, i3 + 1)), i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void readLongSit(final DeviceSetting deviceSetting) {
        CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq(Constants.CMD_GET_SIT_LONG), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                AiReminderViewModel.m573readLongSit$lambda2(deviceSetting, this, (ReadSitLongRsp) baseRspCmd);
            }
        });
        saveReminder(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), deviceSetting);
        this._uiState.postValue(deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readLongSit$lambda-2, reason: not valid java name */
    public static final void m573readLongSit$lambda2(DeviceSetting deviceSetting, AiReminderViewModel this$0, ReadSitLongRsp readSitLongRsp) {
        Intrinsics.checkNotNullParameter(deviceSetting, "$deviceSetting");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        deviceSetting.setLongSitSwitch(readSitLongRsp.isEnable());
        StartEndTimeEntity startEndTimeEntity = readSitLongRsp.getStartEndTimeEntity();
        deviceSetting.setLongSitStart((startEndTimeEntity.getStartHour() * 60) + startEndTimeEntity.getStartMinute());
        deviceSetting.setLongSitEnd((startEndTimeEntity.getEndHour() * 60) + startEndTimeEntity.getEndMinute());
        deviceSetting.setLongSitDuring(readSitLongRsp.getCycle());
        deviceSetting.setLongSitWeek(readSitLongRsp.getWeekMask());
        Iterator<Map.Entry<Integer, Integer>> it = this$0.drinkIndexMap.entrySet().iterator();
        if (it.hasNext()) {
            this$0.readDrinkData(it.next().getKey().intValue(), deviceSetting);
        }
    }

    public final void saveReminder(final String mac, final DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AiReminderViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel.saveReminder.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiReminderViewModel aiReminderViewModel) {
                invoke2(aiReminderViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiReminderViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
            }
        });
    }

    public final void execAlarm(DeviceSetting setting) throws InterruptedException {
        Intrinsics.checkNotNullParameter(setting, "setting");
        AlarmNewEntity alarmNewEntity = new AlarmNewEntity();
        List<AlarmBean> alarmList = setting.getAlarmList();
        ArrayList arrayList = new ArrayList();
        for (AlarmBean alarmBean : alarmList) {
            if (alarmBean.getDefault()) {
                AlarmNewEntity.AlarmBean alarmBean2 = new AlarmNewEntity.AlarmBean();
                alarmBean2.setMin(alarmBean.getTime());
                alarmBean2.setContent(alarmBean.getTitle());
                String content = alarmBean2.getContent();
                Intrinsics.checkNotNullExpressionValue(content, "entity.content");
                alarmBean2.setAlarmLength(StringsKt.encodeToByteArray(content).length + 4);
                if (alarmBean.getSwitch()) {
                    alarmBean2.setRepeatAndEnable(alarmBean.getWeek() | 128);
                } else {
                    alarmBean2.setRepeatAndEnable(alarmBean.getWeek() & (-129));
                }
                arrayList.add(alarmBean2);
            }
        }
        alarmNewEntity.setTotal(arrayList.size());
        alarmNewEntity.setData(arrayList);
        if (arrayList.size() > 0) {
            LargeDataHandler.getInstance().writeAlarm(alarmNewEntity);
            this._uiState.postValue(setting);
        } else {
            alarmNewEntity.setTotal(0);
            LargeDataHandler.getInstance().writeAlarm(alarmNewEntity);
        }
    }
}
