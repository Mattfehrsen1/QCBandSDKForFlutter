package com.qcwireless.qcwatch.ui.mine.about;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback;
import com.oudmon.ble.base.bluetooth.spp.bean.MyDeviceInfo;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.DevTestEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityDebugBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DebugActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\bH\u0014J\b\u0010\u000e\u001a\u00020\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/about/DebugActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDebugBinding;", "count", "", "copyFile", "", "initLog", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DebugActivity extends BaseActivity {
    private ActivityDebugBinding binding;
    private int count;

    private final void initLog() {
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDebugBinding activityDebugBindingInflate = ActivityDebugBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDebugBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDebugBindingInflate;
        if (activityDebugBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBindingInflate = null;
        }
        ConstraintLayout root = activityDebugBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        XLog.i(Boolean.valueOf(UserConfig.INSTANCE.getInstance().getDevelopment()));
        ActivityDebugBinding activityDebugBinding = this.binding;
        ActivityDebugBinding activityDebugBinding2 = null;
        if (activityDebugBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding = null;
        }
        activityDebugBinding.qcDebug.setChecked(UserConfig.INSTANCE.getInstance().getIsDebug());
        ActivityDebugBinding activityDebugBinding3 = this.binding;
        if (activityDebugBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding3 = null;
        }
        activityDebugBinding3.qcTest.setChecked(UserConfig.INSTANCE.getInstance().getDevelopment());
        ActivityDebugBinding activityDebugBinding4 = this.binding;
        if (activityDebugBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding4 = null;
        }
        activityDebugBinding4.qcDebug.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DebugActivity.m885setupViews$lambda0(this.f$0, compoundButton, z);
            }
        });
        ActivityDebugBinding activityDebugBinding5 = this.binding;
        if (activityDebugBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding5 = null;
        }
        activityDebugBinding5.btnCopy.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                DebugActivity.m886setupViews$lambda1(this.f$0, view);
            }
        });
        ActivityDebugBinding activityDebugBinding6 = this.binding;
        if (activityDebugBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding6 = null;
        }
        activityDebugBinding6.text3.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DebugActivity.m887setupViews$lambda2(this.f$0, view);
            }
        });
        ActivityDebugBinding activityDebugBinding7 = this.binding;
        if (activityDebugBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding7 = null;
        }
        activityDebugBinding7.text4.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DebugActivity.m888setupViews$lambda3(this.f$0, view);
            }
        });
        ActivityDebugBinding activityDebugBinding8 = this.binding;
        if (activityDebugBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDebugBinding8 = null;
        }
        activityDebugBinding8.qcTest.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DebugActivity.m889setupViews$lambda4(compoundButton, z);
            }
        });
        ActivityDebugBinding activityDebugBinding9 = this.binding;
        if (activityDebugBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDebugBinding2 = activityDebugBinding9;
        }
        activityDebugBinding2.tvShow.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DebugActivity.m890setupViews$lambda5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m885setupViews$lambda0(DebugActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserConfig.INSTANCE.getInstance().setDebug(z);
        UserConfig.INSTANCE.getInstance().save();
        if (z) {
            this$0.initLog();
            LogToFile.getInstance().initPath(QJavaApplication.getInstance().getApplication());
            LogToFile.getInstance().debug = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m886setupViews$lambda1(DebugActivity this$0, View view) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.copyFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m887setupViews$lambda2(DebugActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DebugActivity debugActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(debugActivity, (Class<?>) TestActivity.class);
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
        debugActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m888setupViews$lambda3(DebugActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DebugActivity debugActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(debugActivity, (Class<?>) TestActivity.class);
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
        debugActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m889setupViews$lambda4(CompoundButton compoundButton, boolean z) {
        XLog.i(Boolean.valueOf(z));
        UserConfig.INSTANCE.getInstance().setDevelopment(z);
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new DevTestEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m890setupViews$lambda5(DebugActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.count + 1;
        this$0.count = i;
        if (i >= 5) {
            ActivityDebugBinding activityDebugBinding = this$0.binding;
            if (activityDebugBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDebugBinding = null;
            }
            ViewKt.visible(activityDebugBinding.qcDebug);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BleOperateManager.getInstance().registerSppCallback(new MyBumblebeeCallback() { // from class: com.qcwireless.qcwatch.ui.mine.about.DebugActivity.onResume.1
            @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
            public void onAckReceived(int var1, byte var2) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
            public void onConnectionStateChanged(BluetoothDevice var1, int var2, int var3) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
            public void onDeviceInfoChanged(MyDeviceInfo var1, int var2) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
            public void onEventReported(int var1, byte[] var2) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
            public void onServiceConnectionStateChanged(boolean var1) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
            public void onStateChanged(int var1) {
                XLog.i(Integer.valueOf(var1));
            }
        });
    }

    private final void copyFile() throws Throwable {
        FileUtils.INSTANCE.copy(getApplication().getExternalFilesDir("") + "/log/", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/log/");
    }
}
