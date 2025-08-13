package com.qcwireless.qcwatch.ui.home.bp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.req.BpSettingReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BpSettingRsp;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository;
import com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartView;
import com.qcwireless.qcwatch.ui.home.bp.bean.BpDetailBean;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
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

/* compiled from: BloodPressureViewModel.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0002*+B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bJ\u000e\u0010%\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bJ\u0006\u0010&\u001a\u00020 J\u0016\u0010'\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010(\u001a\u00020\tJ\u0006\u0010)\u001a\u00020 R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00168F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00168F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018¨\u0006,"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "bloodPressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_deviceSetting", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "_lastDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiDetail", "Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel$BloodPressureDetail;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel$BloodPressureUI;", "detailList", "", "Lcom/qcwireless/qcwatch/ui/home/bp/bean/BpDetailBean;", "getDetailList", "()Ljava/util/List;", "deviceSetting", "Landroidx/lifecycle/LiveData;", "getDeviceSetting", "()Landroidx/lifecycle/LiveData;", "lastDate", "getLastDate", "uiDetail", "getUiDetail", "uiState", "getUiState", "initData", "", "mac", "", "queryBloodPressureByDate", "date", "queryBloodPressureByDateDetail", "queryLastData", "saveDeviceSetting", "setting", "syncBp", "BloodPressureDetail", "BloodPressureUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _deviceSetting;
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<BloodPressureDetail> _uiDetail;
    private final MutableLiveData<BloodPressureUI> _uiState;
    private final BloodPressureRepository bloodPressureRepository;
    private final List<BpDetailBean> detailList;
    private final DeviceSettingRepository deviceSettingRepository;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveDeviceSetting$lambda-0, reason: not valid java name */
    public static final void m666saveDeviceSetting$lambda0(BpSettingRsp bpSettingRsp) {
    }

    public BloodPressureViewModel(BloodPressureRepository bloodPressureRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(bloodPressureRepository, "bloodPressureRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.bloodPressureRepository = bloodPressureRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this.detailList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
        this._uiDetail = new MutableLiveData<>();
        this._deviceSetting = new MutableLiveData<>();
    }

    public final List<BpDetailBean> getDetailList() {
        return this.detailList;
    }

    public final LiveData<BloodPressureUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final LiveData<BloodPressureDetail> getUiDetail() {
        return this._uiDetail;
    }

    public final LiveData<DeviceSetting> getDeviceSetting() {
        return this._deviceSetting;
    }

    public final void saveDeviceSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(BpSettingReq.getWriteInstance(setting.getBpSwitch(), new StartEndTimeEntity(0, 0, 23, 59), 60), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                BloodPressureViewModel.m666saveDeviceSetting$lambda0((BpSettingRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$initData$1", f = "BloodPressureViewModel.kt", i = {}, l = {76, 76}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$initData$1, reason: invalid class name */
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
            return BloodPressureViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = BloodPressureViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final BloodPressureViewModel bloodPressureViewModel = BloodPressureViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.initData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    bloodPressureViewModel._deviceSetting.postValue(deviceSetting);
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

    public final void queryBloodPressureByDate(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodPressureViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.queryBloodPressureByDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodPressureViewModel bloodPressureViewModel) {
                invoke2(bloodPressureViewModel);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x0065  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2(com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel r18) {
                /*
                    r17 = this;
                    r0 = r17
                    java.lang.String r1 = "$this$ktxRunOnBgSingle"
                    r2 = r18
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>()
                    java.util.List r1 = (java.util.List) r1
                    com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository r3 = com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.access$getBloodPressureRepository$p(r18)
                    com.qcwireless.qc_utils.date.DateUtil r4 = r1
                    long r4 = r4.getZeroTime()
                    com.qcwireless.qc_utils.date.DateUtil r6 = r1
                    long r6 = r6.getZeroTime()
                    r8 = 86399(0x1517f, float:1.21071E-40)
                    long r8 = (long) r8
                    long r6 = r6 + r8
                    java.util.List r3 = r3.queryBpByDate(r4, r6)
                    r4 = 0
                    r5 = 1
                    if (r3 == 0) goto L8b
                    java.util.Iterator r6 = r3.iterator()
                    r7 = 0
                L32:
                    boolean r8 = r6.hasNext()
                    if (r8 == 0) goto L8b
                    int r8 = r7 + 1
                    java.lang.Object r9 = r6.next()
                    com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r9 = (com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity) r9
                    r10 = 60
                    if (r7 <= 0) goto L65
                    int r11 = r7 + (-1)
                    java.lang.Object r11 = r3.get(r11)
                    com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r11 = (com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity) r11
                    long r11 = r11.getUnixTime()
                    long r13 = (long) r10
                    long r11 = r11 / r13
                    java.lang.Object r7 = r3.get(r7)
                    com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r7 = (com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity) r7
                    long r15 = r7.getUnixTime()
                    long r15 = r15 / r13
                    int r7 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
                    if (r7 != 0) goto L65
                    com.elvishew.xlog.XLog.i(r9)
                    goto L89
                L65:
                    com.qcwireless.qc_utils.date.DateUtil r7 = new com.qcwireless.qc_utils.date.DateUtil
                    long r11 = r9.getUnixTime()
                    r7.<init>(r11, r5)
                    int r11 = r7.getHour()
                    int r11 = r11 * 60
                    int r7 = r7.getMinute()
                    int r11 = r11 + r7
                    com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartView$BpDataBean r7 = new com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartView$BpDataBean
                    int r10 = r9.getSbp()
                    int r9 = r9.getDbp()
                    r7.<init>(r11, r10, r9)
                    r1.add(r7)
                L89:
                    r7 = r8
                    goto L32
                L8b:
                    com.qcwireless.qc_utils.date.DateUtil r3 = r1
                    boolean r3 = r3.isToday()
                    if (r3 == 0) goto La0
                    androidx.lifecycle.MutableLiveData r2 = com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.access$get_uiState$p(r18)
                    com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$BloodPressureUI r3 = new com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$BloodPressureUI
                    r3.<init>(r5, r1)
                    r2.postValue(r3)
                    goto Lac
                La0:
                    androidx.lifecycle.MutableLiveData r2 = com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.access$get_uiState$p(r18)
                    com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$BloodPressureUI r3 = new com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$BloodPressureUI
                    r3.<init>(r4, r1)
                    r2.postValue(r3)
                Lac:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.C05691.invoke2(com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel):void");
            }
        });
    }

    public final void queryBloodPressureByDateDetail(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodPressureViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel.queryBloodPressureByDateDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodPressureViewModel bloodPressureViewModel) {
                invoke2(bloodPressureViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BloodPressureViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList arrayList = new ArrayList();
                List<BloodPressureEntity> listQueryBpByDate = ktxRunOnBgSingle.bloodPressureRepository.queryBpByDate(date.getZeroTime(), date.getZeroTime() + 86399);
                if (listQueryBpByDate != null) {
                    for (BloodPressureEntity bloodPressureEntity : listQueryBpByDate) {
                        DateUtil dateUtil = new DateUtil(bloodPressureEntity.getUnixTime(), true);
                        arrayList.add(new BpDetailBean((dateUtil.getHour() * 60) + dateUtil.getMinute(), bloodPressureEntity.getSbp(), bloodPressureEntity.getDbp()));
                    }
                }
                if (arrayList.size() > 1) {
                    CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$queryBloodPressureByDateDetail$1$invoke$$inlined$sortByDescending$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(((BpDetailBean) t2).getMin()), Integer.valueOf(((BpDetailBean) t).getMin()));
                        }
                    });
                }
                ktxRunOnBgSingle._uiDetail.postValue(new BloodPressureDetail(arrayList));
            }
        });
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$syncBp$1", f = "BloodPressureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$syncBp$1, reason: invalid class name and case insensitive filesystem */
    static final class C05721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05721(Continuation<? super C05721> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BloodPressureViewModel.this.new C05721(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BloodPressureRepository bloodPressureRepository = BloodPressureViewModel.this.bloodPressureRepository;
            final BloodPressureViewModel bloodPressureViewModel = BloodPressureViewModel.this;
            bloodPressureRepository.syncAutoBp(new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$syncBp$1$1$1
                @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                public void result(int errorCode, ReadBlePressureRsp t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                    final BloodPressureViewModel bloodPressureViewModel2 = bloodPressureViewModel;
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodPressureViewModel$syncBp$1$1$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$syncBp$1$1$1$result$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(BloodPressureViewModel$syncBp$1$1$1 bloodPressureViewModel$syncBp$1$1$1) {
                            invoke2(bloodPressureViewModel$syncBp$1$1$1);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(BloodPressureViewModel$syncBp$1$1$1 ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            bloodPressureViewModel2.queryBloodPressureByDateDetail(new DateUtil());
                        }
                    });
                    EventBus.getDefault().post(new ManualRefreshEvent());
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void syncBp() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05721(null), 3, null);
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$queryLastData$1", f = "BloodPressureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel$queryLastData$1, reason: invalid class name and case insensitive filesystem */
    static final class C05711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05711(Continuation<? super C05711> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BloodPressureViewModel.this.new C05711(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BloodPressureEntity bloodPressureEntityQueryLastBp = BloodPressureViewModel.this.bloodPressureRepository.queryLastBp();
            if (bloodPressureEntityQueryLastBp == null) {
                BloodPressureViewModel.this._lastDate.postValue(new DateUtil());
            } else {
                BloodPressureViewModel.this._lastDate.postValue(new DateUtil(bloodPressureEntityQueryLastBp.getUnixTime(), true));
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryLastData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05711(null), 3, null);
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel$BloodPressureUI;", "", "today", "", "listData", "", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodPressureChartView$BpDataBean;", "(ZLjava/util/List;)V", "getListData", "()Ljava/util/List;", "getToday", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BloodPressureUI {
        private final List<QBloodPressureChartView.BpDataBean> listData;
        private final boolean today;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BloodPressureUI copy$default(BloodPressureUI bloodPressureUI, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = bloodPressureUI.today;
            }
            if ((i & 2) != 0) {
                list = bloodPressureUI.listData;
            }
            return bloodPressureUI.copy(z, list);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getToday() {
            return this.today;
        }

        public final List<QBloodPressureChartView.BpDataBean> component2() {
            return this.listData;
        }

        public final BloodPressureUI copy(boolean today, List<? extends QBloodPressureChartView.BpDataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            return new BloodPressureUI(today, listData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BloodPressureUI)) {
                return false;
            }
            BloodPressureUI bloodPressureUI = (BloodPressureUI) other;
            return this.today == bloodPressureUI.today && Intrinsics.areEqual(this.listData, bloodPressureUI.listData);
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
            return "BloodPressureUI(today=" + this.today + ", listData=" + this.listData + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BloodPressureUI(boolean z, List<? extends QBloodPressureChartView.BpDataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            this.today = z;
            this.listData = listData;
        }

        public final boolean getToday() {
            return this.today;
        }

        public final List<QBloodPressureChartView.BpDataBean> getListData() {
            return this.listData;
        }
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel$BloodPressureDetail;", "", "listData", "", "Lcom/qcwireless/qcwatch/ui/home/bp/bean/BpDetailBean;", "(Ljava/util/List;)V", "getListData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BloodPressureDetail {
        private final List<BpDetailBean> listData;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BloodPressureDetail copy$default(BloodPressureDetail bloodPressureDetail, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = bloodPressureDetail.listData;
            }
            return bloodPressureDetail.copy(list);
        }

        public final List<BpDetailBean> component1() {
            return this.listData;
        }

        public final BloodPressureDetail copy(List<BpDetailBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            return new BloodPressureDetail(listData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BloodPressureDetail) && Intrinsics.areEqual(this.listData, ((BloodPressureDetail) other).listData);
        }

        public int hashCode() {
            return this.listData.hashCode();
        }

        public String toString() {
            return "BloodPressureDetail(listData=" + this.listData + ')';
        }

        public BloodPressureDetail(List<BpDetailBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            this.listData = listData;
        }

        public final List<BpDetailBean> getListData() {
            return this.listData;
        }
    }
}
