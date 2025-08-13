package com.qcwireless.qcwatch.ui.device.quicksms;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.bean.SmsQuickBean;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityQuickSmsInputBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: QuickSmsInputActivity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/quicksms/QuickSmsInputActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityQuickSmsInputBinding;", "list", "", "Lcom/oudmon/ble/base/communication/bigData/bean/SmsQuickBean;", "getWholeText", "", "text", "byteCount", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QuickSmsInputActivity extends BaseActivity {
    private ActivityQuickSmsInputBinding binding;
    private List<SmsQuickBean> list = new ArrayList();

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQuickSmsInputBinding activityQuickSmsInputBindingInflate = ActivityQuickSmsInputBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityQuickSmsInputBindingInflate, "inflate(layoutInflater)");
        this.binding = activityQuickSmsInputBindingInflate;
        if (activityQuickSmsInputBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsInputBindingInflate = null;
        }
        ConstraintLayout root = activityQuickSmsInputBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws IOException {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        String string = extras.getString(Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
        Bundle extras2 = getIntent().getExtras();
        Intrinsics.checkNotNull(extras2);
        final int i = extras2.getInt(Constant.MODIFY_ACTIVITY_INTENT_INDEX);
        final ActivityQuickSmsInputBinding activityQuickSmsInputBinding = this.binding;
        if (activityQuickSmsInputBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsInputBinding = null;
        }
        activityQuickSmsInputBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_611));
        activityQuickSmsInputBinding.userSmsQuick.setText(string);
        String smsQuickText = UserConfig.INSTANCE.getInstance().getSmsQuickText();
        XLog.i(smsQuickText);
        if (smsQuickText.length() > 0) {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<SmsQuickBean>>() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsInputActivity$setupViews$lambda-2$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            Object objFromJson = jsonAdapterAdapter.fromJson(smsQuickText);
            Intrinsics.checkNotNull(objFromJson);
            this.list = (List) objFromJson;
        }
        ViewKt.visible(activityQuickSmsInputBinding.titleBar.tvRightText);
        activityQuickSmsInputBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        EditText userSmsQuick = activityQuickSmsInputBinding.userSmsQuick;
        Intrinsics.checkNotNullExpressionValue(userSmsQuick, "userSmsQuick");
        userSmsQuick.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsInputActivity$setupViews$lambda-2$$inlined$doAfterTextChanged$1
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
                String string2 = s.toString();
                byte[] bytes = string2.getBytes(Charsets.UTF_16BE);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                if (bytes.length > 32) {
                    String wholeText = this.this$0.getWholeText(string2, 32);
                    activityQuickSmsInputBinding.userSmsQuick.setText(wholeText);
                    activityQuickSmsInputBinding.userSmsQuick.setSelection(wholeText.length());
                }
            }
        });
        activityQuickSmsInputBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsInputActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws InterruptedException {
                QuickSmsInputActivity.m562setupViews$lambda2$lambda1(this.f$0, activityQuickSmsInputBinding, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m562setupViews$lambda2$lambda1(QuickSmsInputActivity this$0, ActivityQuickSmsInputBinding this_run, int i, View view) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        Editable text = this_run.userSmsQuick.getText();
        Intrinsics.checkNotNullExpressionValue(text, "userSmsQuick.text");
        this$0.list.set(i, new SmsQuickBean(i, StringsKt.trim(text).toString()));
        LargeDataHandler.getInstance().writeSmsQuick(this$0.list);
        UserConfig.INSTANCE.getInstance().setSmsQuickText(MoshiUtilsKt.toJson(this$0.list));
        UserConfig.INSTANCE.getInstance().save();
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWholeText(String text, int byteCount) {
        try {
            byte[] bytes = text.getBytes(Charsets.UTF_16BE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            if (bytes.length > byteCount) {
                char[] charArray = text.toCharArray();
                Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
                int length = charArray.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= length) {
                        i = 0;
                        break;
                    }
                    char c = charArray[i];
                    if (c < 0 || c < 128) {
                    }
                    i2 += 2;
                    if (i2 > byteCount) {
                        break;
                    }
                    i++;
                }
                return new String(charArray, 0, i);
            }
        } catch (UnsupportedEncodingException unused) {
        }
        return text;
    }
}
