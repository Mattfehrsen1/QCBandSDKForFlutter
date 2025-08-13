package com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.elvishew.xlog.XLog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.bean.mine.GoogleFitDataBean;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QSleepBarChart;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean;
import com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: GoogleFitSync.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0012\u001a\u00020\nJ\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u0015\u001a\u00020\u000eJ\b\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync;", "", "()V", "fitnessOptions", "Lcom/google/android/gms/fitness/FitnessOptions;", "getFitnessOptions", "()Lcom/google/android/gms/fitness/FitnessOptions;", "fitnessOptions$delegate", "Lkotlin/Lazy;", "connectGoogleFit", "", "activity", "Landroid/app/Activity;", "disconnectGoogleFit", "", "getGoogleAccount", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "getLastSignIn", "hasGoogleFitPermissions", "isGooglePlayAvailable", "supportGoogleFit", "syncGoogleFit", "uploadHeart2Fit", "uploadSleep2Fit", "uploadStepAndCalorie2Fit", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GoogleFitSync {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<GoogleFitSync> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<GoogleFitSync>() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GoogleFitSync invoke() {
            return new GoogleFitSync();
        }
    });

    /* renamed from: fitnessOptions$delegate, reason: from kotlin metadata */
    private final Lazy fitnessOptions = LazyKt.lazy(new Function0<FitnessOptions>() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$fitnessOptions$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FitnessOptions invoke() {
            FitnessOptions fitnessOptionsBuild = FitnessOptions.builder().addDataType(DataType.TYPE_STEP_COUNT_DELTA, 1).addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, 1).addDataType(DataType.TYPE_CALORIES_EXPENDED, 1).addDataType(DataType.TYPE_SLEEP_SEGMENT, 1).addDataType(DataType.TYPE_HEART_RATE_BPM, 1).build();
            Intrinsics.checkNotNullExpressionValue(fitnessOptionsBuild, "builder()\n            .a…ITE)\n            .build()");
            return fitnessOptionsBuild;
        }
    });

    private final FitnessOptions getFitnessOptions() {
        return (FitnessOptions) this.fitnessOptions.getValue();
    }

    public final boolean supportGoogleFit(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (isGooglePlayAvailable(activity)) {
            return true;
        }
        XLog.i("GooglePlayAvailable");
        return false;
    }

    public final boolean connectGoogleFit(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (activity.isDestroyed()) {
            return false;
        }
        if (!isGooglePlayAvailable(activity)) {
            XLog.i("GooglePlayAvailable");
            return false;
        }
        GoogleSignInAccount googleAccount = getGoogleAccount();
        GoogleSignIn.requestPermissions(activity, 2000, googleAccount, getFitnessOptions());
        if (GoogleSignIn.hasPermissions(googleAccount, getFitnessOptions())) {
            return true;
        }
        GoogleSignIn.requestPermissions(activity, 2000, googleAccount, getFitnessOptions());
        XLog.e("权限没有授权");
        return false;
    }

    public final boolean hasGoogleFitPermissions() {
        return GoogleSignIn.hasPermissions(getGoogleAccount(), getFitnessOptions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GoogleSignInAccount getGoogleAccount() {
        GoogleSignInAccount accountForExtension = GoogleSignIn.getAccountForExtension(QJavaApplication.getInstance().getApplication(), getFitnessOptions());
        Intrinsics.checkNotNullExpressionValue(accountForExtension, "getAccountForExtension(\n…     fitnessOptions\n    )");
        return accountForExtension;
    }

    public final GoogleSignInAccount getLastSignIn() {
        return GoogleSignIn.getLastSignedInAccount(QJavaApplication.getInstance().getApplication());
    }

    private final boolean isGooglePlayAvailable(Activity activity) {
        Dialog errorDialog;
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(googleApiAvailability, "getInstance()");
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(activity.getApplicationContext());
        if (iIsGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (googleApiAvailability.isUserResolvableError(iIsGooglePlayServicesAvailable) && (errorDialog = googleApiAvailability.getErrorDialog(activity, iIsGooglePlayServicesAvailable, 2404)) != null) {
            errorDialog.show();
        }
        return false;
    }

    public final void disconnectGoogleFit() {
        Fitness.getConfigClient(QJavaApplication.getInstance().getApplication(), getGoogleAccount()).disableFit();
    }

    public final void syncGoogleFit() {
        try {
            uploadStepAndCalorie2Fit();
            uploadSleep2Fit();
            uploadHeart2Fit();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v15, types: [T, com.qcwireless.qcwatch.ui.base.bean.mine.GoogleFitDataBean, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.Object] */
    private final void uploadStepAndCalorie2Fit() {
        DateUtil dateUtil = new DateUtil();
        String googleFitLastInfo = UserConfig.INSTANCE.getInstance().getGoogleFitLastInfo();
        if (googleFitLastInfo.length() == 0) {
            GoogleFitDataBean googleFitDataBean = new GoogleFitDataBean();
            googleFitDataBean.setStep(0);
            googleFitDataBean.setCalorie(0);
            googleFitDataBean.setTime((int) dateUtil.getZeroTime());
            UserConfig.INSTANCE.getInstance().setGoogleFitLastInfo(MoshiUtilsKt.toJson(googleFitDataBean));
            UserConfig.INSTANCE.getInstance().save();
            googleFitLastInfo = MoshiUtilsKt.toJson(googleFitDataBean);
        } else {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<GoogleFitDataBean>() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            if (((GoogleFitDataBean) jsonAdapterAdapter.fromJson(googleFitLastInfo)) != null && !new DateUtil(r2.getTime(), true).isToday()) {
                GoogleFitDataBean googleFitDataBean2 = new GoogleFitDataBean();
                googleFitDataBean2.setStep(0);
                googleFitDataBean2.setCalorie(0);
                googleFitDataBean2.setTime((int) dateUtil.getZeroTime());
                UserConfig.INSTANCE.getInstance().setGoogleFitLastInfo(MoshiUtilsKt.toJson(googleFitDataBean2));
                UserConfig.INSTANCE.getInstance().save();
                googleFitLastInfo = MoshiUtilsKt.toJson(googleFitDataBean2);
            }
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        JsonAdapter jsonAdapterAdapter2 = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<GoogleFitDataBean>() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$$inlined$fromJson$2
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        objectRef.element = jsonAdapterAdapter2.fromJson(googleFitLastInfo);
        XLog.i(objectRef.element);
        if (objectRef.element == 0) {
            ?? googleFitDataBean3 = new GoogleFitDataBean();
            googleFitDataBean3.setStep(0);
            googleFitDataBean3.setCalorie(0);
            googleFitDataBean3.setTime((int) dateUtil.getZeroTime());
            UserConfig.INSTANCE.getInstance().setGoogleFitLastInfo(MoshiUtilsKt.toJson(googleFitDataBean3));
            UserConfig.INSTANCE.getInstance().save();
            objectRef.element = googleFitDataBean3;
        } else if (dateUtil.getUnixTimestamp() - ((GoogleFitDataBean) objectRef.element).getTime() < 120) {
            XLog.i("Google Fit 两分钟内不同步");
            return;
        }
        ThreadExtKt.ktxRunOnBgSingle(this, new C06571(dateUtil, objectRef));
    }

    /* compiled from: GoogleFitSync.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$1, reason: invalid class name and case insensitive filesystem */
    static final class C06571 extends Lambda implements Function1<GoogleFitSync, Unit> {
        final /* synthetic */ Ref.ObjectRef<GoogleFitDataBean> $last;
        final /* synthetic */ DateUtil $todayDate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06571(DateUtil dateUtil, Ref.ObjectRef<GoogleFitDataBean> objectRef) {
            super(1);
            this.$todayDate = dateUtil;
            this.$last = objectRef;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GoogleFitSync googleFitSync) {
            invoke2(googleFitSync);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(GoogleFitSync ktxRunOnBgSingle) {
            String str;
            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
            long unixTimestamp = this.$todayDate.getUnixTimestamp();
            StepDetailRepository getInstance = StepDetailRepository.INSTANCE.getGetInstance();
            String y_m_d = this.$todayDate.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "todayDate.y_M_D");
            final StepTotal stepTotalQueryStepTotal = getInstance.queryStepTotal(y_m_d);
            XLog.i(this.$last.element);
            XLog.i(stepTotalQueryStepTotal);
            DataSource.Builder builder = new DataSource.Builder();
            Application application = QJavaApplication.getInstance().getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "getInstance().application");
            DataSource dataSourceBuild = builder.setAppPackageName(GlobalKt.getPackageName(application)).setDataType(DataType.AGGREGATE_STEP_COUNT_DELTA).setType(0).build();
            Intrinsics.checkNotNullExpressionValue(dataSourceBuild, "Builder()\n              …\n                .build()");
            try {
                ArrayList arrayList = new ArrayList();
                if (stepTotalQueryStepTotal == null) {
                    str = "builder(stepDataSource)\n…                 .build()";
                } else {
                    if (this.$last.element.getStep() > stepTotalQueryStepTotal.getStep()) {
                        return;
                    }
                    if (this.$last.element.getTime() == 0) {
                        this.$last.element.setTime((int) this.$todayDate.getZeroTime());
                    }
                    str = "builder(stepDataSource)\n…                 .build()";
                    DataPoint dataPointBuild = DataPoint.builder(dataSourceBuild).setField(Field.FIELD_STEPS, stepTotalQueryStepTotal.getStep() - this.$last.element.getStep()).setTimeInterval(this.$last.element.getTime(), unixTimestamp, TimeUnit.SECONDS).build();
                    Intrinsics.checkNotNullExpressionValue(dataPointBuild, str);
                    arrayList.add(dataPointBuild);
                }
                if (arrayList.size() <= 0) {
                    return;
                }
                final GoogleFitDataBean googleFitDataBean = new GoogleFitDataBean();
                if (stepTotalQueryStepTotal != null) {
                    googleFitDataBean.setStep(stepTotalQueryStepTotal.getStep());
                }
                if (stepTotalQueryStepTotal != null) {
                    googleFitDataBean.setCalorie(stepTotalQueryStepTotal.getCarolie() / 1000);
                }
                googleFitDataBean.setTime((int) unixTimestamp);
                DataSet dataSetBuild = DataSet.builder(dataSourceBuild).addAll(arrayList).build();
                Intrinsics.checkNotNullExpressionValue(dataSetBuild, str);
                Task<Void> taskInsertData = Fitness.getHistoryClient(QJavaApplication.getInstance().getApplication(), ktxRunOnBgSingle.getGoogleAccount()).insertData(dataSetBuild);
                final Ref.ObjectRef<GoogleFitDataBean> objectRef = this.$last;
                taskInsertData.addOnSuccessListener(new OnSuccessListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$1$$ExternalSyntheticLambda3
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        GoogleFitSync.C06571.m981invoke$lambda0(stepTotalQueryStepTotal, objectRef, googleFitDataBean, (Void) obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$1$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        GoogleFitSync.C06571.m982invoke$lambda1(exc);
                    }
                });
                DataSource.Builder builder2 = new DataSource.Builder();
                Application application2 = QJavaApplication.getInstance().getApplication();
                Intrinsics.checkNotNullExpressionValue(application2, "getInstance().application");
                DataSource dataSourceBuild2 = builder2.setAppPackageName(GlobalKt.getPackageName(application2)).setDataType(DataType.TYPE_CALORIES_EXPENDED).setType(0).build();
                Intrinsics.checkNotNullExpressionValue(dataSourceBuild2, "Builder()\n              …                 .build()");
                ArrayList arrayList2 = new ArrayList();
                DataPoint.Builder builder3 = DataPoint.builder(dataSourceBuild2);
                Field field = Field.FIELD_CALORIES;
                Intrinsics.checkNotNull(stepTotalQueryStepTotal);
                DataPoint dataPointBuild2 = builder3.setField(field, (stepTotalQueryStepTotal.getCarolie() / 1000) - this.$last.element.getCalorie()).setTimeInterval(this.$last.element.getTime(), unixTimestamp, TimeUnit.SECONDS).build();
                Intrinsics.checkNotNullExpressionValue(dataPointBuild2, "builder(calorieDataSourc…                 .build()");
                arrayList2.add(dataPointBuild2);
                DataSet dataSetBuild2 = DataSet.builder(dataSourceBuild2).addAll(arrayList2).build();
                Intrinsics.checkNotNullExpressionValue(dataSetBuild2, "builder(calorieDataSourc…                 .build()");
                Task<Void> taskInsertData2 = Fitness.getHistoryClient(QJavaApplication.getInstance().getApplication(), ktxRunOnBgSingle.getGoogleAccount()).insertData(dataSetBuild2);
                final Ref.ObjectRef<GoogleFitDataBean> objectRef2 = this.$last;
                taskInsertData2.addOnSuccessListener(new OnSuccessListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$1$$ExternalSyntheticLambda2
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        GoogleFitSync.C06571.m983invoke$lambda2(stepTotalQueryStepTotal, objectRef2, (Void) obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadStepAndCalorie2Fit$1$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        GoogleFitSync.C06571.m984invoke$lambda3(exc);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m981invoke$lambda0(StepTotal stepTotal, Ref.ObjectRef last, GoogleFitDataBean googleFitBean, Void r5) {
            Intrinsics.checkNotNullParameter(last, "$last");
            Intrinsics.checkNotNullParameter(googleFitBean, "$googleFitBean");
            if (stepTotal != null) {
                XLog.i("google fit 步数同步成功" + (stepTotal.getStep() - ((GoogleFitDataBean) last.element).getStep()));
                XLog.i(Integer.valueOf(stepTotal.getStep()));
                XLog.i(Integer.valueOf(((GoogleFitDataBean) last.element).getStep()));
                Objects.toString(QCApplication.INSTANCE.getCONTEXT().getExternalFilesDir(""));
                new com.oudmon.ble.base.util.DateUtil().getY_M_D();
                UserConfig.INSTANCE.getInstance().setGoogleFitLastInfo(MoshiUtilsKt.toJson(googleFitBean));
                UserConfig.INSTANCE.getInstance().save();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-1, reason: not valid java name */
        public static final void m982invoke$lambda1(Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
            StringBuilder sb = new StringBuilder();
            sb.append("googleFit: 步数同步失败");
            it.printStackTrace();
            sb.append(Unit.INSTANCE);
            XLog.i(sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: invoke$lambda-2, reason: not valid java name */
        public static final void m983invoke$lambda2(StepTotal stepTotal, Ref.ObjectRef last, Void r3) {
            Intrinsics.checkNotNullParameter(last, "$last");
            StringBuilder sb = new StringBuilder();
            sb.append("google fit 卡路里同步成功");
            Intrinsics.checkNotNull(stepTotal);
            sb.append((stepTotal.getCarolie() / 1000) - ((GoogleFitDataBean) last.element).getCalorie());
            XLog.i(sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-3, reason: not valid java name */
        public static final void m984invoke$lambda3(Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
            StringBuilder sb = new StringBuilder();
            sb.append("google Fit: 卡路里同步失败");
            it.printStackTrace();
            sb.append(Unit.INSTANCE);
            XLog.i(sb.toString());
        }
    }

    /* compiled from: GoogleFitSync.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadSleep2Fit$1, reason: invalid class name and case insensitive filesystem */
    static final class C06561 extends Lambda implements Function1<GoogleFitSync, Unit> {
        public static final C06561 INSTANCE = new C06561();

        C06561() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GoogleFitSync googleFitSync) {
            invoke2(googleFitSync);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(GoogleFitSync ktxRunOnBgSingle) {
            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
            try {
                DateUtil dateUtil = new DateUtil();
                SleepViewBean sleepViewBean = new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null);
                if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                    sleepViewBean = SleepDetailRepository.INSTANCE.getGetInstance().querySleepNewProtocol(new DateUtil());
                } else {
                    SleepDetailRepository getInstance = SleepDetailRepository.INSTANCE.getGetInstance();
                    String y_m_d = dateUtil.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "sleepDate.y_M_D");
                    SleepDetail sleepDetailQuerySleepByDate = getInstance.querySleepByDate(y_m_d);
                    DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                    dateUtil2.addDay(-1);
                    SleepDetailRepository getInstance2 = SleepDetailRepository.INSTANCE.getGetInstance();
                    String y_m_d2 = dateUtil2.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d2, "yesDate.y_M_D");
                    SleepDetail sleepDetailQuerySleepByDate2 = getInstance2.querySleepByDate(y_m_d2);
                    if (sleepDetailQuerySleepByDate2 == null) {
                        sleepDetailQuerySleepByDate2 = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                    }
                    if (sleepDetailQuerySleepByDate != null) {
                        sleepViewBean = SleepDetailRepository.INSTANCE.getGetInstance().calcSleepViewData(sleepDetailQuerySleepByDate, sleepDetailQuerySleepByDate2);
                    }
                }
                if (sleepViewBean.getTotalSleep() <= 0) {
                    return;
                }
                Session.Builder identifier = new Session.Builder().setName(dateUtil.getY_M_D()).setIdentifier(UserConfig.INSTANCE.getInstance().getDeviceNameNoClear());
                Application application = QJavaApplication.getInstance().getApplication();
                Intrinsics.checkNotNullExpressionValue(application, "getInstance().application");
                Session sessionBuild = identifier.setDescription(GlobalKt.getAppName(application)).setStartTime(sleepViewBean.getStartTime(), TimeUnit.SECONDS).setEndTime(sleepViewBean.getEndTime(), TimeUnit.SECONDS).setActivity(FitnessActivities.SLEEP).build();
                Intrinsics.checkNotNullExpressionValue(sessionBuild, "Builder().setName(sleepD…                 .build()");
                DataSource dataSourceBuild = new DataSource.Builder().setDataType(DataType.TYPE_SLEEP_SEGMENT).setType(0).setAppPackageName(QJavaApplication.getInstance().getApplication()).build();
                Intrinsics.checkNotNullExpressionValue(dataSourceBuild, "Builder()\n              …                 .build()");
                ArrayList arrayList = new ArrayList();
                XLog.i(sleepViewBean.getData());
                for (QSleepBarChart.SleepDataBean sleepDataBean : sleepViewBean.getData()) {
                    int type = sleepDataBean.getType();
                    if (type == 1) {
                        DataPoint dataPointBuild = DataPoint.builder(dataSourceBuild).setTimeInterval(sleepDataBean.getSleepStart(), sleepDataBean.getSleepEnd(), TimeUnit.SECONDS).setField(Field.FIELD_SLEEP_SEGMENT_TYPE, 5).build();
                        Intrinsics.checkNotNullExpressionValue(dataPointBuild, "builder(dataSource)\n    …                 .build()");
                        arrayList.add(dataPointBuild);
                    } else if (type == 2) {
                        DataPoint dataPointBuild2 = DataPoint.builder(dataSourceBuild).setTimeInterval(sleepDataBean.getSleepStart(), sleepDataBean.getSleepEnd(), TimeUnit.SECONDS).setField(Field.FIELD_SLEEP_SEGMENT_TYPE, 4).build();
                        Intrinsics.checkNotNullExpressionValue(dataPointBuild2, "builder(dataSource)\n    …                 .build()");
                        arrayList.add(dataPointBuild2);
                    } else if (type == 3) {
                        DataPoint dataPointBuild3 = DataPoint.builder(dataSourceBuild).setTimeInterval(sleepDataBean.getSleepStart(), sleepDataBean.getSleepEnd(), TimeUnit.SECONDS).setField(Field.FIELD_SLEEP_SEGMENT_TYPE, 1).build();
                        Intrinsics.checkNotNullExpressionValue(dataPointBuild3, "builder(dataSource)\n    …                 .build()");
                        arrayList.add(dataPointBuild3);
                    }
                }
                DataSet dataSetBuild = DataSet.builder(dataSourceBuild).addAll(arrayList).build();
                Intrinsics.checkNotNullExpressionValue(dataSetBuild, "builder(dataSource)\n    …                 .build()");
                SessionInsertRequest.Builder builder = new SessionInsertRequest.Builder();
                builder.addDataSet(dataSetBuild);
                builder.setSession(sessionBuild);
                SessionInsertRequest sessionInsertRequestBuild = builder.build();
                Intrinsics.checkNotNullExpressionValue(sessionInsertRequestBuild, "builder.build()");
                Fitness.getSessionsClient(QJavaApplication.getInstance().getApplication(), ktxRunOnBgSingle.getGoogleAccount()).insertSession(sessionInsertRequestBuild).addOnSuccessListener(new OnSuccessListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadSleep2Fit$1$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        XLog.i("google fit 睡眠成功");
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadSleep2Fit$1$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        GoogleFitSync.C06561.m977invoke$lambda2(exc);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-2, reason: not valid java name */
        public static final void m977invoke$lambda2(Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            XLog.i("google fit 睡眠失败: " + e.getLocalizedMessage());
        }
    }

    private final void uploadSleep2Fit() {
        ThreadExtKt.ktxRunOnBgSingle(this, C06561.INSTANCE);
    }

    private final void uploadHeart2Fit() {
        DataSource.Builder dataType = new DataSource.Builder().setType(0).setDataType(DataType.TYPE_HEART_RATE_BPM);
        Application application = QJavaApplication.getInstance().getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getInstance().application");
        DataSource dataSourceBuild = dataType.setAppPackageName(GlobalKt.getPackageName(application)).build();
        Intrinsics.checkNotNullExpressionValue(dataSourceBuild, "Builder().setType(DataSo…on))\n            .build()");
        ThreadExtKt.ktxRunOnBgSingle(this, new AnonymousClass1(dataSourceBuild));
    }

    /* compiled from: GoogleFitSync.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadHeart2Fit$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function1<GoogleFitSync, Unit> {
        final /* synthetic */ DataSource $dataSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DataSource dataSource) {
            super(1);
            this.$dataSource = dataSource;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GoogleFitSync googleFitSync) {
            invoke2(googleFitSync);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(GoogleFitSync ktxRunOnBgSingle) {
            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
            DateUtil dateUtil = new DateUtil();
            List<QHeartLineChartView.HeartDataBean> listQueryHeartDetail = HeartRateDetailRepository.INSTANCE.getGetInstance().queryHeartDetail(dateUtil);
            ArrayList arrayList = new ArrayList();
            Iterator<QHeartLineChartView.HeartDataBean> it = listQueryHeartDetail.iterator();
            while (it.hasNext()) {
                if (it.next().getValue() > 0) {
                    DataPoint dataPointBuild = DataPoint.builder(this.$dataSource).setField(Field.FIELD_BPM, r3.getValue()).setTimeInterval((r3.getMin() * 60) + dateUtil.getZeroTime(), 1 + dateUtil.getZeroTime() + (r3.getMin() * 60), TimeUnit.SECONDS).build();
                    Intrinsics.checkNotNullExpressionValue(dataPointBuild, "builder(dataSource)\n    …                 .build()");
                    arrayList.add(dataPointBuild);
                }
            }
            DataSet dataSetBuild = DataSet.builder(this.$dataSource).addAll(arrayList).build();
            Intrinsics.checkNotNullExpressionValue(dataSetBuild, "builder(dataSource)\n    …\n                .build()");
            try {
                if (dataSetBuild.isEmpty()) {
                    return;
                }
                Fitness.getHistoryClient(QJavaApplication.getInstance().getApplication(), ktxRunOnBgSingle.getGoogleAccount()).insertData(dataSetBuild).addOnSuccessListener(new OnSuccessListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadHeart2Fit$1$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        XLog.i("google Fit 心率插入成功--> ");
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync$uploadHeart2Fit$1$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        GoogleFitSync.AnonymousClass1.m975invoke$lambda1(exc);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                XLog.i("google Fit 心率插入异常--> ");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-1, reason: not valid java name */
        public static final void m975invoke$lambda1(Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
            StringBuilder sb = new StringBuilder();
            sb.append("google Fit 心率插入失败--> ");
            it.printStackTrace();
            sb.append(Unit.INSTANCE);
            XLog.i(sb.toString());
        }
    }

    /* compiled from: GoogleFitSync.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/mine/thirdSync/googlefit/GoogleFitSync;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GoogleFitSync getGetInstance() {
            return (GoogleFitSync) GoogleFitSync.getInstance$delegate.getValue();
        }
    }
}
