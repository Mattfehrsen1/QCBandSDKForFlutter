package com.qcwireless.qcwatch.ui.home.step;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.DeviceSyncTodayStepsAndDetailEvent;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityStepBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView;
import com.qcwireless.qcwatch.ui.home.step.day.DayStepFragment;
import com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragment;
import com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragment;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: StepActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0016H\u0014J\b\u0010\u001d\u001a\u00020\u0016H\u0014J\b\u0010\u001e\u001a\u00020\u0016H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/step/StepActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityStepBinding;", "dayStepFragment", "Lcom/qcwireless/qcwatch/ui/home/step/day/DayStepFragment;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragmentManager$delegate", "Lkotlin/Lazy;", "monthStepFragment", "Lcom/qcwireless/qcwatch/ui/home/step/month/MonthStepFragment;", "titleList", "", "", "[Ljava/lang/String;", "weekStepFragment", "Lcom/qcwireless/qcwatch/ui/home/step/week/WeekStepFragment;", "hideFragments", "", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StepActivity extends BaseActivity {
    private ActivityStepBinding binding;
    private DayStepFragment dayStepFragment;

    /* renamed from: fragmentManager$delegate, reason: from kotlin metadata */
    private final Lazy fragmentManager = LazyKt.lazy(new Function0<FragmentManager>() { // from class: com.qcwireless.qcwatch.ui.home.step.StepActivity$fragmentManager$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FragmentManager invoke() {
            FragmentManager supportFragmentManager = this.this$0.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
            return supportFragmentManager;
        }
    });
    private MonthStepFragment monthStepFragment;
    private String[] titleList;
    private WeekStepFragment weekStepFragment;

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentManager getFragmentManager() {
        return (FragmentManager) this.fragmentManager.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStepBinding activityStepBindingInflate = ActivityStepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityStepBindingInflate, "inflate(layoutInflater)");
        this.binding = activityStepBindingInflate;
        if (activityStepBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityStepBindingInflate = null;
        }
        ConstraintLayout root = activityStepBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.step_bg);
        String string = getString(R.string.qc_text_117);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_117)");
        String string2 = getString(R.string.qc_text_105);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_105)");
        String string3 = getString(R.string.qc_text_106);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_106)");
        this.titleList = new String[]{string, string2, string3};
        ActivityStepBinding activityStepBinding = this.binding;
        String[] strArr = null;
        if (activityStepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityStepBinding = null;
        }
        activityStepBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_131));
        ViewKt.gone(activityStepBinding.titleBar.divider);
        QSwitchButtonView qSwitchButtonView = activityStepBinding.stepQsvView;
        String[] strArr2 = this.titleList;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleList");
        } else {
            strArr = strArr2;
        }
        qSwitchButtonView.setTitle(strArr);
        activityStepBinding.stepQsvView.select(0, true);
        activityStepBinding.stepQsvView.setActivity(this);
        activityStepBinding.stepQsvView.setOnTabSelectedListener(new QSwitchButtonView.OnTabSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.step.StepActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView.OnTabSelectedListener
            public void onTabSelected(int index) {
                FragmentTransaction fragmentTransactionBeginTransaction = this.this$0.getFragmentManager().beginTransaction();
                StepActivity stepActivity = this.this$0;
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "this");
                stepActivity.hideFragments(fragmentTransactionBeginTransaction);
                if (index != 0) {
                    if (index != 1) {
                        if (index == 2) {
                            if (stepActivity.monthStepFragment == null) {
                                stepActivity.monthStepFragment = MonthStepFragment.INSTANCE.newInstance();
                                MonthStepFragment monthStepFragment = stepActivity.monthStepFragment;
                                Intrinsics.checkNotNull(monthStepFragment);
                                fragmentTransactionBeginTransaction.add(R.id.stepContainer, monthStepFragment);
                            } else {
                                MonthStepFragment monthStepFragment2 = stepActivity.monthStepFragment;
                                Intrinsics.checkNotNull(monthStepFragment2);
                                fragmentTransactionBeginTransaction.show(monthStepFragment2);
                            }
                        }
                    } else if (stepActivity.weekStepFragment == null) {
                        stepActivity.weekStepFragment = WeekStepFragment.INSTANCE.newInstance();
                        WeekStepFragment weekStepFragment = stepActivity.weekStepFragment;
                        Intrinsics.checkNotNull(weekStepFragment);
                        fragmentTransactionBeginTransaction.add(R.id.stepContainer, weekStepFragment);
                    } else {
                        WeekStepFragment weekStepFragment2 = stepActivity.weekStepFragment;
                        Intrinsics.checkNotNull(weekStepFragment2);
                        fragmentTransactionBeginTransaction.show(weekStepFragment2);
                    }
                } else if (stepActivity.dayStepFragment == null) {
                    stepActivity.dayStepFragment = DayStepFragment.INSTANCE.newInstance();
                    DayStepFragment dayStepFragment = stepActivity.dayStepFragment;
                    Intrinsics.checkNotNull(dayStepFragment);
                    fragmentTransactionBeginTransaction.add(R.id.stepContainer, dayStepFragment);
                } else {
                    DayStepFragment dayStepFragment2 = stepActivity.dayStepFragment;
                    Intrinsics.checkNotNull(dayStepFragment2);
                    fragmentTransactionBeginTransaction.show(dayStepFragment2);
                }
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        });
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideFragments(FragmentTransaction transaction) {
        DayStepFragment dayStepFragment = this.dayStepFragment;
        if (dayStepFragment != null) {
            Intrinsics.checkNotNull(dayStepFragment);
            transaction.hide(dayStepFragment);
        }
        WeekStepFragment weekStepFragment = this.weekStepFragment;
        if (weekStepFragment != null) {
            Intrinsics.checkNotNull(weekStepFragment);
            transaction.hide(weekStepFragment);
        }
        MonthStepFragment monthStepFragment = this.monthStepFragment;
        if (monthStepFragment != null) {
            Intrinsics.checkNotNull(monthStepFragment);
            transaction.hide(monthStepFragment);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new DeviceSyncTodayStepsAndDetailEvent());
    }
}
