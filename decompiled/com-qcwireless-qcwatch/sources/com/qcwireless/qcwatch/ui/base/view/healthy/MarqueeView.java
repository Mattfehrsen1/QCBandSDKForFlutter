package com.qcwireless.qcwatch.ui.base.view.healthy;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.qcwireless.qcwatch.R;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class MarqueeView extends ViewFlipper implements SkinCompatSupportable {
    private static final int TEXT_GRAVITY_CENTER = 1;
    private static final int TEXT_GRAVITY_LEFT = 0;
    private static final int TEXT_GRAVITY_RIGHT = 2;
    private int animDuration;
    private int gravity;
    private int interval;
    private boolean isSetAnimDuration;
    private Context mContext;
    private List<? extends CharSequence> notices;
    private OnItemClickListener onItemClickListener;
    private boolean singleLine;
    private int textColor;
    private int textColorId;
    private int textSize;

    public interface OnItemClickListener {
        void onItemClick(int position, TextView textView);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isSetAnimDuration = false;
        this.interval = 2000;
        this.animDuration = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.textSize = 14;
        this.textColor = -1;
        this.singleLine = false;
        this.gravity = 19;
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (this.notices == null) {
            this.notices = new ArrayList();
        }
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        this.interval = typedArrayObtainStyledAttributes.getInteger(2, this.interval);
        this.isSetAnimDuration = typedArrayObtainStyledAttributes.hasValue(0);
        this.singleLine = typedArrayObtainStyledAttributes.getBoolean(3, false);
        this.animDuration = typedArrayObtainStyledAttributes.getInteger(0, this.animDuration);
        if (typedArrayObtainStyledAttributes.hasValue(5)) {
            int dimension = (int) typedArrayObtainStyledAttributes.getDimension(5, this.textSize);
            this.textSize = dimension;
            this.textSize = px2sp(this.mContext, dimension);
        }
        this.textColor = typedArrayObtainStyledAttributes.getColor(4, this.textColor);
        this.textColorId = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        int i = typedArrayObtainStyledAttributes.getInt(1, 0);
        if (i == 1) {
            this.gravity = 17;
        } else if (i == 2) {
            this.gravity = 21;
        }
        typedArrayObtainStyledAttributes.recycle();
        setFlipInterval(this.interval);
        switchSkin();
    }

    private void switchSkin() {
        try {
            int iCheckResourceId = SkinCompatHelper.checkResourceId(this.textColorId);
            if (iCheckResourceId != 0) {
                this.textColor = SkinCompatResources.getColor(getContext(), iCheckResourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startWithText(final String notice) {
        if (TextUtils.isEmpty(notice)) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.qcwireless.qcwatch.ui.base.view.healthy.MarqueeView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                MarqueeView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                MarqueeView marqueeView = MarqueeView.this;
                marqueeView.startWithFixedWidth(notice, marqueeView.getWidth());
            }
        });
    }

    public int px2sp(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public void startWithList(List<? extends CharSequence> notices) throws Resources.NotFoundException {
        setNotices(notices);
        start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWithFixedWidth(String notice, int width) throws Resources.NotFoundException {
        int length = notice.length();
        int iPx2dip = px2dip(this.mContext, width);
        int i = iPx2dip / this.textSize;
        if (iPx2dip == 0) {
            throw new RuntimeException("Please set MarqueeView width !");
        }
        ArrayList arrayList = new ArrayList();
        if (length <= i) {
            arrayList.add(notice);
        } else {
            int i2 = 0;
            int i3 = (length / i) + (length % i != 0 ? 1 : 0);
            while (i2 < i3) {
                int i4 = i2 * i;
                i2++;
                int i5 = i2 * i;
                if (i5 >= length) {
                    i5 = length;
                }
                arrayList.add(notice.substring(i4, i5));
            }
        }
        this.notices.addAll(arrayList);
        start();
    }

    public boolean start() throws Resources.NotFoundException {
        List<? extends CharSequence> list = this.notices;
        boolean z = false;
        z = false;
        if (list != null && list.size() != 0) {
            removeAllViews();
            resetAnimation();
            for (final int i = 0; i < this.notices.size(); i++) {
                final TextView textViewCreateTextView = createTextView(this.notices.get(i), i);
                textViewCreateTextView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.healthy.MarqueeView.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        if (MarqueeView.this.onItemClickListener != null) {
                            MarqueeView.this.onItemClickListener.onItemClick(i, textViewCreateTextView);
                        }
                    }
                });
                addView(textViewCreateTextView);
            }
            z = true;
            z = true;
            if (this.notices.size() > 1) {
                startFlipping();
            } else {
                stopFlipping();
            }
        }
        return z;
    }

    private void resetAnimation() throws Resources.NotFoundException {
        clearAnimation();
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_marquee_in);
        if (this.isSetAnimDuration) {
            animationLoadAnimation.setDuration(this.animDuration);
        }
        setInAnimation(animationLoadAnimation);
        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_marquee_out);
        if (this.isSetAnimDuration) {
            animationLoadAnimation2.setDuration(this.animDuration);
        }
        setOutAnimation(animationLoadAnimation2);
    }

    private TextView createTextView(CharSequence text, int position) {
        TextView textView = new TextView(this.mContext);
        textView.setMaxWidth((int) dp2px(this.mContext, 250.0f));
        textView.setMinWidth((int) dp2px(this.mContext, 220.0f));
        textView.setGravity(this.gravity);
        textView.setText(text);
        textView.setTextColor(this.textColor);
        textView.setTextSize(this.textSize);
        textView.setSingleLine(this.singleLine);
        textView.setTag(Integer.valueOf(position));
        return textView;
    }

    public int getPosition() {
        return ((Integer) getCurrentView().getTag()).intValue();
    }

    public List<? extends CharSequence> getNotices() {
        return this.notices;
    }

    public void setNotices(List<? extends CharSequence> notices) {
        this.notices = notices;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        switchSkin();
    }
}
