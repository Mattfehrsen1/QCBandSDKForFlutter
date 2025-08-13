package com.qcwireless.qcwatch.ui.base.bean.response.device;

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

/* compiled from: DeviceMissFileRespJsonAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DeviceMissFileRespJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DeviceMissFileResp;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "constructorRef", "Ljava/lang/reflect/Constructor;", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.base.bean.response.device.DeviceMissFileRespJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<DeviceMissFileResp> {
    private volatile Constructor<DeviceMissFileResp> constructorRef;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("fileName", "desc", "hardwareVersion", "firmwareVersion", "downloadUrl");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"fileName\", \"desc\",\n …eVersion\", \"downloadUrl\")");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "fileName");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(String::cl…ySet(),\n      \"fileName\")");
        this.stringAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("GeneratedJsonAdapter(");
        sb.append("DeviceMissFileResp");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public DeviceMissFileResp fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        int i = -1;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                strFromJson = this.stringAdapter.fromJson(reader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("fileName", "fileName", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"fileName…      \"fileName\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
                i &= -2;
            } else if (iSelectName == 1) {
                strFromJson2 = this.stringAdapter.fromJson(reader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("desc", "desc", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"desc\", \"desc\", reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
                i &= -3;
            } else if (iSelectName == 2) {
                strFromJson3 = this.stringAdapter.fromJson(reader);
                if (strFromJson3 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("hardwareVersion", "hardwareVersion", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"hardware…hardwareVersion\", reader)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
                i &= -5;
            } else if (iSelectName == 3) {
                strFromJson4 = this.stringAdapter.fromJson(reader);
                if (strFromJson4 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("firmwareVersion", "firmwareVersion", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"firmware…firmwareVersion\", reader)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
                i &= -9;
            } else if (iSelectName == 4) {
                strFromJson5 = this.stringAdapter.fromJson(reader);
                if (strFromJson5 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("downloadUrl", "downloadUrl", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"download…   \"downloadUrl\", reader)");
                    throw jsonDataExceptionUnexpectedNull5;
                }
                i &= -17;
            } else {
                continue;
            }
        }
        reader.endObject();
        if (i == -32) {
            Objects.requireNonNull(strFromJson, "null cannot be cast to non-null type kotlin.String");
            Objects.requireNonNull(strFromJson2, "null cannot be cast to non-null type kotlin.String");
            Objects.requireNonNull(strFromJson3, "null cannot be cast to non-null type kotlin.String");
            Objects.requireNonNull(strFromJson4, "null cannot be cast to non-null type kotlin.String");
            Objects.requireNonNull(strFromJson5, "null cannot be cast to non-null type kotlin.String");
            return new DeviceMissFileResp(strFromJson, strFromJson2, strFromJson3, strFromJson4, strFromJson5);
        }
        Constructor<DeviceMissFileResp> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = DeviceMissFileResp.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "DeviceMissFileResp::clas…his.constructorRef = it }");
        }
        DeviceMissFileResp deviceMissFileRespNewInstance = declaredConstructor.newInstance(strFromJson, strFromJson2, strFromJson3, strFromJson4, strFromJson5, Integer.valueOf(i), null);
        Intrinsics.checkNotNullExpressionValue(deviceMissFileRespNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return deviceMissFileRespNewInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, DeviceMissFileResp value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("fileName");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getFileName());
        writer.name("desc");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getDesc());
        writer.name("hardwareVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getHardwareVersion());
        writer.name("firmwareVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getFirmwareVersion());
        writer.name("downloadUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getDownloadUrl());
        writer.endObject();
    }
}
