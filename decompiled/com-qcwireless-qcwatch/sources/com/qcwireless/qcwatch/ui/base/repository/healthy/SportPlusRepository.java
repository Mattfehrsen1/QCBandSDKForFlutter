package com.qcwireless.qcwatch.ui.base.repository.healthy;

import com.blankj.utilcode.constant.CacheConstants;
import com.luck.picture.lib.config.PictureConfig;
import com.oudmon.ble.base.communication.sport.SportLocation;
import com.oudmon.ble.base.communication.sport.SportPlusEntity;
import com.oudmon.ble.base.communication.sport.SportPlusHandle;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.SportDetailResp;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcSportPlusDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.home.sport.bean.HeartDetail;
import com.qcwireless.qcwatch.ui.home.sport.bean.HomeSportDetail;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: SportPlusRepository.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u0011\u001a\u00020\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017J\u001a\u0010\u0019\u001a\u00020\u00062\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140\u001bJ\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001f0\u001eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "", "()V", "sportPlusDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSportPlusDetailDao;", "downSportDetailFromServer", "", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryByTypeAndStartTime", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SportPlusDetail;", "type", "", "startTime", "queryLastSportDate", "queryLastSportPlus", "Lcom/qcwireless/qcwatch/ui/home/sport/bean/HomeSportDetail;", "querySportPlus", "", "Lcom/qcwireless/qcwatch/ui/home/sport/bean/SportDetail;", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "end", "syncTodaySportPlus", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "Lcom/oudmon/ble/base/communication/sport/SportPlusEntity;", "updateSportDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportPlusRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SportPlusRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SportPlusRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SportPlusRepository invoke() {
            return new SportPlusRepository();
        }
    });
    private final QcSportPlusDetailDao sportPlusDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcSportPlusDao();

    public final void syncTodaySportPlus(final BaseDeviceResult<List<SportPlusEntity>> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        final SportPlusHandle sportPlusHandle = new SportPlusHandle();
        sportPlusHandle.init(new SportPlusHandle.IOpResult() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.sport.SportPlusHandle.IOpResult
            public final void onSummary(int i, List list) {
                SportPlusRepository.m300syncTodaySportPlus$lambda0(this.f$0, result, i, list);
            }
        });
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<SportPlusRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository.syncTodaySportPlus.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SportPlusRepository sportPlusRepository) {
                invoke2(sportPlusRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SportPlusRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                SportPlusDetail sportPlusDetailQueryLastSyncDate = ktxRunOnBgSingle.sportPlusDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                if (sportPlusDetailQueryLastSyncDate == null) {
                    sportPlusHandle.cmdSummary(0);
                } else {
                    sportPlusHandle.cmdSummary((int) new DateUtil(sportPlusDetailQueryLastSyncDate.getStartTime(), true).getUnixTimestamp());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodaySportPlus$lambda-0, reason: not valid java name */
    public static final void m300syncTodaySportPlus$lambda0(SportPlusRepository this$0, BaseDeviceResult result, int i, List mutableList) {
        String strSubstring;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (i == 2) {
            StringBuilder sb = new StringBuilder();
            Iterator it = mutableList.iterator();
            while (it.hasNext()) {
                SportPlusEntity sportPlusEntity = (SportPlusEntity) it.next();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                List<SportLocation> list = sportPlusEntity.mLocations;
                StringsKt.clear(sb);
                Iterator<SportLocation> it2 = list.iterator();
                while (it2.hasNext()) {
                    sb.append(it2.next().mRateReal);
                    sb.append(",");
                }
                if (sb.length() > 0) {
                    strSubstring = sb.substring(0, sb.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "rates.substring(0, rates.length - 1)");
                } else {
                    strSubstring = "";
                }
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String str = simpleDateFormat.format(new Date(sportPlusEntity.mStartTime * 1000));
                Intrinsics.checkNotNullExpressionValue(str, "sdf.format(Date(item.mStartTime.toLong()*1000))");
                final SportPlusDetail sportPlusDetail = new SportPlusDetail(deviceAddressNoClear, str, sportPlusEntity.mStartTime, sportPlusEntity.mSportType, sportPlusEntity.mDuration, sportPlusEntity.mDistance, sportPlusEntity.mCalories, sportPlusEntity.steps, strSubstring, sportPlusEntity.mRateAvg, false);
                ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<SportPlusRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$syncTodaySportPlus$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SportPlusRepository sportPlusRepository) {
                        invoke2(sportPlusRepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SportPlusRepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        ktxRunOnBgSingle.sportPlusDao.insert(sportPlusDetail);
                    }
                });
            }
            Intrinsics.checkNotNullExpressionValue(mutableList, "mutableList");
            result.result(0, mutableList);
        }
    }

    public final List<SportDetail> querySportPlus(DateUtil start, DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        ArrayList arrayList = new ArrayList();
        for (SportPlusDetail sportPlusDetail : this.sportPlusDao.querySportPlusByStartTime(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), start.getZeroTime(), (end.getZeroTime() + CacheConstants.DAY) - 1)) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(sportPlusDetail.getRateValue());
            ArrayList arrayList2 = new ArrayList();
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                arrayList2.add(new HeartDetail(sportPlusDetail.getStartTime() + (i * 60), iArrStringToIntArray[i]));
            }
            arrayList.add(new SportDetail(sportPlusDetail.getSportType(), sportPlusDetail.getSteps(), sportPlusDetail.getStartTime(), sportPlusDetail.getDuration(), sportPlusDetail.getCalories(), sportPlusDetail.getDistance(), arrayList2));
        }
        return arrayList;
    }

    public final SportPlusDetail queryLastSportDate() {
        return this.sportPlusDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final SportPlusDetail queryByTypeAndStartTime(int type, long startTime) {
        return this.sportPlusDao.querySportByStartTimeAndType(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), type, startTime);
    }

    public final HomeSportDetail queryLastSportPlus() {
        SportPlusDetail sportPlusDetailQueryByAddressOrderByStartTime = this.sportPlusDao.queryByAddressOrderByStartTime(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        if (sportPlusDetailQueryByAddressOrderByStartTime != null) {
            return new HomeSportDetail(sportPlusDetailQueryByAddressOrderByStartTime.getSportType(), sportPlusDetailQueryByAddressOrderByStartTime.getDistance(), sportPlusDetailQueryByAddressOrderByStartTime.getCalories(), QcDateUtil.INSTANCE.getGetInstance().localDateFormatSport(new DateUtil(sportPlusDetailQueryByAddressOrderByStartTime.getStartTime(), true)));
        }
        return new HomeSportDetail(0, 0.0f, 0.0f, "");
    }

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$2", f = "SportPlusRepository.kt", i = {0, 0, 1}, l = {174, 174, 180}, m = "invokeSuspend", n = {"$this$flow", "notUpList", "$this$flow"}, s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03972 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C03972(Continuation<? super C03972> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03972 c03972 = SportPlusRepository.this.new C03972(continuation);
            c03972.L$0 = obj;
            return c03972;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03972) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0111 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instructions count: 300
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository.C03972.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: SportPlusRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$2$1", f = "SportPlusRepository.kt", i = {}, l = {175}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            final /* synthetic */ List<SportPlusDetail> $notUpList;
            int label;
            final /* synthetic */ SportPlusRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, List<SportPlusDetail> list, SportPlusRepository sportPlusRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$notUpList = list;
                this.this$0 = sportPlusRepository;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new AnonymousClass1(this.$$this$flow, this.$notUpList, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(1), 0, false, 12, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                for (SportPlusDetail sportPlusDetail : this.$notUpList) {
                    sportPlusDetail.setSync(true);
                    this.this$0.sportPlusDao.insert(sportPlusDetail);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: SportPlusRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$2$2", f = "SportPlusRepository.kt", i = {}, l = {181}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00662 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00662(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00662> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00662 c00662 = new C00662(this.$$this$flow, continuation);
                c00662.I$0 = i;
                return c00662.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object updateSportDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03972(null)), new C03983(null)), Dispatchers.getIO()), new C03994(null));
    }

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$3", f = "SportPlusRepository.kt", i = {}, l = {185}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03983 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03983(Continuation<? super C03983> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03983 c03983 = new C03983(continuation);
            c03983.L$0 = obj;
            return c03983;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03983) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$4", f = "SportPlusRepository.kt", i = {}, l = {PictureConfig.CHOOSE_REQUEST}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$updateSportDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03994 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03994(Continuation<? super C03994> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03994 c03994 = new C03994(continuation);
            c03994.L$0 = flowCollector;
            c03994.L$1 = th;
            return c03994.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SportDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2", f = "SportPlusRepository.kt", i = {0, 1}, l = {194, 194, 196}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SportDetailResp>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastSyncId;
        final /* synthetic */ long $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(long j, long j2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$uid = j;
            this.$lastSyncId = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$uid, this.$lastSyncId, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SportDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SportDetailResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SportDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0086 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2f
                if (r1 == r4) goto L27
                if (r1 == r3) goto L1f
                if (r1 != r2) goto L17
                kotlin.ResultKt.throwOnFailure(r14)
                goto L87
            L17:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L1f:
                java.lang.Object r1 = r13.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r14)
                goto L70
            L27:
                java.lang.Object r1 = r13.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r14)
                goto L59
            L2f:
                kotlin.ResultKt.throwOnFailure(r14)
                java.lang.Object r14 = r13.L$0
                kotlinx.coroutines.flow.FlowCollector r14 = (kotlinx.coroutines.flow.FlowCollector) r14
                com.qcwireless.qcwatch.ui.base.bean.request.healthy.HealthyDataDownRequest r1 = new com.qcwireless.qcwatch.ui.base.bean.request.healthy.HealthyDataDownRequest
                long r7 = r13.$uid
                long r9 = r13.$lastSyncId
                r11 = 30
                r6 = r1
                r6.<init>(r7, r9, r11)
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r6 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r6 = r6.service()
                r7 = r13
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r13.L$0 = r14
                r13.label = r4
                java.lang.Object r1 = r6.downSportDetail(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$1
                r4.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r13
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r13.L$0 = r1
                r13.label = r3
                java.lang.Object r14 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r14, r4, r6)
                if (r14 != r0) goto L70
                return r0
            L70:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r13
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r13.L$0 = r5
                r13.label = r2
                java.lang.Object r14 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r14, r3, r1)
                if (r14 != r0) goto L87
                return r0
            L87:
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: SportPlusRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SportDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$1", f = "SportPlusRepository.kt", i = {}, l = {195}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends SportDetailResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<SportDetailResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<SportDetailResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends SportDetailResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<SportDetailResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<SportDetailResp> list, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = list;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    List list = (List) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, list, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: SportPlusRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$2", f = "SportPlusRepository.kt", i = {}, l = {197}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00652 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<SportDetailResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00652(FlowCollector<? super NetState<List<SportDetailResp>>> flowCollector, Continuation<? super C00652> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00652 c00652 = new C00652(this.$$this$flow, continuation);
                c00652.I$0 = i;
                return c00652.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object downSportDetailFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository.downSportDetailFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<SportDetailResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<SportDetailResp>> netState, Continuation<? super Unit> continuation2) {
                List<SportDetailResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (SportDetailResp sportDetailResp : listIsSuccess) {
                            DateUtil dateUtil = new DateUtil(sportDetailResp.getStartTime(), true);
                            if (String.valueOf(sportDetailResp.getStartTime()).length() == 13) {
                                dateUtil = new DateUtil(sportDetailResp.getStartTime(), false);
                            }
                            String deviceId = sportDetailResp.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            String y_m_d = dateUtil.getY_M_D();
                            Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                            long unixTimestamp = dateUtil.getUnixTimestamp();
                            int rawType = sportDetailResp.getRawType();
                            int duration = (int) sportDetailResp.getDuration();
                            float distance = sportDetailResp.getDistance();
                            float calorie = sportDetailResp.getCalorie();
                            int step = sportDetailResp.getStep();
                            List<Integer> heartrates = sportDetailResp.getHeartrates();
                            Intrinsics.checkNotNull(heartrates);
                            try {
                                SportPlusRepository.this.sportPlusDao.insert(new SportPlusDetail(deviceId, y_m_d, unixTimestamp, rawType, duration, distance, calorie, step, StringUtilsKt.intListToString(heartrates), sportDetailResp.getMRateAvg(), true));
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                                return Unit.INSTANCE;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SportDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$3", f = "SportPlusRepository.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SportDetailResp>>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SportDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SportDetailResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SportDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SportDetailResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$4", f = "SportPlusRepository.kt", i = {}, l = {CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository$downSportDetailFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends SportDetailResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SportDetailResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SportDetailResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SportDetailResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = flowCollector;
            anonymousClass4.L$1 = th;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: SportPlusRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SportPlusRepository getGetInstance() {
            return (SportPlusRepository) SportPlusRepository.getInstance$delegate.getValue();
        }
    }
}
