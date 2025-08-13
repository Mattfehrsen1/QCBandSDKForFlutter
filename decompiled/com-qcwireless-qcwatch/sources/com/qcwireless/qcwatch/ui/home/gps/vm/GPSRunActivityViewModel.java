package com.qcwireless.qcwatch.ui.home.gps.vm;

import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.repository.gps.GpsRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GPSRunActivityViewModel.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSRunActivityViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "gpsRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/gps/GpsRepository;)V", "saveGpsDetail", "", "entity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GPSRunActivityViewModel extends BaseViewModel {
    private final GpsRepository gpsRepository;

    public GPSRunActivityViewModel(GpsRepository gpsRepository) {
        Intrinsics.checkNotNullParameter(gpsRepository, "gpsRepository");
        this.gpsRepository = gpsRepository;
    }

    public final void saveGpsDetail(final GpsDetail entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GPSRunActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.vm.GPSRunActivityViewModel.saveGpsDetail.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GPSRunActivityViewModel gPSRunActivityViewModel) {
                invoke2(gPSRunActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GPSRunActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.gpsRepository.saveGpsDetail(entity);
            }
        });
    }
}
