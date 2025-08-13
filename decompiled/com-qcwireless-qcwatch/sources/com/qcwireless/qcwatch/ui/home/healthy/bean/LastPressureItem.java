package com.qcwireless.qcwatch.ui.home.healthy.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.ui.base.repository.entity.PressureManualEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.view.QPressureLineChartHomeView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: LastPressureItem.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00000\u0019R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0013\"\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastPressureItem;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "()V", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QPressureLineChartHomeView$DataBean;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "dateStr", "", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "itemType", "", "getItemType", "()I", "value", "getValue", "setValue", "(I)V", "queryLastPressure", "Lkotlinx/coroutines/flow/Flow;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LastPressureItem implements MultiItemEntity {
    private List<? extends QPressureLineChartHomeView.DataBean> data;
    private String dateStr;
    private int value;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 14;
    }

    public LastPressureItem() {
        String y_m_d = new DateUtil().getY_M_D();
        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
        this.dateStr = y_m_d;
    }

    public final List<QPressureLineChartHomeView.DataBean> getData() {
        return this.data;
    }

    public final void setData(List<? extends QPressureLineChartHomeView.DataBean> list) {
        this.data = list;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final void setDateStr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dateStr = str;
    }

    /* compiled from: LastPressureItem.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastPressureItem;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem$queryLastPressure$1", f = "LastPressureItem.kt", i = {}, l = {43, 55, 65}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem$queryLastPressure$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<FlowCollector<? super LastPressureItem>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super LastPressureItem> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                List<QPressureLineChartHomeView.DataBean> listQueryLastPressure = PressureRepository.INSTANCE.getGetInstance().queryLastPressure();
                PressureManualEntity pressureManualEntityQueryAppPressure = PressureRepository.INSTANCE.getGetInstance().queryAppPressure();
                if (!listQueryLastPressure.isEmpty()) {
                    if (pressureManualEntityQueryAppPressure != null && pressureManualEntityQueryAppPressure.getTimestamp() > listQueryLastPressure.get(0).getUnixTime()) {
                        LastPressureItem lastPressureItem = new LastPressureItem();
                        if (Intrinsics.areEqual(pressureManualEntityQueryAppPressure.getDateStr(), new DateUtil(listQueryLastPressure.get(0).getUnixTime(), true).getY_M_D())) {
                            lastPressureItem.setData(listQueryLastPressure);
                        } else {
                            lastPressureItem.setData(new ArrayList());
                        }
                        lastPressureItem.setDateStr(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(pressureManualEntityQueryAppPressure.getTimestamp(), true)));
                        if (pressureManualEntityQueryAppPressure.getTimestamp() > listQueryLastPressure.get(0).getUnixTime() + listQueryLastPressure.get(listQueryLastPressure.size() - 1).getSeconds()) {
                            lastPressureItem.setValue(pressureManualEntityQueryAppPressure.getPressure());
                        } else {
                            lastPressureItem.setValue(listQueryLastPressure.get(listQueryLastPressure.size() - 1).getMaxValue());
                        }
                        this.label = 1;
                        if (flowCollector.emit(lastPressureItem, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        LastPressureItem lastPressureItem2 = new LastPressureItem();
                        lastPressureItem2.setData(listQueryLastPressure);
                        lastPressureItem2.setDateStr(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(listQueryLastPressure.get(0).getUnixTime(), true)));
                        int unixTime = listQueryLastPressure.get(0).getUnixTime() + listQueryLastPressure.get(listQueryLastPressure.size() - 1).getSeconds();
                        if (pressureManualEntityQueryAppPressure != null && pressureManualEntityQueryAppPressure.getTimestamp() > unixTime) {
                            lastPressureItem2.setValue(pressureManualEntityQueryAppPressure.getPressure());
                        } else {
                            lastPressureItem2.setValue(listQueryLastPressure.get(listQueryLastPressure.size() - 1).getMaxValue());
                        }
                        this.label = 2;
                        if (flowCollector.emit(lastPressureItem2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    PressureManualEntity pressureManualEntityQueryAppPressure2 = PressureRepository.INSTANCE.getGetInstance().queryAppPressure();
                    LastPressureItem lastPressureItem3 = new LastPressureItem();
                    if (pressureManualEntityQueryAppPressure2 != null) {
                        lastPressureItem3.setData(new ArrayList());
                        lastPressureItem3.setDateStr(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(pressureManualEntityQueryAppPressure2.getTimestamp(), true)));
                        lastPressureItem3.setValue(pressureManualEntityQueryAppPressure2.getPressure());
                    }
                    this.label = 3;
                    if (flowCollector.emit(lastPressureItem3, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 1 || i == 2 || i == 3) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        this.value = i;
    }

    public final Flow<LastPressureItem> queryLastPressure() {
        return FlowKt.flowOn(FlowKt.m2566catch(FlowKt.onStart(FlowKt.flow(new AnonymousClass1(null)), new AnonymousClass2(null)), new AnonymousClass3(null)), Dispatchers.getIO());
    }

    /* compiled from: LastPressureItem.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastPressureItem;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem$queryLastPressure$2", f = "LastPressureItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem$queryLastPressure$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super LastPressureItem>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super LastPressureItem> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: LastPressureItem.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastPressureItem;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem$queryLastPressure$3", f = "LastPressureItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem$queryLastPressure$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super LastPressureItem>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super LastPressureItem> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation);
            anonymousClass3.L$0 = th;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((Throwable) this.L$0).printStackTrace();
            return Unit.INSTANCE;
        }
    }
}
