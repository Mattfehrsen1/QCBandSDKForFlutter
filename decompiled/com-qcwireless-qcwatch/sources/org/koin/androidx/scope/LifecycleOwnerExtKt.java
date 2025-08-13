package org.koin.androidx.scope;

import android.content.ComponentCallbacks;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.ComponentCallbackExtKt;
import org.koin.core.Koin;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.TypeQualifier;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;

/* compiled from: LifecycleOwnerExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0012\u001a\u00020\f*\u00020\u0002H\u0002\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0015"}, d2 = {"currentScope", "Lorg/koin/core/scope/Scope;", "Landroidx/lifecycle/LifecycleOwner;", "getCurrentScope", "(Landroidx/lifecycle/LifecycleOwner;)Lorg/koin/core/scope/Scope;", "bindScope", "", "scope", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "createAndBindScope", "scopeId", "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "getKoin", "Lorg/koin/core/Koin;", "getOrCreateCurrentScope", "getScopeId", "getScopeName", "Lorg/koin/core/qualifier/TypeQualifier;", "koin-androidx-scope_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class LifecycleOwnerExtKt {
    private static final Koin getKoin(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            return ComponentCallbackExtKt.getKoin((ComponentCallbacks) lifecycleOwner);
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.ComponentCallbacks");
    }

    private static final TypeQualifier getScopeName(LifecycleOwner lifecycleOwner) {
        return new TypeQualifier(Reflection.getOrCreateKotlinClass(lifecycleOwner.getClass()));
    }

    private static final String getScopeId(LifecycleOwner lifecycleOwner) {
        return KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(lifecycleOwner.getClass())) + "@" + System.identityHashCode(lifecycleOwner);
    }

    private static final Scope getOrCreateCurrentScope(LifecycleOwner lifecycleOwner) {
        String scopeId = getScopeId(lifecycleOwner);
        Scope scopeOrNull = getKoin(lifecycleOwner).getScopeOrNull(scopeId);
        return scopeOrNull != null ? scopeOrNull : createAndBindScope(lifecycleOwner, scopeId, getScopeName(lifecycleOwner));
    }

    private static final Scope createAndBindScope(LifecycleOwner lifecycleOwner, String str, Qualifier qualifier) {
        Scope scopeCreateScope = getKoin(lifecycleOwner).createScope(str, qualifier);
        bindScope$default(lifecycleOwner, scopeCreateScope, null, 2, null);
        return scopeCreateScope;
    }

    public static /* synthetic */ void bindScope$default(LifecycleOwner lifecycleOwner, Scope scope, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = Lifecycle.Event.ON_DESTROY;
        }
        bindScope(lifecycleOwner, scope, event);
    }

    public static final void bindScope(LifecycleOwner bindScope, Scope scope, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(bindScope, "$this$bindScope");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Intrinsics.checkParameterIsNotNull(event, "event");
        bindScope.getLifecycle().addObserver(new ScopeObserver(event, bindScope, scope));
    }

    public static final Scope getCurrentScope(LifecycleOwner currentScope) {
        Intrinsics.checkParameterIsNotNull(currentScope, "$this$currentScope");
        return getOrCreateCurrentScope(currentScope);
    }
}
