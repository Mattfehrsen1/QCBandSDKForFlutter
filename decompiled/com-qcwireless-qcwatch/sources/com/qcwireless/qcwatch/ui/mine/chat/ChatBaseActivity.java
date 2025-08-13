package com.qcwireless.qcwatch.ui.mine.chat;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.mine.chat.bean.Message;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChatBaseActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0018\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0015H\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u0015H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/ChatBaseActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Lcom/stfalcon/chatkit/messages/MessagesListAdapter$SelectionListener;", "Lcom/stfalcon/chatkit/messages/MessagesListAdapter$OnLoadMoreListener;", "()V", "imageLoader", "Lcom/stfalcon/chatkit/commons/ImageLoader;", "getImageLoader", "()Lcom/stfalcon/chatkit/commons/ImageLoader;", "setImageLoader", "(Lcom/stfalcon/chatkit/commons/ImageLoader;)V", "menu", "Landroid/view/Menu;", "messagesAdapter", "Lcom/stfalcon/chatkit/messages/MessagesListAdapter;", "Lcom/qcwireless/qcwatch/ui/mine/chat/bean/Message;", "getMessagesAdapter", "()Lcom/stfalcon/chatkit/messages/MessagesListAdapter;", "setMessagesAdapter", "(Lcom/stfalcon/chatkit/messages/MessagesListAdapter;)V", "selectionCount", "", "senderId", "", "getSenderId", "()Ljava/lang/String;", "getMessageStringFormatter", "Lcom/stfalcon/chatkit/messages/MessagesListAdapter$Formatter;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLoadMore", "page", "totalItemsCount", "onSelectionChanged", "count", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ChatBaseActivity extends BaseActivity implements MessagesListAdapter.SelectionListener, MessagesListAdapter.OnLoadMoreListener {
    public ImageLoader imageLoader;
    private Menu menu;
    public MessagesListAdapter<Message> messagesAdapter;
    private int selectionCount;
    private final String senderId = "0";

    private final MessagesListAdapter.Formatter<Message> getMessageStringFormatter() {
        return new MessagesListAdapter.Formatter() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatBaseActivity$$ExternalSyntheticLambda1
            @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.Formatter
            public final String format(Object obj) {
                return ChatBaseActivity.m903getMessageStringFormatter$lambda1((Message) obj);
            }
        };
    }

    @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.OnLoadMoreListener
    public void onLoadMore(int page, int totalItemsCount) {
    }

    @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.SelectionListener
    public void onSelectionChanged(int count) {
    }

    public final String getSenderId() {
        return this.senderId;
    }

    public final ImageLoader getImageLoader() {
        ImageLoader imageLoader = this.imageLoader;
        if (imageLoader != null) {
            return imageLoader;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageLoader");
        return null;
    }

    public final void setImageLoader(ImageLoader imageLoader) {
        Intrinsics.checkNotNullParameter(imageLoader, "<set-?>");
        this.imageLoader = imageLoader;
    }

    public final MessagesListAdapter<Message> getMessagesAdapter() {
        MessagesListAdapter<Message> messagesListAdapter = this.messagesAdapter;
        if (messagesListAdapter != null) {
            return messagesListAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("messagesAdapter");
        return null;
    }

    public final void setMessagesAdapter(MessagesListAdapter<Message> messagesListAdapter) {
        Intrinsics.checkNotNullParameter(messagesListAdapter, "<set-?>");
        this.messagesAdapter = messagesListAdapter;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImageLoader(new ImageLoader() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatBaseActivity$$ExternalSyntheticLambda0
            @Override // com.stfalcon.chatkit.commons.ImageLoader
            public final void loadImage(ImageView imageView, String str, Object obj) {
                ChatBaseActivity.m904onCreate$lambda0(imageView, str, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m904onCreate$lambda0(ImageView imageView, String str, Object obj) {
        Picasso.get().load(str).into(imageView);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.selectionCount == 0) {
            super.onBackPressed();
            return;
        }
        MessagesListAdapter<Message> messagesAdapter = getMessagesAdapter();
        Intrinsics.checkNotNull(messagesAdapter);
        messagesAdapter.unselectAllItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getMessageStringFormatter$lambda-1, reason: not valid java name */
    public static final String m903getMessageStringFormatter$lambda1(Message message) {
        Intrinsics.checkNotNullParameter(message, "message");
        String str = new SimpleDateFormat("MMM d, EEE 'at' h:mm a", Locale.US).format(message.getCreatedAt());
        String text = message.getText();
        if (text == null) {
            text = "[attachment]";
        }
        return String.format(Locale.getDefault(), "%s: %s (%s)", message.getUser().getName(), text, str);
    }
}
