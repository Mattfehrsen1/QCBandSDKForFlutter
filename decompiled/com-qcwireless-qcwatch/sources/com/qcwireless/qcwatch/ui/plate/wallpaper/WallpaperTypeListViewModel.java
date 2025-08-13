package com.qcwireless.qcwatch.ui.plate.wallpaper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.plate.bean.DialBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WallpaperTypeListViewModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperTypeListViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "_typeUi", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/DialBean;", "marketList", "getMarketList", "()Ljava/util/List;", "typeUi", "Landroidx/lifecycle/LiveData;", "getTypeUi", "()Landroidx/lifecycle/LiveData;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WallpaperTypeListViewModel extends BaseViewModel {
    private final MutableLiveData<List<DialBean>> _typeUi;
    private final List<DialBean> marketList;
    private final WatchFaceRepository watchFaceRepository;

    public WallpaperTypeListViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this.marketList = new ArrayList();
        this._typeUi = new MutableLiveData<>();
    }

    public final List<DialBean> getMarketList() {
        return this.marketList;
    }

    public final LiveData<List<DialBean>> getTypeUi() {
        return this._typeUi;
    }
}
