package com.qcwireless.qcwatch.ui.home.temperature;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.databinding.ActivityTemperatureGuideBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import skin.support.content.res.SkinCompatResources;

/* compiled from: TemperatureGuideActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/TemperatureGuideActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityTemperatureGuideBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureGuideActivity extends BaseActivity {
    private ActivityTemperatureGuideBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTemperatureGuideBinding activityTemperatureGuideBindingInflate = ActivityTemperatureGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTemperatureGuideBindingInflate, "inflate(layoutInflater)");
        this.binding = activityTemperatureGuideBindingInflate;
        if (activityTemperatureGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureGuideBindingInflate = null;
        }
        ConstraintLayout root = activityTemperatureGuideBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityTemperatureGuideBinding activityTemperatureGuideBinding = this.binding;
        if (activityTemperatureGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureGuideBinding = null;
        }
        activityTemperatureGuideBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_500));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(null), 3, null);
    }

    /* compiled from: TemperatureGuideActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.temperature.TemperatureGuideActivity$setupViews$2", f = "TemperatureGuideActivity.kt", i = {}, l = {32, 33}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.temperature.TemperatureGuideActivity$setupViews$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TemperatureGuideActivity.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceDisplay(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final TemperatureGuideActivity temperatureGuideActivity = TemperatureGuideActivity.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureGuideActivity.setupViews.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Integer) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(Integer num, Continuation<? super Unit> continuation) {
                    ActivityTemperatureGuideBinding activityTemperatureGuideBinding = null;
                    if (num != null && num.intValue() == 1) {
                        ActivityTemperatureGuideBinding activityTemperatureGuideBinding2 = temperatureGuideActivity.binding;
                        if (activityTemperatureGuideBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityTemperatureGuideBinding = activityTemperatureGuideBinding2;
                        }
                        activityTemperatureGuideBinding.watchDisplay.setBackground(SkinCompatResources.getDrawable(temperatureGuideActivity, R.mipmap.device_checking_guide_s));
                    } else {
                        ActivityTemperatureGuideBinding activityTemperatureGuideBinding3 = temperatureGuideActivity.binding;
                        if (activityTemperatureGuideBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityTemperatureGuideBinding = activityTemperatureGuideBinding3;
                        }
                        activityTemperatureGuideBinding.watchDisplay.setBackground(SkinCompatResources.getDrawable(temperatureGuideActivity, R.mipmap.device_checking_guide_y));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }
}
