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
public final class ActivityLoginBinding implements ViewBinding {
    public final Button btnLogin;
    public final CheckBox imgPwd;
    public final View line1;
    private final ConstraintLayout rootView;
    public final TextView tvCenter;
    public final TextView tvEmailTitle;
    public final TextView tvError;
    public final TextView tvForgetPws;
    public final TextView tvPwd;
    public final TextView tvRegister;
    public final TextView tvWelcome;
    public final TextView userAgreement;
    public final EditText userEmail;
    public final TextView userPrivacy;
    public final TextView userPrivacyEn;
    public final EditText userPwd;

    private ActivityLoginBinding(ConstraintLayout rootView, Button btnLogin, CheckBox imgPwd, View line1, TextView tvCenter, TextView tvEmailTitle, TextView tvError, TextView tvForgetPws, TextView tvPwd, TextView tvRegister, TextView tvWelcome, TextView userAgreement, EditText userEmail, TextView userPrivacy, TextView userPrivacyEn, EditText userPwd) {
        this.rootView = rootView;
        this.btnLogin = btnLogin;
        this.imgPwd = imgPwd;
        this.line1 = line1;
        this.tvCenter = tvCenter;
        this.tvEmailTitle = tvEmailTitle;
        this.tvError = tvError;
        this.tvForgetPws = tvForgetPws;
        this.tvPwd = tvPwd;
        this.tvRegister = tvRegister;
        this.tvWelcome = tvWelcome;
        this.userAgreement = userAgreement;
        this.userEmail = userEmail;
        this.userPrivacy = userPrivacy;
        this.userPrivacyEn = userPrivacyEn;
        this.userPwd = userPwd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_login, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLoginBinding bind(View rootView) {
        int i = R.id.btn_login;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_login);
        if (button != null) {
            i = R.id.img_pwd;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.img_pwd);
            if (checkBox != null) {
                i = R.id.line_1;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                if (viewFindChildViewById != null) {
                    i = R.id.tv_center;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_center);
                    if (textView != null) {
                        i = R.id.tv_email_title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_email_title);
                        if (textView2 != null) {
                            i = R.id.tv_error;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_error);
                            if (textView3 != null) {
                                i = R.id.tv_forget_pws;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_forget_pws);
                                if (textView4 != null) {
                                    i = R.id.tv_pwd;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pwd);
                                    if (textView5 != null) {
                                        i = R.id.tv_register;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_register);
                                        if (textView6 != null) {
                                            i = R.id.tv_welcome;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_welcome);
                                            if (textView7 != null) {
                                                i = R.id.user_agreement;
                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_agreement);
                                                if (textView8 != null) {
                                                    i = R.id.user_email;
                                                    EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_email);
                                                    if (editText != null) {
                                                        i = R.id.user_privacy;
                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_privacy);
                                                        if (textView9 != null) {
                                                            i = R.id.user_privacy_en;
                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_privacy_en);
                                                            if (textView10 != null) {
                                                                i = R.id.user_pwd;
                                                                EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_pwd);
                                                                if (editText2 != null) {
                                                                    return new ActivityLoginBinding((ConstraintLayout) rootView, button, checkBox, viewFindChildViewById, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, editText, textView9, textView10, editText2);
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
