package org.koin.core.qualifier;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringQualifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lorg/koin/core/qualifier/StringQualifier;", "Lorg/koin/core/qualifier/Qualifier;", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final /* data */ class StringQualifier implements Qualifier {
    private final String value;

    public static /* synthetic */ StringQualifier copy$default(StringQualifier stringQualifier, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = stringQualifier.value;
        }
        return stringQualifier.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final StringQualifier copy(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return new StringQualifier(value);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof StringQualifier) && Intrinsics.areEqual(this.value, ((StringQualifier) other).value);
        }
        return true;
    }

    public int hashCode() {
        String str = this.value;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public StringQualifier(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }
}
