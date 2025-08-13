package com.qcwireless.qcwatch.ui.home.menstruation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMenstruationBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSwitchView;
import com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel;
import com.qcwireless.qcwatch.ui.home.menstruation.bean.MenstruationBean;
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
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;
import skin.support.content.res.SkinCompatResources;

/* compiled from: MenstruationActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\"\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0012\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0014H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMenstruationBinding;", "selectDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "getSelectDate", "()Lcom/qcwireless/qc_utils/date/DateUtil;", "setSelectDate", "(Lcom/qcwireless/qc_utils/date/DateUtil;)V", "settingBean", "Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "addOrUpdate", "", "check", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "CalendarSelectListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MenstruationActivity extends BaseActivity {
    private ActivityMenstruationBinding binding;
    private DateUtil selectDate;
    private MenstruationBean settingBean;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MenstruationActivity() {
        final MenstruationActivity menstruationActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MenstruationViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MenstruationViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(menstruationActivity, Reflection.getOrCreateKotlinClass(MenstruationViewModel.class), qualifier, objArr);
            }
        });
        this.selectDate = new DateUtil();
    }

    private final MenstruationViewModel getViewModel() {
        return (MenstruationViewModel) this.viewModel.getValue();
    }

    public final DateUtil getSelectDate() {
        return this.selectDate;
    }

    public final void setSelectDate(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "<set-?>");
        this.selectDate = dateUtil;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMenstruationBinding activityMenstruationBindingInflate = ActivityMenstruationBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMenstruationBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMenstruationBindingInflate;
        if (activityMenstruationBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationBindingInflate = null;
        }
        ConstraintLayout root = activityMenstruationBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.menstruation_bg);
        this.settingBean = new MenstruationBean(false, 0, 0, 0L, false, 0, 0, 0, 255, null);
        getViewModel().queryMenstruationSetting();
        final ActivityMenstruationBinding activityMenstruationBinding = this.binding;
        ActivityMenstruationBinding activityMenstruationBinding2 = null;
        if (activityMenstruationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationBinding = null;
        }
        activityMenstruationBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_190));
        ViewKt.gone(activityMenstruationBinding.titleBar.divider);
        MenstruationActivity menstruationActivity = this;
        activityMenstruationBinding.titleBar.tvRightText.setTextColor(ContextCompat.getColor(menstruationActivity, R.color.menstruation_common_1));
        activityMenstruationBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_191));
        activityMenstruationBinding.titleBar.tvRightText.setTextColor(SkinCompatResources.getColor(menstruationActivity, R.color.title_bar_center));
        ViewKt.visible(activityMenstruationBinding.titleBar.tvRightText);
        activityMenstruationBinding.qcDateChange.setShowFuture(true);
        activityMenstruationBinding.qcDateChange.setMonth(this.selectDate);
        XLog.i(this.selectDate.getY_M_D());
        activityMenstruationBinding.qcDateChange.setDateListener(new QDateMonthSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                activityMenstruationBinding.calendarView.scrollToCalendar(dateUtil.getYear(), dateUtil.getMonth(), dateUtil.getDay());
            }
        });
        activityMenstruationBinding.qcMenstruationStatus.setQSettingItemCheckListener(new QSwitchView.OnSwitchStateChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchView.OnSwitchStateChangeListener
            public final void onSwitchStateChange(boolean z) {
                MenstruationActivity.m782setupViews$lambda2$lambda0(this.f$0, z);
            }
        });
        activityMenstruationBinding.calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity$$ExternalSyntheticLambda2
            @Override // com.haibin.calendarview.CalendarView.OnMonthChangeListener
            public final void onMonthChange(int i, int i2) {
                MenstruationActivity.m783setupViews$lambda2$lambda1(this.f$0, activityMenstruationBinding, i, i2);
            }
        });
        activityMenstruationBinding.calendarView.setOnCalendarSelectListener(new CalendarSelectListener());
        activityMenstruationBinding.calendarView.setBackground(SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_5), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_5), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_5));
        activityMenstruationBinding.calendarView.setSelectedColor(SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_6), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_6), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_6));
        activityMenstruationBinding.calendarView.setTextColor(SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_theme), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_common_1), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_show_4), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_common_1), SkinCompatResources.getColor(menstruationActivity, R.color.menstruation_common_1));
        View[] viewArr = new View[1];
        ActivityMenstruationBinding activityMenstruationBinding3 = this.binding;
        if (activityMenstruationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMenstruationBinding2 = activityMenstruationBinding3;
        }
        viewArr[0] = activityMenstruationBinding2.titleBar.tvRightText;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity.setupViews.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityMenstruationBinding activityMenstruationBinding4 = MenstruationActivity.this.binding;
                if (activityMenstruationBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMenstruationBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityMenstruationBinding4.titleBar.tvRightText)) {
                    MenstruationActivity menstruationActivity2 = MenstruationActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(menstruationActivity2, (Class<?>) MenstruationSettingActivity.class);
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
                    menstruationActivity2.startActivityForResult(intent, 200);
                }
            }
        });
        MenstruationActivity menstruationActivity2 = this;
        getViewModel().getUiState().observe(menstruationActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenstruationActivity.m784setupViews$lambda3(this.f$0, (MenstruationViewModel.MenstruationSettingUI) obj);
            }
        });
        getViewModel().getUiShowState().observe(menstruationActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.menstruation.MenstruationActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenstruationActivity.m785setupViews$lambda4(this.f$0, (MenstruationViewModel.MenstruationShowUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-0, reason: not valid java name */
    public static final void m782setupViews$lambda2$lambda0(MenstruationActivity this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.addOrUpdate(z);
        this$0.getViewModel().execToDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m783setupViews$lambda2$lambda1(MenstruationActivity this$0, ActivityMenstruationBinding this_run, int i, int i2) {
        DateUtil dateUtil;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        DateUtil dateUtil2 = new DateUtil();
        MenstruationViewModel viewModel = this$0.getViewModel();
        MenstruationActivity menstruationActivity = this$0;
        DateUtil dateUtil3 = new DateUtil(i, i2, dateUtil2.getDay());
        MenstruationBean menstruationBean = this$0.settingBean;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settingBean");
            menstruationBean = null;
        }
        viewModel.addMenstruationData(menstruationActivity, dateUtil3, false, menstruationBean);
        if (i == dateUtil2.getYear() && i2 == dateUtil2.getMonth()) {
            dateUtil = new DateUtil(i, i2, dateUtil2.getDay());
        } else {
            dateUtil = new DateUtil(i, i2, 1);
        }
        this$0.selectDate = dateUtil;
        if (i == dateUtil2.getYear() && i2 == dateUtil2.getMonth()) {
            this_run.qcDateChange.setMonth(new DateUtil(i, i2, dateUtil2.getDay()));
        } else {
            this_run.qcDateChange.setMonth(new DateUtil(i, i2, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m784setupViews$lambda3(MenstruationActivity this$0, MenstruationViewModel.MenstruationSettingUI menstruationSettingUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.settingBean = menstruationSettingUI.getEntity();
        this$0.addOrUpdate(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m785setupViews$lambda4(MenstruationActivity this$0, MenstruationViewModel.MenstruationShowUI menstruationShowUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityMenstruationBinding activityMenstruationBinding = this$0.binding;
        ActivityMenstruationBinding activityMenstruationBinding2 = null;
        if (activityMenstruationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMenstruationBinding = null;
        }
        activityMenstruationBinding.calendarView.setSchemeDate(menstruationShowUI.getMap());
        ActivityMenstruationBinding activityMenstruationBinding3 = this$0.binding;
        if (activityMenstruationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMenstruationBinding2 = activityMenstruationBinding3;
        }
        activityMenstruationBinding2.calendarView.scrollToCalendar(this$0.selectDate.getYear(), this$0.selectDate.getMonth(), this$0.selectDate.getDay());
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setupViews();
    }

    private final void addOrUpdate(boolean check) {
        DateUtil dateUtil = new DateUtil(this.selectDate.getUnixTimestamp(), true);
        MenstruationViewModel viewModel = getViewModel();
        MenstruationActivity menstruationActivity = this;
        MenstruationBean menstruationBean = this.settingBean;
        if (menstruationBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settingBean");
            menstruationBean = null;
        }
        viewModel.addMenstruationData(menstruationActivity, dateUtil, check, menstruationBean);
    }

    /* compiled from: MenstruationActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationActivity$CalendarSelectListener;", "Lcom/haibin/calendarview/CalendarView$OnCalendarSelectListener;", "(Lcom/qcwireless/qcwatch/ui/home/menstruation/MenstruationActivity;)V", "onCalendarOutOfRange", "", "calendar", "Lcom/haibin/calendarview/Calendar;", "onCalendarSelect", "isClick", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CalendarSelectListener implements CalendarView.OnCalendarSelectListener {
        @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
        public void onCalendarOutOfRange(Calendar calendar) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
        }

        public CalendarSelectListener() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
        public void onCalendarSelect(Calendar calendar, boolean isClick) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            String scheme = calendar.getScheme();
            ActivityMenstruationBinding activityMenstruationBinding = null;
            if (scheme == null) {
                ActivityMenstruationBinding activityMenstruationBinding2 = MenstruationActivity.this.binding;
                if (activityMenstruationBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMenstruationBinding = activityMenstruationBinding2;
                }
                activityMenstruationBinding.tvNumberDay.setText(MenstruationActivity.this.getString(R.string.qc_text_184));
                return;
            }
            if (scheme.length() == 0) {
                ActivityMenstruationBinding activityMenstruationBinding3 = MenstruationActivity.this.binding;
                if (activityMenstruationBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMenstruationBinding = activityMenstruationBinding3;
                }
                activityMenstruationBinding.tvNumberDay.setText(MenstruationActivity.this.getString(R.string.qc_text_184));
                return;
            }
            switch (scheme.hashCode()) {
                case 49:
                    if (scheme.equals("1")) {
                        ActivityMenstruationBinding activityMenstruationBinding4 = MenstruationActivity.this.binding;
                        if (activityMenstruationBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMenstruationBinding = activityMenstruationBinding4;
                        }
                        activityMenstruationBinding.tvNumberDay.setText(MenstruationActivity.this.getString(R.string.qc_text_186));
                        return;
                    }
                    break;
                case 50:
                    if (scheme.equals("2")) {
                        ActivityMenstruationBinding activityMenstruationBinding5 = MenstruationActivity.this.binding;
                        if (activityMenstruationBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMenstruationBinding = activityMenstruationBinding5;
                        }
                        activityMenstruationBinding.tvNumberDay.setText(MenstruationActivity.this.getString(R.string.qc_text_187));
                        return;
                    }
                    break;
                case 51:
                    if (scheme.equals("3")) {
                        ActivityMenstruationBinding activityMenstruationBinding6 = MenstruationActivity.this.binding;
                        if (activityMenstruationBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMenstruationBinding = activityMenstruationBinding6;
                        }
                        activityMenstruationBinding.tvNumberDay.setText(MenstruationActivity.this.getString(R.string.qc_text_185));
                        return;
                    }
                    break;
            }
            ActivityMenstruationBinding activityMenstruationBinding7 = MenstruationActivity.this.binding;
            if (activityMenstruationBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMenstruationBinding = activityMenstruationBinding7;
            }
            activityMenstruationBinding.tvNumberDay.setText(MenstruationActivity.this.getString(R.string.qc_text_184));
        }
    }
}
