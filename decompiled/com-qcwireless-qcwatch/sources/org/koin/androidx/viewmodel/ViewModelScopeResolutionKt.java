package org.koin.androidx.viewmodel;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.scope.Scope;

/* compiled from: ViewModelScopeResolution.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"getViewModel", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "Lorg/koin/core/scope/Scope;", "parameters", "Lorg/koin/androidx/viewmodel/ViewModelParameters;", "(Lorg/koin/core/scope/Scope;Lorg/koin/androidx/viewmodel/ViewModelParameters;)Landroidx/lifecycle/ViewModel;", "koin-androidx-viewmodel_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ViewModelScopeResolutionKt {
    public static final <T extends ViewModel> T getViewModel(Scope getViewModel, ViewModelParameters<T> parameters) {
        Intrinsics.checkParameterIsNotNull(getViewModel, "$this$getViewModel");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        return (T) ViewModelResolutionKt.getInstance(ViewModelResolutionKt.createViewModelProvider(getViewModel, ViewModelResolutionKt.getViewModelStore(parameters.getOwner(), parameters), parameters), parameters);
    }
}
