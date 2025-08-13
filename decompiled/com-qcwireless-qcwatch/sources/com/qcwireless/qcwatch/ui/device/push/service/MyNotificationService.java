package com.qcwireless.qcwatch.ui.device.push.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RemoteController;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.work.PeriodicWorkRequest;
import com.baidu.location.LocationConst;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.device.push.bean.MusicBean;
import com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil;
import com.qcwireless.qcwatch.ui.device.push.utils.SoftwarePackageAction;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: MyNotificationService.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0014\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u0010H\u0016J\u0016\u0010-\u001a\u00020\u001a2\f\u0010.\u001a\b\u0018\u00010/R\u00020\tH\u0016J\u0010\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\u001fH\u0016J(\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u000b2\u0006\u00104\u001a\u000205H\u0016J\u0010\u00106\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u001fH\u0016J\b\u00108\u001a\u00020\u001aH\u0016J\u0010\u00109\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0016J\"\u0010:\u001a\u00020\u001f2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u001fH\u0016J\b\u0010=\u001a\u00020\u001aH\u0002J\b\u0010>\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006?"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/service/MyNotificationService;", "Landroid/service/notification/NotificationListenerService;", "Landroid/media/RemoteController$OnClientUpdateListener;", "()V", "lastBean", "Lcom/qcwireless/qcwatch/ui/device/push/bean/MusicBean;", "mLastContext", "", "mRemoteController", "Landroid/media/RemoteController;", "mTime", "", "musicBean", "myHandler", "Landroid/os/Handler;", "playing", "", "qcMessagePushDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMessagePushDao;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "cmdToDevice", "", "bean", "filterGroupName", NotificationCompat.CATEGORY_MESSAGE, "getSystemVolume", "", "context", "Landroid/content/Context;", "handlerNotification", "sbn", "Landroid/service/notification/StatusBarNotification;", "isSameContext", "data", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onClientChange", "clearing", "onClientMetadataUpdate", "metadataEditor", "Landroid/media/RemoteController$MetadataEditor;", "onClientPlaybackStateUpdate", LocationConst.HDYawConst.KEY_HD_YAW_STATE, "stateChangeTimeMs", "currentPosMs", "speed", "", "onClientTransportControlUpdate", "transportControlFlags", "onCreate", "onNotificationPosted", "onStartCommand", "flags", "startId", "registerRemoteController", "wakeUpReConnect", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyNotificationService extends NotificationListenerService implements RemoteController.OnClientUpdateListener {
    private RemoteController mRemoteController;
    private MusicBean musicBean;
    private boolean playing;
    private final Handler myHandler = new Handler(Looper.getMainLooper());
    private MusicBean lastBean = new MusicBean();
    private long mTime = System.currentTimeMillis();
    private final QcMessagePushDao qcMessagePushDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcMessagePushDao();
    private String mLastContext = "";
    private Runnable runnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            MyNotificationService.m559runnable$lambda0(this.f$0);
        }
    };

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientChange(boolean clearing) {
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientTransportControlUpdate(int transportControlFlags) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            registerRemoteController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification sbn) {
        Intrinsics.checkNotNullParameter(sbn, "sbn");
        super.onNotificationPosted(sbn);
        try {
            handlerNotification(sbn);
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.onNotificationPosted.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) {
                    invoke2(myNotificationService);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MyNotificationService ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    long wakeTime = UserConfig.INSTANCE.getInstance().getWakeTime();
                    if (BleOperateManager.getInstance().isConnected() || new DateUtil().getUnixTimestamp() <= wakeTime) {
                        return;
                    }
                    XLog.i("---------通知唤醒线程--------");
                    ThreadManager.getInstance().wakeUp();
                    UserConfig.INSTANCE.getInstance().setWakeTime(new DateUtil().getUnixTimestamp() + 60);
                    UserConfig.INSTANCE.getInstance().save();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runnable$lambda-0, reason: not valid java name */
    public static final void m559runnable$lambda0(MyNotificationService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.wakeUpReConnect();
    }

    public final Runnable getRunnable() {
        return this.runnable;
    }

    public final void setRunnable(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "<set-?>");
        this.runnable = runnable;
    }

    private final void wakeUpReConnect() {
        XLog.e("mac: " + UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear() + ", name: " + UserConfig.INSTANCE.getInstance().getDeviceNameNoClear());
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        ThreadManager.getInstance().wakeUp();
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientPlaybackStateUpdate(int state) throws InterruptedException {
        if (state == 2) {
            this.playing = false;
            MusicBean musicBean = this.musicBean;
            if (musicBean != null) {
                Intrinsics.checkNotNull(musicBean);
                musicBean.playing = false;
                MusicBean musicBean2 = this.musicBean;
                Intrinsics.checkNotNull(musicBean2);
                cmdToDevice(musicBean2);
                return;
            }
            return;
        }
        if (state == 3) {
            this.playing = true;
            MusicBean musicBean3 = this.musicBean;
            if (musicBean3 != null) {
                Intrinsics.checkNotNull(musicBean3);
                musicBean3.playing = true;
                MusicBean musicBean4 = this.musicBean;
                Intrinsics.checkNotNull(musicBean4);
                cmdToDevice(musicBean4);
                return;
            }
            return;
        }
        XLog.i("----" + state);
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientPlaybackStateUpdate(int state, long stateChangeTimeMs, long currentPosMs, float speed) throws InterruptedException {
        if (state == 2) {
            this.playing = false;
            MusicBean musicBean = this.musicBean;
            if (musicBean != null) {
                Intrinsics.checkNotNull(musicBean);
                musicBean.playing = false;
                MusicBean musicBean2 = this.musicBean;
                Intrinsics.checkNotNull(musicBean2);
                cmdToDevice(musicBean2);
                return;
            }
            return;
        }
        if (state == 3) {
            this.playing = true;
            MusicBean musicBean3 = this.musicBean;
            if (musicBean3 != null) {
                Intrinsics.checkNotNull(musicBean3);
                musicBean3.playing = true;
                MusicBean musicBean4 = this.musicBean;
                Intrinsics.checkNotNull(musicBean4);
                cmdToDevice(musicBean4);
                return;
            }
            return;
        }
        XLog.i("----" + state);
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientMetadataUpdate(RemoteController.MetadataEditor metadataEditor) throws InterruptedException {
        Intrinsics.checkNotNull(metadataEditor);
        String string = metadataEditor.getString(2, "null");
        String string2 = metadataEditor.getString(1, "null");
        String string3 = metadataEditor.getString(7, "null");
        long j = metadataEditor.getLong(9, -1L);
        MusicBean musicBean = new MusicBean();
        this.musicBean = musicBean;
        Intrinsics.checkNotNull(musicBean);
        musicBean.artistName = string;
        MusicBean musicBean2 = this.musicBean;
        Intrinsics.checkNotNull(musicBean2);
        musicBean2.album = string2;
        MusicBean musicBean3 = this.musicBean;
        Intrinsics.checkNotNull(musicBean3);
        musicBean3.track = string3;
        MusicBean musicBean4 = this.musicBean;
        Intrinsics.checkNotNull(musicBean4);
        musicBean4.duration = Long.valueOf(j);
        MusicBean musicBean5 = this.musicBean;
        Intrinsics.checkNotNull(musicBean5);
        musicBean5.playing = Boolean.valueOf(this.playing);
        XLog.i(this.musicBean);
        MusicBean musicBean6 = this.musicBean;
        Intrinsics.checkNotNull(musicBean6);
        cmdToDevice(musicBean6);
    }

    private final void registerRemoteController() throws IllegalArgumentException {
        Object systemService;
        this.mRemoteController = new RemoteController(this, this);
        boolean zRegisterRemoteController = false;
        try {
            systemService = getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        } catch (NullPointerException | SecurityException unused) {
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.media.AudioManager");
        }
        zRegisterRemoteController = ((AudioManager) systemService).registerRemoteController(this.mRemoteController);
        if (zRegisterRemoteController) {
            try {
                RemoteController remoteController = this.mRemoteController;
                Intrinsics.checkNotNull(remoteController);
                remoteController.setArtworkConfiguration(100, 100);
                RemoteController remoteController2 = this.mRemoteController;
                Intrinsics.checkNotNull(remoteController2);
                remoteController2.setSynchronizationMode(1);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    private final void cmdToDevice(MusicBean bean) throws InterruptedException {
        if (QCApplication.INSTANCE.getGetInstance().getUpdating() == 1 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 2 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 3 || QCApplication.INSTANCE.getGetInstance().getUpdating() == 4) {
            XLog.i("正在固件升级返回");
            return;
        }
        if (Intrinsics.areEqual(bean, this.lastBean)) {
            return;
        }
        this.lastBean = bean;
        if (bean.track == null) {
            XLog.i("-------");
            return;
        }
        FileHandle fileHandle = FileHandle.getInstance();
        Boolean bool = bean.playing;
        Intrinsics.checkNotNullExpressionValue(bool, "bean.playing");
        fileHandle.executeMusicSend(bool.booleanValue(), 0, getSystemVolume(this), bean.track);
    }

    private final int getSystemVolume(Context context) {
        Object systemService = context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        AudioManager audioManager = (AudioManager) systemService;
        return (audioManager.getStreamVolume(3) * 100) / audioManager.getStreamMaxVolume(3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v44, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v47, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v2, types: [T, java.lang.CharSequence] */
    private final void handlerNotification(final StatusBarNotification sbn) {
        try {
            final int messagePushSupport = UserConfig.INSTANCE.getInstance().getMessagePushSupport();
            final Notification notification = sbn.getNotification();
            String string = sbn.toString();
            Intrinsics.checkNotNullExpressionValue(string, "sbn.toString()");
            final String packageName = sbn.getPackageName();
            CharSequence charSequence = notification.tickerText;
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE);
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT);
            if (objectRef.element == 0) {
                objectRef.element = "";
            }
            if (objectRef2.element == 0) {
                objectRef2.element = "";
            }
            if (TextUtils.isEmpty(charSequence) || !StringsKt.equals("misscall", charSequence.toString(), true)) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.wechat, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.wechat);
                            if (messagePushEntityQueryByName != null) {
                                if (messagePushEntityQueryByName.getOpen() != 1) {
                                    return;
                                }
                                if (objectRef2.element.length() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append((Object) objectRef.element);
                                    sb.append(':');
                                    sb.append((Object) objectRef2.element);
                                    MessagePushUtil.pushMsg(3, sb.toString());
                                    return;
                                }
                                if (notification.tickerText != null) {
                                    CharSequence charSequence2 = notification.tickerText;
                                    Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                    String str = (String) charSequence2;
                                    if (TextUtils.isEmpty(str) || StringsKt.startsWith$default(str, "语音聊天中, 轻击以继续", false, 2, (Object) null)) {
                                        return;
                                    }
                                    MessagePushUtil.pushMsg(3, str);
                                }
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.qq, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.qq);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (objectRef2.element != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append((Object) objectRef.element);
                                sb.append(':');
                                sb.append((Object) objectRef2.element);
                                String string2 = sb.toString();
                                if (TextUtils.isEmpty(string2)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(2, string2);
                                return;
                            }
                            if (objectRef.element != null || objectRef2.element != null) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append((Object) objectRef.element);
                                sb2.append(' ');
                                sb2.append((Object) objectRef2.element);
                                String string3 = sb2.toString();
                                ktxRunOnBgSingle.isSameContext(string3);
                                if (StringsKt.contains$default((CharSequence) string3, (CharSequence) "条新消息", false, 2, (Object) null)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(2, string3);
                                return;
                            }
                            if (sbn.getId() < 65535) {
                                MessagePushUtil.pushMsg(2, "New message");
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.twitter", false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.TWITTER);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(7, strFilterGroupName);
                                return;
                            }
                            MessagePushUtil.pushMsg(7, "New message");
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.facebook", false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.FACEBOOK);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                XLog.i(Integer.valueOf(messagePushSupport));
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                if ((messagePushSupport & 1) != 0) {
                                    String packageName2 = packageName;
                                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "orca", false, 2, (Object) null)) {
                                        MessagePushUtil.pushMsg(17, strFilterGroupName);
                                        return;
                                    } else {
                                        MessagePushUtil.pushMsg(5, strFilterGroupName);
                                        return;
                                    }
                                }
                                MessagePushUtil.pushMsg(5, strFilterGroupName);
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                String str = "" + ((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT));
                                ktxRunOnBgSingle.isSameContext(str);
                                String str2 = str;
                                if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "条新消息", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) str2, (CharSequence) "开始聊天", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) str2, (CharSequence) "个对话", false, 2, (Object) null)) {
                                    return;
                                }
                                if ((messagePushSupport & 1) != 0) {
                                    String packageName3 = packageName;
                                    Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                    if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "orca", false, 2, (Object) null)) {
                                        MessagePushUtil.pushMsg(17, str);
                                        return;
                                    } else {
                                        MessagePushUtil.pushMsg(5, str);
                                        return;
                                    }
                                }
                                MessagePushUtil.pushMsg(5, str);
                                return;
                            }
                            MessagePushUtil.pushMsg(5, "New message");
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.whatsapp, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingleAnother) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingleAnother.qcMessagePushDao.queryByName(SoftwarePackageAction.whatsapp);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE));
                                sb.append(':');
                                sb.append((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT));
                                String string2 = sb.toString();
                                if (ktxRunOnBgSingleAnother.isSameContext(string2) || StringsKt.contains$default((CharSequence) string2, (CharSequence) "条新消息", false, 2, (Object) null)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(6, string2);
                                return;
                            }
                            if (notification.tickerText != null) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE));
                                sb2.append(' ');
                                sb2.append((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT));
                                String strFilterGroupName = ktxRunOnBgSingleAnother.filterGroupName(sb2.toString());
                                String str = strFilterGroupName;
                                if (TextUtils.isEmpty(str) || ktxRunOnBgSingleAnother.isSameContext(strFilterGroupName) || StringsKt.contains$default((CharSequence) str, (CharSequence) "条新消息", false, 2, (Object) null)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(6, strFilterGroupName);
                                return;
                            }
                            if (sbn.getTag() == null) {
                                MessagePushUtil.pushMsg(6, "New message");
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.skype", false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            MessagePushEntity messagePushEntityQueryByName;
                            MessagePushEntity messagePushEntityQueryByName2;
                            MessagePushEntity messagePushEntityQueryByName3;
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            if (!Intrinsics.areEqual(SoftwarePackageAction.SKYPE1, sbn.getPackageName()) || ((messagePushEntityQueryByName3 = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.SKYPE1)) != null && messagePushEntityQueryByName3.getOpen() == 1)) {
                                if (!Intrinsics.areEqual(SoftwarePackageAction.SKYPE2, sbn.getPackageName()) || ((messagePushEntityQueryByName2 = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.SKYPE2)) != null && messagePushEntityQueryByName2.getOpen() == 1)) {
                                    XLog.i(packageName);
                                    if (!Intrinsics.areEqual(SoftwarePackageAction.SKYPE3, sbn.getPackageName()) || ((messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.SKYPE3)) != null && messagePushEntityQueryByName.getOpen() == 1)) {
                                        if (notification.tickerText != null) {
                                            CharSequence charSequence2 = notification.tickerText;
                                            Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                            String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                            if (!TextUtils.isEmpty(strFilterGroupName)) {
                                                MessagePushUtil.pushMsg(8, strFilterGroupName);
                                            }
                                        } else if (notification.priority > 0) {
                                            MessagePushUtil.pushMsg(8, "New message");
                                        }
                                        if ((objectRef2.element.length() > 0) && notification.tickerText == null) {
                                            MessagePushUtil.pushMsg(8, objectRef2.element.toString());
                                        }
                                    }
                                }
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "jp.naver.line", false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.7
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.LINE);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(9, strFilterGroupName);
                                return;
                            }
                            MessagePushUtil.pushMsg(9, String.valueOf(objectRef2.element));
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.linkedin, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.linkedin);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (!TextUtils.isEmpty(notification.tickerText) && !notification.tickerText.equals("null")) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                boolean zIsSameContext = ktxRunOnBgSingle.isSameContext(strFilterGroupName);
                                XLog.i("handlerNotification.. linkin: " + zIsSameContext);
                                if (zIsSameContext) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(10, strFilterGroupName);
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE));
                                sb.append(' ');
                                sb.append((Object) notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT));
                                String string2 = sb.toString();
                                boolean zIsSameContext2 = ktxRunOnBgSingle.isSameContext(string2);
                                XLog.i("handlerNotification.. isSame: " + zIsSameContext2);
                                if (zIsSameContext2) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(10, string2);
                                return;
                            }
                            MessagePushUtil.pushMsg(10, String.valueOf(objectRef2.element));
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.instagram, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingleNetWork(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.9
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingleNetWork) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingleNetWork, "$this$ktxRunOnBgSingleNetWork");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingleNetWork.qcMessagePushDao.queryByName(SoftwarePackageAction.instagram);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append((Object) objectRef.element);
                                    sb.append(':');
                                    sb.append((Object) objectRef2.element);
                                    MessagePushUtil.pushMsg(11, sb.toString());
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingleNetWork.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(11, strFilterGroupName);
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.tim, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.10
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.tim);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(12, strFilterGroupName);
                                return;
                            }
                            MessagePushUtil.pushMsg(12, "New message");
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.snapchat, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.11
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.snapchat);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append((Object) objectRef.element);
                                    sb.append(':');
                                    sb.append((Object) objectRef2.element);
                                    MessagePushUtil.pushMsg(13, sb.toString());
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(13, strFilterGroupName);
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.viber", false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.12
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00ac -> B:28:0x00af). Please report as a decompilation issue!!! */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName("com.viber.voip");
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            XLog.i(messagePushEntityQueryByName);
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                try {
                                    if ((messagePushSupport & 16) != 0) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append((Object) objectRef.element);
                                        sb.append(':');
                                        sb.append((Object) objectRef2.element);
                                        MessagePushUtil.pushMsg(21, sb.toString());
                                    } else {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append((Object) objectRef.element);
                                        sb2.append(':');
                                        sb2.append((Object) objectRef2.element);
                                        MessagePushUtil.pushMsg(14, sb2.toString());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                if ((messagePushSupport & 16) != 0) {
                                    MessagePushUtil.pushMsg(21, strFilterGroupName);
                                } else {
                                    MessagePushUtil.pushMsg(14, strFilterGroupName);
                                }
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "org.telegram", false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.13
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00aa -> B:28:0x00ad). Please report as a decompilation issue!!! */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.Telegram);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                try {
                                    if ((messagePushSupport & 8) != 0) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append((Object) objectRef.element);
                                        sb.append(':');
                                        sb.append((Object) objectRef2.element);
                                        MessagePushUtil.pushMsg(20, sb.toString());
                                    } else {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append((Object) objectRef.element);
                                        sb2.append(':');
                                        sb2.append((Object) objectRef2.element);
                                        MessagePushUtil.pushMsg(14, sb2.toString());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                if ((messagePushSupport & 8) != 0) {
                                    MessagePushUtil.pushMsg(20, strFilterGroupName);
                                } else {
                                    MessagePushUtil.pushMsg(14, strFilterGroupName);
                                }
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.KAKAOTALK, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.14
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.KAKAOTALK);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                if ((messagePushSupport & 4) != 0) {
                                    MessagePushUtil.pushMsg(19, strFilterGroupName);
                                    return;
                                } else {
                                    MessagePushUtil.pushMsg(14, strFilterGroupName);
                                    return;
                                }
                            }
                            try {
                                if ((messagePushSupport & 4) != 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append((Object) objectRef.element);
                                    sb.append(' ');
                                    sb.append((Object) objectRef2.element);
                                    MessagePushUtil.pushMsg(19, sb.toString());
                                } else {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append((Object) objectRef.element);
                                    sb2.append(' ');
                                    sb2.append((Object) objectRef2.element);
                                    MessagePushUtil.pushMsg(14, sb2.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.ZALO, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.15
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00a9 -> B:28:0x00ac). Please report as a decompilation issue!!! */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.ZALO);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                try {
                                    if ((messagePushSupport & 2) != 0) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append((Object) objectRef.element);
                                        sb.append(':');
                                        sb.append((Object) objectRef2.element);
                                        MessagePushUtil.pushMsg(18, sb.toString());
                                    } else {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append((Object) objectRef.element);
                                        sb2.append(':');
                                        sb2.append((Object) objectRef2.element);
                                        MessagePushUtil.pushMsg(14, sb2.toString());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String strFilterGroupName = ktxRunOnBgSingle.filterGroupName((String) charSequence2);
                                if (TextUtils.isEmpty(strFilterGroupName)) {
                                    return;
                                }
                                if ((messagePushSupport & 2) != 0) {
                                    MessagePushUtil.pushMsg(18, strFilterGroupName);
                                } else {
                                    MessagePushUtil.pushMsg(14, strFilterGroupName);
                                }
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.Pinterest, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.16
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.Pinterest);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String str = (String) charSequence2;
                                if (TextUtils.isEmpty(str)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(14, str);
                                return;
                            }
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append((Object) objectRef.element);
                                sb.append(' ');
                                sb.append((Object) objectRef2.element);
                                MessagePushUtil.pushMsg(14, sb.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.Tumblr, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.17
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.Tumblr);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String str = (String) charSequence2;
                                if (TextUtils.isEmpty(str)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(14, str);
                                return;
                            }
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append((Object) objectRef.element);
                                sb.append(' ');
                                sb.append((Object) objectRef2.element);
                                MessagePushUtil.pushMsg(14, sb.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) SoftwarePackageAction.Gmail, false, 2, (Object) null)) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.18
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                            invoke2(myNotificationService);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            MessagePushEntity messagePushEntityQueryByName = ktxRunOnBgSingle.qcMessagePushDao.queryByName(SoftwarePackageAction.Gmail);
                            if (messagePushEntityQueryByName == null || messagePushEntityQueryByName.getOpen() != 1) {
                                return;
                            }
                            if (notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE) != null || notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT) != null) {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append((Object) objectRef.element);
                                    sb.append(':');
                                    sb.append((Object) objectRef2.element);
                                    MessagePushUtil.pushMsg(14, sb.toString());
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (notification.tickerText != null) {
                                CharSequence charSequence2 = notification.tickerText;
                                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                                String str = (String) charSequence2;
                                if (TextUtils.isEmpty(str)) {
                                    return;
                                }
                                MessagePushUtil.pushMsg(14, str);
                                return;
                            }
                            try {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append((Object) objectRef.element);
                                sb2.append(' ');
                                sb2.append((Object) objectRef2.element);
                                MessagePushUtil.pushMsg(14, sb2.toString());
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "sms", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "mms", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "conversations", false, 2, (Object) null)) {
                    if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.android", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.samsung.android", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) string, (CharSequence) "sms", false, 2, (Object) null)) {
                        if (notification.tickerText != null) {
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.android.mms", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.samsung.android.messaging", false, 2, (Object) null)) {
                                return;
                            }
                            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                            T t = objectRef2.element;
                            if (t == 0) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            objectRef3.element = (String) t;
                            objectRef3.element = filterGroupName((String) objectRef3.element);
                            if (TextUtils.isEmpty((CharSequence) objectRef3.element) || isSameContext((String) objectRef3.element)) {
                                return;
                            }
                            if ((!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.tencent.qqmusic", false, 2, (Object) null) || !TextUtils.isEmpty(this.mLastContext)) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.qcwx", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.qcwireless", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.app.watch", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.wacsoso", false, 2, (Object) null)) {
                                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.19
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                                        invoke2(myNotificationService);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        if (UserConfig.INSTANCE.getInstance().getOtherSoftwarePush()) {
                                            MessagePushUtil.pushMsg(14, objectRef3.element);
                                        }
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        try {
                            if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.qcwx", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.qcwireless", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.app.watch", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.wacsoso", false, 2, (Object) null)) {
                                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyNotificationService, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService.handlerNotification.20
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(MyNotificationService myNotificationService) throws UnsupportedEncodingException {
                                        invoke2(myNotificationService);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(MyNotificationService ktxRunOnBgSingle) throws UnsupportedEncodingException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        if (UserConfig.INSTANCE.getInstance().getOtherSoftwarePush()) {
                                            CharSequence charSequence2 = objectRef.element;
                                            if (charSequence2 == null || charSequence2.length() == 0) {
                                                CharSequence charSequence3 = objectRef2.element;
                                                if (charSequence3 == null || charSequence3.length() == 0) {
                                                    return;
                                                }
                                            }
                                            CharSequence charSequence4 = objectRef2.element;
                                            if (charSequence4 == null || charSequence4.length() == 0) {
                                                MessagePushUtil.pushMsg(14, String.valueOf(objectRef.element));
                                                return;
                                            }
                                            StringBuilder sb = new StringBuilder();
                                            sb.append((Object) objectRef.element);
                                            sb.append(':');
                                            sb.append((Object) objectRef2.element);
                                            MessagePushUtil.pushMsg(14, sb.toString());
                                        }
                                    }
                                });
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                try {
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "com.miui", false, 2, (Object) null)) {
                        return;
                    }
                } catch (Exception unused) {
                }
                if (notification.tickerText != null) {
                    if (UserConfig.INSTANCE.getInstance().getSmsPushEnable()) {
                        CharSequence charSequence2 = notification.tickerText;
                        Intrinsics.checkNotNullExpressionValue(charSequence2, "notification.tickerText");
                        MessagePushUtil.pushSmsMsg(1, (String) StringsKt.trim(charSequence2));
                        return;
                    }
                    return;
                }
                if ((((CharSequence) objectRef2.element).length() > 0) && UserConfig.INSTANCE.getInstance().getSmsPushEnable()) {
                    MessagePushUtil.pushSmsMsgFromTitle(1, StringsKt.replace$default(String.valueOf(objectRef.element), " ", "", false, 4, (Object) null) + ':' + objectRef2.element);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSameContext(String data) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mTime;
        this.mTime = System.currentTimeMillis();
        if (jCurrentTimeMillis > PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
            this.mLastContext = data;
            return false;
        }
        boolean zEquals = TextUtils.equals(data, this.mLastContext);
        this.mLastContext = data;
        return zEquals;
    }

    public final String filterGroupName(String msg) {
        String strReplaceFirst = Pattern.compile("\\(.+\\):").matcher(msg).replaceFirst(":");
        Intrinsics.checkNotNullExpressionValue(strReplaceFirst, "pattern.matcher(msg).replaceFirst(\":\")");
        return strReplaceFirst;
    }
}
