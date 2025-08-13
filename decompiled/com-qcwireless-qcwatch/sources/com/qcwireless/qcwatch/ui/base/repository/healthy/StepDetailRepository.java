package com.qcwireless.qcwatch.ui.base.repository.healthy;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.blankj.utilcode.constant.CacheConstants;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.BleStepDetails;
import com.oudmon.ble.base.communication.req.ReadDetailSportDataReq;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.ReadDetailSportDataRsp;
import com.oudmon.ble.base.communication.rsp.TodaySportDataRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.StepDetailResp;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcStepDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcStepTotalDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import com.realsil.sdk.core.bluetooth.connection.le.GattClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

/* compiled from: StepDetailRepository.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 >2\u00020\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000eJ!\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000eH\u0002J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020\u0005J\u0010\u0010!\u001a\u0004\u0018\u00010\u000e2\u0006\u0010 \u001a\u00020\u0005J\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020\u0005J\u0010\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010 \u001a\u00020\u0005J\u000e\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020$J&\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u00062\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0+H\u0002J/\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0+H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010/J\u0014\u00100\u001a\u00020\f2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002010+J\u001c\u00102\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u00062\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0+J@\u00103\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u0002072\u000e\u00108\u001a\n\u0012\u0004\u0012\u000209\u0018\u00010\u001eH\u0002J\u001d\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060<0;H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;", "", "()V", "historyDate", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "stepDetailDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcStepDetailDao;", "stepTotalDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcStepTotalDao;", "calcStepTotal", "", "stepDetail", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepDetail;", "deleteStepDetailError", "detail", "downStepDetailFromServer", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "merge", "oldEntity", "newEntity", "parseStepDetail", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/ReadDetailSportDataRsp;", "parseTodayStepDetail", "queryStepDetail", "", "Lcom/qcwireless/qcwatch/ui/base/view/QStepBarChart$StepDataBean;", "date", "queryStepDetailError", "queryStepDetailFifteenMinutes", "queryStepTotal", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepTotal;", "saveTotalDate", "stepTotal", "syncDeviceStepDetail", "key", "dayIndex", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "syncHistoryStepDetail", "deviceName", "deviceAddress", "(Ljava/lang/String;Ljava/lang/String;Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncTodayStep", "Lcom/oudmon/ble/base/communication/rsp/TodaySportDataRsp;", "syncTodayStepDetail", "toStepDetail", "deviceId", "interval", "isSync", "", "newStepDetailList", "Lcom/oudmon/ble/base/communication/entity/BleStepDetails;", "updateStepDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StepDetailRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<StepDetailRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<StepDetailRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final StepDetailRepository invoke() {
            return new StepDetailRepository();
        }
    });
    private final QcStepDetailDao stepDetailDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcStepDetailDao();
    private final QcStepTotalDao stepTotalDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcStepTotalDao();
    private ConcurrentHashMap<String, Integer> historyDate = new ConcurrentHashMap<>();

    public final StepTotal queryStepTotal(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.stepTotalDao.queryTotalStepByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), date);
    }

    public final StepDetail queryStepDetailError(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.stepDetailDao.queryByDate(date, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final void deleteStepDetailError(StepDetail detail) {
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.stepDetailDao.delete(detail);
    }

    public final List<QStepBarChart.StepDataBean> queryStepDetailFifteenMinutes(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        StepDetail stepDetailQueryByDate = this.stepDetailDao.queryByDate(date, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        if (stepDetailQueryByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getCounts());
            int[] iArrStringToIntArray3 = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getCalories());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                arrayList.add(new QStepBarChart.StepDataBean(iArrStringToIntArray[i] * 15 * 60, iArrStringToIntArray2[i], 0, iArrStringToIntArray3[i]));
            }
        }
        return arrayList;
    }

    public final List<QStepBarChart.StepDataBean> queryStepDetail(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        StepDetail stepDetailQueryByDate = this.stepDetailDao.queryByDate(date, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        if (stepDetailQueryByDate != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C04011(stepDetailQueryByDate, null), 3, null);
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getCounts());
            int[] iArrStringToIntArray3 = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getMiles());
            int[] iArrStringToIntArray4 = StringUtilsKt.stringToIntArray(stepDetailQueryByDate.getCalories());
            int length = iArrStringToIntArray.length;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = (iArrStringToIntArray[i2] * 15) / 60;
                QStepBarChart.StepDataBean stepDataBean = (QStepBarChart.StepDataBean) linkedHashMap.get(String.valueOf(i3));
                if (stepDataBean != null) {
                    stepDataBean.setSteps(stepDataBean.getSteps() + iArrStringToIntArray2[i2]);
                    stepDataBean.setDistance(stepDataBean.getDistance() + iArrStringToIntArray3[i2]);
                    stepDataBean.setCalorie(stepDataBean.getCalorie() + iArrStringToIntArray4[i2]);
                    linkedHashMap.put(String.valueOf(i3), stepDataBean);
                } else {
                    linkedHashMap.put(String.valueOf(i3), new QStepBarChart.StepDataBean(i3 * CacheConstants.HOUR, iArrStringToIntArray2[i2], iArrStringToIntArray3[i2], iArrStringToIntArray4[i2]));
                }
            }
        }
        while (true) {
            int i4 = i + 1;
            if (linkedHashMap.get(String.valueOf(i)) != null) {
                Object obj = linkedHashMap.get(String.valueOf(i));
                Intrinsics.checkNotNull(obj);
                arrayList.add(i, obj);
            } else {
                arrayList.add(i, new QStepBarChart.StepDataBean(i * CacheConstants.HOUR, 0, 0, 0));
            }
            if (i4 > 23) {
                return arrayList;
            }
            i = i4;
        }
    }

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$queryStepDetail$1", f = "StepDetailRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$queryStepDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C04011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ StepDetail $stepDetail;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04011(StepDetail stepDetail, Continuation<? super C04011> continuation) {
            super(2, continuation);
            this.$stepDetail = stepDetail;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return StepDetailRepository.this.new C04011(this.$stepDetail, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            StepDetailRepository.this.calcStepTotal(this.$stepDetail);
            return Unit.INSTANCE;
        }
    }

    public final void syncTodayStep(final BaseDeviceResult<TodaySportDataRsp> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 72), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                StepDetailRepository.m303syncTodayStep$lambda0(this.f$0, result, (TodaySportDataRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayStep$lambda-0, reason: not valid java name */
    public static final void m303syncTodayStep$lambda0(StepDetailRepository this$0, final BaseDeviceResult result, final TodaySportDataRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            final DateUtil dateUtil = new DateUtil(it.getSportTotal().getYear(), it.getSportTotal().getMonth(), it.getSportTotal().getDay());
            ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<StepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$syncTodayStep$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(StepDetailRepository stepDetailRepository) {
                    invoke2(stepDetailRepository);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(StepDetailRepository ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    QcStepTotalDao qcStepTotalDao = ktxRunOnBgSingle.stepTotalDao;
                    String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "d.y_M_D");
                    if (qcStepTotalDao.queryTotalStepByAddressAndDate(deviceAddressNoClear, y_m_d) != null) {
                        QcStepTotalDao qcStepTotalDao2 = ktxRunOnBgSingle.stepTotalDao;
                        String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d2 = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d2, "d.y_M_D");
                        qcStepTotalDao2.insert(new StepTotal(deviceAddressNoClear2, y_m_d2, it.getSportTotal().getTotalSteps(), it.getSportTotal().getWalkDistance(), it.getSportTotal().getCalorie(), (int) dateUtil.getUnixTimestamp()));
                    } else {
                        QcStepTotalDao qcStepTotalDao3 = ktxRunOnBgSingle.stepTotalDao;
                        String deviceAddressNoClear3 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d3 = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d3, "d.y_M_D");
                        qcStepTotalDao3.insert(new StepTotal(deviceAddressNoClear3, y_m_d3, it.getSportTotal().getTotalSteps(), it.getSportTotal().getWalkDistance(), it.getSportTotal().getCalorie(), (int) dateUtil.getUnixTimestamp()));
                    }
                    BaseDeviceResult<TodaySportDataRsp> baseDeviceResult = result;
                    TodaySportDataRsp it2 = it;
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    baseDeviceResult.result(0, it2);
                }
            });
        } else {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            result.result(-1, it);
        }
    }

    public final void saveTotalDate(final StepTotal stepTotal) {
        Intrinsics.checkNotNullParameter(stepTotal, "stepTotal");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<StepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.saveTotalDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(StepDetailRepository stepDetailRepository) {
                invoke2(stepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(StepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.stepTotalDao.insert(stepTotal);
            }
        });
    }

    public final Object syncHistoryStepDetail(String str, String str2, final BaseDeviceResult<ReadDetailSportDataRsp> baseDeviceResult, Continuation<? super Unit> continuation) {
        if (!BleOperateManager.getInstance().isConnected()) {
            return Unit.INSTANCE;
        }
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.flow(new C04032(str2, null)), Dispatchers.getIO()), new C04043(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.syncHistoryStepDetail.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Map<String, Integer>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Map<String, Integer> map, Continuation<? super Unit> continuation2) {
                Iterator it = StepDetailRepository.this.historyDate.entrySet().iterator();
                if (it.hasNext()) {
                    Object next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                    Map.Entry entry = (Map.Entry) next;
                    StepDetailRepository stepDetailRepository = StepDetailRepository.this;
                    Object key = entry.getKey();
                    Intrinsics.checkNotNullExpressionValue(key, "bean.key");
                    Object value = entry.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "bean.value");
                    stepDetailRepository.syncDeviceStepDetail((String) key, ((Number) value).intValue(), baseDeviceResult);
                } else {
                    baseDeviceResult.result(0, new ReadDetailSportDataRsp());
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$syncHistoryStepDetail$2", f = "StepDetailRepository.kt", i = {}, l = {215}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$syncHistoryStepDetail$2, reason: invalid class name and case insensitive filesystem */
    static final class C04032 extends SuspendLambda implements Function2<FlowCollector<? super Map<String, ? extends Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $deviceAddress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04032(String str, Continuation<? super C04032> continuation) {
            super(2, continuation);
            this.$deviceAddress = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04032 c04032 = StepDetailRepository.this.new C04032(this.$deviceAddress, continuation);
            c04032.L$0 = obj;
            return c04032;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04032) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                DateUtil dateUtil = new DateUtil();
                for (int i2 = 1; i2 < 7; i2++) {
                    dateUtil.addDay(-1);
                    ConcurrentHashMap concurrentHashMap = StepDetailRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                List<StepDetail> listQueryLastSyncDate = StepDetailRepository.this.stepDetailDao.queryLastSyncDate(this.$deviceAddress);
                if (listQueryLastSyncDate != null) {
                    for (StepDetail stepDetail : listQueryLastSyncDate) {
                        if (new DateUtil(stepDetail.getLastSyncTime(), true).isToday()) {
                            StepDetailRepository.this.historyDate.remove(stepDetail.getDateStr());
                            XLog.i("exists data remove date:" + stepDetail.getDateStr());
                        }
                    }
                }
                XLog.i("sync history step detail");
                XLog.i(StepDetailRepository.this.historyDate);
                this.label = 1;
                if (flowCollector.emit(StepDetailRepository.this.historyDate, this) == coroutine_suspended) {
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

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$syncHistoryStepDetail$3", f = "StepDetailRepository.kt", i = {}, l = {223}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$syncHistoryStepDetail$3, reason: invalid class name and case insensitive filesystem */
    static final class C04043 extends SuspendLambda implements Function3<FlowCollector<? super Map<String, ? extends Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04043(Continuation<? super C04043> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04043 c04043 = StepDetailRepository.this.new C04043(continuation);
            c04043.L$0 = flowCollector;
            c04043.L$1 = th;
            return c04043.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                DateUtil dateUtil = new DateUtil();
                for (int i2 = 1; i2 < 7; i2++) {
                    dateUtil.addDay(-1);
                    ConcurrentHashMap concurrentHashMap = StepDetailRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(StepDetailRepository.this.historyDate, this) == coroutine_suspended) {
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

    private final void parseTodayStepDetail(final ReadDetailSportDataRsp resultEntity) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<StepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.parseTodayStepDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(StepDetailRepository stepDetailRepository) {
                invoke2(stepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(StepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList<BleStepDetails> bleStepDetailses = resultEntity.getBleStepDetailses();
                Intrinsics.checkNotNullExpressionValue(bleStepDetailses, "resultEntity.bleStepDetailses");
                if (bleStepDetailses.size() > 0) {
                    DateUtil dateUtil = new DateUtil(bleStepDetailses.get(0).getYear(), bleStepDetailses.get(0).getMonth(), bleStepDetailses.get(0).getDay());
                    QcStepDetailDao qcStepDetailDao = ktxRunOnBgSingle.stepDetailDao;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "dataDate.y_M_D");
                    qcStepDetailDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                    String deviceNameNoClear = UserConfig.INSTANCE.getInstance().getDeviceNameNoClear();
                    String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                    String y_m_d2 = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d2, "dataDate.y_M_D");
                    ktxRunOnBgSingle.stepDetailDao.insert(ktxRunOnBgSingle.toStepDetail(deviceNameNoClear, deviceAddressNoClear, y_m_d2, TypedValues.Custom.TYPE_INT, false, bleStepDetailses));
                    return;
                }
                ThreadExtKt.ktxRunOnBgSingle(ktxRunOnBgSingle, new Function1<StepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.parseTodayStepDetail.1.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(StepDetailRepository stepDetailRepository) {
                        invoke2(stepDetailRepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(StepDetailRepository ktxRunOnBgSingle2) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle2, "$this$ktxRunOnBgSingle");
                        QcStepDetailDao qcStepDetailDao2 = ktxRunOnBgSingle2.stepDetailDao;
                        String y_m_d3 = new DateUtil().getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d3, "DateUtil().y_M_D");
                        StepDetail stepDetailQueryByDate = qcStepDetailDao2.queryByDate(y_m_d3, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                        if (stepDetailQueryByDate != null) {
                            ktxRunOnBgSingle2.stepDetailDao.delete(stepDetailQueryByDate);
                        }
                        String y_m_d4 = new DateUtil().getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d4, "DateUtil().y_M_D");
                        StepTotal stepTotalQueryStepTotal = ktxRunOnBgSingle2.queryStepTotal(y_m_d4);
                        if (stepTotalQueryStepTotal == null || stepTotalQueryStepTotal.getStep() != 0) {
                            return;
                        }
                        ktxRunOnBgSingle2.stepTotalDao.delete(stepTotalQueryStepTotal);
                    }
                });
            }
        });
    }

    private final void parseStepDetail(final ReadDetailSportDataRsp resultEntity) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<StepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.parseStepDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(StepDetailRepository stepDetailRepository) {
                invoke2(stepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(StepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList<BleStepDetails> bleStepDetailses = resultEntity.getBleStepDetailses();
                Intrinsics.checkNotNullExpressionValue(bleStepDetailses, "resultEntity.bleStepDetailses");
                if (bleStepDetailses.size() > 0) {
                    DateUtil dateUtil = new DateUtil(bleStepDetailses.get(0).getYear(), bleStepDetailses.get(0).getMonth(), bleStepDetailses.get(0).getDay());
                    QcStepDetailDao qcStepDetailDao = ktxRunOnBgSingle.stepDetailDao;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "dataDate.y_M_D");
                    StepDetail stepDetailQueryByDate = qcStepDetailDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                    String deviceNameNoClear = UserConfig.INSTANCE.getInstance().getDeviceNameNoClear();
                    String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                    String y_m_d2 = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d2, "dataDate.y_M_D");
                    StepDetail stepDetail = ktxRunOnBgSingle.toStepDetail(deviceNameNoClear, deviceAddressNoClear, y_m_d2, TypedValues.Custom.TYPE_INT, false, bleStepDetailses);
                    if (DateUtil.isSameDay(dateUtil.toDate(), new Date())) {
                        if (stepDetailQueryByDate != null) {
                            stepDetail = ktxRunOnBgSingle.merge(stepDetailQueryByDate, stepDetail);
                        }
                        ktxRunOnBgSingle.stepDetailDao.insert(stepDetail);
                        ktxRunOnBgSingle.calcStepTotal(stepDetail);
                        return;
                    }
                    ktxRunOnBgSingle.stepDetailDao.insert(stepDetail);
                    ktxRunOnBgSingle.calcStepTotal(stepDetail);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calcStepTotal(StepDetail stepDetail) {
        int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(stepDetail.getIndex_str());
        int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(stepDetail.getCounts());
        int[] iArrStringToIntArray3 = StringUtilsKt.stringToIntArray(stepDetail.getMiles());
        int[] iArrStringToIntArray4 = StringUtilsKt.stringToIntArray(stepDetail.getCalories());
        int length = iArrStringToIntArray.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArrStringToIntArray[i4];
            i += iArrStringToIntArray2[i4];
            i2 += iArrStringToIntArray3[i4];
            i3 += iArrStringToIntArray4[i4];
        }
        DateUtil dateUtil = new DateUtil(DateUtil.String2Date("yyyy-MM-dd", stepDetail.getDateStr()));
        StepTotal stepTotal = new StepTotal(stepDetail.getDeviceAddress(), stepDetail.getDateStr(), i, i2, i3, (int) dateUtil.getZeroTime());
        if (stepTotal.getStep() > 0 || stepTotal.getCarolie() > 0) {
            QcStepTotalDao qcStepTotalDao = this.stepTotalDao;
            String deviceAddress = stepDetail.getDeviceAddress();
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
            StepTotal stepTotalQueryTotalStepByAddressAndDate = qcStepTotalDao.queryTotalStepByAddressAndDate(deviceAddress, y_m_d);
            if (stepTotalQueryTotalStepByAddressAndDate == null) {
                this.stepTotalDao.insert(stepTotal);
            } else if (stepTotalQueryTotalStepByAddressAndDate.getCarolie() < stepTotal.getCarolie() || stepTotalQueryTotalStepByAddressAndDate.getStep() < stepTotal.getStep() || stepTotalQueryTotalStepByAddressAndDate.getDistance() < stepTotal.getDistance()) {
                this.stepTotalDao.insert(stepTotal);
            }
        }
    }

    public final void syncTodayStepDetail(int dayIndex, final BaseDeviceResult<ReadDetailSportDataRsp> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CommandHandle.getInstance().executeReqCmd(new ReadDetailSportDataReq(dayIndex, 0, 95), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                StepDetailRepository.m304syncTodayStepDetail$lambda1(this.f$0, result, (ReadDetailSportDataRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayStepDetail$lambda-1, reason: not valid java name */
    public static final void m304syncTodayStepDetail$lambda1(StepDetailRepository this$0, BaseDeviceResult result, ReadDetailSportDataRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.parseTodayStepDetail(it);
            result.result(0, it);
        } else {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            result.result(-1, it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncDeviceStepDetail(final String key, int dayIndex, final BaseDeviceResult<ReadDetailSportDataRsp> result) {
        CommandHandle.getInstance().executeReqCmd(new ReadDetailSportDataReq(dayIndex, 0, 95), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                StepDetailRepository.m302syncDeviceStepDetail$lambda2(this.f$0, key, result, (ReadDetailSportDataRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceStepDetail$lambda-2, reason: not valid java name */
    public static final void m302syncDeviceStepDetail$lambda2(StepDetailRepository this$0, String key, BaseDeviceResult result, ReadDetailSportDataRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            ArrayList<BleStepDetails> list = it.getBleStepDetailses();
            Intrinsics.checkNotNullExpressionValue(list, "list");
            if (!list.isEmpty()) {
                this$0.historyDate.remove(new DateUtil(list.get(0).getYear(), list.get(0).getMonth(), list.get(0).getDay()).getY_M_D());
            } else {
                this$0.historyDate.remove(key);
            }
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.parseStepDetail(it);
            if (!this$0.historyDate.isEmpty()) {
                Iterator<Map.Entry<String, Integer>> it2 = this$0.historyDate.entrySet().iterator();
                if (it2.hasNext()) {
                    Map.Entry<String, Integer> next = it2.next();
                    Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                    Map.Entry<String, Integer> entry = next;
                    String key2 = entry.getKey();
                    Intrinsics.checkNotNullExpressionValue(key2, "bean.key");
                    Integer value = entry.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "bean.value");
                    this$0.syncDeviceStepDetail(key2, value.intValue(), result);
                    return;
                }
                return;
            }
            result.result(0, it);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        result.result(-1, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StepDetail toStepDetail(String deviceName, String deviceId, String date, int interval, boolean isSync, List<? extends BleStepDetails> newStepDetailList) {
        SparseArray sparseArray = new SparseArray();
        if (newStepDetailList != null) {
            for (BleStepDetails bleStepDetails : newStepDetailList) {
                sparseArray.put(bleStepDetails.getTimeIndex(), new Integer[]{Integer.valueOf(bleStepDetails.getWalkSteps()), Integer.valueOf(bleStepDetails.getDistance()), Integer.valueOf(bleStepDetails.getCalorie())});
            }
        }
        int size = interval * sparseArray.size();
        int size2 = sparseArray.size();
        int[] iArr = new int[size2];
        int size3 = sparseArray.size();
        for (int i = 0; i < size3; i++) {
            iArr[i] = sparseArray.keyAt(i);
        }
        Arrays.sort(iArr);
        String strIntArrayToString = StringUtilsKt.intArrayToString(iArr);
        int[] iArr2 = new int[sparseArray.size()];
        int[] iArr3 = new int[sparseArray.size()];
        int[] iArr4 = new int[sparseArray.size()];
        for (int i2 = 0; i2 < size2; i2++) {
            iArr2[i2] = ((Integer[]) sparseArray.get(iArr[i2]))[0].intValue();
            iArr3[i2] = ((Integer[]) sparseArray.get(iArr[i2]))[1].intValue();
            iArr4[i2] = ((Integer[]) sparseArray.get(iArr[i2]))[2].intValue();
        }
        return new StepDetail(deviceId, date, interval, size, strIntArrayToString, StringUtilsKt.intArrayToString(iArr2), StringUtilsKt.intArrayToString(iArr3), StringUtilsKt.intArrayToString(iArr4), isSync, new DateUtil().getUnixTimestamp());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StepDetail merge(StepDetail oldEntity, StepDetail newEntity) {
        SparseArray sparseArray = new SparseArray();
        int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(oldEntity.getIndex_str());
        int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(oldEntity.getCounts());
        int[] iArrStringToIntArray3 = StringUtilsKt.stringToIntArray(oldEntity.getMiles());
        int[] iArrStringToIntArray4 = StringUtilsKt.stringToIntArray(oldEntity.getCalories());
        int length = iArrStringToIntArray.length;
        for (int i = 0; i < length; i++) {
            sparseArray.put(iArrStringToIntArray[i], new Integer[]{Integer.valueOf(iArrStringToIntArray2[i]), Integer.valueOf(iArrStringToIntArray3[i]), Integer.valueOf(iArrStringToIntArray4[i])});
        }
        SparseArray sparseArray2 = new SparseArray();
        int[] iArrStringToIntArray5 = StringUtilsKt.stringToIntArray(newEntity.getIndex_str());
        int[] iArrStringToIntArray6 = StringUtilsKt.stringToIntArray(newEntity.getCounts());
        int[] iArrStringToIntArray7 = StringUtilsKt.stringToIntArray(newEntity.getMiles());
        int[] iArrStringToIntArray8 = StringUtilsKt.stringToIntArray(newEntity.getCalories());
        int length2 = iArrStringToIntArray5.length;
        for (int i2 = 0; i2 < length2; i2++) {
            sparseArray2.put(iArrStringToIntArray5[i2], new Integer[]{Integer.valueOf(iArrStringToIntArray6[i2]), Integer.valueOf(iArrStringToIntArray7[i2]), Integer.valueOf(iArrStringToIntArray8[i2])});
        }
        SparseArray sparseArray3 = new SparseArray();
        int size = sparseArray.size();
        for (int i3 = 0; i3 < size; i3++) {
            sparseArray3.put(sparseArray.keyAt(i3), sparseArray.valueAt(i3));
        }
        int size2 = sparseArray2.size();
        for (int i4 = 0; i4 < size2; i4++) {
            sparseArray3.put(sparseArray2.keyAt(i4), sparseArray2.valueAt(i4));
        }
        oldEntity.setTotalActiveTime(oldEntity.getIntervar() * sparseArray3.size());
        int size3 = sparseArray3.size();
        int[] iArr = new int[size3];
        int size4 = sparseArray3.size();
        for (int i5 = 0; i5 < size4; i5++) {
            iArr[i5] = sparseArray3.keyAt(i5);
        }
        Arrays.sort(iArr);
        oldEntity.setIndex_str(StringUtilsKt.intArrayToString(iArr));
        int[] iArr2 = new int[sparseArray3.size()];
        int[] iArr3 = new int[sparseArray3.size()];
        int[] iArr4 = new int[sparseArray3.size()];
        for (int i6 = 0; i6 < size3; i6++) {
            iArr2[i6] = ((Integer[]) sparseArray3.get(iArr[i6]))[0].intValue();
            iArr3[i6] = ((Integer[]) sparseArray3.get(iArr[i6]))[1].intValue();
            iArr4[i6] = ((Integer[]) sparseArray3.get(iArr[i6]))[2].intValue();
        }
        oldEntity.setCounts(StringUtilsKt.intArrayToString(iArr2));
        oldEntity.setMiles(StringUtilsKt.intArrayToString(iArr3));
        oldEntity.setCalories(StringUtilsKt.intArrayToString(iArr4));
        oldEntity.setSync(false);
        return oldEntity;
    }

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$2", f = "StepDetailRepository.kt", i = {0, 0, 1}, l = {516, 516, 522}, m = "invokeSuspend", n = {"$this$flow", "notUpList", "$this$flow"}, s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C04062 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C04062(Continuation<? super C04062> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04062 c04062 = StepDetailRepository.this.new C04062(continuation);
            c04062.L$0 = obj;
            return c04062;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04062) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0118 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instructions count: 307
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.C04062.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: StepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$2$1", f = "StepDetailRepository.kt", i = {}, l = {517}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            final /* synthetic */ List<StepDetail> $notUpList;
            int label;
            final /* synthetic */ StepDetailRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, List<StepDetail> list, StepDetailRepository stepDetailRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$notUpList = list;
                this.this$0 = stepDetailRepository;
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
                for (StepDetail stepDetail : this.$notUpList) {
                    stepDetail.setSync(true);
                    this.this$0.stepDetailDao.insert(stepDetail);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: StepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$2$2", f = "StepDetailRepository.kt", i = {}, l = {523}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00692 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00692(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00692> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00692 c00692 = new C00692(this.$$this$flow, continuation);
                c00692.I$0 = i;
                return c00692.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateStepDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04062(null)), new C04073(null)), Dispatchers.getIO()), new C04084(null));
    }

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$3", f = "StepDetailRepository.kt", i = {}, l = {GattClient.STATE_PREPARED}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C04073 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04073(Continuation<? super C04073> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04073 c04073 = new C04073(continuation);
            c04073.L$0 = obj;
            return c04073;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04073) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$4", f = "StepDetailRepository.kt", i = {}, l = {530}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$updateStepDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C04084 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04084(Continuation<? super C04084> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04084 c04084 = new C04084(continuation);
            c04084.L$0 = flowCollector;
            c04084.L$1 = th;
            return c04084.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/StepDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2", f = "StepDetailRepository.kt", i = {0, 1}, l = {GattClient.STATE_PENDDING_DISCOVERY_SERVICE, GattClient.STATE_PENDDING_DISCOVERY_SERVICE, 538}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends StepDetailResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends StepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<StepDetailResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<StepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
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
                java.lang.Object r1 = r6.downStepDetail(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: StepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/StepDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$1", f = "StepDetailRepository.kt", i = {}, l = {GattClient.STATE_DISCOVERY_SERVICE}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends StepDetailResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<StepDetailResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<StepDetailResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends StepDetailResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<StepDetailResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<StepDetailResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: StepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$2", f = "StepDetailRepository.kt", i = {}, l = {GattClient.STATE_READ_DEVICE_INFO}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00672 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<StepDetailResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00672(FlowCollector<? super NetState<List<StepDetailResp>>> flowCollector, Continuation<? super C00672> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00672 c00672 = new C00672(this.$$this$flow, continuation);
                c00672.I$0 = i;
                return c00672.invokeSuspend(Unit.INSTANCE);
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

    public final Object downStepDetailFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository.downStepDetailFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<StepDetailResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<StepDetailResp>> netState, Continuation<? super Unit> continuation2) {
                List<StepDetailResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (StepDetailResp stepDetailResp : listIsSuccess) {
                            String deviceId = stepDetailResp.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            String dateStr = stepDetailResp.getDateStr();
                            Intrinsics.checkNotNull(dateStr);
                            StepDetail stepDetail = new StepDetail(deviceId, dateStr, stepDetailResp.getInterval(), stepDetailResp.getTotalActiveTime(), StringUtilsKt.intListToString(stepDetailResp.getIndexs()), StringUtilsKt.intListToString(stepDetailResp.getCounts()), StringUtilsKt.intListToString(stepDetailResp.getMiles()), StringUtilsKt.intListToString(stepDetailResp.getCalories()), true, new DateUtil().getUnixTimestamp());
                            StepDetailRepository.this.stepDetailDao.insert(stepDetail);
                            StepDetailRepository.this.calcStepTotal(stepDetail);
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

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/StepDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$3", f = "StepDetailRepository.kt", i = {}, l = {GattClient.STATE_READ_BATTERY_INFO}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends StepDetailResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends StepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<StepDetailResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<StepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/StepDetailResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$4", f = "StepDetailRepository.kt", i = {}, l = {545}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository$downStepDetailFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends StepDetailResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends StepDetailResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<StepDetailResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<StepDetailResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: StepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StepDetailRepository getGetInstance() {
            return (StepDetailRepository) StepDetailRepository.getInstance$delegate.getValue();
        }
    }
}
