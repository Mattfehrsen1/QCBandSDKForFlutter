package org.koin.core;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: KoinComponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0002\u0018\u0001*\u00020\u00032\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001a>\u0010\t\u001a\u0002H\n\"\u0006\b\u0000\u0010\n\u0018\u0001*\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\u0086\b¢\u0006\u0002\u0010\r\u001a?\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u000f\"\u0006\b\u0000\u0010\n\u0018\u0001*\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\u0086\b¨\u0006\u0010"}, d2 = {"bind", ExifInterface.LATITUDE_SOUTH, "P", "Lorg/koin/core/KoinComponent;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lorg/koin/core/KoinComponent;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "get", ExifInterface.GPS_DIRECTION_TRUE, "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "(Lorg/koin/core/KoinComponent;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "inject", "Lkotlin/Lazy;", "koin-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class KoinComponentKt {
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object get$default(KoinComponent koinComponent, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Scope rootScope = koinComponent.getKoin().getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.get((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), qualifier, (Function0<DefinitionParameters>) function0);
    }

    private static final <T> T get(KoinComponent koinComponent, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Scope rootScope = koinComponent.getKoin().getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    static /* synthetic */ Lazy inject$default(KoinComponent koinComponent, final Qualifier qualifier, final Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        final Scope rootScope = koinComponent.getKoin().getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.core.KoinComponentKt$inject$$inlined$inject$4
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

    private static final <T> Lazy<T> inject(KoinComponent koinComponent, final Qualifier qualifier, final Function0<DefinitionParameters> function0) {
        final Scope rootScope = koinComponent.getKoin().getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.core.KoinComponentKt$inject$$inlined$inject$2
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

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object bind$default(KoinComponent koinComponent, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Scope rootScope = koinComponent.getKoin().getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    private static final <S, P> S bind(KoinComponent koinComponent, Function0<DefinitionParameters> function0) {
        Scope rootScope = koinComponent.getKoin().getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass<?> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return (S) rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }
}
