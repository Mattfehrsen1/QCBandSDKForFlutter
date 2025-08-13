package org.koin.androidx.viewmodel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ViewModelParameters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002BW\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\nj\u0004\u0018\u0001`\u000f¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001f\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\nj\u0004\u0018\u0001`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/koin/androidx/viewmodel/ViewModelParameters;", ExifInterface.GPS_DIRECTION_TRUE, "", "clazz", "Lkotlin/reflect/KClass;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", TypedValues.TransitionType.S_FROM, "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelStoreOwner;", "Lorg/koin/androidx/viewmodel/ViewModelStoreOwnerDefinition;", "parameters", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Landroidx/lifecycle/LifecycleOwner;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getClazz", "()Lkotlin/reflect/KClass;", "getFrom", "()Lkotlin/jvm/functions/Function0;", "getOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getParameters", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "koin-androidx-viewmodel_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ViewModelParameters<T> {
    private final KClass<T> clazz;
    private final Function0<ViewModelStoreOwner> from;
    private final LifecycleOwner owner;
    private final Function0<DefinitionParameters> parameters;
    private final Qualifier qualifier;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewModelParameters(KClass<T> clazz, LifecycleOwner owner, Qualifier qualifier, Function0<? extends ViewModelStoreOwner> function0, Function0<DefinitionParameters> function02) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        this.clazz = clazz;
        this.owner = owner;
        this.qualifier = qualifier;
        this.from = function0;
        this.parameters = function02;
    }

    public final KClass<T> getClazz() {
        return this.clazz;
    }

    public final LifecycleOwner getOwner() {
        return this.owner;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ViewModelParameters(KClass kClass, LifecycleOwner lifecycleOwner, Qualifier qualifier, Function0 function0, Function0 function02, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Qualifier qualifier2;
        Function0 function03;
        Function0 function04;
        if ((i & 4) != 0) {
            qualifier2 = null;
        } else {
            qualifier2 = qualifier;
        }
        if ((i & 8) != 0) {
            function03 = null;
        } else {
            function03 = function0;
        }
        if ((i & 16) != 0) {
            function04 = null;
        } else {
            function04 = function02;
        }
        this(kClass, lifecycleOwner, qualifier2, function03, function04);
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final Function0<ViewModelStoreOwner> getFrom() {
        return this.from;
    }

    public final Function0<DefinitionParameters> getParameters() {
        return this.parameters;
    }
}
