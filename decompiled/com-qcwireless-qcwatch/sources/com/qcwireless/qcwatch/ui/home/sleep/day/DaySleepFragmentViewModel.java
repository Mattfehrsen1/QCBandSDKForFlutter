package com.qcwireless.qcwatch.ui.home.sleep.day;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.SleepDetail;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepLunchBean;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DaySleepFragmentViewModel.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "sleepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;)V", "_lastDate", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiLunchState", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepLunchBean;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;", "lastDate", "Landroidx/lifecycle/LiveData;", "getLastDate", "()Landroidx/lifecycle/LiveData;", "uiLunchState", "getUiLunchState", "uiState", "getUiState", "queryLastData", "", "showDaySleepLunch", "dateUtil", "showDaySleepView", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DaySleepFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<SleepLunchBean> _uiLunchState;
    private final MutableLiveData<SleepViewBean> _uiState;
    private final SleepDetailRepository sleepDetailRepository;

    public DaySleepFragmentViewModel(SleepDetailRepository sleepDetailRepository) {
        Intrinsics.checkNotNullParameter(sleepDetailRepository, "sleepDetailRepository");
        this.sleepDetailRepository = sleepDetailRepository;
        this._uiState = new MutableLiveData<>();
        this._uiLunchState = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
    }

    public final LiveData<SleepViewBean> getUiState() {
        return this._uiState;
    }

    public final LiveData<SleepLunchBean> getUiLunchState() {
        return this._uiLunchState;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final void showDaySleepLunch(final DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DaySleepFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragmentViewModel.showDaySleepLunch.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DaySleepFragmentViewModel daySleepFragmentViewModel) {
                invoke2(daySleepFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DaySleepFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiLunchState.postValue(ktxRunOnBgSingle.sleepDetailRepository.querySleepLunchProtocol(dateUtil));
            }
        });
    }

    public final void showDaySleepView(final DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DaySleepFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragmentViewModel.showDaySleepView.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DaySleepFragmentViewModel daySleepFragmentViewModel) {
                invoke2(daySleepFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DaySleepFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                    ktxRunOnBgSingle._uiState.postValue(ktxRunOnBgSingle.sleepDetailRepository.querySleepNewProtocol(dateUtil));
                    return;
                }
                SleepDetailRepository sleepDetailRepository = ktxRunOnBgSingle.sleepDetailRepository;
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
                SleepDetail sleepDetailQuerySleepByDate = sleepDetailRepository.querySleepByDate(y_m_d);
                XLog.i(dateUtil.getY_M_D());
                DateUtil dateUtil2 = new DateUtil(dateUtil.getUnixTimestamp(), true);
                dateUtil2.addDay(-1);
                SleepDetailRepository sleepDetailRepository2 = ktxRunOnBgSingle.sleepDetailRepository;
                String y_m_d2 = dateUtil2.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "yesDate.y_M_D");
                SleepDetail sleepDetailQuerySleepByDate2 = sleepDetailRepository2.querySleepByDate(y_m_d2);
                if (sleepDetailQuerySleepByDate == null) {
                    ktxRunOnBgSingle._uiState.postValue(new SleepViewBean(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null));
                }
                if (sleepDetailQuerySleepByDate2 == null) {
                    sleepDetailQuerySleepByDate2 = new SleepDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), "", TypedValues.Custom.TYPE_INT, "", "", false, new DateUtil().getUnixTimestamp());
                }
                if (sleepDetailQuerySleepByDate != null) {
                    ktxRunOnBgSingle._uiState.postValue(ktxRunOnBgSingle.sleepDetailRepository.calcSleepViewData(sleepDetailQuerySleepByDate, sleepDetailQuerySleepByDate2));
                }
            }
        });
    }

    /* compiled from: DaySleepFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragmentViewModel$queryLastData$1", f = "DaySleepFragmentViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragmentViewModel$queryLastData$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DaySleepFragmentViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                if (DaySleepFragmentViewModel.this.sleepDetailRepository.queryLastSleepNewProtocol() == null) {
                    DaySleepFragmentViewModel.this._lastDate.postValue(new DateUtil());
                } else {
                    DateUtil dateUtil = new DateUtil(r4.getEt(), true);
                    XLog.i(dateUtil.getY_M_D());
                    DaySleepFragmentViewModel.this._lastDate.postValue(dateUtil);
                }
            } else {
                SleepDetail sleepDetailQueryLastSleep = DaySleepFragmentViewModel.this.sleepDetailRepository.queryLastSleep();
                if (sleepDetailQueryLastSleep == null) {
                    DaySleepFragmentViewModel.this._lastDate.postValue(new DateUtil());
                } else {
                    DaySleepFragmentViewModel.this._lastDate.postValue(new DateUtil(DateUtil.String2Date("yyyy-MM-dd", sleepDetailQueryLastSleep.getDateStr())));
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryLastData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }
}
