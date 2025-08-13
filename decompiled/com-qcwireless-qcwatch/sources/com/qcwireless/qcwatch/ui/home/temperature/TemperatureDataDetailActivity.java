package com.qcwireless.qcwatch.ui.home.temperature;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationConst;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityTemperatureDataDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.temperature.adapter.TemperatureDetailAdapter;
import com.qcwireless.qcwatch.ui.home.temperature.bean.TemperatureDetailBean;
import com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: TemperatureDataDetailActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\u0010H\u0015J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/TemperatureDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/temperature/adapter/TemperatureDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityTemperatureDataDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureDataDetailActivity extends BaseActivity {
    private TemperatureDetailAdapter adapter;
    private ActivityTemperatureDataDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public TemperatureDataDetailActivity() {
        final TemperatureDataDetailActivity temperatureDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<TemperatureViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final TemperatureViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(temperatureDataDetailActivity, Reflection.getOrCreateKotlinClass(TemperatureViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    private final TemperatureViewModel getViewModel() {
        return (TemperatureViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBindingInflate = ActivityTemperatureDataDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTemperatureDataDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityTemperatureDataDetailBindingInflate;
        if (activityTemperatureDataDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureDataDetailBindingInflate = null;
        }
        ConstraintLayout root = activityTemperatureDataDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.color_F9F9F9);
        Bundle extras = getIntent().getExtras();
        TemperatureDetailAdapter temperatureDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        long jIntValue = numValueOf.intValue();
        this.date = new DateUtil(jIntValue, true);
        getViewModel().queryTemperatureByDateDetail(this.date);
        TemperatureDataDetailActivity temperatureDataDetailActivity = this;
        TemperatureDetailAdapter temperatureDetailAdapter2 = new TemperatureDetailAdapter(temperatureDataDetailActivity, getViewModel().getDetailList());
        this.adapter = temperatureDetailAdapter2;
        temperatureDetailAdapter2.setEmptyView(R.layout.recycleview_heart_empty);
        ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBinding = this.binding;
        if (activityTemperatureDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureDataDetailBinding = null;
        }
        activityTemperatureDataDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityTemperatureDataDetailBinding.tvDataDate.setText(new DateUtil(jIntValue, true).getY_M_D());
        if (this.date.isToday()) {
            ViewKt.visible(activityTemperatureDataDetailBinding.titleBar.tvRightText);
            activityTemperatureDataDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityTemperatureDataDetailBinding.titleBar.tvRightText);
        }
        activityTemperatureDataDetailBinding.rcvTemperatureDetail.setLayoutManager(new LinearLayoutManager(temperatureDataDetailActivity));
        RecyclerView recyclerView = activityTemperatureDataDetailBinding.rcvTemperatureDetail;
        TemperatureDetailAdapter temperatureDetailAdapter3 = this.adapter;
        if (temperatureDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            temperatureDetailAdapter = temperatureDetailAdapter3;
        }
        recyclerView.setAdapter(temperatureDetailAdapter);
        activityTemperatureDataDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureDataDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureDataDetailActivity.m874setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        getViewModel().getUiDetail().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureDataDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TemperatureDataDetailActivity.m875setupViews$lambda2(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m874setupViews$lambda1$lambda0(TemperatureDataDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else {
            this$0.getViewModel().syncTemperatureSingleCheck();
            this$0.startAnim();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m875setupViews$lambda2(TemperatureDataDetailActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDetailList().clear();
        List<TemperatureDetailBean> detailList = this$0.getViewModel().getDetailList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        detailList.addAll(it);
        TemperatureDetailAdapter temperatureDetailAdapter = null;
        if (this$0.getViewModel().getDetailList().size() == 0) {
            ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBinding = this$0.binding;
            if (activityTemperatureDataDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureDataDetailBinding = null;
            }
            ViewKt.visible(activityTemperatureDataDetailBinding.noDataView);
        } else {
            ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBinding2 = this$0.binding;
            if (activityTemperatureDataDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureDataDetailBinding2 = null;
            }
            ViewKt.gone(activityTemperatureDataDetailBinding2.noDataView);
        }
        TemperatureDetailAdapter temperatureDetailAdapter2 = this$0.adapter;
        if (temperatureDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            temperatureDetailAdapter = temperatureDetailAdapter2;
        }
        temperatureDetailAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryTemperatureByDateDetail(this.date);
        }
    }

    private final void startAnim() {
        ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBinding = this.binding;
        ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBinding2 = null;
        if (activityTemperatureDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureDataDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityTemperatureDataDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityTemperatureDataDetailBinding activityTemperatureDataDetailBinding3 = this.binding;
        if (activityTemperatureDataDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTemperatureDataDetailBinding2 = activityTemperatureDataDetailBinding3;
        }
        activityTemperatureDataDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
