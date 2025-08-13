package org.koin.android.ext.koin;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.logger.AndroidLogger;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.logger.Level;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.BeanRegistry;
import org.koin.core.scope.Scope;

/* compiled from: KoinExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"androidContext", "Lorg/koin/core/KoinApplication;", "Landroid/content/Context;", "androidFileProperties", "koinPropertyFile", "", "androidLogger", "level", "Lorg/koin/core/logger/Level;", "koin-android_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class KoinExtKt {
    public static /* synthetic */ KoinApplication androidLogger$default(KoinApplication koinApplication, Level level, int i, Object obj) {
        if ((i & 1) != 0) {
            level = Level.INFO;
        }
        return androidLogger(koinApplication, level);
    }

    public static final KoinApplication androidLogger(KoinApplication androidLogger, Level level) {
        Intrinsics.checkParameterIsNotNull(androidLogger, "$this$androidLogger");
        Intrinsics.checkParameterIsNotNull(level, "level");
        KoinApplication.INSTANCE.setLogger(new AndroidLogger(level));
        return androidLogger;
    }

    public static final KoinApplication androidContext(KoinApplication androidContext, final Context androidContext2) {
        Intrinsics.checkParameterIsNotNull(androidContext, "$this$androidContext");
        Intrinsics.checkParameterIsNotNull(androidContext2, "androidContext");
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.INFO)) {
            KoinApplication.INSTANCE.getLogger().info("[init] declare Android Context");
        }
        BeanRegistry beanRegistry = androidContext.getKoin().getRootScope().getBeanRegistry();
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Function2<Scope, DefinitionParameters, Context> function2 = new Function2<Scope, DefinitionParameters, Context>() { // from class: org.koin.android.ext.koin.KoinExtKt.androidContext.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Context invoke(Scope receiver, DefinitionParameters it) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                Intrinsics.checkParameterIsNotNull(it, "it");
                return androidContext2;
            }
        };
        Kind kind = Kind.Single;
        BeanDefinition<?> beanDefinition = new BeanDefinition<>(null, null, Reflection.getOrCreateKotlinClass(Context.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        beanRegistry.saveDefinition(beanDefinition);
        if (androidContext2 instanceof Application) {
            BeanRegistry beanRegistry2 = androidContext.getKoin().getRootScope().getBeanRegistry();
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            Function2<Scope, DefinitionParameters, Application> function22 = new Function2<Scope, DefinitionParameters, Application>() { // from class: org.koin.android.ext.koin.KoinExtKt.androidContext.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Application invoke(Scope receiver, DefinitionParameters it) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return (Application) androidContext2;
                }
            };
            Kind kind2 = Kind.Single;
            BeanDefinition<?> beanDefinition2 = new BeanDefinition<>(null, null, Reflection.getOrCreateKotlinClass(Application.class));
            beanDefinition2.setDefinition(function22);
            beanDefinition2.setKind(kind2);
            beanRegistry2.saveDefinition(beanDefinition2);
        }
        return androidContext;
    }

    public static /* synthetic */ KoinApplication androidFileProperties$default(KoinApplication koinApplication, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "koin.properties";
        }
        return androidFileProperties(koinApplication, str);
    }

    public static final KoinApplication androidFileProperties(KoinApplication androidFileProperties, String koinPropertyFile) throws IOException {
        String[] list;
        Intrinsics.checkParameterIsNotNull(androidFileProperties, "$this$androidFileProperties");
        Intrinsics.checkParameterIsNotNull(koinPropertyFile, "koinPropertyFile");
        Properties properties = new Properties();
        Context context = (Context) androidFileProperties.getKoin().getRootScope().get(Reflection.getOrCreateKotlinClass(Context.class), (Qualifier) null, (Function0<DefinitionParameters>) null);
        try {
            AssetManager assets = context.getAssets();
            if ((assets == null || (list = assets.list("")) == null) ? false : ArraysKt.contains(list, koinPropertyFile)) {
                try {
                    InputStream inputStreamOpen = context.getAssets().open(koinPropertyFile);
                    try {
                        properties.load(inputStreamOpen);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(inputStreamOpen, null);
                        androidFileProperties.getKoin().getPropertyRegistry().saveProperties(properties);
                        Unit unit2 = Unit.INSTANCE;
                        if (KoinApplication.INSTANCE.getLogger().isAt(Level.INFO)) {
                            KoinApplication.INSTANCE.getLogger().info("[Android-Properties] loaded " + unit2 + " properties from assets/koin.properties");
                        }
                    } finally {
                    }
                } catch (Exception e) {
                    KoinApplication.INSTANCE.getLogger().error("[Android-Properties] error for binding properties : " + e);
                }
            } else if (KoinApplication.INSTANCE.getLogger().isAt(Level.INFO)) {
                KoinApplication.INSTANCE.getLogger().info("[Android-Properties] no assets/koin.properties file to load");
            }
        } catch (Exception e2) {
            KoinApplication.INSTANCE.getLogger().error("[Android-Properties] error while loading properties from assets/koin.properties : " + e2);
        }
        return androidFileProperties;
    }
}
