package com.qcwireless.qcwatch.ui.device.music.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MenuSongBean.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/bean/MenuSongBean;", "", "()V", "menuId", "", "getMenuId", "()I", "setMenuId", "(I)V", "menuName", "", "getMenuName", "()Ljava/lang/String;", "setMenuName", "(Ljava/lang/String;)V", "songCounts", "getSongCounts", "setSongCounts", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MenuSongBean {
    private int menuId;
    private String menuName = "";
    private int songCounts;

    public final String getMenuName() {
        return this.menuName;
    }

    public final void setMenuName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.menuName = str;
    }

    public final int getMenuId() {
        return this.menuId;
    }

    public final void setMenuId(int i) {
        this.menuId = i;
    }

    public final int getSongCounts() {
        return this.songCounts;
    }

    public final void setSongCounts(int i) {
        this.songCounts = i;
    }
}
