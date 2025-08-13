package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.blankj.utilcode.constant.CacheConstants;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.Spo2DownResp;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodOxygenDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodOxygenEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartView;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.bean.BloodOxyDetailBean;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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

/* compiled from: BloodOxygenRepository.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u001c\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0 0\u001fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;", "", "()V", "bloodOxygenDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodOxygenDao;", "downBoFromServer", "", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryBloodOxygenByDate", "", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodOxygenLineChartView$DataBean;", "mac", "", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryBloodOxygenByDateDetail", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/bean/BloodOxyDetailBean;", "queryLastBloodOxygen", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodOxygenLineChartHomeView$DataBean;", "queryLastBloodOxygenDate", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/BloodOxygenEntity;", "syncAutoBloodOxygen", TypedValues.CycleType.S_WAVE_OFFSET, "", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "Lcom/oudmon/ble/base/communication/rsp/ReadBlePressureRsp;", "updateBloodOxygenDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodOxygenRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BloodOxygenRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<BloodOxygenRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BloodOxygenRepository invoke() {
            return new BloodOxygenRepository();
        }
    });
    private final QcBloodOxygenDao bloodOxygenDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcBloodOxygenDao();

    public final List<QBloodOxygenLineChartView.DataBean> queryBloodOxygenByDate(String mac, DateUtil date) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcBloodOxygenDao qcBloodOxygenDao = this.bloodOxygenDao;
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        BloodOxygenEntity bloodOxygenEntityQueryBloodOxygenByDate = qcBloodOxygenDao.queryBloodOxygenByDate(mac, y_m_d);
        if (bloodOxygenEntityQueryBloodOxygenByDate != null) {
            String maxArray = bloodOxygenEntityQueryBloodOxygenByDate.getMaxArray();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Integer>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryBloodOxygenByDate$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list = (List) jsonAdapterAdapter.fromJson(maxArray);
            String minArray = bloodOxygenEntityQueryBloodOxygenByDate.getMinArray();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Integer>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryBloodOxygenByDate$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list2 = (List) jsonAdapterAdapter2.fromJson(minArray);
            if (list != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    int i2 = i * CacheConstants.HOUR;
                    Intrinsics.checkNotNull(list2);
                    arrayList.add(new QBloodOxygenLineChartView.DataBean(i2, ((Number) list2.get(i)).intValue(), iIntValue));
                    i++;
                }
            }
        }
        return arrayList;
    }

    public final List<BloodOxyDetailBean> queryBloodOxygenByDateDetail(String mac, DateUtil date) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcBloodOxygenDao qcBloodOxygenDao = this.bloodOxygenDao;
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        BloodOxygenEntity bloodOxygenEntityQueryBloodOxygenByDate = qcBloodOxygenDao.queryBloodOxygenByDate(mac, y_m_d);
        if (bloodOxygenEntityQueryBloodOxygenByDate != null) {
            String maxArray = bloodOxygenEntityQueryBloodOxygenByDate.getMaxArray();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Integer>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryBloodOxygenByDateDetail$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list = (List) jsonAdapterAdapter.fromJson(maxArray);
            String minArray = bloodOxygenEntityQueryBloodOxygenByDate.getMinArray();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Integer>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryBloodOxygenByDateDetail$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list2 = (List) jsonAdapterAdapter2.fromJson(minArray);
            if (list != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2 = i + 1;
                    int iIntValue = ((Number) it.next()).intValue();
                    if (iIntValue > 0) {
                        int i3 = i * CacheConstants.HOUR;
                        Intrinsics.checkNotNull(list2);
                        arrayList.add(new BloodOxyDetailBean(i3, ((Number) list2.get(i)).intValue(), iIntValue));
                    }
                    i = i2;
                }
            }
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryBloodOxygenByDateDetail$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((BloodOxyDetailBean) t2).getSeconds()), Integer.valueOf(((BloodOxyDetailBean) t).getSeconds()));
                }
            });
        }
        return arrayList;
    }

    public final BloodOxygenEntity queryLastBloodOxygenDate() {
        return this.bloodOxygenDao.queryBloodOxygenByDateDesc(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final List<QBloodOxygenLineChartHomeView.DataBean> queryLastBloodOxygen(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        ArrayList arrayList = new ArrayList();
        BloodOxygenEntity bloodOxygenEntityQueryBloodOxygenByDateDesc = this.bloodOxygenDao.queryBloodOxygenByDateDesc(mac);
        if (bloodOxygenEntityQueryBloodOxygenByDateDesc != null) {
            String maxArray = bloodOxygenEntityQueryBloodOxygenByDateDesc.getMaxArray();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Integer>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryLastBloodOxygen$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list = (List) jsonAdapterAdapter.fromJson(maxArray);
            String minArray = bloodOxygenEntityQueryBloodOxygenByDateDesc.getMinArray();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Integer>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$queryLastBloodOxygen$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list2 = (List) jsonAdapterAdapter2.fromJson(minArray);
            if (list != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    int i2 = i * CacheConstants.HOUR;
                    Intrinsics.checkNotNull(list2);
                    arrayList.add(new QBloodOxygenLineChartHomeView.DataBean(i2, ((Number) list2.get(i)).intValue(), iIntValue, (int) bloodOxygenEntityQueryBloodOxygenByDateDesc.getUnixTime()));
                    i++;
                }
            }
        }
        return arrayList;
    }

    public final void syncAutoBloodOxygen(int offset, final BaseDeviceResult<ReadBlePressureRsp> result) throws InterruptedException {
        Intrinsics.checkNotNullParameter(result, "result");
        LargeDataHandler.getInstance().syncBloodOxygen(offset, new ILargeDataResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, byte[] bArr) {
                BloodOxygenRepository.m284syncAutoBloodOxygen$lambda1(this.f$0, result, i, bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncAutoBloodOxygen$lambda-1, reason: not valid java name */
    public static final void m284syncAutoBloodOxygen$lambda1(BloodOxygenRepository this$0, BaseDeviceResult result, int i, byte[] bArr) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (i == 42) {
            int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4)) / 49;
            int i2 = 0;
            while (i2 < iBytesToInt) {
                int i3 = (i2 * 49) + 6;
                i2++;
                byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i3, (i2 * 49) + 6);
                XLog.i(bArrCopyOfRange);
                final DateUtil dateUtil = new DateUtil();
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                int length = bArrCopyOfRange.length;
                for (int i4 = 0; i4 < length; i4++) {
                    if (i4 == 0) {
                        dateUtil.addDay(-bArrCopyOfRange[0]);
                    } else if (i4 % 2 == 0) {
                        arrayList2.add(Integer.valueOf(bArrCopyOfRange[i4]));
                    } else {
                        arrayList.add(Integer.valueOf(bArrCopyOfRange[i4]));
                    }
                }
                ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<BloodOxygenRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$syncAutoBloodOxygen$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodOxygenRepository bloodOxygenRepository) {
                        invoke2(bloodOxygenRepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodOxygenRepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                        ktxRunOnBgSingle.bloodOxygenDao.insert(new BloodOxygenEntity(deviceAddressNoClear, y_m_d, MoshiUtilsKt.toJson(arrayList2), MoshiUtilsKt.toJson(arrayList), dateUtil.getZeroTime(), false, new DateUtil().getUnixTimestamp()));
                    }
                });
            }
            if (iBytesToInt == 0) {
                ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<BloodOxygenRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$syncAutoBloodOxygen$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodOxygenRepository bloodOxygenRepository) {
                        invoke2(bloodOxygenRepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodOxygenRepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        QcBloodOxygenDao qcBloodOxygenDao = ktxRunOnBgSingle.bloodOxygenDao;
                        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d = new DateUtil().getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                        BloodOxygenEntity bloodOxygenEntityQueryBloodOxygenByDate = qcBloodOxygenDao.queryBloodOxygenByDate(deviceAddressNoClear, y_m_d);
                        if (bloodOxygenEntityQueryBloodOxygenByDate != null) {
                            ktxRunOnBgSingle.bloodOxygenDao.delete(bloodOxygenEntityQueryBloodOxygenByDate);
                        }
                    }
                });
            }
            result.result(0, new ReadBlePressureRsp());
        }
    }

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$2", f = "BloodOxygenRepository.kt", i = {0, 1}, l = {189, 189, 191}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03492 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03492(Continuation<? super C03492> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03492 c03492 = BloodOxygenRepository.this.new C03492(continuation);
            c03492.L$0 = obj;
            return c03492;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03492) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x013c A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instructions count: 343
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository.C03492.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: BloodOxygenRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$2$1", f = "BloodOxygenRepository.kt", i = {}, l = {190}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$2$1, reason: invalid class name */
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

        /* compiled from: BloodOxygenRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$2$2", f = "BloodOxygenRepository.kt", i = {}, l = {192}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00532 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00532(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00532> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00532 c00532 = new C00532(this.$$this$flow, continuation);
                c00532.I$0 = i;
                return c00532.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateBloodOxygenDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03492(null)), new C03503(null)), Dispatchers.getIO()), new C03514(null));
    }

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$3", f = "BloodOxygenRepository.kt", i = {}, l = {196}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03503 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03503(Continuation<? super C03503> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03503 c03503 = new C03503(continuation);
            c03503.L$0 = obj;
            return c03503;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03503) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$4", f = "BloodOxygenRepository.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$updateBloodOxygenDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03514 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03514(Continuation<? super C03514> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03514 c03514 = new C03514(continuation);
            c03514.L$0 = flowCollector;
            c03514.L$1 = th;
            return c03514.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/Spo2DownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2", f = "BloodOxygenRepository.kt", i = {0, 1}, l = {204, 204, 206}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends Spo2DownResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends Spo2DownResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<Spo2DownResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<Spo2DownResp>>> flowCollector, Continuation<? super Unit> continuation) {
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
                com.qcwireless.qcwatch.ui.base.bean.request.healthy.Spo2DownRequest r1 = new com.qcwireless.qcwatch.ui.base.bean.request.healthy.Spo2DownRequest
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
                java.lang.Object r1 = r6.downBo2(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: BloodOxygenRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/Spo2DownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$1", f = "BloodOxygenRepository.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends Spo2DownResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<Spo2DownResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<Spo2DownResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends Spo2DownResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<Spo2DownResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<Spo2DownResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: BloodOxygenRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$2", f = "BloodOxygenRepository.kt", i = {}, l = {207}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00522 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<Spo2DownResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00522(FlowCollector<? super NetState<List<Spo2DownResp>>> flowCollector, Continuation<? super C00522> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00522 c00522 = new C00522(this.$$this$flow, continuation);
                c00522.I$0 = i;
                return c00522.invokeSuspend(Unit.INSTANCE);
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

    public final Object downBoFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository.downBoFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<Spo2DownResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<Spo2DownResp>> netState, Continuation<? super Unit> continuation2) {
                List<Spo2DownResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (Spo2DownResp spo2DownResp : listIsSuccess) {
                            BloodOxygenEntity bloodOxygenEntity = new BloodOxygenEntity(spo2DownResp.getDeviceAddress(), spo2DownResp.getDateStr(), MoshiUtilsKt.toJson(spo2DownResp.getMinValue()), MoshiUtilsKt.toJson(spo2DownResp.getMaxValue()), DateUtil.parse(spo2DownResp.getDateStr(), DateUtil.DateFormater.dFyyyyMMdd).getZeroTime(), true, new DateUtil().getUnixTimestamp());
                            if ((bloodOxygenEntity.getDeviceAddress().length() > 0) && (!spo2DownResp.getMinValue().isEmpty())) {
                                BloodOxygenRepository.this.bloodOxygenDao.insert(bloodOxygenEntity);
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

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/Spo2DownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$3", f = "BloodOxygenRepository.kt", i = {}, l = {210}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends Spo2DownResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends Spo2DownResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<Spo2DownResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<Spo2DownResp>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/Spo2DownResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$4", f = "BloodOxygenRepository.kt", i = {}, l = {213}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository$downBoFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends Spo2DownResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends Spo2DownResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<Spo2DownResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<Spo2DownResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: BloodOxygenRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BloodOxygenRepository getGetInstance() {
            return (BloodOxygenRepository) BloodOxygenRepository.getInstance$delegate.getValue();
        }
    }
}
