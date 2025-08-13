package com.qcwireless.qcwatch.ui.plate.market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.blankj.utilcode.constant.TimeConstants;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceDownloadProgressEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceInstallSuccessEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceRefreshEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.FragmentWatchMarketBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import com.qcwireless.qcwatch.ui.plate.adapter.DeviceWatchFaceListAdapter;
import com.qcwireless.qcwatch.ui.plate.adapter.MarketWatchFaceListAdapter;
import com.qcwireless.qcwatch.ui.plate.bean.DeviceWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.bean.MarketWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WatchMarketFragment.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 62\u00020\u0001:\u000267B\u0005¢\u0006\u0002\u0010\u0002J4\u0010 \u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"0!\u0018\u00010!\"\u0004\b\u0000\u0010\"2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u0002H\"\u0018\u00010!2\u0006\u0010$\u001a\u00020\bH\u0002J\b\u0010%\u001a\u00020&H\u0016J&\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020&H\u0016J\u0010\u00100\u001a\u00020&2\u0006\u00101\u001a\u000202H\u0007J\b\u00103\u001a\u00020&H\u0016J\u0010\u00104\u001a\u00020&2\u0006\u00105\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00060\u0018R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u00068"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWatchMarketBinding;", "currBean", "Lcom/qcwireless/qcwatch/ui/plate/bean/MarketWatchFaceBean;", "currDeletePosition", "", "currPage", "deviceAdapter", "Lcom/qcwireless/qcwatch/ui/plate/adapter/DeviceWatchFaceListAdapter;", "handler", "Landroid/os/Handler;", "isError", "", "marketAdapter", "Lcom/qcwireless/qcwatch/ui/plate/adapter/MarketWatchFaceListAdapter;", "marketItemClickPosition", "maxWatchFace", "ongoing", "pageCount", "pageIndex", "timeOutRunnable", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragment$TimeOutRunnable;", "totalPage", "watchFaceViewModel", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel;", "getWatchFaceViewModel", "()Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel;", "watchFaceViewModel$delegate", "Lkotlin/Lazy;", "fixedGrouping", "", ExifInterface.GPS_DIRECTION_TRUE, "source", "n", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "toInstallActivity", "position", "Companion", "TimeOutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchMarketFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentWatchMarketBinding binding;
    private MarketWatchFaceBean currBean;
    private int currDeletePosition;
    private int currPage;
    private DeviceWatchFaceListAdapter deviceAdapter;
    private final Handler handler;
    private boolean isError;
    private MarketWatchFaceListAdapter marketAdapter;
    private int marketItemClickPosition;
    private int maxWatchFace;
    private boolean ongoing;
    private final int pageCount;
    private int pageIndex;
    private final TimeOutRunnable timeOutRunnable;
    private int totalPage;

    /* renamed from: watchFaceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy watchFaceViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public WatchMarketFragment() {
        final WatchMarketFragment watchMarketFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.watchFaceViewModel = LazyKt.lazy(new Function0<WatchMarketFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WatchMarketFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(watchMarketFragment, Reflection.getOrCreateKotlinClass(WatchMarketFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.marketItemClickPosition = -1;
        this.currDeletePosition = -1;
        this.timeOutRunnable = new TimeOutRunnable();
        this.maxWatchFace = 6;
        this.totalPage = 320;
        this.currPage = 18;
        this.pageCount = 18;
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    private final WatchMarketFragmentViewModel getWatchFaceViewModel() {
        return (WatchMarketFragmentViewModel) this.watchFaceViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWatchMarketBinding fragmentWatchMarketBindingInflate = FragmentWatchMarketBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWatchMarketBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWatchMarketBindingInflate;
        EventBus.getDefault().register(this);
        FragmentWatchMarketBinding fragmentWatchMarketBinding = this.binding;
        if (fragmentWatchMarketBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchMarketBinding = null;
        }
        return fragmentWatchMarketBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() throws InterruptedException {
        super.loadDataOnce();
        int maxWatchFace = UserConfig.INSTANCE.getInstance().getMaxWatchFace();
        this.maxWatchFace = maxWatchFace;
        XLog.i(Integer.valueOf(maxWatchFace));
        getWatchFaceViewModel().getWatchFaceFromDevice();
        MarketWatchFaceListAdapter marketWatchFaceListAdapter = new MarketWatchFaceListAdapter(getActivity());
        this.marketAdapter = marketWatchFaceListAdapter;
        marketWatchFaceListAdapter.setData$com_github_CymChad_brvah(getWatchFaceViewModel().getMarketList());
        DeviceWatchFaceListAdapter deviceWatchFaceListAdapter = new DeviceWatchFaceListAdapter(getActivity());
        this.deviceAdapter = deviceWatchFaceListAdapter;
        deviceWatchFaceListAdapter.setData$com_github_CymChad_brvah(getWatchFaceViewModel().getWatchList());
        MarketWatchFaceListAdapter marketWatchFaceListAdapter2 = null;
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.recycleview_watch_market_header, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) headerView.findViewById(R.id.rcv_local_view);
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        DeviceWatchFaceListAdapter deviceWatchFaceListAdapter2 = this.deviceAdapter;
        if (deviceWatchFaceListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            deviceWatchFaceListAdapter2 = null;
        }
        recyclerView.setAdapter(deviceWatchFaceListAdapter2);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        MarketWatchFaceListAdapter marketWatchFaceListAdapter3 = this.marketAdapter;
        if (marketWatchFaceListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
            marketWatchFaceListAdapter3 = null;
        }
        Intrinsics.checkNotNullExpressionValue(headerView, "headerView");
        BaseQuickAdapter.addHeaderView$default(marketWatchFaceListAdapter3, headerView, 0, 0, 6, null);
        FragmentWatchMarketBinding fragmentWatchMarketBinding = this.binding;
        if (fragmentWatchMarketBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchMarketBinding = null;
        }
        MarketWatchFaceListAdapter marketWatchFaceListAdapter4 = this.marketAdapter;
        if (marketWatchFaceListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
            marketWatchFaceListAdapter4 = null;
        }
        marketWatchFaceListAdapter4.setHasStableIds(true);
        RecyclerView.ItemAnimator itemAnimator2 = fragmentWatchMarketBinding.rcvNetworkView.getItemAnimator();
        Objects.requireNonNull(itemAnimator2, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator2).setSupportsChangeAnimations(false);
        MyRecycleView myRecycleView = fragmentWatchMarketBinding.rcvNetworkView;
        MarketWatchFaceListAdapter marketWatchFaceListAdapter5 = this.marketAdapter;
        if (marketWatchFaceListAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
            marketWatchFaceListAdapter5 = null;
        }
        myRecycleView.setAdapter(marketWatchFaceListAdapter5);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        fragmentWatchMarketBinding.rcvNetworkView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$loadDataOnce$1$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                if (position == 0) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        WatchMarketFragment watchMarketFragment = this;
        getWatchFaceViewModel().getUiState().observe(watchMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchMarketFragment.m1047loadDataOnce$lambda1(this.f$0, (WatchMarketFragmentViewModel.WatchMarketUI) obj);
            }
        });
        DeviceWatchFaceListAdapter deviceWatchFaceListAdapter3 = this.deviceAdapter;
        if (deviceWatchFaceListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            deviceWatchFaceListAdapter3 = null;
        }
        deviceWatchFaceListAdapter3.getDeletePosition().observe(watchMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchMarketFragment.m1048loadDataOnce$lambda2(this.f$0, (Integer) obj);
            }
        });
        MarketWatchFaceListAdapter marketWatchFaceListAdapter6 = this.marketAdapter;
        if (marketWatchFaceListAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
            marketWatchFaceListAdapter6 = null;
        }
        marketWatchFaceListAdapter6.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WatchMarketFragment.m1049loadDataOnce$lambda4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getWatchFaceViewModel().getFileDownloadSuccess().observe(watchMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchMarketFragment.m1051loadDataOnce$lambda5(this.f$0, (ListenerBean) obj);
            }
        });
        MarketWatchFaceListAdapter marketWatchFaceListAdapter7 = this.marketAdapter;
        if (marketWatchFaceListAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
        } else {
            marketWatchFaceListAdapter2 = marketWatchFaceListAdapter7;
        }
        marketWatchFaceListAdapter2.getFileNotExists().observe(watchMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchMarketFragment.m1052loadDataOnce$lambda6(this.f$0, (MarketWatchFaceBean) obj);
            }
        });
        getWatchFaceViewModel().getProgressValue().observe(watchMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws InterruptedException {
                WatchMarketFragment.m1053loadDataOnce$lambda7(this.f$0, (WatchMarketFragmentViewModel.ProgressUI) obj);
            }
        });
        getWatchFaceViewModel().getMarketState().observe(watchMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchMarketFragment.m1054loadDataOnce$lambda8(this.f$0, (WatchMarketFragmentViewModel.WatchMarketUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m1047loadDataOnce$lambda1(WatchMarketFragment this$0, WatchMarketFragmentViewModel.WatchMarketUI watchMarketUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i("-------------do it-----------" + watchMarketUI.getMarketList().size());
        this$0.getWatchFaceViewModel().getWatchList().clear();
        this$0.getWatchFaceViewModel().getWatchList().addAll(watchMarketUI.getWatchList());
        DeviceWatchFaceListAdapter deviceWatchFaceListAdapter = this$0.deviceAdapter;
        MarketWatchFaceListAdapter marketWatchFaceListAdapter = null;
        if (deviceWatchFaceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            deviceWatchFaceListAdapter = null;
        }
        deviceWatchFaceListAdapter.notifyDataSetChanged();
        if (watchMarketUI.getMarketList().size() != this$0.getWatchFaceViewModel().getMarketListBackup().size() || watchMarketUI.getWatchList().size() != this$0.getWatchFaceViewModel().getWatchListBackUp().size() || this$0.getWatchFaceViewModel().getMarketList().size() == 0) {
            this$0.getWatchFaceViewModel().getWatchListBackUp().clear();
            this$0.getWatchFaceViewModel().getWatchListBackUp().addAll(watchMarketUI.getWatchList());
            this$0.getWatchFaceViewModel().getMarketList().clear();
            this$0.getWatchFaceViewModel().getMarketList().addAll(watchMarketUI.getMarketList());
            this$0.getWatchFaceViewModel().getMarketListBackup().clear();
            this$0.getWatchFaceViewModel().getMarketListBackup().addAll(watchMarketUI.getMarketList());
            XLog.i("-----刷新表盘");
            MarketWatchFaceListAdapter marketWatchFaceListAdapter2 = this$0.marketAdapter;
            if (marketWatchFaceListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
            } else {
                marketWatchFaceListAdapter = marketWatchFaceListAdapter2;
            }
            marketWatchFaceListAdapter.notifyDataSetChanged();
        }
        XLog.i("market size:" + watchMarketUI.getMarketList().size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m1048loadDataOnce$lambda2(WatchMarketFragment this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (num != null) {
            try {
                if (!BleOperateManager.getInstance().isConnected()) {
                    String string = this$0.getString(R.string.qc_text_75);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                } else if (this$0.ongoing) {
                    String string2 = this$0.getString(R.string.qc_text_249);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_249)");
                    GlobalKt.showToast$default(string2, 0, 1, null);
                } else {
                    if (this$0.getWatchFaceViewModel().getWatchList().size() < num.intValue()) {
                        return;
                    }
                    this$0.showLoadingDialogTimeout(15000);
                    this$0.ongoing = false;
                    DeviceWatchFaceBean deviceWatchFaceBean = this$0.getWatchFaceViewModel().getWatchList().get(num.intValue());
                    this$0.currDeletePosition = num.intValue();
                    this$0.getWatchFaceViewModel().deleteWatchFaceToDevice(deviceWatchFaceBean.getWatchFace().getName());
                    this$0.handler.postDelayed(this$0.timeOutRunnable, 5000L);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4, reason: not valid java name */
    public static final void m1049loadDataOnce$lambda4(WatchMarketFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getWatchFaceViewModel().getMarketList().size() * 2 < UserConfig.INSTANCE.getInstance().getServerMarketSize()) {
            this$0.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    WatchMarketFragment.m1050loadDataOnce$lambda4$lambda3();
                }
            }, 6000L);
        } else {
            if (SyncStatus.INSTANCE.getGetInstance().getSync()) {
                String string = this$0.getString(R.string.qc_text_236);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_236)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
            this$0.toInstallActivity(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1050loadDataOnce$lambda4$lambda3() {
        UserConfig.INSTANCE.getInstance().setServerMarketSize(0);
        UserConfig.INSTANCE.getInstance().save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-5, reason: not valid java name */
    public static final void m1051loadDataOnce$lambda5(WatchMarketFragment this$0, ListenerBean listenerBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (listenerBean.getSuccess()) {
            if (this$0.getWatchFaceViewModel().getWatchList().size() >= this$0.maxWatchFace) {
                this$0.ongoing = false;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = this$0.getString(R.string.qc_text_268);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_268)");
                String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.maxWatchFace)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                GlobalKt.showToast$default(str, 0, 1, null);
                return;
            }
            for (MarketWatchFaceBean marketWatchFaceBean : this$0.getWatchFaceViewModel().getMarketList()) {
                if (Intrinsics.areEqual(marketWatchFaceBean.getWatchFace().getName(), listenerBean.getName())) {
                    XLog.i(listenerBean.getName());
                    this$0.ongoing = false;
                    this$0.getWatchFaceViewModel().execWatchFaceToDeviceByName(marketWatchFaceBean.getWatchFace().getName());
                    return;
                }
            }
            return;
        }
        if (NetWorkUtils.INSTANCE.isNetworkAvailable(this$0.getActivity())) {
            return;
        }
        this$0.dismissLoadingDialogDelay(1000);
        String string2 = this$0.getString(R.string.qc_text_223);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_223)");
        GlobalKt.showToast$default(string2, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-6, reason: not valid java name */
    public static final void m1052loadDataOnce$lambda6(WatchMarketFragment this$0, MarketWatchFaceBean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WatchMarketFragmentViewModel watchFaceViewModel = this$0.getWatchFaceViewModel();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        watchFaceViewModel.downloadWatchFaceImageFileNotExists(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7, reason: not valid java name */
    public static final void m1053loadDataOnce$lambda7(WatchMarketFragment this$0, WatchMarketFragmentViewModel.ProgressUI progressUI) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.ongoing = progressUI.isRunning();
        MarketWatchFaceListAdapter marketWatchFaceListAdapter = null;
        if (this$0.marketItemClickPosition >= 0 && progressUI != null && !progressUI.isDelete() && this$0.marketItemClickPosition < this$0.getWatchFaceViewModel().getMarketList().size()) {
            MarketWatchFaceBean marketWatchFaceBean = this$0.getWatchFaceViewModel().getMarketList().get(this$0.marketItemClickPosition);
            marketWatchFaceBean.setProgressBar(progressUI.getProgress());
            QCApplication.INSTANCE.getGetInstance().setUpdating(3);
            if (!this$0.isDialogShowing()) {
                this$0.showLoadingDialogTimeout(TimeConstants.MIN);
                XLog.i("show dialog");
            }
            MarketWatchFaceListAdapter marketWatchFaceListAdapter2 = this$0.marketAdapter;
            if (marketWatchFaceListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                marketWatchFaceListAdapter2 = null;
            }
            marketWatchFaceListAdapter2.notifyItemChanged(this$0.marketItemClickPosition);
            if (progressUI.getProgress() == 100 && progressUI.getSuccess()) {
                this$0.ongoing = false;
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                this$0.handler.removeCallbacks(this$0.timeOutRunnable);
                DeviceWatchFaceBean deviceWatchFaceBean = new DeviceWatchFaceBean(marketWatchFaceBean.getWatchFace(), true);
                String name = marketWatchFaceBean.getWatchFace().getName();
                MarketWatchFaceBean marketWatchFaceBean2 = this$0.currBean;
                Intrinsics.checkNotNull(marketWatchFaceBean2);
                if (!Intrinsics.areEqual(name, marketWatchFaceBean2.getWatchFace().getName())) {
                    this$0.getWatchFaceViewModel().getWatchFaceFromDevice();
                } else {
                    this$0.getWatchFaceViewModel().getWatchList().add(deviceWatchFaceBean);
                    this$0.getWatchFaceViewModel().getMarketList().remove(marketWatchFaceBean);
                    this$0.getWatchFaceViewModel().getLocalWatchFace().put(marketWatchFaceBean.getWatchFace().getName(), deviceWatchFaceBean);
                    DeviceWatchFaceListAdapter deviceWatchFaceListAdapter = this$0.deviceAdapter;
                    if (deviceWatchFaceListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                        deviceWatchFaceListAdapter = null;
                    }
                    deviceWatchFaceListAdapter.notifyDataSetChanged();
                    MarketWatchFaceListAdapter marketWatchFaceListAdapter3 = this$0.marketAdapter;
                    if (marketWatchFaceListAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                        marketWatchFaceListAdapter3 = null;
                    }
                    marketWatchFaceListAdapter3.notifyDataSetChanged();
                }
                this$0.dismissLoadingDialog();
            }
        }
        if (progressUI != null) {
            if (progressUI.isDelete()) {
                try {
                    this$0.dismissLoadingDialog();
                    this$0.handler.removeCallbacks(this$0.timeOutRunnable);
                    this$0.ongoing = false;
                    DeviceWatchFaceBean deviceWatchFaceBean2 = this$0.getWatchFaceViewModel().getWatchList().get(this$0.currDeletePosition);
                    this$0.getWatchFaceViewModel().getLocalWatchFace().remove(deviceWatchFaceBean2.getWatchFace().getName());
                    this$0.getWatchFaceViewModel().getWatchList().remove(deviceWatchFaceBean2);
                    this$0.getWatchFaceViewModel().getMarketList().add(new MarketWatchFaceBean(deviceWatchFaceBean2.getWatchFace(), 0));
                    DeviceWatchFaceListAdapter deviceWatchFaceListAdapter2 = this$0.deviceAdapter;
                    if (deviceWatchFaceListAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                        deviceWatchFaceListAdapter2 = null;
                    }
                    deviceWatchFaceListAdapter2.notifyDataSetChanged();
                    MarketWatchFaceListAdapter marketWatchFaceListAdapter4 = this$0.marketAdapter;
                    if (marketWatchFaceListAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                        marketWatchFaceListAdapter4 = null;
                    }
                    marketWatchFaceListAdapter4.notifyDataSetChanged();
                } catch (Exception unused) {
                }
            }
            if (progressUI.getError()) {
                try {
                    this$0.ongoing = false;
                    this$0.dismissLoadingDialog();
                    MarketWatchFaceBean marketWatchFaceBean3 = this$0.getWatchFaceViewModel().getMarketList().get(this$0.marketItemClickPosition);
                    this$0.getWatchFaceViewModel().queryByName(marketWatchFaceBean3.getWatchFace().getName());
                    XLog.i("------------------手表报错");
                    if (progressUI.getProgress() == 666) {
                        marketWatchFaceBean3.getWatchFace().setLocalBinUrl("");
                        this$0.getWatchFaceViewModel().getMarketList().set(this$0.marketItemClickPosition, marketWatchFaceBean3);
                        this$0.getWatchFaceViewModel().downloadWatchFaceNotExistsNotRepeat(marketWatchFaceBean3);
                    }
                    DeviceWatchFaceListAdapter deviceWatchFaceListAdapter3 = this$0.deviceAdapter;
                    if (deviceWatchFaceListAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                        deviceWatchFaceListAdapter3 = null;
                    }
                    deviceWatchFaceListAdapter3.notifyDataSetChanged();
                    MarketWatchFaceListAdapter marketWatchFaceListAdapter5 = this$0.marketAdapter;
                    if (marketWatchFaceListAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                    } else {
                        marketWatchFaceListAdapter = marketWatchFaceListAdapter5;
                    }
                    marketWatchFaceListAdapter.notifyDataSetChanged();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        this$0.ongoing = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-8, reason: not valid java name */
    public static final void m1054loadDataOnce$lambda8(WatchMarketFragment this$0, WatchMarketFragmentViewModel.WatchMarketUI watchMarketUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getWatchFaceViewModel().getMarketList().clear();
        this$0.getWatchFaceViewModel().getMarketList().addAll(watchMarketUI.getMarketList());
        MarketWatchFaceListAdapter marketWatchFaceListAdapter = this$0.marketAdapter;
        if (marketWatchFaceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
            marketWatchFaceListAdapter = null;
        }
        marketWatchFaceListAdapter.notifyDataSetChanged();
    }

    private final void toInstallActivity(int position) {
        if (getWatchFaceViewModel().getWatchList().size() >= this.maxWatchFace) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(R.string.qc_text_268);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_268)");
            String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this.maxWatchFace)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            GlobalKt.showToast$default(str, 0, 1, null);
            return;
        }
        if (!BleOperateManager.getInstance().isConnected()) {
            String string2 = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        MarketWatchFaceBean marketWatchFaceBean = getWatchFaceViewModel().getMarketList().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("dialName", marketWatchFaceBean.getWatchFace().getName());
        WatchMarketFragment watchMarketFragment = this;
        FragmentActivity it = watchMarketFragment.getActivity();
        if (it != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) WatchFaceInstallActivity.class);
            intent.setFlags(1);
            intent.putExtras(bundle);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str2 = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).intValue()), "putExtra(name, value)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).byteValue()), "putExtra(name, value)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Character) second).charValue()), "putExtra(name, value)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).shortValue()), "putExtra(name, value)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).longValue()), "putExtra(name, value)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).floatValue()), "putExtra(name, value)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).doubleValue()), "putExtra(name, value)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (String) second), "putExtra(name, value)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (CharSequence) second), "putExtra(name, value)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(name, value)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (boolean[]) second), "putExtra(name, value)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (byte[]) second), "putExtra(name, value)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (short[]) second), "putExtra(name, value)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (char[]) second), "putExtra(name, value)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (int[]) second), "putExtra(name, value)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (long[]) second), "putExtra(name, value)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (float[]) second), "putExtra(name, value)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (double[]) second), "putExtra(name, value)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Bundle) second), "putExtra(name, value)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(name, value)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            watchMarketFragment.startActivity(intent);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getWatchFaceViewModel().initCallback();
        getWatchFaceViewModel().checkCustomizeWatchFace();
    }

    /* compiled from: WatchMarketFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragment$TimeOutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragment;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeOutRunnable implements Runnable {
        public TimeOutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            WatchMarketFragment.this.ongoing = false;
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) throws Throwable {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        RecyclerView.Adapter adapter = null;
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            this.maxWatchFace = UserConfig.INSTANCE.getInstance().getMaxWatchFace();
            try {
                if (!this.ongoing) {
                    XLog.i("----onMessageEvent WatchFaceRefreshEvent");
                    if (BleOperateManager.getInstance().isConnected()) {
                        if (!SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()) {
                            getWatchFaceViewModel().getMarketListBackup().clear();
                            getWatchFaceViewModel().getWatchFaceFromDevice();
                        } else {
                            this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda8
                                @Override // java.lang.Runnable
                                public final void run() throws InterruptedException {
                                    WatchMarketFragment.m1055onMessageEvent$lambda10(this.f$0);
                                }
                            }, 3000L);
                        }
                    } else {
                        MarketWatchFaceListAdapter marketWatchFaceListAdapter = this.marketAdapter;
                        if (marketWatchFaceListAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                            marketWatchFaceListAdapter = null;
                        }
                        marketWatchFaceListAdapter.notifyDataSetChanged();
                        DeviceWatchFaceListAdapter deviceWatchFaceListAdapter = this.deviceAdapter;
                        if (deviceWatchFaceListAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                            deviceWatchFaceListAdapter = null;
                        }
                        deviceWatchFaceListAdapter.notifyDataSetChanged();
                    }
                } else {
                    MarketWatchFaceListAdapter marketWatchFaceListAdapter2 = this.marketAdapter;
                    if (marketWatchFaceListAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                        marketWatchFaceListAdapter2 = null;
                    }
                    marketWatchFaceListAdapter2.notifyDataSetChanged();
                    DeviceWatchFaceListAdapter deviceWatchFaceListAdapter2 = this.deviceAdapter;
                    if (deviceWatchFaceListAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                        deviceWatchFaceListAdapter2 = null;
                    }
                    deviceWatchFaceListAdapter2.notifyDataSetChanged();
                }
            } catch (Exception unused) {
            }
            String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
            if (deviceAddress == null || deviceAddress.length() == 0) {
                getWatchFaceViewModel().getWatchList().clear();
                getWatchFaceViewModel().getMarketList().clear();
                DeviceWatchFaceListAdapter deviceWatchFaceListAdapter3 = this.deviceAdapter;
                if (deviceWatchFaceListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                    deviceWatchFaceListAdapter3 = null;
                }
                deviceWatchFaceListAdapter3.notifyDataSetChanged();
                MarketWatchFaceListAdapter marketWatchFaceListAdapter3 = this.marketAdapter;
                if (marketWatchFaceListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                    marketWatchFaceListAdapter3 = null;
                }
                marketWatchFaceListAdapter3.notifyDataSetChanged();
            }
        }
        if (messageEvent instanceof WatchFaceRefreshEvent) {
            XLog.i("---WatchFaceRefreshEvent");
            getWatchFaceViewModel().getWatchFaceFromDevice();
            return;
        }
        try {
            if (messageEvent instanceof WatchFaceInstallSuccessEvent) {
                if (BleOperateManager.getInstance().isConnected()) {
                    if (!SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()) {
                        getWatchFaceViewModel().getMarketListBackup().clear();
                        getWatchFaceViewModel().getWatchFaceFromDevice();
                        return;
                    } else {
                        this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment$$ExternalSyntheticLambda7
                            @Override // java.lang.Runnable
                            public final void run() throws InterruptedException {
                                WatchMarketFragment.m1056onMessageEvent$lambda11(this.f$0);
                            }
                        }, 3000L);
                        return;
                    }
                }
                MarketWatchFaceListAdapter marketWatchFaceListAdapter4 = this.marketAdapter;
                if (marketWatchFaceListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                    marketWatchFaceListAdapter4 = null;
                }
                marketWatchFaceListAdapter4.notifyDataSetChanged();
                DeviceWatchFaceListAdapter deviceWatchFaceListAdapter4 = this.deviceAdapter;
                if (deviceWatchFaceListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                } else {
                    adapter = deviceWatchFaceListAdapter4;
                }
                adapter.notifyDataSetChanged();
                return;
            }
            if (messageEvent instanceof BluetoothEvent) {
                this.ongoing = false;
                if (((BluetoothEvent) messageEvent).getConnect()) {
                    return;
                }
                dismissLoadingDialog();
                DeviceWatchFaceListAdapter deviceWatchFaceListAdapter5 = this.deviceAdapter;
                if (deviceWatchFaceListAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                    deviceWatchFaceListAdapter5 = null;
                }
                deviceWatchFaceListAdapter5.notifyDataSetChanged();
                MarketWatchFaceBean marketWatchFaceBean = getWatchFaceViewModel().getMarketList().get(this.marketItemClickPosition);
                marketWatchFaceBean.setProgressBar(0);
                getWatchFaceViewModel().getMarketList().set(this.marketItemClickPosition, marketWatchFaceBean);
                MarketWatchFaceListAdapter marketWatchFaceListAdapter5 = this.marketAdapter;
                if (marketWatchFaceListAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                    marketWatchFaceListAdapter5 = null;
                }
                marketWatchFaceListAdapter5.notifyItemChanged(this.marketItemClickPosition);
                MarketWatchFaceListAdapter marketWatchFaceListAdapter6 = this.marketAdapter;
                if (marketWatchFaceListAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                } else {
                    adapter = marketWatchFaceListAdapter6;
                }
                adapter.notifyDataSetChanged();
                return;
            }
            if (messageEvent instanceof WatchFaceDownloadProgressEvent) {
                WatchFaceDownloadProgressEvent watchFaceDownloadProgressEvent = (WatchFaceDownloadProgressEvent) messageEvent;
                if (watchFaceDownloadProgressEvent.getProgressValue() == 100) {
                    getWatchFaceViewModel().getMarketListBackup().clear();
                }
                getWatchFaceViewModel().getMarketWatchFace();
                LogToFile.getInstance().wtf("刷新表盘:" + watchFaceDownloadProgressEvent.getProgressValue());
                XLog.i("刷新表盘:" + watchFaceDownloadProgressEvent.getProgressValue());
                return;
            }
            if (messageEvent instanceof UnbindDeviceEvent) {
                getWatchFaceViewModel().getWatchList().clear();
                getWatchFaceViewModel().getMarketList().clear();
                DeviceWatchFaceListAdapter deviceWatchFaceListAdapter6 = this.deviceAdapter;
                if (deviceWatchFaceListAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                    deviceWatchFaceListAdapter6 = null;
                }
                deviceWatchFaceListAdapter6.notifyDataSetChanged();
                MarketWatchFaceListAdapter marketWatchFaceListAdapter7 = this.marketAdapter;
                if (marketWatchFaceListAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marketAdapter");
                } else {
                    adapter = marketWatchFaceListAdapter7;
                }
                adapter.notifyDataSetChanged();
                XLog.i("---UnbindDeviceEvent------");
            }
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onMessageEvent$lambda-10, reason: not valid java name */
    public static final void m1055onMessageEvent$lambda10(WatchMarketFragment this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getWatchFaceViewModel().getWatchFaceFromDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onMessageEvent$lambda-11, reason: not valid java name */
    public static final void m1056onMessageEvent$lambda11(WatchMarketFragment this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getWatchFaceViewModel().getWatchFaceFromDevice();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: WatchMarketFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WatchMarketFragment newInstance() {
            return new WatchMarketFragment();
        }
    }

    private final <T> List<List<T>> fixedGrouping(List<? extends T> source, int n) {
        if (source == null || source.isEmpty() || n <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = source.size();
        int size2 = size % n == 0 ? size / n : (source.size() / n) + 1;
        int i = 0;
        while (i < size2) {
            ArrayList arrayList2 = new ArrayList();
            i++;
            int i2 = i * n;
            for (int i3 = i * n; i3 < i2; i3++) {
                if (i3 < size) {
                    arrayList2.add(source.get(i3));
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }
}
