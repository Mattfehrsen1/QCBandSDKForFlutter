package org.koin.core.instance;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;

/* compiled from: SingleDefinitionInstance.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u001b\u0010\n\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/koin/core/instance/SingleDefinitionInstance;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/koin/core/instance/DefinitionInstance;", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "value", "Ljava/lang/Object;", "close", "", "get", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "isCreated", "", "release", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class SingleDefinitionInstance<T> extends DefinitionInstance<T> {
    private T value;

    @Override // org.koin.core.instance.DefinitionInstance
    public void release(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleDefinitionInstance(BeanDefinition<T> beanDefinition) {
        super(beanDefinition);
        Intrinsics.checkParameterIsNotNull(beanDefinition, "beanDefinition");
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public boolean isCreated(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return this.value != null;
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public void close() {
        Function1<T, Unit> onClose = getBeanDefinition().getOnClose();
        if (onClose != null) {
            onClose.invoke(this.value);
        }
        this.value = null;
    }

    @Override // org.koin.core.instance.DefinitionInstance
    public <T> T get(InstanceContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.value == null) {
            this.value = create(context);
        }
        T t = this.value;
        if (!(t instanceof Object)) {
            t = null;
        }
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Single instance created couldn't return value".toString());
    }
}
