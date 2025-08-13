package com.qcwireless.qcwatch.ui.mine.register;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.LoginSuccessEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityRegisterBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.mine.MineActivityFinish;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.util.MD5UtilKt;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
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

/* compiled from: RegisterActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0019H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/register/RegisterActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityRegisterBinding;", "email", "", "emptyInputFilter", "Landroid/text/InputFilter;", "getEmptyInputFilter", "()Landroid/text/InputFilter;", "setEmptyInputFilter", "(Landroid/text/InputFilter;)V", "inputFilter", "password", "passwordAgain", "registerViewModel", "Lcom/qcwireless/qcwatch/ui/mine/register/RegisterViewModel;", "getRegisterViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/register/RegisterViewModel;", "registerViewModel$delegate", "Lkotlin/Lazy;", "isValidEmail", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding binding;
    private InputFilter emptyInputFilter;
    private InputFilter inputFilter;

    /* renamed from: registerViewModel$delegate, reason: from kotlin metadata */
    private final Lazy registerViewModel;
    private String email = "";
    private String password = "";
    private String passwordAgain = "";

    /* JADX WARN: Multi-variable type inference failed */
    public RegisterActivity() {
        final RegisterActivity registerActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.registerViewModel = LazyKt.lazy(new Function0<RegisterViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final RegisterViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(registerActivity, Reflection.getOrCreateKotlinClass(RegisterViewModel.class), qualifier, objArr);
            }
        });
        this.emptyInputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$$ExternalSyntheticLambda0
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return RegisterActivity.m967emptyInputFilter$lambda7(this.f$0, charSequence, i, i2, spanned, i3, i4);
            }
        };
        this.inputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$inputFilter$1
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
    public final RegisterViewModel getRegisterViewModel() {
        return (RegisterViewModel) this.registerViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding activityRegisterBindingInflate = ActivityRegisterBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRegisterBindingInflate, "inflate(layoutInflater)");
        this.binding = activityRegisterBindingInflate;
        if (activityRegisterBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRegisterBindingInflate = null;
        }
        ConstraintLayout root = activityRegisterBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        final ActivityRegisterBinding activityRegisterBinding = this.binding;
        ActivityRegisterBinding activityRegisterBinding2 = null;
        if (activityRegisterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRegisterBinding = null;
        }
        activityRegisterBinding.imgPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RegisterActivity.m968setupViews$lambda6$lambda0(activityRegisterBinding, compoundButton, z);
            }
        });
        activityRegisterBinding.imgPwdEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RegisterActivity.m969setupViews$lambda6$lambda1(activityRegisterBinding, compoundButton, z);
            }
        });
        activityRegisterBinding.userEmail.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        activityRegisterBinding.userPwd.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        activityRegisterBinding.userPwdAgain.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        EditText userEmail = activityRegisterBinding.userEmail;
        Intrinsics.checkNotNullExpressionValue(userEmail, "userEmail");
        userEmail.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$setupViews$lambda-6$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Editable text = activityRegisterBinding.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text, "userEmail.text");
                ActivityRegisterBinding activityRegisterBinding3 = null;
                if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "@", false, 2, (Object) null)) {
                    activityRegisterBinding.tvError.setText(this.getString(R.string.qc_text_219));
                    activityRegisterBinding.btnRegister.setEnabled(false);
                    return;
                }
                activityRegisterBinding.btnRegister.setEnabled(true);
                activityRegisterBinding.tvError.setText("");
                if (!this.isValidEmail(activityRegisterBinding.userEmail.getText().toString())) {
                    ActivityRegisterBinding activityRegisterBinding4 = this.binding;
                    if (activityRegisterBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisterBinding3 = activityRegisterBinding4;
                    }
                    activityRegisterBinding3.tvError.setText(this.getString(R.string.qc_text_219));
                    return;
                }
                RegisterActivity registerActivity = this;
                Editable text2 = activityRegisterBinding.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "userEmail.text");
                registerActivity.email = new Regex("\\s").replace(text2, "");
            }
        });
        EditText userPwd = activityRegisterBinding.userPwd;
        Intrinsics.checkNotNullExpressionValue(userPwd, "userPwd");
        userPwd.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$setupViews$lambda-6$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (activityRegisterBinding.userPwd.getText().length() < 6 || activityRegisterBinding.userPwd.getText().length() > 12) {
                    activityRegisterBinding.tvError.setText(this.getString(R.string.qc_text_220));
                    activityRegisterBinding.btnRegister.setEnabled(false);
                } else {
                    activityRegisterBinding.btnRegister.setEnabled(true);
                    activityRegisterBinding.tvError.setText("");
                    this.password = activityRegisterBinding.userPwd.getText().toString();
                }
            }
        });
        EditText userPwdAgain = activityRegisterBinding.userPwdAgain;
        Intrinsics.checkNotNullExpressionValue(userPwdAgain, "userPwdAgain");
        userPwdAgain.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$setupViews$lambda-6$$inlined$doAfterTextChanged$3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (activityRegisterBinding.userPwdAgain.getText().length() < 6 || activityRegisterBinding.userPwdAgain.getText().length() > 12) {
                    activityRegisterBinding.tvError.setText(this.getString(R.string.qc_text_220));
                    activityRegisterBinding.btnRegister.setEnabled(false);
                } else {
                    activityRegisterBinding.btnRegister.setEnabled(true);
                    activityRegisterBinding.tvError.setText("");
                    this.passwordAgain = activityRegisterBinding.userPwdAgain.getText().toString();
                }
            }
        });
        View[] viewArr = new View[1];
        ActivityRegisterBinding activityRegisterBinding3 = this.binding;
        if (activityRegisterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRegisterBinding2 = activityRegisterBinding3;
        }
        viewArr[0] = activityRegisterBinding2.btnRegister;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$setupViews$1$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                ActivityRegisterBinding activityRegisterBinding4 = this.this$0.binding;
                ActivityRegisterBinding activityRegisterBinding5 = null;
                if (activityRegisterBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRegisterBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityRegisterBinding4.btnRegister)) {
                    if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this.this$0)) {
                        ActivityRegisterBinding activityRegisterBinding6 = this.this$0.binding;
                        if (activityRegisterBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityRegisterBinding5 = activityRegisterBinding6;
                        }
                        activityRegisterBinding5.tvError.setText(this.this$0.getString(R.string.qc_text_223));
                        return;
                    }
                    final RegisterActivity registerActivity = this.this$0;
                    ThreadExtKt.ktxRunOnBgSingle(setOnClickListener, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$setupViews$1$6.1
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
                            final RegisterActivity registerActivity2 = registerActivity;
                            ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity.setupViews.1.6.1.1
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
                                    ActivityRegisterBinding activityRegisterBinding7 = registerActivity2.binding;
                                    if (activityRegisterBinding7 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityRegisterBinding7 = null;
                                    }
                                    activityRegisterBinding7.tvError.setText(registerActivity2.getString(R.string.qc_text_223));
                                }
                            });
                        }
                    });
                    Editable text = activityRegisterBinding.userEmail.getText();
                    Intrinsics.checkNotNullExpressionValue(text, "userEmail.text");
                    if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "@", false, 2, (Object) null)) {
                        activityRegisterBinding.tvError.setText(this.this$0.getString(R.string.qc_text_219));
                        activityRegisterBinding.btnRegister.setEnabled(false);
                        return;
                    }
                    activityRegisterBinding.btnRegister.setEnabled(true);
                    activityRegisterBinding.tvError.setText("");
                    RegisterActivity registerActivity2 = this.this$0;
                    if (!registerActivity2.isValidEmail(registerActivity2.email)) {
                        ActivityRegisterBinding activityRegisterBinding7 = this.this$0.binding;
                        if (activityRegisterBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityRegisterBinding7 = null;
                        }
                        activityRegisterBinding7.tvError.setText(this.this$0.getString(R.string.qc_text_219));
                        ActivityRegisterBinding activityRegisterBinding8 = this.this$0.binding;
                        if (activityRegisterBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityRegisterBinding5 = activityRegisterBinding8;
                        }
                        activityRegisterBinding5.btnRegister.setEnabled(false);
                        return;
                    }
                    activityRegisterBinding.btnRegister.setEnabled(true);
                    activityRegisterBinding.tvError.setText("");
                    ActivityRegisterBinding activityRegisterBinding9 = this.this$0.binding;
                    if (activityRegisterBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisterBinding9 = null;
                    }
                    Editable text2 = activityRegisterBinding9.userEmail.getText();
                    Intrinsics.checkNotNullExpressionValue(text2, "binding.userEmail.text");
                    Editable editable = text2;
                    ActivityRegisterBinding activityRegisterBinding10 = this.this$0.binding;
                    if (activityRegisterBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisterBinding10 = null;
                    }
                    Editable text3 = activityRegisterBinding10.userEmail.getText();
                    Intrinsics.checkNotNullExpressionValue(text3, "binding.userEmail.text");
                    String string = editable.subSequence(0, StringsKt.indexOf$default((CharSequence) text3, "@", 0, false, 4, (Object) null)).toString();
                    ActivityRegisterBinding activityRegisterBinding11 = this.this$0.binding;
                    if (activityRegisterBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisterBinding11 = null;
                    }
                    Editable text4 = activityRegisterBinding11.userEmail.getText();
                    Intrinsics.checkNotNullExpressionValue(text4, "binding.userEmail.text");
                    if (StringsKt.contains$default((CharSequence) text4, (CharSequence) "@", false, 2, (Object) null)) {
                        if (!(string.length() == 0) && string.length() >= 2) {
                            ActivityRegisterBinding activityRegisterBinding12 = this.this$0.binding;
                            if (activityRegisterBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRegisterBinding12 = null;
                            }
                            if (activityRegisterBinding12.userEmail.getText().length() >= 3) {
                                activityRegisterBinding.btnRegister.setEnabled(true);
                                ActivityRegisterBinding activityRegisterBinding13 = this.this$0.binding;
                                if (activityRegisterBinding13 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityRegisterBinding13 = null;
                                }
                                activityRegisterBinding13.tvError.setText("");
                                RegisterActivity registerActivity3 = this.this$0;
                                Editable text5 = activityRegisterBinding.userEmail.getText();
                                Intrinsics.checkNotNullExpressionValue(text5, "userEmail.text");
                                registerActivity3.email = new Regex("\\s").replace(text5, "");
                                RegisterActivity registerActivity4 = this.this$0;
                                ActivityRegisterBinding activityRegisterBinding14 = registerActivity4.binding;
                                if (activityRegisterBinding14 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityRegisterBinding14 = null;
                                }
                                Editable text6 = activityRegisterBinding14.userPwdAgain.getText();
                                Intrinsics.checkNotNullExpressionValue(text6, "binding.userPwdAgain.text");
                                registerActivity4.passwordAgain = new Regex("\\s").replace(text6, "");
                                if (this.this$0.passwordAgain.length() < 6 || this.this$0.passwordAgain.length() > 12) {
                                    ActivityRegisterBinding activityRegisterBinding15 = this.this$0.binding;
                                    if (activityRegisterBinding15 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityRegisterBinding15 = null;
                                    }
                                    activityRegisterBinding15.tvError.setText(this.this$0.getString(R.string.qc_text_220));
                                    ActivityRegisterBinding activityRegisterBinding16 = this.this$0.binding;
                                    if (activityRegisterBinding16 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        activityRegisterBinding5 = activityRegisterBinding16;
                                    }
                                    activityRegisterBinding5.btnRegister.setEnabled(false);
                                    return;
                                }
                                activityRegisterBinding.btnRegister.setEnabled(true);
                                ActivityRegisterBinding activityRegisterBinding17 = this.this$0.binding;
                                if (activityRegisterBinding17 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityRegisterBinding17 = null;
                                }
                                activityRegisterBinding17.tvError.setText("");
                                RegisterActivity registerActivity5 = this.this$0;
                                ActivityRegisterBinding activityRegisterBinding18 = registerActivity5.binding;
                                if (activityRegisterBinding18 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityRegisterBinding18 = null;
                                }
                                Editable text7 = activityRegisterBinding18.userPwd.getText();
                                Intrinsics.checkNotNullExpressionValue(text7, "binding.userPwd.text");
                                registerActivity5.password = new Regex("\\s").replace(text7, "");
                                if (this.this$0.password.length() < 6 || this.this$0.password.length() > 12) {
                                    ActivityRegisterBinding activityRegisterBinding19 = this.this$0.binding;
                                    if (activityRegisterBinding19 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityRegisterBinding19 = null;
                                    }
                                    activityRegisterBinding19.tvError.setText(this.this$0.getString(R.string.qc_text_220));
                                    ActivityRegisterBinding activityRegisterBinding20 = this.this$0.binding;
                                    if (activityRegisterBinding20 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        activityRegisterBinding5 = activityRegisterBinding20;
                                    }
                                    activityRegisterBinding5.btnRegister.setEnabled(false);
                                    return;
                                }
                                activityRegisterBinding.btnRegister.setEnabled(true);
                                ActivityRegisterBinding activityRegisterBinding21 = this.this$0.binding;
                                if (activityRegisterBinding21 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityRegisterBinding21 = null;
                                }
                                activityRegisterBinding21.tvError.setText("");
                                if (!Intrinsics.areEqual(this.this$0.password, this.this$0.passwordAgain)) {
                                    ActivityRegisterBinding activityRegisterBinding22 = this.this$0.binding;
                                    if (activityRegisterBinding22 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        activityRegisterBinding5 = activityRegisterBinding22;
                                    }
                                    activityRegisterBinding5.tvError.setText(this.this$0.getString(R.string.qc_text_224));
                                    return;
                                }
                                if (!(this.this$0.email.length() == 0)) {
                                    if (!(this.this$0.password.length() == 0)) {
                                        if (!(this.this$0.passwordAgain.length() == 0)) {
                                            this.this$0.showLoadingDialog();
                                            if (QCApplication.INSTANCE.getGetInstance().getPingHwServer()) {
                                                this.this$0.getRegisterViewModel().register(this.this$0.email, MD5UtilKt.encode(this.this$0.passwordAgain), 2);
                                                return;
                                            } else {
                                                this.this$0.getRegisterViewModel().registerChina(this.this$0.email, MD5UtilKt.encode(this.this$0.passwordAgain), 2);
                                                return;
                                            }
                                        }
                                    }
                                }
                                ActivityRegisterBinding activityRegisterBinding23 = this.this$0.binding;
                                if (activityRegisterBinding23 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityRegisterBinding5 = activityRegisterBinding23;
                                }
                                activityRegisterBinding5.tvError.setText(this.this$0.getString(R.string.qc_text_225));
                                return;
                            }
                        }
                    }
                    ActivityRegisterBinding activityRegisterBinding24 = this.this$0.binding;
                    if (activityRegisterBinding24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRegisterBinding24 = null;
                    }
                    activityRegisterBinding24.tvError.setText(this.this$0.getString(R.string.qc_text_219));
                    ActivityRegisterBinding activityRegisterBinding25 = this.this$0.binding;
                    if (activityRegisterBinding25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRegisterBinding5 = activityRegisterBinding25;
                    }
                    activityRegisterBinding5.btnRegister.setEnabled(false);
                }
            }
        });
        getRegisterViewModel().getRegisterUI().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RegisterActivity.m970setupViews$lambda6$lambda5(this.f$0, (NetState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6$lambda-0, reason: not valid java name */
    public static final void m968setupViews$lambda6$lambda0(ActivityRegisterBinding this_run, CompoundButton compoundButton, boolean z) {
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
    /* renamed from: setupViews$lambda-6$lambda-1, reason: not valid java name */
    public static final void m969setupViews$lambda6$lambda1(ActivityRegisterBinding this_run, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (z) {
            this_run.userPwdAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this_run.userPwdAgain.setSelection(this_run.userPwdAgain.length());
        } else {
            this_run.userPwdAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this_run.userPwdAgain.setSelection(this_run.userPwdAgain.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6$lambda-5, reason: not valid java name */
    public static final void m970setupViews$lambda6$lambda5(RegisterActivity this$0, NetState netState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (netState.getIsLoading()) {
            this$0.showLoadingDialog();
        } else {
            this$0.dismissLoadingDialog();
        }
        int retCode = netState.getRetCode();
        if (retCode != 0) {
            if (retCode != 50004) {
                return;
            }
            ActivityRegisterBinding activityRegisterBinding = this$0.binding;
            if (activityRegisterBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRegisterBinding = null;
            }
            activityRegisterBinding.tvError.setText(this$0.getString(R.string.qc_text_226));
            String string = this$0.getString(R.string.qc_text_226);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_226)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        UserConfig.INSTANCE.getInstance().setLoginStatus(true);
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new MineActivityFinish(1));
        if (UserConfig.INSTANCE.getInstance().getCsChatLogin()) {
            EventBus.getDefault().post(new LoginSuccessEvent(1));
        } else {
            EventBus.getDefault().post(new LoginSuccessEvent(0));
        }
        EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
        String string2 = this$0.getString(R.string.qc_text_510);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_510)");
        GlobalKt.showToast$default(string2, 0, 1, null);
        RegisterActivity registerActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(registerActivity, (Class<?>) FirstProfileActivity.class);
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
        registerActivity.startActivity(intent);
        this$0.finish();
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
    /* renamed from: emptyInputFilter$lambda-7, reason: not valid java name */
    public static final CharSequence m967emptyInputFilter$lambda7(RegisterActivity this$0, CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!Intrinsics.areEqual(charSequence, " ")) {
            return charSequence;
        }
        ActivityRegisterBinding activityRegisterBinding = this$0.binding;
        if (activityRegisterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRegisterBinding = null;
        }
        activityRegisterBinding.tvError.setText(this$0.getString(R.string.qc_text_284));
        return "";
    }
}
