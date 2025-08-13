package com.qcwireless.qcwatch.ui.home.pressure;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.haibin.calendarview.Calendar;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityRingPressureBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView;
import com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment;
import com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragment;
import com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragment;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RingPressureActivity.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\bJ\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0002J\u0012\u0010&\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020!H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR&\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u001cX\u0082.¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/RingPressureActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityRingPressureBinding;", "calendarDialog", "Lcom/qcwireless/qcwatch/base/dialog/CenterDialog;", "clickDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "dayPressureFragment", "Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragment;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragmentManager$delegate", "Lkotlin/Lazy;", "map", "", "", "Lcom/haibin/calendarview/Calendar;", "getMap", "()Ljava/util/Map;", "setMap", "(Ljava/util/Map;)V", "monthPressureFragment", "Lcom/qcwireless/qcwatch/ui/home/pressure/month/MonthPressureFragment;", "titleList", "", "[Ljava/lang/String;", "weekPressureFragment", "Lcom/qcwireless/qcwatch/ui/home/pressure/week/WeekPressureFragment;", "doCalendar", "", "date", "hideFragments", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RingPressureActivity extends BaseActivity {
    private ActivityRingPressureBinding binding;
    private CenterDialog calendarDialog;
    private DayPressureFragment dayPressureFragment;
    private MonthPressureFragment monthPressureFragment;
    private String[] titleList;
    private WeekPressureFragment weekPressureFragment;

    /* renamed from: fragmentManager$delegate, reason: from kotlin metadata */
    private final Lazy fragmentManager = LazyKt.lazy(new Function0<FragmentManager>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureActivity$fragmentManager$2
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
    private Map<String, Calendar> map = new HashMap();
    private DateUtil clickDate = new DateUtil();

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentManager getFragmentManager() {
        return (FragmentManager) this.fragmentManager.getValue();
    }

    public final Map<String, Calendar> getMap() {
        return this.map;
    }

    public final void setMap(Map<String, Calendar> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.map = map;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRingPressureBinding activityRingPressureBindingInflate = ActivityRingPressureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRingPressureBindingInflate, "inflate(layoutInflater)");
        this.binding = activityRingPressureBindingInflate;
        if (activityRingPressureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingPressureBindingInflate = null;
        }
        ConstraintLayout root = activityRingPressureBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.pressure_bg);
        String string = getString(R.string.qc_text_117);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_117)");
        String string2 = getString(R.string.qc_text_105);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_105)");
        String string3 = getString(R.string.qc_text_106);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_106)");
        this.titleList = new String[]{string, string2, string3};
        ActivityRingPressureBinding activityRingPressureBinding = this.binding;
        String[] strArr = null;
        if (activityRingPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRingPressureBinding = null;
        }
        activityRingPressureBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_6666561));
        ViewKt.gone(activityRingPressureBinding.titleBar.divider);
        QSwitchButtonView qSwitchButtonView = activityRingPressureBinding.stepQsvView;
        String[] strArr2 = this.titleList;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleList");
        } else {
            strArr = strArr2;
        }
        qSwitchButtonView.setTitle(strArr);
        activityRingPressureBinding.stepQsvView.select(0, true);
        activityRingPressureBinding.stepQsvView.setActivity(this);
        activityRingPressureBinding.stepQsvView.setOnTabSelectedListener(new QSwitchButtonView.OnTabSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView.OnTabSelectedListener
            public void onTabSelected(int index) {
                FragmentTransaction fragmentTransactionBeginTransaction = this.this$0.getFragmentManager().beginTransaction();
                RingPressureActivity ringPressureActivity = this.this$0;
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "this");
                ringPressureActivity.hideFragments(fragmentTransactionBeginTransaction);
                if (index != 0) {
                    if (index != 1) {
                        if (index == 2) {
                            if (ringPressureActivity.monthPressureFragment == null) {
                                ringPressureActivity.monthPressureFragment = MonthPressureFragment.INSTANCE.newInstance();
                                MonthPressureFragment monthPressureFragment = ringPressureActivity.monthPressureFragment;
                                Intrinsics.checkNotNull(monthPressureFragment);
                                fragmentTransactionBeginTransaction.add(R.id.stepContainer, monthPressureFragment);
                            } else {
                                MonthPressureFragment monthPressureFragment2 = ringPressureActivity.monthPressureFragment;
                                Intrinsics.checkNotNull(monthPressureFragment2);
                                fragmentTransactionBeginTransaction.show(monthPressureFragment2);
                            }
                        }
                    } else if (ringPressureActivity.weekPressureFragment == null) {
                        ringPressureActivity.weekPressureFragment = WeekPressureFragment.INSTANCE.newInstance();
                        WeekPressureFragment weekPressureFragment = ringPressureActivity.weekPressureFragment;
                        Intrinsics.checkNotNull(weekPressureFragment);
                        fragmentTransactionBeginTransaction.add(R.id.stepContainer, weekPressureFragment);
                    } else {
                        WeekPressureFragment weekPressureFragment2 = ringPressureActivity.weekPressureFragment;
                        Intrinsics.checkNotNull(weekPressureFragment2);
                        fragmentTransactionBeginTransaction.show(weekPressureFragment2);
                    }
                } else if (ringPressureActivity.dayPressureFragment == null) {
                    ringPressureActivity.dayPressureFragment = DayPressureFragment.INSTANCE.newInstance();
                    DayPressureFragment dayPressureFragment = ringPressureActivity.dayPressureFragment;
                    Intrinsics.checkNotNull(dayPressureFragment);
                    fragmentTransactionBeginTransaction.add(R.id.stepContainer, dayPressureFragment);
                } else {
                    DayPressureFragment dayPressureFragment2 = ringPressureActivity.dayPressureFragment;
                    Intrinsics.checkNotNull(dayPressureFragment2);
                    fragmentTransactionBeginTransaction.show(dayPressureFragment2);
                }
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        });
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<RingPressureActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.RingPressureActivity.setupViews.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RingPressureActivity ringPressureActivity) {
                invoke2(ringPressureActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RingPressureActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.setMap(PressureRepository.INSTANCE.getGetInstance().calendarPressure(new DateUtil().getYear(), new DateUtil().getMonth()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideFragments(FragmentTransaction transaction) {
        DayPressureFragment dayPressureFragment = this.dayPressureFragment;
        if (dayPressureFragment != null) {
            Intrinsics.checkNotNull(dayPressureFragment);
            transaction.hide(dayPressureFragment);
        }
        WeekPressureFragment weekPressureFragment = this.weekPressureFragment;
        if (weekPressureFragment != null) {
            Intrinsics.checkNotNull(weekPressureFragment);
            transaction.hide(weekPressureFragment);
        }
        MonthPressureFragment monthPressureFragment = this.monthPressureFragment;
        if (monthPressureFragment != null) {
            Intrinsics.checkNotNull(monthPressureFragment);
            transaction.hide(monthPressureFragment);
        }
    }

    public final void doCalendar(DateUtil date) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.clickDate = date;
    }
}
