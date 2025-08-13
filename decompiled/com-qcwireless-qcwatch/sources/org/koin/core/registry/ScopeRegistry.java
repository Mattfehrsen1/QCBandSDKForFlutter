package org.koin.core.registry;

import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.error.NoScopeDefinitionFoundException;
import org.koin.core.error.ScopeAlreadyCreatedException;
import org.koin.core.error.ScopeNotCreatedException;
import org.koin.core.logger.Level;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.core.scope.ScopeDefinition;
import org.koin.dsl.ScopeSet;

/* compiled from: ScopeRegistry.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\"\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0005j\u0002`\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\f2\n\u0010\u0012\u001a\u00060\u0005j\u0002`\u0013J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\u0005J\u0012\u0010\u001b\u001a\u00020\n2\n\u0010\u0012\u001a\u00060\u0005j\u0002`\u0013J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\n2\n\u0010\u0012\u001a\u00060\u0005j\u0002`\u0013J\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u001eJ\u000e\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001b\u0010 \u001a\u00020\f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\"H\u0000¢\u0006\u0002\b#J\u0010\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\nH\u0002J\u0010\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\f2\u0006\u0010%\u001a\u00020\nH\u0002J\u0010\u0010*\u001a\u00020\f2\u0006\u0010'\u001a\u00020(H\u0002J\u001b\u0010+\u001a\u00020\f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\"H\u0000¢\u0006\u0002\b,J\u0010\u0010-\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R \u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lorg/koin/core/registry/ScopeRegistry;", "", "()V", "definitions", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lorg/koin/core/scope/ScopeDefinition;", "getDefinitions$koin_core", "()Ljava/util/concurrent/ConcurrentHashMap;", "instances", "Lorg/koin/core/scope/Scope;", "close", "", "closeRelatedScopes", "originalSet", "createScopeInstance", "koin", "Lorg/koin/core/Koin;", BreakpointSQLiteKey.ID, "Lorg/koin/core/scope/ScopeID;", "scopeName", "Lorg/koin/core/qualifier/Qualifier;", "declareScopes", "module", "Lorg/koin/core/module/Module;", "deleteScopeInstance", "getScopeDefinition", "getScopeInstance", "getScopeInstanceOrNull", "getScopeSets", "", "loadDefaultScopes", "loadScopes", "modules", "", "loadScopes$koin_core", "registerScopeInstance", "instance", "saveDefinition", "scopeSet", "Lorg/koin/dsl/ScopeSet;", "saveInstance", "unloadDefinition", "unloadScopedDefinitions", "unloadScopedDefinitions$koin_core", "unloadScopes", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ScopeRegistry {
    private final ConcurrentHashMap<String, ScopeDefinition> definitions = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Scope> instances = new ConcurrentHashMap<>();

    public final ConcurrentHashMap<String, ScopeDefinition> getDefinitions$koin_core() {
        return this.definitions;
    }

    public final Collection<ScopeDefinition> getScopeSets() {
        Collection<ScopeDefinition> collectionValues = this.definitions.values();
        Intrinsics.checkExpressionValueIsNotNull(collectionValues, "definitions.values");
        return collectionValues;
    }

    private final void unloadScopes(Module module) {
        Iterator<T> it = module.getScopes$koin_core().iterator();
        while (it.hasNext()) {
            unloadDefinition((ScopeSet) it.next());
        }
    }

    public final void loadDefaultScopes(Koin koin) {
        Intrinsics.checkParameterIsNotNull(koin, "koin");
        saveInstance(koin.getRootScope());
    }

    private final void declareScopes(Module module) {
        Iterator<T> it = module.getScopes$koin_core().iterator();
        while (it.hasNext()) {
            saveDefinition((ScopeSet) it.next());
        }
    }

    private final void unloadDefinition(ScopeSet scopeSet) {
        ScopeDefinition scopeDefinition = this.definitions.get(scopeSet.getQualifier().toString());
        if (scopeDefinition != null) {
            if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
                KoinApplication.INSTANCE.getLogger().info("unbind scoped definitions: " + scopeSet.getDefinitions() + " from '" + scopeSet.getQualifier() + '\'');
            }
            Intrinsics.checkExpressionValueIsNotNull(scopeDefinition, "scopeDefinition");
            closeRelatedScopes(scopeDefinition);
            scopeDefinition.getDefinitions().removeAll(scopeSet.getDefinitions());
        }
    }

    private final void closeRelatedScopes(ScopeDefinition originalSet) {
        Collection<Scope> collectionValues = this.instances.values();
        Intrinsics.checkExpressionValueIsNotNull(collectionValues, "instances.values");
        for (Scope scope : collectionValues) {
            if (Intrinsics.areEqual(scope.getScopeDefinition(), originalSet)) {
                scope.close();
            }
        }
    }

    private final void saveDefinition(ScopeSet scopeSet) {
        ScopeDefinition scopeDefinition = this.definitions.get(scopeSet.getQualifier().toString());
        if (scopeDefinition == null) {
            this.definitions.put(scopeSet.getQualifier().toString(), scopeSet.createDefinition());
        } else {
            scopeDefinition.getDefinitions().addAll(scopeSet.getDefinitions());
        }
    }

    public final ScopeDefinition getScopeDefinition(String scopeName) {
        Intrinsics.checkParameterIsNotNull(scopeName, "scopeName");
        return this.definitions.get(scopeName);
    }

    public final Scope createScopeInstance(Koin koin, String id, Qualifier scopeName) throws ScopeAlreadyCreatedException, NoScopeDefinitionFoundException {
        Intrinsics.checkParameterIsNotNull(koin, "koin");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(scopeName, "scopeName");
        ScopeDefinition scopeDefinition = this.definitions.get(scopeName.toString());
        if (scopeDefinition == null) {
            throw new NoScopeDefinitionFoundException("No scope definition found for scopeName '" + scopeName + '\'');
        }
        Scope scope = new Scope(id, false, koin, 2, null);
        scope.setScopeDefinition(scopeDefinition);
        scope.declareDefinitionsFromScopeSet$koin_core();
        registerScopeInstance(scope);
        return scope;
    }

    private final void registerScopeInstance(Scope instance) throws ScopeAlreadyCreatedException {
        if (this.instances.get(instance.getId()) != null) {
            throw new ScopeAlreadyCreatedException("A scope with id '" + instance.getId() + "' already exists. Reuse or close it.");
        }
        saveInstance(instance);
    }

    public final Scope getScopeInstance(String id) throws ScopeNotCreatedException {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Scope scope = this.instances.get(id);
        if (scope != null) {
            return scope;
        }
        throw new ScopeNotCreatedException("ScopeInstance with id '" + id + "' not found. Create a scope instance with id '" + id + '\'');
    }

    private final void saveInstance(Scope instance) {
        this.instances.put(instance.getId(), instance);
    }

    public final Scope getScopeInstanceOrNull(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        return this.instances.get(id);
    }

    public final void deleteScopeInstance(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.instances.remove(id);
    }

    public final void close() {
        Collection<Scope> collectionValues = this.instances.values();
        Intrinsics.checkExpressionValueIsNotNull(collectionValues, "instances.values");
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            ((Scope) it.next()).close();
        }
        this.definitions.clear();
        this.instances.clear();
    }

    public final void loadScopes$koin_core(Iterable<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        Iterator<Module> it = modules.iterator();
        while (it.hasNext()) {
            declareScopes(it.next());
        }
    }

    public final void unloadScopedDefinitions$koin_core(Iterable<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        Iterator<Module> it = modules.iterator();
        while (it.hasNext()) {
            unloadScopes(it.next());
        }
    }
}
