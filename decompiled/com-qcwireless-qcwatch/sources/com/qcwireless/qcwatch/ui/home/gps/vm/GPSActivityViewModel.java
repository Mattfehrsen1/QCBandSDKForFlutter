package com.qcwireless.qcwatch.ui.home.gps.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.repository.gps.GpsRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GPSActivityViewModel.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSActivityViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "gpsRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;)V", "_gpsDetail", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "_uiState", "", "gpsDetail", "Landroidx/lifecycle/LiveData;", "getGpsDetail", "()Landroidx/lifecycle/LiveData;", "uiState", "getUiState", "queryByStartTime", "", "startTime", "", "queryTotalDistance", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GPSActivityViewModel extends BaseViewModel {
    private final MutableLiveData<GpsDetail> _gpsDetail;
    private final MutableLiveData<String> _uiState;
    private final GpsRepository gpsRepository;

    public GPSActivityViewModel(GpsRepository gpsRepository) {
        Intrinsics.checkNotNullParameter(gpsRepository, "gpsRepository");
        this.gpsRepository = gpsRepository;
        this._uiState = new MutableLiveData<>();
        this._gpsDetail = new MutableLiveData<>();
    }

    public final LiveData<String> getUiState() {
        return this._uiState;
    }

    public final LiveData<GpsDetail> getGpsDetail() {
        return this._gpsDetail;
    }

    public final void queryTotalDistance() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GPSActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel.queryTotalDistance.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GPSActivityViewModel gPSActivityViewModel) {
                invoke2(gPSActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GPSActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiState.postValue(ktxRunOnBgSingle.gpsRepository.queryTotalDistance());
            }
        });
    }

    public final void queryByStartTime(final long startTime) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GPSActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel.queryByStartTime.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GPSActivityViewModel gPSActivityViewModel) {
                invoke2(gPSActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GPSActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                GpsDetail gpsDetailQueryGpsDetailByStartTime = ktxRunOnBgSingle.gpsRepository.queryGpsDetailByStartTime(startTime);
                if (gpsDetailQueryGpsDetailByStartTime != null) {
                    ktxRunOnBgSingle._gpsDetail.postValue(gpsDetailQueryGpsDetailByStartTime);
                }
            }
        });
    }
}
