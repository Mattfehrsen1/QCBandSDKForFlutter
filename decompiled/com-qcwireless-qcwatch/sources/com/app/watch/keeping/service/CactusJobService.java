package com.app.watch.keeping.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import androidx.work.WorkRequest;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.entity.Constant;
import com.app.watch.keeping.exception.CactusException;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.ConfigExtKt;
import com.app.watch.keeping.ext.NotificationExtKt;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CactusJobService.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\"\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/app/watch/keeping/service/CactusJobService;", "Landroid/app/job/JobService;", "()V", "mCactusConfig", "Lcom/app/watch/keeping/entity/CactusConfig;", "mIsStop", "", "mJobId", "", "mJobScheduler", "Landroid/app/job/JobScheduler;", "onCreate", "", "onDestroy", "onStartCommand", "intent", "Landroid/content/Intent;", "flags", "startId", "onStartJob", "jobParameters", "Landroid/app/job/JobParameters;", "onStopJob", "registerJob", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CactusJobService extends JobService {
    private CactusConfig mCactusConfig;
    private boolean mIsStop;
    private int mJobId = 100;
    private JobScheduler mJobScheduler;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        CactusJobService cactusJobService = this;
        this.mCactusConfig = ConfigExtKt.getConfig(cactusJobService);
        registerJob();
        CactusExtKt.registerStopReceiver(cactusJobService, new Function0<Unit>() { // from class: com.app.watch.keeping.service.CactusJobService.onCreate.1
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
                CactusJobService.this.mIsStop = true;
                CactusExtKt.stopService(CactusJobService.this);
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws CactusException {
        CactusConfig cactusConfig;
        if (intent != null && (cactusConfig = (CactusConfig) intent.getParcelableExtra(Constant.CACTUS_CONFIG)) != null) {
            this.mCactusConfig = cactusConfig;
        }
        CactusJobService cactusJobService = this;
        CactusConfig cactusConfig2 = this.mCactusConfig;
        CactusConfig cactusConfig3 = null;
        if (cactusConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig2 = null;
        }
        NotificationExtKt.setNotification$default(cactusJobService, cactusConfig2.getNotificationConfig(), false, 2, null);
        CactusJobService cactusJobService2 = this;
        CactusConfig cactusConfig4 = this.mCactusConfig;
        if (cactusConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
        } else {
            cactusConfig3 = cactusConfig4;
        }
        CactusExtKt.registerCactus(cactusJobService2, cactusConfig3);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForeground(true);
        JobScheduler jobScheduler = this.mJobScheduler;
        if (jobScheduler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mJobScheduler");
            jobScheduler = null;
        }
        jobScheduler.cancel(this.mJobId);
        ConfigExtKt.saveJobId(this, -1);
        super.onDestroy();
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Intrinsics.checkNotNullParameter(jobParameters, "jobParameters");
        CactusExtKt.log("onStartJob");
        CactusJobService cactusJobService = this;
        if (CactusExtKt.isCactusRunning(cactusJobService) || this.mIsStop) {
            return false;
        }
        CactusConfig cactusConfig = this.mCactusConfig;
        if (cactusConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig = null;
        }
        CactusExtKt.registerCactus(cactusJobService, cactusConfig);
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        Intrinsics.checkNotNullParameter(jobParameters, "jobParameters");
        CactusExtKt.log("onStopJob");
        CactusJobService cactusJobService = this;
        if (CactusExtKt.isCactusRunning(cactusJobService) || this.mIsStop) {
            return false;
        }
        CactusConfig cactusConfig = this.mCactusConfig;
        if (cactusConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCactusConfig");
            cactusConfig = null;
        }
        CactusExtKt.registerCactus(cactusJobService, cactusConfig);
        return false;
    }

    private final void registerJob() {
        Object systemService = getSystemService("jobscheduler");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
        this.mJobScheduler = (JobScheduler) systemService;
        CactusJobService cactusJobService = this;
        int jobId = ConfigExtKt.getJobId(cactusJobService);
        this.mJobId = jobId;
        JobScheduler jobScheduler = null;
        if (jobId != -1) {
            JobScheduler jobScheduler2 = this.mJobScheduler;
            if (jobScheduler2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mJobScheduler");
                jobScheduler2 = null;
            }
            jobScheduler2.cancel(this.mJobId);
        }
        int id = CactusExtKt.getId();
        this.mJobId = id;
        ConfigExtKt.saveJobId(cactusJobService, id);
        JobInfo.Builder builder = new JobInfo.Builder(this.mJobId, new ComponentName(getPackageName(), CactusJobService.class.getName()));
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setMinimumLatency(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
                builder.setOverrideDeadline(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
                builder.setMinimumLatency(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
                builder.setBackoffCriteria(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, 0);
            } else {
                builder.setPeriodic(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
                builder.setRequiresDeviceIdle(true);
            }
            builder.setRequiredNetworkType(1);
            builder.setRequiresCharging(true);
            builder.setPersisted(true);
        } catch (Exception unused) {
        }
        JobScheduler jobScheduler3 = this.mJobScheduler;
        if (jobScheduler3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mJobScheduler");
        } else {
            jobScheduler = jobScheduler3;
        }
        jobScheduler.schedule(builder.build());
    }
}
