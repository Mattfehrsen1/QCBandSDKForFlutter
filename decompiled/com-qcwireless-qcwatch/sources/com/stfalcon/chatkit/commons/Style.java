package com.stfalcon.chatkit.commons;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;

/* loaded from: classes4.dex */
public abstract class Style {
    protected AttributeSet attrs;
    protected Context context;
    protected Resources resources;

    protected Style(Context context, AttributeSet attrs) {
        this.context = context;
        this.resources = context.getResources();
        this.attrs = attrs;
    }

    protected final int getSystemPrimaryTextColor() {
        return getSystemColor(R.attr.textColorPrimary);
    }

    protected final int getSystemHintColor() {
        return getSystemColor(R.attr.textColorHint);
    }

    protected final int getSystemColor(int attr) throws Resources.NotFoundException {
        TypedArray typedArrayObtainStyledAttributes = this.context.obtainStyledAttributes(new TypedValue().data, new int[]{attr});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    protected final int getDimension(int dimen) {
        return this.resources.getDimensionPixelSize(dimen);
    }

    protected final int getColor(int color) {
        return ContextCompat.getColor(this.context, color);
    }

    protected final Drawable getDrawable(int drawable) {
        return ContextCompat.getDrawable(this.context, drawable);
    }

    protected final Drawable getVectorDrawable(int drawable) {
        return ContextCompat.getDrawable(this.context, drawable);
    }
}
