package com.qcwireless.qcwatch.ui.mine.ai.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiAnalysisContentData.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JM\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u000bHÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiAnalysisContentData;", "", "comprehensiveOptimization", "", "listData", "", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiDataModel;", "createTime", "summarize", "riskWarning", "score", "", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getComprehensiveOptimization", "()Ljava/lang/String;", "getCreateTime", "getListData", "()Ljava/util/List;", "getRiskWarning", "getScore", "()I", "setScore", "(I)V", "getSummarize", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AiAnalysisContentData {
    private final String comprehensiveOptimization;
    private final String createTime;
    private final List<AiDataModel> listData;
    private final String riskWarning;
    private int score;
    private final String summarize;

    public static /* synthetic */ AiAnalysisContentData copy$default(AiAnalysisContentData aiAnalysisContentData, String str, List list, String str2, String str3, String str4, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = aiAnalysisContentData.comprehensiveOptimization;
        }
        if ((i2 & 2) != 0) {
            list = aiAnalysisContentData.listData;
        }
        List list2 = list;
        if ((i2 & 4) != 0) {
            str2 = aiAnalysisContentData.createTime;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = aiAnalysisContentData.summarize;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            str4 = aiAnalysisContentData.riskWarning;
        }
        String str7 = str4;
        if ((i2 & 32) != 0) {
            i = aiAnalysisContentData.score;
        }
        return aiAnalysisContentData.copy(str, list2, str5, str6, str7, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getComprehensiveOptimization() {
        return this.comprehensiveOptimization;
    }

    public final List<AiDataModel> component2() {
        return this.listData;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCreateTime() {
        return this.createTime;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSummarize() {
        return this.summarize;
    }

    /* renamed from: component5, reason: from getter */
    public final String getRiskWarning() {
        return this.riskWarning;
    }

    /* renamed from: component6, reason: from getter */
    public final int getScore() {
        return this.score;
    }

    public final AiAnalysisContentData copy(String comprehensiveOptimization, List<AiDataModel> listData, String createTime, String summarize, String riskWarning, int score) {
        Intrinsics.checkNotNullParameter(listData, "listData");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(summarize, "summarize");
        Intrinsics.checkNotNullParameter(riskWarning, "riskWarning");
        return new AiAnalysisContentData(comprehensiveOptimization, listData, createTime, summarize, riskWarning, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiAnalysisContentData)) {
            return false;
        }
        AiAnalysisContentData aiAnalysisContentData = (AiAnalysisContentData) other;
        return Intrinsics.areEqual(this.comprehensiveOptimization, aiAnalysisContentData.comprehensiveOptimization) && Intrinsics.areEqual(this.listData, aiAnalysisContentData.listData) && Intrinsics.areEqual(this.createTime, aiAnalysisContentData.createTime) && Intrinsics.areEqual(this.summarize, aiAnalysisContentData.summarize) && Intrinsics.areEqual(this.riskWarning, aiAnalysisContentData.riskWarning) && this.score == aiAnalysisContentData.score;
    }

    public int hashCode() {
        String str = this.comprehensiveOptimization;
        return ((((((((((str == null ? 0 : str.hashCode()) * 31) + this.listData.hashCode()) * 31) + this.createTime.hashCode()) * 31) + this.summarize.hashCode()) * 31) + this.riskWarning.hashCode()) * 31) + this.score;
    }

    public String toString() {
        return "AiAnalysisContentData(comprehensiveOptimization=" + this.comprehensiveOptimization + ", listData=" + this.listData + ", createTime=" + this.createTime + ", summarize=" + this.summarize + ", riskWarning=" + this.riskWarning + ", score=" + this.score + ')';
    }

    public AiAnalysisContentData(String str, List<AiDataModel> listData, String createTime, String summarize, String riskWarning, int i) {
        Intrinsics.checkNotNullParameter(listData, "listData");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(summarize, "summarize");
        Intrinsics.checkNotNullParameter(riskWarning, "riskWarning");
        this.comprehensiveOptimization = str;
        this.listData = listData;
        this.createTime = createTime;
        this.summarize = summarize;
        this.riskWarning = riskWarning;
        this.score = i;
    }

    public /* synthetic */ AiAnalysisContentData(String str, List list, String str2, String str3, String str4, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, list, str2, str3, str4, i);
    }

    public final String getComprehensiveOptimization() {
        return this.comprehensiveOptimization;
    }

    public final List<AiDataModel> getListData() {
        return this.listData;
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final String getSummarize() {
        return this.summarize;
    }

    public final String getRiskWarning() {
        return this.riskWarning;
    }

    public final int getScore() {
        return this.score;
    }

    public final void setScore(int i) {
        this.score = i;
    }
}
