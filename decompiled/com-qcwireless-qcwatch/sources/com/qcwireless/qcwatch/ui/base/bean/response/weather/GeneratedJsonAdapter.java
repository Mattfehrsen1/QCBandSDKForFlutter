package com.qcwireless.qcwatch.ui.base.bean.response.weather;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeatherRespJsonAdapter.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/weather/WeatherRespJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/weather/WeatherResp;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "intAdapter", "", "longAdapter", "", "nullableStringAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.base.bean.response.weather.WeatherRespJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<WeatherResp> {
    private final JsonAdapter<Boolean> booleanAdapter;
    private volatile Constructor<WeatherResp> constructorRef;
    private final JsonAdapter<Integer> intAdapter;
    private final JsonAdapter<Long> longAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("timeStamp", "digitType", "type", "lowTemp", "highTemp", "rainProd", "humidity", "isBringUmbrella");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"timeStamp\", \"digitTy…dity\", \"isBringUmbrella\")");
        this.options = optionsOf;
        JsonAdapter<Long> jsonAdapterAdapter = moshi.adapter(Long.TYPE, SetsKt.emptySet(), "timeStamp");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Long::clas…Set(),\n      \"timeStamp\")");
        this.longAdapter = jsonAdapterAdapter;
        JsonAdapter<Integer> jsonAdapterAdapter2 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "digitType");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(Int::class… emptySet(), \"digitType\")");
        this.intAdapter = jsonAdapterAdapter2;
        JsonAdapter<String> jsonAdapterAdapter3 = moshi.adapter(String.class, SetsKt.emptySet(), "type");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "moshi.adapter(String::cl…      emptySet(), \"type\")");
        this.nullableStringAdapter = jsonAdapterAdapter3;
        JsonAdapter<Boolean> jsonAdapterAdapter4 = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "isBringUmbrella");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter4, "moshi.adapter(Boolean::c…\n      \"isBringUmbrella\")");
        this.booleanAdapter = jsonAdapterAdapter4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(33);
        sb.append("GeneratedJsonAdapter(");
        sb.append("WeatherResp");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public WeatherResp fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Long lFromJson = 0L;
        Integer numFromJson = 0;
        reader.beginObject();
        Integer numFromJson2 = numFromJson;
        Integer numFromJson3 = numFromJson2;
        Integer numFromJson4 = numFromJson3;
        Boolean boolFromJson = false;
        int i = -1;
        String strFromJson = null;
        Integer numFromJson5 = numFromJson4;
        while (reader.hasNext()) {
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    break;
                case 0:
                    lFromJson = this.longAdapter.fromJson(reader);
                    if (lFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("timeStamp", "timeStamp", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"timeStam…     \"timeStamp\", reader)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    i &= -2;
                    break;
                case 1:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("digitType", "digitType", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"digitTyp…     \"digitType\", reader)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    i &= -3;
                    break;
                case 2:
                    strFromJson = this.nullableStringAdapter.fromJson(reader);
                    i &= -5;
                    break;
                case 3:
                    numFromJson5 = this.intAdapter.fromJson(reader);
                    if (numFromJson5 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("lowTemp", "lowTemp", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"lowTemp\"…p\",\n              reader)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    i &= -9;
                    break;
                case 4:
                    numFromJson2 = this.intAdapter.fromJson(reader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("highTemp", "highTemp", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"highTemp…      \"highTemp\", reader)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    i &= -17;
                    break;
                case 5:
                    numFromJson3 = this.intAdapter.fromJson(reader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("rainProd", "rainProd", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"rainProd…      \"rainProd\", reader)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    i &= -33;
                    break;
                case 6:
                    numFromJson4 = this.intAdapter.fromJson(reader);
                    if (numFromJson4 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull6 = Util.unexpectedNull("humidity", "humidity", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull6, "unexpectedNull(\"humidity…      \"humidity\", reader)");
                        throw jsonDataExceptionUnexpectedNull6;
                    }
                    i &= -65;
                    break;
                case 7:
                    boolFromJson = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull7 = Util.unexpectedNull("isBringUmbrella", "isBringUmbrella", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull7, "unexpectedNull(\"isBringU…isBringUmbrella\", reader)");
                        throw jsonDataExceptionUnexpectedNull7;
                    }
                    i &= -129;
                    break;
            }
        }
        reader.endObject();
        if (i == -256) {
            return new WeatherResp(lFromJson.longValue(), numFromJson.intValue(), strFromJson, numFromJson5.intValue(), numFromJson2.intValue(), numFromJson3.intValue(), numFromJson4.intValue(), boolFromJson.booleanValue());
        }
        Constructor<WeatherResp> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = WeatherResp.class.getDeclaredConstructor(Long.TYPE, Integer.TYPE, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "WeatherResp::class.java.…his.constructorRef = it }");
        }
        WeatherResp weatherRespNewInstance = declaredConstructor.newInstance(lFromJson, numFromJson, strFromJson, numFromJson5, numFromJson2, numFromJson3, numFromJson4, boolFromJson, Integer.valueOf(i), null);
        Intrinsics.checkNotNullExpressionValue(weatherRespNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return weatherRespNewInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, WeatherResp value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("timeStamp");
        this.longAdapter.toJson(writer, (JsonWriter) Long.valueOf(value_.getTimeStamp()));
        writer.name("digitType");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getDigitType()));
        writer.name("type");
        this.nullableStringAdapter.toJson(writer, (JsonWriter) value_.getType());
        writer.name("lowTemp");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getLowTemp()));
        writer.name("highTemp");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getHighTemp()));
        writer.name("rainProd");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getRainProd()));
        writer.name("humidity");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getHumidity()));
        writer.name("isBringUmbrella");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.isBringUmbrella()));
        writer.endObject();
    }
}
