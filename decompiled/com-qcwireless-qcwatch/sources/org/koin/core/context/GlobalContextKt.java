package org.koin.core.context;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.KoinApplication;
import org.koin.core.module.Module;

/* compiled from: GlobalContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004\u001a#\u0010\u0006\u001a\u00020\u00072\u001b\u0010\b\u001a\u0017\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\tj\u0002`\n¢\u0006\u0002\b\u000b\u001a\u0006\u0010\f\u001a\u00020\u0001\u001a\u0014\u0010\r\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a\u000e\u0010\r\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u000e"}, d2 = {"loadKoinModules", "", "modules", "", "Lorg/koin/core/module/Module;", "module", "startKoin", "Lorg/koin/core/KoinApplication;", "appDeclaration", "Lkotlin/Function1;", "Lorg/koin/dsl/KoinAppDeclaration;", "Lkotlin/ExtensionFunctionType;", "stopKoin", "unloadKoinModules", "koin-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class GlobalContextKt {
    public static final KoinApplication startKoin(Function1<? super KoinApplication, Unit> appDeclaration) {
        Intrinsics.checkParameterIsNotNull(appDeclaration, "appDeclaration");
        KoinApplication koinApplicationCreate = KoinApplication.INSTANCE.create();
        GlobalContext.start(koinApplicationCreate);
        appDeclaration.invoke(koinApplicationCreate);
        koinApplicationCreate.createEagerInstances();
        return koinApplicationCreate;
    }

    public static final void stopKoin() {
        GlobalContext.stop();
    }

    public static final void loadKoinModules(Module module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        GlobalContext.get().modules(CollectionsKt.listOf(module));
    }

    public static final void loadKoinModules(List<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        GlobalContext.get().modules(modules);
    }

    public static final void unloadKoinModules(Module module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        GlobalContext.get().unloadModules(CollectionsKt.listOf(module));
    }

    public static final void unloadKoinModules(List<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        GlobalContext.get().unloadModules(modules);
    }
}
