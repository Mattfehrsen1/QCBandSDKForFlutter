package com.qcwireless.qcwatch.ui.mine.ai.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiChatBean.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;", "", "role", "", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "type", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getContent", "()Ljava/lang/String;", "getRole", "getType", "()I", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AiChatBean {
    private final String content;
    private final String role;
    private final int type;

    public static /* synthetic */ AiChatBean copy$default(AiChatBean aiChatBean, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = aiChatBean.role;
        }
        if ((i2 & 2) != 0) {
            str2 = aiChatBean.content;
        }
        if ((i2 & 4) != 0) {
            i = aiChatBean.type;
        }
        return aiChatBean.copy(str, str2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component3, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public final AiChatBean copy(String role, String content, int type) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        return new AiChatBean(role, content, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiChatBean)) {
            return false;
        }
        AiChatBean aiChatBean = (AiChatBean) other;
        return Intrinsics.areEqual(this.role, aiChatBean.role) && Intrinsics.areEqual(this.content, aiChatBean.content) && this.type == aiChatBean.type;
    }

    public int hashCode() {
        return (((this.role.hashCode() * 31) + this.content.hashCode()) * 31) + this.type;
    }

    public String toString() {
        return "AiChatBean(role=" + this.role + ", content=" + this.content + ", type=" + this.type + ')';
    }

    public AiChatBean(String role, String content, int i) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        this.role = role;
        this.content = content;
        this.type = i;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getType() {
        return this.type;
    }
}
