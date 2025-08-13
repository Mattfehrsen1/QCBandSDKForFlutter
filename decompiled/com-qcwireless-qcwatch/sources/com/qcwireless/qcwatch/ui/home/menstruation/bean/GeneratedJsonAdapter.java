package com.qcwireless.qcwatch.ui.home.menstruation.bean;

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

/* compiled from: MenstruationBeanJsonAdapter.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "intAdapter", "", "longAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.home.menstruation.bean.MenstruationBeanJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<MenstruationBean> {
    private final JsonAdapter<Boolean> booleanAdapter;
    private volatile Constructor<MenstruationBean> constructorRef;
    private final JsonAdapter<Integer> intAdapter;
    private final JsonAdapter<Long> longAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("menstruationSwitch", "during", "cycle", "lastTime", "menstruationAlarm", "alarm1", "alarm2", "alarmMin");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"menstruationSwitch\",…1\", \"alarm2\", \"alarmMin\")");
        this.options = optionsOf;
        JsonAdapter<Boolean> jsonAdapterAdapter = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "menstruationSwitch");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Boolean::c…    \"menstruationSwitch\")");
        this.booleanAdapter = jsonAdapterAdapter;
        JsonAdapter<Integer> jsonAdapterAdapter2 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "during");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(Int::class…va, emptySet(), \"during\")");
        this.intAdapter = jsonAdapterAdapter2;
        JsonAdapter<Long> jsonAdapterAdapter3 = moshi.adapter(Long.TYPE, SetsKt.emptySet(), "lastTime");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "moshi.adapter(Long::clas…ySet(),\n      \"lastTime\")");
        this.longAdapter = jsonAdapterAdapter3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(38);
        sb.append("GeneratedJsonAdapter(");
        sb.append("MenstruationBean");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public MenstruationBean fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Boolean boolFromJson = false;
        reader.beginObject();
        Integer numFromJson = 0;
        Integer numFromJson2 = null;
        Integer numFromJson3 = null;
        Integer numFromJson4 = null;
        Long lFromJson = 0L;
        int i = -1;
        Boolean boolFromJson2 = boolFromJson;
        Integer numFromJson5 = null;
        while (reader.hasNext()) {
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    break;
                case 0:
                    boolFromJson = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("menstruationSwitch", "menstruationSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"menstrua…struationSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    i &= -2;
                    break;
                case 1:
                    numFromJson5 = this.intAdapter.fromJson(reader);
                    if (numFromJson5 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("during", "during", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"during\",…g\",\n              reader)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    i &= -3;
                    break;
                case 2:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("cycle", "cycle", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"cycle\", \"cycle\", reader)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    i &= -5;
                    break;
                case 3:
                    lFromJson = this.longAdapter.fromJson(reader);
                    if (lFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("lastTime", "lastTime", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"lastTime…      \"lastTime\", reader)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    i &= -9;
                    break;
                case 4:
                    boolFromJson2 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("menstruationAlarm", "menstruationAlarm", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"menstrua…nstruationAlarm\", reader)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    i &= -17;
                    break;
                case 5:
                    numFromJson2 = this.intAdapter.fromJson(reader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull6 = Util.unexpectedNull("alarm1", "alarm1", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull6, "unexpectedNull(\"alarm1\",…1\",\n              reader)");
                        throw jsonDataExceptionUnexpectedNull6;
                    }
                    i &= -33;
                    break;
                case 6:
                    numFromJson3 = this.intAdapter.fromJson(reader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull7 = Util.unexpectedNull("alarm2", "alarm2", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull7, "unexpectedNull(\"alarm2\",…2\",\n              reader)");
                        throw jsonDataExceptionUnexpectedNull7;
                    }
                    i &= -65;
                    break;
                case 7:
                    numFromJson4 = this.intAdapter.fromJson(reader);
                    if (numFromJson4 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull8 = Util.unexpectedNull("alarmMin", "alarmMin", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull8, "unexpectedNull(\"alarmMin…      \"alarmMin\", reader)");
                        throw jsonDataExceptionUnexpectedNull8;
                    }
                    i &= -129;
                    break;
            }
        }
        reader.endObject();
        if (i == -256) {
            return new MenstruationBean(boolFromJson.booleanValue(), numFromJson5.intValue(), numFromJson.intValue(), lFromJson.longValue(), boolFromJson2.booleanValue(), numFromJson2.intValue(), numFromJson3.intValue(), numFromJson4.intValue());
        }
        Constructor<MenstruationBean> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = MenstruationBean.class.getDeclaredConstructor(Boolean.TYPE, Integer.TYPE, Integer.TYPE, Long.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "MenstruationBean::class.…his.constructorRef = it }");
        }
        MenstruationBean menstruationBeanNewInstance = declaredConstructor.newInstance(boolFromJson, numFromJson5, numFromJson, lFromJson, boolFromJson2, numFromJson2, numFromJson3, numFromJson4, Integer.valueOf(i), null);
        Intrinsics.checkNotNullExpressionValue(menstruationBeanNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return menstruationBeanNewInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, MenstruationBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("menstruationSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getMenstruationSwitch()));
        writer.name("during");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getDuring()));
        writer.name("cycle");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getCycle()));
        writer.name("lastTime");
        this.longAdapter.toJson(writer, (JsonWriter) Long.valueOf(value_.getLastTime()));
        writer.name("menstruationAlarm");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getMenstruationAlarm()));
        writer.name("alarm1");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getAlarm1()));
        writer.name("alarm2");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getAlarm2()));
        writer.name("alarmMin");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getAlarmMin()));
        writer.endObject();
    }
}
