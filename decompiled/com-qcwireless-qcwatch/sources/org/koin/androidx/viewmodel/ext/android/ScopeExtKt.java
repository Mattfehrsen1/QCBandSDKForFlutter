package org.koin.androidx.viewmodel.ext.android;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.androidx.viewmodel.ViewModelParameters;
import org.koin.androidx.viewmodel.ViewModelScopeResolutionKt;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ScopeExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aS\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\r¢\u0006\u0002\u0010\u000e\u001aJ\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\b\n\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0086\b¢\u0006\u0002\u0010\u000f\u001aT\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0011\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\r\u001aK\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0011\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\b\n\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0086\b¨\u0006\u0012"}, d2 = {"getViewModel", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "Lorg/koin/core/scope/Scope;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "clazz", "Lkotlin/reflect/KClass;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lorg/koin/core/scope/Scope;Landroidx/lifecycle/LifecycleOwner;Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "(Lorg/koin/core/scope/Scope;Landroidx/lifecycle/LifecycleOwner;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "viewModel", "Lkotlin/Lazy;", "koin-androidx-viewmodel_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ScopeExtKt {
    public static /* synthetic */ Lazy viewModel$default(Scope scope, LifecycleOwner lifecycleOwner, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            qualifier = null;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        return viewModel(scope, lifecycleOwner, kClass, qualifier, function0);
    }

    public static final <T extends ViewModel> Lazy<T> viewModel(final Scope viewModel, final LifecycleOwner owner, final KClass<T> clazz, final Qualifier qualifier, final Function0<DefinitionParameters> function0) {
        Intrinsics.checkParameterIsNotNull(viewModel, "$this$viewModel");
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.androidx.viewmodel.ext.android.ScopeExtKt.viewModel.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModel invoke() {
                return ScopeExtKt.getViewModel(viewModel, owner, clazz, qualifier, function0);
            }
        });
    }

    static /* synthetic */ Lazy viewModel$default(Scope scope, LifecycleOwner lifecycleOwner, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(new AnonymousClass2(scope, lifecycleOwner, qualifier, function0));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: ScopeExt.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "invoke", "()Landroidx/lifecycle/ViewModel;"}, k = 3, mv = {1, 1, 15})
    /* renamed from: org.koin.androidx.viewmodel.ext.android.ScopeExtKt$viewModel$2, reason: invalid class name */
    public static final class AnonymousClass2<T> extends Lambda implements Function0<T> {
        final /* synthetic */ LifecycleOwner $owner;
        final /* synthetic */ Function0 $parameters;
        final /* synthetic */ Qualifier $qualifier;
        final /* synthetic */ Scope $this_viewModel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Scope scope, LifecycleOwner lifecycleOwner, Qualifier qualifier, Function0 function0) {
            super(0);
            this.$this_viewModel = scope;
            this.$owner = lifecycleOwner;
            this.$qualifier = qualifier;
            this.$parameters = function0;
        }

        /* JADX WARN: Incorrect return type in method signature: ()TT; */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModel invoke() {
            Scope scope = this.$this_viewModel;
            LifecycleOwner lifecycleOwner = this.$owner;
            Qualifier qualifier = this.$qualifier;
            Function0 function0 = this.$parameters;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return ScopeExtKt.getViewModel(scope, lifecycleOwner, Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function0);
        }
    }

    private static final <T extends ViewModel> Lazy<T> viewModel(Scope scope, LifecycleOwner lifecycleOwner, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new AnonymousClass2(scope, lifecycleOwner, qualifier, function0));
    }

    static /* synthetic */ ViewModel getViewModel$default(Scope scope, LifecycleOwner lifecycleOwner, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getViewModel(scope, lifecycleOwner, Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function0);
    }

    private static final <T extends ViewModel> T getViewModel(Scope scope, LifecycleOwner lifecycleOwner, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) getViewModel(scope, lifecycleOwner, Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function0);
    }

    public static /* synthetic */ ViewModel getViewModel$default(Scope scope, LifecycleOwner lifecycleOwner, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            qualifier = null;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        return getViewModel(scope, lifecycleOwner, kClass, qualifier, function0);
    }

    public static final <T extends ViewModel> T getViewModel(Scope getViewModel, LifecycleOwner owner, KClass<T> clazz, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.checkParameterIsNotNull(getViewModel, "$this$getViewModel");
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return (T) ViewModelScopeResolutionKt.getViewModel(getViewModel, new ViewModelParameters(clazz, owner, qualifier, null, function0, 8, null));
    }
}
