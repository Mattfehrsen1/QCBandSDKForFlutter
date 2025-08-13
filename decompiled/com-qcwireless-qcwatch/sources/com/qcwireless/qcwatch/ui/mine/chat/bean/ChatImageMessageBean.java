package com.qcwireless.qcwatch.ui.mine.chat.bean;

import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChatImageMessageBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010\u000eR\u001a\u0010'\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\f\"\u0004\b)\u0010\u000e¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatImageMessageBean;", "", "()V", "client", "", "getClient", "()I", "setClient", "(I)V", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "fromUser", "Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatImageMessageBean$FromUser;", "getFromUser", "()Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatImageMessageBean$FromUser;", "setFromUser", "(Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatImageMessageBean$FromUser;)V", "going", "getGoing", "setGoing", "hdVersion", "getHdVersion", "setHdVersion", BreakpointSQLiteKey.ID, "getId", "setId", "sendTime", "", "getSendTime", "()J", "setSendTime", "(J)V", "toContactId", "getToContactId", "setToContactId", "type", "getType", "setType", "FromUser", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChatImageMessageBean {
    private FromUser fromUser;
    private String hdVersion;
    private String id;
    private long sendTime;
    private String toContactId;
    private String type = PictureMimeType.MIME_TYPE_PREFIX_IMAGE;
    private String going = "going";
    private String content = "";
    private int client = 1;

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    public final String getGoing() {
        return this.going;
    }

    public final void setGoing(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.going = str;
    }

    public final long getSendTime() {
        return this.sendTime;
    }

    public final void setSendTime(long j) {
        this.sendTime = j;
    }

    public final String getToContactId() {
        return this.toContactId;
    }

    public final void setToContactId(String str) {
        this.toContactId = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content = str;
    }

    public final int getClient() {
        return this.client;
    }

    public final void setClient(int i) {
        this.client = i;
    }

    public final String getHdVersion() {
        return this.hdVersion;
    }

    public final void setHdVersion(String str) {
        this.hdVersion = str;
    }

    public final FromUser getFromUser() {
        return this.fromUser;
    }

    public final void setFromUser(FromUser fromUser) {
        this.fromUser = fromUser;
    }

    /* compiled from: ChatImageMessageBean.kt */
    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/bean/ChatImageMessageBean$FromUser;", "", "()V", "avatar", "", "getAvatar", "()Ljava/lang/String;", "setAvatar", "(Ljava/lang/String;)V", "displayName", "getDisplayName", "setDisplayName", BreakpointSQLiteKey.ID, "getId", "setId", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class FromUser {
        private String avatar;
        private String displayName;
        private String id;

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            this.id = str;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        public final void setDisplayName(String str) {
            this.displayName = str;
        }

        public final String getAvatar() {
            return this.avatar;
        }

        public final void setAvatar(String str) {
            this.avatar = str;
        }
    }
}
