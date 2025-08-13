package com.app.watch.keeping.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import com.app.watch.keeping.Cactus;
import com.app.watch.keeping.callback.CactusBackgroundCallback;
import com.app.watch.keeping.callback.CactusCallback;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.entity.Constant;
import com.app.watch.keeping.entity.ICactusInterface;
import com.app.watch.keeping.exception.CactusException;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.ConfigExtKt;
import com.app.watch.keeping.ext.NotificationExtKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocalService.kt */
@Metadata(d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u0014\u0018\u00002\u00020\u00012\u00020\u0002:\u0002-.B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u000bH\u0002J\u0014\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0019H\u0016J\b\u0010$\u001a\u00020\u0019H\u0016J\"\u0010%\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0007H\u0016J\b\u0010(\u001a\u00020\u0019H\u0002J\b\u0010)\u001a\u00020\u0019H\u0002J\b\u0010*\u001a\u00020\u0019H\u0002J\b\u0010+\u001a\u00020\u0019H\u0002J\b\u0010,\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0018\u00010\u0017R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/app/watch/keeping/service/LocalService;", "Landroid/app/Service;", "Landroid/os/IBinder$DeathRecipient;", "()V", "mCactusConfig", "Lcom/app/watch/keeping/entity/CactusConfig;", "mConnectionTimes", "", "mIInterface", "Lcom/app/watch/keeping/entity/ICactusInterface;", "mIsBind", "", "mIsDeathBind", "mIsServiceRunning", "mIsStop", "mLocalBinder", "Lcom/app/watch/keeping/service/LocalService$LocalBinder;", "mMediaPlayer", "Landroid/media/MediaPlayer;", "mServiceConnection", "com/app/watch/keeping/service/LocalService$mServiceConnection$1", "Lcom/app/watch/keeping/service/LocalService$mServiceConnection$1;", "mServiceReceiver", "Lcom/app/watch/keeping/service/LocalService$ServiceReceiver;", "binderDied", "", "closeOnePix", "doWork", Cactus.CACTUS_TIMES, "onBackground", "background", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "onStop", "openOnePix", "registerBroadcastReceiver", "stopBind", "unregisterReceiver", "LocalBinder", "ServiceReceiver", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LocalService extends Service implements IBinder.DeathRecipient {
    private CactusConfig mCactusConfig;
    private ICactusInterface mIInterface;
    private boolean mIsBind;
    private boolean mIsDeathBind;
    private boolean mIsServiceRunning;
    private boolean mIsStop;
    private LocalBinder mLocalBinder;
    private MediaPlayer mMediaPlayer;
    private ServiceReceiver mServiceReceiver;
    private int mConnectionTimes = CactusExtKt.getSTimes();
    private final LocalService$mServiceConnection$1 mServiceConnection = new ServiceConnection() { // from class: com.app.watch.keeping.service.LocalService$mServiceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            CactusExtKt.log("onServiceDisconnected");
            if (this.this$0.mIsStop) {
                return;
            }
            LocalService localService = this.this$0;
            LocalService localService2 = localService;
            LocalService$mServiceConnection$1 localService$mServiceConnection$1 = this;
            CactusConfig cactusConfig = localService.mCactusConfig;
            if (cactusConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                cactusConfig = null;
            }
            localService.mIsBind = CactusExtKt.startRemoteService(localService2, localService$mServiceConnection$1, cactusConfig);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
            CactusExtKt.log("onServiceConnected");
            if (service != null) {
                LocalService localService = this.this$0;
                ICactusInterface iCactusInterfaceAsInterface = ICactusInterface.Stub.asInterface(service);
                CactusConfig cactusConfig = null;
                if (iCactusInterfaceAsInterface == null) {
                    iCactusInterfaceAsInterface = null;
                } else if (iCactusInterfaceAsInterface.asBinder().isBinderAlive() && iCactusInterfaceAsInterface.asBinder().pingBinder()) {
                    try {
                        localService.mConnectionTimes++;
                        int unused = localService.mConnectionTimes;
                        CactusConfig cactusConfig2 = localService.mCactusConfig;
                        if (cactusConfig2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                        } else {
                            cactusConfig = cactusConfig2;
                        }
                        iCactusInterfaceAsInterface.wakeup(cactusConfig);
                        iCactusInterfaceAsInterface.connectionTimes(localService.mConnectionTimes);
                        if (!localService.mIsDeathBind) {
                            localService.mIsDeathBind = true;
                            iCactusInterfaceAsInterface.asBinder().linkToDeath(localService, 0);
                        }
                    } catch (Exception unused2) {
                        localService.mConnectionTimes--;
                        int unused3 = localService.mConnectionTimes;
                    }
                }
                localService.mIInterface = iCactusInterfaceAsInterface;
            }
        }
    };

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        CactusExtKt.log("binderDied");
        try {
            CactusExtKt.unlinkToDeath(this, this.mIInterface, new Function0<Unit>() { // from class: com.app.watch.keeping.service.LocalService.binderDied.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    LocalService.this.mIsDeathBind = false;
                    CactusConfig cactusConfig = null;
                    LocalService.this.mIInterface = null;
                    if (LocalService.this.mIsStop) {
                        return;
                    }
                    LocalService localService = LocalService.this;
                    LocalService localService2 = localService;
                    LocalService$mServiceConnection$1 localService$mServiceConnection$1 = localService.mServiceConnection;
                    CactusConfig cactusConfig2 = LocalService.this.mCactusConfig;
                    if (cactusConfig2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                    } else {
                        cactusConfig = cactusConfig2;
                    }
                    localService.mIsBind = CactusExtKt.startRemoteService(localService2, localService$mServiceConnection$1, cactusConfig);
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LocalService localService = this;
        this.mCactusConfig = ConfigExtKt.getConfig(localService);
        CactusExtKt.registerStopReceiver(localService, new Function0<Unit>() { // from class: com.app.watch.keeping.service.LocalService.onCreate.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LocalService.this.mIsStop = true;
                CactusExtKt.setSTimes(LocalService.this.mConnectionTimes);
                CactusExtKt.stopService(LocalService.this);
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws CactusException {
        CactusConfig cactusConfig;
        if (intent != null && (cactusConfig = (CactusConfig) intent.getParcelableExtra(Constant.CACTUS_CONFIG)) != null) {
            this.mCactusConfig = cactusConfig;
        }
        LocalService localService = this;
        CactusConfig cactusConfig2 = this.mCactusConfig;
        CactusConfig cactusConfig3 = null;
        if (cactusConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig2 = null;
        }
        NotificationExtKt.setNotification$default(localService, cactusConfig2.getNotificationConfig(), false, 2, null);
        LocalService$mServiceConnection$1 localService$mServiceConnection$1 = this.mServiceConnection;
        CactusConfig cactusConfig4 = this.mCactusConfig;
        if (cactusConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
        } else {
            cactusConfig3 = cactusConfig4;
        }
        this.mIsBind = CactusExtKt.startRemoteService(localService, localService$mServiceConnection$1, cactusConfig3);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        stopBind();
        stopService(new Intent(this, (Class<?>) RemoteService.class));
        onStop();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LocalBinder localBinder = new LocalBinder();
        this.mLocalBinder = localBinder;
        return localBinder;
    }

    /* compiled from: LocalService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/app/watch/keeping/service/LocalService$LocalBinder;", "Lcom/app/watch/keeping/entity/ICactusInterface$Stub;", "(Lcom/app/watch/keeping/service/LocalService;)V", "connectionTimes", "", "time", "", "wakeup", "config", "Lcom/app/watch/keeping/entity/CactusConfig;", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LocalBinder extends ICactusInterface.Stub {
        public LocalBinder() {
        }

        @Override // com.app.watch.keeping.entity.ICactusInterface
        public void wakeup(CactusConfig config) {
            Intrinsics.checkNotNullParameter(config, "config");
            LocalService.this.mCactusConfig = config;
        }

        @Override // com.app.watch.keeping.entity.ICactusInterface
        public void connectionTimes(int time) {
            LocalService.this.mConnectionTimes = time;
            if (LocalService.this.mConnectionTimes > 3 && LocalService.this.mConnectionTimes % 2 == 0) {
                LocalService localService = LocalService.this;
                localService.mConnectionTimes++;
                int unused = localService.mConnectionTimes;
            }
            CactusExtKt.setSTimes(LocalService.this.mConnectionTimes);
            LocalService localService2 = LocalService.this;
            localService2.doWork((localService2.mConnectionTimes + 1) / 2);
        }
    }

    /* compiled from: LocalService.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/app/watch/keeping/service/LocalService$ServiceReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/app/watch/keeping/service/LocalService;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ServiceReceiver extends BroadcastReceiver {
        public ServiceReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            LocalService localService = LocalService.this;
            if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_OFF")) {
                CactusExtKt.log("screen off");
                localService.openOnePix();
                return;
            }
            if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_ON")) {
                CactusExtKt.log("screen on");
                localService.closeOnePix();
            } else if (Intrinsics.areEqual(action, Cactus.CACTUS_BACKGROUND)) {
                CactusExtKt.log("background");
                localService.onBackground(true);
            } else if (Intrinsics.areEqual(action, Cactus.CACTUS_FOREGROUND)) {
                CactusExtKt.log("foreground");
                localService.onBackground(false);
            }
        }
    }

    private final void stopBind() {
        try {
            if (this.mIsDeathBind) {
                this.mIsDeathBind = false;
                CactusExtKt.unlinkToDeath$default(this, this.mIInterface, null, 2, null);
            }
            if (this.mIsBind) {
                unbindService(this.mServiceConnection);
                this.mIsBind = false;
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doWork(final int times) {
        if (this.mIsServiceRunning) {
            return;
        }
        this.mIsServiceRunning = true;
        CactusExtKt.log("LocalService is run >>>> do work times = " + times);
        registerBroadcastReceiver();
        sendBroadcast(new Intent(Cactus.CACTUS_WORK).putExtra(Cactus.CACTUS_TIMES, times));
        if (true ^ Constant.INSTANCE.getCALLBACKS$keeping_release().isEmpty()) {
            for (final CactusCallback cactusCallback : Constant.INSTANCE.getCALLBACKS$keeping_release()) {
                CactusConfig cactusConfig = this.mCactusConfig;
                if (cactusConfig == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                    cactusConfig = null;
                }
                if (cactusConfig.getDefaultConfig().getWorkOnMainThread()) {
                    CactusExtKt.getSMainHandler().post(new Runnable() { // from class: com.app.watch.keeping.service.LocalService$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocalService.m191doWork$lambda2$lambda1(cactusCallback, times);
                        }
                    });
                } else {
                    cactusCallback.doWork(times);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doWork$lambda-2$lambda-1, reason: not valid java name */
    public static final void m191doWork$lambda2$lambda1(CactusCallback it, int i) {
        Intrinsics.checkNotNullParameter(it, "$it");
        it.doWork(i);
    }

    private final void onStop() {
        if (this.mIsServiceRunning) {
            this.mIsServiceRunning = false;
            CactusExtKt.log("LocalService is stop!");
            unregisterReceiver();
            sendBroadcast(new Intent(Cactus.CACTUS_STOP));
            this.mMediaPlayer = null;
            if (!Constant.INSTANCE.getCALLBACKS$keeping_release().isEmpty()) {
                Iterator<T> it = Constant.INSTANCE.getCALLBACKS$keeping_release().iterator();
                while (it.hasNext()) {
                    ((CactusCallback) it.next()).onStop();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openOnePix() {
        CactusConfig cactusConfig = this.mCactusConfig;
        if (cactusConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig = null;
        }
        if (cactusConfig.getDefaultConfig().getOnePixEnabled()) {
            CactusExtKt.getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.service.LocalService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws PendingIntent.CanceledException {
                    LocalService.m192openOnePix$lambda4(this.f$0);
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openOnePix$lambda-4, reason: not valid java name */
    public static final void m192openOnePix$lambda4(LocalService this$0) throws PendingIntent.CanceledException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CactusExtKt.startOnePixActivity(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeOnePix() {
        CactusConfig cactusConfig = this.mCactusConfig;
        if (cactusConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig = null;
        }
        if (cactusConfig.getDefaultConfig().getOnePixEnabled()) {
            CactusExtKt.backBackground();
            CactusExtKt.finishOnePix();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBackground(boolean background) {
        if (!Constant.INSTANCE.getBACKGROUND_CALLBACKS$keeping_release().isEmpty()) {
            Iterator<T> it = Constant.INSTANCE.getBACKGROUND_CALLBACKS$keeping_release().iterator();
            while (it.hasNext()) {
                ((CactusBackgroundCallback) it.next()).onBackground(background);
            }
        }
    }

    private final void registerBroadcastReceiver() {
        if (this.mServiceReceiver == null) {
            this.mServiceReceiver = new ServiceReceiver();
        }
        ServiceReceiver serviceReceiver = this.mServiceReceiver;
        if (serviceReceiver != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction(Cactus.CACTUS_BACKGROUND);
            intentFilter.addAction(Cactus.CACTUS_FOREGROUND);
            Unit unit = Unit.INSTANCE;
            registerReceiver(serviceReceiver, intentFilter);
        }
    }

    private final void unregisterReceiver() {
        ServiceReceiver serviceReceiver = this.mServiceReceiver;
        if (serviceReceiver != null) {
            unregisterReceiver(serviceReceiver);
            this.mServiceReceiver = null;
        }
    }
}
