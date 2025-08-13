package io.fotoapparat.result;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: WhenDoneListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0017\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/fotoapparat/result/WhenDoneListener;", ExifInterface.GPS_DIRECTION_TRUE, "", "whenDone", "", "it", "(Ljava/lang/Object;)V", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface WhenDoneListener<T> {
    void whenDone(T it);
}
