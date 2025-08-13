package com.app.watch.keeping.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.entity.Constant;
import com.app.watch.keeping.entity.ICactusInterface;
import com.app.watch.keeping.exception.CactusException;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.ConfigExtKt;
import com.app.watch.keeping.ext.NotificationExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RemoteService.kt */
@Metadata(d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u000f\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\"\u0010\u001b\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0011\u001a\u00060\u0012R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/app/watch/keeping/service/RemoteService;", "Landroid/app/Service;", "Landroid/os/IBinder$DeathRecipient;", "()V", "mCactusConfig", "Lcom/app/watch/keeping/entity/CactusConfig;", "mConnectionTimes", "", "mIInterface", "Lcom/app/watch/keeping/entity/ICactusInterface;", "mIsBind", "", "mIsDeathBind", "mIsStop", "mServiceConnection", "com/app/watch/keeping/service/RemoteService$mServiceConnection$1", "Lcom/app/watch/keeping/service/RemoteService$mServiceConnection$1;", "remoteBinder", "Lcom/app/watch/keeping/service/RemoteService$RemoteBinder;", "binderDied", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "stopBind", "RemoteBinder", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RemoteService extends Service implements IBinder.DeathRecipient {
    private CactusConfig mCactusConfig;
    private ICactusInterface mIInterface;
    private boolean mIsBind;
    private boolean mIsDeathBind;
    private boolean mIsStop;
    private RemoteBinder remoteBinder;
    private int mConnectionTimes = CactusExtKt.getSTimes();
    private final RemoteService$mServiceConnection$1 mServiceConnection = new ServiceConnection() { // from class: com.app.watch.keeping.service.RemoteService$mServiceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            CactusExtKt.log("onServiceDisconnected");
            if (this.this$0.mIsStop) {
                return;
            }
            RemoteService remoteService = this.this$0;
            RemoteService remoteService2 = remoteService;
            RemoteService$mServiceConnection$1 remoteService$mServiceConnection$1 = this;
            CactusConfig cactusConfig = remoteService.mCactusConfig;
            if (cactusConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                cactusConfig = null;
            }
            remoteService.mIsBind = CactusExtKt.startLocalService$default(remoteService2, remoteService$mServiceConnection$1, cactusConfig, false, 4, null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
            CactusExtKt.log("onServiceConnected");
            if (service != null) {
                RemoteService remoteService = this.this$0;
                ICactusInterface iCactusInterfaceAsInterface = ICactusInterface.Stub.asInterface(service);
                CactusConfig cactusConfig = null;
                if (iCactusInterfaceAsInterface == null) {
                    iCactusInterfaceAsInterface = null;
                } else if (iCactusInterfaceAsInterface.asBinder().isBinderAlive() && iCactusInterfaceAsInterface.asBinder().pingBinder()) {
                    try {
                        remoteService.mConnectionTimes++;
                        int unused = remoteService.mConnectionTimes;
                        CactusConfig cactusConfig2 = remoteService.mCactusConfig;
                        if (cactusConfig2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                        } else {
                            cactusConfig = cactusConfig2;
                        }
                        iCactusInterfaceAsInterface.wakeup(cactusConfig);
                        iCactusInterfaceAsInterface.connectionTimes(remoteService.mConnectionTimes);
                        if (!remoteService.mIsDeathBind) {
                            remoteService.mIsDeathBind = true;
                            iCactusInterfaceAsInterface.asBinder().linkToDeath(remoteService, 0);
                        }
                    } catch (Exception unused2) {
                        remoteService.mConnectionTimes--;
                        int unused3 = remoteService.mConnectionTimes;
                    }
                }
                remoteService.mIInterface = iCactusInterfaceAsInterface;
            }
        }
    };

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        CactusExtKt.log("binderDied");
        try {
            CactusExtKt.unlinkToDeath(this, this.mIInterface, new Function0<Unit>() { // from class: com.app.watch.keeping.service.RemoteService.binderDied.1
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
                    RemoteService.this.mIsDeathBind = false;
                    RemoteService.this.mIInterface = null;
                    if (RemoteService.this.mIsStop) {
                        return;
                    }
                    RemoteService remoteService = RemoteService.this;
                    RemoteService remoteService2 = remoteService;
                    RemoteService$mServiceConnection$1 remoteService$mServiceConnection$1 = remoteService.mServiceConnection;
                    CactusConfig cactusConfig = RemoteService.this.mCactusConfig;
                    if (cactusConfig == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                        cactusConfig = null;
                    }
                    remoteService.mIsBind = CactusExtKt.startLocalService$default(remoteService2, remoteService$mServiceConnection$1, cactusConfig, false, 4, null);
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            CactusExtKt.log("handleNotification");
            CactusConfig config = ConfigExtKt.getConfig(this);
            this.mCactusConfig = config;
            RemoteService remoteService = this;
            if (config == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                config = null;
            }
            NotificationExtKt.setNotification$default(remoteService, config.getNotificationConfig(), false, 2, null);
        } catch (Exception unused) {
        }
        CactusExtKt.registerStopReceiver(this, new Function0<Unit>() { // from class: com.app.watch.keeping.service.RemoteService.onCreate.1
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
                RemoteService.this.mIsStop = true;
                CactusExtKt.setSTimes(RemoteService.this.mConnectionTimes);
                CactusExtKt.stopService(RemoteService.this);
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws CactusException {
        CactusConfig cactusConfig;
        if (intent != null && (cactusConfig = (CactusConfig) intent.getParcelableExtra(Constant.CACTUS_CONFIG)) != null) {
            CactusExtKt.setSCactusConfig(cactusConfig);
            this.mCactusConfig = cactusConfig;
        }
        RemoteService remoteService = this;
        CactusConfig cactusConfig2 = this.mCactusConfig;
        CactusConfig cactusConfig3 = null;
        if (cactusConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig2 = null;
        }
        NotificationExtKt.setNotification$default(remoteService, cactusConfig2.getNotificationConfig(), false, 2, null);
        RemoteService$mServiceConnection$1 remoteService$mServiceConnection$1 = this.mServiceConnection;
        CactusConfig cactusConfig4 = this.mCactusConfig;
        if (cactusConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
        } else {
            cactusConfig3 = cactusConfig4;
        }
        this.mIsBind = CactusExtKt.startLocalService(remoteService, remoteService$mServiceConnection$1, cactusConfig3, false);
        CactusExtKt.log("RemoteService is running");
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        stopBind();
        CactusExtKt.log("RemoteService has stopped");
        Process.killProcess(Process.myPid());
        System.exit(10);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        RemoteBinder remoteBinder = new RemoteBinder();
        this.remoteBinder = remoteBinder;
        return remoteBinder;
    }

    /* compiled from: RemoteService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lcom/app/watch/keeping/service/RemoteService$RemoteBinder;", "Lcom/app/watch/keeping/entity/ICactusInterface$Stub;", "(Lcom/app/watch/keeping/service/RemoteService;)V", "connectionTimes", "", "time", "", "wakeup", "config", "Lcom/app/watch/keeping/entity/CactusConfig;", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class RemoteBinder extends ICactusInterface.Stub {
        public RemoteBinder() {
        }

        @Override // com.app.watch.keeping.entity.ICactusInterface
        public void wakeup(CactusConfig config) throws CactusException {
            if (config != null) {
                RemoteService.this.mCactusConfig = config;
            }
            RemoteService remoteService = RemoteService.this;
            RemoteService remoteService2 = remoteService;
            CactusConfig cactusConfig = remoteService.mCactusConfig;
            if (cactusConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
                cactusConfig = null;
            }
            NotificationExtKt.setNotification$default(remoteService2, cactusConfig.getNotificationConfig(), false, 2, null);
        }

        @Override // com.app.watch.keeping.entity.ICactusInterface
        public void connectionTimes(int time) {
            RemoteService.this.mConnectionTimes = time;
            if (RemoteService.this.mConnectionTimes > 4 && RemoteService.this.mConnectionTimes % 2 == 1) {
                RemoteService remoteService = RemoteService.this;
                remoteService.mConnectionTimes++;
                int unused = remoteService.mConnectionTimes;
            }
            CactusExtKt.setSTimes(RemoteService.this.mConnectionTimes);
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
}
