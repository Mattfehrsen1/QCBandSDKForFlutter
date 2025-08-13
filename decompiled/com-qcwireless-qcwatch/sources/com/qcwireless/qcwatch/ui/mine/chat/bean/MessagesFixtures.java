package com.qcwireless.qcwatch.ui.mine.chat.bean;

import com.qcwireless.qcwatch.ui.mine.chat.bean.Message;

/* loaded from: classes3.dex */
public final class MessagesFixtures extends FixturesData {
    private MessagesFixtures() {
        throw new AssertionError();
    }

    public static Message getTextMessage() {
        return getTextMessage(getRandomMessage());
    }

    public static Message getTextMessage(String text) {
        return new Message(getRandomId(), getUser(), text);
    }

    public static Message getTextMessageWithUrl(String text, String url) {
        Message message = new Message(getRandomId(), getUser(), text);
        message.setImage(new Message.Image(url));
        return message;
    }

    public static Message getTextMessageFromServer(String text) {
        return new Message(getRandomId(), new User("1", names.get(0), "", true), text);
    }

    public static Message getTextMessageFromServerWidthImage(String text) {
        Message message = new Message(getRandomId(), new User("1", names.get(0), "", true), text);
        message.setImage(new Message.Image(text));
        return message;
    }

    public static Message getTextMessageFromLocal(String text, String q1, String q2, String q3, String q4, String cs) {
        Message message = new Message(getRandomId(), new User("1", names.get(0), "", true), text, q1, q2, q3, q4, cs);
        message.setLocalMessage(new Message.LocalMessage(1));
        return message;
    }

    public static Message getTextRespMessageFromLocal(String text, String q1, String cs) {
        Message message = new Message(getRandomId(), new User("1", names.get(0), "", true), text, q1, cs);
        message.setLocalMessage(new Message.LocalMessage(2));
        return message;
    }

    public static Message getTextRespMessageFromLocalQuDao(String text, String q1, String q2, String q3, String cs) {
        Message message = new Message(getRandomId(), new User("1", names.get(0), "", true), text, q1, q2, q3, cs);
        message.setLocalMessage(new Message.LocalMessage(3));
        return message;
    }

    private static User getUser() {
        return new User("0", names.get(0), "", true);
    }
}
