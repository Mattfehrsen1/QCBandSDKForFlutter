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
import com.qcwireless.qcwatch.ui.base.bean.response.device.CustomDialResp;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.plate.bean.DeviceWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.bean.MarketWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: WatchMarketFragmentViewModel.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001:\u0003ABCB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00100\u001a\u000201J\u0010\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u0015H\u0002J\u000e\u00104\u001a\u0002012\u0006\u00105\u001a\u00020\u0015J\u000e\u00106\u001a\u0002012\u0006\u00107\u001a\u00020\u001bJ\u000e\u00108\u001a\u0002012\u0006\u00107\u001a\u00020\u001bJ\u000e\u00109\u001a\u0002012\u0006\u00107\u001a\u00020\u001bJ\u0016\u0010:\u001a\u0002012\u0006\u00105\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u0015J\u000e\u0010<\u001a\u0002012\u0006\u00105\u001a\u00020\u0015J\u0006\u0010=\u001a\u000201J\u0006\u0010>\u001a\u000201J\u0006\u0010?\u001a\u000201J\u000e\u0010@\u001a\u0002012\u0006\u00105\u001a\u00020\u0015R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00060\u000eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\u00108F¢\u0006\u0006\u001a\u0004\b!\u0010\u0012R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108F¢\u0006\u0006\u001a\u0004\b#\u0010\u0012R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\u00108F¢\u0006\u0006\u001a\u0004\b)\u0010\u0012R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00160\u001a¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00160\u001a¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001d¨\u0006D"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "_fileDownloadSuccess", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/ListenerBean;", "_marketState", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel$WatchMarketUI;", "_progressValue", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel$ProgressUI;", "_uiState", "callback", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel$Callback;", "fileDownloadSuccess", "Landroidx/lifecycle/LiveData;", "getFileDownloadSuccess", "()Landroidx/lifecycle/LiveData;", "localWatchFace", "", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/DeviceWatchFaceBean;", "getLocalWatchFace", "()Ljava/util/Map;", "marketList", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/MarketWatchFaceBean;", "getMarketList", "()Ljava/util/List;", "marketListBackup", "getMarketListBackup", "marketState", "getMarketState", "progressValue", "getProgressValue", "storeList", "getStoreList", "storeListFromServer", "getStoreListFromServer", "uiState", "getUiState", "watchFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchFaceDao;", "watchList", "getWatchList", "watchListBackUp", "getWatchListBackUp", "checkCustomizeWatchFace", "", "customDeviceWatchFace", "hwVersion", "deleteWatchFaceToDevice", "name", "downloadWatchFaceImageFileNotExists", "item", "downloadWatchFaceNotExists", "downloadWatchFaceNotExistsNotRepeat", "execWatchFaceToDevice", "binPath", "execWatchFaceToDeviceByName", "getMarketWatchFace", "getWatchFaceFromDevice", "initCallback", "queryByName", "Callback", "ProgressUI", "WatchMarketUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchMarketFragmentViewModel extends BaseViewModel {
    private final MutableLiveData<ListenerBean> _fileDownloadSuccess;
    private final MutableLiveData<WatchMarketUI> _marketState;
    private final MutableLiveData<ProgressUI> _progressValue;
    private final MutableLiveData<WatchMarketUI> _uiState;
    private final Callback callback;
    private final Map<String, DeviceWatchFaceBean> localWatchFace;
    private final List<MarketWatchFaceBean> marketList;
    private final List<MarketWatchFaceBean> marketListBackup;
    private final List<MarketWatchFaceBean> storeList;
    private final List<MarketWatchFaceBean> storeListFromServer;
    private final QcWatchFaceDao watchFaceDao;
    private final WatchFaceRepository watchFaceRepository;
    private final List<DeviceWatchFaceBean> watchList;
    private final List<DeviceWatchFaceBean> watchListBackUp;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadWatchFaceNotExists$lambda-0, reason: not valid java name */
    public static final void m1058downloadWatchFaceNotExists$lambda0(long j, long j2) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadWatchFaceNotExistsNotRepeat$lambda-1, reason: not valid java name */
    public static final void m1059downloadWatchFaceNotExistsNotRepeat$lambda1(long j, long j2) {
    }

    public WatchMarketFragmentViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this.watchFaceDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchFaceDao();
        this.marketList = new ArrayList();
        this.marketListBackup = new ArrayList();
        this.storeList = new ArrayList();
        this.storeListFromServer = new ArrayList();
        this.watchList = new ArrayList();
        this.watchListBackUp = new ArrayList();
        this.localWatchFace = new LinkedHashMap();
        this._uiState = new MutableLiveData<>();
        this.callback = new Callback();
        this._progressValue = new MutableLiveData<>();
        this._marketState = new MutableLiveData<>();
        this._fileDownloadSuccess = new MutableLiveData<>();
    }

    public final List<MarketWatchFaceBean> getMarketList() {
        return this.marketList;
    }

    public final List<MarketWatchFaceBean> getMarketListBackup() {
        return this.marketListBackup;
    }

    public final List<MarketWatchFaceBean> getStoreList() {
        return this.storeList;
    }

    public final List<MarketWatchFaceBean> getStoreListFromServer() {
        return this.storeListFromServer;
    }

    public final List<DeviceWatchFaceBean> getWatchList() {
        return this.watchList;
    }

    public final List<DeviceWatchFaceBean> getWatchListBackUp() {
        return this.watchListBackUp;
    }

    public final Map<String, DeviceWatchFaceBean> getLocalWatchFace() {
        return this.localWatchFace;
    }

    public final LiveData<WatchMarketUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<ProgressUI> getProgressValue() {
        return this._progressValue;
    }

    public final LiveData<WatchMarketUI> getMarketState() {
        return this._marketState;
    }

    public final LiveData<ListenerBean> getFileDownloadSuccess() {
        return this._fileDownloadSuccess;
    }

    public final void getMarketWatchFace() throws InterruptedException {
        ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<WatchMarketFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel.getMarketWatchFace.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WatchMarketFragmentViewModel watchMarketFragmentViewModel) {
                invoke2(watchMarketFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WatchMarketFragmentViewModel ktxRunOnBgSingleAnother) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                List<WatchFace> listQueryWatchFaceListByVersion = ktxRunOnBgSingleAnother.watchFaceRepository.queryWatchFaceListByVersion();
                if (listQueryWatchFaceListByVersion != null) {
                    ArrayList arrayList3 = new ArrayList();
                    for (WatchFace watchFace : listQueryWatchFaceListByVersion) {
                        if (ktxRunOnBgSingleAnother.getLocalWatchFace().get(watchFace.getName()) == null) {
                            arrayList3.add(new MarketWatchFaceBean(watchFace, 0));
                        }
                    }
                    arrayList.addAll(arrayList3);
                }
                ktxRunOnBgSingleAnother._marketState.postValue(new WatchMarketUI(arrayList, arrayList2));
            }
        });
    }

    public final void downloadWatchFaceImageFileNotExists(MarketWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.watchFaceRepository.downloadWatchFaceImageFileNotExists(item);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    public final void downloadWatchFaceNotExists(MarketWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final File watchFaceDirFile = FileUtils.INSTANCE.getWatchFaceDirFile();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = item.getWatchFace().getName();
        if (FileUtils.INSTANCE.fileExists(watchFaceDirFile.getAbsolutePath() + '/' + ((String) objectRef.element))) {
            FileUtils.INSTANCE.deleteFile(watchFaceDirFile.getAbsolutePath() + '/' + ((String) objectRef.element));
        }
        AndroidNetworking.download(item.getWatchFace().getBinUrl(), watchFaceDirFile.toString(), "temp_" + ((String) objectRef.element)).setTag(objectRef.element).setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$$ExternalSyntheticLambda1
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public final void onProgress(long j, long j2) {
                WatchMarketFragmentViewModel.m1058downloadWatchFaceNotExists$lambda0(j, j2);
            }
        }).startDownload(new DownloadListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel.downloadWatchFaceNotExists.2
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() {
                XLog.i("temp_" + objectRef.element);
                final Ref.ObjectRef<String> objectRef2 = objectRef;
                final WatchMarketFragmentViewModel watchMarketFragmentViewModel = this;
                final File file = watchFaceDirFile;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$downloadWatchFaceNotExists$2$onDownloadComplete$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WatchMarketFragmentViewModel.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WatchMarketFragmentViewModel.AnonymousClass2 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        String strSubstringAfter$default = StringsKt.substringAfter$default(objectRef2.element, "temp_", (String) null, 2, (Object) null);
                        com.blankj.utilcode.util.FileUtils.rename(FileUtils.INSTANCE.getWatchFaceDirFile().getAbsolutePath() + "/temp_" + objectRef2.element, strSubstringAfter$default);
                        WatchFace watchFaceQueryWatchFaceByName = watchMarketFragmentViewModel.watchFaceDao.queryWatchFaceByName(strSubstringAfter$default, UserConfig.INSTANCE.getInstance().getHwVersion());
                        if (watchFaceQueryWatchFaceByName != null) {
                            watchFaceQueryWatchFaceByName.setLocalBinUrl(file.getAbsolutePath() + '/' + strSubstringAfter$default);
                            watchMarketFragmentViewModel.watchFaceDao.insert(watchFaceQueryWatchFaceByName);
                            watchMarketFragmentViewModel._fileDownloadSuccess.postValue(new ListenerBean(true, strSubstringAfter$default, 100));
                        }
                    }
                });
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError anError) {
                this._fileDownloadSuccess.postValue(new ListenerBean(false, StringsKt.substringAfter$default(objectRef.element, "temp_", (String) null, 2, (Object) null), -1));
            }
        });
    }

    public final void downloadWatchFaceNotExistsNotRepeat(MarketWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(item, "item");
        File watchFaceDirFile = FileUtils.INSTANCE.getWatchFaceDirFile();
        final String name = item.getWatchFace().getName();
        if (FileUtils.INSTANCE.fileExists(watchFaceDirFile.getAbsolutePath() + '/' + name)) {
            FileUtils.INSTANCE.deleteFile(watchFaceDirFile.getAbsolutePath() + '/' + name);
        }
        AndroidNetworking.download(item.getWatchFace().getBinUrl(), watchFaceDirFile.toString(), name).setTag((Object) name).setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$$ExternalSyntheticLambda0
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public final void onProgress(long j, long j2) {
                WatchMarketFragmentViewModel.m1059downloadWatchFaceNotExistsNotRepeat$lambda1(j, j2);
            }
        }).startDownload(new DownloadListener() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel.downloadWatchFaceNotExistsNotRepeat.2
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError anError) {
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() {
                XLog.i(String.valueOf(name));
            }
        });
    }

    /* compiled from: WatchMarketFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$checkCustomizeWatchFace$1", f = "WatchMarketFragmentViewModel.kt", i = {}, l = {165, 166}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$checkCustomizeWatchFace$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WatchMarketFragmentViewModel.this.new AnonymousClass1(continuation);
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
                obj = WatchMarketFragmentViewModel.this.watchFaceRepository.getCustomizeParamsFromLocalByType(this);
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
            final WatchMarketFragmentViewModel watchMarketFragmentViewModel = WatchMarketFragmentViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel.checkCustomizeWatchFace.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((CustomFaceEntity) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(CustomFaceEntity customFaceEntity, Continuation<? super Unit> continuation) {
                    XLog.i(customFaceEntity);
                    if (customFaceEntity == null) {
                        XLog.i("---customDeviceWatchFace");
                        watchMarketFragmentViewModel.customDeviceWatchFace(UserConfig.INSTANCE.getInstance().getHwVersion());
                    } else {
                        XLog.i("---自定义已经下载");
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkCustomizeWatchFace() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: WatchMarketFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$customDeviceWatchFace$1", f = "WatchMarketFragmentViewModel.kt", i = {}, l = {191, 191}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$customDeviceWatchFace$1, reason: invalid class name and case insensitive filesystem */
    static final class C06721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hwVersion;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06721(String str, Continuation<? super C06721> continuation) {
            super(2, continuation);
            this.$hwVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WatchMarketFragmentViewModel.this.new C06721(this.$hwVersion, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final WatchMarketFragmentViewModel watchMarketFragmentViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceRepository watchFaceRepository = WatchMarketFragmentViewModel.this.watchFaceRepository;
                String str = this.$hwVersion;
                WatchMarketFragmentViewModel watchMarketFragmentViewModel2 = WatchMarketFragmentViewModel.this;
                this.L$0 = watchMarketFragmentViewModel2;
                this.label = 1;
                obj = watchFaceRepository.getCustomWatchFaceParams(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                watchMarketFragmentViewModel = watchMarketFragmentViewModel2;
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                watchMarketFragmentViewModel = (WatchMarketFragmentViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$customDeviceWatchFace$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<CustomDialResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<CustomDialResp> watchFaceState, Continuation<? super Unit> continuation) {
                    CustomDialResp customDialRespIsSuccess;
                    List<CustomDialResp.ElementUI> elements;
                    if (watchFaceState.isSuccess() != null && (elements = (customDialRespIsSuccess = watchFaceState.isSuccess()).getElements()) != null) {
                        XLog.i("---customDeviceWatchFace");
                        watchMarketFragmentViewModel.watchFaceRepository.downloadCustomImage(watchFaceState.isSuccess().getBgImage(), elements, customDialRespIsSuccess.getWidth(), customDialRespIsSuccess.getHeight());
                    }
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void customDeviceWatchFace(String hwVersion) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06721(hwVersion, null), 3, null);
    }

    public final void initCallback() {
        FileHandle.getInstance().registerCallback(this.callback);
    }

    public final void getWatchFaceFromDevice() throws InterruptedException {
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().initRegister();
        FileHandle.getInstance().startObtainPlate();
    }

    public final void deleteWatchFaceToDevice(String name) throws InterruptedException {
        Intrinsics.checkNotNullParameter(name, "name");
        FileHandle.getInstance().setCurrFileType(1);
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().initRegister();
        FileHandle.getInstance().executeFileDelete(name);
    }

    public final void execWatchFaceToDeviceByName(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WatchMarketFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel.execWatchFaceToDeviceByName.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WatchMarketFragmentViewModel watchMarketFragmentViewModel) {
                invoke2(watchMarketFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WatchMarketFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                WatchFace watchFaceQueryByName = ktxRunOnBgSingle.watchFaceRepository.queryByName(name);
                if (watchFaceQueryByName != null) {
                    ktxRunOnBgSingle.execWatchFaceToDevice(watchFaceQueryByName.getName(), watchFaceQueryByName.getLocalBinUrl());
                }
            }
        });
    }

    public final void queryByName(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WatchMarketFragmentViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel.queryByName.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WatchMarketFragmentViewModel watchMarketFragmentViewModel) {
                invoke2(watchMarketFragmentViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WatchMarketFragmentViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                WatchFace watchFaceQueryByName = ktxRunOnBgSingle.watchFaceRepository.queryByName(name);
                if (watchFaceQueryByName != null) {
                    watchFaceQueryByName.setLocalBinUrl("");
                    FileUtils.INSTANCE.deleteFile(FileUtils.INSTANCE.getWatchFaceDirFile().getAbsolutePath() + '/' + watchFaceQueryByName.getName());
                    ktxRunOnBgSingle.watchFaceRepository.updateWatchFaceBean(watchFaceQueryByName);
                }
            }
        });
    }

    public final void execWatchFaceToDevice(String name, String binPath) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(binPath, "binPath");
        FileHandle.getInstance().setCurrFileType(1);
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().initRegister();
        if (FileHandle.getInstance().executeFilePrepare(binPath)) {
            FileHandle.getInstance().executeFileInit(name, 54);
        } else {
            this._progressValue.postValue(new ProgressUI(100, false, false, false, true));
        }
    }

    /* compiled from: WatchMarketFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007R*\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\bj\b\u0012\u0004\u0012\u00020\u0004`\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00060\bj\b\u0012\u0004\u0012\u00020\u0006`\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel$WatchMarketUI;", "", "marketList", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/MarketWatchFaceBean;", "watchList", "Lcom/qcwireless/qcwatch/ui/plate/bean/DeviceWatchFaceBean;", "(Ljava/util/List;Ljava/util/List;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMarketList", "()Ljava/util/ArrayList;", "setMarketList", "(Ljava/util/ArrayList;)V", "getWatchList", "setWatchList", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class WatchMarketUI {
        private ArrayList<MarketWatchFaceBean> marketList;
        private ArrayList<DeviceWatchFaceBean> watchList;

        public WatchMarketUI(List<MarketWatchFaceBean> marketList, List<DeviceWatchFaceBean> watchList) {
            Intrinsics.checkNotNullParameter(marketList, "marketList");
            Intrinsics.checkNotNullParameter(watchList, "watchList");
            this.marketList = new ArrayList<>();
            this.watchList = new ArrayList<>();
            this.marketList = (ArrayList) marketList;
            this.watchList = (ArrayList) watchList;
        }

        public final ArrayList<MarketWatchFaceBean> getMarketList() {
            return this.marketList;
        }

        public final void setMarketList(ArrayList<MarketWatchFaceBean> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.marketList = arrayList;
        }

        public final ArrayList<DeviceWatchFaceBean> getWatchList() {
            return this.watchList;
        }

        public final void setWatchList(ArrayList<DeviceWatchFaceBean> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.watchList = arrayList;
        }
    }

    /* compiled from: WatchMarketFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel$ProgressUI;", "", "progress", "", ANConstants.SUCCESS, "", "isRunning", "isDelete", "error", "(IZZZZ)V", "getError", "()Z", "getProgress", "()I", "setProgress", "(I)V", "getSuccess", "component1", "component2", "component3", "component4", "component5", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    /* compiled from: WatchMarketFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\u00042\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/plate/market/WatchMarketFragmentViewModel;)V", "onComplete", "", "onDeletePlate", "onDeletePlateError", "code", "", "onProgress", "percent", "onUpdatePlate", "array", "", "Lcom/oudmon/ble/base/communication/file/PlateEntity;", "onUpdatePlateError", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlate(final List<PlateEntity> array) {
            final WatchMarketFragmentViewModel watchMarketFragmentViewModel = WatchMarketFragmentViewModel.this;
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<Callback, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel$Callback$onUpdatePlate$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(WatchMarketFragmentViewModel.Callback callback) {
                    invoke2(callback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(WatchMarketFragmentViewModel.Callback ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    List<WatchFace> listQueryWatchFaceListByVersion = watchMarketFragmentViewModel.watchFaceRepository.queryWatchFaceListByVersion();
                    if (listQueryWatchFaceListByVersion != null) {
                        ArrayList arrayList3 = new ArrayList();
                        ArrayList arrayList4 = new ArrayList();
                        for (WatchFace watchFace : listQueryWatchFaceListByVersion) {
                            if (watchFace.getLocalBinUrl().length() > 0) {
                                if (watchFace.getLocalImageUrl().length() > 0) {
                                    arrayList4.add(new MarketWatchFaceBean(watchFace, 0));
                                }
                            }
                            arrayList3.add(new MarketWatchFaceBean(watchFace, 0));
                        }
                        arrayList.addAll(arrayList3);
                    }
                    if (array != null) {
                        arrayList2.clear();
                        watchMarketFragmentViewModel.getLocalWatchFace().clear();
                        for (PlateEntity plateEntity : array) {
                            WatchFaceRepository watchFaceRepository = watchMarketFragmentViewModel.watchFaceRepository;
                            String str = plateEntity.mPlateName;
                            Intrinsics.checkNotNullExpressionValue(str, "item.mPlateName");
                            WatchFace watchFaceQueryByName = watchFaceRepository.queryByName(str);
                            if (watchFaceQueryByName != null) {
                                DeviceWatchFaceBean deviceWatchFaceBean = new DeviceWatchFaceBean(watchFaceQueryByName, plateEntity.mDelete);
                                arrayList2.add(deviceWatchFaceBean);
                                Map<String, DeviceWatchFaceBean> localWatchFace = watchMarketFragmentViewModel.getLocalWatchFace();
                                String str2 = plateEntity.mPlateName;
                                Intrinsics.checkNotNullExpressionValue(str2, "item.mPlateName");
                                localWatchFace.put(str2, deviceWatchFaceBean);
                            } else {
                                String str3 = plateEntity.mPlateName;
                                Intrinsics.checkNotNullExpressionValue(str3, "item.mPlateName");
                                DeviceWatchFaceBean deviceWatchFaceBean2 = new DeviceWatchFaceBean(new WatchFace(str3, "", "", "", 2.0f, 1, "ui", "", "", (int) UserConfig.INSTANCE.getInstance().getWatchFaceMarketVersion(), 1), plateEntity.mDelete);
                                arrayList2.add(deviceWatchFaceBean2);
                                Map<String, DeviceWatchFaceBean> localWatchFace2 = watchMarketFragmentViewModel.getLocalWatchFace();
                                String str4 = plateEntity.mPlateName;
                                Intrinsics.checkNotNullExpressionValue(str4, "item.mPlateName");
                                localWatchFace2.put(str4, deviceWatchFaceBean2);
                            }
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            MarketWatchFaceBean marketWatchFaceBean = (MarketWatchFaceBean) it.next();
                            Iterator it2 = arrayList2.iterator();
                            while (it2.hasNext()) {
                                if (Intrinsics.areEqual(marketWatchFaceBean.getWatchFace().getName(), ((DeviceWatchFaceBean) it2.next()).getWatchFace().getName())) {
                                    it.remove();
                                }
                            }
                        }
                        watchMarketFragmentViewModel._uiState.postValue(new WatchMarketFragmentViewModel.WatchMarketUI(arrayList, arrayList2));
                    }
                }
            });
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onProgress(int percent) {
            if (FileHandle.getInstance().getCurrFileType() == 1) {
                WatchMarketFragmentViewModel.this._progressValue.postValue(new ProgressUI(percent, false, true, false, false));
            }
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onComplete() {
            if (FileHandle.getInstance().getCurrFileType() == 1) {
                WatchMarketFragmentViewModel.this._progressValue.postValue(new ProgressUI(100, true, false, false, false));
            }
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlate() {
            WatchMarketFragmentViewModel.this._progressValue.postValue(new ProgressUI(100, false, false, true, false));
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlateError(int code) {
            WatchMarketFragmentViewModel.this._progressValue.postValue(new ProgressUI(code, false, false, false, true));
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlateError(int code) {
            WatchMarketFragmentViewModel.this._progressValue.postValue(new ProgressUI(code, false, false, false, true));
        }
    }
}
