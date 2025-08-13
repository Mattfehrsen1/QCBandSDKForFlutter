package com.stfalcon.chatkit.commons.models;

/* loaded from: classes4.dex */
public interface MessageContentType extends IMessage {

    public interface Image extends IMessage {
        String getImageUrl();
    }

    public interface LocalMessage extends IMessage {
        int localMessage();
    }
}
