package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.blankj.utilcode.constant.CacheConstants;
import com.elvishew.xlog.XLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.bean.ManualHeartRate;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse;
import com.oudmon.ble.base.communication.req.ReadHeartRateReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.ReadHeartRateRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.HeartRateResp;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcHeartRateDetailDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcManualHeartDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.HeartRateDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.ManualHeartEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView;
import com.qcwireless.qcwatch.ui.home.heart.bean.HeartRateDetailBean;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: HeartRateDetailRepository.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 22\u00020\u0001:\u00012B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J&\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001f0%H\u0002J'\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u00072\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001f0%H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010(J\u000e\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020+J\u0016\u0010)\u001a\u00020\f2\u0006\u0010,\u001a\u00020\b2\u0006\u0010*\u001a\u00020+J\u001c\u0010-\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001f0%J\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b000/H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;", "", "()V", "heartRateDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcHeartRateDetailDao;", "historyDate", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "manualHeartRateDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcManualHeartDao;", "downHeartRateDetailFromServer", "", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTimeZone", "", "queryHeartDetail", "", "Lcom/qcwireless/qcwatch/ui/base/view/QHeartLineChartView$HeartDataBean;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryHeartRateByDateDetailResp", "Lcom/qcwireless/qcwatch/ui/home/heart/bean/HeartRateDetailBean;", "queryLastData", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/HeartRateDetail;", "queryLastHeartDetail", "saveHeartRate", "heartResp", "Lcom/oudmon/ble/base/communication/rsp/ReadHeartRateRsp;", "saveHeartRateToday", "syncHeartRateDetail", "key", "time", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "syncHistoryHeartDetail", "deviceAddress", "(Ljava/lang/String;Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncManualHeartRate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/oudmon/ble/base/communication/bigData/resp/ILargeDataManualHeartRateResponse;", TypedValues.CycleType.S_WAVE_OFFSET, "syncTodayHeartRate", "updateHeartRateDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartRateDetailRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<HeartRateDetailRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<HeartRateDetailRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HeartRateDetailRepository invoke() {
            return new HeartRateDetailRepository();
        }
    });
    private final QcHeartRateDetailDao heartRateDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcHeartRateDao();
    private final QcManualHeartDao manualHeartRateDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcManualHeartDao();
    private ConcurrentHashMap<String, Integer> historyDate = new ConcurrentHashMap<>();

    public final List<QHeartLineChartView.HeartDataBean> queryHeartDetail(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        QcManualHeartDao qcManualHeartDao = this.manualHeartRateDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        ManualHeartEntity manualHeartEntityQueryByDate = qcManualHeartDao.queryByDate(deviceAddressNoClear, y_m_d);
        QcHeartRateDetailDao qcHeartRateDetailDao = this.heartRateDao;
        String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d2 = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d2, "date.y_M_D");
        HeartRateDetail heartRateDetailQueryHeartByDate = qcHeartRateDetailDao.queryHeartByDate(deviceAddressNoClear2, y_m_d2);
        if (heartRateDetailQueryHeartByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(heartRateDetailQueryHeartByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(heartRateDetailQueryHeartByDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArrStringToIntArray[i];
                if (iArrStringToIntArray2[i] != 0) {
                    QHeartLineChartView.HeartDataBean heartDataBean = new QHeartLineChartView.HeartDataBean(i2 * heartRateDetailQueryHeartByDate.getIntervar(), iArrStringToIntArray2[i], false, heartRateDetailQueryHeartByDate.getIntervar());
                    linkedHashMap.put(Integer.valueOf(heartDataBean.getMin()), heartDataBean);
                }
            }
        }
        if (manualHeartEntityQueryByDate != null) {
            String content = manualHeartEntityQueryByDate.getContent();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends ManualHeartRate.DetailBean>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryHeartDetail$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List<ManualHeartRate.DetailBean> list = (List) jsonAdapterAdapter.fromJson(content);
            if (list != null) {
                for (ManualHeartRate.DetailBean detailBean : list) {
                    if (detailBean.getV() != 0) {
                        QHeartLineChartView.HeartDataBean heartDataBean2 = new QHeartLineChartView.HeartDataBean(detailBean.getM(), detailBean.getV(), false, 0);
                        linkedHashMap.put(Integer.valueOf(heartDataBean2.getMin()), heartDataBean2);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.values());
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryHeartDetail$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((QHeartLineChartView.HeartDataBean) t).getMin()), Integer.valueOf(((QHeartLineChartView.HeartDataBean) t2).getMin()));
                }
            });
        }
        return arrayList;
    }

    public final List<HeartRateDetailBean> queryHeartRateByDateDetailResp(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcManualHeartDao qcManualHeartDao = this.manualHeartRateDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        ManualHeartEntity manualHeartEntityQueryByDate = qcManualHeartDao.queryByDate(deviceAddressNoClear, y_m_d);
        if (manualHeartEntityQueryByDate != null) {
            String content = manualHeartEntityQueryByDate.getContent();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends ManualHeartRate.DetailBean>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryHeartRateByDateDetailResp$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List<ManualHeartRate.DetailBean> list = (List) jsonAdapterAdapter.fromJson(content);
            if (list != null) {
                int i = 0;
                for (ManualHeartRate.DetailBean detailBean : list) {
                    int i2 = i + 1;
                    if (detailBean.getV() != 0) {
                        if (i >= 1 && ((ManualHeartRate.DetailBean) list.get(i - 1)).getM() == ((ManualHeartRate.DetailBean) list.get(i)).getM()) {
                            arrayList.add(new HeartRateDetailBean(date.getZeroTime() + (detailBean.getM() * 60) + i, detailBean.getV()));
                        } else {
                            arrayList.add(new HeartRateDetailBean(date.getZeroTime() + (detailBean.getM() * 60), detailBean.getV()));
                        }
                    }
                    i = i2;
                }
            }
        }
        QcHeartRateDetailDao qcHeartRateDetailDao = this.heartRateDao;
        String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d2 = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d2, "date.y_M_D");
        HeartRateDetail heartRateDetailQueryHeartByDate = qcHeartRateDetailDao.queryHeartByDate(deviceAddressNoClear2, y_m_d2);
        if (heartRateDetailQueryHeartByDate != null) {
            int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(heartRateDetailQueryHeartByDate.getIndex_str());
            int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(heartRateDetailQueryHeartByDate.getValue());
            int length = iArrStringToIntArray.length;
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = iArrStringToIntArray[i3];
                if (iArrStringToIntArray2[i3] != 0) {
                    arrayList.add(new HeartRateDetailBean(date.getZeroTime() + (i4 * heartRateDetailQueryHeartByDate.getIntervar() * 60), iArrStringToIntArray2[i3]));
                }
            }
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryHeartRateByDateDetailResp$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((HeartRateDetailBean) t2).getTimestamp()), Long.valueOf(((HeartRateDetailBean) t).getTimestamp()));
                }
            });
        }
        return arrayList;
    }

    public final void syncTodayHeartRate(DateUtil date, final BaseDeviceResult<ReadHeartRateRsp> result) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(result, "result");
        CommandHandle.getInstance().executeReqCmd(new ReadHeartRateReq(date.getUnixTimestamp() + ((int) (getTimeZone() * CacheConstants.HOUR))), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                HeartRateDetailRepository.m292syncTodayHeartRate$lambda2(this.f$0, result, (ReadHeartRateRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayHeartRate$lambda-2, reason: not valid java name */
    public static final void m292syncTodayHeartRate$lambda2(HeartRateDetailRepository this$0, BaseDeviceResult result, ReadHeartRateRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.saveHeartRateToday(it);
            if (it.isEndFlag()) {
                result.result(0, it);
                return;
            }
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        result.result(-1, it);
    }

    public final void syncManualHeartRate(int offset, final ILargeDataManualHeartRateResponse listener) throws InterruptedException {
        Intrinsics.checkNotNullParameter(listener, "listener");
        LargeDataHandler.getInstance().syncManualHeartRateList(offset, new ILargeDataManualHeartRateResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse
            public final void manualHeart(ManualHeartRate manualHeartRate) {
                HeartRateDetailRepository.m291syncManualHeartRate$lambda3(listener, this, manualHeartRate);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncManualHeartRate$lambda-3, reason: not valid java name */
    public static final void m291syncManualHeartRate$lambda3(ILargeDataManualHeartRateResponse listener, HeartRateDetailRepository this$0, final ManualHeartRate manualHeartRate) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        listener.manualHeart(manualHeartRate);
        final DateUtil dateUtil = new DateUtil();
        dateUtil.addDay(-manualHeartRate.getIndex());
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<HeartRateDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$syncManualHeartRate$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartRateDetailRepository heartRateDetailRepository) {
                invoke2(heartRateDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartRateDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                List<ManualHeartRate.DetailBean> data = manualHeartRate.getData();
                Intrinsics.checkNotNullExpressionValue(data, "it.data");
                ktxRunOnBgSingle.manualHeartRateDao.insert(new ManualHeartEntity(deviceAddressNoClear, y_m_d, MoshiUtilsKt.toJson(data)));
            }
        });
    }

    public final void syncManualHeartRate(ILargeDataManualHeartRateResponse listener) throws InterruptedException {
        Intrinsics.checkNotNullParameter(listener, "listener");
        DateUtil dateUtil = new DateUtil();
        dateUtil.addDay(-1);
        QcManualHeartDao qcManualHeartDao = this.manualHeartRateDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = dateUtil.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        if (qcManualHeartDao.queryByDate(deviceAddressNoClear, y_m_d) == null) {
            syncManualHeartRate(255, listener);
        } else {
            syncManualHeartRate(0, listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncHeartRateDetail(final String key, long time, final BaseDeviceResult<ReadHeartRateRsp> result) {
        this.historyDate.remove(key);
        CommandHandle.getInstance().executeReqCmd(new ReadHeartRateReq(time), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                HeartRateDetailRepository.m290syncHeartRateDetail$lambda4(this.f$0, key, result, (ReadHeartRateRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncHeartRateDetail$lambda-4, reason: not valid java name */
    public static final void m290syncHeartRateDetail$lambda4(HeartRateDetailRepository this$0, String key, BaseDeviceResult result, ReadHeartRateRsp it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (it.getStatus() == 0) {
            int i = it.getmUtcTime();
            this$0.historyDate.remove(new DateUtil(i, true).getY_M_D());
            if (i == 0) {
                this$0.historyDate.remove(key);
            }
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.saveHeartRate(it);
            if (!this$0.historyDate.isEmpty()) {
                Iterator<Map.Entry<String, Integer>> it2 = this$0.historyDate.entrySet().iterator();
                if (it2.hasNext()) {
                    Map.Entry<String, Integer> next = it2.next();
                    Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                    String key2 = next.getKey();
                    Intrinsics.checkNotNullExpressionValue(key2, "bean.key");
                    this$0.syncHeartRateDetail(key2, r7.getValue().intValue(), result);
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

    public final Object syncHistoryHeartDetail(String str, final BaseDeviceResult<ReadHeartRateRsp> baseDeviceResult, Continuation<? super Unit> continuation) {
        if (!BleOperateManager.getInstance().isConnected()) {
            return Unit.INSTANCE;
        }
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.flow(new C03692(str, null)), Dispatchers.getIO()), new C03703(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository.syncHistoryHeartDetail.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Map<String, Integer>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Map<String, Integer> map, Continuation<? super Unit> continuation2) {
                Iterator it = HeartRateDetailRepository.this.historyDate.entrySet().iterator();
                if (it.hasNext()) {
                    Object next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                    HeartRateDetailRepository heartRateDetailRepository = HeartRateDetailRepository.this;
                    Object key = ((Map.Entry) next).getKey();
                    Intrinsics.checkNotNullExpressionValue(key, "bean.key");
                    heartRateDetailRepository.syncHeartRateDetail((String) key, ((Number) r4.getValue()).intValue(), baseDeviceResult);
                } else {
                    baseDeviceResult.result(0, new ReadHeartRateRsp());
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$syncHistoryHeartDetail$2", f = "HeartRateDetailRepository.kt", i = {}, l = {272}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$syncHistoryHeartDetail$2, reason: invalid class name and case insensitive filesystem */
    static final class C03692 extends SuspendLambda implements Function2<FlowCollector<? super Map<String, ? extends Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $deviceAddress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03692(String str, Continuation<? super C03692> continuation) {
            super(2, continuation);
            this.$deviceAddress = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03692 c03692 = HeartRateDetailRepository.this.new C03692(this.$deviceAddress, continuation);
            c03692.L$0 = obj;
            return c03692;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03692) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                DateUtil dateUtil = new DateUtil();
                dateUtil.setHour(0);
                dateUtil.setMinute(0);
                DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp() + ((int) (HeartRateDetailRepository.this.getTimeZone() * CacheConstants.HOUR)), true);
                for (int i2 = 1; i2 < 7; i2++) {
                    dateUtil2.addDay(-1);
                    ConcurrentHashMap concurrentHashMap = HeartRateDetailRepository.this.historyDate;
                    String y_m_d = dateUtil2.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "newDate.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt((int) dateUtil2.getUnixTimestamp()));
                }
                List<HeartRateDetail> listQueryDaysSyncDate = HeartRateDetailRepository.this.heartRateDao.queryDaysSyncDate(this.$deviceAddress);
                if (listQueryDaysSyncDate != null) {
                    for (HeartRateDetail heartRateDetail : listQueryDaysSyncDate) {
                        if (new DateUtil(heartRateDetail.getLastSyncTime(), true).isToday()) {
                            HeartRateDetailRepository.this.historyDate.remove(heartRateDetail.getDateStr());
                        }
                    }
                }
                XLog.i("-sync heart date");
                XLog.i(HeartRateDetailRepository.this.historyDate);
                LogToFile.getInstance().wtf("同步数据日期：");
                LogToFile.getInstance().wtf(MoshiUtilsKt.toJson(HeartRateDetailRepository.this.historyDate));
                this.label = 1;
                if (flowCollector.emit(HeartRateDetailRepository.this.historyDate, this) == coroutine_suspended) {
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

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$syncHistoryHeartDetail$3", f = "HeartRateDetailRepository.kt", i = {}, l = {280}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$syncHistoryHeartDetail$3, reason: invalid class name and case insensitive filesystem */
    static final class C03703 extends SuspendLambda implements Function3<FlowCollector<? super Map<String, ? extends Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03703(Continuation<? super C03703> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03703 c03703 = HeartRateDetailRepository.this.new C03703(continuation);
            c03703.L$0 = flowCollector;
            c03703.L$1 = th;
            return c03703.invokeSuspend(Unit.INSTANCE);
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
                    ConcurrentHashMap concurrentHashMap = HeartRateDetailRepository.this.historyDate;
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    concurrentHashMap.put(y_m_d, Boxing.boxInt(i2));
                }
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(HeartRateDetailRepository.this.historyDate, this) == coroutine_suspended) {
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

    private final void saveHeartRate(final ReadHeartRateRsp heartResp) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartRateDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository.saveHeartRate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartRateDetailRepository heartRateDetailRepository) {
                invoke2(heartRateDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartRateDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                DateUtil dateUtil = new DateUtil(heartResp.getmUtcTime(), true);
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                byte[] bArr = heartResp.getmHeartRateArray();
                int length = bArr.length;
                for (int i = 0; i < length; i++) {
                    sb.append(i);
                    sb.append(",");
                    int iByteToInt = bArr[i];
                    if (iByteToInt < 0) {
                        iByteToInt = ByteUtil.byteToInt(bArr[i]);
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
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateStr.y_M_D");
                int range = heartResp.getRange();
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "indexStr.toString()");
                String string2 = sb2.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "values.toString()");
                HeartRateDetail heartRateDetail = new HeartRateDetail(deviceAddressNoClear, y_m_d, range, string, string2, dateUtil.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                if (dateUtil.getZeroTime() > 0) {
                    ktxRunOnBgSingle.heartRateDao.insert(heartRateDetail);
                }
            }
        });
    }

    private final void saveHeartRateToday(final ReadHeartRateRsp heartResp) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HeartRateDetailRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository.saveHeartRateToday.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartRateDetailRepository heartRateDetailRepository) {
                invoke2(heartRateDetailRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartRateDetailRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                DateUtil dateUtil = new DateUtil(heartResp.getmUtcTime(), true);
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                byte[] bArr = heartResp.getmHeartRateArray();
                int length = bArr.length;
                for (int i = 0; i < length; i++) {
                    sb.append(i);
                    sb.append(",");
                    int iByteToInt = bArr[i];
                    if (iByteToInt < 0) {
                        iByteToInt = ByteUtil.byteToInt(bArr[i]);
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
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateStr.y_M_D");
                int range = heartResp.getRange();
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "indexStr.toString()");
                String string2 = sb2.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "values.toString()");
                HeartRateDetail heartRateDetail = new HeartRateDetail(deviceAddressNoClear, y_m_d, range, string, string2, dateUtil.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                if (dateUtil.getZeroTime() > 0) {
                    ktxRunOnBgSingle.heartRateDao.insert(heartRateDetail);
                    return;
                }
                QcHeartRateDetailDao qcHeartRateDetailDao = ktxRunOnBgSingle.heartRateDao;
                String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                qcHeartRateDetailDao.delete(new HeartRateDetail(deviceAddressNoClear2, y_m_d2, heartResp.getRange(), "", "", new DateUtil().getZeroTime(), false, new DateUtil().getUnixTimestamp()));
            }
        });
    }

    public final HeartRateDetail queryLastData() {
        return this.heartRateDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final List<QHeartLineChartView.HeartDataBean> queryLastHeartDetail() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        HeartRateDetail heartRateDetailQueryLastSyncDate = this.heartRateDao.queryLastSyncDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        ManualHeartEntity manualHeartEntityQueryDataDate = this.manualHeartRateDao.queryDataDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        long zeroTime = (manualHeartEntityQueryDataDate == null || manualHeartEntityQueryDataDate.getContent().length() <= 2) ? 0L : DateUtil.parse(manualHeartEntityQueryDataDate.getDateStr(), DateUtil.DateFormater.dFyyyyMMdd).getZeroTime();
        long zeroTime2 = heartRateDetailQueryLastSyncDate != null ? new DateUtil(heartRateDetailQueryLastSyncDate.getUnixTime(), true).getZeroTime() : 0L;
        if (zeroTime2 == zeroTime) {
            if (heartRateDetailQueryLastSyncDate != null) {
                int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(heartRateDetailQueryLastSyncDate.getIndex_str());
                int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(heartRateDetailQueryLastSyncDate.getValue());
                int length = iArrStringToIntArray.length;
                for (int i = 0; i < length; i++) {
                    int i2 = iArrStringToIntArray[i];
                    if (iArrStringToIntArray2[i] != 0) {
                        QHeartLineChartView.HeartDataBean heartDataBean = new QHeartLineChartView.HeartDataBean(i2 * heartRateDetailQueryLastSyncDate.getIntervar(), iArrStringToIntArray2[i], false, heartRateDetailQueryLastSyncDate.getIntervar());
                        heartDataBean.setUnixTime(heartRateDetailQueryLastSyncDate.getUnixTime());
                        linkedHashMap.put(Integer.valueOf(heartDataBean.getMin()), heartDataBean);
                    }
                }
            }
            if (manualHeartEntityQueryDataDate != null) {
                String content = manualHeartEntityQueryDataDate.getContent();
                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends ManualHeartRate.DetailBean>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryLastHeartDetail$$inlined$fromJson$1
                }.getType());
                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
                List<ManualHeartRate.DetailBean> list = (List) jsonAdapterAdapter.fromJson(content);
                if (list != null) {
                    for (ManualHeartRate.DetailBean detailBean : list) {
                        if (detailBean.getV() != 0) {
                            QHeartLineChartView.HeartDataBean heartDataBean2 = new QHeartLineChartView.HeartDataBean(detailBean.getM(), detailBean.getV(), false, 0);
                            heartDataBean2.setUnixTime(DateUtil.parse(manualHeartEntityQueryDataDate.getDateStr(), DateUtil.DateFormater.dFyyyyMMdd).getUnixTimestamp());
                            linkedHashMap.put(Integer.valueOf(heartDataBean2.getMin()), heartDataBean2);
                        }
                    }
                }
            }
        } else if (zeroTime2 > zeroTime) {
            if (heartRateDetailQueryLastSyncDate != null) {
                int[] iArrStringToIntArray3 = StringUtilsKt.stringToIntArray(heartRateDetailQueryLastSyncDate.getIndex_str());
                int[] iArrStringToIntArray4 = StringUtilsKt.stringToIntArray(heartRateDetailQueryLastSyncDate.getValue());
                int length2 = iArrStringToIntArray3.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = iArrStringToIntArray3[i3];
                    if (iArrStringToIntArray4[i3] != 0) {
                        QHeartLineChartView.HeartDataBean heartDataBean3 = new QHeartLineChartView.HeartDataBean(i4 * heartRateDetailQueryLastSyncDate.getIntervar(), iArrStringToIntArray4[i3], false, heartRateDetailQueryLastSyncDate.getIntervar());
                        heartDataBean3.setUnixTime(heartRateDetailQueryLastSyncDate.getUnixTime());
                        linkedHashMap.put(Integer.valueOf(heartDataBean3.getMin()), heartDataBean3);
                    }
                }
            }
        } else if (manualHeartEntityQueryDataDate != null) {
            String content2 = manualHeartEntityQueryDataDate.getContent();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends ManualHeartRate.DetailBean>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryLastHeartDetail$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List<ManualHeartRate.DetailBean> list2 = (List) jsonAdapterAdapter2.fromJson(content2);
            if (list2 != null) {
                int i5 = 0;
                for (ManualHeartRate.DetailBean detailBean2 : list2) {
                    int i6 = i5 + 1;
                    if (detailBean2.getV() != 0 && (i5 < 1 || ((ManualHeartRate.DetailBean) list2.get(i5 - 1)).getM() != ((ManualHeartRate.DetailBean) list2.get(i5)).getM())) {
                        QHeartLineChartView.HeartDataBean heartDataBean4 = new QHeartLineChartView.HeartDataBean(detailBean2.getM(), detailBean2.getV(), false, 0);
                        heartDataBean4.setUnixTime(DateUtil.parse(manualHeartEntityQueryDataDate.getDateStr(), DateUtil.DateFormater.dFyyyyMMdd).getUnixTimestamp());
                        linkedHashMap.put(Integer.valueOf(heartDataBean4.getMin()), heartDataBean4);
                    }
                    i5 = i6;
                }
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.values());
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$queryLastHeartDetail$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((QHeartLineChartView.HeartDataBean) t).getMin()), Integer.valueOf(((QHeartLineChartView.HeartDataBean) t2).getMin()));
                }
            });
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getTimeZone() {
        return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 3600000.0f;
    }

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$2", f = "HeartRateDetailRepository.kt", i = {0, 0, 1}, l = {TypedValues.PositionType.TYPE_DRAWPATH, TypedValues.PositionType.TYPE_DRAWPATH, TypedValues.PositionType.TYPE_CURVE_FIT}, m = "invokeSuspend", n = {"$this$flow", "notUpList", "$this$flow"}, s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03722 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C03722(Continuation<? super C03722> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03722 c03722 = HeartRateDetailRepository.this.new C03722(continuation);
            c03722.L$0 = obj;
            return c03722;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03722) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00fc A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instructions count: 279
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository.C03722.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: HeartRateDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$2$1", f = "HeartRateDetailRepository.kt", i = {}, l = {TypedValues.PositionType.TYPE_PERCENT_WIDTH}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            final /* synthetic */ List<HeartRateDetail> $notUpList;
            int label;
            final /* synthetic */ HeartRateDetailRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, List<HeartRateDetail> list, HeartRateDetailRepository heartRateDetailRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$notUpList = list;
                this.this$0 = heartRateDetailRepository;
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
                for (HeartRateDetail heartRateDetail : this.$notUpList) {
                    heartRateDetail.setSync(true);
                    this.this$0.heartRateDao.insert(heartRateDetail);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: HeartRateDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$2$2", f = "HeartRateDetailRepository.kt", i = {}, l = {509}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00592 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00592(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00592> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00592 c00592 = new C00592(this.$$this$flow, continuation);
                c00592.I$0 = i;
                return c00592.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateHeartRateDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03722(null)), new C03733(null)), Dispatchers.getIO()), new C03744(null));
    }

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$3", f = "HeartRateDetailRepository.kt", i = {}, l = {513}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03733 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03733(Continuation<? super C03733> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03733 c03733 = new C03733(continuation);
            c03733.L$0 = obj;
            return c03733;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03733) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$4", f = "HeartRateDetailRepository.kt", i = {}, l = {516}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$updateHeartRateDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03744 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03744(Continuation<? super C03744> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03744 c03744 = new C03744(continuation);
            c03744.L$0 = flowCollector;
            c03744.L$1 = th;
            return c03744.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2", f = "HeartRateDetailRepository.kt", i = {0, 1}, l = {522, 522, 524}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends HeartRateResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends HeartRateResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<HeartRateResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<HeartRateResp>>> flowCollector, Continuation<? super Unit> continuation) {
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
                java.lang.Object r1 = r6.downHeartRateDetail(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: HeartRateDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$1", f = "HeartRateDetailRepository.kt", i = {}, l = {523}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends HeartRateResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<HeartRateResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<HeartRateResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends HeartRateResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<HeartRateResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<HeartRateResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: HeartRateDetailRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$2", f = "HeartRateDetailRepository.kt", i = {}, l = {525}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00582 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<HeartRateResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00582(FlowCollector<? super NetState<List<HeartRateResp>>> flowCollector, Continuation<? super C00582> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00582 c00582 = new C00582(this.$$this$flow, continuation);
                c00582.I$0 = i;
                return c00582.invokeSuspend(Unit.INSTANCE);
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

    public final Object downHeartRateDetailFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository.downHeartRateDetailFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<HeartRateResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<HeartRateResp>> netState, Continuation<? super Unit> continuation2) {
                List<HeartRateResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (HeartRateResp heartRateResp : listIsSuccess) {
                            String deviceId = heartRateResp.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            String date = heartRateResp.getDate();
                            Intrinsics.checkNotNull(date);
                            int interval = heartRateResp.getInterval() / 60;
                            HeartRateResp.HeartDetail data = heartRateResp.getData();
                            Intrinsics.checkNotNull(data);
                            String strIntListToString = StringUtilsKt.intListToString(data.getIndexs());
                            HeartRateResp.HeartDetail data2 = heartRateResp.getData();
                            Intrinsics.checkNotNull(data2);
                            String strIntListToString2 = StringUtilsKt.intListToString(data2.getValues());
                            String date2 = heartRateResp.getDate();
                            Intrinsics.checkNotNull(date2);
                            HeartRateDetailRepository.this.heartRateDao.insert(new HeartRateDetail(deviceId, date, interval, strIntListToString, strIntListToString2, new DateUtil(DateUtil.dateY_M_D2Stamp(date2), false).getZeroTime(), true, 0L));
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

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$3", f = "HeartRateDetailRepository.kt", i = {}, l = {528}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends HeartRateResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends HeartRateResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<HeartRateResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<HeartRateResp>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$4", f = "HeartRateDetailRepository.kt", i = {}, l = {531}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository$downHeartRateDetailFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends HeartRateResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends HeartRateResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<HeartRateResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<HeartRateResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: HeartRateDetailRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HeartRateDetailRepository getGetInstance() {
            return (HeartRateDetailRepository) HeartRateDetailRepository.getInstance$delegate.getValue();
        }
    }
}
