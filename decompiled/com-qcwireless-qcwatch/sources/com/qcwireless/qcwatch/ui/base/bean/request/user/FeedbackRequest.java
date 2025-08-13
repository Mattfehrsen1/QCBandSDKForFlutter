package com.qcwireless.qcwatch.ui.base.bean.request.user;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedbackRequest.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/user/FeedbackRequest;", "", "()V", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "email", "getEmail", "setEmail", "feedbackId", "", "getFeedbackId", "()I", "setFeedbackId", "(I)V", "typeId", "getTypeId", "setTypeId", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeedbackRequest {
    private int feedbackId;
    private int typeId;
    private String email = "";
    private String content = "";

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    public final int getFeedbackId() {
        return this.feedbackId;
    }

    public final void setFeedbackId(int i) {
        this.feedbackId = i;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.email = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content = str;
    }
}
