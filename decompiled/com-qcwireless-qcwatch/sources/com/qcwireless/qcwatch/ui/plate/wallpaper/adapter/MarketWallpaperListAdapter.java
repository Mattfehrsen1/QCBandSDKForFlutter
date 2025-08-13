package com.qcwireless.qcwatch.ui.plate.wallpaper.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qc_utils.date.LanguageUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.plate.bean.DialBean;
import com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.MarketWallpaperListAdapter;
import com.squareup.moshi.JsonAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MarketWallpaperListAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0002H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/adapter/MarketWallpaperListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/plate/bean/DialBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_fileNotExists", "Landroidx/lifecycle/MutableLiveData;", "fileNotExists", "Landroidx/lifecycle/LiveData;", "getFileNotExists", "()Landroidx/lifecycle/LiveData;", "map", "", "", "parentFile", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MarketWallpaperListAdapter extends BaseQuickAdapter<DialBean, BaseViewHolder> {
    private final MutableLiveData<DialBean> _fileNotExists;
    private Map<String, String> map;
    private final String parentFile;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarketWallpaperListAdapter(Context context) {
        LinkedHashMap linkedHashMap;
        super(R.layout.recycleview_item_wallpaper_market, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
        String absolutePath = FileUtils.INSTANCE.getWatchWallpaperDirFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "FileUtils.getWatchWallpaperDirFile().absolutePath");
        this.parentFile = absolutePath;
        String skusGoogle = UserConfig.INSTANCE.getInstance().getSkusGoogle();
        if (!(skusGoogle.length() > 0)) {
            linkedHashMap = new LinkedHashMap();
        } else {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<Map<String, String>>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.MarketWallpaperListAdapter$special$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            linkedHashMap = (Map) jsonAdapterAdapter.fromJson(skusGoogle);
        }
        this.map = linkedHashMap;
        this._fileNotExists = new MutableLiveData<>();
    }

    public final LiveData<DialBean> getFileNotExists() {
        return this._fileNotExists;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final DialBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        final ImageView imageView = (ImageView) holder.getView(R.id.image_watch_face);
        TextView textView = (TextView) holder.getView(R.id.tv_price);
        TextView textView2 = (TextView) holder.getView(R.id.tv_desc);
        ImageView imageView2 = (ImageView) holder.getView(R.id.image_bg);
        if (UserConfig.INSTANCE.getInstance().getRoundScreen()) {
            imageView2.setBackgroundResource(R.drawable.bg_rect_corner_20_watchface_round);
        } else {
            imageView2.setBackgroundResource(R.drawable.bg_rect_corner_20_watchface);
        }
        textView.setVisibility(8);
        if (LanguageUtil.isChina()) {
            textView2.setText(item.getNameDesc());
            ViewKt.visible(textView2);
        } else {
            ViewKt.gone(textView2);
            textView2.setText("");
        }
        String preImageUrl = item.getPreImageUrl();
        String str = PictureMimeType.PNG;
        if (!StringsKt.endsWith$default(preImageUrl, PictureMimeType.PNG, false, 2, (Object) null)) {
            str = ".gif";
        }
        String str2 = ((String) StringsKt.split$default((CharSequence) item.getName(), new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + str;
        if (FileUtils.INSTANCE.fileExists(this.parentFile + '/' + str2)) {
            Glide.with(getContext()).load("file://" + this.parentFile + '/' + str2).fitCenter().error(R.mipmap.dial_default).signature(new ObjectKey(str2)).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.MarketWallpaperListAdapter.convert.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable placeholder) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    if (resource instanceof GifDrawable) {
                        imageView.setImageDrawable(resource);
                        ((GifDrawable) resource).start();
                    } else {
                        imageView.setImageDrawable(resource);
                    }
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    this._fileNotExists.postValue(item);
                    try {
                        final MarketWallpaperListAdapter marketWallpaperListAdapter = this;
                        final DialBean dialBean = item;
                        final ImageView imageView3 = imageView;
                        ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.MarketWallpaperListAdapter$convert$1$onLoadFailed$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(MarketWallpaperListAdapter.AnonymousClass1 anonymousClass1) {
                                invoke2(anonymousClass1);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(MarketWallpaperListAdapter.AnonymousClass1 ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                Glide.with(marketWallpaperListAdapter.getContext()).load(dialBean.getPreImageUrl()).fitCenter().signature(new ObjectKey(dialBean.getName())).into(imageView3);
                            }
                        });
                    } catch (Exception unused) {
                    }
                }
            });
            return;
        }
        Glide.with(getContext()).load(item.getPreImageUrl()).fitCenter().error(R.mipmap.dial_default).signature(new ObjectKey(item.getName())).into(imageView);
    }
}
