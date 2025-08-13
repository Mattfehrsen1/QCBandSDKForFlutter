package com.qcwireless.qcwatch.ui.home.pressure;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationConst;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityRingPressureDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.PressureDetail;
import com.qcwireless.qcwatch.ui.base.repository.entity.PressureManualEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.home.pressure.adapter.AppManualPressureDetailAdapter;
import com.qcwireless.qcwatch.ui.home.pressure.bean.PressureDetailBean;
import com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: RingPressureDetailActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003J\b\u0010\u0019\u001a\u00020\u0010H\u0015J\b\u0010\u001a\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/RingPressureDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/pressure/adapter/AppManualPressureDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityRingPressureDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "queryManualPressure", "type", "", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RingPressureDetailActivity extends BaseActivity {
    private AppManualPressureDetailAdapter adapter;
    private ActivityRingPressureDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public RingPressureDetailActivity() {
        final RingPressureDetailActivity ringPressureDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<DayPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DayPressureFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(ringPressureDetailActivity, Reflection.getOrCreateKotlinClass(DayPressureFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DayPressureFragmentViewModel getViewModel() {
        return (DayPressureFragmentViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRingPressureDetailBinding activityRingPressureDetailBindingInflate = ActivityRingPressureDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRingPressureDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityRingPressureDetailBindingInflate;
        if (activityRingPressureDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingPressureDetailBindingInflate = null;
        }
        ConstraintLayout root = activityRingPressureDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        AppManualPressureDetailAdapter appManualPressureDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        int iIntValue = numValueOf.intValue();
        Bundle extras2 = getIntent().getExtras();
        Integer numValueOf2 = extras2 != null ? Integer.valueOf(extras2.getInt("type")) : null;
        Intrinsics.checkNotNull(numValueOf2);
        int iIntValue2 = numValueOf2.intValue();
        long j = iIntValue;
        DateUtil dateUtil = new DateUtil(j, true);
        this.date = dateUtil;
        queryManualPressure(dateUtil, iIntValue2);
        RingPressureDetailActivity ringPressureDetailActivity = this;
        AppManualPressureDetailAdapter appManualPressureDetailAdapter2 = new AppManualPressureDetailAdapter(ringPressureDetailActivity, getViewModel().getManualList());
        this.adapter = appManualPressureDetailAdapter2;
        appManualPressureDetailAdapter2.setEmptyView(R.layout.recycleview_heart_empty);
        ActivityRingPressureDetailBinding activityRingPressureDetailBinding = this.binding;
        if (activityRingPressureDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingPressureDetailBinding = null;
        }
        activityRingPressureDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityRingPressureDetailBinding.tvDataDate.setText(new DateUtil(j, true).getY_M_D());
        ViewKt.gone(activityRingPressureDetailBinding.titleBar.tvRightText);
        activityRingPressureDetailBinding.rcvHeartRateDetail.setLayoutManager(new LinearLayoutManager(ringPressureDetailActivity));
        RecyclerView recyclerView = activityRingPressureDetailBinding.rcvHeartRateDetail;
        AppManualPressureDetailAdapter appManualPressureDetailAdapter3 = this.adapter;
        if (appManualPressureDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            appManualPressureDetailAdapter = appManualPressureDetailAdapter3;
        }
        recyclerView.setAdapter(appManualPressureDetailAdapter);
        if (this.date.isToday()) {
            ViewKt.visible(activityRingPressureDetailBinding.titleBar.tvRightText);
            activityRingPressureDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityRingPressureDetailBinding.titleBar.tvRightText);
        }
        activityRingPressureDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RingPressureDetailActivity.m800setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m800setupViews$lambda1$lambda0(RingPressureDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else {
            this$0.getViewModel().syncTodayData();
            this$0.startAnim();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryPressureByDate(this.date);
        }
    }

    private final void queryManualPressure(final DateUtil date, final int type) {
        ThreadExtKt.ktxRunOnBgFix(this, new Function1<RingPressureDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureDetailActivity.queryManualPressure.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RingPressureDetailActivity ringPressureDetailActivity) {
                invoke2(ringPressureDetailActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RingPressureDetailActivity ktxRunOnBgFix) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                int i = type;
                if (i == 2) {
                    List<PressureManualEntity> listQueryManualPressureAll = PressureRepository.INSTANCE.getGetInstance().queryManualPressureAll(date);
                    ktxRunOnBgFix.getViewModel().getManualList().clear();
                    XLog.i(Integer.valueOf(listQueryManualPressureAll.size()));
                    for (PressureManualEntity pressureManualEntity : listQueryManualPressureAll) {
                        ktxRunOnBgFix.getViewModel().getManualList().add(new PressureDetailBean(pressureManualEntity.getTimestamp(), pressureManualEntity.getPressure()));
                    }
                } else if (i == 1) {
                    PressureRepository getInstance = PressureRepository.INSTANCE.getGetInstance();
                    String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                    String y_m_d = date.getY_M_D();
                    Intrinsics.checkNotNullExpressionValue(y_m_d, "date.y_M_D");
                    PressureDetail pressureDetailQueryPressureByDate = getInstance.queryPressureByDate(deviceAddressNoClear, y_m_d);
                    ktxRunOnBgFix.getViewModel().getManualList().clear();
                    if (pressureDetailQueryPressureByDate != null) {
                        int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(pressureDetailQueryPressureByDate.getIndex_str());
                        int[] iArrStringToIntArray2 = StringUtilsKt.stringToIntArray(pressureDetailQueryPressureByDate.getValue());
                        int length = iArrStringToIntArray.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            int i3 = iArrStringToIntArray[i2];
                            if (iArrStringToIntArray2[i2] != 0) {
                                ktxRunOnBgFix.getViewModel().getManualList().add(new PressureDetailBean(((int) new DateUtil(pressureDetailQueryPressureByDate.getUnixTime(), true).getZeroTime()) + (i3 * pressureDetailQueryPressureByDate.getIntervar() * 60), iArrStringToIntArray2[i2]));
                            }
                        }
                    }
                    List<PressureDetailBean> manualList = ktxRunOnBgFix.getViewModel().getManualList();
                    if (manualList.size() > 1) {
                        CollectionsKt.sortWith(manualList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureDetailActivity$queryManualPressure$1$invoke$$inlined$sortByDescending$1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                return ComparisonsKt.compareValues(Integer.valueOf(((PressureDetailBean) t2).getSeconds() / 60), Integer.valueOf(((PressureDetailBean) t).getSeconds() / 60));
                            }
                        });
                    }
                }
                ThreadExtKt.ktxRunOnUi(ktxRunOnBgFix, new Function1<RingPressureDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureDetailActivity.queryManualPressure.1.3
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(RingPressureDetailActivity ringPressureDetailActivity) {
                        invoke2(ringPressureDetailActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RingPressureDetailActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        AppManualPressureDetailAdapter appManualPressureDetailAdapter = null;
                        if (ktxRunOnUi.getViewModel().getManualList().size() == 0) {
                            ActivityRingPressureDetailBinding activityRingPressureDetailBinding = ktxRunOnUi.binding;
                            if (activityRingPressureDetailBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingPressureDetailBinding = null;
                            }
                            ViewKt.visible(activityRingPressureDetailBinding.noDataView);
                            ActivityRingPressureDetailBinding activityRingPressureDetailBinding2 = ktxRunOnUi.binding;
                            if (activityRingPressureDetailBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingPressureDetailBinding2 = null;
                            }
                            ViewKt.visible(activityRingPressureDetailBinding2.tvErrorText);
                        } else {
                            ActivityRingPressureDetailBinding activityRingPressureDetailBinding3 = ktxRunOnUi.binding;
                            if (activityRingPressureDetailBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingPressureDetailBinding3 = null;
                            }
                            ViewKt.gone(activityRingPressureDetailBinding3.noDataView);
                            ActivityRingPressureDetailBinding activityRingPressureDetailBinding4 = ktxRunOnUi.binding;
                            if (activityRingPressureDetailBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingPressureDetailBinding4 = null;
                            }
                            ViewKt.gone(activityRingPressureDetailBinding4.tvErrorText);
                        }
                        AppManualPressureDetailAdapter appManualPressureDetailAdapter2 = ktxRunOnUi.adapter;
                        if (appManualPressureDetailAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            appManualPressureDetailAdapter = appManualPressureDetailAdapter2;
                        }
                        appManualPressureDetailAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private final void startAnim() {
        ActivityRingPressureDetailBinding activityRingPressureDetailBinding = this.binding;
        ActivityRingPressureDetailBinding activityRingPressureDetailBinding2 = null;
        if (activityRingPressureDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingPressureDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityRingPressureDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityRingPressureDetailBinding activityRingPressureDetailBinding3 = this.binding;
        if (activityRingPressureDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRingPressureDetailBinding2 = activityRingPressureDetailBinding3;
        }
        activityRingPressureDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
