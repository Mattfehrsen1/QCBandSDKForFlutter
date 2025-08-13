package com.app.watch.keeping.entity;

import com.app.watch.keeping.callback.CactusBackgroundCallback;
import com.app.watch.keeping.callback.CactusCallback;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: Constant.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0004j\b\u0012\u0004\u0012\u00020\u0013`\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/app/watch/keeping/entity/Constant;", "", "()V", "BACKGROUND_CALLBACKS", "Ljava/util/ArrayList;", "Lcom/app/watch/keeping/callback/CactusBackgroundCallback;", "Lkotlin/collections/ArrayList;", "getBACKGROUND_CALLBACKS$keeping_release", "()Ljava/util/ArrayList;", "CACTUS_CONFIG", "", "CACTUS_EMOTE_SERVICE", "CACTUS_FLAG_STOP", "CACTUS_JOB_ID", "CACTUS_NOTIFICATION_CONFIG", "CACTUS_PACKAGE", "CACTUS_SERVICE_ID", "CACTUS_TAG", "CALLBACKS", "Lcom/app/watch/keeping/callback/CactusCallback;", "getCALLBACKS$keeping_release", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Constant {
    public static final String CACTUS_CONFIG = "cactusConfig";
    public static final String CACTUS_EMOTE_SERVICE = "JamesRemoteService";
    public static final String CACTUS_FLAG_STOP = "com.app.QWatchPro.flag.stop";
    public static final String CACTUS_JOB_ID = "jobId";
    public static final String CACTUS_NOTIFICATION_CONFIG = "notificationConfig";
    public static final String CACTUS_PACKAGE = "com.app.QWatchPro";
    public static final String CACTUS_SERVICE_ID = "serviceId";
    public static final String CACTUS_TAG = "james";
    public static final Constant INSTANCE = new Constant();
    private static final ArrayList<CactusCallback> CALLBACKS = new ArrayList<>();
    private static final ArrayList<CactusBackgroundCallback> BACKGROUND_CALLBACKS = new ArrayList<>();

    private Constant() {
    }

    public final ArrayList<CactusCallback> getCALLBACKS$keeping_release() {
        return CALLBACKS;
    }

    public final ArrayList<CactusBackgroundCallback> getBACKGROUND_CALLBACKS$keeping_release() {
        return BACKGROUND_CALLBACKS;
    }
}
