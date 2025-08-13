package com.qcwireless.qcwatch.ui.mine.guide.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class IntentWrapper {
    protected static final int COOLPAD = 113;
    protected static final int DOZE = 98;
    protected static final int GIONEE = 110;
    protected static final int HUAWEI = 99;
    protected static final int HUAWEI_GOD = 100;
    protected static final int LENOVO = 114;
    protected static final int LENOVO_GOD = 115;
    protected static final int LETV = 111;
    protected static final int LETV_GOD = 112;
    protected static final int MEIZU = 104;
    protected static final int MEIZU_GOD = 105;
    protected static final int OPPO = 106;
    protected static final int OPPO_OLD = 108;
    protected static final int SAMSUNG_Auto = 118;
    protected static final int SAMSUNG_L = 103;
    protected static final int SAMSUNG_M = 107;
    protected static final int VIVO_GOD = 109;
    protected static final int XIAOMI = 101;
    protected static final int XIAOMI_GOD = 102;
    protected static final int ZTE = 116;
    protected static final int ZTE_GOD = 117;
    protected Intent intent;
    protected int type;

    public static List<IntentWrapper> getIntentWrapperList(Context context) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24 && !((PowerManager) context.getSystemService("power")).isIgnoringBatteryOptimizations(context.getPackageName())) {
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            arrayList.add(new IntentWrapper(intent, 98));
        }
        Intent intent2 = new Intent();
        intent2.setAction("huawei.intent.action.HSM_BOOTAPP_MANAGER");
        arrayList.add(new IntentWrapper(intent2, 99));
        Intent intent3 = new Intent();
        intent3.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
        arrayList.add(new IntentWrapper(intent3, 100));
        Intent intent4 = new Intent();
        intent4.setAction("miui.intent.action.OP_AUTO_START");
        intent4.addCategory("android.intent.category.DEFAULT");
        arrayList.add(new IntentWrapper(intent4, 101));
        Intent intent5 = new Intent();
        try {
            intent5.addFlags(268435456);
            intent5.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            arrayList.add(new IntentWrapper(intent5, 101));
        } catch (Exception unused) {
        }
        Intent intent6 = new Intent();
        intent6.setComponent(new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"));
        intent6.putExtra("package_name", context.getPackageName());
        intent6.putExtra("package_label", getApplicationName(context));
        arrayList.add(new IntentWrapper(intent6, 102));
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.samsung.android.sm");
        if (launchIntentForPackage != null) {
            arrayList.add(new IntentWrapper(launchIntentForPackage, 103));
        }
        Intent intent7 = new Intent();
        intent7.setComponent(new ComponentName("com.samsung.android.sm", "com.samsung.android.sm.ui.battery.BatteryActivity"));
        arrayList.add(new IntentWrapper(intent7, 107));
        Intent intent8 = new Intent();
        intent8.setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.scoreboard.ScoreBoardActivity"));
        arrayList.add(new IntentWrapper(intent8, 118));
        Intent intent9 = new Intent("android.intent.action.MAIN");
        intent9.addCategory("android.intent.category.LAUNCHER");
        intent9.setComponent(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.scoreboard.CstyleScoreBoardActivity"));
        arrayList.add(new IntentWrapper(intent9, 118));
        Intent intent10 = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent10.addCategory("android.intent.category.DEFAULT");
        intent10.putExtra("packageName", context.getPackageName());
        arrayList.add(new IntentWrapper(intent10, 104));
        Intent intent11 = new Intent();
        intent11.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity"));
        arrayList.add(new IntentWrapper(intent11, 105));
        Intent intent12 = new Intent();
        intent12.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
        arrayList.add(new IntentWrapper(intent12, 106));
        Intent intent13 = new Intent();
        intent13.setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity"));
        arrayList.add(new IntentWrapper(intent13, 108));
        Intent intent14 = new Intent();
        intent14.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"));
        arrayList.add(new IntentWrapper(intent14, 108));
        Intent intent15 = new Intent();
        intent15.setComponent(new ComponentName("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity"));
        arrayList.add(new IntentWrapper(intent15, 109));
        Intent intent16 = new Intent();
        intent16.setComponent(new ComponentName("com.gionee.softmanager", "com.gionee.softmanager.MainActivity"));
        arrayList.add(new IntentWrapper(intent16, 110));
        Intent intent17 = new Intent();
        intent17.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
        arrayList.add(new IntentWrapper(intent17, 111));
        Intent intent18 = new Intent();
        intent18.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity"));
        arrayList.add(new IntentWrapper(intent18, 112));
        Intent intent19 = new Intent();
        intent19.setComponent(new ComponentName("com.yulong.android.security", "com.yulong.android.seccenter.tabbarmain"));
        arrayList.add(new IntentWrapper(intent19, 113));
        Intent intent20 = new Intent();
        intent20.setComponent(new ComponentName("com.lenovo.security", "com.lenovo.security.purebackground.PureBackgroundActivity"));
        arrayList.add(new IntentWrapper(intent20, 114));
        Intent intent21 = new Intent();
        intent21.setComponent(new ComponentName("com.lenovo.powersetting", "com.lenovo.powersetting.ui.Settings$HighPowerApplicationsActivity"));
        arrayList.add(new IntentWrapper(intent21, 115));
        Intent intent22 = new Intent();
        intent22.setComponent(new ComponentName("com.zte.heartyservice", "com.zte.heartyservice.autorun.AppAutoRunManager"));
        arrayList.add(new IntentWrapper(intent22, 116));
        Intent intent23 = new Intent();
        intent23.setComponent(new ComponentName("com.zte.heartyservice", "com.zte.heartyservice.setting.ClearAppSettingsActivity"));
        arrayList.add(new IntentWrapper(intent23, 117));
        return arrayList;
    }

    public static String getApplicationName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return context.getPackageName();
        }
    }

    public static List<IntentWrapper> whiteListMatters(final Activity a) {
        ArrayList arrayList = new ArrayList();
        for (IntentWrapper intentWrapper : getIntentWrapperList(a)) {
            if (intentWrapper.doesActivityExists(a)) {
                intentWrapper.startActivitySafely(a);
                arrayList.add(intentWrapper);
            }
        }
        return arrayList;
    }

    public static void onBackPressed(Activity a) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        a.startActivity(intent);
    }

    protected IntentWrapper(Intent intent, int type) {
        this.intent = intent;
        this.type = type;
    }

    protected boolean doesActivityExists(Context context) {
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(this.intent, 65536);
        return listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
    }

    protected void startActivitySafely(Activity activityContext) {
        try {
            activityContext.startActivity(this.intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
