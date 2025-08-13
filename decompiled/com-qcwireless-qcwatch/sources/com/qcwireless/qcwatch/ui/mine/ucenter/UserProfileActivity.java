package com.qcwireless.qcwatch.ui.mine.ucenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.baidu.location.LocationClientOption;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomBirthdayDialog;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog;
import com.qcwireless.qcwatch.base.dialog.BottomListDialog;
import com.qcwireless.qcwatch.base.dialog.EditTextDialog;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityUserProfileBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.text.ParseException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: UserProfileActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\"H\u0014J\b\u0010&\u001a\u00020\"H\u0014J\b\u0010'\u001a\u00020\"H\u0014J\b\u0010(\u001a\u00020\"H\u0002J\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020+J\b\u0010,\u001a\u00020\"H\u0014J\u0006\u0010-\u001a\u00020\"J\u0006\u0010.\u001a\u00020\"J\u0006\u0010/\u001a\u00020\"J\u0006\u00100\u001a\u00020\"J\u0006\u00101\u001a\u00020\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001e¨\u00062"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityUserProfileBinding;", "birthdayDialog", "Lcom/qcwireless/qcwatch/base/dialog/BottomBirthdayDialog;", "bottomDialog", "Lcom/qcwireless/qcwatch/base/dialog/BottomHeightWeightDialog;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "genderDialog", "Lcom/qcwireless/qcwatch/base/dialog/BottomListDialog;", "heightDialog", "<set-?>", "", "metric", "getMetric", "()Z", "setMetric", "(Z)V", "metric$delegate", "Lkotlin/properties/ReadWriteProperty;", "nickNameDialog", "Lcom/qcwireless/qcwatch/base/dialog/EditTextDialog;", "userEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onStop", "refreshHeightAndWeight", "setNickNameDialog", "text", "", "setupViews", "showBirthdayDialog", "showGenderDialog", "showHeightDialog", "showLogOffDialog", "showWeightDialog", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserProfileActivity extends BaseActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(UserProfileActivity.class, "metric", "getMetric()Z", 0))};
    private ActivityUserProfileBinding binding;
    private BottomBirthdayDialog birthdayDialog;
    private BottomHeightWeightDialog bottomDialog;
    private DeviceSetting deviceSetting;
    private BottomListDialog genderDialog;
    private BottomHeightWeightDialog heightDialog;

    /* renamed from: metric$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty metric;
    private EditTextDialog nickNameDialog;
    private UserEntity userEntity;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public UserProfileActivity() {
        final UserProfileActivity userProfileActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<UserProfileViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final UserProfileViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(userProfileActivity, Reflection.getOrCreateKotlinClass(UserProfileViewModel.class), qualifier, objArr);
            }
        });
        this.metric = Delegates.INSTANCE.notNull();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UserProfileViewModel getViewModel() {
        return (UserProfileViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getMetric() {
        return ((Boolean) this.metric.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    private final void setMetric(boolean z) {
        this.metric.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserProfileBinding activityUserProfileBindingInflate = ActivityUserProfileBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityUserProfileBindingInflate, "inflate(layoutInflater)");
        this.binding = activityUserProfileBindingInflate;
        if (activityUserProfileBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserProfileBindingInflate = null;
        }
        ConstraintLayout root = activityUserProfileBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialogTimeout(1000);
        getViewModel().queryUserByUidUI();
        getViewModel().initDialogData();
        setMetric(UserConfig.INSTANCE.getInstance().getMetric());
        ActivityUserProfileBinding activityUserProfileBinding = this.binding;
        if (activityUserProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserProfileBinding = null;
        }
        activityUserProfileBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_151));
        ViewKt.visible(activityUserProfileBinding.titleBar.tvRightText);
        activityUserProfileBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        activityUserProfileBinding.userCenterHeight.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showHeightDialog();
            }
        });
        activityUserProfileBinding.userCenterWeight.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showWeightDialog();
            }
        });
        activityUserProfileBinding.userCenterBirthday.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) throws ParseException {
                this.this$0.showBirthdayDialog();
            }
        });
        activityUserProfileBinding.userCenterGender.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showGenderDialog();
            }
        });
        activityUserProfileBinding.userLogOff.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showLogOffDialog();
            }
        });
        activityUserProfileBinding.userCenterName.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$1$6
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                if (this.this$0.userEntity != null) {
                    UserProfileActivity userProfileActivity = this.this$0;
                    UserEntity userEntity = userProfileActivity.userEntity;
                    if (userEntity == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                        userEntity = null;
                    }
                    userProfileActivity.setNickNameDialog(userEntity.getNickName());
                    return;
                }
                long uid = UserConfig.INSTANCE.getInstance().getUid();
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                this.this$0.userEntity = new UserEntity(uid, "", "", 1, 60.0f, 120, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, y_m_d, 0);
            }
        });
        activityUserProfileBinding.btnExit.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserProfileActivity.m996setupViews$lambda2$lambda0(this.f$0, view);
            }
        });
        activityUserProfileBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws InterruptedException {
                UserProfileActivity.m997setupViews$lambda2$lambda1(this.f$0, view);
            }
        });
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws ParseException {
                UserProfileActivity.m998setupViews$lambda4(this.f$0, (UserProfileViewModel.ProfileUI) obj);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass3(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-0, reason: not valid java name */
    public static final void m996setupViews$lambda2$lambda0(UserProfileActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserConfig.INSTANCE.getInstance().setLoginStatus(false);
        UserConfig.INSTANCE.getInstance().setContactId("");
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m997setupViews$lambda2$lambda1(UserProfileActivity this$0, View view) throws InterruptedException {
        DeviceSetting deviceSetting;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.userEntity != null) {
            UserProfileViewModel viewModel = this$0.getViewModel();
            UserEntity userEntity = this$0.userEntity;
            UserEntity userEntity2 = null;
            if (userEntity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity = null;
            }
            viewModel.saveUserEntity(userEntity);
            UserProfileViewModel viewModel2 = this$0.getViewModel();
            UserEntity userEntity3 = this$0.userEntity;
            if (userEntity3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity3 = null;
            }
            viewModel2.updateUserProfile(userEntity3);
            UserConfig.INSTANCE.getInstance().setMetric(this$0.getMetric());
            UserConfig.INSTANCE.getInstance().save();
            EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UserProfileActivity$setupViews$1$8$2(this$0, null), 3, null);
            StringBuilder sb = new StringBuilder();
            UserEntity userEntity4 = this$0.userEntity;
            if (userEntity4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity4 = null;
            }
            sb.append(userEntity4.getBirthday());
            sb.append("-01");
            int ageByBirthday = DateUtil.getAgeByBirthday(DateUtil.String2Date("yyyy-MM-dd", sb.toString()));
            LargeDataHandler largeDataHandler = LargeDataHandler.getInstance();
            UserEntity userEntity5 = this$0.userEntity;
            if (userEntity5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity5 = null;
            }
            largeDataHandler.setDeviceNickName(userEntity5.getNickName());
            UserProfileViewModel viewModel3 = this$0.getViewModel();
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting = null;
            } else {
                deviceSetting = deviceSetting2;
            }
            UserEntity userEntity6 = this$0.userEntity;
            if (userEntity6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity6 = null;
            }
            int gender = userEntity6.getGender() - 1;
            UserEntity userEntity7 = this$0.userEntity;
            if (userEntity7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity7 = null;
            }
            int height = (int) userEntity7.getHeight();
            UserEntity userEntity8 = this$0.userEntity;
            if (userEntity8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            } else {
                userEntity2 = userEntity8;
            }
            viewModel3.execUserInfoToDevice(deviceSetting, gender, ageByBirthday, height, (int) userEntity2.getWeight());
        }
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m998setupViews$lambda4(UserProfileActivity this$0, UserProfileViewModel.ProfileUI profileUI) throws ParseException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityUserProfileBinding activityUserProfileBinding = this$0.binding;
        UserEntity userEntity = null;
        if (activityUserProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserProfileBinding = null;
        }
        this$0.userEntity = profileUI.getEntity();
        activityUserProfileBinding.userCenterName.setRightText(profileUI.getNick());
        UserEntity userEntity2 = this$0.userEntity;
        if (userEntity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            userEntity2 = null;
        }
        if (userEntity2.getGender() == 0) {
            UserEntity userEntity3 = this$0.userEntity;
            if (userEntity3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity3 = null;
            }
            userEntity3.setGender(1);
        }
        if (profileUI.getGender() == 1) {
            activityUserProfileBinding.userCenterGender.setRightText(this$0.getString(R.string.qc_text_152));
        } else {
            activityUserProfileBinding.userCenterGender.setRightText(this$0.getString(R.string.qc_text_153));
        }
        String birthday = profileUI.getBirthday();
        if (birthday.length() == 0) {
            birthday = new DateUtil().getYyyyMMDate();
            Intrinsics.checkNotNullExpressionValue(birthday, "DateUtil().yyyyMMDate");
        }
        DateUtil d = DateUtil.parse(birthday, DateUtil.DateFormater.yyyyMM);
        QSettingItem qSettingItem = activityUserProfileBinding.userCenterBirthday;
        QcDateUtil getInstance = QcDateUtil.INSTANCE.getGetInstance();
        Intrinsics.checkNotNullExpressionValue(d, "d");
        qSettingItem.setRightText(getInstance.localDateNoDayFormat(d));
        if (this$0.getMetric()) {
            activityUserProfileBinding.userCenterWeight.setRightText(profileUI.getWeight() + "kg");
            activityUserProfileBinding.userCenterHeight.setRightText(profileUI.getHeight() + "cm");
            return;
        }
        int iCmToIn = MetricUtilsKt.cmToIn(profileUI.getHeight());
        activityUserProfileBinding.userCenterHeight.setRightText((iCmToIn / 12) + "ft" + (iCmToIn % 12) + "in");
        QSettingItem qSettingItem2 = activityUserProfileBinding.userCenterWeight;
        StringBuilder sb = new StringBuilder();
        sb.append(profileUI.getWeightLbs());
        sb.append("lbs");
        qSettingItem2.setRightText(sb.toString());
        UserEntity userEntity4 = this$0.userEntity;
        if (userEntity4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
        } else {
            userEntity = userEntity4;
        }
        userEntity.setWeightLbs(profileUI.getWeightLbs());
    }

    /* compiled from: UserProfileActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$3", f = "UserProfileActivity.kt", i = {}, l = {211, AudioEq.PARSE_EQ_DATA_LENGTH}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$setupViews$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfileActivity.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final UserProfileActivity userProfileActivity = UserProfileActivity.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity.setupViews.3.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null) {
                        userProfileActivity.deviceSetting = deviceSetting;
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ActivityUserProfileBinding activityUserProfileBinding = null;
        if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            ActivityUserProfileBinding activityUserProfileBinding2 = this.binding;
            if (activityUserProfileBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityUserProfileBinding = activityUserProfileBinding2;
            }
            ViewKt.visible(activityUserProfileBinding.btnExit);
        } else {
            ActivityUserProfileBinding activityUserProfileBinding3 = this.binding;
            if (activityUserProfileBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityUserProfileBinding = activityUserProfileBinding3;
            }
            ViewKt.gone(activityUserProfileBinding.btnExit);
        }
        if (this.userEntity == null) {
            getViewModel().queryUserByUidUI();
        }
    }

    /* compiled from: UserProfileActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$onStop$1", f = "UserProfileActivity.kt", i = {}, l = {236, 237}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$onStop$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfileActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final UserProfileActivity userProfileActivity = UserProfileActivity.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity.onStop.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null) {
                        if (userProfileActivity.getMetric()) {
                            deviceSetting.setMetricUnit(0);
                        } else {
                            deviceSetting.setMetricUnit(1);
                        }
                        userProfileActivity.getViewModel().execUnit(deviceSetting);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void refreshHeightAndWeight() {
        UserEntity userEntity = null;
        if (getMetric()) {
            ActivityUserProfileBinding activityUserProfileBinding = this.binding;
            if (activityUserProfileBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserProfileBinding = null;
            }
            QSettingItem qSettingItem = activityUserProfileBinding.userCenterWeight;
            StringBuilder sb = new StringBuilder();
            UserEntity userEntity2 = this.userEntity;
            if (userEntity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity2 = null;
            }
            sb.append((int) userEntity2.getWeight());
            sb.append("kg");
            qSettingItem.setRightText(sb.toString());
            ActivityUserProfileBinding activityUserProfileBinding2 = this.binding;
            if (activityUserProfileBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserProfileBinding2 = null;
            }
            QSettingItem qSettingItem2 = activityUserProfileBinding2.userCenterHeight;
            StringBuilder sb2 = new StringBuilder();
            UserEntity userEntity3 = this.userEntity;
            if (userEntity3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            } else {
                userEntity = userEntity3;
            }
            sb2.append((int) userEntity.getHeight());
            sb2.append("cm");
            qSettingItem2.setRightText(sb2.toString());
            return;
        }
        UserEntity userEntity4 = this.userEntity;
        if (userEntity4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            userEntity4 = null;
        }
        int iCmToIn = MetricUtilsKt.cmToIn((int) userEntity4.getHeight());
        String str = (iCmToIn / 12) + "ft" + (iCmToIn % 12) + "in";
        ActivityUserProfileBinding activityUserProfileBinding3 = this.binding;
        if (activityUserProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserProfileBinding3 = null;
        }
        activityUserProfileBinding3.userCenterHeight.setRightText(str);
        ActivityUserProfileBinding activityUserProfileBinding4 = this.binding;
        if (activityUserProfileBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserProfileBinding4 = null;
        }
        QSettingItem qSettingItem3 = activityUserProfileBinding4.userCenterWeight;
        StringBuilder sb3 = new StringBuilder();
        UserEntity userEntity5 = this.userEntity;
        if (userEntity5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
        } else {
            userEntity = userEntity5;
        }
        sb3.append(userEntity.getWeightLbs());
        sb3.append("lbs");
        qSettingItem3.setRightText(sb3.toString());
    }

    public final void showHeightDialog() {
        BottomHeightWeightDialog bottomHeightWeightDialog;
        BottomHeightWeightDialog bottomHeightWeightDialogCreate = new BottomHeightWeightDialog.Builder(getActivity()).create();
        Intrinsics.checkNotNullExpressionValue(bottomHeightWeightDialogCreate, "builder.create()");
        this.heightDialog = bottomHeightWeightDialogCreate;
        BottomHeightWeightDialog bottomHeightWeightDialog2 = null;
        if (bottomHeightWeightDialogCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("heightDialog");
            bottomHeightWeightDialog = null;
        } else {
            bottomHeightWeightDialog = bottomHeightWeightDialogCreate;
        }
        bottomHeightWeightDialog.initData(this, getViewModel().getMHeightList(), getViewModel().getIHeightList(), getMetric(), true);
        BottomHeightWeightDialog bottomHeightWeightDialog3 = this.heightDialog;
        if (bottomHeightWeightDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("heightDialog");
            bottomHeightWeightDialog3 = null;
        }
        bottomHeightWeightDialog3.setDialogTitle(getString(R.string.qc_text_145));
        BottomHeightWeightDialog bottomHeightWeightDialog4 = this.heightDialog;
        if (bottomHeightWeightDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("heightDialog");
            bottomHeightWeightDialog4 = null;
        }
        bottomHeightWeightDialog4.setListener(new BottomHeightWeightDialog.DialogSaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog.DialogSaveClickListener
            public final void itemClick(int i, boolean z, boolean z2) {
                UserProfileActivity.m1001showHeightDialog$lambda5(this.f$0, i, z, z2);
            }
        });
        BottomHeightWeightDialog bottomHeightWeightDialog5 = this.heightDialog;
        if (bottomHeightWeightDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("heightDialog");
            bottomHeightWeightDialog5 = null;
        }
        UserEntity userEntity = this.userEntity;
        if (userEntity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            userEntity = null;
        }
        bottomHeightWeightDialog5.setCurrItem((int) userEntity.getHeight(), getMetric());
        BottomHeightWeightDialog bottomHeightWeightDialog6 = this.heightDialog;
        if (bottomHeightWeightDialog6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("heightDialog");
        } else {
            bottomHeightWeightDialog2 = bottomHeightWeightDialog6;
        }
        bottomHeightWeightDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showHeightDialog$lambda-5, reason: not valid java name */
    public static final void m1001showHeightDialog$lambda5(UserProfileActivity this$0, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z2) {
            UserEntity userEntity = this$0.userEntity;
            if (userEntity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity = null;
            }
            userEntity.setHeight(i);
            this$0.refreshHeightAndWeight();
            return;
        }
        this$0.setMetric(z);
    }

    public final void showWeightDialog() {
        BottomHeightWeightDialog bottomHeightWeightDialog;
        BottomHeightWeightDialog bottomHeightWeightDialogCreate = new BottomHeightWeightDialog.Builder(getActivity()).create();
        Intrinsics.checkNotNullExpressionValue(bottomHeightWeightDialogCreate, "builder.create()");
        this.bottomDialog = bottomHeightWeightDialogCreate;
        BottomHeightWeightDialog bottomHeightWeightDialog2 = null;
        if (bottomHeightWeightDialogCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
            bottomHeightWeightDialog = null;
        } else {
            bottomHeightWeightDialog = bottomHeightWeightDialogCreate;
        }
        bottomHeightWeightDialog.initData(this, getViewModel().getMWeightList(), getViewModel().getIWeightList(), getMetric(), false);
        BottomHeightWeightDialog bottomHeightWeightDialog3 = this.bottomDialog;
        if (bottomHeightWeightDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
            bottomHeightWeightDialog3 = null;
        }
        bottomHeightWeightDialog3.setDialogTitle(getString(R.string.qc_text_146));
        BottomHeightWeightDialog bottomHeightWeightDialog4 = this.bottomDialog;
        if (bottomHeightWeightDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
            bottomHeightWeightDialog4 = null;
        }
        bottomHeightWeightDialog4.setListener(new BottomHeightWeightDialog.DialogSaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog.DialogSaveClickListener
            public final void itemClick(int i, boolean z, boolean z2) {
                UserProfileActivity.m1004showWeightDialog$lambda6(this.f$0, i, z, z2);
            }
        });
        if (getMetric()) {
            BottomHeightWeightDialog bottomHeightWeightDialog5 = this.bottomDialog;
            if (bottomHeightWeightDialog5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
                bottomHeightWeightDialog5 = null;
            }
            UserEntity userEntity = this.userEntity;
            if (userEntity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity = null;
            }
            bottomHeightWeightDialog5.setCurrItem((int) userEntity.getWeight(), getMetric());
        } else {
            BottomHeightWeightDialog bottomHeightWeightDialog6 = this.bottomDialog;
            if (bottomHeightWeightDialog6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
                bottomHeightWeightDialog6 = null;
            }
            UserEntity userEntity2 = this.userEntity;
            if (userEntity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                userEntity2 = null;
            }
            bottomHeightWeightDialog6.setCurrItem(userEntity2.getWeightLbs(), getMetric());
        }
        BottomHeightWeightDialog bottomHeightWeightDialog7 = this.bottomDialog;
        if (bottomHeightWeightDialog7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
        } else {
            bottomHeightWeightDialog2 = bottomHeightWeightDialog7;
        }
        bottomHeightWeightDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showWeightDialog$lambda-6, reason: not valid java name */
    public static final void m1004showWeightDialog$lambda6(UserProfileActivity this$0, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z2) {
            XLog.i(Integer.valueOf(i));
            UserEntity userEntity = null;
            if (z) {
                UserEntity userEntity2 = this$0.userEntity;
                if (userEntity2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                    userEntity2 = null;
                }
                userEntity2.setWeight(i);
                UserEntity userEntity3 = this$0.userEntity;
                if (userEntity3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                } else {
                    userEntity = userEntity3;
                }
                userEntity.setWeightLbs(MetricUtilsKt.kgToLbs(i));
                XLog.i(Float.valueOf(MetricUtilsKt.kgToLbs(i)));
            } else {
                UserEntity userEntity4 = this$0.userEntity;
                if (userEntity4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                    userEntity4 = null;
                }
                userEntity4.setWeightLbs(i);
                UserEntity userEntity5 = this$0.userEntity;
                if (userEntity5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                } else {
                    userEntity = userEntity5;
                }
                userEntity.setWeight(MetricUtilsKt.lbsToKg(i));
                XLog.i(Float.valueOf(MetricUtilsKt.lbsToKg(i)));
            }
            this$0.refreshHeightAndWeight();
            return;
        }
        this$0.setMetric(z);
    }

    public final void showBirthdayDialog() throws ParseException {
        UserEntity userEntity = this.userEntity;
        BottomBirthdayDialog bottomBirthdayDialog = null;
        if (userEntity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            userEntity = null;
        }
        String birthday = userEntity.getBirthday();
        if (birthday.length() == 0) {
            birthday = new DateUtil().getY_M_D();
            Intrinsics.checkNotNullExpressionValue(birthday, "DateUtil().y_M_D");
        }
        DateUtil dateUtil = DateUtil.parse(birthday, DateUtil.DateFormater.yyyyMM);
        BottomBirthdayDialog bottomBirthdayDialogCreate = new BottomBirthdayDialog.Builder(getActivity()).create();
        Intrinsics.checkNotNullExpressionValue(bottomBirthdayDialogCreate, "builder.create()");
        this.birthdayDialog = bottomBirthdayDialogCreate;
        if (bottomBirthdayDialogCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayDialog");
            bottomBirthdayDialogCreate = null;
        }
        bottomBirthdayDialogCreate.initData(this);
        BottomBirthdayDialog bottomBirthdayDialog2 = this.birthdayDialog;
        if (bottomBirthdayDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayDialog");
            bottomBirthdayDialog2 = null;
        }
        bottomBirthdayDialog2.setDialogTitle(getString(R.string.qc_text_144));
        BottomBirthdayDialog bottomBirthdayDialog3 = this.birthdayDialog;
        if (bottomBirthdayDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayDialog");
            bottomBirthdayDialog3 = null;
        }
        bottomBirthdayDialog3.setListener(new BottomBirthdayDialog.DialogSaveClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.BottomBirthdayDialog.DialogSaveClickListener
            public final void itemClick(int i, int i2) {
                UserProfileActivity.m999showBirthdayDialog$lambda7(this.f$0, i, i2);
            }
        });
        BottomBirthdayDialog bottomBirthdayDialog4 = this.birthdayDialog;
        if (bottomBirthdayDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayDialog");
            bottomBirthdayDialog4 = null;
        }
        bottomBirthdayDialog4.setCurrItem(dateUtil.getYear(), dateUtil.getMonth());
        BottomBirthdayDialog bottomBirthdayDialog5 = this.birthdayDialog;
        if (bottomBirthdayDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayDialog");
        } else {
            bottomBirthdayDialog = bottomBirthdayDialog5;
        }
        bottomBirthdayDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showBirthdayDialog$lambda-7, reason: not valid java name */
    public static final void m999showBirthdayDialog$lambda7(UserProfileActivity this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DateUtil dateUtil = new DateUtil(i, i2, 1);
        UserEntity userEntity = null;
        if (dateUtil.getUnixTimestamp() > new DateUtil().getUnixTimestamp()) {
            String string = this$0.getString(R.string.qc_text_229);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_229)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        ActivityUserProfileBinding activityUserProfileBinding = this$0.binding;
        if (activityUserProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserProfileBinding = null;
        }
        activityUserProfileBinding.userCenterBirthday.setRightText(QcDateUtil.INSTANCE.getGetInstance().localDateNoDayFormat(dateUtil));
        UserEntity userEntity2 = this$0.userEntity;
        if (userEntity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
        } else {
            userEntity = userEntity2;
        }
        String yyyyMMDate = dateUtil.getYyyyMMDate();
        Intrinsics.checkNotNullExpressionValue(yyyyMMDate, "d.yyyyMMDate");
        userEntity.setBirthday(yyyyMMDate);
    }

    public final void showGenderDialog() {
        UserEntity userEntity = this.userEntity;
        BottomListDialog bottomListDialog = null;
        if (userEntity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            userEntity = null;
        }
        int gender = userEntity.getGender();
        getViewModel().getGenderList().clear();
        getViewModel().getGenderList().add(0, new ListDataBean(GlobalKt.getString(R.string.qc_text_152), gender == 1));
        getViewModel().getGenderList().add(1, new ListDataBean(GlobalKt.getString(R.string.qc_text_153), gender == 2));
        BottomListDialog bottomListDialogCreate = new BottomListDialog.Builder(getActivity()).create();
        Intrinsics.checkNotNullExpressionValue(bottomListDialogCreate, "builder.create()");
        this.genderDialog = bottomListDialogCreate;
        if (bottomListDialogCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genderDialog");
            bottomListDialogCreate = null;
        }
        bottomListDialogCreate.show();
        BottomListDialog bottomListDialog2 = this.genderDialog;
        if (bottomListDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genderDialog");
            bottomListDialog2 = null;
        }
        bottomListDialog2.initView();
        BottomListDialog bottomListDialog3 = this.genderDialog;
        if (bottomListDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genderDialog");
            bottomListDialog3 = null;
        }
        bottomListDialog3.setSubTitle(getString(R.string.qc_text_154));
        BottomListDialog bottomListDialog4 = this.genderDialog;
        if (bottomListDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genderDialog");
            bottomListDialog4 = null;
        }
        bottomListDialog4.setData(getViewModel().getGenderList());
        BottomListDialog bottomListDialog5 = this.genderDialog;
        if (bottomListDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genderDialog");
        } else {
            bottomListDialog = bottomListDialog5;
        }
        bottomListDialog.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda8
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                UserProfileActivity.m1000showGenderDialog$lambda8(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showGenderDialog$lambda-8, reason: not valid java name */
    public static final void m1000showGenderDialog$lambda8(UserProfileActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserEntity userEntity = null;
        ActivityUserProfileBinding activityUserProfileBinding = null;
        if (i == 0) {
            ActivityUserProfileBinding activityUserProfileBinding2 = this$0.binding;
            if (activityUserProfileBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserProfileBinding2 = null;
            }
            activityUserProfileBinding2.userCenterGender.setRightText(this$0.getString(R.string.qc_text_152));
            UserEntity userEntity2 = this$0.userEntity;
            if (userEntity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            } else {
                userEntity = userEntity2;
            }
            userEntity.setGender(1);
            return;
        }
        if (i != 1) {
            return;
        }
        UserEntity userEntity3 = this$0.userEntity;
        if (userEntity3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            userEntity3 = null;
        }
        userEntity3.setGender(2);
        ActivityUserProfileBinding activityUserProfileBinding3 = this$0.binding;
        if (activityUserProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityUserProfileBinding = activityUserProfileBinding3;
        }
        activityUserProfileBinding.userCenterGender.setRightText(this$0.getString(R.string.qc_text_153));
    }

    public final void setNickNameDialog(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        EditTextDialog editTextDialog = new EditTextDialog(this, text);
        this.nickNameDialog = editTextDialog;
        editTextDialog.setOnTextConfirmListener(new EditTextDialog.OnTextConfirmListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda9
            @Override // com.qcwireless.qcwatch.base.dialog.EditTextDialog.OnTextConfirmListener
            public final void OnConfirm(String str) {
                UserProfileActivity.m995setNickNameDialog$lambda9(this.f$0, str);
            }
        });
        EditTextDialog editTextDialog2 = this.nickNameDialog;
        if (editTextDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nickNameDialog");
            editTextDialog2 = null;
        }
        editTextDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setNickNameDialog$lambda-9, reason: not valid java name */
    public static final void m995setNickNameDialog$lambda9(UserProfileActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.length() > 0) {
            ActivityUserProfileBinding activityUserProfileBinding = this$0.binding;
            UserEntity userEntity = null;
            if (activityUserProfileBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserProfileBinding = null;
            }
            activityUserProfileBinding.userCenterName.setRightText(it);
            UserEntity userEntity2 = this$0.userEntity;
            if (userEntity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userEntity");
            } else {
                userEntity = userEntity2;
            }
            userEntity.setNickName(it);
            XLog.i(it);
            EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        XLog.i("onPause");
        try {
            BottomHeightWeightDialog bottomHeightWeightDialog = this.heightDialog;
            EditTextDialog editTextDialog = null;
            if (bottomHeightWeightDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("heightDialog");
                bottomHeightWeightDialog = null;
            }
            bottomHeightWeightDialog.dismiss();
            BottomHeightWeightDialog bottomHeightWeightDialog2 = this.bottomDialog;
            if (bottomHeightWeightDialog2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomDialog");
                bottomHeightWeightDialog2 = null;
            }
            bottomHeightWeightDialog2.dismiss();
            BottomBirthdayDialog bottomBirthdayDialog = this.birthdayDialog;
            if (bottomBirthdayDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("birthdayDialog");
                bottomBirthdayDialog = null;
            }
            bottomBirthdayDialog.dismiss();
            BottomListDialog bottomListDialog = this.genderDialog;
            if (bottomListDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genderDialog");
                bottomListDialog = null;
            }
            bottomListDialog.dismiss();
            EditTextDialog editTextDialog2 = this.nickNameDialog;
            if (editTextDialog2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nickNameDialog");
            } else {
                editTextDialog = editTextDialog2;
            }
            editTextDialog.dismiss();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showLogOffDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_log_off);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserProfileActivity.m1002showLogOffDialog$lambda10(objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserProfileActivity.m1003showLogOffDialog$lambda11(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showLogOffDialog$lambda-10, reason: not valid java name */
    public static final void m1002showLogOffDialog$lambda10(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showLogOffDialog$lambda-11, reason: not valid java name */
    public static final void m1003showLogOffDialog$lambda11(UserProfileActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.getViewModel().logOff();
        ((BottomDialog) bottomDialog.element).dismiss();
        this$0.finish();
    }
}
