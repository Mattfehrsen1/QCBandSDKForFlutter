package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.CircleFrameLayout;
import com.qcwireless.qcwatch.ui.base.view.ColorPickerView;
import com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup;
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;

/* loaded from: classes3.dex */
public final class FragmentWatchFaceDiyBinding implements ViewBinding {
    public final ProgressButton btnDiySave;
    public final ColorPickerView colorPicker;
    public final ConstraintRadioGroup colorRadioGroup;
    public final ConstraintLayout cslDiy;
    public final RecyclerView diyFaceRecycler;
    public final RecyclerView diyNeedleRecycler;
    public final ConstraintRadioGroup diyRadioGroup;
    public final CircleFrameLayout dragLayout;
    public final Group group1;
    public final Group group2;
    public final ImageView imageCamera;
    public final ImageView imageWatchNeedle;
    public final ImageView imageWatchPreview;
    public final TextView ivImageLight;
    public final TextView ivImageShow;
    public final TextView ivNeedleShow;
    public final LinearLayout layoutSeekbar;
    public final RadioButton rbNeedle;
    public final RadioButton rbNumber;
    public final RadioButton rbText1;
    public final RadioButton rbText2;
    public final RadioButton rbText3;
    public final RadioButton rbText4;
    public final RadioButton rbText5;
    public final RadioButton rbText6;
    public final RadioButton rbText7;
    public final RadioButton rbText8;
    private final ConstraintLayout rootView;
    public final SeekBar seekBar;
    public final TextView tvColorTitle;
    public final View viewOut;
    public final View viewOutCamera;

    private FragmentWatchFaceDiyBinding(ConstraintLayout rootView, ProgressButton btnDiySave, ColorPickerView colorPicker, ConstraintRadioGroup colorRadioGroup, ConstraintLayout cslDiy, RecyclerView diyFaceRecycler, RecyclerView diyNeedleRecycler, ConstraintRadioGroup diyRadioGroup, CircleFrameLayout dragLayout, Group group1, Group group2, ImageView imageCamera, ImageView imageWatchNeedle, ImageView imageWatchPreview, TextView ivImageLight, TextView ivImageShow, TextView ivNeedleShow, LinearLayout layoutSeekbar, RadioButton rbNeedle, RadioButton rbNumber, RadioButton rbText1, RadioButton rbText2, RadioButton rbText3, RadioButton rbText4, RadioButton rbText5, RadioButton rbText6, RadioButton rbText7, RadioButton rbText8, SeekBar seekBar, TextView tvColorTitle, View viewOut, View viewOutCamera) {
        this.rootView = rootView;
        this.btnDiySave = btnDiySave;
        this.colorPicker = colorPicker;
        this.colorRadioGroup = colorRadioGroup;
        this.cslDiy = cslDiy;
        this.diyFaceRecycler = diyFaceRecycler;
        this.diyNeedleRecycler = diyNeedleRecycler;
        this.diyRadioGroup = diyRadioGroup;
        this.dragLayout = dragLayout;
        this.group1 = group1;
        this.group2 = group2;
        this.imageCamera = imageCamera;
        this.imageWatchNeedle = imageWatchNeedle;
        this.imageWatchPreview = imageWatchPreview;
        this.ivImageLight = ivImageLight;
        this.ivImageShow = ivImageShow;
        this.ivNeedleShow = ivNeedleShow;
        this.layoutSeekbar = layoutSeekbar;
        this.rbNeedle = rbNeedle;
        this.rbNumber = rbNumber;
        this.rbText1 = rbText1;
        this.rbText2 = rbText2;
        this.rbText3 = rbText3;
        this.rbText4 = rbText4;
        this.rbText5 = rbText5;
        this.rbText6 = rbText6;
        this.rbText7 = rbText7;
        this.rbText8 = rbText8;
        this.seekBar = seekBar;
        this.tvColorTitle = tvColorTitle;
        this.viewOut = viewOut;
        this.viewOutCamera = viewOutCamera;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWatchFaceDiyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentWatchFaceDiyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_watch_face_diy, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWatchFaceDiyBinding bind(View rootView) {
        int i = R.id.btn_diy_save;
        ProgressButton progressButton = (ProgressButton) ViewBindings.findChildViewById(rootView, R.id.btn_diy_save);
        if (progressButton != null) {
            i = R.id.color_picker;
            ColorPickerView colorPickerView = (ColorPickerView) ViewBindings.findChildViewById(rootView, R.id.color_picker);
            if (colorPickerView != null) {
                i = R.id.color_radio_group;
                ConstraintRadioGroup constraintRadioGroup = (ConstraintRadioGroup) ViewBindings.findChildViewById(rootView, R.id.color_radio_group);
                if (constraintRadioGroup != null) {
                    i = R.id.csl_diy;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_diy);
                    if (constraintLayout != null) {
                        i = R.id.diy_face_recycler;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.diy_face_recycler);
                        if (recyclerView != null) {
                            i = R.id.diy_needle_recycler;
                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.diy_needle_recycler);
                            if (recyclerView2 != null) {
                                i = R.id.diy_radio_group;
                                ConstraintRadioGroup constraintRadioGroup2 = (ConstraintRadioGroup) ViewBindings.findChildViewById(rootView, R.id.diy_radio_group);
                                if (constraintRadioGroup2 != null) {
                                    i = R.id.drag_layout;
                                    CircleFrameLayout circleFrameLayout = (CircleFrameLayout) ViewBindings.findChildViewById(rootView, R.id.drag_layout);
                                    if (circleFrameLayout != null) {
                                        i = R.id.group_1;
                                        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.group_1);
                                        if (group != null) {
                                            i = R.id.group_2;
                                            Group group2 = (Group) ViewBindings.findChildViewById(rootView, R.id.group_2);
                                            if (group2 != null) {
                                                i = R.id.image_camera;
                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_camera);
                                                if (imageView != null) {
                                                    i = R.id.image_watch_needle;
                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_needle);
                                                    if (imageView2 != null) {
                                                        i = R.id.image_watch_preview;
                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_preview);
                                                        if (imageView3 != null) {
                                                            i = R.id.iv_image_light;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.iv_image_light);
                                                            if (textView != null) {
                                                                i = R.id.iv_image_show;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.iv_image_show);
                                                                if (textView2 != null) {
                                                                    i = R.id.iv_needle_show;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.iv_needle_show);
                                                                    if (textView3 != null) {
                                                                        i = R.id.layout_seekbar;
                                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_seekbar);
                                                                        if (linearLayout != null) {
                                                                            i = R.id.rb_needle;
                                                                            RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_needle);
                                                                            if (radioButton != null) {
                                                                                i = R.id.rb_number;
                                                                                RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_number);
                                                                                if (radioButton2 != null) {
                                                                                    i = R.id.rb_text1;
                                                                                    RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text1);
                                                                                    if (radioButton3 != null) {
                                                                                        i = R.id.rb_text2;
                                                                                        RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text2);
                                                                                        if (radioButton4 != null) {
                                                                                            i = R.id.rb_text3;
                                                                                            RadioButton radioButton5 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text3);
                                                                                            if (radioButton5 != null) {
                                                                                                i = R.id.rb_text4;
                                                                                                RadioButton radioButton6 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text4);
                                                                                                if (radioButton6 != null) {
                                                                                                    i = R.id.rb_text5;
                                                                                                    RadioButton radioButton7 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text5);
                                                                                                    if (radioButton7 != null) {
                                                                                                        i = R.id.rb_text6;
                                                                                                        RadioButton radioButton8 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text6);
                                                                                                        if (radioButton8 != null) {
                                                                                                            i = R.id.rb_text7;
                                                                                                            RadioButton radioButton9 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text7);
                                                                                                            if (radioButton9 != null) {
                                                                                                                i = R.id.rb_text8;
                                                                                                                RadioButton radioButton10 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text8);
                                                                                                                if (radioButton10 != null) {
                                                                                                                    i = R.id.seek_bar;
                                                                                                                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.seek_bar);
                                                                                                                    if (seekBar != null) {
                                                                                                                        i = R.id.tv_color_title;
                                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_color_title);
                                                                                                                        if (textView4 != null) {
                                                                                                                            i = R.id.view_out;
                                                                                                                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.view_out);
                                                                                                                            if (viewFindChildViewById != null) {
                                                                                                                                i = R.id.view_out_camera;
                                                                                                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.view_out_camera);
                                                                                                                                if (viewFindChildViewById2 != null) {
                                                                                                                                    return new FragmentWatchFaceDiyBinding((ConstraintLayout) rootView, progressButton, colorPickerView, constraintRadioGroup, constraintLayout, recyclerView, recyclerView2, constraintRadioGroup2, circleFrameLayout, group, group2, imageView, imageView2, imageView3, textView, textView2, textView3, linearLayout, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioButton9, radioButton10, seekBar, textView4, viewFindChildViewById, viewFindChildViewById2);
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
