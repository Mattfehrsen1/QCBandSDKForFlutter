package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.internal.view.SupportMenu;
import com.elvishew.xlog.XLog;
import com.haibin.calendarview.Calendar;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.PressureReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.PressureRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.ManualPressureDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcPressureDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.PressureDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.PressureManualEntity;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.base.view.QPressureBarChart;
import com.qcwireless.qcwatch.ui.base.view.QPressureLineChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QPressureMonthHistoryBarChart;
import com.qcwireless.qcwatch.ui.base.view.QPressureWeekHistoryBarChart;
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
import kotlin.math.MathKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: PressureRepository.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 =2\u00020\u0001:\u0001=B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006J\u0006\u0010\u0010\u001a\u00020\u0011J(\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018J\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00182\u0006\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u001fJ\u0018\u0010%\u001a\u0004\u0018\u00010\u001b2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u0005J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00182\u0006\u0010\u001e\u001a\u00020\u001fJ\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00182\u0006\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u001fJ\u0016\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0006J\u0010\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u000202H\u0002J\u0010\u00103\u001a\u00020\u00112\u0006\u00101\u001a\u000202H\u0002J'\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00052\f\u00106\u001a\b\u0012\u0004\u0012\u00020207H\u0086@ø\u0001\u0000¢\u0006\u0002\u00108J&\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00062\f\u00106\u001a\b\u0012\u0004\u0012\u00020207H\u0002J\u0014\u0010<\u001a\u00020\u00112\f\u00106\u001a\b\u0012\u0004\u0012\u00020207R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "", "()V", "historyDate", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "pressureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcPressureDetailDao;", "pressureManualDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/ManualPressureDao;", "calendarPressure", "", "Lcom/haibin/calendarview/Calendar;", "year", "month", "deleteData", "", "getSchemeCalendar", "day", "text", "queryAppPressure", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/PressureManualEntity;", "queryLastPressure", "", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureLineChartHomeView$DataBean;", "queryLastPressureDate", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/PressureDetail;", "queryManualPressure", "", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryManualPressureAll", "queryMonthHistoryPressureByDate", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureMonthHistoryBarChart$StepDataBean;", "start", "end", "queryPressureByDate", "mac", "dateStr", "queryPressureByDateDetailResp", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureBarChart$PressureDataBean;", "queryWeekHistoryPressureByDate", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureWeekHistoryBarChart$StepDataBean;", "saveManualPressure", "dataTime", "", "value", "savePressure", "heartResp", "Lcom/oudmon/ble/base/communication/rsp/PressureRsp;", "savePressureToday", "syncHistoryPressureDetail", "deviceAddress", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "(Ljava/lang/String;Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPressureDetail", "key", TypedValues.CycleType.S_WAVE_OFFSET, "syncTodayPressure", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PressureRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<PressureRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<PressureRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PressureRepository invoke() {
            return new PressureRepository();
        }
    });
    private final QcPressureDetailDao pressureDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcPressureDao();
    private final ManualPressureDao pressureManualDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).manualPressureDao();
    private ConcurrentHashMap<String, Integer> historyDate = new ConcurrentHashMap<>();

    public final PressureDetail queryLastPressureDate() {
        return this.pressureDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "");
    }

    public final List<QPressureWeekHistoryBarChart.StepDataBean> queryWeekHistoryPressureByDate(DateUtil start, DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        dateUtil.setHour(0);
        dateUtil.setMinute(0);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<PressureDetail> listQueryByAddressAndDate = this.pressureDao.queryByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), String.valueOf(start.getZeroTime()), String.valueOf(end.getUnixTimestamp()));
        for (int i = 0; i < 7; i++) {
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "tempDate.y_M_D");
            linkedHashMap.put(y_m_d, new QPressureWeekHistoryBarChart.StepDataBean(dateUtil.getUnixTimestamp(), 0));
            dateUtil.addDay(1);
        }
        for (PressureDetail pressureDetail : listQueryByAddressAndDate) {
            String dateStr = pressureDetail.getDateStr();
            QPressureWeekHistoryBarChart.StepDataBean stepDataBean = (QPressureWeekHistoryBarChart.StepDataBean) linkedHashMap.get(dateStr);
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(pressureDetail.getValue());
            int length = iArrStringToIntArray.length;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                int i5 = iArrStringToIntArray[i4];
                if (iArrStringToIntArray[i4] != 0) {
                    i3 += i5;
                    i2++;
                }
            }
            if (i2 > 0) {
                if (stepDataBean != null) {
                    stepDataBean.setSteps(MathKt.roundToInt((i3 * 1.0f) / i2));
                }
            } else if (stepDataBean != null) {
                stepDataBean.setSteps(0);
            }
            if (stepDataBean != null) {
                linkedHashMap.put(dateStr, stepDataBean);
            }
        }
        return new ArrayList(linkedHashMap.values());
    }

    public final List<QPressureMonthHistoryBarChart.StepDataBean> queryMonthHistoryPressureByDate(DateUtil start, DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        dateUtil.setHour(0);
        dateUtil.setMinute(0);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<PressureDetail> listQueryByAddressAndDate = this.pressureDao.queryByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), String.valueOf(start.getZeroTime()), String.valueOf(end.getUnixTimestamp()));
        int daysOfMonth = DateUtil.getDaysOfMonth(start.toDate());
        if (1 <= daysOfMonth) {
            int i = 1;
            while (true) {
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "tempDate.y_M_D");
                linkedHashMap.put(y_m_d, new QPressureMonthHistoryBarChart.StepDataBean(dateUtil.getUnixTimestamp(), 0));
                dateUtil.addDay(1);
                if (i == daysOfMonth) {
                    break;
                }
                i++;
            }
        }
        for (PressureDetail pressureDetail : listQueryByAddressAndDate) {
            String dateStr = pressureDetail.getDateStr();
            QPressureMonthHistoryBarChart.StepDataBean stepDataBean = (QPressureMonthHistoryBarChart.StepDataBean) linkedHashMap.get(dateStr);
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(pressureDetail.getValue());
            int length = iArrStringToIntArray.length;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                int i5 = iArrStringToIntArray[i4];
                if (iArrStringToIntArray[i4] != 0) {
                    i3 += i5;
                    i2++;
                }
            }
            if (i2 > 0) {
                if (stepDataBean != null) {
                    stepDataBean.setSteps(MathKt.roundToInt((i3 * 1.0f) / i2));
                }
            } else if (stepDataBean != null) {
                stepDataBean.setSteps(0);
            }
            if (stepDataBean != null) {
                linkedHashMap.put(dateStr, stepDataBean);
            }
        }
        return new ArrayList(linkedHashMap.values());
    }

    public final List<QPressureBarChart.PressureDataBean> queryPressureByDateDetailResp(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcPressureDetailDao qcPressureDetailDao = this.pressureDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        PressureDetail pressureDetailQueryPressureByDate = qcPressureDetailDao.queryPressureByDate(deviceAddressNoClear, y_m_d);
        if (pressureDetailQueryPressureByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(pressureDetailQueryPressureByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(pressureDetailQueryPressureByDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    arrayList.add(new QPressureBarChart.PressureDataBean(i2 * pressureDetailQueryPressureByDate.getIntervar() * 60, iArrStringToIntArray2[i]));
                }
            }
        }
        return arrayList;
    }

    public final void syncTodayPressure(final BaseDeviceResult<PressureRsp> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CommandHandle.getInstance().executeReqCmd(new PressureReq((byte) 0), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                PressureRepository.m294syncTodayPressure$lambda0(this.f$0, result, (PressureRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayPressure$lambda-0, reason: not valid java name */
    public static final void m294syncTodayPressure$lambda0(PressureRepository this$0, BaseDeviceResult result, PressureRsp it) {
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

    private final void savePressureToday(final PressureRsp heartResp) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<PressureRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository.savePressureToday.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PressureRepository pressureRepository) {
                invoke2(pressureRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PressureRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                DateUtil dateUtil = new DateUtil();
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                if (heartResp.getPressureArray() == null) {
                    return;
                }
                byte[] pressureArray = heartResp.getPressureArray();
                int length = pressureArray.length;
                for (int i = 0; i < length; i++) {
                    sb.append(i);
                    sb.append(",");
                    int iByteToInt = pressureArray[i];
                    if (iByteToInt < 0) {
                        iByteToInt = ByteUtil.byteToInt(pressureArray[i]);
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
                PressureDetail pressureDetail = new PressureDetail(deviceAddressNoClear, y_m_d, range, string, string2, dateUtil.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                if (dateUtil.getZeroTime() > 0) {
                    ktxRunOnBgSingle.pressureDao.insert(pressureDetail);
                    return;
                }
                QcPressureDetailDao qcPressureDetailDao = ktxRunOnBgSingle.pressureDao;
                String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                qcPressureDetailDao.delete(new PressureDetail(deviceAddressNoClear2, y_m_d2, heartResp.getRange(), "", "", new DateUtil().getZeroTime(), false, new DateUtil().getUnixTimestamp()));
            }
        });
    }

    public final Object syncHistoryPressureDetail(String str, final BaseDeviceResult<PressureRsp> baseDeviceResult, Continuation<? super Unit> continuation) {
        if (!BleOperateManager.getInstance().isConnected()) {
            return Unit.INSTANCE;
        }
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.flow(new AnonymousClass2(str, null)), Dispatchers.getIO()), new AnonymousClass3(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository.syncHistoryPressureDetail.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Map<String, Integer>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Map<String, Integer> map, Continuation<? super Unit> continuation2) {
                Iterator it = PressureRepository.this.historyDate.entrySet().iterator();
                if (it.hasNext()) {
                    Object next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                    Map.Entry entry = (Map.Entry) next;
                    PressureRepository pressureRepository = PressureRepository.this;
                    Object key = entry.getKey();
                    Intrinsics.checkNotNullExpressionValue(key, "bean.key");
                    Object value = entry.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "bean.value");
                    pressureRepository.syncPressureDetail((String) key, ((Number) value).intValue(), baseDeviceResult);
                } else {
                    baseDeviceResult.result(0, new PressureRsp());
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: PressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$syncHistoryPressureDetail$2", f = "PressureRepository.kt", i = {}, l = {256}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$syncHistoryPressureDetail$2, reason: invalid class name */
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
            AnonymousClass2 anonymousClass2 = PressureRepository.this.new AnonymousClass2(this.$deviceAddress, continuation);
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
                    ConcurrentHashMap concurrentHashMap = PressureRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                List<PressureDetail> listQueryDaysSyncDate = PressureRepository.this.pressureDao.queryDaysSyncDate(this.$deviceAddress);
                if (listQueryDaysSyncDate != null) {
                    for (PressureDetail pressureDetail : listQueryDaysSyncDate) {
                        if (new DateUtil(pressureDetail.getLastSyncTime(), true).isToday()) {
                            PressureRepository.this.historyDate.remove(pressureDetail.getDateStr());
                        }
                    }
                }
                XLog.i("-sync pressure date");
                XLog.i(PressureRepository.this.historyDate);
                this.label = 1;
                if (flowCollector.emit(PressureRepository.this.historyDate, this) == coroutine_suspended) {
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

    /* compiled from: PressureRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$syncHistoryPressureDetail$3", f = "PressureRepository.kt", i = {}, l = {264}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$syncHistoryPressureDetail$3, reason: invalid class name */
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
            AnonymousClass3 anonymousClass3 = PressureRepository.this.new AnonymousClass3(continuation);
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
                    ConcurrentHashMap concurrentHashMap = PressureRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(PressureRepository.this.historyDate, this) == coroutine_suspended) {
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
    public final void syncPressureDetail(final String key, int offset, final BaseDeviceResult<PressureRsp> result) {
        this.historyDate.remove(key);
        CommandHandle.getInstance().executeReqCmd(new PressureReq((byte) offset), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                PressureRepository.m293syncPressureDetail$lambda1(this.f$0, key, result, (PressureRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncPressureDetail$lambda-1, reason: not valid java name */
    public static final void m293syncPressureDetail$lambda1(PressureRepository this$0, String key, BaseDeviceResult result, PressureRsp it) {
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
                    this$0.syncPressureDetail(key2, value.intValue(), result);
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

    private final void savePressure(final PressureRsp heartResp) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<PressureRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository.savePressure.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PressureRepository pressureRepository) {
                invoke2(pressureRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PressureRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                com.oudmon.ble.base.util.DateUtil today = heartResp.getToday();
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                byte[] pressureArray = heartResp.getPressureArray();
                int length = pressureArray.length;
                for (int i = 0; i < length; i++) {
                    sb.append(i);
                    sb.append(",");
                    int iByteToInt = pressureArray[i];
                    if (iByteToInt < 0) {
                        iByteToInt = ByteUtil.byteToInt(pressureArray[i]);
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
                PressureDetail pressureDetail = new PressureDetail(deviceAddressNoClear, y_m_d, range, string, string2, today.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                if (today.getZeroTime() > 0) {
                    ktxRunOnBgSingle.pressureDao.insert(pressureDetail);
                }
            }
        });
    }

    public final void deleteData() throws InterruptedException {
        ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<PressureRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository.deleteData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PressureRepository pressureRepository) {
                invoke2(pressureRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PressureRepository ktxRunOnBgSingleAnother) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                ManualPressureDao manualPressureDao = ktxRunOnBgSingleAnother.pressureManualDao;
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                List<PressureManualEntity> listQueryDataAll = manualPressureDao.queryDataAll(deviceAddressNoClear, y_m_d);
                if (listQueryDataAll != null) {
                    ktxRunOnBgSingleAnother.pressureManualDao.deleteList(listQueryDataAll);
                }
            }
        });
    }

    public final PressureManualEntity queryAppPressure() {
        return this.pressureManualDao.queryDataLimitLast(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final List<QPressureLineChartHomeView.DataBean> queryLastPressure() {
        new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        PressureDetail pressureDetailQueryLastSyncDate = this.pressureDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "");
        if (pressureDetailQueryLastSyncDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(pressureDetailQueryLastSyncDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(pressureDetailQueryLastSyncDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    QPressureLineChartHomeView.DataBean dataBean = new QPressureLineChartHomeView.DataBean(i2 * pressureDetailQueryLastSyncDate.getIntervar() * 60, iArrStringToIntArray2[i], iArrStringToIntArray2[i]);
                    dataBean.setUnixTime((int) pressureDetailQueryLastSyncDate.getUnixTime());
                    linkedHashMap.put(Integer.valueOf(dataBean.getSeconds() / 60), dataBean);
                }
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.values());
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository$queryLastPressure$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((QPressureLineChartHomeView.DataBean) t).getSeconds() / 60), Integer.valueOf(((QPressureLineChartHomeView.DataBean) t2).getSeconds() / 60));
                }
            });
        }
        return arrayList;
    }

    public final List<PressureManualEntity> queryManualPressure(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ManualPressureDao manualPressureDao = this.pressureManualDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        return manualPressureDao.queryDataLimit(deviceAddressNoClear, y_m_d);
    }

    public final List<PressureManualEntity> queryManualPressureAll(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ManualPressureDao manualPressureDao = this.pressureManualDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        return manualPressureDao.queryDataAll(deviceAddressNoClear, y_m_d);
    }

    public final PressureDetail queryPressureByDate(String mac, String dateStr) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return this.pressureDao.queryPressureByDate(mac, dateStr);
    }

    public final void saveManualPressure(final long dataTime, final int value) throws InterruptedException {
        ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<PressureRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository.saveManualPressure.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PressureRepository pressureRepository) {
                invoke2(pressureRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PressureRepository ktxRunOnBgSingleAnother) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                DateUtil dateUtil = new DateUtil(dataTime, true);
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                ktxRunOnBgSingleAnother.pressureManualDao.insert(new PressureManualEntity(deviceAddressNoClear, y_m_d, value, (int) dataTime));
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    public final Map<String, Calendar> calendarPressure(int year, int month) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        DateUtil dateUtil = new DateUtil(year, month, 1);
        for (int i = 1; i < 32; i++) {
            QcPressureDetailDao qcPressureDetailDao = this.pressureDao;
            String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "monthDate.y_M_D");
            if (qcPressureDetailDao.queryPressureByDate(deviceAddressNoClear, y_m_d) != null) {
                String string = getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), "").toString();
                Intrinsics.checkNotNullExpressionValue(string, "getSchemeCalendar(monthD…           \"\").toString()");
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

    /* compiled from: PressureRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PressureRepository getGetInstance() {
            return (PressureRepository) PressureRepository.getInstance$delegate.getValue();
        }
    }
}
