package com.qcwireless.qcwatch.ui.home.gps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.PhoneGpsReq;
import com.oudmon.ble.base.communication.rsp.AppGpsRsp;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.qcwireless.qcwatch.databinding.ActivityGpsPrepareBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GpsPrepareActivity.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsPrepareActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "animation1", "Landroid/view/animation/TranslateAnimation;", "animation2", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGpsPrepareBinding;", "gpsResponse", "Lcom/oudmon/ble/base/communication/ICommandResponse;", "Lcom/oudmon/ble/base/communication/rsp/AppGpsRsp;", "number", "", "startGps", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "startAnim1", "startAnim2", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsPrepareActivity extends BaseActivity {
    private TranslateAnimation animation1;
    private TranslateAnimation animation2;
    private ActivityGpsPrepareBinding binding;
    private int number = 3;
    private boolean startGps = true;
    private ICommandResponse<AppGpsRsp> gpsResponse = new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsPrepareActivity$$ExternalSyntheticLambda0
        @Override // com.oudmon.ble.base.communication.ICommandResponse
        public final void onDataResponse(BaseRspCmd baseRspCmd) {
            GpsPrepareActivity.m675gpsResponse$lambda0(this.f$0, (AppGpsRsp) baseRspCmd);
        }
    };

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGpsPrepareBinding activityGpsPrepareBindingInflate = ActivityGpsPrepareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGpsPrepareBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGpsPrepareBindingInflate;
        if (activityGpsPrepareBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsPrepareBindingInflate = null;
        }
        ConstraintLayout root = activityGpsPrepareBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        startAnim1();
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 1), this.gpsResponse);
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
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsPrepareActivity.startAnim1.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    ActivityGpsPrepareBinding activityGpsPrepareBinding = GpsPrepareActivity.this.binding;
                    if (activityGpsPrepareBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityGpsPrepareBinding = null;
                    }
                    activityGpsPrepareBinding.tvNumber1.setText(String.valueOf(GpsPrepareActivity.this.number));
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    SystemClock.sleep(100L);
                    GpsPrepareActivity gpsPrepareActivity = GpsPrepareActivity.this;
                    gpsPrepareActivity.number--;
                    if (GpsPrepareActivity.this.number >= 1) {
                        ActivityGpsPrepareBinding activityGpsPrepareBinding = GpsPrepareActivity.this.binding;
                        if (activityGpsPrepareBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityGpsPrepareBinding = null;
                        }
                        activityGpsPrepareBinding.tvNumber1.startAnimation(GpsPrepareActivity.this.animation1);
                        GpsPrepareActivity.this.startAnim2();
                        return;
                    }
                    if (GpsPrepareActivity.this.startGps) {
                        GpsPrepareActivity.this.finish();
                        GpsPrepareActivity gpsPrepareActivity2 = GpsPrepareActivity.this;
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intent intent = new Intent(gpsPrepareActivity2, (Class<?>) GpsRunActivity.class);
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
                        gpsPrepareActivity2.startActivity(intent);
                    }
                }
            });
        }
        ActivityGpsPrepareBinding activityGpsPrepareBinding = this.binding;
        if (activityGpsPrepareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsPrepareBinding = null;
        }
        activityGpsPrepareBinding.tvNumber1.startAnimation(this.animation1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAnim2() {
        ActivityGpsPrepareBinding activityGpsPrepareBinding = this.binding;
        ActivityGpsPrepareBinding activityGpsPrepareBinding2 = null;
        if (activityGpsPrepareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsPrepareBinding = null;
        }
        activityGpsPrepareBinding.tvNumber2.setText(String.valueOf(this.number + 1));
        if (this.animation2 == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 300.0f);
            this.animation2 = translateAnimation;
            Intrinsics.checkNotNull(translateAnimation);
            translateAnimation.setDuration(300L);
            TranslateAnimation translateAnimation2 = this.animation2;
            Intrinsics.checkNotNull(translateAnimation2);
            translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsPrepareActivity.startAnim2.1
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
                    ActivityGpsPrepareBinding activityGpsPrepareBinding3 = GpsPrepareActivity.this.binding;
                    if (activityGpsPrepareBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityGpsPrepareBinding3 = null;
                    }
                    activityGpsPrepareBinding3.tvNumber2.setText("");
                }
            });
        }
        ActivityGpsPrepareBinding activityGpsPrepareBinding3 = this.binding;
        if (activityGpsPrepareBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsPrepareBinding2 = activityGpsPrepareBinding3;
        }
        activityGpsPrepareBinding2.tvNumber2.startAnimation(this.animation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: gpsResponse$lambda-0, reason: not valid java name */
    public static final void m675gpsResponse$lambda0(GpsPrepareActivity this$0, AppGpsRsp appGpsRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(appGpsRsp);
        if (appGpsRsp != null) {
            try {
                if (appGpsRsp.getGpsStatus() == 4) {
                    this$0.startGps = false;
                    this$0.finish();
                }
            } catch (Exception unused) {
            }
        }
    }
}
