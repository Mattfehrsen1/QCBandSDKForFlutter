package com.qcwireless.qcwatch.ui.device.more;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.DisplayTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DisplayTimeRsp;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel;
import java.util.ArrayList;
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

/* compiled from: MoreSettingViewModel.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u001e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/MoreSettingViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "settingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/more/MoreSettingViewModel$MoreUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "cleanContact", "", "getScreenSetting", "mac", "", "saveScreenSeconds", "seconds", "", "totalNeedle", "currNeedle", "MoreUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreSettingViewModel extends BaseViewModel {
    private final MutableLiveData<MoreUI> _uiState;
    private final DeviceSettingRepository settingRepository;

    public MoreSettingViewModel(DeviceSettingRepository settingRepository) {
        Intrinsics.checkNotNullParameter(settingRepository, "settingRepository");
        this.settingRepository = settingRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<MoreUI> getUiState() {
        return this._uiState;
    }

    /* compiled from: MoreSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$getScreenSetting$1", f = "MoreSettingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$getScreenSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C05231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05231(Continuation<? super C05231> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MoreSettingViewModel.this.new C05231(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CommandHandle commandHandle = CommandHandle.getInstance();
            DisplayTimeReq readInstance = DisplayTimeReq.getReadInstance();
            final MoreSettingViewModel moreSettingViewModel = MoreSettingViewModel.this;
            commandHandle.executeReqCmd(readInstance, new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$getScreenSetting$1$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    MoreSettingViewModel.C05231.m471invokeSuspend$lambda0(moreSettingViewModel, (DisplayTimeRsp) baseRspCmd);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-0, reason: not valid java name */
        public static final void m471invokeSuspend$lambda0(MoreSettingViewModel moreSettingViewModel, DisplayTimeRsp displayTimeRsp) {
            moreSettingViewModel._uiState.postValue(new MoreUI(displayTimeRsp.getDisplayTime(), displayTimeRsp.getTotal(), displayTimeRsp.getType()));
        }
    }

    public final void getScreenSetting(String mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new C05231(null));
    }

    /* compiled from: MoreSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$saveScreenSeconds$1", f = "MoreSettingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$saveScreenSeconds$1, reason: invalid class name and case insensitive filesystem */
    static final class C05241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $currNeedle;
        final /* synthetic */ int $seconds;
        final /* synthetic */ int $totalNeedle;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05241(int i, int i2, int i3, Continuation<? super C05241> continuation) {
            super(2, continuation);
            this.$seconds = i;
            this.$totalNeedle = i2;
            this.$currNeedle = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-0, reason: not valid java name */
        public static final void m473invokeSuspend$lambda0(DisplayTimeRsp displayTimeRsp) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05241(this.$seconds, this.$totalNeedle, this.$currNeedle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CommandHandle.getInstance().executeReqCmd(DisplayTimeReq.getWriteInstance(this.$seconds, 0, 0, this.$totalNeedle, this.$currNeedle), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$saveScreenSeconds$1$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    MoreSettingViewModel.C05241.m473invokeSuspend$lambda0((DisplayTimeRsp) baseRspCmd);
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void saveScreenSeconds(int seconds, int totalNeedle, int currNeedle) {
        launchOnUI(new C05241(seconds, totalNeedle, currNeedle, null));
    }

    /* compiled from: MoreSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$cleanContact$1", f = "MoreSettingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel$cleanContact$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MoreSettingViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MoreSettingViewModel.this.settingRepository.saveContact(new ArrayList());
            return Unit.INSTANCE;
        }
    }

    public final void cleanContact() {
        launchOnUI(new AnonymousClass1(null));
    }

    /* compiled from: MoreSettingViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/MoreSettingViewModel$MoreUI;", "", "screenSeconds", "", "totalNeedle", "currNeedle", "(III)V", "getCurrNeedle", "()I", "setCurrNeedle", "(I)V", "getScreenSeconds", "getTotalNeedle", "setTotalNeedle", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class MoreUI {
        private int currNeedle;
        private final int screenSeconds;
        private int totalNeedle;

        public static /* synthetic */ MoreUI copy$default(MoreUI moreUI, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = moreUI.screenSeconds;
            }
            if ((i4 & 2) != 0) {
                i2 = moreUI.totalNeedle;
            }
            if ((i4 & 4) != 0) {
                i3 = moreUI.currNeedle;
            }
            return moreUI.copy(i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getScreenSeconds() {
            return this.screenSeconds;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTotalNeedle() {
            return this.totalNeedle;
        }

        /* renamed from: component3, reason: from getter */
        public final int getCurrNeedle() {
            return this.currNeedle;
        }

        public final MoreUI copy(int screenSeconds, int totalNeedle, int currNeedle) {
            return new MoreUI(screenSeconds, totalNeedle, currNeedle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MoreUI)) {
                return false;
            }
            MoreUI moreUI = (MoreUI) other;
            return this.screenSeconds == moreUI.screenSeconds && this.totalNeedle == moreUI.totalNeedle && this.currNeedle == moreUI.currNeedle;
        }

        public int hashCode() {
            return (((this.screenSeconds * 31) + this.totalNeedle) * 31) + this.currNeedle;
        }

        public String toString() {
            return "MoreUI(screenSeconds=" + this.screenSeconds + ", totalNeedle=" + this.totalNeedle + ", currNeedle=" + this.currNeedle + ')';
        }

        public MoreUI(int i, int i2, int i3) {
            this.screenSeconds = i;
            this.totalNeedle = i2;
            this.currNeedle = i3;
        }

        public final int getScreenSeconds() {
            return this.screenSeconds;
        }

        public final int getTotalNeedle() {
            return this.totalNeedle;
        }

        public final void setTotalNeedle(int i) {
            this.totalNeedle = i;
        }

        public final int getCurrNeedle() {
            return this.currNeedle;
        }

        public final void setCurrNeedle(int i) {
            this.currNeedle = i;
        }
    }
}
