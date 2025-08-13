package com.qcwireless.qcwatch.ui.home.heart;

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
import com.qcwireless.qcwatch.databinding.ActivityHeartRateDataDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel;
import com.qcwireless.qcwatch.ui.home.heart.adapter.HeartRateDetailAdapter;
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

/* compiled from: HeartRateDataDetailActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\u0010H\u0015J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartRateDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/heart/adapter/HeartRateDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityHeartRateDataDetailBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "startAnim", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartRateDataDetailActivity extends BaseActivity {
    private HeartRateDetailAdapter adapter;
    private ActivityHeartRateDataDetailBinding binding;
    private DateUtil date;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public HeartRateDataDetailActivity() {
        final HeartRateDataDetailActivity heartRateDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HeartActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartRateDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HeartActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(heartRateDataDetailActivity, Reflection.getOrCreateKotlinClass(HeartActivityViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    private final HeartActivityViewModel getViewModel() {
        return (HeartActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBindingInflate = ActivityHeartRateDataDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityHeartRateDataDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityHeartRateDataDetailBindingInflate;
        if (activityHeartRateDataDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartRateDataDetailBindingInflate = null;
        }
        ConstraintLayout root = activityHeartRateDataDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        HeartRateDetailAdapter heartRateDetailAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP)) : null;
        Intrinsics.checkNotNull(numValueOf);
        final int iIntValue = numValueOf.intValue();
        long j = iIntValue;
        this.date = new DateUtil(j, true);
        getViewModel().queryHeartRateByDateDetail(this.date);
        HeartRateDataDetailActivity heartRateDataDetailActivity = this;
        HeartRateDetailAdapter heartRateDetailAdapter2 = new HeartRateDetailAdapter(heartRateDataDetailActivity, getViewModel().getDetailList());
        this.adapter = heartRateDetailAdapter2;
        heartRateDetailAdapter2.setEmptyView(R.layout.recycleview_heart_empty);
        ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding = this.binding;
        if (activityHeartRateDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartRateDataDetailBinding = null;
        }
        activityHeartRateDataDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityHeartRateDataDetailBinding.tvDataDate.setText(new DateUtil(j, true).getY_M_D());
        if (this.date.isToday()) {
            ViewKt.visible(activityHeartRateDataDetailBinding.titleBar.tvRightText);
            activityHeartRateDataDetailBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.data_detail_refresh);
        } else {
            ViewKt.gone(activityHeartRateDataDetailBinding.titleBar.tvRightText);
        }
        activityHeartRateDataDetailBinding.rcvHeartRateDetail.setLayoutManager(new LinearLayoutManager(heartRateDataDetailActivity));
        RecyclerView recyclerView = activityHeartRateDataDetailBinding.rcvHeartRateDetail;
        HeartRateDetailAdapter heartRateDetailAdapter3 = this.adapter;
        if (heartRateDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            heartRateDetailAdapter = heartRateDetailAdapter3;
        }
        recyclerView.setAdapter(heartRateDetailAdapter);
        activityHeartRateDataDetailBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartRateDataDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws InterruptedException {
                HeartRateDataDetailActivity.m763setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        getViewModel().getUiDetail().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartRateDataDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeartRateDataDetailActivity.m764setupViews$lambda2(this.f$0, iIntValue, (HeartActivityViewModel.HeartRateDetail) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m763setupViews$lambda1$lambda0(HeartRateDataDetailActivity this$0, View view) throws InterruptedException {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m764setupViews$lambda2(HeartRateDataDetailActivity this$0, int i, HeartActivityViewModel.HeartRateDetail heartRateDetail) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDetailList().clear();
        this$0.getViewModel().getDetailList().addAll(heartRateDetail.getData());
        HeartRateDetailAdapter heartRateDetailAdapter = null;
        if (this$0.getViewModel().getDetailList().size() == 0) {
            ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding = this$0.binding;
            if (activityHeartRateDataDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHeartRateDataDetailBinding = null;
            }
            ViewKt.visible(activityHeartRateDataDetailBinding.noDataView);
            if (new DateUtil(i, true).isToday()) {
                ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding2 = this$0.binding;
                if (activityHeartRateDataDetailBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHeartRateDataDetailBinding2 = null;
                }
                ViewKt.visible(activityHeartRateDataDetailBinding2.tvErrorText);
            } else {
                ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding3 = this$0.binding;
                if (activityHeartRateDataDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHeartRateDataDetailBinding3 = null;
                }
                ViewKt.gone(activityHeartRateDataDetailBinding3.tvErrorText);
            }
        } else {
            ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding4 = this$0.binding;
            if (activityHeartRateDataDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHeartRateDataDetailBinding4 = null;
            }
            ViewKt.gone(activityHeartRateDataDetailBinding4.noDataView);
        }
        HeartRateDetailAdapter heartRateDetailAdapter2 = this$0.adapter;
        if (heartRateDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            heartRateDetailAdapter = heartRateDetailAdapter2;
        }
        heartRateDetailAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryHeartRateByDateDetail(this.date);
        }
    }

    private final void startAnim() {
        ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding = this.binding;
        ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding2 = null;
        if (activityHeartRateDataDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartRateDataDetailBinding = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityHeartRateDataDetailBinding.titleBar.tvRightText, Key.ROTATION, 0.0f, 360.0f);
        float f = getResources().getDisplayMetrics().density * LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        ActivityHeartRateDataDetailBinding activityHeartRateDataDetailBinding3 = this.binding;
        if (activityHeartRateDataDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHeartRateDataDetailBinding2 = activityHeartRateDataDetailBinding3;
        }
        activityHeartRateDataDetailBinding2.titleBar.tvRightText.setCameraDistance(f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
    }
}
