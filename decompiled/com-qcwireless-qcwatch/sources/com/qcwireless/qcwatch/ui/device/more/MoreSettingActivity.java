package com.qcwireless.qcwatch.ui.device.more;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.RestoreKeyReq;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDeviceScreenDialog;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMoreSettingBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.OneKeySupport;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel;
import com.qcwireless.qcwatch.ui.device.more.ecard.ECardListActivity;
import com.squareup.moshi.JsonAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MoreSettingActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0014J\u0006\u0010\u0017\u001a\u00020\u0010J\u0006\u0010\u0018\u001a\u00020\u0010J\u0006\u0010\u0019\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/MoreSettingActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMoreSettingBinding;", "currNeedle", "", "moreSettingVm", "Lcom/qcwireless/qcwatch/ui/device/more/MoreSettingViewModel;", "getMoreSettingVm", "()Lcom/qcwireless/qcwatch/ui/device/more/MoreSettingViewModel;", "moreSettingVm$delegate", "Lkotlin/Lazy;", "screen", "totalNeedle", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "showFactoryDialog", "showRestartDialog", "showScreenDialog", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreSettingActivity extends BaseActivity {
    private ActivityMoreSettingBinding binding;
    private int currNeedle;

    /* renamed from: moreSettingVm$delegate, reason: from kotlin metadata */
    private final Lazy moreSettingVm;
    private int screen;
    private int totalNeedle;

    /* JADX WARN: Multi-variable type inference failed */
    public MoreSettingActivity() {
        final MoreSettingActivity moreSettingActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.moreSettingVm = LazyKt.lazy(new Function0<MoreSettingViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MoreSettingViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(moreSettingActivity, Reflection.getOrCreateKotlinClass(MoreSettingViewModel.class), qualifier, objArr);
            }
        });
        this.screen = 5;
        this.totalNeedle = 1;
    }

    private final MoreSettingViewModel getMoreSettingVm() {
        return (MoreSettingViewModel) this.moreSettingVm.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMoreSettingBinding activityMoreSettingBindingInflate = ActivityMoreSettingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMoreSettingBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMoreSettingBindingInflate;
        if (activityMoreSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMoreSettingBindingInflate = null;
        }
        LinearLayout root = activityMoreSettingBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityMoreSettingBinding activityMoreSettingBinding = null;
        try {
            if (UserConfig.INSTANCE.getInstance().getDeviceNotScreen() || StringsKt.startsWith$default(UserConfig.INSTANCE.getInstance().getHwVersion(), "NB39_V1", false, 2, (Object) null)) {
                ActivityMoreSettingBinding activityMoreSettingBinding2 = this.binding;
                if (activityMoreSettingBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMoreSettingBinding2 = null;
                }
                ViewKt.gone(activityMoreSettingBinding2.qcMoreScreen);
            }
            String oneKeySupport = UserConfig.INSTANCE.getInstance().getOneKeySupport();
            XLog.i(oneKeySupport);
            if (oneKeySupport.length() > 0) {
                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<OneKeySupport>() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$setupViews$$inlined$fromJson$1
                }.getType());
                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
                OneKeySupport oneKeySupport2 = (OneKeySupport) jsonAdapterAdapter.fromJson(oneKeySupport);
                if (oneKeySupport2 != null && oneKeySupport2.getSupportECard()) {
                    ActivityMoreSettingBinding activityMoreSettingBinding3 = this.binding;
                    if (activityMoreSettingBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMoreSettingBinding3 = null;
                    }
                    ViewKt.visible(activityMoreSettingBinding3.qcMoreEcard);
                    ActivityMoreSettingBinding activityMoreSettingBinding4 = this.binding;
                    if (activityMoreSettingBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMoreSettingBinding4 = null;
                    }
                    ViewKt.gone(activityMoreSettingBinding4.qcMoreScreen);
                    ActivityMoreSettingBinding activityMoreSettingBinding5 = this.binding;
                    if (activityMoreSettingBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMoreSettingBinding5 = null;
                    }
                    ViewKt.gone(activityMoreSettingBinding5.line1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getMoreSettingVm().getScreenSetting(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        ActivityMoreSettingBinding activityMoreSettingBinding6 = this.binding;
        if (activityMoreSettingBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMoreSettingBinding = activityMoreSettingBinding6;
        }
        activityMoreSettingBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_20));
        activityMoreSettingBinding.qcMoreScreen.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$setupViews$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showScreenDialog();
            }
        });
        activityMoreSettingBinding.qcMoreRestart.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$setupViews$2$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showRestartDialog();
            }
        });
        activityMoreSettingBinding.qcMoreFactory.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$setupViews$2$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showFactoryDialog();
            }
        });
        activityMoreSettingBinding.qcMoreEcard.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$setupViews$2$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                MoreSettingActivity moreSettingActivity = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(moreSettingActivity, (Class<?>) ECardListActivity.class);
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
                moreSettingActivity.startActivity(intent);
            }
        });
        getMoreSettingVm().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MoreSettingActivity.m465setupViews$lambda3(this.f$0, (MoreSettingViewModel.MoreUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m465setupViews$lambda3(MoreSettingActivity this$0, MoreSettingViewModel.MoreUI moreUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityMoreSettingBinding activityMoreSettingBinding = this$0.binding;
        if (activityMoreSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMoreSettingBinding = null;
        }
        QSettingItem qSettingItem = activityMoreSettingBinding.qcMoreScreen;
        StringBuilder sb = new StringBuilder();
        sb.append(moreUI.getScreenSeconds());
        sb.append('s');
        qSettingItem.setRightText(sb.toString());
        this$0.screen = moreUI.getScreenSeconds();
        this$0.totalNeedle = moreUI.getTotalNeedle();
        this$0.currNeedle = moreUI.getCurrNeedle();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    public final void showScreenDialog() {
        BottomDeviceScreenDialog.Builder builder = new BottomDeviceScreenDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_screen_light);
        BottomDeviceScreenDialog bottomDeviceScreenDialogCreate = builder.create();
        bottomDeviceScreenDialogCreate.setListener(new BottomDeviceScreenDialog.SelectSecondListener() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.BottomDeviceScreenDialog.SelectSecondListener
            public final void showSecond(String str) {
                MoreSettingActivity.m470showScreenDialog$lambda4(this.f$0, str);
            }
        });
        bottomDeviceScreenDialogCreate.initData(this);
        bottomDeviceScreenDialogCreate.setCurr(this.screen);
        bottomDeviceScreenDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showScreenDialog$lambda-4, reason: not valid java name */
    public static final void m470showScreenDialog$lambda4(MoreSettingActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.screen = Integer.parseInt(it);
        ActivityMoreSettingBinding activityMoreSettingBinding = this$0.binding;
        if (activityMoreSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMoreSettingBinding = null;
        }
        activityMoreSettingBinding.qcMoreScreen.setRightText(it + 's');
        this$0.getMoreSettingVm().saveScreenSeconds(this$0.screen, this$0.totalNeedle, this$0.currNeedle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showRestartDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        XLog.i(Boolean.valueOf(UserConfig.INSTANCE.getInstance().getDeviceNotScreen()));
        if (UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
            builder.setContentView(R.layout.layout_dialog_restart_1);
        } else {
            builder.setContentView(R.layout.layout_dialog_restart);
        }
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSettingActivity.m468showRestartDialog$lambda5(objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSettingActivity.m469showRestartDialog$lambda6(objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showRestartDialog$lambda-5, reason: not valid java name */
    public static final void m468showRestartDialog$lambda5(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showRestartDialog$lambda-6, reason: not valid java name */
    public static final void m469showRestartDialog$lambda6(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        CommandHandle.getInstance().executeReqCmdNoCallback(new SimpleKeyReq((byte) 8));
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showFactoryDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_factory);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSettingActivity.m466showFactoryDialog$lambda7(objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSettingActivity.m467showFactoryDialog$lambda8(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showFactoryDialog$lambda-7, reason: not valid java name */
    public static final void m466showFactoryDialog$lambda7(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showFactoryDialog$lambda-8, reason: not valid java name */
    public static final void m467showFactoryDialog$lambda8(MoreSettingActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        CommandHandle.getInstance().executeReqCmdNoCallback(new RestoreKeyReq((byte) -1));
        this$0.getMoreSettingVm().cleanContact();
        ((BottomDialog) bottomDialog.element).dismiss();
    }
}
