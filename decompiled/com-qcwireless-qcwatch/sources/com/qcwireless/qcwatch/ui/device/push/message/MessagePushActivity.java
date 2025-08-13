package com.qcwireless.qcwatch.ui.device.push.message;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.MessagePermissionEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMessagePushBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;
import com.qcwireless.qcwatch.ui.device.push.adapter.PushSoftwareAdapter;
import com.qcwireless.qcwatch.ui.device.push.bean.SoftwarePush;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;
import skin.support.content.res.SkinCompatResources;

/* compiled from: MessagePushActivity.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J2\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006 "}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/push/adapter/PushSoftwareAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMessagePushBinding;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "pushViewModel", "Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushViewModel;", "getPushViewModel", "()Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushViewModel;", "pushViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setRichText", "textView", "Landroid/widget/TextView;", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", TypedValues.AttributesType.S_TARGET, TypedValues.Custom.S_COLOR, "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "setupViews", "CallPermissionCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MessagePushActivity extends BaseActivity {
    private PushSoftwareAdapter adapter;
    private ActivityMessagePushBinding binding;
    private DeviceSetting deviceSetting;

    /* renamed from: pushViewModel$delegate, reason: from kotlin metadata */
    private final Lazy pushViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MessagePushActivity() {
        final MessagePushActivity messagePushActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.pushViewModel = LazyKt.lazy(new Function0<MessagePushViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MessagePushViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(messagePushActivity, Reflection.getOrCreateKotlinClass(MessagePushViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MessagePushViewModel getPushViewModel() {
        return (MessagePushViewModel) this.pushViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMessagePushBinding activityMessagePushBindingInflate = ActivityMessagePushBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMessagePushBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMessagePushBindingInflate;
        if (activityMessagePushBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMessagePushBindingInflate = null;
        }
        ConstraintLayout root = activityMessagePushBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialogTimeout(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        MessagePushActivity messagePushActivity = this;
        this.adapter = new PushSoftwareAdapter(messagePushActivity, getPushViewModel().getSoftList());
        final ActivityMessagePushBinding activityMessagePushBinding = this.binding;
        ActivityMessagePushBinding activityMessagePushBinding2 = null;
        if (activityMessagePushBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMessagePushBinding = null;
        }
        ActivityMessagePushBinding activityMessagePushBinding3 = this.binding;
        if (activityMessagePushBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMessagePushBinding3 = null;
        }
        activityMessagePushBinding3.titleBar.tvTitle.setText(getString(R.string.qc_text_48));
        activityMessagePushBinding.pushRcv.setLayoutManager(new LinearLayoutManager(messagePushActivity));
        RecyclerView recyclerView = activityMessagePushBinding.pushRcv;
        PushSoftwareAdapter pushSoftwareAdapter = this.adapter;
        if (pushSoftwareAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter = null;
        }
        recyclerView.setAdapter(pushSoftwareAdapter);
        View footerView = LayoutInflater.from(messagePushActivity).inflate(R.layout.recycleview_item_msg_push_footer, (ViewGroup) null);
        QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) footerView.findViewById(R.id.qc_other_switch);
        qSettingItemWithClickSystem.setChecked(UserConfig.INSTANCE.getInstance().getOtherSoftwarePush());
        qSettingItemWithClickSystem.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MessagePushActivity.m549setupViews$lambda4$lambda0(compoundButton, z);
            }
        });
        PushSoftwareAdapter pushSoftwareAdapter2 = this.adapter;
        if (pushSoftwareAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter2 = null;
        }
        Intrinsics.checkNotNullExpressionValue(footerView, "footerView");
        BaseQuickAdapter.addFooterView$default(pushSoftwareAdapter2, footerView, 0, 0, 6, null);
        activityMessagePushBinding.qcMessageSwitch.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MessagePushActivity.m550setupViews$lambda4$lambda1(activityMessagePushBinding, this, compoundButton, z);
            }
        });
        MessagePushActivity messagePushActivity2 = this;
        getPushViewModel().getDeviceSetting().observe(messagePushActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MessagePushActivity.m551setupViews$lambda4$lambda2(this.f$0, (DeviceSetting) obj);
            }
        });
        activityMessagePushBinding.qcSmsSwitch.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) throws NoSuchMethodException, SecurityException {
                MessagePushActivity.m552setupViews$lambda4$lambda3(this.f$0, compoundButton, z);
            }
        });
        getPushViewModel().getUiState().observe(messagePushActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MessagePushActivity.m553setupViews$lambda5(this.f$0, (MessagePushViewModel.MessagePushUI) obj);
            }
        });
        PushSoftwareAdapter pushSoftwareAdapter3 = this.adapter;
        if (pushSoftwareAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter3 = null;
        }
        pushSoftwareAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessagePushActivity.m554setupViews$lambda6(this.f$0, baseQuickAdapter, view, i);
            }
        });
        ActivityMessagePushBinding activityMessagePushBinding4 = this.binding;
        if (activityMessagePushBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMessagePushBinding2 = activityMessagePushBinding4;
        }
        TextView textView = activityMessagePushBinding2.tvInfo1;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvInfo1");
        setRichText(textView, getString(R.string.qc_text_336), getString(R.string.qc_text_337), SkinCompatResources.getColor(messagePushActivity, R.color.color_FF6A00), new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MessagePushActivity.m555setupViews$lambda7(this.f$0, view);
            }
        });
        getPushViewModel().initData();
        getPushViewModel().initDeviceSetting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-0, reason: not valid java name */
    public static final void m549setupViews$lambda4$lambda0(CompoundButton compoundButton, boolean z) {
        UserConfig.INSTANCE.getInstance().setOtherSoftwarePush(z);
        UserConfig.INSTANCE.getInstance().save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-1, reason: not valid java name */
    public static final void m550setupViews$lambda4$lambda1(ActivityMessagePushBinding this_run, MessagePushActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            ViewKt.visible(this_run.messagePushGroup);
        } else {
            ViewKt.gone(this_run.messagePushGroup);
        }
        UserConfig.INSTANCE.getInstance().setMessagePushEnable(z);
        UserConfig.INSTANCE.getInstance().save();
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setMessagePushSwitch(z);
        MessagePushViewModel pushViewModel = this$0.getPushViewModel();
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        pushViewModel.saveDeviceSetting(new DeviceSettingEntity(deviceAddressNoClear, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting2)));
        EventBus.getDefault().post(new MessagePermissionEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-2, reason: not valid java name */
    public static final void m551setupViews$lambda4$lambda2(MessagePushActivity this$0, DeviceSetting deviceSetting) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (deviceSetting != null) {
            this$0.deviceSetting = deviceSetting;
            XLog.i(Boolean.valueOf(UserConfig.INSTANCE.getInstance().getMessagePushEnable()));
            ActivityMessagePushBinding activityMessagePushBinding = this$0.binding;
            DeviceSetting deviceSetting2 = null;
            ActivityMessagePushBinding activityMessagePushBinding2 = null;
            if (activityMessagePushBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMessagePushBinding = null;
            }
            activityMessagePushBinding.qcMessageSwitch.setChecked(UserConfig.INSTANCE.getInstance().getMessagePushEnable());
            if (UserConfig.INSTANCE.getInstance().getMessagePushEnable()) {
                ActivityMessagePushBinding activityMessagePushBinding3 = this$0.binding;
                if (activityMessagePushBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMessagePushBinding3 = null;
                }
                ViewKt.visible(activityMessagePushBinding3.messagePushGroup);
            } else {
                ActivityMessagePushBinding activityMessagePushBinding4 = this$0.binding;
                if (activityMessagePushBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMessagePushBinding4 = null;
                }
                ViewKt.gone(activityMessagePushBinding4.messagePushGroup);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(Permission.READ_SMS);
            arrayList.add(Permission.RECEIVE_SMS);
            Iterator it = arrayList.iterator();
            if (it.hasNext()) {
                if (PermissionChecker.checkSelfPermission(this$0, (String) it.next()) != 0) {
                    ActivityMessagePushBinding activityMessagePushBinding5 = this$0.binding;
                    if (activityMessagePushBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMessagePushBinding2 = activityMessagePushBinding5;
                    }
                    activityMessagePushBinding2.qcSmsSwitch.setChecked(false);
                    UserConfig.INSTANCE.getInstance().setSmsPushEnable(false);
                    UserConfig.INSTANCE.getInstance().save();
                    return;
                }
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                DeviceSetting deviceSetting3 = this$0.deviceSetting;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting3 = null;
                }
                companion.setSmsPushEnable(deviceSetting3.getSmsPushSwitch());
                ActivityMessagePushBinding activityMessagePushBinding6 = this$0.binding;
                if (activityMessagePushBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMessagePushBinding6 = null;
                }
                QSettingItemWithClickSystem qSettingItemWithClickSystem = activityMessagePushBinding6.qcSmsSwitch;
                DeviceSetting deviceSetting4 = this$0.deviceSetting;
                if (deviceSetting4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                } else {
                    deviceSetting2 = deviceSetting4;
                }
                qSettingItemWithClickSystem.setChecked(deviceSetting2.getSmsPushSwitch());
                UserConfig.INSTANCE.getInstance().save();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-3, reason: not valid java name */
    public static final void m552setupViews$lambda4$lambda3(MessagePushActivity this$0, CompoundButton compoundButton, boolean z) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            Activity activity = this$0.getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            PermissionUtilKt.requestSMSPermission((FragmentActivity) activity, this$0.new CallPermissionCallback());
            return;
        }
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setSmsPushSwitch(z);
        MessagePushViewModel pushViewModel = this$0.getPushViewModel();
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        pushViewModel.saveDeviceSetting(new DeviceSettingEntity(deviceAddressNoClear, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting2)));
        UserConfig.INSTANCE.getInstance().setSmsPushEnable(z);
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new MessagePermissionEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m553setupViews$lambda5(MessagePushActivity this$0, MessagePushViewModel.MessagePushUI messagePushUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!messagePushUI.getInitFlag()) {
            this$0.getPushViewModel().queryOpenSoftWare(this$0);
            return;
        }
        if (messagePushUI.getData() != null) {
            this$0.getPushViewModel().getSoftList().clear();
            List<SoftwarePush> softList = this$0.getPushViewModel().getSoftList();
            List<SoftwarePush> data = messagePushUI.getData();
            Intrinsics.checkNotNull(data);
            softList.addAll(data);
            XLog.i("消息推送列表长度:" + this$0.getPushViewModel().getSoftList().size());
            PushSoftwareAdapter pushSoftwareAdapter = this$0.adapter;
            if (pushSoftwareAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                pushSoftwareAdapter = null;
            }
            pushSoftwareAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m554setupViews$lambda6(MessagePushActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        SoftwarePush softwarePush = this$0.getPushViewModel().getSoftList().get(i);
        softwarePush.setSwitch(!softwarePush.getSwitch());
        this$0.getPushViewModel().saveBean(softwarePush);
        PushSoftwareAdapter pushSoftwareAdapter = this$0.adapter;
        if (pushSoftwareAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter = null;
        }
        pushSoftwareAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m555setupViews$lambda7(MessagePushActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ThreadUtils.postDelay(new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity.onResume.1
            @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
            protected void task() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Permission.READ_SMS);
                arrayList.add(Permission.RECEIVE_SMS);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    PermissionChecker.checkSelfPermission(MessagePushActivity.this, (String) it.next());
                }
            }
        }, 100L);
    }

    public final void setRichText(TextView textView, String content, String target, final int color, final View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(listener, "listener");
        String str = content;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        Matcher matcher = Pattern.compile(target).matcher(str);
        while (matcher.find()) {
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity.setRichText.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    listener.onClick(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint ds) {
                    Intrinsics.checkNotNullParameter(ds, "ds");
                    super.updateDrawState(ds);
                    ds.setUnderlineText(true);
                    ds.setColor(color);
                }
            }, matcher.start(), matcher.end(), 33);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* compiled from: MessagePushActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushActivity$CallPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CallPermissionCallback implements OnPermissionCallback {
        public CallPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            XLog.i(Boolean.valueOf(all));
            XLog.i(permissions);
            DeviceSetting deviceSetting = null;
            if (all) {
                ActivityMessagePushBinding activityMessagePushBinding = MessagePushActivity.this.binding;
                if (activityMessagePushBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMessagePushBinding = null;
                }
                activityMessagePushBinding.qcSmsSwitch.setChecked(true);
                DeviceSetting deviceSetting2 = MessagePushActivity.this.deviceSetting;
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setSmsPushSwitch(true);
                UserConfig.INSTANCE.getInstance().setSmsPushEnable(true);
                UserConfig.INSTANCE.getInstance().save();
                final MessagePushActivity messagePushActivity = MessagePushActivity.this;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<CallPermissionCallback, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$CallPermissionCallback$onGranted$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MessagePushActivity.CallPermissionCallback callPermissionCallback) {
                        invoke2(callPermissionCallback);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MessagePushActivity.CallPermissionCallback ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(Permission.READ_SMS);
                        arrayList.add(Permission.RECEIVE_SMS);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (PermissionChecker.checkSelfPermission(QCApplication.INSTANCE.getCONTEXT(), (String) it.next()) != 0) {
                                final MessagePushActivity messagePushActivity2 = messagePushActivity;
                                ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<MessagePushActivity.CallPermissionCallback, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity$CallPermissionCallback$onGranted$1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(MessagePushActivity.CallPermissionCallback callPermissionCallback) {
                                        invoke2(callPermissionCallback);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(MessagePushActivity.CallPermissionCallback ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        ActivityMessagePushBinding activityMessagePushBinding2 = messagePushActivity2.binding;
                                        if (activityMessagePushBinding2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityMessagePushBinding2 = null;
                                        }
                                        activityMessagePushBinding2.qcSmsSwitch.setChecked(false);
                                        DeviceSetting deviceSetting3 = messagePushActivity2.deviceSetting;
                                        if (deviceSetting3 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                                            deviceSetting3 = null;
                                        }
                                        deviceSetting3.setSmsPushSwitch(false);
                                        UserConfig.INSTANCE.getInstance().setSmsPushEnable(false);
                                        UserConfig.INSTANCE.getInstance().save();
                                        String string = messagePushActivity2.getString(R.string.qc_text_77);
                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                                        GlobalKt.showToast$default(string, 0, 1, null);
                                    }
                                });
                                XXPermissions.startPermissionActivity((Activity) messagePushActivity, (List<String>) arrayList);
                                return;
                            }
                        }
                    }
                });
            } else {
                ActivityMessagePushBinding activityMessagePushBinding2 = MessagePushActivity.this.binding;
                if (activityMessagePushBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMessagePushBinding2 = null;
                }
                activityMessagePushBinding2.qcSmsSwitch.setChecked(false);
                DeviceSetting deviceSetting3 = MessagePushActivity.this.deviceSetting;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting3 = null;
                }
                deviceSetting3.setSmsPushSwitch(false);
                UserConfig.INSTANCE.getInstance().setSmsPushEnable(false);
                UserConfig.INSTANCE.getInstance().save();
                String string = MessagePushActivity.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
            MessagePushViewModel pushViewModel = MessagePushActivity.this.getPushViewModel();
            String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            DeviceSetting deviceSetting4 = MessagePushActivity.this.deviceSetting;
            if (deviceSetting4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            } else {
                deviceSetting = deviceSetting4;
            }
            pushViewModel.saveDeviceSetting(new DeviceSettingEntity(deviceAddressNoClear, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting)));
            EventBus.getDefault().post(new MessagePermissionEvent());
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            UserConfig.INSTANCE.getInstance().setSmsPushEnable(false);
            UserConfig.INSTANCE.getInstance().save();
            DeviceSetting deviceSetting = MessagePushActivity.this.deviceSetting;
            ActivityMessagePushBinding activityMessagePushBinding = null;
            if (deviceSetting == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting = null;
            }
            deviceSetting.setSmsPushSwitch(false);
            MessagePushViewModel pushViewModel = MessagePushActivity.this.getPushViewModel();
            String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
            DeviceSetting deviceSetting2 = MessagePushActivity.this.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            pushViewModel.saveDeviceSetting(new DeviceSettingEntity(deviceAddressNoClear, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting2)));
            String string = MessagePushActivity.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
            ActivityMessagePushBinding activityMessagePushBinding2 = MessagePushActivity.this.binding;
            if (activityMessagePushBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMessagePushBinding = activityMessagePushBinding2;
            }
            activityMessagePushBinding.qcSmsSwitch.setChecked(false);
            if (never) {
                XXPermissions.startPermissionActivity((Activity) MessagePushActivity.this, permissions);
            }
            EventBus.getDefault().post(new MessagePermissionEvent());
        }
    }
}
