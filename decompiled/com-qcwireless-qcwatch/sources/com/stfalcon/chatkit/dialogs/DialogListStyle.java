package com.stfalcon.chatkit.dialogs;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.commons.Style;

/* loaded from: classes4.dex */
class DialogListStyle extends Style {
    private int dialogAvatarHeight;
    private int dialogAvatarWidth;
    private int dialogDateColor;
    private int dialogDateSize;
    private int dialogDateStyle;
    private int dialogDividerColor;
    private boolean dialogDividerEnabled;
    private int dialogDividerLeftPadding;
    private int dialogDividerRightPadding;
    private int dialogItemBackground;
    private boolean dialogMessageAvatarEnabled;
    private int dialogMessageAvatarHeight;
    private int dialogMessageAvatarWidth;
    private int dialogMessageTextColor;
    private int dialogMessageTextSize;
    private int dialogMessageTextStyle;
    private int dialogTitleTextColor;
    private int dialogTitleTextSize;
    private int dialogTitleTextStyle;
    private int dialogUnreadBubbleBackgroundColor;
    private boolean dialogUnreadBubbleEnabled;
    private int dialogUnreadBubbleTextColor;
    private int dialogUnreadBubbleTextSize;
    private int dialogUnreadBubbleTextStyle;
    private int dialogUnreadDateColor;
    private int dialogUnreadDateStyle;
    private int dialogUnreadItemBackground;
    private int dialogUnreadMessageTextColor;
    private int dialogUnreadMessageTextStyle;
    private int dialogUnreadTitleTextColor;
    private int dialogUnreadTitleTextStyle;

    static DialogListStyle parse(Context context, AttributeSet attrs) {
        DialogListStyle dialogListStyle = new DialogListStyle(context, attrs);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DialogsList);
        dialogListStyle.dialogItemBackground = typedArrayObtainStyledAttributes.getColor(9, dialogListStyle.getColor(R.color.transparent));
        dialogListStyle.dialogUnreadItemBackground = typedArrayObtainStyledAttributes.getColor(26, dialogListStyle.getColor(R.color.transparent));
        dialogListStyle.dialogTitleTextColor = typedArrayObtainStyledAttributes.getColor(16, dialogListStyle.getColor(R.color.dialog_title_text));
        dialogListStyle.dialogTitleTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(17, context.getResources().getDimensionPixelSize(R.dimen.dialog_title_text_size));
        dialogListStyle.dialogTitleTextStyle = typedArrayObtainStyledAttributes.getInt(18, 0);
        dialogListStyle.dialogUnreadTitleTextColor = typedArrayObtainStyledAttributes.getColor(29, dialogListStyle.getColor(R.color.dialog_title_text));
        dialogListStyle.dialogUnreadTitleTextStyle = typedArrayObtainStyledAttributes.getInt(30, 0);
        dialogListStyle.dialogMessageTextColor = typedArrayObtainStyledAttributes.getColor(13, dialogListStyle.getColor(R.color.dialog_message_text));
        dialogListStyle.dialogMessageTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, context.getResources().getDimensionPixelSize(R.dimen.dialog_message_text_size));
        dialogListStyle.dialogMessageTextStyle = typedArrayObtainStyledAttributes.getInt(15, 0);
        dialogListStyle.dialogUnreadMessageTextColor = typedArrayObtainStyledAttributes.getColor(27, dialogListStyle.getColor(R.color.dialog_message_text));
        dialogListStyle.dialogUnreadMessageTextStyle = typedArrayObtainStyledAttributes.getInt(28, 0);
        dialogListStyle.dialogDateColor = typedArrayObtainStyledAttributes.getColor(2, dialogListStyle.getColor(R.color.dialog_date_text));
        dialogListStyle.dialogDateSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, context.getResources().getDimensionPixelSize(R.dimen.dialog_date_text_size));
        dialogListStyle.dialogDateStyle = typedArrayObtainStyledAttributes.getInt(4, 0);
        dialogListStyle.dialogUnreadDateColor = typedArrayObtainStyledAttributes.getColor(24, dialogListStyle.getColor(R.color.dialog_date_text));
        dialogListStyle.dialogUnreadDateStyle = typedArrayObtainStyledAttributes.getInt(25, 0);
        dialogListStyle.dialogUnreadBubbleEnabled = typedArrayObtainStyledAttributes.getBoolean(20, true);
        dialogListStyle.dialogUnreadBubbleBackgroundColor = typedArrayObtainStyledAttributes.getColor(19, dialogListStyle.getColor(R.color.dialog_unread_bubble));
        dialogListStyle.dialogUnreadBubbleTextColor = typedArrayObtainStyledAttributes.getColor(21, dialogListStyle.getColor(R.color.dialog_unread_text));
        dialogListStyle.dialogUnreadBubbleTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(22, context.getResources().getDimensionPixelSize(R.dimen.dialog_unread_bubble_text_size));
        dialogListStyle.dialogUnreadBubbleTextStyle = typedArrayObtainStyledAttributes.getInt(23, 0);
        dialogListStyle.dialogAvatarWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, context.getResources().getDimensionPixelSize(R.dimen.dialog_avatar_width));
        dialogListStyle.dialogAvatarHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, context.getResources().getDimensionPixelSize(R.dimen.dialog_avatar_height));
        dialogListStyle.dialogMessageAvatarEnabled = typedArrayObtainStyledAttributes.getBoolean(10, true);
        dialogListStyle.dialogMessageAvatarWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, context.getResources().getDimensionPixelSize(R.dimen.dialog_last_message_avatar_width));
        dialogListStyle.dialogMessageAvatarHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, context.getResources().getDimensionPixelSize(R.dimen.dialog_last_message_avatar_height));
        dialogListStyle.dialogDividerEnabled = typedArrayObtainStyledAttributes.getBoolean(6, true);
        dialogListStyle.dialogDividerColor = typedArrayObtainStyledAttributes.getColor(5, dialogListStyle.getColor(R.color.dialog_divider));
        dialogListStyle.dialogDividerLeftPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, context.getResources().getDimensionPixelSize(R.dimen.dialog_divider_margin_left));
        dialogListStyle.dialogDividerRightPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, context.getResources().getDimensionPixelSize(R.dimen.dialog_divider_margin_right));
        typedArrayObtainStyledAttributes.recycle();
        return dialogListStyle;
    }

    private DialogListStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getDialogTitleTextColor() {
        return this.dialogTitleTextColor;
    }

    protected int getDialogTitleTextSize() {
        return this.dialogTitleTextSize;
    }

    protected int getDialogTitleTextStyle() {
        return this.dialogTitleTextStyle;
    }

    protected int getDialogUnreadTitleTextColor() {
        return this.dialogUnreadTitleTextColor;
    }

    protected int getDialogUnreadTitleTextStyle() {
        return this.dialogUnreadTitleTextStyle;
    }

    protected int getDialogMessageTextColor() {
        return this.dialogMessageTextColor;
    }

    protected int getDialogMessageTextSize() {
        return this.dialogMessageTextSize;
    }

    protected int getDialogMessageTextStyle() {
        return this.dialogMessageTextStyle;
    }

    protected int getDialogUnreadMessageTextColor() {
        return this.dialogUnreadMessageTextColor;
    }

    protected int getDialogUnreadMessageTextStyle() {
        return this.dialogUnreadMessageTextStyle;
    }

    protected int getDialogDateColor() {
        return this.dialogDateColor;
    }

    protected int getDialogDateSize() {
        return this.dialogDateSize;
    }

    protected int getDialogDateStyle() {
        return this.dialogDateStyle;
    }

    protected int getDialogUnreadDateColor() {
        return this.dialogUnreadDateColor;
    }

    protected int getDialogUnreadDateStyle() {
        return this.dialogUnreadDateStyle;
    }

    protected boolean isDialogUnreadBubbleEnabled() {
        return this.dialogUnreadBubbleEnabled;
    }

    protected int getDialogUnreadBubbleTextColor() {
        return this.dialogUnreadBubbleTextColor;
    }

    protected int getDialogUnreadBubbleTextSize() {
        return this.dialogUnreadBubbleTextSize;
    }

    protected int getDialogUnreadBubbleTextStyle() {
        return this.dialogUnreadBubbleTextStyle;
    }

    protected int getDialogUnreadBubbleBackgroundColor() {
        return this.dialogUnreadBubbleBackgroundColor;
    }

    protected int getDialogAvatarWidth() {
        return this.dialogAvatarWidth;
    }

    protected int getDialogAvatarHeight() {
        return this.dialogAvatarHeight;
    }

    protected boolean isDialogDividerEnabled() {
        return this.dialogDividerEnabled;
    }

    protected int getDialogDividerColor() {
        return this.dialogDividerColor;
    }

    protected int getDialogDividerLeftPadding() {
        return this.dialogDividerLeftPadding;
    }

    protected int getDialogDividerRightPadding() {
        return this.dialogDividerRightPadding;
    }

    protected int getDialogItemBackground() {
        return this.dialogItemBackground;
    }

    protected int getDialogUnreadItemBackground() {
        return this.dialogUnreadItemBackground;
    }

    protected boolean isDialogMessageAvatarEnabled() {
        return this.dialogMessageAvatarEnabled;
    }

    protected int getDialogMessageAvatarWidth() {
        return this.dialogMessageAvatarWidth;
    }

    protected int getDialogMessageAvatarHeight() {
        return this.dialogMessageAvatarHeight;
    }
}
