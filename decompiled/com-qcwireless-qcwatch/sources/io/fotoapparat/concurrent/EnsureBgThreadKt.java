package io.fotoapparat.concurrent;

import android.os.Looper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ensureBgThread.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"ensureBackgroundThread", "", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class EnsureBgThreadKt {
    public static final void ensureBackgroundThread() {
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            throw new IllegalThreadStateException("Operation should not run from main thread.");
        }
    }
}
