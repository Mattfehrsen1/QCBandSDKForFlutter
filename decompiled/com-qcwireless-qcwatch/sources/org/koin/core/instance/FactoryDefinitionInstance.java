package org.koin.core.instance;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;

/* compiled from: FactoryDefinitionInstance.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u001b\u0010\b\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000f"}, d2 = {"Lorg/koin/core/instance/FactoryDefinitionInstance;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/koin/core/instance/DefinitionInstance;", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "close", "", "get", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "isCreated", "", "release", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class FactoryDefinitionInstance<T> extends DefinitionInstance<T> {
    @Override // org.koin.core.instance.DefinitionInstance
    public boolean isCreated(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return false;
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public void release(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FactoryDefinitionInstance(BeanDefinition<T> beanDefinition) {
        super(beanDefinition);
        Intrinsics.checkParameterIsNotNull(beanDefinition, "beanDefinition");
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public void close() {
        Function1<T, Unit> onClose = getBeanDefinition().getOnClose();
        if (onClose != null) {
            onClose.invoke(null);
        }
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public <T> T get(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return create(context);
    }
}
