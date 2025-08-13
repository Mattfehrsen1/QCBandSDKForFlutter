package com.qcwireless.qcwatch.base.utils;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;

/* compiled from: MoshiUtils.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010%\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\b\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ#\u0010\b\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u0011J#\u0010\b\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u0014J \u0010\u0015\u001a\u0004\u0018\u0001H\t\"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0086\b¢\u0006\u0002\u0010\u0016J \u0010\u0015\u001a\u0004\u0018\u0001H\t\"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0086\b¢\u0006\u0002\u0010\u0017J$\u0010\u0015\u001a\u0004\u0018\u0001H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\b¢\u0006\u0002\u0010\u001aJ#\u0010\u0015\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u001bJ \u0010\u0015\u001a\u0004\u0018\u0001H\t\"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\b¢\u0006\u0002\u0010\u001cJ\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\t0\u001e\"\u0006\b\u0000\u0010\t\u0018\u0001H\u0086\bJ\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\t0\u001e\"\u0004\b\u0000\u0010\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\t0 \"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0086\bJ\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\t0 \"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0086\bJ\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\t0 \"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\bJ\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\t0 \"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\bJ-\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0\"\"\u0006\b\u0000\u0010#\u0018\u0001\"\u0006\b\u0001\u0010$\u0018\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0086\bJ-\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0\"\"\u0006\b\u0000\u0010#\u0018\u0001\"\u0006\b\u0001\u0010$\u0018\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0086\bJ-\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0\"\"\u0006\b\u0000\u0010#\u0018\u0001\"\u0006\b\u0001\u0010$\u0018\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\bJ-\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0\"\"\u0006\b\u0000\u0010#\u0018\u0001\"\u0006\b\u0001\u0010$\u0018\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\bJ\u001e\u0010%\u001a\u00020\u0019\"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010&\u001a\u0002H\tH\u0086\b¢\u0006\u0002\u0010'R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006("}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/MoshiUtils;", "", "()V", "moshiBuild", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "getMoshiBuild", "()Lcom/squareup/moshi/Moshi;", "fromJons", ExifInterface.GPS_DIRECTION_TRUE, "reader", "Lcom/squareup/moshi/JsonReader;", "type", "Ljava/lang/reflect/Type;", "(Lcom/squareup/moshi/JsonReader;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "is", "Ljava/io/InputStream;", "(Ljava/io/InputStream;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "buffer", "Lokio/BufferedSource;", "(Lokio/BufferedSource;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "fromJson", "(Lcom/squareup/moshi/JsonReader;)Ljava/lang/Object;", "(Ljava/io/InputStream;)Ljava/lang/Object;", "json", "", "(Ljava/lang/String;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "(Lokio/BufferedSource;)Ljava/lang/Object;", "getAdapter", "Lcom/squareup/moshi/JsonAdapter;", "listFromJson", "", "mapFromJson", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "toJson", "t", "(Ljava/lang/Object;)Ljava/lang/String;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoshiUtils {
    public static final MoshiUtils INSTANCE = new MoshiUtils();
    private static final Moshi moshiBuild = new Moshi.Builder().build();

    private MoshiUtils() {
    }

    public final Moshi getMoshiBuild() {
        return moshiBuild;
    }

    public final <T> T fromJson(String json, Type type) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(type, "type");
        return getAdapter(type).fromJson(json);
    }

    public final <T> T fromJons(BufferedSource buffer, Type type) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(type, "type");
        return getAdapter(type).fromJson(buffer);
    }

    public final <T> T fromJons(InputStream is, Type type) {
        Intrinsics.checkNotNullParameter(is, "is");
        Intrinsics.checkNotNullParameter(type, "type");
        return getAdapter(type).fromJson(new Buffer().readFrom(is));
    }

    public final <T> T fromJons(JsonReader reader, Type type) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(type, "type");
        return getAdapter(type).fromJson(reader);
    }

    public final /* synthetic */ <T> List<T> listFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
        List<T> list = (List) fromJson(json, parameterizedTypeNewParameterizedType);
        return list != null ? list : new ArrayList();
    }

    public final /* synthetic */ <T> List<T> listFromJson(BufferedSource buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
        List<T> list = (List) fromJons(buffer, parameterizedTypeNewParameterizedType);
        return list != null ? list : new ArrayList();
    }

    public final /* synthetic */ <T> List<T> listFromJson(InputStream is) {
        Intrinsics.checkNotNullParameter(is, "is");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
        List<T> list = (List) fromJons(is, parameterizedTypeNewParameterizedType);
        return list != null ? list : new ArrayList();
    }

    public final /* synthetic */ <T> List<T> listFromJson(JsonReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
        List<T> list = (List) fromJons(reader, parameterizedTypeNewParameterizedType);
        return list != null ? list : new ArrayList();
    }

    public final /* synthetic */ <K, V> Map<K, V> mapFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.reifiedOperationMarker(4, "K");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(Map.class, Object.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, V::class.java)");
        Map<K, V> map = (Map) fromJson(json, parameterizedTypeNewParameterizedType);
        return map != null ? map : new LinkedHashMap();
    }

    public final /* synthetic */ <K, V> Map<K, V> mapFromJson(BufferedSource buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.reifiedOperationMarker(4, "K");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(Map.class, Object.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, V::class.java)");
        Map<K, V> map = (Map) fromJons(buffer, parameterizedTypeNewParameterizedType);
        return map != null ? map : new LinkedHashMap();
    }

    public final /* synthetic */ <K, V> Map<K, V> mapFromJson(InputStream is) {
        Intrinsics.checkNotNullParameter(is, "is");
        Intrinsics.reifiedOperationMarker(4, "K");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(Map.class, Object.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, V::class.java)");
        Map<K, V> map = (Map) fromJons(is, parameterizedTypeNewParameterizedType);
        return map != null ? map : new LinkedHashMap();
    }

    public final /* synthetic */ <K, V> Map<K, V> mapFromJson(JsonReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.reifiedOperationMarker(4, "K");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(Map.class, Object.class, Object.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, V::class.java)");
        Map<K, V> map = (Map) fromJons(reader, parameterizedTypeNewParameterizedType);
        return map != null ? map : new LinkedHashMap();
    }

    private final <T> JsonAdapter<T> getAdapter(Type type) {
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild.adapter(type);
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(type)");
        return jsonAdapterAdapter;
    }

    public final /* synthetic */ <T> JsonAdapter<T> getAdapter() {
        Moshi moshiBuild2 = getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild2.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtils.getAdapter.1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return jsonAdapterAdapter;
    }

    public final /* synthetic */ <T> T fromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        Moshi moshiBuild2 = getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild2.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtils$fromJson$$inlined$getAdapter$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return jsonAdapterAdapter.fromJson(json);
    }

    public final /* synthetic */ <T> T fromJson(BufferedSource buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Moshi moshiBuild2 = getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild2.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtils$fromJson$$inlined$getAdapter$2
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return jsonAdapterAdapter.fromJson(buffer);
    }

    public final /* synthetic */ <T> T fromJson(InputStream is) {
        Intrinsics.checkNotNullParameter(is, "is");
        Moshi moshiBuild2 = getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild2.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtils$fromJson$$inlined$getAdapter$3
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return jsonAdapterAdapter.fromJson(new Buffer().readFrom(is));
    }

    public final /* synthetic */ <T> T fromJson(JsonReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Moshi moshiBuild2 = getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild2.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtils$fromJson$$inlined$getAdapter$4
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        return jsonAdapterAdapter.fromJson(reader);
    }

    public final /* synthetic */ <T> String toJson(T t) {
        Moshi moshiBuild2 = getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild2.adapter(new TypeToken<T>() { // from class: com.qcwireless.qcwatch.base.utils.MoshiUtils$toJson$$inlined$getAdapter$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        String json = jsonAdapterAdapter.toJson(t);
        return json == null ? "" : json;
    }
}
