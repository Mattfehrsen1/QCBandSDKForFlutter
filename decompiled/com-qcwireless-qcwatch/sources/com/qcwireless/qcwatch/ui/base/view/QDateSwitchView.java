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

/* compiled from: QDateSwitchView.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001fB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\fJ\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006 "}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateSwitchView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "dateListener", "Lcom/qcwireless/qcwatch/ui/base/view/QDateSwitchView$QDateBefore;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "mAfter", "Landroid/widget/ImageView;", "mBefore", "mTitle", "Landroid/widget/TextView;", "sportFlag", "", "getSportFlag", "()Z", "setSportFlag", "(Z)V", "initView", "", "setDateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setDateUtil", "QDateBefore", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QDateSwitchView extends ConstraintLayout {
    private QDateBefore dateListener;
    private DateUtil dateUtil;
    private ImageView mAfter;
    private ImageView mBefore;
    private TextView mTitle;
    private boolean sportFlag;

    /* compiled from: QDateSwitchView.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QDateSwitchView$QDateBefore;", "", "clickBefore", "", TypedValues.Custom.S_BOOLEAN, "", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface QDateBefore {
        void clickBefore(boolean z, DateUtil dateUtil);
    }

    public final boolean getSportFlag() {
        return this.sportFlag;
    }

    public final void setSportFlag(boolean z) {
        this.sportFlag = z;
    }

    public final void setDateUtil(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        if (dateUtil.getZeroTime() > new DateUtil().getZeroTime()) {
            ViewKt.gone(this.mAfter);
            return;
        }
        if (dateUtil.isToday() || QcDateUtil.INSTANCE.getGetInstance().sportIsToday(dateUtil)) {
            this.dateUtil = dateUtil;
            if (this.sportFlag) {
                TextView textView = this.mTitle;
                Intrinsics.checkNotNull(textView);
                textView.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormatSport(dateUtil));
            } else {
                TextView textView2 = this.mTitle;
                Intrinsics.checkNotNull(textView2);
                textView2.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(dateUtil));
            }
            ViewKt.gone(this.mAfter);
            return;
        }
        ViewKt.visible(this.mAfter);
        this.dateUtil = dateUtil;
        if (this.sportFlag) {
            TextView textView3 = this.mTitle;
            Intrinsics.checkNotNull(textView3);
            textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormatSport(dateUtil));
        } else {
            TextView textView4 = this.mTitle;
            Intrinsics.checkNotNull(textView4);
            textView4.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(dateUtil));
        }
    }

    public final void setDateListener(QDateBefore listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.dateListener = listener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateSwitchView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QDateSwitchView(Context context, AttributeSet attributeSet, int i) {
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
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateSwitchView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateSwitchView.m310initView$lambda1(this.f$0, view);
            }
        });
        ImageView imageView2 = this.mAfter;
        Intrinsics.checkNotNull(imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QDateSwitchView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QDateSwitchView.m311initView$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1, reason: not valid java name */
    public static final void m310initView$lambda1(QDateSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addDay(-1);
            if (this$0.sportFlag) {
                TextView textView = this$0.mTitle;
                Intrinsics.checkNotNull(textView);
                QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
                DateUtil dateUtil2 = this$0.dateUtil;
                Intrinsics.checkNotNull(dateUtil2);
                textView.setText(getInstance.localDateFormatSport(dateUtil2));
            } else {
                TextView textView2 = this$0.mTitle;
                Intrinsics.checkNotNull(textView2);
                QcDateUtil getInstance2 = QcDateUtil.INSTANCE.getGetInstance();
                DateUtil dateUtil3 = this$0.dateUtil;
                Intrinsics.checkNotNull(dateUtil3);
                textView2.setText(getInstance2.localDateFormat(dateUtil3));
            }
        }
        DateUtil dateUtil4 = this$0.dateUtil;
        if (dateUtil4 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(true, dateUtil4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3, reason: not valid java name */
    public static final void m311initView$lambda3(QDateSwitchView this$0, View view) {
        QDateBefore qDateBefore;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = this$0.dateUtil;
        if (dateUtil != null) {
            Intrinsics.checkNotNull(dateUtil);
            dateUtil.addDay(1);
            if (this$0.sportFlag) {
                TextView textView = this$0.mTitle;
                Intrinsics.checkNotNull(textView);
                QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
                DateUtil dateUtil2 = this$0.dateUtil;
                Intrinsics.checkNotNull(dateUtil2);
                textView.setText(getInstance.localDateFormatSport(dateUtil2));
            } else {
                TextView textView2 = this$0.mTitle;
                Intrinsics.checkNotNull(textView2);
                QcDateUtil getInstance2 = QcDateUtil.INSTANCE.getGetInstance();
                DateUtil dateUtil3 = this$0.dateUtil;
                Intrinsics.checkNotNull(dateUtil3);
                textView2.setText(getInstance2.localDateFormat(dateUtil3));
            }
        }
        DateUtil dateUtil4 = this$0.dateUtil;
        if (dateUtil4 == null || (qDateBefore = this$0.dateListener) == null) {
            return;
        }
        qDateBefore.clickBefore(false, dateUtil4);
    }
}
