package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class QBgRunningTextView extends ConstraintLayout {
    private TextView des;
    private TextView title;
    private TextView toSetting;

    public QBgRunningTextView(Context context) {
        super(context);
    }

    public QBgRunningTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.qc_appbackground_text, this);
        this.title = (TextView) viewInflate.findViewById(R.id.app_title);
        this.des = (TextView) viewInflate.findViewById(R.id.app_des);
        this.toSetting = (TextView) viewInflate.findViewById(R.id.btn_app_setting);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.qc_background_setting);
        String string = typedArrayObtainStyledAttributes.getString(1);
        String string2 = typedArrayObtainStyledAttributes.getString(0);
        this.title.setText(string);
        this.des.setText(string2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public QBgRunningTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setClickListener(View.OnClickListener listener) {
        this.toSetting.setOnClickListener(listener);
    }

    public void setImgBackground(Drawable drawable) {
        this.toSetting.setBackground(drawable);
    }

    public void setBtnText(String text) {
        this.toSetting.setText(text);
    }

    public void setBtnTextColor(int color) {
        this.toSetting.setTextColor(color);
    }
}
