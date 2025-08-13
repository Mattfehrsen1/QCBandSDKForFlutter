package com.qcwireless.qcwatch.ui.home.sport.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes3.dex */
public class CommonViewHolder {
    private View mConvertView;
    private SparseArray<View> mWidgetViews = new SparseArray<>();

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        View viewInflate = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView = viewInflate;
        viewInflate.setTag(this);
    }

    public static CommonViewHolder get(View convertView, Context context, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int i) {
        T t = (T) this.mWidgetViews.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.mConvertView.findViewById(i);
        this.mWidgetViews.put(i, t2);
        return t2;
    }

    public void setText(int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
    }

    public void setImageResource(int viewId, int drawableId) {
        ((ImageView) getView(viewId)).setImageResource(drawableId);
    }

    public void setBackgroundRes(int viewId, int drawableId) {
        ((ImageView) getView(viewId)).setBackgroundResource(drawableId);
    }

    public void setImageBitmap(int viewId, Bitmap bm) {
        setImageBackground(viewId, new BitmapDrawable(bm));
    }

    public void setImageBackground(int viewId, Drawable drawable) {
        ((ImageView) getView(viewId)).setBackground(drawable);
    }

    public View getConvertView() {
        return this.mConvertView;
    }
}
