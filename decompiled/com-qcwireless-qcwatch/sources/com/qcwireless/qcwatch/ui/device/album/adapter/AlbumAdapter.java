package com.qcwireless.qcwatch.ui.device.album.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.device.album.bean.AlbumBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0002H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/album/adapter/AlbumAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/device/album/bean/AlbumBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "height", "", "width", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumAdapter extends BaseQuickAdapter<AlbumBean, BaseViewHolder> {
    private int height;
    private int width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumAdapter(Context context, List<AlbumBean> data) {
        super(R.layout.recycleview_item_album, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.width = 368;
        this.height = 448;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, AlbumBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) holder.getView(R.id.image_select);
        ImageView imageView2 = (ImageView) holder.getView(R.id.image_result);
        TextView textView = (TextView) holder.getView(R.id.tv_status);
        TextView textView2 = (TextView) holder.getView(R.id.tv_progress_value);
        ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progress_style);
        Glide.with(getContext()).load("file://" + item.getPath()).centerCrop().override(this.width, this.height).into(imageView);
        int type = item.getType();
        if (type == -1) {
            textView.setText(GlobalKt.getString(R.string.qc_text_582));
            imageView2.setImageResource(R.mipmap.album_fail);
            ViewKt.visible(imageView2);
        } else if (type == 1) {
            textView.setText(GlobalKt.getString(R.string.qc_text_583));
            ViewKt.gone(imageView2);
        } else if (type == 100) {
            textView.setText(GlobalKt.getString(R.string.qc_text_581));
            imageView2.setImageResource(R.mipmap.album_success);
            ViewKt.visible(imageView2);
        } else {
            textView.setText(GlobalKt.getString(R.string.qc_text_584));
            ViewKt.gone(imageView2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(item.getProgress());
        sb.append('%');
        textView2.setText(sb.toString());
        progressBar.setProgress(item.getProgress());
    }
}
