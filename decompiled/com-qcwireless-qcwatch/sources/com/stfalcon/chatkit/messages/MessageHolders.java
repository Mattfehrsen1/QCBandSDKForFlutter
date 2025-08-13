package com.stfalcon.chatkit.messages;

import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.ViewHolder;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.utils.DateFormatter;
import com.stfalcon.chatkit.utils.RoundedImageView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes4.dex */
public class MessageHolders {
    private static final short VIEW_TYPE_DATE_HEADER = 130;
    private static final short VIEW_TYPE_IMAGE_MESSAGE = 132;
    private static final short VIEW_TYPE_TEXT_MESSAGE = 131;
    private static final short VIEW_TYPE_TEXT_MESSAGE_LOCAL = 133;
    private static final short VIEW_TYPE_TEXT_MESSAGE_LOCAL_QUDAO = 135;
    private static final short VIEW_TYPE_TEXT_MESSAGE_LOCAL_RESP = 134;
    private ContentChecker contentChecker;
    private List<ContentTypeConfig> customContentTypes = new ArrayList();
    private Class<? extends ViewHolder<Date>> dateHeaderHolder = DefaultDateHeaderViewHolder.class;
    private int dateHeaderLayout = R.layout.item_date_header;
    private HolderConfig<IMessage> incomingLocalQuDaoTextConfig = new HolderConfig<>(DefaultIncomingTextLocalQuDaoMessageViewHolder.class, R.layout.item_incoming_text_message_local_qudao);
    private HolderConfig<IMessage> incomingLocalRespTextConfig = new HolderConfig<>(DefaultIncomingTextLocalRespMessageViewHolder.class, R.layout.item_incoming_text_message_local_tocs);
    private HolderConfig<IMessage> incomingLocalTextConfig = new HolderConfig<>(DefaultIncomingTextLocalMessageViewHolder.class, R.layout.item_incoming_text_message_local);
    private HolderConfig<IMessage> incomingTextConfig = new HolderConfig<>(DefaultIncomingTextMessageViewHolder.class, R.layout.item_incoming_text_message);
    private HolderConfig<IMessage> outcomingTextConfig = new HolderConfig<>(DefaultOutcomingTextMessageViewHolder.class, R.layout.item_outcoming_text_message);
    private HolderConfig<MessageContentType.Image> incomingImageConfig = new HolderConfig<>(DefaultIncomingImageMessageViewHolder.class, R.layout.item_incoming_image_message);
    private HolderConfig<MessageContentType.Image> outcomingImageConfig = new HolderConfig<>(DefaultOutcomingImageMessageViewHolder.class, R.layout.item_outcoming_image_message);

    public interface ContentChecker<MESSAGE extends IMessage> {
        boolean hasContentFor(MESSAGE message, byte type);
    }

    interface DefaultMessageViewHolder {
        void applyStyle(MessagesListStyle style);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setIncomingTextConfig(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, int layout) {
        this.incomingTextConfig.holder = holder;
        this.incomingTextConfig.layout = layout;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setIncomingTextConfig(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, int layout, Object payload) {
        this.incomingTextConfig.holder = holder;
        this.incomingTextConfig.layout = layout;
        this.incomingTextConfig.payload = payload;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setIncomingTextHolder(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder) {
        this.incomingTextConfig.holder = holder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setIncomingTextHolder(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, Object payload) {
        this.incomingTextConfig.holder = holder;
        this.incomingTextConfig.payload = payload;
        return this;
    }

    public MessageHolders setIncomingTextLayout(int layout) {
        this.incomingTextConfig.layout = layout;
        return this;
    }

    public MessageHolders setIncomingTextLayout(int layout, Object payload) {
        this.incomingTextConfig.layout = layout;
        this.incomingTextConfig.payload = payload;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setOutcomingTextConfig(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, int layout) {
        this.outcomingTextConfig.holder = holder;
        this.outcomingTextConfig.layout = layout;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setOutcomingTextConfig(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, int layout, Object payload) {
        this.outcomingTextConfig.holder = holder;
        this.outcomingTextConfig.layout = layout;
        this.outcomingTextConfig.payload = payload;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setOutcomingTextHolder(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder) {
        this.outcomingTextConfig.holder = holder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MessageHolders setOutcomingTextHolder(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, Object payload) {
        this.outcomingTextConfig.holder = holder;
        this.outcomingTextConfig.payload = payload;
        return this;
    }

    public MessageHolders setOutcomingTextLayout(int layout) {
        this.outcomingTextConfig.layout = layout;
        return this;
    }

    public MessageHolders setOutcomingTextLayout(int layout, Object payload) {
        this.outcomingTextConfig.layout = layout;
        this.outcomingTextConfig.payload = payload;
        return this;
    }

    public MessageHolders setIncomingImageConfig(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder, int layout) {
        this.incomingImageConfig.holder = holder;
        this.incomingImageConfig.layout = layout;
        return this;
    }

    public MessageHolders setIncomingImageConfig(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder, int layout, Object payload) {
        this.incomingImageConfig.holder = holder;
        this.incomingImageConfig.layout = layout;
        this.incomingImageConfig.payload = payload;
        return this;
    }

    public MessageHolders setIncomingImageHolder(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder) {
        this.incomingImageConfig.holder = holder;
        return this;
    }

    public MessageHolders setIncomingImageHolder(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder, Object payload) {
        this.incomingImageConfig.holder = holder;
        this.incomingImageConfig.payload = payload;
        return this;
    }

    public MessageHolders setIncomingImageLayout(int layout) {
        this.incomingImageConfig.layout = layout;
        return this;
    }

    public MessageHolders setIncomingImageLayout(int layout, Object payload) {
        this.incomingImageConfig.layout = layout;
        this.incomingImageConfig.payload = payload;
        return this;
    }

    public MessageHolders setOutcomingImageConfig(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder, int layout) {
        this.outcomingImageConfig.holder = holder;
        this.outcomingImageConfig.layout = layout;
        return this;
    }

    public MessageHolders setOutcomingImageConfig(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder, int layout, Object payload) {
        this.outcomingImageConfig.holder = holder;
        this.outcomingImageConfig.layout = layout;
        this.outcomingImageConfig.payload = payload;
        return this;
    }

    public MessageHolders setOutcomingImageHolder(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder) {
        this.outcomingImageConfig.holder = holder;
        return this;
    }

    public MessageHolders setOutcomingImageHolder(Class<? extends BaseMessageViewHolder<? extends MessageContentType.Image>> holder, Object payload) {
        this.outcomingImageConfig.holder = holder;
        this.outcomingImageConfig.payload = payload;
        return this;
    }

    public MessageHolders setOutcomingImageLayout(int layout) {
        this.outcomingImageConfig.layout = layout;
        return this;
    }

    public MessageHolders setOutcomingImageLayout(int layout, Object payload) {
        this.outcomingImageConfig.layout = layout;
        this.outcomingImageConfig.payload = payload;
        return this;
    }

    public MessageHolders setDateHeaderConfig(Class<? extends ViewHolder<Date>> holder, int layout) {
        this.dateHeaderHolder = holder;
        this.dateHeaderLayout = layout;
        return this;
    }

    public MessageHolders setDateHeaderHolder(Class<? extends ViewHolder<Date>> holder) {
        this.dateHeaderHolder = holder;
        return this;
    }

    public MessageHolders setDateHeaderLayout(int layout) {
        this.dateHeaderLayout = layout;
        return this;
    }

    public <TYPE extends MessageContentType> MessageHolders registerContentType(byte type, Class<? extends BaseMessageViewHolder<TYPE>> holder, int incomingLayout, int outcomingLayout, ContentChecker contentChecker) {
        return registerContentType(type, holder, incomingLayout, holder, outcomingLayout, contentChecker);
    }

    public <TYPE extends MessageContentType> MessageHolders registerContentType(byte type, Class<? extends BaseMessageViewHolder<TYPE>> incomingHolder, int incomingLayout, Class<? extends BaseMessageViewHolder<TYPE>> outcomingHolder, int outcomingLayout, ContentChecker contentChecker) {
        if (type == 0) {
            throw new IllegalArgumentException("content type must be greater or less than '0'!");
        }
        this.customContentTypes.add(new ContentTypeConfig(type, new HolderConfig(incomingHolder, incomingLayout), new HolderConfig(outcomingHolder, outcomingLayout)));
        this.contentChecker = contentChecker;
        return this;
    }

    public <TYPE extends MessageContentType> MessageHolders registerContentType(byte type, Class<? extends BaseMessageViewHolder<TYPE>> incomingHolder, Object incomingPayload, int incomingLayout, Class<? extends BaseMessageViewHolder<TYPE>> outcomingHolder, Object outcomingPayload, int outcomingLayout, ContentChecker contentChecker) {
        if (type == 0) {
            throw new IllegalArgumentException("content type must be greater or less than '0'!");
        }
        this.customContentTypes.add(new ContentTypeConfig(type, new HolderConfig(incomingHolder, incomingLayout, incomingPayload), new HolderConfig(outcomingHolder, outcomingLayout, outcomingPayload)));
        this.contentChecker = contentChecker;
        return this;
    }

    protected ViewHolder getHolder(ViewGroup parent, int viewType, MessagesListStyle messagesListStyle) {
        if (viewType == -132) {
            return getHolder(parent, this.outcomingImageConfig, messagesListStyle);
        }
        if (viewType != -131) {
            switch (viewType) {
                case 130:
                    return getHolder(parent, this.dateHeaderLayout, this.dateHeaderHolder, messagesListStyle, null);
                case 131:
                    return getHolder(parent, this.incomingTextConfig, messagesListStyle);
                case 132:
                    return getHolder(parent, this.incomingImageConfig, messagesListStyle);
                case 133:
                    return getHolder(parent, this.incomingLocalTextConfig, messagesListStyle);
                case 134:
                    return getHolder(parent, this.incomingLocalRespTextConfig, messagesListStyle);
                case 135:
                    return getHolder(parent, this.incomingLocalQuDaoTextConfig, messagesListStyle);
                default:
                    for (ContentTypeConfig contentTypeConfig : this.customContentTypes) {
                        if (Math.abs((int) contentTypeConfig.type) == Math.abs(viewType)) {
                            return viewType > 0 ? getHolder(parent, contentTypeConfig.incomingConfig, messagesListStyle) : getHolder(parent, contentTypeConfig.outcomingConfig, messagesListStyle);
                        }
                    }
                    throw new IllegalStateException("Wrong message view type. Please, report this issue on GitHub with full stacktrace in description.");
            }
        }
        return getHolder(parent, this.outcomingTextConfig, messagesListStyle);
    }

    protected void bind(final ViewHolder holder, final Object item, boolean isSelected, final ImageLoader imageLoader, final View.OnClickListener onMessageClickListener, final View.OnLongClickListener onMessageLongClickListener, final DateFormatter.Formatter dateHeadersFormatter, final SparseArray<MessagesListAdapter.OnMessageViewClickListener> clickListenersArray) {
        if (item instanceof IMessage) {
            BaseMessageViewHolder baseMessageViewHolder = (BaseMessageViewHolder) holder;
            baseMessageViewHolder.isSelected = isSelected;
            baseMessageViewHolder.imageLoader = imageLoader;
            holder.itemView.setOnLongClickListener(onMessageLongClickListener);
            holder.itemView.setOnClickListener(onMessageClickListener);
            for (int i = 0; i < clickListenersArray.size(); i++) {
                final int iKeyAt = clickListenersArray.keyAt(i);
                final View viewFindViewById = holder.itemView.findViewById(iKeyAt);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.stfalcon.chatkit.messages.MessageHolders$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ((MessagesListAdapter.OnMessageViewClickListener) clickListenersArray.get(iKeyAt)).onMessageViewClick(viewFindViewById, (IMessage) item);
                        }
                    });
                }
            }
        } else if (item instanceof Date) {
            ((DefaultDateHeaderViewHolder) holder).dateHeadersFormatter = dateHeadersFormatter;
        }
        holder.onBind(item);
    }

    protected int getViewType(Object item, String senderId) {
        short contentViewType;
        boolean zContentEquals;
        if (item instanceof IMessage) {
            IMessage iMessage = (IMessage) item;
            zContentEquals = iMessage.getUser().getId().contentEquals(senderId);
            contentViewType = getContentViewType(iMessage);
        } else {
            contentViewType = VIEW_TYPE_DATE_HEADER;
            zContentEquals = false;
        }
        return zContentEquals ? contentViewType * (-1) : contentViewType;
    }

    private ViewHolder getHolder(ViewGroup parent, HolderConfig holderConfig, MessagesListStyle style) {
        return getHolder(parent, holderConfig.layout, holderConfig.holder, style, holderConfig.payload);
    }

    private <HOLDER extends ViewHolder> ViewHolder getHolder(ViewGroup parent, int layout, Class<HOLDER> holderClass, MessagesListStyle style, Object payload) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        HOLDER holderNewInstance;
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        try {
            try {
                Constructor<HOLDER> declaredConstructor = holderClass.getDeclaredConstructor(View.class, Object.class);
                declaredConstructor.setAccessible(true);
                holderNewInstance = declaredConstructor.newInstance(viewInflate, payload);
            } catch (NoSuchMethodException unused) {
                Constructor<HOLDER> declaredConstructor2 = holderClass.getDeclaredConstructor(View.class);
                declaredConstructor2.setAccessible(true);
                holderNewInstance = declaredConstructor2.newInstance(viewInflate);
            }
            if ((holderNewInstance instanceof DefaultMessageViewHolder) && style != null) {
                ((DefaultMessageViewHolder) holderNewInstance).applyStyle(style);
            }
            return holderNewInstance;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Somehow we couldn't create the ViewHolder for message. Please, report this issue on GitHub with full stacktrace in description.", e);
        }
    }

    private short getContentViewType(IMessage message) {
        if ((message instanceof MessageContentType.Image) && ((MessageContentType.Image) message).getImageUrl() != null) {
            return VIEW_TYPE_IMAGE_MESSAGE;
        }
        boolean z = message instanceof MessageContentType.LocalMessage;
        if (z && ((MessageContentType.LocalMessage) message).localMessage() == 1) {
            return VIEW_TYPE_TEXT_MESSAGE_LOCAL;
        }
        if (z && ((MessageContentType.LocalMessage) message).localMessage() == 2) {
            return VIEW_TYPE_TEXT_MESSAGE_LOCAL_RESP;
        }
        if (z && ((MessageContentType.LocalMessage) message).localMessage() == 3) {
            return VIEW_TYPE_TEXT_MESSAGE_LOCAL_QUDAO;
        }
        if (!(message instanceof MessageContentType)) {
            return VIEW_TYPE_TEXT_MESSAGE;
        }
        for (int i = 0; i < this.customContentTypes.size(); i++) {
            ContentTypeConfig contentTypeConfig = this.customContentTypes.get(i);
            ContentChecker contentChecker = this.contentChecker;
            if (contentChecker == null) {
                throw new IllegalArgumentException("ContentChecker cannot be null when using custom content types!");
            }
            if (contentChecker.hasContentFor(message, contentTypeConfig.type)) {
                return contentTypeConfig.type;
            }
        }
        return VIEW_TYPE_TEXT_MESSAGE;
    }

    public static abstract class BaseMessageViewHolder<MESSAGE extends IMessage> extends ViewHolder<MESSAGE> {
        protected ImageLoader imageLoader;
        boolean isSelected;
        protected Object payload;

        @Deprecated
        public BaseMessageViewHolder(View itemView) {
            super(itemView);
        }

        public BaseMessageViewHolder(View itemView, Object payload) {
            super(itemView);
            this.payload = payload;
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public boolean isSelectionModeEnabled() {
            return MessagesListAdapter.isSelectionModeEnabled;
        }

        public ImageLoader getImageLoader() {
            return this.imageLoader;
        }

        protected void configureLinksBehavior(final TextView text) {
            text.setLinksClickable(false);
            text.setMovementMethod(new LinkMovementMethod() { // from class: com.stfalcon.chatkit.messages.MessageHolders.BaseMessageViewHolder.1
                @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
                public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
                    boolean zOnTouchEvent = !MessagesListAdapter.isSelectionModeEnabled ? super.onTouchEvent(widget, buffer, event) : false;
                    BaseMessageViewHolder.this.itemView.onTouchEvent(event);
                    return zOnTouchEvent;
                }
            });
        }
    }

    public static class IncomingLocalQudaoTextMessageViewHolder<MESSAGE extends IMessage> extends BaseIncomingMessageViewHolder<MESSAGE> {
        protected ViewGroup bubble;
        protected TextView cs;
        protected TextView q1;
        protected TextView q2;
        protected TextView q3;
        protected TextView text;

        @Deprecated
        public IncomingLocalQudaoTextMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public IncomingLocalQudaoTextMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((IncomingLocalQudaoTextMessageViewHolder<MESSAGE>) message);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setSelected(isSelected());
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setText(message.getText());
            }
            TextView textView2 = this.q1;
            if (textView2 != null) {
                textView2.setText(message.getQ1());
            }
            TextView textView3 = this.q2;
            if (textView3 != null) {
                textView3.setText(message.getQ2());
            }
            TextView textView4 = this.q3;
            if (textView4 != null) {
                textView4.setText(message.getQ3());
            }
            TextView textView5 = this.cs;
            if (textView5 != null) {
                textView5.setText(message.getCs());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setPadding(style.getIncomingDefaultBubblePaddingLeft(), style.getIncomingDefaultBubblePaddingTop(), style.getIncomingDefaultBubblePaddingRight(), style.getIncomingDefaultBubblePaddingBottom());
                ViewGroup viewGroup2 = this.bubble;
                viewGroup2.setBackground(SkinCompatResources.getDrawable(viewGroup2.getContext(), R.drawable.bg_rect_ffffff_corner_12));
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setTextColor(style.getIncomingTextColor());
                this.text.setTextSize(0, style.getIncomingTextSize());
                TextView textView2 = this.text;
                textView2.setTypeface(textView2.getTypeface(), style.getIncomingTextStyle());
                this.text.setAutoLinkMask(style.getTextAutoLinkMask());
                this.text.setLinkTextColor(style.getIncomingTextLinkColor());
                configureLinksBehavior(this.text);
            }
            TextView textView3 = this.q1;
            if (textView3 != null) {
                textView3.setTextColor(style.getIncomingQuestionTextColor());
                this.q2.setTextColor(style.getIncomingQuestionTextColor());
                this.q3.setTextColor(style.getIncomingQuestionTextColor());
                this.cs.setTextColor(style.getIncomingTextColor());
            }
        }

        private void init(View itemView) {
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble_2);
            this.text = (TextView) itemView.findViewById(R.id.qudao_messageText);
            this.q1 = (TextView) itemView.findViewById(R.id.qudao_messageText_1);
            this.q2 = (TextView) itemView.findViewById(R.id.qudao_messageText_2);
            this.q3 = (TextView) itemView.findViewById(R.id.qudao_messageText_3);
            this.cs = (TextView) itemView.findViewById(R.id.qudao_toCs);
        }
    }

    public static class IncomingLocalRespTextMessageViewHolder<MESSAGE extends IMessage> extends BaseIncomingMessageViewHolder<MESSAGE> {
        protected ViewGroup bubble;
        protected TextView cs;
        protected TextView q1;
        protected TextView text;

        @Deprecated
        public IncomingLocalRespTextMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public IncomingLocalRespTextMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((IncomingLocalRespTextMessageViewHolder<MESSAGE>) message);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setSelected(isSelected());
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setText(message.getText());
            }
            TextView textView2 = this.q1;
            if (textView2 != null) {
                textView2.setText(message.getQ1());
            }
            TextView textView3 = this.cs;
            if (textView3 != null) {
                textView3.setText(message.getCs());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setPadding(style.getIncomingDefaultBubblePaddingLeft(), style.getIncomingDefaultBubblePaddingTop(), style.getIncomingDefaultBubblePaddingRight(), style.getIncomingDefaultBubblePaddingBottom());
                ViewGroup viewGroup2 = this.bubble;
                viewGroup2.setBackground(SkinCompatResources.getDrawable(viewGroup2.getContext(), R.drawable.bg_rect_ffffff_corner_12));
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setTextColor(style.getIncomingTextColor());
                this.text.setTextSize(0, style.getIncomingTextSize());
                TextView textView2 = this.text;
                textView2.setTypeface(textView2.getTypeface(), style.getIncomingTextStyle());
                this.text.setAutoLinkMask(style.getTextAutoLinkMask());
                this.text.setLinkTextColor(style.getIncomingTextLinkColor());
                configureLinksBehavior(this.text);
            }
            TextView textView3 = this.q1;
            if (textView3 != null) {
                textView3.setTextColor(style.getIncomingTextColor());
                this.cs.setTextColor(style.getIncomingQuestionTextColor());
            }
        }

        private void init(View itemView) {
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble_3);
            this.text = (TextView) itemView.findViewById(R.id.q_messageText);
            this.q1 = (TextView) itemView.findViewById(R.id.q_messageText_1);
            this.cs = (TextView) itemView.findViewById(R.id.q_toCs);
        }
    }

    public static class IncomingLocalTextMessageViewHolder<MESSAGE extends IMessage> extends BaseIncomingMessageViewHolder<MESSAGE> {
        protected ViewGroup bubble;
        protected TextView cs;
        protected TextView q1;
        protected TextView q2;
        protected TextView q3;
        protected TextView q4;
        protected TextView text;

        @Deprecated
        public IncomingLocalTextMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public IncomingLocalTextMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((IncomingLocalTextMessageViewHolder<MESSAGE>) message);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setSelected(isSelected());
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setText(message.getText());
            }
            TextView textView2 = this.q1;
            if (textView2 != null) {
                textView2.setText(message.getQ1());
            }
            TextView textView3 = this.q2;
            if (textView3 != null) {
                textView3.setText(message.getQ2());
            }
            TextView textView4 = this.q3;
            if (textView4 != null) {
                textView4.setText(message.getQ3());
            }
            TextView textView5 = this.q4;
            if (textView5 != null) {
                textView5.setText(message.getQ4());
            }
            TextView textView6 = this.cs;
            if (textView6 != null) {
                textView6.setText(message.getCs());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setPadding(style.getIncomingDefaultBubblePaddingLeft(), style.getIncomingDefaultBubblePaddingTop(), style.getIncomingDefaultBubblePaddingRight(), style.getIncomingDefaultBubblePaddingBottom());
                ViewGroup viewGroup2 = this.bubble;
                viewGroup2.setBackground(SkinCompatResources.getDrawable(viewGroup2.getContext(), R.drawable.bg_rect_ffffff_corner_12));
                Log.i("-----1--", "设置成功了");
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setTextColor(style.getIncomingTextColor());
                this.text.setTextSize(0, style.getIncomingTextSize());
                TextView textView2 = this.text;
                textView2.setTypeface(textView2.getTypeface(), style.getIncomingTextStyle());
                this.text.setAutoLinkMask(style.getTextAutoLinkMask());
                this.text.setLinkTextColor(style.getIncomingTextLinkColor());
                configureLinksBehavior(this.text);
            }
            TextView textView3 = this.q1;
            if (textView3 != null) {
                textView3.setTextColor(style.getIncomingQuestionTextColor());
                this.q2.setTextColor(style.getIncomingQuestionTextColor());
                this.q3.setTextColor(style.getIncomingQuestionTextColor());
                this.q4.setTextColor(style.getIncomingTextColor());
                this.cs.setTextColor(style.getIncomingQuestionTextColor());
                Log.i("-------", "设置成功了");
            }
        }

        private void init(View itemView) {
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble_1);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
            this.q1 = (TextView) itemView.findViewById(R.id.messageText_1);
            this.q2 = (TextView) itemView.findViewById(R.id.messageText_2);
            this.q3 = (TextView) itemView.findViewById(R.id.messageText_3);
            this.q4 = (TextView) itemView.findViewById(R.id.messageText_4);
            this.cs = (TextView) itemView.findViewById(R.id.toCs);
        }
    }

    public static class IncomingTextMessageViewHolder<MESSAGE extends IMessage> extends BaseIncomingMessageViewHolder<MESSAGE> {
        protected ViewGroup bubble;
        protected TextView text;

        @Deprecated
        public IncomingTextMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public IncomingTextMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((IncomingTextMessageViewHolder<MESSAGE>) message);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setSelected(isSelected());
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setText(message.getText());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setPadding(style.getIncomingDefaultBubblePaddingLeft(), style.getIncomingDefaultBubblePaddingTop(), style.getIncomingDefaultBubblePaddingRight(), style.getIncomingDefaultBubblePaddingBottom());
                ViewGroup viewGroup2 = this.bubble;
                viewGroup2.setBackground(SkinCompatResources.getDrawable(viewGroup2.getContext(), R.drawable.bg_rect_ffffff_corner_12));
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setTextColor(style.getIncomingTextColor());
                this.text.setTextSize(0, style.getIncomingTextSize());
                TextView textView2 = this.text;
                textView2.setTypeface(textView2.getTypeface(), style.getIncomingTextStyle());
                this.text.setAutoLinkMask(style.getTextAutoLinkMask());
                this.text.setLinkTextColor(style.getIncomingTextLinkColor());
                configureLinksBehavior(this.text);
            }
        }

        private void init(View itemView) {
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
        }
    }

    public static class OutcomingTextMessageViewHolder<MESSAGE extends IMessage> extends BaseOutcomingMessageViewHolder<MESSAGE> {
        protected ViewGroup bubble;
        protected TextView text;

        @Deprecated
        public OutcomingTextMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public OutcomingTextMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseOutcomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((OutcomingTextMessageViewHolder<MESSAGE>) message);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setSelected(isSelected());
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setText(message.getText());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseOutcomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public final void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            ViewGroup viewGroup = this.bubble;
            if (viewGroup != null) {
                viewGroup.setPadding(style.getOutcomingDefaultBubblePaddingLeft(), style.getOutcomingDefaultBubblePaddingTop(), style.getOutcomingDefaultBubblePaddingRight(), style.getOutcomingDefaultBubblePaddingBottom());
                ViewGroup viewGroup2 = this.bubble;
                viewGroup2.setBackground(SkinCompatResources.getDrawable(viewGroup2.getContext(), R.drawable.bg_rect_ffffff_corner_12_1));
            }
            TextView textView = this.text;
            if (textView != null) {
                textView.setTextColor(style.getOutcomingTextColor());
                this.text.setTextSize(0, style.getOutcomingTextSize());
                TextView textView2 = this.text;
                textView2.setTypeface(textView2.getTypeface(), style.getOutcomingTextStyle());
                this.text.setAutoLinkMask(style.getTextAutoLinkMask());
                this.text.setLinkTextColor(style.getOutcomingTextLinkColor());
                configureLinksBehavior(this.text);
            }
        }

        private void init(View itemView) {
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble_4);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
        }
    }

    public static class IncomingImageMessageViewHolder<MESSAGE extends MessageContentType.Image> extends BaseIncomingMessageViewHolder<MESSAGE> {
        protected ImageView image;
        protected View imageOverlay;

        protected Object getPayloadForImageLoader(MESSAGE message) {
            return null;
        }

        @Deprecated
        public IncomingImageMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public IncomingImageMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((IncomingImageMessageViewHolder<MESSAGE>) message);
            if (this.image != null && this.imageLoader != null) {
                Log.i("chat", message.getImageUrl());
                this.imageLoader.loadImage(this.image, message.getImageUrl(), getPayloadForImageLoader(message));
            }
            View view = this.imageOverlay;
            if (view != null) {
                view.setSelected(isSelected());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseIncomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public final void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            if (this.time != null) {
                this.time.setTextColor(style.getIncomingImageTimeTextColor());
                this.time.setTextSize(0, style.getIncomingImageTimeTextSize());
                this.time.setTypeface(this.time.getTypeface(), style.getIncomingImageTimeTextStyle());
            }
        }

        private void init(View itemView) {
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.imageOverlay = itemView.findViewById(R.id.imageOverlay);
            ImageView imageView = this.image;
            if (imageView instanceof RoundedImageView) {
                ((RoundedImageView) imageView).setCorners(R.dimen.message_bubble_corners_radius, R.dimen.message_bubble_corners_radius, R.dimen.message_bubble_corners_radius, 0);
            }
        }
    }

    public static class OutcomingImageMessageViewHolder<MESSAGE extends MessageContentType.Image> extends BaseOutcomingMessageViewHolder<MESSAGE> {
        protected ImageView image;
        protected View imageOverlay;

        protected Object getPayloadForImageLoader(MESSAGE message) {
            return null;
        }

        @Deprecated
        public OutcomingImageMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public OutcomingImageMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseOutcomingMessageViewHolder, com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            super.onBind((OutcomingImageMessageViewHolder<MESSAGE>) message);
            if (this.image != null && this.imageLoader != null) {
                Log.i("chat1", message.getImageUrl());
                this.imageLoader.loadImage(this.image, message.getImageUrl(), getPayloadForImageLoader(message));
            }
            View view = this.imageOverlay;
            if (view != null) {
                view.setSelected(isSelected());
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.BaseOutcomingMessageViewHolder, com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public final void applyStyle(MessagesListStyle style) {
            super.applyStyle(style);
            if (this.time != null) {
                this.time.setTextColor(style.getOutcomingImageTimeTextColor());
                this.time.setTextSize(0, style.getOutcomingImageTimeTextSize());
                this.time.setTypeface(this.time.getTypeface(), style.getOutcomingImageTimeTextStyle());
            }
        }

        private void init(View itemView) {
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.imageOverlay = itemView.findViewById(R.id.imageOverlay);
            ImageView imageView = this.image;
            if (imageView instanceof RoundedImageView) {
                ((RoundedImageView) imageView).setCorners(R.dimen.message_bubble_corners_radius, R.dimen.message_bubble_corners_radius, 0, R.dimen.message_bubble_corners_radius);
            }
        }
    }

    public static class DefaultDateHeaderViewHolder extends ViewHolder<Date> implements DefaultMessageViewHolder {
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

    public static abstract class BaseIncomingMessageViewHolder<MESSAGE extends IMessage> extends BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {
        protected TextView time;
        protected ImageView userAvatar;

        @Deprecated
        public BaseIncomingMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public BaseIncomingMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            TextView textView = this.time;
            if (textView != null) {
                textView.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));
            }
            if (this.userAvatar != null) {
                boolean z = (this.imageLoader == null || message.getUser().getAvatar() == null || message.getUser().getAvatar().isEmpty()) ? false : true;
                this.userAvatar.setVisibility(z ? 0 : 8);
                if (z) {
                    this.imageLoader.loadImage(this.userAvatar, message.getUser().getAvatar(), null);
                }
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            TextView textView = this.time;
            if (textView != null) {
                textView.setTextColor(style.getIncomingTimeTextColor());
                this.time.setTextSize(0, style.getIncomingTimeTextSize());
                TextView textView2 = this.time;
                textView2.setTypeface(textView2.getTypeface(), style.getIncomingTimeTextStyle());
            }
            ImageView imageView = this.userAvatar;
            if (imageView != null) {
                imageView.getLayoutParams().width = style.getIncomingAvatarWidth();
                this.userAvatar.getLayoutParams().height = style.getIncomingAvatarHeight();
            }
        }

        private void init(View itemView) {
            this.time = (TextView) itemView.findViewById(R.id.messageTime);
            this.userAvatar = (ImageView) itemView.findViewById(R.id.messageUserAvatar);
        }
    }

    public static abstract class BaseOutcomingMessageViewHolder<MESSAGE extends IMessage> extends BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {
        protected TextView time;

        @Deprecated
        public BaseOutcomingMessageViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public BaseOutcomingMessageViewHolder(View itemView, Object payload) {
            super(itemView, payload);
            init(itemView);
        }

        @Override // com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(MESSAGE message) {
            TextView textView = this.time;
            if (textView != null) {
                textView.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));
            }
        }

        @Override // com.stfalcon.chatkit.messages.MessageHolders.DefaultMessageViewHolder
        public void applyStyle(MessagesListStyle style) {
            TextView textView = this.time;
            if (textView != null) {
                textView.setTextColor(style.getOutcomingTimeTextColor());
                this.time.setTextSize(0, style.getOutcomingTimeTextSize());
                TextView textView2 = this.time;
                textView2.setTypeface(textView2.getTypeface(), style.getOutcomingTimeTextStyle());
            }
        }

        private void init(View itemView) {
            this.time = (TextView) itemView.findViewById(R.id.messageTime);
        }
    }

    private static class DefaultIncomingTextMessageViewHolder extends IncomingTextMessageViewHolder<IMessage> {
        public DefaultIncomingTextMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class DefaultIncomingTextLocalMessageViewHolder extends IncomingLocalTextMessageViewHolder<IMessage> {
        public DefaultIncomingTextLocalMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class DefaultIncomingTextLocalRespMessageViewHolder extends IncomingLocalRespTextMessageViewHolder<IMessage> {
        public DefaultIncomingTextLocalRespMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class DefaultIncomingTextLocalQuDaoMessageViewHolder extends IncomingLocalQudaoTextMessageViewHolder<IMessage> {
        public DefaultIncomingTextLocalQuDaoMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class DefaultOutcomingTextMessageViewHolder extends OutcomingTextMessageViewHolder<IMessage> {
        public DefaultOutcomingTextMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class DefaultIncomingImageMessageViewHolder extends IncomingImageMessageViewHolder<MessageContentType.Image> {
        public DefaultIncomingImageMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class DefaultOutcomingImageMessageViewHolder extends OutcomingImageMessageViewHolder<MessageContentType.Image> {
        public DefaultOutcomingImageMessageViewHolder(View itemView) {
            super(itemView, null);
        }
    }

    private static class ContentTypeConfig<TYPE extends MessageContentType> {
        private HolderConfig<TYPE> incomingConfig;
        private HolderConfig<TYPE> outcomingConfig;
        private byte type;

        private ContentTypeConfig(byte type, HolderConfig<TYPE> incomingConfig, HolderConfig<TYPE> outcomingConfig) {
            this.type = type;
            this.incomingConfig = incomingConfig;
            this.outcomingConfig = outcomingConfig;
        }
    }

    private static class HolderConfig<T extends IMessage> {
        protected Class<? extends BaseMessageViewHolder<? extends T>> holder;
        protected int layout;
        protected Object payload;

        HolderConfig(Class<? extends BaseMessageViewHolder<? extends T>> holder, int layout) {
            this.holder = holder;
            this.layout = layout;
        }

        HolderConfig(Class<? extends BaseMessageViewHolder<? extends T>> holder, int layout, Object payload) {
            this.holder = holder;
            this.layout = layout;
            this.payload = payload;
        }
    }
}
