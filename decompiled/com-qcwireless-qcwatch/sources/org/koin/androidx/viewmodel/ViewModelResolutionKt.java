package org.koin.androidx.viewmodel;

import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.logger.Level;
import org.koin.core.scope.Scope;
import org.koin.core.time.MeasureKt;

/* compiled from: ViewModelResolution.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a'\u0010\t\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b¢\u0006\u0002\u0010\n\u001a'\u0010\u000b\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b¢\u0006\u0002\u0010\r\u001a\"\u0010\u000e\u001a\u00020\u0006\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b¨\u0006\u0010"}, d2 = {"createViewModelProvider", "Landroidx/lifecycle/ViewModelProvider;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "Lorg/koin/core/scope/Scope;", "vmStore", "Landroidx/lifecycle/ViewModelStore;", "parameters", "Lorg/koin/androidx/viewmodel/ViewModelParameters;", "getInstance", "(Landroidx/lifecycle/ViewModelProvider;Lorg/koin/androidx/viewmodel/ViewModelParameters;)Landroidx/lifecycle/ViewModel;", "getViewModel", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;Lorg/koin/androidx/viewmodel/ViewModelParameters;)Landroidx/lifecycle/ViewModel;", "getViewModelStore", "Landroidx/lifecycle/LifecycleOwner;", "koin-androidx-viewmodel_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ViewModelResolutionKt {
    public static final <T extends ViewModel> T getViewModel(Koin getViewModel, ViewModelParameters<T> parameters) {
        Intrinsics.checkParameterIsNotNull(getViewModel, "$this$getViewModel");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        return (T) getInstance(createViewModelProvider(getViewModel.getRootScope(), getViewModelStore(parameters.getOwner(), parameters), parameters), parameters);
    }

    public static final <T extends ViewModel> T getInstance(final ViewModelProvider getInstance, final ViewModelParameters<T> parameters) {
        T t;
        Intrinsics.checkParameterIsNotNull(getInstance, "$this$getInstance");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        final Class<T> javaClass = JvmClassMappingKt.getJavaClass((KClass) parameters.getClazz());
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
            KoinApplication.INSTANCE.getLogger().debug("!- ViewModelProvider getting instance");
            Pair pairMeasureDuration = MeasureKt.measureDuration(new Function0<T>() { // from class: org.koin.androidx.viewmodel.ViewModelResolutionKt.getInstance.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Incorrect return type in method signature: ()TT; */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModel invoke() {
                    if (parameters.getQualifier() != null) {
                        return getInstance.get(parameters.getQualifier().toString(), javaClass);
                    }
                    return getInstance.get(javaClass);
                }
            });
            T instance = (T) pairMeasureDuration.component1();
            double dDoubleValue = ((Number) pairMeasureDuration.component2()).doubleValue();
            KoinApplication.INSTANCE.getLogger().debug("!- ViewModelProvider got instance in " + dDoubleValue);
            Intrinsics.checkExpressionValueIsNotNull(instance, "instance");
            return instance;
        }
        if (parameters.getQualifier() != null) {
            t = (T) getInstance.get(parameters.getQualifier().toString(), javaClass);
        } else {
            t = (T) getInstance.get(javaClass);
        }
        Intrinsics.checkExpressionValueIsNotNull(t, "if (parameters.qualifier….get(javaClass)\n        }");
        return t;
    }

    public static final <T extends ViewModel> ViewModelStore getViewModelStore(LifecycleOwner getViewModelStore, ViewModelParameters<T> parameters) {
        Intrinsics.checkParameterIsNotNull(getViewModelStore, "$this$getViewModelStore");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        if (parameters.getFrom() != null) {
            ViewModelStore viewModelStore = parameters.getFrom().invoke().getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "parameters.from.invoke().viewModelStore");
            return viewModelStore;
        }
        if (getViewModelStore instanceof FragmentActivity) {
            ViewModelStore viewModelStore2 = ((FragmentActivity) getViewModelStore).getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore2, "this.viewModelStore");
            return viewModelStore2;
        }
        if (getViewModelStore instanceof Fragment) {
            ViewModelStore viewModelStore3 = ((Fragment) getViewModelStore).getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore3, "this.viewModelStore");
            return viewModelStore3;
        }
        throw new IllegalStateException(("Can't getByClass ViewModel '" + parameters.getClazz() + "' on " + getViewModelStore + " - Is not a FragmentActivity nor a Fragment neither a valid ViewModelStoreOwner").toString());
    }

    public static final <T extends ViewModel> ViewModelProvider createViewModelProvider(final Scope createViewModelProvider, ViewModelStore vmStore, final ViewModelParameters<T> parameters) {
        Intrinsics.checkParameterIsNotNull(createViewModelProvider, "$this$createViewModelProvider");
        Intrinsics.checkParameterIsNotNull(vmStore, "vmStore");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        return new ViewModelProvider(vmStore, new ViewModelProvider.Factory() { // from class: org.koin.androidx.viewmodel.ViewModelResolutionKt.createViewModelProvider.1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(Class<T> modelClass) {
                Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
                return (T) createViewModelProvider.get(parameters.getClazz(), parameters.getQualifier(), parameters.getParameters());
            }
        });
    }
}
