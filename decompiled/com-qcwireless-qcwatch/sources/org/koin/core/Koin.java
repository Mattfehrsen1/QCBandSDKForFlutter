package org.koin.core;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.PropertyRegistry;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.core.scope.Scope$declare$definition$1;
import org.koin.core.scope.Scope$declare$definition$2;
import org.koin.core.scope.ScopeDefinition;
import org.koin.ext.KClassExtKt;

/* compiled from: Koin.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JA\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00122\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017¢\u0006\u0002\u0010\u0018J6\u0010\u000f\u001a\u0002H\u0010\"\u0006\b\u0000\u0010\u0010\u0018\u0001\"\u0006\b\u0001\u0010\u0019\u0018\u00012\u0016\b\n\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017H\u0086\b¢\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\r\u0010\u001d\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001eJ\u001a\u0010\u001f\u001a\u00020\b2\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010#\u001a\u00020$J@\u0010%\u001a\u00020\u001c\"\u0006\b\u0000\u0010&\u0018\u00012\u0006\u0010'\u001a\u0002H&2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0014\b\u0002\u0010(\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0018\u00010)H\u0086\b¢\u0006\u0002\u0010*J\u0012\u0010+\u001a\u00020\u001c2\n\u0010 \u001a\u00060!j\u0002`\"J=\u0010,\u001a\u0002H&\"\u0004\b\u0000\u0010&2\n\u0010-\u001a\u0006\u0012\u0002\b\u00030\u00122\b\u0010#\u001a\u0004\u0018\u00010$2\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017¢\u0006\u0002\u0010.J:\u0010,\u001a\u0002H&\"\u0006\b\u0000\u0010&\u0018\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0016\b\n\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017H\u0087\b¢\u0006\u0002\u0010/J\u0017\u00100\u001a\b\u0012\u0004\u0012\u0002H&0)\"\u0006\b\u0000\u0010&\u0018\u0001H\u0086\bJ\u001a\u00101\u001a\u00020\b2\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010#\u001a\u00020$J<\u00102\u001a\u0004\u0018\u0001H&\"\u0006\b\u0000\u0010&\u0018\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0016\b\n\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017H\u0087\b¢\u0006\u0002\u0010/J\u001b\u00103\u001a\u0004\u0018\u0001H&\"\u0004\b\u0000\u0010&2\u0006\u00104\u001a\u00020!¢\u0006\u0002\u00105J!\u00103\u001a\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u00104\u001a\u00020!2\u0006\u00106\u001a\u0002H&¢\u0006\u0002\u00107J\u0012\u00108\u001a\u00020\b2\n\u0010 \u001a\u00060!j\u0002`\"J\u0014\u00109\u001a\u0004\u0018\u00010\b2\n\u0010 \u001a\u00060!j\u0002`\"J;\u0010:\u001a\b\u0012\u0004\u0012\u0002H&0;\"\u0006\b\u0000\u0010&\u0018\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0016\b\n\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017H\u0087\bJ=\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H&0;\"\u0006\b\u0000\u0010&\u0018\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0016\b\n\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0004\u0018\u0001`\u0017H\u0087\bJ%\u0010=\u001a\u00020\u001c\"\b\b\u0000\u0010&*\u00020\u00012\u0006\u00104\u001a\u00020!2\u0006\u0010>\u001a\u0002H&¢\u0006\u0002\u0010?R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006@"}, d2 = {"Lorg/koin/core/Koin;", "", "()V", "propertyRegistry", "Lorg/koin/core/registry/PropertyRegistry;", "getPropertyRegistry", "()Lorg/koin/core/registry/PropertyRegistry;", "rootScope", "Lorg/koin/core/scope/Scope;", "getRootScope", "()Lorg/koin/core/scope/Scope;", "scopeRegistry", "Lorg/koin/core/registry/ScopeRegistry;", "getScopeRegistry", "()Lorg/koin/core/registry/ScopeRegistry;", "bind", ExifInterface.LATITUDE_SOUTH, "primaryType", "Lkotlin/reflect/KClass;", "secondaryType", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "P", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "createEagerInstances", "createEagerInstances$koin_core", "createScope", "scopeId", "", "Lorg/koin/core/scope/ScopeID;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "declare", ExifInterface.GPS_DIRECTION_TRUE, "instance", "secondaryTypes", "", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;)V", "deleteScope", "get", "clazz", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAll", "getOrCreateScope", "getOrNull", "getProperty", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getScope", "getScopeOrNull", "inject", "Lkotlin/Lazy;", "injectOrNull", "setProperty", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class Koin {
    private final ScopeRegistry scopeRegistry = new ScopeRegistry();
    private final PropertyRegistry propertyRegistry = new PropertyRegistry();
    private final Scope rootScope = new Scope("-Root-", true, this);

    private final <T> T get() {
        return (T) get$default(this, null, null, 3, null);
    }

    private final <T> T get(Qualifier qualifier) {
        return (T) get$default(this, qualifier, null, 2, null);
    }

    private final <T> T getOrNull() {
        return (T) getOrNull$default(this, null, null, 3, null);
    }

    private final <T> T getOrNull(Qualifier qualifier) {
        return (T) getOrNull$default(this, qualifier, null, 2, null);
    }

    private final <T> Lazy<T> inject() {
        return inject$default(this, null, null, 3, null);
    }

    private final <T> Lazy<T> inject(Qualifier qualifier) {
        return inject$default(this, qualifier, null, 2, null);
    }

    private final <T> Lazy<T> injectOrNull() {
        return injectOrNull$default(this, null, null, 3, null);
    }

    private final <T> Lazy<T> injectOrNull(Qualifier qualifier) {
        return injectOrNull$default(this, qualifier, null, 2, null);
    }

    public final ScopeRegistry getScopeRegistry() {
        return this.scopeRegistry;
    }

    public final PropertyRegistry getPropertyRegistry() {
        return this.propertyRegistry;
    }

    public final Scope getRootScope() {
        return this.rootScope;
    }

    static /* synthetic */ Lazy inject$default(Koin koin, final Qualifier qualifier, final Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        final Scope rootScope = koin.getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.core.Koin$inject$$inlined$inject$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                Scope scope = rootScope;
                Qualifier qualifier2 = qualifier;
                Function0<DefinitionParameters> function02 = function0;
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                return (T) scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier2, function02);
            }
        });
    }

    private final <T> Lazy<T> inject(final Qualifier qualifier, final Function0<DefinitionParameters> parameters) {
        final Scope rootScope = getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.core.Koin$inject$$inlined$inject$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                Scope scope = rootScope;
                Qualifier qualifier2 = qualifier;
                Function0<DefinitionParameters> function0 = parameters;
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                return (T) scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier2, function0);
            }
        });
    }

    static /* synthetic */ Lazy injectOrNull$default(Koin koin, final Qualifier qualifier, final Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        final Scope rootScope = koin.getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.core.Koin$injectOrNull$$inlined$injectOrNull$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                Scope scope = rootScope;
                Qualifier qualifier2 = qualifier;
                Function0<DefinitionParameters> function02 = function0;
                try {
                    Intrinsics.reifiedOperationMarker(4, "T?");
                    return (T) scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier2, function02);
                } catch (Exception unused) {
                    Logger logger = KoinApplication.INSTANCE.getLogger();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can't get instance for ");
                    Intrinsics.reifiedOperationMarker(4, "T?");
                    sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
                    logger.error(sb.toString());
                    return null;
                }
            }
        });
    }

    private final <T> Lazy<T> injectOrNull(final Qualifier qualifier, final Function0<DefinitionParameters> parameters) {
        final Scope rootScope = getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.core.Koin$injectOrNull$$inlined$injectOrNull$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                Scope scope = rootScope;
                Qualifier qualifier2 = qualifier;
                Function0<DefinitionParameters> function0 = parameters;
                try {
                    Intrinsics.reifiedOperationMarker(4, "T?");
                    return (T) scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier2, function0);
                } catch (Exception unused) {
                    Logger logger = KoinApplication.INSTANCE.getLogger();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can't get instance for ");
                    Intrinsics.reifiedOperationMarker(4, "T?");
                    sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
                    logger.error(sb.toString());
                    return null;
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object get$default(Koin koin, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Scope rootScope = koin.getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.get((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), qualifier, (Function0<DefinitionParameters>) function0);
    }

    private final <T> T get(Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        Scope rootScope = getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object getOrNull$default(Koin koin, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Scope rootScope = koin.getRootScope();
        try {
            Intrinsics.reifiedOperationMarker(4, "T?");
            return rootScope.get((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), qualifier, (Function0<DefinitionParameters>) function0);
        } catch (Exception unused) {
            Logger logger = KoinApplication.INSTANCE.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, "T?");
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }

    private final <T> T getOrNull(Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        Scope rootScope = getRootScope();
        try {
            Intrinsics.reifiedOperationMarker(4, "T?");
            return (T) rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
        } catch (Exception unused) {
            Logger logger = KoinApplication.INSTANCE.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, "T?");
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }

    public final <T> T get(KClass<?> clazz, Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return (T) this.rootScope.get(clazz, qualifier, parameters);
    }

    static /* synthetic */ void declare$default(Koin koin, Object obj, Qualifier qualifier, List list, int i, Object obj2) {
        BeanDefinition<?> beanDefinition;
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        Scope rootScope = koin.getRootScope();
        if (rootScope.isRoot()) {
            DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
            Scope$declare$definition$1 scope$declare$definition$1 = new Scope$declare$definition$1(obj);
            Kind kind = Kind.Single;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            beanDefinition = new BeanDefinition<>(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$1);
            beanDefinition.setKind(kind);
        } else {
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            ScopeDefinition scopeDefinition = rootScope.getScopeDefinition();
            Qualifier qualifier2 = scopeDefinition != null ? scopeDefinition.getQualifier() : null;
            Scope$declare$definition$2 scope$declare$definition$2 = new Scope$declare$definition$2(obj);
            Kind kind2 = Kind.Scoped;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            beanDefinition = new BeanDefinition<>(qualifier, qualifier2, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$2);
            beanDefinition.setKind(kind2);
        }
        if (list != null) {
            beanDefinition.getSecondaryTypes().addAll(list);
        }
        rootScope.getBeanRegistry().saveDefinition(beanDefinition);
    }

    private final <T> void declare(T instance, Qualifier qualifier, List<? extends KClass<?>> secondaryTypes) {
        BeanDefinition<?> beanDefinition;
        Scope rootScope = getRootScope();
        if (rootScope.isRoot()) {
            DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
            Scope$declare$definition$1 scope$declare$definition$1 = new Scope$declare$definition$1(instance);
            Kind kind = Kind.Single;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            beanDefinition = new BeanDefinition<>(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$1);
            beanDefinition.setKind(kind);
        } else {
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            ScopeDefinition scopeDefinition = rootScope.getScopeDefinition();
            Qualifier qualifier2 = scopeDefinition != null ? scopeDefinition.getQualifier() : null;
            Scope$declare$definition$2 scope$declare$definition$2 = new Scope$declare$definition$2(instance);
            Kind kind2 = Kind.Scoped;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            beanDefinition = new BeanDefinition<>(qualifier, qualifier2, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$2);
            beanDefinition.setKind(kind2);
        }
        if (secondaryTypes != null) {
            beanDefinition.getSecondaryTypes().addAll(secondaryTypes);
        }
        rootScope.getBeanRegistry().saveDefinition(beanDefinition);
    }

    private final <T> List<T> getAll() {
        Scope rootScope = getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.getAll(Reflection.getOrCreateKotlinClass(Object.class));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object bind$default(Koin koin, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Scope rootScope = koin.getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    private final <S, P> S bind(Function0<DefinitionParameters> parameters) {
        Scope rootScope = getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass<?> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return (S) rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object bind$default(Koin koin, KClass kClass, KClass kClass2, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = null;
        }
        return koin.bind(kClass, kClass2, function0);
    }

    public final <S> S bind(KClass<?> primaryType, KClass<?> secondaryType, Function0<DefinitionParameters> parameters) {
        Intrinsics.checkParameterIsNotNull(primaryType, "primaryType");
        Intrinsics.checkParameterIsNotNull(secondaryType, "secondaryType");
        return (S) this.rootScope.bind(primaryType, secondaryType, parameters);
    }

    public final void createEagerInstances$koin_core() {
        this.rootScope.createEagerInstances$koin_core();
    }

    public final Scope createScope(String scopeId, Qualifier qualifier) {
        Intrinsics.checkParameterIsNotNull(scopeId, "scopeId");
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
            KoinApplication.INSTANCE.getLogger().debug("!- create scope - id:" + scopeId + " q:" + qualifier);
        }
        return this.scopeRegistry.createScopeInstance(this, scopeId, qualifier);
    }

    public final Scope getOrCreateScope(String scopeId, Qualifier qualifier) {
        Intrinsics.checkParameterIsNotNull(scopeId, "scopeId");
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        Scope scopeInstanceOrNull = this.scopeRegistry.getScopeInstanceOrNull(scopeId);
        return scopeInstanceOrNull != null ? scopeInstanceOrNull : createScope(scopeId, qualifier);
    }

    public final Scope getScope(String scopeId) {
        Intrinsics.checkParameterIsNotNull(scopeId, "scopeId");
        return this.scopeRegistry.getScopeInstance(scopeId);
    }

    public final Scope getScopeOrNull(String scopeId) {
        Intrinsics.checkParameterIsNotNull(scopeId, "scopeId");
        return this.scopeRegistry.getScopeInstanceOrNull(scopeId);
    }

    public final void deleteScope(String scopeId) {
        Intrinsics.checkParameterIsNotNull(scopeId, "scopeId");
        this.scopeRegistry.deleteScopeInstance(scopeId);
    }

    public final <T> T getProperty(String key, T defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        T t = (T) this.propertyRegistry.getProperty(key);
        return t != null ? t : defaultValue;
    }

    public final <T> T getProperty(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) this.propertyRegistry.getProperty(key);
    }

    public final <T> void setProperty(String key, T value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.propertyRegistry.saveProperty$koin_core(key, value);
    }

    public final void close() {
        this.scopeRegistry.close();
        this.rootScope.close();
        this.propertyRegistry.close();
    }
}
