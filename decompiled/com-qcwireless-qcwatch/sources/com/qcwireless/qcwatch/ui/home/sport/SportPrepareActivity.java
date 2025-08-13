package com.qcwireless.qcwatch.ui.home.sport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.AppSportRsp;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.qcwireless.qcwatch.databinding.ActivitySportPrepareBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SportPrepareActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportPrepareActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "animation1", "Landroid/view/animation/TranslateAnimation;", "animation2", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySportPrepareBinding;", "gpsResponse", "Lcom/oudmon/ble/base/communication/ICommandResponse;", "Lcom/oudmon/ble/base/communication/rsp/AppSportRsp;", "number", "", "sportType", "startGps", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "startAnim1", "startAnim2", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportPrepareActivity extends BaseActivity {
    private TranslateAnimation animation1;
    private TranslateAnimation animation2;
    private ActivitySportPrepareBinding binding;
    private int sportType;
    private int number = 3;
    private boolean startGps = true;
    private ICommandResponse<AppSportRsp> gpsResponse = new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportPrepareActivity$$ExternalSyntheticLambda0
        @Override // com.oudmon.ble.base.communication.ICommandResponse
        public final void onDataResponse(BaseRspCmd baseRspCmd) {
            SportPrepareActivity.m839gpsResponse$lambda0(this.f$0, (AppSportRsp) baseRspCmd);
        }
    };

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySportPrepareBinding activitySportPrepareBindingInflate = ActivitySportPrepareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySportPrepareBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySportPrepareBindingInflate;
        if (activitySportPrepareBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportPrepareBindingInflate = null;
        }
        ConstraintLayout root = activitySportPrepareBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("sportType")) : null;
        Intrinsics.checkNotNull(numValueOf);
        this.sportType = numValueOf.intValue();
        startAnim1();
    }

    private final void startAnim1() {
        if (this.animation1 == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -400.0f, 0.0f);
            this.animation1 = translateAnimation;
            Intrinsics.checkNotNull(translateAnimation);
            translateAnimation.setInterpolator(new AnticipateOvershootInterpolator(4.0f));
            TranslateAnimation translateAnimation2 = this.animation1;
            Intrinsics.checkNotNull(translateAnimation2);
            translateAnimation2.setDuration(800L);
            TranslateAnimation translateAnimation3 = this.animation1;
            Intrinsics.checkNotNull(translateAnimation3);
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportPrepareActivity.startAnim1.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    ActivitySportPrepareBinding activitySportPrepareBinding = SportPrepareActivity.this.binding;
                    if (activitySportPrepareBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySportPrepareBinding = null;
                    }
                    activitySportPrepareBinding.tvNumber1.setText(String.valueOf(SportPrepareActivity.this.number));
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    SystemClock.sleep(100L);
                    SportPrepareActivity sportPrepareActivity = SportPrepareActivity.this;
                    sportPrepareActivity.number--;
                    if (SportPrepareActivity.this.number >= 1) {
                        ActivitySportPrepareBinding activitySportPrepareBinding = SportPrepareActivity.this.binding;
                        if (activitySportPrepareBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySportPrepareBinding = null;
                        }
                        activitySportPrepareBinding.tvNumber1.startAnimation(SportPrepareActivity.this.animation1);
                        SportPrepareActivity.this.startAnim2();
                        return;
                    }
                    if (SportPrepareActivity.this.startGps) {
                        SportPrepareActivity.this.finish();
                        Bundle bundle = new Bundle();
                        bundle.putInt("sportType", SportPrepareActivity.this.sportType);
                        SportPrepareActivity sportPrepareActivity2 = SportPrepareActivity.this;
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intent intent = new Intent(sportPrepareActivity2, (Class<?>) SportRunningActivity.class);
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
                        sportPrepareActivity2.startActivity(intent);
                    }
                }
            });
        }
        ActivitySportPrepareBinding activitySportPrepareBinding = this.binding;
        if (activitySportPrepareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportPrepareBinding = null;
        }
        activitySportPrepareBinding.tvNumber1.startAnimation(this.animation1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAnim2() {
        ActivitySportPrepareBinding activitySportPrepareBinding = this.binding;
        ActivitySportPrepareBinding activitySportPrepareBinding2 = null;
        if (activitySportPrepareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportPrepareBinding = null;
        }
        activitySportPrepareBinding.tvNumber2.setText(String.valueOf(this.number + 1));
        if (this.animation2 == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 300.0f);
            this.animation2 = translateAnimation;
            Intrinsics.checkNotNull(translateAnimation);
            translateAnimation.setDuration(300L);
            TranslateAnimation translateAnimation2 = this.animation2;
            Intrinsics.checkNotNull(translateAnimation2);
            translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportPrepareActivity.startAnim2.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    ActivitySportPrepareBinding activitySportPrepareBinding3 = SportPrepareActivity.this.binding;
                    if (activitySportPrepareBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySportPrepareBinding3 = null;
                    }
                    activitySportPrepareBinding3.tvNumber2.setText("");
                }
            });
        }
        ActivitySportPrepareBinding activitySportPrepareBinding3 = this.binding;
        if (activitySportPrepareBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportPrepareBinding2 = activitySportPrepareBinding3;
        }
        activitySportPrepareBinding2.tvNumber2.startAnimation(this.animation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: gpsResponse$lambda-0, reason: not valid java name */
    public static final void m839gpsResponse$lambda0(SportPrepareActivity this$0, AppSportRsp appSportRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (appSportRsp != null) {
            try {
                if (appSportRsp.getGpsStatus() == 4) {
                    this$0.startGps = false;
                    this$0.finish();
                }
            } catch (Exception unused) {
            }
        }
    }
}
