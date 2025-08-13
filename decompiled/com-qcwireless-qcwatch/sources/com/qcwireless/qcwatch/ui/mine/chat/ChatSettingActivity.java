package com.qcwireless.qcwatch.ui.mine.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityChatSettingBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ChatSettingActivity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\bH\u0014J\b\u0010\f\u001a\u00020\bH\u0014J\b\u0010\r\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/ChatSettingActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityChatSettingBinding;", "type", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupViews", "showCleanDialog", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChatSettingActivity extends BaseActivity {
    private ActivityChatSettingBinding binding;
    private int type = 100;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatSettingBinding activityChatSettingBindingInflate = ActivityChatSettingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityChatSettingBindingInflate, "inflate(layoutInflater)");
        this.binding = activityChatSettingBindingInflate;
        if (activityChatSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatSettingBindingInflate = null;
        }
        ConstraintLayout root = activityChatSettingBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityChatSettingBinding activityChatSettingBinding = this.binding;
        ActivityChatSettingBinding activityChatSettingBinding2 = null;
        if (activityChatSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatSettingBinding = null;
        }
        activityChatSettingBinding.titleBar.tvTitle.setText(getString(R.string.ring_text_mine_124));
        ActivityChatSettingBinding activityChatSettingBinding3 = this.binding;
        if (activityChatSettingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatSettingBinding3 = null;
        }
        activityChatSettingBinding3.qcMessageDisturb.setChecked(UserConfig.INSTANCE.getInstance().getDisturbFlag());
        ActivityChatSettingBinding activityChatSettingBinding4 = this.binding;
        if (activityChatSettingBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatSettingBinding4 = null;
        }
        activityChatSettingBinding4.qcMessageDisturb.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatSettingActivity$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ChatSettingActivity.m907setupViews$lambda0(compoundButton, z);
            }
        });
        ActivityChatSettingBinding activityChatSettingBinding5 = this.binding;
        if (activityChatSettingBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityChatSettingBinding2 = activityChatSettingBinding5;
        }
        activityChatSettingBinding2.btnConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatSettingActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatSettingActivity.m908setupViews$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m907setupViews$lambda0(CompoundButton compoundButton, boolean z) {
        UserConfig.INSTANCE.getInstance().setDisturbFlag(z);
        UserConfig.INSTANCE.getInstance().save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m908setupViews$lambda1(ChatSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this$0)) {
            String string = this$0.getString(R.string.qc_text_223);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        this$0.showCleanDialog();
    }

    private final void showCleanDialog() {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_clean_chat);
        final BottomDialog bottomDialogCreate = builder.create();
        bottomDialogCreate.show();
        View contentView = bottomDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatSettingActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bottomDialogCreate.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatSettingActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatSettingActivity.m910showCleanDialog$lambda3(bottomDialogCreate, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCleanDialog$lambda-3, reason: not valid java name */
    public static final void m910showCleanDialog$lambda3(BottomDialog bottomDialog, ChatSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomDialog.dismiss();
        this$0.showLoadingDialog();
        this$0.type = 101;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ChatSettingActivity$showCleanDialog$2$1(this$0, null), 3, null);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        setResult(this.type);
    }
}
