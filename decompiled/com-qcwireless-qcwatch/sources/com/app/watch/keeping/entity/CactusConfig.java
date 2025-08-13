package com.app.watch.keeping.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CactusConfig.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\bHÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0014HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0014H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lcom/app/watch/keeping/entity/CactusConfig;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", Constant.CACTUS_NOTIFICATION_CONFIG, "Lcom/app/watch/keeping/entity/NotificationConfig;", "defaultConfig", "Lcom/app/watch/keeping/entity/DefaultConfig;", "(Lcom/app/watch/keeping/entity/NotificationConfig;Lcom/app/watch/keeping/entity/DefaultConfig;)V", "getDefaultConfig", "()Lcom/app/watch/keeping/entity/DefaultConfig;", "getNotificationConfig", "()Lcom/app/watch/keeping/entity/NotificationConfig;", "setNotificationConfig", "(Lcom/app/watch/keeping/entity/NotificationConfig;)V", "component1", "component2", "copy", "describeContents", "", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class CactusConfig implements Parcelable {
    private final DefaultConfig defaultConfig;
    private NotificationConfig notificationConfig;
    public static final Parcelable.Creator<CactusConfig> CREATOR = new Parcelable.Creator<CactusConfig>() { // from class: com.app.watch.keeping.entity.CactusConfig$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CactusConfig createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new CactusConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CactusConfig[] newArray(int size) {
            return new CactusConfig[size];
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    public CactusConfig() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ CactusConfig copy$default(CactusConfig cactusConfig, NotificationConfig notificationConfig, DefaultConfig defaultConfig, int i, Object obj) {
        if ((i & 1) != 0) {
            notificationConfig = cactusConfig.notificationConfig;
        }
        if ((i & 2) != 0) {
            defaultConfig = cactusConfig.defaultConfig;
        }
        return cactusConfig.copy(notificationConfig, defaultConfig);
    }

    /* renamed from: component1, reason: from getter */
    public final NotificationConfig getNotificationConfig() {
        return this.notificationConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public final CactusConfig copy(NotificationConfig notificationConfig, DefaultConfig defaultConfig) {
        Intrinsics.checkNotNullParameter(notificationConfig, "notificationConfig");
        Intrinsics.checkNotNullParameter(defaultConfig, "defaultConfig");
        return new CactusConfig(notificationConfig, defaultConfig);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CactusConfig)) {
            return false;
        }
        CactusConfig cactusConfig = (CactusConfig) other;
        return Intrinsics.areEqual(this.notificationConfig, cactusConfig.notificationConfig) && Intrinsics.areEqual(this.defaultConfig, cactusConfig.defaultConfig);
    }

    public int hashCode() {
        return (this.notificationConfig.hashCode() * 31) + this.defaultConfig.hashCode();
    }

    public String toString() {
        return "CactusConfig(notificationConfig=" + this.notificationConfig + ", defaultConfig=" + this.defaultConfig + ')';
    }

    public CactusConfig(NotificationConfig notificationConfig, DefaultConfig defaultConfig) {
        Intrinsics.checkNotNullParameter(notificationConfig, "notificationConfig");
        Intrinsics.checkNotNullParameter(defaultConfig, "defaultConfig");
        this.notificationConfig = notificationConfig;
        this.defaultConfig = defaultConfig;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ CactusConfig(com.app.watch.keeping.entity.NotificationConfig r20, com.app.watch.keeping.entity.DefaultConfig r21, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
        /*
            r19 = this;
            r0 = r22 & 1
            if (r0 == 0) goto L1f
            com.app.watch.keeping.entity.NotificationConfig r0 = new com.app.watch.keeping.entity.NotificationConfig
            r1 = r0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 32767(0x7fff, float:4.5916E-41)
            r18 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            goto L21
        L1f:
            r0 = r20
        L21:
            r1 = r22 & 2
            if (r1 == 0) goto L39
            com.app.watch.keeping.entity.DefaultConfig r1 = new com.app.watch.keeping.entity.DefaultConfig
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 255(0xff, float:3.57E-43)
            r12 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r2 = r19
            goto L3d
        L39:
            r2 = r19
            r1 = r21
        L3d:
            r2.<init>(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.app.watch.keeping.entity.CactusConfig.<init>(com.app.watch.keeping.entity.NotificationConfig, com.app.watch.keeping.entity.DefaultConfig, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final NotificationConfig getNotificationConfig() {
        return this.notificationConfig;
    }

    public final void setNotificationConfig(NotificationConfig notificationConfig) {
        Intrinsics.checkNotNullParameter(notificationConfig, "<set-?>");
        this.notificationConfig = notificationConfig;
    }

    public final DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CactusConfig(Parcel source) {
        Intrinsics.checkNotNullParameter(source, "source");
        Parcelable parcelable = source.readParcelable(NotificationConfig.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable);
        Parcelable parcelable2 = source.readParcelable(DefaultConfig.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable2);
        this((NotificationConfig) parcelable, (DefaultConfig) parcelable2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeParcelable(this.notificationConfig, 0);
        dest.writeParcelable(this.defaultConfig, 0);
    }
}
