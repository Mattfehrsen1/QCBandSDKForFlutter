package com.qcwireless.qcwatch.ui.home.temperature.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineHomeChartView;
import com.qcwireless.qcwatch.ui.home.temperature.bean.TemperatureDetailBean;
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

/* compiled from: TemperatureViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u0006\u0010\u001c\u001a\u00020\u001aJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0007J\u0006\u0010\u001f\u001a\u00020\u001aR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00128F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00128F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "temperatureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;)V", "_lastDate", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qc_utils/date/DateUtil;", "_uiDetail", "", "Lcom/qcwireless/qcwatch/ui/home/temperature/bean/TemperatureDetailBean;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel$TemperatureUI;", "detailList", "", "getDetailList", "()Ljava/util/List;", "lastDate", "Landroidx/lifecycle/LiveData;", "getLastDate", "()Landroidx/lifecycle/LiveData;", "uiDetail", "getUiDetail", "uiState", "getUiState", "getTemperatureByDate", "", "dateUtil", "queryLastDate", "queryTemperatureByDateDetail", "date", "syncTemperatureSingleCheck", "TemperatureUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureViewModel extends BaseViewModel {
    private final MutableLiveData<DateUtil> _lastDate;
    private final MutableLiveData<List<TemperatureDetailBean>> _uiDetail;
    private final MutableLiveData<TemperatureUI> _uiState;
    private final List<TemperatureDetailBean> detailList;
    private final TemperatureRepository temperatureRepository;

    public TemperatureViewModel(TemperatureRepository temperatureRepository) {
        Intrinsics.checkNotNullParameter(temperatureRepository, "temperatureRepository");
        this.temperatureRepository = temperatureRepository;
        this.detailList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._lastDate = new MutableLiveData<>();
        this._uiDetail = new MutableLiveData<>();
    }

    public final List<TemperatureDetailBean> getDetailList() {
        return this.detailList;
    }

    public final LiveData<TemperatureUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DateUtil> getLastDate() {
        return this._lastDate;
    }

    public final LiveData<List<TemperatureDetailBean>> getUiDetail() {
        return this._uiDetail;
    }

    public final void queryLastDate() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<TemperatureViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel.queryLastDate.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TemperatureViewModel temperatureViewModel) {
                invoke2(temperatureViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TemperatureViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<QTemperatureLineHomeChartView.TemperatureDataBean> listQueryTemperatureLast = ktxRunOnBgSingle.temperatureRepository.queryTemperatureLast();
                if (!listQueryTemperatureLast.isEmpty()) {
                    ktxRunOnBgSingle._lastDate.postValue(new DateUtil(listQueryTemperatureLast.get(0).getUnixTime(), true));
                } else {
                    ktxRunOnBgSingle._lastDate.postValue(new DateUtil());
                }
            }
        });
    }

    public final void getTemperatureByDate(final DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<TemperatureViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel.getTemperatureByDate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TemperatureViewModel temperatureViewModel) {
                invoke2(temperatureViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TemperatureViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiState.postValue(new TemperatureUI(ktxRunOnBgSingle.temperatureRepository.queryTemperatureByDate(dateUtil)));
            }
        });
    }

    public final void queryTemperatureByDateDetail(final DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<TemperatureViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel.queryTemperatureByDateDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TemperatureViewModel temperatureViewModel) {
                invoke2(temperatureViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TemperatureViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiDetail.postValue(ktxRunOnBgSingle.temperatureRepository.queryTemperatureDetailByDate(date));
            }
        });
    }

    /* compiled from: TemperatureViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel$syncTemperatureSingleCheck$1", f = "TemperatureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel$syncTemperatureSingleCheck$1, reason: invalid class name and case insensitive filesystem */
    static final class C06401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06401(Continuation<? super C06401> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TemperatureViewModel.this.new C06401(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TemperatureRepository temperatureRepository = TemperatureViewModel.this.temperatureRepository;
            temperatureRepository.initData();
            temperatureRepository.syncTodayTemperature();
            ThreadExtKt.ktxRunOnUiDelay(temperatureRepository, 500L, new Function1<TemperatureRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel$syncTemperatureSingleCheck$1$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TemperatureRepository temperatureRepository2) {
                    invoke2(temperatureRepository2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TemperatureRepository ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    ktxRunOnUiDelay.syncSingleCheckTemperature(0);
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void syncTemperatureSingleCheck() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06401(null), 3, null);
    }

    /* compiled from: TemperatureViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel$TemperatureUI;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QTemperatureLineChartView$TemperatureDataBean;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class TemperatureUI {
        private final List<QTemperatureLineChartView.TemperatureDataBean> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TemperatureUI copy$default(TemperatureUI temperatureUI, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = temperatureUI.data;
            }
            return temperatureUI.copy(list);
        }

        public final List<QTemperatureLineChartView.TemperatureDataBean> component1() {
            return this.data;
        }

        public final TemperatureUI copy(List<? extends QTemperatureLineChartView.TemperatureDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new TemperatureUI(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TemperatureUI) && Intrinsics.areEqual(this.data, ((TemperatureUI) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "TemperatureUI(data=" + this.data + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public TemperatureUI(List<? extends QTemperatureLineChartView.TemperatureDataBean> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<QTemperatureLineChartView.TemperatureDataBean> getData() {
            return this.data;
        }
    }
}
