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
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcBloodSugarDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodSugarEntity;
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartView;
import com.qcwireless.qcwatch.ui.home.bloodsugar.bean.BloodSugarDetailBean;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodSugarRepository.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\b\u001a\u00020\tJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u001c\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;", "", "()V", "bloodSugarDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodSugarDao;", "queryBloodSugarByDate", "", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodSugarLineChartView$DataBean;", "mac", "", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryBloodSugarByDateDetail", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/bean/BloodSugarDetailBean;", "queryLastBloodSugar", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodSugarLineChartHomeView$DataBean;", "queryLastBloodSugarDate", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/BloodSugarEntity;", "syncBloodSugar", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "result", "Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", "Lcom/oudmon/ble/base/communication/rsp/ReadBlePressureRsp;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodSugarRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BloodSugarRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<BloodSugarRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BloodSugarRepository invoke() {
            return new BloodSugarRepository();
        }
    });
    private final QcBloodSugarDao bloodSugarDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcBloodSugar();

    public final List<QBloodSugarLineChartView.DataBean> queryBloodSugarByDate(String mac, DateUtil date) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcBloodSugarDao qcBloodSugarDao = this.bloodSugarDao;
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        BloodSugarEntity bloodSugarEntityQueryBloodSugarByDate = qcBloodSugarDao.queryBloodSugarByDate(mac, y_m_d);
        if (bloodSugarEntityQueryBloodSugarByDate != null) {
            String maxArray = bloodSugarEntityQueryBloodSugarByDate.getMaxArray();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Float>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryBloodSugarByDate$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list = (List) jsonAdapterAdapter.fromJson(maxArray);
            String minArray = bloodSugarEntityQueryBloodSugarByDate.getMinArray();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Float>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryBloodSugarByDate$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list2 = (List) jsonAdapterAdapter2.fromJson(minArray);
            if (list != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    float fFloatValue = ((Number) it.next()).floatValue();
                    int i2 = i * CacheConstants.HOUR;
                    Intrinsics.checkNotNull(list2);
                    float f = 10;
                    arrayList.add(new QBloodSugarLineChartView.DataBean(i2, (((Number) list2.get(i)).floatValue() * 1.0f) / f, (fFloatValue * 1.0f) / f));
                    i++;
                }
            }
        }
        return arrayList;
    }

    public final List<BloodSugarDetailBean> queryBloodSugarByDateDetail(String mac, DateUtil date) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        QcBloodSugarDao qcBloodSugarDao = this.bloodSugarDao;
        String y_m_d = date.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
        BloodSugarEntity bloodSugarEntityQueryBloodSugarByDate = qcBloodSugarDao.queryBloodSugarByDate(mac, y_m_d);
        if (bloodSugarEntityQueryBloodSugarByDate != null) {
            String maxArray = bloodSugarEntityQueryBloodSugarByDate.getMaxArray();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Float>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryBloodSugarByDateDetail$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list = (List) jsonAdapterAdapter.fromJson(maxArray);
            String minArray = bloodSugarEntityQueryBloodSugarByDate.getMinArray();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Float>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryBloodSugarByDateDetail$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list2 = (List) jsonAdapterAdapter2.fromJson(minArray);
            if (list != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2 = i + 1;
                    float fFloatValue = ((Number) it.next()).floatValue();
                    if (fFloatValue > 0.0f) {
                        int i3 = i * CacheConstants.HOUR;
                        Intrinsics.checkNotNull(list2);
                        float f = 10;
                        arrayList.add(new BloodSugarDetailBean(i3, (((Number) list2.get(i)).floatValue() * 1.0f) / f, (fFloatValue * 1.0f) / f));
                    }
                    i = i2;
                }
            }
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryBloodSugarByDateDetail$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((BloodSugarDetailBean) t2).getSeconds()), Integer.valueOf(((BloodSugarDetailBean) t).getSeconds()));
                }
            });
        }
        return arrayList;
    }

    public final BloodSugarEntity queryLastBloodSugarDate() {
        return this.bloodSugarDao.queryBloodSugarByDateDesc(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final List<QBloodSugarLineChartHomeView.DataBean> queryLastBloodSugar(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        ArrayList arrayList = new ArrayList();
        BloodSugarEntity bloodSugarEntityQueryBloodSugarByDateDesc = this.bloodSugarDao.queryBloodSugarByDateDesc(mac);
        if (bloodSugarEntityQueryBloodSugarByDateDesc != null) {
            String maxArray = bloodSugarEntityQueryBloodSugarByDateDesc.getMaxArray();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Float>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryLastBloodSugar$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list = (List) jsonAdapterAdapter.fromJson(maxArray);
            String minArray = bloodSugarEntityQueryBloodSugarByDateDesc.getMinArray();
            JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends Float>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$queryLastBloodSugar$$inlined$fromJson$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List list2 = (List) jsonAdapterAdapter2.fromJson(minArray);
            if (list != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    float fFloatValue = ((Number) it.next()).floatValue();
                    int i2 = i * CacheConstants.HOUR;
                    Intrinsics.checkNotNull(list2);
                    float f = 10;
                    arrayList.add(new QBloodSugarLineChartHomeView.DataBean(i2, (((Number) list2.get(i)).floatValue() * 1.0f) / f, (fFloatValue * 1.0f) / f, (int) bloodSugarEntityQueryBloodSugarByDateDesc.getUnixTime()));
                    i++;
                }
            }
        }
        return arrayList;
    }

    public final void syncBloodSugar(int offset, final BaseDeviceResult<ReadBlePressureRsp> result) throws InterruptedException {
        Intrinsics.checkNotNullParameter(result, "result");
        LargeDataHandler.getInstance().syncBloodSugar(offset, new ILargeDataResponse() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, byte[] bArr) {
                BloodSugarRepository.m287syncBloodSugar$lambda1(this.f$0, result, i, bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncBloodSugar$lambda-1, reason: not valid java name */
    public static final void m287syncBloodSugar$lambda1(BloodSugarRepository this$0, BaseDeviceResult result, int i, byte[] bArr) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (i == 71) {
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
                ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<BloodSugarRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$syncBloodSugar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodSugarRepository bloodSugarRepository) {
                        invoke2(bloodSugarRepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodSugarRepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                        BloodSugarEntity bloodSugarEntity = new BloodSugarEntity(deviceAddressNoClear, y_m_d, MoshiUtilsKt.toJson(arrayList2), MoshiUtilsKt.toJson(arrayList), dateUtil.getZeroTime(), false, new DateUtil().getUnixTimestamp());
                        XLog.i(bloodSugarEntity);
                        ktxRunOnBgSingle.bloodSugarDao.insert(bloodSugarEntity);
                    }
                });
            }
            if (iBytesToInt == 0) {
                ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<BloodSugarRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository$syncBloodSugar$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodSugarRepository bloodSugarRepository) {
                        invoke2(bloodSugarRepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodSugarRepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        QcBloodSugarDao qcBloodSugarDao = ktxRunOnBgSingle.bloodSugarDao;
                        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d = new DateUtil().getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                        BloodSugarEntity bloodSugarEntityQueryBloodSugarByDate = qcBloodSugarDao.queryBloodSugarByDate(deviceAddressNoClear, y_m_d);
                        if (bloodSugarEntityQueryBloodSugarByDate != null) {
                            ktxRunOnBgSingle.bloodSugarDao.delete(bloodSugarEntityQueryBloodSugarByDate);
                        }
                    }
                });
            }
            result.result(0, new ReadBlePressureRsp());
        }
    }

    /* compiled from: BloodSugarRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BloodSugarRepository getGetInstance() {
            return (BloodSugarRepository) BloodSugarRepository.getInstance$delegate.getValue();
        }
    }
}
