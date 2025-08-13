package com.qcwireless.qcwatch.ui.mine.chat.bean;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;
import java.util.Date;

/* loaded from: classes3.dex */
public class Message implements IMessage, MessageContentType.Image, MessageContentType, MessageContentType.LocalMessage {
    private Date createdAt;
    private String cs;
    private String id;
    private Image image;
    LocalMessage localMessage;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String text;
    private User user;
    private Voice voice;

    public String getStatus() {
        return "Sent";
    }

    public Message(String id, User user, String text) {
        this(id, user, text, new Date());
    }

    public Message(String id, User user, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Message(String id, User user, String text, String q1, String q2, String q3, String q4, String cs) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.cs = cs;
        this.createdAt = new Date();
    }

    public Message(String id, User user, String text, String q1, String cs) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.q1 = q1;
        this.cs = cs;
        this.createdAt = new Date();
    }

    public Message(String id, User user, String text, String q1, String q2, String q3, String cs) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.cs = cs;
        this.createdAt = new Date();
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getId() {
        return this.id;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getText() {
        return this.text;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public User getUser() {
        return this.user;
    }

    @Override // com.stfalcon.chatkit.commons.models.MessageContentType.Image
    public String getImageUrl() {
        Image image = this.image;
        if (image == null) {
            return null;
        }
        return image.url;
    }

    public Voice getVoice() {
        return this.voice;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getQ1() {
        return this.q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getQ2() {
        return this.q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getQ3() {
        return this.q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getQ4() {
        return this.q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    @Override // com.stfalcon.chatkit.commons.models.IMessage
    public String getCs() {
        return this.cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public LocalMessage getLocalMessage() {
        return this.localMessage;
    }

    public void setLocalMessage(LocalMessage localMessage) {
        this.localMessage = localMessage;
    }

    @Override // com.stfalcon.chatkit.commons.models.MessageContentType.LocalMessage
    public int localMessage() {
        LocalMessage localMessage = this.localMessage;
        if (localMessage == null) {
            return 0;
        }
        return localMessage.result;
    }

    public static class Image {
        private String url;

        public Image(String url) {
            this.url = url;
        }
    }

    public static class LocalMessage {
        private int result;

        public LocalMessage(int result) {
            this.result = result;
        }
    }

    public static class Voice {
        private int duration;
        private String url;

        public Voice(String url, int duration) {
            this.url = url;
            this.duration = duration;
        }

        public String getUrl() {
            return this.url;
        }

        public int getDuration() {
            return this.duration;
        }
    }
}
