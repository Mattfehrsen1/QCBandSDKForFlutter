package com.app.watch.keeping.callback;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.app.watch.keeping.Cactus;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.pix.OnePixActivity;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppBackgroundCallback.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\u00020\u0001:\u0001\"B+\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\u001a\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0014H\u0016J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0007H\u0002J\u0015\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u001eJ\u001d\u0010\u001f\u001a\u00020\u0007*\u00020 2\u000e\b\u0004\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070!H\u0082\bR\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/app/watch/keeping/callback/AppBackgroundCallback;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "mContext", "Ljava/lang/ref/WeakReference;", "mFrontActivityCount", "", "mIsFirst", "mIsSend", "mUseCallback", "onActivityCreated", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "post", "useCallback", "useCallback$keeping_release", "postDelayed", "Landroid/os/Handler;", "Lkotlin/Function0;", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AppBackgroundCallback implements Application.ActivityLifecycleCallbacks {
    private static final long FIRST_TIME = 1000;
    private Function1<? super Boolean, Unit> block;
    private Context context;
    private WeakReference<Context> mContext;
    private int mFrontActivityCount;
    private boolean mIsFirst;
    private boolean mIsSend;
    private boolean mUseCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public AppBackgroundCallback() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AppBackgroundCallback(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    public AppBackgroundCallback(Context context, Function1<? super Boolean, Unit> function1) {
        this.context = context;
        this.block = function1;
        this.mIsFirst = true;
        this.mUseCallback = true;
        CactusExtKt.getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.callback.AppBackgroundCallback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppBackgroundCallback.m181_init_$lambda0(this.f$0);
            }
        }, 1000L);
    }

    public /* synthetic */ AppBackgroundCallback(Context context, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : context, (i & 2) != 0 ? null : function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m181_init_$lambda0(AppBackgroundCallback this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mFrontActivityCount == 0) {
            this$0.post();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (activity instanceof OnePixActivity) {
            return;
        }
        this.mContext = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (activity instanceof OnePixActivity) {
            return;
        }
        this.mFrontActivityCount++;
        post();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (activity instanceof OnePixActivity) {
            return;
        }
        this.mFrontActivityCount--;
        post();
    }

    public final void useCallback$keeping_release(boolean useCallback) {
        this.mUseCallback = useCallback;
    }

    private final void post() {
        final Context context;
        WeakReference<Context> weakReference = this.mContext;
        if (weakReference == null || (context = weakReference.get()) == null) {
            context = this.context;
        }
        if (context == null || !this.mUseCallback) {
            return;
        }
        if (this.mFrontActivityCount == 0) {
            this.mIsSend = false;
            Handler sMainHandler = CactusExtKt.getSMainHandler();
            if (!this.mIsFirst) {
                context.sendBroadcast(new Intent().setAction(Cactus.CACTUS_BACKGROUND));
                Function1 function1 = this.block;
                if (function1 != null) {
                    function1.invoke(true);
                    return;
                }
                return;
            }
            sMainHandler.postDelayed(new Runnable() { // from class: com.app.watch.keeping.callback.AppBackgroundCallback$post$lambda-5$$inlined$postDelayed$1
                @Override // java.lang.Runnable
                public final void run() {
                    context.sendBroadcast(new Intent().setAction(Cactus.CACTUS_BACKGROUND));
                    Function1 function12 = this.block;
                    if (function12 != null) {
                        function12.invoke(true);
                    }
                    this.this$0$inline_fun.mIsFirst = false;
                }
            }, 1000L);
            return;
        }
        if (this.mIsSend) {
            return;
        }
        this.mIsSend = true;
        Handler sMainHandler2 = CactusExtKt.getSMainHandler();
        if (!this.mIsFirst) {
            context.sendBroadcast(new Intent().setAction(Cactus.CACTUS_FOREGROUND));
            Function1 function12 = this.block;
            if (function12 != null) {
                function12.invoke(false);
                return;
            }
            return;
        }
        sMainHandler2.postDelayed(new Runnable() { // from class: com.app.watch.keeping.callback.AppBackgroundCallback$post$lambda-5$$inlined$postDelayed$2
            @Override // java.lang.Runnable
            public final void run() {
                context.sendBroadcast(new Intent().setAction(Cactus.CACTUS_FOREGROUND));
                Function1 function13 = this.block;
                if (function13 != null) {
                    function13.invoke(false);
                }
                this.this$0$inline_fun.mIsFirst = false;
            }
        }, 1000L);
    }

    private final void postDelayed(Handler handler, final Function0<Unit> function0) {
        if (this.mIsFirst) {
            handler.postDelayed(new Runnable() { // from class: com.app.watch.keeping.callback.AppBackgroundCallback.postDelayed.1
                @Override // java.lang.Runnable
                public final void run() {
                    function0.invoke();
                    this.mIsFirst = false;
                }
            }, 1000L);
        } else {
            function0.invoke();
        }
    }
}
