package com.app.watch.keeping.callback;

import com.app.watch.keeping.Cactus;
import kotlin.Metadata;

/* compiled from: CactusCallback.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/app/watch/keeping/callback/CactusCallback;", "", "doWork", "", Cactus.CACTUS_TIMES, "", "onStop", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public interface CactusCallback {
    void doWork(int times);

    void onStop();
}
