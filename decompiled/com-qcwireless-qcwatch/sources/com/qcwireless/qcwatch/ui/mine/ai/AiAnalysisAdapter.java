package com.qcwireless.qcwatch.ui.mine.ai;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.mine.ai.bean.AiDataModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiAnalysisAdapter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/AiAnalysisAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiDataModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AiAnalysisAdapter extends BaseQuickAdapter<AiDataModel, BaseViewHolder> {
    public AiAnalysisAdapter() {
        super(R.layout.recycleview_item_ai_analysis, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, AiDataModel item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_content, item.getContent());
        holder.setText(R.id.tv_suggestion, item.getSuggestion());
    }
}
