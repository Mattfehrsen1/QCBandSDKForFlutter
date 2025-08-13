package com.qcwireless.qcwatch.ui.plate.market;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANConstants;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.file.PlateEntity;
import com.oudmon.ble.base.communication.file.SimpleCallback;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.plate.bean.MarketWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WatchFaceInstallViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\"#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fJ\u000e\u0010!\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "_fileDownloadSuccess", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/ListenerBean;", "_marketUI", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchFace;", "_progressValue", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel$ProgressUI;", "callback", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel$Callback;", "fileDownloadSuccess", "Landroidx/lifecycle/LiveData;", "getFileDownloadSuccess", "()Landroidx/lifecycle/LiveData;", "marketUI", "getMarketUI", "progressValue", "getProgressValue", "watchFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchFaceDao;", "cleanWatchFaceCallback", "", "downloadWatchFaceNotExists", "item", "Lcom/qcwireless/qcwatch/ui/plate/bean/MarketWatchFaceBean;", "execWatchFaceToDevice", "name", "", "binPath", "queryByName", "Callback", "ProgressUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFaceInstallViewModel extends BaseViewModel {
    private final MutableLiveData<ListenerBean> _fileDownloadSuccess;
    private final MutableLiveData<WatchFace> _marketUI;
    private final MutableLiveData<ProgressUI> _progressValue;
    private final Callback callback;
    private final QcWatchFaceDao watchFaceDao;
    private final WatchFaceRepository watchFaceRepository;

    public WatchFaceInstallViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this._marketUI = new MutableLiveData<>();
        this.callback = new Callback();
        this._progressValue = new MutableLiveData<>();
        this.watchFaceDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchFaceDao();
        this._fileDownloadSuccess = new MutableLiveData<>();
    }

    public final LiveData<WatchFace> getMarketUI() {
        return this._marketUI;
    }

    public final LiveData<ProgressUI> getProgressValue() {
        return this._progressValue;
    }

    public final LiveData<ListenerBean> getFileDownloadSuccess() {
        return this._fileDownloadSuccess;
    }

    public final void cleanWatchFaceCallback() {
        FileHandle.getInstance().clearCallback();
    }

    public final void queryByName(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WatchFaceInstallViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel.queryByName.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WatchFaceInstallViewModel watchFaceInstallViewModel) {
                invoke2(watchFaceInstallViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WatchFaceInstallViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._marketUI.postValue(ktxRunOnBgSingle.watchFaceRepository.queryByName(name));
            }
        });
    }

    public final void execWatchFaceToDevice(String name, String binPath) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(binPath, "binPath");
        FileHandle.getInstance().setCurrFileType(1);
        FileHandle.getInstance().removeCallback(this.callback);
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().initRegister();
        if (FileHandle.getInstance().executeFilePrepare(binPath)) {
            FileHandle.getInstance().executeFileInit(name, 54);
        } else {
            this._progressValue.postValue(new ProgressUI(100, false, false, false, true));
        }
    }

    public final void downloadWatchFaceNotExists(MarketWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final File watchFaceDirFile = FileUtils.INSTANCE.getWatchFaceDirFile();
        final String name = item.getWatchFace().getName();
        if (FileUtils.INSTANCE.fileExists(watchFaceDirFile.getAbsolutePath() + '/' + name)) {
            FileUtils.INSTANCE.deleteFile(watchFaceDirFile.getAbsolutePath() + '/' + name);
        }
        AndroidNetworking.download(item.getWatchFace().getBinUrl(), watchFaceDirFile.toString(), "temp_" + name).setTag((Object) name).setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel$$ExternalSyntheticLambda0
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public final void onProgress(long j, long j2) {
                WatchFaceInstallViewModel.m1040downloadWatchFaceNotExists$lambda0(this.f$0, j, j2);
            }
        }).startDownload(new DownloadListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel.downloadWatchFaceNotExists.2
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() {
                XLog.i("temp_" + name);
                final String str = name;
                final WatchFaceInstallViewModel watchFaceInstallViewModel = this;
                final File file = watchFaceDirFile;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel$downloadWatchFaceNotExists$2$onDownloadComplete$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WatchFaceInstallViewModel.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WatchFaceInstallViewModel.AnonymousClass2 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        String strSubstringAfter$default = StringsKt.substringAfter$default(str, "temp_", (String) null, 2, (Object) null);
                        com.blankj.utilcode.util.FileUtils.rename(FileUtils.INSTANCE.getWatchFaceDirFile().getAbsolutePath() + "/temp_" + str, strSubstringAfter$default);
                        WatchFace watchFaceQueryWatchFaceByName = watchFaceInstallViewModel.watchFaceDao.queryWatchFaceByName(strSubstringAfter$default, UserConfig.INSTANCE.getInstance().getHwVersion());
                        if (watchFaceQueryWatchFaceByName != null) {
                            watchFaceQueryWatchFaceByName.setLocalBinUrl(file.getAbsolutePath() + '/' + strSubstringAfter$default);
                            watchFaceInstallViewModel.watchFaceDao.insert(watchFaceQueryWatchFaceByName);
                            watchFaceInstallViewModel._fileDownloadSuccess.postValue(new ListenerBean(true, strSubstringAfter$default, 100));
                        }
                    }
                });
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError anError) {
                this._fileDownloadSuccess.postValue(new ListenerBean(false, StringsKt.substringAfter$default(name, "temp_", (String) null, 2, (Object) null), -1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadWatchFaceNotExists$lambda-0, reason: not valid java name */
    public static final void m1040downloadWatchFaceNotExists$lambda0(WatchFaceInstallViewModel this$0, long j, long j2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._fileDownloadSuccess.postValue(new ListenerBean(false, "", (int) ((j * 100) / j2)));
    }

    /* compiled from: WatchFaceInstallViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0016¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel;)V", "onComplete", "", "onDeletePlate", "onProgress", "percent", "", "onUpdatePlate", "array", "", "Lcom/oudmon/ble/base/communication/file/PlateEntity;", "onUpdatePlateError", "code", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlate(List<PlateEntity> array) {
            Intrinsics.checkNotNullParameter(array, "array");
        }

        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onProgress(int percent) {
            if (FileHandle.getInstance().getCurrFileType() == 1) {
                WatchFaceInstallViewModel.this._progressValue.postValue(new ProgressUI(percent, false, true, false, false));
            }
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onComplete() {
            if (FileHandle.getInstance().getCurrFileType() == 1) {
                WatchFaceInstallViewModel.this._progressValue.postValue(new ProgressUI(100, true, false, false, false));
            }
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlate() {
            super.onDeletePlate();
            WatchFaceInstallViewModel.this._progressValue.postValue(new ProgressUI(100, false, false, true, false));
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlateError(int code) {
            super.onUpdatePlateError(code);
            WatchFaceInstallViewModel.this._progressValue.postValue(new ProgressUI(100, false, false, false, true));
        }
    }

    /* compiled from: WatchFaceInstallViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel$ProgressUI;", "", "progress", "", ANConstants.SUCCESS, "", "isRunning", "isDelete", "error", "(IZZZZ)V", "getError", "()Z", "getProgress", "()I", "setProgress", "(I)V", "getSuccess", "component1", "component2", "component3", "component4", "component5", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ProgressUI {
        private final boolean error;
        private final boolean isDelete;
        private final boolean isRunning;
        private int progress;
        private final boolean success;

        public static /* synthetic */ ProgressUI copy$default(ProgressUI progressUI, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = progressUI.progress;
            }
            if ((i2 & 2) != 0) {
                z = progressUI.success;
            }
            boolean z5 = z;
            if ((i2 & 4) != 0) {
                z2 = progressUI.isRunning;
            }
            boolean z6 = z2;
            if ((i2 & 8) != 0) {
                z3 = progressUI.isDelete;
            }
            boolean z7 = z3;
            if ((i2 & 16) != 0) {
                z4 = progressUI.error;
            }
            return progressUI.copy(i, z5, z6, z7, z4);
        }

        /* renamed from: component1, reason: from getter */
        public final int getProgress() {
            return this.progress;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsRunning() {
            return this.isRunning;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsDelete() {
            return this.isDelete;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getError() {
            return this.error;
        }

        public final ProgressUI copy(int progress, boolean success, boolean isRunning, boolean isDelete, boolean error) {
            return new ProgressUI(progress, success, isRunning, isDelete, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProgressUI)) {
                return false;
            }
            ProgressUI progressUI = (ProgressUI) other;
            return this.progress == progressUI.progress && this.success == progressUI.success && this.isRunning == progressUI.isRunning && this.isDelete == progressUI.isDelete && this.error == progressUI.error;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int i = this.progress * 31;
            boolean z = this.success;
            int i2 = z;
            if (z != 0) {
                i2 = 1;
            }
            int i3 = (i + i2) * 31;
            boolean z2 = this.isRunning;
            int i4 = z2;
            if (z2 != 0) {
                i4 = 1;
            }
            int i5 = (i3 + i4) * 31;
            boolean z3 = this.isDelete;
            int i6 = z3;
            if (z3 != 0) {
                i6 = 1;
            }
            int i7 = (i5 + i6) * 31;
            boolean z4 = this.error;
            return i7 + (z4 ? 1 : z4 ? 1 : 0);
        }

        public String toString() {
            return "ProgressUI(progress=" + this.progress + ", success=" + this.success + ", isRunning=" + this.isRunning + ", isDelete=" + this.isDelete + ", error=" + this.error + ')';
        }

        public ProgressUI(int i, boolean z, boolean z2, boolean z3, boolean z4) {
            this.progress = i;
            this.success = z;
            this.isRunning = z2;
            this.isDelete = z3;
            this.error = z4;
        }

        public final int getProgress() {
            return this.progress;
        }

        public final void setProgress(int i) {
            this.progress = i;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final boolean isRunning() {
            return this.isRunning;
        }

        public final boolean isDelete() {
            return this.isDelete;
        }

        public final boolean getError() {
            return this.error;
        }
    }
}
