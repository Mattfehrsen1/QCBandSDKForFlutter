package androidx.camera.core;

import android.util.SparseArray;
import androidx.camera.core.impl.ImageProxyBundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class SettableImageProxyBundle implements ImageProxyBundle {
    private final List<Integer> mCaptureIdList;
    private String mTagBundleKey;
    final Object mLock = new Object();
    final SparseArray<CallbackToFutureAdapter.Completer<ImageProxy>> mCompleters = new SparseArray<>();
    private final SparseArray<ListenableFuture<ImageProxy>> mFutureResults = new SparseArray<>();
    private final List<ImageProxy> mOwnedImageProxies = new ArrayList();
    private boolean mClosed = false;

    SettableImageProxyBundle(List<Integer> captureIds, String tagBundleKey) {
        this.mTagBundleKey = null;
        this.mCaptureIdList = captureIds;
        this.mTagBundleKey = tagBundleKey;
        setup();
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    public ListenableFuture<ImageProxy> getImageProxy(int captureId) {
        ListenableFuture<ImageProxy> listenableFuture;
        synchronized (this.mLock) {
            if (this.mClosed) {
                throw new IllegalStateException("ImageProxyBundle already closed.");
            }
            listenableFuture = this.mFutureResults.get(captureId);
            if (listenableFuture == null) {
                throw new IllegalArgumentException("ImageProxyBundle does not contain this id: " + captureId);
            }
        }
        return listenableFuture;
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    public List<Integer> getCaptureIds() {
        return Collections.unmodifiableList(this.mCaptureIdList);
    }

    void addImageProxy(ImageProxy imageProxy) {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            Integer tag = imageProxy.getImageInfo().getTagBundle().getTag(this.mTagBundleKey);
            if (tag == null) {
                throw new IllegalArgumentException("CaptureId is null.");
            }
            CallbackToFutureAdapter.Completer<ImageProxy> completer = this.mCompleters.get(tag.intValue());
            if (completer != null) {
                this.mOwnedImageProxies.add(imageProxy);
                completer.set(imageProxy);
            } else {
                throw new IllegalArgumentException("ImageProxyBundle does not contain this id: " + tag);
            }
        }
    }

    void close() {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            Iterator<ImageProxy> it = this.mOwnedImageProxies.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.mOwnedImageProxies.clear();
            this.mFutureResults.clear();
            this.mCompleters.clear();
            this.mClosed = true;
        }
    }

    void reset() {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            Iterator<ImageProxy> it = this.mOwnedImageProxies.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.mOwnedImageProxies.clear();
            this.mFutureResults.clear();
            this.mCompleters.clear();
            setup();
        }
    }

    private void setup() {
        synchronized (this.mLock) {
            Iterator<Integer> it = this.mCaptureIdList.iterator();
            while (it.hasNext()) {
                final int iIntValue = it.next().intValue();
                this.mFutureResults.put(iIntValue, CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<ImageProxy>() { // from class: androidx.camera.core.SettableImageProxyBundle.1
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public Object attachCompleter(CallbackToFutureAdapter.Completer<ImageProxy> completer) {
                        synchronized (SettableImageProxyBundle.this.mLock) {
                            SettableImageProxyBundle.this.mCompleters.put(iIntValue, completer);
                        }
                        return "getImageProxy(id: " + iIntValue + ")";
                    }
                }));
            }
        }
    }
}
