package com.qcwireless.qcwatch.ui.base.bean.response.mine.feedback;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedbackResp.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/feedback/FeedbackResp;", "", "typeId", "", "feedbackId", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", "(IILjava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getFeedbackId", "()I", "getTypeId", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class FeedbackResp {
    private final String content;
    private final int feedbackId;
    private final int typeId;

    public static /* synthetic */ FeedbackResp copy$default(FeedbackResp feedbackResp, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = feedbackResp.typeId;
        }
        if ((i3 & 2) != 0) {
            i2 = feedbackResp.feedbackId;
        }
        if ((i3 & 4) != 0) {
            str = feedbackResp.content;
        }
        return feedbackResp.copy(i, i2, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTypeId() {
        return this.typeId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getFeedbackId() {
        return this.feedbackId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    public final FeedbackResp copy(int typeId, int feedbackId, String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return new FeedbackResp(typeId, feedbackId, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeedbackResp)) {
            return false;
        }
        FeedbackResp feedbackResp = (FeedbackResp) other;
        return this.typeId == feedbackResp.typeId && this.feedbackId == feedbackResp.feedbackId && Intrinsics.areEqual(this.content, feedbackResp.content);
    }

    public int hashCode() {
        return (((this.typeId * 31) + this.feedbackId) * 31) + this.content.hashCode();
    }

    public String toString() {
        return "FeedbackResp(typeId=" + this.typeId + ", feedbackId=" + this.feedbackId + ", content=" + this.content + ')';
    }

    public FeedbackResp(int i, int i2, String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.typeId = i;
        this.feedbackId = i2;
        this.content = content;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final int getFeedbackId() {
        return this.feedbackId;
    }

    public final String getContent() {
        return this.content;
    }
}
