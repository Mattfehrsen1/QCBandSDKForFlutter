package com.qcwireless.qcwatch.ui.home.gps;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.databinding.ActivityGpsHistoryBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragment;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GpsHistoryActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082.¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsHistoryActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGpsHistoryBinding;", "dayGpsFragment", "Lcom/qcwireless/qcwatch/ui/home/gps/day/DayGpsFragment;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragmentManager$delegate", "Lkotlin/Lazy;", "titleList", "", "", "[Ljava/lang/String;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsHistoryActivity extends BaseActivity {
    private ActivityGpsHistoryBinding binding;
    private DayGpsFragment dayGpsFragment;

    /* renamed from: fragmentManager$delegate, reason: from kotlin metadata */
    private final Lazy fragmentManager = LazyKt.lazy(new Function0<FragmentManager>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsHistoryActivity$fragmentManager$2
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
    private String[] titleList;

    private final FragmentManager getFragmentManager() {
        return (FragmentManager) this.fragmentManager.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGpsHistoryBinding activityGpsHistoryBindingInflate = ActivityGpsHistoryBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGpsHistoryBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGpsHistoryBindingInflate;
        if (activityGpsHistoryBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsHistoryBindingInflate = null;
        }
        ConstraintLayout root = activityGpsHistoryBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.gps_bg);
        String string = getString(R.string.qc_text_117);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_117)");
        String string2 = getString(R.string.qc_text_105);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_105)");
        String string3 = getString(R.string.qc_text_106);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_106)");
        this.titleList = new String[]{string, string2, string3};
        ActivityGpsHistoryBinding activityGpsHistoryBinding = this.binding;
        if (activityGpsHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsHistoryBinding = null;
        }
        activityGpsHistoryBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_92));
        FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
        DayGpsFragment dayGpsFragment = this.dayGpsFragment;
        if (dayGpsFragment == null) {
            DayGpsFragment dayGpsFragmentNewInstance = DayGpsFragment.INSTANCE.newInstance();
            this.dayGpsFragment = dayGpsFragmentNewInstance;
            Intrinsics.checkNotNull(dayGpsFragmentNewInstance);
            fragmentTransactionBeginTransaction.add(R.id.gpsContainer, dayGpsFragmentNewInstance);
        } else {
            Intrinsics.checkNotNull(dayGpsFragment);
            fragmentTransactionBeginTransaction.show(dayGpsFragment);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }
}
