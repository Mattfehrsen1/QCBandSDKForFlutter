package org.koin.androidx.scope;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.KoinComponent;
import org.koin.core.scope.Scope;

/* compiled from: ScopeObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0007J\b\u0010\u0012\u001a\u00020\u0011H\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lorg/koin/androidx/scope/ScopeObserver;", "Landroidx/lifecycle/LifecycleObserver;", "Lorg/koin/core/KoinComponent;", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", TypedValues.AttributesType.S_TARGET, "", "scope", "Lorg/koin/core/scope/Scope;", "(Landroidx/lifecycle/Lifecycle$Event;Ljava/lang/Object;Lorg/koin/core/scope/Scope;)V", "getEvent", "()Landroidx/lifecycle/Lifecycle$Event;", "getScope", "()Lorg/koin/core/scope/Scope;", "getTarget", "()Ljava/lang/Object;", "onDestroy", "", "onStop", "koin-androidx-scope_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ScopeObserver implements LifecycleObserver, KoinComponent {
    private final Lifecycle.Event event;
    private final Scope scope;
    private final Object target;

    public ScopeObserver(Lifecycle.Event event, Object target, Scope scope) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        this.event = event;
        this.target = target;
        this.scope = scope;
    }

    public final Lifecycle.Event getEvent() {
        return this.event;
    }

    @Override // org.koin.core.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.getKoin(this);
    }

    public final Scope getScope() {
        return this.scope;
    }

    public final Object getTarget() {
        return this.target;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onStop() {
        if (this.event == Lifecycle.Event.ON_STOP) {
            KoinApplication.INSTANCE.getLogger().debug(this.target + " received ON_STOP");
            this.scope.close();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy() {
        if (this.event == Lifecycle.Event.ON_DESTROY) {
            KoinApplication.INSTANCE.getLogger().debug(this.target + " received ON_DESTROY");
            this.scope.close();
        }
    }
}
