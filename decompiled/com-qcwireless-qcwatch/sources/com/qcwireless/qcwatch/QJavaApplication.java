package com.qcwireless.qcwatch;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class QJavaApplication {
    private static QJavaApplication instance;
    private Application application;
    private Map<String, String> plates = new HashMap();
    private List<String> filterPrefix = new ArrayList();
    private Map<String, String> filterPrefixMap = new ConcurrentHashMap();
    private Map<String, Integer> screenMap = new HashMap();
    private String currThemeNam = "";
    private int themeRefresh = 0;
    private String currWallpaperName = "";

    public static QJavaApplication getInstance() {
        QJavaApplication qJavaApplication;
        QJavaApplication qJavaApplication2 = instance;
        if (qJavaApplication2 != null) {
            return qJavaApplication2;
        }
        synchronized (QJavaApplication.class) {
            if (instance == null) {
                instance = new QJavaApplication();
            }
            qJavaApplication = instance;
        }
        return qJavaApplication;
    }

    public String getCurrThemeNam() {
        return this.currThemeNam;
    }

    public void setCurrThemeNam(String currThemeNam) {
        this.currThemeNam = currThemeNam;
    }

    public int getThemeRefresh() {
        return this.themeRefresh;
    }

    public void setThemeRefresh(int themeRefresh) {
        this.themeRefresh = themeRefresh;
    }

    public String getCurrWallpaperName() {
        return this.currWallpaperName;
    }

    public void setCurrWallpaperName(String currWallpaperName) {
        this.currWallpaperName = currWallpaperName;
    }

    public static String getAppLogPath() {
        return getInstance().getApplication().getExternalFilesDir("") + "/log/" + new DateUtil().getY_M_D() + "app_log.txt";
    }

    public String getYMD() {
        return new DateUtil().getY_M_D_H_M_S();
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public static void trySetupNotifyService(Context context) {
        toggleNotificationService(context);
    }

    private static void toggleNotificationService(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.setComponentEnabledSetting(new ComponentName(context, (Class<?>) MyNotificationService.class), 2, 1);
            packageManager.setComponentEnabledSetting(new ComponentName(context, (Class<?>) MyNotificationService.class), 1, 1);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNotificationServiceRunning(Context context) {
        try {
            Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
            while (it.hasNext()) {
                if (MyNotificationService.class.getName().equals(it.next().service.getClassName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void putKeys(String key) {
        this.plates.put(key, key);
    }

    public int localPlates() {
        return this.plates.size();
    }

    public boolean hasKey(String key) {
        return this.plates.get(key) != null;
    }

    public void removeKeys(String key) {
        this.plates.remove(key);
    }

    public void clear() {
        this.plates.clear();
    }

    public void putScanKeys(String key) {
        this.filterPrefix.add(key);
    }

    public void putScanKeysMap(String key) {
        this.filterPrefixMap.put(key, key);
    }

    public Map<String, String> getKeysMap() {
        if (this.filterPrefixMap.size() == 0) {
            String sharedString = PreUtil.getSharedString(PreUtil.Action_Scan_Config, "O_");
            XLog.i(sharedString);
            for (String str : sharedString.split(",")) {
                this.filterPrefixMap.put(str, str);
            }
        }
        return this.filterPrefixMap;
    }

    public List<String> getKeys() {
        if (this.filterPrefix.size() == 0) {
            String sharedString = PreUtil.getSharedString(PreUtil.Action_Scan_Config, "O_");
            XLog.i(sharedString);
            for (String str : sharedString.split(",")) {
                this.filterPrefix.add(str);
            }
        }
        return this.filterPrefix;
    }

    public Map<String, Integer> getScreenMap() {
        return this.screenMap;
    }
}
