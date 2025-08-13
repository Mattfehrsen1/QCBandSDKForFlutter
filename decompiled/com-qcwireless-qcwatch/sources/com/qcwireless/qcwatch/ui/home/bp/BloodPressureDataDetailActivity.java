package com.qcwireless.qcwatch.ui.home.bp;

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
import com.qcwireless.qcwatch.databinding.ActivityBloodPressureDataDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel;
import com.qcwireless.qcwatch.ui.home.bp.adapter.BloodPressureDetailAdapter;
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

/* compiled from: BloodPressureDataDetailActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\u0010H\u0015J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/bp/adapter/BloodPressureDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodPressureDataDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureDataDetailActivity extends BaseActivity {
    private BloodPressureDetailAdapter adapter;
    private ActivityBloodPressureDataDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public BloodPressureDataDetailActivity() {
        final BloodPressureDataDetailActivity bloodPressureDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<BloodPressureViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodPressureViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodPressureDataDetailActivity, Reflection.getOrCreateKotlinClass(BloodPressureViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    private final BloodPressureViewModel getViewModel() {
        return (BloodPressureViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBindingInflate = ActivityBloodPressureDataDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodPressureDataDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodPressureDataDetailBindingInflate;
        if (activityBloodPressureDataDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureDataDetailBindingInflate = null;
        }
        ConstraintLayout root = activityBloodPressureDataDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        BloodPressureDetailAdapter bloodPressureDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        final int iIntValue = numValueOf.intValue();
        long j = iIntValue;
        this.date = new DateUtil(j, true);
        getViewModel().queryBloodPressureByDateDetail(this.date);
        BloodPressureDataDetailActivity bloodPressureDataDetailActivity = this;
        this.adapter = new BloodPressureDetailAdapter(bloodPressureDataDetailActivity, getViewModel().getDetailList());
        ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding = this.binding;
        if (activityBloodPressureDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureDataDetailBinding = null;
        }
        activityBloodPressureDataDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityBloodPressureDataDetailBinding.tvDataDate.setText(new DateUtil(j, true).getY_M_D());
        if (this.date.isToday()) {
            ViewKt.visible(activityBloodPressureDataDetailBinding.titleBar.tvRightText);
            activityBloodPressureDataDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityBloodPressureDataDetailBinding.titleBar.tvRightText);
        }
        activityBloodPressureDataDetailBinding.rcvBloodPressureDetail.setLayoutManager(new LinearLayoutManager(bloodPressureDataDetailActivity));
        RecyclerView recyclerView = activityBloodPressureDataDetailBinding.rcvBloodPressureDetail;
        BloodPressureDetailAdapter bloodPressureDetailAdapter2 = this.adapter;
        if (bloodPressureDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            bloodPressureDetailAdapter = bloodPressureDetailAdapter2;
        }
        recyclerView.setAdapter(bloodPressureDetailAdapter);
        activityBloodPressureDataDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureDataDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureDataDetailActivity.m664setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        getViewModel().getUiDetail().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureDataDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodPressureDataDetailActivity.m665setupViews$lambda2(this.f$0, iIntValue, (BloodPressureViewModel.BloodPressureDetail) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m664setupViews$lambda1$lambda0(BloodPressureDataDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startAnim();
        this$0.getViewModel().syncBp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m665setupViews$lambda2(BloodPressureDataDetailActivity this$0, int i, BloodPressureViewModel.BloodPressureDetail bloodPressureDetail) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDetailList().clear();
        this$0.getViewModel().getDetailList().addAll(bloodPressureDetail.getListData());
        BloodPressureDetailAdapter bloodPressureDetailAdapter = null;
        if (this$0.getViewModel().getDetailList().size() == 0) {
            ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding = this$0.binding;
            if (activityBloodPressureDataDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodPressureDataDetailBinding = null;
            }
            ViewKt.visible(activityBloodPressureDataDetailBinding.noDataView);
            if (new DateUtil(i, true).isToday()) {
                ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding2 = this$0.binding;
                if (activityBloodPressureDataDetailBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodPressureDataDetailBinding2 = null;
                }
                ViewKt.visible(activityBloodPressureDataDetailBinding2.tvErrorText);
            } else {
                ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding3 = this$0.binding;
                if (activityBloodPressureDataDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodPressureDataDetailBinding3 = null;
                }
                ViewKt.gone(activityBloodPressureDataDetailBinding3.tvErrorText);
            }
        } else {
            ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding4 = this$0.binding;
            if (activityBloodPressureDataDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodPressureDataDetailBinding4 = null;
            }
            ViewKt.gone(activityBloodPressureDataDetailBinding4.noDataView);
        }
        BloodPressureDetailAdapter bloodPressureDetailAdapter2 = this$0.adapter;
        if (bloodPressureDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            bloodPressureDetailAdapter = bloodPressureDetailAdapter2;
        }
        bloodPressureDetailAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryBloodPressureByDateDetail(this.date);
        }
    }

    private final void startAnim() {
        ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding = this.binding;
        ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding2 = null;
        if (activityBloodPressureDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureDataDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityBloodPressureDataDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityBloodPressureDataDetailBinding activityBloodPressureDataDetailBinding3 = this.binding;
        if (activityBloodPressureDataDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodPressureDataDetailBinding2 = activityBloodPressureDataDetailBinding3;
        }
        activityBloodPressureDataDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
