package com.qcwireless.qcwatch.ui.home.sport.day;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.oudmon.ble.base.communication.sport.SportPlusEntity;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentDaySportBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailCalorieActivity;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity;
import com.qcwireless.qcwatch.ui.home.sport.adapter.SportDetailAdapter;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragmentViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DaySportFragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\u00020\u0001:\u0002 !B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\b\u0010\u001f\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/sport/adapter/SportDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentDaySportBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "dayViewModel", "Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragmentViewModel;", "getDayViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragmentViewModel;", "dayViewModel$delegate", "Lkotlin/Lazy;", "deviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment$MyDeviceNotifyListener;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "Companion", "MyDeviceNotifyListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DaySportFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private SportDetailAdapter adapter;
    private FragmentDaySportBinding binding;
    private DateUtil date;

    /* renamed from: dayViewModel$delegate, reason: from kotlin metadata */
    private final Lazy dayViewModel;
    private MyDeviceNotifyListener deviceNotifyListener;

    /* JADX WARN: Multi-variable type inference failed */
    public DaySportFragment() {
        final DaySportFragment daySportFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.dayViewModel = LazyKt.lazy(new Function0<DaySportFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DaySportFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(daySportFragment, Reflection.getOrCreateKotlinClass(DaySportFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DaySportFragmentViewModel getDayViewModel() {
        return (DaySportFragmentViewModel) this.dayViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDaySportBinding fragmentDaySportBindingInflate = FragmentDaySportBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentDaySportBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentDaySportBindingInflate;
        EventBus.getDefault().register(this);
        FragmentDaySportBinding fragmentDaySportBinding = this.binding;
        if (fragmentDaySportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySportBinding = null;
        }
        return fragmentDaySportBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        getDayViewModel().queryLastData();
        final FragmentDaySportBinding fragmentDaySportBinding = this.binding;
        SportDetailAdapter sportDetailAdapter = null;
        if (fragmentDaySportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySportBinding = null;
        }
        this.adapter = new SportDetailAdapter(getActivity(), getDayViewModel().getSportDetailList());
        fragmentDaySportBinding.sportDetailRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView recyclerView = fragmentDaySportBinding.sportDetailRcv;
        SportDetailAdapter sportDetailAdapter2 = this.adapter;
        if (sportDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            sportDetailAdapter2 = null;
        }
        recyclerView.setAdapter(sportDetailAdapter2);
        fragmentDaySportBinding.totalTimes.setItemTitle(String.valueOf(getDayViewModel().getSportDetailList().size()));
        fragmentDaySportBinding.qcDateChange.setSportFlag(true);
        fragmentDaySportBinding.qcDateChange.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                if (dateUtil.getZeroTime() <= new DateUtil().getUnixTimestamp()) {
                    this.date = dateUtil;
                    fragmentDaySportBinding.qcDateChange.setDateUtil(dateUtil);
                    this.getDayViewModel().daySportPlusDetail(dateUtil);
                } else {
                    fragmentDaySportBinding.qcDateChange.setDateUtil(new DateUtil());
                    this.getDayViewModel().daySportPlusDetail(new DateUtil());
                }
            }
        });
        DaySportFragment daySportFragment = this;
        getDayViewModel().getUiState().observe(daySportFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DaySportFragment.m850loadDataOnce$lambda2(this.f$0, (DaySportFragmentViewModel.DaySportUI) obj);
            }
        });
        getDayViewModel().getLastDate().observe(daySportFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DaySportFragment.m851loadDataOnce$lambda3(this.f$0, (DateUtil) obj);
            }
        });
        SportDetailAdapter sportDetailAdapter3 = this.adapter;
        if (sportDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            sportDetailAdapter = sportDetailAdapter3;
        }
        sportDetailAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DaySportFragment.m852loadDataOnce$lambda7$lambda6(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m850loadDataOnce$lambda2(DaySportFragment this$0, DaySportFragmentViewModel.DaySportUI daySportUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDaySportBinding fragmentDaySportBinding = this$0.binding;
        SportDetailAdapter sportDetailAdapter = null;
        if (fragmentDaySportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySportBinding = null;
        }
        fragmentDaySportBinding.weekTotal.setText(daySportUI.getSportTimes());
        fragmentDaySportBinding.totalTimes.setItemTitle(daySportUI.getSportCounts());
        fragmentDaySportBinding.totalCal.setItemTitle(daySportUI.getSportCalorie());
        fragmentDaySportBinding.totalDays.setItemTitle(daySportUI.getDays());
        if (this$0.getDayViewModel().getSportDetailList().size() == 0) {
            FragmentDaySportBinding fragmentDaySportBinding2 = this$0.binding;
            if (fragmentDaySportBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDaySportBinding2 = null;
            }
            ViewKt.visible(fragmentDaySportBinding2.noDataLayout);
        } else {
            FragmentDaySportBinding fragmentDaySportBinding3 = this$0.binding;
            if (fragmentDaySportBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDaySportBinding3 = null;
            }
            ViewKt.gone(fragmentDaySportBinding3.noDataLayout);
        }
        SportDetailAdapter sportDetailAdapter2 = this$0.adapter;
        if (sportDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            sportDetailAdapter = sportDetailAdapter2;
        }
        sportDetailAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m851loadDataOnce$lambda3(DaySportFragment this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.date = it;
        this$0.getDayViewModel().daySportPlusDetail(it);
        FragmentDaySportBinding fragmentDaySportBinding = this$0.binding;
        if (fragmentDaySportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDaySportBinding = null;
        }
        fragmentDaySportBinding.qcDateChange.setDateUtil(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7$lambda-6, reason: not valid java name */
    public static final void m852loadDataOnce$lambda7$lambda6(DaySportFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        SportDetail sportDetail = this$0.getDayViewModel().getSportDetailList().get(i);
        if (((int) sportDetail.getDistance()) == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("sport_type", sportDetail.getSportType());
            bundle.putLong("start_time", sportDetail.getStartTime());
            Unit unit = Unit.INSTANCE;
            DaySportFragment daySportFragment = this$0;
            FragmentActivity it = daySportFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) SportDetailCalorieActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
                Unit unit3 = Unit.INSTANCE;
                Unit unit4 = Unit.INSTANCE;
                Unit unit5 = Unit.INSTANCE;
                daySportFragment.startActivity(intent);
                Unit unit6 = Unit.INSTANCE;
                Unit unit7 = Unit.INSTANCE;
                return;
            }
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("sport_type", sportDetail.getSportType());
        bundle2.putLong("start_time", sportDetail.getStartTime());
        Unit unit8 = Unit.INSTANCE;
        DaySportFragment daySportFragment2 = this$0;
        FragmentActivity it2 = daySportFragment2.getActivity();
        if (it2 != null) {
            ArrayList<Pair> arrayList2 = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            Intent intent2 = new Intent(it2, (Class<?>) SportDetailDistanceActivity.class);
            intent2.setFlags(1);
            intent2.putExtras(bundle2);
            for (Pair pair2 : arrayList2) {
                if (pair2 != null) {
                    String str2 = (String) pair2.getFirst();
                    Object second2 = pair2.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                    } else {
                        Unit unit9 = Unit.INSTANCE;
                    }
                }
            }
            Unit unit10 = Unit.INSTANCE;
            Unit unit11 = Unit.INSTANCE;
            Unit unit12 = Unit.INSTANCE;
            daySportFragment2.startActivity(intent2);
            Unit unit13 = Unit.INSTANCE;
            Unit unit14 = Unit.INSTANCE;
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.deviceNotifyListener = new MyDeviceNotifyListener();
        BleOperateManager bleOperateManager = BleOperateManager.getInstance();
        MyDeviceNotifyListener myDeviceNotifyListener = this.deviceNotifyListener;
        if (myDeviceNotifyListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceNotifyListener");
            myDeviceNotifyListener = null;
        }
        bleOperateManager.addOutDeviceListener(7, myDeviceNotifyListener);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getDayViewModel().queryLastData();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeOutDeviceListener(7);
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: DaySportFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) {
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0 && resultEntity.getDataType() == 7) {
                SportPlusRepository.INSTANCE.getGetInstance().syncTodaySportPlus((BaseDeviceResult) new BaseDeviceResult<List<? extends SportPlusEntity>>() { // from class: com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment$MyDeviceNotifyListener$onDataResponse$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, List<? extends SportPlusEntity> t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        XLog.i("当天运动同步结束");
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        }
    }

    /* compiled from: DaySportFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DaySportFragment newInstance() {
            return new DaySportFragment();
        }
    }
}
