package androidx.camera.core.impl;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public interface Observable<T> {

    public interface Observer<T> {
        void onError(Throwable t);

        void onNewData(T value);
    }

    void addObserver(Executor executor, Observer<T> observer);

    ListenableFuture<T> fetchData();

    void removeObserver(Observer<T> observer);
}
