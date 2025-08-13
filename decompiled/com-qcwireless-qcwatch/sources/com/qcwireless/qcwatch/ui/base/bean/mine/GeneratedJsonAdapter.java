package com.qcwireless.qcwatch.ui.base.bean.mine;

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

/* compiled from: GoogleFitDataBeanJsonAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/mine/GoogleFitDataBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/base/bean/mine/GoogleFitDataBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "intAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.base.bean.mine.GoogleFitDataBeanJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<GoogleFitDataBean> {
    private final JsonAdapter<Integer> intAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("calorie", "step", "time");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"calorie\", \"step\", \"time\")");
        this.options = optionsOf;
        JsonAdapter<Integer> jsonAdapterAdapter = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "calorie");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Int::class…a, emptySet(), \"calorie\")");
        this.intAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(39);
        sb.append("GeneratedJsonAdapter(");
        sb.append("GoogleFitDataBean");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public GoogleFitDataBean fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        Integer numFromJson = null;
        Integer numFromJson2 = null;
        Integer numFromJson3 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                numFromJson = this.intAdapter.fromJson(reader);
                if (numFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("calorie", "calorie", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"calorie\"…       \"calorie\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                numFromJson2 = this.intAdapter.fromJson(reader);
                if (numFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("step", "step", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"step\", \"step\", reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2 && (numFromJson3 = this.intAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("time", "time", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"time\", \"time\", reader)");
                throw jsonDataExceptionUnexpectedNull3;
            }
        }
        reader.endObject();
        GoogleFitDataBean googleFitDataBean = new GoogleFitDataBean();
        googleFitDataBean.setCalorie(numFromJson != null ? numFromJson.intValue() : googleFitDataBean.getCalorie());
        googleFitDataBean.setStep(numFromJson2 != null ? numFromJson2.intValue() : googleFitDataBean.getStep());
        googleFitDataBean.setTime(numFromJson3 != null ? numFromJson3.intValue() : googleFitDataBean.getTime());
        return googleFitDataBean;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, GoogleFitDataBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("calorie");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getCalorie()));
        writer.name("step");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getStep()));
        writer.name("time");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getTime()));
        writer.endObject();
    }
}
