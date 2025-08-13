package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.ViewKt;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* compiled from: QSettingItem.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u00012\u00020\u0002:\u0001IB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u000207H\u0002J\u0010\u00109\u001a\u0002072\u0006\u0010:\u001a\u00020\nH\u0002J\"\u0010;\u001a\u0002072\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0007J\b\u0010<\u001a\u0004\u0018\u00010\u0001J\u0010\u0010=\u001a\u0002072\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u000e\u0010>\u001a\u0002072\u0006\u0010?\u001a\u00020\rJ\u000e\u0010@\u001a\u0002072\u0006\u0010A\u001a\u00020\rJ\u0010\u0010B\u001a\u0002072\b\u0010C\u001a\u0004\u0018\u00010\u0017J\u0010\u0010D\u001a\u0002072\b\u0010C\u001a\u0004\u0018\u00010\u0017J\u0006\u0010E\u001a\u000207J\u0010\u0010F\u001a\u0002072\b\u0010C\u001a\u0004\u0018\u00010\u0017J\u0010\u0010G\u001a\u0002072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0010\u0010H\u001a\u0002072\u0006\u0010$\u001a\u00020\nH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSettingItem;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lskin/support/widget/SkinCompatSupportable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mChecked", "", "mErrorIcon", "Landroid/widget/ImageView;", "mIvLeftIcon", "mIvRightIcon", "mLeftIcon", "Landroid/graphics/drawable/Drawable;", "mLeftIconId", "mLeftIconSzie", "mLeftSubText", "", "mLeftText", "mLeftTextColorId", "mOnLSettingItemClick", "Lcom/qcwireless/qcwatch/ui/base/view/QSettingItem$OnLSettingItemClick;", "mRightIcon", "mRightIconId", "mRightIcon_check", "Landroidx/appcompat/widget/AppCompatCheckBox;", "mRightIcon_switch", "Lcom/qcwireless/qcwatch/ui/base/view/QSwitchCompat;", "mRightLayout", "Landroid/widget/FrameLayout;", "mRightStyle", "mRightTextColor", "mRightTextColorId", "mRightTextSize", "", "mRootLayout", "mSkinCompatBackgroundHelper", "Lskin/support/widget/SkinCompatBackgroundHelper;", "mSkinCompatBackgroundHelper1", "mTextColor", "mTextSize", "mToast", "Landroid/widget/TextView;", "mTvLeftSubText", "mTvLeftText", "mTvRightText", "mUnderLine", "Landroid/view/View;", "applySkin", "", "applyTopBarBackgroundColor", "clickOn", BreakpointSQLiteKey.ID, "getCustomStyle", "getmRootLayout", "initView", "setChecked", "checked", "setErrorIcon", "flag", "setLeftSubText", "info", "setLeftText", "setRightIconGone", "setRightText", "setmOnLSettingItemClick", "switchRightStyle", "OnLSettingItemClick", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSettingItem extends ConstraintLayout implements SkinCompatSupportable {
    private boolean mChecked;
    private ImageView mErrorIcon;
    private ImageView mIvLeftIcon;
    private ImageView mIvRightIcon;
    private Drawable mLeftIcon;
    private int mLeftIconId;
    private int mLeftIconSzie;
    private String mLeftSubText;
    private String mLeftText;
    private int mLeftTextColorId;
    private OnLSettingItemClick mOnLSettingItemClick;
    private Drawable mRightIcon;
    private int mRightIconId;
    private AppCompatCheckBox mRightIcon_check;
    private QSwitchCompat mRightIcon_switch;
    private FrameLayout mRightLayout;
    private int mRightStyle;
    private int mRightTextColor;
    private int mRightTextColorId;
    private float mRightTextSize;
    private ConstraintLayout mRootLayout;
    private SkinCompatBackgroundHelper mSkinCompatBackgroundHelper;
    private SkinCompatBackgroundHelper mSkinCompatBackgroundHelper1;
    private int mTextColor;
    private final int mTextSize;
    private final TextView mToast;
    private TextView mTvLeftSubText;
    private TextView mTvLeftText;
    private TextView mTvRightText;
    private final View mUnderLine;

    /* compiled from: QSettingItem.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSettingItem$OnLSettingItemClick;", "", "click", "", BreakpointSQLiteKey.ID, "", "isChecked", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnLSettingItemClick {
        void click(int id, boolean isChecked);
    }

    public final void setmOnLSettingItemClick(OnLSettingItemClick mOnLSettingItemClick) {
        this.mOnLSettingItemClick = mOnLSettingItemClick;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSettingItem(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        getCustomStyle(context, null, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSettingItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        getCustomStyle(context, attributeSet, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSettingItem(Context context, AttributeSet attributeSet, int i) {
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
            switch (index) {
                case 2:
                    this.mLeftTextColorId = typedArrayObtainStyledAttributes.getResourceId(2, 0);
                    this.mTextColor = typedArrayObtainStyledAttributes.getColor(index, -3355444);
                    TextView textView = this.mTvLeftText;
                    Intrinsics.checkNotNull(textView);
                    textView.setTextColor(this.mTextColor);
                    break;
                case 3:
                    float f = typedArrayObtainStyledAttributes.getFloat(index, 16.0f);
                    TextView textView2 = this.mTvLeftText;
                    Intrinsics.checkNotNull(textView2);
                    textView2.setTextSize(f);
                    break;
                case 4:
                    if (typedArrayObtainStyledAttributes.getBoolean(index, false)) {
                        TextView textView3 = this.mTvRightText;
                        Intrinsics.checkNotNull(textView3);
                        textView3.setVisibility(0);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (typedArrayObtainStyledAttributes.getBoolean(index, true)) {
                        break;
                    } else {
                        View view = this.mUnderLine;
                        Intrinsics.checkNotNull(view);
                        view.setVisibility(8);
                        break;
                    }
                case 7:
                    this.mLeftIconId = typedArrayObtainStyledAttributes.getResourceId(7, 0);
                    Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(index);
                    this.mLeftIcon = drawable;
                    if (drawable != null) {
                        ImageView imageView = this.mIvLeftIcon;
                        Intrinsics.checkNotNull(imageView);
                        imageView.setVisibility(0);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    this.mLeftIconSzie = (int) typedArrayObtainStyledAttributes.getDimension(index, 16.0f);
                    ImageView imageView2 = this.mIvLeftIcon;
                    Intrinsics.checkNotNull(imageView2);
                    ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
                    Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
                    ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
                    layoutParams2.width = this.mLeftIconSzie;
                    layoutParams2.height = this.mLeftIconSzie;
                    ImageView imageView3 = this.mIvLeftIcon;
                    Intrinsics.checkNotNull(imageView3);
                    imageView3.setLayoutParams(layoutParams2);
                    break;
                case 9:
                    this.mLeftSubText = typedArrayObtainStyledAttributes.getString(index);
                    TextView textView4 = this.mTvLeftSubText;
                    Intrinsics.checkNotNull(textView4);
                    textView4.setText(this.mLeftSubText);
                    break;
                case 10:
                    this.mLeftText = typedArrayObtainStyledAttributes.getString(index);
                    TextView textView5 = this.mTvLeftText;
                    Intrinsics.checkNotNull(textView5);
                    textView5.setText(this.mLeftText);
                    break;
                case 11:
                    int dimension = (int) typedArrayObtainStyledAttributes.getDimension(index, 8.0f);
                    TextView textView6 = this.mTvLeftText;
                    Intrinsics.checkNotNull(textView6);
                    ViewGroup.LayoutParams layoutParams3 = textView6.getLayoutParams();
                    Objects.requireNonNull(layoutParams3, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
                    ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
                    layoutParams4.leftMargin = dimension;
                    TextView textView7 = this.mTvLeftText;
                    Intrinsics.checkNotNull(textView7);
                    textView7.setLayoutParams(layoutParams4);
                    break;
                case 12:
                    this.mRightIconId = typedArrayObtainStyledAttributes.getResourceId(12, 0);
                    this.mRightIcon = typedArrayObtainStyledAttributes.getDrawable(index);
                    break;
                case 13:
                    this.mRightStyle = typedArrayObtainStyledAttributes.getInt(index, 0);
                    break;
                case 14:
                    TextView textView8 = this.mTvRightText;
                    Intrinsics.checkNotNull(textView8);
                    textView8.setText(typedArrayObtainStyledAttributes.getString(index));
                    break;
                case 15:
                    this.mRightTextColorId = typedArrayObtainStyledAttributes.getResourceId(15, 0);
                    this.mRightTextColor = typedArrayObtainStyledAttributes.getColor(index, -7829368);
                    break;
                case 16:
                    this.mRightTextSize = typedArrayObtainStyledAttributes.getFloat(index, 12.0f);
                    TextView textView9 = this.mTvRightText;
                    Intrinsics.checkNotNull(textView9);
                    textView9.setTextSize(this.mRightTextSize);
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        switchRightStyle(this.mRightStyle);
        ConstraintLayout constraintLayout = this.mRootLayout;
        Intrinsics.checkNotNull(constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSettingItem$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QSettingItem.m317getCustomStyle$lambda0(this.f$0, view2);
            }
        });
        AppCompatCheckBox appCompatCheckBox = this.mRightIcon_check;
        Intrinsics.checkNotNull(appCompatCheckBox);
        appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSettingItem$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                QSettingItem.m318getCustomStyle$lambda1(this.f$0, compoundButton, z);
            }
        });
        QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat);
        qSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSettingItem$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                QSettingItem.m319getCustomStyle$lambda2(this.f$0, compoundButton, z);
            }
        });
        applyTopBarBackgroundColor();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this.mIvLeftIcon);
        this.mSkinCompatBackgroundHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper2 = this.mSkinCompatBackgroundHelper;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper3 = null;
        if (skinCompatBackgroundHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSkinCompatBackgroundHelper");
            skinCompatBackgroundHelper2 = null;
        }
        skinCompatBackgroundHelper2.onSetBackgroundResource(this.mLeftIconId);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper4 = new SkinCompatBackgroundHelper(this.mIvRightIcon);
        this.mSkinCompatBackgroundHelper1 = skinCompatBackgroundHelper4;
        skinCompatBackgroundHelper4.loadFromAttributes(attrs, defStyleAttr);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper5 = this.mSkinCompatBackgroundHelper1;
        if (skinCompatBackgroundHelper5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSkinCompatBackgroundHelper1");
        } else {
            skinCompatBackgroundHelper3 = skinCompatBackgroundHelper5;
        }
        skinCompatBackgroundHelper3.onSetBackgroundResource(this.mRightIconId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCustomStyle$lambda-0, reason: not valid java name */
    public static final void m317getCustomStyle$lambda0(QSettingItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.clickOn(view.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCustomStyle$lambda-1, reason: not valid java name */
    public static final void m318getCustomStyle$lambda1(QSettingItem this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnLSettingItemClick onLSettingItemClick = this$0.mOnLSettingItemClick;
        if (onLSettingItemClick != null) {
            Intrinsics.checkNotNull(onLSettingItemClick);
            onLSettingItemClick.click(compoundButton.getId(), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCustomStyle$lambda-2, reason: not valid java name */
    public static final void m319getCustomStyle$lambda2(QSettingItem this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnLSettingItemClick onLSettingItemClick = this$0.mOnLSettingItemClick;
        if (onLSettingItemClick != null) {
            Intrinsics.checkNotNull(onLSettingItemClick);
            onLSettingItemClick.click(compoundButton.getId(), z);
        }
    }

    private final void applyTopBarBackgroundColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mLeftTextColorId);
        if (iCheckResourceId != 0) {
            int color = SkinCompatResources.getColor(getContext(), iCheckResourceId);
            TextView textView = this.mTvLeftText;
            Intrinsics.checkNotNull(textView);
            textView.setTextColor(color);
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.mRightTextColorId);
        if (iCheckResourceId2 != 0) {
            int color2 = SkinCompatResources.getColor(getContext(), iCheckResourceId2);
            TextView textView2 = this.mTvRightText;
            Intrinsics.checkNotNull(textView2);
            textView2.setTextColor(color2);
        }
    }

    private final void switchRightStyle(int mRightStyle) {
        if (mRightStyle == 0) {
            ImageView imageView = this.mIvRightIcon;
            Intrinsics.checkNotNull(imageView);
            imageView.setVisibility(0);
            AppCompatCheckBox appCompatCheckBox = this.mRightIcon_check;
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setVisibility(8);
            QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
            Intrinsics.checkNotNull(qSwitchCompat);
            qSwitchCompat.setVisibility(8);
            return;
        }
        if (mRightStyle == 1) {
            FrameLayout frameLayout = this.mRightLayout;
            Intrinsics.checkNotNull(frameLayout);
            frameLayout.setVisibility(4);
            return;
        }
        if (mRightStyle == 2) {
            ImageView imageView2 = this.mIvRightIcon;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setVisibility(8);
            AppCompatCheckBox appCompatCheckBox2 = this.mRightIcon_check;
            Intrinsics.checkNotNull(appCompatCheckBox2);
            appCompatCheckBox2.setVisibility(0);
            QSwitchCompat qSwitchCompat2 = this.mRightIcon_switch;
            Intrinsics.checkNotNull(qSwitchCompat2);
            qSwitchCompat2.setVisibility(8);
            return;
        }
        if (mRightStyle != 3) {
            return;
        }
        ImageView imageView3 = this.mIvRightIcon;
        Intrinsics.checkNotNull(imageView3);
        imageView3.setVisibility(8);
        AppCompatCheckBox appCompatCheckBox3 = this.mRightIcon_check;
        Intrinsics.checkNotNull(appCompatCheckBox3);
        appCompatCheckBox3.setVisibility(8);
        QSwitchCompat qSwitchCompat3 = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat3);
        qSwitchCompat3.setVisibility(0);
    }

    private final void initView(Context context) {
        View viewInflate = ConstraintLayout.inflate(context, R.layout.qc_device_item, this);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layout.qc_device_item, this)");
        this.mRootLayout = (ConstraintLayout) viewInflate.findViewById(R.id.rootLayout);
        this.mTvLeftText = (TextView) viewInflate.findViewById(R.id.tv_lefttext);
        this.mTvRightText = (TextView) viewInflate.findViewById(R.id.tv_righttext);
        this.mIvLeftIcon = (ImageView) viewInflate.findViewById(R.id.iv_lefticon);
        this.mIvRightIcon = (ImageView) viewInflate.findViewById(R.id.iv_righticon);
        this.mRightLayout = (FrameLayout) viewInflate.findViewById(R.id.rightlayout);
        this.mRightIcon_check = (AppCompatCheckBox) viewInflate.findViewById(R.id.rightcheck);
        this.mRightIcon_switch = (QSwitchCompat) viewInflate.findViewById(R.id.rightswitch);
        this.mTvLeftSubText = (TextView) viewInflate.findViewById(R.id.tv_left_sub_text);
        this.mErrorIcon = (ImageView) viewInflate.findViewById(R.id.image_error);
    }

    private final void clickOn(int id) {
        int i = this.mRightStyle;
        if (i == 0 || i == 1) {
            OnLSettingItemClick onLSettingItemClick = this.mOnLSettingItemClick;
            if (onLSettingItemClick != null) {
                Intrinsics.checkNotNull(onLSettingItemClick);
                onLSettingItemClick.click(id, this.mChecked);
                return;
            }
            return;
        }
        if (i == 2) {
            AppCompatCheckBox appCompatCheckBox = this.mRightIcon_check;
            Intrinsics.checkNotNull(appCompatCheckBox);
            Intrinsics.checkNotNull(this.mRightIcon_check);
            appCompatCheckBox.setChecked(!r0.isChecked());
            AppCompatCheckBox appCompatCheckBox2 = this.mRightIcon_check;
            Intrinsics.checkNotNull(appCompatCheckBox2);
            this.mChecked = appCompatCheckBox2.isChecked();
            return;
        }
        if (i != 3) {
            return;
        }
        QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat);
        Intrinsics.checkNotNull(this.mRightIcon_switch);
        qSwitchCompat.setChecked(!r0.isChecked());
        AppCompatCheckBox appCompatCheckBox3 = this.mRightIcon_check;
        Intrinsics.checkNotNull(appCompatCheckBox3);
        this.mChecked = appCompatCheckBox3.isChecked();
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

    public final void setRightText(String info) {
        TextView textView = this.mTvRightText;
        Intrinsics.checkNotNull(textView);
        textView.setText(info);
    }

    public final void setChecked(boolean checked) {
        QSwitchCompat qSwitchCompat = this.mRightIcon_switch;
        Intrinsics.checkNotNull(qSwitchCompat);
        qSwitchCompat.setChecked(checked);
    }

    public final void setErrorIcon(boolean flag) {
        if (flag) {
            ViewKt.visible(this.mErrorIcon);
        } else {
            ViewKt.gone(this.mErrorIcon);
        }
    }

    public final void setRightIconGone() {
        ImageView imageView = this.mIvRightIcon;
        Intrinsics.checkNotNull(imageView);
        imageView.setVisibility(8);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        try {
            applyTopBarBackgroundColor();
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
            postInvalidate();
        } catch (Exception unused) {
        }
    }
}
