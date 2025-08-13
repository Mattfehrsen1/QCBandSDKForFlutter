package com.qcwireless.qcwatch.ui.device.contact.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView;
import java.util.List;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class SideBarLayout extends RelativeLayout implements SideBarSortView.OnIndexChangedListener, SkinCompatSupportable {
    private Context mContext;
    private View mLayout;
    private List<String> mList;
    private OnSideBarLayoutListener mListener;
    private SideBarSortView mSortView;
    private TextView mTvTips;
    private int selectTextColor;
    private int selectTextId;
    private float selectTextSize;
    private int unSelectTextId;
    private int unselectTextColor;
    private float unselectTextSize;
    private Drawable wordBackground;
    private int wordTextColor;
    private float wordTextSize;

    public interface OnSideBarLayoutListener {
        void onSideBarScrollUpdateItem(String word);
    }

    private void applyTextColor() {
    }

    public SideBarLayout(Context context) {
        super(context);
        this.selectTextId = 0;
        this.unSelectTextId = 0;
    }

    public SideBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.selectTextId = 0;
        this.unSelectTextId = 0;
        init(context, attrs);
        initView();
    }

    public SideBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.selectTextId = 0;
        this.unSelectTextId = 0;
        init(context, attrs);
        initView();
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.SideBarView);
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attrs, R.styleable.SideBarView);
            this.unselectTextColor = typedArrayObtainStyledAttributes.getColor(4, Color.parseColor("#1ABDE6"));
            this.selectTextColor = typedArrayObtainStyledAttributes.getColor(2, Color.parseColor("#2E56D7"));
            this.selectTextSize = typedArrayObtainStyledAttributes.getDimension(3, dip2px(this.mContext, 12.0f));
            this.unselectTextSize = typedArrayObtainStyledAttributes.getDimension(5, dip2px(this.mContext, 10.0f));
            this.wordTextSize = typedArrayObtainStyledAttributes.getDimension(8, px2sp(this.mContext, 45.0f));
            this.wordTextColor = typedArrayObtainStyledAttributes.getColor(7, Color.parseColor("#FFFFFF"));
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(6);
            this.wordBackground = drawable;
            if (drawable == null) {
                this.wordBackground = context.getResources().getDrawable(R.drawable.sort_text_view_hint_bg);
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_sidebar_layout, (ViewGroup) null, true);
        this.mLayout = viewInflate;
        this.mTvTips = (TextView) viewInflate.findViewById(R.id.tvTips);
        SideBarSortView sideBarSortView = (SideBarSortView) this.mLayout.findViewById(R.id.sortView);
        this.mSortView = sideBarSortView;
        sideBarSortView.setIndexChangedListener(this);
        this.mSortView.setmTextColor(this.unselectTextColor);
        this.mSortView.setmTextColorChoose(this.selectTextColor);
        this.mSortView.setmTextSize(this.unselectTextSize);
        this.mSortView.setmTextSizeChoose(this.selectTextSize);
        this.mSortView.invalidate();
        this.mTvTips.setTextColor(SkinCompatResources.getColor(this.mContext, R.color.color_FF6A00));
        this.mTvTips.setTextSize(px2sp(this.mContext, this.wordTextSize));
        this.mTvTips.setBackground(this.wordBackground);
        applyTextColor();
        addView(this.mLayout);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyTextColor();
    }

    public void setSideBarLayout(OnSideBarLayoutListener listener) {
        this.mListener = listener;
    }

    @Override // com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView.OnIndexChangedListener
    public void onSideBarScrollUpdateItem(String word) {
        this.mTvTips.setVisibility(0);
        this.mTvTips.setText(word);
        OnSideBarLayoutListener onSideBarLayoutListener = this.mListener;
        if (onSideBarLayoutListener != null) {
            onSideBarLayoutListener.onSideBarScrollUpdateItem(word);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView.OnIndexChangedListener
    public void onSideBarScrollEndHideText() {
        this.mTvTips.setVisibility(8);
    }

    public void OnItemScrollUpdateText(String word) {
        if (this.mListener != null) {
            this.mSortView.onitemScrollUpdateText(word);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
