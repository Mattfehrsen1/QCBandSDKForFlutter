package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.internal.view.SupportMenu;
import com.elvishew.xlog.XLog;
import com.haibin.calendarview.Calendar;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.HRVReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.HRVRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.ManualHrvDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcHrvDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.HRVDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.HRVManualEntity;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.base.view.QHrvChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QHrvLineChartView;
import java.util.ArrayList;
import java.util.Comparator;
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
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
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
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: HRVRepository.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 72\u00020\u0001:\u00017B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006J\u0006\u0010\u0010\u001a\u00020\u0011J(\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u001a\u001a\u00020\u001bJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\b\u0010\"\u001a\u0004\u0018\u00010#J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0006J\u0010\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,H\u0002J'\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u00052\f\u00100\u001a\b\u0012\u0004\u0012\u00020,01H\u0086@ø\u0001\u0000¢\u0006\u0002\u00102J&\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00062\f\u00100\u001a\b\u0012\u0004\u0012\u00020,01H\u0002J\u0014\u00106\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u00020,01R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;", "", "()V", "historyDate", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "hrvDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcHrvDetailDao;", "hrvManualDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/ManualHrvDao;", "calendarPressure", "", "Lcom/haibin/calendarview/Calendar;", "year", "month", "deleteData", "", "getSchemeCalendar", "day", "text", "queryAppPressure", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/HRVManualEntity;", "queryHrvByDateDetailMap", "", "Lcom/qcwireless/qcwatch/ui/base/view/QHrvLineChartView$HeartDataBean;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryHrvByDateDetailResp", "", "queryHrvByDateDetailRespAsc", "queryLastHrv", "", "Lcom/qcwireless/qcwatch/ui/base/view/QHrvChartHomeView$BpDataBean;", "queryLastPressureDate", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/HRVDetail;", "queryManualPressure", "queryManualPressureAll", "saveManualPressure", "dataTime", "", "value", "savePressure", "heartResp", "Lcom/oudmon/ble/base/communication/rsp/HRVRsp;", "savePressureToday", "syncHistoryHrvDetail", "deviceAddress", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "(Ljava/lang/String;Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncHrvDetail", "key", TypedValues.CycleType.S_WAVE_OFFSET, "syncTodayHrv", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HRVRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<HRVRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<HRVRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HRVRepository invoke() {
            return new HRVRepository();
        }
    });
    private final QcHrvDetailDao hrvDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcHrvDetailDao();
    private final ManualHrvDao hrvManualDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).manualHrvDao();
    private ConcurrentHashMap<String, Integer> historyDate = new ConcurrentHashMap<>();

    public final HRVDetail queryLastPressureDate() {
        return this.hrvDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "");
    }

    public final List<QHrvLineChartView.HeartDataBean> queryHrvByDateDetailResp(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcHrvDetailDao qcHrvDetailDao = this.hrvDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        HRVDetail hRVDetailQueryPressureByDate = qcHrvDetailDao.queryPressureByDate(deviceAddressNoClear, y_m_d);
        if (hRVDetailQueryPressureByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(hRVDetailQueryPressureByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(hRVDetailQueryPressureByDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    arrayList.add(new QHrvLineChartView.HeartDataBean(i2 * hRVDetailQueryPressureByDate.getIntervar(), iArrStringToIntArray2[i], false, hRVDetailQueryPressureByDate.getIntervar()));
                }
            }
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$queryHrvByDateDetailResp$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((QHrvLineChartView.HeartDataBean) t2).getMin()), Integer.valueOf(((QHrvLineChartView.HeartDataBean) t).getMin()));
                }
            });
        }
        return arrayList;
    }

    public final List<QHrvLineChartView.HeartDataBean> queryHrvByDateDetailRespAsc(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcHrvDetailDao qcHrvDetailDao = this.hrvDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        HRVDetail hRVDetailQueryPressureByDate = qcHrvDetailDao.queryPressureByDate(deviceAddressNoClear, y_m_d);
        if (hRVDetailQueryPressureByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(hRVDetailQueryPressureByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(hRVDetailQueryPressureByDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    arrayList.add(new QHrvLineChartView.HeartDataBean(i2 * hRVDetailQueryPressureByDate.getIntervar(), iArrStringToIntArray2[i], false, hRVDetailQueryPressureByDate.getIntervar()));
                }
            }
        }
        return arrayList;
    }

    public final Map<Integer, QHrvLineChartView.HeartDataBean> queryHrvByDateDetailMap(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        QcHrvDetailDao qcHrvDetailDao = this.hrvDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        HRVDetail hRVDetailQueryPressureByDate = qcHrvDetailDao.queryPressureByDate(deviceAddressNoClear, y_m_d);
        if (hRVDetailQueryPressureByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(hRVDetailQueryPressureByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(hRVDetailQueryPressureByDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    QHrvLineChartView.HeartDataBean heartDataBean = new QHrvLineChartView.HeartDataBean(i2 * hRVDetailQueryPressureByDate.getIntervar(), iArrStringToIntArray2[i], false, hRVDetailQueryPressureByDate.getIntervar());
                    linkedHashMap.put(Integer.valueOf(heartDataBean.getMin()), heartDataBean);
                }
            }
        }
        return linkedHashMap;
    }

    public final void syncTodayHrv(final BaseDeviceResult<HRVRsp> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CommandHandle.getInstance().executeReqCmd(new HRVReq((byte) 0), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                HRVRepository.m289syncTodayHrv$lambda1(this.f$0, result, (HRVRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayHrv$lambda-1, reason: not valid java name */
    public static final void m289syncTodayHrv$lambda1(HRVRepository this$0, BaseDeviceResult result, HRVRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.savePressureToday(it);
            if (it.isEndFlag()) {
                result.result(0, it);
                return;
            }
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        result.result(-1, it);
    }

    private final void savePressureToday(final HRVRsp heartResp) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HRVRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository.savePressureToday.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HRVRepository hRVRepository) {
                invoke2(hRVRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HRVRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                DateUtil dateUtil = new DateUtil();
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                if (heartResp.getHrvArray() == null) {
                    return;
                }
                byte[] hrvArray = heartResp.getHrvArray();
                int length = hrvArray.length;
                for (int i = 0; i < length; i++) {
                    sb.append(i);
                    sb.append(",");
                    int iByteToInt = hrvArray[i];
                    if (iByteToInt < 0) {
                        iByteToInt = ByteUtil.byteToInt(hrvArray[i]);
                    }
                    sb2.append(iByteToInt);
                    sb2.append(",");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb2.deleteCharAt(sb2.length() - 1);
                }
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                int range = heartResp.getRange();
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "indexStr.toString()");
                String string2 = sb2.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "values.toString()");
                HRVDetail hRVDetail = new HRVDetail(deviceAddressNoClear, y_m_d, range, string, string2, dateUtil.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                XLog.i(hRVDetail);
                if (dateUtil.getZeroTime() > 0) {
                    ktxRunOnBgSingle.hrvDao.insert(hRVDetail);
                    return;
                }
                QcHrvDetailDao qcHrvDetailDao = ktxRunOnBgSingle.hrvDao;
                String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                qcHrvDetailDao.delete(new HRVDetail(deviceAddressNoClear2, y_m_d2, heartResp.getRange(), "", "", new DateUtil().getZeroTime(), false, new DateUtil().getUnixTimestamp()));
            }
        });
    }

    public final Object syncHistoryHrvDetail(String str, final BaseDeviceResult<HRVRsp> baseDeviceResult, Continuation<? super Unit> continuation) {
        if (!BleOperateManager.getInstance().isConnected()) {
            return Unit.INSTANCE;
        }
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.flow(new AnonymousClass2(str, null)), Dispatchers.getIO()), new AnonymousClass3(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository.syncHistoryHrvDetail.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Map<String, Integer>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Map<String, Integer> map, Continuation<? super Unit> continuation2) {
                Iterator it = HRVRepository.this.historyDate.entrySet().iterator();
                if (it.hasNext()) {
                    Object next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                    Map.Entry entry = (Map.Entry) next;
                    HRVRepository hRVRepository = HRVRepository.this;
                    Object key = entry.getKey();
                    Intrinsics.checkNotNullExpressionValue(key, "bean.key");
                    Object value = entry.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "bean.value");
                    hRVRepository.syncHrvDetail((String) key, ((Number) value).intValue(), baseDeviceResult);
                } else {
                    baseDeviceResult.result(0, new HRVRsp());
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: HRVRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$syncHistoryHrvDetail$2", f = "HRVRepository.kt", i = {}, l = {218}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$syncHistoryHrvDetail$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super Map<String, ? extends Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $deviceAddress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$deviceAddress = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = HRVRepository.this.new AnonymousClass2(this.$deviceAddress, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    ConcurrentHashMap concurrentHashMap = HRVRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                List<HRVDetail> listQueryDaysSyncDate = HRVRepository.this.hrvDao.queryDaysSyncDate(this.$deviceAddress);
                if (listQueryDaysSyncDate != null) {
                    for (HRVDetail hRVDetail : listQueryDaysSyncDate) {
                        if (new DateUtil(hRVDetail.getLastSyncTime(), true).isToday()) {
                            HRVRepository.this.historyDate.remove(hRVDetail.getDateStr());
                        }
                    }
                }
                XLog.i("-sync hrv date");
                XLog.i(HRVRepository.this.historyDate);
                this.label = 1;
                if (flowCollector.emit(HRVRepository.this.historyDate, this) == coroutine_suspended) {
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

    /* compiled from: HRVRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$syncHistoryHrvDetail$3", f = "HRVRepository.kt", i = {}, l = {226}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$syncHistoryHrvDetail$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super Map<String, ? extends Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass3 anonymousClass3 = HRVRepository.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = flowCollector;
            anonymousClass3.L$1 = th;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
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
                    ConcurrentHashMap concurrentHashMap = HRVRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(HRVRepository.this.historyDate, this) == coroutine_suspended) {
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
    public final void syncHrvDetail(final String key, int offset, final BaseDeviceResult<HRVRsp> result) {
        this.historyDate.remove(key);
        CommandHandle.getInstance().executeReqCmd(new HRVReq((byte) offset), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                HRVRepository.m288syncHrvDetail$lambda2(this.f$0, key, result, (HRVRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncHrvDetail$lambda-2, reason: not valid java name */
    public static final void m288syncHrvDetail$lambda2(HRVRepository this$0, String key, BaseDeviceResult result, HRVRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            DateUtil dateUtil = new DateUtil();
            dateUtil.addDay(-it.getOffset());
            this$0.historyDate.remove(dateUtil.getY_M_D());
            if (dateUtil.isToday()) {
                this$0.historyDate.remove(key);
            }
            if (it.getOffset() > 0) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                this$0.savePressure(it);
            }
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
                    this$0.syncHrvDetail(key2, value.intValue(), result);
                    return;
                }
                return;
            }
            Intrinsics.checkNotNullExpressionValue(it, "it");
            result.result(0, it);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        result.result(-1, it);
    }

    private final void savePressure(final HRVRsp heartResp) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HRVRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository.savePressure.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HRVRepository hRVRepository) {
                invoke2(hRVRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HRVRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                com.oudmon.ble.base.util.DateUtil today = heartResp.getToday();
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                byte[] hrvArray = heartResp.getHrvArray();
                int length = hrvArray.length;
                for (int i = 0; i < length; i++) {
                    sb.append(i);
                    sb.append(",");
                    int iByteToInt = hrvArray[i];
                    if (iByteToInt < 0) {
                        iByteToInt = ByteUtil.byteToInt(hrvArray[i]);
                    }
                    sb2.append(iByteToInt);
                    sb2.append(",");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb2.deleteCharAt(sb2.length() - 1);
                }
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = today.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateStr.y_M_D");
                int range = heartResp.getRange();
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "indexStr.toString()");
                String string2 = sb2.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "values.toString()");
                HRVDetail hRVDetail = new HRVDetail(deviceAddressNoClear, y_m_d, range, string, string2, today.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                if (today.getZeroTime() > 0) {
                    ktxRunOnBgSingle.hrvDao.insert(hRVDetail);
                }
            }
        });
    }

    public final void deleteData() throws InterruptedException {
        ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<HRVRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository.deleteData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HRVRepository hRVRepository) {
                invoke2(hRVRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HRVRepository ktxRunOnBgSingleAnother) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                ManualHrvDao manualHrvDao = ktxRunOnBgSingleAnother.hrvManualDao;
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                List<HRVManualEntity> listQueryDataAll = manualHrvDao.queryDataAll(deviceAddressNoClear, y_m_d);
                if (listQueryDataAll != null) {
                    ktxRunOnBgSingleAnother.hrvManualDao.deleteList(listQueryDataAll);
                }
                QcHrvDetailDao qcHrvDetailDao = ktxRunOnBgSingleAnother.hrvDao;
                String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                HRVDetail hRVDetailQueryPressureByDate = qcHrvDetailDao.queryPressureByDate(deviceAddressNoClear2, y_m_d2);
                if (hRVDetailQueryPressureByDate != null) {
                    ktxRunOnBgSingleAnother.hrvDao.delete(hRVDetailQueryPressureByDate);
                }
            }
        });
    }

    public final HRVManualEntity queryAppPressure() {
        return this.hrvManualDao.queryDataLimitLast(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final List<QHrvChartHomeView.BpDataBean> queryLastHrv() {
        new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        HRVDetail hRVDetailQueryLastSyncDate = this.hrvDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "");
        if (hRVDetailQueryLastSyncDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(hRVDetailQueryLastSyncDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(hRVDetailQueryLastSyncDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    QHrvChartHomeView.BpDataBean bpDataBean = new QHrvChartHomeView.BpDataBean(hRVDetailQueryLastSyncDate.getIntervar() * i2, iArrStringToIntArray2[i]);
                    bpDataBean.setUnixTime(hRVDetailQueryLastSyncDate.getUnixTime());
                    linkedHashMap.put(Integer.valueOf(i2 * hRVDetailQueryLastSyncDate.getIntervar()), bpDataBean);
                }
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.values());
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository$queryLastHrv$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((QHrvChartHomeView.BpDataBean) t).getMin()), Integer.valueOf(((QHrvChartHomeView.BpDataBean) t2).getMin()));
                }
            });
        }
        return arrayList;
    }

    public final List<HRVManualEntity> queryManualPressure(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ManualHrvDao manualHrvDao = this.hrvManualDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        return manualHrvDao.queryDataLimit(deviceAddressNoClear, y_m_d);
    }

    public final List<HRVManualEntity> queryManualPressureAll(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ManualHrvDao manualHrvDao = this.hrvManualDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        return manualHrvDao.queryDataAll(deviceAddressNoClear, y_m_d);
    }

    public final void saveManualPressure(final long dataTime, final int value) throws InterruptedException {
        ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<HRVRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository.saveManualPressure.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HRVRepository hRVRepository) {
                invoke2(hRVRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HRVRepository ktxRunOnBgSingleAnother) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                DateUtil dateUtil = new DateUtil(dataTime, true);
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                ktxRunOnBgSingleAnother.hrvManualDao.insert(new HRVManualEntity(deviceAddressNoClear, y_m_d, value, (int) dataTime));
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    public final Map<String, Calendar> calendarPressure(int year, int month) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        DateUtil dateUtil = new DateUtil(year, month, 1);
        for (int i = 1; i < 32; i++) {
            QcHrvDetailDao qcHrvDetailDao = this.hrvDao;
            String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "monthDate.y_M_D");
            if (qcHrvDetailDao.queryPressureByDate(deviceAddressNoClear, y_m_d) != null) {
                String string = getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), "").toString();
                Intrinsics.checkNotNullExpressionValue(string, "getSchemeCalendar(\n     …             ).toString()");
                linkedHashMap.put(string, getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), ""));
            }
            dateUtil.addDay(1);
        }
        return linkedHashMap;
    }

    private final Calendar getSchemeCalendar(int year, int month, int day, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(SupportMenu.CATEGORY_MASK);
        calendar.setScheme(text);
        return calendar;
    }

    /* compiled from: HRVRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HRVRepository getGetInstance() {
            return (HRVRepository) HRVRepository.getInstance$delegate.getValue();
        }
    }
}
