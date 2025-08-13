package com.qcwireless.qcwatch.ui.device.heart;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.databinding.ActivityHeartDetectionBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: HeartDetectionActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/heart/HeartDetectionActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityHeartDetectionBinding;", "heartDetectionViewModel", "Lcom/qcwireless/qcwatch/ui/device/heart/HeartDetectionViewModel;", "getHeartDetectionViewModel", "()Lcom/qcwireless/qcwatch/ui/device/heart/HeartDetectionViewModel;", "heartDetectionViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartDetectionActivity extends BaseActivity {
    private ActivityHeartDetectionBinding binding;

    /* renamed from: heartDetectionViewModel$delegate, reason: from kotlin metadata */
    private final Lazy heartDetectionViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public HeartDetectionActivity() {
        final HeartDetectionActivity heartDetectionActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.heartDetectionViewModel = LazyKt.lazy(new Function0<HeartDetectionViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.heart.HeartDetectionActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.heart.HeartDetectionViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HeartDetectionViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(heartDetectionActivity, Reflection.getOrCreateKotlinClass(HeartDetectionViewModel.class), qualifier, objArr);
            }
        });
    }

    private final HeartDetectionViewModel getHeartDetectionViewModel() {
        return (HeartDetectionViewModel) this.heartDetectionViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHeartDetectionBinding activityHeartDetectionBindingInflate = ActivityHeartDetectionBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityHeartDetectionBindingInflate, "inflate(layoutInflater)");
        this.binding = activityHeartDetectionBindingInflate;
        if (activityHeartDetectionBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartDetectionBindingInflate = null;
        }
        ConstraintLayout root = activityHeartDetectionBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
    }
}
