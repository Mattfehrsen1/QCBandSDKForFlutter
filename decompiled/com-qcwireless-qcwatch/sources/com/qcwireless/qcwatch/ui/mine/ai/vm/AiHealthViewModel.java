package com.qcwireless.qcwatch.ui.mine.ai.vm;

import android.content.res.Resources;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AiHealthViewModel.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u000e\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)J\u0006\u0010+\u001a\u00020!J \u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020!H\u0002R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/vm/AiHealthViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "healthyRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;", "stepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;", "sleepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "heartDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;", "sportPlusRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "bloodPressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;", "bloodOxygenRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;", "temperatureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;", "bloodSugarRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;", "pressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "hrvRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;)V", "analysisFailLD", "Landroidx/lifecycle/MutableLiveData;", "", "getAnalysisFailLD", "()Landroidx/lifecycle/MutableLiveData;", "analysisLD", "", "getAnalysisLD", "noTargetDataLD", "getNoTargetDataLD", "formatText", "minute", "", "getChatGptHealthData", "", "getChatGptHealthDataCache", "getLanguageBySimpleName", "resExampleJson", "steps", "avgHeart", FitnessActivities.SLEEP, "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AiHealthViewModel extends BaseViewModel {
    private final MutableLiveData<Boolean> analysisFailLD;
    private final MutableLiveData<String> analysisLD;
    private final BloodOxygenRepository bloodOxygenRepository;
    private final BloodPressureRepository bloodPressureRepository;
    private final BloodSugarRepository bloodSugarRepository;
    private final HealthyRepository healthyRepository;
    private final HeartRateDetailRepository heartDetailRepository;
    private final HRVRepository hrvRepository;
    private final MutableLiveData<Boolean> noTargetDataLD;
    private final PressureRepository pressureRepository;
    private final SleepDetailRepository sleepDetailRepository;
    private final SportPlusRepository sportPlusRepository;
    private final StepDetailRepository stepDetailRepository;
    private final TemperatureRepository temperatureRepository;
    private final WatchFaceRepository watchFaceRepository;

    public AiHealthViewModel(HealthyRepository healthyRepository, StepDetailRepository stepDetailRepository, SleepDetailRepository sleepDetailRepository, HeartRateDetailRepository heartDetailRepository, SportPlusRepository sportPlusRepository, WatchFaceRepository watchFaceRepository, BloodPressureRepository bloodPressureRepository, BloodOxygenRepository bloodOxygenRepository, TemperatureRepository temperatureRepository, BloodSugarRepository bloodSugarRepository, PressureRepository pressureRepository, HRVRepository hrvRepository) {
        Intrinsics.checkNotNullParameter(healthyRepository, "healthyRepository");
        Intrinsics.checkNotNullParameter(stepDetailRepository, "stepDetailRepository");
        Intrinsics.checkNotNullParameter(sleepDetailRepository, "sleepDetailRepository");
        Intrinsics.checkNotNullParameter(heartDetailRepository, "heartDetailRepository");
        Intrinsics.checkNotNullParameter(sportPlusRepository, "sportPlusRepository");
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        Intrinsics.checkNotNullParameter(bloodPressureRepository, "bloodPressureRepository");
        Intrinsics.checkNotNullParameter(bloodOxygenRepository, "bloodOxygenRepository");
        Intrinsics.checkNotNullParameter(temperatureRepository, "temperatureRepository");
        Intrinsics.checkNotNullParameter(bloodSugarRepository, "bloodSugarRepository");
        Intrinsics.checkNotNullParameter(pressureRepository, "pressureRepository");
        Intrinsics.checkNotNullParameter(hrvRepository, "hrvRepository");
        this.healthyRepository = healthyRepository;
        this.stepDetailRepository = stepDetailRepository;
        this.sleepDetailRepository = sleepDetailRepository;
        this.heartDetailRepository = heartDetailRepository;
        this.sportPlusRepository = sportPlusRepository;
        this.watchFaceRepository = watchFaceRepository;
        this.bloodPressureRepository = bloodPressureRepository;
        this.bloodOxygenRepository = bloodOxygenRepository;
        this.temperatureRepository = temperatureRepository;
        this.bloodSugarRepository = bloodSugarRepository;
        this.pressureRepository = pressureRepository;
        this.hrvRepository = hrvRepository;
        this.analysisLD = new MutableLiveData<>();
        this.analysisFailLD = new MutableLiveData<>();
        this.noTargetDataLD = new MutableLiveData<>();
    }

    public final MutableLiveData<String> getAnalysisLD() {
        return this.analysisLD;
    }

    public final MutableLiveData<Boolean> getAnalysisFailLD() {
        return this.analysisFailLD;
    }

    public final MutableLiveData<Boolean> getNoTargetDataLD() {
        return this.noTargetDataLD;
    }

    /* compiled from: AiHealthViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel$getChatGptHealthDataCache$1", f = "AiHealthViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel$getChatGptHealthDataCache$1, reason: invalid class name and case insensitive filesystem */
    static final class C06421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06421(Continuation<? super C06421> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AiHealthViewModel.this.new C06421(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AiHealthViewModel.this.getAnalysisLD().postValue(UserConfig.INSTANCE.getInstance().getLastAnalyzeResultJson());
            return Unit.INSTANCE;
        }
    }

    public final void getChatGptHealthDataCache() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C06421(null), 2, null);
    }

    /* compiled from: AiHealthViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel$getChatGptHealthData$1", f = "AiHealthViewModel.kt", i = {}, l = {123, 141, 141}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel$getChatGptHealthData$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AiHealthViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x0241 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instructions count: 581
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void getChatGptHealthData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    public final String formatText(int minute) {
        String string;
        String string2;
        String string3;
        int i = minute / 60;
        int i2 = minute % 60;
        if (i == 0) {
            if (i2 < 10) {
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(i2);
                string3 = sb.toString();
            } else {
                string3 = i2 + "";
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(GlobalKt.getString(R.string.qc_text_8053), Arrays.copyOf(new Object[]{string3}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            return str;
        }
        if (i < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(i);
            string = sb2.toString();
        } else {
            string = i + "";
        }
        if (i2 < 10) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append('0');
            sb3.append(i2);
            string2 = sb3.toString();
        } else {
            string2 = i2 + "";
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String str2 = String.format(GlobalKt.getString(R.string.qc_text_8052), Arrays.copyOf(new Object[]{string, string2}, 2));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String resExampleJson(int steps, int avgHeart, String sleep) throws Resources.NotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"score\": 0,\n  \"createTime\":\"\",\n  \"riskWarning\":\"\",\n  \"comprehensiveOptimization\":\"\",\n  \"summarize\":\"\",\n  \"listData\": [\n    {\n      \"title\": \"🏃\u200d♂️ ");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(GlobalKt.getString(R.string.qc_ai_text_10), Arrays.copyOf(new Object[]{steps + GlobalKt.getString(R.string.qc_text_82)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        sb.append(str);
        sb.append("\",\n      \"content\": \"\",\n      \"suggestion\":\"\"\n    },\n    {\n      \"title\": \"❤️ ");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String str2 = String.format(GlobalKt.getString(R.string.qc_ai_text_11), Arrays.copyOf(new Object[]{avgHeart + GlobalKt.getString(R.string.qc_text_89)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        sb.append(str2);
        sb.append("\",\n      \"content\": \"\",\n      \"suggestion\":\"\"\n    },\n    {\n      \"title\": \"🌙 ");
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String str3 = String.format(GlobalKt.getString(R.string.qc_ai_text_12), Arrays.copyOf(new Object[]{sleep}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
        sb.append(str3);
        sb.append("\",\n      \"content\": \"\",\n      \"suggestion\":\"\"\n    }\n  ]\n}");
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01dd A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x034f A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getLanguageBySimpleName() {
        /*
            Method dump skipped, instructions count: 1070
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel.getLanguageBySimpleName():java.lang.String");
    }
}
