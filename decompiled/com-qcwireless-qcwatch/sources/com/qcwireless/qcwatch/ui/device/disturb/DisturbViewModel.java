package com.qcwireless.qcwatch.ui.device.disturb;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.req.DndReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DndRsp;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.device.DeviceFragment;
import com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel;
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
import org.greenrobot.eventbus.EventBus;

/* compiled from: DisturbViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbViewModel$DisturbUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "getDisturbSwitch", "", "mac", "", "saveDisturbSwitch", "disturb", "DisturbUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DisturbViewModel extends BaseViewModel {
    private final MutableLiveData<DisturbUI> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;

    public DisturbViewModel(DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<DisturbUI> getUiState() {
        return this._uiState;
    }

    /* compiled from: DisturbViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$getDisturbSwitch$1", f = "DisturbViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$getDisturbSwitch$1, reason: invalid class name */
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
            return DisturbViewModel.this.new AnonymousClass1(this.$mac, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: DisturbViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$getDisturbSwitch$1$1", f = "DisturbViewModel.kt", i = {}, l = {39, 39}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$getDisturbSwitch$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $mac;
            int label;
            final /* synthetic */ DisturbViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01131(DisturbViewModel disturbViewModel, String str, Continuation<? super C01131> continuation) {
                super(2, continuation);
                this.this$0 = disturbViewModel;
                this.$mac = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01131(this.this$0, this.$mac, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = this.this$0.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
                final DisturbViewModel disturbViewModel = this.this$0;
                this.label = 2;
                if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel.getDisturbSwitch.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                        if (deviceSetting != null) {
                            disturbViewModel._uiState.postValue(new DisturbUI(deviceSetting.getDisturbSwitch(), deviceSetting.getDisturbStart(), deviceSetting.getDisturbEnd(), deviceSetting.getDisturbManualSwitch()));
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DisturbViewModel.this.launchOnUI(new C01131(DisturbViewModel.this, this.$mac, null));
            return Unit.INSTANCE;
        }
    }

    public final void getDisturbSwitch(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new AnonymousClass1(mac, null));
    }

    /* compiled from: DisturbViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$saveDisturbSwitch$1", f = "DisturbViewModel.kt", i = {}, l = {56, 56}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$saveDisturbSwitch$1, reason: invalid class name and case insensitive filesystem */
    static final class C05181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DisturbUI $disturb;
        final /* synthetic */ String $mac;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05181(String str, DisturbUI disturbUI, Continuation<? super C05181> continuation) {
            super(2, continuation);
            this.$mac = str;
            this.$disturb = disturbUI;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DisturbViewModel.this.new C05181(this.$mac, this.$disturb, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: DisturbViewModel.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "emit", "(Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$saveDisturbSwitch$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01151<T> implements FlowCollector {
            final /* synthetic */ DisturbUI $disturb;
            final /* synthetic */ String $mac;
            final /* synthetic */ DisturbViewModel this$0;

            C01151(DisturbViewModel disturbViewModel, String str, DisturbUI disturbUI) {
                this.this$0 = disturbViewModel;
                this.$mac = str;
                this.$disturb = disturbUI;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((DeviceSetting) obj, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                if (deviceSetting != null) {
                    DisturbUI disturbUI = this.$disturb;
                    deviceSetting.setDisturbSwitch(disturbUI.getSwitch());
                    deviceSetting.setDisturbStart(disturbUI.getStart());
                    deviceSetting.setDisturbEnd(disturbUI.getEnd());
                }
                if (deviceSetting != null) {
                    this.this$0.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(this.$mac, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting)));
                    CommandHandle.getInstance().executeReqCmd(DndReq.getWriteInstance(deviceSetting.getDisturbSwitch(), new StartEndTimeEntity(deviceSetting.getDisturbStart() / 60, deviceSetting.getDisturbStart() % 60, deviceSetting.getDisturbEnd() / 60, deviceSetting.getDisturbEnd() % 60)), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel$saveDisturbSwitch$1$1$$ExternalSyntheticLambda0
                        @Override // com.oudmon.ble.base.communication.ICommandResponse
                        public final void onDataResponse(BaseRspCmd baseRspCmd) {
                            DisturbViewModel.C05181.C01151.m445emit$lambda1((DndRsp) baseRspCmd);
                        }
                    });
                    this.this$0._uiState.postValue(new DisturbUI(deviceSetting.getDisturbSwitch(), deviceSetting.getDisturbStart(), deviceSetting.getDisturbEnd(), deviceSetting.getDisturbManualSwitch()));
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: emit$lambda-1, reason: not valid java name */
            public static final void m445emit$lambda1(DndRsp dndRsp) {
                EventBus.getDefault().post(new RefreshEvent(DeviceFragment.class));
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DisturbViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            if (((Flow) obj).collect(new C01151(DisturbViewModel.this, this.$mac, this.$disturb), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveDisturbSwitch(String mac, DisturbUI disturb) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(disturb, "disturb");
        launchOnUI(new C05181(mac, disturb, null));
    }

    /* compiled from: DisturbViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbViewModel$DisturbUI;", "", "switch", "", "start", "", "end", "manualSwitch", "(ZIIZ)V", "getEnd", "()I", "setEnd", "(I)V", "getManualSwitch", "()Z", "setManualSwitch", "(Z)V", "getStart", "setStart", "getSwitch", "setSwitch", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class DisturbUI {
        private int end;
        private boolean manualSwitch;
        private int start;
        private boolean switch;

        public static /* synthetic */ DisturbUI copy$default(DisturbUI disturbUI, boolean z, int i, int i2, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                z = disturbUI.switch;
            }
            if ((i3 & 2) != 0) {
                i = disturbUI.start;
            }
            if ((i3 & 4) != 0) {
                i2 = disturbUI.end;
            }
            if ((i3 & 8) != 0) {
                z2 = disturbUI.manualSwitch;
            }
            return disturbUI.copy(z, i, i2, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getSwitch() {
            return this.switch;
        }

        /* renamed from: component2, reason: from getter */
        public final int getStart() {
            return this.start;
        }

        /* renamed from: component3, reason: from getter */
        public final int getEnd() {
            return this.end;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getManualSwitch() {
            return this.manualSwitch;
        }

        public final DisturbUI copy(boolean z, int start, int end, boolean manualSwitch) {
            return new DisturbUI(z, start, end, manualSwitch);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DisturbUI)) {
                return false;
            }
            DisturbUI disturbUI = (DisturbUI) other;
            return this.switch == disturbUI.switch && this.start == disturbUI.start && this.end == disturbUI.end && this.manualSwitch == disturbUI.manualSwitch;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        public int hashCode() {
            boolean z = this.switch;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = ((((r0 * 31) + this.start) * 31) + this.end) * 31;
            boolean z2 = this.manualSwitch;
            return i + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "DisturbUI(switch=" + this.switch + ", start=" + this.start + ", end=" + this.end + ", manualSwitch=" + this.manualSwitch + ')';
        }

        public DisturbUI(boolean z, int i, int i2, boolean z2) {
            this.switch = z;
            this.start = i;
            this.end = i2;
            this.manualSwitch = z2;
        }

        public final boolean getSwitch() {
            return this.switch;
        }

        public final void setSwitch(boolean z) {
            this.switch = z;
        }

        public final int getStart() {
            return this.start;
        }

        public final void setStart(int i) {
            this.start = i;
        }

        public final int getEnd() {
            return this.end;
        }

        public final void setEnd(int i) {
            this.end = i;
        }

        public final boolean getManualSwitch() {
            return this.manualSwitch;
        }

        public final void setManualSwitch(boolean z) {
            this.manualSwitch = z;
        }
    }
}
