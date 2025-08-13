package com.qcwireless.qcwatch.ui.plate.wallpaper;

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
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.PlateEntity;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.GenerateOrderNumberResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceRanking;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.plate.bean.DeviceWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: WallpaperDetailActivityViewModel.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0002@AB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\bJ\u000e\u0010:\u001a\u0002072\u0006\u0010;\u001a\u00020\u0012J\u0016\u0010<\u001a\u0002072\u0006\u00109\u001a\u00020\b2\u0006\u00108\u001a\u00020\bJ\u000e\u0010=\u001a\u0002072\u0006\u00108\u001a\u00020\bJ\u0016\u0010>\u001a\u0002072\u0006\u00108\u001a\u00020\b2\u0006\u0010?\u001a\u00020\bR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00168F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u00168F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00168F¢\u0006\u0006\u001a\u0004\b \u0010\u0018R \u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120\u00168F¢\u0006\u0006\u001a\u0004\b.\u0010\u0018R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00140\u00168F¢\u0006\u0006\u001a\u0004\b0\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u00101\u001a\b\u0012\u0004\u0012\u00020\t0\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010%\"\u0004\b3\u0010'R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "_deviceWatchFaceList", "Landroidx/lifecycle/MutableLiveData;", "", "", "Lcom/oudmon/ble/base/communication/file/PlateEntity;", "_dialDetailUI", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel$DialDetailUI;", "_dialogRefresh", "", "_downloadCount", "_fileDownloadSuccess", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/ListenerBean;", "_marketUI", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchWallpaperFace;", "_progressValue", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel$ProgressUI;", "deviceWatchFaceList", "Landroidx/lifecycle/LiveData;", "getDeviceWatchFaceList", "()Landroidx/lifecycle/LiveData;", "dialDetailUI", "getDialDetailUI", "dialogRefresh", "getDialogRefresh", "downloadCount", "getDownloadCount", "fileDownloadSuccess", "getFileDownloadSuccess", "localWatchFace", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/DeviceWatchFaceBean;", "getLocalWatchFace", "()Ljava/util/List;", "setLocalWatchFace", "(Ljava/util/List;)V", "localWatchFaceCount", "getLocalWatchFaceCount", "()I", "setLocalWatchFaceCount", "(I)V", "marketUI", "getMarketUI", "progressValue", "getProgressValue", "watchList", "getWatchList", "setWatchList", "watchWallpaperDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchWallpaperDao;", "downloadCountUpdate", "", "name", "hdName", "downloadWatchWallpaperNotExists", "item", "getWatchfaceDownloadCount", "queryByName", "wallpaperToDevice", "path", "DialDetailUI", "ProgressUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WallpaperDetailActivityViewModel extends BaseViewModel {
    private final MutableLiveData<Map<String, PlateEntity>> _deviceWatchFaceList;
    private final MutableLiveData<DialDetailUI> _dialDetailUI;
    private final MutableLiveData<Integer> _dialogRefresh;
    private final MutableLiveData<Integer> _downloadCount;
    private final MutableLiveData<ListenerBean> _fileDownloadSuccess;
    private final MutableLiveData<WatchWallpaperFace> _marketUI;
    private final MutableLiveData<ProgressUI> _progressValue;
    private List<DeviceWatchFaceBean> localWatchFace;
    private int localWatchFaceCount;
    private final WatchFaceRepository watchFaceRepository;
    private List<PlateEntity> watchList;
    private final QcWatchWallpaperDao watchWallpaperDao;

    public WallpaperDetailActivityViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this._marketUI = new MutableLiveData<>();
        this._downloadCount = new MutableLiveData<>();
        this._dialDetailUI = new MutableLiveData<>();
        this._progressValue = new MutableLiveData<>();
        this._deviceWatchFaceList = new MutableLiveData<>();
        this.watchList = new ArrayList();
        this.localWatchFace = new ArrayList();
        this._dialogRefresh = new MutableLiveData<>();
        this.watchWallpaperDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchWallpaperDao();
        this._fileDownloadSuccess = new MutableLiveData<>();
    }

    public final LiveData<WatchWallpaperFace> getMarketUI() {
        return this._marketUI;
    }

    public final LiveData<Integer> getDownloadCount() {
        return this._downloadCount;
    }

    public final LiveData<DialDetailUI> getDialDetailUI() {
        return this._dialDetailUI;
    }

    public final LiveData<ProgressUI> getProgressValue() {
        return this._progressValue;
    }

    public final LiveData<Map<String, PlateEntity>> getDeviceWatchFaceList() {
        return this._deviceWatchFaceList;
    }

    public final List<PlateEntity> getWatchList() {
        return this.watchList;
    }

    public final void setWatchList(List<PlateEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.watchList = list;
    }

    public final List<DeviceWatchFaceBean> getLocalWatchFace() {
        return this.localWatchFace;
    }

    public final void setLocalWatchFace(List<DeviceWatchFaceBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.localWatchFace = list;
    }

    public final LiveData<Integer> getDialogRefresh() {
        return this._dialogRefresh;
    }

    public final LiveData<ListenerBean> getFileDownloadSuccess() {
        return this._fileDownloadSuccess;
    }

    public final int getLocalWatchFaceCount() {
        return this.localWatchFaceCount;
    }

    public final void setLocalWatchFaceCount(int i) {
        this.localWatchFaceCount = i;
    }

    public final void downloadWatchWallpaperNotExists(WatchWallpaperFace item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final File watchWallpaperDirFile = FileUtils.INSTANCE.getWatchWallpaperDirFile();
        final String wallpaperName = item.getWallpaperName();
        if (FileUtils.INSTANCE.fileExists(watchWallpaperDirFile.getAbsolutePath() + '/' + wallpaperName)) {
            FileUtils.INSTANCE.deleteFile(watchWallpaperDirFile.getAbsolutePath() + '/' + wallpaperName);
        }
        AndroidNetworking.download(item.getWallpaperFileUrl(), watchWallpaperDirFile.toString(), "temp_" + wallpaperName).setTag((Object) wallpaperName).setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel$$ExternalSyntheticLambda0
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public final void onProgress(long j, long j2) {
                WallpaperDetailActivityViewModel.m1069downloadWatchWallpaperNotExists$lambda0(j, j2);
            }
        }).startDownload(new DownloadListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel.downloadWatchWallpaperNotExists.2
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() {
                XLog.i("temp_" + wallpaperName);
                final String str = wallpaperName;
                final WallpaperDetailActivityViewModel wallpaperDetailActivityViewModel = this;
                final File file = watchWallpaperDirFile;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel$downloadWatchWallpaperNotExists$2$onDownloadComplete$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WallpaperDetailActivityViewModel.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WallpaperDetailActivityViewModel.AnonymousClass2 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        String strSubstringAfter$default = StringsKt.substringAfter$default(str, "temp_", (String) null, 2, (Object) null);
                        com.blankj.utilcode.util.FileUtils.rename(FileUtils.INSTANCE.getWatchWallpaperDirFile().getAbsolutePath() + "/temp_" + str, strSubstringAfter$default);
                        WatchWallpaperFace watchWallpaperFaceQueryWatchWallpaperByName = wallpaperDetailActivityViewModel.watchWallpaperDao.queryWatchWallpaperByName(strSubstringAfter$default, UserConfig.INSTANCE.getInstance().getHwVersion());
                        if (watchWallpaperFaceQueryWatchWallpaperByName != null) {
                            watchWallpaperFaceQueryWatchWallpaperByName.setLocalBinUrl(file.getAbsolutePath() + '/' + strSubstringAfter$default);
                            wallpaperDetailActivityViewModel.watchWallpaperDao.insert(watchWallpaperFaceQueryWatchWallpaperByName);
                            wallpaperDetailActivityViewModel._fileDownloadSuccess.postValue(new ListenerBean(true, strSubstringAfter$default, 0));
                        }
                    }
                });
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError anError) {
                this._fileDownloadSuccess.postValue(new ListenerBean(false, StringsKt.substringAfter$default(wallpaperName, "temp_", (String) null, 2, (Object) null), 0));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadWatchWallpaperNotExists$lambda-0, reason: not valid java name */
    public static final void m1069downloadWatchWallpaperNotExists$lambda0(long j, long j2) {
        XLog.i(Long.valueOf((j * 100) / j2));
    }

    public final void queryByName(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WallpaperDetailActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel.queryByName.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WallpaperDetailActivityViewModel wallpaperDetailActivityViewModel) {
                invoke2(wallpaperDetailActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WallpaperDetailActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                WatchWallpaperFace watchWallpaperFaceQueryWallpaperByName = ktxRunOnBgSingle.watchFaceRepository.queryWallpaperByName(name);
                if (watchWallpaperFaceQueryWallpaperByName != null) {
                    ktxRunOnBgSingle._marketUI.postValue(watchWallpaperFaceQueryWallpaperByName);
                }
            }
        });
    }

    /* compiled from: WallpaperDetailActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel$getWatchfaceDownloadCount$1", f = "WallpaperDetailActivityViewModel.kt", i = {}, l = {144, 144}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel$getWatchfaceDownloadCount$1, reason: invalid class name and case insensitive filesystem */
    static final class C06771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        final /* synthetic */ String $name;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06771(String str, String str2, Continuation<? super C06771> continuation) {
            super(2, continuation);
            this.$hdName = str;
            this.$name = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WallpaperDetailActivityViewModel.this.new C06771(this.$hdName, this.$name, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = WallpaperDetailActivityViewModel.this.watchFaceRepository.getWatchFaceDownloadCount(this.$hdName, this.$name, this);
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
            final WallpaperDetailActivityViewModel wallpaperDetailActivityViewModel = WallpaperDetailActivityViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel.getWatchfaceDownloadCount.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<Integer> watchFaceState, Continuation<? super Unit> continuation) {
                    MutableLiveData mutableLiveData = wallpaperDetailActivityViewModel._downloadCount;
                    Integer numIsSuccess = watchFaceState.isSuccess();
                    Intrinsics.checkNotNull(numIsSuccess);
                    mutableLiveData.postValue(numIsSuccess);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getWatchfaceDownloadCount(String hdName, String name) {
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        Intrinsics.checkNotNullParameter(name, "name");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C06771(hdName, name, null), 3, null);
    }

    /* compiled from: WallpaperDetailActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel$downloadCountUpdate$1", f = "WallpaperDetailActivityViewModel.kt", i = {}, l = {157, 157}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel$downloadCountUpdate$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        final /* synthetic */ String $name;
        int label;
        final /* synthetic */ WallpaperDetailActivityViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2, WallpaperDetailActivityViewModel wallpaperDetailActivityViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$name = str;
            this.$hdName = str2;
            this.this$0 = wallpaperDetailActivityViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$name, this.$hdName, this.this$0, continuation);
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
                WatchFaceRanking watchFaceRanking = new WatchFaceRanking(this.$name, this.$hdName);
                this.label = 1;
                obj = this.this$0.watchFaceRepository.updateDownloadCount(watchFaceRanking, this);
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
            final WallpaperDetailActivityViewModel wallpaperDetailActivityViewModel = this.this$0;
            final String str = this.$hdName;
            final String str2 = this.$name;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel.downloadCountUpdate.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<Integer> watchFaceState, Continuation<? super Unit> continuation) {
                    wallpaperDetailActivityViewModel.getWatchfaceDownloadCount(str, str2);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void downloadCountUpdate(String name, String hdName) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(name, hdName, this, null), 3, null);
    }

    public final void wallpaperToDevice(String name, String path) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        EbookHandle.getInstance().setCurrFileType(EbookHandle.FileTypeWALLPAPER);
        XLog.i(name);
        if (EbookHandle.getInstance().executeFilePrepare(path)) {
            EbookHandle.getInstance().cmdFileInit(name);
        } else {
            XLog.i("文件不存在");
        }
    }

    /* compiled from: WallpaperDetailActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel$ProgressUI;", "", "progress", "", ANConstants.SUCCESS, "", "isRunning", "isDelete", "error", "(IZZZZ)V", "getError", "()Z", "getProgress", "()I", "setProgress", "(I)V", "getSuccess", "component1", "component2", "component3", "component4", "component5", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    /* compiled from: WallpaperDetailActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel$DialDetailUI;", "", "()V", "generateResp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/GenerateOrderNumberResp;", "getGenerateResp", "()Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/GenerateOrderNumberResp;", "setGenerateResp", "(Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/GenerateOrderNumberResp;)V", "payStatus", "", "getPayStatus", "()I", "setPayStatus", "(I)V", "payUpdate", "getPayUpdate", "setPayUpdate", "type", "getType", "setType", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DialDetailUI {
        private GenerateOrderNumberResp generateResp;
        private int type;
        private int payUpdate = -1;
        private int payStatus = -1;

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final GenerateOrderNumberResp getGenerateResp() {
            return this.generateResp;
        }

        public final void setGenerateResp(GenerateOrderNumberResp generateOrderNumberResp) {
            this.generateResp = generateOrderNumberResp;
        }

        public final int getPayUpdate() {
            return this.payUpdate;
        }

        public final void setPayUpdate(int i) {
            this.payUpdate = i;
        }

        public final int getPayStatus() {
            return this.payStatus;
        }

        public final void setPayStatus(int i) {
            this.payStatus = i;
        }
    }
}
