package com.qcwireless.qcwatch.ui.device.bp;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.qcwireless.qcwatch.databinding.ActivityBloodPressureBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: BloodPressureSettingActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/bp/BloodPressureSettingActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodPressureBinding;", "bpViewModel", "Lcom/qcwireless/qcwatch/ui/device/bp/BloodPressureViewModel;", "getBpViewModel", "()Lcom/qcwireless/qcwatch/ui/device/bp/BloodPressureViewModel;", "bpViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureSettingActivity extends BaseActivity {
    private ActivityBloodPressureBinding binding;

    /* renamed from: bpViewModel$delegate, reason: from kotlin metadata */
    private final Lazy bpViewModel;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m389setupViews$lambda1$lambda0(BloodPressureViewModel.BpUI bpUI) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BloodPressureSettingActivity() {
        final BloodPressureSettingActivity bloodPressureSettingActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.bpViewModel = LazyKt.lazy(new Function0<BloodPressureViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.bp.BloodPressureSettingActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodPressureViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodPressureSettingActivity, Reflection.getOrCreateKotlinClass(BloodPressureViewModel.class), qualifier, objArr);
            }
        });
    }

    private final BloodPressureViewModel getBpViewModel() {
        return (BloodPressureViewModel) this.bpViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodPressureBinding activityBloodPressureBindingInflate = ActivityBloodPressureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodPressureBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodPressureBindingInflate;
        if (activityBloodPressureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBindingInflate = null;
        }
        ConstraintLayout root = activityBloodPressureBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        BloodPressureViewModel bpViewModel = getBpViewModel();
        bpViewModel.getBpSetting("123");
        bpViewModel.saveBpSetting("123", true, 1, 23);
        bpViewModel.getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.bp.BloodPressureSettingActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodPressureSettingActivity.m389setupViews$lambda1$lambda0((BloodPressureViewModel.BpUI) obj);
            }
        });
    }
}
