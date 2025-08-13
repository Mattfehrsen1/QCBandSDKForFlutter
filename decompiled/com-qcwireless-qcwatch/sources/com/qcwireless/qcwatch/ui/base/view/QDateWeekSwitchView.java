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

/* compiled from: QDateWeekSwitchView.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001bB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\fJ\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000eR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateWeekSwitchView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "dateListener", "Lcom/qcwireless/qcwatch/ui/base/view/QDateWeekSwitchView$QDateBefore;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "endDate", "mAfter", "Landroid/widget/ImageView;", "mBefore", "mTitle", "Landroid/widget/TextView;", "initView", "", "setDateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setWeekDayStart", "start", "QDateBefore", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QDateWeekSwitchView extends ConstraintLayout {
    private QDateBefore dateListener;
    private DateUtil dateUtil;
    private DateUtil endDate;
    private ImageView mAfter;
    private ImageView mBefore;
    private TextView mTitle;

    /* compiled from: QDateWeekSwitchView.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateWeekSwitchView$QDateBefore;", "", "clickBefore", "", TypedValues.Custom.S_BOOLEAN, "", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface QDateBefore {
        void clickBefore(boolean z, DateUtil dateUtil);
    }

    public final void setWeekDayStart(DateUtil start) {
        Intrinsics.checkNotNullParameter(start, "start");
        this.dateUtil = start;
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        this.endDate = dateUtil;
        Intrinsics.checkNotNull(dateUtil);
        dateUtil.addDay(6);
        TextView textView = this.mTitle;
        Intrinsics.checkNotNull(textView);
        QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
        DateUtil dateUtil2 = this.dateUtil;
        Intrinsics.checkNotNull(dateUtil2);
        DateUtil dateUtil3 = this.endDate;
        Intrinsics.checkNotNull(dateUtil3);
        textView.setText(getInstance.localDateNoYearFormat(dateUtil2, dateUtil3));
        ViewKt.gone(this.mAfter);
    }

    public final void setDateListener(QDateBefore listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.dateListener = listener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateWeekSwitchView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateWeekSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateWeekSwitchView(Context context, AttributeSet attributeSet, int i) {
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
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateWeekSwitchView.m313initView$lambda1(this.f$0, view);
            }
        });
        ImageView imageView2 = this.mAfter;
        Intrinsics.checkNotNull(imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateWeekSwitchView.m314initView$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1, reason: not valid java name */
    public static final void m313initView$lambda1(QDateWeekSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.dateUtil != null) {
            ViewKt.visible(this$0.mAfter);
            DateUtil dateUtil = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addDay(-7);
            DateUtil dateUtil2 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil2);
            DateUtil dateUtil3 = new DateUtil(dateUtil2.getUnixTimestamp(), true);
            this$0.endDate = dateUtil3;
            Intrinsics.checkNotNull(dateUtil3);
            dateUtil3.addDay(6);
            TextView textView = this$0.mTitle;
            Intrinsics.checkNotNull(textView);
            QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
            DateUtil dateUtil4 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil4);
            DateUtil dateUtil5 = this$0.endDate;
            Intrinsics.checkNotNull(dateUtil5);
            textView.setText(getInstance.localDateNoYearFormat(dateUtil4, dateUtil5));
        }
        DateUtil dateUtil6 = this$0.dateUtil;
        if (dateUtil6 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(true, dateUtil6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3, reason: not valid java name */
    public static final void m314initView$lambda3(QDateWeekSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addDay(7);
            DateUtil dateUtil2 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil2);
            DateUtil dateUtil3 = new DateUtil(dateUtil2.getUnixTimestamp(), true);
            this$0.endDate = dateUtil3;
            Intrinsics.checkNotNull(dateUtil3);
            dateUtil3.addDay(6);
            TextView textView = this$0.mTitle;
            Intrinsics.checkNotNull(textView);
            QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
            DateUtil dateUtil4 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil4);
            DateUtil dateUtil5 = this$0.endDate;
            Intrinsics.checkNotNull(dateUtil5);
            textView.setText(getInstance.localDateNoYearFormat(dateUtil4, dateUtil5));
            long zeroTime = DateUtil.firstDayOfWeek().getZeroTime();
            DateUtil dateUtil6 = this$0.dateUtil;
            Intrinsics.checkNotNull(dateUtil6);
            if (zeroTime == dateUtil6.getZeroTime()) {
                ViewKt.gone(this$0.mAfter);
            }
        }
        DateUtil dateUtil7 = this$0.dateUtil;
        if (dateUtil7 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(false, dateUtil7);
    }
}
