package com.qcwireless.qcwatch.ui.home.heart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.bigData.bean.ManualHeartRate;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse;
import com.oudmon.ble.base.communication.req.HeartRateSettingReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.HeartRateSettingRsp;
import com.oudmon.ble.base.communication.rsp.ReadHeartRateRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceFunctionList;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView;
import com.qcwireless.qcwatch.ui.home.heart.bean.HeartRateDetailBean;
import com.squareup.moshi.JsonAdapter;
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

/* compiled from: HeartActivityViewModel.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0003012B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0016\u001a\u00020%J\u000e\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020\u000fJ\u0006\u0010+\u001a\u00020%J\u0016\u0010,\u001a\u00020%2\u0006\u0010'\u001a\u00020(2\u0006\u0010-\u001a\u00020\rJ\u000e\u0010.\u001a\u00020%2\u0006\u0010*\u001a\u00020\u000fJ\u0006\u0010/\u001a\u00020%R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u00158F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00158F¢\u0006\u0006\u001a\u0004\b \u0010\u0017R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u00158F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0017R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00130\u00158F¢\u0006\u0006\u001a\u0004\b$\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "heartRateDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_age", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$UserAge;", "_deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "_lastDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiDetail", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$HeartRateDetail;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$HeartDetailUI;", "age", "Landroidx/lifecycle/LiveData;", "getAge", "()Landroidx/lifecycle/LiveData;", "detailList", "", "Lcom/qcwireless/qcwatch/ui/home/heart/bean/HeartRateDetailBean;", "getDetailList", "()Ljava/util/List;", "deviceSetting", "getDeviceSetting", "lastDate", "getLastDate", "uiDetail", "getUiDetail", "uiState", "getUiState", "", "initData", "mac", "", "queryHeartRateByDateDetail", "date", "queryLastData", "saveDeviceSetting", "setting", "showHeartRateDetail", "syncTodayData", "HeartDetailUI", "HeartRateDetail", "UserAge", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartActivityViewModel extends BaseViewModel {
    private final MutableLiveData<UserAge> _age;
    private final MutableLiveData<DeviceSetting> _deviceSetting;
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<HeartRateDetail> _uiDetail;
    private final MutableLiveData<HeartDetailUI> _uiState;
    private final List<HeartRateDetailBean> detailList;
    private DeviceSettingRepository deviceSettingRepository;
    private final HeartRateDetailRepository heartRateDetailRepository;
    private final UserProfileRepository userProfileRepository;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveDeviceSetting$lambda-0, reason: not valid java name */
    public static final void m759saveDeviceSetting$lambda0(HeartRateSettingRsp heartRateSettingRsp) {
    }

    public HeartActivityViewModel(HeartRateDetailRepository heartRateDetailRepository, UserProfileRepository userProfileRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(heartRateDetailRepository, "heartRateDetailRepository");
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.heartRateDetailRepository = heartRateDetailRepository;
        this.userProfileRepository = userProfileRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this.detailList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._age = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
        this._uiDetail = new MutableLiveData<>();
        this._deviceSetting = new MutableLiveData<>();
    }

    public final List<HeartRateDetailBean> getDetailList() {
        return this.detailList;
    }

    public final LiveData<HeartDetailUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<UserAge> getAge() {
        return this._age;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final LiveData<HeartRateDetail> getUiDetail() {
        return this._uiDetail;
    }

    public final LiveData<DeviceSetting> getDeviceSetting() {
        return this._deviceSetting;
    }

    public final void showHeartRateDetail(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel.showHeartRateDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartActivityViewModel heartActivityViewModel) {
                invoke2(heartActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<QHeartLineChartView.HeartDataBean> listQueryHeartDetail = ktxRunOnBgSingle.heartRateDetailRepository.queryHeartDetail(date);
                if (date.isToday()) {
                    ktxRunOnBgSingle._uiState.postValue(new HeartDetailUI(true, listQueryHeartDetail));
                } else {
                    ktxRunOnBgSingle._uiState.postValue(new HeartDetailUI(false, listQueryHeartDetail));
                }
            }
        });
    }

    /* compiled from: HeartActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$getAge$1", f = "HeartActivityViewModel.kt", i = {}, l = {78}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$getAge$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HeartActivityViewModel.this.new AnonymousClass1(continuation);
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
                Flow<Integer> userAge = HeartActivityViewModel.this.userProfileRepository.getUserAge();
                final HeartActivityViewModel heartActivityViewModel = HeartActivityViewModel.this;
                this.label = 1;
                if (userAge.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel.getAge.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).intValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(int i2, Continuation<? super Unit> continuation) {
                        heartActivityViewModel._age.postValue(new UserAge(i2));
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* renamed from: getAge, reason: collision with other method in class */
    public final void m761getAge() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: HeartActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$queryLastData$1", f = "HeartActivityViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$queryLastData$1, reason: invalid class name and case insensitive filesystem */
    static final class C06211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06211(Continuation<? super C06211> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HeartActivityViewModel.this.new C06211(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<QHeartLineChartView.HeartDataBean> listQueryLastHeartDetail = HeartActivityViewModel.this.heartRateDetailRepository.queryLastHeartDetail();
            if (!listQueryLastHeartDetail.isEmpty()) {
                HeartActivityViewModel.this._lastDate.postValue(new DateUtil(listQueryLastHeartDetail.get(0).getUnixTime(), true));
            } else {
                HeartActivityViewModel.this._lastDate.postValue(new DateUtil());
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryLastData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06211(null), 3, null);
    }

    public final void queryHeartRateByDateDetail(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel.queryHeartRateByDateDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartActivityViewModel heartActivityViewModel) {
                invoke2(heartActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiDetail.postValue(new HeartRateDetail(ktxRunOnBgSingle.heartRateDetailRepository.queryHeartRateByDateDetailResp(date)));
            }
        });
    }

    public final void saveDeviceSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(HeartRateSettingReq.getWriteInstance(setting.getHrDetection()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                HeartActivityViewModel.m759saveDeviceSetting$lambda0((HeartRateSettingRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    /* compiled from: HeartActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$initData$1", f = "HeartActivityViewModel.kt", i = {}, l = {118, 118}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$initData$1, reason: invalid class name and case insensitive filesystem */
    static final class C06191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06191(String str, Continuation<? super C06191> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HeartActivityViewModel.this.new C06191(this.$mac, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = HeartActivityViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final HeartActivityViewModel heartActivityViewModel = HeartActivityViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel.initData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    heartActivityViewModel._deviceSetting.postValue(deviceSetting);
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
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06191(mac, null), 3, null);
    }

    public final void syncTodayData() throws InterruptedException {
        final HeartRateDetailRepository heartRateDetailRepository = this.heartRateDetailRepository;
        String deviceFunctionList = UserConfig.INSTANCE.getInstance().getDeviceFunctionList();
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<DeviceFunctionList>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$lambda-2$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        DeviceFunctionList deviceFunctionList2 = (DeviceFunctionList) jsonAdapterAdapter.fromJson(deviceFunctionList);
        if (deviceFunctionList2 != null) {
            if (deviceFunctionList2.getManualHeart()) {
                heartRateDetailRepository.syncManualHeartRate(0, new ILargeDataManualHeartRateResponse() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$$ExternalSyntheticLambda1
                    @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse
                    public final void manualHeart(ManualHeartRate manualHeartRate) {
                        HeartActivityViewModel.m760syncTodayData$lambda2$lambda1(heartRateDetailRepository, this, manualHeartRate);
                    }
                });
                return;
            } else {
                heartRateDetailRepository.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$1$2
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadHeartRateRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        final HeartActivityViewModel heartActivityViewModel = this.this$0;
                        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartActivityViewModel$syncTodayData$1$2, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$1$2$result$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(HeartActivityViewModel$syncTodayData$1$2 heartActivityViewModel$syncTodayData$1$2) {
                                invoke2(heartActivityViewModel$syncTodayData$1$2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(HeartActivityViewModel$syncTodayData$1$2 ktxRunOnBgSingle) {
                                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                heartActivityViewModel.queryHeartRateByDateDetail(new DateUtil());
                                EventBus.getDefault().post(new ManualRefreshEvent());
                            }
                        });
                    }
                });
                return;
            }
        }
        heartRateDetailRepository.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$1$3
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadHeartRateRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                final HeartActivityViewModel heartActivityViewModel = this.this$0;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartActivityViewModel$syncTodayData$1$3, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$1$3$result$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HeartActivityViewModel$syncTodayData$1$3 heartActivityViewModel$syncTodayData$1$3) {
                        invoke2(heartActivityViewModel$syncTodayData$1$3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HeartActivityViewModel$syncTodayData$1$3 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        heartActivityViewModel.queryHeartRateByDateDetail(new DateUtil());
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayData$lambda-2$lambda-1, reason: not valid java name */
    public static final void m760syncTodayData$lambda2$lambda1(HeartRateDetailRepository this_run, final HeartActivityViewModel this$0, ManualHeartRate manualHeartRate) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_run.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$1$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadHeartRateRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                final HeartActivityViewModel heartActivityViewModel = this.this$0;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartActivityViewModel$syncTodayData$1$1$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel$syncTodayData$1$1$1$result$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HeartActivityViewModel$syncTodayData$1$1$1 heartActivityViewModel$syncTodayData$1$1$1) {
                        invoke2(heartActivityViewModel$syncTodayData$1$1$1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HeartActivityViewModel$syncTodayData$1$1$1 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        heartActivityViewModel.queryHeartRateByDateDetail(new DateUtil());
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        });
    }

    /* compiled from: HeartActivityViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$HeartDetailUI;", "", "today", "", "listData", "", "Lcom/qcwireless/qcwatch/ui/base/view/QHeartLineChartView$HeartDataBean;", "(ZLjava/util/List;)V", "getListData", "()Ljava/util/List;", "getToday", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class HeartDetailUI {
        private final List<QHeartLineChartView.HeartDataBean> listData;
        private final boolean today;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HeartDetailUI copy$default(HeartDetailUI heartDetailUI, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = heartDetailUI.today;
            }
            if ((i & 2) != 0) {
                list = heartDetailUI.listData;
            }
            return heartDetailUI.copy(z, list);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getToday() {
            return this.today;
        }

        public final List<QHeartLineChartView.HeartDataBean> component2() {
            return this.listData;
        }

        public final HeartDetailUI copy(boolean today, List<? extends QHeartLineChartView.HeartDataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            return new HeartDetailUI(today, listData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HeartDetailUI)) {
                return false;
            }
            HeartDetailUI heartDetailUI = (HeartDetailUI) other;
            return this.today == heartDetailUI.today && Intrinsics.areEqual(this.listData, heartDetailUI.listData);
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
            return "HeartDetailUI(today=" + this.today + ", listData=" + this.listData + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public HeartDetailUI(boolean z, List<? extends QHeartLineChartView.HeartDataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            this.today = z;
            this.listData = listData;
        }

        public final boolean getToday() {
            return this.today;
        }

        public final List<QHeartLineChartView.HeartDataBean> getListData() {
            return this.listData;
        }
    }

    /* compiled from: HeartActivityViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$UserAge;", "", "age", "", "(I)V", "getAge", "()I", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class UserAge {
        private final int age;

        public static /* synthetic */ UserAge copy$default(UserAge userAge, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = userAge.age;
            }
            return userAge.copy(i);
        }

        /* renamed from: component1, reason: from getter */
        public final int getAge() {
            return this.age;
        }

        public final UserAge copy(int age) {
            return new UserAge(age);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UserAge) && this.age == ((UserAge) other).age;
        }

        public int hashCode() {
            return this.age;
        }

        public String toString() {
            return "UserAge(age=" + this.age + ')';
        }

        public UserAge(int i) {
            this.age = i;
        }

        public final int getAge() {
            return this.age;
        }
    }

    /* compiled from: HeartActivityViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$HeartRateDetail;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/home/heart/bean/HeartRateDetailBean;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class HeartRateDetail {
        private final List<HeartRateDetailBean> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HeartRateDetail copy$default(HeartRateDetail heartRateDetail, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = heartRateDetail.data;
            }
            return heartRateDetail.copy(list);
        }

        public final List<HeartRateDetailBean> component1() {
            return this.data;
        }

        public final HeartRateDetail copy(List<HeartRateDetailBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new HeartRateDetail(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof HeartRateDetail) && Intrinsics.areEqual(this.data, ((HeartRateDetail) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "HeartRateDetail(data=" + this.data + ')';
        }

        public HeartRateDetail(List<HeartRateDetailBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<HeartRateDetailBean> getData() {
            return this.data;
        }
    }
}
