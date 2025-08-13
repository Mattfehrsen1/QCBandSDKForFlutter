package com.qcwireless.qcwatch.ui.device.quicksms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.bean.SmsQuickBean;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataSmsQuickResponse;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityQuickSmsBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QuickSmsActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u000eH\u0014J\b\u0010\u0018\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/quicksms/QuickSmsActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityQuickSmsBinding;", "list", "", "Lcom/oudmon/ble/base/communication/bigData/bean/SmsQuickBean;", "text0", "", "text1", "text2", "text3", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QuickSmsActivity extends BaseActivity {
    private ActivityQuickSmsBinding binding;
    private String text0 = "";
    private String text1 = "";
    private String text2 = "";
    private String text3 = "";
    private List<SmsQuickBean> list = new ArrayList();

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQuickSmsBinding activityQuickSmsBindingInflate = ActivityQuickSmsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityQuickSmsBindingInflate, "inflate(layoutInflater)");
        this.binding = activityQuickSmsBindingInflate;
        if (activityQuickSmsBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsBindingInflate = null;
        }
        ConstraintLayout root = activityQuickSmsBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException {
        super.setupViews();
        ActivityQuickSmsBinding activityQuickSmsBinding = this.binding;
        ActivityQuickSmsBinding activityQuickSmsBinding2 = null;
        if (activityQuickSmsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsBinding = null;
        }
        activityQuickSmsBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_606));
        ActivityQuickSmsBinding activityQuickSmsBinding3 = this.binding;
        if (activityQuickSmsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsBinding3 = null;
        }
        activityQuickSmsBinding3.sms1.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity.setupViews.2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                QuickSmsActivity quickSmsActivity = QuickSmsActivity.this;
                bundle.putInt(Constant.MODIFY_ACTIVITY_INTENT_INDEX, 0);
                bundle.putString(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, quickSmsActivity.text0);
                QuickSmsActivity quickSmsActivity2 = QuickSmsActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(quickSmsActivity2, (Class<?>) QuickSmsInputActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
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
                quickSmsActivity2.startActivityForResult(intent, 300);
            }
        });
        ActivityQuickSmsBinding activityQuickSmsBinding4 = this.binding;
        if (activityQuickSmsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsBinding4 = null;
        }
        activityQuickSmsBinding4.sms2.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity.setupViews.3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                QuickSmsActivity quickSmsActivity = QuickSmsActivity.this;
                bundle.putInt(Constant.MODIFY_ACTIVITY_INTENT_INDEX, 1);
                bundle.putString(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, quickSmsActivity.text1);
                QuickSmsActivity quickSmsActivity2 = QuickSmsActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(quickSmsActivity2, (Class<?>) QuickSmsInputActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
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
                quickSmsActivity2.startActivityForResult(intent, 301);
            }
        });
        ActivityQuickSmsBinding activityQuickSmsBinding5 = this.binding;
        if (activityQuickSmsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickSmsBinding5 = null;
        }
        activityQuickSmsBinding5.sms3.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity.setupViews.4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                QuickSmsActivity quickSmsActivity = QuickSmsActivity.this;
                bundle.putInt(Constant.MODIFY_ACTIVITY_INTENT_INDEX, 2);
                bundle.putString(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, quickSmsActivity.text2);
                QuickSmsActivity quickSmsActivity2 = QuickSmsActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(quickSmsActivity2, (Class<?>) QuickSmsInputActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
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
                quickSmsActivity2.startActivityForResult(intent, 302);
            }
        });
        ActivityQuickSmsBinding activityQuickSmsBinding6 = this.binding;
        if (activityQuickSmsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityQuickSmsBinding2 = activityQuickSmsBinding6;
        }
        activityQuickSmsBinding2.sms4.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity.setupViews.5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                QuickSmsActivity quickSmsActivity = QuickSmsActivity.this;
                bundle.putInt(Constant.MODIFY_ACTIVITY_INTENT_INDEX, 3);
                bundle.putString(Constant.MODIFY_ACTIVITY_INTENT_CONTENT, quickSmsActivity.text3);
                QuickSmsActivity quickSmsActivity2 = QuickSmsActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(quickSmsActivity2, (Class<?>) QuickSmsInputActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
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
                quickSmsActivity2.startActivityForResult(intent, 303);
            }
        });
        LargeDataHandler.getInstance().readSmsQuick(new ILargeDataSmsQuickResponse() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataSmsQuickResponse
            public final void smsQuick(List list) {
                QuickSmsActivity.m560setupViews$lambda1(this.f$0, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m560setupViews$lambda1(QuickSmsActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(it);
        UserConfig companion = UserConfig.INSTANCE.getInstance();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        companion.setSmsQuickText(MoshiUtilsKt.toJson(it));
        UserConfig.INSTANCE.getInstance().save();
        Iterator it2 = it.iterator();
        int i = 0;
        while (it2.hasNext()) {
            int i2 = i + 1;
            SmsQuickBean smsQuickBean = (SmsQuickBean) it2.next();
            ActivityQuickSmsBinding activityQuickSmsBinding = null;
            if (i == 0) {
                String text = smsQuickBean.getText();
                Intrinsics.checkNotNullExpressionValue(text, "item.text");
                this$0.text0 = text;
                ActivityQuickSmsBinding activityQuickSmsBinding2 = this$0.binding;
                if (activityQuickSmsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityQuickSmsBinding = activityQuickSmsBinding2;
                }
                activityQuickSmsBinding.sms1.setLeftText(this$0.text0);
            } else if (i == 1) {
                String text2 = smsQuickBean.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "item.text");
                this$0.text1 = text2;
                ActivityQuickSmsBinding activityQuickSmsBinding3 = this$0.binding;
                if (activityQuickSmsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityQuickSmsBinding = activityQuickSmsBinding3;
                }
                activityQuickSmsBinding.sms2.setLeftText(this$0.text1);
            } else if (i == 2) {
                String text3 = smsQuickBean.getText();
                Intrinsics.checkNotNullExpressionValue(text3, "item.text");
                this$0.text2 = text3;
                ActivityQuickSmsBinding activityQuickSmsBinding4 = this$0.binding;
                if (activityQuickSmsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityQuickSmsBinding = activityQuickSmsBinding4;
                }
                activityQuickSmsBinding.sms3.setLeftText(this$0.text2);
            } else if (i == 3) {
                String text4 = smsQuickBean.getText();
                Intrinsics.checkNotNullExpressionValue(text4, "item.text");
                this$0.text3 = text4;
                ActivityQuickSmsBinding activityQuickSmsBinding5 = this$0.binding;
                if (activityQuickSmsBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityQuickSmsBinding = activityQuickSmsBinding5;
                }
                activityQuickSmsBinding.sms4.setLeftText(this$0.text3);
            }
            i = i2;
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() throws IOException {
        super.onResume();
        int i = 0;
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
        }
        String smsQuickText = UserConfig.INSTANCE.getInstance().getSmsQuickText();
        XLog.i(smsQuickText);
        if (smsQuickText.length() > 0) {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<SmsQuickBean>>() { // from class: com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity$onResume$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            Object objFromJson = jsonAdapterAdapter.fromJson(smsQuickText);
            Intrinsics.checkNotNull(objFromJson);
            List<SmsQuickBean> list = (List) objFromJson;
            this.list = list;
            for (SmsQuickBean smsQuickBean : list) {
                int i2 = i + 1;
                if (i == 0) {
                    String text = smsQuickBean.getText();
                    Intrinsics.checkNotNullExpressionValue(text, "item.text");
                    this.text0 = text;
                    ActivityQuickSmsBinding activityQuickSmsBinding = this.binding;
                    if (activityQuickSmsBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityQuickSmsBinding = null;
                    }
                    activityQuickSmsBinding.sms1.setLeftText(this.text0);
                } else if (i == 1) {
                    String text2 = smsQuickBean.getText();
                    Intrinsics.checkNotNullExpressionValue(text2, "item.text");
                    this.text1 = text2;
                    ActivityQuickSmsBinding activityQuickSmsBinding2 = this.binding;
                    if (activityQuickSmsBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityQuickSmsBinding2 = null;
                    }
                    activityQuickSmsBinding2.sms2.setLeftText(this.text1);
                } else if (i == 2) {
                    String text3 = smsQuickBean.getText();
                    Intrinsics.checkNotNullExpressionValue(text3, "item.text");
                    this.text2 = text3;
                    ActivityQuickSmsBinding activityQuickSmsBinding3 = this.binding;
                    if (activityQuickSmsBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityQuickSmsBinding3 = null;
                    }
                    activityQuickSmsBinding3.sms3.setLeftText(this.text2);
                } else if (i == 3) {
                    String text4 = smsQuickBean.getText();
                    Intrinsics.checkNotNullExpressionValue(text4, "item.text");
                    this.text3 = text4;
                    ActivityQuickSmsBinding activityQuickSmsBinding4 = this.binding;
                    if (activityQuickSmsBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityQuickSmsBinding4 = null;
                    }
                    activityQuickSmsBinding4.sms4.setLeftText(this.text3);
                }
                i = i2;
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        XLog.i(Integer.valueOf(resultCode));
    }
}
