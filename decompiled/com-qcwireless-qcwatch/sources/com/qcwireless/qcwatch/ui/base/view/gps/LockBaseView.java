package com.qcwireless.qcwatch.ui.base.view.gps;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.gps.LockProgressBar;

/* loaded from: classes3.dex */
public class LockBaseView extends FrameLayout implements LockProgressBar.onAnimEnd {
    Handler handler;
    ImageView imageView;
    boolean isLock;
    LockProgressBar progressBar;

    public LockBaseView(Context context) {
        super(context);
        this.isLock = false;
        this.handler = new Handler() { // from class: com.qcwireless.qcwatch.ui.base.view.gps.LockBaseView.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LockBaseView.this.animBack();
            }
        };
        initView(context);
    }

    public LockBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isLock = false;
        this.handler = new Handler() { // from class: com.qcwireless.qcwatch.ui.base.view.gps.LockBaseView.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LockBaseView.this.animBack();
            }
        };
        initView(context);
    }

    protected void initView(Context context) {
        ImageView imageView = new ImageView(context);
        this.imageView = imageView;
        imageView.setPadding(22, 22, 22, 22);
        this.imageView.setImageResource(R.mipmap.gps_end);
        LockProgressBar lockProgressBar = new LockProgressBar(context, ContextCompat.getColor(context, R.color.gps_run_lock_1), ContextCompat.getColor(context, R.color.gps_run_lock_2));
        this.progressBar = lockProgressBar;
        lockProgressBar.setAnimListener(this);
        addView(this.imageView);
        addView(this.progressBar);
        this.progressBar.setVisibility(4);
    }

    public void setImageBg(int bg1, int color1, int color2) {
        this.imageView.setImageResource(bg1);
        this.progressBar.setPaintColor(color1, color2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.progressBar.startAnim();
        } else if (action == 1) {
            this.progressBar.setCut(true);
        }
        return true;
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.gps.LockProgressBar.onAnimEnd
    public void animEndListener(boolean isLock) {
        this.isLock = isLock;
        this.handler.sendEmptyMessage(1);
    }

    protected void animBack() {
        this.progressBar.setVisibility(4);
    }
}
