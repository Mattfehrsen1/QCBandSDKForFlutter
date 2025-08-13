package com.qcwireless.qcwatch.ui.activity;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.databinding.ActivityAppLockBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppLockActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/AppLockActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityAppLockBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AppLockActivity extends BaseActivity {
    private ActivityAppLockBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAppLockBinding activityAppLockBindingInflate = ActivityAppLockBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAppLockBindingInflate, "inflate(layoutInflater)");
        this.binding = activityAppLockBindingInflate;
        if (activityAppLockBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAppLockBindingInflate = null;
        }
        ConstraintLayout root = activityAppLockBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
    }
}
