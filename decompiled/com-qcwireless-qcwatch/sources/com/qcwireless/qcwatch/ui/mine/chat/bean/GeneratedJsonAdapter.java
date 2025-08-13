package com.qcwireless.qcwatch.ui.mine.chat.bean;

import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.qcwireless.qcwatch.ui.mine.chat.bean.ChatMessageBean;
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

/* compiled from: ChatMessageBeanJsonAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatMessageBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatMessageBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "intAdapter", "", "longAdapter", "", "nullableFromUserAdapter", "Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatMessageBean$FromUser;", "nullableStringAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.mine.chat.bean.ChatMessageBeanJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<ChatMessageBean> {
    private final JsonAdapter<Integer> intAdapter;
    private final JsonAdapter<Long> longAdapter;
    private final JsonAdapter<ChatMessageBean.FromUser> nullableFromUserAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("client", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "fromUser", "going", "hdVersion", BreakpointSQLiteKey.ID, "sendTime", "toContactId", "type");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"client\", \"content\", …\", \"toContactId\", \"type\")");
        this.options = optionsOf;
        JsonAdapter<Integer> jsonAdapterAdapter = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "client");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(Int::class…va, emptySet(), \"client\")");
        this.intAdapter = jsonAdapterAdapter;
        JsonAdapter<String> jsonAdapterAdapter2 = moshi.adapter(String.class, SetsKt.emptySet(), Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshi.adapter(String::cl…tySet(),\n      \"content\")");
        this.stringAdapter = jsonAdapterAdapter2;
        JsonAdapter<ChatMessageBean.FromUser> jsonAdapterAdapter3 = moshi.adapter(ChatMessageBean.FromUser.class, SetsKt.emptySet(), "fromUser");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "moshi.adapter(ChatMessag…, emptySet(), \"fromUser\")");
        this.nullableFromUserAdapter = jsonAdapterAdapter3;
        JsonAdapter<String> jsonAdapterAdapter4 = moshi.adapter(String.class, SetsKt.emptySet(), "hdVersion");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter4, "moshi.adapter(String::cl… emptySet(), \"hdVersion\")");
        this.nullableStringAdapter = jsonAdapterAdapter4;
        JsonAdapter<Long> jsonAdapterAdapter5 = moshi.adapter(Long.TYPE, SetsKt.emptySet(), "sendTime");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter5, "moshi.adapter(Long::clas…ySet(),\n      \"sendTime\")");
        this.longAdapter = jsonAdapterAdapter5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(37);
        sb.append("GeneratedJsonAdapter(");
        sb.append("ChatMessageBean");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public ChatMessageBean fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        boolean z = false;
        Integer numFromJson = null;
        String content = null;
        ChatMessageBean.FromUser fromUserFromJson = null;
        String going = null;
        String strFromJson = null;
        String strFromJson2 = null;
        Long lFromJson = null;
        String strFromJson3 = null;
        String type = null;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (reader.hasNext()) {
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    break;
                case 0:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("client", "client", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"client\",…ent\",\n            reader)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    break;
                case 1:
                    content = this.stringAdapter.fromJson(reader);
                    if (content == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, Constant.MODIFY_ACTIVITY_INTENT_CONTENT, reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"content\"…       \"content\", reader)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    break;
                case 2:
                    fromUserFromJson = this.nullableFromUserAdapter.fromJson(reader);
                    z = true;
                    break;
                case 3:
                    going = this.stringAdapter.fromJson(reader);
                    if (going == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("going", "going", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"going\", …ing\",\n            reader)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    break;
                case 4:
                    strFromJson = this.nullableStringAdapter.fromJson(reader);
                    z2 = true;
                    break;
                case 5:
                    strFromJson2 = this.nullableStringAdapter.fromJson(reader);
                    z3 = true;
                    break;
                case 6:
                    lFromJson = this.longAdapter.fromJson(reader);
                    if (lFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("sendTime", "sendTime", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"sendTime…      \"sendTime\", reader)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    break;
                case 7:
                    strFromJson3 = this.nullableStringAdapter.fromJson(reader);
                    z4 = true;
                    break;
                case 8:
                    type = this.stringAdapter.fromJson(reader);
                    if (type == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("type", "type", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"type\", \"type\",\n            reader)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    break;
            }
        }
        reader.endObject();
        ChatMessageBean chatMessageBean = new ChatMessageBean();
        chatMessageBean.setClient(numFromJson != null ? numFromJson.intValue() : chatMessageBean.getClient());
        if (content == null) {
            content = chatMessageBean.getContent();
        }
        chatMessageBean.setContent(content);
        if (z) {
            chatMessageBean.setFromUser(fromUserFromJson);
        }
        if (going == null) {
            going = chatMessageBean.getGoing();
        }
        chatMessageBean.setGoing(going);
        if (z2) {
            chatMessageBean.setHdVersion(strFromJson);
        }
        if (z3) {
            chatMessageBean.setId(strFromJson2);
        }
        chatMessageBean.setSendTime(lFromJson != null ? lFromJson.longValue() : chatMessageBean.getSendTime());
        if (z4) {
            chatMessageBean.setToContactId(strFromJson3);
        }
        if (type == null) {
            type = chatMessageBean.getType();
        }
        chatMessageBean.setType(type);
        return chatMessageBean;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, ChatMessageBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("client");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getClient()));
        writer.name(Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getContent());
        writer.name("fromUser");
        this.nullableFromUserAdapter.toJson(writer, (JsonWriter) value_.getFromUser());
        writer.name("going");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getGoing());
        writer.name("hdVersion");
        this.nullableStringAdapter.toJson(writer, (JsonWriter) value_.getHdVersion());
        writer.name(BreakpointSQLiteKey.ID);
        this.nullableStringAdapter.toJson(writer, (JsonWriter) value_.getId());
        writer.name("sendTime");
        this.longAdapter.toJson(writer, (JsonWriter) Long.valueOf(value_.getSendTime()));
        writer.name("toContactId");
        this.nullableStringAdapter.toJson(writer, (JsonWriter) value_.getToContactId());
        writer.name("type");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getType());
        writer.endObject();
    }
}
