package com.qcwireless.qcwatch.ui.home.heart;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.databinding.ActivityExerciseHeartGuideBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ExerciseHeartGuideActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/ExerciseHeartGuideActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityExerciseHeartGuideBinding;", "maxHeart", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExerciseHeartGuideActivity extends BaseActivity {
    private ActivityExerciseHeartGuideBinding binding;
    private int maxHeart;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ExerciseHeartGuideActivity() {
        final ExerciseHeartGuideActivity exerciseHeartGuideActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HeartActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.heart.ExerciseHeartGuideActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HeartActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(exerciseHeartGuideActivity, Reflection.getOrCreateKotlinClass(HeartActivityViewModel.class), qualifier, objArr);
            }
        });
        this.maxHeart = 200;
    }

    private final HeartActivityViewModel getViewModel() {
        return (HeartActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityExerciseHeartGuideBinding activityExerciseHeartGuideBindingInflate = ActivityExerciseHeartGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityExerciseHeartGuideBindingInflate, "inflate(layoutInflater)");
        this.binding = activityExerciseHeartGuideBindingInflate;
        if (activityExerciseHeartGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityExerciseHeartGuideBindingInflate = null;
        }
        ConstraintLayout root = activityExerciseHeartGuideBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getViewModel().m761getAge();
        getViewModel().getAge().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.ExerciseHeartGuideActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ExerciseHeartGuideActivity.m743setupViews$lambda1(this.f$0, (HeartActivityViewModel.UserAge) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m743setupViews$lambda1(ExerciseHeartGuideActivity this$0, HeartActivityViewModel.UserAge userAge) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.maxHeart = 220 - userAge.getAge();
        ActivityExerciseHeartGuideBinding activityExerciseHeartGuideBinding = this$0.binding;
        if (activityExerciseHeartGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityExerciseHeartGuideBinding = null;
        }
        activityExerciseHeartGuideBinding.titleBar.tvTitle.setText(this$0.getString(R.string.qc_text_498));
        activityExerciseHeartGuideBinding.tv10.setText(String.valueOf(MathKt.roundToInt(this$0.maxHeart * 0.5d)));
        activityExerciseHeartGuideBinding.tv7.setText(String.valueOf(MathKt.roundToInt(this$0.maxHeart * 0.6d)));
        activityExerciseHeartGuideBinding.tv5.setText(String.valueOf(MathKt.roundToInt(this$0.maxHeart * 0.7d)));
        activityExerciseHeartGuideBinding.tv3.setText(String.valueOf(MathKt.roundToInt(this$0.maxHeart * 0.8d)));
        activityExerciseHeartGuideBinding.tv1.setText(String.valueOf(MathKt.roundToInt(this$0.maxHeart * 0.9d)));
    }
}
