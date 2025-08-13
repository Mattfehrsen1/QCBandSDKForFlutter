package com.qcwireless.qcwatch.ui.device.remind.drink;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.entity.AlarmEntity;
import com.oudmon.ble.base.communication.req.SetDrinkAlarmReq;
import com.qcwireless.qcwatch.base.dialog.bean.WeekRepeat;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.bean.device.DrinkBean;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DrinkViewModel.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0007J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/drink/DrinkViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "uiStatue", "Landroidx/lifecycle/LiveData;", "getUiStatue", "()Landroidx/lifecycle/LiveData;", "weekList", "", "Lcom/qcwireless/qcwatch/base/dialog/bean/WeekRepeat;", "getWeekList", "()Ljava/util/List;", "getDrinkSetting", "", "mac", "", "saveDrinkSetting", "setting", "saveDrinkSettingNotExecCmd", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DrinkViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;
    private final List<WeekRepeat> weekList;

    public DrinkViewModel(DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this.weekList = new ArrayList();
        this._uiState = new MutableLiveData<>();
    }

    public final List<WeekRepeat> getWeekList() {
        return this.weekList;
    }

    public final LiveData<DeviceSetting> getUiStatue() {
        return this._uiState;
    }

    /* compiled from: DrinkViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel$getDrinkSetting$1", f = "DrinkViewModel.kt", i = {}, l = {31, 31}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel$getDrinkSetting$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DrinkViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = DrinkViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final DrinkViewModel drinkViewModel = DrinkViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel.getDrinkSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    MutableLiveData mutableLiveData = drinkViewModel._uiState;
                    if (deviceSetting != null) {
                        mutableLiveData.postValue(deviceSetting);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getDrinkSetting(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new AnonymousClass1(mac, null));
    }

    /* compiled from: DrinkViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel$saveDrinkSettingNotExecCmd$1", f = "DrinkViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel$saveDrinkSettingNotExecCmd$1, reason: invalid class name and case insensitive filesystem */
    static final class C05531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DeviceSetting $setting;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05531(DeviceSetting deviceSetting, Continuation<? super C05531> continuation) {
            super(2, continuation);
            this.$setting = deviceSetting;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DrinkViewModel.this.new C05531(this.$setting, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DrinkViewModel.this._uiState.postValue(this.$setting);
            return Unit.INSTANCE;
        }
    }

    public final void saveDrinkSettingNotExecCmd(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        launchOnUI(new C05531(setting, null));
    }

    /* compiled from: DrinkViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel$saveDrinkSetting$1", f = "DrinkViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel$saveDrinkSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C05521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        final /* synthetic */ DeviceSetting $setting;
        int label;
        final /* synthetic */ DrinkViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05521(DeviceSetting deviceSetting, DrinkViewModel drinkViewModel, String str, Continuation<? super C05521> continuation) {
            super(2, continuation);
            this.$setting = deviceSetting;
            this.this$0 = drinkViewModel;
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05521(this.$setting, this.this$0, this.$mac, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!this.$setting.getDrinkSwitch()) {
                this.$setting.setDrinkWeek(0);
            }
            this.this$0.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(this.$mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(this.$setting)));
            DrinkBean[] drinkArray = this.$setting.getDrinkArray();
            int length = drinkArray.length;
            for (int i = 0; i < length; i++) {
                DrinkBean drinkBean = drinkArray[i];
                boolean z = drinkBean.getSwitch();
                CommandHandle.getInstance().executeReqCmdNoCallback(new SetDrinkAlarmReq(new AlarmEntity(i, z ? 1 : 0, drinkBean.getTime() / 60, drinkBean.getTime() % 60, (byte) this.$setting.getDrinkWeek())));
            }
            this.this$0._uiState.postValue(this.$setting);
            return Unit.INSTANCE;
        }
    }

    public final void saveDrinkSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        launchOnUI(new C05521(setting, this, mac, null));
    }
}
