package com.qcwireless.qcwatch.ui.home.heart;

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
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityRingHrvDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository;
import com.qcwireless.qcwatch.ui.base.view.QHrvLineChartView;
import com.qcwireless.qcwatch.ui.home.heart.adapter.ManualHrvDetailAdapter;
import com.qcwireless.qcwatch.ui.home.heart.bean.HeartRateDetailBean;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: HrvDataDetailActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0014J\b\u0010\u0018\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HrvDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/heart/adapter/ManualHrvDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityRingHrvDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "queryManualPressure", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HrvDataDetailActivity extends BaseActivity {
    private ManualHrvDetailAdapter adapter;
    private ActivityRingHrvDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public HrvDataDetailActivity() {
        final HrvDataDetailActivity hrvDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HrvActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.heart.HrvActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HrvActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(hrvDataDetailActivity, Reflection.getOrCreateKotlinClass(HrvActivityViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HrvActivityViewModel getViewModel() {
        return (HrvActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRingHrvDetailBinding activityRingHrvDetailBindingInflate = ActivityRingHrvDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRingHrvDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityRingHrvDetailBindingInflate;
        if (activityRingHrvDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingHrvDetailBindingInflate = null;
        }
        ConstraintLayout root = activityRingHrvDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        ManualHrvDetailAdapter manualHrvDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        long jIntValue = numValueOf.intValue();
        DateUtil dateUtil = new DateUtil(jIntValue, true);
        this.date = dateUtil;
        queryManualPressure(dateUtil);
        HrvDataDetailActivity hrvDataDetailActivity = this;
        ManualHrvDetailAdapter manualHrvDetailAdapter2 = new ManualHrvDetailAdapter(hrvDataDetailActivity, getViewModel().getManualList());
        this.adapter = manualHrvDetailAdapter2;
        manualHrvDetailAdapter2.setEmptyView(R.layout.recycleview_heart_empty);
        ActivityRingHrvDetailBinding activityRingHrvDetailBinding = this.binding;
        if (activityRingHrvDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingHrvDetailBinding = null;
        }
        activityRingHrvDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityRingHrvDetailBinding.tvDataDate.setText(new DateUtil(jIntValue, true).getY_M_D());
        activityRingHrvDetailBinding.rcvHeartRateDetail.setLayoutManager(new LinearLayoutManager(hrvDataDetailActivity));
        RecyclerView recyclerView = activityRingHrvDetailBinding.rcvHeartRateDetail;
        ManualHrvDetailAdapter manualHrvDetailAdapter3 = this.adapter;
        if (manualHrvDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            manualHrvDetailAdapter = manualHrvDetailAdapter3;
        }
        recyclerView.setAdapter(manualHrvDetailAdapter);
        if (this.date.isToday()) {
            ViewKt.visible(activityRingHrvDetailBinding.titleBar.tvRightText);
            activityRingHrvDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityRingHrvDetailBinding.titleBar.tvRightText);
        }
        activityRingHrvDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvDataDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HrvDataDetailActivity.m779setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m779setupViews$lambda1$lambda0(HrvDataDetailActivity this$0, View view) {
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

    private final void queryManualPressure(final DateUtil date) {
        ThreadExtKt.ktxRunOnBgFix(this, new Function1<HrvDataDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvDataDetailActivity.queryManualPressure.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HrvDataDetailActivity hrvDataDetailActivity) {
                invoke2(hrvDataDetailActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HrvDataDetailActivity ktxRunOnBgFix) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                List<QHrvLineChartView.HeartDataBean> listQueryHrvByDateDetailResp = HRVRepository.INSTANCE.getGetInstance().queryHrvByDateDetailResp(date);
                ktxRunOnBgFix.getViewModel().getManualList().clear();
                XLog.i(Integer.valueOf(listQueryHrvByDateDetailResp.size()));
                DateUtil dateUtil = date;
                Iterator<T> it = listQueryHrvByDateDetailResp.iterator();
                while (it.hasNext()) {
                    ktxRunOnBgFix.getViewModel().getManualList().add(new HeartRateDetailBean(dateUtil.getZeroTime() + (r2.getMin() * 60), ((QHrvLineChartView.HeartDataBean) it.next()).getValue()));
                }
                ThreadExtKt.ktxRunOnUi(ktxRunOnBgFix, new Function1<HrvDataDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvDataDetailActivity.queryManualPressure.1.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HrvDataDetailActivity hrvDataDetailActivity) {
                        invoke2(hrvDataDetailActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HrvDataDetailActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ManualHrvDetailAdapter manualHrvDetailAdapter = null;
                        if (ktxRunOnUi.getViewModel().getManualList().size() == 0) {
                            ActivityRingHrvDetailBinding activityRingHrvDetailBinding = ktxRunOnUi.binding;
                            if (activityRingHrvDetailBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingHrvDetailBinding = null;
                            }
                            ViewKt.visible(activityRingHrvDetailBinding.noDataView);
                            ActivityRingHrvDetailBinding activityRingHrvDetailBinding2 = ktxRunOnUi.binding;
                            if (activityRingHrvDetailBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingHrvDetailBinding2 = null;
                            }
                            ViewKt.visible(activityRingHrvDetailBinding2.tvErrorText);
                        } else {
                            ActivityRingHrvDetailBinding activityRingHrvDetailBinding3 = ktxRunOnUi.binding;
                            if (activityRingHrvDetailBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingHrvDetailBinding3 = null;
                            }
                            ViewKt.gone(activityRingHrvDetailBinding3.tvErrorText);
                            ActivityRingHrvDetailBinding activityRingHrvDetailBinding4 = ktxRunOnUi.binding;
                            if (activityRingHrvDetailBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingHrvDetailBinding4 = null;
                            }
                            ViewKt.gone(activityRingHrvDetailBinding4.noDataView);
                        }
                        ManualHrvDetailAdapter manualHrvDetailAdapter2 = ktxRunOnUi.adapter;
                        if (manualHrvDetailAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            manualHrvDetailAdapter = manualHrvDetailAdapter2;
                        }
                        manualHrvDetailAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
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

    private final void startAnim() {
        ActivityRingHrvDetailBinding activityRingHrvDetailBinding = this.binding;
        ActivityRingHrvDetailBinding activityRingHrvDetailBinding2 = null;
        if (activityRingHrvDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingHrvDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityRingHrvDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityRingHrvDetailBinding activityRingHrvDetailBinding3 = this.binding;
        if (activityRingHrvDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRingHrvDetailBinding2 = activityRingHrvDetailBinding3;
        }
        activityRingHrvDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
