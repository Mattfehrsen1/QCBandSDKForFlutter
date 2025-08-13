package com.qcwireless.qcwatch.ui.base.bean.weather;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyLocationBeanJsonAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "floatAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBeanJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<MyLocationBean> {
    private final JsonAdapter<Float> floatAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("longitude", "latitude", "language", "city", "address");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"longitude\", \"latitud…uage\", \"city\", \"address\")");
        this.options = optionsOf;
        JsonAdapter<Float> jsonAdapterAdapter = moshi.adapter(Float.TYPE, SetsKt.emptySet(), "longitude");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Float::cla…Set(),\n      \"longitude\")");
        this.floatAdapter = jsonAdapterAdapter;
        JsonAdapter<String> jsonAdapterAdapter2 = moshi.adapter(String.class, SetsKt.emptySet(), "language");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(String::cl…ySet(),\n      \"language\")");
        this.stringAdapter = jsonAdapterAdapter2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(36);
        sb.append("GeneratedJsonAdapter(");
        sb.append("MyLocationBean");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public MyLocationBean fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        Float fFromJson = null;
        Float fFromJson2 = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                fFromJson = this.floatAdapter.fromJson(reader);
                if (fFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("longitude", "longitude", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"longitud…     \"longitude\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                fFromJson2 = this.floatAdapter.fromJson(reader);
                if (fFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("latitude", "latitude", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"latitude…      \"latitude\", reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2) {
                strFromJson = this.stringAdapter.fromJson(reader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("language", "language", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"language…      \"language\", reader)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
            } else if (iSelectName == 3) {
                strFromJson2 = this.stringAdapter.fromJson(reader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("city", "city", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"city\", \"city\",\n            reader)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
            } else if (iSelectName == 4 && (strFromJson3 = this.stringAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("address", "address", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"address\"…       \"address\", reader)");
                throw jsonDataExceptionUnexpectedNull5;
            }
        }
        reader.endObject();
        if (fFromJson != null) {
            float fFloatValue = fFromJson.floatValue();
            if (fFromJson2 != null) {
                float fFloatValue2 = fFromJson2.floatValue();
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("language", "language", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"language\", \"language\", reader)");
                    throw jsonDataExceptionMissingProperty;
                }
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("city", "city", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"city\", \"city\", reader)");
                    throw jsonDataExceptionMissingProperty2;
                }
                if (strFromJson3 != null) {
                    return new MyLocationBean(fFloatValue, fFloatValue2, strFromJson, strFromJson2, strFromJson3);
                }
                JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty("address", "address", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(\"address\", \"address\", reader)");
                throw jsonDataExceptionMissingProperty3;
            }
            JsonDataException jsonDataExceptionMissingProperty4 = Util.missingProperty("latitude", "latitude", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty4, "missingProperty(\"latitude\", \"latitude\", reader)");
            throw jsonDataExceptionMissingProperty4;
        }
        JsonDataException jsonDataExceptionMissingProperty5 = Util.missingProperty("longitude", "longitude", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty5, "missingProperty(\"longitude\", \"longitude\", reader)");
        throw jsonDataExceptionMissingProperty5;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, MyLocationBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("longitude");
        this.floatAdapter.toJson(writer, (JsonWriter) Float.valueOf(value_.getLongitude()));
        writer.name("latitude");
        this.floatAdapter.toJson(writer, (JsonWriter) Float.valueOf(value_.getLatitude()));
        writer.name("language");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getLanguage());
        writer.name("city");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getCity());
        writer.name("address");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getAddress());
        writer.endObject();
    }
}
