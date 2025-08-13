package com.stfalcon.chatkit.messages;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.graphics.drawable.DrawableCompat;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.commons.Style;

/* loaded from: classes4.dex */
class MessageInputStyle extends Style {
    private static final int DEFAULT_DELAY_TYPING_STATUS = 1500;
    private static final int DEFAULT_MAX_LINES = 5;
    private int attachmentButtonBackground;
    private int attachmentButtonDefaultBgColor;
    private int attachmentButtonDefaultBgDisabledColor;
    private int attachmentButtonDefaultBgPressedColor;
    private int attachmentButtonDefaultIconColor;
    private int attachmentButtonDefaultIconDisabledColor;
    private int attachmentButtonDefaultIconPressedColor;
    private int attachmentButtonHeight;
    private int attachmentButtonIcon;
    private int attachmentButtonMargin;
    private int attachmentButtonWidth;
    private int delayTypingStatus;
    private Drawable inputBackground;
    private int inputButtonBackground;
    private int inputButtonDefaultBgColor;
    private int inputButtonDefaultBgDisabledColor;
    private int inputButtonDefaultBgPressedColor;
    private int inputButtonDefaultIconColor;
    private int inputButtonDefaultIconDisabledColor;
    private int inputButtonDefaultIconPressedColor;
    private int inputButtonHeight;
    private int inputButtonIcon;
    private int inputButtonMargin;
    private int inputButtonWidth;
    private Drawable inputCursorDrawable;
    private int inputDefaultPaddingBottom;
    private int inputDefaultPaddingLeft;
    private int inputDefaultPaddingRight;
    private int inputDefaultPaddingTop;
    private String inputHint;
    private int inputHintColor;
    private int inputMaxLines;
    private String inputText;
    private int inputTextColor;
    private int inputTextSize;
    private boolean showAttachmentButton;

    static MessageInputStyle parse(Context context, AttributeSet attrs) {
        MessageInputStyle messageInputStyle = new MessageInputStyle(context, attrs);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MessageInput);
        messageInputStyle.showAttachmentButton = typedArrayObtainStyledAttributes.getBoolean(31, false);
        messageInputStyle.attachmentButtonBackground = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        messageInputStyle.attachmentButtonDefaultBgColor = typedArrayObtainStyledAttributes.getColor(1, messageInputStyle.getColor(R.color.white_four));
        messageInputStyle.attachmentButtonDefaultBgPressedColor = typedArrayObtainStyledAttributes.getColor(3, messageInputStyle.getColor(R.color.white_five));
        messageInputStyle.attachmentButtonDefaultBgDisabledColor = typedArrayObtainStyledAttributes.getColor(2, messageInputStyle.getColor(R.color.transparent));
        messageInputStyle.attachmentButtonIcon = typedArrayObtainStyledAttributes.getResourceId(8, -1);
        messageInputStyle.attachmentButtonDefaultIconColor = typedArrayObtainStyledAttributes.getColor(4, messageInputStyle.getColor(R.color.cornflower_blue_two));
        messageInputStyle.attachmentButtonDefaultIconPressedColor = typedArrayObtainStyledAttributes.getColor(6, messageInputStyle.getColor(R.color.cornflower_blue_two_dark));
        messageInputStyle.attachmentButtonDefaultIconDisabledColor = typedArrayObtainStyledAttributes.getColor(5, messageInputStyle.getColor(R.color.cornflower_blue_light_40));
        messageInputStyle.attachmentButtonWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, messageInputStyle.getDimension(R.dimen.input_button_width));
        messageInputStyle.attachmentButtonHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, messageInputStyle.getDimension(R.dimen.input_button_height));
        messageInputStyle.attachmentButtonMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, messageInputStyle.getDimension(R.dimen.input_button_margin));
        messageInputStyle.inputButtonBackground = typedArrayObtainStyledAttributes.getResourceId(13, -1);
        messageInputStyle.inputButtonDefaultBgColor = typedArrayObtainStyledAttributes.getColor(14, messageInputStyle.getColor(R.color.cornflower_blue_two));
        messageInputStyle.inputButtonDefaultBgPressedColor = typedArrayObtainStyledAttributes.getColor(16, messageInputStyle.getColor(R.color.cornflower_blue_two_dark));
        messageInputStyle.inputButtonDefaultBgDisabledColor = typedArrayObtainStyledAttributes.getColor(15, messageInputStyle.getColor(R.color.white_four));
        messageInputStyle.inputButtonIcon = typedArrayObtainStyledAttributes.getResourceId(21, -1);
        messageInputStyle.inputButtonDefaultIconColor = typedArrayObtainStyledAttributes.getColor(17, messageInputStyle.getColor(R.color.white));
        messageInputStyle.inputButtonDefaultIconPressedColor = typedArrayObtainStyledAttributes.getColor(19, messageInputStyle.getColor(R.color.white));
        messageInputStyle.inputButtonDefaultIconDisabledColor = typedArrayObtainStyledAttributes.getColor(18, messageInputStyle.getColor(R.color.warm_grey));
        messageInputStyle.inputButtonWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(23, messageInputStyle.getDimension(R.dimen.input_button_width));
        messageInputStyle.inputButtonHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(20, messageInputStyle.getDimension(R.dimen.input_button_height));
        messageInputStyle.inputButtonMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(22, messageInputStyle.getDimension(R.dimen.input_button_margin));
        messageInputStyle.inputMaxLines = typedArrayObtainStyledAttributes.getInt(27, 5);
        messageInputStyle.inputHint = typedArrayObtainStyledAttributes.getString(25);
        messageInputStyle.inputText = typedArrayObtainStyledAttributes.getString(28);
        messageInputStyle.inputTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(30, messageInputStyle.getDimension(R.dimen.input_text_size));
        messageInputStyle.inputTextColor = typedArrayObtainStyledAttributes.getColor(29, messageInputStyle.getColor(R.color.dark_grey_two));
        messageInputStyle.inputHintColor = typedArrayObtainStyledAttributes.getColor(26, messageInputStyle.getColor(R.color.warm_grey_three));
        messageInputStyle.inputBackground = typedArrayObtainStyledAttributes.getDrawable(12);
        messageInputStyle.inputCursorDrawable = typedArrayObtainStyledAttributes.getDrawable(24);
        messageInputStyle.delayTypingStatus = typedArrayObtainStyledAttributes.getInt(11, 1500);
        typedArrayObtainStyledAttributes.recycle();
        messageInputStyle.inputDefaultPaddingLeft = messageInputStyle.getDimension(R.dimen.input_padding_left);
        messageInputStyle.inputDefaultPaddingRight = messageInputStyle.getDimension(R.dimen.input_padding_right);
        messageInputStyle.inputDefaultPaddingTop = messageInputStyle.getDimension(R.dimen.input_padding_top);
        messageInputStyle.inputDefaultPaddingBottom = messageInputStyle.getDimension(R.dimen.input_padding_bottom);
        return messageInputStyle;
    }

    private MessageInputStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Drawable getSelector(int normalColor, int pressedColor, int disabledColor, int shape) {
        Drawable drawableMutate = DrawableCompat.wrap(getVectorDrawable(shape)).mutate();
        DrawableCompat.setTintList(drawableMutate, new ColorStateList(new int[][]{new int[]{android.R.attr.state_enabled, -16842919}, new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, new int[]{-16842910}}, new int[]{normalColor, pressedColor, disabledColor}));
        return drawableMutate;
    }

    protected boolean showAttachmentButton() {
        return this.showAttachmentButton;
    }

    protected Drawable getAttachmentButtonBackground() {
        int i = this.attachmentButtonBackground;
        if (i == -1) {
            return getSelector(this.attachmentButtonDefaultBgColor, this.attachmentButtonDefaultBgPressedColor, this.attachmentButtonDefaultBgDisabledColor, R.mipmap.mask);
        }
        return getDrawable(i);
    }

    protected Drawable getAttachmentButtonIcon() {
        int i = this.attachmentButtonIcon;
        if (i == -1) {
            return getSelector(this.attachmentButtonDefaultIconColor, this.attachmentButtonDefaultIconPressedColor, this.attachmentButtonDefaultIconDisabledColor, R.mipmap.ic_add_attachment);
        }
        return getDrawable(i);
    }

    protected int getAttachmentButtonWidth() {
        return this.attachmentButtonWidth;
    }

    protected int getAttachmentButtonHeight() {
        return this.attachmentButtonHeight;
    }

    protected int getAttachmentButtonMargin() {
        return this.attachmentButtonMargin;
    }

    protected Drawable getInputButtonBackground() {
        int i = this.inputButtonBackground;
        if (i == -1) {
            return getSelector(this.inputButtonDefaultBgColor, this.inputButtonDefaultBgPressedColor, this.inputButtonDefaultBgDisabledColor, R.mipmap.mask);
        }
        return getDrawable(i);
    }

    protected Drawable getInputButtonIcon() {
        int i = this.inputButtonIcon;
        if (i == -1) {
            return getSelector(this.inputButtonDefaultIconColor, this.inputButtonDefaultIconPressedColor, this.inputButtonDefaultIconDisabledColor, R.mipmap.ic_send);
        }
        return getDrawable(i);
    }

    protected int getInputButtonMargin() {
        return this.inputButtonMargin;
    }

    protected int getInputButtonWidth() {
        return this.inputButtonWidth;
    }

    protected int getInputButtonHeight() {
        return this.inputButtonHeight;
    }

    protected int getInputMaxLines() {
        return this.inputMaxLines;
    }

    protected String getInputHint() {
        return this.inputHint;
    }

    protected String getInputText() {
        return this.inputText;
    }

    protected int getInputTextSize() {
        return this.inputTextSize;
    }

    protected int getInputTextColor() {
        return this.inputTextColor;
    }

    protected int getInputHintColor() {
        return this.inputHintColor;
    }

    protected Drawable getInputBackground() {
        return this.inputBackground;
    }

    protected Drawable getInputCursorDrawable() {
        return this.inputCursorDrawable;
    }

    protected int getInputDefaultPaddingLeft() {
        return this.inputDefaultPaddingLeft;
    }

    protected int getInputDefaultPaddingRight() {
        return this.inputDefaultPaddingRight;
    }

    protected int getInputDefaultPaddingTop() {
        return this.inputDefaultPaddingTop;
    }

    protected int getInputDefaultPaddingBottom() {
        return this.inputDefaultPaddingBottom;
    }

    int getDelayTypingStatus() {
        return this.delayTypingStatus;
    }
}
