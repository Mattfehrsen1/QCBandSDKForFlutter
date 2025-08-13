package com.qcwireless.qcwatch.ui.home.sport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class BaseCommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected int mItemLayoutId;
    protected LayoutInflater mLayoutInflater;

    public abstract void convert(CommonViewHolder holder, T item);

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public BaseCommonAdapter(LayoutInflater layoutInflater, List<T> datas, Context context, int itemLayoutId) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.mContext = context;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.mDatas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        List<T> list = this.mDatas;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = getHolder(position, convertView, parent);
        if (this.mDatas.size() > 0) {
            convert(holder, this.mDatas.get(position));
        }
        return holder.getConvertView();
    }

    private CommonViewHolder getHolder(int position, View convertView, ViewGroup parent) {
        return CommonViewHolder.get(convertView, this.mContext, parent, this.mItemLayoutId, position);
    }
}
