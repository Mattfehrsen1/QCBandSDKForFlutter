package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.constant.CacheConstants;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcMenstruationDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.MenstruationEntity;
import com.qcwireless.qcwatch.ui.home.menstruation.bean.MenstruationBean;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
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
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: MenstruationRepository.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010%\n\u0002\b\u0003\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J+\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u0007\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012J&\u0010\u0015\u001a\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u00172\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H\u0002J!\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\b2\u0006\u0010\u001c\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ \u0010\u001e\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u001e\u0010 \u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00192\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000bJ\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0019J\b\u0010$\u001a\u0004\u0018\u00010\u001aJ\b\u0010%\u001a\u00020\u0012H\u0002J,\u0010&\u001a\u00020\u00142\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0(2\u0006\u0010)\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/MenstruationRepository;", "", "()V", "qcDeviceSettingDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDeviceSettingDao;", "qcMenstruationDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMenstruationDao;", "addMenstruationData", "Lkotlinx/coroutines/flow/Flow;", "", "", "", "d", "Lcom/qcwireless/qc_utils/date/DateUtil;", "(Lcom/qcwireless/qc_utils/date/DateUtil;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "check", "bean", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "addMenstruationFromLastTime", "", "addViewData", "showData", "", "list", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/MenstruationEntity;", "getMenstruationSetting", "mac", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertOne", "during", "loadMonthData", "year", "month", "loadPreData", "queryMaxStartTime", "queryMenstruationSetting", "showCurrMonth", "map", "", "lastAuntDate", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MenstruationRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<MenstruationRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MenstruationRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MenstruationRepository invoke() {
            return new MenstruationRepository();
        }
    });
    private final QcDeviceSettingDao qcDeviceSettingDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcDeviceSettingDao();
    private final QcMenstruationDao qcMenstruationDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcMenstruationDao();

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$2", f = "MenstruationRepository.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$2, reason: invalid class name and case insensitive filesystem */
    static final class C03752 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03752(String str, Continuation<? super C03752> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03752 c03752 = MenstruationRepository.this.new C03752(this.$mac, continuation);
            c03752.L$0 = obj;
            return c03752;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03752) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(MenstruationRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.MenstruationSetting), this) == coroutine_suspended) {
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

    public final Object getMenstruationSetting(String str, Continuation<? super Flow<MenstruationBean>> continuation) {
        final Flow flow = FlowKt.flow(new C03752(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<MenstruationBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super MenstruationBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1$2", f = "MenstruationRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) throws java.io.IOException {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L19:
                        java.lang.Object r7 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L66
                    L2a:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L32:
                        kotlin.ResultKt.throwOnFailure(r7)
                        kotlinx.coroutines.flow.FlowCollector r7 = r5.$this_unsafeFlow
                        r2 = r0
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity r6 = (com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity) r6
                        java.lang.String r6 = r6.getContent()
                        com.qcwireless.qcwatch.base.utils.MoshiUtils r2 = com.qcwireless.qcwatch.base.utils.MoshiUtils.INSTANCE
                        com.squareup.moshi.Moshi r2 = r2.getMoshiBuild()
                        com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$lambda-0$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$lambda-0$$inlined$fromJson$1
                        r4.<init>()
                        java.lang.reflect.Type r4 = r4.getType()
                        com.squareup.moshi.JsonAdapter r2 = r2.adapter(r4)
                        java.lang.String r4 = "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                        java.lang.Object r6 = r2.fromJson(r6)
                        r0.label = r3
                        java.lang.Object r6 = r7.emit(r6, r0)
                        if (r6 != r1) goto L66
                        return r1
                    L66:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03764(null)), Dispatchers.getIO()), new AnonymousClass5(str, null));
    }

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$4", f = "MenstruationRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$4, reason: invalid class name and case insensitive filesystem */
    static final class C03764 extends SuspendLambda implements Function2<FlowCollector<? super MenstruationBean>, Continuation<? super Unit>, Object> {
        int label;

        C03764(Continuation<? super C03764> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03764(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super MenstruationBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03764) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$5", f = "MenstruationRepository.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$getMenstruationSetting$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function3<FlowCollector<? super MenstruationBean>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(String str, Continuation<? super AnonymousClass5> continuation) {
            super(3, continuation);
            this.$mac = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super MenstruationBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass5 anonymousClass5 = MenstruationRepository.this.new AnonymousClass5(this.$mac, continuation);
            anonymousClass5.L$0 = flowCollector;
            return anonymousClass5.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                final MenstruationRepository menstruationRepository = MenstruationRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super MenstruationBean>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository.getMenstruationSetting.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super MenstruationBean> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super MenstruationBean> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        menstruationRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.MenstruationSetting, MoshiUtilsKt.toJson(new MenstruationBean(false, 0, 0, 0L, false, 0, 0, 0, 255, null))));
                    }
                });
                this.label = 1;
                if (flowCollector.emit(new MenstruationBean(false, 0, 0, 0L, false, 0, 0, 0, 255, null), this) == coroutine_suspended) {
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

    public final boolean addMenstruationData(DateUtil d, boolean check, MenstruationBean bean) {
        Intrinsics.checkNotNullParameter(d, "d");
        Intrinsics.checkNotNullParameter(bean, "bean");
        int year = d.getYear();
        int month = d.getMonth();
        long zeroTime = d.getZeroTime();
        List<MenstruationEntity> listQueryMenstruationByYearAndMonth = this.qcMenstruationDao.queryMenstruationByYearAndMonth(year, month);
        if (!listQueryMenstruationByYearAndMonth.isEmpty()) {
            for (MenstruationEntity menstruationEntity : listQueryMenstruationByYearAndMonth) {
                long endTime = menstruationEntity.getEndTime();
                long startTime = menstruationEntity.getStartTime();
                DateUtil dateUtil = new DateUtil(endTime, true);
                dateUtil.addDay(10);
                if (zeroTime < startTime) {
                    if (check) {
                        menstruationEntity.setStartTime(zeroTime);
                        this.qcMenstruationDao.insert(menstruationEntity);
                    }
                    return true;
                }
                if (startTime <= zeroTime && zeroTime <= endTime) {
                    if (check) {
                        menstruationEntity.setEndTime(zeroTime);
                        this.qcMenstruationDao.insert(menstruationEntity);
                        XLog.i(menstruationEntity);
                    }
                    return false;
                }
                if (zeroTime > endTime && zeroTime <= dateUtil.getZeroTime()) {
                    if (check) {
                        menstruationEntity.setEndTime(zeroTime);
                        this.qcMenstruationDao.insert(menstruationEntity);
                    }
                    return false;
                }
                if (zeroTime > dateUtil.getZeroTime()) {
                    if (listQueryMenstruationByYearAndMonth.size() <= 1) {
                        insertOne(d, bean.getDuring(), check);
                        return true;
                    }
                    XLog.i("continue");
                } else {
                    XLog.i("else");
                }
            }
            return true;
        }
        insertOne(d, bean.getDuring(), check);
        return true;
    }

    private final void insertOne(DateUtil d, int during, boolean check) {
        if (check) {
            DateUtil dateUtil = new DateUtil(d.getUnixTimestamp(), true);
            dateUtil.addDay(during - 1);
            String yyyyMMDate = d.getYyyyMMDate();
            Intrinsics.checkNotNullExpressionValue(yyyyMMDate, "d.yyyyMMDate");
            MenstruationEntity menstruationEntity = new MenstruationEntity(yyyyMMDate, d.getYear(), d.getMonth(), d.getZeroTime(), dateUtil.getZeroTime(), during);
            this.qcMenstruationDao.insert(menstruationEntity);
            XLog.i(menstruationEntity);
        }
    }

    public final void addMenstruationFromLastTime(final MenstruationBean bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        DateUtil dateUtil = new DateUtil(bean.getLastTime(), true);
        DateUtil dateUtil2 = new DateUtil(bean.getLastTime(), true);
        dateUtil2.addDay(-(bean.getDuring() - 1));
        String yyyyMMDate = dateUtil2.getYyyyMMDate();
        Intrinsics.checkNotNullExpressionValue(yyyyMMDate, "dStart.yyyyMMDate");
        final MenstruationEntity menstruationEntity = new MenstruationEntity(yyyyMMDate, dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getZeroTime(), dateUtil.getZeroTime(), bean.getDuring());
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MenstruationRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository.addMenstruationFromLastTime.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MenstruationRepository menstruationRepository) {
                invoke2(menstruationRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MenstruationRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.MenstruationSetting, MoshiUtilsKt.toJson(bean)));
                XLog.i(Boolean.valueOf(bean.getMenstruationSwitch()));
                ktxRunOnBgSingle.qcMenstruationDao.deleteAll();
                ktxRunOnBgSingle.qcMenstruationDao.insert(menstruationEntity);
            }
        });
    }

    public final List<DateUtil> loadMonthData(int year, int month) {
        DateUtil dateUtil = new DateUtil(year, month, 1);
        dateUtil.addMonth(1);
        DateUtil dateUtil2 = new DateUtil(year, month, 1);
        dateUtil2.addMonth(-1);
        DateUtil dateUtil3 = new DateUtil(dateUtil.getZeroTime(), true);
        DateUtil dateUtil4 = new DateUtil(dateUtil2.getZeroTime(), true);
        List<MenstruationEntity> listQueryMenstruationByYearAndMonth = this.qcMenstruationDao.queryMenstruationByYearAndMonth(year, month);
        List<MenstruationEntity> listQueryMenstruationByYearAndMonth2 = this.qcMenstruationDao.queryMenstruationByYearAndMonth(dateUtil3.getYear(), dateUtil3.getMonth());
        List<MenstruationEntity> listQueryMenstruationByYearAndMonth3 = this.qcMenstruationDao.queryMenstruationByYearAndMonth(dateUtil4.getYear(), dateUtil4.getMonth());
        ArrayList arrayList = new ArrayList();
        addViewData(arrayList, listQueryMenstruationByYearAndMonth);
        addViewData(arrayList, listQueryMenstruationByYearAndMonth2);
        addViewData(arrayList, listQueryMenstruationByYearAndMonth3);
        return arrayList;
    }

    private final void addViewData(List<DateUtil> showData, List<MenstruationEntity> list) {
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            MenstruationEntity menstruationEntity = list.get(i);
            long startTime = menstruationEntity.getStartTime();
            long endTime = menstruationEntity.getEndTime();
            if (startTime == endTime) {
                this.qcMenstruationDao.delete(menstruationEntity);
                return;
            }
            int iAbs = (int) Math.abs((endTime - startTime) / CacheConstants.DAY);
            DateUtil dateUtil = new DateUtil(startTime, true);
            showData.add(new DateUtil(dateUtil.getZeroTime(), true));
            for (int i2 = 0; i2 < iAbs; i2++) {
                dateUtil.addDay(1);
                showData.add(new DateUtil(dateUtil.getZeroTime(), true));
            }
        }
    }

    public final List<DateUtil> loadPreData() {
        ArrayList arrayList = new ArrayList();
        List<MenstruationEntity> listQueryAll = this.qcMenstruationDao.queryAll();
        if (!listQueryAll.isEmpty()) {
            int size = listQueryAll.size();
            for (int i = 0; i < size; i++) {
                DateUtil dateUtil = new DateUtil(listQueryAll.get(i).getStartTime(), true);
                dateUtil.addDay(-19);
                for (int i2 = 0; i2 < 10; i2++) {
                    arrayList.add(new DateUtil(dateUtil.getZeroTime(), true));
                    dateUtil.addDay(1);
                }
            }
        }
        return arrayList;
    }

    public final MenstruationEntity queryMaxStartTime() {
        return this.qcMenstruationDao.queryMaxByStartTime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MenstruationBean queryMenstruationSetting() {
        String content = this.qcDeviceSettingDao.queryByMacAndAction(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.MenstruationSetting).getContent();
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<MenstruationBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$queryMenstruationSetting$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        MenstruationBean menstruationBean = (MenstruationBean) jsonAdapterAdapter.fromJson(content);
        return menstruationBean == null ? new MenstruationBean(false, 0, 0, 0L, false, 0, 0, 0, 255, null) : menstruationBean;
    }

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$addMenstruationData$2", f = "MenstruationRepository.kt", i = {}, l = {238}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$addMenstruationData$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super Map<String, ? extends Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ DateUtil $d;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DateUtil dateUtil, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$d = dateUtil;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = MenstruationRepository.this.new AnonymousClass2(this.$d, continuation);
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
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                MenstruationBean menstruationBeanQueryMenstruationSetting = MenstruationRepository.this.queryMenstruationSetting();
                List<DateUtil> listLoadMonthData = MenstruationRepository.this.loadMonthData(this.$d.getYear(), this.$d.getMonth());
                MenstruationEntity menstruationEntityQueryMaxStartTime = MenstruationRepository.this.queryMaxStartTime();
                if (menstruationEntityQueryMaxStartTime != null) {
                    MenstruationRepository.this.showCurrMonth(linkedHashMap, new DateUtil(menstruationEntityQueryMaxStartTime.getStartTime(), true), menstruationBeanQueryMenstruationSetting);
                }
                List<DateUtil> listLoadPreData = MenstruationRepository.this.loadPreData();
                if (listLoadPreData != null && (!listLoadPreData.isEmpty())) {
                    int size = listLoadPreData.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        DateUtil dateUtil = listLoadPreData.get(i2);
                        String y_m_d = new DateUtil(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay()).getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(d2.year, d2.month, d2.day).y_M_D");
                        linkedHashMap.put(y_m_d, Boxing.boxInt(1));
                    }
                }
                if (listLoadMonthData != null && (!listLoadMonthData.isEmpty())) {
                    int size2 = listLoadMonthData.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        DateUtil dateUtil2 = listLoadMonthData.get(i3);
                        String y_m_d2 = new DateUtil(dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getDay()).getY_M_D();
                        Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil(d2.year, d2.month, d2.day).y_M_D");
                        linkedHashMap.put(y_m_d2, Boxing.boxInt(3));
                    }
                }
                this.label = 1;
                if (flowCollector.emit(linkedHashMap, this) == coroutine_suspended) {
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

    public final Object addMenstruationData(DateUtil dateUtil, Continuation<? super Flow<? extends Map<String, Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(dateUtil, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$addMenstruationData$3", f = "MenstruationRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$addMenstruationData$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super Map<String, ? extends Integer>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$addMenstruationData$4", f = "MenstruationRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository$addMenstruationData$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super Map<String, ? extends Integer>>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Map<String, ? extends Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Map<String, Integer>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Map<String, Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = th;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((Throwable) this.L$0).printStackTrace();
            XLog.i(Unit.INSTANCE);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCurrMonth(Map<String, Integer> map, DateUtil lastAuntDate, MenstruationBean bean) {
        int cycle = bean.getCycle();
        int during = bean.getDuring();
        for (int i = 0; i < 12; i++) {
            DateUtil dateUtil = new DateUtil(lastAuntDate.getTimestamp(), false);
            dateUtil.addDay(cycle * i);
            dateUtil.addDay(-19);
            for (int i2 = 0; i2 < 10; i2++) {
                String y_m_d = new DateUtil(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay()).getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil(d2.year, d2.month, d2.day).y_M_D");
                map.put(y_m_d, 1);
                dateUtil.addDay(1);
            }
        }
        int i3 = 0;
        while (i3 < 12) {
            DateUtil dateUtil2 = new DateUtil(lastAuntDate.getTimestamp(), false);
            i3++;
            dateUtil2.addDay(cycle * i3);
            for (int i4 = 0; i4 < during; i4++) {
                new DateUtil().setDay(1);
                String y_m_d2 = new DateUtil(dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getDay()).getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil(d1.year, d1.month, d1.day).y_M_D");
                map.put(y_m_d2, 2);
                dateUtil2.addDay(1);
            }
        }
    }

    /* compiled from: MenstruationRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/MenstruationRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/MenstruationRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/MenstruationRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MenstruationRepository getGetInstance() {
            return (MenstruationRepository) MenstruationRepository.getInstance$delegate.getValue();
        }
    }
}
