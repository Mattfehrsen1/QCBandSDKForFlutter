package org.koin.core.definition;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.instance.FactoryDefinitionInstance;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.ScopeDefinitionInstance;
import org.koin.core.instance.SingleDefinitionInstance;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: BeanDefinition.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010C\u001a\u00020!J\u0006\u0010D\u001a\u00020!J\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0006\u0010H\u001a\u00020FJ\b\u0010I\u001a\u00020JH\u0016J\u0019\u0010K\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010L\u001a\u00020M¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020PH\u0016R;\u0010\t\u001a#\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u00000\nj\b\u0012\u0004\u0012\u00028\u0000`\r¢\u0006\u0002\b\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR6\u0010\u001f\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020!\u0018\u00010 j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R6\u0010'\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020!\u0018\u00010 j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0015\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010:R2\u0010<\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070=j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007`>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010B¨\u0006Q"}, d2 = {"Lorg/koin/core/definition/BeanDefinition;", ExifInterface.GPS_DIRECTION_TRUE, "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "scopeName", "primaryType", "Lkotlin/reflect/KClass;", "(Lorg/koin/core/qualifier/Qualifier;Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;)V", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "getDefinition", "()Lkotlin/jvm/functions/Function2;", "setDefinition", "(Lkotlin/jvm/functions/Function2;)V", "instance", "Lorg/koin/core/instance/DefinitionInstance;", "getInstance", "()Lorg/koin/core/instance/DefinitionInstance;", "setInstance", "(Lorg/koin/core/instance/DefinitionInstance;)V", "kind", "Lorg/koin/core/definition/Kind;", "getKind", "()Lorg/koin/core/definition/Kind;", "setKind", "(Lorg/koin/core/definition/Kind;)V", "onClose", "Lkotlin/Function1;", "", "Lorg/koin/core/definition/OnCloseCallback;", "getOnClose", "()Lkotlin/jvm/functions/Function1;", "setOnClose", "(Lkotlin/jvm/functions/Function1;)V", "onRelease", "Lorg/koin/core/definition/OnReleaseCallback;", "getOnRelease", "setOnRelease", "options", "Lorg/koin/core/definition/Options;", "getOptions", "()Lorg/koin/core/definition/Options;", "setOptions", "(Lorg/koin/core/definition/Options;)V", "getPrimaryType", "()Lkotlin/reflect/KClass;", "properties", "Lorg/koin/core/definition/Properties;", "getProperties", "()Lorg/koin/core/definition/Properties;", "setProperties", "(Lorg/koin/core/definition/Properties;)V", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "getScopeName", "secondaryTypes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getSecondaryTypes", "()Ljava/util/ArrayList;", "setSecondaryTypes", "(Ljava/util/ArrayList;)V", "close", "createInstanceHolder", "equals", "", FitnessActivities.OTHER, "hasScopeSet", "hashCode", "", "resolveInstance", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "toString", "", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class BeanDefinition<T> {
    public Function2<? super Scope, ? super DefinitionParameters, ? extends T> definition;
    private DefinitionInstance<T> instance;
    public Kind kind;
    private Function1<? super T, Unit> onClose;
    private Function1<? super T, Unit> onRelease;
    private Options options;
    private final KClass<?> primaryType;
    private Properties properties;
    private final Qualifier qualifier;
    private final Qualifier scopeName;
    private ArrayList<KClass<?>> secondaryTypes;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Kind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Kind.Single.ordinal()] = 1;
            iArr[Kind.Factory.ordinal()] = 2;
            iArr[Kind.Scoped.ordinal()] = 3;
        }
    }

    public BeanDefinition(Qualifier qualifier, Qualifier qualifier2, KClass<?> primaryType) {
        Intrinsics.checkParameterIsNotNull(primaryType, "primaryType");
        this.qualifier = qualifier;
        this.scopeName = qualifier2;
        this.primaryType = primaryType;
        this.secondaryTypes = new ArrayList<>();
        this.options = new Options(false, false, 3, null);
        this.properties = new Properties(null, 1, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ BeanDefinition(Qualifier qualifier, Qualifier qualifier2, KClass kClass, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            qualifier2 = null;
        }
        this(qualifier, qualifier2, kClass);
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final Qualifier getScopeName() {
        return this.scopeName;
    }

    public final KClass<?> getPrimaryType() {
        return this.primaryType;
    }

    public final ArrayList<KClass<?>> getSecondaryTypes() {
        return this.secondaryTypes;
    }

    public final void setSecondaryTypes(ArrayList<KClass<?>> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.secondaryTypes = arrayList;
    }

    public final DefinitionInstance<T> getInstance() {
        return this.instance;
    }

    public final void setInstance(DefinitionInstance<T> definitionInstance) {
        this.instance = definitionInstance;
    }

    public final Function2<Scope, DefinitionParameters, T> getDefinition() {
        Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2 = this.definition;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("definition");
        }
        return function2;
    }

    public final void setDefinition(Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.definition = function2;
    }

    public final Options getOptions() {
        return this.options;
    }

    public final void setOptions(Options options) {
        Intrinsics.checkParameterIsNotNull(options, "<set-?>");
        this.options = options;
    }

    public final Properties getProperties() {
        return this.properties;
    }

    public final void setProperties(Properties properties) {
        Intrinsics.checkParameterIsNotNull(properties, "<set-?>");
        this.properties = properties;
    }

    public final Kind getKind() {
        Kind kind = this.kind;
        if (kind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("kind");
        }
        return kind;
    }

    public final void setKind(Kind kind) {
        Intrinsics.checkParameterIsNotNull(kind, "<set-?>");
        this.kind = kind;
    }

    public final Function1<T, Unit> getOnRelease() {
        return this.onRelease;
    }

    public final void setOnRelease(Function1<? super T, Unit> function1) {
        this.onRelease = function1;
    }

    public final Function1<T, Unit> getOnClose() {
        return this.onClose;
    }

    public final void setOnClose(Function1<? super T, Unit> function1) {
        this.onClose = function1;
    }

    public final boolean hasScopeSet() {
        return this.scopeName != null;
    }

    public final void createInstanceHolder() {
        SingleDefinitionInstance singleDefinitionInstance;
        Kind kind = this.kind;
        if (kind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("kind");
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 1) {
            singleDefinitionInstance = new SingleDefinitionInstance(this);
        } else if (i == 2) {
            singleDefinitionInstance = new FactoryDefinitionInstance(this);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            singleDefinitionInstance = new ScopeDefinitionInstance(this);
        }
        this.instance = singleDefinitionInstance;
    }

    public final <T> T resolveInstance(InstanceContext context) {
        T t;
        Intrinsics.checkParameterIsNotNull(context, "context");
        DefinitionInstance<T> definitionInstance = this.instance;
        if (definitionInstance != null && (t = definitionInstance.get(context)) != null) {
            return t;
        }
        throw new IllegalStateException(("Definition without any InstanceContext - " + this).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r14 = this;
            org.koin.core.definition.Kind r0 = r14.kind
            if (r0 != 0) goto L9
            java.lang.String r1 = "kind"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L9:
            java.lang.String r0 = r0.toString()
            org.koin.core.qualifier.Qualifier r1 = r14.qualifier
            java.lang.String r2 = "', "
            java.lang.String r3 = ""
            if (r1 == 0) goto L2e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "name:'"
            r1.append(r4)
            org.koin.core.qualifier.Qualifier r4 = r14.qualifier
            r1.append(r4)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            if (r1 == 0) goto L2e
            goto L2f
        L2e:
            r1 = r3
        L2f:
            org.koin.core.qualifier.Qualifier r4 = r14.scopeName
            if (r4 == 0) goto L4c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "scope:'"
            r4.append(r5)
            org.koin.core.qualifier.Qualifier r5 = r14.scopeName
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            if (r2 == 0) goto L4c
            goto L4d
        L4c:
            r2 = r3
        L4d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "primary_type:'"
            r4.append(r5)
            kotlin.reflect.KClass<?> r5 = r14.primaryType
            java.lang.String r5 = org.koin.ext.KClassExtKt.getFullName(r5)
            r4.append(r5)
            r5 = 39
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.util.ArrayList<kotlin.reflect.KClass<?>> r5 = r14.secondaryTypes
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            r5 = r5 ^ 1
            if (r5 == 0) goto La0
            java.util.ArrayList<kotlin.reflect.KClass<?>> r3 = r14.secondaryTypes
            r5 = r3
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.lang.String r3 = ","
            r6 = r3
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1 r3 = new kotlin.jvm.functions.Function1<kotlin.reflect.KClass<?>, java.lang.String>() { // from class: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1
                static {
                    /*
                        org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1 r0 = new org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1) org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1.INSTANCE org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.String invoke(kotlin.reflect.KClass<?> r1) {
                    /*
                        r0 = this;
                        kotlin.reflect.KClass r1 = (kotlin.reflect.KClass) r1
                        java.lang.String r1 = r0.invoke(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1.invoke(java.lang.Object):java.lang.Object");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.String invoke(kotlin.reflect.KClass<?> r2) {
                    /*
                        r1 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
                        java.lang.String r2 = org.koin.ext.KClassExtKt.getFullName(r2)
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1.invoke(kotlin.reflect.KClass):java.lang.String");
                }
            }
            r11 = r3
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            r12 = 30
            r13 = 0
            java.lang.String r3 = kotlin.collections.CollectionsKt.joinToString$default(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = ", secondary_type:"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
        La0:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[type:"
            r5.append(r6)
            r5.append(r0)
            r0 = 44
            r5.append(r0)
            r5.append(r2)
            r5.append(r1)
            r5.append(r4)
            r5.append(r3)
            r0 = 93
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.BeanDefinition.toString():java.lang.String");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            BeanDefinition beanDefinition = (BeanDefinition) other;
            return ((Intrinsics.areEqual(this.qualifier, beanDefinition.qualifier) ^ true) || (Intrinsics.areEqual(this.primaryType, beanDefinition.primaryType) ^ true)) ? false : true;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.koin.core.definition.BeanDefinition<*>");
    }

    public int hashCode() {
        Qualifier qualifier = this.qualifier;
        return ((qualifier != null ? qualifier.hashCode() : 0) * 31) + this.primaryType.hashCode();
    }

    public final void close() {
        DefinitionInstance<T> definitionInstance = this.instance;
        if (definitionInstance != null) {
            definitionInstance.close();
        }
        this.instance = null;
    }
}
