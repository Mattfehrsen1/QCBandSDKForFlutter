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
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.file.SimpleCallback;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qcwatch.base.event.OTAFileStatusEvent;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import com.qcwireless.qcwatch.ui.base.bean.response.device.DeviceMissFileResp;
import com.qcwireless.qcwatch.ui.base.repository.device.OTAFileStatus;
import com.qcwireless.qcwatch.ui.base.repository.device.OTARepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

/* compiled from: WatchFileDismissViewModel.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003,-.B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001e\u001a\u00020\u001fJ \u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"H\u0002J\u001e\u0010%\u001a\u00020\u001f2\u0016\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\"0\u0016j\b\u0012\u0004\u0012\u00020\"`\u0017J \u0010'\u001a\u00020\u001f2\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0016j\b\u0012\u0004\u0012\u00020\u000e`\u0017H\u0002J\u0006\u0010(\u001a\u00020\u001fJ\u0006\u0010)\u001a\u00020\u001fJ\u0014\u0010*\u001a\u00020\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0+R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0016j\b\u0012\u0004\u0012\u00020\u000e`\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u00108F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0012¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "otaRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/OTARepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/OTARepository;)V", "_dismissFileState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel$DismissFileFirmwareUI;", "_uiState", "Lcom/qcwireless/qcwatch/ui/base/repository/device/OTAFileStatus;", "callback", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel$Callback;", "dismissFile", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DeviceMissFileResp;", "dismissFileState", "Landroidx/lifecycle/LiveData;", "getDismissFileState", "()Landroidx/lifecycle/LiveData;", "downloadCallback", "Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel$QueueDismissFileListener;", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mSendFileIndex", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "uiState", "getUiState", "cancelTask", "", "createTask", "url", "", "fileName", "tag", "downloadFile", "fileNames", "downloadWatchDismissFiles", "initFileHandler", "sendFile", "startDismissFileOTA", "", "Callback", "DismissFileFirmwareUI", "QueueDismissFileListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFileDismissViewModel extends BaseViewModel {
    private final MutableLiveData<DismissFileFirmwareUI> _dismissFileState;
    private final MutableLiveData<OTAFileStatus> _uiState;
    private final Callback callback;
    private List<DeviceMissFileResp> dismissFile;
    private final QueueDismissFileListener downloadCallback;
    private ArrayList<DeviceMissFileResp> list;
    private int mSendFileIndex;
    private final OTARepository otaRepository;
    private DownloadTask task;

    public WatchFileDismissViewModel(OTARepository otaRepository) {
        Intrinsics.checkNotNullParameter(otaRepository, "otaRepository");
        this.otaRepository = otaRepository;
        this.dismissFile = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this.callback = new Callback();
        this.downloadCallback = new QueueDismissFileListener();
        this._dismissFileState = new MutableLiveData<>();
    }

    public final LiveData<OTAFileStatus> getUiState() {
        return this._uiState;
    }

    public final LiveData<DismissFileFirmwareUI> getDismissFileState() {
        return this._dismissFileState;
    }

    public final void startDismissFileOTA(List<DeviceMissFileResp> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        initFileHandler();
        this.dismissFile.clear();
        this.dismissFile.addAll(list);
        this.mSendFileIndex = 0;
        sendFile();
    }

    public final void initFileHandler() {
        FileHandle.getInstance().removeCallback(this.callback);
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().initRegister();
    }

    public final void sendFile() {
        try {
            if (this.mSendFileIndex < this.dismissFile.size()) {
                final DeviceMissFileResp deviceMissFileResp = this.dismissFile.get(this.mSendFileIndex);
                if (FileHandle.getInstance().checkFile(new File(FileUtils.INSTANCE.getBinDirFile(), deviceMissFileResp.getFileName()).getAbsolutePath())) {
                    ThreadUtils.postDelay(new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel.sendFile.1
                        @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
                        protected void task() throws UnsupportedEncodingException {
                            FileHandle.getInstance().cmdFileInit(deviceMissFileResp.getFileName());
                        }
                    }, 1000L);
                } else {
                    OTAFileStatus oTAFileStatus = new OTAFileStatus();
                    oTAFileStatus.setFileExists(false);
                    this._uiState.postValue(oTAFileStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: WatchFileDismissViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel$downloadFile$1", f = "WatchFileDismissViewModel.kt", i = {}, l = {83, 83}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel$downloadFile$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ArrayList<String> $fileNames;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ArrayList<String> arrayList, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$fileNames = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WatchFileDismissViewModel.this.new AnonymousClass1(this.$fileNames, continuation);
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
                obj = WatchFileDismissViewModel.this.otaRepository.getDeviceMissFileFromServer(this.$fileNames, this);
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
            final WatchFileDismissViewModel watchFileDismissViewModel = WatchFileDismissViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel.downloadFile.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<ArrayList<DeviceMissFileResp>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<ArrayList<DeviceMissFileResp>> netState, Continuation<? super Unit> continuation) {
                    XLog.i(netState);
                    if (netState.isSuccess() != null) {
                        watchFileDismissViewModel.downloadWatchDismissFiles(netState.isSuccess());
                        watchFileDismissViewModel.list = netState.isSuccess();
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void downloadFile(ArrayList<String> fileNames) {
        Intrinsics.checkNotNullParameter(fileNames, "fileNames");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(fileNames, null), 3, null);
    }

    private final DownloadTask createTask(String url, String fileName, String tag) {
        DownloadTask downloadTaskBuild = new DownloadTask.Builder(url, FileUtils.INSTANCE.getBinDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).setPriority(100).setReadBufferSize(8192).build();
        Intrinsics.checkNotNullExpressionValue(downloadTaskBuild, "Builder(url, parentFile)…192)\n            .build()");
        this.task = downloadTaskBuild;
        if (downloadTaskBuild == null) {
            Intrinsics.throwUninitializedPropertyAccessException("task");
            downloadTaskBuild = null;
        }
        downloadTaskBuild.setTag(tag);
        DownloadTask downloadTask = this.task;
        if (downloadTask != null) {
            return downloadTask;
        }
        Intrinsics.throwUninitializedPropertyAccessException("task");
        return null;
    }

    public final void cancelTask() {
        try {
            DownloadTask downloadTask = this.task;
            if (downloadTask != null) {
                if (downloadTask == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("task");
                    downloadTask = null;
                }
                downloadTask.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadWatchDismissFiles(ArrayList<DeviceMissFileResp> list) {
        File binDirFile = FileUtils.INSTANCE.getBinDirFile();
        Iterator<DeviceMissFileResp> it = list.iterator();
        while (it.hasNext()) {
            DeviceMissFileResp next = it.next();
            if (FileUtils.INSTANCE.fileExists(binDirFile.getAbsolutePath() + '/' + next.getFileName())) {
                FileUtils.INSTANCE.deleteFile(binDirFile.getAbsolutePath() + '/' + next.getFileName());
            }
            createTask(next.getDownloadUrl(), next.getFileName(), next.getFileName()).enqueue(this.downloadCallback);
        }
    }

    /* compiled from: WatchFileDismissViewModel.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J*\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel$QueueDismissFileListener;", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "(Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel;)V", "connected", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "blockCount", "", "currentOffset", "", "totalLength", "progress", "retry", "cause", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "realCause", "Ljava/lang/Exception;", "model", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "taskStart", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class QueueDismissFileListener extends DownloadListener1 {
        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
            Intrinsics.checkNotNullParameter(task, "task");
        }

        public QueueDismissFileListener() {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(model, "model");
            XLog.i("task start");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void taskEnd(DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(cause, "cause");
            Intrinsics.checkNotNullParameter(model, "model");
            if (cause == EndCause.ERROR) {
                ArrayList arrayList = WatchFileDismissViewModel.this.list;
                ArrayList arrayList2 = null;
                if (arrayList == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("list");
                    arrayList = null;
                }
                if (arrayList.size() > 0) {
                    WatchFileDismissViewModel watchFileDismissViewModel = WatchFileDismissViewModel.this;
                    ArrayList arrayList3 = watchFileDismissViewModel.list;
                    if (arrayList3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("list");
                    } else {
                        arrayList2 = arrayList3;
                    }
                    watchFileDismissViewModel.downloadWatchDismissFiles(arrayList2);
                }
            }
            XLog.i("task end" + realCause);
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void retry(DownloadTask task, ResumeFailedCause cause) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(cause, "cause");
            XLog.i("task retry");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void progress(DownloadTask task, long currentOffset, long totalLength) {
            int i;
            Intrinsics.checkNotNullParameter(task, "task");
            XLog.i("下载进度:" + currentOffset + "--" + totalLength);
            long j = (((long) 100) * currentOffset) / totalLength;
            XLog.i(Long.valueOf(j));
            if (currentOffset == totalLength || (i = (int) j) == 100) {
                WatchFileDismissViewModel.this._dismissFileState.postValue(new DismissFileFirmwareUI(100L, task.getTag().toString(), true));
                EventBus.getDefault().post(new OTAFileStatusEvent(1, 100, true, task.getTag().toString()));
            } else {
                WatchFileDismissViewModel.this._dismissFileState.postValue(new DismissFileFirmwareUI(j, task.getTag().toString(), false));
                EventBus.getDefault().post(new OTAFileStatusEvent(1, i, false, task.getTag().toString()));
            }
        }
    }

    /* compiled from: WatchFileDismissViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel$DismissFileFirmwareUI;", "", "progressBar", "", "fileName", "", ANConstants.SUCCESS, "", "(JLjava/lang/String;Z)V", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getProgressBar", "()J", "setProgressBar", "(J)V", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "component3", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class DismissFileFirmwareUI {
        private String fileName;
        private long progressBar;
        private boolean success;

        public DismissFileFirmwareUI() {
            this(0L, null, false, 7, null);
        }

        public static /* synthetic */ DismissFileFirmwareUI copy$default(DismissFileFirmwareUI dismissFileFirmwareUI, long j, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = dismissFileFirmwareUI.progressBar;
            }
            if ((i & 2) != 0) {
                str = dismissFileFirmwareUI.fileName;
            }
            if ((i & 4) != 0) {
                z = dismissFileFirmwareUI.success;
            }
            return dismissFileFirmwareUI.copy(j, str, z);
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

        public final DismissFileFirmwareUI copy(long progressBar, String fileName, boolean success) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new DismissFileFirmwareUI(progressBar, fileName, success);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DismissFileFirmwareUI)) {
                return false;
            }
            DismissFileFirmwareUI dismissFileFirmwareUI = (DismissFileFirmwareUI) other;
            return this.progressBar == dismissFileFirmwareUI.progressBar && Intrinsics.areEqual(this.fileName, dismissFileFirmwareUI.fileName) && this.success == dismissFileFirmwareUI.success;
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
            return "DismissFileFirmwareUI(progressBar=" + this.progressBar + ", fileName=" + this.fileName + ", success=" + this.success + ')';
        }

        public DismissFileFirmwareUI(long j, String fileName, boolean z) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            this.progressBar = j;
            this.fileName = fileName;
            this.success = z;
        }

        public /* synthetic */ DismissFileFirmwareUI(long j, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
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

    /* compiled from: WatchFileDismissViewModel.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/device/dfu/vm/WatchFileDismissViewModel;)V", "onComplete", "", "onProgress", "percent", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onProgress(int percent) {
            OTAFileStatus oTAFileStatus = new OTAFileStatus();
            oTAFileStatus.setCurrIndex(WatchFileDismissViewModel.this.mSendFileIndex);
            oTAFileStatus.setTotalIndex(WatchFileDismissViewModel.this.dismissFile.size());
            oTAFileStatus.setProgressBar(percent);
            oTAFileStatus.setOtaSuccess(false);
            WatchFileDismissViewModel.this._uiState.postValue(oTAFileStatus);
            EventBus.getDefault().post(new OTAFileStatusEvent(2, percent, false, ""));
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onComplete() {
            WatchFileDismissViewModel.this.mSendFileIndex++;
            if (WatchFileDismissViewModel.this.mSendFileIndex >= WatchFileDismissViewModel.this.dismissFile.size()) {
                OTAFileStatus oTAFileStatus = new OTAFileStatus();
                oTAFileStatus.setOtaSuccess(true);
                WatchFileDismissViewModel.this._uiState.postValue(oTAFileStatus);
                EventBus.getDefault().post(new OTAFileStatusEvent(2, 100, true, ""));
                return;
            }
            WatchFileDismissViewModel.this.sendFile();
        }
    }
}
