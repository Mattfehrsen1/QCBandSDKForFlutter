package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* loaded from: classes3.dex */
public final class FragmentDeviceBinding implements ViewBinding {
    public final TextView appStatus1;
    public final TextView appStatus2;
    public final TextView appStatus3;
    public final TextView appStatus4;
    public final TextView appStatus5;
    public final TextView appStatus6;
    public final Button btnAddDevice;
    public final TextView btnDeviceAdd;
    public final TextView btnFirmwareUpdate;
    public final ConstraintLayout ctlNoDevice;
    public final Group deviceBind;
    public final Group deviceNotBind;
    public final View disconnectView;
    public final View dot1;
    public final View dot2;
    public final ImageView imageDebugBg;
    public final ImageView imageWarming;
    public final ImageView imageWatch;
    public final LinearLayout llSettingLayout;
    public final QSettingItem qcSettingAiPush;
    public final QSettingItem qcSettingAlbum;
    public final QSettingItemWithClickSystem qcSettingBatteryWarming;
    public final QSettingItemWithClickSystem qcSettingCall;
    public final QSettingItem qcSettingCamera;
    public final QSettingItem qcSettingContact;
    public final QSettingItem qcSettingDisturb;
    public final QSettingItem qcSettingEbook;
    public final QSettingItem qcSettingFastSms;
    public final QSettingItem qcSettingFindWatch;
    public final QSettingItem qcSettingForward;
    public final QSettingItemWithClick qcSettingGesture;
    public final QSettingItem qcSettingMap;
    public final QSettingItem qcSettingMessage;
    public final QSettingItem qcSettingMore;
    public final QSettingItem qcSettingMusic;
    public final QSettingItem qcSettingRecord;
    public final QSettingItem qcSettingTime;
    public final QSettingItem qcSettingUnit;
    public final QSettingItem qcSettingWeather;
    private final ConstraintLayout rootView;
    public final SmartRefreshLayout syncDeviceRefresh;
    public final ConstraintLayout topDeviceLayout;
    public final TextView tvDev;
    public final TextView tvDeviceAddress;
    public final TextView tvDeviceBattery;
    public final TextView tvDeviceName;
    public final TextView tvDeviceUnbind;
    public final TextView tvFirmwareVersion;
    public final TextView tvNoDeviceTitle;
    public final TextView tvText1;
    public final TextView tvText2;

    private FragmentDeviceBinding(ConstraintLayout rootView, TextView appStatus1, TextView appStatus2, TextView appStatus3, TextView appStatus4, TextView appStatus5, TextView appStatus6, Button btnAddDevice, TextView btnDeviceAdd, TextView btnFirmwareUpdate, ConstraintLayout ctlNoDevice, Group deviceBind, Group deviceNotBind, View disconnectView, View dot1, View dot2, ImageView imageDebugBg, ImageView imageWarming, ImageView imageWatch, LinearLayout llSettingLayout, QSettingItem qcSettingAiPush, QSettingItem qcSettingAlbum, QSettingItemWithClickSystem qcSettingBatteryWarming, QSettingItemWithClickSystem qcSettingCall, QSettingItem qcSettingCamera, QSettingItem qcSettingContact, QSettingItem qcSettingDisturb, QSettingItem qcSettingEbook, QSettingItem qcSettingFastSms, QSettingItem qcSettingFindWatch, QSettingItem qcSettingForward, QSettingItemWithClick qcSettingGesture, QSettingItem qcSettingMap, QSettingItem qcSettingMessage, QSettingItem qcSettingMore, QSettingItem qcSettingMusic, QSettingItem qcSettingRecord, QSettingItem qcSettingTime, QSettingItem qcSettingUnit, QSettingItem qcSettingWeather, SmartRefreshLayout syncDeviceRefresh, ConstraintLayout topDeviceLayout, TextView tvDev, TextView tvDeviceAddress, TextView tvDeviceBattery, TextView tvDeviceName, TextView tvDeviceUnbind, TextView tvFirmwareVersion, TextView tvNoDeviceTitle, TextView tvText1, TextView tvText2) {
        this.rootView = rootView;
        this.appStatus1 = appStatus1;
        this.appStatus2 = appStatus2;
        this.appStatus3 = appStatus3;
        this.appStatus4 = appStatus4;
        this.appStatus5 = appStatus5;
        this.appStatus6 = appStatus6;
        this.btnAddDevice = btnAddDevice;
        this.btnDeviceAdd = btnDeviceAdd;
        this.btnFirmwareUpdate = btnFirmwareUpdate;
        this.ctlNoDevice = ctlNoDevice;
        this.deviceBind = deviceBind;
        this.deviceNotBind = deviceNotBind;
        this.disconnectView = disconnectView;
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.imageDebugBg = imageDebugBg;
        this.imageWarming = imageWarming;
        this.imageWatch = imageWatch;
        this.llSettingLayout = llSettingLayout;
        this.qcSettingAiPush = qcSettingAiPush;
        this.qcSettingAlbum = qcSettingAlbum;
        this.qcSettingBatteryWarming = qcSettingBatteryWarming;
        this.qcSettingCall = qcSettingCall;
        this.qcSettingCamera = qcSettingCamera;
        this.qcSettingContact = qcSettingContact;
        this.qcSettingDisturb = qcSettingDisturb;
        this.qcSettingEbook = qcSettingEbook;
        this.qcSettingFastSms = qcSettingFastSms;
        this.qcSettingFindWatch = qcSettingFindWatch;
        this.qcSettingForward = qcSettingForward;
        this.qcSettingGesture = qcSettingGesture;
        this.qcSettingMap = qcSettingMap;
        this.qcSettingMessage = qcSettingMessage;
        this.qcSettingMore = qcSettingMore;
        this.qcSettingMusic = qcSettingMusic;
        this.qcSettingRecord = qcSettingRecord;
        this.qcSettingTime = qcSettingTime;
        this.qcSettingUnit = qcSettingUnit;
        this.qcSettingWeather = qcSettingWeather;
        this.syncDeviceRefresh = syncDeviceRefresh;
        this.topDeviceLayout = topDeviceLayout;
        this.tvDev = tvDev;
        this.tvDeviceAddress = tvDeviceAddress;
        this.tvDeviceBattery = tvDeviceBattery;
        this.tvDeviceName = tvDeviceName;
        this.tvDeviceUnbind = tvDeviceUnbind;
        this.tvFirmwareVersion = tvFirmwareVersion;
        this.tvNoDeviceTitle = tvNoDeviceTitle;
        this.tvText1 = tvText1;
        this.tvText2 = tvText2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDeviceBinding bind(View rootView) {
        int i = R.id.app_status_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_status_1);
        if (textView != null) {
            i = R.id.app_status_2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_status_2);
            if (textView2 != null) {
                i = R.id.app_status_3;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_status_3);
                if (textView3 != null) {
                    i = R.id.app_status_4;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_status_4);
                    if (textView4 != null) {
                        i = R.id.app_status_5;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_status_5);
                        if (textView5 != null) {
                            i = R.id.app_status_6;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_status_6);
                            if (textView6 != null) {
                                i = R.id.btn_add_device;
                                Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_add_device);
                                if (button != null) {
                                    i = R.id.btn_device_add;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_device_add);
                                    if (textView7 != null) {
                                        i = R.id.btn_firmware_update;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_firmware_update);
                                        if (textView8 != null) {
                                            i = R.id.ctl_no_device;
                                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_no_device);
                                            if (constraintLayout != null) {
                                                i = R.id.device_bind;
                                                Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.device_bind);
                                                if (group != null) {
                                                    i = R.id.device_not_bind;
                                                    Group group2 = (Group) ViewBindings.findChildViewById(rootView, R.id.device_not_bind);
                                                    if (group2 != null) {
                                                        i = R.id.disconnect_view;
                                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.disconnect_view);
                                                        if (viewFindChildViewById != null) {
                                                            i = R.id.dot_1;
                                                            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.dot_1);
                                                            if (viewFindChildViewById2 != null) {
                                                                i = R.id.dot_2;
                                                                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.dot_2);
                                                                if (viewFindChildViewById3 != null) {
                                                                    i = R.id.image_debug_bg;
                                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_debug_bg);
                                                                    if (imageView != null) {
                                                                        i = R.id.image_warming;
                                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_warming);
                                                                        if (imageView2 != null) {
                                                                            i = R.id.image_watch;
                                                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch);
                                                                            if (imageView3 != null) {
                                                                                i = R.id.ll_setting_layout;
                                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_setting_layout);
                                                                                if (linearLayout != null) {
                                                                                    i = R.id.qc_setting_ai_push;
                                                                                    QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_ai_push);
                                                                                    if (qSettingItem != null) {
                                                                                        i = R.id.qc_setting_album;
                                                                                        QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_album);
                                                                                        if (qSettingItem2 != null) {
                                                                                            i = R.id.qc_setting_battery_warming;
                                                                                            QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_battery_warming);
                                                                                            if (qSettingItemWithClickSystem != null) {
                                                                                                i = R.id.qc_setting_call;
                                                                                                QSettingItemWithClickSystem qSettingItemWithClickSystem2 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_call);
                                                                                                if (qSettingItemWithClickSystem2 != null) {
                                                                                                    i = R.id.qc_setting_camera;
                                                                                                    QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_camera);
                                                                                                    if (qSettingItem3 != null) {
                                                                                                        i = R.id.qc_setting_contact;
                                                                                                        QSettingItem qSettingItem4 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_contact);
                                                                                                        if (qSettingItem4 != null) {
                                                                                                            i = R.id.qc_setting_disturb;
                                                                                                            QSettingItem qSettingItem5 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_disturb);
                                                                                                            if (qSettingItem5 != null) {
                                                                                                                i = R.id.qc_setting_ebook;
                                                                                                                QSettingItem qSettingItem6 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_ebook);
                                                                                                                if (qSettingItem6 != null) {
                                                                                                                    i = R.id.qc_setting_fast_sms;
                                                                                                                    QSettingItem qSettingItem7 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_fast_sms);
                                                                                                                    if (qSettingItem7 != null) {
                                                                                                                        i = R.id.qc_setting_find_watch;
                                                                                                                        QSettingItem qSettingItem8 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_find_watch);
                                                                                                                        if (qSettingItem8 != null) {
                                                                                                                            i = R.id.qc_setting_forward;
                                                                                                                            QSettingItem qSettingItem9 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_forward);
                                                                                                                            if (qSettingItem9 != null) {
                                                                                                                                i = R.id.qc_setting_gesture;
                                                                                                                                QSettingItemWithClick qSettingItemWithClick = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.qc_setting_gesture);
                                                                                                                                if (qSettingItemWithClick != null) {
                                                                                                                                    i = R.id.qc_setting_map;
                                                                                                                                    QSettingItem qSettingItem10 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_map);
                                                                                                                                    if (qSettingItem10 != null) {
                                                                                                                                        i = R.id.qc_setting_message;
                                                                                                                                        QSettingItem qSettingItem11 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_message);
                                                                                                                                        if (qSettingItem11 != null) {
                                                                                                                                            i = R.id.qc_setting_more;
                                                                                                                                            QSettingItem qSettingItem12 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_more);
                                                                                                                                            if (qSettingItem12 != null) {
                                                                                                                                                i = R.id.qc_setting_music;
                                                                                                                                                QSettingItem qSettingItem13 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_music);
                                                                                                                                                if (qSettingItem13 != null) {
                                                                                                                                                    i = R.id.qc_setting_record;
                                                                                                                                                    QSettingItem qSettingItem14 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_record);
                                                                                                                                                    if (qSettingItem14 != null) {
                                                                                                                                                        i = R.id.qc_setting_time;
                                                                                                                                                        QSettingItem qSettingItem15 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_time);
                                                                                                                                                        if (qSettingItem15 != null) {
                                                                                                                                                            i = R.id.qc_setting_unit;
                                                                                                                                                            QSettingItem qSettingItem16 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_unit);
                                                                                                                                                            if (qSettingItem16 != null) {
                                                                                                                                                                i = R.id.qc_setting_weather;
                                                                                                                                                                QSettingItem qSettingItem17 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_weather);
                                                                                                                                                                if (qSettingItem17 != null) {
                                                                                                                                                                    i = R.id.sync_device_refresh;
                                                                                                                                                                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.sync_device_refresh);
                                                                                                                                                                    if (smartRefreshLayout != null) {
                                                                                                                                                                        i = R.id.top_device_layout;
                                                                                                                                                                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.top_device_layout);
                                                                                                                                                                        if (constraintLayout2 != null) {
                                                                                                                                                                            i = R.id.tv_dev;
                                                                                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dev);
                                                                                                                                                                            if (textView9 != null) {
                                                                                                                                                                                i = R.id.tv_device_address;
                                                                                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_device_address);
                                                                                                                                                                                if (textView10 != null) {
                                                                                                                                                                                    i = R.id.tv_device_battery;
                                                                                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_device_battery);
                                                                                                                                                                                    if (textView11 != null) {
                                                                                                                                                                                        i = R.id.tv_device_name;
                                                                                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_device_name);
                                                                                                                                                                                        if (textView12 != null) {
                                                                                                                                                                                            i = R.id.tv_device_unbind;
                                                                                                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_device_unbind);
                                                                                                                                                                                            if (textView13 != null) {
                                                                                                                                                                                                i = R.id.tv_firmware_version;
                                                                                                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_firmware_version);
                                                                                                                                                                                                if (textView14 != null) {
                                                                                                                                                                                                    i = R.id.tv_no_device_title;
                                                                                                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no_device_title);
                                                                                                                                                                                                    if (textView15 != null) {
                                                                                                                                                                                                        i = R.id.tv_text_1;
                                                                                                                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_1);
                                                                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                                                                            i = R.id.tv_text_2;
                                                                                                                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_2);
                                                                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                                                                return new FragmentDeviceBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, button, textView7, textView8, constraintLayout, group, group2, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, imageView, imageView2, imageView3, linearLayout, qSettingItem, qSettingItem2, qSettingItemWithClickSystem, qSettingItemWithClickSystem2, qSettingItem3, qSettingItem4, qSettingItem5, qSettingItem6, qSettingItem7, qSettingItem8, qSettingItem9, qSettingItemWithClick, qSettingItem10, qSettingItem11, qSettingItem12, qSettingItem13, qSettingItem14, qSettingItem15, qSettingItem16, qSettingItem17, smartRefreshLayout, constraintLayout2, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17);
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
