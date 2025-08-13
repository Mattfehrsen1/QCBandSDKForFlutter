package com.qcwireless.qcwatch.ui.home.sport.bean;

import java.util.List;

/* loaded from: classes3.dex */
public class DataListTree<K, V> {
    private K mGroupItem;
    private List<V> mSubItem;

    public DataListTree(K groupItem, List<V> subItem) {
        this.mGroupItem = groupItem;
        this.mSubItem = subItem;
    }

    public K getGroupItem() {
        return this.mGroupItem;
    }

    public List<V> getSubItem() {
        return this.mSubItem;
    }
}
