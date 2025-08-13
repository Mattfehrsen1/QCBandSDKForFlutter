package com.qcwireless.qcwatch.ui.mine.about;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.databinding.ActivityTestBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/about/TestActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityTestBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TestActivity extends BaseActivity {
    private ActivityTestBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding activityTestBindingInflate = ActivityTestBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTestBindingInflate, "inflate(layoutInflater)");
        this.binding = activityTestBindingInflate;
        if (activityTestBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBindingInflate = null;
        }
        ConstraintLayout root = activityTestBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityTestBinding activityTestBinding = this.binding;
        ActivityTestBinding activityTestBinding2 = null;
        if (activityTestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBinding = null;
        }
        activityTestBinding.btn1.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.TestActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestActivity.m892setupViews$lambda0(this.f$0, view);
            }
        });
        ActivityTestBinding activityTestBinding3 = this.binding;
        if (activityTestBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTestBinding2 = activityTestBinding3;
        }
        activityTestBinding2.btn2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.TestActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestActivity.m893setupViews$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m892setupViews$lambda0(TestActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AudioManager audioManager = SystemServiceExtKt.getAudioManager(this$0);
        Intrinsics.checkNotNull(audioManager);
        audioManager.adjustStreamVolume(3, 1, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m893setupViews$lambda1(TestActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AudioManager audioManager = SystemServiceExtKt.getAudioManager(this$0);
        Intrinsics.checkNotNull(audioManager);
        audioManager.adjustStreamVolume(3, -1, 1);
    }
}
