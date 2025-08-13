package org.koin.core.scope;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.fitness.FitnessActivities;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.error.MissingPropertyException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.instance.InstanceContext;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.BeanRegistry;
import org.koin.core.time.MeasureKt;
import org.koin.ext.KClassExtKt;

/* compiled from: Scope.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ?\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%¢\u0006\u0002\u0010&J6\u0010\u001d\u001a\u0002H\u001e\"\u0006\b\u0000\u0010\u001e\u0018\u0001\"\u0006\b\u0001\u0010'\u0018\u00012\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0086\b¢\u0006\u0002\u0010(J\u0006\u0010)\u001a\u00020*J\r\u0010+\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\u000e\u0010-\u001a\u00020\bHÀ\u0003¢\u0006\u0002\b.J+\u0010/\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\r\u00100\u001a\u00020*H\u0000¢\u0006\u0002\b1J@\u00102\u001a\u00020*\"\u0006\b\u0000\u00103\u0018\u00012\u0006\u00104\u001a\u0002H32\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0014\b\u0002\u00107\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030 \u0018\u000108H\u0086\b¢\u0006\u0002\u00109J\r\u0010:\u001a\u00020*H\u0000¢\u0006\u0002\b;J\u0013\u0010<\u001a\u00020\u00062\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\"\u0010>\u001a\u0006\u0012\u0002\b\u00030?2\b\u00105\u001a\u0004\u0018\u0001062\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 H\u0002JC\u0010A\u001a\u0002H3\"\u0004\b\u0000\u001032\n\u0010@\u001a\u0006\u0012\u0002\b\u00030B2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0007¢\u0006\u0002\u0010CJ=\u0010A\u001a\u0002H3\"\u0004\b\u0000\u001032\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 2\b\u00105\u001a\u0004\u0018\u0001062\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%¢\u0006\u0002\u0010DJ:\u0010A\u001a\u0002H3\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0087\b¢\u0006\u0002\u0010EJ\u0017\u0010F\u001a\b\u0012\u0004\u0012\u0002H308\"\u0006\b\u0000\u00103\u0018\u0001H\u0086\bJ\u001e\u0010F\u001a\b\u0012\u0004\u0012\u0002H308\"\u0004\b\u0000\u001032\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 J\u0006\u0010G\u001a\u00020\bJ<\u0010H\u001a\u0004\u0018\u0001H3\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0087\b¢\u0006\u0002\u0010EJ\u0019\u0010I\u001a\u0002H3\"\u0004\b\u0000\u001032\u0006\u0010J\u001a\u00020\u0003¢\u0006\u0002\u0010KJ!\u0010I\u001a\u0002H3\"\u0004\b\u0000\u001032\u0006\u0010J\u001a\u00020\u00032\u0006\u0010L\u001a\u0002H3¢\u0006\u0002\u0010MJ\u001b\u0010N\u001a\u0004\u0018\u0001H3\"\u0004\b\u0000\u001032\u0006\u0010J\u001a\u00020\u0003¢\u0006\u0002\u0010KJ\u0012\u0010O\u001a\u00020\u00002\n\u0010P\u001a\u00060\u0003j\u0002`\u0004J\t\u0010Q\u001a\u00020RHÖ\u0001J;\u0010S\u001a\b\u0012\u0004\u0012\u0002H30T\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0087\bJ=\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H30T\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0087\bJ\u000e\u0010V\u001a\u00020*2\u0006\u0010W\u001a\u00020\u0012J?\u0010X\u001a\u0002H3\"\u0004\b\u0000\u001032\b\u00105\u001a\u0004\u0018\u0001062\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0002¢\u0006\u0002\u0010YJ\b\u0010Z\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006["}, d2 = {"Lorg/koin/core/scope/Scope;", "", BreakpointSQLiteKey.ID, "", "Lorg/koin/core/scope/ScopeID;", "isRoot", "", "_koin", "Lorg/koin/core/Koin;", "(Ljava/lang/String;ZLorg/koin/core/Koin;)V", "get_koin$koin_core", "()Lorg/koin/core/Koin;", "beanRegistry", "Lorg/koin/core/registry/BeanRegistry;", "getBeanRegistry", "()Lorg/koin/core/registry/BeanRegistry;", "callbacks", "Ljava/util/ArrayList;", "Lorg/koin/core/scope/ScopeCallback;", "Lkotlin/collections/ArrayList;", "getId", "()Ljava/lang/String;", "()Z", "scopeDefinition", "Lorg/koin/core/scope/ScopeDefinition;", "getScopeDefinition", "()Lorg/koin/core/scope/ScopeDefinition;", "setScopeDefinition", "(Lorg/koin/core/scope/ScopeDefinition;)V", "bind", ExifInterface.LATITUDE_SOUTH, "primaryType", "Lkotlin/reflect/KClass;", "secondaryType", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "P", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "component1", "component2", "component3", "component3$koin_core", "copy", "createEagerInstances", "createEagerInstances$koin_core", "declare", ExifInterface.GPS_DIRECTION_TRUE, "instance", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "secondaryTypes", "", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;)V", "declareDefinitionsFromScopeSet", "declareDefinitionsFromScopeSet$koin_core", "equals", FitnessActivities.OTHER, "findDefinition", "Lorg/koin/core/definition/BeanDefinition;", "clazz", "get", "Ljava/lang/Class;", "(Ljava/lang/Class;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAll", "getKoin", "getOrNull", "getProperty", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getPropertyOrNull", "getScope", "scopeID", "hashCode", "", "inject", "Lkotlin/Lazy;", "injectOrNull", "registerCallback", "callback", "resolveInstance", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toString", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final /* data */ class Scope {
    private final Koin _koin;
    private final BeanRegistry beanRegistry;
    private final ArrayList<ScopeCallback> callbacks;
    private final String id;
    private final boolean isRoot;
    private ScopeDefinition scopeDefinition;

    public static /* synthetic */ Scope copy$default(Scope scope, String str, boolean z, Koin koin, int i, Object obj) {
        if ((i & 1) != 0) {
            str = scope.id;
        }
        if ((i & 2) != 0) {
            z = scope.isRoot;
        }
        if ((i & 4) != 0) {
            koin = scope._koin;
        }
        return scope.copy(str, z, koin);
    }

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

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    /* renamed from: component3$koin_core, reason: from getter */
    public final Koin get_koin() {
        return this._koin;
    }

    public final Scope copy(String id, boolean isRoot, Koin _koin) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(_koin, "_koin");
        return new Scope(id, isRoot, _koin);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof Scope) {
                Scope scope = (Scope) other;
                if (Intrinsics.areEqual(this.id, scope.id)) {
                    if (!(this.isRoot == scope.isRoot) || !Intrinsics.areEqual(this._koin, scope._koin)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final <T> T get(Class<?> cls) {
        return (T) get$default(this, cls, null, null, 6, null);
    }

    public final <T> T get(Class<?> cls, Qualifier qualifier) {
        return (T) get$default(this, cls, qualifier, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.id;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isRoot;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        Koin koin = this._koin;
        return i2 + (koin != null ? koin.hashCode() : 0);
    }

    public Scope(String id, boolean z, Koin _koin) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(_koin, "_koin");
        this.id = id;
        this.isRoot = z;
        this._koin = _koin;
        this.beanRegistry = new BeanRegistry();
        this.callbacks = new ArrayList<>();
    }

    public final String getId() {
        return this.id;
    }

    public /* synthetic */ Scope(String str, boolean z, Koin koin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, koin);
    }

    public final boolean isRoot() {
        return this.isRoot;
    }

    public final Koin get_koin$koin_core() {
        return this._koin;
    }

    public final BeanRegistry getBeanRegistry() {
        return this.beanRegistry;
    }

    public final ScopeDefinition getScopeDefinition() {
        return this.scopeDefinition;
    }

    public final void setScopeDefinition(ScopeDefinition scopeDefinition) {
        this.scopeDefinition = scopeDefinition;
    }

    static /* synthetic */ Lazy inject$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(scope.new AnonymousClass1(qualifier, function0));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Scope.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0004\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    /* renamed from: org.koin.core.scope.Scope$inject$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends Lambda implements Function0<T> {
        final /* synthetic */ Function0 $parameters;
        final /* synthetic */ Qualifier $qualifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Qualifier qualifier, Function0 function0) {
            super(0);
            this.$qualifier = qualifier;
            this.$parameters = function0;
        }

        @Override // kotlin.jvm.functions.Function0
        public final T invoke() {
            Scope scope = Scope.this;
            Qualifier qualifier = this.$qualifier;
            Function0<DefinitionParameters> function0 = this.$parameters;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
        }
    }

    private final <T> Lazy<T> inject(Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new AnonymousClass1(qualifier, parameters));
    }

    static /* synthetic */ Lazy injectOrNull$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(scope.new C08331(qualifier, function0));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Scope.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0004\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    /* renamed from: org.koin.core.scope.Scope$injectOrNull$1, reason: invalid class name and case insensitive filesystem */
    public static final class C08331<T> extends Lambda implements Function0<T> {
        final /* synthetic */ Function0 $parameters;
        final /* synthetic */ Qualifier $qualifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08331(Qualifier qualifier, Function0 function0) {
            super(0);
            this.$qualifier = qualifier;
            this.$parameters = function0;
        }

        @Override // kotlin.jvm.functions.Function0
        public final T invoke() {
            Scope scope = Scope.this;
            Qualifier qualifier = this.$qualifier;
            Function0<DefinitionParameters> function0 = this.$parameters;
            try {
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                return (T) scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
            } catch (Exception unused) {
                Logger logger = KoinApplication.INSTANCE.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Can't get instance for ");
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
                logger.error(sb.toString());
                return null;
            }
        }
    }

    private final <T> Lazy<T> injectOrNull(Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new C08331(qualifier, parameters));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object get$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return scope.get((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), qualifier, (Function0<DefinitionParameters>) function0);
    }

    private final <T> T get(Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object getOrNull$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        try {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return scope.get((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), qualifier, (Function0<DefinitionParameters>) function0);
        } catch (Exception unused) {
            Logger logger = KoinApplication.INSTANCE.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }

    private final <T> T getOrNull(Qualifier qualifier, Function0<DefinitionParameters> parameters) {
        try {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
        } catch (Exception unused) {
            Logger logger = KoinApplication.INSTANCE.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }

    public final <T> T get(final KClass<?> clazz, final Qualifier qualifier, final Function0<DefinitionParameters> parameters) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        synchronized (this) {
            if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
                KoinApplication.INSTANCE.getLogger().debug("+- get '" + KClassExtKt.getFullName(clazz) + '\'');
                Pair pairMeasureDuration = MeasureKt.measureDuration(new Function0<T>() { // from class: org.koin.core.scope.Scope$get$$inlined$synchronized$lambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final T invoke() {
                        return (T) this.this$0.resolveInstance(qualifier, clazz, parameters);
                    }
                });
                T t = (T) pairMeasureDuration.component1();
                double dDoubleValue = ((Number) pairMeasureDuration.component2()).doubleValue();
                KoinApplication.INSTANCE.getLogger().debug("+- got '" + KClassExtKt.getFullName(clazz) + "' in " + dDoubleValue + " ms");
                return t;
            }
            return (T) resolveInstance(qualifier, clazz, parameters);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object get$default(Scope scope, Class cls, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return scope.get((Class<?>) cls, qualifier, (Function0<DefinitionParameters>) function0);
    }

    public final <T> T get(final Class<?> clazz, final Qualifier qualifier, final Function0<DefinitionParameters> parameters) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        synchronized (this) {
            final KClass<?> kotlinClass = JvmClassMappingKt.getKotlinClass(clazz);
            if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
                KoinApplication.INSTANCE.getLogger().debug("+- get '" + KClassExtKt.getFullName(kotlinClass) + '\'');
                Pair pairMeasureDuration = MeasureKt.measureDuration(new Function0<T>() { // from class: org.koin.core.scope.Scope$get$$inlined$synchronized$lambda$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final T invoke() {
                        return (T) this.resolveInstance(qualifier, kotlinClass, parameters);
                    }
                });
                T t = (T) pairMeasureDuration.component1();
                double dDoubleValue = ((Number) pairMeasureDuration.component2()).doubleValue();
                KoinApplication.INSTANCE.getLogger().debug("+- got '" + KClassExtKt.getFullName(kotlinClass) + "' in " + dDoubleValue + " ms");
                return t;
            }
            return (T) resolveInstance(qualifier, kotlinClass, parameters);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> T resolveInstance(Qualifier qualifier, KClass<?> clazz, Function0<DefinitionParameters> parameters) {
        return (T) findDefinition(qualifier, clazz).resolveInstance(new InstanceContext(this._koin, this, parameters));
    }

    private final BeanDefinition<?> findDefinition(Qualifier qualifier, KClass<?> clazz) throws NoBeanDefFoundException {
        BeanDefinition<?> beanDefinitionFindDefinition = this.beanRegistry.findDefinition(qualifier, clazz);
        if (beanDefinitionFindDefinition != null) {
            return beanDefinitionFindDefinition;
        }
        if (this.isRoot) {
            throw new NoBeanDefFoundException("No definition found for '" + KClassExtKt.getFullName(clazz) + "' has been found. Check your module definitions.");
        }
        return this._koin.getRootScope().findDefinition(qualifier, clazz);
    }

    public final void createEagerInstances$koin_core() {
        if (this.isRoot) {
            Set<BeanDefinition<?>> setFindAllCreatedAtStartDefinition$koin_core = this.beanRegistry.findAllCreatedAtStartDefinition$koin_core();
            if (!setFindAllCreatedAtStartDefinition$koin_core.isEmpty()) {
                Iterator<T> it = setFindAllCreatedAtStartDefinition$koin_core.iterator();
                while (it.hasNext()) {
                    ((BeanDefinition) it.next()).resolveInstance(new InstanceContext(this._koin, this, null, 4, null));
                }
            }
        }
    }

    static /* synthetic */ void declare$default(Scope scope, Object obj, Qualifier qualifier, List list, int i, Object obj2) {
        BeanDefinition<?> beanDefinition;
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        if (scope.isRoot()) {
            DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
            Scope$declare$definition$1 scope$declare$definition$1 = new Scope$declare$definition$1(obj);
            Kind kind = Kind.Single;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            beanDefinition = new BeanDefinition<>(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$1);
            beanDefinition.setKind(kind);
        } else {
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            ScopeDefinition scopeDefinition = scope.getScopeDefinition();
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
        scope.getBeanRegistry().saveDefinition(beanDefinition);
    }

    private final <T> void declare(T instance, Qualifier qualifier, List<? extends KClass<?>> secondaryTypes) {
        BeanDefinition<?> beanDefinition;
        if (isRoot()) {
            DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
            Scope$declare$definition$1 scope$declare$definition$1 = new Scope$declare$definition$1(instance);
            Kind kind = Kind.Single;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            beanDefinition = new BeanDefinition<>(qualifier, null, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$1);
            beanDefinition.setKind(kind);
        } else {
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            ScopeDefinition scopeDefinition = getScopeDefinition();
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
        getBeanRegistry().saveDefinition(beanDefinition);
    }

    public final Koin getKoin() {
        return this._koin;
    }

    public final Scope getScope(String scopeID) {
        Intrinsics.checkParameterIsNotNull(scopeID, "scopeID");
        return getKoin().getScope(scopeID);
    }

    public final void registerCallback(ScopeCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callbacks.add(callback);
    }

    private final <T> List<T> getAll() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getAll(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final <T> List<T> getAll(KClass<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        List<BeanDefinition<?>> definitionsForClass = this.beanRegistry.getDefinitionsForClass(clazz);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(definitionsForClass, 10));
        Iterator<T> it = definitionsForClass.iterator();
        while (it.hasNext()) {
            DefinitionInstance<T> beanDefinition = ((BeanDefinition) it.next()).getInstance();
            if (beanDefinition == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(beanDefinition.get(new InstanceContext(this._koin, this, null, 4, null)));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object bind$default(Scope scope, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return scope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    private final <S, P> S bind(Function0<DefinitionParameters> parameters) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass<?> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return (S) bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, parameters);
    }

    public final <S> S bind(KClass<?> primaryType, KClass<?> secondaryType, Function0<DefinitionParameters> parameters) {
        Intrinsics.checkParameterIsNotNull(primaryType, "primaryType");
        Intrinsics.checkParameterIsNotNull(secondaryType, "secondaryType");
        Iterator<T> it = this.beanRegistry.getAllDefinitions().iterator();
        while (it.hasNext()) {
            BeanDefinition beanDefinition = (BeanDefinition) it.next();
            if (Intrinsics.areEqual(beanDefinition.getPrimaryType(), primaryType) && beanDefinition.getSecondaryTypes().contains(secondaryType)) {
                DefinitionInstance beanDefinition2 = beanDefinition.getInstance();
                if (beanDefinition2 == null) {
                    Intrinsics.throwNpe();
                }
                return (S) beanDefinition2.get(new InstanceContext(getKoin(), this, parameters));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final <T> T getProperty(String key, T defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) this._koin.getProperty(key, defaultValue);
    }

    public final <T> T getPropertyOrNull(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) this._koin.getProperty(key);
    }

    public final <T> T getProperty(String key) throws MissingPropertyException {
        Intrinsics.checkParameterIsNotNull(key, "key");
        T t = (T) this._koin.getProperty(key);
        if (t != null) {
            return t;
        }
        throw new MissingPropertyException("Property '" + key + "' not found");
    }

    public final void declareDefinitionsFromScopeSet$koin_core() {
        HashSet<BeanDefinition<?>> definitions;
        ScopeDefinition scopeDefinition = this.scopeDefinition;
        if (scopeDefinition == null || (definitions = scopeDefinition.getDefinitions()) == null) {
            return;
        }
        for (BeanDefinition<?> beanDefinition : definitions) {
            this.beanRegistry.saveDefinition(beanDefinition);
            beanDefinition.createInstanceHolder();
        }
    }

    public final void close() {
        synchronized (this) {
            if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
                KoinApplication.INSTANCE.getLogger().info("closing scope:'" + this.id + '\'');
            }
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((ScopeCallback) it.next()).onScopeClose(this);
            }
            this.callbacks.clear();
            ScopeDefinition scopeDefinition = this.scopeDefinition;
            if (scopeDefinition != null) {
                scopeDefinition.release$koin_core(this);
            }
            this.beanRegistry.close();
            this._koin.deleteScope(this.id);
            Unit unit = Unit.INSTANCE;
        }
    }

    public String toString() {
        ScopeDefinition scopeDefinition = this.scopeDefinition;
        StringBuilder sb = new StringBuilder();
        sb.append(",set:'");
        sb.append(scopeDefinition != null ? scopeDefinition.getQualifier() : null);
        sb.append('\'');
        return "Scope[id:'" + this.id + '\'' + sb.toString() + ']';
    }
}
