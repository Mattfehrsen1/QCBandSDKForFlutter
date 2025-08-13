package com.qcwireless.qcwatch.ui.base.view.gps;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class GpsStartView extends FrameLayout {
    ImageView imageView;

    public GpsStartView(Context context) {
        super(context);
        initView(context);
    }

    public GpsStartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    protected void initView(Context context) {
        ImageView imageView = new ImageView(context);
        this.imageView = imageView;
        imageView.setPadding(22, 22, 22, 22);
        this.imageView.setImageResource(R.mipmap.gps_start);
        addView(this.imageView);
    }
}
