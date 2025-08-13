package com.qcwireless.qcwatch.ui.base.repository.watchface;

import androidx.exifinterface.media.ExifInterface;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: WatchFaceState.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel$UiState;", "isSuccess", "retCode", "", "(Ljava/lang/Object;I)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFaceState<T> extends BaseViewModel.UiState<T> {
    /* JADX WARN: Illegal instructions before constructor call */
    public WatchFaceState() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        this(defaultConstructorMarker, 0, 3, defaultConstructorMarker);
    }

    public /* synthetic */ WatchFaceState(Object obj, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : obj, (i2 & 2) != 0 ? 0 : i);
    }

    public WatchFaceState(T t, int i) {
        super(false, t, i);
    }
}
