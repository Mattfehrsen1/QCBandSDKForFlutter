package com.app.watch.keeping.entity;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: NotificationConfig.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bE\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000 f2\u00020\u0001:\u0001fB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B§\u0001\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001bJ\u000e\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020\u0000J\t\u0010L\u001a\u00020\u0006HÆ\u0003J\t\u0010M\u001a\u00020\u0011HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010S\u001a\u00020\bHÆ\u0003J\t\u0010T\u001a\u00020\bHÆ\u0003J\t\u0010U\u001a\u00020\bHÆ\u0003J\t\u0010V\u001a\u00020\bHÆ\u0003J\t\u0010W\u001a\u00020\u0006HÆ\u0003J\t\u0010X\u001a\u00020\u0006HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\t\u0010Z\u001a\u00020\u0011HÆ\u0003J«\u0001\u0010[\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\b\u0010\\\u001a\u00020\u0006H\u0016J\u0013\u0010]\u001a\u00020\u00112\b\u0010^\u001a\u0004\u0018\u00010_HÖ\u0003J\t\u0010`\u001a\u00020\u0006HÖ\u0001J\t\u0010a\u001a\u00020\bHÖ\u0001J\u0018\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020\u00032\u0006\u0010e\u001a\u00020\u0006H\u0016R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\u0012\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001d\"\u0004\bC\u0010\u001fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010/\"\u0004\bE\u00101R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010/\"\u0004\bG\u00101R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010!\"\u0004\bI\u0010#¨\u0006g"}, d2 = {"Lcom/app/watch/keeping/entity/NotificationConfig;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", Constant.CACTUS_SERVICE_ID, "", "channelId", "", "channelName", "title", com.qcwireless.qcwatch.ui.mine.chat.Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "smallIcon", "largeIcon", "largeIconBitmap", "Landroid/graphics/Bitmap;", "hideNotificationAfterO", "", "hideNotification", "remoteViews", "Landroid/widget/RemoteViews;", "bigRemoteViews", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "notification", "Landroid/app/Notification;", "notificationChannel", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILandroid/graphics/Bitmap;ZZLandroid/widget/RemoteViews;Landroid/widget/RemoteViews;Landroid/app/PendingIntent;Landroid/app/Notification;Landroid/os/Parcelable;)V", "getBigRemoteViews", "()Landroid/widget/RemoteViews;", "setBigRemoteViews", "(Landroid/widget/RemoteViews;)V", "getChannelId", "()Ljava/lang/String;", "setChannelId", "(Ljava/lang/String;)V", "getChannelName", "setChannelName", "getContent", "setContent", "getHideNotification", "()Z", "setHideNotification", "(Z)V", "getHideNotificationAfterO", "setHideNotificationAfterO", "getLargeIcon", "()I", "setLargeIcon", "(I)V", "getLargeIconBitmap", "()Landroid/graphics/Bitmap;", "setLargeIconBitmap", "(Landroid/graphics/Bitmap;)V", "getNotification", "()Landroid/app/Notification;", "setNotification", "(Landroid/app/Notification;)V", "getNotificationChannel", "()Landroid/os/Parcelable;", "setNotificationChannel", "(Landroid/os/Parcelable;)V", "getPendingIntent", "()Landroid/app/PendingIntent;", "setPendingIntent", "(Landroid/app/PendingIntent;)V", "getRemoteViews", "setRemoteViews", "getServiceId", "setServiceId", "getSmallIcon", "setSmallIcon", "getTitle", "setTitle", "canUpdate", Constant.CACTUS_NOTIFICATION_CONFIG, "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class NotificationConfig implements Parcelable {
    private RemoteViews bigRemoteViews;
    private String channelId;
    private String channelName;
    private String content;
    private boolean hideNotification;
    private boolean hideNotificationAfterO;
    private int largeIcon;
    private Bitmap largeIconBitmap;
    private transient Notification notification;
    private transient Parcelable notificationChannel;
    private transient PendingIntent pendingIntent;
    private RemoteViews remoteViews;
    private int serviceId;
    private int smallIcon;
    private String title;
    public static final Parcelable.Creator<NotificationConfig> CREATOR = new Parcelable.Creator<NotificationConfig>() { // from class: com.app.watch.keeping.entity.NotificationConfig$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationConfig createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new NotificationConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationConfig[] newArray(int size) {
            return new NotificationConfig[size];
        }
    };

    public NotificationConfig() {
        this(0, null, null, null, null, 0, 0, null, false, false, null, null, null, null, null, 32767, null);
    }

    /* renamed from: component1, reason: from getter */
    public final int getServiceId() {
        return this.serviceId;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getHideNotification() {
        return this.hideNotification;
    }

    /* renamed from: component11, reason: from getter */
    public final RemoteViews getRemoteViews() {
        return this.remoteViews;
    }

    /* renamed from: component12, reason: from getter */
    public final RemoteViews getBigRemoteViews() {
        return this.bigRemoteViews;
    }

    /* renamed from: component13, reason: from getter */
    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    /* renamed from: component14, reason: from getter */
    public final Notification getNotification() {
        return this.notification;
    }

    /* renamed from: component15, reason: from getter */
    public final Parcelable getNotificationChannel() {
        return this.notificationChannel;
    }

    /* renamed from: component2, reason: from getter */
    public final String getChannelId() {
        return this.channelId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getChannelName() {
        return this.channelName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component5, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component6, reason: from getter */
    public final int getSmallIcon() {
        return this.smallIcon;
    }

    /* renamed from: component7, reason: from getter */
    public final int getLargeIcon() {
        return this.largeIcon;
    }

    /* renamed from: component8, reason: from getter */
    public final Bitmap getLargeIconBitmap() {
        return this.largeIconBitmap;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getHideNotificationAfterO() {
        return this.hideNotificationAfterO;
    }

    public final NotificationConfig copy(int serviceId, String channelId, String channelName, String title, String content, int smallIcon, int largeIcon, Bitmap largeIconBitmap, boolean hideNotificationAfterO, boolean hideNotification, RemoteViews remoteViews, RemoteViews bigRemoteViews, PendingIntent pendingIntent, Notification notification, Parcelable notificationChannel) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(content, "content");
        return new NotificationConfig(serviceId, channelId, channelName, title, content, smallIcon, largeIcon, largeIconBitmap, hideNotificationAfterO, hideNotification, remoteViews, bigRemoteViews, pendingIntent, notification, notificationChannel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationConfig)) {
            return false;
        }
        NotificationConfig notificationConfig = (NotificationConfig) other;
        return this.serviceId == notificationConfig.serviceId && Intrinsics.areEqual(this.channelId, notificationConfig.channelId) && Intrinsics.areEqual(this.channelName, notificationConfig.channelName) && Intrinsics.areEqual(this.title, notificationConfig.title) && Intrinsics.areEqual(this.content, notificationConfig.content) && this.smallIcon == notificationConfig.smallIcon && this.largeIcon == notificationConfig.largeIcon && Intrinsics.areEqual(this.largeIconBitmap, notificationConfig.largeIconBitmap) && this.hideNotificationAfterO == notificationConfig.hideNotificationAfterO && this.hideNotification == notificationConfig.hideNotification && Intrinsics.areEqual(this.remoteViews, notificationConfig.remoteViews) && Intrinsics.areEqual(this.bigRemoteViews, notificationConfig.bigRemoteViews) && Intrinsics.areEqual(this.pendingIntent, notificationConfig.pendingIntent) && Intrinsics.areEqual(this.notification, notificationConfig.notification) && Intrinsics.areEqual(this.notificationChannel, notificationConfig.notificationChannel);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((((this.serviceId * 31) + this.channelId.hashCode()) * 31) + this.channelName.hashCode()) * 31) + this.title.hashCode()) * 31) + this.content.hashCode()) * 31) + this.smallIcon) * 31) + this.largeIcon) * 31;
        Bitmap bitmap = this.largeIconBitmap;
        int iHashCode2 = (iHashCode + (bitmap == null ? 0 : bitmap.hashCode())) * 31;
        boolean z = this.hideNotificationAfterO;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode2 + i) * 31;
        boolean z2 = this.hideNotification;
        int i3 = (i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        RemoteViews remoteViews = this.remoteViews;
        int iHashCode3 = (i3 + (remoteViews == null ? 0 : remoteViews.hashCode())) * 31;
        RemoteViews remoteViews2 = this.bigRemoteViews;
        int iHashCode4 = (iHashCode3 + (remoteViews2 == null ? 0 : remoteViews2.hashCode())) * 31;
        PendingIntent pendingIntent = this.pendingIntent;
        int iHashCode5 = (iHashCode4 + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
        Notification notification = this.notification;
        int iHashCode6 = (iHashCode5 + (notification == null ? 0 : notification.hashCode())) * 31;
        Parcelable parcelable = this.notificationChannel;
        return iHashCode6 + (parcelable != null ? parcelable.hashCode() : 0);
    }

    public String toString() {
        return "NotificationConfig(serviceId=" + this.serviceId + ", channelId=" + this.channelId + ", channelName=" + this.channelName + ", title=" + this.title + ", content=" + this.content + ", smallIcon=" + this.smallIcon + ", largeIcon=" + this.largeIcon + ", largeIconBitmap=" + this.largeIconBitmap + ", hideNotificationAfterO=" + this.hideNotificationAfterO + ", hideNotification=" + this.hideNotification + ", remoteViews=" + this.remoteViews + ", bigRemoteViews=" + this.bigRemoteViews + ", pendingIntent=" + this.pendingIntent + ", notification=" + this.notification + ", notificationChannel=" + this.notificationChannel + ')';
    }

    public NotificationConfig(int i, String channelId, String channelName, String title, String content, int i2, int i3, Bitmap bitmap, boolean z, boolean z2, RemoteViews remoteViews, RemoteViews remoteViews2, PendingIntent pendingIntent, Notification notification, Parcelable parcelable) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(content, "content");
        this.serviceId = i;
        this.channelId = channelId;
        this.channelName = channelName;
        this.title = title;
        this.content = content;
        this.smallIcon = i2;
        this.largeIcon = i3;
        this.largeIconBitmap = bitmap;
        this.hideNotificationAfterO = z;
        this.hideNotification = z2;
        this.remoteViews = remoteViews;
        this.bigRemoteViews = remoteViews2;
        this.pendingIntent = pendingIntent;
        this.notification = notification;
        this.notificationChannel = parcelable;
    }

    public /* synthetic */ NotificationConfig(int i, String str, String str2, String str3, String str4, int i2, int i3, Bitmap bitmap, boolean z, boolean z2, RemoteViews remoteViews, RemoteViews remoteViews2, PendingIntent pendingIntent, Notification notification, Parcelable parcelable, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? RangesKt.random(new IntRange(1, Integer.MAX_VALUE), Random.INSTANCE) : i, (i4 & 2) != 0 ? "Cactus" : str, (i4 & 4) != 0 ? "Cactus" : str2, (i4 & 8) == 0 ? str3 : "Cactus", (i4 & 16) != 0 ? "Cactus is running" : str4, (i4 & 32) != 0 ? 0 : i2, (i4 & 64) != 0 ? 0 : i3, (i4 & 128) != 0 ? null : bitmap, (i4 & 256) != 0 ? false : z, (i4 & 512) == 0 ? z2 : false, (i4 & 1024) != 0 ? null : remoteViews, (i4 & 2048) != 0 ? null : remoteViews2, (i4 & 4096) != 0 ? null : pendingIntent, (i4 & 8192) != 0 ? null : notification, (i4 & 16384) == 0 ? parcelable : null);
    }

    public final int getServiceId() {
        return this.serviceId;
    }

    public final void setServiceId(int i) {
        this.serviceId = i;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    public final void setChannelId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.channelId = str;
    }

    public final String getChannelName() {
        return this.channelName;
    }

    public final void setChannelName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.channelName = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content = str;
    }

    public final int getSmallIcon() {
        return this.smallIcon;
    }

    public final void setSmallIcon(int i) {
        this.smallIcon = i;
    }

    public final int getLargeIcon() {
        return this.largeIcon;
    }

    public final void setLargeIcon(int i) {
        this.largeIcon = i;
    }

    public final Bitmap getLargeIconBitmap() {
        return this.largeIconBitmap;
    }

    public final void setLargeIconBitmap(Bitmap bitmap) {
        this.largeIconBitmap = bitmap;
    }

    public final boolean getHideNotificationAfterO() {
        return this.hideNotificationAfterO;
    }

    public final void setHideNotificationAfterO(boolean z) {
        this.hideNotificationAfterO = z;
    }

    public final boolean getHideNotification() {
        return this.hideNotification;
    }

    public final void setHideNotification(boolean z) {
        this.hideNotification = z;
    }

    public final RemoteViews getRemoteViews() {
        return this.remoteViews;
    }

    public final void setRemoteViews(RemoteViews remoteViews) {
        this.remoteViews = remoteViews;
    }

    public final RemoteViews getBigRemoteViews() {
        return this.bigRemoteViews;
    }

    public final void setBigRemoteViews(RemoteViews remoteViews) {
        this.bigRemoteViews = remoteViews;
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public final Notification getNotification() {
        return this.notification;
    }

    public final void setNotification(Notification notification) {
        this.notification = notification;
    }

    public final Parcelable getNotificationChannel() {
        return this.notificationChannel;
    }

    public final void setNotificationChannel(Parcelable parcelable) {
        this.notificationChannel = parcelable;
    }

    public final boolean canUpdate(NotificationConfig notificationConfig) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(notificationConfig, "notificationConfig");
        boolean z3 = this.serviceId == notificationConfig.serviceId && Intrinsics.areEqual(this.channelId, notificationConfig.channelId) && Intrinsics.areEqual(this.channelName, notificationConfig.channelName) && (z = this.hideNotification) == notificationConfig.hideNotification && (z2 = this.hideNotificationAfterO) == notificationConfig.hideNotificationAfterO && !z && !z2;
        if (Build.VERSION.SDK_INT < 26) {
            return z3;
        }
        if (z3 && Intrinsics.areEqual(this.notificationChannel, notificationConfig.notificationChannel)) {
            Notification notification = this.notification;
            String channelId = notification != null ? notification.getChannelId() : null;
            Notification notification2 = notificationConfig.notification;
            if (Intrinsics.areEqual(channelId, notification2 != null ? notification2.getChannelId() : null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NotificationConfig(Parcel source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int i = source.readInt();
        String string = source.readString();
        String str = string == null ? "Cactus" : string;
        String string2 = source.readString();
        String str2 = string2 == null ? "Cactus" : string2;
        String string3 = source.readString();
        String str3 = string3 == null ? "Cactus" : string3;
        String string4 = source.readString();
        this(i, str, str2, str3, string4 == null ? "The app of cactus is running" : string4, source.readInt(), source.readInt(), (Bitmap) source.readParcelable(Bitmap.class.getClassLoader()), 1 == source.readInt(), 1 == source.readInt(), (RemoteViews) source.readParcelable(RemoteViews.class.getClassLoader()), (RemoteViews) source.readParcelable(RemoteViews.class.getClassLoader()), (PendingIntent) source.readParcelable(PendingIntent.class.getClassLoader()), (Notification) source.readParcelable(Notification.class.getClassLoader()), source.readParcelable(Parcelable.class.getClassLoader()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.serviceId);
        dest.writeString(this.channelId);
        dest.writeString(this.channelName);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeInt(this.smallIcon);
        dest.writeInt(this.largeIcon);
        dest.writeParcelable(this.largeIconBitmap, 0);
        dest.writeInt(this.hideNotificationAfterO ? 1 : 0);
        dest.writeInt(this.hideNotification ? 1 : 0);
        dest.writeParcelable(this.remoteViews, 0);
        dest.writeParcelable(this.bigRemoteViews, 0);
        dest.writeParcelable(this.pendingIntent, 0);
        dest.writeParcelable(this.notification, 0);
        dest.writeParcelable(this.notificationChannel, 0);
    }
}
