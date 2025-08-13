package com.qcwireless.qcwatch.ui.device.remind.longsit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.req.SetSitLongReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.SimpleStatusRsp;
import com.qcwireless.qcwatch.base.dialog.bean.WeekRepeat;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel;
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

/* compiled from: LongSitViewModel.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J*\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002J\u0016\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0007J\u0016\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/longsit/LongSitViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "uiStatue", "Landroidx/lifecycle/LiveData;", "getUiStatue", "()Landroidx/lifecycle/LiveData;", "weekList", "", "Lcom/qcwireless/qcwatch/base/dialog/bean/WeekRepeat;", "getWeekList", "()Ljava/util/List;", "getLongSitSetting", "", "mac", "", "getSedentaryInput", "Lcom/oudmon/ble/base/communication/req/SetSitLongReq;", "start", "", "end", "gap", "week", "saveLongSitSetting", "setting", "saveLongSitSettingNotExecCmd", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LongSitViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;
    private final List<WeekRepeat> weekList;

    public LongSitViewModel(DeviceSettingRepository deviceSettingRepository) {
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

    /* compiled from: LongSitViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$getLongSitSetting$1", f = "LongSitViewModel.kt", i = {}, l = {32, 32}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$getLongSitSetting$1, reason: invalid class name */
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
            return LongSitViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = LongSitViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final LongSitViewModel longSitViewModel = LongSitViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel.getLongSitSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    MutableLiveData mutableLiveData = longSitViewModel._uiState;
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

    public final void getLongSitSetting(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new AnonymousClass1(mac, null));
    }

    /* compiled from: LongSitViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$saveLongSitSetting$1", f = "LongSitViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$saveLongSitSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C05541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        final /* synthetic */ DeviceSetting $setting;
        int label;
        final /* synthetic */ LongSitViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05541(DeviceSetting deviceSetting, LongSitViewModel longSitViewModel, String str, Continuation<? super C05541> continuation) {
            super(2, continuation);
            this.$setting = deviceSetting;
            this.this$0 = longSitViewModel;
            this.$mac = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-0, reason: not valid java name */
        public static final void m589invokeSuspend$lambda0(SimpleStatusRsp simpleStatusRsp) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05541(this.$setting, this.this$0, this.$mac, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!this.$setting.getLongSitSwitch()) {
                this.$setting.setLongSitWeek(0);
            }
            this.this$0.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(this.$mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(this.$setting)));
            CommandHandle.getInstance().executeReqCmd(this.this$0.getSedentaryInput(this.$setting.getLongSitStart(), this.$setting.getLongSitEnd(), this.$setting.getLongSitDuring(), this.$setting.getLongSitWeek()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$saveLongSitSetting$1$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    LongSitViewModel.C05541.m589invokeSuspend$lambda0((SimpleStatusRsp) baseRspCmd);
                }
            });
            this.this$0._uiState.postValue(this.$setting);
            return Unit.INSTANCE;
        }
    }

    public final void saveLongSitSetting(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        launchOnUI(new C05541(setting, this, mac, null));
    }

    /* compiled from: LongSitViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$saveLongSitSettingNotExecCmd$1", f = "LongSitViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel$saveLongSitSettingNotExecCmd$1, reason: invalid class name and case insensitive filesystem */
    static final class C05551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DeviceSetting $setting;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05551(DeviceSetting deviceSetting, Continuation<? super C05551> continuation) {
            super(2, continuation);
            this.$setting = deviceSetting;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LongSitViewModel.this.new C05551(this.$setting, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LongSitViewModel.this._uiState.postValue(this.$setting);
            return Unit.INSTANCE;
        }
    }

    public final void saveLongSitSettingNotExecCmd(String mac, DeviceSetting setting) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(setting, "setting");
        launchOnUI(new C05551(setting, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SetSitLongReq getSedentaryInput(int start, int end, int gap, int week) {
        return new SetSitLongReq(new StartEndTimeEntity(start / 60, start % 60, end / 60, end % 60), (byte) week, gap);
    }
}
