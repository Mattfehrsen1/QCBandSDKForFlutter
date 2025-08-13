package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;
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

/* compiled from: ManualHeartEntityJsonAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/ManualHeartEntityJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ManualHeartEntity;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.base.repository.entity.ManualHeartEntityJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<ManualHeartEntity> {
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("mac", "dateStr", Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"mac\", \"dateStr\", \"content\")");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "mac");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(String::cl… emptySet(),\n      \"mac\")");
        this.stringAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(39);
        sb.append("GeneratedJsonAdapter(");
        sb.append("ManualHeartEntity");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public ManualHeartEntity fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                strFromJson = this.stringAdapter.fromJson(reader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("mac", "mac", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"mac\", \"mac\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                strFromJson2 = this.stringAdapter.fromJson(reader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("dateStr", "dateStr", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"dateStr\"…       \"dateStr\", reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2 && (strFromJson3 = this.stringAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, Constant.MODIFY_ACTIVITY_INTENT_CONTENT, reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"content\"…       \"content\", reader)");
                throw jsonDataExceptionUnexpectedNull3;
            }
        }
        reader.endObject();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("mac", "mac", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"mac\", \"mac\", reader)");
            throw jsonDataExceptionMissingProperty;
        }
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("dateStr", "dateStr", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"dateStr\", \"dateStr\", reader)");
            throw jsonDataExceptionMissingProperty2;
        }
        if (strFromJson3 != null) {
            return new ManualHeartEntity(strFromJson, strFromJson2, strFromJson3);
        }
        JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, Constant.MODIFY_ACTIVITY_INTENT_CONTENT, reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(\"content\", \"content\", reader)");
        throw jsonDataExceptionMissingProperty3;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, ManualHeartEntity value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("mac");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getMac());
        writer.name("dateStr");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getDateStr());
        writer.name(Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getContent());
        writer.endObject();
    }
}
