package com.qcwireless.qcwatch.ui.device.forward;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.CallForwardSettingReq;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityCallPhoneNumberInputBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: CallPhoneNumberInputActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\u0006H\u0014J\b\u0010\u000b\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/forward/CallPhoneNumberInputActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityCallPhoneNumberInputBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CallPhoneNumberInputActivity extends BaseActivity {
    private ActivityCallPhoneNumberInputBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBindingInflate = ActivityCallPhoneNumberInputBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCallPhoneNumberInputBindingInflate, "inflate(layoutInflater)");
        this.binding = activityCallPhoneNumberInputBindingInflate;
        if (activityCallPhoneNumberInputBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallPhoneNumberInputBindingInflate = null;
        }
        ConstraintLayout root = activityCallPhoneNumberInputBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        final int i = extras.getInt("forward");
        Bundle extras2 = getIntent().getExtras();
        Intrinsics.checkNotNull(extras2);
        String string = extras2.getString("number");
        ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding = this.binding;
        ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding2 = null;
        if (activityCallPhoneNumberInputBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallPhoneNumberInputBinding = null;
        }
        activityCallPhoneNumberInputBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_604));
        ViewKt.visible(activityCallPhoneNumberInputBinding.titleBar.tvRightText);
        activityCallPhoneNumberInputBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        if (string != null) {
            String str = string;
            if (str.length() > 0) {
                ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding3 = this.binding;
                if (activityCallPhoneNumberInputBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCallPhoneNumberInputBinding3 = null;
                }
                activityCallPhoneNumberInputBinding3.userPhone.setText(str);
            }
        }
        ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding4 = this.binding;
        if (activityCallPhoneNumberInputBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCallPhoneNumberInputBinding2 = activityCallPhoneNumberInputBinding4;
        }
        EditText editText = activityCallPhoneNumberInputBinding2.userPhone;
        Intrinsics.checkNotNullExpressionValue(editText, "binding.userPhone");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallPhoneNumberInputActivity$setupViews$lambda-2$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Editable editable = s;
                if (editable == null || editable.length() == 0) {
                    return;
                }
                byte[] bytes = s.toString().getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                if (bytes.length > 11) {
                    String string2 = s.toString();
                    ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding5 = this.this$0.binding;
                    ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding6 = null;
                    if (activityCallPhoneNumberInputBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityCallPhoneNumberInputBinding5 = null;
                    }
                    EditText editText2 = activityCallPhoneNumberInputBinding5.userPhone;
                    String strSubstring = string2.substring(0, 11);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    editText2.setText(strSubstring);
                    ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding7 = this.this$0.binding;
                    if (activityCallPhoneNumberInputBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityCallPhoneNumberInputBinding6 = activityCallPhoneNumberInputBinding7;
                    }
                    activityCallPhoneNumberInputBinding6.userPhone.setSelection(11);
                }
            }
        });
        activityCallPhoneNumberInputBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallPhoneNumberInputActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CallPhoneNumberInputActivity.m464setupViews$lambda2$lambda1(this.f$0, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m464setupViews$lambda2$lambda1(CallPhoneNumberInputActivity this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding = this$0.binding;
        if (activityCallPhoneNumberInputBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallPhoneNumberInputBinding = null;
        }
        Editable text = activityCallPhoneNumberInputBinding.userPhone.getText();
        Intrinsics.checkNotNullExpressionValue(text, "binding.userPhone.text");
        CharSequence charSequenceTrim = StringsKt.trim(text);
        if (charSequenceTrim.length() == 0) {
            String string2 = this$0.getString(R.string.qc_text_621);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_621)");
            GlobalKt.showToast$default(string2, 0, 1, null);
        } else {
            CommandHandle.getInstance().executeReqCmdNoCallback(CallForwardSettingReq.getWriteInstance(true, i, charSequenceTrim.toString()));
            this$0.finish();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ActivityCallPhoneNumberInputBinding activityCallPhoneNumberInputBinding = this.binding;
        if (activityCallPhoneNumberInputBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallPhoneNumberInputBinding = null;
        }
        Editable text = activityCallPhoneNumberInputBinding.userPhone.getText();
        Intrinsics.checkNotNullExpressionValue(text, "binding.userPhone.text");
        CharSequence charSequenceTrim = StringsKt.trim(text);
        if (charSequenceTrim.length() > 0) {
            UserConfig.INSTANCE.getInstance().setForwardPhoneNumber(charSequenceTrim.toString());
            UserConfig.INSTANCE.getInstance().save();
        }
    }
}
