package io.fotoapparat.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ConflatedBroadcastChannel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;

/* compiled from: AwaitBroadcastChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B%\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\u0010\tJ\u0011\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0097\u0001J\u0011\u0010*\u001a\u00020\u0004H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010+J\t\u0010,\u001a\u00020-H\u0096\u0001J\u0012\u0010,\u001a\u00020\u00042\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u000e\u00100\u001a\u00020\u0004H\u0097\u0001¢\u0006\u0002\b,J\u0013\u00101\u001a\u00020\u00042\b\u0010.\u001a\u0004\u0018\u00010/H\u0096\u0001J6\u00102\u001a\u0002H3\"\u0004\b\u0001\u001032\u0006\u00104\u001a\u0002H32\u0018\u00105\u001a\u0014\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002H306H\u0096\u0001¢\u0006\u0002\u00108J(\u00109\u001a\u0004\u0018\u0001H:\"\b\b\u0001\u0010:*\u0002072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H:0\u0016H\u0096\u0003¢\u0006\u0002\u0010;J\r\u0010<\u001a\u00060=j\u0002`>H\u0097\u0001J\u000e\u0010?\u001a\u00020\u0004H\u0097\u0001¢\u0006\u0002\u0010@J\u000b\u0010A\u001a\u0004\u0018\u00010/H\u0097\u0001J\u0011\u0010B\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010+J.\u0010C\u001a\u00020-2#\u0010D\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0EH\u0097\u0001JB\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042'\u0010D\u001a#\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0Ej\u0002`LH\u0097\u0001J2\u0010H\u001a\u00020I2'\u0010D\u001a#\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0Ej\u0002`LH\u0096\u0001J\u0011\u0010M\u001a\u00020-H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010+J\u0015\u0010N\u001a\u00020O2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0096\u0001J\u0015\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010RJ\u000f\u0010S\u001a\b\u0012\u0004\u0012\u00028\u00000TH\u0096\u0001J\u0011\u0010U\u001a\u00020O2\u0006\u0010V\u001a\u00020OH\u0096\u0003J\u0011\u0010U\u001a\u00020\f2\u0006\u0010W\u001a\u00020\fH\u0097\u0003J\u0019\u0010X\u001a\u00020-2\u0006\u0010Q\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010YJ\t\u0010Z\u001a\u00020\u0004H\u0096\u0001R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00048\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0012\u0010\u0013\u001a\u00020\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u00048\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u001aX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R$\u0010!\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#0\"X\u0096\u0005¢\u0006\u0006\u001a\u0004\b$\u0010%\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006["}, d2 = {"Lio/fotoapparat/coroutines/AwaitBroadcastChannel;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/Deferred;", "", "channel", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", "(Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;Lkotlinx/coroutines/CompletableDeferred;)V", "children", "Lkotlin/sequences/Sequence;", "Lkotlinx/coroutines/Job;", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "()Z", "isCancelled", "isClosedForSend", "isCompleted", "isFull", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "", "cause", "", "cancel0", "close", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", ExifInterface.LONGITUDE_EAST, "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "getCancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getCompleted", "()Ljava/lang/Boolean;", "getCompletionExceptionOrNull", "getValue", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "Lkotlinx/coroutines/CompletionHandler;", "join", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "offer", "element", "(Ljava/lang/Object;)Z", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "plus", "context", FitnessActivities.OTHER, "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AwaitBroadcastChannel<T> implements BroadcastChannel<T>, Deferred<Boolean> {
    private final ConflatedBroadcastChannel<T> channel;
    private final CompletableDeferred<Boolean> deferred;

    /* compiled from: AwaitBroadcastChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0086@ø\u0001\u0000"}, d2 = {"getValue", "", ExifInterface.GPS_DIRECTION_TRUE, "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/coroutines/AwaitBroadcastChannel", f = "AwaitBroadcastChannel.kt", i = {0}, l = {19, 21}, m = "getValue", n = {"this"}, s = {"L$0"})
    /* renamed from: io.fotoapparat.coroutines.AwaitBroadcastChannel$getValue$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AwaitBroadcastChannel.this.getValue(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AwaitBroadcastChannel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // kotlinx.coroutines.Job
    public ChildHandle attachChild(ChildJob child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        return this.deferred.attachChild(child);
    }

    @Override // kotlinx.coroutines.Deferred
    public Object await(Continuation<? super Boolean> continuation) {
        Object objAwait = this.deferred.await(continuation);
        Intrinsics.checkExpressionValueIsNotNull(objAwait, "await(...)");
        return objAwait;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel() {
        this.deferred.cancel();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left here for binary compatibility")
    /* renamed from: cancel, reason: collision with other method in class */
    public /* synthetic */ boolean m1100cancel() {
        return this.deferred.cancel();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(Throwable cause) {
        return this.channel.cancel(cause);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R initial, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        return (R) this.deferred.fold(initial, operation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (E) this.deferred.get(key);
    }

    @Override // kotlinx.coroutines.Job
    public CancellationException getCancellationException() {
        return this.deferred.getCancellationException();
    }

    @Override // kotlinx.coroutines.Job
    public Sequence<Job> getChildren() {
        return this.deferred.getChildren();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.Deferred
    public Boolean getCompleted() {
        Boolean completed = this.deferred.getCompleted();
        Intrinsics.checkExpressionValueIsNotNull(completed, "getCompleted(...)");
        return completed;
    }

    @Override // kotlinx.coroutines.Deferred
    public Throwable getCompletionExceptionOrNull() {
        return this.deferred.getCompletionExceptionOrNull();
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<?> getKey() {
        return this.deferred.getKey();
    }

    @Override // kotlinx.coroutines.Deferred
    public SelectClause1<Boolean> getOnAwait() {
        return this.deferred.getOnAwait();
    }

    @Override // kotlinx.coroutines.Job
    public SelectClause0 getOnJoin() {
        return this.deferred.getOnJoin();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<T, SendChannel<T>> getOnSend() {
        return this.channel.getOnSend();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.channel.invokeOnClose(handler);
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return this.deferred.invokeOnCompletion(handler);
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return this.deferred.invokeOnCompletion(onCancelling, invokeImmediately, handler);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return this.deferred.isActive();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return this.deferred.isCancelled();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this.channel.isClosedForSend();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCompleted() {
        return this.deferred.isCompleted();
    }

    public boolean isFull() {
        return this.channel.isFull();
    }

    @Override // kotlinx.coroutines.Job
    public Object join(Continuation<? super Unit> continuation) {
        return this.deferred.join(continuation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.deferred.minusKey(key);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<T> openSubscription() {
        return this.channel.openSubscription();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return this.deferred.plus(context);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return this.deferred.plus(other);
    }

    @Override // kotlinx.coroutines.Job
    public boolean start() {
        return this.deferred.start();
    }

    public AwaitBroadcastChannel(ConflatedBroadcastChannel<T> channel, CompletableDeferred<Boolean> deferred) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(deferred, "deferred");
        this.channel = channel;
        this.deferred = deferred;
    }

    public /* synthetic */ AwaitBroadcastChannel(ConflatedBroadcastChannel conflatedBroadcastChannel, CompletableDeferred completableDeferred, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ConflatedBroadcastChannel() : conflatedBroadcastChannel, (i & 2) != 0 ? CompletableDeferredKt.CompletableDeferred$default(null, 1, null) : completableDeferred);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getValue(kotlin.coroutines.Continuation<? super T> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.fotoapparat.coroutines.AwaitBroadcastChannel.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r5
            io.fotoapparat.coroutines.AwaitBroadcastChannel$getValue$1 r0 = (io.fotoapparat.coroutines.AwaitBroadcastChannel.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            io.fotoapparat.coroutines.AwaitBroadcastChannel$getValue$1 r0 = new io.fotoapparat.coroutines.AwaitBroadcastChannel$getValue$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L34
            java.lang.Object r0 = r0.L$0
            io.fotoapparat.coroutines.AwaitBroadcastChannel r0 = (io.fotoapparat.coroutines.AwaitBroadcastChannel) r0
            boolean r1 = r5 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L2f
            goto L4f
        L2f:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r5 = r5.exception
            throw r5
        L34:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L3d:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L56
            kotlinx.coroutines.CompletableDeferred<java.lang.Boolean> r5 = r4.deferred
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.await(r0)
            if (r5 != r1) goto L4e
            return r1
        L4e:
            r0 = r4
        L4f:
            kotlinx.coroutines.channels.ConflatedBroadcastChannel<T> r5 = r0.channel
            java.lang.Object r5 = r5.getValue()
            return r5
        L56:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r5 = r5.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fotoapparat.coroutines.AwaitBroadcastChannel.getValue(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(T element) {
        this.deferred.complete(true);
        return this.channel.offer(element);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(T t, Continuation<? super Unit> continuation) {
        this.deferred.complete(Boxing.boxBoolean(true));
        return this.channel.send(t, continuation);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel, kotlinx.coroutines.Job
    public boolean cancel(Throwable cause) {
        return this.channel.cancel(cause) && this.deferred.cancel(cause);
    }
}
