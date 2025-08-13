package com.qcwireless.qcwatch.ui.base.view.gps;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.gps.LockProgressBar;

/* loaded from: classes3.dex */
public class LockView extends FrameLayout implements LockProgressBar.onAnimEnd {
    private EndListener endListener;
    Handler handler;
    private ImageView imageView;
    private boolean isLock;
    private View.OnClickListener listener;
    private ImageView lockImage;
    private LockProgressBar progressBar;

    public interface EndListener {
        void onEnd();
    }

    public boolean isLock() {
        return this.isLock;
    }

    public LockView(Context context) {
        super(context);
        this.isLock = false;
        this.handler = new Handler() { // from class: com.qcwireless.qcwatch.ui.base.view.gps.LockView.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LockView.this.progressBar.setVisibility(4);
                if (LockView.this.isLock) {
                    LockView.this.flipAnimator(2);
                }
            }
        };
        initView(context);
    }

    public LockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isLock = false;
        this.handler = new Handler() { // from class: com.qcwireless.qcwatch.ui.base.view.gps.LockView.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LockView.this.progressBar.setVisibility(4);
                if (LockView.this.isLock) {
                    LockView.this.flipAnimator(2);
                }
            }
        };
        initView(context);
    }

    public void setLock(boolean lock) {
        this.isLock = lock;
    }

    public EndListener getEndListener() {
        return this.endListener;
    }

    public void setEndListener(EndListener endListener) {
        this.endListener = endListener;
    }

    public View.OnClickListener getListener() {
        return this.listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
        ImageView imageView = this.imageView;
        if (imageView != null) {
            imageView.setOnClickListener(listener);
        }
    }

    private void initView(Context context) {
        this.imageView = new ImageView(context);
        this.lockImage = new ImageView(context);
        this.imageView.setPadding(22, 22, 22, 22);
        this.lockImage.setPadding(22, 22, 22, 22);
        this.imageView.setImageResource(R.mipmap.gps_pause);
        this.lockImage.setImageResource(R.mipmap.gps_lock);
        LockProgressBar lockProgressBar = new LockProgressBar(context);
        this.progressBar = lockProgressBar;
        lockProgressBar.setAnimListener(this);
        addView(this.lockImage);
        addView(this.imageView);
        addView(this.progressBar);
        this.lockImage.setVisibility(8);
        this.progressBar.setVisibility(4);
        setImageBg(R.mipmap.gps_pause, R.mipmap.gps_lock, ContextCompat.getColor(context, R.color.gps_run_lock_1), ContextCompat.getColor(context, R.color.gps_run_lock_2));
    }

    public void setImageBg(int bg1, int bg2, int color1, int color2) {
        this.imageView.setImageResource(bg1);
        this.lockImage.setImageResource(bg2);
        this.progressBar.setPaintColor(color1, color2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            Log.i("----testEvent", "LockView is Down");
            this.progressBar.startAnim();
        } else if (action == 1) {
            this.progressBar.setCut(true);
        }
        return true;
    }

    public void flipAnimator(final int type) {
        ImageView imageView;
        ImageView imageView2;
        if (type == 1) {
            imageView = this.imageView;
            imageView2 = this.lockImage;
        } else {
            imageView = this.lockImage;
            imageView2 = this.imageView;
        }
        final ImageView imageView3 = imageView;
        final ImageView imageView4 = imageView2;
        imageView3.setVisibility(0);
        imageView4.setVisibility(8);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView3, "rotationY", 0.0f, 90.0f);
        final ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageView4, "rotationY", -90.0f, 0.0f);
        objectAnimatorOfFloat2.setInterpolator(new OvershootInterpolator(2.0f));
        objectAnimatorOfFloat.setDuration(200L).start();
        objectAnimatorOfFloat.addListener(new Animator.AnimatorListener() { // from class: com.qcwireless.qcwatch.ui.base.view.gps.LockView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                imageView3.setVisibility(8);
                imageView4.setVisibility(0);
                objectAnimatorOfFloat2.setDuration(200L).start();
                if (type != 2 || LockView.this.endListener == null) {
                    return;
                }
                LockView.this.endListener.onEnd();
            }
        });
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.gps.LockProgressBar.onAnimEnd
    public void animEndListener(boolean isLock) {
        this.isLock = isLock;
        this.handler.sendEmptyMessage(1);
    }
}
