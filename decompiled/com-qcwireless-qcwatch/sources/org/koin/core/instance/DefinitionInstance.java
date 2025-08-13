package org.koin.core.instance;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.error.InstanceCreationException;
import org.koin.core.logger.Level;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.scope.Scope;

/* compiled from: DefinitionInstance.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u0000 \u0012*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0012B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH&J\u001b\u0010\n\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\u001b\u0010\u000e\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0002\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH&R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/koin/core/instance/DefinitionInstance;", ExifInterface.GPS_DIRECTION_TRUE, "", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "getBeanDefinition", "()Lorg/koin/core/definition/BeanDefinition;", "close", "", "create", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "get", "isCreated", "", "release", "Companion", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public abstract class DefinitionInstance<T> {
    public static final String ERROR_SEPARATOR = "\n\t";
    private final BeanDefinition<T> beanDefinition;

    public abstract void close();

    public abstract <T> T get(InstanceContext context);

    public abstract boolean isCreated(InstanceContext context);

    public abstract void release(InstanceContext context);

    public DefinitionInstance(BeanDefinition<T> beanDefinition) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "beanDefinition");
        this.beanDefinition = beanDefinition;
    }

    public final BeanDefinition<T> getBeanDefinition() {
        return this.beanDefinition;
    }

    public <T> T create(InstanceContext context) throws InstanceCreationException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
            KoinApplication.INSTANCE.getLogger().debug("| create instance for " + this.beanDefinition);
        }
        try {
            DefinitionParameters parameters = context.getParameters();
            Function2<Scope, DefinitionParameters, T> definition = this.beanDefinition.getDefinition();
            Scope scope = context.getScope();
            if (scope != null) {
                return definition.invoke(scope, parameters);
            }
            throw new IllegalStateException("Can't execute definition instance while this context is not registered against any Koin instance".toString());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(e.toString());
            sb.append(ERROR_SEPARATOR);
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            ArrayList arrayList = new ArrayList();
            for (StackTraceElement it : stackTrace) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Intrinsics.checkExpressionValueIsNotNull(it.getClassName(), "it.className");
                if (!(!StringsKt.contains$default((CharSequence) r8, (CharSequence) "sun.reflect", false, 2, (Object) null))) {
                    break;
                }
                arrayList.add(it);
            }
            sb.append(CollectionsKt.joinToString$default(arrayList, ERROR_SEPARATOR, null, null, 0, null, null, 62, null));
            KoinApplication.INSTANCE.getLogger().error("Instance creation error : could not create instance for " + this.beanDefinition + ": " + sb.toString());
            throw new InstanceCreationException("Could not create instance for " + this.beanDefinition, e);
        }
    }
}
