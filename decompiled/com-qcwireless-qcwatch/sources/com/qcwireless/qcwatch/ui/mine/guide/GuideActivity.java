package com.qcwireless.qcwatch.ui.mine.guide;

import android.os.Bundle;
import android.webkit.WebSettings;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.base.view.Localization;
import com.qcwireless.qcwatch.databinding.ActivityGuideBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GuideActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/guide/GuideActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGuideBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GuideActivity extends BaseActivity {
    private ActivityGuideBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGuideBinding activityGuideBindingInflate = ActivityGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGuideBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGuideBindingInflate;
        if (activityGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGuideBindingInflate = null;
        }
        ConstraintLayout root = activityGuideBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        ActivityGuideBinding activityGuideBinding = this.binding;
        ActivityGuideBinding activityGuideBinding2 = null;
        if (activityGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGuideBinding = null;
        }
        WebSettings settings = activityGuideBinding.webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "binding.webView.settings");
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(false);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDatabasePath(getDir("databases", 0).getPath());
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        try {
            String language = Locale.getDefault().getLanguage();
            if (StringsKt.equals("de", language, true)) {
                ActivityGuideBinding activityGuideBinding3 = this.binding;
                if (activityGuideBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGuideBinding3 = null;
                }
                activityGuideBinding3.webView.loadUrl("file:///android_asset/dist/index.html#/home/de");
                return;
            }
            if (StringsKt.equals(Localization.language, language, true)) {
                ActivityGuideBinding activityGuideBinding4 = this.binding;
                if (activityGuideBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGuideBinding4 = null;
                }
                activityGuideBinding4.webView.loadUrl("file:///android_asset/dist/index.html#/home/fr");
                return;
            }
            Intrinsics.checkNotNullExpressionValue(language, "language");
            if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
                ActivityGuideBinding activityGuideBinding5 = this.binding;
                if (activityGuideBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGuideBinding5 = null;
                }
                activityGuideBinding5.webView.loadUrl("file:///android_asset/dist/index.html#/home/zh");
                return;
            }
            if (StringsKt.equals("es", language, true)) {
                ActivityGuideBinding activityGuideBinding6 = this.binding;
                if (activityGuideBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGuideBinding6 = null;
                }
                activityGuideBinding6.webView.loadUrl("file:///android_asset/dist/index.html#/home/es");
                return;
            }
            if (StringsKt.equals("ja", language, true)) {
                ActivityGuideBinding activityGuideBinding7 = this.binding;
                if (activityGuideBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGuideBinding7 = null;
                }
                activityGuideBinding7.webView.loadUrl("file:///android_asset/dist/index.html#/home/ja");
                return;
            }
            if (StringsKt.equals("it", language, true)) {
                ActivityGuideBinding activityGuideBinding8 = this.binding;
                if (activityGuideBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGuideBinding8 = null;
                }
                activityGuideBinding8.webView.loadUrl("file:///android_asset/dist/index.html#/home/it");
                return;
            }
            ActivityGuideBinding activityGuideBinding9 = this.binding;
            if (activityGuideBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGuideBinding9 = null;
            }
            activityGuideBinding9.webView.loadUrl("file:///android_asset/dist/index.html#/home/en");
        } catch (Exception e) {
            e.printStackTrace();
            ActivityGuideBinding activityGuideBinding10 = this.binding;
            if (activityGuideBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGuideBinding2 = activityGuideBinding10;
            }
            activityGuideBinding2.webView.loadUrl("file:///android_asset/dist/index.html#/home/en");
        }
    }
}
