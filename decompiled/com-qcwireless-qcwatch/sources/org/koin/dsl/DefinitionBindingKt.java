package org.koin.dsl;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.definition.BeanDefinition;

/* compiled from: DefinitionBinding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0086\u0004\u001a,\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u00012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0007H\u0086\u0004¢\u0006\u0002\u0010\b\u001a?\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\t\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u0002H\u0002`\fH\u0086\u0004\u001a?\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\r\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u0002H\u0002`\u000eH\u0086\u0004¨\u0006\u000f"}, d2 = {"bind", "Lorg/koin/core/definition/BeanDefinition;", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Lkotlin/reflect/KClass;", "binds", "classes", "", "(Lorg/koin/core/definition/BeanDefinition;[Lkotlin/reflect/KClass;)Lorg/koin/core/definition/BeanDefinition;", "onClose", "Lkotlin/Function1;", "", "Lorg/koin/core/definition/OnCloseCallback;", "onRelease", "Lorg/koin/core/definition/OnReleaseCallback;", "koin-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class DefinitionBindingKt {
    public static final <T> BeanDefinition<T> bind(BeanDefinition<T> bind, KClass<?> clazz) {
        Intrinsics.checkParameterIsNotNull(bind, "$this$bind");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        bind.getSecondaryTypes().add(clazz);
        return bind;
    }

    public static final BeanDefinition<?> binds(BeanDefinition<?> binds, KClass<?>[] classes) {
        Intrinsics.checkParameterIsNotNull(binds, "$this$binds");
        Intrinsics.checkParameterIsNotNull(classes, "classes");
        CollectionsKt.addAll(binds.getSecondaryTypes(), classes);
        return binds;
    }

    public static final <T> BeanDefinition<T> onRelease(BeanDefinition<T> onRelease, Function1<? super T, Unit> onRelease2) {
        Intrinsics.checkParameterIsNotNull(onRelease, "$this$onRelease");
        Intrinsics.checkParameterIsNotNull(onRelease2, "onRelease");
        onRelease.setOnRelease(onRelease2);
        return onRelease;
    }

    public static final <T> BeanDefinition<T> onClose(BeanDefinition<T> onClose, Function1<? super T, Unit> onClose2) {
        Intrinsics.checkParameterIsNotNull(onClose, "$this$onClose");
        Intrinsics.checkParameterIsNotNull(onClose2, "onClose");
        onClose.setOnClose(onClose2);
        return onClose;
    }
}
