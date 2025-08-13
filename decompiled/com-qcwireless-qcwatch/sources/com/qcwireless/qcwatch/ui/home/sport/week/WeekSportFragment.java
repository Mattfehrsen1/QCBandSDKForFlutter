package com.qcwireless.qcwatch.ui.home.sport.week;

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
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentWeekSportBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailCalorieActivity;
import com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity;
import com.qcwireless.qcwatch.ui.home.sport.adapter.SportDetailAdapter;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import com.qcwireless.qcwatch.ui.home.sport.week.WeekSportViewModel;
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

/* compiled from: WeekSportFragment.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/sport/adapter/SportDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWeekSportBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "weekViewModel", "Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportViewModel;", "getWeekViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportViewModel;", "weekViewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeekSportFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private SportDetailAdapter adapter;
    private FragmentWeekSportBinding binding;
    private DateUtil date;

    /* renamed from: weekViewModel$delegate, reason: from kotlin metadata */
    private final Lazy weekViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public WeekSportFragment() {
        final WeekSportFragment weekSportFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.weekViewModel = LazyKt.lazy(new Function0<WeekSportViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sport.week.WeekSportFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sport.week.WeekSportViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WeekSportViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(weekSportFragment, Reflection.getOrCreateKotlinClass(WeekSportViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WeekSportViewModel getWeekViewModel() {
        return (WeekSportViewModel) this.weekViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWeekSportBinding fragmentWeekSportBindingInflate = FragmentWeekSportBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWeekSportBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWeekSportBindingInflate;
        EventBus.getDefault().register(this);
        FragmentWeekSportBinding fragmentWeekSportBinding = this.binding;
        if (fragmentWeekSportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekSportBinding = null;
        }
        return fragmentWeekSportBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        DateUtil dateUtilFirstDayOfWeek = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek, "firstDayOfWeek()");
        this.date = dateUtilFirstDayOfWeek;
        WeekSportViewModel weekViewModel = getWeekViewModel();
        DateUtil dateUtil = this.date;
        SportDetailAdapter sportDetailAdapter = null;
        if (dateUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("date");
            dateUtil = null;
        }
        weekViewModel.weekSportPlusDetail(dateUtil);
        FragmentWeekSportBinding fragmentWeekSportBinding = this.binding;
        if (fragmentWeekSportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekSportBinding = null;
        }
        this.adapter = new SportDetailAdapter(getActivity(), getWeekViewModel().getSportDetailList());
        fragmentWeekSportBinding.sportDetailRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView recyclerView = fragmentWeekSportBinding.sportDetailRcv;
        SportDetailAdapter sportDetailAdapter2 = this.adapter;
        if (sportDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            sportDetailAdapter2 = null;
        }
        recyclerView.setAdapter(sportDetailAdapter2);
        SportDetailAdapter sportDetailAdapter3 = this.adapter;
        if (sportDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            sportDetailAdapter3 = null;
        }
        sportDetailAdapter3.notifyDataSetChanged();
        fragmentWeekSportBinding.totalTimes.setItemTitle(String.valueOf(getWeekViewModel().getSportDetailList().size()));
        QDateWeekSwitchView qDateWeekSwitchView = fragmentWeekSportBinding.qcDateChange;
        DateUtil dateUtilFirstDayOfWeek2 = DateUtil.firstDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dateUtilFirstDayOfWeek2, "firstDayOfWeek()");
        qDateWeekSwitchView.setWeekDayStart(dateUtilFirstDayOfWeek2);
        fragmentWeekSportBinding.qcDateChange.setDateListener(new QDateWeekSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.sport.week.WeekSportFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil2) {
                Intrinsics.checkNotNullParameter(dateUtil2, "dateUtil");
                this.this$0.date = dateUtil2;
                this.this$0.getWeekViewModel().weekSportPlusDetail(dateUtil2);
            }
        });
        getWeekViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sport.week.WeekSportFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WeekSportFragment.m857loadDataOnce$lambda2(this.f$0, (WeekSportViewModel.WeekSportUI) obj);
            }
        });
        SportDetailAdapter sportDetailAdapter4 = this.adapter;
        if (sportDetailAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            sportDetailAdapter = sportDetailAdapter4;
        }
        sportDetailAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.week.WeekSportFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WeekSportFragment.m858loadDataOnce$lambda6$lambda5(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m857loadDataOnce$lambda2(WeekSportFragment this$0, WeekSportViewModel.WeekSportUI weekSportUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWeekSportBinding fragmentWeekSportBinding = this$0.binding;
        SportDetailAdapter sportDetailAdapter = null;
        if (fragmentWeekSportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeekSportBinding = null;
        }
        fragmentWeekSportBinding.weekTotal.setText(weekSportUI.getSportTimes());
        fragmentWeekSportBinding.totalTimes.setItemTitle(weekSportUI.getSportCounts());
        fragmentWeekSportBinding.totalCal.setItemTitle(weekSportUI.getSportCalorie());
        fragmentWeekSportBinding.totalDays.setItemTitle(weekSportUI.getDays());
        if (this$0.getWeekViewModel().getSportDetailList().size() == 0) {
            FragmentWeekSportBinding fragmentWeekSportBinding2 = this$0.binding;
            if (fragmentWeekSportBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekSportBinding2 = null;
            }
            ViewKt.visible(fragmentWeekSportBinding2.noDataLayout);
        } else {
            FragmentWeekSportBinding fragmentWeekSportBinding3 = this$0.binding;
            if (fragmentWeekSportBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeekSportBinding3 = null;
            }
            ViewKt.gone(fragmentWeekSportBinding3.noDataLayout);
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
    /* renamed from: loadDataOnce$lambda-6$lambda-5, reason: not valid java name */
    public static final void m858loadDataOnce$lambda6$lambda5(WeekSportFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        SportDetail sportDetail = this$0.getWeekViewModel().getSportDetailList().get(i);
        if (((int) sportDetail.getDistance()) == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("sport_type", sportDetail.getSportType());
            bundle.putLong("start_time", sportDetail.getStartTime());
            Unit unit = Unit.INSTANCE;
            WeekSportFragment weekSportFragment = this$0;
            FragmentActivity it = weekSportFragment.getActivity();
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
                weekSportFragment.startActivity(intent);
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
        WeekSportFragment weekSportFragment2 = this$0;
        FragmentActivity it2 = weekSportFragment2.getActivity();
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
            weekSportFragment2.startActivity(intent2);
            Unit unit13 = Unit.INSTANCE;
            Unit unit14 = Unit.INSTANCE;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof ManualRefreshEvent) {
            XLog.i("-----week");
            WeekSportViewModel weekViewModel = getWeekViewModel();
            DateUtil dateUtil = this.date;
            if (dateUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("date");
                dateUtil = null;
            }
            weekViewModel.weekSportPlusDetail(dateUtil);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: WeekSportFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeekSportFragment newInstance() {
            return new WeekSportFragment();
        }
    }
}
