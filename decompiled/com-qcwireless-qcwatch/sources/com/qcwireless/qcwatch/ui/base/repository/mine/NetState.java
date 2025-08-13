package com.qcwireless.qcwatch.ui.base.repository.mine;

import androidx.exifinterface.media.ExifInterface;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NetState.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B/\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel$UiState;", "isLoading", "", "isSuccess", "retCode", "", "enableLoginButton", "(ZLjava/lang/Object;IZ)V", "getEnableLoginButton", "()Z", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetState<T> extends BaseViewModel.UiState<T> {
    private final boolean enableLoginButton;

    public NetState() {
        this(false, null, 0, false, 15, null);
    }

    public /* synthetic */ NetState(boolean z, Object obj, int i, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? false : z2);
    }

    public final boolean getEnableLoginButton() {
        return this.enableLoginButton;
    }

    public NetState(boolean z, T t, int i, boolean z2) {
        super(z, t, i);
        this.enableLoginButton = z2;
    }
}
