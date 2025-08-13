package com.qcwireless.qcwatch.ui.base.repository.ws;

import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.cs.SupportCsResp;
import com.qcwireless.qcwatch.ui.base.bean.response.cs.WsChatMessage;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: WebsocketRepository.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u00042\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00060\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/ws/WebsocketRepository;", "", "()V", "getChatList", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/WsChatMessage;", "contactId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCsSupport", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeleteMessage", "", "getUnReadMessage", "getWebsocketContactId", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebsocketRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<WebsocketRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<WebsocketRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final WebsocketRepository invoke() {
            return new WebsocketRepository();
        }
    });

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2", f = "WebsocketRepository.kt", i = {0, 1}, l = {19, 19, 23}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2, reason: invalid class name and case insensitive filesystem */
    static final class C04842 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04842(Continuation<? super C04842> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04842 c04842 = new C04842(continuation);
            c04842.L$0 = obj;
            return c04842;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04842) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0091 A[RETURN] */
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
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L32
                if (r1 == r4) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L17
                goto L95
            L17:
                r12 = move-exception
                goto L92
            L1a:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L22:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L17
                goto L7b
            L2a:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L17
                goto L64
            L32:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r6 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig r6 = r6.getInstance()     // Catch: java.lang.Exception -> L17
                long r6 = r6.getUid()     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r8 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig r8 = r8.getInstance()     // Catch: java.lang.Exception -> L17
                java.lang.String r8 = r8.getHwVersion()     // Catch: java.lang.Exception -> L17
                r9 = r11
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch: java.lang.Exception -> L17
                r11.L$0 = r12     // Catch: java.lang.Exception -> L17
                r11.label = r4     // Catch: java.lang.Exception -> L17
                java.lang.Object r1 = r1.contactId(r6, r8, r9)     // Catch: java.lang.Exception -> L17
                if (r1 != r0) goto L61
                return r0
            L61:
                r10 = r1
                r1 = r12
                r12 = r10
            L64:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r12 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r12     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$1     // Catch: java.lang.Exception -> L17
                r4.<init>(r1, r5)     // Catch: java.lang.Exception -> L17
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L17
                r6 = r11
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L17
                r11.L$0 = r1     // Catch: java.lang.Exception -> L17
                r11.label = r3     // Catch: java.lang.Exception -> L17
                java.lang.Object r12 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r12, r4, r6)     // Catch: java.lang.Exception -> L17
                if (r12 != r0) goto L7b
                return r0
            L7b:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r12 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r12     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$2     // Catch: java.lang.Exception -> L17
                r3.<init>(r1, r5)     // Catch: java.lang.Exception -> L17
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L17
                r1 = r11
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L17
                r11.L$0 = r5     // Catch: java.lang.Exception -> L17
                r11.label = r2     // Catch: java.lang.Exception -> L17
                java.lang.Object r12 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r12, r3, r1)     // Catch: java.lang.Exception -> L17
                if (r12 != r0) goto L95
                return r0
            L92:
                r12.printStackTrace()
            L95:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository.C04842.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$1", f = "WebsocketRepository.kt", i = {}, l = {22}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$1, reason: invalid class name */
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
                    UserConfig.INSTANCE.getInstance().setContactId(str);
                    UserConfig.INSTANCE.getInstance().save();
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

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$2", f = "WebsocketRepository.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00972 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00972(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super C00972> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00972 c00972 = new C00972(this.$$this$flow, continuation);
                c00972.I$0 = i;
                return c00972.invokeSuspend(Unit.INSTANCE);
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

    public final Object getWebsocketContactId(Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04842(null)), new C04853(null)), Dispatchers.getIO()), new C04864(null));
    }

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$3", f = "WebsocketRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$3, reason: invalid class name and case insensitive filesystem */
    static final class C04853 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C04853(Continuation<? super C04853> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04853(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04853) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$4", f = "WebsocketRepository.kt", i = {}, l = {32}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getWebsocketContactId$4, reason: invalid class name and case insensitive filesystem */
    static final class C04864 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04864(Continuation<? super C04864> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04864 c04864 = new C04864(continuation);
            c04864.L$0 = flowCollector;
            return c04864.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2", f = "WebsocketRepository.kt", i = {0, 1}, l = {40, 40, 42}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2, reason: invalid class name and case insensitive filesystem */
    static final class C04812 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04812(Continuation<? super C04812> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04812 c04812 = new C04812(continuation);
            c04812.L$0 = obj;
            return c04812;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04812) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00a1 A[RETURN] */
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
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L32
                if (r1 == r5) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L17
                goto La5
            L17:
                r10 = move-exception
                goto La2
            L1a:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L22:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L17
                goto L8b
            L2a:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L17
                goto L74
            L32:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r1 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig r1 = r1.getInstance()     // Catch: java.lang.Exception -> L17
                java.lang.String r1 = r1.getContactId()     // Catch: java.lang.Exception -> L17
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Exception -> L17
                int r1 = r1.length()     // Catch: java.lang.Exception -> L17
                if (r1 != 0) goto L4d
                r1 = 1
                goto L4e
            L4d:
                r1 = 0
            L4e:
                if (r1 == 0) goto L53
                kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L17
                return r10
            L53:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r6 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig r6 = r6.getInstance()     // Catch: java.lang.Exception -> L17
                java.lang.String r6 = r6.getContactId()     // Catch: java.lang.Exception -> L17
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L17
                r9.L$0 = r10     // Catch: java.lang.Exception -> L17
                r9.label = r5     // Catch: java.lang.Exception -> L17
                java.lang.Object r1 = r1.unread(r6, r7)     // Catch: java.lang.Exception -> L17
                if (r1 != r0) goto L71
                return r0
            L71:
                r8 = r1
                r1 = r10
                r10 = r8
            L74:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$1 r5 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$1     // Catch: java.lang.Exception -> L17
                r5.<init>(r1, r4)     // Catch: java.lang.Exception -> L17
                kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5     // Catch: java.lang.Exception -> L17
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L17
                r9.L$0 = r1     // Catch: java.lang.Exception -> L17
                r9.label = r3     // Catch: java.lang.Exception -> L17
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r5, r6)     // Catch: java.lang.Exception -> L17
                if (r10 != r0) goto L8b
                return r0
            L8b:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$2     // Catch: java.lang.Exception -> L17
                r3.<init>(r1, r4)     // Catch: java.lang.Exception -> L17
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L17
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L17
                r9.L$0 = r4     // Catch: java.lang.Exception -> L17
                r9.label = r2     // Catch: java.lang.Exception -> L17
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L17
                if (r10 != r0) goto La5
                return r0
            La2:
                r10.printStackTrace()
            La5:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository.C04812.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$1", f = "WebsocketRepository.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.I$0 = i;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(i2), 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$2", f = "WebsocketRepository.kt", i = {}, l = {43}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00962 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00962(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00962> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00962 c00962 = new C00962(this.$$this$flow, continuation);
                c00962.I$0 = i;
                return c00962.invokeSuspend(Unit.INSTANCE);
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

    public final Object getUnReadMessage(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04812(null)), new C04823(null)), Dispatchers.getIO()), new C04834(null));
    }

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$3", f = "WebsocketRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$3, reason: invalid class name and case insensitive filesystem */
    static final class C04823 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C04823(Continuation<? super C04823> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04823(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04823) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$4", f = "WebsocketRepository.kt", i = {}, l = {51}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getUnReadMessage$4, reason: invalid class name and case insensitive filesystem */
    static final class C04834 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04834(Continuation<? super C04834> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04834 c04834 = new C04834(continuation);
            c04834.L$0 = flowCollector;
            return c04834.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2", f = "WebsocketRepository.kt", i = {0, 1}, l = {59, 59, 61}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2, reason: invalid class name and case insensitive filesystem */
    static final class C04782 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04782(Continuation<? super C04782> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04782 c04782 = new C04782(continuation);
            c04782.L$0 = obj;
            return c04782;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04782) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00a1 A[RETURN] */
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
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L32
                if (r1 == r5) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L17
                goto La5
            L17:
                r10 = move-exception
                goto La2
            L1a:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L22:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L17
                goto L8b
            L2a:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L17
                goto L74
            L32:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r1 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig r1 = r1.getInstance()     // Catch: java.lang.Exception -> L17
                java.lang.String r1 = r1.getContactId()     // Catch: java.lang.Exception -> L17
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Exception -> L17
                int r1 = r1.length()     // Catch: java.lang.Exception -> L17
                if (r1 != 0) goto L4d
                r1 = 1
                goto L4e
            L4d:
                r1 = 0
            L4e:
                if (r1 == 0) goto L53
                kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L17
                return r10
            L53:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r6 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.base.pref.UserConfig r6 = r6.getInstance()     // Catch: java.lang.Exception -> L17
                java.lang.String r6 = r6.getContactId()     // Catch: java.lang.Exception -> L17
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L17
                r9.L$0 = r10     // Catch: java.lang.Exception -> L17
                r9.label = r5     // Catch: java.lang.Exception -> L17
                java.lang.Object r1 = r1.deleteContact(r6, r7)     // Catch: java.lang.Exception -> L17
                if (r1 != r0) goto L71
                return r0
            L71:
                r8 = r1
                r1 = r10
                r10 = r8
            L74:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r10     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$1 r5 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$1     // Catch: java.lang.Exception -> L17
                r5.<init>(r1, r4)     // Catch: java.lang.Exception -> L17
                kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5     // Catch: java.lang.Exception -> L17
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L17
                r9.L$0 = r1     // Catch: java.lang.Exception -> L17
                r9.label = r3     // Catch: java.lang.Exception -> L17
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.success(r10, r5, r6)     // Catch: java.lang.Exception -> L17
                if (r10 != r0) goto L8b
                return r0
            L8b:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r10     // Catch: java.lang.Exception -> L17
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$2     // Catch: java.lang.Exception -> L17
                r3.<init>(r1, r4)     // Catch: java.lang.Exception -> L17
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L17
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L17
                r9.L$0 = r4     // Catch: java.lang.Exception -> L17
                r9.label = r2     // Catch: java.lang.Exception -> L17
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L17
                if (r10 != r0) goto La5
                return r0
            La2:
                r10.printStackTrace()
            La5:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository.C04782.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$1", f = "WebsocketRepository.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.I$0 = i;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(i2), 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$2", f = "WebsocketRepository.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00952 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00952(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00952> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00952 c00952 = new C00952(this.$$this$flow, continuation);
                c00952.I$0 = i;
                return c00952.invokeSuspend(Unit.INSTANCE);
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

    public final Object getDeleteMessage(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04782(null)), new C04793(null)), Dispatchers.getIO()), new C04804(null));
    }

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$3", f = "WebsocketRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$3, reason: invalid class name and case insensitive filesystem */
    static final class C04793 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C04793(Continuation<? super C04793> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04793(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04793) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$4", f = "WebsocketRepository.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getDeleteMessage$4, reason: invalid class name and case insensitive filesystem */
    static final class C04804 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04804(Continuation<? super C04804> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04804 c04804 = new C04804(continuation);
            c04804.L$0 = flowCollector;
            return c04804.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/WsChatMessage;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2", f = "WebsocketRepository.kt", i = {0, 1}, l = {75, 75, 78}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends WsChatMessage>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $contactId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$contactId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$contactId, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends WsChatMessage>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<WsChatMessage>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<WsChatMessage>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007d A[RETURN] */
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
                goto L81
            L16:
                r10 = move-exception
                goto L7e
            L18:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L20:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L67
            L28:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L50
            L30:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L16
                java.lang.String r6 = r9.$contactId     // Catch: java.lang.Exception -> L16
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L16
                r9.L$0 = r10     // Catch: java.lang.Exception -> L16
                r9.label = r4     // Catch: java.lang.Exception -> L16
                java.lang.Object r1 = r1.messageList(r6, r7)     // Catch: java.lang.Exception -> L16
                if (r1 != r0) goto L4d
                return r0
            L4d:
                r8 = r1
                r1 = r10
                r10 = r8
            L50:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$1     // Catch: java.lang.Exception -> L16
                r4.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L16
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L16
                r9.L$0 = r1     // Catch: java.lang.Exception -> L16
                r9.label = r3     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r6)     // Catch: java.lang.Exception -> L16
                if (r10 != r0) goto L67
                return r0
            L67:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$2     // Catch: java.lang.Exception -> L16
                r3.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L16
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L16
                r9.L$0 = r5     // Catch: java.lang.Exception -> L16
                r9.label = r2     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L16
                if (r10 != r0) goto L81
                return r0
            L7e:
                r10.printStackTrace()
            L81:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/WsChatMessage;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$1", f = "WebsocketRepository.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends WsChatMessage>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<? extends WsChatMessage>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<WsChatMessage>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, List<? extends WsChatMessage> list, Continuation<? super Unit> continuation) {
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
                    if (this.$$this$flow.emit(new NetState<>(false, list, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$2", f = "WebsocketRepository.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00932 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<? extends WsChatMessage>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00932(FlowCollector<? super NetState<List<WsChatMessage>>> flowCollector, Continuation<? super C00932> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00932 c00932 = new C00932(this.$$this$flow, continuation);
                c00932.I$0 = i;
                return c00932.invokeSuspend(Unit.INSTANCE);
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

    public final Object getChatList(String str, Continuation<? super Flow<NetState<List<WsChatMessage>>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(str, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/WsChatMessage;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$3", f = "WebsocketRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends WsChatMessage>>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends WsChatMessage>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<WsChatMessage>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<WsChatMessage>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/WsChatMessage;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$4", f = "WebsocketRepository.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getChatList$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends WsChatMessage>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends WsChatMessage>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<WsChatMessage>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<WsChatMessage>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2", f = "WebsocketRepository.kt", i = {0, 1}, l = {93, 93, 96}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2, reason: invalid class name and case insensitive filesystem */
    static final class C04752 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SupportCsResp>>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04752(Continuation<? super C04752> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04752 c04752 = new C04752(continuation);
            c04752.L$0 = obj;
            return c04752;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SupportCsResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SupportCsResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SupportCsResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04752) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007b A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L30
                if (r1 == r4) goto L28
                if (r1 == r3) goto L20
                if (r1 != r2) goto L18
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L16
                goto L7f
            L16:
                r9 = move-exception
                goto L7c
            L18:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L20:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L16
                goto L65
            L28:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L16
                goto L4e
            L30:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L16
                r6 = r8
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L16
                r8.L$0 = r9     // Catch: java.lang.Exception -> L16
                r8.label = r4     // Catch: java.lang.Exception -> L16
                java.lang.Object r1 = r1.showSupportCs(r6)     // Catch: java.lang.Exception -> L16
                if (r1 != r0) goto L4b
                return r0
            L4b:
                r7 = r1
                r1 = r9
                r9 = r7
            L4e:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r9 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r9     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$1     // Catch: java.lang.Exception -> L16
                r4.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L16
                r6 = r8
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L16
                r8.L$0 = r1     // Catch: java.lang.Exception -> L16
                r8.label = r3     // Catch: java.lang.Exception -> L16
                java.lang.Object r9 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r9, r4, r6)     // Catch: java.lang.Exception -> L16
                if (r9 != r0) goto L65
                return r0
            L65:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r9 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r9     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$2     // Catch: java.lang.Exception -> L16
                r3.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L16
                r1 = r8
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L16
                r8.L$0 = r5     // Catch: java.lang.Exception -> L16
                r8.label = r2     // Catch: java.lang.Exception -> L16
                java.lang.Object r9 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r9, r3, r1)     // Catch: java.lang.Exception -> L16
                if (r9 != r0) goto L7f
                return r0
            L7c:
                r9.printStackTrace()
            L7f:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository.C04752.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$1", f = "WebsocketRepository.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends SupportCsResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<SupportCsResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<SupportCsResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends SupportCsResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<SupportCsResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<SupportCsResp> list, Continuation<? super Unit> continuation) {
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
                    if (this.$$this$flow.emit(new NetState<>(false, list, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: WebsocketRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$2", f = "WebsocketRepository.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00942 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<SupportCsResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00942(FlowCollector<? super NetState<List<SupportCsResp>>> flowCollector, Continuation<? super C00942> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00942 c00942 = new C00942(this.$$this$flow, continuation);
                c00942.I$0 = i;
                return c00942.invokeSuspend(Unit.INSTANCE);
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

    public final Object getCsSupport(Continuation<? super Flow<NetState<List<SupportCsResp>>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04752(null)), new C04763(null)), Dispatchers.getIO()), new C04774(null));
    }

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$3", f = "WebsocketRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$3, reason: invalid class name and case insensitive filesystem */
    static final class C04763 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends SupportCsResp>>>, Continuation<? super Unit>, Object> {
        int label;

        C04763(Continuation<? super C04763> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04763(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SupportCsResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SupportCsResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SupportCsResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04763) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$4", f = "WebsocketRepository.kt", i = {}, l = {105}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$getCsSupport$4, reason: invalid class name and case insensitive filesystem */
    static final class C04774 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends SupportCsResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04774(Continuation<? super C04774> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends SupportCsResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<SupportCsResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<SupportCsResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04774 c04774 = new C04774(continuation);
            c04774.L$0 = flowCollector;
            return c04774.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: WebsocketRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/ws/WebsocketRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/ws/WebsocketRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/ws/WebsocketRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WebsocketRepository getGetInstance() {
            return (WebsocketRepository) WebsocketRepository.getInstance$delegate.getValue();
        }
    }
}
