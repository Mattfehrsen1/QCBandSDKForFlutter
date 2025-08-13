package org.koin.core.qualifier;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: Qualifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\u0086\b\u001a\u000e\u0010\u0000\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"named", "Lorg/koin/core/qualifier/TypeQualifier;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/koin/core/qualifier/StringQualifier;", "name", "", "koin-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class QualifierKt {
    public static final StringQualifier named(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new StringQualifier(name);
    }

    private static final <T> TypeQualifier named() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
    }
}
