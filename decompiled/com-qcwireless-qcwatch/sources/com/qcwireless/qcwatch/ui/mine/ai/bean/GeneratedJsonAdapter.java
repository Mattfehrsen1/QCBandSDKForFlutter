package com.qcwireless.qcwatch.ui.mine.ai.bean;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiAnalysisContentDataJsonAdapter.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u001a\u001a\u00020\u000eH\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiAnalysisContentDataJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiAnalysisContentData;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "constructorRef", "Ljava/lang/reflect/Constructor;", "intAdapter", "", "listOfAiDataModelAdapter", "", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiDataModel;", "nullableStringAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.mine.ai.bean.AiAnalysisContentDataJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<AiAnalysisContentData> {
    private volatile Constructor<AiAnalysisContentData> constructorRef;
    private final JsonAdapter<Integer> intAdapter;
    private final JsonAdapter<List<AiDataModel>> listOfAiDataModelAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("comprehensiveOptimization", "listData", "createTime", "summarize", "riskWarning", "score");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"comprehensiveOptimiz…, \"riskWarning\", \"score\")");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "comprehensiveOptimization");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(String::cl…mprehensiveOptimization\")");
        this.nullableStringAdapter = jsonAdapterAdapter;
        JsonAdapter<List<AiDataModel>> jsonAdapterAdapter2 = moshi.adapter(Types.newParameterizedType(List.class, AiDataModel.class), SetsKt.emptySet(), "listData");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(Types.newP…  emptySet(), \"listData\")");
        this.listOfAiDataModelAdapter = jsonAdapterAdapter2;
        JsonAdapter<String> jsonAdapterAdapter3 = moshi.adapter(String.class, SetsKt.emptySet(), "createTime");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "moshi.adapter(String::cl…et(),\n      \"createTime\")");
        this.stringAdapter = jsonAdapterAdapter3;
        JsonAdapter<Integer> jsonAdapterAdapter4 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "score");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter4, "moshi.adapter(Int::class…ava, emptySet(), \"score\")");
        this.intAdapter = jsonAdapterAdapter4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(43);
        sb.append("GeneratedJsonAdapter(");
        sb.append("AiAnalysisContentData");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public AiAnalysisContentData fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String str;
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        int i = -1;
        Integer numFromJson = null;
        String strFromJson = null;
        List<AiDataModel> listFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        while (reader.hasNext()) {
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(reader);
                    i &= -2;
                    break;
                case 1:
                    listFromJson = this.listOfAiDataModelAdapter.fromJson(reader);
                    if (listFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("listData", "listData", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"listData\", \"listData\", reader)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    break;
                case 2:
                    strFromJson2 = this.stringAdapter.fromJson(reader);
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("createTime", "createTime", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"createTi…    \"createTime\", reader)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    break;
                case 3:
                    strFromJson3 = this.stringAdapter.fromJson(reader);
                    if (strFromJson3 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("summarize", "summarize", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"summariz…     \"summarize\", reader)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    break;
                case 4:
                    strFromJson4 = this.stringAdapter.fromJson(reader);
                    if (strFromJson4 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("riskWarning", "riskWarning", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"riskWarn…\", \"riskWarning\", reader)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    break;
                case 5:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("score", "score", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"score\", …ore\",\n            reader)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    break;
            }
        }
        reader.endObject();
        if (i == -2) {
            if (listFromJson == null) {
                JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("listData", "listData", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"listData\", \"listData\", reader)");
                throw jsonDataExceptionMissingProperty;
            }
            if (strFromJson2 == null) {
                JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("createTime", "createTime", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"createT…e\", \"createTime\", reader)");
                throw jsonDataExceptionMissingProperty2;
            }
            if (strFromJson3 == null) {
                JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty("summarize", "summarize", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(\"summarize\", \"summarize\", reader)");
                throw jsonDataExceptionMissingProperty3;
            }
            if (strFromJson4 == null) {
                JsonDataException jsonDataExceptionMissingProperty4 = Util.missingProperty("riskWarning", "riskWarning", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty4, "missingProperty(\"riskWar…g\",\n              reader)");
                throw jsonDataExceptionMissingProperty4;
            }
            if (numFromJson != null) {
                return new AiAnalysisContentData(strFromJson, listFromJson, strFromJson2, strFromJson3, strFromJson4, numFromJson.intValue());
            }
            JsonDataException jsonDataExceptionMissingProperty5 = Util.missingProperty("score", "score", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty5, "missingProperty(\"score\", \"score\", reader)");
            throw jsonDataExceptionMissingProperty5;
        }
        Constructor<AiAnalysisContentData> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            str = "missingProperty(\"summarize\", \"summarize\", reader)";
            declaredConstructor = AiAnalysisContentData.class.getDeclaredConstructor(String.class, List.class, String.class, String.class, String.class, Integer.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "AiAnalysisContentData::c…his.constructorRef = it }");
        } else {
            str = "missingProperty(\"summarize\", \"summarize\", reader)";
        }
        Object[] objArr = new Object[8];
        objArr[0] = strFromJson;
        if (listFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty6 = Util.missingProperty("listData", "listData", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty6, "missingProperty(\"listData\", \"listData\", reader)");
            throw jsonDataExceptionMissingProperty6;
        }
        objArr[1] = listFromJson;
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionMissingProperty7 = Util.missingProperty("createTime", "createTime", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty7, "missingProperty(\"createT…e\", \"createTime\", reader)");
            throw jsonDataExceptionMissingProperty7;
        }
        objArr[2] = strFromJson2;
        if (strFromJson3 == null) {
            JsonDataException jsonDataExceptionMissingProperty8 = Util.missingProperty("summarize", "summarize", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty8, str);
            throw jsonDataExceptionMissingProperty8;
        }
        objArr[3] = strFromJson3;
        if (strFromJson4 == null) {
            JsonDataException jsonDataExceptionMissingProperty9 = Util.missingProperty("riskWarning", "riskWarning", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty9, "missingProperty(\"riskWar…\", \"riskWarning\", reader)");
            throw jsonDataExceptionMissingProperty9;
        }
        objArr[4] = strFromJson4;
        if (numFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty10 = Util.missingProperty("score", "score", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty10, "missingProperty(\"score\", \"score\", reader)");
            throw jsonDataExceptionMissingProperty10;
        }
        objArr[5] = Integer.valueOf(numFromJson.intValue());
        objArr[6] = Integer.valueOf(i);
        objArr[7] = null;
        AiAnalysisContentData aiAnalysisContentDataNewInstance = declaredConstructor.newInstance(objArr);
        Intrinsics.checkNotNullExpressionValue(aiAnalysisContentDataNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return aiAnalysisContentDataNewInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, AiAnalysisContentData value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("comprehensiveOptimization");
        this.nullableStringAdapter.toJson(writer, (JsonWriter) value_.getComprehensiveOptimization());
        writer.name("listData");
        this.listOfAiDataModelAdapter.toJson(writer, (JsonWriter) value_.getListData());
        writer.name("createTime");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getCreateTime());
        writer.name("summarize");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getSummarize());
        writer.name("riskWarning");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getRiskWarning());
        writer.name("score");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getScore()));
        writer.endObject();
    }
}
