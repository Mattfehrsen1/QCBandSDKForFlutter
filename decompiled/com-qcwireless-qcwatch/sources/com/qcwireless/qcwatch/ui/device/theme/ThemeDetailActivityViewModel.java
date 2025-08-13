package com.qcwireless.qcwatch.ui.device.theme;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceRanking;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchThemeFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel;
import java.io.File;
import java.io.UnsupportedEncodingException;
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

/* compiled from: ThemeDetailActivityViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u000bJ\u0016\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivityViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "_downloadCount", "Landroidx/lifecycle/MutableLiveData;", "", "_fileDownloadSuccess", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/ListenerBean;", "_marketUI", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchThemeFace;", "downloadCount", "Landroidx/lifecycle/LiveData;", "getDownloadCount", "()Landroidx/lifecycle/LiveData;", "fileDownloadSuccess", "getFileDownloadSuccess", "marketUI", "getMarketUI", "qcWatchThemeDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchThemeDao;", "downloadCountUpdate", "", "name", "", "hdName", "downloadWatchThemeNotExists", "item", "getWatchfaceDownloadCount", "queryByName", "themeToDevice", "path", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThemeDetailActivityViewModel extends BaseViewModel {
    private final MutableLiveData<Integer> _downloadCount;
    private final MutableLiveData<ListenerBean> _fileDownloadSuccess;
    private final MutableLiveData<WatchThemeFace> _marketUI;
    private final QcWatchThemeDao qcWatchThemeDao;
    private final WatchFaceRepository watchFaceRepository;

    public ThemeDetailActivityViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this._marketUI = new MutableLiveData<>();
        this._fileDownloadSuccess = new MutableLiveData<>();
        this._downloadCount = new MutableLiveData<>();
        this.qcWatchThemeDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchThemeDao();
    }

    public final LiveData<WatchThemeFace> getMarketUI() {
        return this._marketUI;
    }

    public final LiveData<ListenerBean> getFileDownloadSuccess() {
        return this._fileDownloadSuccess;
    }

    public final LiveData<Integer> getDownloadCount() {
        return this._downloadCount;
    }

    /* compiled from: ThemeDetailActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel$getWatchfaceDownloadCount$1", f = "ThemeDetailActivityViewModel.kt", i = {}, l = {59, 59}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel$getWatchfaceDownloadCount$1, reason: invalid class name and case insensitive filesystem */
    static final class C05561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        final /* synthetic */ String $name;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05561(String str, String str2, Continuation<? super C05561> continuation) {
            super(2, continuation);
            this.$hdName = str;
            this.$name = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ThemeDetailActivityViewModel.this.new C05561(this.$hdName, this.$name, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = ThemeDetailActivityViewModel.this.watchFaceRepository.getWatchFaceDownloadCount(this.$hdName, this.$name, this);
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
            final ThemeDetailActivityViewModel themeDetailActivityViewModel = ThemeDetailActivityViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel.getWatchfaceDownloadCount.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<Integer> watchFaceState, Continuation<? super Unit> continuation) {
                    MutableLiveData mutableLiveData = themeDetailActivityViewModel._downloadCount;
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
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05561(hdName, name, null), 3, null);
    }

    /* compiled from: ThemeDetailActivityViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel$downloadCountUpdate$1", f = "ThemeDetailActivityViewModel.kt", i = {}, l = {68, 68}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel$downloadCountUpdate$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        final /* synthetic */ String $name;
        int label;
        final /* synthetic */ ThemeDetailActivityViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2, ThemeDetailActivityViewModel themeDetailActivityViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$name = str;
            this.$hdName = str2;
            this.this$0 = themeDetailActivityViewModel;
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
            final ThemeDetailActivityViewModel themeDetailActivityViewModel = this.this$0;
            final String str = this.$hdName;
            final String str2 = this.$name;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel.downloadCountUpdate.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<Integer> watchFaceState, Continuation<? super Unit> continuation) {
                    themeDetailActivityViewModel.getWatchfaceDownloadCount(str, str2);
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

    public final void queryByName(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<ThemeDetailActivityViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel.queryByName.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ThemeDetailActivityViewModel themeDetailActivityViewModel) {
                invoke2(themeDetailActivityViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ThemeDetailActivityViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._marketUI.postValue(ktxRunOnBgSingle.qcWatchThemeDao.queryWatchThemeByName(name, UserConfig.INSTANCE.getInstance().getHwVersion()));
            }
        });
    }

    public final void themeToDevice(String name, String path) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        EbookHandle.getInstance().setCurrFileType(EbookHandle.FileTypeTHEME);
        XLog.i(name);
        if (EbookHandle.getInstance().executeFilePrepare(path)) {
            EbookHandle.getInstance().cmdFileInit(name);
        } else {
            XLog.i("文件不存在");
        }
    }

    public final void downloadWatchThemeNotExists(WatchThemeFace item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final File watchThemeDirFile = FileUtils.INSTANCE.getWatchThemeDirFile();
        final String themeName = item.getThemeName();
        if (FileUtils.INSTANCE.fileExists(watchThemeDirFile.getAbsolutePath() + '/' + themeName)) {
            FileUtils.INSTANCE.deleteFile(watchThemeDirFile.getAbsolutePath() + '/' + themeName);
        }
        AndroidNetworking.download(item.getBinUrl(), watchThemeDirFile.toString(), "temp_" + themeName).setTag((Object) themeName).setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel$$ExternalSyntheticLambda0
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public final void onProgress(long j, long j2) {
                ThemeDetailActivityViewModel.m599downloadWatchThemeNotExists$lambda0(j, j2);
            }
        }).startDownload(new DownloadListener() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel.downloadWatchThemeNotExists.2
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() throws InterruptedException {
                XLog.i("temp_" + themeName);
                final String str = themeName;
                final ThemeDetailActivityViewModel themeDetailActivityViewModel = this;
                final File file = watchThemeDirFile;
                ThreadExtKt.ktxRunOnBgSingleAnother(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel$downloadWatchThemeNotExists$2$onDownloadComplete$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ThemeDetailActivityViewModel.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ThemeDetailActivityViewModel.AnonymousClass2 ktxRunOnBgSingleAnother) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingleAnother, "$this$ktxRunOnBgSingleAnother");
                        String strSubstringAfter$default = StringsKt.substringAfter$default(str, "temp_", (String) null, 2, (Object) null);
                        com.blankj.utilcode.util.FileUtils.rename(FileUtils.INSTANCE.getWatchThemeDirFile().getAbsolutePath() + "/temp_" + str, strSubstringAfter$default);
                        WatchThemeFace watchThemeFaceQueryWatchThemeByName = themeDetailActivityViewModel.qcWatchThemeDao.queryWatchThemeByName(strSubstringAfter$default, UserConfig.INSTANCE.getInstance().getHwVersion());
                        if (watchThemeFaceQueryWatchThemeByName != null) {
                            watchThemeFaceQueryWatchThemeByName.setLocalBinUrl(file.getAbsolutePath() + '/' + strSubstringAfter$default);
                            themeDetailActivityViewModel.qcWatchThemeDao.insert(watchThemeFaceQueryWatchThemeByName);
                            themeDetailActivityViewModel._fileDownloadSuccess.postValue(new ListenerBean(true, strSubstringAfter$default, 0));
                        }
                    }
                });
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError anError) {
                this._fileDownloadSuccess.postValue(new ListenerBean(false, StringsKt.substringAfter$default(themeName, "temp_", (String) null, 2, (Object) null), 0));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadWatchThemeNotExists$lambda-0, reason: not valid java name */
    public static final void m599downloadWatchThemeNotExists$lambda0(long j, long j2) {
        XLog.i(Long.valueOf((j * 100) / j2));
    }
}
