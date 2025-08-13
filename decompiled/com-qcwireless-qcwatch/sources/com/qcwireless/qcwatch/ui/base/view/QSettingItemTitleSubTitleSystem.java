package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.qcwireless.qcwatch.R;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* compiled from: QSettingItemTitleSubTitleSystem.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002:\u0001EB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020+H\u0002J\u0010\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020\nH\u0002J\"\u0010/\u001a\u00020+2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0007J\u0006\u00100\u001a\u00020\nJ\b\u00101\u001a\u0004\u0018\u00010\u0001J\u0010\u00102\u001a\u00020+2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u000e\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\u000fJ\u000e\u00105\u001a\u00020+2\u0006\u00104\u001a\u00020\u000fJ\u0010\u00106\u001a\u00020+2\b\u00107\u001a\u0004\u0018\u00010\u0012J\u0010\u00108\u001a\u00020+2\b\u00107\u001a\u0004\u0018\u00010\u0012J\u000e\u00109\u001a\u00020+2\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020+2\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020+2\u0006\u0010@\u001a\u00020\nJ\u0010\u0010A\u001a\u00020+2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u000e\u0010B\u001a\u00020+2\u0006\u0010C\u001a\u00020\u001eJ\u0010\u0010D\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\nH\u0002R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSettingItemTitleSubTitleSystem;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lskin/support/widget/SkinCompatSupportable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "imageView", "Landroid/widget/ImageView;", "mChecked", "", "mLeftIconId", "mLeftSubText", "", "mLeftText", "mLeftTextColorId", "mOnLSettingItemClick", "Lcom/qcwireless/qcwatch/ui/base/view/QSettingItemTitleSubTitleSystem$OnLSettingItemWithClickSystem;", "mRightIconId", "mRightIcon_switch", "Lcom/qcwireless/qcwatch/ui/base/view/QSwitchCompat;", "mRightStyle", "mRightTextColor", "mRightTextColorId", "mRightTextSize", "", "mRootLayout", "mSkinCompatBackgroundHelper", "Lskin/support/widget/SkinCompatBackgroundHelper;", "mSkinCompatBackgroundHelper1", "mTextColor", "mToast", "Landroid/widget/TextView;", "mTvLeftSubText", "mTvLeftText", "mUnderLine", "Landroid/view/View;", "applySkin", "", "applyTopBarBackgroundColor", "clickOn", BreakpointSQLiteKey.ID, "getCustomStyle", "getTextLines", "getmRootLayout", "initView", "setChecked", "checked", "setCheckedAble", "setLeftSubText", "info", "setLeftText", "setQSettingItemCheckListener", "mOnCheckChangeListener", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "setSubTitleClick", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "setTextLines", "line", "setmOnLSettingItemWithClick", "startAnim", "value", "switchRightStyle", "OnLSettingItemWithClickSystem", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSettingItemTitleSubTitleSystem extends ConstraintLayout implements SkinCompatSupportable {
    private ImageView imageView;
    private boolean mChecked;
    private int mLeftIconId;
    private String mLeftSubText;
    private String mLeftText;
    private int mLeftTextColorId;
    private OnLSettingItemWithClickSystem mOnLSettingItemClick;
    private int mRightIconId;
    private QSwitchCompat mRightIcon_switch;
    private int mRightStyle;
    private int mRightTextColor;
    private int mRightTextColorId;
    private float mRightTextSize;
    private ConstraintLayout mRootLayout;
    private SkinCompatBackgroundHelper mSkinCompatBackgroundHelper;
    private SkinCompatBackgroundHelper mSkinCompatBackgroundHelper1;
    private int mTextColor;
    private final TextView mToast;
    private TextView mTvLeftSubText;
    private TextView mTvLeftText;
    private final View mUnderLine;

    /* compiled from: QSettingItemTitleSubTitleSystem.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSettingItemTitleSubTitleSystem$OnLSettingItemWithClickSystem;", "", "click", "", BreakpointSQLiteKey.ID, "", "isChecked", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnLSettingItemWithClickSystem {
        void click(int id, boolean isChecked);
    }

    public final void setmOnLSettingItemWithClick(OnLSettingItemWithClickSystem mOnLSettingItemClick) {
        this.mOnLSettingItemClick = mOnLSettingItemClick;
    }

    public final void setQSettingItemCheckListener(CompoundButton.OnCheckedChangeListener mOnCheckChangeListener) {
        Intrinsics.checkNotNullParameter(mOnCheckChangeListener, "mOnCheckChangeListener");
        QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat);
        qSwitchCompat.setOnCheckedChangeListener(mOnCheckChangeListener);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSettingItemTitleSubTitleSystem(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        getCustomStyle(context, null, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSettingItemTitleSubTitleSystem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        getCustomStyle(context, attributeSet, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSettingItemTitleSubTitleSystem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        getCustomStyle(context, attributeSet, i);
    }

    public final void getCustomStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.QSettingView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…R.styleable.QSettingView)");
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == 2) {
                this.mLeftTextColorId = typedArrayObtainStyledAttributes.getResourceId(2, 0);
                this.mTextColor = typedArrayObtainStyledAttributes.getColor(index, -3355444);
                TextView textView = this.mTvLeftText;
                Intrinsics.checkNotNull(textView);
                textView.setTextColor(this.mTextColor);
            } else if (index == 3) {
                float f = typedArrayObtainStyledAttributes.getFloat(index, 16.0f);
                TextView textView2 = this.mTvLeftText;
                Intrinsics.checkNotNull(textView2);
                textView2.setTextSize(f);
            } else if (index != 5) {
                if (index != 6) {
                    if (index == 13) {
                        this.mRightStyle = typedArrayObtainStyledAttributes.getInt(index, 0);
                    } else if (index != 15) {
                        switch (index) {
                            case 9:
                                this.mLeftSubText = typedArrayObtainStyledAttributes.getString(index);
                                TextView textView3 = this.mTvLeftSubText;
                                Intrinsics.checkNotNull(textView3);
                                textView3.setText(this.mLeftSubText);
                                break;
                            case 10:
                                this.mLeftText = typedArrayObtainStyledAttributes.getString(index);
                                TextView textView4 = this.mTvLeftText;
                                Intrinsics.checkNotNull(textView4);
                                textView4.setText(this.mLeftText);
                                break;
                            case 11:
                                int dimension = (int) typedArrayObtainStyledAttributes.getDimension(index, 8.0f);
                                TextView textView5 = this.mTvLeftText;
                                Intrinsics.checkNotNull(textView5);
                                ViewGroup.LayoutParams layoutParams = textView5.getLayoutParams();
                                Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
                                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
                                layoutParams2.leftMargin = dimension;
                                TextView textView6 = this.mTvLeftText;
                                Intrinsics.checkNotNull(textView6);
                                textView6.setLayoutParams(layoutParams2);
                                break;
                        }
                    } else {
                        this.mRightTextColorId = typedArrayObtainStyledAttributes.getResourceId(15, 0);
                        this.mRightTextColor = typedArrayObtainStyledAttributes.getColor(index, -7829368);
                    }
                } else if (!typedArrayObtainStyledAttributes.getBoolean(index, true)) {
                    View view = this.mUnderLine;
                    Intrinsics.checkNotNull(view);
                    view.setVisibility(8);
                }
            } else if (typedArrayObtainStyledAttributes.getBoolean(index, false)) {
                TextView textView7 = this.mTvLeftSubText;
                Intrinsics.checkNotNull(textView7);
                textView7.setVisibility(0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        switchRightStyle(this.mRightStyle);
        ConstraintLayout constraintLayout = this.mRootLayout;
        Intrinsics.checkNotNull(constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QSettingItemTitleSubTitleSystem.m320getCustomStyle$lambda0(this.f$0, view2);
            }
        });
        applyTopBarBackgroundColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCustomStyle$lambda-0, reason: not valid java name */
    public static final void m320getCustomStyle$lambda0(QSettingItemTitleSubTitleSystem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.clickOn(view.getId());
    }

    private final void applyTopBarBackgroundColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mLeftTextColorId);
        if (iCheckResourceId != 0) {
            int color = SkinCompatResources.getColor(getContext(), iCheckResourceId);
            TextView textView = this.mTvLeftText;
            Intrinsics.checkNotNull(textView);
            textView.setTextColor(color);
        }
    }

    private final void switchRightStyle(int mRightStyle) {
        if (mRightStyle == 0) {
            QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
            Intrinsics.checkNotNull(qSwitchCompat);
            qSwitchCompat.setVisibility(8);
        } else if (mRightStyle == 2) {
            QSwitchCompat qSwitchCompat2 = this.mRightIcon_switch;
            Intrinsics.checkNotNull(qSwitchCompat2);
            qSwitchCompat2.setVisibility(8);
        } else {
            if (mRightStyle != 3) {
                return;
            }
            QSwitchCompat qSwitchCompat3 = this.mRightIcon_switch;
            Intrinsics.checkNotNull(qSwitchCompat3);
            qSwitchCompat3.setVisibility(0);
        }
    }

    private final void initView(Context context) {
        View viewInflate = ConstraintLayout.inflate(context, R.layout.qc_device_item_with_title_click_system, this);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layou…title_click_system, this)");
        this.mRootLayout = (ConstraintLayout) viewInflate.findViewById(R.id.rootLayout);
        this.mTvLeftText = (TextView) viewInflate.findViewById(R.id.tv_lefttext);
        this.mRightIcon_switch = (QSwitchCompat) viewInflate.findViewById(R.id.rightswitch);
        this.mTvLeftSubText = (TextView) viewInflate.findViewById(R.id.tv_left_sub_text);
        this.imageView = (ImageView) viewInflate.findViewById(R.id.image_text_down);
    }

    private final void clickOn(int id) {
        OnLSettingItemWithClickSystem onLSettingItemWithClickSystem;
        int i = this.mRightStyle;
        if (i == 0 || i == 1) {
            OnLSettingItemWithClickSystem onLSettingItemWithClickSystem2 = this.mOnLSettingItemClick;
            if (onLSettingItemWithClickSystem2 != null) {
                Intrinsics.checkNotNull(onLSettingItemWithClickSystem2);
                onLSettingItemWithClickSystem2.click(id, this.mChecked);
                return;
            }
            return;
        }
        if (i == 3 && (onLSettingItemWithClickSystem = this.mOnLSettingItemClick) != null) {
            Intrinsics.checkNotNull(onLSettingItemWithClickSystem);
            onLSettingItemWithClickSystem.click(id, this.mChecked);
        }
    }

    /* renamed from: getmRootLayout, reason: from getter */
    public final ConstraintLayout getMRootLayout() {
        return this.mRootLayout;
    }

    public final void setLeftText(String info) {
        TextView textView = this.mTvLeftText;
        Intrinsics.checkNotNull(textView);
        textView.setText(info);
    }

    public final void setLeftSubText(String info) {
        TextView textView = this.mTvLeftSubText;
        Intrinsics.checkNotNull(textView);
        textView.setText(info);
    }

    public final void setChecked(boolean checked) {
        QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat);
        qSwitchCompat.setChecked(checked);
    }

    public final void setCheckedAble(boolean checked) {
        QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat);
        qSwitchCompat.setClickable(checked);
    }

    public final void setSubTitleClick(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        TextView textView = this.mTvLeftSubText;
        if (textView != null) {
            textView.setOnClickListener(listener);
        }
    }

    public final int getTextLines() {
        TextView textView = this.mTvLeftSubText;
        Intrinsics.checkNotNull(textView);
        return textView.getMaxLines();
    }

    public final void setTextLines(int line) {
        TextView textView = this.mTvLeftSubText;
        Intrinsics.checkNotNull(textView);
        textView.setMaxLines(line);
    }

    public final void startAnim(float value) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, value, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setDuration(1L);
        rotateAnimation.setFillAfter(true);
        ImageView imageView = this.imageView;
        Intrinsics.checkNotNull(imageView);
        imageView.startAnimation(rotateAnimation);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyTopBarBackgroundColor();
        try {
            SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mSkinCompatBackgroundHelper;
            SkinCompatBackgroundHelper skinCompatBackgroundHelper2 = null;
            if (skinCompatBackgroundHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSkinCompatBackgroundHelper");
                skinCompatBackgroundHelper = null;
            }
            skinCompatBackgroundHelper.applySkin();
            SkinCompatBackgroundHelper skinCompatBackgroundHelper3 = this.mSkinCompatBackgroundHelper1;
            if (skinCompatBackgroundHelper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSkinCompatBackgroundHelper1");
            } else {
                skinCompatBackgroundHelper2 = skinCompatBackgroundHelper3;
            }
            skinCompatBackgroundHelper2.applySkin();
        } catch (Exception unused) {
        }
        postInvalidate();
    }
}
