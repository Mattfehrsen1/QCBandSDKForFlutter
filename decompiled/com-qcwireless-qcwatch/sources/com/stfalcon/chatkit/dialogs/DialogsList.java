package com.stfalcon.chatkit.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stfalcon.chatkit.commons.models.IDialog;

/* loaded from: classes4.dex */
public class DialogsList extends RecyclerView {
    private DialogListStyle dialogStyle;

    public DialogsList(Context context) {
        super(context);
    }

    public DialogsList(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
    }

    public DialogsList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseStyle(context, attrs);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        setLayoutManager(linearLayoutManager);
        setItemAnimator(defaultItemAnimator);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        throw new IllegalArgumentException("You can't set adapter to DialogsList. Use #setAdapter(DialogsListAdapter) instead.");
    }

    public <DIALOG extends IDialog<?>> void setAdapter(DialogsListAdapter<DIALOG> adapter) {
        setAdapter(adapter, false);
    }

    public <DIALOG extends IDialog<?>> void setAdapter(DialogsListAdapter<DIALOG> adapter, boolean reverseLayout) {
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, reverseLayout);
        setItemAnimator(defaultItemAnimator);
        setLayoutManager(linearLayoutManager);
        adapter.setStyle(this.dialogStyle);
        super.setAdapter((RecyclerView.Adapter) adapter);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        this.dialogStyle = DialogListStyle.parse(context, attrs);
    }
}
