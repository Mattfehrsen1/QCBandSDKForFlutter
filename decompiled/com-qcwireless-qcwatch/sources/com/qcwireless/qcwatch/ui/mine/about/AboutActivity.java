package com.qcwireless.qcwatch.ui.mine.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityAboutBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.request.device.LastOtaRequest;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.qcwireless.qcwatch.ui.mine.privacy.LanguagePPMURLKt;
import com.qcwireless.qcwatch.ui.mine.privacy.WebActivity;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AboutActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\rH\u0014J\b\u0010\u0012\u001a\u00020\rH\u0014J\b\u0010\u0013\u001a\u00020\rH\u0002J\u0006\u0010\u0014\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/about/AboutActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityAboutBinding;", "click_title_times", "", "is_title_clicked", "", "max_click_times", "text", "", "checkOta", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "showCleanAppCacheDialog", "unBindDevice", "AllPermissionCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AboutActivity extends BaseActivity {
    private ActivityAboutBinding binding;
    private int click_title_times;
    private boolean is_title_clicked;
    private final int max_click_times = 5;
    private String text = "1";

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding activityAboutBindingInflate = ActivityAboutBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAboutBindingInflate, "inflate(layoutInflater)");
        this.binding = activityAboutBindingInflate;
        if (activityAboutBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBindingInflate = null;
        }
        ConstraintLayout root = activityAboutBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityAboutBinding activityAboutBinding = this.binding;
        ActivityAboutBinding activityAboutBinding2 = null;
        if (activityAboutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBinding = null;
        }
        activityAboutBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_147));
        activityAboutBinding.tvAppName.setText(GlobalKt.getAppName());
        activityAboutBinding.tvAppVersion.setText(GlobalKt.getVersionName(this));
        ActivityAboutBinding activityAboutBinding3 = this.binding;
        if (activityAboutBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBinding3 = null;
        }
        activityAboutBinding3.qcAppCache.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showCleanAppCacheDialog();
            }
        });
        View[] viewArr = new View[2];
        ActivityAboutBinding activityAboutBinding4 = this.binding;
        if (activityAboutBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBinding4 = null;
        }
        viewArr[0] = activityAboutBinding4.userPrivacy;
        ActivityAboutBinding activityAboutBinding5 = this.binding;
        if (activityAboutBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAboutBinding2 = activityAboutBinding5;
        }
        viewArr[1] = activityAboutBinding2.appIcon;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity.setupViews.2
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
                ActivityAboutBinding activityAboutBinding6 = AboutActivity.this.binding;
                ActivityAboutBinding activityAboutBinding7 = null;
                if (activityAboutBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAboutBinding6 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityAboutBinding6.userPrivacy)) {
                    ActivityAboutBinding activityAboutBinding8 = AboutActivity.this.binding;
                    if (activityAboutBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAboutBinding7 = activityAboutBinding8;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityAboutBinding7.appIcon)) {
                        if (!AboutActivity.this.is_title_clicked) {
                            AboutActivity.this.is_title_clicked = true;
                            AboutActivity.this.click_title_times = 1;
                            Timer timer = new Timer();
                            final AboutActivity aboutActivity = AboutActivity.this;
                            timer.schedule(new TimerTask() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity.setupViews.2.1
                                @Override // java.util.TimerTask, java.lang.Runnable
                                public void run() {
                                    aboutActivity.is_title_clicked = false;
                                    aboutActivity.click_title_times = 0;
                                }
                            }, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
                            return;
                        }
                        AboutActivity.this.click_title_times++;
                        if (AboutActivity.this.click_title_times == AboutActivity.this.max_click_times) {
                            AboutActivity.this.is_title_clicked = false;
                            AboutActivity.this.click_title_times = 0;
                            AboutActivity aboutActivity2 = AboutActivity.this;
                            ArrayList<Pair> arrayList = new ArrayList();
                            Intent intent = new Intent(aboutActivity2, (Class<?>) DebugActivity.class);
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
                            Unit unit2 = Unit.INSTANCE;
                            Unit unit3 = Unit.INSTANCE;
                            Unit unit4 = Unit.INSTANCE;
                            aboutActivity2.startActivity(intent);
                            return;
                        }
                        return;
                    }
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("url", LanguagePPMURLKt.getLanguagePPMUrl());
                Unit unit5 = Unit.INSTANCE;
                AboutActivity aboutActivity3 = AboutActivity.this;
                ArrayList<Pair> arrayList2 = new ArrayList();
                Intent intent2 = new Intent(aboutActivity3, (Class<?>) WebActivity.class);
                intent2.setFlags(1);
                intent2.putExtras(bundle);
                for (Pair pair2 : arrayList2) {
                    if (pair2 != null) {
                        String str2 = (String) pair2.getFirst();
                        Object second2 = pair2.getSecond();
                        if (second2 instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                        } else if (second2 instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                        } else if (second2 instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                        } else if (second2 instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                        } else if (second2 instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                        } else if (second2 instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                        } else {
                            Unit unit6 = Unit.INSTANCE;
                        }
                    }
                }
                Unit unit7 = Unit.INSTANCE;
                Unit unit8 = Unit.INSTANCE;
                Unit unit9 = Unit.INSTANCE;
                aboutActivity3.startActivity(intent2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showCleanAppCacheDialog() {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_app_cache);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = builder.create();
        T t = objectRef.element;
        Intrinsics.checkNotNull(t);
        ((BottomDialog) t).show();
        T t2 = objectRef.element;
        Intrinsics.checkNotNull(t2);
        View contentView = ((BottomDialog) t2).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "cleanCacheDialog!!.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.m882showCleanAppCacheDialog$lambda1(objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.m883showCleanAppCacheDialog$lambda2(objectRef, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showCleanAppCacheDialog$lambda-1, reason: not valid java name */
    public static final void m882showCleanAppCacheDialog$lambda1(Ref.ObjectRef cleanCacheDialog, View view) {
        Intrinsics.checkNotNullParameter(cleanCacheDialog, "$cleanCacheDialog");
        T t = cleanCacheDialog.element;
        Intrinsics.checkNotNull(t);
        ((BottomDialog) t).dismiss();
        cleanCacheDialog.element = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showCleanAppCacheDialog$lambda-2, reason: not valid java name */
    public static final void m883showCleanAppCacheDialog$lambda2(Ref.ObjectRef cleanCacheDialog, final AboutActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(cleanCacheDialog, "$cleanCacheDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        T t = cleanCacheDialog.element;
        Intrinsics.checkNotNull(t);
        ((BottomDialog) t).dismiss();
        cleanCacheDialog.element = null;
        ThreadUtils.postDelay(new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity$showCleanAppCacheDialog$2$1
            @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
            protected void task() {
                UserConfig.INSTANCE.getInstance().setDeviceAddressNoClear("");
                UserConfig.INSTANCE.getInstance().save();
                this.this$0.unBindDevice();
                QJavaApplication.getInstance().clear();
                PreUtil.putInt(PreUtil.Action_Today_Steps, 0);
                BleScannerHelper.getInstance().removeMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
                UserConfig.INSTANCE.getInstance().clearAll();
                FileUtils.INSTANCE.deleteDir(FileUtils.INSTANCE.getAppRootFile(QCApplication.INSTANCE.getCONTEXT()).getAbsolutePath());
                FileUtils.INSTANCE.deleteDir(FileUtils.INSTANCE.getAppCacheRootFile(QCApplication.INSTANCE.getCONTEXT()).getAbsolutePath());
                Glide.get(QCApplication.INSTANCE.getCONTEXT()).clearMemory();
                Glide.get(QCApplication.INSTANCE.getCONTEXT()).clearDiskCache();
            }
        }, 10L);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    /* compiled from: AboutActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/about/AboutActivity$AllPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/mine/about/AboutActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class AllPermissionCallback implements OnPermissionCallback {
        public AllPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (!all) {
                AboutActivity.this.text = "2";
                String string = AboutActivity.this.getString(R.string.qc_text_44);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_44)");
                GlobalKt.showToast(string, 1);
                return;
            }
            AboutActivity.this.text = "666";
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                AboutActivity.this.text = "3";
                XXPermissions.startPermissionActivity((Activity) AboutActivity.this, permissions);
                String string = AboutActivity.this.getString(R.string.qc_text_387);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_387)");
                GlobalKt.showToast(string, 1);
            }
        }
    }

    private final void checkOta() {
        boolean pingHwServer = QCApplication.INSTANCE.getGetInstance().getPingHwServer();
        String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
        String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        String country = Locale.getDefault().getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "getDefault().country");
        LastOtaRequest lastOtaRequest = new LastOtaRequest(1, 1L, hwVersion, fmVersion, 1, deviceAddressNoClear, country, 2);
        ActivityAboutBinding activityAboutBinding = this.binding;
        if (activityAboutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBinding = null;
        }
        activityAboutBinding.tv1.setText(lastOtaRequest + "----" + this.text);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(pingHwServer, null), 3, null);
    }

    /* compiled from: AboutActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.about.AboutActivity$checkOta$1", f = "AboutActivity.kt", i = {0, 2}, l = {205, AudioEq.SW_EQ_DATA_LENGTH, 222, 225}, m = "invokeSuspend", n = {"$this$invokeSuspend_u24lambda_u2d0", "$this$invokeSuspend_u24lambda_u2d0"}, s = {"L$1", "L$1"})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.about.AboutActivity$checkOta$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $netWork;
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$netWork = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AboutActivity.this.new AnonymousClass1(this.$netWork, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0096 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00d4 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L40
                if (r1 == r5) goto L32
                if (r1 == r4) goto L2d
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                goto L2d
            L16:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1e:
                boolean r1 = r10.Z$0
                java.lang.Object r3 = r10.L$1
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r3 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository) r3
                java.lang.Object r4 = r10.L$0
                com.qcwireless.qcwatch.ui.mine.about.AboutActivity r4 = (com.qcwireless.qcwatch.ui.mine.about.AboutActivity) r4
                kotlin.ResultKt.throwOnFailure(r11)
                goto Lbf
            L2d:
                kotlin.ResultKt.throwOnFailure(r11)
                goto Ld5
            L32:
                boolean r1 = r10.Z$0
                java.lang.Object r2 = r10.L$1
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r2 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository) r2
                java.lang.Object r3 = r10.L$0
                com.qcwireless.qcwatch.ui.mine.about.AboutActivity r3 = (com.qcwireless.qcwatch.ui.mine.about.AboutActivity) r3
                kotlin.ResultKt.throwOnFailure(r11)
                goto L81
            L40:
                kotlin.ResultKt.throwOnFailure(r11)
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$Companion r11 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r11 = r11.getGetInstance()
                com.qcwireless.qcwatch.ui.mine.about.AboutActivity r1 = com.qcwireless.qcwatch.ui.mine.about.AboutActivity.this
                boolean r7 = r10.$netWork
                com.qcwireless.qcwatch.QCApplication$Companion r8 = com.qcwireless.qcwatch.QCApplication.INSTANCE
                com.qcwireless.qcwatch.QCApplication r8 = r8.getGetInstance()
                boolean r8 = r8.getPingHwServer()
                if (r8 == 0) goto L97
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r2 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r2 = r2.getInstance()
                java.lang.String r2 = r2.getHwVersion()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r3 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r3 = r3.getInstance()
                java.lang.String r3 = r3.getFmVersion()
                r10.L$0 = r1
                r10.L$1 = r11
                r10.Z$0 = r7
                r10.label = r5
                java.lang.Object r2 = r11.checkOtaFromServer(r2, r3, r10)
                if (r2 != r0) goto L7c
                return r0
            L7c:
                r3 = r1
                r1 = r7
                r9 = r2
                r2 = r11
                r11 = r9
            L81:
                kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
                com.qcwireless.qcwatch.ui.mine.about.AboutActivity$checkOta$1$1$1 r5 = new com.qcwireless.qcwatch.ui.mine.about.AboutActivity$checkOta$1$1$1
                r5.<init>()
                kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
                r10.L$0 = r6
                r10.L$1 = r6
                r10.label = r4
                java.lang.Object r11 = r11.collect(r5, r10)
                if (r11 != r0) goto Ld5
                return r0
            L97:
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r4 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r4 = r4.getInstance()
                java.lang.String r4 = r4.getHwVersion()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r5 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r5 = r5.getInstance()
                java.lang.String r5 = r5.getFmVersion()
                r10.L$0 = r1
                r10.L$1 = r11
                r10.Z$0 = r7
                r10.label = r3
                java.lang.Object r3 = r11.checkOtaFromServerChina(r4, r5, r10)
                if (r3 != r0) goto Lba
                return r0
            Lba:
                r4 = r1
                r1 = r7
                r9 = r3
                r3 = r11
                r11 = r9
            Lbf:
                kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
                com.qcwireless.qcwatch.ui.mine.about.AboutActivity$checkOta$1$1$2 r5 = new com.qcwireless.qcwatch.ui.mine.about.AboutActivity$checkOta$1$1$2
                r5.<init>()
                kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
                r10.L$0 = r6
                r10.L$1 = r6
                r10.label = r2
                java.lang.Object r11 = r11.collect(r5, r10)
                if (r11 != r0) goto Ld5
                return r0
            Ld5:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.mine.about.AboutActivity.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void unBindDevice() {
        BleOperateManager.getInstance().disconnectRtkSPP();
        DeviceManager.getInstance().reSet();
        UserConfig.INSTANCE.getInstance().setGpsLocationTime(0L);
        UserConfig.INSTANCE.getInstance().setLastDeviceAddress(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        UserConfig.INSTANCE.getInstance().setServerMarketSize(0);
        UserConfig.INSTANCE.getInstance().setDeviceName("");
        UserConfig.INSTANCE.getInstance().setDeviceAddress("");
        UserConfig.INSTANCE.getInstance().setHwVersion("");
        UserConfig.INSTANCE.getInstance().setFmVersion("");
        UserConfig.INSTANCE.getInstance().setRtkMcuSupport(false);
        UserConfig.INSTANCE.getInstance().setMusicSupport(false);
        PreUtil.putString(PreUtil.Action_Device_Address, "");
        UserConfig.INSTANCE.getInstance().setMaxWatchFace(6);
        FileHandle.getInstance().endAndRelease();
        BleOperateManager.getInstance().unBindDevice();
        QJavaApplication.getInstance().clear();
        ThreadExtKt.ktxRunOnUiDelay(this, TrackingService.Constant.FASTEST_UPDATE_INTERVAL, new Function1<AboutActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.about.AboutActivity.unBindDevice.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AboutActivity aboutActivity) {
                invoke2(aboutActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AboutActivity ktxRunOnUiDelay) {
                Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                BleScannerHelper.getInstance().removeSystemBle();
                BleScannerHelper.getInstance().removeMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
            }
        });
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new UnbindDeviceEvent());
        new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
    }
}
