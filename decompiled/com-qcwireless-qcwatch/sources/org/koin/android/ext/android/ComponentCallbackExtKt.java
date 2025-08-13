package org.koin.android.ext.android;

import android.content.ComponentCallbacks;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.context.GlobalContext;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ComponentCallbackExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0002\u0018\u0001*\u00020\u00032\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001aB\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\u0086\b¢\u0006\u0002\u0010\u000e\u001a\n\u0010\u000f\u001a\u00020\u0010*\u00020\u0003\u001aC\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\n0\u0012\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\u0086\b¨\u0006\u0013"}, d2 = {"bind", ExifInterface.LATITUDE_SOUTH, "P", "Landroid/content/ComponentCallbacks;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Landroid/content/ComponentCallbacks;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "get", ExifInterface.GPS_DIRECTION_TRUE, "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "(Landroid/content/ComponentCallbacks;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getKoin", "Lorg/koin/core/Koin;", "inject", "Lkotlin/Lazy;", "koin-android_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ComponentCallbackExtKt {
    public static final Koin getKoin(ComponentCallbacks getKoin) {
        Intrinsics.checkParameterIsNotNull(getKoin, "$this$getKoin");
        return getKoin instanceof KoinComponent ? ((KoinComponent) getKoin).getKoin() : GlobalContext.get().getKoin();
    }

    static /* synthetic */ Lazy inject$default(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(new AnonymousClass1(componentCallbacks, qualifier, function0));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: ComponentCallbackExt.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    /* renamed from: org.koin.android.ext.android.ComponentCallbackExtKt$inject$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends Lambda implements Function0<T> {
        final /* synthetic */ Function0 $parameters;
        final /* synthetic */ Qualifier $qualifier;
        final /* synthetic */ ComponentCallbacks $this_inject;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
            super(0);
            this.$this_inject = componentCallbacks;
            this.$qualifier = qualifier;
            this.$parameters = function0;
        }

        @Override // kotlin.jvm.functions.Function0
        public final T invoke() {
            ComponentCallbacks componentCallbacks = this.$this_inject;
            Qualifier qualifier = this.$qualifier;
            Function0<DefinitionParameters> function0 = this.$parameters;
            Scope rootScope = ComponentCallbackExtKt.getKoin(componentCallbacks).getRootScope();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
        }
    }

    private static final <T> Lazy<T> inject(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new AnonymousClass1(componentCallbacks, qualifier, function0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object get$default(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.get((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), qualifier, (Function0<DefinitionParameters>) function0);
    }

    private static final <T> T get(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object bind$default(ComponentCallbacks componentCallbacks, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    private static final <S, P> S bind(ComponentCallbacks componentCallbacks, Function0<DefinitionParameters> function0) {
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass<?> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return (S) rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }
}
