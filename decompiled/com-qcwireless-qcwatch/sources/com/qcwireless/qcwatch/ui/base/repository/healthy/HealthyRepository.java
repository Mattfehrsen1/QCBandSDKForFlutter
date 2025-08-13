package com.qcwireless.qcwatch.ui.base.repository.healthy;

import androidx.exifinterface.media.ExifInterface;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.home.healthy.bean.DFUInformationBean;
import com.qcwireless.qcwatch.ui.home.healthy.bean.WatchFaceVersionBean;
import com.qcwireless.qcwatch.ui.mine.ai.bean.AiChatBean;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: HealthyRepository.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J3\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00062\u0006\u0010\u0010\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00062\u0006\u0010\u0010\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;", "", "()V", "qcDeviceSettingDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDeviceSettingDao;", "aiChatGPT", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;", "uid", "", "messages", "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFirmwareInfo", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/DFUInformationBean;", "mac", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalWatchFaceVersion", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/WatchFaceVersionBean;", "getTokenNotLogin", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDeviceSetting", "", "deviceSettingEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;", "(Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDfuInformation", "saveWatchFaceVersion", "watchFaceBean", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HealthyRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<HealthyRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<HealthyRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HealthyRepository invoke() {
            return new HealthyRepository();
        }
    });
    private final QcDeviceSettingDao qcDeviceSettingDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcDeviceSettingDao();

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$2", f = "HealthyRepository.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$2, reason: invalid class name and case insensitive filesystem */
    static final class C03582 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03582(String str, Continuation<? super C03582> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03582 c03582 = HealthyRepository.this.new C03582(this.$mac, continuation);
            c03582.L$0 = obj;
            return c03582;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03582) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(HealthyRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DFUInfo), this) == coroutine_suspended) {
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

    public final Object getFirmwareInfo(String str, Continuation<? super Flow<DFUInformationBean>> continuation) {
        final Flow flow = FlowKt.flow(new C03582(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DFUInformationBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super DFUInformationBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1$2", f = "HealthyRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$lambda-0$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$lambda-0$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03594(null)), Dispatchers.getIO()), new AnonymousClass5(str, null));
    }

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/DFUInformationBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$4", f = "HealthyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$4, reason: invalid class name and case insensitive filesystem */
    static final class C03594 extends SuspendLambda implements Function2<FlowCollector<? super DFUInformationBean>, Continuation<? super Unit>, Object> {
        int label;

        C03594(Continuation<? super C03594> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03594(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DFUInformationBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03594) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/DFUInformationBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$5", f = "HealthyRepository.kt", i = {}, l = {55}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getFirmwareInfo$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function3<FlowCollector<? super DFUInformationBean>, Throwable, Continuation<? super Unit>, Object> {
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
        public final Object invoke(FlowCollector<? super DFUInformationBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass5 anonymousClass5 = HealthyRepository.this.new AnonymousClass5(this.$mac, continuation);
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
                final HealthyRepository healthyRepository = HealthyRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super DFUInformationBean>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.getFirmwareInfo.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super DFUInformationBean> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super DFUInformationBean> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        healthyRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.DFUInfo, MoshiUtilsKt.toJson(new DFUInformationBean())));
                    }
                });
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new DFUInformationBean(), this) == coroutine_suspended) {
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

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2", f = "HealthyRepository.kt", i = {0, 1, 2, 3}, l = {61, 61, 63, 67}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow", "e"}, s = {"L$0", "L$0", "L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<AiChatBean>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<AiChatBean> $messages;
        final /* synthetic */ String $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, List<AiChatBean> list, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$uid = str;
            this.$messages = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$uid, this.$messages, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<AiChatBean>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x008c A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.flow.FlowCollector] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 0
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                if (r1 == 0) goto L3f
                if (r1 == r6) goto L37
                if (r1 == r5) goto L2f
                if (r1 == r4) goto L26
                if (r1 != r3) goto L1e
                java.lang.Object r0 = r11.L$0
                java.lang.Exception r0 = (java.lang.Exception) r0
                kotlin.ResultKt.throwOnFailure(r12)
                goto Lab
            L1e:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L26:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L8d
                goto Lae
            L2f:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L8d
                goto L76
            L37:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L8d
                goto L5f
            L3f:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                r1 = r12
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r12 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L8d
                com.qcwireless.qcwatch.ui.base.api.QcService r12 = r12.service()     // Catch: java.lang.Exception -> L8d
                java.lang.String r7 = r11.$uid     // Catch: java.lang.Exception -> L8d
                java.util.List<com.qcwireless.qcwatch.ui.mine.ai.bean.AiChatBean> r8 = r11.$messages     // Catch: java.lang.Exception -> L8d
                r9 = r11
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch: java.lang.Exception -> L8d
                r11.L$0 = r1     // Catch: java.lang.Exception -> L8d
                r11.label = r6     // Catch: java.lang.Exception -> L8d
                java.lang.Object r12 = r12.aiChatGPT(r7, r8, r9)     // Catch: java.lang.Exception -> L8d
                if (r12 != r0) goto L5f
                return r0
            L5f:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r12 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r12     // Catch: java.lang.Exception -> L8d
                com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$1 r6 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$1     // Catch: java.lang.Exception -> L8d
                r6.<init>(r1, r2)     // Catch: java.lang.Exception -> L8d
                kotlin.jvm.functions.Function3 r6 = (kotlin.jvm.functions.Function3) r6     // Catch: java.lang.Exception -> L8d
                r7 = r11
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L8d
                r11.L$0 = r1     // Catch: java.lang.Exception -> L8d
                r11.label = r5     // Catch: java.lang.Exception -> L8d
                java.lang.Object r12 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r12, r6, r7)     // Catch: java.lang.Exception -> L8d
                if (r12 != r0) goto L76
                return r0
            L76:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r12 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r12     // Catch: java.lang.Exception -> L8d
                com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$2 r5 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$2     // Catch: java.lang.Exception -> L8d
                r5.<init>(r1, r2)     // Catch: java.lang.Exception -> L8d
                kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5     // Catch: java.lang.Exception -> L8d
                r2 = r11
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> L8d
                r11.L$0 = r1     // Catch: java.lang.Exception -> L8d
                r11.label = r4     // Catch: java.lang.Exception -> L8d
                java.lang.Object r12 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r12, r5, r2)     // Catch: java.lang.Exception -> L8d
                if (r12 != r0) goto Lae
                return r0
            L8d:
                r12 = move-exception
                com.qcwireless.qcwatch.ui.base.repository.mine.NetState r2 = new com.qcwireless.qcwatch.ui.base.repository.mine.NetState
                r5 = 0
                r6 = 0
                r7 = -11111(0xffffffffffffd499, float:NaN)
                r8 = 0
                r9 = 11
                r10 = 0
                r4 = r2
                r4.<init>(r5, r6, r7, r8, r9, r10)
                r4 = r11
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r11.L$0 = r12
                r11.label = r3
                java.lang.Object r1 = r1.emit(r2, r4)
                if (r1 != r0) goto Laa
                return r0
            Laa:
                r0 = r12
            Lab:
                r0.printStackTrace()
            Lae:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: HealthyRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "tokenResp", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$1", f = "HealthyRepository.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, AiChatBean, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<AiChatBean>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<AiChatBean>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, AiChatBean aiChatBean, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = aiChatBean;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AiChatBean aiChatBean = (AiChatBean) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, aiChatBean, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: HealthyRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$2", f = "HealthyRepository.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00562 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<AiChatBean>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00562(FlowCollector<? super NetState<AiChatBean>> flowCollector, Continuation<? super C00562> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00562 c00562 = new C00562(this.$$this$flow, continuation);
                c00562.I$0 = i;
                return c00562.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object aiChatGPT(String str, List<AiChatBean> list, Continuation<? super Flow<NetState<AiChatBean>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(str, list, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$3", f = "HealthyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<AiChatBean>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<AiChatBean>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$4", f = "HealthyRepository.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$aiChatGPT$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<AiChatBean>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<AiChatBean>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = flowCollector;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    public final void saveDfuInformation(final DeviceSettingEntity deviceSettingEntity) {
        Intrinsics.checkNotNullParameter(deviceSettingEntity, "deviceSettingEntity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.saveDfuInformation.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyRepository healthyRepository) {
                invoke2(healthyRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(deviceSettingEntity);
            }
        });
    }

    public final void saveWatchFaceVersion(final WatchFaceVersionBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.saveWatchFaceVersion.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyRepository healthyRepository) {
                invoke2(healthyRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchFaceMarketVersion, MoshiUtilsKt.toJson(watchFaceBean)));
            }
        });
    }

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$2", f = "HealthyRepository.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$2, reason: invalid class name and case insensitive filesystem */
    static final class C03602 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03602(String str, Continuation<? super C03602> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03602 c03602 = HealthyRepository.this.new C03602(this.$mac, continuation);
            c03602.L$0 = obj;
            return c03602;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03602) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(HealthyRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.WatchFaceMarketVersion), this) == coroutine_suspended) {
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

    public final Object getLocalWatchFaceVersion(String str, Continuation<? super Flow<WatchFaceVersionBean>> continuation) {
        final Flow flow = FlowKt.flow(new C03602(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<WatchFaceVersionBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super WatchFaceVersionBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1$2", f = "HealthyRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$lambda-1$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$lambda-1$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03614(null)), Dispatchers.getIO()), new C03625(str, null));
    }

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/WatchFaceVersionBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$4", f = "HealthyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$4, reason: invalid class name and case insensitive filesystem */
    static final class C03614 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceVersionBean>, Continuation<? super Unit>, Object> {
        int label;

        C03614(Continuation<? super C03614> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03614(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceVersionBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03614) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/WatchFaceVersionBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$5", f = "HealthyRepository.kt", i = {}, l = {105}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getLocalWatchFaceVersion$5, reason: invalid class name and case insensitive filesystem */
    static final class C03625 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceVersionBean>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03625(String str, Continuation<? super C03625> continuation) {
            super(3, continuation);
            this.$mac = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super WatchFaceVersionBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03625 c03625 = HealthyRepository.this.new C03625(this.$mac, continuation);
            c03625.L$0 = flowCollector;
            return c03625.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                final HealthyRepository healthyRepository = HealthyRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super WatchFaceVersionBean>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.getLocalWatchFaceVersion.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super WatchFaceVersionBean> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super WatchFaceVersionBean> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        healthyRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.WatchFaceMarketVersion, MoshiUtilsKt.toJson(new WatchFaceVersionBean(UserConfig.INSTANCE.getInstance().getHwVersion(), "0"))));
                    }
                });
                this.label = 1;
                if (flowCollector.emit(new WatchFaceVersionBean(UserConfig.INSTANCE.getInstance().getHwVersion(), "0"), this) == coroutine_suspended) {
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

    public final Object saveDeviceSetting(final DeviceSettingEntity deviceSettingEntity, Continuation<? super Unit> continuation) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.saveDeviceSetting.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyRepository healthyRepository) {
                invoke2(healthyRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(deviceSettingEntity);
            }
        });
        return Unit.INSTANCE;
    }

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2", f = "HealthyRepository.kt", i = {0, 1}, l = {117, 117, 119}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2, reason: invalid class name and case insensitive filesystem */
    static final class C03632 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03632(Continuation<? super C03632> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03632 c03632 = new C03632(continuation);
            c03632.L$0 = obj;
            return c03632;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03632) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007e A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L30
                if (r1 == r4) goto L28
                if (r1 == r3) goto L20
                if (r1 != r2) goto L18
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L82
            L16:
                r10 = move-exception
                goto L7f
            L18:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L20:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L68
            L28:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L51
            L30:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L16
                java.lang.String r6 = "qcwx_android"
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L16
                r9.L$0 = r10     // Catch: java.lang.Exception -> L16
                r9.label = r4     // Catch: java.lang.Exception -> L16
                java.lang.Object r1 = r1.getToken(r6, r7)     // Catch: java.lang.Exception -> L16
                if (r1 != r0) goto L4e
                return r0
            L4e:
                r8 = r1
                r1 = r10
                r10 = r8
            L51:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$1     // Catch: java.lang.Exception -> L16
                r4.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L16
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L16
                r9.L$0 = r1     // Catch: java.lang.Exception -> L16
                r9.label = r3     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r6)     // Catch: java.lang.Exception -> L16
                if (r10 != r0) goto L68
                return r0
            L68:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$2     // Catch: java.lang.Exception -> L16
                r3.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L16
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L16
                r9.L$0 = r5     // Catch: java.lang.Exception -> L16
                r9.label = r2     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L16
                if (r10 != r0) goto L82
                return r0
            L7f:
                r10.printStackTrace()
            L82:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository.C03632.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: HealthyRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "tokenResp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$1", f = "HealthyRepository.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, String str, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = str;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, str, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: HealthyRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$2", f = "HealthyRepository.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00572 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00572(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super C00572> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00572 c00572 = new C00572(this.$$this$flow, continuation);
                c00572.I$0 = i;
                return c00572.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object getTokenNotLogin(Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03632(null)), new C03643(null)), Dispatchers.getIO()), new C03654(null));
    }

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$3", f = "HealthyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$3, reason: invalid class name and case insensitive filesystem */
    static final class C03643 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C03643(Continuation<? super C03643> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03643(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03643) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$4", f = "HealthyRepository.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository$getTokenNotLogin$4, reason: invalid class name and case insensitive filesystem */
    static final class C03654 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03654(Continuation<? super C03654> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03654 c03654 = new C03654(continuation);
            c03654.L$0 = flowCollector;
            return c03654.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: HealthyRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HealthyRepository getGetInstance() {
            return (HealthyRepository) HealthyRepository.getInstance$delegate.getValue();
        }
    }
}
