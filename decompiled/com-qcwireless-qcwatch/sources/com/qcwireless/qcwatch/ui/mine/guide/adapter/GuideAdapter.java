package com.qcwireless.qcwatch.ui.mine.guide.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.mine.guide.bean.GlideBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GuideAdapter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/guide/adapter/GuideAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/mine/guide/bean/GlideBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GuideAdapter extends BaseQuickAdapter<GlideBean, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GuideAdapter(Context context, List<GlideBean> data) {
        super(R.layout.recycleview_item_guide, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, GlideBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        holder.setText(R.id.item_title, item.getTitle());
        ImageView imageView = (ImageView) holder.getView(R.id.item_image);
        if (TextUtils.isEmpty(item.getImage())) {
            return;
        }
        XLog.i(FileUtils.INSTANCE.getGuideDirFile().getPath());
        Glide.with(getContext()).load(Uri.parse("file://" + FileUtils.INSTANCE.getAppRootFile(QCApplication.INSTANCE.getCONTEXT()).getPath() + '/' + item.getImage())).into(imageView);
    }
}
