package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
final class RecordJsonAdapter<T> extends JsonAdapter<T> {
    static final JsonAdapter.Factory FACTORY = new JsonAdapter.Factory() { // from class: com.squareup.moshi.RecordJsonAdapter.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            return null;
        }
    };

    RecordJsonAdapter() {
    }

    @Override // com.squareup.moshi.JsonAdapter
    @Nullable
    public T fromJson(JsonReader jsonReader) throws IOException {
        throw new AssertionError();
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException {
        throw new AssertionError();
    }
}
