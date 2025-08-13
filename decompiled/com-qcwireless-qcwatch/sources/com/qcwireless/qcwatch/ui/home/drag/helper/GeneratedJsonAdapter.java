package com.qcwireless.qcwatch.ui.home.drag.helper;

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

/* compiled from: ItemEntityJsonAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/drag/helper/ItemEntityJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/ItemEntity;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "intAdapter", "", "nullableStringAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntityJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<ItemEntity> {
    private final JsonAdapter<Boolean> booleanAdapter;
    private volatile Constructor<ItemEntity> constructorRef;
    private final JsonAdapter<Integer> intAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("text", "isChecked", "modelType", "showOrNot");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"text\", \"isChecked\", …Type\",\n      \"showOrNot\")");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "text");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(String::cl…      emptySet(), \"text\")");
        this.nullableStringAdapter = jsonAdapterAdapter;
        JsonAdapter<Boolean> jsonAdapterAdapter2 = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "isChecked");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(Boolean::c…Set(),\n      \"isChecked\")");
        this.booleanAdapter = jsonAdapterAdapter2;
        JsonAdapter<Integer> jsonAdapterAdapter3 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "modelType");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "moshi.adapter(Int::class… emptySet(), \"modelType\")");
        this.intAdapter = jsonAdapterAdapter3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("GeneratedJsonAdapter(");
        sb.append("ItemEntity");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public ItemEntity fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Boolean boolFromJson = false;
        reader.beginObject();
        int i = -1;
        String strFromJson = null;
        Boolean boolFromJson2 = null;
        Integer numFromJson = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(reader);
            } else if (iSelectName == 1) {
                boolFromJson2 = this.booleanAdapter.fromJson(reader);
                if (boolFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("isChecked", "isChecked", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"isChecke…     \"isChecked\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 2) {
                numFromJson = this.intAdapter.fromJson(reader);
                if (numFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("modelType", "modelType", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"modelTyp…     \"modelType\", reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 3) {
                boolFromJson = this.booleanAdapter.fromJson(reader);
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("showOrNot", "showOrNot", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"showOrNo…     \"showOrNot\", reader)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
                i &= -9;
            } else {
                continue;
            }
        }
        reader.endObject();
        if (i == -9) {
            if (boolFromJson2 != null) {
                boolean zBooleanValue = boolFromJson2.booleanValue();
                if (numFromJson != null) {
                    return new ItemEntity(strFromJson, zBooleanValue, numFromJson.intValue(), boolFromJson.booleanValue());
                }
                JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("modelType", "modelType", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(\"modelType\", \"modelType\", reader)");
                throw jsonDataExceptionMissingProperty;
            }
            JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("isChecked", "isChecked", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(\"isChecked\", \"isChecked\", reader)");
            throw jsonDataExceptionMissingProperty2;
        }
        Constructor<ItemEntity> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = ItemEntity.class.getDeclaredConstructor(String.class, Boolean.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "ItemEntity::class.java.g…his.constructorRef = it }");
        }
        Object[] objArr = new Object[6];
        objArr[0] = strFromJson;
        if (boolFromJson2 != null) {
            objArr[1] = Boolean.valueOf(boolFromJson2.booleanValue());
            if (numFromJson == null) {
                JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty("modelType", "modelType", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(\"modelType\", \"modelType\", reader)");
                throw jsonDataExceptionMissingProperty3;
            }
            objArr[2] = Integer.valueOf(numFromJson.intValue());
            objArr[3] = boolFromJson;
            objArr[4] = Integer.valueOf(i);
            objArr[5] = null;
            ItemEntity itemEntityNewInstance = declaredConstructor.newInstance(objArr);
            Intrinsics.checkNotNullExpressionValue(itemEntityNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
            return itemEntityNewInstance;
        }
        JsonDataException jsonDataExceptionMissingProperty4 = Util.missingProperty("isChecked", "isChecked", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty4, "missingProperty(\"isChecked\", \"isChecked\", reader)");
        throw jsonDataExceptionMissingProperty4;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, ItemEntity value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("text");
        this.nullableStringAdapter.toJson(writer, (JsonWriter) value_.getText());
        writer.name("isChecked");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.isChecked()));
        writer.name("modelType");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getModelType()));
        writer.name("showOrNot");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getShowOrNot()));
        writer.endObject();
    }
}
