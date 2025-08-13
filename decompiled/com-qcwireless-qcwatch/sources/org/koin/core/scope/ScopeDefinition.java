package org.koin.core.scope;

import com.google.android.gms.fitness.FitnessActivities;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.instance.InstanceContext;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ScopeDefinition.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R)\u0010\u0005\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lorg/koin/core/scope/ScopeDefinition;", "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "(Lorg/koin/core/qualifier/Qualifier;)V", "definitions", "Ljava/util/HashSet;", "Lorg/koin/core/definition/BeanDefinition;", "Lkotlin/collections/HashSet;", "getDefinitions", "()Ljava/util/HashSet;", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "release", "", "instance", "Lorg/koin/core/scope/Scope;", "release$koin_core", "toString", "", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final /* data */ class ScopeDefinition {
    private final HashSet<BeanDefinition<?>> definitions;
    private final Qualifier qualifier;

    public static /* synthetic */ ScopeDefinition copy$default(ScopeDefinition scopeDefinition, Qualifier qualifier, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = scopeDefinition.qualifier;
        }
        return scopeDefinition.copy(qualifier);
    }

    /* renamed from: component1, reason: from getter */
    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final ScopeDefinition copy(Qualifier qualifier) {
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        return new ScopeDefinition(qualifier);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ScopeDefinition) && Intrinsics.areEqual(this.qualifier, ((ScopeDefinition) other).qualifier);
        }
        return true;
    }

    public int hashCode() {
        Qualifier qualifier = this.qualifier;
        if (qualifier != null) {
            return qualifier.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ScopeDefinition(qualifier=" + this.qualifier + ")";
    }

    public ScopeDefinition(Qualifier qualifier) {
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        this.qualifier = qualifier;
        this.definitions = new HashSet<>();
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final HashSet<BeanDefinition<?>> getDefinitions() {
        return this.definitions;
    }

    public final void release$koin_core(Scope instance) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
        Iterator<T> it = this.definitions.iterator();
        while (it.hasNext()) {
            DefinitionInstance beanDefinition = ((BeanDefinition) it.next()).getInstance();
            if (beanDefinition != null) {
                beanDefinition.release(new InstanceContext(null, instance, null, 5, null));
            }
        }
    }
}
