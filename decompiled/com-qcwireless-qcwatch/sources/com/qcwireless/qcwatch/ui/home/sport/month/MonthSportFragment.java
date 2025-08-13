package com.qcwireless.qcwatch.ui.home.sport.month;

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
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentMonthSportBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailCalorieActivity;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity;
import com.qcwireless.qcwatch.ui.home.sport.adapter.ExpandableRecyclerViewAdapter;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragmentViewModel;
import java.io.Serializable;
import java.util.ArrayList;
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

/* compiled from: MonthSportFragment.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/sport/adapter/ExpandableRecyclerViewAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentMonthSportBinding;", "firstDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "lastDate", "monthViewModel", "Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragmentViewModel;", "getMonthViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragmentViewModel;", "monthViewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonthSportFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ExpandableRecyclerViewAdapter adapter;
    private FragmentMonthSportBinding binding;
    private DateUtil firstDate;
    private DateUtil lastDate;

    /* renamed from: monthViewModel$delegate, reason: from kotlin metadata */
    private final Lazy monthViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MonthSportFragment() {
        final MonthSportFragment monthSportFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.monthViewModel = LazyKt.lazy(new Function0<MonthSportFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MonthSportFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(monthSportFragment, Reflection.getOrCreateKotlinClass(MonthSportFragmentViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MonthSportFragmentViewModel getMonthViewModel() {
        return (MonthSportFragmentViewModel) this.monthViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMonthSportBinding fragmentMonthSportBindingInflate = FragmentMonthSportBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentMonthSportBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentMonthSportBindingInflate;
        EventBus.getDefault().register(this);
        FragmentMonthSportBinding fragmentMonthSportBinding = this.binding;
        if (fragmentMonthSportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSportBinding = null;
        }
        return fragmentMonthSportBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        DateUtil firstDayOfMonth = DateUtil.getFirstDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(firstDayOfMonth, "getFirstDayOfMonth(DateUtil())");
        this.firstDate = firstDayOfMonth;
        DateUtil lastDayOfMonth = DateUtil.getLastDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(lastDayOfMonth, "getLastDayOfMonth(DateUtil())");
        this.lastDate = lastDayOfMonth;
        MonthSportFragmentViewModel monthViewModel = getMonthViewModel();
        DateUtil firstDayOfMonth2 = DateUtil.getFirstDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(firstDayOfMonth2, "getFirstDayOfMonth(DateUtil())");
        DateUtil lastDayOfMonth2 = DateUtil.getLastDayOfMonth(new DateUtil());
        Intrinsics.checkNotNullExpressionValue(lastDayOfMonth2, "getLastDayOfMonth(\n     … DateUtil()\n            )");
        monthViewModel.showMonthData(firstDayOfMonth2, lastDayOfMonth2);
        ExpandableRecyclerViewAdapter expandableRecyclerViewAdapter = new ExpandableRecyclerViewAdapter(getActivity());
        this.adapter = expandableRecyclerViewAdapter;
        expandableRecyclerViewAdapter.setListener(new ExpandableRecyclerViewAdapter.SubItemListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragment$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.home.sport.adapter.ExpandableRecyclerViewAdapter.SubItemListener
            public final void itemClick(SportDetail sportDetail) {
                MonthSportFragment.m853loadDataOnce$lambda2(this.f$0, sportDetail);
            }
        });
        FragmentMonthSportBinding fragmentMonthSportBinding = this.binding;
        if (fragmentMonthSportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSportBinding = null;
        }
        fragmentMonthSportBinding.qcDateChange.setMonth(new DateUtil());
        fragmentMonthSportBinding.qcDateChange.setDateListener(new QDateMonthSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragment$loadDataOnce$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                DateUtil first = DateUtil.getFirstDayOfMonth(dateUtil);
                DateUtil last = DateUtil.getLastDayOfMonth(dateUtil);
                MonthSportFragment monthSportFragment = this.this$0;
                Intrinsics.checkNotNullExpressionValue(first, "first");
                monthSportFragment.firstDate = first;
                MonthSportFragment monthSportFragment2 = this.this$0;
                Intrinsics.checkNotNullExpressionValue(last, "last");
                monthSportFragment2.lastDate = last;
                this.this$0.getMonthViewModel().showMonthData(first, last);
            }
        });
        getMonthViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MonthSportFragment.m854loadDataOnce$lambda6(this.f$0, (MonthSportFragmentViewModel.MonthSportUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m853loadDataOnce$lambda2(MonthSportFragment this$0, SportDetail sportDetail) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((int) sportDetail.getDistance()) == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("sport_type", sportDetail.getSportType());
            bundle.putLong("start_time", sportDetail.getStartTime());
            MonthSportFragment monthSportFragment = this$0;
            FragmentActivity it = monthSportFragment.getActivity();
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
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                monthSportFragment.startActivity(intent);
                return;
            }
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("sport_type", sportDetail.getSportType());
        bundle2.putLong("start_time", sportDetail.getStartTime());
        MonthSportFragment monthSportFragment2 = this$0;
        FragmentActivity it2 = monthSportFragment2.getActivity();
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
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            monthSportFragment2.startActivity(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-6, reason: not valid java name */
    public static final void m854loadDataOnce$lambda6(MonthSportFragment this$0, MonthSportFragmentViewModel.MonthSportUI monthSportUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentMonthSportBinding fragmentMonthSportBinding = this$0.binding;
        ExpandableRecyclerViewAdapter expandableRecyclerViewAdapter = null;
        if (fragmentMonthSportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSportBinding = null;
        }
        fragmentMonthSportBinding.weekTotal.setText(monthSportUI.getSportTimes());
        fragmentMonthSportBinding.totalTimes.setItemTitle(monthSportUI.getSportCounts());
        XLog.i(monthSportUI.getSportCalorie());
        fragmentMonthSportBinding.totalCal.setItemTitle(monthSportUI.getSportCalorie());
        fragmentMonthSportBinding.totalDays.setItemTitle(monthSportUI.getDays());
        FragmentMonthSportBinding fragmentMonthSportBinding2 = this$0.binding;
        if (fragmentMonthSportBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMonthSportBinding2 = null;
        }
        fragmentMonthSportBinding2.expList.setLayoutManager(new LinearLayoutManager(this$0.getActivity()));
        RecyclerView recyclerView = fragmentMonthSportBinding2.expList;
        ExpandableRecyclerViewAdapter expandableRecyclerViewAdapter2 = this$0.adapter;
        if (expandableRecyclerViewAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            expandableRecyclerViewAdapter2 = null;
        }
        recyclerView.setAdapter(expandableRecyclerViewAdapter2);
        this$0.getMonthViewModel().getMDataListTreesBackup().clear();
        this$0.getMonthViewModel().getMDataListTreesBackup().addAll(this$0.getMonthViewModel().getMDataListTrees());
        ExpandableRecyclerViewAdapter expandableRecyclerViewAdapter3 = this$0.adapter;
        if (expandableRecyclerViewAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            expandableRecyclerViewAdapter3 = null;
        }
        expandableRecyclerViewAdapter3.setData(this$0.getMonthViewModel().getMDataListTreesBackup(), UserConfig.INSTANCE.getInstance().getMetric());
        if (this$0.getMonthViewModel().getMDataListTrees().size() == 0) {
            FragmentMonthSportBinding fragmentMonthSportBinding3 = this$0.binding;
            if (fragmentMonthSportBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthSportBinding3 = null;
            }
            ViewKt.visible(fragmentMonthSportBinding3.noDataLayout);
        } else {
            FragmentMonthSportBinding fragmentMonthSportBinding4 = this$0.binding;
            if (fragmentMonthSportBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMonthSportBinding4 = null;
            }
            ViewKt.gone(fragmentMonthSportBinding4.noDataLayout);
        }
        ExpandableRecyclerViewAdapter expandableRecyclerViewAdapter4 = this$0.adapter;
        if (expandableRecyclerViewAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            expandableRecyclerViewAdapter = expandableRecyclerViewAdapter4;
        }
        expandableRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof ManualRefreshEvent) {
            MonthSportFragmentViewModel monthViewModel = getMonthViewModel();
            DateUtil dateUtil = this.firstDate;
            DateUtil dateUtil2 = null;
            if (dateUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firstDate");
                dateUtil = null;
            }
            DateUtil dateUtil3 = this.lastDate;
            if (dateUtil3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lastDate");
            } else {
                dateUtil2 = dateUtil3;
            }
            monthViewModel.showMonthData(dateUtil, dateUtil2);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: MonthSportFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MonthSportFragment newInstance() {
            return new MonthSportFragment();
        }
    }
}
