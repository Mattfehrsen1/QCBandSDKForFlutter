package com.stfalcon.chatkit.messages;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.ViewHolder;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.RecyclerScrollMoreListener;
import com.stfalcon.chatkit.utils.DateFormatter;
import io.reactivex.internal.operators.flowable.FlowableReplay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MessagesListAdapter<MESSAGE extends IMessage> extends RecyclerView.Adapter<ViewHolder> implements RecyclerScrollMoreListener.OnLoadMoreListener {
    protected static boolean isSelectionModeEnabled;
    private DateFormatter.Formatter dateHeadersFormatter;
    private MessageHolders holders;
    private ImageLoader imageLoader;
    protected List<Wrapper> items;
    private RecyclerView.LayoutManager layoutManager;
    private OnLoadMoreListener loadMoreListener;
    private MessagesListStyle messagesListStyle;
    private OnMessageClickListener<MESSAGE> onMessageClickListener;
    private OnMessageLongClickListener<MESSAGE> onMessageLongClickListener;
    private OnMessageViewClickListener<MESSAGE> onMessageViewClickListener;
    private OnMessageViewLongClickListener<MESSAGE> onMessageViewLongClickListener;
    private int selectedItemsCount;
    private SelectionListener selectionListener;
    private String senderId;
    private SparseArray<OnMessageViewClickListener> viewClickListenersArray;

    public interface Formatter<MESSAGE> {
        String format(MESSAGE message);
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int page, int totalItemsCount);
    }

    public interface OnMessageClickListener<MESSAGE extends IMessage> {
        void onMessageClick(MESSAGE message);
    }

    public interface OnMessageLongClickListener<MESSAGE extends IMessage> {
        void onMessageLongClick(MESSAGE message);
    }

    public interface OnMessageViewClickListener<MESSAGE extends IMessage> {
        void onMessageViewClick(View view, MESSAGE message);
    }

    public interface OnMessageViewLongClickListener<MESSAGE extends IMessage> {
        void onMessageViewLongClick(View view, MESSAGE message);
    }

    public interface SelectionListener {
        void onSelectionChanged(int count);
    }

    public MessagesListAdapter(String senderId, ImageLoader imageLoader) {
        this(senderId, new MessageHolders(), imageLoader);
    }

    public MessagesListAdapter(String senderId, MessageHolders holders, ImageLoader imageLoader) {
        this.viewClickListenersArray = new SparseArray<>();
        this.senderId = senderId;
        this.holders = holders;
        this.imageLoader = imageLoader;
        this.items = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.holders.getHolder(parent, viewType, this.messagesListStyle);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wrapper wrapper = this.items.get(position);
        this.holders.bind(holder, wrapper.item, wrapper.isSelected, this.imageLoader, getMessageClickListener(wrapper), getMessageLongClickListener(wrapper), this.dateHeadersFormatter, this.viewClickListenersArray);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.holders.getViewType(this.items.get(position).item, this.senderId);
    }

    @Override // com.stfalcon.chatkit.messages.RecyclerScrollMoreListener.OnLoadMoreListener
    public void onLoadMore(int page, int total) {
        OnLoadMoreListener onLoadMoreListener = this.loadMoreListener;
        if (onLoadMoreListener != null) {
            onLoadMoreListener.onLoadMore(page, total);
        }
    }

    @Override // com.stfalcon.chatkit.messages.RecyclerScrollMoreListener.OnLoadMoreListener
    public int getMessagesCount() {
        Iterator<Wrapper> it = this.items.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().item instanceof IMessage) {
                i++;
            }
        }
        return i;
    }

    public void addToStart(MESSAGE message, boolean scroll) {
        boolean z = !isPreviousSameDate(0, message.getCreatedAt());
        if (z) {
            this.items.add(0, new Wrapper(message.getCreatedAt()));
        }
        this.items.add(0, new Wrapper(message));
        notifyItemRangeInserted(0, z ? 2 : 1);
        RecyclerView.LayoutManager layoutManager = this.layoutManager;
        if (layoutManager == null || !scroll) {
            return;
        }
        layoutManager.scrollToPosition(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addToEnd(List<MESSAGE> messages, boolean reverse) {
        if (messages.isEmpty()) {
            return;
        }
        if (reverse) {
            Collections.reverse(messages);
        }
        if (!this.items.isEmpty()) {
            int size = this.items.size() - 1;
            if (DateFormatter.isSameDay(messages.get(0).getCreatedAt(), (Date) this.items.get(size).item)) {
                this.items.remove(size);
                notifyItemRemoved(size);
            }
        }
        int size2 = this.items.size();
        generateDateHeaders(messages);
        notifyItemRangeInserted(size2, this.items.size() - size2);
    }

    private boolean isRecyclerViewFull() {
        RecyclerView.LayoutManager layoutManager = this.layoutManager;
        if (layoutManager == null) {
            return false;
        }
        return (((LinearLayoutManager) this.layoutManager).findLastVisibleItemPosition() - ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition()) + 1 >= getItemCount();
    }

    public boolean update(MESSAGE message) {
        return update(message.getId(), message);
    }

    public boolean update(String oldId, MESSAGE newMessage) {
        int messagePositionById = getMessagePositionById(oldId);
        if (messagePositionById < 0) {
            return false;
        }
        this.items.set(messagePositionById, new Wrapper(newMessage));
        notifyItemChanged(messagePositionById);
        return true;
    }

    public void updateAndMoveToStart(MESSAGE newMessage) {
        int messagePositionById = getMessagePositionById(newMessage.getId());
        if (messagePositionById >= 0) {
            Wrapper wrapper = new Wrapper(newMessage);
            this.items.remove(messagePositionById);
            this.items.add(0, wrapper);
            notifyItemMoved(messagePositionById, 0);
            notifyItemChanged(0);
        }
    }

    public void upsert(MESSAGE message) {
        if (update(message)) {
            return;
        }
        addToStart(message, false);
    }

    public void upsert(MESSAGE message, boolean moveToStartIfUpdate) {
        if (moveToStartIfUpdate) {
            if (getMessagePositionById(message.getId()) > 0) {
                updateAndMoveToStart(message);
                return;
            } else {
                upsert(message);
                return;
            }
        }
        upsert(message);
    }

    public void delete(MESSAGE message) {
        deleteById(message.getId());
    }

    public void delete(List<MESSAGE> messages) {
        Iterator<MESSAGE> it = messages.iterator();
        boolean z = false;
        while (it.hasNext()) {
            int messagePositionById = getMessagePositionById(it.next().getId());
            if (messagePositionById >= 0) {
                this.items.remove(messagePositionById);
                notifyItemRemoved(messagePositionById);
                z = true;
            }
        }
        if (z) {
            recountDateHeaders();
        }
    }

    public void deleteById(String id) {
        int messagePositionById = getMessagePositionById(id);
        if (messagePositionById >= 0) {
            this.items.remove(messagePositionById);
            notifyItemRemoved(messagePositionById);
            recountDateHeaders();
        }
    }

    public void deleteByIds(String[] ids) {
        boolean z = false;
        for (String str : ids) {
            int messagePositionById = getMessagePositionById(str);
            if (messagePositionById >= 0) {
                this.items.remove(messagePositionById);
                notifyItemRemoved(messagePositionById);
                z = true;
            }
        }
        if (z) {
            recountDateHeaders();
        }
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public void clear() {
        clear(true);
    }

    public void clear(boolean notifyDataSetChanged) {
        List<Wrapper> list = this.items;
        if (list != null) {
            list.clear();
            if (notifyDataSetChanged) {
                notifyDataSetChanged();
            }
        }
    }

    public void enableSelectionMode(SelectionListener selectionListener) {
        if (selectionListener == null) {
            throw new IllegalArgumentException("SelectionListener must not be null. Use `disableSelectionMode()` if you want tp disable selection mode");
        }
        this.selectionListener = selectionListener;
    }

    public void disableSelectionMode() {
        this.selectionListener = null;
        unselectAllItems();
    }

    public ArrayList<MESSAGE> getSelectedMessages() {
        FlowableReplay.UnboundedReplayBuffer unboundedReplayBuffer = (ArrayList<MESSAGE>) new ArrayList();
        for (Wrapper wrapper : this.items) {
            if ((wrapper.item instanceof IMessage) && wrapper.isSelected) {
                unboundedReplayBuffer.add((IMessage) wrapper.item);
            }
        }
        return unboundedReplayBuffer;
    }

    public String getSelectedMessagesText(Formatter<MESSAGE> formatter, boolean reverse) {
        String selectedText = getSelectedText(formatter, reverse);
        unselectAllItems();
        return selectedText;
    }

    public String copySelectedMessagesText(Context context, Formatter<MESSAGE> formatter, boolean reverse) {
        String selectedText = getSelectedText(formatter, reverse);
        copyToClipboard(context, selectedText);
        unselectAllItems();
        return selectedText;
    }

    public void unselectAllItems() {
        for (int i = 0; i < this.items.size(); i++) {
            Wrapper wrapper = this.items.get(i);
            if (wrapper.isSelected) {
                wrapper.isSelected = false;
                notifyItemChanged(i);
            }
        }
        isSelectionModeEnabled = false;
        this.selectedItemsCount = 0;
        notifySelectionChanged();
    }

    public void deleteSelectedMessages() {
        delete(getSelectedMessages());
        unselectAllItems();
    }

    public void setOnMessageClickListener(OnMessageClickListener<MESSAGE> onMessageClickListener) {
        this.onMessageClickListener = onMessageClickListener;
    }

    public void setOnMessageViewClickListener(OnMessageViewClickListener<MESSAGE> onMessageViewClickListener) {
        this.onMessageViewClickListener = onMessageViewClickListener;
    }

    public void registerViewClickListener(int viewId, OnMessageViewClickListener<MESSAGE> onMessageViewClickListener) {
        this.viewClickListenersArray.append(viewId, onMessageViewClickListener);
    }

    public void setOnMessageLongClickListener(OnMessageLongClickListener<MESSAGE> onMessageLongClickListener) {
        this.onMessageLongClickListener = onMessageLongClickListener;
    }

    public void setOnMessageViewLongClickListener(OnMessageViewLongClickListener<MESSAGE> onMessageViewLongClickListener) {
        this.onMessageViewLongClickListener = onMessageViewLongClickListener;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setDateHeadersFormatter(DateFormatter.Formatter dateHeadersFormatter) {
        this.dateHeadersFormatter = dateHeadersFormatter;
    }

    private void recountDateHeaders() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).item instanceof Date) {
                if (i == 0) {
                    arrayList.add(Integer.valueOf(i));
                } else if (this.items.get(i - 1).item instanceof Date) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            this.items.remove(iIntValue);
            notifyItemRemoved(iIntValue);
        }
    }

    protected void generateDateHeaders(List<MESSAGE> messages) {
        int i = 0;
        while (i < messages.size()) {
            MESSAGE message = messages.get(i);
            this.items.add(new Wrapper(message));
            i++;
            if (messages.size() > i) {
                if (!DateFormatter.isSameDay(message.getCreatedAt(), messages.get(i).getCreatedAt())) {
                    this.items.add(new Wrapper(message.getCreatedAt()));
                }
            } else {
                this.items.add(new Wrapper(message.getCreatedAt()));
            }
        }
    }

    private int getMessagePositionById(String id) {
        for (int i = 0; i < this.items.size(); i++) {
            Wrapper wrapper = this.items.get(i);
            if ((wrapper.item instanceof IMessage) && ((IMessage) wrapper.item).getId().contentEquals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isPreviousSameDate(int position, Date dateToCompare) {
        if (this.items.size() > position && (this.items.get(position).item instanceof IMessage)) {
            return DateFormatter.isSameDay(dateToCompare, ((IMessage) this.items.get(position).item).getCreatedAt());
        }
        return false;
    }

    private boolean isPreviousSameAuthor(String id, int position) {
        int i = position + 1;
        return this.items.size() > i && (this.items.get(i).item instanceof IMessage) && ((IMessage) this.items.get(i).item).getUser().getId().contentEquals(id);
    }

    private void incrementSelectedItemsCount() {
        this.selectedItemsCount++;
        notifySelectionChanged();
    }

    private void decrementSelectedItemsCount() {
        int i = this.selectedItemsCount - 1;
        this.selectedItemsCount = i;
        isSelectionModeEnabled = i > 0;
        notifySelectionChanged();
    }

    private void notifySelectionChanged() {
        SelectionListener selectionListener = this.selectionListener;
        if (selectionListener != null) {
            selectionListener.onSelectionChanged(this.selectedItemsCount);
        }
    }

    private void notifyMessageClicked(MESSAGE message) {
        OnMessageClickListener<MESSAGE> onMessageClickListener = this.onMessageClickListener;
        if (onMessageClickListener != null) {
            onMessageClickListener.onMessageClick(message);
        }
    }

    private void notifyMessageViewClicked(View view, MESSAGE message) {
        OnMessageViewClickListener<MESSAGE> onMessageViewClickListener = this.onMessageViewClickListener;
        if (onMessageViewClickListener != null) {
            onMessageViewClickListener.onMessageViewClick(view, message);
        }
    }

    private void notifyMessageLongClicked(MESSAGE message) {
        OnMessageLongClickListener<MESSAGE> onMessageLongClickListener = this.onMessageLongClickListener;
        if (onMessageLongClickListener != null) {
            onMessageLongClickListener.onMessageLongClick(message);
        }
    }

    private void notifyMessageViewLongClicked(View view, MESSAGE message) {
        OnMessageViewLongClickListener<MESSAGE> onMessageViewLongClickListener = this.onMessageViewLongClickListener;
        if (onMessageViewLongClickListener != null) {
            onMessageViewLongClickListener.onMessageViewLongClick(view, message);
        }
    }

    private View.OnClickListener getMessageClickListener(final Wrapper<MESSAGE> wrapper) {
        return new View.OnClickListener() { // from class: com.stfalcon.chatkit.messages.MessagesListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1085x1e7c9af8(wrapper, view);
            }
        };
    }

    /* renamed from: lambda$getMessageClickListener$0$com-stfalcon-chatkit-messages-MessagesListAdapter, reason: not valid java name */
    public /* synthetic */ void m1085x1e7c9af8(Wrapper wrapper, View view) {
        if (this.selectionListener != null && isSelectionModeEnabled) {
            wrapper.isSelected = !wrapper.isSelected;
            if (wrapper.isSelected) {
                incrementSelectedItemsCount();
            } else {
                decrementSelectedItemsCount();
            }
            notifyItemChanged(getMessagePositionById(((IMessage) wrapper.item).getId()));
            return;
        }
        notifyMessageClicked((IMessage) wrapper.item);
        notifyMessageViewClicked(view, (IMessage) wrapper.item);
    }

    private View.OnLongClickListener getMessageLongClickListener(final Wrapper<MESSAGE> wrapper) {
        return new View.OnLongClickListener() { // from class: com.stfalcon.chatkit.messages.MessagesListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.m1086x169be89d(wrapper, view);
            }
        };
    }

    /* renamed from: lambda$getMessageLongClickListener$1$com-stfalcon-chatkit-messages-MessagesListAdapter, reason: not valid java name */
    public /* synthetic */ boolean m1086x169be89d(Wrapper wrapper, View view) {
        if (this.selectionListener == null) {
            notifyMessageLongClicked((IMessage) wrapper.item);
            notifyMessageViewLongClicked(view, (IMessage) wrapper.item);
        } else {
            isSelectionModeEnabled = true;
            view.performClick();
        }
        return true;
    }

    private String getSelectedText(Formatter<MESSAGE> formatter, boolean reverse) {
        String string;
        StringBuilder sb = new StringBuilder();
        ArrayList<MESSAGE> selectedMessages = getSelectedMessages();
        if (reverse) {
            Collections.reverse(selectedMessages);
        }
        Iterator<MESSAGE> it = selectedMessages.iterator();
        while (it.hasNext()) {
            MESSAGE next = it.next();
            if (formatter == null) {
                string = next.toString();
            } else {
                string = formatter.format(next);
            }
            sb.append(string);
            sb.append("\n\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }

    private void copyToClipboard(Context context, String copiedText) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(copiedText, copiedText));
    }

    void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setStyle(MessagesListStyle style) {
        this.messagesListStyle = style;
    }

    public static class Wrapper<DATA> {
        public boolean isSelected;
        public DATA item;

        Wrapper(DATA item) {
            this.item = item;
        }
    }

    @Deprecated
    public static class HoldersConfig extends MessageHolders {
        @Deprecated
        public void setIncoming(Class<? extends MessageHolders.BaseMessageViewHolder<? extends IMessage>> holder, int layout) {
            super.setIncomingTextConfig(holder, layout);
        }

        @Deprecated
        public void setIncomingHolder(Class<? extends MessageHolders.BaseMessageViewHolder<? extends IMessage>> holder) {
            super.setIncomingTextHolder(holder);
        }

        @Deprecated
        public void setIncomingLayout(int layout) {
            super.setIncomingTextLayout(layout);
        }

        @Deprecated
        public void setOutcoming(Class<? extends MessageHolders.BaseMessageViewHolder<? extends IMessage>> holder, int layout) {
            super.setOutcomingTextConfig(holder, layout);
        }

        @Deprecated
        public void setOutcomingHolder(Class<? extends MessageHolders.BaseMessageViewHolder<? extends IMessage>> holder) {
            super.setOutcomingTextHolder(holder);
        }

        @Deprecated
        public void setOutcomingLayout(int layout) {
            setOutcomingTextLayout(layout);
        }

        @Deprecated
        public void setDateHeader(Class<? extends ViewHolder<Date>> holder, int layout) {
            super.setDateHeaderConfig(holder, layout);
        }
    }

    @Deprecated
    public static abstract class BaseMessageViewHolder<MESSAGE extends IMessage> extends MessageHolders.BaseMessageViewHolder<MESSAGE> {
        protected ImageLoader imageLoader;
        private boolean isSelected;

        public BaseMessageViewHolder(View itemView) {
            super(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseMessageViewHolder
        public boolean isSelected() {
            return this.isSelected;
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseMessageViewHolder
        public boolean isSelectionModeEnabled() {
            return MessagesListAdapter.isSelectionModeEnabled;
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseMessageViewHolder
        public ImageLoader getImageLoader() {
            return this.imageLoader;
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseMessageViewHolder
        protected void configureLinksBehavior(final TextView text) {
            text.setLinksClickable(false);
            text.setMovementMethod(new LinkMovementMethod() { // from class: com.stfalcon.chatkit.messages.MessagesListAdapter.BaseMessageViewHolder.1
                @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
                public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
                    boolean zOnTouchEvent = !MessagesListAdapter.isSelectionModeEnabled ? super.onTouchEvent(widget, buffer, event) : false;
                    BaseMessageViewHolder.this.itemView.onTouchEvent(event);
                    return zOnTouchEvent;
                }
            });
        }
    }

    @Deprecated
    public static class DefaultDateHeaderViewHolder extends ViewHolder<Date> implements MessageHolders.DefaultMessageViewHolder {
        protected String dateFormat;
        protected DateFormatter.Formatter dateHeadersFormatter;
        protected TextView text;

        public DefaultDateHeaderViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
        }

        @Override // com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(Date date) {
            if (this.text != null) {
                DateFormatter.Formatter formatter = this.dateHeadersFormatter;
                String str = formatter != null ? formatter.format(date) : null;
                TextView textView = this.text;
                if (str == null) {
                    str = DateFormatter.format(date, this.dateFormat);
                }
                textView.setText(str);
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            TextView textView = this.text;
            if (textView != null) {
                textView.setTextColor(style.getDateHeaderTextColor());
                this.text.setTextSize(0, style.getDateHeaderTextSize());
                TextView textView2 = this.text;
                textView2.setTypeface(textView2.getTypeface(), style.getDateHeaderTextStyle());
                this.text.setPadding(style.getDateHeaderPadding(), style.getDateHeaderPadding(), style.getDateHeaderPadding(), style.getDateHeaderPadding());
            }
            String dateHeaderFormat = style.getDateHeaderFormat();
            this.dateFormat = dateHeaderFormat;
            if (dateHeaderFormat == null) {
                dateHeaderFormat = DateFormatter.Template.STRING_DAY_MONTH_YEAR.get();
            }
            this.dateFormat = dateHeaderFormat;
        }
    }

    @Deprecated
    public static class IncomingMessageViewHolder<MESSAGE extends IMessage> extends MessageHolders.IncomingTextMessageViewHolder<MESSAGE> implements MessageHolders.DefaultMessageViewHolder {
        public IncomingMessageViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Deprecated
    public static class OutcomingMessageViewHolder<MESSAGE extends IMessage> extends MessageHolders.OutcomingTextMessageViewHolder<MESSAGE> {
        public OutcomingMessageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
