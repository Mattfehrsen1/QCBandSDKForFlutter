package com.qcwireless.qcwatch.ui.base.service;

import android.os.Build;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcSyncDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.SyncDataEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.realsil.sdk.bbpro.core.protocol.Contract;
import java.util.Locale;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: NetService.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\u000b\u001a\u00020\u0006J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\u0011\u001a\u00020\u0006J\u0006\u0010\u0012\u001a\u00020\u0006J\b\u0010\u0013\u001a\u00020\u0006H\u0002J\b\u0010\u0014\u001a\u00020\u0006H\u0002J\u0006\u0010\u0015\u001a\u00020\u0006J\b\u0010\u0016\u001a\u00020\u0006H\u0002J\b\u0010\u0017\u001a\u00020\u0006H\u0002J\b\u0010\u0018\u001a\u00020\u0006H\u0002J\b\u0010\u0019\u001a\u00020\u0006H\u0002J\b\u0010\u001a\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/service/NetService;", "", "()V", "syncDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSyncDao;", "downAll", "", "downBoList", "lastId", "", "downBpList", "downGoalSetting", "downHeartRateDetail", "downSleepDetail", "downSportDetail", "downStepDetail", "downTemperature", "downUserProfile", "upAll", "upBoList", "upBpList", "upCollectionData", "upHeartRateDetail", "upSleepDetail", "upSportDetailDetail", "upStepsDetail", "upTemperatureList", "Companion", "SyncAction", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetService {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<NetService> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<NetService>() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetService invoke() {
            return new NetService();
        }
    });
    private final QcSyncDao syncDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcSyncDao();

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/service/NetService$SyncAction;", "", "()V", SyncAction.BloodOxygen_Action, "", SyncAction.BloodPressure_Action, SyncAction.Heart_Rate_Action, SyncAction.Sleep_Action, SyncAction.Sport_Plus_Action, SyncAction.Step_Action, SyncAction.Temperature_Action, "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SyncAction {
        public static final String BloodOxygen_Action = "BloodOxygen_Action";
        public static final String BloodPressure_Action = "BloodPressure_Action";
        public static final String Heart_Rate_Action = "Heart_Rate_Action";
        public static final SyncAction INSTANCE = new SyncAction();
        public static final String Sleep_Action = "Sleep_Action";
        public static final String Sport_Plus_Action = "Sport_Plus_Action";
        public static final String Step_Action = "Step_Action";
        public static final String Temperature_Action = "Temperature_Action";

        private SyncAction() {
        }
    }

    public final void upAll() {
        XLog.i(Boolean.valueOf(UserConfig.INSTANCE.getInstance().getLoginStatus()));
        if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            ThreadExtKt.ktxRunOnBgFix(this, new Function1<NetService, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upAll.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NetService netService) {
                    invoke2(netService);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NetService ktxRunOnBgFix) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                    ktxRunOnBgFix.upHeartRateDetail();
                    ktxRunOnBgFix.upSleepDetail();
                    ktxRunOnBgFix.upSportDetailDetail();
                    ktxRunOnBgFix.upStepsDetail();
                    ktxRunOnBgFix.upBpList();
                    ktxRunOnBgFix.upBoList();
                    ktxRunOnBgFix.upTemperatureList();
                }
            });
        }
    }

    public final void downAll() {
        if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            ThreadExtKt.ktxRunOnBgFix(this, new Function1<NetService, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.downAll.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NetService netService) {
                    invoke2(netService);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NetService ktxRunOnBgFix) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                    SyncDataEntity syncDataEntityQueryByUidAndAction = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Step_Action);
                    if (syncDataEntityQueryByUidAndAction != null) {
                        ktxRunOnBgFix.downStepDetail(syncDataEntityQueryByUidAndAction.getLastSyncTime());
                    } else {
                        DateUtil dateUtil = new DateUtil();
                        dateUtil.addDay(-30);
                        ktxRunOnBgFix.downStepDetail(dateUtil.getTimestamp());
                        ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Step_Action, dateUtil.getTimestamp()));
                    }
                    SyncDataEntity syncDataEntityQueryByUidAndAction2 = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Sleep_Action);
                    if (syncDataEntityQueryByUidAndAction2 != null) {
                        ktxRunOnBgFix.downSleepDetail(syncDataEntityQueryByUidAndAction2.getLastSyncTime());
                    } else {
                        DateUtil dateUtil2 = new DateUtil();
                        dateUtil2.addDay(-30);
                        ktxRunOnBgFix.downSleepDetail(dateUtil2.getTimestamp());
                        ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Sleep_Action, dateUtil2.getTimestamp()));
                    }
                    SyncDataEntity syncDataEntityQueryByUidAndAction3 = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Heart_Rate_Action);
                    if (syncDataEntityQueryByUidAndAction3 != null) {
                        ktxRunOnBgFix.downSleepDetail(syncDataEntityQueryByUidAndAction3.getLastSyncTime());
                    } else {
                        DateUtil dateUtil3 = new DateUtil();
                        dateUtil3.addDay(-30);
                        ktxRunOnBgFix.downHeartRateDetail(dateUtil3.getTimestamp());
                        ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Heart_Rate_Action, dateUtil3.getTimestamp()));
                    }
                    SyncDataEntity syncDataEntityQueryByUidAndAction4 = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Sport_Plus_Action);
                    if (syncDataEntityQueryByUidAndAction4 != null) {
                        ktxRunOnBgFix.downSportDetail(syncDataEntityQueryByUidAndAction4.getLastSyncTime() / 1000);
                    } else {
                        DateUtil dateUtil4 = new DateUtil();
                        dateUtil4.addDay(-30);
                        ktxRunOnBgFix.downSportDetail(dateUtil4.getTimestamp() / 1000);
                        ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Sport_Plus_Action, dateUtil4.getTimestamp()));
                    }
                    SyncDataEntity syncDataEntityQueryByUidAndAction5 = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.BloodPressure_Action);
                    if (syncDataEntityQueryByUidAndAction5 != null) {
                        ktxRunOnBgFix.downBpList(syncDataEntityQueryByUidAndAction5.getLastSyncTime());
                    } else {
                        DateUtil dateUtil5 = new DateUtil();
                        dateUtil5.addDay(-30);
                        ktxRunOnBgFix.downBpList(dateUtil5.getTimestamp());
                        ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.BloodPressure_Action, dateUtil5.getTimestamp()));
                    }
                    SyncDataEntity syncDataEntityQueryByUidAndAction6 = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.BloodOxygen_Action);
                    if (syncDataEntityQueryByUidAndAction6 != null) {
                        ktxRunOnBgFix.downBoList(syncDataEntityQueryByUidAndAction6.getLastSyncTime());
                    } else {
                        DateUtil dateUtil6 = new DateUtil();
                        dateUtil6.addDay(-30);
                        ktxRunOnBgFix.downBoList(dateUtil6.getTimestamp());
                        ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.BloodOxygen_Action, dateUtil6.getTimestamp()));
                    }
                    SyncDataEntity syncDataEntityQueryByUidAndAction7 = ktxRunOnBgFix.syncDao.queryByUidAndAction(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Temperature_Action);
                    if (syncDataEntityQueryByUidAndAction7 != null) {
                        ktxRunOnBgFix.downTemperature(syncDataEntityQueryByUidAndAction7.getLastSyncTime());
                        return;
                    }
                    DateUtil dateUtil7 = new DateUtil();
                    dateUtil7.addDay(-30);
                    ktxRunOnBgFix.downTemperature(dateUtil7.getTimestamp());
                    ktxRunOnBgFix.syncDao.insert(new SyncDataEntity(UserConfig.INSTANCE.getInstance().getUid(), SyncAction.Temperature_Action, dateUtil7.getTimestamp()));
                }
            });
        }
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downUserProfile$1", f = "NetService.kt", i = {}, l = {CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downUserProfile$1, reason: invalid class name and case insensitive filesystem */
    static final class C04951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04951(Continuation<? super C04951> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04951(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (UserProfileRepository.INSTANCE.getGetInstance().downUserProfileFromServer(UserConfig.INSTANCE.getInstance().getUid(), this) == coroutine_suspended) {
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

    public final void downUserProfile() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04951(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downGoalSetting$1", f = "NetService.kt", i = {}, l = {209}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downGoalSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C04891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04891(Continuation<? super C04891> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04891(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (UserProfileRepository.INSTANCE.getGetInstance().downGoalSettingFromServer(UserConfig.INSTANCE.getInstance().getUid(), this) == coroutine_suspended) {
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

    public final void downGoalSetting() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04891(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upStepsDetail$1", f = "NetService.kt", i = {}, l = {215, 215}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upStepsDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C05031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05031(Continuation<? super C05031> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05031(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = StepDetailRepository.INSTANCE.getGetInstance().updateStepDetailToServer(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upStepsDetail.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upStepsDetail() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05031(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upBpList$1", f = "NetService.kt", i = {}, l = {223, 223}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upBpList$1, reason: invalid class name and case insensitive filesystem */
    static final class C04981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04981(Continuation<? super C04981> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04981(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BloodPressureRepository.INSTANCE.getGetInstance().updateBpDetailToServer(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upBpList.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upBpList() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04981(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upBoList$1", f = "NetService.kt", i = {}, l = {231, 231}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upBoList$1, reason: invalid class name and case insensitive filesystem */
    static final class C04971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04971(Continuation<? super C04971> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04971(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BloodOxygenRepository.INSTANCE.getGetInstance().updateBloodOxygenDetailToServer(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upBoList.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upBoList() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04971(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upTemperatureList$1", f = "NetService.kt", i = {}, l = {240, 240}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upTemperatureList$1, reason: invalid class name and case insensitive filesystem */
    static final class C05041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05041(Continuation<? super C05041> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05041(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = TemperatureRepository.INSTANCE.getGetInstance().updateTemperatureDetailToServer(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upTemperatureList.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upTemperatureList() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05041(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1", f = "NetService.kt", i = {}, l = {250, 250, 252, 252}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C05011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05011(Continuation<? super C05011> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05011(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x005b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x007f A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L29
                if (r1 == r5) goto L25
                if (r1 == r4) goto L21
                if (r1 == r3) goto L1d
                if (r1 != r2) goto L15
                goto L21
            L15:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1d:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L6e
            L21:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L80
            L25:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L4a
            L29:
                kotlin.ResultKt.throwOnFailure(r7)
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r7 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r7 = r7.getInstance()
                boolean r7 = r7.getNewSleepProtocol()
                if (r7 == 0) goto L5c
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$Companion r7 = com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository r7 = r7.getGetInstance()
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r6.label = r5
                java.lang.Object r7 = r7.updateSleepDetailToServerNewProtocol(r1)
                if (r7 != r0) goto L4a
                return r0
            L4a:
                kotlinx.coroutines.flow.Flow r7 = (kotlinx.coroutines.flow.Flow) r7
                com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$1<T> r1 = new kotlinx.coroutines.flow.FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upSleepDetail.1.1
                    static {
                        /*
                            com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$1 r0 = new com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$1
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$1<T>) com.qcwireless.qcwatch.ui.base.service.NetService.upSleepDetail.1.1.INSTANCE com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$1
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.C01021.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.C01021.<init>():void");
                    }

                    public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState<java.lang.Integer> r1, kotlin.coroutines.Continuation<? super kotlin.Unit> r2) {
                        /*
                            r0 = this;
                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.C01021.emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState, kotlin.coroutines.Continuation):java.lang.Object");
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ java.lang.Object emit(java.lang.Object r1, kotlin.coroutines.Continuation r2) {
                        /*
                            r0 = this;
                            com.qcwireless.qcwatch.ui.base.repository.mine.NetState r1 = (com.qcwireless.qcwatch.ui.base.repository.mine.NetState) r1
                            java.lang.Object r1 = r0.emit(r1, r2)
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.C01021.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r6.label = r4
                java.lang.Object r7 = r7.collect(r1, r2)
                if (r7 != r0) goto L80
                return r0
            L5c:
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$Companion r7 = com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository r7 = r7.getGetInstance()
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r6.label = r3
                java.lang.Object r7 = r7.updateSleepDetailToServer(r1)
                if (r7 != r0) goto L6e
                return r0
            L6e:
                kotlinx.coroutines.flow.Flow r7 = (kotlinx.coroutines.flow.Flow) r7
                com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$2<T> r1 = new kotlinx.coroutines.flow.FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upSleepDetail.1.2
                    static {
                        /*
                            com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$2 r0 = new com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$2
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$2<T>) com.qcwireless.qcwatch.ui.base.service.NetService.upSleepDetail.1.2.INSTANCE com.qcwireless.qcwatch.ui.base.service.NetService$upSleepDetail$1$2
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.AnonymousClass2.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.AnonymousClass2.<init>():void");
                    }

                    public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState<java.lang.Integer> r1, kotlin.coroutines.Continuation<? super kotlin.Unit> r2) {
                        /*
                            r0 = this;
                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.AnonymousClass2.emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState, kotlin.coroutines.Continuation):java.lang.Object");
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ java.lang.Object emit(java.lang.Object r1, kotlin.coroutines.Continuation r2) {
                        /*
                            r0 = this;
                            com.qcwireless.qcwatch.ui.base.repository.mine.NetState r1 = (com.qcwireless.qcwatch.ui.base.repository.mine.NetState) r1
                            java.lang.Object r1 = r0.emit(r1, r2)
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                r3 = r6
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r6.label = r2
                java.lang.Object r7 = r7.collect(r1, r3)
                if (r7 != r0) goto L80
                return r0
            L80:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.service.NetService.C05011.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upSleepDetail() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05011(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upHeartRateDetail$1", f = "NetService.kt", i = {}, l = {Contract.CMD_SET_VERSION.V1_3, Contract.CMD_SET_VERSION.V1_3}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upHeartRateDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C05001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05001(Continuation<? super C05001> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05001(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = HeartRateDetailRepository.INSTANCE.getGetInstance().updateHeartRateDetailToServer(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upHeartRateDetail.1.1
                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upHeartRateDetail() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05001(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upSportDetailDetail$1", f = "NetService.kt", i = {}, l = {265, 265}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upSportDetailDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C05021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05021(Continuation<? super C05021> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05021(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = SportPlusRepository.INSTANCE.getGetInstance().updateSportDetailToServer(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upSportDetailDetail.1.1
                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upSportDetailDetail() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05021(null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downStepDetail$1", f = "NetService.kt", i = {}, l = {271}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downStepDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C04931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04931(long j, Continuation<? super C04931> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04931(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (StepDetailRepository.INSTANCE.getGetInstance().downStepDetailFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void downStepDetail(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04931(lastId, null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downBpList$1", f = "NetService.kt", i = {}, l = {280}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downBpList$1, reason: invalid class name and case insensitive filesystem */
    static final class C04881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04881(long j, Continuation<? super C04881> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04881(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BloodPressureRepository.INSTANCE.getGetInstance().downBpFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void downBpList(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04881(lastId, null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downTemperature$1", f = "NetService.kt", i = {}, l = {289}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downTemperature$1, reason: invalid class name and case insensitive filesystem */
    static final class C04941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04941(long j, Continuation<? super C04941> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04941(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (TemperatureRepository.INSTANCE.getGetInstance().downTemperatureFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void downTemperature(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04941(lastId, null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downBoList$1", f = "NetService.kt", i = {}, l = {298}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downBoList$1, reason: invalid class name and case insensitive filesystem */
    static final class C04871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04871(long j, Continuation<? super C04871> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04871(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BloodOxygenRepository.INSTANCE.getGetInstance().downBoFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void downBoList(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04871(lastId, null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downSleepDetail$1", f = "NetService.kt", i = {}, l = {307, 311}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downSleepDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C04911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04911(long j, Continuation<? super C04911> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04911(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SleepDetailRepository.INSTANCE.getGetInstance().downSleepDetailNewProtocolFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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
            if (SleepDetailRepository.INSTANCE.getGetInstance().downSleepDetailFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downSleepDetail(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04911(lastId, null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downHeartRateDetail$1", f = "NetService.kt", i = {}, l = {320}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downHeartRateDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C04901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04901(long j, Continuation<? super C04901> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04901(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (HeartRateDetailRepository.INSTANCE.getGetInstance().downHeartRateDetailFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void downHeartRateDetail(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04901(lastId, null), 3, null);
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$downSportDetail$1", f = "NetService.kt", i = {}, l = {329}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$downSportDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C04921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04921(long j, Continuation<? super C04921> continuation) {
            super(2, continuation);
            this.$lastId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04921(this.$lastId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SportPlusRepository.INSTANCE.getGetInstance().downSportDetailFromServer(UserConfig.INSTANCE.getInstance().getUid(), this.$lastId, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void downSportDetail(long lastId) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C04921(lastId, null), 3, null);
    }

    public final void upCollectionData() {
        ThreadExtKt.ktxRunOnBgSingleNetWork(this, new Function1<NetService, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.service.NetService.upCollectionData.1

            /* compiled from: NetService.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.service.NetService$upCollectionData$1$1", f = "NetService.kt", i = {}, l = {359}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.qcwireless.qcwatch.ui.base.service.NetService$upCollectionData$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                C01001(Continuation<? super C01001> continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01001(continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            String country = Locale.getDefault().getCountry();
                            String brand = Build.BRAND;
                            String model = Build.MODEL;
                            String system = Build.VERSION.RELEASE;
                            String str = GlobalKt.getAppName(QCApplication.INSTANCE.getCONTEXT()) + '-' + GlobalKt.getVersionName(QCApplication.INSTANCE.getCONTEXT());
                            String deviceNameNoClear = UserConfig.INSTANCE.getInstance().getDeviceNameNoClear();
                            String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                            Intrinsics.checkNotNullExpressionValue(country, "country");
                            Intrinsics.checkNotNullExpressionValue(model, "model");
                            Intrinsics.checkNotNullExpressionValue(brand, "brand");
                            Intrinsics.checkNotNullExpressionValue(system, "system");
                            CollectionRequest collectionRequest = new CollectionRequest(deviceNameNoClear, deviceAddressNoClear, country, model, brand, system, str, UserConfig.INSTANCE.getInstance().getHwVersion(), UserConfig.INSTANCE.getInstance().getFmVersion(), UserConfig.INSTANCE.getInstance().getUserEmail(), UserConfig.INSTANCE.getInstance().getUid());
                            this.label = 1;
                            if (QcRetrofitClient.INSTANCE.service().collectionData(collectionRequest, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (UserConfig.INSTANCE.getInstance().getFmVersion().length() > 0) {
                        UserConfig.INSTANCE.getInstance().setLastCollectionTime(new DateUtil().getUnixTimestamp() + 21600);
                        UserConfig.INSTANCE.getInstance().save();
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NetService netService) {
                invoke2(netService);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NetService ktxRunOnBgSingleNetWork) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleNetWork, "$this$ktxRunOnBgSingleNetWork");
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C01001(null), 3, null);
            }
        });
    }

    /* compiled from: NetService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/service/NetService$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/service/NetService;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/service/NetService;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NetService getGetInstance() {
            return (NetService) NetService.getInstance$delegate.getValue();
        }
    }
}
