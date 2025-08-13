package com.qcwireless.qcwatch.ui.device.dfu.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.androidnetworking.common.ANConstants;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.qcwireless.qcwatch.base.event.OTAFileStatusEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import com.qcwireless.qcwatch.ui.base.bean.response.device.FirmwareOtaResp;
import com.qcwireless.qcwatch.ui.base.repository.device.OTARepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.home.healthy.bean.DFUInformationBean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DeviceFirmwareUpdateViewModel.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001f B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0017\u001a\u00020\u0015J \u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aH\u0002J\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "otaRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/OTARepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/OTARepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel$FirmwareUI;", "_updateUiInfo", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "callback", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel$QueueBinListener;", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "updateUiInfo", "getUpdateUiInfo", "cancelTask", "", "checkOta", "checkOtaChina", "createTask", "url", "", "fileName", "tag", "saveDeviceDfuInformation", "firmwareOtaResp", "FirmwareUI", "QueueBinListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceFirmwareUpdateViewModel extends BaseViewModel {
    private final MutableLiveData<FirmwareUI> _uiState;
    private final MutableLiveData<FirmwareOtaResp> _updateUiInfo;
    private final QueueBinListener callback;
    private final OTARepository otaRepository;
    private DownloadTask task;

    public DeviceFirmwareUpdateViewModel(OTARepository otaRepository) {
        Intrinsics.checkNotNullParameter(otaRepository, "otaRepository");
        this.otaRepository = otaRepository;
        this._uiState = new MutableLiveData<>();
        this.callback = new QueueBinListener();
        this._updateUiInfo = new MutableLiveData<>();
    }

    public final LiveData<FirmwareUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<FirmwareOtaResp> getUpdateUiInfo() {
        return this._updateUiInfo;
    }

    /* compiled from: DeviceFirmwareUpdateViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel$checkOta$1", f = "DeviceFirmwareUpdateViewModel.kt", i = {}, l = {46, 49}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel$checkOta$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceFirmwareUpdateViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final DeviceFirmwareUpdateViewModel deviceFirmwareUpdateViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OTARepository oTARepository = DeviceFirmwareUpdateViewModel.this.otaRepository;
                deviceFirmwareUpdateViewModel = DeviceFirmwareUpdateViewModel.this;
                String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
                String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
                this.L$0 = deviceFirmwareUpdateViewModel;
                this.label = 1;
                obj = oTARepository.checkOtaFromServer(hwVersion, fmVersion, this);
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
                deviceFirmwareUpdateViewModel = (DeviceFirmwareUpdateViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel$checkOta$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<FirmwareOtaResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<FirmwareOtaResp> netState, Continuation<? super Unit> continuation) {
                    FirmwareOtaResp firmwareOtaRespIsSuccess = netState.isSuccess();
                    if (firmwareOtaRespIsSuccess != null) {
                        deviceFirmwareUpdateViewModel.saveDeviceDfuInformation(firmwareOtaRespIsSuccess);
                    }
                    MutableLiveData mutableLiveData = deviceFirmwareUpdateViewModel._updateUiInfo;
                    FirmwareOtaResp firmwareOtaRespIsSuccess2 = netState.isSuccess();
                    Intrinsics.checkNotNull(firmwareOtaRespIsSuccess2);
                    mutableLiveData.postValue(firmwareOtaRespIsSuccess2);
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkOta() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: DeviceFirmwareUpdateViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel$checkOtaChina$1", f = "DeviceFirmwareUpdateViewModel.kt", i = {}, l = {60, 63}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel$checkOtaChina$1, reason: invalid class name and case insensitive filesystem */
    static final class C05161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C05161(Continuation<? super C05161> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceFirmwareUpdateViewModel.this.new C05161(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final DeviceFirmwareUpdateViewModel deviceFirmwareUpdateViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OTARepository oTARepository = DeviceFirmwareUpdateViewModel.this.otaRepository;
                deviceFirmwareUpdateViewModel = DeviceFirmwareUpdateViewModel.this;
                String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
                String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
                this.L$0 = deviceFirmwareUpdateViewModel;
                this.label = 1;
                obj = oTARepository.checkOtaFromServerChina(hwVersion, fmVersion, this);
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
                deviceFirmwareUpdateViewModel = (DeviceFirmwareUpdateViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel$checkOtaChina$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<FirmwareOtaResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<FirmwareOtaResp> netState, Continuation<? super Unit> continuation) {
                    MutableLiveData mutableLiveData = deviceFirmwareUpdateViewModel._updateUiInfo;
                    FirmwareOtaResp firmwareOtaRespIsSuccess = netState.isSuccess();
                    Intrinsics.checkNotNull(firmwareOtaRespIsSuccess);
                    mutableLiveData.postValue(firmwareOtaRespIsSuccess);
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkOtaChina() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05161(null), 3, null);
    }

    public final void saveDeviceDfuInformation(FirmwareOtaResp firmwareOtaResp) {
        Intrinsics.checkNotNullParameter(firmwareOtaResp, "firmwareOtaResp");
        DFUInformationBean dFUInformationBean = new DFUInformationBean();
        dFUInformationBean.setHardwareVersion(firmwareOtaResp.getHardwareVersion());
        dFUInformationBean.setLastVersion(firmwareOtaResp.getVersion());
        if (firmwareOtaResp.getIsEnforceUpdate().length() == 0) {
            dFUInformationBean.setEnforceUpdate(1);
        }
        dFUInformationBean.setOpenOrNot(firmwareOtaResp.getOpenOrNot());
        dFUInformationBean.setDownloadUrl(firmwareOtaResp.getDownloadUrl());
        if (dFUInformationBean.getLastVersion().length() > 0) {
            int openOrNot = dFUInformationBean.getOpenOrNot();
            boolean development = UserConfig.INSTANCE.getInstance().getDevelopment();
            String str = dFUInformationBean.getLastVersion() + ".bin";
            DownloadTask downloadTask = null;
            if (development) {
                if (openOrNot == 3) {
                    DownloadTask downloadTaskCreateTask = createTask(dFUInformationBean.getDownloadUrl(), str, str);
                    this.task = downloadTaskCreateTask;
                    if (downloadTaskCreateTask == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("task");
                    } else {
                        downloadTask = downloadTaskCreateTask;
                    }
                    downloadTask.enqueue(this.callback);
                    return;
                }
                return;
            }
            if (openOrNot == 2) {
                DownloadTask downloadTaskCreateTask2 = createTask(dFUInformationBean.getDownloadUrl(), str, str);
                this.task = downloadTaskCreateTask2;
                if (downloadTaskCreateTask2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("task");
                } else {
                    downloadTask = downloadTaskCreateTask2;
                }
                downloadTask.enqueue(this.callback);
            }
        }
    }

    public final void cancelTask() {
        try {
            DownloadTask downloadTask = this.task;
            if (downloadTask == null) {
                Intrinsics.throwUninitializedPropertyAccessException("task");
                downloadTask = null;
            }
            downloadTask.cancel();
        } catch (Exception unused) {
        }
    }

    /* compiled from: DeviceFirmwareUpdateViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel$FirmwareUI;", "", "progressBar", "", "fileName", "", ANConstants.SUCCESS, "", "(JLjava/lang/String;Z)V", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getProgressBar", "()J", "setProgressBar", "(J)V", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "component3", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class FirmwareUI {
        private String fileName;
        private long progressBar;
        private boolean success;

        public FirmwareUI() {
            this(0L, null, false, 7, null);
        }

        public static /* synthetic */ FirmwareUI copy$default(FirmwareUI firmwareUI, long j, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = firmwareUI.progressBar;
            }
            if ((i & 2) != 0) {
                str = firmwareUI.fileName;
            }
            if ((i & 4) != 0) {
                z = firmwareUI.success;
            }
            return firmwareUI.copy(j, str, z);
        }

        /* renamed from: component1, reason: from getter */
        public final long getProgressBar() {
            return this.progressBar;
        }

        /* renamed from: component2, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final FirmwareUI copy(long progressBar, String fileName, boolean success) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new FirmwareUI(progressBar, fileName, success);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FirmwareUI)) {
                return false;
            }
            FirmwareUI firmwareUI = (FirmwareUI) other;
            return this.progressBar == firmwareUI.progressBar && Intrinsics.areEqual(this.fileName, firmwareUI.fileName) && this.success == firmwareUI.success;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int iM = ((CollectionRequest$$ExternalSyntheticBackport0.m(this.progressBar) * 31) + this.fileName.hashCode()) * 31;
            boolean z = this.success;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return iM + i;
        }

        public String toString() {
            return "FirmwareUI(progressBar=" + this.progressBar + ", fileName=" + this.fileName + ", success=" + this.success + ')';
        }

        public FirmwareUI(long j, String fileName, boolean z) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            this.progressBar = j;
            this.fileName = fileName;
            this.success = z;
        }

        public /* synthetic */ FirmwareUI(long j, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? false : z);
        }

        public final long getProgressBar() {
            return this.progressBar;
        }

        public final void setProgressBar(long j) {
            this.progressBar = j;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final void setFileName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.fileName = str;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final void setSuccess(boolean z) {
            this.success = z;
        }
    }

    private final DownloadTask createTask(String url, String fileName, String tag) {
        DownloadTask task = new DownloadTask.Builder(url, FileUtils.INSTANCE.getBinDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).setPriority(11).setReadBufferSize(8192).build();
        task.setTag(tag);
        Intrinsics.checkNotNullExpressionValue(task, "task");
        return task;
    }

    /* compiled from: DeviceFirmwareUpdateViewModel.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J*\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel$QueueBinListener;", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "(Lcom/qcwireless/qcwatch/ui/device/dfu/vm/DeviceFirmwareUpdateViewModel;)V", "connected", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "blockCount", "", "currentOffset", "", "totalLength", "progress", "retry", "cause", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "realCause", "Ljava/lang/Exception;", "model", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "taskStart", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class QueueBinListener extends DownloadListener1 {
        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
            Intrinsics.checkNotNullParameter(task, "task");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void retry(DownloadTask task, ResumeFailedCause cause) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(cause, "cause");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void taskEnd(DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(cause, "cause");
            Intrinsics.checkNotNullParameter(model, "model");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(model, "model");
        }

        public QueueBinListener() {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void progress(DownloadTask task, long currentOffset, long totalLength) {
            int i;
            Intrinsics.checkNotNullParameter(task, "task");
            XLog.i("下载进度:" + currentOffset + "--" + totalLength);
            long j = (((long) 100) * currentOffset) / totalLength;
            XLog.i(Long.valueOf(j));
            if (currentOffset == totalLength || (i = (int) j) == 100) {
                DeviceFirmwareUpdateViewModel.this._uiState.postValue(new FirmwareUI(100L, task.getTag().toString(), true));
                EventBus.getDefault().post(new OTAFileStatusEvent(3, 100, true, task.getTag().toString()));
            } else {
                DeviceFirmwareUpdateViewModel.this._uiState.postValue(new FirmwareUI(j, task.getTag().toString(), false));
                EventBus.getDefault().post(new OTAFileStatusEvent(3, i, false, task.getTag().toString()));
            }
        }
    }
}
