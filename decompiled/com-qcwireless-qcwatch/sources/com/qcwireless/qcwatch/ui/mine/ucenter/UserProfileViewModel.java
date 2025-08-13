package com.qcwireless.qcwatch.ui.mine.ucenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.req.TimeFormatReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.TimeFormatRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: UserProfileViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001BB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J.\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u001fJ&\u0010.\u001a\u00020+2\u0006\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u001fJ\u0006\u00104\u001a\u00020+J\u0006\u00105\u001a\u00020+J\u000e\u00106\u001a\u00020+2\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u00020+J\u0006\u0010:\u001a\u00020+J\u000e\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u00020+2\u0006\u0010<\u001a\u00020=R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0010R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0'8F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel$ProfileUI;", "genderList", "", "Lcom/qcwireless/qcwatch/base/dialog/bean/ListDataBean;", "getGenderList", "()Ljava/util/List;", "setGenderList", "(Ljava/util/List;)V", "iHeightList", "", "getIHeightList", "setIHeightList", "iWeightList", "getIWeightList", "setIWeightList", "mHeightList", "getMHeightList", "setMHeightList", "mWeightList", "getMWeightList", "setMWeightList", "maxHeightImperial", "", "minHeightImperial", "noLoginId", "getNoLoginId", "()I", "setNoLoginId", "(I)V", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "execUnit", "", "setting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "execUserInfoToDevice", "deviceSetting", "boy", "age", "height", "weight", "initDialogData", "logOff", "queryUserByUid", "uid", "", "queryUserByUidUI", "queryUserByUidUILoginSuccess", "saveUserEntity", "entity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "upPhotoImage", "file", "Ljava/io/File;", "updateUserProfile", "ProfileUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserProfileViewModel extends BaseViewModel {
    private final MutableLiveData<ProfileUI> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;
    private List<ListDataBean> genderList;
    private List<String> iHeightList;
    private List<String> iWeightList;
    private List<String> mHeightList;
    private List<String> mWeightList;
    private final int maxHeightImperial;
    private final int minHeightImperial;
    private int noLoginId;
    private final UserProfileRepository userProfileRepository;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execUnit$lambda-1, reason: not valid java name */
    public static final void m1006execUnit$lambda1(TimeFormatRsp timeFormatRsp) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execUserInfoToDevice$lambda-0, reason: not valid java name */
    public static final void m1007execUserInfoToDevice$lambda0(TimeFormatRsp timeFormatRsp) {
    }

    public UserProfileViewModel(UserProfileRepository userProfileRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.userProfileRepository = userProfileRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this.minHeightImperial = 13;
        this.maxHeightImperial = 100;
        this.iHeightList = new ArrayList();
        this.mHeightList = new ArrayList();
        this.mWeightList = new ArrayList();
        this.iWeightList = new ArrayList();
        this.genderList = new ArrayList();
        this.noLoginId = 968888;
        this._uiState = new MutableLiveData<>();
    }

    public final List<String> getIHeightList() {
        return this.iHeightList;
    }

    public final void setIHeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.iHeightList = list;
    }

    public final List<String> getMHeightList() {
        return this.mHeightList;
    }

    public final void setMHeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mHeightList = list;
    }

    public final List<String> getMWeightList() {
        return this.mWeightList;
    }

    public final void setMWeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mWeightList = list;
    }

    public final List<String> getIWeightList() {
        return this.iWeightList;
    }

    public final void setIWeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.iWeightList = list;
    }

    public final List<ListDataBean> getGenderList() {
        return this.genderList;
    }

    public final void setGenderList(List<ListDataBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.genderList = list;
    }

    public final int getNoLoginId() {
        return this.noLoginId;
    }

    public final void setNoLoginId(int i) {
        this.noLoginId = i;
    }

    public final LiveData<ProfileUI> getUiState() {
        return this._uiState;
    }

    public final void initDialogData() {
        int i = 33;
        while (true) {
            int i2 = i + 1;
            this.mHeightList.add(String.valueOf(i));
            if (i2 > 255) {
                break;
            } else {
                i = i2;
            }
        }
        int i3 = this.minHeightImperial;
        int i4 = this.maxHeightImperial;
        if (i3 <= i4) {
            while (true) {
                List<String> list = this.iHeightList;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%d'%d''", Arrays.copyOf(new Object[]{Integer.valueOf(i3 / 12), Integer.valueOf(i3 % 12)}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                list.add(str);
                if (i3 == i4) {
                    break;
                } else {
                    i3++;
                }
            }
        }
        int i5 = 30;
        while (true) {
            int i6 = i5 + 1;
            this.mWeightList.add(String.valueOf(i5));
            if (i6 > 255) {
                break;
            } else {
                i5 = i6;
            }
        }
        int i7 = 60;
        while (true) {
            int i8 = i7 + 1;
            this.iWeightList.add(String.valueOf(i7));
            if (i8 > 562) {
                return;
            } else {
                i7 = i8;
            }
        }
    }

    public final void queryUserByUid(final long uid) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<UserProfileViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.queryUserByUid.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserProfileViewModel userProfileViewModel) {
                invoke2(userProfileViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserProfileViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserEntity userEntityQueryUserByUid = ktxRunOnBgSingle.userProfileRepository.queryUserByUid(uid);
                XLog.i(userEntityQueryUserByUid);
                if (userEntityQueryUserByUid != null) {
                    ktxRunOnBgSingle._uiState.postValue(new ProfileUI(userEntityQueryUserByUid.getNickName(), userEntityQueryUserByUid.getGender(), userEntityQueryUserByUid.getBirthday(), (int) userEntityQueryUserByUid.getHeight(), (int) userEntityQueryUserByUid.getWeight(), userEntityQueryUserByUid.getWeightLbs(), userEntityQueryUserByUid));
                    return;
                }
                long j = uid;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                UserEntity userEntity = new UserEntity(j, "", "", 1, 60.0f, 120, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, y_m_d, 0);
                ktxRunOnBgSingle._uiState.postValue(new ProfileUI(userEntity.getNickName(), userEntity.getGender(), userEntity.getBirthday(), (int) userEntity.getHeight(), (int) userEntity.getWeight(), userEntity.getWeightLbs(), userEntity));
            }
        });
    }

    public final void queryUserByUidUI() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<UserProfileViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.queryUserByUidUI.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserProfileViewModel userProfileViewModel) {
                invoke2(userProfileViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserProfileViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserEntity userEntityQueryUserByUid = ktxRunOnBgSingle.userProfileRepository.queryUserByUid(UserConfig.INSTANCE.getInstance().getUid());
                if (userEntityQueryUserByUid != null) {
                    ktxRunOnBgSingle._uiState.postValue(new ProfileUI(userEntityQueryUserByUid.getNickName(), userEntityQueryUserByUid.getGender(), userEntityQueryUserByUid.getBirthday(), (int) userEntityQueryUserByUid.getHeight(), (int) userEntityQueryUserByUid.getWeight(), userEntityQueryUserByUid.getWeightLbs(), userEntityQueryUserByUid));
                    return;
                }
                long uid = UserConfig.INSTANCE.getInstance().getUid();
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                UserEntity userEntity = new UserEntity(uid, "", "", 1, 60.0f, 120, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, y_m_d, 0);
                ktxRunOnBgSingle._uiState.postValue(new ProfileUI(userEntity.getNickName(), userEntity.getGender(), userEntity.getBirthday(), (int) userEntity.getHeight(), (int) userEntity.getWeight(), userEntity.getWeightLbs(), userEntity));
            }
        });
    }

    public final void queryUserByUidUILoginSuccess() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<UserProfileViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.queryUserByUidUILoginSuccess.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserProfileViewModel userProfileViewModel) throws InterruptedException {
                invoke2(userProfileViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserProfileViewModel ktxRunOnBgSingle) throws InterruptedException {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserEntity userEntityQueryUserByUid = ktxRunOnBgSingle.userProfileRepository.queryUserByUid(UserConfig.INSTANCE.getInstance().getUid());
                if (userEntityQueryUserByUid != null) {
                    int ageByBirthday = DateUtil.getAgeByBirthday(DateUtil.String2Date("yyyy-MM-dd", userEntityQueryUserByUid.getBirthday() + "-01"));
                    if (userEntityQueryUserByUid.getNickName().length() > 0) {
                        LargeDataHandler.getInstance().setDeviceNickName(userEntityQueryUserByUid.getNickName());
                    }
                    ktxRunOnBgSingle.execUserInfoToDevice(userEntityQueryUserByUid.getGender() - 1, ageByBirthday, (int) userEntityQueryUserByUid.getHeight(), (int) userEntityQueryUserByUid.getWeight());
                    return;
                }
                LargeDataHandler.getInstance().setDeviceNickName("");
                ktxRunOnBgSingle.execUserInfoToDevice(0, 26, 175, 60);
            }
        });
    }

    /* compiled from: UserProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$execUserInfoToDevice$1", f = "UserProfileViewModel.kt", i = {}, l = {181, 182}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$execUserInfoToDevice$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $age;
        final /* synthetic */ int $boy;
        final /* synthetic */ int $height;
        final /* synthetic */ int $weight;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, int i2, int i3, int i4, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$boy = i;
            this.$age = i2;
            this.$height = i3;
            this.$weight = i4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$boy, this.$age, this.$height, this.$weight, continuation);
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
            this.label = 2;
            if (((Flow) obj).collect(new C02141(this.$boy, this.$age, this.$height, this.$weight), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: UserProfileViewModel.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "emit", "(Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$execUserInfoToDevice$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02141<T> implements FlowCollector {
            final /* synthetic */ int $age;
            final /* synthetic */ int $boy;
            final /* synthetic */ int $height;
            final /* synthetic */ int $weight;

            C02141(int i, int i2, int i3, int i4) {
                this.$boy = i;
                this.$age = i2;
                this.$height = i3;
                this.$weight = i4;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: emit$lambda-0, reason: not valid java name */
            public static final void m1008emit$lambda0(TimeFormatRsp timeFormatRsp) {
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((DeviceSetting) obj, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                if (deviceSetting != null) {
                    CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getWriteInstance(deviceSetting.getTimeFormat() == 0, deviceSetting.getMetricUnit(), this.$boy, this.$age, this.$height, this.$weight, 120, 90, BDLocation.TypeCoarseLocation), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$execUserInfoToDevice$1$1$$ExternalSyntheticLambda0
                        @Override // com.oudmon.ble.base.communication.ICommandResponse
                        public final void onDataResponse(BaseRspCmd baseRspCmd) {
                            UserProfileViewModel.AnonymousClass1.C02141.m1008emit$lambda0((TimeFormatRsp) baseRspCmd);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void execUserInfoToDevice(int boy, int age, int height, int weight) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(boy, age, height, weight, null), 3, null);
    }

    public final void saveUserEntity(final UserEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<UserProfileViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.saveUserEntity.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserProfileViewModel userProfileViewModel) {
                invoke2(userProfileViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserProfileViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.userProfileRepository.insertUser(entity);
            }
        });
    }

    /* compiled from: UserProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$updateUserProfile$1", f = "UserProfileViewModel.kt", i = {}, l = {204, 204}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$updateUserProfile$1, reason: invalid class name and case insensitive filesystem */
    static final class C06651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ UserEntity $entity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06651(UserEntity userEntity, Continuation<? super C06651> continuation) {
            super(2, continuation);
            this.$entity = userEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfileViewModel.this.new C06651(this.$entity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = UserProfileViewModel.this.userProfileRepository.updateUserProfileToServer(this.$entity, this);
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
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.updateUserProfile.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    XLog.i(netState);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateUserProfile(UserEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06651(entity, null), 3, null);
    }

    /* compiled from: UserProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$upPhotoImage$1", f = "UserProfileViewModel.kt", i = {}, l = {AudioEq.PARSE_EQ_DATA_LENGTH, AudioEq.PARSE_EQ_DATA_LENGTH}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$upPhotoImage$1, reason: invalid class name and case insensitive filesystem */
    static final class C06641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ File $file;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06641(File file, Continuation<? super C06641> continuation) {
            super(2, continuation);
            this.$file = file;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfileViewModel.this.new C06641(this.$file, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = UserProfileViewModel.this.userProfileRepository.upPhotoImage(this.$file, this);
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
            final UserProfileViewModel userProfileViewModel = UserProfileViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.upPhotoImage.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    UserEntity userEntityQueryUserByUid = userProfileViewModel.userProfileRepository.queryUserByUid(UserConfig.INSTANCE.getInstance().getUid());
                    if (userEntityQueryUserByUid != null) {
                        userEntityQueryUserByUid.setAvatarUrl(String.valueOf(netState.isSuccess()));
                        userProfileViewModel.userProfileRepository.insertUser(userEntityQueryUserByUid);
                        userProfileViewModel._uiState.postValue(new ProfileUI(userEntityQueryUserByUid.getNickName(), userEntityQueryUserByUid.getGender(), userEntityQueryUserByUid.getBirthday(), (int) userEntityQueryUserByUid.getHeight(), (int) userEntityQueryUserByUid.getWeight(), userEntityQueryUserByUid.getWeightLbs(), userEntityQueryUserByUid));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void upPhotoImage(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06641(file, null), 3, null);
    }

    public final void execUserInfoToDevice(DeviceSetting deviceSetting, int boy, int age, int height, int weight) {
        Intrinsics.checkNotNullParameter(deviceSetting, "deviceSetting");
        CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getWriteInstance(deviceSetting.getTimeFormat() == 0, deviceSetting.getMetricUnit(), boy, age, height, weight, 120, 90, BDLocation.TypeCoarseLocation), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                UserProfileViewModel.m1007execUserInfoToDevice$lambda0((TimeFormatRsp) baseRspCmd);
            }
        });
    }

    public final void execUnit(DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(setting, "setting");
        CommandHandle.getInstance().executeReqCmd(TimeFormatReq.getWriteInstance(setting.getTimeFormat() == 0, (byte) setting.getMetricUnit()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                UserProfileViewModel.m1006execUnit$lambda1((TimeFormatRsp) baseRspCmd);
            }
        });
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(setting)));
    }

    /* compiled from: UserProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$logOff$1", f = "UserProfileViewModel.kt", i = {}, l = {257, 257}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel$logOff$1, reason: invalid class name and case insensitive filesystem */
    static final class C06591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06591(Continuation<? super C06591> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfileViewModel.this.new C06591(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = UserProfileViewModel.this.userProfileRepository.logOff(UserConfig.INSTANCE.getInstance().getUid(), this);
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
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel.logOff.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    UserConfig.INSTANCE.getInstance().setLoginStatus(false);
                    UserConfig.INSTANCE.getInstance().setUid(0L);
                    UserConfig.INSTANCE.getInstance().setUserEmail("");
                    UserConfig.INSTANCE.getInstance().setContactId("");
                    UserConfig.INSTANCE.getInstance().save();
                    EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void logOff() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06591(null), 3, null);
    }

    /* compiled from: UserProfileViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0005HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel$ProfileUI;", "", "nick", "", "gender", "", "birthday", "height", "weight", "weightLbs", "entity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "(Ljava/lang/String;ILjava/lang/String;IIILcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;)V", "getBirthday", "()Ljava/lang/String;", "getEntity", "()Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "getGender", "()I", "getHeight", "getNick", "getWeight", "getWeightLbs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ProfileUI {
        private final String birthday;
        private final UserEntity entity;
        private final int gender;
        private final int height;
        private final String nick;
        private final int weight;
        private final int weightLbs;

        public static /* synthetic */ ProfileUI copy$default(ProfileUI profileUI, String str, int i, String str2, int i2, int i3, int i4, UserEntity userEntity, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                str = profileUI.nick;
            }
            if ((i5 & 2) != 0) {
                i = profileUI.gender;
            }
            int i6 = i;
            if ((i5 & 4) != 0) {
                str2 = profileUI.birthday;
            }
            String str3 = str2;
            if ((i5 & 8) != 0) {
                i2 = profileUI.height;
            }
            int i7 = i2;
            if ((i5 & 16) != 0) {
                i3 = profileUI.weight;
            }
            int i8 = i3;
            if ((i5 & 32) != 0) {
                i4 = profileUI.weightLbs;
            }
            int i9 = i4;
            if ((i5 & 64) != 0) {
                userEntity = profileUI.entity;
            }
            return profileUI.copy(str, i6, str3, i7, i8, i9, userEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final String getNick() {
            return this.nick;
        }

        /* renamed from: component2, reason: from getter */
        public final int getGender() {
            return this.gender;
        }

        /* renamed from: component3, reason: from getter */
        public final String getBirthday() {
            return this.birthday;
        }

        /* renamed from: component4, reason: from getter */
        public final int getHeight() {
            return this.height;
        }

        /* renamed from: component5, reason: from getter */
        public final int getWeight() {
            return this.weight;
        }

        /* renamed from: component6, reason: from getter */
        public final int getWeightLbs() {
            return this.weightLbs;
        }

        /* renamed from: component7, reason: from getter */
        public final UserEntity getEntity() {
            return this.entity;
        }

        public final ProfileUI copy(String nick, int gender, String birthday, int height, int weight, int weightLbs, UserEntity entity) {
            Intrinsics.checkNotNullParameter(nick, "nick");
            Intrinsics.checkNotNullParameter(birthday, "birthday");
            Intrinsics.checkNotNullParameter(entity, "entity");
            return new ProfileUI(nick, gender, birthday, height, weight, weightLbs, entity);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProfileUI)) {
                return false;
            }
            ProfileUI profileUI = (ProfileUI) other;
            return Intrinsics.areEqual(this.nick, profileUI.nick) && this.gender == profileUI.gender && Intrinsics.areEqual(this.birthday, profileUI.birthday) && this.height == profileUI.height && this.weight == profileUI.weight && this.weightLbs == profileUI.weightLbs && Intrinsics.areEqual(this.entity, profileUI.entity);
        }

        public int hashCode() {
            return (((((((((((this.nick.hashCode() * 31) + this.gender) * 31) + this.birthday.hashCode()) * 31) + this.height) * 31) + this.weight) * 31) + this.weightLbs) * 31) + this.entity.hashCode();
        }

        public String toString() {
            return "ProfileUI(nick=" + this.nick + ", gender=" + this.gender + ", birthday=" + this.birthday + ", height=" + this.height + ", weight=" + this.weight + ", weightLbs=" + this.weightLbs + ", entity=" + this.entity + ')';
        }

        public ProfileUI(String nick, int i, String birthday, int i2, int i3, int i4, UserEntity entity) {
            Intrinsics.checkNotNullParameter(nick, "nick");
            Intrinsics.checkNotNullParameter(birthday, "birthday");
            Intrinsics.checkNotNullParameter(entity, "entity");
            this.nick = nick;
            this.gender = i;
            this.birthday = birthday;
            this.height = i2;
            this.weight = i3;
            this.weightLbs = i4;
            this.entity = entity;
        }

        public final String getNick() {
            return this.nick;
        }

        public final int getGender() {
            return this.gender;
        }

        public final String getBirthday() {
            return this.birthday;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getWeight() {
            return this.weight;
        }

        public final int getWeightLbs() {
            return this.weightLbs;
        }

        public final UserEntity getEntity() {
            return this.entity;
        }
    }
}
