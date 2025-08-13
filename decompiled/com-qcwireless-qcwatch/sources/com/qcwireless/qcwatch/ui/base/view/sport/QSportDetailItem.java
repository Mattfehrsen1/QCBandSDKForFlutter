package com.qcwireless.qcwatch.ui.base.view.sport;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/* compiled from: QSportDetailItem.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0002J \u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0012J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0012R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/sport/QSportDetailItem;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lskin/support/widget/SkinCompatSupportable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "drawableTitleId", "icon", "Landroid/widget/ImageView;", "mSkinCompatBackgroundHelper", "Lskin/support/widget/SkinCompatBackgroundHelper;", "titleStr", "", "titleText", "Landroid/widget/TextView;", "unitStr", "unitText", "valueText", "applySkin", "", "applyTopBarBackgroundColor", "getCustomStyle", "initView", "setTextMarquee", "setUnit", "text", "setValue", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSportDetailItem extends ConstraintLayout implements SkinCompatSupportable {
    private int drawableTitleId;
    private ImageView icon;
    private SkinCompatBackgroundHelper mSkinCompatBackgroundHelper;
    private String titleStr;
    private TextView titleText;
    private String unitStr;
    private TextView unitText;
    private TextView valueText;

    private final void applyTopBarBackgroundColor() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSportDetailItem(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.titleStr = "";
        this.unitStr = "";
        getCustomStyle(context, null, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSportDetailItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.titleStr = "";
        this.unitStr = "";
        getCustomStyle(context, attributeSet, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSportDetailItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.titleStr = "";
        this.unitStr = "";
        getCustomStyle(context, attributeSet, i);
    }

    public final void getCustomStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper;
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.QSportDetailView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…yleable.QSportDetailView)");
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        int i = 0;
        while (true) {
            skinCompatBackgroundHelper = null;
            TextView textView = null;
            TextView textView2 = null;
            if (i >= indexCount) {
                break;
            }
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == 0) {
                this.drawableTitleId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            } else if (index == 1) {
                this.titleStr = String.valueOf(typedArrayObtainStyledAttributes.getString(index));
                TextView textView3 = this.titleText;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("titleText");
                } else {
                    textView2 = textView3;
                }
                textView2.setText(this.titleStr);
            } else if (index == 2) {
                this.unitStr = String.valueOf(typedArrayObtainStyledAttributes.getString(index));
                TextView textView4 = this.unitText;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitText");
                } else {
                    textView = textView4;
                }
                textView.setText(this.unitStr);
            }
            i++;
        }
        typedArrayObtainStyledAttributes.recycle();
        ImageView imageView = this.icon;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("icon");
            imageView = null;
        }
        SkinCompatBackgroundHelper skinCompatBackgroundHelper2 = new SkinCompatBackgroundHelper(imageView);
        this.mSkinCompatBackgroundHelper = skinCompatBackgroundHelper2;
        skinCompatBackgroundHelper2.loadFromAttributes(attrs, defStyleAttr);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper3 = this.mSkinCompatBackgroundHelper;
        if (skinCompatBackgroundHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSkinCompatBackgroundHelper");
        } else {
            skinCompatBackgroundHelper = skinCompatBackgroundHelper3;
        }
        skinCompatBackgroundHelper.onSetBackgroundResource(this.drawableTitleId);
    }

    public final void setValue(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.valueText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueText");
            textView = null;
        }
        textView.setText(text);
    }

    public final void setUnit(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.unitText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitText");
            textView = null;
        }
        textView.setText(text);
    }

    private final void initView(Context context) {
        View viewInflate = ConstraintLayout.inflate(context, R.layout.sport_detail_item, this);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layout.sport_detail_item, this)");
        View viewFindViewById = viewInflate.findViewById(R.id.tv_item_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "mView.findViewById(R.id.tv_item_title)");
        this.titleText = (TextView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.image_item);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "mView.findViewById(R.id.image_item)");
        this.icon = (ImageView) viewFindViewById2;
        View viewFindViewById3 = viewInflate.findViewById(R.id.tv_item_value);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "mView.findViewById(R.id.tv_item_value)");
        this.valueText = (TextView) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.tv_item_value_unit);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "mView.findViewById(R.id.tv_item_value_unit)");
        this.unitText = (TextView) viewFindViewById4;
        setTextMarquee();
    }

    private final void setTextMarquee() {
        TextView textView = this.titleText;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView = null;
        }
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        TextView textView3 = this.titleText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView3 = null;
        }
        textView3.setSingleLine();
        TextView textView4 = this.titleText;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView4 = null;
        }
        textView4.setSelected(true);
        TextView textView5 = this.titleText;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView5 = null;
        }
        textView5.setFocusable(true);
        TextView textView6 = this.titleText;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView6 = null;
        }
        textView6.setFocusableInTouchMode(true);
        TextView textView7 = this.unitText;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitText");
            textView7 = null;
        }
        textView7.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        TextView textView8 = this.unitText;
        if (textView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitText");
            textView8 = null;
        }
        textView8.setSingleLine();
        TextView textView9 = this.unitText;
        if (textView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitText");
            textView9 = null;
        }
        textView9.setSelected(true);
        TextView textView10 = this.unitText;
        if (textView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitText");
            textView10 = null;
        }
        textView10.setFocusable(true);
        TextView textView11 = this.unitText;
        if (textView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitText");
        } else {
            textView2 = textView11;
        }
        textView2.setFocusableInTouchMode(true);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mSkinCompatBackgroundHelper;
        if (skinCompatBackgroundHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSkinCompatBackgroundHelper");
            skinCompatBackgroundHelper = null;
        }
        skinCompatBackgroundHelper.applySkin();
    }
}
