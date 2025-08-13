package com.qcwireless.qcwatch.ui.home.sport;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivitySportBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView;
import com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragment;
import com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragment;
import com.qcwireless.qcwatch.ui.home.sport.week.WeekSportFragment;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SportActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001bH\u0015J\b\u0010\u001e\u001a\u00020\u0016H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySportBinding;", "dayFragment", "Lcom/qcwireless/qcwatch/ui/home/sport/day/DaySportFragment;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragmentManager$delegate", "Lkotlin/Lazy;", "monthFragment", "Lcom/qcwireless/qcwatch/ui/home/sport/month/MonthSportFragment;", "titleList", "", "", "[Ljava/lang/String;", "weekFragment", "Lcom/qcwireless/qcwatch/ui/home/sport/week/WeekSportFragment;", "hideFragments", "", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportActivity extends BaseActivity {
    private ActivitySportBinding binding;
    private DaySportFragment dayFragment;

    /* renamed from: fragmentManager$delegate, reason: from kotlin metadata */
    private final Lazy fragmentManager = LazyKt.lazy(new Function0<FragmentManager>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportActivity$fragmentManager$2
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
    private MonthSportFragment monthFragment;
    private String[] titleList;
    private WeekSportFragment weekFragment;

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
        ActivitySportBinding activitySportBindingInflate = ActivitySportBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySportBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySportBindingInflate;
        if (activitySportBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportBindingInflate = null;
        }
        ConstraintLayout root = activitySportBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.sport_bg);
        String string = getString(R.string.qc_text_117);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_117)");
        String string2 = getString(R.string.qc_text_105);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_105)");
        String string3 = getString(R.string.qc_text_106);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_106)");
        this.titleList = new String[]{string, string2, string3};
        ActivitySportBinding activitySportBinding = this.binding;
        String[] strArr = null;
        if (activitySportBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportBinding = null;
        }
        activitySportBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_83));
        ViewKt.gone(activitySportBinding.titleBar.divider);
        QSwitchButtonView qSwitchButtonView = activitySportBinding.qsvView;
        String[] strArr2 = this.titleList;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleList");
        } else {
            strArr = strArr2;
        }
        qSwitchButtonView.setTitle(strArr);
        activitySportBinding.qsvView.select(0, true);
        activitySportBinding.qsvView.setActivity(this);
        activitySportBinding.qsvView.setOnTabSelectedListener(new QSwitchButtonView.OnTabSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView.OnTabSelectedListener
            public void onTabSelected(int index) {
                FragmentTransaction fragmentTransactionBeginTransaction = this.this$0.getFragmentManager().beginTransaction();
                SportActivity sportActivity = this.this$0;
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "this");
                sportActivity.hideFragments(fragmentTransactionBeginTransaction);
                if (index != 0) {
                    if (index != 1) {
                        if (index == 2) {
                            if (sportActivity.monthFragment == null) {
                                sportActivity.monthFragment = MonthSportFragment.INSTANCE.newInstance();
                                MonthSportFragment monthSportFragment = sportActivity.monthFragment;
                                Intrinsics.checkNotNull(monthSportFragment);
                                fragmentTransactionBeginTransaction.add(R.id.sportContainer, monthSportFragment);
                            } else {
                                MonthSportFragment monthSportFragment2 = sportActivity.monthFragment;
                                Intrinsics.checkNotNull(monthSportFragment2);
                                fragmentTransactionBeginTransaction.show(monthSportFragment2);
                            }
                        }
                    } else if (sportActivity.weekFragment == null) {
                        sportActivity.weekFragment = WeekSportFragment.INSTANCE.newInstance();
                        WeekSportFragment weekSportFragment = sportActivity.weekFragment;
                        Intrinsics.checkNotNull(weekSportFragment);
                        fragmentTransactionBeginTransaction.add(R.id.sportContainer, weekSportFragment);
                    } else {
                        WeekSportFragment weekSportFragment2 = sportActivity.weekFragment;
                        Intrinsics.checkNotNull(weekSportFragment2);
                        fragmentTransactionBeginTransaction.show(weekSportFragment2);
                    }
                } else if (sportActivity.dayFragment == null) {
                    sportActivity.dayFragment = DaySportFragment.INSTANCE.newInstance();
                    DaySportFragment daySportFragment = sportActivity.dayFragment;
                    Intrinsics.checkNotNull(daySportFragment);
                    fragmentTransactionBeginTransaction.add(R.id.sportContainer, daySportFragment);
                } else {
                    DaySportFragment daySportFragment2 = sportActivity.dayFragment;
                    Intrinsics.checkNotNull(daySportFragment2);
                    fragmentTransactionBeginTransaction.show(daySportFragment2);
                }
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideFragments(FragmentTransaction transaction) {
        DaySportFragment daySportFragment = this.dayFragment;
        if (daySportFragment != null) {
            Intrinsics.checkNotNull(daySportFragment);
            transaction.hide(daySportFragment);
        }
        WeekSportFragment weekSportFragment = this.weekFragment;
        if (weekSportFragment != null) {
            Intrinsics.checkNotNull(weekSportFragment);
            transaction.hide(weekSportFragment);
        }
        MonthSportFragment monthSportFragment = this.monthFragment;
        if (monthSportFragment != null) {
            Intrinsics.checkNotNull(monthSportFragment);
            transaction.hide(monthSportFragment);
        }
    }
}
