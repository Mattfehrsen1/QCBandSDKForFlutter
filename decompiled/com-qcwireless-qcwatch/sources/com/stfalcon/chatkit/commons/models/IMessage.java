package com.stfalcon.chatkit.commons.models;

import java.util.Date;

/* loaded from: classes4.dex */
public interface IMessage {
    Date getCreatedAt();

    String getCs();

    String getId();

    String getQ1();

    String getQ2();

    String getQ3();

    String getQ4();

    String getText();

    IUser getUser();
}
