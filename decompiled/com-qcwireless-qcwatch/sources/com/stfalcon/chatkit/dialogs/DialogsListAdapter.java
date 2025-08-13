package com.stfalcon.chatkit.dialogs;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.ViewHolder;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.utils.DateFormatter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class DialogsListAdapter<DIALOG extends IDialog> extends RecyclerView.Adapter<BaseDialogViewHolder> {
    private DateFormatter.Formatter datesFormatter;
    private DialogListStyle dialogStyle;
    private Class<? extends BaseDialogViewHolder> holderClass;
    private ImageLoader imageLoader;
    private int itemLayoutId;
    protected List<DIALOG> items;
    private OnDialogClickListener<DIALOG> onDialogClickListener;
    private OnDialogViewClickListener<DIALOG> onDialogViewClickListener;
    private OnDialogViewLongClickListener<DIALOG> onDialogViewLongClickListener;
    private OnDialogLongClickListener<DIALOG> onLongItemClickListener;

    public interface OnDialogClickListener<DIALOG extends IDialog> {
        void onDialogClick(DIALOG dialog);
    }

    public interface OnDialogLongClickListener<DIALOG extends IDialog> {
        void onDialogLongClick(DIALOG dialog);
    }

    public interface OnDialogViewClickListener<DIALOG extends IDialog> {
        void onDialogViewClick(View view, DIALOG dialog);
    }

    public interface OnDialogViewLongClickListener<DIALOG extends IDialog> {
        void onDialogViewLongClick(View view, DIALOG dialog);
    }

    public DialogsListAdapter(ImageLoader imageLoader) {
        this(R.layout.item_dialog, DialogViewHolder.class, imageLoader);
    }

    public DialogsListAdapter(int itemLayoutId, ImageLoader imageLoader) {
        this(itemLayoutId, DialogViewHolder.class, imageLoader);
    }

    public DialogsListAdapter(int itemLayoutId, Class<? extends BaseDialogViewHolder> holderClass, ImageLoader imageLoader) {
        this.items = new ArrayList();
        this.itemLayoutId = itemLayoutId;
        this.holderClass = holderClass;
        this.imageLoader = imageLoader;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseDialogViewHolder holder, int position) {
        holder.setImageLoader(this.imageLoader);
        holder.setOnDialogClickListener(this.onDialogClickListener);
        holder.setOnDialogViewClickListener(this.onDialogViewClickListener);
        holder.setOnLongItemClickListener(this.onLongItemClickListener);
        holder.setOnDialogViewLongClickListener(this.onDialogViewLongClickListener);
        holder.setDatesFormatter(this.datesFormatter);
        holder.onBind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(this.itemLayoutId, parent, false);
        try {
            Constructor<? extends BaseDialogViewHolder> declaredConstructor = this.holderClass.getDeclaredConstructor(View.class);
            declaredConstructor.setAccessible(true);
            BaseDialogViewHolder baseDialogViewHolderNewInstance = declaredConstructor.newInstance(viewInflate);
            if (baseDialogViewHolderNewInstance instanceof DialogViewHolder) {
                ((DialogViewHolder) baseDialogViewHolderNewInstance).setDialogStyle(this.dialogStyle);
            }
            return baseDialogViewHolderNewInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void deleteById(String id) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public void clear() {
        List<DIALOG> list = this.items;
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    public void setItems(List<DIALOG> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<DIALOG> newItems) {
        if (newItems != null) {
            if (this.items == null) {
                this.items = new ArrayList();
            }
            int size = this.items.size();
            this.items.addAll(newItems);
            notifyItemRangeInserted(size, this.items.size());
        }
    }

    public void addItem(DIALOG dialog) {
        this.items.add(dialog);
        notifyItemInserted(this.items.size() - 1);
    }

    public void addItem(int position, DIALOG dialog) {
        this.items.add(position, dialog);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        this.items.add(toPosition, this.items.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

    public void updateItem(int position, DIALOG item) {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        this.items.set(position, item);
        notifyItemChanged(position);
    }

    public void updateItemById(DIALOG item) {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(item.getId())) {
                this.items.set(i, item);
                notifyItemChanged(i);
                return;
            }
        }
    }

    public void upsertItem(DIALOG item) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.items.size()) {
                break;
            }
            if (this.items.get(i).getId().equals(item.getId())) {
                this.items.set(i, item);
                notifyItemChanged(i);
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        addItem(item);
    }

    public DIALOG getItemById(String id) {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        for (DIALOG dialog : this.items) {
            if (dialog.getId() == null && id == null) {
                return dialog;
            }
            if (dialog.getId() != null && dialog.getId().equals(id)) {
                return dialog;
            }
        }
        return null;
    }

    public boolean updateDialogWithMessage(String dialogId, IMessage message) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(dialogId)) {
                this.items.get(i).setLastMessage(message);
                notifyItemChanged(i);
                if (i != 0) {
                    Collections.swap(this.items, i, 0);
                    notifyItemMoved(i, 0);
                }
                return true;
            }
        }
        return false;
    }

    public void sortByLastMessageDate() {
        Collections.sort(this.items, new Comparator() { // from class: com.stfalcon.chatkit.dialogs.DialogsListAdapter$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DialogsListAdapter.lambda$sortByLastMessageDate$0((IDialog) obj, (IDialog) obj2);
            }
        });
        notifyDataSetChanged();
    }

    static /* synthetic */ int lambda$sortByLastMessageDate$0(IDialog iDialog, IDialog iDialog2) {
        if (iDialog.getLastMessage().getCreatedAt().after(iDialog2.getLastMessage().getCreatedAt())) {
            return -1;
        }
        return iDialog.getLastMessage().getCreatedAt().before(iDialog2.getLastMessage().getCreatedAt()) ? 1 : 0;
    }

    public void sort(Comparator<DIALOG> comparator) {
        Collections.sort(this.items, comparator);
        notifyDataSetChanged();
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public OnDialogClickListener getOnDialogClickListener() {
        return this.onDialogClickListener;
    }

    public void setOnDialogClickListener(OnDialogClickListener<DIALOG> onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public OnDialogViewClickListener getOnDialogViewClickListener() {
        return this.onDialogViewClickListener;
    }

    public void setOnDialogViewClickListener(OnDialogViewClickListener<DIALOG> clickListener) {
        this.onDialogViewClickListener = clickListener;
    }

    public OnDialogLongClickListener getOnLongItemClickListener() {
        return this.onLongItemClickListener;
    }

    public void setOnDialogLongClickListener(OnDialogLongClickListener<DIALOG> onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public OnDialogViewLongClickListener<DIALOG> getOnDialogViewLongClickListener() {
        return this.onDialogViewLongClickListener;
    }

    public void setOnDialogViewLongClickListener(OnDialogViewLongClickListener<DIALOG> clickListener) {
        this.onDialogViewLongClickListener = clickListener;
    }

    public void setDatesFormatter(DateFormatter.Formatter datesFormatter) {
        this.datesFormatter = datesFormatter;
    }

    void setStyle(DialogListStyle dialogStyle) {
        this.dialogStyle = dialogStyle;
    }

    public int getDialogPosition(DIALOG dialog) {
        return this.items.indexOf(dialog);
    }

    public static abstract class BaseDialogViewHolder<DIALOG extends IDialog> extends ViewHolder<DIALOG> {
        protected DateFormatter.Formatter datesFormatter;
        protected ImageLoader imageLoader;
        protected OnDialogClickListener<DIALOG> onDialogClickListener;
        protected OnDialogViewClickListener<DIALOG> onDialogViewClickListener;
        protected OnDialogViewLongClickListener<DIALOG> onDialogViewLongClickListener;
        protected OnDialogLongClickListener<DIALOG> onLongItemClickListener;

        public BaseDialogViewHolder(View itemView) {
            super(itemView);
        }

        void setImageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
        }

        protected void setOnDialogClickListener(OnDialogClickListener<DIALOG> onDialogClickListener) {
            this.onDialogClickListener = onDialogClickListener;
        }

        protected void setOnDialogViewClickListener(OnDialogViewClickListener<DIALOG> onDialogViewClickListener) {
            this.onDialogViewClickListener = onDialogViewClickListener;
        }

        protected void setOnLongItemClickListener(OnDialogLongClickListener<DIALOG> onLongItemClickListener) {
            this.onLongItemClickListener = onLongItemClickListener;
        }

        protected void setOnDialogViewLongClickListener(OnDialogViewLongClickListener<DIALOG> onDialogViewLongClickListener) {
            this.onDialogViewLongClickListener = onDialogViewLongClickListener;
        }

        public void setDatesFormatter(DateFormatter.Formatter dateHeadersFormatter) {
            this.datesFormatter = dateHeadersFormatter;
        }
    }

    public static class DialogViewHolder<DIALOG extends IDialog> extends BaseDialogViewHolder<DIALOG> {
        protected ViewGroup container;
        protected DialogListStyle dialogStyle;
        protected View divider;
        protected ViewGroup dividerContainer;
        protected ImageView ivAvatar;
        protected ImageView ivLastMessageUser;
        protected ViewGroup root;
        protected TextView tvBubble;
        protected TextView tvDate;
        protected TextView tvLastMessage;
        protected TextView tvName;

        public DialogViewHolder(View itemView) {
            super(itemView);
            this.root = (ViewGroup) itemView.findViewById(R.id.dialogRootLayout);
            this.container = (ViewGroup) itemView.findViewById(R.id.dialogContainer);
            this.tvName = (TextView) itemView.findViewById(R.id.dialogName);
            this.tvDate = (TextView) itemView.findViewById(R.id.dialogDate);
            this.tvLastMessage = (TextView) itemView.findViewById(R.id.dialogLastMessage);
            this.tvBubble = (TextView) itemView.findViewById(R.id.dialogUnreadBubble);
            this.ivLastMessageUser = (ImageView) itemView.findViewById(R.id.dialogLastMessageUserAvatar);
            this.ivAvatar = (ImageView) itemView.findViewById(R.id.dialogAvatar);
            this.dividerContainer = (ViewGroup) itemView.findViewById(R.id.dialogDividerContainer);
            this.divider = itemView.findViewById(R.id.dialogDivider);
        }

        private void applyStyle() {
            if (this.dialogStyle != null) {
                TextView textView = this.tvName;
                if (textView != null) {
                    textView.setTextSize(0, r0.getDialogTitleTextSize());
                }
                TextView textView2 = this.tvLastMessage;
                if (textView2 != null) {
                    textView2.setTextSize(0, this.dialogStyle.getDialogMessageTextSize());
                }
                TextView textView3 = this.tvDate;
                if (textView3 != null) {
                    textView3.setTextSize(0, this.dialogStyle.getDialogDateSize());
                }
                View view = this.divider;
                if (view != null) {
                    view.setBackgroundColor(this.dialogStyle.getDialogDividerColor());
                }
                ViewGroup viewGroup = this.dividerContainer;
                if (viewGroup != null) {
                    viewGroup.setPadding(this.dialogStyle.getDialogDividerLeftPadding(), 0, this.dialogStyle.getDialogDividerRightPadding(), 0);
                }
                ImageView imageView = this.ivAvatar;
                if (imageView != null) {
                    imageView.getLayoutParams().width = this.dialogStyle.getDialogAvatarWidth();
                    this.ivAvatar.getLayoutParams().height = this.dialogStyle.getDialogAvatarHeight();
                }
                ImageView imageView2 = this.ivLastMessageUser;
                if (imageView2 != null) {
                    imageView2.getLayoutParams().width = this.dialogStyle.getDialogMessageAvatarWidth();
                    this.ivLastMessageUser.getLayoutParams().height = this.dialogStyle.getDialogMessageAvatarHeight();
                }
                TextView textView4 = this.tvBubble;
                if (textView4 != null) {
                    ((GradientDrawable) textView4.getBackground()).setColor(this.dialogStyle.getDialogUnreadBubbleBackgroundColor());
                    this.tvBubble.setVisibility(this.dialogStyle.isDialogDividerEnabled() ? 0 : 8);
                    this.tvBubble.setTextSize(0, this.dialogStyle.getDialogUnreadBubbleTextSize());
                    this.tvBubble.setTextColor(this.dialogStyle.getDialogUnreadBubbleTextColor());
                    TextView textView5 = this.tvBubble;
                    textView5.setTypeface(textView5.getTypeface(), this.dialogStyle.getDialogUnreadBubbleTextStyle());
                }
            }
        }

        private void applyDefaultStyle() {
            DialogListStyle dialogListStyle = this.dialogStyle;
            if (dialogListStyle != null) {
                ViewGroup viewGroup = this.root;
                if (viewGroup != null) {
                    viewGroup.setBackgroundColor(dialogListStyle.getDialogItemBackground());
                }
                TextView textView = this.tvName;
                if (textView != null) {
                    textView.setTextColor(this.dialogStyle.getDialogTitleTextColor());
                    this.tvName.setTypeface(Typeface.DEFAULT, this.dialogStyle.getDialogTitleTextStyle());
                }
                TextView textView2 = this.tvDate;
                if (textView2 != null) {
                    textView2.setTextColor(this.dialogStyle.getDialogDateColor());
                    this.tvDate.setTypeface(Typeface.DEFAULT, this.dialogStyle.getDialogDateStyle());
                }
                TextView textView3 = this.tvLastMessage;
                if (textView3 != null) {
                    textView3.setTextColor(this.dialogStyle.getDialogMessageTextColor());
                    this.tvLastMessage.setTypeface(Typeface.DEFAULT, this.dialogStyle.getDialogMessageTextStyle());
                }
            }
        }

        private void applyUnreadStyle() {
            DialogListStyle dialogListStyle = this.dialogStyle;
            if (dialogListStyle != null) {
                ViewGroup viewGroup = this.root;
                if (viewGroup != null) {
                    viewGroup.setBackgroundColor(dialogListStyle.getDialogUnreadItemBackground());
                }
                TextView textView = this.tvName;
                if (textView != null) {
                    textView.setTextColor(this.dialogStyle.getDialogUnreadTitleTextColor());
                    this.tvName.setTypeface(Typeface.DEFAULT, this.dialogStyle.getDialogUnreadTitleTextStyle());
                }
                TextView textView2 = this.tvDate;
                if (textView2 != null) {
                    textView2.setTextColor(this.dialogStyle.getDialogUnreadDateColor());
                    this.tvDate.setTypeface(Typeface.DEFAULT, this.dialogStyle.getDialogUnreadDateStyle());
                }
                TextView textView3 = this.tvLastMessage;
                if (textView3 != null) {
                    textView3.setTextColor(this.dialogStyle.getDialogUnreadMessageTextColor());
                    this.tvLastMessage.setTypeface(Typeface.DEFAULT, this.dialogStyle.getDialogUnreadMessageTextStyle());
                }
            }
        }

        @Override // com.stfalcon.chatkit.commons.ViewHolder
        public void onBind(final DIALOG dialog) {
            if (dialog.getUnreadCount() > 0) {
                applyUnreadStyle();
            } else {
                applyDefaultStyle();
            }
            this.tvName.setText(dialog.getDialogName());
            if (dialog.getLastMessage() != null) {
                Date createdAt = dialog.getLastMessage().getCreatedAt();
                String dateString = this.datesFormatter != null ? this.datesFormatter.format(createdAt) : null;
                TextView textView = this.tvDate;
                if (dateString == null) {
                    dateString = getDateString(createdAt);
                }
                textView.setText(dateString);
            } else {
                this.tvDate.setText((CharSequence) null);
            }
            if (this.imageLoader != null) {
                this.imageLoader.loadImage(this.ivAvatar, dialog.getDialogPhoto(), null);
            }
            if (this.imageLoader != null && dialog.getLastMessage() != null) {
                this.imageLoader.loadImage(this.ivLastMessageUser, dialog.getLastMessage().getUser().getAvatar(), null);
            }
            this.ivLastMessageUser.setVisibility((!this.dialogStyle.isDialogMessageAvatarEnabled() || dialog.getUsers().size() <= 1 || dialog.getLastMessage() == null) ? 8 : 0);
            if (dialog.getLastMessage() != null) {
                this.tvLastMessage.setText(dialog.getLastMessage().getText());
            } else {
                this.tvLastMessage.setText((CharSequence) null);
            }
            this.tvBubble.setText(String.valueOf(dialog.getUnreadCount()));
            this.tvBubble.setVisibility((!this.dialogStyle.isDialogUnreadBubbleEnabled() || dialog.getUnreadCount() <= 0) ? 8 : 0);
            this.container.setOnClickListener(new View.OnClickListener() { // from class: com.stfalcon.chatkit.dialogs.DialogsListAdapter$DialogViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m1083x81e2c316(dialog, view);
                }
            });
            this.container.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.stfalcon.chatkit.dialogs.DialogsListAdapter$DialogViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return this.f$0.m1084xec124b35(dialog, view);
                }
            });
        }

        /* renamed from: lambda$onBind$0$com-stfalcon-chatkit-dialogs-DialogsListAdapter$DialogViewHolder, reason: not valid java name */
        public /* synthetic */ void m1083x81e2c316(IDialog iDialog, View view) {
            if (this.onDialogClickListener != null) {
                this.onDialogClickListener.onDialogClick(iDialog);
            }
            if (this.onDialogViewClickListener != null) {
                this.onDialogViewClickListener.onDialogViewClick(view, iDialog);
            }
        }

        /* renamed from: lambda$onBind$1$com-stfalcon-chatkit-dialogs-DialogsListAdapter$DialogViewHolder, reason: not valid java name */
        public /* synthetic */ boolean m1084xec124b35(IDialog iDialog, View view) {
            if (this.onLongItemClickListener != null) {
                this.onLongItemClickListener.onDialogLongClick(iDialog);
            }
            if (this.onDialogViewLongClickListener != null) {
                this.onDialogViewLongClickListener.onDialogViewLongClick(view, iDialog);
            }
            return (this.onLongItemClickListener == null && this.onDialogViewLongClickListener == null) ? false : true;
        }

        protected String getDateString(Date date) {
            return DateFormatter.format(date, DateFormatter.Template.TIME);
        }

        protected DialogListStyle getDialogStyle() {
            return this.dialogStyle;
        }

        protected void setDialogStyle(DialogListStyle dialogStyle) {
            this.dialogStyle = dialogStyle;
            applyStyle();
        }
    }
}
