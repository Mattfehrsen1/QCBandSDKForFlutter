package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuation) {
        this.mWorkContinuation = workContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
            }
            if (addToDatabase()) {
                PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean zProcessContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return zProcessContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        boolean z = false;
        if (parents != null) {
            boolean zProcessContinuation = false;
            for (WorkContinuationImpl workContinuationImpl : parents) {
                if (!workContinuationImpl.isEnqueued()) {
                    zProcessContinuation |= processContinuation(workContinuationImpl);
                } else {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", workContinuationImpl.getIds())), new Throwable[0]);
                }
            }
            z = zProcessContinuation;
        }
        return enqueueContinuation(workContinuation) | z;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        boolean zEnqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuation).toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return zEnqueueWorkWithPrerequisites;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01eb A[LOOP:5: B:127:0x01e5->B:129:0x01eb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0214 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0154 A[PHI: r1 r9 r12 r13 r14
      0x0154: PHI (r1v1 java.lang.String[]) = (r1v0 java.lang.String[]), (r1v0 java.lang.String[]), (r1v4 java.lang.String[]) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
      0x0154: PHI (r9v2 boolean) = (r9v1 boolean), (r9v1 boolean), (r9v7 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
      0x0154: PHI (r12v2 boolean) = (r12v1 boolean), (r12v1 boolean), (r12v4 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
      0x0154: PHI (r13v2 boolean) = (r13v1 boolean), (r13v1 boolean), (r13v5 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
      0x0154: PHI (r14v2 boolean) = (r14v1 boolean), (r14v1 boolean), (r14v5 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl r18, java.util.List<? extends androidx.work.WorkRequest> r19, java.lang.String[] r20, java.lang.String r21, androidx.work.ExistingWorkPolicy r22) {
        /*
            Method dump skipped, instructions count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.EnqueueRunnable.enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl, java.util.List, java.lang.String[], java.lang.String, androidx.work.ExistingWorkPolicy):boolean");
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        String str = workSpec.workerClassName;
        if (str.equals(ConstraintTrackingWorker.class.getName())) {
            return;
        }
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            Data.Builder builder = new Data.Builder();
            builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }

    private static boolean usesScheduler(WorkManagerImpl workManager, String className) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName(className);
            Iterator<Scheduler> it = workManager.getSchedulers().iterator();
            while (it.hasNext()) {
                if (cls.isAssignableFrom(it.next().getClass())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException unused) {
        }
        return false;
    }
}
