package com.qcwireless.qcwatch.ui.device.forward;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.CallForwardSettingReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.CallForwardRsp;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityCallForwardingBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallForwardingActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/forward/CallForwardingActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityCallForwardingBinding;", "forwardType", "", "phoneNumber", "", "doCheck", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CallForwardingActivity extends BaseActivity {
    private ActivityCallForwardingBinding binding;
    private int forwardType = -1;
    private String phoneNumber = "";

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1$lambda-0, reason: not valid java name */
    public static final void m463setupViews$lambda2$lambda1$lambda0(CallForwardRsp callForwardRsp) {
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCallForwardingBinding activityCallForwardingBindingInflate = ActivityCallForwardingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCallForwardingBindingInflate, "inflate(layoutInflater)");
        this.binding = activityCallForwardingBindingInflate;
        if (activityCallForwardingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallForwardingBindingInflate = null;
        }
        ConstraintLayout root = activityCallForwardingBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityCallForwardingBinding activityCallForwardingBinding = this.binding;
        if (activityCallForwardingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallForwardingBinding = null;
        }
        activityCallForwardingBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_598));
        activityCallForwardingBinding.qcForwardTo.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                CallForwardingActivity callForwardingActivity = this.this$0;
                bundle.putInt("forward", 2);
                if (callForwardingActivity.forwardType == 2) {
                    bundle.putString("number", UserConfig.INSTANCE.getInstance().getForwardPhoneNumber());
                } else {
                    bundle.putString("number", "");
                }
                CallForwardingActivity callForwardingActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(callForwardingActivity2, (Class<?>) CallPhoneNumberInputActivity.class);
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
                callForwardingActivity2.startActivity(intent);
            }
        });
        activityCallForwardingBinding.qcForwardNoOneHeard.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity$setupViews$1$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                CallForwardingActivity callForwardingActivity = this.this$0;
                bundle.putInt("forward", 1);
                if (callForwardingActivity.forwardType == 1) {
                    bundle.putString("number", UserConfig.INSTANCE.getInstance().getForwardPhoneNumber());
                } else {
                    bundle.putString("number", "");
                }
                CallForwardingActivity callForwardingActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(callForwardingActivity2, (Class<?>) CallPhoneNumberInputActivity.class);
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
                callForwardingActivity2.startActivity(intent);
            }
        });
        activityCallForwardingBinding.qcForward.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CallForwardingActivity.m462setupViews$lambda2$lambda1(this.f$0, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m462setupViews$lambda2$lambda1(CallForwardingActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Boolean.valueOf(z));
        ActivityCallForwardingBinding activityCallForwardingBinding = null;
        if (z) {
            ActivityCallForwardingBinding activityCallForwardingBinding2 = this$0.binding;
            if (activityCallForwardingBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCallForwardingBinding2 = null;
            }
            ViewKt.visible(activityCallForwardingBinding2.qcForwardTo);
            ActivityCallForwardingBinding activityCallForwardingBinding3 = this$0.binding;
            if (activityCallForwardingBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCallForwardingBinding = activityCallForwardingBinding3;
            }
            ViewKt.visible(activityCallForwardingBinding.qcForwardNoOneHeard);
            return;
        }
        ActivityCallForwardingBinding activityCallForwardingBinding4 = this$0.binding;
        if (activityCallForwardingBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCallForwardingBinding4 = null;
        }
        ViewKt.gone(activityCallForwardingBinding4.qcForwardTo);
        ActivityCallForwardingBinding activityCallForwardingBinding5 = this$0.binding;
        if (activityCallForwardingBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCallForwardingBinding = activityCallForwardingBinding5;
        }
        ViewKt.gone(activityCallForwardingBinding.qcForwardNoOneHeard);
        CommandHandle.getInstance().executeReqCmd(CallForwardSettingReq.getWriteInstance(false, 0, UserConfig.INSTANCE.getInstance().getForwardPhoneNumber()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                CallForwardingActivity.m463setupViews$lambda2$lambda1$lambda0((CallForwardRsp) baseRspCmd);
            }
        });
    }

    private final void doCheck() {
        try {
            CommandHandle.getInstance().executeReqCmd(CallForwardSettingReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity$$ExternalSyntheticLambda1
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    CallForwardingActivity.m461doCheck$lambda3(this.f$0, (CallForwardRsp) baseRspCmd);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doCheck$lambda-3, reason: not valid java name */
    public static final void m461doCheck$lambda3(CallForwardingActivity this$0, final CallForwardRsp callForwardRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (callForwardRsp.getAction() == 1) {
            XLog.i(callForwardRsp);
            ThreadExtKt.ktxRunOnUi(this$0, new Function1<CallForwardingActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity$doCheck$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CallForwardingActivity callForwardingActivity) {
                    invoke2(callForwardingActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CallForwardingActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    ActivityCallForwardingBinding activityCallForwardingBinding = null;
                    if (!callForwardRsp.isEnable()) {
                        ActivityCallForwardingBinding activityCallForwardingBinding2 = ktxRunOnUi.binding;
                        if (activityCallForwardingBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCallForwardingBinding2 = null;
                        }
                        activityCallForwardingBinding2.qcForwardNoOneHeard.setLeftSubText(ktxRunOnUi.getString(R.string.qc_text_21));
                        ActivityCallForwardingBinding activityCallForwardingBinding3 = ktxRunOnUi.binding;
                        if (activityCallForwardingBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCallForwardingBinding3 = null;
                        }
                        activityCallForwardingBinding3.qcForwardTo.setLeftSubText(ktxRunOnUi.getString(R.string.qc_text_21));
                    } else if (callForwardRsp.getCallForwardType() == 1) {
                        ActivityCallForwardingBinding activityCallForwardingBinding4 = ktxRunOnUi.binding;
                        if (activityCallForwardingBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCallForwardingBinding4 = null;
                        }
                        activityCallForwardingBinding4.qcForwardNoOneHeard.setLeftSubText(ktxRunOnUi.getString(R.string.qc_text_22));
                        ActivityCallForwardingBinding activityCallForwardingBinding5 = ktxRunOnUi.binding;
                        if (activityCallForwardingBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCallForwardingBinding5 = null;
                        }
                        activityCallForwardingBinding5.qcForwardTo.setLeftSubText(ktxRunOnUi.getString(R.string.qc_text_21));
                    } else if (callForwardRsp.getCallForwardType() == 2) {
                        ActivityCallForwardingBinding activityCallForwardingBinding6 = ktxRunOnUi.binding;
                        if (activityCallForwardingBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCallForwardingBinding6 = null;
                        }
                        activityCallForwardingBinding6.qcForwardNoOneHeard.setLeftSubText(ktxRunOnUi.getString(R.string.qc_text_21));
                        ActivityCallForwardingBinding activityCallForwardingBinding7 = ktxRunOnUi.binding;
                        if (activityCallForwardingBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCallForwardingBinding7 = null;
                        }
                        activityCallForwardingBinding7.qcForwardTo.setLeftSubText(ktxRunOnUi.getString(R.string.qc_text_22));
                    }
                    ActivityCallForwardingBinding activityCallForwardingBinding8 = ktxRunOnUi.binding;
                    if (activityCallForwardingBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityCallForwardingBinding = activityCallForwardingBinding8;
                    }
                    activityCallForwardingBinding.qcForward.setChecked(callForwardRsp.isEnable());
                    ktxRunOnUi.forwardType = callForwardRsp.getCallForwardType();
                    String phoneNumber = callForwardRsp.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "it.phoneNumber");
                    ktxRunOnUi.phoneNumber = phoneNumber;
                    UserConfig companion = UserConfig.INSTANCE.getInstance();
                    String phoneNumber2 = callForwardRsp.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber2, "it.phoneNumber");
                    companion.setForwardPhoneNumber(phoneNumber2);
                    UserConfig.INSTANCE.getInstance().save();
                }
            });
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        doCheck();
    }
}
