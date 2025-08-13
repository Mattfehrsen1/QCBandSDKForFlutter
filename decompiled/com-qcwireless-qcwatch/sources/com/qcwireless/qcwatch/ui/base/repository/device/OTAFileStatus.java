package com.qcwireless.qcwatch.ui.base.repository.device;

import kotlin.Metadata;

/* compiled from: OTAFileStatus.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/OTAFileStatus;", "", "()V", "currIndex", "", "getCurrIndex", "()I", "setCurrIndex", "(I)V", "fileExists", "", "getFileExists", "()Z", "setFileExists", "(Z)V", "otaSuccess", "getOtaSuccess", "setOtaSuccess", "progressBar", "getProgressBar", "setProgressBar", "totalIndex", "getTotalIndex", "setTotalIndex", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OTAFileStatus {
    private int currIndex;
    private boolean fileExists = true;
    private boolean otaSuccess;
    private int progressBar;
    private int totalIndex;

    public final int getCurrIndex() {
        return this.currIndex;
    }

    public final void setCurrIndex(int i) {
        this.currIndex = i;
    }

    public final int getTotalIndex() {
        return this.totalIndex;
    }

    public final void setTotalIndex(int i) {
        this.totalIndex = i;
    }

    public final int getProgressBar() {
        return this.progressBar;
    }

    public final void setProgressBar(int i) {
        this.progressBar = i;
    }

    public final boolean getOtaSuccess() {
        return this.otaSuccess;
    }

    public final void setOtaSuccess(boolean z) {
        this.otaSuccess = z;
    }

    public final boolean getFileExists() {
        return this.fileExists;
    }

    public final void setFileExists(boolean z) {
        this.fileExists = z;
    }
}
