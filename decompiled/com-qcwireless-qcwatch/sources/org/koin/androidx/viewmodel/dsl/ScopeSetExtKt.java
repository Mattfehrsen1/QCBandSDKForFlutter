package org.koin.androidx.viewmodel.dsl;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.error.DefinitionOverrideException;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.dsl.ScopeSet;

/* compiled from: ScopeSetExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a`\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2)\b\b\u0010\t\u001a#\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00020\nj\b\u0012\u0004\u0012\u0002H\u0002`\r¢\u0006\u0002\b\u000eH\u0086\b¨\u0006\u000f"}, d2 = {"viewModel", "Lorg/koin/core/definition/BeanDefinition;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "Lorg/koin/dsl/ScopeSet;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "override", "", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "koin-androidx-viewmodel_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ScopeSetExtKt {
    static /* synthetic */ BeanDefinition viewModel$default(ScopeSet scopeSet, Qualifier qualifier, boolean z, Function2 function2, int i, Object obj) throws DefinitionOverrideException {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Qualifier qualifier2 = scopeSet.getQualifier();
        Kind kind = Kind.Factory;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition<?> beanDefinition = new BeanDefinition<>(qualifier, qualifier2, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        scopeSet.declareDefinition(beanDefinition, new Options(false, z));
        ModuleExtKt.setIsViewModel(beanDefinition);
        if (!scopeSet.getDefinitions().contains(beanDefinition)) {
            scopeSet.getDefinitions().add(beanDefinition);
            return beanDefinition;
        }
        throw new DefinitionOverrideException("Can't add definition " + beanDefinition + " for scope " + scopeSet.getQualifier() + " as it already exists");
    }

    private static final <T extends ViewModel> BeanDefinition<T> viewModel(ScopeSet scopeSet, Qualifier qualifier, boolean z, Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2) throws DefinitionOverrideException {
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Qualifier qualifier2 = scopeSet.getQualifier();
        Kind kind = Kind.Factory;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition<T> beanDefinition = new BeanDefinition<>(qualifier, qualifier2, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        scopeSet.declareDefinition(beanDefinition, new Options(false, z));
        ModuleExtKt.setIsViewModel(beanDefinition);
        if (!scopeSet.getDefinitions().contains(beanDefinition)) {
            scopeSet.getDefinitions().add(beanDefinition);
            return beanDefinition;
        }
        throw new DefinitionOverrideException("Can't add definition " + beanDefinition + " for scope " + scopeSet.getQualifier() + " as it already exists");
    }
}
