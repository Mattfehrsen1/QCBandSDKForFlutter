package com.qcwireless.qcwatch.ui.home.gps.day;

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
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.OnDeviceGPSEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.databinding.FragmentDayGpsBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.home.gps.GpsMapActivity;
import com.qcwireless.qcwatch.ui.home.gps.adapter.GpsDetailAdapter;
import com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DayGpsFragment.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/gps/adapter/GpsDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentDayGpsBinding;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DayGpsFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private GpsDetailAdapter adapter;
    private FragmentDayGpsBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public DayGpsFragment() {
        final DayGpsFragment dayGpsFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<DayGpsFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DayGpsFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(dayGpsFragment, Reflection.getOrCreateKotlinClass(DayGpsFragmentViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DayGpsFragmentViewModel getViewModel() {
        return (DayGpsFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDayGpsBinding fragmentDayGpsBindingInflate = FragmentDayGpsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentDayGpsBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentDayGpsBindingInflate;
        EventBus.getDefault().register(this);
        FragmentDayGpsBinding fragmentDayGpsBinding = this.binding;
        if (fragmentDayGpsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayGpsBinding = null;
        }
        return fragmentDayGpsBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DayGpsFragment.m692loadDataOnce$lambda1(this.f$0, (DayGpsFragmentViewModel.GpsDetailUI) obj);
            }
        });
        getViewModel().queryDetailByDate(new DateUtil());
        final FragmentDayGpsBinding fragmentDayGpsBinding = this.binding;
        GpsDetailAdapter gpsDetailAdapter = null;
        if (fragmentDayGpsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayGpsBinding = null;
        }
        fragmentDayGpsBinding.qcDateChange.setDateUtil(new DateUtil());
        fragmentDayGpsBinding.qcDateChange.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragment$loadDataOnce$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                fragmentDayGpsBinding.qcDateChange.setDateUtil(dateUtil);
                this.getViewModel().queryDetailByDate(dateUtil);
            }
        });
        this.adapter = new GpsDetailAdapter(getActivity(), getViewModel().getGpsDetailList());
        fragmentDayGpsBinding.gpsDetailRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView recyclerView = fragmentDayGpsBinding.gpsDetailRcv;
        GpsDetailAdapter gpsDetailAdapter2 = this.adapter;
        if (gpsDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            gpsDetailAdapter2 = null;
        }
        recyclerView.setAdapter(gpsDetailAdapter2);
        GpsDetailAdapter gpsDetailAdapter3 = this.adapter;
        if (gpsDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            gpsDetailAdapter3 = null;
        }
        gpsDetailAdapter3.notifyDataSetChanged();
        GpsDetailAdapter gpsDetailAdapter4 = this.adapter;
        if (gpsDetailAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            gpsDetailAdapter = gpsDetailAdapter4;
        }
        gpsDetailAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DayGpsFragment.m693loadDataOnce$lambda3(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m692loadDataOnce$lambda1(DayGpsFragment this$0, DayGpsFragmentViewModel.GpsDetailUI gpsDetailUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDayGpsBinding fragmentDayGpsBinding = this$0.binding;
        GpsDetailAdapter gpsDetailAdapter = null;
        if (fragmentDayGpsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayGpsBinding = null;
        }
        fragmentDayGpsBinding.todayTotalDuring.setText(gpsDetailUI.getTotalDuring());
        fragmentDayGpsBinding.totalDistance.setItemTitle(gpsDetailUI.getTotalDistance());
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            QSportItemView qSportItemView = fragmentDayGpsBinding.totalDistance;
            String string = this$0.getString(R.string.qc_text_88);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
            qSportItemView.setItemUnit(string);
        } else {
            QSportItemView qSportItemView2 = fragmentDayGpsBinding.totalDistance;
            String string2 = this$0.getString(R.string.qc_text_358);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
            qSportItemView2.setItemUnit(string2);
        }
        fragmentDayGpsBinding.totalCal.setItemTitle(gpsDetailUI.getTotalCalorie());
        fragmentDayGpsBinding.totalTimes.setItemTitle(gpsDetailUI.getTotalTime());
        GpsDetailAdapter gpsDetailAdapter2 = this$0.adapter;
        if (gpsDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            gpsDetailAdapter = gpsDetailAdapter2;
        }
        gpsDetailAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m693loadDataOnce$lambda3(DayGpsFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        final GpsDetail gpsDetail = this$0.getViewModel().getGpsDetailList().get(i);
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<DayGpsFragment, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragment$loadDataOnce$3$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DayGpsFragment dayGpsFragment) {
                invoke2(dayGpsFragment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DayGpsFragment ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                XLog.i(gpsDetail);
                SportPlusDetail sportPlusDetailQueryByTypeAndStartTime = SportPlusRepository.INSTANCE.getGetInstance().queryByTypeAndStartTime(250, gpsDetail.getStartTime());
                XLog.i(sportPlusDetailQueryByTypeAndStartTime);
                if (sportPlusDetailQueryByTypeAndStartTime != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("sport_type", 250);
                    bundle.putLong("start_time", sportPlusDetailQueryByTypeAndStartTime.getStartTime());
                    DayGpsFragment dayGpsFragment = ktxRunOnBgSingle;
                    FragmentActivity it = dayGpsFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) SportDetailDistanceActivity.class);
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
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                        dayGpsFragment.startActivity(intent);
                        return;
                    }
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putLong("start", gpsDetail.getStartTime());
                DayGpsFragment dayGpsFragment2 = ktxRunOnBgSingle;
                FragmentActivity it2 = dayGpsFragment2.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) GpsMapActivity.class);
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
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    dayGpsFragment2.startActivity(intent2);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof OnDeviceGPSEvent) {
            getViewModel().queryDetailByDate(new DateUtil());
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: DayGpsFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DayGpsFragment newInstance() {
            return new DayGpsFragment();
        }
    }
}
