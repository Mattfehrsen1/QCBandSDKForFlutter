package com.app.watch.keeping.ext;

import android.content.Context;
import android.content.SharedPreferences;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.entity.Constant;
import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConfigExt.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\u000e\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0004*\u00020\u0002H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\n\u001a\u00020\b*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¨\u0006\f"}, d2 = {"getConfig", "Lcom/app/watch/keeping/entity/CactusConfig;", "Landroid/content/Context;", "getJobId", "", "getPreviousConfig", "getServiceId", "saveConfig", "", Constant.CACTUS_CONFIG, "saveJobId", Constant.CACTUS_JOB_ID, "keeping_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ConfigExtKt {
    public static final void saveConfig(Context context, CactusConfig cactusConfig) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        CactusExtKt.setSCactusConfig(cactusConfig);
        int serviceId = getServiceId(context);
        if (serviceId > 0) {
            cactusConfig.getNotificationConfig().setServiceId(serviceId);
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constant.CACTUS_TAG, 0).edit();
        editorEdit.putString(Constant.CACTUS_CONFIG, new Gson().toJson(cactusConfig));
        if (serviceId <= 0) {
            editorEdit.putInt(Constant.CACTUS_SERVICE_ID, cactusConfig.getNotificationConfig().getServiceId());
        }
        editorEdit.apply();
    }

    public static final CactusConfig getConfig(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        CactusConfig sCactusConfig = CactusExtKt.getSCactusConfig();
        if (sCactusConfig != null) {
            return sCactusConfig;
        }
        CactusConfig previousConfig = getPreviousConfig(context);
        return previousConfig == null ? new CactusConfig(null, null, 3, null) : previousConfig;
    }

    public static final CactusConfig getPreviousConfig(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        String string = context.getSharedPreferences(Constant.CACTUS_TAG, 0).getString(Constant.CACTUS_CONFIG, null);
        if (string != null) {
            return (CactusConfig) new Gson().fromJson(string, CactusConfig.class);
        }
        return null;
    }

    public static final void saveJobId(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        context.getSharedPreferences(Constant.CACTUS_TAG, 0).edit().putInt(Constant.CACTUS_JOB_ID, i).apply();
    }

    public static final int getJobId(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getSharedPreferences(Constant.CACTUS_TAG, 0).getInt(Constant.CACTUS_JOB_ID, -1);
    }

    private static final int getServiceId(Context context) {
        return context.getSharedPreferences(Constant.CACTUS_TAG, 0).getInt(Constant.CACTUS_SERVICE_ID, -1);
    }
}
