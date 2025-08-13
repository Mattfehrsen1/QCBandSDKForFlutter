package com.qcwireless.qcwatch.ui.mine.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.LoginSuccessEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityLoginBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.mine.MineActivityFinish;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.util.MD5UtilKt;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity;
import com.qcwireless.qcwatch.ui.mine.privacy.LanguagePPMURLKt;
import com.qcwireless.qcwatch.ui.mine.privacy.WebActivity;
import com.qcwireless.qcwatch.ui.mine.register.RegisterActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: LoginActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001aH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/login/LoginActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityLoginBinding;", "email", "", "emptyInputFilter", "Landroid/text/InputFilter;", "getEmptyInputFilter", "()Landroid/text/InputFilter;", "setEmptyInputFilter", "(Landroid/text/InputFilter;)V", "inputFilter", "loginType", "", "loginViewModel", "Lcom/qcwireless/qcwatch/ui/mine/login/LoginViewModel;", "getLoginViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/login/LoginViewModel;", "loginViewModel$delegate", "Lkotlin/Lazy;", "password", "isValidEmail", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private String email;
    private InputFilter emptyInputFilter;
    private InputFilter inputFilter;
    private int loginType;

    /* renamed from: loginViewModel$delegate, reason: from kotlin metadata */
    private final Lazy loginViewModel;
    private String password;

    /* JADX WARN: Multi-variable type inference failed */
    public LoginActivity() {
        final LoginActivity loginActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.loginViewModel = LazyKt.lazy(new Function0<LoginViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.login.LoginViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final LoginViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(loginActivity, Reflection.getOrCreateKotlinClass(LoginViewModel.class), qualifier, objArr);
            }
        });
        this.emptyInputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$$ExternalSyntheticLambda0
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return LoginActivity.m962emptyInputFilter$lambda6(this.f$0, charSequence, i, i2, spanned, i3, i4);
            }
        };
        this.inputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$inputFilter$1
            private Pattern emoji = Pattern.compile("[^\\u0000-\\uFFFF]", 66);

            public final Pattern getEmoji() {
                return this.emoji;
            }

            public final void setEmoji(Pattern pattern) {
                this.emoji = pattern;
            }

            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Intrinsics.checkNotNullParameter(source, "source");
                Intrinsics.checkNotNullParameter(dest, "dest");
                return this.emoji.matcher(source).find() ? "" : source;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoginViewModel getLoginViewModel() {
        return (LoginViewModel) this.loginViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBindingInflate = ActivityLoginBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityLoginBindingInflate, "inflate(layoutInflater)");
        this.binding = activityLoginBindingInflate;
        if (activityLoginBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBindingInflate = null;
        }
        ConstraintLayout root = activityLoginBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        final ActivityLoginBinding activityLoginBinding = null;
        try {
            Bundle extras = getIntent().getExtras();
            Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("loginType")) : null;
            Intrinsics.checkNotNull(numValueOf);
            this.loginType = numValueOf.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        View[] viewArr = new View[4];
        ActivityLoginBinding activityLoginBinding2 = this.binding;
        if (activityLoginBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding2 = null;
        }
        viewArr[0] = activityLoginBinding2.btnLogin;
        ActivityLoginBinding activityLoginBinding3 = this.binding;
        if (activityLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding3 = null;
        }
        viewArr[1] = activityLoginBinding3.tvForgetPws;
        ActivityLoginBinding activityLoginBinding4 = this.binding;
        if (activityLoginBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding4 = null;
        }
        viewArr[2] = activityLoginBinding4.tvRegister;
        ActivityLoginBinding activityLoginBinding5 = this.binding;
        if (activityLoginBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding5 = null;
        }
        viewArr[3] = activityLoginBinding5.userPrivacyEn;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity.setupViews.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityLoginBinding activityLoginBinding6 = LoginActivity.this.binding;
                ActivityLoginBinding activityLoginBinding7 = null;
                ActivityLoginBinding activityLoginBinding8 = null;
                ActivityLoginBinding activityLoginBinding9 = null;
                ActivityLoginBinding activityLoginBinding10 = null;
                String str = null;
                String str2 = null;
                ActivityLoginBinding activityLoginBinding11 = null;
                ActivityLoginBinding activityLoginBinding12 = null;
                if (activityLoginBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding6 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityLoginBinding6.btnLogin)) {
                    ActivityLoginBinding activityLoginBinding13 = LoginActivity.this.binding;
                    if (activityLoginBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityLoginBinding13 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityLoginBinding13.tvForgetPws)) {
                        ActivityLoginBinding activityLoginBinding14 = LoginActivity.this.binding;
                        if (activityLoginBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityLoginBinding14 = null;
                        }
                        if (!Intrinsics.areEqual(setOnClickListener, activityLoginBinding14.tvRegister)) {
                            ActivityLoginBinding activityLoginBinding15 = LoginActivity.this.binding;
                            if (activityLoginBinding15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityLoginBinding7 = activityLoginBinding15;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, activityLoginBinding7.userPrivacyEn)) {
                                Bundle bundle = new Bundle();
                                bundle.putString("url", LanguagePPMURLKt.getLanguagePPMUrl());
                                Unit unit = Unit.INSTANCE;
                                LoginActivity loginActivity = LoginActivity.this;
                                ArrayList<Pair> arrayList = new ArrayList();
                                Intent intent = new Intent(loginActivity, (Class<?>) WebActivity.class);
                                intent.setFlags(1);
                                intent.putExtras(bundle);
                                for (Pair pair : arrayList) {
                                    if (pair != null) {
                                        String str3 = (String) pair.getFirst();
                                        Object second = pair.getSecond();
                                        if (second instanceof Integer) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).intValue()), "putExtra(name, value)");
                                        } else if (second instanceof Byte) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).byteValue()), "putExtra(name, value)");
                                        } else if (second instanceof Character) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Character) second).charValue()), "putExtra(name, value)");
                                        } else if (second instanceof Short) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).shortValue()), "putExtra(name, value)");
                                        } else if (second instanceof Boolean) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                        } else if (second instanceof Long) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).longValue()), "putExtra(name, value)");
                                        } else if (second instanceof Float) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).floatValue()), "putExtra(name, value)");
                                        } else if (second instanceof Double) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).doubleValue()), "putExtra(name, value)");
                                        } else if (second instanceof String) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (String) second), "putExtra(name, value)");
                                        } else if (second instanceof CharSequence) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (CharSequence) second), "putExtra(name, value)");
                                        } else if (second instanceof Parcelable) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Parcelable) second), "putExtra(name, value)");
                                        } else if (second instanceof Object[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Serializable) second), "putExtra(name, value)");
                                        } else if (second instanceof ArrayList) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Serializable) second), "putExtra(name, value)");
                                        } else if (second instanceof Serializable) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Serializable) second), "putExtra(name, value)");
                                        } else if (second instanceof boolean[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (boolean[]) second), "putExtra(name, value)");
                                        } else if (second instanceof byte[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (byte[]) second), "putExtra(name, value)");
                                        } else if (second instanceof short[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (short[]) second), "putExtra(name, value)");
                                        } else if (second instanceof char[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (char[]) second), "putExtra(name, value)");
                                        } else if (second instanceof int[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (int[]) second), "putExtra(name, value)");
                                        } else if (second instanceof long[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (long[]) second), "putExtra(name, value)");
                                        } else if (second instanceof float[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (float[]) second), "putExtra(name, value)");
                                        } else if (second instanceof double[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (double[]) second), "putExtra(name, value)");
                                        } else if (second instanceof Bundle) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Bundle) second), "putExtra(name, value)");
                                        } else if (second instanceof Intent) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Parcelable) second), "putExtra(name, value)");
                                        } else {
                                            Unit unit2 = Unit.INSTANCE;
                                        }
                                    }
                                }
                                Unit unit3 = Unit.INSTANCE;
                                Unit unit4 = Unit.INSTANCE;
                                Unit unit5 = Unit.INSTANCE;
                                loginActivity.startActivity(intent);
                                return;
                            }
                            return;
                        }
                        LoginActivity loginActivity2 = LoginActivity.this;
                        ArrayList<Pair> arrayList2 = new ArrayList();
                        Intent intent2 = new Intent(loginActivity2, (Class<?>) RegisterActivity.class);
                        for (Pair pair2 : arrayList2) {
                            if (pair2 != null) {
                                String str4 = (String) pair2.getFirst();
                                Object second2 = pair2.getSecond();
                                if (second2 instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Number) second2).intValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Number) second2).byteValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Character) second2).charValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Number) second2).shortValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Number) second2).longValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Number) second2).floatValue()), "putExtra(name, value)");
                                } else if (second2 instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, ((Number) second2).doubleValue()), "putExtra(name, value)");
                                } else if (second2 instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (String) second2), "putExtra(name, value)");
                                } else if (second2 instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (CharSequence) second2), "putExtra(name, value)");
                                } else if (second2 instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (Parcelable) second2), "putExtra(name, value)");
                                } else if (second2 instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (Serializable) second2), "putExtra(name, value)");
                                } else if (second2 instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (Serializable) second2), "putExtra(name, value)");
                                } else if (second2 instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (Serializable) second2), "putExtra(name, value)");
                                } else if (second2 instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (boolean[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (byte[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (short[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (char[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (int[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (long[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (float[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (double[]) second2), "putExtra(name, value)");
                                } else if (second2 instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (Bundle) second2), "putExtra(name, value)");
                                } else if (second2 instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str4, (Parcelable) second2), "putExtra(name, value)");
                                } else {
                                    Unit unit6 = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit7 = Unit.INSTANCE;
                        Unit unit8 = Unit.INSTANCE;
                        Unit unit9 = Unit.INSTANCE;
                        loginActivity2.startActivity(intent2);
                        return;
                    }
                    LoginActivity loginActivity3 = LoginActivity.this;
                    ArrayList<Pair> arrayList3 = new ArrayList();
                    Intent intent3 = new Intent(loginActivity3, (Class<?>) ForgetPwdActivity.class);
                    for (Pair pair3 : arrayList3) {
                        if (pair3 != null) {
                            String str5 = (String) pair3.getFirst();
                            Object second3 = pair3.getSecond();
                            if (second3 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Number) second3).intValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Number) second3).byteValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Character) second3).charValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Number) second3).shortValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Boolean) second3).booleanValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Number) second3).longValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Number) second3).floatValue()), "putExtra(name, value)");
                            } else if (second3 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, ((Number) second3).doubleValue()), "putExtra(name, value)");
                            } else if (second3 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (String) second3), "putExtra(name, value)");
                            } else if (second3 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (CharSequence) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (Parcelable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (Serializable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (Serializable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (Serializable) second3), "putExtra(name, value)");
                            } else if (second3 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (boolean[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (byte[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (short[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (char[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (int[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (long[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (float[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (double[]) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (Bundle) second3), "putExtra(name, value)");
                            } else if (second3 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str5, (Parcelable) second3), "putExtra(name, value)");
                            } else {
                                Unit unit10 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit11 = Unit.INSTANCE;
                    Unit unit12 = Unit.INSTANCE;
                    Unit unit13 = Unit.INSTANCE;
                    loginActivity3.startActivity(intent3);
                    return;
                }
                if (!NetWorkUtils.INSTANCE.isNetworkAvailable(LoginActivity.this)) {
                    ActivityLoginBinding activityLoginBinding16 = LoginActivity.this.binding;
                    if (activityLoginBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityLoginBinding8 = activityLoginBinding16;
                    }
                    activityLoginBinding8.tvError.setText(LoginActivity.this.getString(R.string.qc_text_223));
                    return;
                }
                final LoginActivity loginActivity4 = LoginActivity.this;
                ThreadExtKt.ktxRunOnBgSingle(setOnClickListener, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity.setupViews.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        if (NetWorkUtils.INSTANCE.isOnline()) {
                            return;
                        }
                        final LoginActivity loginActivity5 = loginActivity4;
                        ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity.setupViews.1.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                                invoke2(view);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(View ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                ActivityLoginBinding activityLoginBinding17 = loginActivity5.binding;
                                if (activityLoginBinding17 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityLoginBinding17 = null;
                                }
                                activityLoginBinding17.tvError.setText(loginActivity5.getString(R.string.qc_text_223));
                            }
                        });
                    }
                });
                ActivityLoginBinding activityLoginBinding17 = LoginActivity.this.binding;
                if (activityLoginBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding17 = null;
                }
                Editable text = activityLoginBinding17.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text, "binding.userEmail.text");
                if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "@", false, 2, (Object) null)) {
                    ActivityLoginBinding activityLoginBinding18 = LoginActivity.this.binding;
                    if (activityLoginBinding18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityLoginBinding18 = null;
                    }
                    activityLoginBinding18.tvError.setText(LoginActivity.this.getString(R.string.qc_text_219));
                    ActivityLoginBinding activityLoginBinding19 = LoginActivity.this.binding;
                    if (activityLoginBinding19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityLoginBinding9 = activityLoginBinding19;
                    }
                    activityLoginBinding9.btnLogin.setEnabled(false);
                    return;
                }
                LoginActivity loginActivity5 = LoginActivity.this;
                ActivityLoginBinding activityLoginBinding20 = loginActivity5.binding;
                if (activityLoginBinding20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding20 = null;
                }
                if (!loginActivity5.isValidEmail(activityLoginBinding20.userEmail.getText().toString())) {
                    ActivityLoginBinding activityLoginBinding21 = LoginActivity.this.binding;
                    if (activityLoginBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityLoginBinding21 = null;
                    }
                    activityLoginBinding21.tvError.setText(LoginActivity.this.getString(R.string.qc_text_219));
                    ActivityLoginBinding activityLoginBinding22 = LoginActivity.this.binding;
                    if (activityLoginBinding22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityLoginBinding10 = activityLoginBinding22;
                    }
                    activityLoginBinding10.btnLogin.setEnabled(false);
                    return;
                }
                ActivityLoginBinding activityLoginBinding23 = LoginActivity.this.binding;
                if (activityLoginBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding23 = null;
                }
                Editable text2 = activityLoginBinding23.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "binding.userEmail.text");
                Editable editable = text2;
                ActivityLoginBinding activityLoginBinding24 = LoginActivity.this.binding;
                if (activityLoginBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding24 = null;
                }
                Editable text3 = activityLoginBinding24.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text3, "binding.userEmail.text");
                String string = editable.subSequence(0, StringsKt.indexOf$default((CharSequence) text3, "@", 0, false, 4, (Object) null)).toString();
                if (!(string.length() == 0) && string.length() >= 2) {
                    ActivityLoginBinding activityLoginBinding25 = LoginActivity.this.binding;
                    if (activityLoginBinding25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityLoginBinding25 = null;
                    }
                    if (activityLoginBinding25.userEmail.getText().length() >= 3) {
                        ActivityLoginBinding activityLoginBinding26 = LoginActivity.this.binding;
                        if (activityLoginBinding26 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityLoginBinding26 = null;
                        }
                        activityLoginBinding26.tvError.setText("");
                        LoginActivity loginActivity6 = LoginActivity.this;
                        ActivityLoginBinding activityLoginBinding27 = loginActivity6.binding;
                        if (activityLoginBinding27 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityLoginBinding27 = null;
                        }
                        Editable text4 = activityLoginBinding27.userEmail.getText();
                        Intrinsics.checkNotNullExpressionValue(text4, "binding.userEmail.text");
                        loginActivity6.email = new Regex("\\s").replace(text4, "");
                        LoginActivity loginActivity7 = LoginActivity.this;
                        ActivityLoginBinding activityLoginBinding28 = loginActivity7.binding;
                        if (activityLoginBinding28 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityLoginBinding28 = null;
                        }
                        Editable text5 = activityLoginBinding28.userPwd.getText();
                        Intrinsics.checkNotNullExpressionValue(text5, "binding.userPwd.text");
                        loginActivity7.password = new Regex("\\s").replace(text5, "");
                        String str6 = LoginActivity.this.password;
                        if (str6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("password");
                            str6 = null;
                        }
                        if (str6.length() >= 6) {
                            String str7 = LoginActivity.this.password;
                            if (str7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("password");
                                str7 = null;
                            }
                            if (str7.length() <= 12) {
                                ActivityLoginBinding activityLoginBinding29 = LoginActivity.this.binding;
                                if (activityLoginBinding29 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityLoginBinding29 = null;
                                }
                                activityLoginBinding29.tvError.setText("");
                                LoginActivity.this.showLoadingDialog();
                                if (QCApplication.INSTANCE.getGetInstance().getPingHwServer()) {
                                    LoginViewModel loginViewModel = LoginActivity.this.getLoginViewModel();
                                    String str8 = LoginActivity.this.email;
                                    if (str8 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("email");
                                        str8 = null;
                                    }
                                    String str9 = LoginActivity.this.password;
                                    if (str9 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("password");
                                    } else {
                                        str = str9;
                                    }
                                    loginViewModel.login(str8, MD5UtilKt.encode(str), 2);
                                    return;
                                }
                                LoginViewModel loginViewModel2 = LoginActivity.this.getLoginViewModel();
                                String str10 = LoginActivity.this.email;
                                if (str10 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("email");
                                    str10 = null;
                                }
                                String str11 = LoginActivity.this.password;
                                if (str11 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("password");
                                } else {
                                    str2 = str11;
                                }
                                loginViewModel2.loginChina(str10, MD5UtilKt.encode(str2), 2);
                                return;
                            }
                        }
                        ActivityLoginBinding activityLoginBinding30 = LoginActivity.this.binding;
                        if (activityLoginBinding30 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityLoginBinding30 = null;
                        }
                        activityLoginBinding30.tvError.setText(LoginActivity.this.getString(R.string.qc_text_220));
                        ActivityLoginBinding activityLoginBinding31 = LoginActivity.this.binding;
                        if (activityLoginBinding31 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityLoginBinding11 = activityLoginBinding31;
                        }
                        activityLoginBinding11.btnLogin.setEnabled(false);
                        return;
                    }
                }
                ActivityLoginBinding activityLoginBinding32 = LoginActivity.this.binding;
                if (activityLoginBinding32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding32 = null;
                }
                activityLoginBinding32.tvError.setText(LoginActivity.this.getString(R.string.qc_text_219));
                ActivityLoginBinding activityLoginBinding33 = LoginActivity.this.binding;
                if (activityLoginBinding33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityLoginBinding12 = activityLoginBinding33;
                }
                activityLoginBinding12.btnLogin.setEnabled(false);
            }
        });
        getLoginViewModel().getLoginUI().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginActivity.m963setupViews$lambda1$lambda0(this.f$0, (NetState) obj);
            }
        });
        ActivityLoginBinding activityLoginBinding6 = this.binding;
        if (activityLoginBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLoginBinding = activityLoginBinding6;
        }
        activityLoginBinding.imgPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LoginActivity.m964setupViews$lambda5$lambda2(activityLoginBinding, compoundButton, z);
            }
        });
        if (UserConfig.INSTANCE.getInstance().getUserEmail().length() > 0) {
            activityLoginBinding.userEmail.setText(UserConfig.INSTANCE.getInstance().getUserEmail());
        }
        activityLoginBinding.userEmail.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        activityLoginBinding.userPwd.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        EditText userEmail = activityLoginBinding.userEmail;
        Intrinsics.checkNotNullExpressionValue(userEmail, "userEmail");
        userEmail.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$setupViews$lambda-5$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                activityLoginBinding.userEmail.requestFocus();
                Editable text = activityLoginBinding.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text, "userEmail.text");
                boolean z = false;
                if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "@", false, 2, (Object) null)) {
                    activityLoginBinding.tvError.setText(this.getString(R.string.qc_text_219));
                    activityLoginBinding.btnLogin.setEnabled(false);
                    return;
                }
                activityLoginBinding.btnLogin.setEnabled(true);
                activityLoginBinding.tvError.setText("");
                Editable text2 = activityLoginBinding.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "userEmail.text");
                Editable text3 = activityLoginBinding.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text3, "userEmail.text");
                if (text2.subSequence(0, StringsKt.indexOf$default((CharSequence) text3, "@", 0, false, 4, (Object) null)).toString().length() == 0) {
                    activityLoginBinding.tvError.setText(this.getString(R.string.qc_text_219));
                    activityLoginBinding.btnLogin.setEnabled(false);
                    return;
                }
                activityLoginBinding.btnLogin.setEnabled(true);
                activityLoginBinding.tvError.setText("");
                Button button = activityLoginBinding.btnLogin;
                Editable text4 = activityLoginBinding.userPwd.getText();
                Intrinsics.checkNotNullExpressionValue(text4, "userPwd.text");
                if (!(text4.length() == 0)) {
                    Editable text5 = activityLoginBinding.userEmail.getText();
                    Intrinsics.checkNotNullExpressionValue(text5, "userEmail.text");
                    if (!(text5.length() == 0)) {
                        z = true;
                    }
                }
                button.setEnabled(z);
                LoginActivity loginActivity = this;
                Editable text6 = activityLoginBinding.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text6, "userEmail.text");
                loginActivity.email = new Regex("\\s").replace(text6, "");
            }
        });
        EditText userPwd = activityLoginBinding.userPwd;
        Intrinsics.checkNotNullExpressionValue(userPwd, "userPwd");
        userPwd.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginActivity$setupViews$lambda-5$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                activityLoginBinding.userPwd.requestFocus();
                LoginActivity loginActivity = this;
                Editable text = activityLoginBinding.userPwd.getText();
                Intrinsics.checkNotNullExpressionValue(text, "userPwd.text");
                loginActivity.password = new Regex("\\s").replace(text, "");
                String str = this.password;
                String str2 = null;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("password");
                    str = null;
                }
                boolean z = false;
                if (str.length() >= 6) {
                    String str3 = this.password;
                    if (str3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("password");
                        str3 = null;
                    }
                    if (str3.length() <= 12) {
                        activityLoginBinding.btnLogin.setEnabled(true);
                        activityLoginBinding.tvError.setText("");
                        Button button = activityLoginBinding.btnLogin;
                        String str4 = this.password;
                        if (str4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("password");
                        } else {
                            str2 = str4;
                        }
                        if (!(str2.length() == 0)) {
                            Editable text2 = activityLoginBinding.userEmail.getText();
                            Intrinsics.checkNotNullExpressionValue(text2, "userEmail.text");
                            if (!(text2.length() == 0)) {
                                z = true;
                            }
                        }
                        button.setEnabled(z);
                        return;
                    }
                }
                activityLoginBinding.tvError.setText(this.getString(R.string.qc_text_220));
                activityLoginBinding.btnLogin.setEnabled(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m963setupViews$lambda1$lambda0(LoginActivity this$0, NetState netState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (netState.getIsLoading()) {
            this$0.showLoadingDialog();
        } else {
            this$0.dismissLoadingDialog();
        }
        int retCode = netState.getRetCode();
        if (retCode == 0) {
            UserConfig.INSTANCE.getInstance().setLoginStatus(true);
            UserConfig.INSTANCE.getInstance().save();
            EventBus.getDefault().post(new LoginSuccessEvent(this$0.loginType));
            EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
            this$0.finish();
            return;
        }
        if (retCode == 50003) {
            ActivityLoginBinding activityLoginBinding = this$0.binding;
            if (activityLoginBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityLoginBinding = null;
            }
            activityLoginBinding.tvError.setText(this$0.getString(R.string.qc_text_222));
            String string = this$0.getString(R.string.qc_text_222);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_222)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (retCode == 50012) {
            ActivityLoginBinding activityLoginBinding2 = this$0.binding;
            if (activityLoginBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityLoginBinding2 = null;
            }
            activityLoginBinding2.tvError.setText(this$0.getString(R.string.qc_text_272));
            String string2 = this$0.getString(R.string.qc_text_272);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_272)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        GlobalKt.showToast$default(this$0.getString(R.string.qc_text_288) + ':' + netState.getRetCode(), 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5$lambda-2, reason: not valid java name */
    public static final void m964setupViews$lambda5$lambda2(ActivityLoginBinding this_run, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (z) {
            this_run.userPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this_run.userPwd.setSelection(this_run.userPwd.length());
        } else {
            this_run.userPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this_run.userPwd.setSelection(this_run.userPwd.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isValidEmail(String email) {
        return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
    }

    public final InputFilter getEmptyInputFilter() {
        return this.emptyInputFilter;
    }

    public final void setEmptyInputFilter(InputFilter inputFilter) {
        Intrinsics.checkNotNullParameter(inputFilter, "<set-?>");
        this.emptyInputFilter = inputFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: emptyInputFilter$lambda-6, reason: not valid java name */
    public static final CharSequence m962emptyInputFilter$lambda6(LoginActivity this$0, CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!Intrinsics.areEqual(charSequence, " ") && !Intrinsics.areEqual(charSequence, "")) {
            return charSequence;
        }
        ActivityLoginBinding activityLoginBinding = this$0.binding;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        activityLoginBinding.tvError.setText(this$0.getString(R.string.qc_text_284));
        return "";
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof MineActivityFinish) {
            finish();
        }
    }
}
