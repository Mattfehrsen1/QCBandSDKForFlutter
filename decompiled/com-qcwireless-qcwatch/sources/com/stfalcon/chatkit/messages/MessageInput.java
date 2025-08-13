package com.stfalcon.chatkit.messages;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.R;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public class MessageInput extends RelativeLayout implements View.OnClickListener, TextWatcher, View.OnFocusChangeListener {
    protected ImageButton attachmentButton;
    protected Space attachmentButtonSpace;
    private AttachmentsListener attachmentsListener;
    private int delayTypingStatusMillis;
    private CharSequence input;
    private InputListener inputListener;
    private boolean isTyping;
    private boolean lastFocus;
    protected EditText messageInput;
    protected ImageButton messageSendButton;
    protected Space sendButtonSpace;
    private TypingListener typingListener;
    private Runnable typingTimerRunnable;

    public interface AttachmentsListener {
        void onAddAttachments();
    }

    public interface InputListener {
        boolean onSubmit(CharSequence input);
    }

    public interface TypingListener {
        void onStartTyping();

        void onStopTyping();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public MessageInput(Context context) {
        super(context);
        this.typingTimerRunnable = new Runnable() { // from class: com.stfalcon.chatkit.messages.MessageInput.1
            @Override // java.lang.Runnable
            public void run() {
                if (MessageInput.this.isTyping) {
                    MessageInput.this.isTyping = false;
                    if (MessageInput.this.typingListener != null) {
                        MessageInput.this.typingListener.onStopTyping();
                    }
                }
            }
        };
        init(context);
    }

    public MessageInput(Context context, AttributeSet attrs) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super(context, attrs);
        this.typingTimerRunnable = new Runnable() { // from class: com.stfalcon.chatkit.messages.MessageInput.1
            @Override // java.lang.Runnable
            public void run() {
                if (MessageInput.this.isTyping) {
                    MessageInput.this.isTyping = false;
                    if (MessageInput.this.typingListener != null) {
                        MessageInput.this.typingListener.onStopTyping();
                    }
                }
            }
        };
        init(context, attrs);
    }

    public MessageInput(Context context, AttributeSet attrs, int defStyleAttr) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        this.typingTimerRunnable = new Runnable() { // from class: com.stfalcon.chatkit.messages.MessageInput.1
            @Override // java.lang.Runnable
            public void run() {
                if (MessageInput.this.isTyping) {
                    MessageInput.this.isTyping = false;
                    if (MessageInput.this.typingListener != null) {
                        MessageInput.this.typingListener.onStopTyping();
                    }
                }
            }
        };
        init(context, attrs);
    }

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public void setAttachmentsListener(AttachmentsListener attachmentsListener) {
        this.attachmentsListener = attachmentsListener;
    }

    public EditText getInputEditText() {
        return this.messageInput;
    }

    public ImageButton getButton() {
        return this.messageSendButton;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.messageSendButton) {
            if (id == R.id.attachmentButton) {
                onAddAttachments();
            }
        } else {
            if (onSubmit()) {
                this.messageInput.setText("");
            }
            removeCallbacks(this.typingTimerRunnable);
            post(this.typingTimerRunnable);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        this.input = s;
        this.messageSendButton.setEnabled(s.length() > 0);
        if (s.length() > 0) {
            if (!this.isTyping) {
                this.isTyping = true;
                TypingListener typingListener = this.typingListener;
                if (typingListener != null) {
                    typingListener.onStartTyping();
                }
            }
            removeCallbacks(this.typingTimerRunnable);
            postDelayed(this.typingTimerRunnable, this.delayTypingStatusMillis);
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        TypingListener typingListener;
        if (this.lastFocus && !hasFocus && (typingListener = this.typingListener) != null) {
            typingListener.onStopTyping();
        }
        this.lastFocus = hasFocus;
    }

    private boolean onSubmit() {
        InputListener inputListener = this.inputListener;
        return inputListener != null && inputListener.onSubmit(this.input);
    }

    private void onAddAttachments() {
        AttachmentsListener attachmentsListener = this.attachmentsListener;
        if (attachmentsListener != null) {
            attachmentsListener.onAddAttachments();
        }
    }

    private void init(Context context, AttributeSet attrs) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        init(context);
        MessageInputStyle messageInputStyle = MessageInputStyle.parse(context, attrs);
        this.messageInput.setMaxLines(messageInputStyle.getInputMaxLines());
        this.messageInput.setHint(messageInputStyle.getInputHint());
        this.messageInput.setText(messageInputStyle.getInputText());
        this.messageInput.setTextSize(0, messageInputStyle.getInputTextSize());
        this.messageInput.setTextColor(messageInputStyle.getInputTextColor());
        this.messageInput.setHintTextColor(messageInputStyle.getInputHintColor());
        ViewCompat.setBackground(this.messageInput, messageInputStyle.getInputBackground());
        setCursor(messageInputStyle.getInputCursorDrawable());
        this.attachmentButton.setVisibility(messageInputStyle.showAttachmentButton() ? 0 : 8);
        this.attachmentButton.setImageDrawable(messageInputStyle.getAttachmentButtonIcon());
        this.attachmentButton.getLayoutParams().width = messageInputStyle.getAttachmentButtonWidth();
        this.attachmentButton.getLayoutParams().height = messageInputStyle.getAttachmentButtonHeight();
        ViewCompat.setBackground(this.attachmentButton, messageInputStyle.getAttachmentButtonBackground());
        this.attachmentButtonSpace.setVisibility(messageInputStyle.showAttachmentButton() ? 0 : 8);
        this.attachmentButtonSpace.getLayoutParams().width = messageInputStyle.getAttachmentButtonMargin();
        this.messageSendButton.setImageDrawable(messageInputStyle.getInputButtonIcon());
        this.messageSendButton.getLayoutParams().width = messageInputStyle.getInputButtonWidth();
        this.messageSendButton.getLayoutParams().height = messageInputStyle.getInputButtonHeight();
        ViewCompat.setBackground(this.messageSendButton, messageInputStyle.getInputButtonBackground());
        this.sendButtonSpace.getLayoutParams().width = messageInputStyle.getInputButtonMargin();
        if (getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
            setPadding(messageInputStyle.getInputDefaultPaddingLeft(), messageInputStyle.getInputDefaultPaddingTop(), messageInputStyle.getInputDefaultPaddingRight(), messageInputStyle.getInputDefaultPaddingBottom());
        }
        this.delayTypingStatusMillis = messageInputStyle.getDelayTypingStatus();
    }

    private void init(Context context) {
        inflate(context, R.layout.view_message_input, this);
        this.messageInput = (EditText) findViewById(R.id.messageInput);
        this.messageSendButton = (ImageButton) findViewById(R.id.messageSendButton);
        this.attachmentButton = (ImageButton) findViewById(R.id.attachmentButton);
        this.sendButtonSpace = (Space) findViewById(R.id.sendButtonSpace);
        this.attachmentButtonSpace = (Space) findViewById(R.id.attachmentButtonSpace);
        this.messageSendButton.setOnClickListener(this);
        this.attachmentButton.setOnClickListener(this);
        this.messageInput.addTextChangedListener(this);
        this.messageInput.setText("");
        this.messageInput.setOnFocusChangeListener(this);
    }

    public void setInputTextColor(int color) {
        this.messageInput.setTextColor(color);
        this.messageInput.setHintTextColor(color);
    }

    private void setCursor(Drawable drawable) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Object obj;
        Class<?> cls;
        if (drawable == null) {
            return;
        }
        try {
            TextView.class.getDeclaredField("mCursorDrawableRes").setAccessible(true);
            if (Build.VERSION.SDK_INT < 16) {
                obj = this.messageInput;
                cls = TextView.class;
            } else {
                Field declaredField = TextView.class.getDeclaredField("mEditor");
                declaredField.setAccessible(true);
                obj = declaredField.get(this.messageInput);
                cls = obj.getClass();
            }
            Field declaredField2 = cls.getDeclaredField("mCursorDrawable");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new Drawable[]{drawable, drawable});
        } catch (Exception unused) {
        }
    }

    public void setTypingListener(TypingListener typingListener) {
        this.typingListener = typingListener;
    }
}
