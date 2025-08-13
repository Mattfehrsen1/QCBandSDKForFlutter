package com.qcwireless.qcwatch.ui.plate.bean;

import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
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

/* compiled from: DeviceWatchFaceBeanJsonAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/bean/DeviceWatchFaceBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/plate/bean/DeviceWatchFaceBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "booleanAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "watchFaceAdapter", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchFace;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.plate.bean.DeviceWatchFaceBeanJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<DeviceWatchFaceBean> {
    private final JsonAdapter<Boolean> booleanAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<WatchFace> watchFaceAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("watchFace", "delete");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"watchFace\", \"delete\")");
        this.options = optionsOf;
        JsonAdapter<WatchFace> jsonAdapterAdapter = moshi.adapter(WatchFace.class, SetsKt.emptySet(), "watchFace");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(WatchFace:… emptySet(), \"watchFace\")");
        this.watchFaceAdapter = jsonAdapterAdapter;
        JsonAdapter<Boolean> jsonAdapterAdapter2 = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "delete");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(Boolean::c…ptySet(),\n      \"delete\")");
        this.booleanAdapter = jsonAdapterAdapter2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(41);
        sb.append("GeneratedJsonAdapter(");
        sb.append("DeviceWatchFaceBean");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public DeviceWatchFaceBean fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        WatchFace watchFaceFromJson = null;
        Boolean boolFromJson = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                watchFaceFromJson = this.watchFaceAdapter.fromJson(reader);
                if (watchFaceFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("watchFace", "watchFace", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"watchFac…     \"watchFace\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1 && (boolFromJson = this.booleanAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("delete", "delete", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"delete\",…        \"delete\", reader)");
                throw jsonDataExceptionUnexpectedNull2;
            }
        }
        reader.endObject();
        if (watchFaceFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("watchFace", "watchFace", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"watchFace\", \"watchFace\", reader)");
            throw jsonDataExceptionMissingProperty;
        }
        if (boolFromJson != null) {
            return new DeviceWatchFaceBean(watchFaceFromJson, boolFromJson.booleanValue());
        }
        JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("delete", "delete", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"delete\", \"delete\", reader)");
        throw jsonDataExceptionMissingProperty2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, DeviceWatchFaceBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("watchFace");
        this.watchFaceAdapter.toJson(writer, (JsonWriter) value_.getWatchFace());
        writer.name("delete");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getDelete()));
        writer.endObject();
    }
}
