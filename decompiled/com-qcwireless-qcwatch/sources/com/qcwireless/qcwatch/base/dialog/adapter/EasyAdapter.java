package com.qcwireless.qcwatch.base.dialog.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class EasyAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {
    private OnItemClickListener onItemClickListener;
    private OnItemMultiSelectListener onItemMultiSelectListener;
    private OnItemSingleSelectListener onItemSingleSelectListener;
    private SelectMode selectMode;
    private int singleSelected = 0;
    private List<Integer> multiSelected = new ArrayList();
    private int maxSelectedCount = -1;

    public interface OnItemClickListener {
        void onClicked(int itemPosition);
    }

    public interface OnItemMultiSelectListener {
        void onSelected(Operation operation, int itemPosition, boolean isSelected);
    }

    public interface OnItemSingleSelectListener {
        void onSelected(int itemPosition, boolean isSelected);
    }

    public enum Operation {
        ORDINARY,
        ALL_SELECTED,
        REVERSE_SELECTED,
        ALL_CANCEL,
        SET_MAX_COUNT
    }

    public enum SelectMode {
        CLICK,
        SINGLE_SELECT,
        MULTI_SELECT
    }

    public abstract void whenBindViewHolder(VH holder, int position);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemSingleSelectListener(OnItemSingleSelectListener onItemSingleSelectListener) {
        this.onItemSingleSelectListener = onItemSingleSelectListener;
    }

    public void setOnItemMultiSelectListener(OnItemMultiSelectListener onItemMultiSelectListener) {
        this.onItemMultiSelectListener = onItemMultiSelectListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VH holder, int position) {
        whenBindViewHolder(holder, position);
        holder.itemView.setTag(Integer.valueOf(position));
        holder.itemView.setOnClickListener(this);
        if (this.selectMode == SelectMode.CLICK) {
            holder.itemView.setSelected(false);
            return;
        }
        if (this.selectMode == SelectMode.SINGLE_SELECT) {
            if (this.singleSelected == position) {
                holder.itemView.setSelected(true);
                return;
            } else {
                holder.itemView.setSelected(false);
                return;
            }
        }
        if (this.selectMode == SelectMode.MULTI_SELECT) {
            if (this.multiSelected.contains(Integer.valueOf(position))) {
                holder.itemView.setSelected(true);
            } else {
                holder.itemView.setSelected(false);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int iIntValue = ((Integer) v.getTag()).intValue();
        if (this.selectMode == SelectMode.CLICK) {
            OnItemClickListener onItemClickListener = this.onItemClickListener;
            if (onItemClickListener != null) {
                onItemClickListener.onClicked(iIntValue);
                return;
            }
            return;
        }
        if (this.selectMode == SelectMode.SINGLE_SELECT) {
            OnItemSingleSelectListener onItemSingleSelectListener = this.onItemSingleSelectListener;
            if (onItemSingleSelectListener != null) {
                if (this.singleSelected == iIntValue) {
                    onItemSingleSelectListener.onSelected(iIntValue, false);
                } else {
                    this.singleSelected = iIntValue;
                    onItemSingleSelectListener.onSelected(iIntValue, true);
                }
            }
            notifyDataSetChanged();
            return;
        }
        if (this.selectMode == SelectMode.MULTI_SELECT) {
            if (this.maxSelectedCount <= 0 || this.multiSelected.size() < this.maxSelectedCount) {
                if (this.multiSelected.contains(Integer.valueOf(iIntValue))) {
                    this.multiSelected.remove(Integer.valueOf(iIntValue));
                    OnItemMultiSelectListener onItemMultiSelectListener = this.onItemMultiSelectListener;
                    if (onItemMultiSelectListener != null) {
                        onItemMultiSelectListener.onSelected(Operation.ORDINARY, iIntValue, false);
                    }
                } else {
                    this.multiSelected.add(Integer.valueOf(iIntValue));
                    OnItemMultiSelectListener onItemMultiSelectListener2 = this.onItemMultiSelectListener;
                    if (onItemMultiSelectListener2 != null) {
                        onItemMultiSelectListener2.onSelected(Operation.ORDINARY, iIntValue, true);
                    }
                }
            } else if (this.multiSelected.size() == this.maxSelectedCount && this.multiSelected.contains(Integer.valueOf(iIntValue))) {
                this.multiSelected.remove(Integer.valueOf(iIntValue));
                OnItemMultiSelectListener onItemMultiSelectListener3 = this.onItemMultiSelectListener;
                if (onItemMultiSelectListener3 != null) {
                    onItemMultiSelectListener3.onSelected(Operation.ORDINARY, iIntValue, false);
                }
            }
            notifyDataSetChanged();
        }
    }

    public void setSelectMode(SelectMode selectMode) {
        this.selectMode = selectMode;
        notifyDataSetChanged();
    }

    public SelectMode getSelectMode() {
        return this.selectMode;
    }

    public void setSelected(int... itemPositions) {
        this.multiSelected.clear();
        if (this.selectMode == SelectMode.SINGLE_SELECT) {
            int i = itemPositions[0];
            this.singleSelected = i;
            OnItemSingleSelectListener onItemSingleSelectListener = this.onItemSingleSelectListener;
            if (onItemSingleSelectListener != null) {
                onItemSingleSelectListener.onSelected(i, true);
            }
        } else {
            for (int i2 : itemPositions) {
                this.multiSelected.add(Integer.valueOf(i2));
                OnItemMultiSelectListener onItemMultiSelectListener = this.onItemMultiSelectListener;
                if (onItemMultiSelectListener != null) {
                    onItemMultiSelectListener.onSelected(Operation.ORDINARY, i2, true);
                }
            }
        }
        notifyDataSetChanged();
    }

    public int getSingleSelected() {
        return this.singleSelected;
    }

    public void clearSelected() {
        if (this.selectMode == SelectMode.MULTI_SELECT) {
            this.multiSelected.clear();
            OnItemMultiSelectListener onItemMultiSelectListener = this.onItemMultiSelectListener;
            if (onItemMultiSelectListener != null) {
                onItemMultiSelectListener.onSelected(Operation.ALL_CANCEL, -1, false);
            }
            notifyDataSetChanged();
        }
    }

    public int getSingleSelectedPosition() {
        return this.singleSelected;
    }

    public List<Integer> getMultiSelectedPosition() {
        return this.multiSelected;
    }

    public void setMaxSelectedCount(int maxSelectedCount) {
        if (maxSelectedCount < this.multiSelected.size()) {
            this.multiSelected.clear();
        }
        this.maxSelectedCount = maxSelectedCount;
        OnItemMultiSelectListener onItemMultiSelectListener = this.onItemMultiSelectListener;
        if (onItemMultiSelectListener != null) {
            onItemMultiSelectListener.onSelected(Operation.SET_MAX_COUNT, -1, false);
        }
        notifyDataSetChanged();
    }

    public int getMaxSelectedCount() {
        return this.maxSelectedCount;
    }

    public void selectAll() {
        if (this.maxSelectedCount <= 0) {
            this.multiSelected.clear();
            for (int i = 0; i < getItemCount(); i++) {
                this.multiSelected.add(Integer.valueOf(i));
            }
            OnItemMultiSelectListener onItemMultiSelectListener = this.onItemMultiSelectListener;
            if (onItemMultiSelectListener != null) {
                onItemMultiSelectListener.onSelected(Operation.ALL_SELECTED, -1, false);
            }
            notifyDataSetChanged();
        }
    }

    public void reverseSelected() {
        if (this.maxSelectedCount <= 0) {
            OnItemMultiSelectListener onItemMultiSelectListener = this.onItemMultiSelectListener;
            if (onItemMultiSelectListener != null) {
                onItemMultiSelectListener.onSelected(Operation.REVERSE_SELECTED, -1, false);
            }
            for (int i = 0; i < getItemCount(); i++) {
                if (this.multiSelected.contains(Integer.valueOf(i))) {
                    this.multiSelected.remove(Integer.valueOf(i));
                } else {
                    this.multiSelected.add(Integer.valueOf(i));
                }
            }
            notifyDataSetChanged();
        }
    }

    public boolean isSelected(int position) {
        if (this.selectMode == SelectMode.SINGLE_SELECT) {
            return position == this.singleSelected;
        }
        if (this.selectMode == SelectMode.MULTI_SELECT) {
            return this.multiSelected.contains(Integer.valueOf(position));
        }
        return false;
    }
}
