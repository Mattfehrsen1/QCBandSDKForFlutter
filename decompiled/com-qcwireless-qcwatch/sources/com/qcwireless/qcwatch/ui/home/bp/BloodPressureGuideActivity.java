package com.qcwireless.qcwatch.ui.home.bp;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.databinding.ActivityBloodPressureGuideBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import skin.support.content.res.SkinCompatResources;

/* compiled from: BloodPressureGuideActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureGuideActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodPressureGuideBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureGuideActivity extends BaseActivity {
    private ActivityBloodPressureGuideBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodPressureGuideBinding activityBloodPressureGuideBindingInflate = ActivityBloodPressureGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodPressureGuideBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodPressureGuideBindingInflate;
        if (activityBloodPressureGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureGuideBindingInflate = null;
        }
        ConstraintLayout root = activityBloodPressureGuideBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityBloodPressureGuideBinding activityBloodPressureGuideBinding = this.binding;
        if (activityBloodPressureGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureGuideBinding = null;
        }
        activityBloodPressureGuideBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_500));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(null), 3, null);
    }

    /* compiled from: BloodPressureGuideActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bp.BloodPressureGuideActivity$setupViews$2", f = "BloodPressureGuideActivity.kt", i = {0}, l = {29, 30}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bp.BloodPressureGuideActivity$setupViews$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = BloodPressureGuideActivity.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
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
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final BloodPressureGuideActivity bloodPressureGuideActivity = BloodPressureGuideActivity.this;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureGuideActivity.setupViews.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Integer) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(final Integer num, Continuation<? super Unit> continuation) {
                    CoroutineScope coroutineScope2 = coroutineScope;
                    final BloodPressureGuideActivity bloodPressureGuideActivity2 = bloodPressureGuideActivity;
                    ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureGuideActivity.setupViews.2.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                            invoke2(coroutineScope3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CoroutineScope ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            Integer num2 = num;
                            ActivityBloodPressureGuideBinding activityBloodPressureGuideBinding = null;
                            if (num2 == null) {
                                ActivityBloodPressureGuideBinding activityBloodPressureGuideBinding2 = bloodPressureGuideActivity2.binding;
                                if (activityBloodPressureGuideBinding2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityBloodPressureGuideBinding = activityBloodPressureGuideBinding2;
                                }
                                activityBloodPressureGuideBinding.watchDisplay.setBackground(SkinCompatResources.getDrawable(bloodPressureGuideActivity2, R.mipmap.device_checking_guide_y));
                                return;
                            }
                            if (num2 != null && num2.intValue() == 1) {
                                ActivityBloodPressureGuideBinding activityBloodPressureGuideBinding3 = bloodPressureGuideActivity2.binding;
                                if (activityBloodPressureGuideBinding3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityBloodPressureGuideBinding = activityBloodPressureGuideBinding3;
                                }
                                activityBloodPressureGuideBinding.watchDisplay.setBackground(SkinCompatResources.getDrawable(bloodPressureGuideActivity2, R.mipmap.device_checking_guide_s));
                                return;
                            }
                            ActivityBloodPressureGuideBinding activityBloodPressureGuideBinding4 = bloodPressureGuideActivity2.binding;
                            if (activityBloodPressureGuideBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityBloodPressureGuideBinding = activityBloodPressureGuideBinding4;
                            }
                            activityBloodPressureGuideBinding.watchDisplay.setBackground(SkinCompatResources.getDrawable(bloodPressureGuideActivity2, R.mipmap.device_checking_guide_y));
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }
}
