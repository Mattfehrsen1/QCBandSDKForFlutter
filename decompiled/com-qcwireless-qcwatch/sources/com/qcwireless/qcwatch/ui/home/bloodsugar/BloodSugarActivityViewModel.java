package com.qcwireless.qcwatch.ui.home.bloodsugar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodSugarEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository;
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartView;
import com.qcwireless.qcwatch.ui.home.bloodsugar.bean.BloodSugarDetailBean;
import java.util.ArrayList;
import java.util.List;
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
import org.greenrobot.eventbus.EventBus;

/* compiled from: BloodSugarActivityViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002 !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u0006\u0010\u001d\u001a\u00020\u001aJ\u0006\u0010\u001e\u001a\u00020\u001aJ\u0006\u0010\u001f\u001a\u00020\u001aR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00128F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00128F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "bloodSugarRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;)V", "_lastDate", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiDetail", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel$BloodSugarDetail;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel$BloodSugarUI;", "detailList", "", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/bean/BloodSugarDetailBean;", "getDetailList", "()Ljava/util/List;", "lastDate", "Landroidx/lifecycle/LiveData;", "getLastDate", "()Landroidx/lifecycle/LiveData;", "uiDetail", "getUiDetail", "uiState", "getUiState", "queryBloodSugarByDate", "", "date", "queryBloodSugarByDateDetail", "queryLastData", "syncBloodSugarSugar", "syncTodayData", "BloodSugarDetail", "BloodSugarUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodSugarActivityViewModel extends BaseViewModel {
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<BloodSugarDetail> _uiDetail;
    private final MutableLiveData<BloodSugarUI> _uiState;
    private final BloodSugarRepository bloodSugarRepository;
    private final List<BloodSugarDetailBean> detailList;

    public BloodSugarActivityViewModel(BloodSugarRepository bloodSugarRepository) {
        Intrinsics.checkNotNullParameter(bloodSugarRepository, "bloodSugarRepository");
        this.bloodSugarRepository = bloodSugarRepository;
        this.detailList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
        this._uiDetail = new MutableLiveData<>();
    }

    public final List<BloodSugarDetailBean> getDetailList() {
        return this.detailList;
    }

    public final LiveData<BloodSugarUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final LiveData<BloodSugarDetail> getUiDetail() {
        return this._uiDetail;
    }

    public final void queryBloodSugarByDate(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodSugarActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel.queryBloodSugarByDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodSugarActivityViewModel bloodSugarActivityViewModel) {
                invoke2(bloodSugarActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BloodSugarActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<QBloodSugarLineChartView.DataBean> listQueryBloodSugarByDate = ktxRunOnBgSingle.bloodSugarRepository.queryBloodSugarByDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), date);
                if (date.isToday()) {
                    ktxRunOnBgSingle._uiState.postValue(new BloodSugarUI(true, listQueryBloodSugarByDate));
                } else {
                    ktxRunOnBgSingle._uiState.postValue(new BloodSugarUI(false, listQueryBloodSugarByDate));
                }
            }
        });
    }

    public final void queryBloodSugarByDateDetail(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodSugarActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel.queryBloodSugarByDateDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BloodSugarActivityViewModel bloodSugarActivityViewModel) {
                invoke2(bloodSugarActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BloodSugarActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiDetail.postValue(new BloodSugarDetail(ktxRunOnBgSingle.bloodSugarRepository.queryBloodSugarByDateDetail(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), date)));
            }
        });
    }

    /* compiled from: BloodSugarActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel$queryLastData$1", f = "BloodSugarActivityViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel$queryLastData$1, reason: invalid class name and case insensitive filesystem */
    static final class C05671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05671(Continuation<? super C05671> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BloodSugarActivityViewModel.this.new C05671(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BloodSugarEntity bloodSugarEntityQueryLastBloodSugarDate = BloodSugarActivityViewModel.this.bloodSugarRepository.queryLastBloodSugarDate();
            if (bloodSugarEntityQueryLastBloodSugarDate == null) {
                BloodSugarActivityViewModel.this._lastDate.postValue(new DateUtil());
            } else {
                BloodSugarActivityViewModel.this._lastDate.postValue(new DateUtil(bloodSugarEntityQueryLastBloodSugarDate.getUnixTime(), true));
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryLastData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05671(null), 3, null);
    }

    public final void syncBloodSugarSugar() throws InterruptedException {
        this.bloodSugarRepository.syncBloodSugar(255, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel.syncBloodSugarSugar.1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadBlePressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
            }
        });
    }

    public final void syncTodayData() throws InterruptedException {
        this.bloodSugarRepository.syncBloodSugar(0, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel$syncTodayData$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadBlePressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                final BloodSugarActivityViewModel bloodSugarActivityViewModel = this.this$0;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<BloodSugarActivityViewModel$syncTodayData$1$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel$syncTodayData$1$1$result$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodSugarActivityViewModel$syncTodayData$1$1 bloodSugarActivityViewModel$syncTodayData$1$1) {
                        invoke2(bloodSugarActivityViewModel$syncTodayData$1$1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodSugarActivityViewModel$syncTodayData$1$1 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        bloodSugarActivityViewModel.queryBloodSugarByDateDetail(new DateUtil());
                    }
                });
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    /* compiled from: BloodSugarActivityViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel$BloodSugarUI;", "", "today", "", "listData", "", "Lcom/qcwireless/qcwatch/ui/base/view/QBloodSugarLineChartView$DataBean;", "(ZLjava/util/List;)V", "getListData", "()Ljava/util/List;", "getToday", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BloodSugarUI {
        private final List<QBloodSugarLineChartView.DataBean> listData;
        private final boolean today;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BloodSugarUI copy$default(BloodSugarUI bloodSugarUI, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = bloodSugarUI.today;
            }
            if ((i & 2) != 0) {
                list = bloodSugarUI.listData;
            }
            return bloodSugarUI.copy(z, list);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getToday() {
            return this.today;
        }

        public final List<QBloodSugarLineChartView.DataBean> component2() {
            return this.listData;
        }

        public final BloodSugarUI copy(boolean today, List<? extends QBloodSugarLineChartView.DataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            return new BloodSugarUI(today, listData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BloodSugarUI)) {
                return false;
            }
            BloodSugarUI bloodSugarUI = (BloodSugarUI) other;
            return this.today == bloodSugarUI.today && Intrinsics.areEqual(this.listData, bloodSugarUI.listData);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.today;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.listData.hashCode();
        }

        public String toString() {
            return "BloodSugarUI(today=" + this.today + ", listData=" + this.listData + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BloodSugarUI(boolean z, List<? extends QBloodSugarLineChartView.DataBean> listData) {
            Intrinsics.checkNotNullParameter(listData, "listData");
            this.today = z;
            this.listData = listData;
        }

        public final boolean getToday() {
            return this.today;
        }

        public final List<QBloodSugarLineChartView.DataBean> getListData() {
            return this.listData;
        }
    }

    /* compiled from: BloodSugarActivityViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel$BloodSugarDetail;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/bean/BloodSugarDetailBean;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BloodSugarDetail {
        private final List<BloodSugarDetailBean> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BloodSugarDetail copy$default(BloodSugarDetail bloodSugarDetail, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = bloodSugarDetail.data;
            }
            return bloodSugarDetail.copy(list);
        }

        public final List<BloodSugarDetailBean> component1() {
            return this.data;
        }

        public final BloodSugarDetail copy(List<BloodSugarDetailBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new BloodSugarDetail(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BloodSugarDetail) && Intrinsics.areEqual(this.data, ((BloodSugarDetail) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "BloodSugarDetail(data=" + this.data + ')';
        }

        public BloodSugarDetail(List<BloodSugarDetailBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<BloodSugarDetailBean> getData() {
            return this.data;
        }
    }
}
