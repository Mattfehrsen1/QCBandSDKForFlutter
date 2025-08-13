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
public final class ActivityForgetPwdBinding implements ViewBinding {
    public final Button btnConfirm;
    public final EditText etVerifyCode;
    public final CheckBox imgPwdConfirm;
    public final CheckBox imgPwdNew;
    private final ConstraintLayout rootView;
    public final TextView sendCode;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvEmailTitle;
    public final TextView tvError;
    public final TextView tvPwdConfirm;
    public final TextView tvPwdNew;
    public final TextView tvVerifyCode;
    public final EditText userEmail;
    public final EditText userPwdConfirm;
    public final EditText userPwdNew;

    private ActivityForgetPwdBinding(ConstraintLayout rootView, Button btnConfirm, EditText etVerifyCode, CheckBox imgPwdConfirm, CheckBox imgPwdNew, TextView sendCode, LayoutTitleBarBinding titleBar, TextView tvEmailTitle, TextView tvError, TextView tvPwdConfirm, TextView tvPwdNew, TextView tvVerifyCode, EditText userEmail, EditText userPwdConfirm, EditText userPwdNew) {
        this.rootView = rootView;
        this.btnConfirm = btnConfirm;
        this.etVerifyCode = etVerifyCode;
        this.imgPwdConfirm = imgPwdConfirm;
        this.imgPwdNew = imgPwdNew;
        this.sendCode = sendCode;
        this.titleBar = titleBar;
        this.tvEmailTitle = tvEmailTitle;
        this.tvError = tvError;
        this.tvPwdConfirm = tvPwdConfirm;
        this.tvPwdNew = tvPwdNew;
        this.tvVerifyCode = tvVerifyCode;
        this.userEmail = userEmail;
        this.userPwdConfirm = userPwdConfirm;
        this.userPwdNew = userPwdNew;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityForgetPwdBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityForgetPwdBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_forget_pwd, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityForgetPwdBinding bind(View rootView) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_confirm);
        if (button != null) {
            i = R.id.et_verify_code;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_verify_code);
            if (editText != null) {
                i = R.id.img_pwd_confirm;
                CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.img_pwd_confirm);
                if (checkBox != null) {
                    i = R.id.img_pwd_new;
                    CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.img_pwd_new);
                    if (checkBox2 != null) {
                        i = R.id.send_code;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.send_code);
                        if (textView != null) {
                            i = R.id.title_bar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                            if (viewFindChildViewById != null) {
                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                i = R.id.tv_email_title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_email_title);
                                if (textView2 != null) {
                                    i = R.id.tv_error;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_error);
                                    if (textView3 != null) {
                                        i = R.id.tv_pwd_confirm;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pwd_confirm);
                                        if (textView4 != null) {
                                            i = R.id.tv_pwd_new;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pwd_new);
                                            if (textView5 != null) {
                                                i = R.id.tv_verify_code;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_verify_code);
                                                if (textView6 != null) {
                                                    i = R.id.user_email;
                                                    EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_email);
                                                    if (editText2 != null) {
                                                        i = R.id.user_pwd_confirm;
                                                        EditText editText3 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_pwd_confirm);
                                                        if (editText3 != null) {
                                                            i = R.id.user_pwd_new;
                                                            EditText editText4 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_pwd_new);
                                                            if (editText4 != null) {
                                                                return new ActivityForgetPwdBinding((ConstraintLayout) rootView, button, editText, checkBox, checkBox2, textView, layoutTitleBarBindingBind, textView2, textView3, textView4, textView5, textView6, editText2, editText3, editText4);
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
