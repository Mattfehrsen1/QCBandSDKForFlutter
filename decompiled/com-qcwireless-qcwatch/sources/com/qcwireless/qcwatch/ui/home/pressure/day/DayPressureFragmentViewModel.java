package com.qcwireless.qcwatch.ui.home.pressure.day;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.PressureSettingReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.PressureRsp;
import com.oudmon.ble.base.communication.rsp.PressureSettingRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.view.QPressureBarChart;
import com.qcwireless.qcwatch.ui.home.pressure.bean.PressureDetailBean;
import java.util.ArrayList;
import java.util.List;
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
import org.greenrobot.eventbus.EventBus;

/* compiled from: DayPressureFragmentViewModel.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001aJ\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\tJ\u0016\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u0007J\u0006\u0010\"\u001a\u00020\u001aR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000f¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "pressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;)V", "_deviceSetting", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "_lastDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel$DayPressureUI;", "deviceSetting", "Landroidx/lifecycle/LiveData;", "getDeviceSetting", "()Landroidx/lifecycle/LiveData;", "lastDate", "getLastDate", "manualList", "", "Lcom/qcwireless/qcwatch/ui/home/pressure/bean/PressureDetailBean;", "getManualList", "()Ljava/util/List;", "uiState", "getUiState", "initData", "", "mac", "", "queryLastData", "queryPressureByDate", "date", "saveDeviceSetting", "setting", "syncTodayData", "DayPressureUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DayPressureFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _deviceSetting;
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<DayPressureUI> _uiState;
    private final List<PressureDetailBean> manualList;
    private final PressureRepository pressureRepository;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveDeviceSetting$lambda-0, reason: not valid java name */
    public static final void m812saveDeviceSetting$lambda0(PressureSettingRsp pressureSettingRsp) {
    }

    public DayPressureFragmentViewModel(PressureRepository pressureRepository) {
        Intrinsics.checkNotNullParameter(pressureRepository, "pressureRepository");
        this.pressureRepository = pressureRepository;
        this._uiState = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
        this.manualList = new ArrayList();
        this._deviceSetting = new MutableLiveData<>();
    }

    public final LiveData<DayPressureUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final List<PressureDetailBean> getManualList() {
        return this.manualList;
    }

    public final LiveData<DeviceSetting> getDeviceSetting() {
        return this._deviceSetting;
    }

    public final void syncTodayData() {
        this.pressureRepository.syncTodayPressure(new BaseDeviceResult<PressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel.syncTodayData.1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, PressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                DayPressureFragmentViewModel.this.queryPressureByDate(new DateUtil());
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    public final void queryLastData() {
        this._lastDate.postValue(new DateUtil());
    }

    public final void queryPressureByDate(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgFix(this, new Function1<DayPressureFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel.queryPressureByDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DayPressureFragmentViewModel dayPressureFragmentViewModel) {
                invoke2(dayPressureFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DayPressureFragmentViewModel ktxRunOnBgFix) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                List<QPressureBarChart.PressureDataBean> listQueryPressureByDateDetailResp = ktxRunOnBgFix.pressureRepository.queryPressureByDateDetailResp(date);
                if (date.isToday()) {
                    ktxRunOnBgFix._uiState.postValue(new DayPressureUI(true, listQueryPressureByDateDetailResp));
                } else {
                    ktxRunOnBgFix._uiState.postValue(new DayPressureUI(false, listQueryPressureByDateDetailResp));
                }
            }
        });
    }

    /* compiled from: DayPressureFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel$initData$1", f = "DayPressureFragmentViewModel.kt", i = {}, l = {102, 102}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel$initData$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        int label;
        final /* synthetic */ DayPressureFragmentViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, DayPressureFragmentViewModel dayPressureFragmentViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mac = str;
            this.this$0 = dayPressureFragmentViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$mac, this.this$0, continuation);
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
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(this.$mac, this);
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
            final DayPressureFragmentViewModel dayPressureFragmentViewModel = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel.initData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    dayPressureFragmentViewModel._deviceSetting.postValue(deviceSetting);
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
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(mac, this, null), 3, null);
    }

    public final void saveDeviceSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(PressureSettingReq.getWriteInstance(setting.getPressureDetection()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DayPressureFragmentViewModel.m812saveDeviceSetting$lambda0((PressureSettingRsp) baseRspCmd);
            }
        });
        DeviceSettingRepository.INSTANCE.getGetInstance().saveDeviceSetting(new DeviceSettingEntity(mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    /* compiled from: DayPressureFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel$DayPressureUI;", "", "today", "", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureBarChart$PressureDataBean;", "(ZLjava/util/List;)V", "getData", "()Ljava/util/List;", "getToday", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class DayPressureUI {
        private final List<QPressureBarChart.PressureDataBean> data;
        private final boolean today;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DayPressureUI copy$default(DayPressureUI dayPressureUI, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = dayPressureUI.today;
            }
            if ((i & 2) != 0) {
                list = dayPressureUI.data;
            }
            return dayPressureUI.copy(z, list);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getToday() {
            return this.today;
        }

        public final List<QPressureBarChart.PressureDataBean> component2() {
            return this.data;
        }

        public final DayPressureUI copy(boolean today, List<? extends QPressureBarChart.PressureDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new DayPressureUI(today, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DayPressureUI)) {
                return false;
            }
            DayPressureUI dayPressureUI = (DayPressureUI) other;
            return this.today == dayPressureUI.today && Intrinsics.areEqual(this.data, dayPressureUI.data);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.today;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.data.hashCode();
        }

        public String toString() {
            return "DayPressureUI(today=" + this.today + ", data=" + this.data + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DayPressureUI(boolean z, List<? extends QPressureBarChart.PressureDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.today = z;
            this.data = data;
        }

        public final boolean getToday() {
            return this.today;
        }

        public final List<QPressureBarChart.PressureDataBean> getData() {
            return this.data;
        }
    }
}
