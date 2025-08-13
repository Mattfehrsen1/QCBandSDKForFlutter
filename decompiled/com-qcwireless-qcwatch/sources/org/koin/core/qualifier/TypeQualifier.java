package org.koin.core.qualifier;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.ext.KClassExtKt;

/* compiled from: TypeQualifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0017\u0010\b\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lorg/koin/core/qualifier/TypeQualifier;", "Lorg/koin/core/qualifier/Qualifier;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "getType", "()Lkotlin/reflect/KClass;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final /* data */ class TypeQualifier implements Qualifier {
    private final KClass<?> type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TypeQualifier copy$default(TypeQualifier typeQualifier, KClass kClass, int i, Object obj) {
        if ((i & 1) != 0) {
            kClass = typeQualifier.type;
        }
        return typeQualifier.copy(kClass);
    }

    public final KClass<?> component1() {
        return this.type;
    }

    public final TypeQualifier copy(KClass<?> type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new TypeQualifier(type);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof TypeQualifier) && Intrinsics.areEqual(this.type, ((TypeQualifier) other).type);
        }
        return true;
    }

    public int hashCode() {
        KClass<?> kClass = this.type;
        if (kClass != null) {
            return kClass.hashCode();
        }
        return 0;
    }

    public TypeQualifier(KClass<?> type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
    }

    public final KClass<?> getType() {
        return this.type;
    }

    public String toString() {
        return KClassExtKt.getFullName(this.type);
    }
}
