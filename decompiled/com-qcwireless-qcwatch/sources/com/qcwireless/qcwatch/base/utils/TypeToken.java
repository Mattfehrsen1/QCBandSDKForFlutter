package com.qcwireless.qcwatch.base.utils;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoshiUtils.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/TypeToken;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "type", "Ljava/lang/reflect/Type;", "getType", "()Ljava/lang/reflect/Type;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class TypeToken<T> {
    public final Type getType() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Objects.requireNonNull(genericSuperclass, "null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
        Type typeCanonicalize = Util.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        Intrinsics.checkNotNullExpressionValue(typeCanonicalize, "run {\n            val su…peArguments[0])\n        }");
        return typeCanonicalize;
    }
}
