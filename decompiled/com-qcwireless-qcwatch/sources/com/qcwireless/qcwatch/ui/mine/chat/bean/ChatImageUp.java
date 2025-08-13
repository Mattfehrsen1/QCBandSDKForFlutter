package com.qcwireless.qcwatch.ui.mine.chat.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChatImageUp.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatImageUp;", "", "retCode", "", "message", "", "data", "(ILjava/lang/String;Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "getMessage", "getRetCode", "()I", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ChatImageUp {
    private final String data;
    private final String message;
    private final int retCode;

    public static /* synthetic */ ChatImageUp copy$default(ChatImageUp chatImageUp, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = chatImageUp.retCode;
        }
        if ((i2 & 2) != 0) {
            str = chatImageUp.message;
        }
        if ((i2 & 4) != 0) {
            str2 = chatImageUp.data;
        }
        return chatImageUp.copy(i, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getRetCode() {
        return this.retCode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component3, reason: from getter */
    public final String getData() {
        return this.data;
    }

    public final ChatImageUp copy(int retCode, String message, String data) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        return new ChatImageUp(retCode, message, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChatImageUp)) {
            return false;
        }
        ChatImageUp chatImageUp = (ChatImageUp) other;
        return this.retCode == chatImageUp.retCode && Intrinsics.areEqual(this.message, chatImageUp.message) && Intrinsics.areEqual(this.data, chatImageUp.data);
    }

    public int hashCode() {
        return (((this.retCode * 31) + this.message.hashCode()) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "ChatImageUp(retCode=" + this.retCode + ", message=" + this.message + ", data=" + this.data + ')';
    }

    public ChatImageUp(int i, String message, String data) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        this.retCode = i;
        this.message = message;
        this.data = data;
    }

    public final String getData() {
        return this.data;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getRetCode() {
        return this.retCode;
    }
}
