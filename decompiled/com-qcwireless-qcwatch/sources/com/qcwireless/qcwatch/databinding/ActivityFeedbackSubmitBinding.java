package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityFeedbackSubmitBinding implements ViewBinding {
    public final ImageView addImage;
    public final EditText etContactEmail;
    public final View line1;
    public final View line2;
    public final View line3;
    public final RecyclerView rcvFeedbackPic;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvContact;
    public final TextView tvFeedbackName;
    public final TextView tvImageNumber;
    public final TextView tvMaxLength;
    public final EditText userFeedbackEt;

    private ActivityFeedbackSubmitBinding(ConstraintLayout rootView, ImageView addImage, EditText etContactEmail, View line1, View line2, View line3, RecyclerView rcvFeedbackPic, LayoutTitleBarBinding titleBar, TextView tvContact, TextView tvFeedbackName, TextView tvImageNumber, TextView tvMaxLength, EditText userFeedbackEt) {
        this.rootView = rootView;
        this.addImage = addImage;
        this.etContactEmail = etContactEmail;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.rcvFeedbackPic = rcvFeedbackPic;
        this.titleBar = titleBar;
        this.tvContact = tvContact;
        this.tvFeedbackName = tvFeedbackName;
        this.tvImageNumber = tvImageNumber;
        this.tvMaxLength = tvMaxLength;
        this.userFeedbackEt = userFeedbackEt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFeedbackSubmitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFeedbackSubmitBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_feedback_submit, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFeedbackSubmitBinding bind(View rootView) {
        int i = R.id.add_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.add_image);
        if (imageView != null) {
            i = R.id.et_contact_email;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_contact_email);
            if (editText != null) {
                i = R.id.line_1;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                if (viewFindChildViewById != null) {
                    i = R.id.line_2;
                    View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.line_2);
                    if (viewFindChildViewById2 != null) {
                        i = R.id.line_3;
                        View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.line_3);
                        if (viewFindChildViewById3 != null) {
                            i = R.id.rcv_feedback_pic;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_feedback_pic);
                            if (recyclerView != null) {
                                i = R.id.title_bar;
                                View viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                if (viewFindChildViewById4 != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById4);
                                    i = R.id.tv_contact;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_contact);
                                    if (textView != null) {
                                        i = R.id.tv_feedback_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_feedback_name);
                                        if (textView2 != null) {
                                            i = R.id.tv_image_number;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_image_number);
                                            if (textView3 != null) {
                                                i = R.id.tv_max_length;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_max_length);
                                                if (textView4 != null) {
                                                    i = R.id.user_feedback_et;
                                                    EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_feedback_et);
                                                    if (editText2 != null) {
                                                        return new ActivityFeedbackSubmitBinding((ConstraintLayout) rootView, imageView, editText, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, recyclerView, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, editText2);
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
