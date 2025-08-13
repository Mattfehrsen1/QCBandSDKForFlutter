package com.qcwireless.qcwatch.ui.plate.wallpaper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.communication.file.PlateEntity;
import com.oudmon.ble.base.communication.file.SimpleCallback;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchWallpaperResp;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.plate.bean.DialBean;
import com.qcwireless.qcwatch.ui.plate.bean.DialItemBean;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
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
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: WallpaperMarketViewModel.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "_marketUI", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/DialItemBean;", "marketList", "getMarketList", "()Ljava/util/List;", "marketUI", "Landroidx/lifecycle/LiveData;", "getMarketUI", "()Landroidx/lifecycle/LiveData;", "doRefresh", "", "getIndexApiUpdate", "getMarketWatchWallpaper", "queryWatchWallpaperByType", "hdName", "", "typeId", "", "watchFaceTryAgain", "Callback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WallpaperMarketViewModel extends BaseViewModel {
    private final MutableLiveData<List<DialItemBean>> _marketUI;
    private final List<DialItemBean> marketList;
    private final WatchFaceRepository watchFaceRepository;

    public WallpaperMarketViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this.marketList = new ArrayList();
        this._marketUI = new MutableLiveData<>();
    }

    public final List<DialItemBean> getMarketList() {
        return this.marketList;
    }

    public final LiveData<List<DialItemBean>> getMarketUI() {
        return this._marketUI;
    }

    /* compiled from: WallpaperMarketViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$getIndexApiUpdate$1", f = "WallpaperMarketViewModel.kt", i = {}, l = {40, 41}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$getIndexApiUpdate$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WallpaperMarketViewModel.this.new AnonymousClass1(continuation);
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
                XLog.i(new DateUtil(UserConfig.INSTANCE.getInstance().getIndexApiUpdateTime(), true).getY_M_D_H_M_S());
                if (UserConfig.INSTANCE.getInstance().getWallpaperApiUpdateTime() < new DateUtil().getUnixTimestamp()) {
                    this.label = 1;
                    obj = WallpaperMarketViewModel.this.watchFaceRepository.getWallpaperList(UserConfig.INSTANCE.getInstance().getHwVersion(), this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    XLog.i("走本地表盘UI");
                    WallpaperMarketViewModel.this.doRefresh();
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        XLog.i("走服务器表盘UI");
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final WallpaperMarketViewModel wallpaperMarketViewModel = WallpaperMarketViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel.getIndexApiUpdate.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<List<WatchWallpaperResp>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<List<WatchWallpaperResp>> watchFaceState, Continuation<? super Unit> continuation) {
                    watchFaceState.isSuccess();
                    wallpaperMarketViewModel.doRefresh();
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            XLog.i("走服务器表盘UI");
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getIndexApiUpdate() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: WallpaperMarketViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$watchFaceTryAgain$1", f = "WallpaperMarketViewModel.kt", i = {}, l = {59, 60}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$watchFaceTryAgain$1, reason: invalid class name and case insensitive filesystem */
    static final class C06811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06811(Continuation<? super C06811> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WallpaperMarketViewModel.this.new C06811(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = WallpaperMarketViewModel.this.watchFaceRepository.getWallpaperList(UserConfig.INSTANCE.getInstance().getHwVersion(), this);
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
            final WallpaperMarketViewModel wallpaperMarketViewModel = WallpaperMarketViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel.watchFaceTryAgain.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<List<WatchWallpaperResp>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<List<WatchWallpaperResp>> watchFaceState, Continuation<? super Unit> continuation) {
                    List<WatchWallpaperResp> listIsSuccess = watchFaceState.isSuccess();
                    if (listIsSuccess != null) {
                        WallpaperMarketViewModel wallpaperMarketViewModel2 = wallpaperMarketViewModel;
                        Iterator<WatchWallpaperResp> it = listIsSuccess.iterator();
                        while (it.hasNext()) {
                            wallpaperMarketViewModel2.queryWatchWallpaperByType(UserConfig.INSTANCE.getInstance().getHwVersion(), it.next().getWallpaperType());
                        }
                    }
                    wallpaperMarketViewModel.doRefresh();
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void watchFaceTryAgain() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06811(null), 3, null);
    }

    /* compiled from: WallpaperMarketViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$queryWatchWallpaperByType$1", f = "WallpaperMarketViewModel.kt", i = {}, l = {77, 77}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$queryWatchWallpaperByType$1, reason: invalid class name and case insensitive filesystem */
    static final class C06801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        final /* synthetic */ int $typeId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06801(String str, int i, Continuation<? super C06801> continuation) {
            super(2, continuation);
            this.$hdName = str;
            this.$typeId = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WallpaperMarketViewModel.this.new C06801(this.$hdName, this.$typeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = WallpaperMarketViewModel.this.watchFaceRepository.getWallpaperByType(this.$hdName, this.$typeId, this);
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
            final WallpaperMarketViewModel wallpaperMarketViewModel = WallpaperMarketViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel.queryWatchWallpaperByType.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<List<WatchWallpaperResp>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<List<WatchWallpaperResp>> watchFaceState, Continuation<? super Unit> continuation) {
                    Unit unit;
                    List<WatchWallpaperResp> listIsSuccess = watchFaceState.isSuccess();
                    if (listIsSuccess != null) {
                        WallpaperMarketViewModel wallpaperMarketViewModel2 = wallpaperMarketViewModel;
                        if (!watchFaceState.isSuccess().isEmpty()) {
                            String str = ((String) StringsKt.split$default((CharSequence) watchFaceState.isSuccess().get(0).getWallpaperName(), new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + PictureMimeType.PNG;
                            File watchWallpaperDirFile = FileUtils.INSTANCE.getWatchWallpaperDirFile();
                            if (!FileUtils.INSTANCE.fileExists(watchWallpaperDirFile.getAbsolutePath() + '/' + str)) {
                                wallpaperMarketViewModel2.watchFaceRepository.downloadWatchWallpaperImageFile(listIsSuccess);
                            }
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryWatchWallpaperByType(String hdName, int typeId) {
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06801(hdName, typeId, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doRefresh() {
        try {
            List<WatchWallpaperFace> allWatchWallpaper = this.watchFaceRepository.getAllWatchWallpaper(UserConfig.INSTANCE.getInstance().getHwVersion());
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List<WatchWallpaperFace> list = allWatchWallpaper;
            if (list == null || list.isEmpty()) {
                UserConfig.INSTANCE.getInstance().setWallpaperApiUpdateTime(0);
                UserConfig.INSTANCE.getInstance().save();
            }
            this.marketList.clear();
            if (allWatchWallpaper != null) {
                for (WatchWallpaperFace watchWallpaperFace : allWatchWallpaper) {
                    if (linkedHashMap.get(watchWallpaperFace.getWallpaperTypeName()) != null) {
                        List list2 = (List) linkedHashMap.get(watchWallpaperFace.getWallpaperTypeName());
                        if (list2 != null) {
                            list2.add(watchWallpaperFace);
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(watchWallpaperFace);
                        linkedHashMap.put(watchWallpaperFace.getWallpaperTypeName(), arrayList);
                    }
                }
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                DialItemBean dialItemBean = new DialItemBean();
                dialItemBean.setTitle((String) entry.getKey());
                ArrayList arrayList2 = new ArrayList();
                for (WatchWallpaperFace watchWallpaperFace2 : (List) entry.getValue()) {
                    dialItemBean.setType(watchWallpaperFace2.getWallpaperType());
                    arrayList2.add(new DialBean(watchWallpaperFace2.getWallpaperName(), watchWallpaperFace2.getWallpaperUrl(), watchWallpaperFace2.getLocalImageUrl(), watchWallpaperFace2.getWallpaperFileUrl(), 0.0f, "2", 3, watchWallpaperFace2.getWallpaperDesc()));
                }
                dialItemBean.setList(arrayList2);
                this.marketList.add(dialItemBean);
            }
            List<DialItemBean> list3 = this.marketList;
            if (list3.size() > 1) {
                CollectionsKt.sortWith(list3, new Comparator() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel$doRefresh$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((DialItemBean) t).getType()), Integer.valueOf(((DialItemBean) t2).getType()));
                    }
                });
            }
            this._marketUI.postValue(this.marketList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void getMarketWatchWallpaper() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WallpaperMarketViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel.getMarketWatchWallpaper.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WallpaperMarketViewModel wallpaperMarketViewModel) {
                invoke2(wallpaperMarketViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WallpaperMarketViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.getIndexApiUpdate();
            }
        });
    }

    /* compiled from: WallpaperMarketViewModel.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketViewModel$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketViewModel;)V", "onUpdatePlate", "", "array", "", "Lcom/oudmon/ble/base/communication/file/PlateEntity;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlate(List<PlateEntity> array) {
            Intrinsics.checkNotNullParameter(array, "array");
            Iterator<T> it = array.iterator();
            while (it.hasNext()) {
                QJavaApplication.getInstance().putKeys(((PlateEntity) it.next()).mPlateName);
            }
        }
    }
}
