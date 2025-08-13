package com.qcwireless.qcwatch.ui.home.heart;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.databinding.ActivityHeartHrvBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HrvGuideActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HrvGuideActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityHeartHrvBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HrvGuideActivity extends BaseActivity {
    private ActivityHeartHrvBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHeartHrvBinding activityHeartHrvBindingInflate = ActivityHeartHrvBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityHeartHrvBindingInflate, "inflate(layoutInflater)");
        this.binding = activityHeartHrvBindingInflate;
        if (activityHeartHrvBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartHrvBindingInflate = null;
        }
        ConstraintLayout root = activityHeartHrvBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityHeartHrvBinding activityHeartHrvBinding = this.binding;
        if (activityHeartHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartHrvBinding = null;
        }
        activityHeartHrvBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_8074));
    }
}
