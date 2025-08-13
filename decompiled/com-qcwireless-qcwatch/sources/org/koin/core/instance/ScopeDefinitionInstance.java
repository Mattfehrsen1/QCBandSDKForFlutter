package org.koin.core.instance;

import androidx.exifinterface.media.ExifInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.error.BadScopeInstanceException;
import org.koin.core.error.ScopeNotCreatedException;
import org.koin.core.logger.Level;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.core.scope.ScopeDefinition;

/* compiled from: ScopeDefinitionInstance.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0016J\u001b\u0010\u000f\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/koin/core/instance/ScopeDefinitionInstance;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/koin/core/instance/DefinitionInstance;", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "values", "", "", "checkScopeResolution", "", "definition", "scope", "Lorg/koin/core/scope/Scope;", "close", "get", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "isCreated", "", "release", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class ScopeDefinitionInstance<T> extends DefinitionInstance<T> {
    private final Map<String, T> values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScopeDefinitionInstance(BeanDefinition<T> beanDefinition) {
        super(beanDefinition);
        Intrinsics.checkParameterIsNotNull(beanDefinition, "beanDefinition");
        this.values = new ConcurrentHashMap();
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public boolean isCreated(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return (context.getScope() == null || this.values.get(context.getScope().getId()) == null) ? false : true;
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public void release(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Scope scope = context.getScope();
        if (scope == null) {
            throw new IllegalStateException("ScopeDefinitionInstance has no scope in context".toString());
        }
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
            KoinApplication.INSTANCE.getLogger().debug("releasing '" + scope + "' ~ " + getBeanDefinition() + ' ');
        }
        Function1<T, Unit> onRelease = getBeanDefinition().getOnRelease();
        if (onRelease != null) {
        }
        this.values.remove(scope.getId());
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public <T> T get(InstanceContext context) throws BadScopeInstanceException, ScopeNotCreatedException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (context.getKoin() == null) {
            throw new IllegalStateException("ScopeDefinitionInstance has no registered Koin instance".toString());
        }
        if (Intrinsics.areEqual(context.getScope(), context.getKoin().getRootScope())) {
            throw new ScopeNotCreatedException("No scope instance created to resolve " + getBeanDefinition());
        }
        Scope scope = context.getScope();
        if (scope == null) {
            throw new IllegalStateException("ScopeDefinitionInstance has no scope in context".toString());
        }
        checkScopeResolution(getBeanDefinition(), scope);
        String id = scope.getId();
        T tCreate = this.values.get(id);
        if (tCreate == null) {
            tCreate = create(context);
            Map<String, T> map = this.values;
            if (tCreate == null) {
                throw new IllegalStateException(("Instance creation from " + getBeanDefinition() + " should not be null").toString());
            }
            map.put(id, tCreate);
        }
        return tCreate;
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public void close() {
        Function1<T, Unit> onClose = getBeanDefinition().getOnClose();
        if (onClose != null) {
            onClose.invoke(null);
        }
        this.values.clear();
    }

    private final void checkScopeResolution(BeanDefinition<?> definition, Scope scope) throws BadScopeInstanceException {
        ScopeDefinition scopeDefinition = scope.getScopeDefinition();
        Qualifier qualifier = scopeDefinition != null ? scopeDefinition.getQualifier() : null;
        Qualifier scopeName = definition.getScopeName();
        if (!Intrinsics.areEqual(scopeName, qualifier)) {
            if (qualifier == null) {
                throw new BadScopeInstanceException("Can't use definition " + definition + " defined for scope '" + scopeName + "', with an open scope instance " + scope + ". Use a scope instance with scope '" + scopeName + '\'');
            }
            if (scopeName == null) {
                return;
            }
            throw new BadScopeInstanceException("Can't use definition " + definition + " defined for scope '" + scopeName + "' with scope instance " + scope + ". Use a scope instance with scope '" + scopeName + "'.");
        }
    }
}
