package com.qcwireless.qcwatch.ui.base.view.skin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QSkinItemView.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/skin/QSkinItemView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "checkImage", "Landroid/widget/ImageView;", "tvBg", "Landroid/widget/TextView;", "tvTitle", "initView", "", "setCheckStatus", "check", "", "setSkinBg", "drawableId", "setSkinName", "name", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSkinItemView extends ConstraintLayout {
    private ImageView checkImage;
    private TextView tvBg;
    private TextView tvTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSkinItemView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSkinItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSkinItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        initView(context);
    }

    public final void initView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        View viewInflate = ConstraintLayout.inflate(context, R.layout.view_skin_item, this);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layout.view_skin_item, this)");
        this.tvTitle = (TextView) viewInflate.findViewById(R.id.tv_skin_name);
        this.tvBg = (TextView) viewInflate.findViewById(R.id.tv_skin_bg);
        this.checkImage = (ImageView) viewInflate.findViewById(R.id.image_skin_check);
    }

    public final void setSkinName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        TextView textView = this.tvTitle;
        Intrinsics.checkNotNull(textView);
        textView.setText(name);
    }

    public final void setSkinBg(int drawableId) {
        TextView textView = this.tvBg;
        Intrinsics.checkNotNull(textView);
        textView.setBackgroundResource(drawableId);
    }

    public final void setCheckStatus(boolean check) {
        if (check) {
            ImageView imageView = this.checkImage;
            Intrinsics.checkNotNull(imageView);
            imageView.setBackgroundResource(R.mipmap.qc_check_on);
        } else {
            ImageView imageView2 = this.checkImage;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setBackgroundResource(R.mipmap.qc_check_off);
        }
    }
}
