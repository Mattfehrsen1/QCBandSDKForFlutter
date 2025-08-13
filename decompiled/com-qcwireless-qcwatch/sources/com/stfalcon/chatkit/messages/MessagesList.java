package com.stfalcon.chatkit.messages;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stfalcon.chatkit.commons.models.IMessage;

/* loaded from: classes4.dex */
public class MessagesList extends RecyclerView {
    public MessagesListStyle messagesListStyle;

    public MessagesList(Context context) {
        super(context);
    }

    public MessagesList(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
    }

    public MessagesList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseStyle(context, attrs);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        throw new IllegalArgumentException("You can't set adapter to MessagesList. Use #setAdapter(MessagesListAdapter) instead.");
    }

    public <MESSAGE extends IMessage> void setAdapter(MessagesListAdapter<MESSAGE> adapter) {
        setAdapter(adapter, true);
    }

    public <MESSAGE extends IMessage> void setAdapter(MessagesListAdapter<MESSAGE> adapter, boolean reverseLayout) {
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, reverseLayout);
        setItemAnimator(defaultItemAnimator);
        setLayoutManager(linearLayoutManager);
        adapter.setLayoutManager(linearLayoutManager);
        adapter.setStyle(this.messagesListStyle);
        addOnScrollListener(new RecyclerScrollMoreListener(linearLayoutManager, adapter));
        super.setAdapter((RecyclerView.Adapter) adapter);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        this.messagesListStyle = MessagesListStyle.parse(context, attrs);
    }

    public MessagesListStyle getListStyle() {
        return this.messagesListStyle;
    }
}
