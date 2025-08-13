package com.app.watch.keeping.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StopReceiver.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\f\u001a\u00020\u000b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001b\u0010\u000f\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0000¢\u0006\u0002\b\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/app/watch/keeping/receiver/StopReceiver;", "Landroid/content/BroadcastReceiver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mActionStop", "", "mBlock", "Lkotlin/Function0;", "", "onReceive", "intent", "Landroid/content/Intent;", "register", "block", "register$keeping_release", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class StopReceiver extends BroadcastReceiver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;
    private String mActionStop;
    private Function0<Unit> mBlock;

    public /* synthetic */ StopReceiver(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private StopReceiver(Context context) {
        this.context = context;
        this.mActionStop = "com.app.QWatchPro.flag.stop." + context.getPackageName();
        context.registerReceiver(this, new IntentFilter(this.mActionStop));
    }

    public final Context getContext() {
        return this.context;
    }

    /* compiled from: StopReceiver.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/app/watch/keeping/receiver/StopReceiver$Companion;", "", "()V", "newInstance", "Lcom/app/watch/keeping/receiver/StopReceiver;", "context", "Landroid/content/Context;", "newInstance$keeping_release", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StopReceiver newInstance$keeping_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new StopReceiver(context, null);
        }
    }

    public final void register$keeping_release(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.mBlock = block;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null || !Intrinsics.areEqual(action, this.mActionStop)) {
            return;
        }
        this.context.unregisterReceiver(this);
        Function0<Unit> function0 = this.mBlock;
        if (function0 != null) {
            function0.invoke();
        }
    }
}
