package com.qcwireless.qcwatch.ui.base.view.pop;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopup;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WPopupAdapter.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001&B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\u001c\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J\u0014\u0010\u001a\u001a\u00020\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0007J\u000e\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0007J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u000eJ\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0007J\u000e\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0007R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupAdapter$ViewHolder;", "popup", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup;", "(Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup;)V", "direction", "", "Ljava/lang/Integer;", "drawablePadding", "mData", "", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupModel;", "mWItemClickListener", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder$OnItemClickListener;", "textColor", "textSize", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "p1", "setData", "data", "setDirection", "d", "setDrawablePadding", "padding", "setItemClickListener", "wItemClickListener", "setTextColor", TypedValues.Custom.S_COLOR, "setTextSize", "size", "ViewHolder", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WPopupAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Integer direction;
    private int drawablePadding;
    private List<WPopupModel> mData;
    private WPopup.Builder.OnItemClickListener mWItemClickListener;
    private final WPopup popup;
    private int textColor;
    private int textSize;

    public WPopupAdapter(WPopup popup) {
        Intrinsics.checkNotNullParameter(popup, "popup");
        this.popup = popup;
        this.textColor = Color.parseColor("#ffffff");
        this.textSize = 14;
        this.drawablePadding = 10;
    }

    public final void setData(List<WPopupModel> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.mData = data;
        notifyDataSetChanged();
    }

    public final void setDirection(int d) {
        this.direction = Integer.valueOf(d);
        notifyDataSetChanged();
    }

    public final void setTextColor(int color) {
        this.textColor = color;
        notifyDataSetChanged();
    }

    public final void setTextSize(int size) {
        this.textSize = size;
        notifyDataSetChanged();
    }

    public final void setDrawablePadding(int padding) {
        this.drawablePadding = padding;
        notifyDataSetChanged();
    }

    public final void setItemClickListener(WPopup.Builder.OnItemClickListener wItemClickListener) {
        Intrinsics.checkNotNullParameter(wItemClickListener, "wItemClickListener");
        this.mWItemClickListener = wItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int p1) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_common_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WPopupModel> list = this.mData;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView tv = holder.getTv();
        List<WPopupModel> list = this.mData;
        Intrinsics.checkNotNull(list);
        tv.setText(list.get(position).getText());
        holder.getTv().setTextColor(this.textColor);
        holder.getTv().setTextSize(this.textSize);
        holder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.WPopupAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WPopupAdapter.m335onBindViewHolder$lambda0(this.f$0, holder, position, view);
            }
        });
        List<WPopupModel> list2 = this.mData;
        Intrinsics.checkNotNull(list2);
        if (list2.get(position).getImgRes() != -1) {
            Resources resources = this.popup.getContext().getResources();
            List<WPopupModel> list3 = this.mData;
            Intrinsics.checkNotNull(list3);
            Drawable drawable = resources.getDrawable(list3.get(position).getImgRes());
            Integer num = this.direction;
            if (num != null && num.intValue() == -3) {
                holder.getTv().setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, drawable);
            } else if (num != null && num.intValue() == -4) {
                holder.getTv().setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
            } else if (num != null && num.intValue() == -1) {
                holder.getTv().setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            } else if (num != null && num.intValue() == -2) {
                holder.getTv().setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            }
            TextView tv2 = holder.getTv();
            Utils utils = Utils.INSTANCE;
            Context context = holder.getTv().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "holder.tv.context");
            tv2.setCompoundDrawablePadding(utils.dp2px(context, this.drawablePadding));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m335onBindViewHolder$lambda0(WPopupAdapter this$0, ViewHolder holder, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        if (this$0.mWItemClickListener != null) {
            this$0.popup.dismiss();
            WPopup.Builder.OnItemClickListener onItemClickListener = this$0.mWItemClickListener;
            Intrinsics.checkNotNull(onItemClickListener);
            onItemClickListener.onItemClick(holder.getView(), i);
        }
    }

    /* compiled from: WPopupAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupAdapter;Landroid/view/View;)V", "tv", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getTv", "()Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ WPopupAdapter this$0;
        private final TextView tv;
        private final View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(WPopupAdapter wPopupAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = wPopupAdapter;
            this.view = view;
            this.tv = (TextView) view.findViewById(R.id.mTvItem);
        }

        public final View getView() {
            return this.view;
        }

        public final TextView getTv() {
            return this.tv;
        }
    }
}
