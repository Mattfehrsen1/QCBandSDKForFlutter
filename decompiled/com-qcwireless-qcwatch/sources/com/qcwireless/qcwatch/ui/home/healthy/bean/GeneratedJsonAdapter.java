package com.qcwireless.qcwatch.ui.home.healthy.bean;

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

/* compiled from: WatchFaceVersionBeanJsonAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/bean/WatchFaceVersionBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/WatchFaceVersionBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.home.healthy.bean.WatchFaceVersionBeanJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<WatchFaceVersionBean> {
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("hardwareVersion", "version");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"hardwareVersion\", \"version\")");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "hardwareVersion");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(String::cl…\n      \"hardwareVersion\")");
        this.stringAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(42);
        sb.append("GeneratedJsonAdapter(");
        sb.append("WatchFaceVersionBean");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public WatchFaceVersionBean fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        String strFromJson = null;
        String strFromJson2 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                strFromJson = this.stringAdapter.fromJson(reader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("hardwareVersion", "hardwareVersion", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"hardware…hardwareVersion\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1 && (strFromJson2 = this.stringAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("version", "version", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"version\"…       \"version\", reader)");
                throw jsonDataExceptionUnexpectedNull2;
            }
        }
        reader.endObject();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("hardwareVersion", "hardwareVersion", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"hardwar…hardwareVersion\", reader)");
            throw jsonDataExceptionMissingProperty;
        }
        if (strFromJson2 != null) {
            return new WatchFaceVersionBean(strFromJson, strFromJson2);
        }
        JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("version", "version", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"version\", \"version\", reader)");
        throw jsonDataExceptionMissingProperty2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, WatchFaceVersionBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("hardwareVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getHardwareVersion());
        writer.name("version");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getVersion());
        writer.endObject();
    }
}
