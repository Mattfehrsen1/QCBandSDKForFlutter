package com.qcwireless.qcwatch.ui.home.bloodoxgen;

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
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityBloodOxygenDataDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.adapter.BloodOxygenDetailAdapter;
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

/* compiled from: BloodOxygenDataDetailActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\u0010H\u0015J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/adapter/BloodOxygenDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodOxygenDataDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodOxygenDataDetailActivity extends BaseActivity {
    private BloodOxygenDetailAdapter adapter;
    private ActivityBloodOxygenDataDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public BloodOxygenDataDetailActivity() {
        final BloodOxygenDataDetailActivity bloodOxygenDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<BloodOxygenViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodOxygenViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodOxygenDataDetailActivity, Reflection.getOrCreateKotlinClass(BloodOxygenViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    private final BloodOxygenViewModel getViewModel() {
        return (BloodOxygenViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBindingInflate = ActivityBloodOxygenDataDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodOxygenDataDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodOxygenDataDetailBindingInflate;
        if (activityBloodOxygenDataDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenDataDetailBindingInflate = null;
        }
        ConstraintLayout root = activityBloodOxygenDataDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        BloodOxygenDetailAdapter bloodOxygenDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        final int iIntValue = numValueOf.intValue();
        long j = iIntValue;
        this.date = new DateUtil(j, true);
        getViewModel().queryBloodOxygenByDateDetail(this.date);
        BloodOxygenDataDetailActivity bloodOxygenDataDetailActivity = this;
        this.adapter = new BloodOxygenDetailAdapter(bloodOxygenDataDetailActivity, getViewModel().getDetailList());
        ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding = this.binding;
        if (activityBloodOxygenDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenDataDetailBinding = null;
        }
        activityBloodOxygenDataDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityBloodOxygenDataDetailBinding.tvDataDate.setText(new DateUtil(j, true).getY_M_D());
        if (this.date.isToday()) {
            ViewKt.visible(activityBloodOxygenDataDetailBinding.titleBar.tvRightText);
            activityBloodOxygenDataDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityBloodOxygenDataDetailBinding.titleBar.tvRightText);
        }
        activityBloodOxygenDataDetailBinding.rcvBloodOxygenDetail.setLayoutManager(new LinearLayoutManager(bloodOxygenDataDetailActivity));
        RecyclerView recyclerView = activityBloodOxygenDataDetailBinding.rcvBloodOxygenDetail;
        BloodOxygenDetailAdapter bloodOxygenDetailAdapter2 = this.adapter;
        if (bloodOxygenDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            bloodOxygenDetailAdapter = bloodOxygenDetailAdapter2;
        }
        recyclerView.setAdapter(bloodOxygenDetailAdapter);
        activityBloodOxygenDataDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenDataDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws InterruptedException {
                BloodOxygenDataDetailActivity.m645setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        getViewModel().getUiDetail().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenDataDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodOxygenDataDetailActivity.m646setupViews$lambda2(this.f$0, iIntValue, (BloodOxygenViewModel.BloodOxygenDetail) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m645setupViews$lambda1$lambda0(BloodOxygenDataDetailActivity this$0, View view) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startAnim();
        this$0.getViewModel().syncTodayData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m646setupViews$lambda2(BloodOxygenDataDetailActivity this$0, int i, BloodOxygenViewModel.BloodOxygenDetail bloodOxygenDetail) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDetailList().clear();
        this$0.getViewModel().getDetailList().addAll(bloodOxygenDetail.getData());
        BloodOxygenDetailAdapter bloodOxygenDetailAdapter = null;
        if (this$0.getViewModel().getDetailList().size() == 0) {
            ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding = this$0.binding;
            if (activityBloodOxygenDataDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenDataDetailBinding = null;
            }
            ViewKt.visible(activityBloodOxygenDataDetailBinding.noDataView);
            if (new DateUtil(i, true).isToday()) {
                ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding2 = this$0.binding;
                if (activityBloodOxygenDataDetailBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenDataDetailBinding2 = null;
                }
                ViewKt.visible(activityBloodOxygenDataDetailBinding2.tvErrorText);
            } else {
                ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding3 = this$0.binding;
                if (activityBloodOxygenDataDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenDataDetailBinding3 = null;
                }
                ViewKt.gone(activityBloodOxygenDataDetailBinding3.tvErrorText);
            }
        } else {
            ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding4 = this$0.binding;
            if (activityBloodOxygenDataDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenDataDetailBinding4 = null;
            }
            ViewKt.gone(activityBloodOxygenDataDetailBinding4.noDataView);
        }
        BloodOxygenDetailAdapter bloodOxygenDetailAdapter2 = this$0.adapter;
        if (bloodOxygenDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            bloodOxygenDetailAdapter = bloodOxygenDetailAdapter2;
        }
        bloodOxygenDetailAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryBloodOxygenByDateDetail(this.date);
        }
    }

    private final void startAnim() {
        ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding = this.binding;
        ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding2 = null;
        if (activityBloodOxygenDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenDataDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityBloodOxygenDataDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityBloodOxygenDataDetailBinding activityBloodOxygenDataDetailBinding3 = this.binding;
        if (activityBloodOxygenDataDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodOxygenDataDetailBinding2 = activityBloodOxygenDataDetailBinding3;
        }
        activityBloodOxygenDataDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
