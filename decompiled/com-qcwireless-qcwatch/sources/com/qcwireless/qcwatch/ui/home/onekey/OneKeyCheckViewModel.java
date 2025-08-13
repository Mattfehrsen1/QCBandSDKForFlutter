package com.qcwireless.qcwatch.ui.home.onekey;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.StartHeartRateReq;
import com.oudmon.ble.base.communication.req.StopHeartRateReq;
import com.oudmon.ble.base.communication.rsp.StartHeartRateRsp;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastOneKeyBean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: OneKeyCheckViewModel.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0003\u001e\u001f B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0015J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0015J\u0006\u0010\u001d\u001a\u00020\u0015R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00060\u0011R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "oneKeyCheckRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/OneKeyCheckRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/OneKeyCheckRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_lastState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel$OneKeyLastBean;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel$OneKeyUI;", "lastBean", "Landroidx/lifecycle/LiveData;", "getLastBean", "()Landroidx/lifecycle/LiveData;", "oneKeyCallback", "Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel$OneKeyResp;", "uiState", "getUiState", "getLastOneKeyCheck", "", "mac", "", "querySupportItems", "saveLastOneKeyCheck", "oneKey", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "startOnKey", "stopOnKey", "OneKeyLastBean", "OneKeyResp", "OneKeyUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OneKeyCheckViewModel extends BaseViewModel {
    private final MutableLiveData<OneKeyLastBean> _lastState;
    private final MutableLiveData<OneKeyUI> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;
    private final OneKeyResp oneKeyCallback;
    private final OneKeyCheckRepository oneKeyCheckRepository;

    public OneKeyCheckViewModel(OneKeyCheckRepository oneKeyCheckRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(oneKeyCheckRepository, "oneKeyCheckRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.oneKeyCheckRepository = oneKeyCheckRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this._uiState = new MutableLiveData<>();
        this._lastState = new MutableLiveData<>();
        this.oneKeyCallback = new OneKeyResp();
    }

    public final LiveData<OneKeyUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<OneKeyLastBean> getLastBean() {
        return this._lastState;
    }

    public final void startOnKey() {
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 5), this.oneKeyCallback);
        BleOperateManager.getInstance().addNotifyListener(105, this.oneKeyCallback);
    }

    public final void stopOnKey() {
        CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHealthCheck(), this.oneKeyCallback);
        BleOperateManager.getInstance().removeNotifyListener(105);
    }

    /* compiled from: OneKeyCheckViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel$getLastOneKeyCheck$1", f = "OneKeyCheckViewModel.kt", i = {}, l = {69, 69}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel$getLastOneKeyCheck$1, reason: invalid class name */
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
            return OneKeyCheckViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = OneKeyCheckViewModel.this.oneKeyCheckRepository.getLastOneKeyCheck(this.$mac, this);
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
            final OneKeyCheckViewModel oneKeyCheckViewModel = OneKeyCheckViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel.getLastOneKeyCheck.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((LastOneKeyBean) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(LastOneKeyBean lastOneKeyBean, Continuation<? super Unit> continuation) {
                    oneKeyCheckViewModel._lastState.postValue(lastOneKeyBean != null ? new OneKeyLastBean(lastOneKeyBean) : null);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getLastOneKeyCheck(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new AnonymousClass1(mac, null));
    }

    /* compiled from: OneKeyCheckViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel$querySupportItems$1", f = "OneKeyCheckViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel$querySupportItems$1, reason: invalid class name and case insensitive filesystem */
    static final class C06261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06261(Continuation<? super C06261> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06261(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public final void querySupportItems() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06261(null), 3, null);
    }

    public final void saveLastOneKeyCheck(LastOneKeyBean oneKey) {
        Intrinsics.checkNotNullParameter(oneKey, "oneKey");
        this.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.LastOneKeyCheck, MoshiUtilsKt.toJson(oneKey)));
    }

    /* compiled from: OneKeyCheckViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel$OneKeyResp;", "Lcom/oudmon/ble/base/communication/ICommandResponse;", "Lcom/oudmon/ble/base/communication/rsp/StartHeartRateRsp;", "(Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel;)V", "onDataResponse", "", "resultEntity", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class OneKeyResp implements ICommandResponse<StartHeartRateRsp> {
        public OneKeyResp() {
        }

        @Override // com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            if (resultEntity != null) {
                XLog.i(resultEntity);
                OneKeyCheckViewModel.this._uiState.postValue(new OneKeyUI(resultEntity.getErrCode(), resultEntity));
            }
        }
    }

    /* compiled from: OneKeyCheckViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel$OneKeyUI;", "", "error", "", "resp", "Lcom/oudmon/ble/base/communication/rsp/StartHeartRateRsp;", "(ILcom/oudmon/ble/base/communication/rsp/StartHeartRateRsp;)V", "getError", "()I", "getResp", "()Lcom/oudmon/ble/base/communication/rsp/StartHeartRateRsp;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class OneKeyUI {
        private final int error;
        private final StartHeartRateRsp resp;

        public static /* synthetic */ OneKeyUI copy$default(OneKeyUI oneKeyUI, int i, StartHeartRateRsp startHeartRateRsp, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = oneKeyUI.error;
            }
            if ((i2 & 2) != 0) {
                startHeartRateRsp = oneKeyUI.resp;
            }
            return oneKeyUI.copy(i, startHeartRateRsp);
        }

        /* renamed from: component1, reason: from getter */
        public final int getError() {
            return this.error;
        }

        /* renamed from: component2, reason: from getter */
        public final StartHeartRateRsp getResp() {
            return this.resp;
        }

        public final OneKeyUI copy(int error, StartHeartRateRsp resp) {
            Intrinsics.checkNotNullParameter(resp, "resp");
            return new OneKeyUI(error, resp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OneKeyUI)) {
                return false;
            }
            OneKeyUI oneKeyUI = (OneKeyUI) other;
            return this.error == oneKeyUI.error && Intrinsics.areEqual(this.resp, oneKeyUI.resp);
        }

        public int hashCode() {
            return (this.error * 31) + this.resp.hashCode();
        }

        public String toString() {
            return "OneKeyUI(error=" + this.error + ", resp=" + this.resp + ')';
        }

        public OneKeyUI(int i, StartHeartRateRsp resp) {
            Intrinsics.checkNotNullParameter(resp, "resp");
            this.error = i;
            this.resp = resp;
        }

        public final int getError() {
            return this.error;
        }

        public final StartHeartRateRsp getResp() {
            return this.resp;
        }
    }

    /* compiled from: OneKeyCheckViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel$OneKeyLastBean;", "", "last", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;)V", "getLast", "()Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class OneKeyLastBean {
        private final LastOneKeyBean last;

        public static /* synthetic */ OneKeyLastBean copy$default(OneKeyLastBean oneKeyLastBean, LastOneKeyBean lastOneKeyBean, int i, Object obj) {
            if ((i & 1) != 0) {
                lastOneKeyBean = oneKeyLastBean.last;
            }
            return oneKeyLastBean.copy(lastOneKeyBean);
        }

        /* renamed from: component1, reason: from getter */
        public final LastOneKeyBean getLast() {
            return this.last;
        }

        public final OneKeyLastBean copy(LastOneKeyBean last) {
            Intrinsics.checkNotNullParameter(last, "last");
            return new OneKeyLastBean(last);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OneKeyLastBean) && Intrinsics.areEqual(this.last, ((OneKeyLastBean) other).last);
        }

        public int hashCode() {
            return this.last.hashCode();
        }

        public String toString() {
            return "OneKeyLastBean(last=" + this.last + ')';
        }

        public OneKeyLastBean(LastOneKeyBean last) {
            Intrinsics.checkNotNullParameter(last, "last");
            this.last = last;
        }

        public final LastOneKeyBean getLast() {
            return this.last;
        }
    }
}
