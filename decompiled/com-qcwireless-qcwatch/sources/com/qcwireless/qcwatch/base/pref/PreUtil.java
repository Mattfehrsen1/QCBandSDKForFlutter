package com.qcwireless.qcwatch.base.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class PreUtil {
    public static final String Action_Device_Address = "com.qc.Action_Device_Address";
    public static final String Action_Device_Name = "com.qc.Action_DeviceName";
    public static final String Action_Scan_Config = "com.qc.app_scan_config";
    public static final String Action_Today_Steps = "com.qc.today_steps";
    private static final String PREFERENCE_FILE_NAME = "UserConfig_Preferences_Qc_1.0";
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static int getSharedInt(String file_name, String key, int defaultValue) {
        return getSharedPreferences(file_name).getInt(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return getSharedPreferences(PREFERENCE_FILE_NAME).getInt(key, defaultValue);
    }

    public static boolean getSharedBoolean(String file_name, String key, boolean defaultValue) {
        return getSharedPreferences(file_name).getBoolean(key, defaultValue);
    }

    public static float getSharedFloat(String file_name, String key, float defaultValue) {
        return getSharedPreferences(file_name).getFloat(key, defaultValue);
    }

    public static long getSharedLong(String file_name, String key, long defaultValue) {
        return getSharedPreferences(file_name).getLong(key, defaultValue);
    }

    public static String getSharedString(String file_name, String key, String defaultValue) {
        return getSharedPreferences(file_name).getString(key, defaultValue);
    }

    public static String getSharedString(String key, String defaultValue) {
        return getSharedPreferences(PREFERENCE_FILE_NAME).getString(key, defaultValue);
    }

    public static Set<String> getSharedSet(String file_name, String key, Set<String> defaultValue) {
        return getSharedPreferences(file_name).getStringSet(key, defaultValue);
    }

    public static Map<String, ?> getAll(String file_name) {
        return getSharedPreferences(file_name).getAll();
    }

    public static boolean contain(String file_name, String key) {
        return getSharedPreferences(file_name).contains(key);
    }

    public static void putSharedInt(String file_name, String key, int value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        editorEdit.putInt(key, value);
        editorEdit.apply();
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(PREFERENCE_FILE_NAME).edit();
        editorEdit.putInt(key, value);
        editorEdit.apply();
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(PREFERENCE_FILE_NAME).edit();
        editorEdit.putString(key, value);
        editorEdit.apply();
    }

    public static void putSharedBoolean(String file_name, String key, boolean value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        editorEdit.putBoolean(key, value);
        editorEdit.apply();
    }

    public static void putSharedFloat(String file_name, String key, float value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        editorEdit.putFloat(key, value);
        editorEdit.apply();
    }

    public static void putSharedLong(String file_name, String key, long value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        editorEdit.putLong(key, value);
        editorEdit.apply();
    }

    public static void putSharedString(String file_name, String key, String value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        editorEdit.putString(key, value);
        editorEdit.apply();
    }

    public static void putSharedSet(String file_name, String key, Set<String> value) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        editorEdit.putStringSet(key, value);
        editorEdit.apply();
    }

    public static void putAll(String file_name, ArrayMap<String, Object> arrayMap) {
        SharedPreferences.Editor sharedEditor = getSharedEditor(file_name, mContext);
        for (Map.Entry<String, Object> entry : arrayMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (entry.getValue() instanceof Integer) {
                sharedEditor.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                sharedEditor.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Boolean) {
                sharedEditor.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Float) {
                sharedEditor.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Set) {
                sharedEditor.putStringSet(key, (Set) value);
            } else if (value instanceof String) {
                sharedEditor.putString(key, String.valueOf(value));
            }
        }
        sharedEditor.apply();
    }

    public static void putAll(String file_name, HashMap<String, Object> arrayMap) {
        SharedPreferences.Editor sharedEditor = getSharedEditor(file_name, mContext);
        for (Map.Entry<String, Object> entry : arrayMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (entry.getValue() instanceof Integer) {
                sharedEditor.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                sharedEditor.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Boolean) {
                sharedEditor.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Float) {
                sharedEditor.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Set) {
                sharedEditor.putStringSet(key, (Set) value);
            } else if (value instanceof String) {
                sharedEditor.putString(key, String.valueOf(value));
            }
        }
        sharedEditor.apply();
    }

    public static void clear(String file_name) {
        getSharedPreferences(file_name).edit().clear().apply();
    }

    public static void remove(String file_name, String... keys) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(file_name).edit();
        for (String str : keys) {
            editorEdit.remove(str);
        }
        editorEdit.apply();
    }

    private static SharedPreferences getSharedPreferences(String file_name) {
        Context context = mContext;
        if (context == null) {
            throw new IllegalStateException("Please init PreUtil in Application");
        }
        return context.getSharedPreferences(file_name, 0);
    }

    public static SharedPreferences getSharedPreferences(String name, Context context) {
        return context.getSharedPreferences(name, 0);
    }

    public static SharedPreferences.Editor getSharedEditor(String name, Context context) {
        return context.getSharedPreferences(name, 0).edit();
    }
}
