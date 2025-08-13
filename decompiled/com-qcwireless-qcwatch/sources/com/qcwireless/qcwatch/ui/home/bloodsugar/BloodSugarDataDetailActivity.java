package com.qcwireless.qcwatch.ui.home.bloodsugar;

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
import com.qcwireless.qcwatch.databinding.ActivityBloodSugarDataDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel;
import com.qcwireless.qcwatch.ui.home.bloodsugar.adapter.BloodSugarDetailAdapter;
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

/* compiled from: BloodSugarDataDetailActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\u0010H\u0015J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/adapter/BloodSugarDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodSugarDataDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodSugarDataDetailActivity extends BaseActivity {
    private BloodSugarDetailAdapter adapter;
    private ActivityBloodSugarDataDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public BloodSugarDataDetailActivity() {
        final BloodSugarDataDetailActivity bloodSugarDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<BloodSugarActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodSugarActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodSugarDataDetailActivity, Reflection.getOrCreateKotlinClass(BloodSugarActivityViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    private final BloodSugarActivityViewModel getViewModel() {
        return (BloodSugarActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBindingInflate = ActivityBloodSugarDataDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodSugarDataDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodSugarDataDetailBindingInflate;
        if (activityBloodSugarDataDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarDataDetailBindingInflate = null;
        }
        ConstraintLayout root = activityBloodSugarDataDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        BloodSugarDetailAdapter bloodSugarDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        final int iIntValue = numValueOf.intValue();
        long j = iIntValue;
        this.date = new DateUtil(j, true);
        getViewModel().queryBloodSugarByDateDetail(this.date);
        BloodSugarDataDetailActivity bloodSugarDataDetailActivity = this;
        this.adapter = new BloodSugarDetailAdapter(bloodSugarDataDetailActivity, getViewModel().getDetailList());
        ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding = this.binding;
        if (activityBloodSugarDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarDataDetailBinding = null;
        }
        activityBloodSugarDataDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityBloodSugarDataDetailBinding.tvDataDate.setText(new DateUtil(j, true).getY_M_D());
        if (this.date.isToday()) {
            ViewKt.visible(activityBloodSugarDataDetailBinding.titleBar.tvRightText);
            activityBloodSugarDataDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityBloodSugarDataDetailBinding.titleBar.tvRightText);
        }
        activityBloodSugarDataDetailBinding.rcvBloodOxygenDetail.setLayoutManager(new LinearLayoutManager(bloodSugarDataDetailActivity));
        RecyclerView recyclerView = activityBloodSugarDataDetailBinding.rcvBloodOxygenDetail;
        BloodSugarDetailAdapter bloodSugarDetailAdapter2 = this.adapter;
        if (bloodSugarDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            bloodSugarDetailAdapter = bloodSugarDetailAdapter2;
        }
        recyclerView.setAdapter(bloodSugarDetailAdapter);
        activityBloodSugarDataDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarDataDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws InterruptedException {
                BloodSugarDataDetailActivity.m652setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        getViewModel().getUiDetail().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarDataDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodSugarDataDetailActivity.m653setupViews$lambda2(this.f$0, iIntValue, (BloodSugarActivityViewModel.BloodSugarDetail) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m652setupViews$lambda1$lambda0(BloodSugarDataDetailActivity this$0, View view) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startAnim();
        this$0.getViewModel().syncTodayData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m653setupViews$lambda2(BloodSugarDataDetailActivity this$0, int i, BloodSugarActivityViewModel.BloodSugarDetail bloodSugarDetail) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDetailList().clear();
        this$0.getViewModel().getDetailList().addAll(bloodSugarDetail.getData());
        BloodSugarDetailAdapter bloodSugarDetailAdapter = null;
        if (this$0.getViewModel().getDetailList().size() == 0) {
            ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding = this$0.binding;
            if (activityBloodSugarDataDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodSugarDataDetailBinding = null;
            }
            ViewKt.visible(activityBloodSugarDataDetailBinding.noDataView);
            if (new DateUtil(i, true).isToday()) {
                ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding2 = this$0.binding;
                if (activityBloodSugarDataDetailBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodSugarDataDetailBinding2 = null;
                }
                ViewKt.visible(activityBloodSugarDataDetailBinding2.tvErrorText);
            } else {
                ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding3 = this$0.binding;
                if (activityBloodSugarDataDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodSugarDataDetailBinding3 = null;
                }
                ViewKt.gone(activityBloodSugarDataDetailBinding3.tvErrorText);
            }
        } else {
            ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding4 = this$0.binding;
            if (activityBloodSugarDataDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodSugarDataDetailBinding4 = null;
            }
            ViewKt.gone(activityBloodSugarDataDetailBinding4.noDataView);
        }
        BloodSugarDetailAdapter bloodSugarDetailAdapter2 = this$0.adapter;
        if (bloodSugarDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            bloodSugarDetailAdapter = bloodSugarDetailAdapter2;
        }
        bloodSugarDetailAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryBloodSugarByDateDetail(this.date);
        }
    }

    private final void startAnim() {
        ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding = this.binding;
        ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding2 = null;
        if (activityBloodSugarDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarDataDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityBloodSugarDataDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityBloodSugarDataDetailBinding activityBloodSugarDataDetailBinding3 = this.binding;
        if (activityBloodSugarDataDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodSugarDataDetailBinding2 = activityBloodSugarDataDetailBinding3;
        }
        activityBloodSugarDataDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
