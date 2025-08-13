package com.qcwireless.qcwatch.ui.home.bloodsugar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import com.baidu.location.LocationConst;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityBloodSugarBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: BloodSugarActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\b\u0010\u0014\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodSugarBinding;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/bloodsugar/BloodSugarActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodSugarActivity extends BaseActivity {
    private ActivityBloodSugarBinding binding;
    private DateUtil dateUtil;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public BloodSugarActivity() {
        final BloodSugarActivity bloodSugarActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<BloodSugarActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodSugarActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodSugarActivity, Reflection.getOrCreateKotlinClass(BloodSugarActivityViewModel.class), qualifier, objArr);
            }
        });
        this.dateUtil = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BloodSugarActivityViewModel getViewModel() {
        return (BloodSugarActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodSugarBinding activityBloodSugarBindingInflate = ActivityBloodSugarBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodSugarBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodSugarBindingInflate;
        if (activityBloodSugarBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarBindingInflate = null;
        }
        NestedScrollView root = activityBloodSugarBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.heart_bg);
        getViewModel().queryLastData();
        final ActivityBloodSugarBinding activityBloodSugarBinding = this.binding;
        ActivityBloodSugarBinding activityBloodSugarBinding2 = null;
        if (activityBloodSugarBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarBinding = null;
        }
        activityBloodSugarBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_569));
        ViewKt.visible(activityBloodSugarBinding.titleBar.tvRightText);
        activityBloodSugarBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.app_warming);
        activityBloodSugarBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarActivity.m648setupViews$lambda3$lambda0(this.f$0, view);
            }
        });
        final QDateSwitchView qDateSwitchView = activityBloodSugarBinding.qcDateChange;
        qDateSwitchView.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$setupViews$1$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil date) {
                Intrinsics.checkNotNullParameter(date, "date");
                this.this$0.dateUtil = date;
                qDateSwitchView.setDateUtil(date);
                this.this$0.getViewModel().queryBloodSugarByDate(date);
            }
        });
        activityBloodSugarBinding.bloodSugarView.setSelectedListener(new QBloodSugarLineChartView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartView.OnSelectedListener
            public final void onSelected(QBloodSugarLineChartView.DataBean dataBean) {
                BloodSugarActivity.m649setupViews$lambda3$lambda2(activityBloodSugarBinding, dataBean);
            }
        });
        BloodSugarActivity bloodSugarActivity = this;
        getViewModel().getUiState().observe(bloodSugarActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodSugarActivity.m650setupViews$lambda4(this.f$0, (BloodSugarActivityViewModel.BloodSugarUI) obj);
            }
        });
        getViewModel().getLastDate().observe(bloodSugarActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodSugarActivity.m651setupViews$lambda5(this.f$0, (DateUtil) obj);
            }
        });
        ActivityBloodSugarBinding activityBloodSugarBinding3 = this.binding;
        if (activityBloodSugarBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodSugarBinding2 = activityBloodSugarBinding3;
        }
        activityBloodSugarBinding2.bloodSugarValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity.setupViews.4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) BloodSugarActivity.this.dateUtil.getUnixTimestamp());
                BloodSugarActivity bloodSugarActivity2 = BloodSugarActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(bloodSugarActivity2, (Class<?>) BloodSugarDataDetailActivity.class);
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
                bloodSugarActivity2.startActivity(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-0, reason: not valid java name */
    public static final void m648setupViews$lambda3$lambda0(BloodSugarActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BloodSugarActivity bloodSugarActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(bloodSugarActivity, (Class<?>) BloodSugarGuideActivity.class);
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
        bloodSugarActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m649setupViews$lambda3$lambda2(ActivityBloodSugarBinding this_run, final QBloodSugarLineChartView.DataBean dataBean) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        ThreadExtKt.ktxRunOnUi(this_run, new Function1<ActivityBloodSugarBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivity$setupViews$1$3$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActivityBloodSugarBinding activityBloodSugarBinding) {
                invoke2(activityBloodSugarBinding);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ActivityBloodSugarBinding ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                if (dataBean.getMinValue() == 0.0f) {
                    ktxRunOnUi.tvSugarValue.setText("--");
                } else {
                    TextView textView = ktxRunOnUi.tvSugarValue;
                    StringBuilder sb = new StringBuilder();
                    sb.append(dataBean.getMinValue());
                    sb.append('-');
                    sb.append(dataBean.getMaxValue());
                    textView.setText(sb.toString());
                }
                ktxRunOnUi.tvHM.setText(DateUtil.dayMinToStr(dataBean.getSeconds() / 60));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m650setupViews$lambda4(BloodSugarActivity this$0, BloodSugarActivityViewModel.BloodSugarUI bloodSugarUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityBloodSugarBinding activityBloodSugarBinding = null;
        if (!bloodSugarUI.getListData().isEmpty()) {
            ActivityBloodSugarBinding activityBloodSugarBinding2 = this$0.binding;
            if (activityBloodSugarBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodSugarBinding2 = null;
            }
            activityBloodSugarBinding2.bloodSugarView.setData(bloodSugarUI.getListData(), bloodSugarUI.getToday());
            int seconds = -1;
            for (QBloodSugarLineChartView.DataBean dataBean : bloodSugarUI.getListData()) {
                if (dataBean.getMaxValue() > 0.0f) {
                    seconds = dataBean.getSeconds();
                }
            }
            String strDayMinToStr = DateUtil.dayMinToStr(seconds / 60);
            if (seconds == -1) {
                ActivityBloodSugarBinding activityBloodSugarBinding3 = this$0.binding;
                if (activityBloodSugarBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBloodSugarBinding = activityBloodSugarBinding3;
                }
                activityBloodSugarBinding.bloodSugarValueDetail.setRightText("");
                return;
            }
            ActivityBloodSugarBinding activityBloodSugarBinding4 = this$0.binding;
            if (activityBloodSugarBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBloodSugarBinding = activityBloodSugarBinding4;
            }
            activityBloodSugarBinding.bloodSugarValueDetail.setRightText(strDayMinToStr);
            return;
        }
        ActivityBloodSugarBinding activityBloodSugarBinding5 = this$0.binding;
        if (activityBloodSugarBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarBinding5 = null;
        }
        activityBloodSugarBinding5.bloodSugarView.setData(bloodSugarUI.getListData(), bloodSugarUI.getToday());
        ActivityBloodSugarBinding activityBloodSugarBinding6 = this$0.binding;
        if (activityBloodSugarBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodSugarBinding = activityBloodSugarBinding6;
        }
        activityBloodSugarBinding.bloodSugarValueDetail.setRightText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m651setupViews$lambda5(BloodSugarActivity this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.dateUtil = it;
        ActivityBloodSugarBinding activityBloodSugarBinding = this$0.binding;
        if (activityBloodSugarBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodSugarBinding = null;
        }
        activityBloodSugarBinding.qcDateChange.setDateUtil(it);
        this$0.getViewModel().queryBloodSugarByDate(it);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof ManualRefreshEvent) {
            if (this.dateUtil.isToday()) {
                getViewModel().queryBloodSugarByDate(this.dateUtil);
            }
        } else if (messageEvent instanceof BluetoothEvent) {
            ((BluetoothEvent) messageEvent).getConnect();
        }
    }
}
