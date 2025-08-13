package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.NotificationCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.ViewKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QSleepAnalysisView.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0015J\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0015J\u0016\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSleepAnalysisView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "tvStatus", "Landroid/widget/TextView;", "tvUnit1", "tvUnit2", "tvValue", "tvValue2", "initView", "", "setStatus", NotificationCompat.CATEGORY_STATUS, "", "drawable", "Landroid/graphics/drawable/Drawable;", "setStatusNull", "setTitleValue", "text", "setValue2", "setValueAndUnit2", "unit", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSleepAnalysisView extends ConstraintLayout {
    private TextView tvStatus;
    private TextView tvUnit1;
    private TextView tvUnit2;
    private TextView tvValue;
    private TextView tvValue2;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSleepAnalysisView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSleepAnalysisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSleepAnalysisView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context, attributeSet);
    }

    private final void initView(Context context, AttributeSet attrs) {
        View viewInflate = ConstraintLayout.inflate(context, R.layout.view_sleep_analysis, this);
        View viewFindViewById = viewInflate.findViewById(R.id.tv_sleep_hour);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.tv_sleep_hour)");
        this.tvValue = (TextView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.tv_sleep_status);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.tv_sleep_status)");
        this.tvStatus = (TextView) viewFindViewById2;
        View viewFindViewById3 = viewInflate.findViewById(R.id.tv_sleep_unit_h);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.tv_sleep_unit_h)");
        this.tvUnit1 = (TextView) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.tv_sleep_unit_min);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.tv_sleep_unit_min)");
        this.tvUnit2 = (TextView) viewFindViewById4;
        View viewFindViewById5 = viewInflate.findViewById(R.id.tv_sleep_min);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.tv_sleep_min)");
        this.tvValue2 = (TextView) viewFindViewById5;
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_sleep_total_time);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_1);
        Group group = (Group) viewInflate.findViewById(R.id.tv_detail_group);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.QSleepItem);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…, R.styleable.QSleepItem)");
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index != 0) {
                if (index == 1) {
                    textView2.setText(typedArrayObtainStyledAttributes.getString(index));
                } else if (index == 2) {
                    textView.setText(typedArrayObtainStyledAttributes.getString(index));
                } else if (index == 3) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    TextView textView3 = this.tvUnit1;
                    if (textView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tvUnit1");
                        textView3 = null;
                    }
                    textView3.setText(string);
                }
            } else if (typedArrayObtainStyledAttributes.getBoolean(index, false)) {
                ViewKt.visible(group);
            } else {
                ViewKt.gone(group);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void setTitleValue(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.tvValue;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvValue");
            textView = null;
        }
        textView.setText(text);
    }

    public final void setValue2(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.tvValue2;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvValue2");
            textView = null;
        }
        textView.setText(text);
    }

    public final void setValueAndUnit2(String text, String unit) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(unit, "unit");
        TextView textView = this.tvValue2;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvValue2");
            textView = null;
        }
        textView.setText(text);
        TextView textView3 = this.tvUnit2;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvUnit2");
        } else {
            textView2 = textView3;
        }
        textView2.setText(unit);
    }

    public final void setStatus(String status, Drawable drawable) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        TextView textView = this.tvStatus;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
            textView = null;
        }
        textView.setText(status);
        TextView textView3 = this.tvStatus;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
        } else {
            textView2 = textView3;
        }
        textView2.setBackground(drawable);
    }

    public final void setStatusNull(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        TextView textView = this.tvStatus;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
            textView = null;
        }
        textView.setText(status);
        TextView textView2 = this.tvStatus;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
            textView2 = null;
        }
        textView2.setBackground(null);
    }
}
