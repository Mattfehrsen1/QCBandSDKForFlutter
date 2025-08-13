package com.qcwireless.qcwatch.ui.home.gps.bean;

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

/* compiled from: QcLatLonJsonAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLonJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLon;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "constructorRef", "Ljava/lang/reflect/Constructor;", "doubleAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.home.gps.bean.QcLatLonJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<QcLatLon> {
    private volatile Constructor<QcLatLon> constructorRef;
    private final JsonAdapter<Double> doubleAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("latitude", "longitude", "pace", "speed");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"latitude\", \"longitude\", \"pace\",\n      \"speed\")");
        this.options = optionsOf;
        JsonAdapter<Double> jsonAdapterAdapter = moshi.adapter(Double.TYPE, SetsKt.emptySet(), "latitude");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Double::cl…ySet(),\n      \"latitude\")");
        this.doubleAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(30);
        sb.append("GeneratedJsonAdapter(");
        sb.append("QcLatLon");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public QcLatLon fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Double dValueOf = Double.valueOf(0.0d);
        reader.beginObject();
        Double dFromJson = dValueOf;
        int i = -1;
        Double dFromJson2 = null;
        Double dFromJson3 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                dFromJson2 = this.doubleAdapter.fromJson(reader);
                if (dFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("latitude", "latitude", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"latitude…      \"latitude\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                dFromJson3 = this.doubleAdapter.fromJson(reader);
                if (dFromJson3 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("longitude", "longitude", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"longitud…     \"longitude\", reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2) {
                dValueOf = this.doubleAdapter.fromJson(reader);
                if (dValueOf == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("pace", "pace", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"pace\", \"pace\", reader)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
                i &= -5;
            } else if (iSelectName == 3) {
                dFromJson = this.doubleAdapter.fromJson(reader);
                if (dFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("speed", "speed", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"speed\", …d\",\n              reader)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
                i &= -9;
            } else {
                continue;
            }
        }
        reader.endObject();
        if (i == -13) {
            if (dFromJson2 != null) {
                double dDoubleValue = dFromJson2.doubleValue();
                if (dFromJson3 != null) {
                    return new QcLatLon(dDoubleValue, dFromJson3.doubleValue(), dValueOf.doubleValue(), dFromJson.doubleValue());
                }
                JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("longitude", "longitude", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"longitude\", \"longitude\", reader)");
                throw jsonDataExceptionMissingProperty;
            }
            JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("latitude", "latitude", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"latitude\", \"latitude\", reader)");
            throw jsonDataExceptionMissingProperty2;
        }
        Constructor<QcLatLon> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = QcLatLon.class.getDeclaredConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "QcLatLon::class.java.get…his.constructorRef = it }");
        }
        Object[] objArr = new Object[6];
        if (dFromJson2 != null) {
            objArr[0] = Double.valueOf(dFromJson2.doubleValue());
            if (dFromJson3 == null) {
                JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty("longitude", "longitude", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(\"longitude\", \"longitude\", reader)");
                throw jsonDataExceptionMissingProperty3;
            }
            objArr[1] = Double.valueOf(dFromJson3.doubleValue());
            objArr[2] = dValueOf;
            objArr[3] = dFromJson;
            objArr[4] = Integer.valueOf(i);
            objArr[5] = null;
            QcLatLon qcLatLonNewInstance = declaredConstructor.newInstance(objArr);
            Intrinsics.checkNotNullExpressionValue(qcLatLonNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
            return qcLatLonNewInstance;
        }
        JsonDataException jsonDataExceptionMissingProperty4 = Util.missingProperty("latitude", "latitude", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty4, "missingProperty(\"latitude\", \"latitude\", reader)");
        throw jsonDataExceptionMissingProperty4;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, QcLatLon value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("latitude");
        this.doubleAdapter.toJson(writer, (JsonWriter) Double.valueOf(value_.getLatitude()));
        writer.name("longitude");
        this.doubleAdapter.toJson(writer, (JsonWriter) Double.valueOf(value_.getLongitude()));
        writer.name("pace");
        this.doubleAdapter.toJson(writer, (JsonWriter) Double.valueOf(value_.getPace()));
        writer.name("speed");
        this.doubleAdapter.toJson(writer, (JsonWriter) Double.valueOf(value_.getSpeed()));
        writer.endObject();
    }
}
