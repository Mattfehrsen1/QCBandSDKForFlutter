package com.qcwireless.qcwatch.ui.base.repository.healthy;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.BpReadConformReq;
import com.oudmon.ble.base.communication.req.ReadPressureReq;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BpDataRsp;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.BpDownResp;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.util.List;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: BloodPressureRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bJ\b\u0010\u0010\u001a\u0004\u0018\u00010\rJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\b\u0010\u0019\u001a\u00020\u0006H\u0002J\u0016\u0010\u001a\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;", "", "()V", "bloodPressureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodPressureDao;", "downBpFromServer", "", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryBpByDate", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/BloodPressureEntity;", "start", "end", "queryLastBp", "queryLastBpValue", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodPressureChartHomeView$BpDataBean;", "mac", "", "syncAutoBp", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "Lcom/oudmon/ble/base/communication/rsp/ReadBlePressureRsp;", "syncAutoBpConfirm", "syncManualBp", "updateBpDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BloodPressureRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<BloodPressureRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BloodPressureRepository invoke() {
            return new BloodPressureRepository();
        }
    });
    private final QcBloodPressureDao bloodPressureDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcBloodPressureDao();

    public final void syncAutoBp(final BaseDeviceResult<ReadBlePressureRsp> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 13), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                BloodPressureRepository.m285syncAutoBp$lambda0(this.f$0, result, (BpDataRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncAutoBp$lambda-0, reason: not valid java name */
    public static final void m285syncAutoBp$lambda0(BloodPressureRepository this$0, BaseDeviceResult result, BpDataRsp bpDataRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (bpDataRsp.getBpDataEntity() != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new BloodPressureRepository$syncAutoBp$1$1(bpDataRsp, this$0, null), 3, null);
        } else {
            this$0.syncManualBp(result);
            XLog.i("同步手动血压");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncAutoBpConfirm() {
        CommandHandle.getInstance().executeReqCmdNoCallback(new BpReadConformReq(true));
    }

    private final void syncManualBp(final BaseDeviceResult<ReadBlePressureRsp> result) {
        CommandHandle.getInstance().executeReqCmd(new ReadPressureReq(0L), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                BloodPressureRepository.m286syncManualBp$lambda1(this.f$0, result, (ReadBlePressureRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncManualBp$lambda-1, reason: not valid java name */
    public static final void m286syncManualBp$lambda1(BloodPressureRepository this$0, final BaseDeviceResult result, final ReadBlePressureRsp readBlePressureRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        XLog.i("血压数据长度:" + readBlePressureRsp.getValueList().size());
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<BloodPressureRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$syncManualBp$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodPressureRepository bloodPressureRepository) {
                invoke2(bloodPressureRepository);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0063  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2(com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository r21) {
                /*
                    r20 = this;
                    r0 = r20
                    java.lang.String r1 = "$this$ktxRunOnBgSingle"
                    r2 = r21
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                    com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp r1 = r1
                    java.util.List r1 = r1.getValueList()
                    java.util.Iterator r1 = r1.iterator()
                    r3 = 0
                    r4 = 0
                L15:
                    boolean r5 = r1.hasNext()
                    if (r5 == 0) goto L92
                    int r5 = r4 + 1
                    java.lang.Object r6 = r1.next()
                    com.oudmon.ble.base.communication.entity.BlePressure r6 = (com.oudmon.ble.base.communication.entity.BlePressure) r6
                    com.oudmon.ble.base.util.DateUtil r7 = new com.oudmon.ble.base.util.DateUtil
                    long r8 = r6.time
                    r10 = 1
                    r7.<init>(r8, r10)
                    int r15 = r6.sbp
                    com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp r8 = r1
                    java.util.List r8 = r8.getValueList()
                    int r8 = r8.size()
                    int r8 = r8 - r10
                    if (r4 >= r8) goto L63
                    com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp r8 = r1
                    java.util.List r8 = r8.getValueList()
                    java.lang.Object r4 = r8.get(r4)
                    com.oudmon.ble.base.communication.entity.BlePressure r4 = (com.oudmon.ble.base.communication.entity.BlePressure) r4
                    long r8 = r4.time
                    r4 = 60
                    long r11 = (long) r4
                    long r8 = r8 / r11
                    com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp r4 = r1
                    java.util.List r4 = r4.getValueList()
                    java.lang.Object r4 = r4.get(r5)
                    com.oudmon.ble.base.communication.entity.BlePressure r4 = (com.oudmon.ble.base.communication.entity.BlePressure) r4
                    long r13 = r4.time
                    long r13 = r13 / r11
                    int r4 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
                    if (r4 != 0) goto L63
                    r7.setSecond(r10)
                    goto L66
                L63:
                    r7.setSecond(r3)
                L66:
                    com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r4 = new com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r8 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r8 = r8.getInstance()
                    java.lang.String r12 = r8.getDeviceAddressNoClear()
                    long r13 = r7.getUnixTimestamp()
                    int r6 = r6.dbp
                    r17 = 0
                    com.qcwireless.qc_utils.date.DateUtil r7 = new com.qcwireless.qc_utils.date.DateUtil
                    r7.<init>()
                    long r18 = r7.getUnixTimestamp()
                    r11 = r4
                    r16 = r6
                    r11.<init>(r12, r13, r15, r16, r17, r18)
                    com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao r6 = com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository.access$getBloodPressureDao$p(r21)
                    r6.insert(r4)
                    r4 = r5
                    goto L15
                L92:
                    com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp r1 = r1
                    int r1 = r1.getStatus()
                    if (r1 != 0) goto La6
                    com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult<com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp> r1 = r2
                    com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp r2 = r1
                    java.lang.String r4 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                    r1.result(r3, r2)
                La6:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$syncManualBp$1$1.invoke2(com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository):void");
            }
        });
    }

    public final List<BloodPressureEntity> queryBpByDate(long start, long end) {
        return this.bloodPressureDao.queryByUnixTime(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), start, end);
    }

    public final BloodPressureEntity queryLastBp() {
        return this.bloodPressureDao.queryLastBpValue(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartHomeView.BpDataBean> queryLastBpValue(java.lang.String r18) {
        /*
            r17 = this;
            r0 = r17
            r2 = r18
            java.lang.String r1 = "mac"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r7 = r1
            java.util.List r7 = (java.util.List) r7
            com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao r1 = r0.bloodPressureDao
            com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r8 = r1.queryLastBpValue(r2)
            if (r8 == 0) goto La2
            com.oudmon.ble.base.util.DateUtil r1 = new com.oudmon.ble.base.util.DateUtil
            long r3 = r8.getUnixTime()
            r9 = 1
            r1.<init>(r3, r9)
            com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodPressureDao r3 = r0.bloodPressureDao
            long r4 = r1.getZeroTime()
            long r10 = r1.getZeroTime()
            r1 = 86399(0x1517f, float:1.21071E-40)
            long r12 = (long) r1
            long r10 = r10 + r12
            r1 = r3
            r2 = r18
            r3 = r4
            r5 = r10
            java.util.List r1 = r1.queryByUnixTime(r2, r3, r5)
            if (r1 == 0) goto La2
            java.util.Iterator r2 = r1.iterator()
            r3 = 0
        L43:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto La2
            int r4 = r3 + 1
            java.lang.Object r5 = r2.next()
            com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r5 = (com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity) r5
            r6 = 60
            if (r3 <= 0) goto L76
            int r10 = r3 + (-1)
            java.lang.Object r10 = r1.get(r10)
            com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r10 = (com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity) r10
            long r10 = r10.getUnixTime()
            long r12 = (long) r6
            long r10 = r10 / r12
            java.lang.Object r3 = r1.get(r3)
            com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity r3 = (com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity) r3
            long r14 = r3.getUnixTime()
            long r14 = r14 / r12
            int r3 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r3 != 0) goto L76
            com.elvishew.xlog.XLog.i(r5)
            goto La0
        L76:
            com.qcwireless.qc_utils.date.DateUtil r3 = new com.qcwireless.qc_utils.date.DateUtil
            long r10 = r5.getUnixTime()
            r3.<init>(r10, r9)
            int r10 = r3.getHour()
            int r10 = r10 * 60
            int r3 = r3.getMinute()
            int r12 = r10 + r3
            com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartHomeView$BpDataBean r3 = new com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartHomeView$BpDataBean
            int r13 = r5.getSbp()
            int r14 = r5.getDbp()
            long r15 = r8.getUnixTime()
            r11 = r3
            r11.<init>(r12, r13, r14, r15)
            r7.add(r3)
        La0:
            r3 = r4
            goto L43
        La2:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository.queryLastBpValue(java.lang.String):java.util.List");
    }

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$2", f = "BloodPressureRepository.kt", i = {0, 1}, l = {206, 206, AudioEq.SW_EQ_DATA_LENGTH}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03522 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03522(Continuation<? super C03522> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03522 c03522 = BloodPressureRepository.this.new C03522(continuation);
            c03522.L$0 = obj;
            return c03522;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03522) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x010a A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instructions count: 293
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository.C03522.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: BloodPressureRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$2$1", f = "BloodPressureRepository.kt", i = {}, l = {207}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new AnonymousClass1(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
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
                return Unit.INSTANCE;
            }
        }

        /* compiled from: BloodPressureRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$2$2", f = "BloodPressureRepository.kt", i = {}, l = {209}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00552 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00552(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00552> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00552 c00552 = new C00552(this.$$this$flow, continuation);
                c00552.I$0 = i;
                return c00552.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateBpDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03522(null)), new C03533(null)), Dispatchers.getIO()), new C03544(null));
    }

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$3", f = "BloodPressureRepository.kt", i = {}, l = {213}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03533 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03533(Continuation<? super C03533> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03533 c03533 = new C03533(continuation);
            c03533.L$0 = obj;
            return c03533;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03533) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$4", f = "BloodPressureRepository.kt", i = {}, l = {216}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$updateBpDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03544 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03544(Continuation<? super C03544> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03544 c03544 = new C03544(continuation);
            c03544.L$0 = flowCollector;
            c03544.L$1 = th;
            return c03544.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/BpDownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2", f = "BloodPressureRepository.kt", i = {0, 1}, l = {221, 221, 223}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends BpDownResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends BpDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<BpDownResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<BpDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
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
                com.qcwireless.qcwatch.ui.base.bean.request.healthy.BpDownRequest r1 = new com.qcwireless.qcwatch.ui.base.bean.request.healthy.BpDownRequest
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
                java.lang.Object r1 = r6.downBp(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: BloodPressureRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/BpDownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$1", f = "BloodPressureRepository.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends BpDownResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<BpDownResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<BpDownResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends BpDownResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<BpDownResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<BpDownResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: BloodPressureRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$2", f = "BloodPressureRepository.kt", i = {}, l = {224}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00542 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<BpDownResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00542(FlowCollector<? super NetState<List<BpDownResp>>> flowCollector, Continuation<? super C00542> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00542 c00542 = new C00542(this.$$this$flow, continuation);
                c00542.I$0 = i;
                return c00542.invokeSuspend(Unit.INSTANCE);
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

    public final Object downBpFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository.downBpFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<BpDownResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<BpDownResp>> netState, Continuation<? super Unit> continuation2) {
                List<BpDownResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (BpDownResp bpDownResp : listIsSuccess) {
                            String deviceId = bpDownResp.getDeviceId();
                            BloodPressureEntity bloodPressureEntity = deviceId != null ? new BloodPressureEntity(deviceId, bpDownResp.getTime() / 1000, bpDownResp.getSbp(), bpDownResp.getDbp(), false, new DateUtil().getUnixTimestamp()) : null;
                            if (bloodPressureEntity != null) {
                                BloodPressureRepository.this.bloodPressureDao.insert(bloodPressureEntity);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/BpDownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$3", f = "BloodPressureRepository.kt", i = {}, l = {227}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends BpDownResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends BpDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<BpDownResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<BpDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/BpDownResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$4", f = "BloodPressureRepository.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$downBpFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends BpDownResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends BpDownResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<BpDownResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<BpDownResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: BloodPressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BloodPressureRepository getGetInstance() {
            return (BloodPressureRepository) BloodPressureRepository.getInstance$delegate.getValue();
        }
    }
}
