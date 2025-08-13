package com.qcwireless.qcwatch.ui.base.api;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.qcwireless.qcwatch.ui.base.api.Result;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: BaseRepository.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JE\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0089\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000b\"\b\b\u0000\u0010\u0005*\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00042+\b\u0002\u0010\r\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u00112+\b\u0002\u0010\u0012\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013JM\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000b\"\b\b\u0000\u0010\u0005*\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/api/BaseRepository;", "", "()V", "apiCall", "Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, NotificationCompat.CATEGORY_CALL, "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeResponse", "Lcom/qcwireless/qcwatch/ui/base/api/Result;", "response", "successBlock", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/ExtensionFunctionType;", "errorBlock", "(Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "safeApiCall", "errorMessage", "", "(Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BaseRepository {

    /* compiled from: BaseRepository.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.api.BaseRepository", f = "BaseRepository.kt", i = {0}, l = {21}, m = "safeApiCall", n = {"errorMessage"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.api.BaseRepository$safeApiCall$1, reason: invalid class name */
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseRepository.this.safeApiCall(null, null, this);
        }
    }

    public final <T> Object apiCall(Function1<? super Continuation<? super QcResponse<? extends T>>, ? extends Object> function1, Continuation<? super QcResponse<? extends T>> continuation) {
        return function1.invoke(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object safeApiCall(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super com.qcwireless.qcwatch.ui.base.api.Result<? extends T>>, ? extends java.lang.Object> r5, java.lang.String r6, kotlin.coroutines.Continuation<? super com.qcwireless.qcwatch.ui.base.api.Result<? extends T>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.api.BaseRepository.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r7
            com.qcwireless.qcwatch.ui.base.api.BaseRepository$safeApiCall$1 r0 = (com.qcwireless.qcwatch.ui.base.api.BaseRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.qcwireless.qcwatch.ui.base.api.BaseRepository$safeApiCall$1 r0 = new com.qcwireless.qcwatch.ui.base.api.BaseRepository$safeApiCall$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r5 = r0.L$0
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L48
            goto L45
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6     // Catch: java.lang.Exception -> L48
            r0.label = r3     // Catch: java.lang.Exception -> L48
            java.lang.Object r7 = r5.invoke(r0)     // Catch: java.lang.Exception -> L48
            if (r7 != r1) goto L45
            return r1
        L45:
            com.qcwireless.qcwatch.ui.base.api.Result r7 = (com.qcwireless.qcwatch.ui.base.api.Result) r7     // Catch: java.lang.Exception -> L48
            goto L59
        L48:
            r5 = move-exception
            com.qcwireless.qcwatch.ui.base.api.Result$Error r7 = new com.qcwireless.qcwatch.ui.base.api.Result$Error
            java.io.IOException r0 = new java.io.IOException
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r0.<init>(r6, r5)
            java.lang.Exception r0 = (java.lang.Exception) r0
            r7.<init>(r0)
            com.qcwireless.qcwatch.ui.base.api.Result r7 = (com.qcwireless.qcwatch.ui.base.api.Result) r7
        L59:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.api.BaseRepository.safeApiCall(kotlin.jvm.functions.Function1, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object executeResponse$default(BaseRepository baseRepository, QcResponse qcResponse, Function2 function2, Function2 function22, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: executeResponse");
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function22 = null;
        }
        return baseRepository.executeResponse(qcResponse, function2, function22, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: BaseRepository.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lcom/qcwireless/qcwatch/ui/base/api/Result;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.api.BaseRepository$executeResponse$2", f = "BaseRepository.kt", i = {}, l = {31, 34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.api.BaseRepository$executeResponse$2, reason: invalid class name */
    static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends T>>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ QcResponse<T> $response;
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $successBlock;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$response = qcResponse;
            this.$errorBlock = function2;
            this.$successBlock = function22;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$response, this.$errorBlock, this.$successBlock, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends T>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return new Result.Error(new IOException(this.$response.getMessage()));
                }
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return new Result.Success(this.$response.getData());
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (this.$response.getRetCode() != 0) {
                Function2<CoroutineScope, Continuation<? super Unit>, Object> function2 = this.$errorBlock;
                if (function2 != null) {
                    this.label = 1;
                    if (function2.invoke(coroutineScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return new Result.Error(new IOException(this.$response.getMessage()));
            }
            Function2<CoroutineScope, Continuation<? super Unit>, Object> function22 = this.$successBlock;
            if (function22 != null) {
                this.label = 2;
                if (function22.invoke(coroutineScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return new Result.Success(this.$response.getData());
        }
    }

    public final <T> Object executeResponse(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super Result<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(qcResponse, function22, function2, null), continuation);
    }
}
