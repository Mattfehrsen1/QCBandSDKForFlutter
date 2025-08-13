package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QDateYearSwitchView.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001aB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\fJ\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u000eR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateYearSwitchView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "dateListener", "Lcom/qcwireless/qcwatch/ui/base/view/QDateYearSwitchView$QDateBefore;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "mAfter", "Landroid/widget/ImageView;", "mBefore", "mTitle", "Landroid/widget/TextView;", "initView", "", "setDateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setMonth", "start", "QDateBefore", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QDateYearSwitchView extends ConstraintLayout {
    private QDateBefore dateListener;
    private DateUtil dateUtil;
    private ImageView mAfter;
    private ImageView mBefore;
    private TextView mTitle;

    /* compiled from: QDateYearSwitchView.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateYearSwitchView$QDateBefore;", "", "clickBefore", "", TypedValues.Custom.S_BOOLEAN, "", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface QDateBefore {
        void clickBefore(boolean z, DateUtil dateUtil);
    }

    public final void setMonth(DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        this.dateUtil = start;
        TextView textView = this.mTitle;
        Intrinsics.checkNotNull(textView);
        DateUtil dateUtil = this.dateUtil;
        Intrinsics.checkNotNull(dateUtil);
        textView.setText(String.valueOf(dateUtil.getYear()));
    }

    public final void setDateListener(QDateBefore listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.dateListener = listener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateYearSwitchView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateYearSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateYearSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    private final void initView(Context context) {
        View viewInflate = ConstraintLayout.inflate(context, R.layout.view_date_switch, this);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layout.view_date_switch, this)");
        this.mBefore = (ImageView) viewInflate.findViewById(R.id.image_before);
        this.mAfter = (ImageView) viewInflate.findViewById(R.id.image_next);
        this.mTitle = (TextView) viewInflate.findViewById(R.id.tv_date_title);
        ImageView imageView = this.mBefore;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateYearSwitchView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateYearSwitchView.m315initView$lambda1(this.f$0, view);
            }
        });
        ImageView imageView2 = this.mAfter;
        Intrinsics.checkNotNull(imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateYearSwitchView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateYearSwitchView.m316initView$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1, reason: not valid java name */
    public static final void m315initView$lambda1(QDateYearSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addMonth(-12);
            TextView textView = this$0.mTitle;
            Intrinsics.checkNotNull(textView);
            DateUtil dateUtil2 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil2);
            textView.setText(String.valueOf(dateUtil2.getYear()));
        }
        DateUtil dateUtil3 = this$0.dateUtil;
        if (dateUtil3 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(true, dateUtil3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3, reason: not valid java name */
    public static final void m316initView$lambda3(QDateYearSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addMonth(12);
            TextView textView = this$0.mTitle;
            Intrinsics.checkNotNull(textView);
            DateUtil dateUtil2 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil2);
            textView.setText(String.valueOf(dateUtil2.getYear()));
        }
        DateUtil dateUtil3 = this$0.dateUtil;
        if (dateUtil3 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(false, dateUtil3);
    }
}
