package com.qcwireless.qcwatch.ui.home.healthy.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean;
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

/* compiled from: LastSleepItem.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastSleepItem;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "()V", "itemType", "", "getItemType", "()I", "calcLastSleep", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LastSleepItem implements MultiItemEntity {
    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 2;
    }

    /* compiled from: LastSleepItem.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem$calcLastSleep$1", f = "LastSleepItem.kt", i = {}, l = {31, 33, 58, 62}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem$calcLastSleep$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<FlowCollector<? super SleepViewBean>, Continuation<? super Unit>, Object> {
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
        public final Object invoke(FlowCollector<? super SleepViewBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                    if (SleepDetailRepository.INSTANCE.getGetInstance().queryLastSleepNewProtocol() != null) {
                        this.label = 1;
                        if (flowCollector.emit(SleepDetailRepository.INSTANCE.getGetInstance().querySleepNewProtocol(new DateUtil(r3.getEt(), true)), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.label = 2;
                        if (flowCollector.emit(new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    SleepDetail sleepDetailQueryLastSleep = SleepDetailRepository.INSTANCE.getGetInstance().queryLastSleep();
                    if (sleepDetailQueryLastSleep != null) {
                        DateUtil dateUtil = new DateUtil(DateUtil.String2Date("yyyy-MM-dd", sleepDetailQueryLastSleep.getDateStr()));
                        SleepDetailRepository getInstance = SleepDetailRepository.INSTANCE.getGetInstance();
                        String y_m_d = dateUtil.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
                        SleepDetail sleepDetailQuerySleepByDate = getInstance.querySleepByDate(y_m_d);
                        DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                        dateUtil2.addDay(-1);
                        SleepDetailRepository getInstance2 = SleepDetailRepository.INSTANCE.getGetInstance();
                        String y_m_d2 = dateUtil2.getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d2, "yesDate.y_M_D");
                        SleepDetail sleepDetailQuerySleepByDate2 = getInstance2.querySleepByDate(y_m_d2);
                        if (sleepDetailQuerySleepByDate2 == null) {
                            sleepDetailQuerySleepByDate2 = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                        }
                        if (sleepDetailQuerySleepByDate != null) {
                            SleepViewBean sleepViewBeanCalcSleepViewData = SleepDetailRepository.INSTANCE.getGetInstance().calcSleepViewData(sleepDetailQuerySleepByDate, sleepDetailQuerySleepByDate2);
                            if (sleepViewBeanCalcSleepViewData.getTotalSleep() > 0) {
                                this.label = 3;
                                if (flowCollector.emit(sleepViewBeanCalcSleepViewData, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    } else {
                        this.label = 4;
                        if (flowCollector.emit(new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
            } else if (i == 1 || i == 2 || i == 3 || i == 4) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final Flow<SleepViewBean> calcLastSleep() {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass1(null)), new AnonymousClass2(null)), Dispatchers.getIO()), new AnonymousClass3(null));
    }

    /* compiled from: LastSleepItem.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem$calcLastSleep$2", f = "LastSleepItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem$calcLastSleep$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super SleepViewBean>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super SleepViewBean> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: LastSleepItem.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem$calcLastSleep$3", f = "LastSleepItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem$calcLastSleep$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super SleepViewBean>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super SleepViewBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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
