package com.qcwireless.qcwatch.ui.mine.findpwd;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityForgetPwdBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.util.MD5UtilKt;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ForgetPwdActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/findpwd/ForgetPwdActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityForgetPwdBinding;", "emptyInputFilter", "Landroid/text/InputFilter;", "handler", "Landroid/os/Handler;", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "", "inputFilter", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/findpwd/ForgetPwdViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/findpwd/ForgetPwdViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "secondsDown", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ForgetPwdActivity extends BaseActivity {
    private ActivityForgetPwdBinding binding;
    private InputFilter emptyInputFilter;
    private final Handler handler;
    private final int index;
    private InputFilter inputFilter;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ForgetPwdActivity() {
        final ForgetPwdActivity forgetPwdActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<ForgetPwdViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ForgetPwdViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(forgetPwdActivity, Reflection.getOrCreateKotlinClass(ForgetPwdViewModel.class), qualifier, objArr);
            }
        });
        this.index = 60;
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
        this.emptyInputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$$ExternalSyntheticLambda0
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return ForgetPwdActivity.m947emptyInputFilter$lambda7(this.f$0, charSequence, i, i2, spanned, i3, i4);
            }
        };
        this.inputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$inputFilter$1
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
    public final ForgetPwdViewModel getViewModel() {
        return (ForgetPwdViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityForgetPwdBinding activityForgetPwdBindingInflate = ActivityForgetPwdBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityForgetPwdBindingInflate, "inflate(layoutInflater)");
        this.binding = activityForgetPwdBindingInflate;
        if (activityForgetPwdBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPwdBindingInflate = null;
        }
        ConstraintLayout root = activityForgetPwdBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        final ActivityForgetPwdBinding activityForgetPwdBinding = this.binding;
        ActivityForgetPwdBinding activityForgetPwdBinding2 = null;
        if (activityForgetPwdBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPwdBinding = null;
        }
        activityForgetPwdBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_179));
        activityForgetPwdBinding.userEmail.setText(UserConfig.INSTANCE.getInstance().getUserEmail());
        activityForgetPwdBinding.imgPwdNew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ForgetPwdActivity.m948setupViews$lambda5$lambda0(activityForgetPwdBinding, compoundButton, z);
            }
        });
        activityForgetPwdBinding.imgPwdConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ForgetPwdActivity.m949setupViews$lambda5$lambda1(activityForgetPwdBinding, compoundButton, z);
            }
        });
        ActivityForgetPwdBinding activityForgetPwdBinding3 = this.binding;
        if (activityForgetPwdBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPwdBinding3 = null;
        }
        activityForgetPwdBinding3.sendCode.setClickable(true);
        activityForgetPwdBinding.userEmail.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        activityForgetPwdBinding.userPwdNew.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        activityForgetPwdBinding.userPwdConfirm.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        EditText userEmail = activityForgetPwdBinding.userEmail;
        Intrinsics.checkNotNullExpressionValue(userEmail, "userEmail");
        userEmail.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$setupViews$lambda-5$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ActivityForgetPwdBinding activityForgetPwdBinding4 = this.this$0.binding;
                if (activityForgetPwdBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding4 = null;
                }
                activityForgetPwdBinding4.tvError.setText("");
            }
        });
        EditText userPwdNew = activityForgetPwdBinding.userPwdNew;
        Intrinsics.checkNotNullExpressionValue(userPwdNew, "userPwdNew");
        userPwdNew.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$setupViews$lambda-5$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ActivityForgetPwdBinding activityForgetPwdBinding4 = this.this$0.binding;
                if (activityForgetPwdBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding4 = null;
                }
                activityForgetPwdBinding4.tvError.setText("");
            }
        });
        EditText userPwdConfirm = activityForgetPwdBinding.userPwdConfirm;
        Intrinsics.checkNotNullExpressionValue(userPwdConfirm, "userPwdConfirm");
        userPwdConfirm.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$setupViews$lambda-5$$inlined$doAfterTextChanged$3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ActivityForgetPwdBinding activityForgetPwdBinding4 = this.this$0.binding;
                if (activityForgetPwdBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding4 = null;
                }
                activityForgetPwdBinding4.tvError.setText("");
            }
        });
        View[] viewArr = new View[2];
        ActivityForgetPwdBinding activityForgetPwdBinding4 = this.binding;
        if (activityForgetPwdBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPwdBinding4 = null;
        }
        viewArr[0] = activityForgetPwdBinding4.sendCode;
        ActivityForgetPwdBinding activityForgetPwdBinding5 = this.binding;
        if (activityForgetPwdBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityForgetPwdBinding2 = activityForgetPwdBinding5;
        }
        viewArr[1] = activityForgetPwdBinding2.btnConfirm;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity.setupViews.2
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
                ActivityForgetPwdBinding activityForgetPwdBinding6 = ForgetPwdActivity.this.binding;
                ActivityForgetPwdBinding activityForgetPwdBinding7 = null;
                if (activityForgetPwdBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding6 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityForgetPwdBinding6.sendCode)) {
                    ActivityForgetPwdBinding activityForgetPwdBinding8 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityForgetPwdBinding8 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityForgetPwdBinding8.btnConfirm)) {
                        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(ForgetPwdActivity.this)) {
                            ActivityForgetPwdBinding activityForgetPwdBinding9 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityForgetPwdBinding7 = activityForgetPwdBinding9;
                            }
                            activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_223));
                            return;
                        }
                        final ForgetPwdActivity forgetPwdActivity = ForgetPwdActivity.this;
                        ThreadExtKt.ktxRunOnBgSingle(setOnClickListener, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity.setupViews.2.2
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
                                final ForgetPwdActivity forgetPwdActivity2 = forgetPwdActivity;
                                ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity.setupViews.2.2.1
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
                                        ActivityForgetPwdBinding activityForgetPwdBinding10 = forgetPwdActivity2.binding;
                                        if (activityForgetPwdBinding10 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityForgetPwdBinding10 = null;
                                        }
                                        activityForgetPwdBinding10.tvError.setText(forgetPwdActivity2.getString(R.string.qc_text_223));
                                    }
                                });
                            }
                        });
                        ActivityForgetPwdBinding activityForgetPwdBinding10 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding10 = null;
                        }
                        String string = activityForgetPwdBinding10.userPwdNew.getText().toString();
                        ActivityForgetPwdBinding activityForgetPwdBinding11 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding11 = null;
                        }
                        String string2 = activityForgetPwdBinding11.userPwdConfirm.getText().toString();
                        ActivityForgetPwdBinding activityForgetPwdBinding12 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding12 = null;
                        }
                        String string3 = activityForgetPwdBinding12.etVerifyCode.getText().toString();
                        ActivityForgetPwdBinding activityForgetPwdBinding13 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding13 = null;
                        }
                        Editable text = activityForgetPwdBinding13.userEmail.getText();
                        Intrinsics.checkNotNullExpressionValue(text, "binding.userEmail.text");
                        String strReplace = new Regex("\\s").replace(text, "");
                        if (strReplace.length() == 0) {
                            ActivityForgetPwdBinding activityForgetPwdBinding14 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityForgetPwdBinding14 = null;
                            }
                            activityForgetPwdBinding14.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_162));
                            String string4 = ForgetPwdActivity.this.getString(R.string.qc_text_162);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_162)");
                            GlobalKt.showToast$default(string4, 0, 1, null);
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding15 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding15 = null;
                        }
                        activityForgetPwdBinding15.tvError.setText("");
                        if (string3.length() == 0) {
                            ActivityForgetPwdBinding activityForgetPwdBinding16 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding16 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityForgetPwdBinding16 = null;
                            }
                            activityForgetPwdBinding16.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_176));
                            String string5 = ForgetPwdActivity.this.getString(R.string.qc_text_176);
                            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_176)");
                            GlobalKt.showToast$default(string5, 0, 1, null);
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding17 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding17 = null;
                        }
                        activityForgetPwdBinding17.tvError.setText("");
                        String str = string;
                        if (str.length() == 0) {
                            ActivityForgetPwdBinding activityForgetPwdBinding18 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding18 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityForgetPwdBinding18 = null;
                            }
                            activityForgetPwdBinding18.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_177));
                            String string6 = ForgetPwdActivity.this.getString(R.string.qc_text_177);
                            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_177)");
                            GlobalKt.showToast$default(string6, 0, 1, null);
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding19 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding19 = null;
                        }
                        activityForgetPwdBinding19.tvError.setText("");
                        String str2 = string2;
                        if (str2.length() == 0) {
                            ActivityForgetPwdBinding activityForgetPwdBinding20 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding20 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityForgetPwdBinding20 = null;
                            }
                            activityForgetPwdBinding20.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_178));
                            String string7 = ForgetPwdActivity.this.getString(R.string.qc_text_177);
                            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_177)");
                            GlobalKt.showToast$default(string7, 0, 1, null);
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding21 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding21 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding21 = null;
                        }
                        activityForgetPwdBinding21.btnConfirm.setEnabled(true);
                        ActivityForgetPwdBinding activityForgetPwdBinding22 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding22 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding22 = null;
                        }
                        activityForgetPwdBinding22.tvError.setText("");
                        if (!Intrinsics.areEqual(string, string2)) {
                            ActivityForgetPwdBinding activityForgetPwdBinding23 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding23 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityForgetPwdBinding23 = null;
                            }
                            activityForgetPwdBinding23.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_234));
                            String string8 = ForgetPwdActivity.this.getString(R.string.qc_text_234);
                            Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_234)");
                            GlobalKt.showToast$default(string8, 0, 1, null);
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding24 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding24 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding24 = null;
                        }
                        activityForgetPwdBinding24.btnConfirm.setEnabled(true);
                        ActivityForgetPwdBinding activityForgetPwdBinding25 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding25 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding25 = null;
                        }
                        activityForgetPwdBinding25.tvError.setText("");
                        String strReplace2 = new Regex("\\s").replace(str, "");
                        if (strReplace2.length() < 6 || strReplace2.length() > 12) {
                            ActivityForgetPwdBinding activityForgetPwdBinding26 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding26 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityForgetPwdBinding7 = activityForgetPwdBinding26;
                            }
                            activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_220));
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding27 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding27 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding27 = null;
                        }
                        activityForgetPwdBinding27.btnConfirm.setEnabled(true);
                        ActivityForgetPwdBinding activityForgetPwdBinding28 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding28 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding28 = null;
                        }
                        activityForgetPwdBinding28.tvError.setText("");
                        String strReplace3 = new Regex("\\s").replace(str2, "");
                        if (strReplace3.length() < 6 || strReplace3.length() > 12) {
                            ActivityForgetPwdBinding activityForgetPwdBinding29 = ForgetPwdActivity.this.binding;
                            if (activityForgetPwdBinding29 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityForgetPwdBinding7 = activityForgetPwdBinding29;
                            }
                            activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_220));
                            return;
                        }
                        ActivityForgetPwdBinding activityForgetPwdBinding30 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding30 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityForgetPwdBinding30 = null;
                        }
                        activityForgetPwdBinding30.btnConfirm.setEnabled(true);
                        ActivityForgetPwdBinding activityForgetPwdBinding31 = ForgetPwdActivity.this.binding;
                        if (activityForgetPwdBinding31 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityForgetPwdBinding7 = activityForgetPwdBinding31;
                        }
                        activityForgetPwdBinding7.tvError.setText("");
                        ForgetPwdActivity.this.getViewModel().resetPwd(string3, strReplace, MD5UtilKt.encode(strReplace3));
                        return;
                    }
                    return;
                }
                if (!NetWorkUtils.INSTANCE.isNetworkAvailable(ForgetPwdActivity.this)) {
                    ActivityForgetPwdBinding activityForgetPwdBinding32 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding32 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityForgetPwdBinding7 = activityForgetPwdBinding32;
                    }
                    activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_223));
                    return;
                }
                final ForgetPwdActivity forgetPwdActivity2 = ForgetPwdActivity.this;
                ThreadExtKt.ktxRunOnBgSingle(setOnClickListener, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity.setupViews.2.1
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
                        final ForgetPwdActivity forgetPwdActivity3 = forgetPwdActivity2;
                        ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity.setupViews.2.1.1
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
                                ActivityForgetPwdBinding activityForgetPwdBinding33 = forgetPwdActivity3.binding;
                                if (activityForgetPwdBinding33 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityForgetPwdBinding33 = null;
                                }
                                activityForgetPwdBinding33.tvError.setText(forgetPwdActivity3.getString(R.string.qc_text_223));
                            }
                        });
                    }
                });
                ActivityForgetPwdBinding activityForgetPwdBinding33 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding33 = null;
                }
                Editable text2 = activityForgetPwdBinding33.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "binding.userEmail.text");
                if (!StringsKt.contains$default((CharSequence) text2, (CharSequence) "@", false, 2, (Object) null)) {
                    ActivityForgetPwdBinding activityForgetPwdBinding34 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding34 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityForgetPwdBinding7 = activityForgetPwdBinding34;
                    }
                    activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_219));
                    return;
                }
                ActivityForgetPwdBinding activityForgetPwdBinding35 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding35 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding35 = null;
                }
                activityForgetPwdBinding35.tvError.setText("");
                ActivityForgetPwdBinding activityForgetPwdBinding36 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding36 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding36 = null;
                }
                Editable text3 = activityForgetPwdBinding36.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text3, "binding.userEmail.text");
                Editable editable = text3;
                ActivityForgetPwdBinding activityForgetPwdBinding37 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding37 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding37 = null;
                }
                Editable text4 = activityForgetPwdBinding37.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text4, "binding.userEmail.text");
                String string9 = editable.subSequence(0, StringsKt.indexOf$default((CharSequence) text4, "@", 0, false, 4, (Object) null)).toString();
                if ((string9.length() == 0) || string9.length() < 2) {
                    ActivityForgetPwdBinding activityForgetPwdBinding38 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding38 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityForgetPwdBinding7 = activityForgetPwdBinding38;
                    }
                    activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_219));
                    return;
                }
                ActivityForgetPwdBinding activityForgetPwdBinding39 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding39 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding39 = null;
                }
                activityForgetPwdBinding39.tvError.setText("");
                ActivityForgetPwdBinding activityForgetPwdBinding40 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding40 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding40 = null;
                }
                Editable text5 = activityForgetPwdBinding40.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text5, "binding.userEmail.text");
                Editable editable2 = text5;
                ActivityForgetPwdBinding activityForgetPwdBinding41 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding41 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding41 = null;
                }
                Editable text6 = activityForgetPwdBinding41.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text6, "binding.userEmail.text");
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) text6, "@", 0, false, 4, (Object) null);
                ActivityForgetPwdBinding activityForgetPwdBinding42 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding42 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding42 = null;
                }
                String string10 = editable2.subSequence(iIndexOf$default, activityForgetPwdBinding42.userEmail.getText().length()).toString();
                XLog.i(string10);
                if ((string10.length() == 0) || string10.length() <= 1) {
                    ActivityForgetPwdBinding activityForgetPwdBinding43 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding43 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityForgetPwdBinding7 = activityForgetPwdBinding43;
                    }
                    activityForgetPwdBinding7.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_219));
                    return;
                }
                ActivityForgetPwdBinding activityForgetPwdBinding44 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding44 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding44 = null;
                }
                activityForgetPwdBinding44.tvError.setText("");
                ActivityForgetPwdBinding activityForgetPwdBinding45 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding45 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding45 = null;
                }
                Editable text7 = activityForgetPwdBinding45.userEmail.getText();
                Intrinsics.checkNotNullExpressionValue(text7, "binding.userEmail.text");
                String strReplace4 = new Regex("\\s").replace(text7, "");
                if (strReplace4.length() == 0) {
                    ActivityForgetPwdBinding activityForgetPwdBinding46 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding46 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityForgetPwdBinding46 = null;
                    }
                    activityForgetPwdBinding46.tvError.setText(ForgetPwdActivity.this.getString(R.string.qc_text_162));
                    String string11 = ForgetPwdActivity.this.getString(R.string.qc_text_162);
                    Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.qc_text_162)");
                    GlobalKt.showToast$default(string11, 0, 1, null);
                    ActivityForgetPwdBinding activityForgetPwdBinding47 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding47 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityForgetPwdBinding47 = null;
                    }
                    activityForgetPwdBinding47.sendCode.setClickable(true);
                    ActivityForgetPwdBinding activityForgetPwdBinding48 = ForgetPwdActivity.this.binding;
                    if (activityForgetPwdBinding48 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityForgetPwdBinding7 = activityForgetPwdBinding48;
                    }
                    activityForgetPwdBinding7.sendCode.setText(ForgetPwdActivity.this.getString(R.string.qc_text_174));
                    return;
                }
                ActivityForgetPwdBinding activityForgetPwdBinding49 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding49 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding49 = null;
                }
                activityForgetPwdBinding49.tvError.setText("");
                ActivityForgetPwdBinding activityForgetPwdBinding50 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding50 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityForgetPwdBinding7 = activityForgetPwdBinding50;
                }
                activityForgetPwdBinding7.sendCode.setClickable(false);
                ForgetPwdActivity.this.getViewModel().sendVerifyCode(strReplace4);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ForgetPwdActivity.m950setupViews$lambda6(this.f$0, (ForgetPwdViewModel.ForgetPwdUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5$lambda-0, reason: not valid java name */
    public static final void m948setupViews$lambda5$lambda0(ActivityForgetPwdBinding this_run, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (z) {
            this_run.userPwdNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this_run.userPwdNew.setSelection(this_run.userPwdNew.length());
        } else {
            this_run.userPwdNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this_run.userPwdNew.setSelection(this_run.userPwdNew.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5$lambda-1, reason: not valid java name */
    public static final void m949setupViews$lambda5$lambda1(ActivityForgetPwdBinding this_run, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (z) {
            this_run.userPwdConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this_run.userPwdConfirm.setSelection(this_run.userPwdConfirm.length());
        } else {
            this_run.userPwdConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this_run.userPwdConfirm.setSelection(this_run.userPwdConfirm.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m950setupViews$lambda6(ForgetPwdActivity this$0, ForgetPwdViewModel.ForgetPwdUI forgetPwdUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (forgetPwdUI.isLoading()) {
            this$0.showLoadingDialog();
        } else {
            this$0.dismissLoadingDialog();
        }
        int type = forgetPwdUI.getType();
        if (type == 1) {
            this$0.secondsDown();
            String string = this$0.getString(R.string.qc_text_233);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_233)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (type == 2) {
            String string2 = this$0.getString(R.string.qc_text_235);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_235)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            this$0.finish();
            return;
        }
        if (type == 10002) {
            ActivityForgetPwdBinding activityForgetPwdBinding = this$0.binding;
            if (activityForgetPwdBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityForgetPwdBinding = null;
            }
            activityForgetPwdBinding.sendCode.setClickable(true);
            String string3 = this$0.getString(R.string.qc_text_289);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_289)");
            GlobalKt.showToast$default(string3, 0, 1, null);
            return;
        }
        if (type == 50005) {
            ActivityForgetPwdBinding activityForgetPwdBinding2 = this$0.binding;
            if (activityForgetPwdBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityForgetPwdBinding2 = null;
            }
            activityForgetPwdBinding2.sendCode.setClickable(true);
            String string4 = this$0.getString(R.string.qc_text_273);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_273)");
            GlobalKt.showToast$default(string4, 0, 1, null);
            return;
        }
        if (type == 50012) {
            ActivityForgetPwdBinding activityForgetPwdBinding3 = this$0.binding;
            if (activityForgetPwdBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityForgetPwdBinding3 = null;
            }
            activityForgetPwdBinding3.sendCode.setClickable(true);
            String string5 = this$0.getString(R.string.qc_text_272);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_272)");
            GlobalKt.showToast$default(string5, 0, 1, null);
            return;
        }
        ActivityForgetPwdBinding activityForgetPwdBinding4 = this$0.binding;
        if (activityForgetPwdBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPwdBinding4 = null;
        }
        activityForgetPwdBinding4.sendCode.setClickable(true);
        GlobalKt.showToast$default(this$0.getString(R.string.qc_text_288) + ':' + forgetPwdUI.getType(), 0, 1, null);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity$secondsDown$1] */
    private final void secondsDown() {
        new CountDownTimer(60000L) { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdActivity.secondsDown.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                ActivityForgetPwdBinding activityForgetPwdBinding = ForgetPwdActivity.this.binding;
                ActivityForgetPwdBinding activityForgetPwdBinding2 = null;
                if (activityForgetPwdBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding = null;
                }
                activityForgetPwdBinding.sendCode.setClickable(true);
                ActivityForgetPwdBinding activityForgetPwdBinding3 = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityForgetPwdBinding2 = activityForgetPwdBinding3;
                }
                activityForgetPwdBinding2.sendCode.setText(ForgetPwdActivity.this.getString(R.string.qc_text_174));
            }

            @Override // android.os.CountDownTimer
            public void onTick(long p0) {
                ActivityForgetPwdBinding activityForgetPwdBinding = ForgetPwdActivity.this.binding;
                if (activityForgetPwdBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPwdBinding = null;
                }
                TextView textView = activityForgetPwdBinding.sendCode;
                StringBuilder sb = new StringBuilder();
                sb.append(p0 / 1000);
                sb.append('s');
                textView.setText(sb.toString());
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: emptyInputFilter$lambda-7, reason: not valid java name */
    public static final CharSequence m947emptyInputFilter$lambda7(ForgetPwdActivity this$0, CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!Intrinsics.areEqual(charSequence, " ")) {
            return charSequence;
        }
        ActivityForgetPwdBinding activityForgetPwdBinding = this$0.binding;
        if (activityForgetPwdBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPwdBinding = null;
        }
        activityForgetPwdBinding.tvError.setText(this$0.getString(R.string.qc_text_284));
        return "";
    }
}
