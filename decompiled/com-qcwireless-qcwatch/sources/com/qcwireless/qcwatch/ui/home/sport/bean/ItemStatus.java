package com.qcwireless.qcwatch.ui.home.sport.bean;

/* loaded from: classes3.dex */
public class ItemStatus {
    public static final int VIEW_TYPE_GROUP_ITEM = 0;
    public static final int VIEW_TYPE_SUB_ITEM = 1;
    private int mGroupItemIndex;
    private int mSubItemIndex = -1;
    private int mViewType;

    public int getViewType() {
        return this.mViewType;
    }

    public void setViewType(int viewType) {
        this.mViewType = viewType;
    }

    public int getGroupItemIndex() {
        return this.mGroupItemIndex;
    }

    public void setGroupItemIndex(int groupItemIndex) {
        this.mGroupItemIndex = groupItemIndex;
    }

    public int getSubItemIndex() {
        return this.mSubItemIndex;
    }

    public void setSubItemIndex(int subItemIndex) {
        this.mSubItemIndex = subItemIndex;
    }
}
