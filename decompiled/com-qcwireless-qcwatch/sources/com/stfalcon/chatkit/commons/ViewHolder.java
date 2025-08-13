package com.stfalcon.chatkit.commons;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public abstract class ViewHolder<DATA> extends RecyclerView.ViewHolder {
    public abstract void onBind(DATA data);

    public ViewHolder(View itemView) {
        super(itemView);
    }
}
