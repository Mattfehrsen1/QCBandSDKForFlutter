package com.qcwireless.qcwatch.ui.mine.chat.bean;

import com.stfalcon.chatkit.commons.models.IUser;

/* loaded from: classes3.dex */
public class User implements IUser {
    private String avatar;
    private String id;
    private String name;
    private boolean online;

    public User(String id, String name, String avatar, boolean online) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }

    @Override // com.stfalcon.chatkit.commons.models.IUser
    public String getId() {
        return this.id;
    }

    @Override // com.stfalcon.chatkit.commons.models.IUser
    public String getName() {
        return this.name;
    }

    @Override // com.stfalcon.chatkit.commons.models.IUser
    public String getAvatar() {
        return this.avatar;
    }

    public boolean isOnline() {
        return this.online;
    }
}
