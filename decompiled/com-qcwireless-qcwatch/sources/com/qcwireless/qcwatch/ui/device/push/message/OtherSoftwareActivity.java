package com.qcwireless.qcwatch.ui.device.push.message;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.databinding.ActivityOtherSoftwareBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.push.adapter.PushSoftwareAdapter;
import com.qcwireless.qcwatch.ui.device.push.bean.SoftwarePush;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: OtherSoftwareActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/OtherSoftwareActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/push/adapter/PushSoftwareAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityOtherSoftwareBinding;", "otherViewModel", "Lcom/qcwireless/qcwatch/ui/device/push/message/OtherSoftwareViewModel;", "getOtherViewModel", "()Lcom/qcwireless/qcwatch/ui/device/push/message/OtherSoftwareViewModel;", "otherViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtherSoftwareActivity extends BaseActivity {
    private PushSoftwareAdapter adapter;
    private ActivityOtherSoftwareBinding binding;

    /* renamed from: otherViewModel$delegate, reason: from kotlin metadata */
    private final Lazy otherViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public OtherSoftwareActivity() {
        final OtherSoftwareActivity otherSoftwareActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.otherViewModel = LazyKt.lazy(new Function0<OtherSoftwareViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final OtherSoftwareViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(otherSoftwareActivity, Reflection.getOrCreateKotlinClass(OtherSoftwareViewModel.class), qualifier, objArr);
            }
        });
    }

    private final OtherSoftwareViewModel getOtherViewModel() {
        return (OtherSoftwareViewModel) this.otherViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOtherSoftwareBinding activityOtherSoftwareBindingInflate = ActivityOtherSoftwareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityOtherSoftwareBindingInflate, "inflate(layoutInflater)");
        this.binding = activityOtherSoftwareBindingInflate;
        if (activityOtherSoftwareBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtherSoftwareBindingInflate = null;
        }
        ConstraintLayout root = activityOtherSoftwareBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialog();
        OtherSoftwareActivity otherSoftwareActivity = this;
        this.adapter = new PushSoftwareAdapter(otherSoftwareActivity, getOtherViewModel().getSoftList());
        ActivityOtherSoftwareBinding activityOtherSoftwareBinding = this.binding;
        PushSoftwareAdapter pushSoftwareAdapter = null;
        if (activityOtherSoftwareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtherSoftwareBinding = null;
        }
        ActivityOtherSoftwareBinding activityOtherSoftwareBinding2 = this.binding;
        if (activityOtherSoftwareBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtherSoftwareBinding2 = null;
        }
        activityOtherSoftwareBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_243));
        activityOtherSoftwareBinding.otherPushRcv.setLayoutManager(new LinearLayoutManager(otherSoftwareActivity));
        RecyclerView recyclerView = activityOtherSoftwareBinding.otherPushRcv;
        PushSoftwareAdapter pushSoftwareAdapter2 = this.adapter;
        if (pushSoftwareAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter2 = null;
        }
        recyclerView.setAdapter(pushSoftwareAdapter2);
        getOtherViewModel().queryOpenSoftWare(this);
        getOtherViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                OtherSoftwareActivity.m557setupViews$lambda2(this.f$0, (MessagePushViewModel.MessagePushUI) obj);
            }
        });
        PushSoftwareAdapter pushSoftwareAdapter3 = this.adapter;
        if (pushSoftwareAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            pushSoftwareAdapter = pushSoftwareAdapter3;
        }
        pushSoftwareAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                OtherSoftwareActivity.m558setupViews$lambda3(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m557setupViews$lambda2(OtherSoftwareActivity this$0, MessagePushViewModel.MessagePushUI messagePushUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
        List<SoftwarePush> data = messagePushUI.getData();
        if (data != null) {
            this$0.getOtherViewModel().getSoftList().clear();
            this$0.getOtherViewModel().getSoftList().addAll(data);
        }
        PushSoftwareAdapter pushSoftwareAdapter = this$0.adapter;
        if (pushSoftwareAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter = null;
        }
        pushSoftwareAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m558setupViews$lambda3(OtherSoftwareActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        SoftwarePush softwarePush = this$0.getOtherViewModel().getSoftList().get(i);
        softwarePush.setSwitch(!softwarePush.getSwitch());
        this$0.getOtherViewModel().saveBean(softwarePush);
        PushSoftwareAdapter pushSoftwareAdapter = this$0.adapter;
        if (pushSoftwareAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            pushSoftwareAdapter = null;
        }
        pushSoftwareAdapter.notifyDataSetChanged();
    }
}
