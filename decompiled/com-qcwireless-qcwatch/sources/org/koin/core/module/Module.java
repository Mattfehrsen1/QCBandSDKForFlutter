package org.koin.core.module;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.dsl.ScopeSet;

/* compiled from: Module.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\"\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\b2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0010JX\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00140\b\"\u0006\b\u0000\u0010\u0014\u0018\u00012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u0004\u001a\u00020\u00032)\b\b\u0010\u0015\u001a#\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\u00140\u001dj\b\u0012\u0004\u0012\u0002H\u0014` ¢\u0006\u0002\b!H\u0086\bJ\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00000#2\u0006\u0010$\u001a\u00020\u0000H\u0086\u0002J'\u0010\u0019\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001c2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00130'¢\u0006\u0002\b!Jb\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00140\b\"\u0006\b\u0000\u0010\u0014\u0018\u00012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032)\b\b\u0010\u0015\u001a#\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\u00140\u001dj\b\u0012\u0004\u0012\u0002H\u0014` ¢\u0006\u0002\b!H\u0086\bJ\u0018\u0010*\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R,\u0010\u0006\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b`\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR$\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0007j\b\u0012\u0004\u0012\u00020\u0010`\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006+"}, d2 = {"Lorg/koin/core/module/Module;", "", "isCreatedAtStart", "", "override", "(ZZ)V", "definitions", "Ljava/util/ArrayList;", "Lorg/koin/core/definition/BeanDefinition;", "Lkotlin/collections/ArrayList;", "getDefinitions$koin_core", "()Ljava/util/ArrayList;", "isCreatedAtStart$koin_core", "()Z", "getOverride$koin_core", "scopes", "Lorg/koin/dsl/ScopeSet;", "getScopes$koin_core", "declareDefinition", "", ExifInterface.GPS_DIRECTION_TRUE, "definition", "options", "Lorg/koin/core/definition/Options;", "declareScope", "scope", "factory", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "plus", "", "module", "scopeName", "scopeSet", "Lkotlin/Function1;", "single", "createdAtStart", "updateOptions", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class Module {
    private final boolean isCreatedAtStart;
    private final boolean override;
    private final ArrayList<BeanDefinition<?>> definitions = new ArrayList<>();
    private final ArrayList<ScopeSet> scopes = new ArrayList<>();

    public Module(boolean z, boolean z2) {
        this.isCreatedAtStart = z;
        this.override = z2;
    }

    /* renamed from: isCreatedAtStart$koin_core, reason: from getter */
    public final boolean getIsCreatedAtStart() {
        return this.isCreatedAtStart;
    }

    /* renamed from: getOverride$koin_core, reason: from getter */
    public final boolean getOverride() {
        return this.override;
    }

    public final ArrayList<BeanDefinition<?>> getDefinitions$koin_core() {
        return this.definitions;
    }

    public final ArrayList<ScopeSet> getScopes$koin_core() {
        return this.scopes;
    }

    public final <T> void declareDefinition(BeanDefinition<T> definition, Options options) {
        Intrinsics.checkParameterIsNotNull(definition, "definition");
        Intrinsics.checkParameterIsNotNull(options, "options");
        updateOptions(definition, options);
        this.definitions.add(definition);
    }

    public final void declareScope(ScopeSet scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        this.scopes.add(scope);
    }

    static /* synthetic */ BeanDefinition single$default(Module module, Qualifier qualifier, boolean z, boolean z2, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Single;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        module.declareDefinition(beanDefinition, new Options(z, z2));
        return beanDefinition;
    }

    private final <T> BeanDefinition<T> single(Qualifier qualifier, boolean createdAtStart, boolean override, Function2<? super Scope, ? super DefinitionParameters, ? extends T> definition) {
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Single;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition<T> beanDefinition = new BeanDefinition<>(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(definition);
        beanDefinition.setKind(kind);
        declareDefinition(beanDefinition, new Options(createdAtStart, override));
        return beanDefinition;
    }

    private final void updateOptions(BeanDefinition<?> beanDefinition, Options options) {
        beanDefinition.getOptions().setCreatedAtStart(options.isCreatedAtStart() || this.isCreatedAtStart);
        beanDefinition.getOptions().setOverride(options.getOverride() || this.override);
    }

    public final void scope(Qualifier scopeName, Function1<? super ScopeSet, Unit> scopeSet) {
        Intrinsics.checkParameterIsNotNull(scopeName, "scopeName");
        Intrinsics.checkParameterIsNotNull(scopeSet, "scopeSet");
        ScopeSet scopeSet2 = new ScopeSet(scopeName);
        scopeSet.invoke(scopeSet2);
        declareScope(scopeSet2);
    }

    static /* synthetic */ BeanDefinition factory$default(Module module, Qualifier qualifier, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Factory;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        module.declareDefinition(beanDefinition, new Options(false, z, 1, null));
        return beanDefinition;
    }

    private final <T> BeanDefinition<T> factory(Qualifier qualifier, boolean override, Function2<? super Scope, ? super DefinitionParameters, ? extends T> definition) {
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Factory;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition<T> beanDefinition = new BeanDefinition<>(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(definition);
        beanDefinition.setKind(kind);
        declareDefinition(beanDefinition, new Options(false, override, 1, null));
        return beanDefinition;
    }

    public final List<Module> plus(Module module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        return CollectionsKt.listOf((Object[]) new Module[]{this, module});
    }
}
