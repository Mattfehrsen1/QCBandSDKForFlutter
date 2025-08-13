package com.qcwireless.qcwatch.ui.base.repository.healthy;

import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.ILargeDataSleepResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.entity.BleSleepDetails;
import com.oudmon.ble.base.communication.req.ReadSleepDetailsReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.ReadSleepDetailsRsp;
import com.oudmon.ble.base.communication.rsp.SleepNewProtoResp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.SleepDetailNewProtocolResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.SleepDetailResp;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepLunchProtocolDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepNewProtocolDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcSleepTotalDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepLunchProtocol;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepNewProtocol;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepTotalHistory;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.util.MapUtils;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.base.view.QSleepBarChart;
import com.qcwireless.qcwatch.ui.base.view.QSleepMonthBarView;
import com.qcwireless.qcwatch.ui.base.view.QSleepWeekBarView;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepLunchBean;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: SleepDetailRepository.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 V2\u00020\u0001:\u0001VB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J!\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ!\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0013H\u0002J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#H\u0002J\u0018\u0010$\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\u0006\u0010%\u001a\u00020\u0006H\u0002J\b\u0010&\u001a\u0004\u0018\u00010\u0013J\b\u0010'\u001a\u0004\u0018\u00010(J\u0010\u0010)\u001a\u0004\u0018\u00010\u00132\u0006\u0010*\u001a\u00020\u0005J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0014\u0010/\u001a\b\u0012\u0004\u0012\u000201002\u0006\u00102\u001a\u00020.J\u000e\u00103\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.J\u0010\u00104\u001a\u0004\u0018\u0001052\u0006\u0010*\u001a\u00020\u0005J\u0014\u00106\u001a\b\u0012\u0004\u0012\u000207002\u0006\u00102\u001a\u00020.J\u001e\u00108\u001a\b\u0012\u0004\u0012\u000205002\u0006\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020.H\u0002J\u0010\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010>\u001a\u00020\u00162\u0006\u0010<\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020\u00162\u0006\u0010<\u001a\u00020?H\u0002J\u0010\u0010A\u001a\u00020\u00162\u0006\u0010<\u001a\u00020?H\u0002J&\u0010B\u001a\u00020\u00162\u0006\u0010C\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00062\f\u0010D\u001a\b\u0012\u0004\u0012\u00020#0EH\u0002J\u001e\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u00062\f\u0010D\u001a\b\u0012\u0004\u0012\u00020#0EH\u0002J/\u0010F\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u00052\u0006\u0010I\u001a\u00020\u00052\f\u0010D\u001a\b\u0012\u0004\u0012\u00020#0EH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010JJ\u001c\u0010K\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u00062\f\u0010D\u001a\b\u0012\u0004\u0012\u00020#0EJ8\u0010L\u001a\u00020\u00132\u0006\u0010H\u001a\u00020\u00052\u0006\u0010I\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u00062\u000e\u0010O\u001a\n\u0012\u0004\u0012\u00020P\u0018\u000100H\u0002J\u001d\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060S0RH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010TJ\u001d\u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060S0RH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010TR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006W"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "", "()V", "historyDate", "", "", "", "sleepDetailDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepDetailDao;", "sleepLunchDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepLunchProtocolDao;", "sleepNewDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepNewProtocolDao;", "sleepTotalDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepTotalDao;", "calcLastSleep", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;", "calcSleepViewData", "currentDay", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepDetail;", "theDayBefore", "deleteLunchSleepData", "", "deleteNewSleepData", "downSleepDetailFromServer", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downSleepDetailNewProtocolFromServer", "merge", "oldEntity", "newEntity", "parseSleepDetail", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/ReadSleepDetailsRsp;", "parseSleepDetailToday", "dayIndex", "queryLastSleep", "queryLastSleepNewProtocol", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepNewProtocol;", "querySleepByDate", "dateStr", "querySleepLunchProtocol", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepLunchBean;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "querySleepMonth", "", "Lcom/qcwireless/qcwatch/ui/base/view/QSleepMonthBarView$SleepDataBean;", "start", "querySleepNewProtocol", "querySleepTotalByDate", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepTotalHistory;", "querySleepWeek", "Lcom/qcwireless/qcwatch/ui/base/view/QSleepWeekBarView$SleepDataBean;", "queryWeekSleepByDate", "dateStart", "dateEnd", "saveDownloadSleepToTotal", "sleepBean", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailNewProtocolResp;", "saveSleepLunchProtocol", "Lcom/oudmon/ble/base/communication/rsp/SleepNewProtoResp;", "saveSleepNewProtocol", "saveSleepToTotal", "syncDeviceSleepDetail", "key", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "syncSleepDetail", TypedValues.CycleType.S_WAVE_OFFSET, "deviceName", "deviceAddress", "(Ljava/lang/String;Ljava/lang/String;Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncTodaySleepDetail", "toSleepDetail", "date", "interval", "sleepDetailList", "Lcom/oudmon/ble/base/communication/entity/BleSleepDetails;", "updateSleepDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSleepDetailToServerNewProtocol", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SleepDetailRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SleepDetailRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SleepDetailRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SleepDetailRepository invoke() {
            return new SleepDetailRepository();
        }
    });
    private final QcSleepDetailDao sleepDetailDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcSleepDetailDao();
    private final QcSleepTotalDao sleepTotalDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcSleepTotalDao();
    private final QcSleepNewProtocolDao sleepNewDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcSleepNewProtocolDao();
    private final QcSleepLunchProtocolDao sleepLunchDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcSleepLunchProtocolDao();
    private Map<String, Integer> historyDate = new LinkedHashMap();

    public final SleepLunchBean querySleepLunchProtocol(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        SleepLunchBean sleepLunchBean = new SleepLunchBean(0, 0, 3, null);
        QcSleepLunchProtocolDao qcSleepLunchProtocolDao = this.sleepLunchDao;
        String y_m_d = dateUtil.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
        SleepLunchProtocol sleepLunchProtocolQueryByDate = qcSleepLunchProtocolDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        if (sleepLunchProtocolQueryByDate != null) {
            sleepLunchBean.setLunchSt(sleepLunchProtocolQueryByDate.getLunchSt());
            sleepLunchBean.setLunchEt(sleepLunchProtocolQueryByDate.getLunchEt());
        }
        return sleepLunchBean;
    }

    public final SleepViewBean querySleepNewProtocol(DateUtil dateUtil) {
        int d;
        int d2;
        int d3;
        Iterator it;
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        SleepViewBean sleepViewBean = new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null);
        QcSleepNewProtocolDao qcSleepNewProtocolDao = this.sleepNewDao;
        String y_m_d = dateUtil.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
        SleepNewProtocol sleepNewProtocolQueryByDate = qcSleepNewProtocolDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        if (sleepNewProtocolQueryByDate != null) {
            String detail = sleepNewProtocolQueryByDate.getDetail();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<SleepNewProtoResp.DetailBean>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$querySleepNewProtocol$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List<SleepNewProtoResp.DetailBean> list = (List) jsonAdapterAdapter.fromJson(detail);
            ArrayList arrayList = new ArrayList();
            int d4 = 0;
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                SleepNewProtoResp.DetailBean detailBean = new SleepNewProtoResp.DetailBean();
                int i = 0;
                for (SleepNewProtoResp.DetailBean detailBean2 : list) {
                    int i2 = i + 1;
                    if (i == 0) {
                        detailBean = detailBean2;
                    }
                    if (i2 < list.size()) {
                        if (detailBean2.getT() == ((SleepNewProtoResp.DetailBean) list.get(i2)).getT()) {
                            detailBean.setD(((SleepNewProtoResp.DetailBean) list.get(i2)).getD() + ((SleepNewProtoResp.DetailBean) list.get(i)).getD());
                        } else {
                            arrayList2.add(detailBean);
                            detailBean = (SleepNewProtoResp.DetailBean) list.get(i2);
                        }
                    } else {
                        arrayList2.add(detailBean);
                    }
                    i = i2;
                }
                Iterator it2 = arrayList2.iterator();
                d = 0;
                d2 = 0;
                d3 = 0;
                int i3 = 0;
                while (it2.hasNext()) {
                    int i4 = i3 + 1;
                    SleepNewProtoResp.DetailBean detailBean3 = (SleepNewProtoResp.DetailBean) it2.next();
                    int t = detailBean3.getT();
                    if (t == 2) {
                        d += detailBean3.getD();
                    } else if (t == 3) {
                        d4 += detailBean3.getD();
                    } else if (t == 4) {
                        d3 += detailBean3.getD();
                    } else if (t == 5) {
                        d2 += detailBean3.getD();
                    }
                    QSleepBarChart.SleepDataBean sleepDataBean = new QSleepBarChart.SleepDataBean();
                    int t2 = detailBean3.getT();
                    if (t2 == 0 || t2 == 1) {
                        sleepDataBean.setType(4);
                    } else if (t2 == 2) {
                        sleepDataBean.setType(2);
                    } else if (t2 == 3) {
                        sleepDataBean.setType(1);
                    } else if (t2 == 4) {
                        sleepDataBean.setType(5);
                    } else if (t2 == 5) {
                        sleepDataBean.setType(3);
                    }
                    if (i3 == 0) {
                        sleepDataBean.setSleepStart(sleepNewProtocolQueryByDate.getSt());
                        it = it2;
                        sleepDataBean.setSleepEnd(sleepNewProtocolQueryByDate.getSt() + (detailBean3.getD() * 60));
                        arrayList.add(sleepDataBean);
                    } else {
                        it = it2;
                        QSleepBarChart.SleepDataBean sleepDataBean2 = (QSleepBarChart.SleepDataBean) arrayList.get(i3 - 1);
                        sleepDataBean.setSleepStart(sleepDataBean2.getSleepEnd());
                        sleepDataBean.setSleepEnd(sleepDataBean2.getSleepEnd() + (detailBean3.getD() * 60));
                        arrayList.add(sleepDataBean);
                    }
                    i3 = i4;
                    it2 = it;
                }
            } else {
                d = 0;
                d2 = 0;
                d3 = 0;
            }
            int i5 = d4 * 60;
            int i6 = d * 60;
            int i7 = d2 * 60;
            int i8 = d3 * 60;
            if (i8 > 0) {
                UserConfig.INSTANCE.getInstance().setSupportRem(true);
                UserConfig.INSTANCE.getInstance().save();
            }
            sleepViewBean.setStartTime(sleepNewProtocolQueryByDate.getSt());
            sleepViewBean.setEndTime(sleepNewProtocolQueryByDate.getEt());
            sleepViewBean.setTotalSleep(i5 + i6 + i7 + i8);
            sleepViewBean.setDeepSleep(i5);
            sleepViewBean.setLightSleep(i6);
            sleepViewBean.setAwakeSleep(i7);
            sleepViewBean.setRapidSleep(i8);
            sleepViewBean.setData(arrayList);
            return sleepViewBean;
        }
        return new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null);
    }

    public final Object syncSleepDetail(String str, String str2, final BaseDeviceResult<ReadSleepDetailsRsp> baseDeviceResult, Continuation<? super Unit> continuation) throws InterruptedException {
        if (!BleOperateManager.getInstance().isConnected()) {
            return Unit.INSTANCE;
        }
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            syncSleepDetail(255, baseDeviceResult);
            return Unit.INSTANCE;
        }
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.flow(new C03872(str2, null)), Dispatchers.getIO()), new C03883(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.syncSleepDetail.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Map<String, Integer>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Map<String, Integer> map, Continuation<? super Unit> continuation2) {
                DateUtil dateUtil = new DateUtil();
                dateUtil.addDay(-1);
                Map map2 = SleepDetailRepository.this.historyDate;
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                map2.put(y_m_d, Boxing.boxInt(1));
                SleepDetailRepository sleepDetailRepository = SleepDetailRepository.this;
                Map<String, Integer> mapSortMapByValue = MapUtils.sortMapByValue(sleepDetailRepository.historyDate);
                Intrinsics.checkNotNullExpressionValue(mapSortMapByValue, "sortMapByValue(historyDate)");
                sleepDetailRepository.historyDate = mapSortMapByValue;
                Iterator it = SleepDetailRepository.this.historyDate.entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    SleepDetailRepository.this.syncDeviceSleepDetail((String) entry.getKey(), ((Number) entry.getValue()).intValue(), baseDeviceResult);
                } else {
                    baseDeviceResult.result(0, new ReadSleepDetailsRsp());
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$syncSleepDetail$2", f = "SleepDetailRepository.kt", i = {}, l = {217}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$syncSleepDetail$2, reason: invalid class name and case insensitive filesystem */
    static final class C03872 extends SuspendLambda implements Function2<FlowCollector<? super Map<String, ? extends Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $deviceAddress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03872(String str, Continuation<? super C03872> continuation) {
            super(2, continuation);
            this.$deviceAddress = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03872 c03872 = SleepDetailRepository.this.new C03872(this.$deviceAddress, continuation);
            c03872.L$0 = obj;
            return c03872;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03872) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                for (int i2 = 1; i2 < 7; i2++) {
                    DateUtil dateUtil = new DateUtil();
                    dateUtil.addDay(-i2);
                    Map map = SleepDetailRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    map.put(y_m_d, Boxing.boxInt(i2));
                }
                List<SleepDetail> listQueryDaysHistoryDate = SleepDetailRepository.this.sleepDetailDao.queryDaysHistoryDate(this.$deviceAddress);
                if (listQueryDaysHistoryDate != null) {
                    for (SleepDetail sleepDetail : listQueryDaysHistoryDate) {
                        if (new DateUtil(sleepDetail.getLastSyncTime(), true).isToday()) {
                            SleepDetailRepository.this.historyDate.remove(sleepDetail.getDateStr());
                        }
                    }
                }
                XLog.i("sync sleep detail");
                XLog.i(SleepDetailRepository.this.historyDate);
                this.label = 1;
                if (flowCollector.emit(SleepDetailRepository.this.historyDate, this) == coroutine_suspended) {
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$syncSleepDetail$3", f = "SleepDetailRepository.kt", i = {}, l = {225}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$syncSleepDetail$3, reason: invalid class name and case insensitive filesystem */
    static final class C03883 extends SuspendLambda implements Function3<FlowCollector<? super Map<String, ? extends Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03883(Continuation<? super C03883> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03883 c03883 = SleepDetailRepository.this.new C03883(continuation);
            c03883.L$0 = flowCollector;
            c03883.L$1 = th;
            return c03883.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                for (int i2 = 1; i2 < 7; i2++) {
                    DateUtil dateUtil = new DateUtil();
                    dateUtil.addDay(-i2);
                    Map map = SleepDetailRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    map.put(y_m_d, Boxing.boxInt(i2));
                }
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(SleepDetailRepository.this.historyDate, this) == coroutine_suspended) {
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

    public final void syncTodaySleepDetail(final int dayIndex, final BaseDeviceResult<ReadSleepDetailsRsp> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            syncSleepDetail(0, result);
        } else {
            CommandHandle.getInstance().executeReqCmd(new ReadSleepDetailsReq(dayIndex, 0, 95), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    SleepDetailRepository.m299syncTodaySleepDetail$lambda0(this.f$0, dayIndex, result, (ReadSleepDetailsRsp) baseRspCmd);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodaySleepDetail$lambda-0, reason: not valid java name */
    public static final void m299syncTodaySleepDetail$lambda0(SleepDetailRepository this$0, int i, BaseDeviceResult result, ReadSleepDetailsRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.parseSleepDetailToday(it, i);
            if (i == 1) {
                result.result(0, it);
            }
            if (i == 0) {
                this$0.syncTodaySleepDetail(1, result);
                return;
            }
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        result.result(-1, it);
    }

    private final void syncSleepDetail(int offset, BaseDeviceResult<ReadSleepDetailsRsp> result) throws InterruptedException {
        LargeDataHandler.getInstance().syncSleepList(offset, new ILargeDataSleepResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ILargeDataSleepResponse
            public final void sleepData(SleepNewProtoResp sleepNewProtoResp) {
                SleepDetailRepository.m297syncSleepDetail$lambda1(this.f$0, sleepNewProtoResp);
            }
        }, new ILargeDataSleepResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ILargeDataSleepResponse
            public final void sleepData(SleepNewProtoResp sleepNewProtoResp) {
                SleepDetailRepository.m298syncSleepDetail$lambda2(this.f$0, sleepNewProtoResp);
            }
        });
        result.result(0, new ReadSleepDetailsRsp());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncSleepDetail$lambda-1, reason: not valid java name */
    public static final void m297syncSleepDetail$lambda1(SleepDetailRepository this$0, final SleepNewProtoResp sleepNewProtoResp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$syncSleepDetail$5$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                invoke2(sleepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SleepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                SleepNewProtoResp it = sleepNewProtoResp;
                if (it == null) {
                    ktxRunOnBgSingle.deleteNewSleepData();
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ktxRunOnBgSingle.saveSleepNewProtocol(it);
                SleepNewProtoResp it2 = sleepNewProtoResp;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                ktxRunOnBgSingle.saveSleepToTotal(it2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncSleepDetail$lambda-2, reason: not valid java name */
    public static final void m298syncSleepDetail$lambda2(SleepDetailRepository this$0, final SleepNewProtoResp sleepNewProtoResp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$syncSleepDetail$6$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                invoke2(sleepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SleepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                XLog.i(new Gson().toJson(sleepNewProtoResp));
                SleepNewProtoResp it = sleepNewProtoResp;
                if (it == null) {
                    ktxRunOnBgSingle.deleteLunchSleepData();
                } else {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    ktxRunOnBgSingle.saveSleepLunchProtocol(it);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveSleepNewProtocol(SleepNewProtoResp sleepBean) {
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = new DateUtil(sleepBean.getEt(), true).getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(sleepBean.et.toLong(), true).y_M_D");
        List<SleepNewProtoResp.DetailBean> list = sleepBean.getList();
        Intrinsics.checkNotNullExpressionValue(list, "sleepBean.list");
        this.sleepNewDao.insert(new SleepNewProtocol(deviceAddressNoClear, y_m_d, MoshiUtilsKt.toJson(list), sleepBean.getSt(), sleepBean.getEt(), false, new DateUtil().getUnixTimestamp()));
    }

    public final SleepViewBean calcLastSleep() {
        if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            if (INSTANCE.getGetInstance().queryLastSleepNewProtocol() != null) {
                return querySleepNewProtocol(new DateUtil(r0.getEt(), true));
            }
        } else {
            Companion companion = INSTANCE;
            SleepDetail sleepDetailQueryLastSleep = companion.getGetInstance().queryLastSleep();
            if (sleepDetailQueryLastSleep != null) {
                DateUtil dateUtil = new DateUtil(DateUtil.String2Date("yyyy-MM-dd", sleepDetailQueryLastSleep.getDateStr()));
                SleepDetailRepository getInstance = companion.getGetInstance();
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
                SleepDetail sleepDetailQuerySleepByDate = getInstance.querySleepByDate(y_m_d);
                DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                dateUtil2.addDay(-1);
                SleepDetailRepository getInstance2 = companion.getGetInstance();
                String y_m_d2 = dateUtil2.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "yesDate.y_M_D");
                SleepDetail sleepDetailQuerySleepByDate2 = getInstance2.querySleepByDate(y_m_d2);
                if (sleepDetailQuerySleepByDate2 == null) {
                    sleepDetailQuerySleepByDate2 = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                }
                if (sleepDetailQuerySleepByDate != null) {
                    SleepViewBean sleepViewBeanCalcSleepViewData = companion.getGetInstance().calcSleepViewData(sleepDetailQuerySleepByDate, sleepDetailQuerySleepByDate2);
                    if (sleepViewBeanCalcSleepViewData.getTotalSleep() > 0) {
                        return sleepViewBeanCalcSleepViewData;
                    }
                }
            }
        }
        return new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveSleepLunchProtocol(SleepNewProtoResp sleepBean) {
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = new DateUtil(sleepBean.getLunchEt(), true).getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(sleepBean.lunchEt.toLong(), true).y_M_D");
        List<SleepNewProtoResp.DetailBean> list = sleepBean.getList();
        Intrinsics.checkNotNullExpressionValue(list, "sleepBean.list");
        this.sleepLunchDao.insert(new SleepLunchProtocol(deviceAddressNoClear, y_m_d, MoshiUtilsKt.toJson(list), sleepBean.getLunchSt(), sleepBean.getLunchEt(), false, new DateUtil().getUnixTimestamp()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteLunchSleepData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.deleteLunchSleepData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                invoke2(sleepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SleepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                QcSleepLunchProtocolDao qcSleepLunchProtocolDao = ktxRunOnBgSingle.sleepLunchDao;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                SleepLunchProtocol sleepLunchProtocolQueryByDate = qcSleepLunchProtocolDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                if (sleepLunchProtocolQueryByDate != null) {
                    ktxRunOnBgSingle.sleepLunchDao.delete(sleepLunchProtocolQueryByDate);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteNewSleepData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.deleteNewSleepData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                invoke2(sleepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SleepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                QcSleepNewProtocolDao qcSleepNewProtocolDao = ktxRunOnBgSingle.sleepNewDao;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                SleepNewProtocol sleepNewProtocolQueryByDate = qcSleepNewProtocolDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                if (sleepNewProtocolQueryByDate != null) {
                    ktxRunOnBgSingle.sleepNewDao.delete(sleepNewProtocolQueryByDate);
                }
                QcSleepTotalDao qcSleepTotalDao = ktxRunOnBgSingle.sleepTotalDao;
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                SleepTotalHistory sleepTotalHistoryQueryTotalSleepByAddressAndDate = qcSleepTotalDao.queryTotalSleepByAddressAndDate(deviceAddressNoClear, y_m_d2);
                if (sleepTotalHistoryQueryTotalSleepByAddressAndDate != null) {
                    ktxRunOnBgSingle.sleepTotalDao.delete(sleepTotalHistoryQueryTotalSleepByAddressAndDate);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveSleepToTotal(SleepNewProtoResp sleepBean) {
        List<SleepNewProtoResp.DetailBean> list = sleepBean.getList();
        Intrinsics.checkNotNullExpressionValue(list, "sleepBean.list");
        int d = 0;
        int d2 = 0;
        int d3 = 0;
        int d4 = 0;
        for (SleepNewProtoResp.DetailBean detailBean : list) {
            int t = detailBean.getT();
            if (t == 2) {
                d2 += detailBean.getD();
            } else if (t == 3) {
                d += detailBean.getD();
            } else if (t == 4) {
                d4 += detailBean.getD();
            } else if (t == 5) {
                d3 += detailBean.getD();
            }
        }
        int i = d * 60;
        int i2 = d2 * 60;
        int i3 = d3 * 60;
        int i4 = d4 * 60;
        if (i4 > 0) {
            UserConfig.INSTANCE.getInstance().setSupportRem(true);
            UserConfig.INSTANCE.getInstance().save();
        }
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = new DateUtil(sleepBean.getEt(), true).getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(sleepBean.et.toLong(), true).y_M_D");
        this.sleepTotalDao.insert(new SleepTotalHistory(deviceAddressNoClear, y_m_d, i + i2 + i3 + i4, i, i2, i4, i3, sleepBean.getSt(), sleepBean.getEt(), (int) new DateUtil(sleepBean.getEt(), true).getZeroTime()));
    }

    public final SleepDetail querySleepByDate(String dateStr) {
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return this.sleepDetailDao.queryByDate(dateStr, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final SleepDetail queryLastSleep() {
        return this.sleepDetailDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final SleepNewProtocol queryLastSleepNewProtocol() {
        return this.sleepNewDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    private final List<SleepTotalHistory> queryWeekSleepByDate(DateUtil dateStart, DateUtil dateEnd) {
        return this.sleepTotalDao.queryByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), String.valueOf(dateStart.getZeroTime()), String.valueOf(dateEnd.getUnixTimestamp()));
    }

    public final SleepTotalHistory querySleepTotalByDate(String dateStr) {
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return this.sleepTotalDao.queryTotalSleepByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), dateStr);
    }

    public final List<QSleepWeekBarView.SleepDataBean> querySleepWeek(DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        DateUtil dateUtil2 = new DateUtil(start.getZeroTime() + 86399, true);
        dateUtil2.addDay(6);
        List<SleepTotalHistory> listQueryWeekSleepByDate = queryWeekSleepByDate(start, dateUtil2);
        for (int i = 0; i < 7; i++) {
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "temp.y_M_D");
            linkedHashMap.put(y_m_d, new QSleepWeekBarView.SleepDataBean(dateUtil.getUnixTimestamp(), 0, 0, 0, 0, 0.0f, 0.0f, 0.0f));
            dateUtil.addDay(1);
        }
        for (SleepTotalHistory sleepTotalHistory : listQueryWeekSleepByDate) {
            int totalSleep = sleepTotalHistory.getTotalSleep() / 60;
            int deepSleep = sleepTotalHistory.getDeepSleep() / 60;
            int lightSleep = sleepTotalHistory.getLightSleep() / 60;
            int rapidSleep = sleepTotalHistory.getRapidSleep() / 60;
            int awake = sleepTotalHistory.getAwake() / 60;
            float f = totalSleep;
            QSleepWeekBarView.SleepDataBean sleepDataBean = new QSleepWeekBarView.SleepDataBean(sleepTotalHistory.getUnixTime(), totalSleep, deepSleep, lightSleep, awake, rapidSleep, (deepSleep * 1.0f) / f, (lightSleep * 1.0f) / f, (awake * 1.0f) / f, (rapidSleep * 1.0f) / f);
            String y_m_d2 = new DateUtil(sleepTotalHistory.getUnixTime(), true).getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil(item.unixTime.toLong(), true).y_M_D");
            linkedHashMap.put(y_m_d2, sleepDataBean);
        }
        return new ArrayList(linkedHashMap.values());
    }

    public final List<QSleepMonthBarView.SleepDataBean> querySleepMonth(DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int daysOfMonth = DateUtil.getDaysOfMonth(start.toDate());
        boolean z = true;
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        DateUtil dateUtil2 = new DateUtil(start.getZeroTime() + 86399, true);
        dateUtil2.addDay(daysOfMonth - 1);
        List<SleepTotalHistory> listQueryWeekSleepByDate = queryWeekSleepByDate(start, dateUtil2);
        for (int i = 0; i < daysOfMonth; i++) {
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "temp.y_M_D");
            linkedHashMap.put(y_m_d, new QSleepMonthBarView.SleepDataBean(dateUtil.getUnixTimestamp(), 0, 0, 0, 0, 0, 0.0f, 0.0f, 0.0f, 0.0f));
            dateUtil.addDay(1);
        }
        for (SleepTotalHistory sleepTotalHistory : listQueryWeekSleepByDate) {
            int totalSleep = sleepTotalHistory.getTotalSleep() / 60;
            int deepSleep = sleepTotalHistory.getDeepSleep() / 60;
            int lightSleep = sleepTotalHistory.getLightSleep() / 60;
            int awake = sleepTotalHistory.getAwake() / 60;
            int rapidSleep = sleepTotalHistory.getRapidSleep() / 60;
            if (sleepTotalHistory.getRapidSleep() > 0) {
                UserConfig.INSTANCE.getInstance().setSupportRem(z);
                UserConfig.INSTANCE.getInstance().save();
            }
            float f = totalSleep;
            QSleepMonthBarView.SleepDataBean sleepDataBean = new QSleepMonthBarView.SleepDataBean(sleepTotalHistory.getUnixTime(), totalSleep, deepSleep, lightSleep, awake, rapidSleep, (deepSleep * 1.0f) / f, (lightSleep * 1.0f) / f, (awake * 1.0f) / f, (rapidSleep * 1.0f) / f);
            String y_m_d2 = new DateUtil(sleepTotalHistory.getUnixTime(), true).getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil(item.unixTime.toLong(), true).y_M_D");
            linkedHashMap.put(y_m_d2, sleepDataBean);
            z = true;
        }
        return new ArrayList(linkedHashMap.values());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncDeviceSleepDetail(final String key, int dayIndex, final BaseDeviceResult<ReadSleepDetailsRsp> result) {
        CommandHandle.getInstance().executeReqCmd(new ReadSleepDetailsReq(dayIndex, 0, 95), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                SleepDetailRepository.m296syncDeviceSleepDetail$lambda4(this.f$0, key, result, (ReadSleepDetailsRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncDeviceSleepDetail$lambda-4, reason: not valid java name */
    public static final void m296syncDeviceSleepDetail$lambda4(SleepDetailRepository this$0, String key, BaseDeviceResult result, ReadSleepDetailsRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            ArrayList<BleSleepDetails> list = it.getBleSleepDetailses();
            Intrinsics.checkNotNullExpressionValue(list, "list");
            if (!list.isEmpty()) {
                this$0.historyDate.remove(new DateUtil(list.get(0).getYear(), list.get(0).getMonth(), list.get(0).getDay()).getY_M_D());
            } else {
                this$0.historyDate.remove(key);
            }
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.parseSleepDetail(it);
            if (!this$0.historyDate.isEmpty()) {
                Iterator<Map.Entry<String, Integer>> it2 = this$0.historyDate.entrySet().iterator();
                if (it2.hasNext()) {
                    Map.Entry<String, Integer> next = it2.next();
                    this$0.syncDeviceSleepDetail(next.getKey(), next.getValue().intValue(), result);
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

    /* JADX WARN: Removed duplicated region for block: B:15:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean calcSleepViewData(com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail r30, com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail r31) {
        /*
            Method dump skipped, instructions count: 1055
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.calcSleepViewData(com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail, com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail):com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean");
    }

    private final void parseSleepDetailToday(final ReadSleepDetailsRsp resultEntity, final int dayIndex) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.parseSleepDetailToday.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                invoke2(sleepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SleepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList<BleSleepDetails> bleSleepDetailses = resultEntity.getBleSleepDetailses();
                Intrinsics.checkNotNullExpressionValue(bleSleepDetailses, "resultEntity.bleSleepDetailses");
                if (bleSleepDetailses.size() > 0) {
                    DateUtil dateUtil = new DateUtil(bleSleepDetailses.get(0).getYear(), bleSleepDetailses.get(0).getMonth(), bleSleepDetailses.get(0).getDay());
                    if (dateUtil.getZeroTime() > new DateUtil().getZeroTime()) {
                        return;
                    }
                    QcSleepDetailDao qcSleepDetailDao = ktxRunOnBgSingle.sleepDetailDao;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "dataDate.y_M_D");
                    SleepDetail sleepDetailQueryByDate = qcSleepDetailDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                    String deviceNameNoClear = UserConfig.INSTANCE.getInstance().getDeviceNameNoClear();
                    String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                    String y_m_d2 = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d2, "dataDate.y_M_D");
                    SleepDetail sleepDetail = ktxRunOnBgSingle.toSleepDetail(deviceNameNoClear, deviceAddressNoClear, y_m_d2, TypedValues.Custom.TYPE_INT, bleSleepDetailses);
                    if (DateUtil.isSameDay(dateUtil.toDate(), new Date())) {
                        if (sleepDetailQueryByDate != null) {
                            sleepDetail = ktxRunOnBgSingle.merge(sleepDetailQueryByDate, sleepDetail);
                        }
                        ktxRunOnBgSingle.sleepDetailDao.insert(sleepDetail);
                    } else {
                        ktxRunOnBgSingle.sleepDetailDao.insert(sleepDetail);
                    }
                    DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                    dateUtil2.addDay(-1);
                    String y_m_d3 = dateUtil2.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d3, "yesDate.y_M_D");
                    SleepDetail sleepDetailQuerySleepByDate = ktxRunOnBgSingle.querySleepByDate(y_m_d3);
                    if (sleepDetailQuerySleepByDate == null) {
                        sleepDetailQuerySleepByDate = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                    }
                    ktxRunOnBgSingle.calcSleepViewData(sleepDetail, sleepDetailQuerySleepByDate);
                    return;
                }
                if (dayIndex == 0) {
                    ThreadExtKt.ktxRunOnBgSingle(ktxRunOnBgSingle, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.parseSleepDetailToday.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                            invoke2(sleepDetailRepository);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SleepDetailRepository ktxRunOnBgSingle2) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle2, "$this$ktxRunOnBgSingle");
                            QcSleepDetailDao qcSleepDetailDao2 = ktxRunOnBgSingle2.sleepDetailDao;
                            String y_m_d4 = new DateUtil().getY_M_D();
                            Intrinsics.checkNotNullExpressionValue(y_m_d4, "DateUtil().y_M_D");
                            SleepDetail sleepDetailQueryByDate2 = qcSleepDetailDao2.queryByDate(y_m_d4, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                            if (sleepDetailQueryByDate2 != null) {
                                ktxRunOnBgSingle2.sleepDetailDao.delete(sleepDetailQueryByDate2);
                            }
                            QcSleepTotalDao qcSleepTotalDao = ktxRunOnBgSingle2.sleepTotalDao;
                            String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                            String y_m_d5 = new DateUtil().getY_M_D();
                            Intrinsics.checkNotNullExpressionValue(y_m_d5, "DateUtil().y_M_D");
                            SleepTotalHistory sleepTotalHistoryQueryTotalSleepByAddressAndDate = qcSleepTotalDao.queryTotalSleepByAddressAndDate(deviceAddressNoClear2, y_m_d5);
                            if (sleepTotalHistoryQueryTotalSleepByAddressAndDate != null) {
                                ktxRunOnBgSingle2.sleepTotalDao.delete(sleepTotalHistoryQueryTotalSleepByAddressAndDate);
                            }
                        }
                    });
                }
            }
        });
    }

    private final void parseSleepDetail(final ReadSleepDetailsRsp resultEntity) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<SleepDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.parseSleepDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SleepDetailRepository sleepDetailRepository) {
                invoke2(sleepDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SleepDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList<BleSleepDetails> bleSleepDetailses = resultEntity.getBleSleepDetailses();
                Intrinsics.checkNotNullExpressionValue(bleSleepDetailses, "resultEntity.bleSleepDetailses");
                if (bleSleepDetailses.size() > 0) {
                    DateUtil dateUtil = new DateUtil(bleSleepDetailses.get(0).getYear(), bleSleepDetailses.get(0).getMonth(), bleSleepDetailses.get(0).getDay());
                    if (dateUtil.getZeroTime() > new DateUtil().getZeroTime()) {
                        return;
                    }
                    QcSleepDetailDao qcSleepDetailDao = ktxRunOnBgSingle.sleepDetailDao;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "dataDate.y_M_D");
                    SleepDetail sleepDetailQueryByDate = qcSleepDetailDao.queryByDate(y_m_d, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                    String deviceNameNoClear = UserConfig.INSTANCE.getInstance().getDeviceNameNoClear();
                    String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                    String y_m_d2 = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d2, "dataDate.y_M_D");
                    SleepDetail sleepDetail = ktxRunOnBgSingle.toSleepDetail(deviceNameNoClear, deviceAddressNoClear, y_m_d2, TypedValues.Custom.TYPE_INT, bleSleepDetailses);
                    if (DateUtil.isSameDay(dateUtil.toDate(), new Date())) {
                        if (sleepDetailQueryByDate != null) {
                            sleepDetail = ktxRunOnBgSingle.merge(sleepDetailQueryByDate, sleepDetail);
                        }
                        ktxRunOnBgSingle.sleepDetailDao.insert(sleepDetail);
                    } else {
                        ktxRunOnBgSingle.sleepDetailDao.insert(sleepDetail);
                    }
                    DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                    dateUtil2.addDay(-1);
                    String y_m_d3 = dateUtil2.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d3, "yesDate.y_M_D");
                    SleepDetail sleepDetailQuerySleepByDate = ktxRunOnBgSingle.querySleepByDate(y_m_d3);
                    if (sleepDetailQuerySleepByDate == null) {
                        sleepDetailQuerySleepByDate = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                    }
                    ktxRunOnBgSingle.calcSleepViewData(sleepDetail, sleepDetailQuerySleepByDate);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SleepDetail toSleepDetail(String deviceName, String deviceAddress, String date, int interval, List<? extends BleSleepDetails> sleepDetailList) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        if (sleepDetailList != null) {
            for (BleSleepDetails bleSleepDetails : sleepDetailList) {
                int[] sleepQualities = bleSleepDetails.getSleepQualities();
                sparseIntArray.put(bleSleepDetails.getTimeIndex(), (sleepQualities[1] * 100000) + (sleepQualities[2] * 1000) + sleepQualities[3]);
            }
        }
        int size = sparseIntArray.size();
        int[] iArr = new int[size];
        int size2 = sparseIntArray.size();
        for (int i = 0; i < size2; i++) {
            iArr[i] = sparseIntArray.keyAt(i);
        }
        Arrays.sort(iArr);
        String strIntArrayToString = StringUtilsKt.intArrayToString(iArr);
        int[] iArr2 = new int[sparseIntArray.size()];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = sparseIntArray.get(iArr[i2]);
        }
        return new SleepDetail(deviceAddress, date, interval, strIntArrayToString, StringUtilsKt.intArrayToString(iArr2), false, new DateUtil().getUnixTimestamp());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SleepDetail merge(SleepDetail oldEntity, SleepDetail newEntity) {
        int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(oldEntity.getIndex_str());
        int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(oldEntity.getQuality());
        int[] iArrStringToIntArray3 = StringUtilsKt.stringToIntArray(newEntity.getIndex_str());
        int[] iArrStringToIntArray4 = StringUtilsKt.stringToIntArray(newEntity.getQuality());
        SparseIntArray sparseIntArray = new SparseIntArray();
        int length = iArrStringToIntArray.length;
        for (int i = 0; i < length; i++) {
            sparseIntArray.put(iArrStringToIntArray[i], iArrStringToIntArray2[i]);
        }
        int length2 = iArrStringToIntArray3.length;
        for (int i2 = 0; i2 < length2; i2++) {
            sparseIntArray.put(iArrStringToIntArray3[i2], iArrStringToIntArray4[i2]);
        }
        int size = sparseIntArray.size();
        int[] iArr = new int[size];
        int size2 = sparseIntArray.size();
        for (int i3 = 0; i3 < size2; i3++) {
            iArr[i3] = sparseIntArray.keyAt(i3);
        }
        Arrays.sort(iArr);
        int[] iArr2 = new int[sparseIntArray.size()];
        for (int i4 = 0; i4 < size; i4++) {
            iArr2[i4] = sparseIntArray.get(iArr[i4]);
        }
        oldEntity.setIndex_str(StringUtilsKt.intArrayToString(iArr));
        oldEntity.setQuality(StringUtilsKt.intArrayToString(iArr2));
        return oldEntity;
    }

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$2", f = "SleepDetailRepository.kt", i = {0, 0, 1}, l = {973, 973, 979}, m = "invokeSuspend", n = {"$this$flow", "notUpList", "$this$flow"}, s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03902 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C03902(Continuation<? super C03902> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03902 c03902 = SleepDetailRepository.this.new C03902(continuation);
            c03902.L$0 = obj;
            return c03902;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03902) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00fe A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instructions count: 281
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.C03902.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$2$1", f = "SleepDetailRepository.kt", i = {}, l = {974}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            final /* synthetic */ List<SleepDetail> $notUpList;
            int label;
            final /* synthetic */ SleepDetailRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, List<SleepDetail> list, SleepDetailRepository sleepDetailRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$notUpList = list;
                this.this$0 = sleepDetailRepository;
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
                for (SleepDetail sleepDetail : this.$notUpList) {
                    sleepDetail.setSync(true);
                    this.this$0.sleepDetailDao.insert(sleepDetail);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$2$2", f = "SleepDetailRepository.kt", i = {}, l = {980}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00632 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00632(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00632> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00632 c00632 = new C00632(this.$$this$flow, continuation);
                c00632.I$0 = i;
                return c00632.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateSleepDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03902(null)), new C03913(null)), Dispatchers.getIO()), new C03924(null));
    }

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$3", f = "SleepDetailRepository.kt", i = {}, l = {984}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03913 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03913(Continuation<? super C03913> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03913 c03913 = new C03913(continuation);
            c03913.L$0 = obj;
            return c03913;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03913) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$4", f = "SleepDetailRepository.kt", i = {}, l = {987}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03924 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03924(Continuation<? super C03924> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03924 c03924 = new C03924(continuation);
            c03924.L$0 = flowCollector;
            c03924.L$1 = th;
            return c03924.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$2", f = "SleepDetailRepository.kt", i = {0, 0, 1}, l = {1010, 1010, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW}, m = "invokeSuspend", n = {"$this$flow", "notUpList", "$this$flow"}, s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$2, reason: invalid class name and case insensitive filesystem */
    static final class C03932 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C03932(Continuation<? super C03932> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03932 c03932 = SleepDetailRepository.this.new C03932(continuation);
            c03932.L$0 = obj;
            return c03932;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03932) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0115 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instructions count: 304
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.C03932.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$2$1", f = "SleepDetailRepository.kt", i = {}, l = {1011}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            final /* synthetic */ List<SleepNewProtocol> $notUpList;
            int label;
            final /* synthetic */ SleepDetailRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, List<SleepNewProtocol> list, SleepDetailRepository sleepDetailRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$notUpList = list;
                this.this$0 = sleepDetailRepository;
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
                for (SleepNewProtocol sleepNewProtocol : this.$notUpList) {
                    sleepNewProtocol.setSync(true);
                    this.this$0.sleepNewDao.insert(sleepNewProtocol);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$2$2", f = "SleepDetailRepository.kt", i = {}, l = {PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00642 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00642(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00642> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00642 c00642 = new C00642(this.$$this$flow, continuation);
                c00642.I$0 = i;
                return c00642.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateSleepDetailToServerNewProtocol(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03932(null)), new C03943(null)), Dispatchers.getIO()), new C03954(null));
    }

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$3", f = "SleepDetailRepository.kt", i = {}, l = {PointerIconCompat.TYPE_GRABBING}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$3, reason: invalid class name and case insensitive filesystem */
    static final class C03943 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03943(Continuation<? super C03943> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03943 c03943 = new C03943(continuation);
            c03943.L$0 = obj;
            return c03943;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03943) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$4", f = "SleepDetailRepository.kt", i = {}, l = {1024}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$updateSleepDetailToServerNewProtocol$4, reason: invalid class name and case insensitive filesystem */
    static final class C03954 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03954(Continuation<? super C03954> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03954 c03954 = new C03954(continuation);
            c03954.L$0 = flowCollector;
            c03954.L$1 = th;
            return c03954.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2", f = "SleepDetailRepository.kt", i = {0, 1}, l = {1030, 1030, 1032}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SleepDetailResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SleepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SleepDetailResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SleepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
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
                java.lang.Object r1 = r6.downSleepDetail(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$1", f = "SleepDetailRepository.kt", i = {}, l = {1031}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends SleepDetailResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<SleepDetailResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<SleepDetailResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends SleepDetailResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<SleepDetailResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<SleepDetailResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$2", f = "SleepDetailRepository.kt", i = {}, l = {1033}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00602 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<SleepDetailResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00602(FlowCollector<? super NetState<List<SleepDetailResp>>> flowCollector, Continuation<? super C00602> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00602 c00602 = new C00602(this.$$this$flow, continuation);
                c00602.I$0 = i;
                return c00602.invokeSuspend(Unit.INSTANCE);
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

    public final Object downSleepDetailFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.downSleepDetailFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<SleepDetailResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<SleepDetailResp>> netState, Continuation<? super Unit> continuation2) {
                List<SleepDetailResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (SleepDetailResp sleepDetailResp : listIsSuccess) {
                            String deviceId = sleepDetailResp.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            String date = sleepDetailResp.getDate();
                            Intrinsics.checkNotNull(date);
                            SleepDetailRepository.this.sleepDetailDao.insert(new SleepDetail(deviceId, date, sleepDetailResp.getIntervar(), StringUtilsKt.intListToString(sleepDetailResp.getIndexs()), StringUtilsKt.intListToString(sleepDetailResp.getQualitys()), true, new DateUtil().getUnixTimestamp()));
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$3", f = "SleepDetailRepository.kt", i = {}, l = {1036}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SleepDetailResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SleepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SleepDetailResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SleepDetailResp>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$4", f = "SleepDetailRepository.kt", i = {}, l = {1039}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends SleepDetailResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SleepDetailResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SleepDetailResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SleepDetailResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailNewProtocolResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2", f = "SleepDetailRepository.kt", i = {0, 1}, l = {1066, 1066, 1068}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03812 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SleepDetailNewProtocolResp>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $lastSyncId;
        final /* synthetic */ long $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03812(long j, long j2, Continuation<? super C03812> continuation) {
            super(2, continuation);
            this.$uid = j;
            this.$lastSyncId = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03812 c03812 = new C03812(this.$uid, this.$lastSyncId, continuation);
            c03812.L$0 = obj;
            return c03812;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SleepDetailNewProtocolResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03812) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                java.lang.Object r1 = r6.downSleepDetailV1(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.C03812.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailNewProtocolResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$1", f = "SleepDetailRepository.kt", i = {}, l = {1067}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends SleepDetailNewProtocolResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<? extends SleepDetailNewProtocolResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, List<? extends SleepDetailNewProtocolResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: SleepDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$2", f = "SleepDetailRepository.kt", i = {}, l = {1069}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00612 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<? extends SleepDetailNewProtocolResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00612(FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>> flowCollector, Continuation<? super C00612> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00612 c00612 = new C00612(this.$$this$flow, continuation);
                c00612.I$0 = i;
                return c00612.invokeSuspend(Unit.INSTANCE);
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

    public final Object downSleepDetailNewProtocolFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03812(j, j2, null)), new C03823(null)), Dispatchers.getIO()), new C03834(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository.downSleepDetailNewProtocolFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<SleepDetailNewProtocolResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<SleepDetailNewProtocolResp>> netState, Continuation<? super Unit> continuation2) {
                List<SleepDetailNewProtocolResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (SleepDetailNewProtocolResp sleepDetailNewProtocolResp : listIsSuccess) {
                            String str = sleepDetailNewProtocolResp.deviceAddress;
                            Intrinsics.checkNotNull(str);
                            String str2 = sleepDetailNewProtocolResp.date;
                            Intrinsics.checkNotNull(str2);
                            List<SleepDetailNewProtocolResp.SleepDetail> list = sleepDetailNewProtocolResp.datas;
                            Intrinsics.checkNotNull(list);
                            SleepDetailRepository.this.sleepNewDao.insert(new SleepNewProtocol(str, str2, MoshiUtilsKt.toJson(list), sleepDetailNewProtocolResp.st, sleepDetailNewProtocolResp.et, true, new DateUtil().getUnixTimestamp()));
                            SleepDetailRepository.this.saveDownloadSleepToTotal(sleepDetailNewProtocolResp);
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailNewProtocolResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$3", f = "SleepDetailRepository.kt", i = {}, l = {1077}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03823 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SleepDetailNewProtocolResp>>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03823(Continuation<? super C03823> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03823 c03823 = new C03823(continuation);
            c03823.L$0 = obj;
            return c03823;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SleepDetailNewProtocolResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03823) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailNewProtocolResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$4", f = "SleepDetailRepository.kt", i = {}, l = {1080}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository$downSleepDetailNewProtocolFromServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03834 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends SleepDetailNewProtocolResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03834(Continuation<? super C03834> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SleepDetailNewProtocolResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SleepDetailNewProtocolResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03834 c03834 = new C03834(continuation);
            c03834.L$0 = flowCollector;
            c03834.L$1 = th;
            return c03834.invokeSuspend(Unit.INSTANCE);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveDownloadSleepToTotal(SleepDetailNewProtocolResp sleepBean) {
        List<SleepDetailNewProtocolResp.SleepDetail> list = sleepBean.datas;
        Intrinsics.checkNotNullExpressionValue(list, "sleepBean.datas");
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (SleepDetailNewProtocolResp.SleepDetail sleepDetail : list) {
            int i5 = sleepDetail.t;
            if (i5 == 2) {
                i2 += sleepDetail.d;
            } else if (i5 == 3) {
                i += sleepDetail.d;
            } else if (i5 == 4) {
                i4 += sleepDetail.d;
            } else if (i5 == 5) {
                i3 += sleepDetail.d;
            }
        }
        int i6 = i * 60;
        int i7 = i2 * 60;
        int i8 = i3 * 60;
        int i9 = i4 * 60;
        int i10 = ((sleepBean.et - sleepBean.st) / 60) % 20;
        String str = sleepBean.deviceAddress;
        Intrinsics.checkNotNullExpressionValue(str, "sleepBean.deviceAddress");
        String y_m_d = new DateUtil(sleepBean.et, true).getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(sleepBean.et.toLong(), true).y_M_D");
        this.sleepTotalDao.insert(new SleepTotalHistory(str, y_m_d, i6 + i7 + i8 + i9, i6, i7, i9, i8, sleepBean.st, sleepBean.et, (int) new DateUtil(sleepBean.et, true).getZeroTime()));
    }

    /* compiled from: SleepDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SleepDetailRepository getGetInstance() {
            return (SleepDetailRepository) SleepDetailRepository.getInstance$delegate.getValue();
        }
    }
}
