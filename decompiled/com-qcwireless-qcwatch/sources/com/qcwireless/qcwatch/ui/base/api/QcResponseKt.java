package com.qcwireless.qcwatch.ui.base.api;

import androidx.exifinterface.media.ExifInterface;
import com.androidnetworking.common.ANConstants;
import com.qcwireless.qcwatch.base.pref.UserConfig;
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
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: QcResponse.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a^\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000121\b\u0002\u0010\u0004\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0005¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u0085\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012+\b\u0002\u0010\u000e\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000f¢\u0006\u0002\b\n2+\b\u0002\u0010\u0004\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000f¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a^\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000121\b\u0002\u0010\u000e\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0005¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"error", "Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "errorBlock", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeResponse", "Lcom/qcwireless/qcwatch/ui/base/api/Result;", "successBlock", "Lkotlin/Function2;", "(Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", ANConstants.SUCCESS, "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QcResponseKt {
    public static /* synthetic */ Object executeResponse$default(QcResponse qcResponse, Function2 function2, Function2 function22, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = null;
        }
        if ((i & 2) != 0) {
            function22 = null;
        }
        return executeResponse(qcResponse, function2, function22, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: QcResponse.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lcom/qcwireless/qcwatch/ui/base/api/Result;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.api.QcResponseKt$executeResponse$2", f = "QcResponse.kt", i = {}, l = {19, 26}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.api.QcResponseKt$executeResponse$2, reason: invalid class name and case insensitive filesystem */
    static final class C02922<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends T>>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $successBlock;
        final /* synthetic */ QcResponse<T> $this_executeResponse;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02922(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super C02922> continuation) {
            super(2, continuation);
            this.$this_executeResponse = qcResponse;
            this.$errorBlock = function2;
            this.$successBlock = function22;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02922 c02922 = new C02922(this.$this_executeResponse, this.$errorBlock, this.$successBlock, continuation);
            c02922.L$0 = obj;
            return c02922;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends T>> continuation) {
            return ((C02922) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r5)
                goto L7a
            L12:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L1a:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L3a
            L1e:
                kotlin.ResultKt.throwOnFailure(r5)
                java.lang.Object r5 = r4.L$0
                kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
                com.qcwireless.qcwatch.ui.base.api.QcResponse<T> r1 = r4.$this_executeResponse
                int r1 = r1.getRetCode()
                if (r1 == 0) goto L6d
                kotlin.jvm.functions.Function2<kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r4.$errorBlock
                if (r1 == 0) goto L3a
                r4.label = r3
                java.lang.Object r5 = r1.invoke(r5, r4)
                if (r5 != r0) goto L3a
                return r0
            L3a:
                com.qcwireless.qcwatch.ui.base.api.QcResponse<T> r5 = r4.$this_executeResponse
                int r5 = r5.getRetCode()
                r0 = 401(0x191, float:5.62E-43)
                if (r5 != r0) goto L58
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r5 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r5 = r5.getInstance()
                java.lang.String r0 = "15ef6eb5403406c1da0dc4a4defa2ea1"
                r5.setUserToken(r0)
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r5 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r5 = r5.getInstance()
                r5.save()
            L58:
                com.qcwireless.qcwatch.ui.base.api.Result$Error r5 = new com.qcwireless.qcwatch.ui.base.api.Result$Error
                java.io.IOException r0 = new java.io.IOException
                com.qcwireless.qcwatch.ui.base.api.QcResponse<T> r1 = r4.$this_executeResponse
                java.lang.String r1 = r1.getMessage()
                r0.<init>(r1)
                java.lang.Exception r0 = (java.lang.Exception) r0
                r5.<init>(r0)
                com.qcwireless.qcwatch.ui.base.api.Result r5 = (com.qcwireless.qcwatch.ui.base.api.Result) r5
                goto L87
            L6d:
                kotlin.jvm.functions.Function2<kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r4.$successBlock
                if (r1 == 0) goto L7a
                r4.label = r2
                java.lang.Object r5 = r1.invoke(r5, r4)
                if (r5 != r0) goto L7a
                return r0
            L7a:
                com.qcwireless.qcwatch.ui.base.api.Result$Success r5 = new com.qcwireless.qcwatch.ui.base.api.Result$Success
                com.qcwireless.qcwatch.ui.base.api.QcResponse<T> r0 = r4.$this_executeResponse
                java.lang.Object r0 = r0.getData()
                r5.<init>(r0)
                com.qcwireless.qcwatch.ui.base.api.Result r5 = (com.qcwireless.qcwatch.ui.base.api.Result) r5
            L87:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.api.QcResponseKt.C02922.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Object executeResponse(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super Result<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new C02922(qcResponse, function22, function2, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: QcResponse.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.api.QcResponseKt$success$2", f = "QcResponse.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.api.QcResponseKt$success$2, reason: invalid class name and case insensitive filesystem */
    static final class C02932<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QcResponse<? extends T>>, Object> {
        final /* synthetic */ Function3<CoroutineScope, T, Continuation<? super Unit>, Object> $successBlock;
        final /* synthetic */ QcResponse<T> $this_success;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02932(QcResponse<? extends T> qcResponse, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C02932> continuation) {
            super(2, continuation);
            this.$this_success = qcResponse;
            this.$successBlock = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02932 c02932 = new C02932(this.$this_success, this.$successBlock, continuation);
            c02932.L$0 = obj;
            return c02932;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QcResponse<? extends T>> continuation) {
            return ((C02932) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Function3<CoroutineScope, T, Continuation<? super Unit>, Object> function3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$this_success.getRetCode() == 0 && (function3 = this.$successBlock) != null) {
                    T data = this.$this_success.getData();
                    this.label = 1;
                    if (function3.invoke(coroutineScope, data, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return this.$this_success;
        }
    }

    public static /* synthetic */ Object success$default(QcResponse qcResponse, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        return success(qcResponse, function3, continuation);
    }

    public static final <T> Object success(QcResponse<? extends T> qcResponse, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super QcResponse<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new C02932(qcResponse, function3, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: QcResponse.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.api.QcResponseKt$error$2", f = "QcResponse.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.api.QcResponseKt$error$2, reason: invalid class name */
    static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QcResponse<? extends T>>, Object> {
        final /* synthetic */ Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ QcResponse<T> $this_error;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(QcResponse<? extends T> qcResponse, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_error = qcResponse;
            this.$errorBlock = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_error, this.$errorBlock, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QcResponse<? extends T>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> function3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$this_error.getRetCode() == 401) {
                    UserConfig.INSTANCE.getInstance().setUserToken("15ef6eb5403406c1da0dc4a4defa2ea1");
                    UserConfig.INSTANCE.getInstance().save();
                }
                if (this.$this_error.getRetCode() != 0 && (function3 = this.$errorBlock) != null) {
                    Integer numBoxInt = Boxing.boxInt(this.$this_error.getRetCode());
                    this.label = 1;
                    if (function3.invoke(coroutineScope, numBoxInt, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return this.$this_error;
        }
    }

    public static /* synthetic */ Object error$default(QcResponse qcResponse, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        return error(qcResponse, function3, continuation);
    }

    public static final <T> Object error(QcResponse<? extends T> qcResponse, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super QcResponse<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(qcResponse, function3, null), continuation);
    }
}
