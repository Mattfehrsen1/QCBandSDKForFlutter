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
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.ViewKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QDateMonthSwitchView.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\fJ\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u000eJ\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0015R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateMonthSwitchView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "dateListener", "Lcom/qcwireless/qcwatch/ui/base/view/QDateMonthSwitchView$QDateBefore;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "mAfter", "Landroid/widget/ImageView;", "mBefore", "mTitle", "Landroid/widget/TextView;", "showFuture", "", "initView", "", "setDateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setMonth", "start", "setShowFuture", "show", "QDateBefore", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QDateMonthSwitchView extends ConstraintLayout {
    private QDateBefore dateListener;
    private DateUtil dateUtil;
    private ImageView mAfter;
    private ImageView mBefore;
    private TextView mTitle;
    private boolean showFuture;

    /* compiled from: QDateMonthSwitchView.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateMonthSwitchView$QDateBefore;", "", "clickBefore", "", TypedValues.Custom.S_BOOLEAN, "", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface QDateBefore {
        void clickBefore(boolean z, DateUtil dateUtil);
    }

    public final void setMonth(DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        this.dateUtil = start;
        TextView textView = this.mTitle;
        Intrinsics.checkNotNull(textView);
        QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
        DateUtil dateUtil = this.dateUtil;
        Intrinsics.checkNotNull(dateUtil);
        textView.setText(getInstance.localDateNoDayFormat(dateUtil));
        if (this.showFuture) {
            return;
        }
        ViewKt.gone(this.mAfter);
    }

    public final void setShowFuture(boolean show) {
        this.showFuture = show;
    }

    public final void setDateListener(QDateBefore listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.dateListener = listener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateMonthSwitchView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateMonthSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateMonthSwitchView(Context context, AttributeSet attributeSet, int i) {
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
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateMonthSwitchView.m307initView$lambda1(this.f$0, view);
            }
        });
        ImageView imageView2 = this.mAfter;
        Intrinsics.checkNotNull(imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateMonthSwitchView.m308initView$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1, reason: not valid java name */
    public static final void m307initView$lambda1(QDateMonthSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addMonth(-1);
            TextView textView = this$0.mTitle;
            Intrinsics.checkNotNull(textView);
            QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
            DateUtil dateUtil2 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil2);
            textView.setText(getInstance.localDateNoDayFormat(dateUtil2));
        }
        ViewKt.visible(this$0.mAfter);
        DateUtil dateUtil3 = this$0.dateUtil;
        if (dateUtil3 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(true, dateUtil3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3, reason: not valid java name */
    public static final void m308initView$lambda3(QDateMonthSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addMonth(1);
            TextView textView = this$0.mTitle;
            Intrinsics.checkNotNull(textView);
            QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
            DateUtil dateUtil2 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil2);
            textView.setText(getInstance.localDateNoDayFormat(dateUtil2));
            if (!this$0.showFuture) {
                DateUtil dateUtil3 = this$0.dateUtil;
                Intrinsics.checkNotNull(dateUtil3);
                if (DateUtil.getFirstDayOfMonth(dateUtil3).getZeroTime() == DateUtil.getFirstDayOfMonth(new DateUtil()).getZeroTime()) {
                    ViewKt.gone(this$0.mAfter);
                }
            }
        }
        DateUtil dateUtil4 = this$0.dateUtil;
        if (dateUtil4 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(false, dateUtil4);
    }
}
