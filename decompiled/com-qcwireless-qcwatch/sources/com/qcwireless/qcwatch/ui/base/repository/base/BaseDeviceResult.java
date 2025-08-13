package com.qcwireless.qcwatch.ui.base.repository.base;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: BaseDeviceResult.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/base/BaseDeviceResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "result", "", "errorCode", "", "t", "(ILjava/lang/Object;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface BaseDeviceResult<T> {
    void result(int errorCode, T t);
}
