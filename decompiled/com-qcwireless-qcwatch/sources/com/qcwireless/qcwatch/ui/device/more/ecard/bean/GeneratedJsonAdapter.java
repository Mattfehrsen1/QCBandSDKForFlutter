package com.qcwireless.qcwatch.ui.device.more.ecard.bean;

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

/* compiled from: SoftwareLinksJsonAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareLinksJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareLinks;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* renamed from: com.qcwireless.qcwatch.ui.device.more.ecard.bean.SoftwareLinksJsonAdapter, reason: from toString */
/* loaded from: classes3.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<SoftwareLinks> {
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("facebookUrl", "qqUrl", "twitterUrl", "wechatUrl", "whatsappUrl");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(\"facebookUrl\", \"qqUrl…echatUrl\", \"whatsappUrl\")");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "facebookUrl");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshi.adapter(String::cl…t(),\n      \"facebookUrl\")");
        this.stringAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(35);
        sb.append("GeneratedJsonAdapter(");
        sb.append("SoftwareLinks");
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public SoftwareLinks fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        String facebookUrl = null;
        String qqUrl = null;
        String twitterUrl = null;
        String wechatUrl = null;
        String whatsappUrl = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                facebookUrl = this.stringAdapter.fromJson(reader);
                if (facebookUrl == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("facebookUrl", "facebookUrl", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(\"facebook…\", \"facebookUrl\", reader)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                qqUrl = this.stringAdapter.fromJson(reader);
                if (qqUrl == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("qqUrl", "qqUrl", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(\"qqUrl\", …Url\",\n            reader)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2) {
                twitterUrl = this.stringAdapter.fromJson(reader);
                if (twitterUrl == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("twitterUrl", "twitterUrl", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(\"twitterU…    \"twitterUrl\", reader)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
            } else if (iSelectName == 3) {
                wechatUrl = this.stringAdapter.fromJson(reader);
                if (wechatUrl == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("wechatUrl", "wechatUrl", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(\"wechatUr…     \"wechatUrl\", reader)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
            } else if (iSelectName == 4 && (whatsappUrl = this.stringAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("whatsappUrl", "whatsappUrl", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(\"whatsapp…\", \"whatsappUrl\", reader)");
                throw jsonDataExceptionUnexpectedNull5;
            }
        }
        reader.endObject();
        SoftwareLinks softwareLinks = new SoftwareLinks();
        if (facebookUrl == null) {
            facebookUrl = softwareLinks.getFacebookUrl();
        }
        softwareLinks.setFacebookUrl(facebookUrl);
        if (qqUrl == null) {
            qqUrl = softwareLinks.getQqUrl();
        }
        softwareLinks.setQqUrl(qqUrl);
        if (twitterUrl == null) {
            twitterUrl = softwareLinks.getTwitterUrl();
        }
        softwareLinks.setTwitterUrl(twitterUrl);
        if (wechatUrl == null) {
            wechatUrl = softwareLinks.getWechatUrl();
        }
        softwareLinks.setWechatUrl(wechatUrl);
        if (whatsappUrl == null) {
            whatsappUrl = softwareLinks.getWhatsappUrl();
        }
        softwareLinks.setWhatsappUrl(whatsappUrl);
        return softwareLinks;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, SoftwareLinks value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Objects.requireNonNull(value_, "value_ was null! Wrap in .nullSafe() to write nullable values.");
        writer.beginObject();
        writer.name("facebookUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getFacebookUrl());
        writer.name("qqUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getQqUrl());
        writer.name("twitterUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getTwitterUrl());
        writer.name("wechatUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getWechatUrl());
        writer.name("whatsappUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getWhatsappUrl());
        writer.endObject();
    }
}
