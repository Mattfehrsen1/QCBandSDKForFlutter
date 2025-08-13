package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.exifinterface.media.ExifInterface;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastOneKeyBean;
import com.squareup.moshi.JsonAdapter;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
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

/* compiled from: OneKeyCheckRepository.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/OneKeyCheckRepository;", "", "()V", "qcDeviceSettingDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDeviceSettingDao;", "getLastOneKeyCheck", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "mac", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryLast", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OneKeyCheckRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<OneKeyCheckRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<OneKeyCheckRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final OneKeyCheckRepository invoke() {
            return new OneKeyCheckRepository();
        }
    });
    private final QcDeviceSettingDao qcDeviceSettingDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcDeviceSettingDao();

    /* compiled from: OneKeyCheckRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$2", f = "OneKeyCheckRepository.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = OneKeyCheckRepository.this.new AnonymousClass2(this.$mac, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(OneKeyCheckRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.LastOneKeyCheck), this) == coroutine_suspended) {
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

    public final Object getLastOneKeyCheck(String str, Continuation<? super Flow<LastOneKeyBean>> continuation) {
        final Flow flow = FlowKt.flow(new AnonymousClass2(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<LastOneKeyBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super LastOneKeyBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1$2", f = "OneKeyCheckRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$lambda-0$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$lambda-0$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new AnonymousClass4(null)), Dispatchers.getIO()), new AnonymousClass5(str, null));
    }

    /* compiled from: OneKeyCheckRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$4", f = "OneKeyCheckRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<FlowCollector<? super LastOneKeyBean>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super LastOneKeyBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: OneKeyCheckRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$5", f = "OneKeyCheckRepository.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$getLastOneKeyCheck$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function3<FlowCollector<? super LastOneKeyBean>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(String str, Continuation<? super AnonymousClass5> continuation) {
            super(3, continuation);
            this.$mac = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super LastOneKeyBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass5 anonymousClass5 = OneKeyCheckRepository.this.new AnonymousClass5(this.$mac, continuation);
            anonymousClass5.L$0 = flowCollector;
            anonymousClass5.L$1 = th;
            return anonymousClass5.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                XLog.i(Unit.INSTANCE);
                final OneKeyCheckRepository oneKeyCheckRepository = OneKeyCheckRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super LastOneKeyBean>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository.getLastOneKeyCheck.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super LastOneKeyBean> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super LastOneKeyBean> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        oneKeyCheckRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.LastOneKeyCheck, MoshiUtilsKt.toJson(new LastOneKeyBean())));
                    }
                });
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new LastOneKeyBean(), this) == coroutine_suspended) {
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

    public final LastOneKeyBean queryLast() {
        String content = this.qcDeviceSettingDao.queryByMacAndAction(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.LastOneKeyCheck).getContent();
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<LastOneKeyBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository$queryLast$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return (LastOneKeyBean) jsonAdapterAdapter.fromJson(content);
    }

    /* compiled from: OneKeyCheckRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/OneKeyCheckRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/OneKeyCheckRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/OneKeyCheckRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OneKeyCheckRepository getGetInstance() {
            return (OneKeyCheckRepository) OneKeyCheckRepository.getInstance$delegate.getValue();
        }
    }
}
