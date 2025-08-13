package com.qcwireless.qcwatch.ui.mine.ai.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiAnalysisContentData.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiDataModel;", "", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", "title", "suggestion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getSuggestion", "getTitle", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AiDataModel {
    private final String content;
    private final String suggestion;
    private final String title;

    public static /* synthetic */ AiDataModel copy$default(AiDataModel aiDataModel, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aiDataModel.content;
        }
        if ((i & 2) != 0) {
            str2 = aiDataModel.title;
        }
        if ((i & 4) != 0) {
            str3 = aiDataModel.suggestion;
        }
        return aiDataModel.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSuggestion() {
        return this.suggestion;
    }

    public final AiDataModel copy(String content, String title, String suggestion) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(suggestion, "suggestion");
        return new AiDataModel(content, title, suggestion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiDataModel)) {
            return false;
        }
        AiDataModel aiDataModel = (AiDataModel) other;
        return Intrinsics.areEqual(this.content, aiDataModel.content) && Intrinsics.areEqual(this.title, aiDataModel.title) && Intrinsics.areEqual(this.suggestion, aiDataModel.suggestion);
    }

    public int hashCode() {
        return (((this.content.hashCode() * 31) + this.title.hashCode()) * 31) + this.suggestion.hashCode();
    }

    public String toString() {
        return "AiDataModel(content=" + this.content + ", title=" + this.title + ", suggestion=" + this.suggestion + ')';
    }

    public AiDataModel(String content, String title, String suggestion) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(suggestion, "suggestion");
        this.content = content;
        this.title = title;
        this.suggestion = suggestion;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSuggestion() {
        return this.suggestion;
    }
}
