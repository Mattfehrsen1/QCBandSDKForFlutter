package com.qcwireless.qcwatch.ui.home.drag.helper;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: DragRecyclerViewAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0002 !B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u000fH\u0017J\u001c\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\u0018\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\u000e\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\fR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter$ItemViewHolder;", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/IItemTouchHelperAdapter;", "list", "", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/ItemEntity;", "mDragListener", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/OnStartDragListener;", "(Ljava/util/List;Lcom/qcwireless/qcwatch/ui/home/drag/helper/OnStartDragListener;)V", "dataList", "mOnClickSwitchListener", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter$OnClickSwitchListener;", "moduleTypeMap", "", "", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "setOnClickSwitchListener", "onClickSwitchListener", "ItemViewHolder", "OnClickSwitchListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DragRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> implements IItemTouchHelperAdapter {
    private List<ItemEntity> dataList;
    private OnStartDragListener mDragListener;
    private OnClickSwitchListener mOnClickSwitchListener;
    private Map<Integer, String> moduleTypeMap;

    /* compiled from: DragRecyclerViewAdapter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter$OnClickSwitchListener;", "", "onClick", "", "position", "", "isChecked", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnClickSwitchListener {
        void onClick(int position, boolean isChecked);
    }

    public DragRecyclerViewAdapter(List<ItemEntity> list, OnStartDragListener mDragListener) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(mDragListener, "mDragListener");
        this.dataList = list;
        this.mDragListener = mDragListener;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.moduleTypeMap = linkedHashMap;
        linkedHashMap.put(1, GlobalKt.getString(R.string.qc_text_85));
        this.moduleTypeMap.put(2, GlobalKt.getString(R.string.qc_text_84));
        this.moduleTypeMap.put(3, GlobalKt.getString(R.string.qc_text_83));
        this.moduleTypeMap.put(7, GlobalKt.getString(R.string.qc_text_92));
        this.moduleTypeMap.put(5, GlobalKt.getString(R.string.qc_text_94));
        this.moduleTypeMap.put(4, GlobalKt.getString(R.string.qc_text_87));
        this.moduleTypeMap.put(9, GlobalKt.getString(R.string.qc_text_86));
        this.moduleTypeMap.put(10, GlobalKt.getString(R.string.qc_text_309));
        this.moduleTypeMap.put(6, GlobalKt.getString(R.string.qc_text_93));
        this.moduleTypeMap.put(12, GlobalKt.getString(R.string.qc_text_569));
        this.moduleTypeMap.put(13, GlobalKt.getString(R.string.qc_text_8060));
        this.moduleTypeMap.put(14, GlobalKt.getString(R.string.qc_text_6666561));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_item_rcv, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(parent.context).inf…_item_rcv, parent, false)");
        return new ItemViewHolder(this, viewInflate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.dataList.get(position);
        holder.getTitle().setText(this.moduleTypeMap.get(Integer.valueOf(((ItemEntity) objectRef.element).getModelType())));
        holder.getCheckBox().setChecked(((ItemEntity) objectRef.element).isChecked());
        if (this.mOnClickSwitchListener != null) {
            holder.getCheckBox().setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.drag.helper.DragRecyclerViewAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DragRecyclerViewAdapter.m668onBindViewHolder$lambda0(objectRef, this, position, view);
                }
            });
        }
        holder.getMenu().setOnTouchListener(new View.OnTouchListener() { // from class: com.qcwireless.qcwatch.ui.home.drag.helper.DragRecyclerViewAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return DragRecyclerViewAdapter.m669onBindViewHolder$lambda1(this.f$0, holder, view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m668onBindViewHolder$lambda0(Ref.ObjectRef item, DragRecyclerViewAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean zIsChecked = ((ItemEntity) item.element).isChecked();
        ((ItemEntity) item.element).setChecked(!zIsChecked);
        OnClickSwitchListener onClickSwitchListener = this$0.mOnClickSwitchListener;
        Intrinsics.checkNotNull(onClickSwitchListener);
        onClickSwitchListener.onClick(i, !zIsChecked);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-1, reason: not valid java name */
    public static final boolean m669onBindViewHolder$lambda1(DragRecyclerViewAdapter this$0, ItemViewHolder holder, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this$0.mDragListener.onStartDrag(holder);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override // com.qcwireless.qcwatch.ui.home.drag.helper.IItemTouchHelperAdapter
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(this.dataList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        this.mDragListener.onEndDrag(this.dataList);
    }

    @Override // com.qcwireless.qcwatch.ui.home.drag.helper.IItemTouchHelperAdapter
    public void onItemDismiss(int position) {
        this.dataList.remove(position);
        notifyItemRemoved(position);
    }

    public final void setOnClickSwitchListener(OnClickSwitchListener onClickSwitchListener) {
        Intrinsics.checkNotNullParameter(onClickSwitchListener, "onClickSwitchListener");
        this.mOnClickSwitchListener = onClickSwitchListener;
    }

    /* compiled from: DragRecyclerViewAdapter.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter$ItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/IItemTouchHelperViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter;Landroid/view/View;)V", "checkBox", "Landroidx/appcompat/widget/AppCompatCheckBox;", "getCheckBox", "()Landroidx/appcompat/widget/AppCompatCheckBox;", "menu", "Landroid/widget/ImageView;", "getMenu", "()Landroid/widget/ImageView;", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "onItemClear", "", "onItemSelected", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ItemViewHolder extends RecyclerView.ViewHolder implements IItemTouchHelperViewHolder {
        private final AppCompatCheckBox checkBox;
        private final ImageView menu;
        final /* synthetic */ DragRecyclerViewAdapter this$0;
        private final TextView title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemViewHolder(DragRecyclerViewAdapter dragRecyclerViewAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = dragRecyclerViewAdapter;
            View viewFindViewById = itemView.findViewById(R.id.item_list_text_textView);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.….item_list_text_textView)");
            this.title = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.item_list_menu_imageView);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.…item_list_menu_imageView)");
            this.menu = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.item_list_switchCompat);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.id.item_list_switchCompat)");
            this.checkBox = (AppCompatCheckBox) viewFindViewById3;
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final ImageView getMenu() {
            return this.menu;
        }

        public final AppCompatCheckBox getCheckBox() {
            return this.checkBox;
        }

        @Override // com.qcwireless.qcwatch.ui.home.drag.helper.IItemTouchHelperViewHolder
        public void onItemSelected() {
            this.itemView.setTranslationZ(10.0f);
        }

        @Override // com.qcwireless.qcwatch.ui.home.drag.helper.IItemTouchHelperViewHolder
        public void onItemClear() {
            this.itemView.setTranslationZ(0.0f);
        }
    }
}
