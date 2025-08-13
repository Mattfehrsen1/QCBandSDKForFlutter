package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityRegisterBinding implements ViewBinding {
    public final Button btnRegister;
    public final CheckBox imgPwd;
    public final CheckBox imgPwdEye;
    public final View line1;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvCenter;
    public final TextView tvEmailTitle;
    public final TextView tvError;
    public final TextView tvPwd;
    public final TextView tvPwdAgain;
    public final TextView tvWelcome;
    public final TextView userAgreement;
    public final EditText userEmail;
    public final TextView userPrivacy;
    public final EditText userPwd;
    public final EditText userPwdAgain;

    private ActivityRegisterBinding(ConstraintLayout rootView, Button btnRegister, CheckBox imgPwd, CheckBox imgPwdEye, View line1, LayoutTitleBarBinding titleBar, TextView tvCenter, TextView tvEmailTitle, TextView tvError, TextView tvPwd, TextView tvPwdAgain, TextView tvWelcome, TextView userAgreement, EditText userEmail, TextView userPrivacy, EditText userPwd, EditText userPwdAgain) {
        this.rootView = rootView;
        this.btnRegister = btnRegister;
        this.imgPwd = imgPwd;
        this.imgPwdEye = imgPwdEye;
        this.line1 = line1;
        this.titleBar = titleBar;
        this.tvCenter = tvCenter;
        this.tvEmailTitle = tvEmailTitle;
        this.tvError = tvError;
        this.tvPwd = tvPwd;
        this.tvPwdAgain = tvPwdAgain;
        this.tvWelcome = tvWelcome;
        this.userAgreement = userAgreement;
        this.userEmail = userEmail;
        this.userPrivacy = userPrivacy;
        this.userPwd = userPwd;
        this.userPwdAgain = userPwdAgain;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRegisterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRegisterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_register, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRegisterBinding bind(View rootView) {
        int i = R.id.btn_register;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_register);
        if (button != null) {
            i = R.id.img_pwd;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.img_pwd);
            if (checkBox != null) {
                i = R.id.img_pwd_eye;
                CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.img_pwd_eye);
                if (checkBox2 != null) {
                    i = R.id.line_1;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                    if (viewFindChildViewById != null) {
                        i = R.id.title_bar;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                        if (viewFindChildViewById2 != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                            i = R.id.tv_center;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_center);
                            if (textView != null) {
                                i = R.id.tv_email_title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_email_title);
                                if (textView2 != null) {
                                    i = R.id.tv_error;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_error);
                                    if (textView3 != null) {
                                        i = R.id.tv_pwd;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pwd);
                                        if (textView4 != null) {
                                            i = R.id.tv_pwd_again;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pwd_again);
                                            if (textView5 != null) {
                                                i = R.id.tv_welcome;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_welcome);
                                                if (textView6 != null) {
                                                    i = R.id.user_agreement;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_agreement);
                                                    if (textView7 != null) {
                                                        i = R.id.user_email;
                                                        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_email);
                                                        if (editText != null) {
                                                            i = R.id.user_privacy;
                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_privacy);
                                                            if (textView8 != null) {
                                                                i = R.id.user_pwd;
                                                                EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_pwd);
                                                                if (editText2 != null) {
                                                                    i = R.id.user_pwd_again;
                                                                    EditText editText3 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_pwd_again);
                                                                    if (editText3 != null) {
                                                                        return new ActivityRegisterBinding((ConstraintLayout) rootView, button, checkBox, checkBox2, viewFindChildViewById, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, editText, textView8, editText2, editText3);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
