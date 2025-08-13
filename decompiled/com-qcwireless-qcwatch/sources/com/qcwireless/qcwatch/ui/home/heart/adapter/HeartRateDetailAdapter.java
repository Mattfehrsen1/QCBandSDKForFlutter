package com.qcwireless.qcwatch.ui.home.heart.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.home.heart.bean.HeartRateDetailBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HeartRateDetailAdapter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/adapter/HeartRateDetailAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/home/heart/bean/HeartRateDetailBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartRateDetailAdapter extends BaseQuickAdapter<HeartRateDetailBean, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeartRateDetailAdapter(Context context, List<HeartRateDetailBean> data) {
        super(R.layout.recycle_view_heart_rate_detail, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, HeartRateDetailBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        DateUtil dateUtil = new DateUtil(item.getTimestamp(), true);
        holder.setText(R.id.heart_rate_value, String.valueOf(item.getValue()));
        holder.setText(R.id.heart_rate_time, dateUtil.getHHmmDate());
    }
}
