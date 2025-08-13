package com.qcwireless.qcwatch.ui.home.sleep;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivitySleepBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView;
import com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragment;
import com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragment;
import com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragment;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001bH\u0015J\b\u0010\u001e\u001a\u00020\u0016H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/SleepActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySleepBinding;", "daySleepFragment", "Lcom/qcwireless/qcwatch/ui/home/sleep/day/DaySleepFragment;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragmentManager$delegate", "Lkotlin/Lazy;", "monthSleepFragment", "Lcom/qcwireless/qcwatch/ui/home/sleep/month/MonthSleepFragment;", "titleList", "", "", "[Ljava/lang/String;", "weekSleepFragment", "Lcom/qcwireless/qcwatch/ui/home/sleep/week/WeekSleepFragment;", "hideFragments", "", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SleepActivity extends BaseActivity {
    private ActivitySleepBinding binding;
    private DaySleepFragment daySleepFragment;

    /* renamed from: fragmentManager$delegate, reason: from kotlin metadata */
    private final Lazy fragmentManager = LazyKt.lazy(new Function0<FragmentManager>() { // from class: com.qcwireless.qcwatch.ui.home.sleep.SleepActivity$fragmentManager$2
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
    private MonthSleepFragment monthSleepFragment;
    private String[] titleList;
    private WeekSleepFragment weekSleepFragment;

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentManager getFragmentManager() {
        return (FragmentManager) this.fragmentManager.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySleepBinding activitySleepBindingInflate = ActivitySleepBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySleepBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySleepBindingInflate;
        if (activitySleepBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySleepBindingInflate = null;
        }
        ConstraintLayout root = activitySleepBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.sleep_bg);
        String string = getString(R.string.qc_text_117);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_117)");
        String string2 = getString(R.string.qc_text_105);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_105)");
        String string3 = getString(R.string.qc_text_106);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_106)");
        this.titleList = new String[]{string, string2, string3};
        ActivitySleepBinding activitySleepBinding = this.binding;
        String[] strArr = null;
        if (activitySleepBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySleepBinding = null;
        }
        activitySleepBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_84));
        ViewKt.gone(activitySleepBinding.titleBar.divider);
        QSwitchButtonView qSwitchButtonView = activitySleepBinding.sleepQsvView;
        String[] strArr2 = this.titleList;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleList");
        } else {
            strArr = strArr2;
        }
        qSwitchButtonView.setTitle(strArr);
        activitySleepBinding.sleepQsvView.setActivity(this);
        activitySleepBinding.sleepQsvView.select(0, true);
        activitySleepBinding.sleepQsvView.setOnTabSelectedListener(new QSwitchButtonView.OnTabSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.sleep.SleepActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView.OnTabSelectedListener
            public void onTabSelected(int index) {
                FragmentTransaction fragmentTransactionBeginTransaction = this.this$0.getFragmentManager().beginTransaction();
                SleepActivity sleepActivity = this.this$0;
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "this");
                sleepActivity.hideFragments(fragmentTransactionBeginTransaction);
                if (index != 0) {
                    if (index != 1) {
                        if (index == 2) {
                            if (sleepActivity.monthSleepFragment == null) {
                                sleepActivity.monthSleepFragment = MonthSleepFragment.INSTANCE.newInstance();
                                MonthSleepFragment monthSleepFragment = sleepActivity.monthSleepFragment;
                                Intrinsics.checkNotNull(monthSleepFragment);
                                fragmentTransactionBeginTransaction.add(R.id.sleepContainer, monthSleepFragment);
                            } else {
                                MonthSleepFragment monthSleepFragment2 = sleepActivity.monthSleepFragment;
                                Intrinsics.checkNotNull(monthSleepFragment2);
                                fragmentTransactionBeginTransaction.show(monthSleepFragment2);
                            }
                        }
                    } else if (sleepActivity.weekSleepFragment == null) {
                        sleepActivity.weekSleepFragment = WeekSleepFragment.INSTANCE.newInstance();
                        WeekSleepFragment weekSleepFragment = sleepActivity.weekSleepFragment;
                        Intrinsics.checkNotNull(weekSleepFragment);
                        fragmentTransactionBeginTransaction.add(R.id.sleepContainer, weekSleepFragment);
                    } else {
                        WeekSleepFragment weekSleepFragment2 = sleepActivity.weekSleepFragment;
                        Intrinsics.checkNotNull(weekSleepFragment2);
                        fragmentTransactionBeginTransaction.show(weekSleepFragment2);
                    }
                } else if (sleepActivity.daySleepFragment == null) {
                    sleepActivity.daySleepFragment = DaySleepFragment.INSTANCE.newInstance();
                    DaySleepFragment daySleepFragment = sleepActivity.daySleepFragment;
                    Intrinsics.checkNotNull(daySleepFragment);
                    fragmentTransactionBeginTransaction.add(R.id.sleepContainer, daySleepFragment);
                } else {
                    DaySleepFragment daySleepFragment2 = sleepActivity.daySleepFragment;
                    Intrinsics.checkNotNull(daySleepFragment2);
                    fragmentTransactionBeginTransaction.show(daySleepFragment2);
                }
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideFragments(FragmentTransaction transaction) {
        DaySleepFragment daySleepFragment = this.daySleepFragment;
        if (daySleepFragment != null) {
            Intrinsics.checkNotNull(daySleepFragment);
            transaction.hide(daySleepFragment);
        }
        WeekSleepFragment weekSleepFragment = this.weekSleepFragment;
        if (weekSleepFragment != null) {
            Intrinsics.checkNotNull(weekSleepFragment);
            transaction.hide(weekSleepFragment);
        }
        MonthSleepFragment monthSleepFragment = this.monthSleepFragment;
        if (monthSleepFragment != null) {
            Intrinsics.checkNotNull(monthSleepFragment);
            transaction.hide(monthSleepFragment);
        }
    }
}
