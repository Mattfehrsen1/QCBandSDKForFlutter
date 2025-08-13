package org.koin.core.instance;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.koin.core.Koin;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.parameter.DefinitionParametersKt;
import org.koin.core.scope.Scope;

/* compiled from: DefinitionInstance.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\u0004\u0018\u0001`\t¢\u0006\u0002\u0010\nR\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\u0004\u0018\u0001`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/koin/core/instance/InstanceContext;", "", "koin", "Lorg/koin/core/Koin;", "scope", "Lorg/koin/core/scope/Scope;", "_parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lorg/koin/core/Koin;Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function0;)V", "getKoin", "()Lorg/koin/core/Koin;", "parameters", "getParameters", "()Lorg/koin/core/parameter/DefinitionParameters;", "getScope", "()Lorg/koin/core/scope/Scope;", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class InstanceContext {
    private final Function0<DefinitionParameters> _parameters;
    private final Koin koin;
    private final DefinitionParameters parameters;
    private final Scope scope;

    public InstanceContext() {
        this(null, null, null, 7, null);
    }

    public InstanceContext(Koin koin, Scope scope, Function0<DefinitionParameters> function0) {
        DefinitionParameters definitionParametersInvoke;
        this.koin = koin;
        this.scope = scope;
        this._parameters = function0;
        this.parameters = (function0 == null || (definitionParametersInvoke = function0.invoke()) == null) ? DefinitionParametersKt.emptyParametersHolder() : definitionParametersInvoke;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ InstanceContext(Koin koin, Scope scope, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            koin = null;
        }
        scope = (i & 2) != 0 ? koin != null ? koin.getRootScope() : null : scope;
        if ((i & 4) != 0) {
            function0 = null;
        }
        this(koin, scope, function0);
    }

    public final Koin getKoin() {
        return this.koin;
    }

    public final Scope getScope() {
        return this.scope;
    }

    public final DefinitionParameters getParameters() {
        return this.parameters;
    }
}
