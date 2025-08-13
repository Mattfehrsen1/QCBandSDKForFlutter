package com.app.watch.keeping.entity;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultConfig.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000 92\u00020\u0001:\u00019B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BW\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u0006HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u000eHÆ\u0003J[\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\b\u0010-\u001a\u00020.H\u0016J\u0013\u0010/\u001a\u00020\u00062\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020.HÖ\u0001J\t\u00103\u001a\u000204HÖ\u0001J\u0018\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020.H\u0016R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0011\"\u0004\b!\u0010\u0013R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0011\"\u0004\b#\u0010\u0013¨\u0006:"}, d2 = {"Lcom/app/watch/keeping/entity/DefaultConfig;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "debug", "", "musicEnabled", "backgroundMusicEnabled", "onePixEnabled", "workerEnabled", "crashRestartEnabled", "workOnMainThread", "restartIntent", "Landroid/content/Intent;", "(ZZZZZZZLandroid/content/Intent;)V", "getBackgroundMusicEnabled", "()Z", "setBackgroundMusicEnabled", "(Z)V", "getCrashRestartEnabled", "setCrashRestartEnabled", "getDebug", "setDebug", "getMusicEnabled", "setMusicEnabled", "getOnePixEnabled", "setOnePixEnabled", "getRestartIntent", "()Landroid/content/Intent;", "setRestartIntent", "(Landroid/content/Intent;)V", "getWorkOnMainThread", "setWorkOnMainThread", "getWorkerEnabled", "setWorkerEnabled", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class DefaultConfig implements Parcelable {
    private boolean backgroundMusicEnabled;
    private boolean crashRestartEnabled;
    private boolean debug;
    private boolean musicEnabled;
    private boolean onePixEnabled;
    private Intent restartIntent;
    private boolean workOnMainThread;
    private boolean workerEnabled;
    public static final Parcelable.Creator<DefaultConfig> CREATOR = new Parcelable.Creator<DefaultConfig>() { // from class: com.app.watch.keeping.entity.DefaultConfig$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DefaultConfig createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new DefaultConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DefaultConfig[] newArray(int size) {
            return new DefaultConfig[size];
        }
    };

    public DefaultConfig() {
        this(false, false, false, false, false, false, false, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getDebug() {
        return this.debug;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getMusicEnabled() {
        return this.musicEnabled;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getBackgroundMusicEnabled() {
        return this.backgroundMusicEnabled;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getOnePixEnabled() {
        return this.onePixEnabled;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getWorkerEnabled() {
        return this.workerEnabled;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getCrashRestartEnabled() {
        return this.crashRestartEnabled;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getWorkOnMainThread() {
        return this.workOnMainThread;
    }

    /* renamed from: component8, reason: from getter */
    public final Intent getRestartIntent() {
        return this.restartIntent;
    }

    public final DefaultConfig copy(boolean debug, boolean musicEnabled, boolean backgroundMusicEnabled, boolean onePixEnabled, boolean workerEnabled, boolean crashRestartEnabled, boolean workOnMainThread, Intent restartIntent) {
        return new DefaultConfig(debug, musicEnabled, backgroundMusicEnabled, onePixEnabled, workerEnabled, crashRestartEnabled, workOnMainThread, restartIntent);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultConfig)) {
            return false;
        }
        DefaultConfig defaultConfig = (DefaultConfig) other;
        return this.debug == defaultConfig.debug && this.musicEnabled == defaultConfig.musicEnabled && this.backgroundMusicEnabled == defaultConfig.backgroundMusicEnabled && this.onePixEnabled == defaultConfig.onePixEnabled && this.workerEnabled == defaultConfig.workerEnabled && this.crashRestartEnabled == defaultConfig.crashRestartEnabled && this.workOnMainThread == defaultConfig.workOnMainThread && Intrinsics.areEqual(this.restartIntent, defaultConfig.restartIntent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v8, types: [boolean] */
    public int hashCode() {
        boolean z = this.debug;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        ?? r2 = this.musicEnabled;
        int i2 = r2;
        if (r2 != 0) {
            i2 = 1;
        }
        int i3 = (i + i2) * 31;
        ?? r22 = this.backgroundMusicEnabled;
        int i4 = r22;
        if (r22 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        ?? r23 = this.onePixEnabled;
        int i6 = r23;
        if (r23 != 0) {
            i6 = 1;
        }
        int i7 = (i5 + i6) * 31;
        ?? r24 = this.workerEnabled;
        int i8 = r24;
        if (r24 != 0) {
            i8 = 1;
        }
        int i9 = (i7 + i8) * 31;
        ?? r25 = this.crashRestartEnabled;
        int i10 = r25;
        if (r25 != 0) {
            i10 = 1;
        }
        int i11 = (i9 + i10) * 31;
        boolean z2 = this.workOnMainThread;
        int i12 = (i11 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        Intent intent = this.restartIntent;
        return i12 + (intent == null ? 0 : intent.hashCode());
    }

    public String toString() {
        return "DefaultConfig(debug=" + this.debug + ", musicEnabled=" + this.musicEnabled + ", backgroundMusicEnabled=" + this.backgroundMusicEnabled + ", onePixEnabled=" + this.onePixEnabled + ", workerEnabled=" + this.workerEnabled + ", crashRestartEnabled=" + this.crashRestartEnabled + ", workOnMainThread=" + this.workOnMainThread + ", restartIntent=" + this.restartIntent + ')';
    }

    public DefaultConfig(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Intent intent) {
        this.debug = z;
        this.musicEnabled = z2;
        this.backgroundMusicEnabled = z3;
        this.onePixEnabled = z4;
        this.workerEnabled = z5;
        this.crashRestartEnabled = z6;
        this.workOnMainThread = z7;
        this.restartIntent = intent;
    }

    public /* synthetic */ DefaultConfig(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Intent intent, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? true : z4, (i & 16) == 0 ? z5 : true, (i & 32) != 0 ? false : z6, (i & 64) == 0 ? z7 : false, (i & 128) != 0 ? null : intent);
    }

    public final boolean getDebug() {
        return this.debug;
    }

    public final void setDebug(boolean z) {
        this.debug = z;
    }

    public final boolean getMusicEnabled() {
        return this.musicEnabled;
    }

    public final void setMusicEnabled(boolean z) {
        this.musicEnabled = z;
    }

    public final boolean getBackgroundMusicEnabled() {
        return this.backgroundMusicEnabled;
    }

    public final void setBackgroundMusicEnabled(boolean z) {
        this.backgroundMusicEnabled = z;
    }

    public final boolean getOnePixEnabled() {
        return this.onePixEnabled;
    }

    public final void setOnePixEnabled(boolean z) {
        this.onePixEnabled = z;
    }

    public final boolean getWorkerEnabled() {
        return this.workerEnabled;
    }

    public final void setWorkerEnabled(boolean z) {
        this.workerEnabled = z;
    }

    public final boolean getCrashRestartEnabled() {
        return this.crashRestartEnabled;
    }

    public final void setCrashRestartEnabled(boolean z) {
        this.crashRestartEnabled = z;
    }

    public final boolean getWorkOnMainThread() {
        return this.workOnMainThread;
    }

    public final void setWorkOnMainThread(boolean z) {
        this.workOnMainThread = z;
    }

    public final Intent getRestartIntent() {
        return this.restartIntent;
    }

    public final void setRestartIntent(Intent intent) {
        this.restartIntent = intent;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultConfig(Parcel source) {
        this(1 == source.readInt(), 1 == source.readInt(), 1 == source.readInt(), 1 == source.readInt(), 1 == source.readInt(), 1 == source.readInt(), 1 == source.readInt(), (Intent) source.readParcelable(Intent.class.getClassLoader()));
        Intrinsics.checkNotNullParameter(source, "source");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.debug ? 1 : 0);
        dest.writeInt(this.musicEnabled ? 1 : 0);
        dest.writeInt(this.backgroundMusicEnabled ? 1 : 0);
        dest.writeInt(this.onePixEnabled ? 1 : 0);
        dest.writeInt(this.workerEnabled ? 1 : 0);
        dest.writeInt(this.crashRestartEnabled ? 1 : 0);
        dest.writeInt(this.workOnMainThread ? 1 : 0);
        dest.writeParcelable(this.restartIntent, 0);
    }
}
