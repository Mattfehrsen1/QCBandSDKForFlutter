package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QSportItemView.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000fR\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSportItemView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "tvSize", "tvTitle", "Landroid/widget/TextView;", "tvUnit", "", "tvUnitView", "getCustomStyle", "", "initView", "setItemTitle", "text", "setItemUnit", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSportItemView extends ConstraintLayout {
    private int tvSize;
    private TextView tvTitle;
    private String tvUnit;
    private TextView tvUnitView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSportItemView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.tvUnit = "";
        initView(context);
        getCustomStyle(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSportItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.tvUnit = "";
        initView(context);
        getCustomStyle(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSportItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.tvUnit = "";
        initView(context);
        getCustomStyle(context, attributeSet);
    }

    private final void getCustomStyle(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.QSportItem);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…, R.styleable.QSportItem)");
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            TextView textView = null;
            if (index == 0) {
                this.tvSize = typedArrayObtainStyledAttributes.getInteger(0, 18);
                TextView textView2 = this.tvTitle;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvTitle");
                } else {
                    textView = textView2;
                }
                textView.setTextSize(this.tvSize);
            } else if (index == 1) {
                TextView textView3 = this.tvTitle;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvTitle");
                } else {
                    textView = textView3;
                }
                textView.setText(String.valueOf(typedArrayObtainStyledAttributes.getString(index)));
            } else if (index == 2) {
                this.tvUnit = String.valueOf(typedArrayObtainStyledAttributes.getString(index));
                TextView textView4 = this.tvUnitView;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvUnitView");
                } else {
                    textView = textView4;
                }
                textView.setText(this.tvUnit);
            }
        }
    }

    private final void initView(Context context) {
        View viewInflate = ConstraintLayout.inflate(context, R.layout.qc_sport_item, this);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layout.qc_sport_item, this)");
        View viewFindViewById = viewInflate.findViewById(R.id.tv_2);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "mView.findViewById(R.id.tv_2)");
        this.tvUnitView = (TextView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.tv_1);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "mView.findViewById(R.id.tv_1)");
        this.tvTitle = (TextView) viewFindViewById2;
    }

    public final void setItemTitle(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.tvTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvTitle");
            textView = null;
        }
        textView.setText(text);
    }

    public final void setItemUnit(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.tvUnitView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvUnitView");
            textView = null;
        }
        textView.setText(text);
    }
}
