package com.qcwireless.qcwatch.ui.base.repository.healthy;

import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.file.SimpleCallback;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.TemperatureDownResp;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcTemperatureDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.BodyTemperatureEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineHomeChartView;
import com.qcwireless.qcwatch.ui.home.temperature.bean.TemperatureDetailBean;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.realsil.sdk.bbpro.core.protocol.Contract;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: TemperatureRepository.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r2\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\rJ\u0006\u0010\u0015\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0006J\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001c0\u001bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;", "", "()V", "temperatureDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcTemperatureDao;", "downTemperatureFromServer", "", "uid", "", "lastSyncId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initData", "queryTemperatureByDate", "", "Lcom/qcwireless/qcwatch/ui/base/view/QTemperatureLineChartView$TemperatureDataBean;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "queryTemperatureDetailByDate", "Lcom/qcwireless/qcwatch/ui/home/temperature/bean/TemperatureDetailBean;", "queryTemperatureLast", "Lcom/qcwireless/qcwatch/ui/base/view/QTemperatureLineHomeChartView$TemperatureDataBean;", "syncAutoCheckTemperature", "syncSingleCheckTemperature", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "", "syncTodayTemperature", "updateTemperatureDetailToServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Callback", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<TemperatureRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<TemperatureRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TemperatureRepository invoke() {
            return new TemperatureRepository();
        }
    });
    private final QcTemperatureDao temperatureDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcTemperatureDao();

    public final void initData() {
        FileHandle.getInstance().clearCallback();
        FileHandle.getInstance().registerCallback(new Callback());
        FileHandle.getInstance().initRegister();
    }

    public final void syncSingleCheckTemperature(int index) {
        FileHandle.getInstance().startObtainTemperatureOnce(index);
    }

    public final void syncAutoCheckTemperature() {
        QcTemperatureDao qcTemperatureDao = this.temperatureDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = new DateUtil().getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
        BodyTemperatureEntity bodyTemperatureEntityQueryTemperatureLastByDate = qcTemperatureDao.queryTemperatureLastByDate(deviceAddressNoClear, y_m_d);
        if (bodyTemperatureEntityQueryTemperatureLastByDate == null) {
            FileHandle.getInstance().startObtainTemperatureSeries(6);
        } else if (DateUtil.isSameDay(new Date(), new DateUtil(bodyTemperatureEntityQueryTemperatureLastByDate.getLastSyncTime(), true).toDate())) {
            FileHandle.getInstance().startObtainTemperatureSeries(2);
        } else {
            FileHandle.getInstance().startObtainTemperatureSeries(6);
        }
    }

    public final void syncTodayTemperature() {
        FileHandle.getInstance().startObtainTemperatureSeries(0);
    }

    public final List<QTemperatureLineChartView.TemperatureDataBean> queryTemperatureByDate(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        ArrayList arrayList = new ArrayList();
        QcTemperatureDao qcTemperatureDao = this.temperatureDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = dateUtil.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
        List<BodyTemperatureEntity> listQueryTemperatureList = qcTemperatureDao.queryTemperatureList(deviceAddressNoClear, y_m_d);
        if (listQueryTemperatureList != null) {
            for (BodyTemperatureEntity bodyTemperatureEntity : listQueryTemperatureList) {
                if (bodyTemperatureEntity.getTemperature() >= 35.0f) {
                    arrayList.add(new QTemperatureLineChartView.TemperatureDataBean(bodyTemperatureEntity.getMin(), bodyTemperatureEntity.getTemperature(), false));
                }
            }
        }
        return arrayList;
    }

    public final List<TemperatureDetailBean> queryTemperatureDetailByDate(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        ArrayList arrayList = new ArrayList();
        QcTemperatureDao qcTemperatureDao = this.temperatureDao;
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String y_m_d = dateUtil.getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
        List<BodyTemperatureEntity> listQueryTemperatureListDesc = qcTemperatureDao.queryTemperatureListDesc(deviceAddressNoClear, y_m_d);
        if (listQueryTemperatureListDesc != null) {
            for (BodyTemperatureEntity bodyTemperatureEntity : listQueryTemperatureListDesc) {
                if (bodyTemperatureEntity.getTemperature() >= 35.0f) {
                    arrayList.add(new TemperatureDetailBean(bodyTemperatureEntity.getMin(), bodyTemperatureEntity.getTemperature()));
                }
            }
        }
        return arrayList;
    }

    public final List<QTemperatureLineHomeChartView.TemperatureDataBean> queryTemperatureLast() {
        List<BodyTemperatureEntity> listQueryTemperatureList;
        ArrayList arrayList = new ArrayList();
        BodyTemperatureEntity bodyTemperatureEntityQueryTemperatureLast = this.temperatureDao.queryTemperatureLast(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        if (bodyTemperatureEntityQueryTemperatureLast != null && (listQueryTemperatureList = this.temperatureDao.queryTemperatureList(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), bodyTemperatureEntityQueryTemperatureLast.getDateStr())) != null) {
            for (BodyTemperatureEntity bodyTemperatureEntity : listQueryTemperatureList) {
                if (bodyTemperatureEntity.getTemperature() >= 35.0f) {
                    arrayList.add(new QTemperatureLineHomeChartView.TemperatureDataBean(bodyTemperatureEntity.getMin(), bodyTemperatureEntity.getTemperature(), false, bodyTemperatureEntity.getUnixTime()));
                }
            }
        }
        return arrayList;
    }

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;)V", "onUpdateTemperature", "", "data", "Lcom/oudmon/ble/base/communication/dfu_temperature/TemperatureEntity;", "onUpdateTemperatureList", "array", "", "Lcom/oudmon/ble/base/communication/dfu_temperature/TemperatureOnceEntity;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdateTemperature(final TemperatureEntity data) {
            final TemperatureRepository temperatureRepository = TemperatureRepository.this;
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<Callback, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$Callback$onUpdateTemperature$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TemperatureRepository.Callback callback) {
                    invoke2(callback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TemperatureRepository.Callback ktxRunOnBgSingle) {
                    DateUtil dateUtil;
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    int i = 0;
                    if (data == null) {
                        QcTemperatureDao qcTemperatureDao = temperatureRepository.temperatureDao;
                        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d = new DateUtil().getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                        List<BodyTemperatureEntity> listQueryTemperatureListByFlag = qcTemperatureDao.queryTemperatureListByFlag(deviceAddressNoClear, y_m_d, 0);
                        if (listQueryTemperatureListByFlag != null) {
                            temperatureRepository.temperatureDao.deleteList(TypeIntrinsics.asMutableList(listQueryTemperatureListByFlag));
                            return;
                        }
                        return;
                    }
                    DateUtil dateUtil2 = new DateUtil();
                    dateUtil2.addDay(-data.mIndex);
                    int i2 = data.mTimeSpan;
                    float[] fArr = data.mValues;
                    Intrinsics.checkNotNullExpressionValue(fArr, "data.mValues");
                    int length = fArr.length;
                    while (i < length) {
                        float f = fArr[i];
                        if (f > 0.0f) {
                            String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                            String y_m_d2 = dateUtil2.getY_M_D();
                            Intrinsics.checkNotNullExpressionValue(y_m_d2, "date.y_M_D");
                            dateUtil = dateUtil2;
                            temperatureRepository.temperatureDao.insert(new BodyTemperatureEntity(deviceAddressNoClear2, y_m_d2, dateUtil2.getZeroTime(), f, i * i2, false, 0, new DateUtil().getUnixTimestamp()));
                        } else {
                            dateUtil = dateUtil2;
                        }
                        i++;
                        dateUtil2 = dateUtil;
                    }
                }
            });
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdateTemperatureList(final List<TemperatureOnceEntity> array) {
            final TemperatureRepository temperatureRepository = TemperatureRepository.this;
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<Callback, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$Callback$onUpdateTemperatureList$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TemperatureRepository.Callback callback) {
                    invoke2(callback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TemperatureRepository.Callback ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    List<TemperatureOnceEntity> list = array;
                    if (list == null) {
                        QcTemperatureDao qcTemperatureDao = temperatureRepository.temperatureDao;
                        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                        String y_m_d = new DateUtil().getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                        List<BodyTemperatureEntity> listQueryTemperatureListByFlag = qcTemperatureDao.queryTemperatureListByFlag(deviceAddressNoClear, y_m_d, 1);
                        if (listQueryTemperatureListByFlag != null) {
                            temperatureRepository.temperatureDao.deleteList(TypeIntrinsics.asMutableList(listQueryTemperatureListByFlag));
                        }
                        EventBus.getDefault().post(new ManualRefreshEvent());
                        return;
                    }
                    if (list.size() > 0) {
                        List<TemperatureOnceEntity> list2 = array;
                        TemperatureRepository temperatureRepository2 = temperatureRepository;
                        for (TemperatureOnceEntity temperatureOnceEntity : list2) {
                            DateUtil dateUtil = new DateUtil(temperatureOnceEntity.mTime, true);
                            String deviceAddressNoClear2 = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                            String y_m_d2 = dateUtil.getY_M_D();
                            Intrinsics.checkNotNullExpressionValue(y_m_d2, "date.y_M_D");
                            temperatureRepository2.temperatureDao.insert(new BodyTemperatureEntity(deviceAddressNoClear2, y_m_d2, dateUtil.getZeroTime(), temperatureOnceEntity.mValue, dateUtil.getTodayMin(), false, 1, new DateUtil().getUnixTimestamp()));
                        }
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                }
            });
        }
    }

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$2", f = "TemperatureRepository.kt", i = {0, 1}, l = {251, 251, 253}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C04092 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04092(Continuation<? super C04092> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04092 c04092 = TemperatureRepository.this.new C04092(continuation);
            c04092.L$0 = obj;
            return c04092;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04092) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x0165 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instructions count: 384
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository.C04092.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: TemperatureRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$2$2", f = "TemperatureRepository.kt", i = {}, l = {252}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00712 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00712(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00712> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new C00712(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
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

        /* compiled from: TemperatureRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$2$3", f = "TemperatureRepository.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$2$3, reason: invalid class name */
        static final class AnonymousClass3 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass3(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass3> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$$this$flow, continuation);
                anonymousClass3.I$0 = i;
                return anonymousClass3.invokeSuspend(Unit.INSTANCE);
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

    public final Object updateTemperatureDetailToServer(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04092(null)), new C04103(null)), Dispatchers.getIO()), new C04114(null));
    }

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$3", f = "TemperatureRepository.kt", i = {}, l = {Contract.CMD_SET_VERSION.V1_2}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C04103 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04103(Continuation<? super C04103> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04103 c04103 = new C04103(continuation);
            c04103.L$0 = obj;
            return c04103;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04103) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$4", f = "TemperatureRepository.kt", i = {}, l = {261}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$updateTemperatureDetailToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C04114 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04114(Continuation<? super C04114> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04114 c04114 = new C04114(continuation);
            c04114.L$0 = flowCollector;
            c04114.L$1 = th;
            return c04114.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/TemperatureDownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2", f = "TemperatureRepository.kt", i = {0, 1}, l = {266, 266, 268}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends TemperatureDownResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends TemperatureDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<TemperatureDownResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<TemperatureDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
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
                com.qcwireless.qcwatch.ui.base.bean.request.healthy.TemperatureDownloadRequest r1 = new com.qcwireless.qcwatch.ui.base.bean.request.healthy.TemperatureDownloadRequest
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
                java.lang.Object r1 = r6.downTemperature(r1, r7)
                if (r1 != r0) goto L56
                return r0
            L56:
                r12 = r1
                r1 = r14
                r14 = r12
            L59:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r14 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r14
                com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$1
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
                com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$2
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
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: TemperatureRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/TemperatureDownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$1", f = "TemperatureRepository.kt", i = {}, l = {267}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends TemperatureDownResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<TemperatureDownResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<TemperatureDownResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends TemperatureDownResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<TemperatureDownResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<TemperatureDownResp> list, Continuation<? super Unit> continuation) {
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

        /* compiled from: TemperatureRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$2", f = "TemperatureRepository.kt", i = {}, l = {269}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00702 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<TemperatureDownResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00702(FlowCollector<? super NetState<List<TemperatureDownResp>>> flowCollector, Continuation<? super C00702> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00702 c00702 = new C00702(this.$$this$flow, continuation);
                c00702.I$0 = i;
                return c00702.invokeSuspend(Unit.INSTANCE);
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

    public final Object downTemperatureFromServer(long j, long j2, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(j, j2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository.downTemperatureFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<TemperatureDownResp>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<TemperatureDownResp>> netState, Continuation<? super Unit> continuation2) {
                List<TemperatureDownResp> listIsSuccess;
                if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                    try {
                        for (TemperatureDownResp temperatureDownResp : listIsSuccess) {
                            Iterator<Float> it = temperatureDownResp.getTemperatures().iterator();
                            int i = 0;
                            while (it.hasNext()) {
                                int i2 = i + 1;
                                float fFloatValue = it.next().floatValue();
                                if (fFloatValue > 32.0f) {
                                    DateUtil dateUtil = DateUtil.parse(temperatureDownResp.getDateStr(), DateUtil.DateFormater.dFyyyyMMdd);
                                    String deviceAddress = temperatureDownResp.getDeviceAddress();
                                    Intrinsics.checkNotNull(deviceAddress);
                                    try {
                                        TemperatureRepository.this.temperatureDao.insert(new BodyTemperatureEntity(deviceAddress, temperatureDownResp.getDateStr(), dateUtil.getZeroTime(), fFloatValue, i, true, 0, new DateUtil().getUnixTimestamp()));
                                    } catch (Exception e) {
                                        e = e;
                                        e.printStackTrace();
                                        return Unit.INSTANCE;
                                    }
                                }
                                i = i2;
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

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/TemperatureDownResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$3", f = "TemperatureRepository.kt", i = {}, l = {272}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends TemperatureDownResp>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends TemperatureDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<TemperatureDownResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<TemperatureDownResp>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/TemperatureDownResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$4", f = "TemperatureRepository.kt", i = {}, l = {275}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository$downTemperatureFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends TemperatureDownResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends TemperatureDownResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<TemperatureDownResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<TemperatureDownResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: TemperatureRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TemperatureRepository getGetInstance() {
            return (TemperatureRepository) TemperatureRepository.getInstance$delegate.getValue();
        }
    }
}
