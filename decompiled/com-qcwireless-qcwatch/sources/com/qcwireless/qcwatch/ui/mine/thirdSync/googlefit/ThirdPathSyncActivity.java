package com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityThirdPathSyncBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.mine.privacy.LanguagePPMURLKt;
import com.qcwireless.qcwatch.ui.mine.privacy.WebActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThirdPathSyncActivity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\"\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0012\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0006H\u0014J\b\u0010\u0011\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/ThirdPathSyncActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityThirdPathSyncBinding;", "checkPermissions", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThirdPathSyncActivity extends BaseActivity {
    private ActivityThirdPathSyncBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityThirdPathSyncBinding activityThirdPathSyncBindingInflate = ActivityThirdPathSyncBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityThirdPathSyncBindingInflate, "inflate(layoutInflater)");
        this.binding = activityThirdPathSyncBindingInflate;
        if (activityThirdPathSyncBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThirdPathSyncBindingInflate = null;
        }
        ConstraintLayout root = activityThirdPathSyncBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityThirdPathSyncBinding activityThirdPathSyncBinding = this.binding;
        ActivityThirdPathSyncBinding activityThirdPathSyncBinding2 = null;
        if (activityThirdPathSyncBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThirdPathSyncBinding = null;
        }
        activityThirdPathSyncBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_454));
        ThirdPathSyncActivity thirdPathSyncActivity = this;
        activityThirdPathSyncBinding.tvGoogleFit.setText(getString(R.string.qc_text_455, new Object[]{GlobalKt.getAppName(thirdPathSyncActivity), GlobalKt.getAppName(thirdPathSyncActivity)}));
        activityThirdPathSyncBinding.qcGoogleFitSync.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.ThirdPathSyncActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ThirdPathSyncActivity.m985setupViews$lambda1$lambda0(this.f$0, compoundButton, z);
            }
        });
        View[] viewArr = new View[2];
        ActivityThirdPathSyncBinding activityThirdPathSyncBinding3 = this.binding;
        if (activityThirdPathSyncBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThirdPathSyncBinding3 = null;
        }
        viewArr[0] = activityThirdPathSyncBinding3.userPrivacy;
        ActivityThirdPathSyncBinding activityThirdPathSyncBinding4 = this.binding;
        if (activityThirdPathSyncBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThirdPathSyncBinding2 = activityThirdPathSyncBinding4;
        }
        viewArr[1] = activityThirdPathSyncBinding2.btnAuthor;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.ThirdPathSyncActivity$setupViews$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityThirdPathSyncBinding activityThirdPathSyncBinding5 = this.this$0.binding;
                ActivityThirdPathSyncBinding activityThirdPathSyncBinding6 = null;
                if (activityThirdPathSyncBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThirdPathSyncBinding5 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityThirdPathSyncBinding5.userPrivacy)) {
                    ActivityThirdPathSyncBinding activityThirdPathSyncBinding7 = this.this$0.binding;
                    if (activityThirdPathSyncBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityThirdPathSyncBinding6 = activityThirdPathSyncBinding7;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityThirdPathSyncBinding6.btnAuthor)) {
                        XLog.i(Boolean.valueOf(GoogleFitSync.INSTANCE.getGetInstance().connectGoogleFit(this.this$0)));
                        return;
                    }
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("url", LanguagePPMURLKt.getLanguagePPMUrl());
                ThirdPathSyncActivity thirdPathSyncActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(thirdPathSyncActivity2, (Class<?>) WebActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                thirdPathSyncActivity2.startActivity(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m985setupViews$lambda1$lambda0(ThirdPathSyncActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            if (z) {
                if (!GoogleFitSync.INSTANCE.getGetInstance().connectGoogleFit(this$0)) {
                    this$0.checkPermissions();
                }
                UserConfig.INSTANCE.getInstance().setGoogleFit(true);
            } else {
                UserConfig.INSTANCE.getInstance().setGoogleFit(false);
                GoogleFitSync.INSTANCE.getGetInstance().disconnectGoogleFit();
            }
            UserConfig.INSTANCE.getInstance().save();
        } catch (Exception unused) {
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ThirdPathSyncActivity thirdPathSyncActivity = this;
        if (ContextCompat.checkSelfPermission(thirdPathSyncActivity, Permission.ACTIVITY_RECOGNITION) != 0) {
            ContextCompat.checkSelfPermission(thirdPathSyncActivity, Permission.ACTIVITY_RECOGNITION);
        }
        checkPermissions();
    }

    private final void checkPermissions() {
        XLog.i(Boolean.valueOf(GoogleFitSync.INSTANCE.getGetInstance().hasGoogleFitPermissions()));
        ActivityThirdPathSyncBinding activityThirdPathSyncBinding = null;
        if (GoogleFitSync.INSTANCE.getGetInstance().hasGoogleFitPermissions() && GoogleFitSync.INSTANCE.getGetInstance().supportGoogleFit(this)) {
            if (UserConfig.INSTANCE.getInstance().getGoogleFit()) {
                ActivityThirdPathSyncBinding activityThirdPathSyncBinding2 = this.binding;
                if (activityThirdPathSyncBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityThirdPathSyncBinding = activityThirdPathSyncBinding2;
                }
                activityThirdPathSyncBinding.qcGoogleFitSync.setChecked(true);
                return;
            }
            ActivityThirdPathSyncBinding activityThirdPathSyncBinding3 = this.binding;
            if (activityThirdPathSyncBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityThirdPathSyncBinding = activityThirdPathSyncBinding3;
            }
            activityThirdPathSyncBinding.qcGoogleFitSync.setChecked(false);
            return;
        }
        UserConfig.INSTANCE.getInstance().setGoogleFit(false);
        UserConfig.INSTANCE.getInstance().save();
        ActivityThirdPathSyncBinding activityThirdPathSyncBinding4 = this.binding;
        if (activityThirdPathSyncBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThirdPathSyncBinding = activityThirdPathSyncBinding4;
        }
        activityThirdPathSyncBinding.qcGoogleFitSync.setChecked(false);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            GoogleFitSync.INSTANCE.getGetInstance().syncGoogleFit();
        }
    }
}
