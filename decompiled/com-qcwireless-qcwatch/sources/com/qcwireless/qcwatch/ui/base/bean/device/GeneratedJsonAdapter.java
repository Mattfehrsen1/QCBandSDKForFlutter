package com.qcwireless.qcwatch.ui.base.bean.device;

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
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: DeviceSettingJsonAdapter.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u001e\u001a\u00020\u0015H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSettingJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "arrayOfDrinkBeanAdapter", "", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "intAdapter", "", "mutableListOfAlarmBeanAdapter", "", "Lcom/qcwireless/qcwatch/ui/base/bean/device/AlarmBean;", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.base.bean.device.DeviceSettingJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<DeviceSetting> {
    private final JsonAdapter<DrinkBean[]> arrayOfDrinkBeanAdapter;
    private final JsonAdapter<Boolean> booleanAdapter;
    private volatile Constructor<DeviceSetting> constructorRef;
    private final JsonAdapter<Integer> intAdapter;
    private final JsonAdapter<List<AlarmBean>> mutableListOfAlarmBeanAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("callSwitch", "messagePushSwitch", "smsPushSwitch", "bpSwitch", "bpStart", "bpEnd", "bpInterval", "hrDetection", "bo2Detection", "hrvEnable", "pressureDetection", "wristSense", "wristSenseHand", "timeFormat", "weatherFormat", "metricUnit", "disturbSwitch", "disturbManualSwitch", "disturbStart", "disturbEnd", "firmwareVersion", "screenLight", "longSitSwitch", "longSitStart", "longSitEnd", "longSitDuring", "longSitWeek", "drinkSwitch", "drinkWeek", "drinkArray", "alarmList", "avatarScreen", "avatarWidth", "avatarHeight", "batteryWarming");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"callSwitch\", \"messag…eight\", \"batteryWarming\")");
        this.options = optionsOf;
        JsonAdapter<Boolean> jsonAdapterAdapter = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "callSwitch");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Boolean::c…et(),\n      \"callSwitch\")");
        this.booleanAdapter = jsonAdapterAdapter;
        JsonAdapter<Integer> jsonAdapterAdapter2 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "bpStart");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(Int::class…a, emptySet(), \"bpStart\")");
        this.intAdapter = jsonAdapterAdapter2;
        JsonAdapter<String> jsonAdapterAdapter3 = moshi.adapter(String.class, SetsKt.emptySet(), "firmwareVersion");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "moshi.adapter(String::cl…\n      \"firmwareVersion\")");
        this.stringAdapter = jsonAdapterAdapter3;
        JsonAdapter<DrinkBean[]> jsonAdapterAdapter4 = moshi.adapter(Types.arrayOf(DrinkBean.class), SetsKt.emptySet(), "drinkArray");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter4, "moshi.adapter(Types.arra…emptySet(), \"drinkArray\")");
        this.arrayOfDrinkBeanAdapter = jsonAdapterAdapter4;
        JsonAdapter<List<AlarmBean>> jsonAdapterAdapter5 = moshi.adapter(Types.newParameterizedType(List.class, AlarmBean.class), SetsKt.emptySet(), "alarmList");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter5, "moshi.adapter(Types.newP… emptySet(), \"alarmList\")");
        this.mutableListOfAlarmBeanAdapter = jsonAdapterAdapter5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(35);
        sb.append("GeneratedJsonAdapter(");
        sb.append("DeviceSetting");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public DeviceSetting fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(reader, "reader");
        Boolean boolFromJson = false;
        reader.beginObject();
        Boolean boolFromJson2 = boolFromJson;
        Boolean boolFromJson3 = boolFromJson2;
        Boolean boolFromJson4 = boolFromJson3;
        Boolean boolFromJson5 = boolFromJson4;
        Boolean boolFromJson6 = boolFromJson5;
        Boolean boolFromJson7 = boolFromJson6;
        Boolean boolFromJson8 = boolFromJson7;
        Boolean boolFromJson9 = boolFromJson8;
        Boolean boolFromJson10 = boolFromJson9;
        Boolean boolFromJson11 = boolFromJson10;
        Boolean boolFromJson12 = boolFromJson11;
        Boolean boolFromJson13 = boolFromJson12;
        Integer numFromJson = 0;
        Integer numFromJson2 = null;
        Integer numFromJson3 = null;
        Integer numFromJson4 = null;
        Integer numFromJson5 = null;
        Integer numFromJson6 = null;
        Integer numFromJson7 = null;
        Integer numFromJson8 = null;
        Integer numFromJson9 = null;
        Integer numFromJson10 = null;
        Integer numFromJson11 = null;
        Integer numFromJson12 = null;
        Integer numFromJson13 = null;
        Integer numFromJson14 = null;
        Integer numFromJson15 = null;
        Integer numFromJson16 = null;
        Integer numFromJson17 = null;
        Integer numFromJson18 = null;
        int i3 = -1;
        int i4 = -1;
        String strFromJson = null;
        DrinkBean[] drinkBeanArrFromJson = null;
        List<AlarmBean> listFromJson = null;
        Boolean boolFromJson14 = boolFromJson13;
        while (reader.hasNext()) {
            String str = strFromJson;
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    strFromJson = str;
                case 0:
                    boolFromJson = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("callSwitch", "callSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"callSwit…    \"callSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    i4 &= -2;
                    strFromJson = str;
                case 1:
                    boolFromJson14 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson14 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("messagePushSwitch", "messagePushSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"messageP…ssagePushSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    i4 &= -3;
                    strFromJson = str;
                case 2:
                    boolFromJson2 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("smsPushSwitch", "smsPushSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"smsPushS… \"smsPushSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    i4 &= -5;
                    strFromJson = str;
                case 3:
                    boolFromJson3 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson3 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("bpSwitch", "bpSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"bpSwitch…      \"bpSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    i4 &= -9;
                    strFromJson = str;
                case 4:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("bpStart", "bpStart", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"bpStart\"…t\",\n              reader)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    i4 &= -17;
                    strFromJson = str;
                case 5:
                    numFromJson2 = this.intAdapter.fromJson(reader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull6 = Util.unexpectedNull("bpEnd", "bpEnd", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull6, "unexpectedNull(\"bpEnd\", \"bpEnd\", reader)");
                        throw jsonDataExceptionUnexpectedNull6;
                    }
                    i4 &= -33;
                    strFromJson = str;
                case 6:
                    numFromJson3 = this.intAdapter.fromJson(reader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull7 = Util.unexpectedNull("bpInterval", "bpInterval", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull7, "unexpectedNull(\"bpInterv…    \"bpInterval\", reader)");
                        throw jsonDataExceptionUnexpectedNull7;
                    }
                    i4 &= -65;
                    strFromJson = str;
                case 7:
                    boolFromJson4 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson4 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull8 = Util.unexpectedNull("hrDetection", "hrDetection", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull8, "unexpectedNull(\"hrDetect…   \"hrDetection\", reader)");
                        throw jsonDataExceptionUnexpectedNull8;
                    }
                    i4 &= -129;
                    strFromJson = str;
                case 8:
                    boolFromJson5 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson5 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull9 = Util.unexpectedNull("bo2Detection", "bo2Detection", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull9, "unexpectedNull(\"bo2Detec…, \"bo2Detection\", reader)");
                        throw jsonDataExceptionUnexpectedNull9;
                    }
                    i4 &= -257;
                    strFromJson = str;
                case 9:
                    boolFromJson6 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson6 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull10 = Util.unexpectedNull("hrvEnable", "hrvEnable", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull10, "unexpectedNull(\"hrvEnabl…     \"hrvEnable\", reader)");
                        throw jsonDataExceptionUnexpectedNull10;
                    }
                    i4 &= -513;
                    strFromJson = str;
                case 10:
                    boolFromJson7 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson7 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull11 = Util.unexpectedNull("pressureDetection", "pressureDetection", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull11, "unexpectedNull(\"pressure…essureDetection\", reader)");
                        throw jsonDataExceptionUnexpectedNull11;
                    }
                    i4 &= -1025;
                    strFromJson = str;
                case 11:
                    boolFromJson8 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson8 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull12 = Util.unexpectedNull("wristSense", "wristSense", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull12, "unexpectedNull(\"wristSen…    \"wristSense\", reader)");
                        throw jsonDataExceptionUnexpectedNull12;
                    }
                    i4 &= -2049;
                    strFromJson = str;
                case 12:
                    numFromJson4 = this.intAdapter.fromJson(reader);
                    if (numFromJson4 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull13 = Util.unexpectedNull("wristSenseHand", "wristSenseHand", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull13, "unexpectedNull(\"wristSen…\"wristSenseHand\", reader)");
                        throw jsonDataExceptionUnexpectedNull13;
                    }
                    i4 &= -4097;
                    strFromJson = str;
                case 13:
                    numFromJson5 = this.intAdapter.fromJson(reader);
                    if (numFromJson5 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull14 = Util.unexpectedNull("timeFormat", "timeFormat", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull14, "unexpectedNull(\"timeForm…    \"timeFormat\", reader)");
                        throw jsonDataExceptionUnexpectedNull14;
                    }
                    i4 &= -8193;
                    strFromJson = str;
                case 14:
                    numFromJson6 = this.intAdapter.fromJson(reader);
                    if (numFromJson6 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull15 = Util.unexpectedNull("weatherFormat", "weatherFormat", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull15, "unexpectedNull(\"weatherF… \"weatherFormat\", reader)");
                        throw jsonDataExceptionUnexpectedNull15;
                    }
                    i4 &= -16385;
                    strFromJson = str;
                case 15:
                    numFromJson7 = this.intAdapter.fromJson(reader);
                    if (numFromJson7 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull16 = Util.unexpectedNull("metricUnit", "metricUnit", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull16, "unexpectedNull(\"metricUn…    \"metricUnit\", reader)");
                        throw jsonDataExceptionUnexpectedNull16;
                    }
                    i2 = -32769;
                    i4 &= i2;
                    strFromJson = str;
                case 16:
                    boolFromJson9 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson9 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull17 = Util.unexpectedNull("disturbSwitch", "disturbSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull17, "unexpectedNull(\"disturbS… \"disturbSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull17;
                    }
                    i2 = -65537;
                    i4 &= i2;
                    strFromJson = str;
                case 17:
                    boolFromJson10 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson10 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull18 = Util.unexpectedNull("disturbManualSwitch", "disturbManualSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull18, "unexpectedNull(\"disturbM…urbManualSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull18;
                    }
                    i2 = -131073;
                    i4 &= i2;
                    strFromJson = str;
                case 18:
                    numFromJson8 = this.intAdapter.fromJson(reader);
                    if (numFromJson8 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull19 = Util.unexpectedNull("disturbStart", "disturbStart", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull19, "unexpectedNull(\"disturbS…  \"disturbStart\", reader)");
                        throw jsonDataExceptionUnexpectedNull19;
                    }
                    i2 = -262145;
                    i4 &= i2;
                    strFromJson = str;
                case 19:
                    numFromJson9 = this.intAdapter.fromJson(reader);
                    if (numFromJson9 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull20 = Util.unexpectedNull("disturbEnd", "disturbEnd", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull20, "unexpectedNull(\"disturbE…    \"disturbEnd\", reader)");
                        throw jsonDataExceptionUnexpectedNull20;
                    }
                    i2 = -524289;
                    i4 &= i2;
                    strFromJson = str;
                case 20:
                    strFromJson = this.stringAdapter.fromJson(reader);
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull21 = Util.unexpectedNull("firmwareVersion", "firmwareVersion", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull21, "unexpectedNull(\"firmware…firmwareVersion\", reader)");
                        throw jsonDataExceptionUnexpectedNull21;
                    }
                    i4 &= -1048577;
                case 21:
                    numFromJson10 = this.intAdapter.fromJson(reader);
                    if (numFromJson10 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull22 = Util.unexpectedNull("screenLight", "screenLight", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull22, "unexpectedNull(\"screenLi…   \"screenLight\", reader)");
                        throw jsonDataExceptionUnexpectedNull22;
                    }
                    i2 = -2097153;
                    i4 &= i2;
                    strFromJson = str;
                case 22:
                    boolFromJson13 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson13 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull23 = Util.unexpectedNull("longSitSwitch", "longSitSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull23, "unexpectedNull(\"longSitS… \"longSitSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull23;
                    }
                    i2 = -4194305;
                    i4 &= i2;
                    strFromJson = str;
                case 23:
                    numFromJson11 = this.intAdapter.fromJson(reader);
                    if (numFromJson11 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull24 = Util.unexpectedNull("longSitStart", "longSitStart", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull24, "unexpectedNull(\"longSitS…  \"longSitStart\", reader)");
                        throw jsonDataExceptionUnexpectedNull24;
                    }
                    i2 = -8388609;
                    i4 &= i2;
                    strFromJson = str;
                case 24:
                    numFromJson12 = this.intAdapter.fromJson(reader);
                    if (numFromJson12 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull25 = Util.unexpectedNull("longSitEnd", "longSitEnd", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull25, "unexpectedNull(\"longSitE…    \"longSitEnd\", reader)");
                        throw jsonDataExceptionUnexpectedNull25;
                    }
                    i2 = -16777217;
                    i4 &= i2;
                    strFromJson = str;
                case 25:
                    numFromJson13 = this.intAdapter.fromJson(reader);
                    if (numFromJson13 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull26 = Util.unexpectedNull("longSitDuring", "longSitDuring", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull26, "unexpectedNull(\"longSitD… \"longSitDuring\", reader)");
                        throw jsonDataExceptionUnexpectedNull26;
                    }
                    i2 = -33554433;
                    i4 &= i2;
                    strFromJson = str;
                case 26:
                    numFromJson14 = this.intAdapter.fromJson(reader);
                    if (numFromJson14 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull27 = Util.unexpectedNull("longSitWeek", "longSitWeek", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull27, "unexpectedNull(\"longSitW…   \"longSitWeek\", reader)");
                        throw jsonDataExceptionUnexpectedNull27;
                    }
                    i2 = -67108865;
                    i4 &= i2;
                    strFromJson = str;
                case 27:
                    boolFromJson11 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson11 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull28 = Util.unexpectedNull("drinkSwitch", "drinkSwitch", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull28, "unexpectedNull(\"drinkSwi…   \"drinkSwitch\", reader)");
                        throw jsonDataExceptionUnexpectedNull28;
                    }
                    i2 = -134217729;
                    i4 &= i2;
                    strFromJson = str;
                case 28:
                    numFromJson15 = this.intAdapter.fromJson(reader);
                    if (numFromJson15 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull29 = Util.unexpectedNull("drinkWeek", "drinkWeek", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull29, "unexpectedNull(\"drinkWee…     \"drinkWeek\", reader)");
                        throw jsonDataExceptionUnexpectedNull29;
                    }
                    i2 = -268435457;
                    i4 &= i2;
                    strFromJson = str;
                case 29:
                    drinkBeanArrFromJson = this.arrayOfDrinkBeanAdapter.fromJson(reader);
                    if (drinkBeanArrFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull30 = Util.unexpectedNull("drinkArray", "drinkArray", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull30, "unexpectedNull(\"drinkArray\", \"drinkArray\", reader)");
                        throw jsonDataExceptionUnexpectedNull30;
                    }
                    i2 = -536870913;
                    i4 &= i2;
                    strFromJson = str;
                case 30:
                    listFromJson = this.mutableListOfAlarmBeanAdapter.fromJson(reader);
                    if (listFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull31 = Util.unexpectedNull("alarmList", "alarmList", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull31, "unexpectedNull(\"alarmList\", \"alarmList\", reader)");
                        throw jsonDataExceptionUnexpectedNull31;
                    }
                    i2 = -1073741825;
                    i4 &= i2;
                    strFromJson = str;
                case 31:
                    numFromJson16 = this.intAdapter.fromJson(reader);
                    if (numFromJson16 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull32 = Util.unexpectedNull("avatarScreen", "avatarScreen", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull32, "unexpectedNull(\"avatarSc…  \"avatarScreen\", reader)");
                        throw jsonDataExceptionUnexpectedNull32;
                    }
                    i2 = Integer.MAX_VALUE;
                    i4 &= i2;
                    strFromJson = str;
                case 32:
                    numFromJson17 = this.intAdapter.fromJson(reader);
                    if (numFromJson17 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull33 = Util.unexpectedNull("avatarWidth", "avatarWidth", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull33, "unexpectedNull(\"avatarWi…   \"avatarWidth\", reader)");
                        throw jsonDataExceptionUnexpectedNull33;
                    }
                    i3 &= -2;
                    strFromJson = str;
                case 33:
                    numFromJson18 = this.intAdapter.fromJson(reader);
                    if (numFromJson18 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull34 = Util.unexpectedNull("avatarHeight", "avatarHeight", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull34, "unexpectedNull(\"avatarHe…  \"avatarHeight\", reader)");
                        throw jsonDataExceptionUnexpectedNull34;
                    }
                    i3 &= -3;
                    strFromJson = str;
                case 34:
                    boolFromJson12 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson12 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull35 = Util.unexpectedNull("batteryWarming", "batteryWarming", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull35, "unexpectedNull(\"batteryW…\"batteryWarming\", reader)");
                        throw jsonDataExceptionUnexpectedNull35;
                    }
                    i3 &= -5;
                    strFromJson = str;
                default:
                    strFromJson = str;
            }
        }
        String str2 = strFromJson;
        reader.endObject();
        if (i4 == 0 && i3 == -8) {
            boolean zBooleanValue = boolFromJson.booleanValue();
            boolean zBooleanValue2 = boolFromJson14.booleanValue();
            boolean zBooleanValue3 = boolFromJson2.booleanValue();
            boolean zBooleanValue4 = boolFromJson3.booleanValue();
            int iIntValue = numFromJson.intValue();
            int iIntValue2 = numFromJson2.intValue();
            int iIntValue3 = numFromJson3.intValue();
            boolean zBooleanValue5 = boolFromJson4.booleanValue();
            boolean zBooleanValue6 = boolFromJson5.booleanValue();
            boolean zBooleanValue7 = boolFromJson6.booleanValue();
            boolean zBooleanValue8 = boolFromJson7.booleanValue();
            boolean zBooleanValue9 = boolFromJson8.booleanValue();
            int iIntValue4 = numFromJson4.intValue();
            int iIntValue5 = numFromJson5.intValue();
            int iIntValue6 = numFromJson6.intValue();
            int iIntValue7 = numFromJson7.intValue();
            boolean zBooleanValue10 = boolFromJson9.booleanValue();
            boolean zBooleanValue11 = boolFromJson10.booleanValue();
            int iIntValue8 = numFromJson8.intValue();
            int iIntValue9 = numFromJson9.intValue();
            Objects.requireNonNull(str2, "null cannot be cast to non-null type kotlin.String");
            int iIntValue10 = numFromJson10.intValue();
            boolean zBooleanValue12 = boolFromJson13.booleanValue();
            int iIntValue11 = numFromJson11.intValue();
            int iIntValue12 = numFromJson12.intValue();
            int iIntValue13 = numFromJson13.intValue();
            int iIntValue14 = numFromJson14.intValue();
            boolean zBooleanValue13 = boolFromJson11.booleanValue();
            int iIntValue15 = numFromJson15.intValue();
            DrinkBean[] drinkBeanArr = drinkBeanArrFromJson;
            Objects.requireNonNull(drinkBeanArr, "null cannot be cast to non-null type kotlin.Array<com.qcwireless.qcwatch.ui.base.bean.device.DrinkBean>");
            List<AlarmBean> list = listFromJson;
            Objects.requireNonNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<com.qcwireless.qcwatch.ui.base.bean.device.AlarmBean>");
            return new DeviceSetting(zBooleanValue, zBooleanValue2, zBooleanValue3, zBooleanValue4, iIntValue, iIntValue2, iIntValue3, zBooleanValue5, zBooleanValue6, zBooleanValue7, zBooleanValue8, zBooleanValue9, iIntValue4, iIntValue5, iIntValue6, iIntValue7, zBooleanValue10, zBooleanValue11, iIntValue8, iIntValue9, str2, iIntValue10, zBooleanValue12, iIntValue11, iIntValue12, iIntValue13, iIntValue14, zBooleanValue13, iIntValue15, drinkBeanArr, TypeIntrinsics.asMutableList(list), numFromJson16.intValue(), numFromJson17.intValue(), numFromJson18.intValue(), boolFromJson12.booleanValue());
        }
        Constructor<DeviceSetting> declaredConstructor = this.constructorRef;
        int i5 = i3;
        if (declaredConstructor == null) {
            i = i4;
            declaredConstructor = DeviceSetting.class.getDeclaredConstructor(Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE, DrinkBean[].class, List.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Unit unit = Unit.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "DeviceSetting::class.jav…his.constructorRef = it }");
        } else {
            i = i4;
        }
        DeviceSetting deviceSettingNewInstance = declaredConstructor.newInstance(boolFromJson, boolFromJson14, boolFromJson2, boolFromJson3, numFromJson, numFromJson2, numFromJson3, boolFromJson4, boolFromJson5, boolFromJson6, boolFromJson7, boolFromJson8, numFromJson4, numFromJson5, numFromJson6, numFromJson7, boolFromJson9, boolFromJson10, numFromJson8, numFromJson9, str2, numFromJson10, boolFromJson13, numFromJson11, numFromJson12, numFromJson13, numFromJson14, boolFromJson11, numFromJson15, drinkBeanArrFromJson, listFromJson, numFromJson16, numFromJson17, numFromJson18, boolFromJson12, Integer.valueOf(i), Integer.valueOf(i5), null);
        Intrinsics.checkNotNullExpressionValue(deviceSettingNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return deviceSettingNewInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, DeviceSetting value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("callSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getCallSwitch()));
        writer.name("messagePushSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getMessagePushSwitch()));
        writer.name("smsPushSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getSmsPushSwitch()));
        writer.name("bpSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getBpSwitch()));
        writer.name("bpStart");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getBpStart()));
        writer.name("bpEnd");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getBpEnd()));
        writer.name("bpInterval");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getBpInterval()));
        writer.name("hrDetection");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getHrDetection()));
        writer.name("bo2Detection");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getBo2Detection()));
        writer.name("hrvEnable");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getHrvEnable()));
        writer.name("pressureDetection");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getPressureDetection()));
        writer.name("wristSense");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getWristSense()));
        writer.name("wristSenseHand");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getWristSenseHand()));
        writer.name("timeFormat");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getTimeFormat()));
        writer.name("weatherFormat");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getWeatherFormat()));
        writer.name("metricUnit");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getMetricUnit()));
        writer.name("disturbSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getDisturbSwitch()));
        writer.name("disturbManualSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getDisturbManualSwitch()));
        writer.name("disturbStart");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getDisturbStart()));
        writer.name("disturbEnd");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getDisturbEnd()));
        writer.name("firmwareVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getFirmwareVersion());
        writer.name("screenLight");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getScreenLight()));
        writer.name("longSitSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getLongSitSwitch()));
        writer.name("longSitStart");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getLongSitStart()));
        writer.name("longSitEnd");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getLongSitEnd()));
        writer.name("longSitDuring");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getLongSitDuring()));
        writer.name("longSitWeek");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getLongSitWeek()));
        writer.name("drinkSwitch");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getDrinkSwitch()));
        writer.name("drinkWeek");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getDrinkWeek()));
        writer.name("drinkArray");
        this.arrayOfDrinkBeanAdapter.toJson(writer, (JsonWriter) value_.getDrinkArray());
        writer.name("alarmList");
        this.mutableListOfAlarmBeanAdapter.toJson(writer, (JsonWriter) value_.getAlarmList());
        writer.name("avatarScreen");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getAvatarScreen()));
        writer.name("avatarWidth");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getAvatarWidth()));
        writer.name("avatarHeight");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getAvatarHeight()));
        writer.name("batteryWarming");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.getBatteryWarming()));
        writer.endObject();
    }
}
