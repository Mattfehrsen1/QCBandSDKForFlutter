package org.greenrobot.eventbus;

/* loaded from: classes5.dex */
public interface MainThreadSupport {
    Poster createPoster(EventBus eventBus);

    boolean isMainThread();
}
