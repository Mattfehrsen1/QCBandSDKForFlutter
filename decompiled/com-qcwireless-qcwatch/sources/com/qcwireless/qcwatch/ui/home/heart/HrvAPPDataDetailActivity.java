package com.qcwireless.qcwatch.ui.home.heart;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.LocationConst;
import com.elvishew.xlog.XLog;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityRingHrvDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.HRVManualEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository;
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
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: HrvAPPDataDetailActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HrvAPPDataDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/heart/adapter/ManualHrvDetailAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityRingHrvDetailBinding;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "queryManualPressure", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HrvAPPDataDetailActivity extends BaseActivity {
    private ManualHrvDetailAdapter adapter;
    private ActivityRingHrvDetailBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public HrvAPPDataDetailActivity() {
        final HrvAPPDataDetailActivity hrvAPPDataDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HrvActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvAPPDataDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.heart.HrvActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HrvActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(hrvAPPDataDetailActivity, Reflection.getOrCreateKotlinClass(HrvActivityViewModel.class), qualifier, objArr);
            }
        });
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
        queryManualPressure(new DateUtil(jIntValue, true));
        HrvAPPDataDetailActivity hrvAPPDataDetailActivity = this;
        ManualHrvDetailAdapter manualHrvDetailAdapter2 = new ManualHrvDetailAdapter(hrvAPPDataDetailActivity, getViewModel().getManualList());
        this.adapter = manualHrvDetailAdapter2;
        manualHrvDetailAdapter2.setEmptyView(R.layout.recycleview_heart_empty);
        ActivityRingHrvDetailBinding activityRingHrvDetailBinding = this.binding;
        if (activityRingHrvDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingHrvDetailBinding = null;
        }
        activityRingHrvDetailBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_480));
        activityRingHrvDetailBinding.tvDataDate.setText(new DateUtil(jIntValue, true).getY_M_D());
        ViewKt.gone(activityRingHrvDetailBinding.titleBar.tvRightText);
        activityRingHrvDetailBinding.rcvHeartRateDetail.setLayoutManager(new LinearLayoutManager(hrvAPPDataDetailActivity));
        RecyclerView recyclerView = activityRingHrvDetailBinding.rcvHeartRateDetail;
        ManualHrvDetailAdapter manualHrvDetailAdapter3 = this.adapter;
        if (manualHrvDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            manualHrvDetailAdapter = manualHrvDetailAdapter3;
        }
        recyclerView.setAdapter(manualHrvDetailAdapter);
    }

    private final void queryManualPressure(final DateUtil date) {
        ThreadExtKt.ktxRunOnBgFix(this, new Function1<HrvAPPDataDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvAPPDataDetailActivity.queryManualPressure.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HrvAPPDataDetailActivity hrvAPPDataDetailActivity) {
                invoke2(hrvAPPDataDetailActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HrvAPPDataDetailActivity ktxRunOnBgFix) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                List<HRVManualEntity> listQueryManualPressureAll = HRVRepository.INSTANCE.getGetInstance().queryManualPressureAll(date);
                ktxRunOnBgFix.getViewModel().getManualList().clear();
                XLog.i(Integer.valueOf(listQueryManualPressureAll.size()));
                Iterator<T> it = listQueryManualPressureAll.iterator();
                while (it.hasNext()) {
                    ktxRunOnBgFix.getViewModel().getManualList().add(new HeartRateDetailBean(r1.getTimestamp(), ((HRVManualEntity) it.next()).getPressure()));
                }
                ThreadExtKt.ktxRunOnUi(ktxRunOnBgFix, new Function1<HrvAPPDataDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvAPPDataDetailActivity.queryManualPressure.1.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HrvAPPDataDetailActivity hrvAPPDataDetailActivity) {
                        invoke2(hrvAPPDataDetailActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HrvAPPDataDetailActivity ktxRunOnUi) {
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
                            ViewKt.gone(activityRingHrvDetailBinding3.noDataView);
                            ActivityRingHrvDetailBinding activityRingHrvDetailBinding4 = ktxRunOnUi.binding;
                            if (activityRingHrvDetailBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRingHrvDetailBinding4 = null;
                            }
                            ViewKt.gone(activityRingHrvDetailBinding4.tvErrorText);
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
}
