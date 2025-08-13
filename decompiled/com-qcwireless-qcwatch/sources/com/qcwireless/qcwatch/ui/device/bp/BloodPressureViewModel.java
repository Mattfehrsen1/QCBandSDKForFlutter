package com.qcwireless.qcwatch.ui.device.bp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
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

/* compiled from: BloodPressureViewModel.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ&\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/bp/BloodPressureViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/bp/BloodPressureViewModel$BpUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "getBpSetting", "", "mac", "", "saveBpSetting", "switch", "", "start", "", "end", "BpUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureViewModel extends BaseViewModel {
    private final MutableLiveData<BpUI> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;

    public BloodPressureViewModel(DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<BpUI> getUiState() {
        return this._uiState;
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel$getBpSetting$1", f = "BloodPressureViewModel.kt", i = {}, l = {24, 24}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel$getBpSetting$1, reason: invalid class name */
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
            return BloodPressureViewModel.this.new AnonymousClass1(this.$mac, continuation);
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
                obj = BloodPressureViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final BloodPressureViewModel bloodPressureViewModel = BloodPressureViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel.getBpSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    bloodPressureViewModel._uiState.postValue(deviceSetting != null ? new BpUI(deviceSetting.getBpSwitch(), deviceSetting.getBpStart(), deviceSetting.getBpEnd()) : null);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getBpSetting(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new AnonymousClass1(mac, null));
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel$saveBpSetting$1", f = "BloodPressureViewModel.kt", i = {}, l = {32, 32}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel$saveBpSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C05061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $end;
        final /* synthetic */ String $mac;
        final /* synthetic */ int $start;
        final /* synthetic */ boolean $switch;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05061(String str, boolean z, int i, int i2, Continuation<? super C05061> continuation) {
            super(2, continuation);
            this.$mac = str;
            this.$switch = z;
            this.$start = i;
            this.$end = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BloodPressureViewModel.this.new C05061(this.$mac, this.$switch, this.$start, this.$end, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BloodPressureViewModel.this.deviceSettingRepository.getDeviceSetting(this.$mac, this);
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
            final BloodPressureViewModel bloodPressureViewModel = BloodPressureViewModel.this;
            final String str = this.$mac;
            final boolean z = this.$switch;
            final int i2 = this.$start;
            final int i3 = this.$end;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.bp.BloodPressureViewModel.saveBpSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    if (deviceSetting != null) {
                        boolean z2 = z;
                        int i4 = i2;
                        int i5 = i3;
                        deviceSetting.setBpSwitch(z2);
                        deviceSetting.setBpStart(i4);
                        deviceSetting.setBpEnd(i5);
                    }
                    if (deviceSetting != null) {
                        bloodPressureViewModel.deviceSettingRepository.saveDeviceSetting(new DeviceSettingEntity(str, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(deviceSetting)));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveBpSetting(String mac, boolean z, int start, int end) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new C05061(mac, z, start, end, null));
    }

    /* compiled from: BloodPressureViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/bp/BloodPressureViewModel$BpUI;", "", "switch", "", "start", "", "end", "(ZII)V", "getEnd", "()I", "getStart", "getSwitch", "()Z", "component1", "component2", "component3", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class BpUI {
        private final int end;
        private final int start;
        private final boolean switch;

        public static /* synthetic */ BpUI copy$default(BpUI bpUI, boolean z, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                z = bpUI.switch;
            }
            if ((i3 & 2) != 0) {
                i = bpUI.start;
            }
            if ((i3 & 4) != 0) {
                i2 = bpUI.end;
            }
            return bpUI.copy(z, i, i2);
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

        public final BpUI copy(boolean z, int start, int end) {
            return new BpUI(z, start, end);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BpUI)) {
                return false;
            }
            BpUI bpUI = (BpUI) other;
            return this.switch == bpUI.switch && this.start == bpUI.start && this.end == bpUI.end;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        public int hashCode() {
            boolean z = this.switch;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (((r0 * 31) + this.start) * 31) + this.end;
        }

        public String toString() {
            return "BpUI(switch=" + this.switch + ", start=" + this.start + ", end=" + this.end + ')';
        }

        public BpUI(boolean z, int i, int i2) {
            this.switch = z;
            this.start = i;
            this.end = i2;
        }

        public final boolean getSwitch() {
            return this.switch;
        }

        public final int getStart() {
            return this.start;
        }

        public final int getEnd() {
            return this.end;
        }
    }
}
