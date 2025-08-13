package com.qcwireless.qcwatch.ui.base.repository.mine;

import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: FindPwdRepository.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ%\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/mine/FindPwdRepository;", "", "()V", "reSetPwd", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "code", "", "account", "password", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendVerifyCode", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FindPwdRepository {

    /* compiled from: FindPwdRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2", f = "FindPwdRepository.kt", i = {1, 2}, l = {25, 29, 29, 31}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2, reason: invalid class name and case insensitive filesystem */
    static final class C04152 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04152(String str, Continuation<? super C04152> continuation) {
            super(2, continuation);
            this.$account = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04152 c04152 = new C04152(this.$account, continuation);
            c04152.L$0 = obj;
            return c04152;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04152) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x009d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                r16 = this;
                r1 = r16
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r1.label
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                r7 = 0
                if (r2 == 0) goto L3f
                if (r2 == r6) goto L3b
                if (r2 == r5) goto L31
                if (r2 == r4) goto L27
                if (r2 != r3) goto L1f
                kotlin.ResultKt.throwOnFailure(r17)     // Catch: java.lang.Exception -> L1c
                goto Lb8
            L1c:
                r0 = move-exception
                goto Lb5
            L1f:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r2)
                throw r0
            L27:
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r17)     // Catch: java.lang.Exception -> L1c
                r4 = r17
                goto L9e
            L31:
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r17)     // Catch: java.lang.Exception -> L1c
                r5 = r17
                goto L87
            L3b:
                kotlin.ResultKt.throwOnFailure(r17)
                goto L69
            L3f:
                kotlin.ResultKt.throwOnFailure(r17)
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                java.lang.String r8 = r1.$account
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                boolean r8 = kotlin.text.StringsKt.isBlank(r8)
                if (r8 == 0) goto L6c
                com.qcwireless.qcwatch.ui.base.repository.mine.NetState r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.NetState
                r10 = 0
                r11 = 0
                r12 = -10001(0xffffffffffffd8ef, float:NaN)
                r13 = 0
                r14 = 3
                r15 = 0
                r9 = r3
                r9.<init>(r10, r11, r12, r13, r14, r15)
                r4 = r1
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r1.label = r6
                java.lang.Object r2 = r2.emit(r3, r4)
                if (r2 != r0) goto L69
                return r0
            L69:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L6c:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r6 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.api.QcService r6 = r6.service()     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.bean.request.user.FindPwdRequest r8 = new com.qcwireless.qcwatch.ui.base.bean.request.user.FindPwdRequest     // Catch: java.lang.Exception -> L1c
                java.lang.String r9 = r1.$account     // Catch: java.lang.Exception -> L1c
                r8.<init>(r9)     // Catch: java.lang.Exception -> L1c
                r9 = r1
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch: java.lang.Exception -> L1c
                r1.L$0 = r2     // Catch: java.lang.Exception -> L1c
                r1.label = r5     // Catch: java.lang.Exception -> L1c
                java.lang.Object r5 = r6.getVerifyCode(r8, r9)     // Catch: java.lang.Exception -> L1c
                if (r5 != r0) goto L87
                return r0
            L87:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r5 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r5     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$1 r6 = new com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$1     // Catch: java.lang.Exception -> L1c
                r6.<init>(r2, r7)     // Catch: java.lang.Exception -> L1c
                kotlin.jvm.functions.Function3 r6 = (kotlin.jvm.functions.Function3) r6     // Catch: java.lang.Exception -> L1c
                r8 = r1
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch: java.lang.Exception -> L1c
                r1.L$0 = r2     // Catch: java.lang.Exception -> L1c
                r1.label = r4     // Catch: java.lang.Exception -> L1c
                java.lang.Object r4 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.success(r5, r6, r8)     // Catch: java.lang.Exception -> L1c
                if (r4 != r0) goto L9e
                return r0
            L9e:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r4 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r4     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$2 r5 = new com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$2     // Catch: java.lang.Exception -> L1c
                r5.<init>(r2, r7)     // Catch: java.lang.Exception -> L1c
                kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5     // Catch: java.lang.Exception -> L1c
                r2 = r1
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> L1c
                r1.L$0 = r7     // Catch: java.lang.Exception -> L1c
                r1.label = r3     // Catch: java.lang.Exception -> L1c
                java.lang.Object r2 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.error(r4, r5, r2)     // Catch: java.lang.Exception -> L1c
                if (r2 != r0) goto Lb8
                return r0
            Lb5:
                r0.printStackTrace()
            Lb8:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository.C04152.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: FindPwdRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$1", f = "FindPwdRepository.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$1, reason: invalid class name */
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

        /* compiled from: FindPwdRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$2", f = "FindPwdRepository.kt", i = {}, l = {32}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00752 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00752(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00752> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00752 c00752 = new C00752(this.$$this$flow, continuation);
                c00752.I$0 = i;
                return c00752.invokeSuspend(Unit.INSTANCE);
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

    public final Object sendVerifyCode(String str, Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04152(str, null)), new C04163(null)), Dispatchers.getIO()), new C04174(null));
    }

    /* compiled from: FindPwdRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$3", f = "FindPwdRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$3, reason: invalid class name and case insensitive filesystem */
    static final class C04163 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C04163(Continuation<? super C04163> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04163(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04163) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: FindPwdRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$4", f = "FindPwdRepository.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$sendVerifyCode$4, reason: invalid class name and case insensitive filesystem */
    static final class C04174 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04174(Continuation<? super C04174> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04174 c04174 = new C04174(continuation);
            c04174.L$0 = flowCollector;
            return c04174.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: FindPwdRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$2", f = "FindPwdRepository.kt", i = {1, 2}, l = {45, 49, 49, 51}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $code;
        final /* synthetic */ String $password;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, String str3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.$code = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$account, this.$password, this.$code, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x00a9 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r16) {
            /*
                Method dump skipped, instructions count: 227
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: FindPwdRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$2$1", f = "FindPwdRepository.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$2$1, reason: invalid class name */
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
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(2), 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: FindPwdRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$2$2", f = "FindPwdRepository.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00742 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00742(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00742> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00742 c00742 = new C00742(this.$$this$flow, continuation);
                c00742.I$0 = i;
                return c00742.invokeSuspend(Unit.INSTANCE);
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

    public final Object reSetPwd(String str, String str2, String str3, Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(str2, str3, str, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: FindPwdRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$3", f = "FindPwdRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: FindPwdRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$4", f = "FindPwdRepository.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository$reSetPwd$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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
}
