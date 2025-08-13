package com.qcwireless.qcwatch.ui.base.repository.mine;

import com.qcwireless.qcwatch.ui.base.api.BaseRepository;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.UserLoginResp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: LoginRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ5\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/mine/LoginRepository;", "Lcom/qcwireless/qcwatch/ui/base/api/BaseRepository;", "()V", "login", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "account", "", "passWord", "type", "", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginChina", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginRepository extends BaseRepository {

    /* compiled from: LoginRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2", f = "LoginRepository.kt", i = {1, 2}, l = {23, 26, 26, 28}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserLoginResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $passWord;
        final /* synthetic */ int $type;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$passWord = str2;
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$account, this.$passWord, this.$type, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x008b A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                r14 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r14.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L37
                if (r1 == r5) goto L32
                if (r1 == r4) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                kotlin.ResultKt.throwOnFailure(r15)
                goto La3
            L1a:
                java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r15.<init>(r0)
                throw r15
            L22:
                java.lang.Object r1 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r15)
                goto L8c
            L2a:
                java.lang.Object r1 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r15)
                goto L75
            L32:
                kotlin.ResultKt.throwOnFailure(r15)
                goto Lbf
            L37:
                kotlin.ResultKt.throwOnFailure(r15)
                java.lang.Object r15 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r15 = (kotlinx.coroutines.flow.FlowCollector) r15
                java.lang.String r1 = r14.$account
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 != 0) goto La6
                java.lang.String r1 = r14.$passWord
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L53
                goto La6
            L53:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()
                com.qcwireless.qcwatch.ui.base.bean.request.user.LoginRequest r5 = new com.qcwireless.qcwatch.ui.base.bean.request.user.LoginRequest
                java.lang.String r7 = r14.$account
                java.lang.String r8 = r14.$passWord
                int r9 = r14.$type
                r5.<init>(r7, r8, r9)
                r7 = r14
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r14.L$0 = r15
                r14.label = r4
                java.lang.Object r1 = r1.login(r5, r7)
                if (r1 != r0) goto L72
                return r0
            L72:
                r13 = r1
                r1 = r15
                r15 = r13
            L75:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r15 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r15
                com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$1
                r4.<init>(r1, r6)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r5 = r14
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r14.L$0 = r1
                r14.label = r3
                java.lang.Object r15 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r15, r4, r5)
                if (r15 != r0) goto L8c
                return r0
            L8c:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r15 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r15
                com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$2
                r3.<init>(r1, r6)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r14
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r14.L$0 = r6
                r14.label = r2
                java.lang.Object r15 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r15, r3, r1)
                if (r15 != r0) goto La3
                return r0
            La3:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            La6:
                com.qcwireless.qcwatch.ui.base.repository.mine.NetState r1 = new com.qcwireless.qcwatch.ui.base.repository.mine.NetState
                r7 = 0
                r8 = 0
                r9 = -10001(0xffffffffffffd8ef, float:NaN)
                r10 = 0
                r11 = 3
                r12 = 0
                r6 = r1
                r6.<init>(r7, r8, r9, r10, r11, r12)
                r2 = r14
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r14.label = r5
                java.lang.Object r15 = r15.emit(r1, r2)
                if (r15 != r0) goto Lbf
                return r0
            Lbf:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: LoginRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "userLoginResp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$1", f = "LoginRepository.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, UserLoginResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserLoginResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, UserLoginResp userLoginResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = userLoginResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    UserLoginResp userLoginResp = (UserLoginResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, userLoginResp, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$2", f = "LoginRepository.kt", i = {}, l = {29}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00762 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserLoginResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00762(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super C00762> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00762 c00762 = new C00762(this.$$this$flow, continuation);
                c00762.I$0 = i;
                return c00762.invokeSuspend(Unit.INSTANCE);
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

    public final Object login(String str, String str2, int i, Continuation<? super Flow<NetState<UserLoginResp>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(str, str2, i, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: LoginRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$3", f = "LoginRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserLoginResp>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: LoginRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$4", f = "LoginRepository.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$login$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<UserLoginResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: LoginRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2", f = "LoginRepository.kt", i = {1, 2}, l = {40, 43, 43, 45}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2, reason: invalid class name and case insensitive filesystem */
    static final class C04182 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserLoginResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $passWord;
        final /* synthetic */ int $type;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04182(String str, String str2, int i, Continuation<? super C04182> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$passWord = str2;
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04182 c04182 = new C04182(this.$account, this.$passWord, this.$type, continuation);
            c04182.L$0 = obj;
            return c04182;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04182) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x008b A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                r14 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r14.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L37
                if (r1 == r5) goto L32
                if (r1 == r4) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                kotlin.ResultKt.throwOnFailure(r15)
                goto La3
            L1a:
                java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r15.<init>(r0)
                throw r15
            L22:
                java.lang.Object r1 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r15)
                goto L8c
            L2a:
                java.lang.Object r1 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r15)
                goto L75
            L32:
                kotlin.ResultKt.throwOnFailure(r15)
                goto Lbf
            L37:
                kotlin.ResultKt.throwOnFailure(r15)
                java.lang.Object r15 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r15 = (kotlinx.coroutines.flow.FlowCollector) r15
                java.lang.String r1 = r14.$account
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 != 0) goto La6
                java.lang.String r1 = r14.$passWord
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L53
                goto La6
            L53:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.getService()
                com.qcwireless.qcwatch.ui.base.bean.request.user.LoginRequest r5 = new com.qcwireless.qcwatch.ui.base.bean.request.user.LoginRequest
                java.lang.String r7 = r14.$account
                java.lang.String r8 = r14.$passWord
                int r9 = r14.$type
                r5.<init>(r7, r8, r9)
                r7 = r14
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r14.L$0 = r15
                r14.label = r4
                java.lang.Object r1 = r1.login(r5, r7)
                if (r1 != r0) goto L72
                return r0
            L72:
                r13 = r1
                r1 = r15
                r15 = r13
            L75:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r15 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r15
                com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$1
                r4.<init>(r1, r6)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r5 = r14
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r14.L$0 = r1
                r14.label = r3
                java.lang.Object r15 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r15, r4, r5)
                if (r15 != r0) goto L8c
                return r0
            L8c:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r15 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r15
                com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$2
                r3.<init>(r1, r6)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r14
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r14.L$0 = r6
                r14.label = r2
                java.lang.Object r15 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r15, r3, r1)
                if (r15 != r0) goto La3
                return r0
            La3:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            La6:
                com.qcwireless.qcwatch.ui.base.repository.mine.NetState r1 = new com.qcwireless.qcwatch.ui.base.repository.mine.NetState
                r7 = 0
                r8 = 0
                r9 = -10001(0xffffffffffffd8ef, float:NaN)
                r10 = 0
                r11 = 3
                r12 = 0
                r6 = r1
                r6.<init>(r7, r8, r9, r10, r11, r12)
                r2 = r14
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r14.label = r5
                java.lang.Object r15 = r15.emit(r1, r2)
                if (r15 != r0) goto Lbf
                return r0
            Lbf:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository.C04182.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: LoginRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "userLoginResp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$1", f = "LoginRepository.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, UserLoginResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserLoginResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, UserLoginResp userLoginResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = userLoginResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    UserLoginResp userLoginResp = (UserLoginResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, userLoginResp, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$2", f = "LoginRepository.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00772 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserLoginResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00772(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super C00772> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00772 c00772 = new C00772(this.$$this$flow, continuation);
                c00772.I$0 = i;
                return c00772.invokeSuspend(Unit.INSTANCE);
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

    public final Object loginChina(String str, String str2, int i, Continuation<? super Flow<NetState<UserLoginResp>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04182(str, str2, i, null)), new C04193(null)), Dispatchers.getIO()), new C04204(null));
    }

    /* compiled from: LoginRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$3", f = "LoginRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$3, reason: invalid class name and case insensitive filesystem */
    static final class C04193 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserLoginResp>>, Continuation<? super Unit>, Object> {
        int label;

        C04193(Continuation<? super C04193> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04193(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04193) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$4", f = "LoginRepository.kt", i = {}, l = {51}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository$loginChina$4, reason: invalid class name and case insensitive filesystem */
    static final class C04204 extends SuspendLambda implements Function3<FlowCollector<? super NetState<UserLoginResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04204(Continuation<? super C04204> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<UserLoginResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04204 c04204 = new C04204(continuation);
            c04204.L$0 = flowCollector;
            return c04204.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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
