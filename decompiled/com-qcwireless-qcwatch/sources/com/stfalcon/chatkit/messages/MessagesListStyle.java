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
public class MessagesListStyle extends Style {
    private String dateHeaderFormat;
    private int dateHeaderPadding;
    private int dateHeaderTextColor;
    private int dateHeaderTextSize;
    private int dateHeaderTextStyle;
    public Drawable inComing;
    private int incomingAvatarHeight;
    private int incomingAvatarWidth;
    public int incomingBubbleDrawable;
    private int incomingDefaultBubbleColor;
    private int incomingDefaultBubblePaddingBottom;
    private int incomingDefaultBubblePaddingLeft;
    private int incomingDefaultBubblePaddingRight;
    private int incomingDefaultBubblePaddingTop;
    private int incomingDefaultBubblePressedColor;
    private int incomingDefaultBubbleSelectedColor;
    private int incomingDefaultImageOverlayPressedColor;
    private int incomingDefaultImageOverlaySelectedColor;
    private int incomingImageOverlayDrawable;
    public int incomingImageTimeTextColor;
    private int incomingImageTimeTextSize;
    private int incomingImageTimeTextStyle;
    private int incomingQuestionTextColor;
    public int incomingTextColor;
    private int incomingTextLinkColor;
    private int incomingTextSize;
    private int incomingTextStyle;
    public int incomingTimeTextColor;
    private int incomingTimeTextSize;
    private int incomingTimeTextStyle;
    public Drawable outComing;
    public int outcomingBubbleDrawable;
    private int outcomingDefaultBubbleColor;
    private int outcomingDefaultBubblePaddingBottom;
    private int outcomingDefaultBubblePaddingLeft;
    private int outcomingDefaultBubblePaddingRight;
    private int outcomingDefaultBubblePaddingTop;
    private int outcomingDefaultBubblePressedColor;
    private int outcomingDefaultBubbleSelectedColor;
    private int outcomingDefaultImageOverlayPressedColor;
    private int outcomingDefaultImageOverlaySelectedColor;
    private int outcomingImageOverlayDrawable;
    public int outcomingImageTimeTextColor;
    private int outcomingImageTimeTextSize;
    private int outcomingImageTimeTextStyle;
    public int outcomingTextColor;
    private int outcomingTextLinkColor;
    private int outcomingTextSize;
    private int outcomingTextStyle;
    public int outcomingTimeTextColor;
    private int outcomingTimeTextSize;
    private int outcomingTimeTextStyle;
    private int textAutoLinkMask;

    static MessagesListStyle parse(Context context, AttributeSet attrs) {
        MessagesListStyle messagesListStyle = new MessagesListStyle(context, attrs);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MessagesList);
        messagesListStyle.textAutoLinkMask = typedArrayObtainStyledAttributes.getInt(50, 0);
        messagesListStyle.incomingTextLinkColor = typedArrayObtainStyledAttributes.getColor(23, messagesListStyle.getColor(R.color.white_two));
        messagesListStyle.outcomingTextLinkColor = typedArrayObtainStyledAttributes.getColor(44, messagesListStyle.getColor(R.color.white_two));
        messagesListStyle.incomingAvatarWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, messagesListStyle.getDimension(R.dimen.message_avatar_width));
        messagesListStyle.incomingAvatarHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, messagesListStyle.getDimension(R.dimen.message_avatar_height));
        messagesListStyle.incomingBubbleDrawable = typedArrayObtainStyledAttributes.getResourceId(7, -1);
        messagesListStyle.incomingDefaultBubbleColor = typedArrayObtainStyledAttributes.getColor(12, messagesListStyle.getColor(R.color.white_two));
        messagesListStyle.incomingDefaultBubblePressedColor = typedArrayObtainStyledAttributes.getColor(13, messagesListStyle.getColor(R.color.white_two));
        messagesListStyle.incomingDefaultBubbleSelectedColor = typedArrayObtainStyledAttributes.getColor(14, messagesListStyle.getColor(R.color.cornflower_blue_two_24));
        messagesListStyle.incomingImageOverlayDrawable = typedArrayObtainStyledAttributes.getResourceId(17, -1);
        messagesListStyle.incomingDefaultImageOverlayPressedColor = typedArrayObtainStyledAttributes.getColor(15, messagesListStyle.getColor(R.color.transparent));
        messagesListStyle.incomingDefaultImageOverlaySelectedColor = typedArrayObtainStyledAttributes.getColor(16, messagesListStyle.getColor(R.color.cornflower_blue_light_40));
        messagesListStyle.incomingDefaultBubblePaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, messagesListStyle.getDimension(R.dimen.message_padding_left));
        messagesListStyle.incomingDefaultBubblePaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, messagesListStyle.getDimension(R.dimen.message_padding_right));
        messagesListStyle.incomingDefaultBubblePaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, messagesListStyle.getDimension(R.dimen.message_padding_top));
        messagesListStyle.incomingDefaultBubblePaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, messagesListStyle.getDimension(R.dimen.message_padding_bottom));
        messagesListStyle.incomingTextColor = typedArrayObtainStyledAttributes.getColor(22, messagesListStyle.getColor(R.color.dark_grey_two));
        messagesListStyle.incomingQuestionTextColor = typedArrayObtainStyledAttributes.getColor(21, messagesListStyle.getColor(R.color.dark_grey_two));
        messagesListStyle.incomingTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(24, messagesListStyle.getDimension(R.dimen.message_text_size));
        messagesListStyle.incomingTextStyle = typedArrayObtainStyledAttributes.getInt(25, 0);
        messagesListStyle.incomingTimeTextColor = typedArrayObtainStyledAttributes.getColor(26, messagesListStyle.getColor(R.color.warm_grey_four));
        messagesListStyle.incomingTimeTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(27, messagesListStyle.getDimension(R.dimen.message_time_text_size));
        messagesListStyle.incomingTimeTextStyle = typedArrayObtainStyledAttributes.getInt(28, 0);
        messagesListStyle.incomingImageTimeTextColor = typedArrayObtainStyledAttributes.getColor(18, messagesListStyle.getColor(R.color.warm_grey_four));
        messagesListStyle.incomingImageTimeTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(19, messagesListStyle.getDimension(R.dimen.message_time_text_size));
        messagesListStyle.incomingImageTimeTextStyle = typedArrayObtainStyledAttributes.getInt(20, 0);
        messagesListStyle.outcomingBubbleDrawable = typedArrayObtainStyledAttributes.getResourceId(29, -1);
        messagesListStyle.outcomingDefaultBubbleColor = typedArrayObtainStyledAttributes.getColor(34, messagesListStyle.getColor(R.color.cornflower_blue_two));
        messagesListStyle.outcomingDefaultBubblePressedColor = typedArrayObtainStyledAttributes.getColor(35, messagesListStyle.getColor(R.color.cornflower_blue_two));
        messagesListStyle.outcomingDefaultBubbleSelectedColor = typedArrayObtainStyledAttributes.getColor(36, messagesListStyle.getColor(R.color.cornflower_blue_two_24));
        messagesListStyle.outcomingImageOverlayDrawable = typedArrayObtainStyledAttributes.getResourceId(39, -1);
        messagesListStyle.outcomingDefaultImageOverlayPressedColor = typedArrayObtainStyledAttributes.getColor(37, messagesListStyle.getColor(R.color.transparent));
        messagesListStyle.outcomingDefaultImageOverlaySelectedColor = typedArrayObtainStyledAttributes.getColor(38, messagesListStyle.getColor(R.color.cornflower_blue_light_40));
        messagesListStyle.outcomingDefaultBubblePaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(31, messagesListStyle.getDimension(R.dimen.message_padding_left));
        messagesListStyle.outcomingDefaultBubblePaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(32, messagesListStyle.getDimension(R.dimen.message_padding_right));
        messagesListStyle.outcomingDefaultBubblePaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(33, messagesListStyle.getDimension(R.dimen.message_padding_top));
        messagesListStyle.outcomingDefaultBubblePaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(30, messagesListStyle.getDimension(R.dimen.message_padding_bottom));
        messagesListStyle.outcomingTextColor = typedArrayObtainStyledAttributes.getColor(43, messagesListStyle.getColor(R.color.white));
        messagesListStyle.outcomingTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(45, messagesListStyle.getDimension(R.dimen.message_text_size));
        messagesListStyle.outcomingTextStyle = typedArrayObtainStyledAttributes.getInt(46, 0);
        messagesListStyle.outcomingTimeTextColor = typedArrayObtainStyledAttributes.getColor(47, messagesListStyle.getColor(R.color.white60));
        messagesListStyle.outcomingTimeTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(48, messagesListStyle.getDimension(R.dimen.message_time_text_size));
        messagesListStyle.outcomingTimeTextStyle = typedArrayObtainStyledAttributes.getInt(49, 0);
        messagesListStyle.outcomingImageTimeTextColor = typedArrayObtainStyledAttributes.getColor(40, messagesListStyle.getColor(R.color.warm_grey_four));
        messagesListStyle.outcomingImageTimeTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(41, messagesListStyle.getDimension(R.dimen.message_time_text_size));
        messagesListStyle.outcomingImageTimeTextStyle = typedArrayObtainStyledAttributes.getInt(42, 0);
        messagesListStyle.dateHeaderPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, messagesListStyle.getDimension(R.dimen.message_date_header_padding));
        messagesListStyle.dateHeaderFormat = typedArrayObtainStyledAttributes.getString(0);
        messagesListStyle.dateHeaderTextColor = typedArrayObtainStyledAttributes.getColor(2, messagesListStyle.getColor(R.color.warm_grey_two));
        messagesListStyle.dateHeaderTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, messagesListStyle.getDimension(R.dimen.message_date_header_text_size));
        messagesListStyle.dateHeaderTextStyle = typedArrayObtainStyledAttributes.getInt(4, 0);
        typedArrayObtainStyledAttributes.recycle();
        return messagesListStyle;
    }

    private MessagesListStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Drawable getMessageSelector(int normalColor, int selectedColor, int pressedColor, int shape) {
        Drawable drawableMutate = DrawableCompat.wrap(getVectorDrawable(shape)).mutate();
        DrawableCompat.setTintList(drawableMutate, new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{android.R.attr.state_pressed}, new int[]{-16842919, -16842913}}, new int[]{selectedColor, pressedColor, normalColor}));
        return drawableMutate;
    }

    public void setInComingDrawable(Drawable drawable) {
        this.inComing = drawable;
    }

    public void setOutComingDrawable(Drawable drawable) {
        this.outComing = drawable;
    }

    protected int getTextAutoLinkMask() {
        return this.textAutoLinkMask;
    }

    protected int getIncomingTextLinkColor() {
        return this.incomingTextLinkColor;
    }

    protected int getOutcomingTextLinkColor() {
        return this.outcomingTextLinkColor;
    }

    protected int getIncomingAvatarWidth() {
        return this.incomingAvatarWidth;
    }

    protected int getIncomingAvatarHeight() {
        return this.incomingAvatarHeight;
    }

    protected int getIncomingDefaultBubblePaddingLeft() {
        return this.incomingDefaultBubblePaddingLeft;
    }

    protected int getIncomingDefaultBubblePaddingRight() {
        return this.incomingDefaultBubblePaddingRight;
    }

    protected int getIncomingDefaultBubblePaddingTop() {
        return this.incomingDefaultBubblePaddingTop;
    }

    protected int getIncomingDefaultBubblePaddingBottom() {
        return this.incomingDefaultBubblePaddingBottom;
    }

    protected int getIncomingTextColor() {
        return this.incomingTextColor;
    }

    public int getIncomingQuestionTextColor() {
        return this.incomingQuestionTextColor;
    }

    public void setIncomingQuestionTextColor(int incomingQuestionTextColor) {
        this.incomingQuestionTextColor = incomingQuestionTextColor;
    }

    protected int getIncomingTextSize() {
        return this.incomingTextSize;
    }

    protected int getIncomingTextStyle() {
        return this.incomingTextStyle;
    }

    protected Drawable getOutcomingBubbleDrawable() {
        int i = this.outcomingBubbleDrawable;
        if (i == -1) {
            return getMessageSelector(this.outcomingDefaultBubbleColor, this.outcomingDefaultBubbleSelectedColor, this.outcomingDefaultBubblePressedColor, R.drawable.shape_outcoming_message);
        }
        return getDrawable(i);
    }

    protected Drawable getOutcomingImageOverlayDrawable() {
        int i = this.outcomingImageOverlayDrawable;
        if (i == -1) {
            return getMessageSelector(0, this.outcomingDefaultImageOverlaySelectedColor, this.outcomingDefaultImageOverlayPressedColor, R.drawable.shape_outcoming_message);
        }
        return getDrawable(i);
    }

    protected int getOutcomingDefaultBubblePaddingLeft() {
        return this.outcomingDefaultBubblePaddingLeft;
    }

    protected int getOutcomingDefaultBubblePaddingRight() {
        return this.outcomingDefaultBubblePaddingRight;
    }

    protected int getOutcomingDefaultBubblePaddingTop() {
        return this.outcomingDefaultBubblePaddingTop;
    }

    protected int getOutcomingDefaultBubblePaddingBottom() {
        return this.outcomingDefaultBubblePaddingBottom;
    }

    protected int getOutcomingTextColor() {
        return this.outcomingTextColor;
    }

    protected int getOutcomingTextSize() {
        return this.outcomingTextSize;
    }

    protected int getOutcomingTextStyle() {
        return this.outcomingTextStyle;
    }

    protected int getOutcomingTimeTextColor() {
        return this.outcomingTimeTextColor;
    }

    protected int getOutcomingTimeTextSize() {
        return this.outcomingTimeTextSize;
    }

    protected int getOutcomingTimeTextStyle() {
        return this.outcomingTimeTextStyle;
    }

    protected int getOutcomingImageTimeTextColor() {
        return this.outcomingImageTimeTextColor;
    }

    protected int getOutcomingImageTimeTextSize() {
        return this.outcomingImageTimeTextSize;
    }

    protected int getOutcomingImageTimeTextStyle() {
        return this.outcomingImageTimeTextStyle;
    }

    protected int getDateHeaderTextColor() {
        return this.dateHeaderTextColor;
    }

    protected int getDateHeaderTextSize() {
        return this.dateHeaderTextSize;
    }

    protected int getDateHeaderTextStyle() {
        return this.dateHeaderTextStyle;
    }

    protected int getDateHeaderPadding() {
        return this.dateHeaderPadding;
    }

    protected String getDateHeaderFormat() {
        return this.dateHeaderFormat;
    }

    protected int getIncomingTimeTextSize() {
        return this.incomingTimeTextSize;
    }

    protected int getIncomingTimeTextStyle() {
        return this.incomingTimeTextStyle;
    }

    protected int getIncomingTimeTextColor() {
        return this.incomingTimeTextColor;
    }

    protected int getIncomingImageTimeTextColor() {
        return this.incomingImageTimeTextColor;
    }

    protected int getIncomingImageTimeTextSize() {
        return this.incomingImageTimeTextSize;
    }

    protected int getIncomingImageTimeTextStyle() {
        return this.incomingImageTimeTextStyle;
    }

    public Drawable getInComing() {
        return this.inComing;
    }

    public Drawable getOutComing() {
        return this.outComing;
    }

    protected Drawable getIncomingBubbleDrawable() {
        int i = this.incomingBubbleDrawable;
        if (i == -1) {
            return getMessageSelector(this.incomingDefaultBubbleColor, this.incomingDefaultBubbleSelectedColor, this.incomingDefaultBubblePressedColor, R.drawable.shape_incoming_message);
        }
        return getDrawable(i);
    }

    protected Drawable getIncomingImageOverlayDrawable() {
        int i = this.incomingImageOverlayDrawable;
        if (i == -1) {
            return getMessageSelector(0, this.incomingDefaultImageOverlaySelectedColor, this.incomingDefaultImageOverlayPressedColor, R.drawable.shape_incoming_message);
        }
        return getDrawable(i);
    }
}
