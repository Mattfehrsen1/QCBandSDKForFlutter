package org.koin.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.error.NoPropertyFileFoundException;
import org.koin.core.logger.EmptyLogger;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.logger.PrintLogger;
import org.koin.core.module.Module;
import org.koin.core.scope.ScopeDefinition;
import org.koin.core.time.MeasureKt;

/* compiled from: KoinApplication.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0000J\u0006\u0010\n\u001a\u00020\u0000J\u0010\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\rJ\r\u0010\u000e\u001a\u00020\bH\u0000¢\u0006\u0002\b\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0013J\u0012\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007J\u001a\u0010\u001a\u001a\u00020\u00002\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u001cJ\u001f\u0010\u001d\u001a\u00020\u00002\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u001e\"\u00020\u0013¢\u0006\u0002\u0010\u001fJ\u0014\u0010\u001d\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006!"}, d2 = {"Lorg/koin/core/KoinApplication;", "", "()V", "koin", "Lorg/koin/core/Koin;", "getKoin", "()Lorg/koin/core/Koin;", "close", "", "createEagerInstances", "environmentProperties", "fileProperties", "fileName", "", "loadDefaults", "loadDefaults$koin_core", "loadModulesAndScopes", "modules", "", "Lorg/koin/core/module/Module;", "logger", "Lorg/koin/core/logger/Logger;", "", "printLogger", "level", "Lorg/koin/core/logger/Level;", "properties", "values", "", "unloadModules", "", "([Lorg/koin/core/module/Module;)Lorg/koin/core/KoinApplication;", "Companion", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class KoinApplication {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Logger logger = new EmptyLogger();
    private final Koin koin;

    @JvmStatic
    public static final KoinApplication create() {
        return INSTANCE.create();
    }

    public final KoinApplication printLogger() {
        return printLogger$default(this, null, 1, null);
    }

    private KoinApplication() {
        this.koin = new Koin();
    }

    public /* synthetic */ KoinApplication(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final Koin getKoin() {
        return this.koin;
    }

    public final void loadDefaults$koin_core() {
        this.koin.getScopeRegistry().loadDefaultScopes(this.koin);
    }

    public final KoinApplication modules(Module modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        return modules(CollectionsKt.listOf(modules));
    }

    public final KoinApplication modules(final List<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        if (logger.isAt(Level.INFO)) {
            double dMeasureDurationOnly = MeasureKt.measureDurationOnly(new Function0<Unit>() { // from class: org.koin.core.KoinApplication$modules$duration$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.loadModulesAndScopes(modules);
                }
            });
            int size = this.koin.getRootScope().getBeanRegistry().getAllDefinitions().size();
            Collection<ScopeDefinition> scopeSets = this.koin.getScopeRegistry().getScopeSets();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(scopeSets, 10));
            Iterator<T> it = scopeSets.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((ScopeDefinition) it.next()).getDefinitions().size()));
            }
            int iSumOfInt = size + CollectionsKt.sumOfInt(arrayList);
            logger.info("total " + iSumOfInt + " registered definitions");
            logger.info("load modules in " + dMeasureDurationOnly + " ms");
        } else {
            loadModulesAndScopes(modules);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadModulesAndScopes(Iterable<Module> modules) {
        this.koin.getRootScope().getBeanRegistry().loadModules(modules);
        this.koin.getScopeRegistry().loadScopes$koin_core(modules);
    }

    public final KoinApplication properties(Map<String, ? extends Object> values) {
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.koin.getPropertyRegistry().saveProperties(values);
        return this;
    }

    public static /* synthetic */ KoinApplication fileProperties$default(KoinApplication koinApplication, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "/koin.properties";
        }
        return koinApplication.fileProperties(str);
    }

    public final KoinApplication fileProperties(String fileName) throws NoPropertyFileFoundException {
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        this.koin.getPropertyRegistry().loadPropertiesFromFile(fileName);
        return this;
    }

    public final KoinApplication environmentProperties() {
        this.koin.getPropertyRegistry().loadEnvironmentProperties();
        return this;
    }

    public final KoinApplication logger(Logger logger2) {
        Intrinsics.checkParameterIsNotNull(logger2, "logger");
        logger = logger2;
        return this;
    }

    public static /* synthetic */ KoinApplication printLogger$default(KoinApplication koinApplication, Level level, int i, Object obj) {
        if ((i & 1) != 0) {
            level = Level.INFO;
        }
        return koinApplication.printLogger(level);
    }

    public final KoinApplication printLogger(Level level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        return logger(new PrintLogger(level));
    }

    public final KoinApplication createEagerInstances() {
        if (logger.isAt(Level.DEBUG)) {
            double dMeasureDurationOnly = MeasureKt.measureDurationOnly(new Function0<Unit>() { // from class: org.koin.core.KoinApplication$createEagerInstances$duration$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.getKoin().createEagerInstances$koin_core();
                }
            });
            logger.debug("instances started in " + dMeasureDurationOnly + " ms");
        } else {
            this.koin.createEagerInstances$koin_core();
        }
        return this;
    }

    public final void close() {
        synchronized (this) {
            this.koin.close();
            if (logger.isAt(Level.INFO)) {
                logger.info("stopped");
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final KoinApplication unloadModules(Module... modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        return unloadModules(ArraysKt.toList(modules));
    }

    public final KoinApplication unloadModules(List<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        List<Module> list = modules;
        this.koin.getRootScope().getBeanRegistry().unloadModules$koin_core(list);
        this.koin.getScopeRegistry().unloadScopedDefinitions$koin_core(list);
        return this;
    }

    /* compiled from: KoinApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/koin/core/KoinApplication$Companion;", "", "()V", "logger", "Lorg/koin/core/logger/Logger;", "getLogger", "()Lorg/koin/core/logger/Logger;", "setLogger", "(Lorg/koin/core/logger/Logger;)V", "create", "Lorg/koin/core/KoinApplication;", "koin-core"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Logger getLogger() {
            return KoinApplication.logger;
        }

        public final void setLogger(Logger logger) {
            Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
            KoinApplication.logger = logger;
        }

        @JvmStatic
        public final KoinApplication create() {
            KoinApplication koinApplication = new KoinApplication(null);
            koinApplication.loadDefaults$koin_core();
            return koinApplication;
        }
    }
}
