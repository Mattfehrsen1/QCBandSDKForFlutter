package com.qcwireless.qcwatch.ui.home.menstruation;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.haibin.calendarview.Calendar;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.MenstruationReq;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.MenstruationEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository;
import com.qcwireless.qcwatch.ui.home.menstruation.bean.MenstruationBean;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import skin.support.content.res.SkinCompatResources;

/* compiled from: MenstruationViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010%\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u000289B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020\u001eJ0\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020\fH\u0002J\u0006\u00100\u001a\u00020\u001eJ\u0006\u00101\u001a\u00020\u001eJ\u000e\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020&J4\u00104\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020)062\u0006\u00107\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u00188F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001a¨\u0006:"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "repository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/MenstruationRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/MenstruationRepository;)V", "_uiShowState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel$MenstruationShowUI;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel$MenstruationSettingUI;", "alarmList", "", "", "getAlarmList", "()Ljava/util/List;", "setAlarmList", "(Ljava/util/List;)V", "cycleList", "getCycleList", "setCycleList", "duringList", "getDuringList", "setDuringList", "uiShowState", "Landroidx/lifecycle/LiveData;", "getUiShowState", "()Landroidx/lifecycle/LiveData;", "uiState", "getUiState", "addMenstruationData", "", "context", "Landroid/content/Context;", "d", "Lcom/qcwireless/qc_utils/date/DateUtil;", "check", "", "bean", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "execToDevice", "getSchemeCalendar", "Lcom/haibin/calendarview/Calendar;", "year", "", "month", "day", TypedValues.Custom.S_COLOR, "text", "initData", "queryMenstruationSetting", "saveMenstruationSetting", "menstruationBean", "showCurrMonth", "map", "", "lastAuntDate", "MenstruationSettingUI", "MenstruationShowUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MenstruationViewModel extends BaseViewModel {
    private final MutableLiveData<MenstruationShowUI> _uiShowState;
    private final MutableLiveData<MenstruationSettingUI> _uiState;
    private List<String> alarmList;
    private List<String> cycleList;
    private List<String> duringList;
    private final MenstruationRepository repository;

    public MenstruationViewModel(MenstruationRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.duringList = new ArrayList();
        this.cycleList = new ArrayList();
        this.alarmList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._uiShowState = new MutableLiveData<>();
    }

    public final List<String> getDuringList() {
        return this.duringList;
    }

    public final void setDuringList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.duringList = list;
    }

    public final List<String> getCycleList() {
        return this.cycleList;
    }

    public final void setCycleList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.cycleList = list;
    }

    public final List<String> getAlarmList() {
        return this.alarmList;
    }

    public final void setAlarmList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.alarmList = list;
    }

    public final LiveData<MenstruationSettingUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<MenstruationShowUI> getUiShowState() {
        return this._uiShowState;
    }

    public final void initData() {
        this.alarmList.clear();
        for (int i = 1; i < 4; i++) {
            this.alarmList.add(String.valueOf(i));
        }
        for (int i2 = 2; i2 < 11; i2++) {
            this.duringList.add(String.valueOf(i2));
        }
        for (int i3 = 20; i3 < 36; i3++) {
            this.cycleList.add(String.valueOf(i3));
        }
    }

    /* compiled from: MenstruationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel$queryMenstruationSetting$1", f = "MenstruationViewModel.kt", i = {}, l = {63, 64}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel$queryMenstruationSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C06251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06251(Continuation<? super C06251> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MenstruationViewModel.this.new C06251(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = MenstruationViewModel.this.repository.getMenstruationSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final MenstruationViewModel menstruationViewModel = MenstruationViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel.queryMenstruationSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((MenstruationBean) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(MenstruationBean menstruationBean, Continuation<? super Unit> continuation) {
                    menstruationViewModel._uiState.postValue(menstruationBean != null ? new MenstruationSettingUI(menstruationBean) : null);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryMenstruationSetting() {
        launchOnUI(new C06251(null));
    }

    /* compiled from: MenstruationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel$execToDevice$1", f = "MenstruationViewModel.kt", i = {}, l = {73, 74}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel$execToDevice$1, reason: invalid class name and case insensitive filesystem */
    static final class C06241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06241(Continuation<? super C06241> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MenstruationViewModel.this.new C06241(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = MenstruationViewModel.this.repository.getMenstruationSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final MenstruationViewModel menstruationViewModel = MenstruationViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel.execToDevice.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((MenstruationBean) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(MenstruationBean menstruationBean, Continuation<? super Unit> continuation) {
                    MenstruationEntity menstruationEntityQueryMaxStartTime;
                    if (menstruationBean != null && (menstruationEntityQueryMaxStartTime = menstruationViewModel.repository.queryMaxStartTime()) != null) {
                        DateUtil dateUtil = new DateUtil(menstruationEntityQueryMaxStartTime.getStartTime(), true);
                        DateUtil dateUtil2 = new DateUtil(menstruationEntityQueryMaxStartTime.getEndTime(), true);
                        int iDaysBetweenMe = new DateUtil().daysBetweenMe(dateUtil);
                        int iDaysBetweenMe2 = new DateUtil().daysBetweenMe(dateUtil2);
                        XLog.i("-----" + menstruationBean.getMenstruationAlarm());
                        CommandHandle.getInstance().executeReqCmdNoCallback(new MenstruationReq(true, menstruationBean.getDuring(), menstruationBean.getCycle(), iDaysBetweenMe, iDaysBetweenMe2, menstruationBean.getMenstruationAlarm(), menstruationBean.getAlarm1(), menstruationBean.getAlarm2(), menstruationBean.getAlarmMin() / 60, menstruationBean.getAlarmMin() % 60));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void execToDevice() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06241(null), 3, null);
    }

    public final void saveMenstruationSetting(MenstruationBean menstruationBean) {
        Intrinsics.checkNotNullParameter(menstruationBean, "menstruationBean");
        this.repository.addMenstruationFromLastTime(menstruationBean);
    }

    public final void addMenstruationData(final Context context, final DateUtil d, final boolean check, final MenstruationBean bean) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(d, "d");
        Intrinsics.checkNotNullParameter(bean, "bean");
        ThreadUtils.postDelay(new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel.addMenstruationData.1
            @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
            protected void task() {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                boolean zAddMenstruationData = MenstruationViewModel.this.repository.addMenstruationData(d, check, bean);
                List<DateUtil> listLoadMonthData = MenstruationViewModel.this.repository.loadMonthData(d.getYear(), d.getMonth());
                MenstruationEntity menstruationEntityQueryMaxStartTime = MenstruationViewModel.this.repository.queryMaxStartTime();
                if (menstruationEntityQueryMaxStartTime != null) {
                    MenstruationViewModel.this.showCurrMonth(context, linkedHashMap, new DateUtil(menstruationEntityQueryMaxStartTime.getStartTime(), true), bean);
                }
                List<DateUtil> listLoadPreData = MenstruationViewModel.this.repository.loadPreData();
                if (listLoadPreData != null && (!listLoadPreData.isEmpty())) {
                    int size = listLoadPreData.size();
                    for (int i = 0; i < size; i++) {
                        DateUtil dateUtil = listLoadPreData.get(i);
                        String string = MenstruationViewModel.this.getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_1), "1").toString();
                        Intrinsics.checkNotNullExpressionValue(string, "getSchemeCalendar(d2.yea…_show_1), \"1\").toString()");
                        linkedHashMap.put(string, MenstruationViewModel.this.getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_1), "1"));
                    }
                }
                if (listLoadMonthData != null && (!listLoadMonthData.isEmpty())) {
                    int size2 = listLoadMonthData.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        DateUtil dateUtil2 = listLoadMonthData.get(i2);
                        String string2 = MenstruationViewModel.this.getSchemeCalendar(dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_2), "3").toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "getSchemeCalendar(d2.yea…_show_2), \"3\").toString()");
                        linkedHashMap.put(string2, MenstruationViewModel.this.getSchemeCalendar(dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_2), "3"));
                    }
                }
                MenstruationViewModel.this._uiShowState.postValue(new MenstruationShowUI(zAddMenstruationData, linkedHashMap));
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCurrMonth(Context context, Map<String, Calendar> map, DateUtil lastAuntDate, MenstruationBean bean) {
        int cycle = bean.getCycle();
        int during = bean.getDuring();
        boolean z = false;
        int i = 0;
        while (i < 36) {
            int i2 = i + 1;
            new DateUtil(lastAuntDate.getTimestamp(), false).addDay(cycle * i2);
            new DateUtil().setDay(1);
            DateUtil dateUtil = new DateUtil(lastAuntDate.getTimestamp(), false);
            dateUtil.addDay(i * cycle);
            dateUtil.addDay(-19);
            for (int i3 = 0; i3 < 10; i3++) {
                String string = getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_1), "1").toString();
                Intrinsics.checkNotNullExpressionValue(string, "getSchemeCalendar(d2.yea…_show_1), \"1\").toString()");
                map.put(string, getSchemeCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_1), "1"));
                dateUtil.addDay(1);
            }
            i = i2;
        }
        int i4 = 0;
        while (i4 < 36) {
            DateUtil dateUtil2 = new DateUtil(lastAuntDate.getTimestamp(), z);
            i4++;
            dateUtil2.addDay(cycle * i4);
            int i5 = 0;
            while (i5 < during) {
                String string2 = getSchemeCalendar(dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_2), "3").toString();
                Intrinsics.checkNotNullExpressionValue(string2, "getSchemeCalendar(d1.yea…_show_2), \"3\").toString()");
                map.put(string2, getSchemeCalendar(dateUtil2.getYear(), dateUtil2.getMonth(), dateUtil2.getDay(), SkinCompatResources.getColor(context, R.color.menstruation_show_2), "3"));
                dateUtil2.addDay(1);
                i5++;
                z = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);
        calendar.setScheme(text);
        return calendar;
    }

    /* compiled from: MenstruationViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel$MenstruationSettingUI;", "", "entity", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "(Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;)V", "getEntity", "()Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class MenstruationSettingUI {
        private final MenstruationBean entity;

        public static /* synthetic */ MenstruationSettingUI copy$default(MenstruationSettingUI menstruationSettingUI, MenstruationBean menstruationBean, int i, Object obj) {
            if ((i & 1) != 0) {
                menstruationBean = menstruationSettingUI.entity;
            }
            return menstruationSettingUI.copy(menstruationBean);
        }

        /* renamed from: component1, reason: from getter */
        public final MenstruationBean getEntity() {
            return this.entity;
        }

        public final MenstruationSettingUI copy(MenstruationBean entity) {
            Intrinsics.checkNotNullParameter(entity, "entity");
            return new MenstruationSettingUI(entity);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MenstruationSettingUI) && Intrinsics.areEqual(this.entity, ((MenstruationSettingUI) other).entity);
        }

        public int hashCode() {
            return this.entity.hashCode();
        }

        public String toString() {
            return "MenstruationSettingUI(entity=" + this.entity + ')';
        }

        public MenstruationSettingUI(MenstruationBean entity) {
            Intrinsics.checkNotNullParameter(entity, "entity");
            this.entity = entity;
        }

        public final MenstruationBean getEntity() {
            return this.entity;
        }
    }

    /* compiled from: MenstruationViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel$MenstruationShowUI;", "", "comeOrGo", "", "map", "", "", "Lcom/haibin/calendarview/Calendar;", "(ZLjava/util/Map;)V", "getComeOrGo", "()Z", "getMap", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class MenstruationShowUI {
        private final boolean comeOrGo;
        private final Map<String, Calendar> map;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MenstruationShowUI copy$default(MenstruationShowUI menstruationShowUI, boolean z, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                z = menstruationShowUI.comeOrGo;
            }
            if ((i & 2) != 0) {
                map = menstruationShowUI.map;
            }
            return menstruationShowUI.copy(z, map);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getComeOrGo() {
            return this.comeOrGo;
        }

        public final Map<String, Calendar> component2() {
            return this.map;
        }

        public final MenstruationShowUI copy(boolean comeOrGo, Map<String, Calendar> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            return new MenstruationShowUI(comeOrGo, map);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MenstruationShowUI)) {
                return false;
            }
            MenstruationShowUI menstruationShowUI = (MenstruationShowUI) other;
            return this.comeOrGo == menstruationShowUI.comeOrGo && Intrinsics.areEqual(this.map, menstruationShowUI.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.comeOrGo;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.map.hashCode();
        }

        public String toString() {
            return "MenstruationShowUI(comeOrGo=" + this.comeOrGo + ", map=" + this.map + ')';
        }

        public MenstruationShowUI(boolean z, Map<String, Calendar> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            this.comeOrGo = z;
            this.map = map;
        }

        public final boolean getComeOrGo() {
            return this.comeOrGo;
        }

        public final Map<String, Calendar> getMap() {
            return this.map;
        }
    }
}
