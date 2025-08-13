package com.qcwireless.qcwatch.base.utils;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoshiUtils.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a\n\u0010\u0005\u001a\u00020\u0003*\u00020\u0002¨\u0006\u0006"}, d2 = {"fromJson", ExifInterface.GPS_DIRECTION_TRUE, "", "", "(Ljava/lang/String;)Ljava/lang/Object;", "toJson", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoshiUtilsKt {
    public static final /* synthetic */ <T> T fromJson(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Moshi moshiBuild = MoshiUtils.INSTANCE.getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtilsKt$fromJson$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return jsonAdapterAdapter.fromJson(str);
    }

    public static final String toJson(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<Object>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtilsKt$toJson$$inlined$toJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        String json = jsonAdapterAdapter.toJson(obj);
        if (json == null) {
            return "";
        }
        Intrinsics.checkNotNullExpressionValue(json, "getAdapter<T>().toJson(t) ?: \"\"");
        return json;
    }
}
