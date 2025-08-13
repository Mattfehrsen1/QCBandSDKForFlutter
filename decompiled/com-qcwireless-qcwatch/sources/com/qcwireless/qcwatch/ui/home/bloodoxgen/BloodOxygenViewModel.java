package com.qcwireless.qcwatch.ui.home.bloodoxgen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.BloodOxygenSettingReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BloodOxygenSettingRsp;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodOxygenEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository;
import com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartView;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.bean.BloodOxyDetailBean;
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

/* compiled from: BloodOxygenViewModel.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0002*+B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bJ\u000e\u0010%\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bJ\u0006\u0010&\u001a\u00020 J\u0016\u0010'\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010(\u001a\u00020\tJ\u0006\u0010)\u001a\u00020 R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00168F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00168F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018¨\u0006,"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "bloodOxygenRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_deviceSetting", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "_lastDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiDetail", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel$BloodOxygenDetail;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel$BloodOxygenUI;", "detailList", "", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/bean/BloodOxyDetailBean;", "getDetailList", "()Ljava/util/List;", "deviceSetting", "Landroidx/lifecycle/LiveData;", "getDeviceSetting", "()Landroidx/lifecycle/LiveData;", "lastDate", "getLastDate", "uiDetail", "getUiDetail", "uiState", "getUiState", "initData", "", "mac", "", "queryBloodOxygenByDate", "date", "queryBloodOxygenByDateDetail", "queryLastData", "saveDeviceSetting", "setting", "syncTodayData", "BloodOxygenDetail", "BloodOxygenUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodOxygenViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _deviceSetting;
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<BloodOxygenDetail> _uiDetail;
    private final MutableLiveData<BloodOxygenUI> _uiState;
    private final BloodOxygenRepository bloodOxygenRepository;
    private final List<BloodOxyDetailBean> detailList;
    private final DeviceSettingRepository deviceSettingRepository;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveDeviceSetting$lambda-1, reason: not valid java name */
    public static final void m647saveDeviceSetting$lambda1(BloodOxygenSettingRsp bloodOxygenSettingRsp) {
    }

    public BloodOxygenViewModel(BloodOxygenRepository bloodOxygenRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(bloodOxygenRepository, "bloodOxygenRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.bloodOxygenRepository = bloodOxygenRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this.detailList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
        this._uiDetail = new MutableLiveData<>();
        this._deviceSetting = new MutableLiveData<>();
    }

    public final List<BloodOxyDetailBean> getDetailList() {
        return this.detailList;
    }

    public final LiveData<BloodOxygenUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final LiveData<BloodOxygenDetail> getUiDetail() {
        return this._uiDetail;
    }

    public final LiveData<DeviceSetting> getDeviceSetting() {
        return this._deviceSetting;
    }

    public final void queryBloodOxygenByDate(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodOxygenViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel.queryBloodOxygenByDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodOxygenViewModel bloodOxygenViewModel) {
                invoke2(bloodOxygenViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BloodOxygenViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<QBloodOxygenLineChartView.DataBean> listQueryBloodOxygenByDate = ktxRunOnBgSingle.bloodOxygenRepository.queryBloodOxygenByDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), date);
                if (date.isToday()) {
                    ktxRunOnBgSingle._uiState.postValue(new BloodOxygenUI(true, listQueryBloodOxygenByDate));
                } else {
                    ktxRunOnBgSingle._uiState.postValue(new BloodOxygenUI(false, listQueryBloodOxygenByDate));
                }
            }
        });
    }

    public final void queryBloodOxygenByDateDetail(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodOxygenViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel.queryBloodOxygenByDateDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodOxygenViewModel bloodOxygenViewModel) {
                invoke2(bloodOxygenViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BloodOxygenViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiDetail.postValue(new BloodOxygenDetail(ktxRunOnBgSingle.bloodOxygenRepository.queryBloodOxygenByDateDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), date)));
            }
        });
    }

    public final void syncTodayData() throws InterruptedException {
        this.bloodOxygenRepository.syncAutoBloodOxygen(0, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$syncTodayData$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadBlePressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                final BloodOxygenViewModel bloodOxygenViewModel = this.this$0;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodOxygenViewModel$syncTodayData$1$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$syncTodayData$1$1$result$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodOxygenViewModel$syncTodayData$1$1 bloodOxygenViewModel$syncTodayData$1$1) {
                        invoke2(bloodOxygenViewModel$syncTodayData$1$1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodOxygenViewModel$syncTodayData$1$1 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        bloodOxygenViewModel.queryBloodOxygenByDateDetail(new DateUtil());
                    }
                });
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    /* compiled from: BloodOxygenViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$queryLastData$1", f = "BloodOxygenViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$queryLastData$1, reason: invalid class name and case insensitive filesystem */
    static final class C05651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05651(Continuation<? super C05651> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BloodOxygenViewModel.this.new C05651(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BloodOxygenEntity bloodOxygenEntityQueryLastBloodOxygenDate = BloodOxygenViewModel.this.bloodOxygenRepository.queryLastBloodOxygenDate();
            if (bloodOxygenEntityQueryLastBloodOxygenDate == null) {
                BloodOxygenViewModel.this._lastDate.postValue(new DateUtil());
            } else {
                BloodOxygenViewModel.this._lastDate.postValue(new DateUtil(bloodOxygenEntityQueryLastBloodOxygenDate.getUnixTime(), true));
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryLastData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05651(null), 3, null);
    }

    public final void saveDeviceSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(BloodOxygenSettingReq.getWriteInstance(setting.getBo2Detection()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                BloodOxygenViewModel.m647saveDeviceSetting$lambda1((BloodOxygenSettingRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    /* compiled from: BloodOxygenViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$initData$1", f = "BloodOxygenViewModel.kt", i = {}, l = {112, 112}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel$initData$1, reason: invalid class name */
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
            return BloodOxygenViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = BloodOxygenViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final BloodOxygenViewModel bloodOxygenViewModel = BloodOxygenViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel.initData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    bloodOxygenViewModel._deviceSetting.postValue(deviceSetting);
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

    /* compiled from: BloodOxygenViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel$BloodOxygenUI;", "", "today", "", "listData", "", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodOxygenLineChartView$DataBean;", "(ZLjava/util/List;)V", "getListData", "()Ljava/util/List;", "getToday", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BloodOxygenUI {
        private final List<QBloodOxygenLineChartView.DataBean> listData;
        private final boolean today;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BloodOxygenUI copy$default(BloodOxygenUI bloodOxygenUI, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = bloodOxygenUI.today;
            }
            if ((i & 2) != 0) {
                list = bloodOxygenUI.listData;
            }
            return bloodOxygenUI.copy(z, list);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getToday() {
            return this.today;
        }

        public final List<QBloodOxygenLineChartView.DataBean> component2() {
            return this.listData;
        }

        public final BloodOxygenUI copy(boolean today, List<? extends QBloodOxygenLineChartView.DataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            return new BloodOxygenUI(today, listData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BloodOxygenUI)) {
                return false;
            }
            BloodOxygenUI bloodOxygenUI = (BloodOxygenUI) other;
            return this.today == bloodOxygenUI.today && Intrinsics.areEqual(this.listData, bloodOxygenUI.listData);
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
            return (r0 * 31) + this.listData.hashCode();
        }

        public String toString() {
            return "BloodOxygenUI(today=" + this.today + ", listData=" + this.listData + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BloodOxygenUI(boolean z, List<? extends QBloodOxygenLineChartView.DataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            this.today = z;
            this.listData = listData;
        }

        public final boolean getToday() {
            return this.today;
        }

        public final List<QBloodOxygenLineChartView.DataBean> getListData() {
            return this.listData;
        }
    }

    /* compiled from: BloodOxygenViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel$BloodOxygenDetail;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/bean/BloodOxyDetailBean;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BloodOxygenDetail {
        private final List<BloodOxyDetailBean> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BloodOxygenDetail copy$default(BloodOxygenDetail bloodOxygenDetail, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = bloodOxygenDetail.data;
            }
            return bloodOxygenDetail.copy(list);
        }

        public final List<BloodOxyDetailBean> component1() {
            return this.data;
        }

        public final BloodOxygenDetail copy(List<BloodOxyDetailBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new BloodOxygenDetail(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BloodOxygenDetail) && Intrinsics.areEqual(this.data, ((BloodOxygenDetail) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "BloodOxygenDetail(data=" + this.data + ')';
        }

        public BloodOxygenDetail(List<BloodOxyDetailBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<BloodOxyDetailBean> getData() {
            return this.data;
        }
    }
}
