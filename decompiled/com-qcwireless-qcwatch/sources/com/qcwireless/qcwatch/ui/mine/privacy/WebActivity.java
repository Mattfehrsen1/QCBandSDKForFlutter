package com.qcwireless.qcwatch.ui.mine.privacy;

import android.os.Bundle;
import android.webkit.WebSettings;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.databinding.ActivityWebBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/privacy/WebActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityWebBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebActivity extends BaseActivity {
    private ActivityWebBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWebBinding activityWebBindingInflate = ActivityWebBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWebBindingInflate, "inflate(layoutInflater)");
        this.binding = activityWebBindingInflate;
        if (activityWebBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebBindingInflate = null;
        }
        ConstraintLayout root = activityWebBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityWebBinding activityWebBinding = this.binding;
        ActivityWebBinding activityWebBinding2 = null;
        if (activityWebBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebBinding = null;
        }
        activityWebBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_149));
        Bundle extras = getIntent().getExtras();
        String string = extras != null ? extras.getString("url") : null;
        XLog.i(string);
        ActivityWebBinding activityWebBinding3 = this.binding;
        if (activityWebBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebBinding3 = null;
        }
        WebSettings settings = activityWebBinding3.webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "binding.webView.settings");
        settings.setJavaScriptEnabled(false);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(-1);
        if (string != null) {
            ActivityWebBinding activityWebBinding4 = this.binding;
            if (activityWebBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWebBinding2 = activityWebBinding4;
            }
            activityWebBinding2.webView.loadUrl(string);
        }
    }
}
