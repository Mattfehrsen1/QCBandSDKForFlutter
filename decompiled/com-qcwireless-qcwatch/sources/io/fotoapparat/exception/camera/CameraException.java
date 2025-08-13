package io.fotoapparat.exception.camera;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0016\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/fotoapparat/exception/camera/CameraException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public class CameraException extends RuntimeException {
    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CameraException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            th = null;
        }
        this(str, th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraException(String message, Throwable th) {
        super(message, th);
        Intrinsics.checkParameterIsNotNull(message, "message");
    }
}
