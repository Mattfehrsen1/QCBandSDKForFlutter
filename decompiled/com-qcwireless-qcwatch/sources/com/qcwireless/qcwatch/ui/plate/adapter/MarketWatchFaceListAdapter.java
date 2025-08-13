package com.qcwireless.qcwatch.ui.plate.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import coil.Coil;
import coil.request.ImageRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.base.view.LoadSendView;
import com.qcwireless.qcwatch.ui.plate.bean.MarketWatchFaceBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* compiled from: MarketWatchFaceListAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0002H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/adapter/MarketWatchFaceListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/plate/bean/MarketWatchFaceBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_fileNotExists", "Landroidx/lifecycle/MutableLiveData;", "fileNotExists", "Landroidx/lifecycle/LiveData;", "getFileNotExists", "()Landroidx/lifecycle/LiveData;", "convert", "", "holder", "item", "getItemId", "", "position", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MarketWatchFaceListAdapter extends BaseQuickAdapter<MarketWatchFaceBean, BaseViewHolder> {
    private final MutableLiveData<MarketWatchFaceBean> _fileNotExists;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarketWatchFaceListAdapter(Context context) {
        super(R.layout.recycleview_item_watch_face_market, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this._fileNotExists = new MutableLiveData<>();
    }

    public final LiveData<MarketWatchFaceBean> getFileNotExists() {
        return this._fileNotExists;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MarketWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        final ImageView imageView = (ImageView) holder.getView(R.id.image_watch_face);
        if (item.getWatchFace().getLocalImageUrl().length() > 0) {
            String str = "file://" + item.getWatchFace().getLocalImageUrl();
            if (StringsKt.endsWith$default(str, ".gif", false, 2, (Object) null)) {
                Glide.with(getContext()).asGif().load(str).fitCenter().signature(new ObjectKey(str + UserConfig.INSTANCE.getInstance().getHwVersion())).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into((RequestBuilder) new CustomTarget<GifDrawable>() { // from class: com.qcwireless.qcwatch.ui.plate.adapter.MarketWatchFaceListAdapter.convert.1
                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((GifDrawable) obj, (Transition<? super GifDrawable>) transition);
                    }

                    public void onResourceReady(GifDrawable resource, Transition<? super GifDrawable> transition) {
                        Intrinsics.checkNotNullParameter(resource, "resource");
                        resource.start();
                        imageView.setImageDrawable(resource);
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable placeholder) {
                        this._fileNotExists.postValue(item);
                    }
                });
            } else {
                Glide.with(getContext()).load(str).fitCenter().placeholder(R.mipmap.app_icon).dontAnimate().signature(new ObjectKey(str + UserConfig.INSTANCE.getInstance().getHwVersion())).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.qcwireless.qcwatch.ui.plate.adapter.MarketWatchFaceListAdapter.convert.2
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable placeholder) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                    }

                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        Intrinsics.checkNotNullParameter(resource, "resource");
                        imageView.setImageDrawable(resource);
                    }

                    @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        ImageView imageView2 = imageView;
                        Coil.imageLoader(imageView2.getContext()).enqueue(new ImageRequest.Builder(imageView2.getContext()).data(item.getWatchFace().getPreImageUrl()).target(imageView2).build());
                        this._fileNotExists.postValue(item);
                    }
                });
            }
        } else {
            Glide.with(getContext()).load(item.getWatchFace().getPreImageUrl()).fitCenter().signature(new ObjectKey(item.getWatchFace().getPreImageUrl() + UserConfig.INSTANCE.getInstance().getHwVersion())).into(imageView);
        }
        LoadSendView loadSendView = (LoadSendView) holder.getView(R.id.lsv_loading);
        if (item.getProgressBar() > 0) {
            ViewKt.visible(loadSendView);
            loadSendView.updateUI(item.getProgressBar() * 3.6f);
        } else {
            ViewKt.gone(loadSendView);
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        try {
            return Integer.parseInt((String) StringsKt.split$default((CharSequence) getItem(position).getWatchFace().getName(), new String[]{"."}, false, 0, 6, (Object) null).get(0), CharsKt.checkRadix(16));
        } catch (Exception unused) {
            return position;
        }
    }
}
