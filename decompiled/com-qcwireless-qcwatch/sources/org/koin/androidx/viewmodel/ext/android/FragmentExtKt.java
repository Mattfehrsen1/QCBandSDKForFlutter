package org.koin.androidx.viewmodel.ext.android;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.android.ext.android.ComponentCallbackExtKt;
import org.koin.androidx.viewmodel.ViewModelParameters;
import org.koin.androidx.viewmodel.ViewModelResolutionKt;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;

/* compiled from: FragmentExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a_\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0012\b\u0002\u0010\b\u001a\f\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\tj\u0004\u0018\u0001`\u000e¢\u0006\u0002\u0010\u000f\u001aV\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0012\b\n\u0010\b\u001a\f\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b2\u0016\b\n\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\tj\u0004\u0018\u0001`\u000eH\u0086\b¢\u0006\u0002\u0010\u0010\u001a`\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0012\b\u0002\u0010\b\u001a\f\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\tj\u0004\u0018\u0001`\u000e\u001aW\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0012\b\n\u0010\b\u001a\f\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b2\u0016\b\n\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\tj\u0004\u0018\u0001`\u000eH\u0086\b¨\u0006\u0013"}, d2 = {"getSharedViewModel", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "clazz", "Lkotlin/reflect/KClass;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", TypedValues.TransitionType.S_FROM, "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelStoreOwner;", "Lorg/koin/androidx/viewmodel/ViewModelStoreOwnerDefinition;", "parameters", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "(Landroidx/fragment/app/Fragment;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "sharedViewModel", "Lkotlin/Lazy;", "koin-androidx-viewmodel_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class FragmentExtKt {
    static /* synthetic */ Lazy sharedViewModel$default(final Fragment fragment, Qualifier qualifier, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<ViewModelStoreOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentExtKt.sharedViewModel.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModelStoreOwner invoke() {
                    FragmentActivity activity = fragment.getActivity();
                    if (activity != null) {
                        return activity;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type androidx.lifecycle.ViewModelStoreOwner");
                }
            };
        }
        if ((i & 4) != 0) {
            function02 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(new C08322(fragment, qualifier, function0, function02));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: FragmentExt.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "invoke", "()Landroidx/lifecycle/ViewModel;"}, k = 3, mv = {1, 1, 15})
    /* renamed from: org.koin.androidx.viewmodel.ext.android.FragmentExtKt$sharedViewModel$2, reason: invalid class name and case insensitive filesystem */
    public static final class C08322<T> extends Lambda implements Function0<T> {
        final /* synthetic */ Function0 $from;
        final /* synthetic */ Function0 $parameters;
        final /* synthetic */ Qualifier $qualifier;
        final /* synthetic */ Fragment $this_sharedViewModel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08322(Fragment fragment, Qualifier qualifier, Function0 function0, Function0 function02) {
            super(0);
            this.$this_sharedViewModel = fragment;
            this.$qualifier = qualifier;
            this.$from = function0;
            this.$parameters = function02;
        }

        /* JADX WARN: Incorrect return type in method signature: ()TT; */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModel invoke() {
            Fragment fragment = this.$this_sharedViewModel;
            Qualifier qualifier = this.$qualifier;
            Function0 function0 = this.$from;
            Function0 function02 = this.$parameters;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return FragmentExtKt.getSharedViewModel(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function0, function02);
        }
    }

    private static final <T extends ViewModel> Lazy<T> sharedViewModel(Fragment fragment, Qualifier qualifier, Function0<? extends ViewModelStoreOwner> function0, Function0<DefinitionParameters> function02) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new C08322(fragment, qualifier, function0, function02));
    }

    public static /* synthetic */ Lazy sharedViewModel$default(final Fragment fragment, KClass kClass, Qualifier qualifier, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = new Function0<ViewModelStoreOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentExtKt.sharedViewModel.3
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModelStoreOwner invoke() {
                    FragmentActivity activity = fragment.getActivity();
                    if (activity != null) {
                        return activity;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type androidx.lifecycle.ViewModelStoreOwner");
                }
            };
        }
        if ((i & 8) != 0) {
            function02 = null;
        }
        return sharedViewModel(fragment, kClass, qualifier, function0, function02);
    }

    public static final <T extends ViewModel> Lazy<T> sharedViewModel(final Fragment sharedViewModel, final KClass<T> clazz, final Qualifier qualifier, final Function0<? extends ViewModelStoreOwner> from, final Function0<DefinitionParameters> function0) {
        Intrinsics.checkParameterIsNotNull(sharedViewModel, "$this$sharedViewModel");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(from, "from");
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentExtKt.sharedViewModel.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModel invoke() {
                return FragmentExtKt.getSharedViewModel(sharedViewModel, clazz, qualifier, from, function0);
            }
        });
    }

    static /* synthetic */ ViewModel getSharedViewModel$default(final Fragment fragment, Qualifier qualifier, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<ViewModelStoreOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentExtKt.getSharedViewModel.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModelStoreOwner invoke() {
                    FragmentActivity activity = fragment.getActivity();
                    if (activity != null) {
                        return activity;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type androidx.lifecycle.ViewModelStoreOwner");
                }
            };
        }
        if ((i & 4) != 0) {
            function02 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getSharedViewModel(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function0, function02);
    }

    private static final <T extends ViewModel> T getSharedViewModel(Fragment fragment, Qualifier qualifier, Function0<? extends ViewModelStoreOwner> function0, Function0<DefinitionParameters> function02) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) getSharedViewModel(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function0, function02);
    }

    public static /* synthetic */ ViewModel getSharedViewModel$default(final Fragment fragment, KClass kClass, Qualifier qualifier, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = new Function0<ViewModelStoreOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentExtKt.getSharedViewModel.2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModelStoreOwner invoke() {
                    FragmentActivity activity = fragment.getActivity();
                    if (activity != null) {
                        return activity;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type androidx.lifecycle.ViewModelStoreOwner");
                }
            };
        }
        if ((i & 8) != 0) {
            function02 = null;
        }
        return getSharedViewModel(fragment, kClass, qualifier, function0, function02);
    }

    public static final <T extends ViewModel> T getSharedViewModel(Fragment getSharedViewModel, KClass<T> clazz, Qualifier qualifier, Function0<? extends ViewModelStoreOwner> from, Function0<DefinitionParameters> function0) {
        Intrinsics.checkParameterIsNotNull(getSharedViewModel, "$this$getSharedViewModel");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(from, "from");
        return (T) ViewModelResolutionKt.getViewModel(ComponentCallbackExtKt.getKoin(getSharedViewModel), new ViewModelParameters(clazz, getSharedViewModel, qualifier, from, function0));
    }
}
