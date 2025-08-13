package com.qcwireless.qcwatch.ui.base.repository.watchface;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.elvishew.xlog.XLog;
import com.liulishuo.okdownload.DownloadContext;
import com.liulishuo.okdownload.DownloadContextListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.WatchFaceDownloadProgressEvent;
import com.qcwireless.qcwatch.base.event.WatchThemeRefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse;
import com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt;
import com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.device.CustomDialResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceRanking;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchThemeResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchWallpaperResp;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcCustomFaceDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchThemeDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchThemeFace;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.home.healthy.bean.WatchFaceVersionBean;
import com.qcwireless.qcwatch.ui.plate.bean.DiyWatchFaceSettingBean;
import com.qcwireless.qcwatch.ui.plate.bean.MarketWatchFaceBean;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: WatchFaceRepository.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 W2\u00020\u0001:\u0001WB\u0005¢\u0006\u0002\u0010\u0002J0\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u000e0\u000e\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J \u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J \u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J,\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000e2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0012J\u001c\u0010\"\u001a\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020#0\u000e2\u0006\u0010$\u001a\u00020\u0012J\u001c\u0010%\u001a\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020#0\u000e2\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(J\u0014\u0010)\u001a\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020*0\u000eJ\u0014\u0010+\u001a\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020,0\u000eJ\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000e2\u0006\u0010/\u001a\u00020\u0016J%\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020302012\u0006\u00104\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J'\u00106\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000207\u0018\u00010\u000e012\u0006\u00108\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J\u0019\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010701H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010:J3\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u000e02012\u0006\u0010/\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J+\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u000e02012\u0006\u0010/\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J-\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001202012\u0006\u00104\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ3\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u000e02012\u0006\u00104\u001a\u00020\u00162\u0006\u0010C\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J%\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001602012\u0006\u00104\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J!\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F012\u0006\u0010G\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J+\u0010H\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u000e02012\u0006\u0010/\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J\f\u0010I\u001a\b\u0012\u0004\u0012\u00020J0\u000eJ\u000e\u0010K\u001a\u00020L2\u0006\u0010@\u001a\u00020\u0016J\u0010\u0010M\u001a\u0004\u0018\u00010.2\u0006\u0010@\u001a\u00020\u0016J\u000e\u0010N\u001a\n\u0012\u0004\u0012\u00020L\u0018\u00010\u000eJ\u000e\u0010O\u001a\u00020\u001c2\u0006\u0010P\u001a\u00020FJ%\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001202012\u0006\u0010R\u001a\u00020SH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010TJ\u000e\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020LR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006X"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "", "()V", "qcCustomFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcCustomFaceDao;", "qcDeviceSettingDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDeviceSettingDao;", "qcWatchThemeDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchThemeDao;", "qcWatchWallpaperDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchWallpaperDao;", "watchFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchFaceDao;", "averageAssign", "", ExifInterface.GPS_DIRECTION_TRUE, "source", "n", "", "createTask", "Lcom/liulishuo/okdownload/DownloadTask;", "url", "", "fileName", "tag", "createThemeTask", "createWallpaperTask", "downloadCustomImage", "", "bgUrl", "list", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp$ElementUI;", "width", "height", "downloadWatchFaceBinFile", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;", "version", "downloadWatchFaceImageFile", "downloadWatchFaceImageFileNotExists", "item", "Lcom/qcwireless/qcwatch/ui/plate/bean/MarketWatchFaceBean;", "downloadWatchThemeImageFile", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;", "downloadWatchWallpaperImageFile", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;", "getAllWatchWallpaper", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchWallpaperFace;", "hdName", "getCustomWatchFaceParams", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;", "hardwareVersion", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCustomizeParamsFromLocal", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "address", "getCustomizeParamsFromLocalByType", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWallpaperByType", "typeId", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWallpaperList", "getWatchFaceDownloadCount", "name", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWatchFaceFromServer", "marketVersion", "getWatchFaceServiceVersion", "getWatchFaceSetting", "Lcom/qcwireless/qcwatch/ui/plate/bean/DiyWatchFaceSettingBean;", "mac", "getWatchTheme", "queryAllTheme", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchThemeFace;", "queryByName", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchFace;", "queryWallpaperByName", "queryWatchFaceListByVersion", "saveWatchFaceSetting", "watchFaceSettingBean", "updateDownloadCount", "params", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceRanking;", "(Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceRanking;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWatchFaceBean", "bean", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFaceRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<WatchFaceRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<WatchFaceRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final WatchFaceRepository invoke() {
            return new WatchFaceRepository();
        }
    });
    private final QcWatchFaceDao watchFaceDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchFaceDao();
    private final QcDeviceSettingDao qcDeviceSettingDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcDeviceSettingDao();
    private final QcCustomFaceDao qcCustomFaceDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcCustomFaceDao();
    private final QcWatchThemeDao qcWatchThemeDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchThemeDao();
    private final QcWatchWallpaperDao qcWatchWallpaperDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchWallpaperDao();

    public final void downloadWatchFaceBinFile(List<WatchFaceResp> list, int version) {
        Intrinsics.checkNotNullParameter(list, "list");
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2", f = "WatchFaceRepository.kt", i = {1, 2}, l = {76, 80, 80, 111}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C04602 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchFaceResp>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        final /* synthetic */ int $marketVersion;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WatchFaceRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04602(String str, int i, WatchFaceRepository watchFaceRepository, Continuation<? super C04602> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
            this.$marketVersion = i;
            this.this$0 = watchFaceRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04602 c04602 = new C04602(this.$hardwareVersion, this.$marketVersion, this.this$0, continuation);
            c04602.L$0 = obj;
            return c04602;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchFaceResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchFaceResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchFaceResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04602) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x0099 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                r14 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r14.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L3a
                if (r1 == r5) goto L36
                if (r1 == r4) goto L2e
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Exception -> L1a
                goto Lb4
            L1a:
                r15 = move-exception
                goto Lb1
            L1d:
                java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r15.<init>(r0)
                throw r15
            L25:
                java.lang.Object r1 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Exception -> L1a
                goto L9a
            L2e:
                java.lang.Object r1 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Exception -> L1a
                goto L7a
            L36:
                kotlin.ResultKt.throwOnFailure(r15)
                goto L5e
            L3a:
                kotlin.ResultKt.throwOnFailure(r15)
                java.lang.Object r15 = r14.L$0
                kotlinx.coroutines.flow.FlowCollector r15 = (kotlinx.coroutines.flow.FlowCollector) r15
                java.lang.String r1 = r14.$hardwareVersion
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L61
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState r1 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState
                r2 = -10001(0xffffffffffffd8ef, float:NaN)
                r1.<init>(r6, r2, r5, r6)
                r2 = r14
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r14.label = r5
                java.lang.Object r15 = r15.emit(r1, r2)
                if (r15 != r0) goto L5e
                return r0
            L5e:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            L61:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L1a
                java.lang.String r5 = r14.$hardwareVersion     // Catch: java.lang.Exception -> L1a
                r7 = r14
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L1a
                r14.L$0 = r15     // Catch: java.lang.Exception -> L1a
                r14.label = r4     // Catch: java.lang.Exception -> L1a
                java.lang.Object r1 = r1.getWatchFace(r5, r7)     // Catch: java.lang.Exception -> L1a
                if (r1 != r0) goto L77
                return r0
            L77:
                r13 = r1
                r1 = r15
                r15 = r13
            L7a:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r15 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r15     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$1     // Catch: java.lang.Exception -> L1a
                int r8 = r14.$marketVersion     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository r9 = r14.this$0     // Catch: java.lang.Exception -> L1a
                java.lang.String r10 = r14.$hardwareVersion     // Catch: java.lang.Exception -> L1a
                r12 = 0
                r7 = r4
                r11 = r1
                r7.<init>(r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> L1a
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L1a
                r5 = r14
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Exception -> L1a
                r14.L$0 = r1     // Catch: java.lang.Exception -> L1a
                r14.label = r3     // Catch: java.lang.Exception -> L1a
                java.lang.Object r15 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r15, r4, r5)     // Catch: java.lang.Exception -> L1a
                if (r15 != r0) goto L9a
                return r0
            L9a:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r15 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r15     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$2     // Catch: java.lang.Exception -> L1a
                r3.<init>(r1, r6)     // Catch: java.lang.Exception -> L1a
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L1a
                r1 = r14
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L1a
                r14.L$0 = r6     // Catch: java.lang.Exception -> L1a
                r14.label = r2     // Catch: java.lang.Exception -> L1a
                java.lang.Object r15 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r15, r3, r1)     // Catch: java.lang.Exception -> L1a
                if (r15 != r0) goto Lb4
                return r0
            Lb1:
                r15.printStackTrace()
            Lb4:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.C04602.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "watchFaceResp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$1", f = "WatchFaceRepository.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends WatchFaceResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchFaceResp>>> $$this$flow;
            final /* synthetic */ String $hardwareVersion;
            final /* synthetic */ int $marketVersion;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ WatchFaceRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(int i, WatchFaceRepository watchFaceRepository, String str, FlowCollector<? super WatchFaceState<List<WatchFaceResp>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$marketVersion = i;
                this.this$0 = watchFaceRepository;
                this.$hardwareVersion = str;
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends WatchFaceResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<WatchFaceResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<WatchFaceResp> list, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$marketVersion, this.this$0, this.$hardwareVersion, this.$$this$flow, continuation);
                anonymousClass1.L$0 = list;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                WatchFace watchFace;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    List list = (List) this.L$0;
                    UserConfig.INSTANCE.getInstance().setServerMarketSize(list.size());
                    UserConfig.INSTANCE.getInstance().save();
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        WatchFaceResp watchFaceResp = (WatchFaceResp) it.next();
                        WatchFace watchFace2 = new WatchFace(watchFaceResp.getName(), watchFaceResp.getHardwareVersion(), watchFaceResp.getPreImageUrl(), watchFaceResp.getBinUrl(), watchFaceResp.getPrice(), 0, watchFaceResp.getWatchDesc(), "", "", this.$marketVersion, watchFaceResp.getTypeId());
                        WatchFace watchFaceQueryWatchFaceByNameAndHdVersion = this.this$0.watchFaceDao.queryWatchFaceByNameAndHdVersion(watchFace2.getName(), this.$hardwareVersion);
                        if (watchFaceQueryWatchFaceByNameAndHdVersion != null) {
                            if (watchFaceQueryWatchFaceByNameAndHdVersion.getLocalImageUrl().length() > 0) {
                                if (watchFaceQueryWatchFaceByNameAndHdVersion.getLocalBinUrl().length() > 0) {
                                    watchFaceQueryWatchFaceByNameAndHdVersion.setMarketVersion(this.$marketVersion);
                                    this.this$0.watchFaceDao.insert(watchFaceQueryWatchFaceByNameAndHdVersion);
                                }
                            }
                        }
                        File watchFaceDirFile = FileUtils.INSTANCE.getWatchFaceDirFile();
                        if (FileUtils.INSTANCE.fileExists(watchFaceDirFile.getAbsolutePath() + '/' + watchFaceResp.getName())) {
                            watchFace = watchFace2;
                            watchFace.setLocalBinUrl(watchFaceDirFile.getAbsolutePath() + '/' + watchFaceResp.getName());
                        } else {
                            watchFace = watchFace2;
                        }
                        this.this$0.watchFaceDao.insert(watchFace);
                    }
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(list, 0), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$2", f = "WatchFaceRepository.kt", i = {}, l = {112}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00892 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchFaceResp>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00892(FlowCollector<? super WatchFaceState<List<WatchFaceResp>>> flowCollector, Continuation<? super C00892> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00892 c00892 = new C00892(this.$$this$flow, continuation);
                c00892.I$0 = i;
                return c00892.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(null, i2, 1, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getWatchFaceFromServer(String str, int i, Continuation<? super Flow<WatchFaceState<List<WatchFaceResp>>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04602(str, i, this, null)), new C04613(null)), Dispatchers.getIO()), new C04624(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$3", f = "WatchFaceRepository.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C04613 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchFaceResp>>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04613(Continuation<? super C04613> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04613 c04613 = new C04613(continuation);
            c04613.L$0 = obj;
            return c04613;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchFaceResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchFaceResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchFaceResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04613) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, 0, 3, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$4", f = "WatchFaceRepository.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceFromServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C04624 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<List<? extends WatchFaceResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04624(Continuation<? super C04624> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchFaceResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchFaceResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchFaceResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04624 c04624 = new C04624(continuation);
            c04624.L$0 = flowCollector;
            return c04624.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2", f = "WatchFaceRepository.kt", i = {1, 2}, l = {127, 131, 131, 133}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2, reason: invalid class name and case insensitive filesystem */
    static final class C04632 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04632(String str, Continuation<? super C04632> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04632 c04632 = new C04632(this.$hardwareVersion, continuation);
            c04632.L$0 = obj;
            return c04632;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04632) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x008f A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L39
                if (r1 == r5) goto L35
                if (r1 == r4) goto L2d
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L1a
                goto Laa
            L1a:
                r10 = move-exception
                goto La7
            L1d:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L25:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L1a
                goto L90
            L2d:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L1a
                goto L79
            L35:
                kotlin.ResultKt.throwOnFailure(r10)
                goto L5d
            L39:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                java.lang.String r1 = r9.$hardwareVersion
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L60
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState r1 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState
                r2 = -10001(0xffffffffffffd8ef, float:NaN)
                r1.<init>(r6, r2, r5, r6)
                r2 = r9
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r9.label = r5
                java.lang.Object r10 = r10.emit(r1, r2)
                if (r10 != r0) goto L5d
                return r0
            L5d:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            L60:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L1a
                java.lang.String r5 = r9.$hardwareVersion     // Catch: java.lang.Exception -> L1a
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L1a
                r9.L$0 = r10     // Catch: java.lang.Exception -> L1a
                r9.label = r4     // Catch: java.lang.Exception -> L1a
                java.lang.Object r1 = r1.getWatchFaceVersion(r5, r7)     // Catch: java.lang.Exception -> L1a
                if (r1 != r0) goto L76
                return r0
            L76:
                r8 = r1
                r1 = r10
                r10 = r8
            L79:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$1     // Catch: java.lang.Exception -> L1a
                r4.<init>(r1, r6)     // Catch: java.lang.Exception -> L1a
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L1a
                r5 = r9
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Exception -> L1a
                r9.L$0 = r1     // Catch: java.lang.Exception -> L1a
                r9.label = r3     // Catch: java.lang.Exception -> L1a
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r5)     // Catch: java.lang.Exception -> L1a
                if (r10 != r0) goto L90
                return r0
            L90:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$2     // Catch: java.lang.Exception -> L1a
                r3.<init>(r1, r6)     // Catch: java.lang.Exception -> L1a
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L1a
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L1a
                r9.L$0 = r6     // Catch: java.lang.Exception -> L1a
                r9.label = r2     // Catch: java.lang.Exception -> L1a
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L1a
                if (r10 != r0) goto Laa
                return r0
            La7:
                r10.printStackTrace()
            Laa:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.C04632.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "version", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$1", f = "WatchFaceRepository.kt", i = {}, l = {132}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<String>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, String str, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = str;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(str, 0), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$2", f = "WatchFaceRepository.kt", i = {}, l = {134}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00902 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<String>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00902(FlowCollector<? super WatchFaceState<String>> flowCollector, Continuation<? super C00902> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00902 c00902 = new C00902(this.$$this$flow, continuation);
                c00902.I$0 = i;
                return c00902.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(null, i2, 1, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getWatchFaceServiceVersion(String str, Continuation<? super Flow<WatchFaceState<String>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04632(str, null)), new C04643(null)), Dispatchers.getIO()), new C04654(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$3, reason: invalid class name and case insensitive filesystem */
    static final class C04643 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C04643(Continuation<? super C04643> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04643(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04643) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$4", f = "WatchFaceRepository.kt", i = {}, l = {142}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceServiceVersion$4, reason: invalid class name and case insensitive filesystem */
    static final class C04654 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04654(Continuation<? super C04654> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super WatchFaceState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04654 c04654 = new C04654(continuation);
            c04654.L$0 = flowCollector;
            return c04654.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void downloadWatchFaceImageFileNotExists(MarketWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(item, "item");
        File watchFaceDirFile = FileUtils.INSTANCE.getWatchFaceDirFile();
        String name = item.getWatchFace().getName();
        String str = name;
        List listSplit$default = StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null);
        String str2 = ((String) StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + '.' + ((String) listSplit$default.get(listSplit$default.size() - 1));
        if (FileUtils.INSTANCE.fileExists(watchFaceDirFile.getAbsolutePath() + '/' + str2)) {
            FileUtils.INSTANCE.deleteFile(watchFaceDirFile.getAbsolutePath() + '/' + str2);
        }
        createTask(item.getWatchFace().getPreImageUrl(), str2, name).enqueue(new DownloadAImageListener());
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$2", f = "WatchFaceRepository.kt", i = {1}, l = {211, 214, 214}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$2, reason: invalid class name and case insensitive filesystem */
    static final class C04572 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        final /* synthetic */ String $name;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04572(String str, String str2, Continuation<? super C04572> continuation) {
            super(2, continuation);
            this.$name = str;
            this.$hardwareVersion = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04572 c04572 = new C04572(this.$name, this.$hardwareVersion, continuation);
            c04572.L$0 = obj;
            return c04572;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04572) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0080 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L81
                goto L81
            L16:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1e:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L81
                goto L6a
            L26:
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L81
                goto L4f
            L2a:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                r1 = r9
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                java.lang.String r9 = r8.$name     // Catch: java.lang.Exception -> L81
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch: java.lang.Exception -> L81
                boolean r9 = kotlin.text.StringsKt.isBlank(r9)     // Catch: java.lang.Exception -> L81
                if (r9 == 0) goto L52
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState r9 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState     // Catch: java.lang.Exception -> L81
                r2 = -10001(0xffffffffffffd8ef, float:NaN)
                r9.<init>(r5, r2, r4, r5)     // Catch: java.lang.Exception -> L81
                r2 = r8
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> L81
                r8.label = r4     // Catch: java.lang.Exception -> L81
                java.lang.Object r9 = r1.emit(r9, r2)     // Catch: java.lang.Exception -> L81
                if (r9 != r0) goto L4f
                return r0
            L4f:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L81
                return r9
            L52:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r9 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L81
                com.qcwireless.qcwatch.ui.base.api.QcService r9 = r9.service()     // Catch: java.lang.Exception -> L81
                java.lang.String r4 = r8.$hardwareVersion     // Catch: java.lang.Exception -> L81
                java.lang.String r6 = r8.$name     // Catch: java.lang.Exception -> L81
                r7 = r8
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L81
                r8.L$0 = r1     // Catch: java.lang.Exception -> L81
                r8.label = r3     // Catch: java.lang.Exception -> L81
                java.lang.Object r9 = r9.watchfaceDownloadCount(r4, r6, r7)     // Catch: java.lang.Exception -> L81
                if (r9 != r0) goto L6a
                return r0
            L6a:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r9 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r9     // Catch: java.lang.Exception -> L81
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$2$1 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$2$1     // Catch: java.lang.Exception -> L81
                r3.<init>(r1, r5)     // Catch: java.lang.Exception -> L81
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L81
                r1 = r8
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L81
                r8.L$0 = r5     // Catch: java.lang.Exception -> L81
                r8.label = r2     // Catch: java.lang.Exception -> L81
                java.lang.Object r9 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r9, r3, r1)     // Catch: java.lang.Exception -> L81
                if (r9 != r0) goto L81
                return r0
            L81:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.C04572.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$2$1", f = "WatchFaceRepository.kt", i = {}, l = {215}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.I$0 = i;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(Boxing.boxInt(i2), 0), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getWatchFaceDownloadCount(String str, String str2, Continuation<? super Flow<WatchFaceState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04572(str2, str, null)), new C04583(null)), Dispatchers.getIO()), new C04594(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$3, reason: invalid class name and case insensitive filesystem */
    static final class C04583 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C04583(Continuation<? super C04583> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04583(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04583) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$4", f = "WatchFaceRepository.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceDownloadCount$4, reason: invalid class name and case insensitive filesystem */
    static final class C04594 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04594(Continuation<? super C04594> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04594 c04594 = new C04594(continuation);
            c04594.L$0 = flowCollector;
            return c04594.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final List<WatchWallpaperFace> getAllWatchWallpaper(String hdName) {
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        return this.qcWatchWallpaperDao.queryAll(hdName);
    }

    public final void downloadWatchFaceImageFile(List<WatchFaceResp> list, final int version) {
        Intrinsics.checkNotNullParameter(list, "list");
        int size = list.size() / 10;
        if (size == 0) {
            size = 1;
        }
        List<List> listAverageAssign = averageAssign(list, size);
        final int size2 = listAverageAssign.size();
        final Ref.IntRef intRef = new Ref.IntRef();
        for (List<WatchFaceResp> list2 : listAverageAssign) {
            DownloadContext.QueueSet queueSet = new DownloadContext.QueueSet();
            File watchFaceDirFile = FileUtils.INSTANCE.getWatchFaceDirFile();
            queueSet.setParentPathFile(watchFaceDirFile);
            queueSet.setMinIntervalMillisCallbackProcess(1000);
            DownloadContext.Builder builderCommit = queueSet.commit();
            for (WatchFaceResp watchFaceResp : list2) {
                String name = watchFaceResp.getName();
                List listSplit$default = StringsKt.split$default((CharSequence) watchFaceResp.getPreImageUrl(), new char[]{'.'}, false, 0, 6, (Object) null);
                String str = ((String) StringsKt.split$default((CharSequence) name, new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + '.' + ((String) listSplit$default.get(listSplit$default.size() - 1));
                if (!FileUtils.INSTANCE.fileExists(watchFaceDirFile.getAbsolutePath() + '/' + str)) {
                    builderCommit.bindSetTask(createTask(watchFaceResp.getPreImageUrl(), str, name));
                } else {
                    WatchFace watchFaceQueryWatchFaceByNameAndHdVersion = this.watchFaceDao.queryWatchFaceByNameAndHdVersion(watchFaceResp.getName(), UserConfig.INSTANCE.getInstance().getHwVersion());
                    if (watchFaceQueryWatchFaceByNameAndHdVersion != null) {
                        watchFaceQueryWatchFaceByNameAndHdVersion.setLocalImageUrl(watchFaceDirFile.getAbsolutePath() + '/' + str);
                        watchFaceQueryWatchFaceByNameAndHdVersion.setMarketVersion(version);
                        this.watchFaceDao.insert(watchFaceQueryWatchFaceByNameAndHdVersion);
                    }
                }
            }
            builderCommit.setListener(new DownloadContextListener() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.downloadWatchFaceImageFile.1
                @Override // com.liulishuo.okdownload.DownloadContextListener
                public void taskEnd(DownloadContext context, DownloadTask task, EndCause cause, Exception realCause, int remainCount) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(task, "task");
                    Intrinsics.checkNotNullParameter(cause, "cause");
                }

                @Override // com.liulishuo.okdownload.DownloadContextListener
                public void queueEnd(DownloadContext context) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    intRef.element++;
                    if (intRef.element >= size2) {
                        XLog.i("------------1000");
                        QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(false);
                        XLog.i("-----------图片下载完");
                        EventBus.getDefault().post(new WatchFaceDownloadProgressEvent(50));
                        HealthyRepository.INSTANCE.getGetInstance().saveWatchFaceVersion(new WatchFaceVersionBean(UserConfig.INSTANCE.getInstance().getHwVersion(), String.valueOf(version)));
                    }
                }
            });
            builderCommit.build().startOnParallel(new QueueImageListener());
        }
    }

    public final void downloadCustomImage(final String bgUrl, List<CustomDialResp.ElementUI> list, final int width, final int height) {
        Intrinsics.checkNotNullParameter(bgUrl, "bgUrl");
        Intrinsics.checkNotNullParameter(list, "list");
        DownloadContext.QueueSet queueSet = new DownloadContext.QueueSet();
        final File customDialDirFile = FileUtils.INSTANCE.getCustomDialDirFile();
        queueSet.setParentPathFile(customDialDirFile);
        queueSet.setMinIntervalMillisCallbackProcess(1000);
        DownloadContext.Builder builderCommit = queueSet.commit();
        for (CustomDialResp.ElementUI elementUI : list) {
            String strValueOf = String.valueOf(elementUI.getType());
            if (!(elementUI.getImageUrl().length() == 0)) {
                String str = UserConfig.INSTANCE.getInstance().getHwVersion() + '_' + strValueOf + PictureMimeType.PNG;
                if (!FileUtils.INSTANCE.fileExists(customDialDirFile.getAbsolutePath() + '/' + str)) {
                    builderCommit.bindSetTask(createTask(elementUI.getImageUrl(), str));
                }
                this.qcCustomFaceDao.insert(new CustomFaceEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), UserConfig.INSTANCE.getInstance().getHwVersion(), elementUI.getType(), elementUI.getX(), elementUI.getY(), elementUI.getImageUrl(), customDialDirFile.getAbsolutePath() + '/' + str));
            }
        }
        final String str2 = UserConfig.INSTANCE.getInstance().getHwVersion() + "_bg.png";
        if (!FileUtils.INSTANCE.fileExists(customDialDirFile.getAbsolutePath() + '/' + str2)) {
            builderCommit.bindSetTask(createTask(bgUrl, str2));
        }
        builderCommit.setListener(new DownloadContextListener() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.downloadCustomImage.1
            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void taskEnd(DownloadContext context, DownloadTask task, EndCause cause, Exception realCause, int remainCount) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(task, "task");
                Intrinsics.checkNotNullParameter(cause, "cause");
            }

            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void queueEnd(DownloadContext context) {
                Intrinsics.checkNotNullParameter(context, "context");
                XLog.i("--------end");
                final int i = width;
                final int i2 = height;
                final String str3 = bgUrl;
                final File file = customDialDirFile;
                final String str4 = str2;
                final WatchFaceRepository watchFaceRepository = this;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$downloadCustomImage$1$queueEnd$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WatchFaceRepository.AnonymousClass1 anonymousClass1) {
                        invoke2(anonymousClass1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WatchFaceRepository.AnonymousClass1 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        watchFaceRepository.qcCustomFaceDao.insert(new CustomFaceEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), UserConfig.INSTANCE.getInstance().getHwVersion(), 666, i, i2, str3, file.getAbsolutePath() + '/' + str4));
                    }
                });
            }
        });
        builderCommit.build().startOnParallel(new QueueDiyImageListener());
    }

    private final DownloadTask createTask(String url, String fileName) {
        DownloadTask downloadTaskBuild = new DownloadTask.Builder(url, FileUtils.INSTANCE.getCustomDialDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPriority(10).setPassIfAlreadyCompleted(false).setReadBufferSize(8192).build();
        Intrinsics.checkNotNullExpressionValue(downloadTaskBuild, "Builder(url, parentFile)…192)\n            .build()");
        return downloadTaskBuild;
    }

    private final DownloadTask createTask(String url, String fileName, String tag) {
        DownloadTask task = new DownloadTask.Builder(url, FileUtils.INSTANCE.getWatchFaceDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).setPriority(10).setConnectionCount(1).setReadBufferSize(8192).build();
        task.setTag(tag);
        Intrinsics.checkNotNullExpressionValue(task, "task");
        return task;
    }

    public final List<WatchFace> queryWatchFaceListByVersion() {
        QcWatchFaceDao qcWatchFaceDao = this.watchFaceDao;
        String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
        Intrinsics.checkNotNull(hwVersion);
        return qcWatchFaceDao.queryWatchFaceByHardwareVersion(hwVersion, UserConfig.INSTANCE.getInstance().getWatchFaceMarketVersion());
    }

    public final WatchFace queryByName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.watchFaceDao.queryWatchFaceByName(name, UserConfig.INSTANCE.getInstance().getHwVersion());
    }

    public final void updateWatchFaceBean(WatchFace bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        this.watchFaceDao.insert(bean);
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$2", f = "WatchFaceRepository.kt", i = {0}, l = {407, 407}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$2, reason: invalid class name and case insensitive filesystem */
    static final class C04722 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ WatchFaceRanking $params;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04722(WatchFaceRanking watchFaceRanking, Continuation<? super C04722> continuation) {
            super(2, continuation);
            this.$params = watchFaceRanking;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04722 c04722 = new C04722(this.$params, continuation);
            c04722.L$0 = obj;
            return c04722;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04722) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$2$1", f = "WatchFaceRepository.kt", i = {}, l = {408}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.I$0 = i;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(Boxing.boxInt(i2), 0), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = (FlowCollector) this.L$0;
                this.L$0 = flowCollector;
                this.label = 1;
                obj = QcRetrofitClient.INSTANCE.service().rankingUpdate(this.$params, this);
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
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = null;
            this.label = 2;
            if (QcNoDataResponseKt.success((QcNoDataResponse) obj, new AnonymousClass1(flowCollector, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final Object updateDownloadCount(WatchFaceRanking watchFaceRanking, Continuation<? super Flow<WatchFaceState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04722(watchFaceRanking, null)), new C04733(null)), Dispatchers.getIO()), new C04744(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$3, reason: invalid class name and case insensitive filesystem */
    static final class C04733 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C04733(Continuation<? super C04733> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04733(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04733) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$4", f = "WatchFaceRepository.kt", i = {}, l = {413}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$updateDownloadCount$4, reason: invalid class name and case insensitive filesystem */
    static final class C04744 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04744(Continuation<? super C04744> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super WatchFaceState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04744 c04744 = new C04744(continuation);
            c04744.L$0 = flowCollector;
            return c04744.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveWatchFaceSetting(final DiyWatchFaceSettingBean watchFaceSettingBean) {
        Intrinsics.checkNotNullParameter(watchFaceSettingBean, "watchFaceSettingBean");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<WatchFaceRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.saveWatchFaceSetting.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WatchFaceRepository watchFaceRepository) {
                invoke2(watchFaceRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WatchFaceRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<CustomFaceEntity> listQueryWatchFaceList = ktxRunOnBgSingle.qcCustomFaceDao.queryWatchFaceList(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                if (listQueryWatchFaceList != null && (!listQueryWatchFaceList.isEmpty())) {
                    for (CustomFaceEntity customFaceEntity : listQueryWatchFaceList) {
                        int type = customFaceEntity.getType();
                        if (type == 1) {
                            watchFaceSettingBean.setLocalHourImageUrl(customFaceEntity.getLocalUrl());
                        } else if (type == 2) {
                            watchFaceSettingBean.setLocalBatteryImageUrl(customFaceEntity.getLocalUrl());
                        } else if (type == 3) {
                            watchFaceSettingBean.setLocalDataImageUrl(customFaceEntity.getLocalUrl());
                        } else if (type == 666) {
                            watchFaceSettingBean.setLocalDefaultImageUrl(customFaceEntity.getLocalUrl());
                        }
                    }
                }
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.DiyWatchFaceSetting, MoshiUtilsKt.toJson(watchFaceSettingBean)));
            }
        });
    }

    private final <T> List<List<T>> averageAssign(List<? extends T> source, int n) {
        List<? extends T> listSubList;
        ArrayList arrayList = new ArrayList();
        int size = source.size() % n;
        int size2 = source.size() / n;
        int i = 0;
        for (int i2 = 0; i2 < n; i2++) {
            if (size > 0) {
                listSubList = source.subList((i2 * size2) + i, ((i2 + 1) * size2) + i + 1);
                size--;
                i++;
            } else {
                listSubList = source.subList((i2 * size2) + i, ((i2 + 1) * size2) + i);
            }
            arrayList.add(listSubList);
        }
        return arrayList;
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$2", f = "WatchFaceRepository.kt", i = {}, l = {473}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$2, reason: invalid class name and case insensitive filesystem */
    static final class C04662 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04662(String str, Continuation<? super C04662> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04662 c04662 = WatchFaceRepository.this.new C04662(this.$mac, continuation);
            c04662.L$0 = obj;
            return c04662;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04662) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(WatchFaceRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DiyWatchFaceSetting), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object getWatchFaceSetting(String str, Continuation<? super Flow<DiyWatchFaceSettingBean>> continuation) {
        final Flow flow = FlowKt.flow(new C04662(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DiyWatchFaceSettingBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super DiyWatchFaceSettingBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1$2", f = "WatchFaceRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) throws java.io.IOException {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L19:
                        java.lang.Object r7 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L66
                    L2a:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L32:
                        kotlin.ResultKt.throwOnFailure(r7)
                        kotlinx.coroutines.flow.FlowCollector r7 = r5.$this_unsafeFlow
                        r2 = r0
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity r6 = (com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity) r6
                        java.lang.String r6 = r6.getContent()
                        com.qcwireless.qcwatch.base.utils.MoshiUtils r2 = com.qcwireless.qcwatch.base.utils.MoshiUtils.INSTANCE
                        com.squareup.moshi.Moshi r2 = r2.getMoshiBuild()
                        com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$lambda-0$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$lambda-0$$inlined$fromJson$1
                        r4.<init>()
                        java.lang.reflect.Type r4 = r4.getType()
                        com.squareup.moshi.JsonAdapter r2 = r2.adapter(r4)
                        java.lang.String r4 = "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                        java.lang.Object r6 = r2.fromJson(r6)
                        r0.label = r3
                        java.lang.Object r6 = r7.emit(r6, r0)
                        if (r6 != r1) goto L66
                        return r1
                    L66:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C04674(null)), Dispatchers.getIO()), new AnonymousClass5(str, null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/plate/bean/DiyWatchFaceSettingBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$4", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$4, reason: invalid class name and case insensitive filesystem */
    static final class C04674 extends SuspendLambda implements Function2<FlowCollector<? super DiyWatchFaceSettingBean>, Continuation<? super Unit>, Object> {
        int label;

        C04674(Continuation<? super C04674> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04674(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DiyWatchFaceSettingBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04674) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/plate/bean/DiyWatchFaceSettingBean;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$5", f = "WatchFaceRepository.kt", i = {}, l = {487}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchFaceSetting$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function3<FlowCollector<? super DiyWatchFaceSettingBean>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(String str, Continuation<? super AnonymousClass5> continuation) {
            super(3, continuation);
            this.$mac = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DiyWatchFaceSettingBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass5 anonymousClass5 = WatchFaceRepository.this.new AnonymousClass5(this.$mac, continuation);
            anonymousClass5.L$0 = flowCollector;
            return anonymousClass5.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                final WatchFaceRepository watchFaceRepository = WatchFaceRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super DiyWatchFaceSettingBean>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.getWatchFaceSetting.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super DiyWatchFaceSettingBean> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super DiyWatchFaceSettingBean> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        watchFaceRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.DiyWatchFaceSetting, MoshiUtilsKt.toJson(new DiyWatchFaceSettingBean(0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, -1, 255, null))));
                    }
                });
                this.label = 1;
                if (flowCollector.emit(new DiyWatchFaceSettingBean(0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, -1, 255, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2", f = "WatchFaceRepository.kt", i = {1, 2}, l = {494, 498, 499, TypedValues.PositionType.TYPE_TRANSITION_EASING}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<CustomDialResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$hardwareVersion, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<CustomDialResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x008f A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L39
                if (r1 == r5) goto L35
                if (r1 == r4) goto L2d
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L1a
                goto Laa
            L1a:
                r10 = move-exception
                goto La7
            L1d:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L25:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L1a
                goto L90
            L2d:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L1a
                goto L79
            L35:
                kotlin.ResultKt.throwOnFailure(r10)
                goto L5d
            L39:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                java.lang.String r1 = r9.$hardwareVersion
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L60
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState r1 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState
                r2 = -10001(0xffffffffffffd8ef, float:NaN)
                r1.<init>(r6, r2, r5, r6)
                r2 = r9
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r9.label = r5
                java.lang.Object r10 = r10.emit(r1, r2)
                if (r10 != r0) goto L5d
                return r0
            L5d:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            L60:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L1a
                java.lang.String r5 = r9.$hardwareVersion     // Catch: java.lang.Exception -> L1a
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L1a
                r9.L$0 = r10     // Catch: java.lang.Exception -> L1a
                r9.label = r4     // Catch: java.lang.Exception -> L1a
                java.lang.Object r1 = r1.getWatchFaceDialParameters(r5, r7)     // Catch: java.lang.Exception -> L1a
                if (r1 != r0) goto L76
                return r0
            L76:
                r8 = r1
                r1 = r10
                r10 = r8
            L79:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$1     // Catch: java.lang.Exception -> L1a
                r4.<init>(r1, r6)     // Catch: java.lang.Exception -> L1a
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L1a
                r5 = r9
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Exception -> L1a
                r9.L$0 = r1     // Catch: java.lang.Exception -> L1a
                r9.label = r3     // Catch: java.lang.Exception -> L1a
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r5)     // Catch: java.lang.Exception -> L1a
                if (r10 != r0) goto L90
                return r0
            L90:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L1a
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$2     // Catch: java.lang.Exception -> L1a
                r3.<init>(r1, r6)     // Catch: java.lang.Exception -> L1a
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L1a
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L1a
                r9.L$0 = r6     // Catch: java.lang.Exception -> L1a
                r9.label = r2     // Catch: java.lang.Exception -> L1a
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L1a
                if (r10 != r0) goto Laa
                return r0
            La7:
                r10.printStackTrace()
            Laa:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "version", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$1", f = "WatchFaceRepository.kt", i = {}, l = {CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, CustomDialResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<CustomDialResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<CustomDialResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, CustomDialResp customDialResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = customDialResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CustomDialResp customDialResp = (CustomDialResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(customDialResp, 0), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$2", f = "WatchFaceRepository.kt", i = {}, l = {TypedValues.PositionType.TYPE_DRAWPATH}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00862 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<CustomDialResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00862(FlowCollector<? super WatchFaceState<CustomDialResp>> flowCollector, Continuation<? super C00862> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00862 c00862 = new C00862(this.$$this$flow, continuation);
                c00862.I$0 = i;
                return c00862.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(null, i2, 1, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getCustomWatchFaceParams(String str, Continuation<? super Flow<WatchFaceState<CustomDialResp>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(str, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<CustomDialResp>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super WatchFaceState<CustomDialResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$4", f = "WatchFaceRepository.kt", i = {}, l = {TypedValues.PositionType.TYPE_POSITION_TYPE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomWatchFaceParams$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<CustomDialResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super WatchFaceState<CustomDialResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = flowCollector;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocal$2", f = "WatchFaceRepository.kt", i = {}, l = {515}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocal$2, reason: invalid class name and case insensitive filesystem */
    static final class C04452 extends SuspendLambda implements Function2<FlowCollector<? super List<? extends CustomFaceEntity>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $address;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04452(String str, Continuation<? super C04452> continuation) {
            super(2, continuation);
            this.$address = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04452 c04452 = WatchFaceRepository.this.new C04452(this.$address, continuation);
            c04452.L$0 = obj;
            return c04452;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super List<? extends CustomFaceEntity>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super List<CustomFaceEntity>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super List<CustomFaceEntity>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04452) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(WatchFaceRepository.this.qcCustomFaceDao.queryWatchFaceList(this.$address), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object getCustomizeParamsFromLocal(String str, Continuation<? super Flow<? extends List<CustomFaceEntity>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04452(str, null)), new C04463(null)), Dispatchers.getIO()), new C04474(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocal$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocal$3, reason: invalid class name and case insensitive filesystem */
    static final class C04463 extends SuspendLambda implements Function2<FlowCollector<? super List<? extends CustomFaceEntity>>, Continuation<? super Unit>, Object> {
        int label;

        C04463(Continuation<? super C04463> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04463(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super List<? extends CustomFaceEntity>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super List<CustomFaceEntity>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super List<CustomFaceEntity>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04463) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocal$4", f = "WatchFaceRepository.kt", i = {}, l = {520}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocal$4, reason: invalid class name and case insensitive filesystem */
    static final class C04474 extends SuspendLambda implements Function3<FlowCollector<? super List<? extends CustomFaceEntity>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04474(Continuation<? super C04474> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super List<? extends CustomFaceEntity>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super List<CustomFaceEntity>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super List<CustomFaceEntity>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04474 c04474 = new C04474(continuation);
            c04474.L$0 = flowCollector;
            c04474.L$1 = th;
            return c04474.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new ArrayList(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocalByType$2", f = "WatchFaceRepository.kt", i = {}, l = {525}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocalByType$2, reason: invalid class name and case insensitive filesystem */
    static final class C04482 extends SuspendLambda implements Function2<FlowCollector<? super CustomFaceEntity>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04482(Continuation<? super C04482> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04482 c04482 = WatchFaceRepository.this.new C04482(continuation);
            c04482.L$0 = obj;
            return c04482;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super CustomFaceEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04482) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(WatchFaceRepository.this.qcCustomFaceDao.queryWatchFaceCustom(UserConfig.INSTANCE.getInstance().getHwVersion()), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object getCustomizeParamsFromLocalByType(Continuation<? super Flow<CustomFaceEntity>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04482(null)), new C04493(null)), Dispatchers.getIO()), new C04504(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocalByType$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocalByType$3, reason: invalid class name and case insensitive filesystem */
    static final class C04493 extends SuspendLambda implements Function2<FlowCollector<? super CustomFaceEntity>, Continuation<? super Unit>, Object> {
        int label;

        C04493(Continuation<? super C04493> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04493(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super CustomFaceEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04493) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocalByType$4", f = "WatchFaceRepository.kt", i = {}, l = {530}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getCustomizeParamsFromLocalByType$4, reason: invalid class name and case insensitive filesystem */
    static final class C04504 extends SuspendLambda implements Function3<FlowCollector<? super CustomFaceEntity>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04504(Continuation<? super C04504> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super CustomFaceEntity> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04504 c04504 = new C04504(continuation);
            c04504.L$0 = flowCollector;
            c04504.L$1 = th;
            return c04504.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final List<WatchThemeFace> queryAllTheme() {
        return this.qcWatchThemeDao.queryAll(UserConfig.INSTANCE.getInstance().getHwVersion());
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2", f = "WatchFaceRepository.kt", i = {0, 1}, l = {538, 538, 560}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2, reason: invalid class name and case insensitive filesystem */
    static final class C04542 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WatchFaceRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04542(String str, WatchFaceRepository watchFaceRepository, Continuation<? super C04542> continuation) {
            super(2, continuation);
            this.$hdName = str;
            this.this$0 = watchFaceRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04542 c04542 = new C04542(this.$hdName, this.this$0, continuation);
            c04542.L$0 = obj;
            return c04542;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04542) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$1", f = "WatchFaceRepository.kt", i = {}, l = {557}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends WatchWallpaperResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchWallpaperResp>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ WatchFaceRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, WatchFaceRepository watchFaceRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.this$0 = watchFaceRepository;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends WatchWallpaperResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<WatchWallpaperResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<WatchWallpaperResp> list, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, this.this$0, continuation);
                anonymousClass1.L$0 = list;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        List<WatchWallpaperResp> list = (List) this.L$0;
                        WatchFaceRepository watchFaceRepository = this.this$0;
                        for (WatchWallpaperResp watchWallpaperResp : list) {
                            watchFaceRepository.qcWatchWallpaperDao.insert(new WatchWallpaperFace(watchWallpaperResp.getWallpaperName(), UserConfig.INSTANCE.getInstance().getHwVersion(), watchWallpaperResp.getWallpaperDesc(), watchWallpaperResp.getWallpaperUrl(), watchWallpaperResp.getWallpaperFileUrl(), watchWallpaperResp.getWallpaperTypeAppIndex(), watchWallpaperResp.getWallpaperAppIndex(), "", "", watchWallpaperResp.getWallpaperType(), watchWallpaperResp.getWallpaperTypeName()));
                        }
                        this.label = 1;
                        if (this.$$this$flow.emit(new WatchFaceState<>(list, 0), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                } catch (Exception unused) {
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x007d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r10)
                goto L7e
            L16:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L1e:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L67
            L26:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L4e
            L2e:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.getService()
                java.lang.String r6 = r9.$hdName
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r9.L$0 = r10
                r9.label = r4
                java.lang.Object r1 = r1.watchWallpaperList(r6, r7)
                if (r1 != r0) goto L4b
                return r0
            L4b:
                r8 = r1
                r1 = r10
                r10 = r8
            L4e:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$1
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository r6 = r9.this$0
                r4.<init>(r1, r6, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r9.L$0 = r1
                r9.label = r3
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r6)
                if (r10 != r0) goto L67
                return r0
            L67:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r9.L$0 = r5
                r9.label = r2
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)
                if (r10 != r0) goto L7e
                return r0
            L7e:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.C04542.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$2", f = "WatchFaceRepository.kt", i = {}, l = {561}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00882 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchWallpaperResp>>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00882(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Continuation<? super C00882> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new C00882(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(null, 0, 3, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getWallpaperList(String str, Continuation<? super Flow<WatchFaceState<List<WatchWallpaperResp>>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04542(str, this, null)), new C04553(null)), Dispatchers.getIO()), new C04564(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$3, reason: invalid class name and case insensitive filesystem */
    static final class C04553 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>>, Continuation<? super Unit>, Object> {
        int label;

        C04553(Continuation<? super C04553> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04553(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04553) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$4", f = "WatchFaceRepository.kt", i = {}, l = {566}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperList$4, reason: invalid class name and case insensitive filesystem */
    static final class C04564 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04564(Continuation<? super C04564> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04564 c04564 = new C04564(continuation);
            c04564.L$0 = flowCollector;
            return c04564.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2", f = "WatchFaceRepository.kt", i = {0, 1}, l = {572, 572, 593}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2, reason: invalid class name and case insensitive filesystem */
    static final class C04682 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchThemeResp>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WatchFaceRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04682(String str, WatchFaceRepository watchFaceRepository, Continuation<? super C04682> continuation) {
            super(2, continuation);
            this.$hdName = str;
            this.this$0 = watchFaceRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04682 c04682 = new C04682(this.$hdName, this.this$0, continuation);
            c04682.L$0 = obj;
            return c04682;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchThemeResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchThemeResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchThemeResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04682) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$1", f = "WatchFaceRepository.kt", i = {}, l = {590}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends WatchThemeResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchThemeResp>>> $$this$flow;
            final /* synthetic */ String $hdName;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ WatchFaceRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<List<WatchThemeResp>>> flowCollector, String str, WatchFaceRepository watchFaceRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$hdName = str;
                this.this$0 = watchFaceRepository;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends WatchThemeResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<WatchThemeResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<WatchThemeResp> list, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, this.$hdName, this.this$0, continuation);
                anonymousClass1.L$0 = list;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        List<WatchThemeResp> list = (List) this.L$0;
                        String str = this.$hdName;
                        WatchFaceRepository watchFaceRepository = this.this$0;
                        for (WatchThemeResp watchThemeResp : list) {
                            WatchFaceRepository watchFaceRepository2 = watchFaceRepository;
                            watchFaceRepository2.qcWatchThemeDao.insert(new WatchThemeFace(watchThemeResp.getThemeName(), str, watchThemeResp.getPreImageUrl(), watchThemeResp.getBinUrl(), watchThemeResp.getThemeDesc(), "", "", watchThemeResp.getThemeType(), watchThemeResp.getAppHomeIndex()));
                            watchFaceRepository = watchFaceRepository2;
                        }
                        this.label = 1;
                        if (this.$$this$flow.emit(new WatchFaceState<>(list, 0), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                } catch (Exception unused) {
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x007f A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r10)
                goto L80
            L16:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L1e:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L69
            L26:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L4e
            L2e:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.getService()
                java.lang.String r6 = r9.$hdName
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r9.L$0 = r10
                r9.label = r4
                java.lang.Object r1 = r1.watchThemeList(r6, r7)
                if (r1 != r0) goto L4b
                return r0
            L4b:
                r8 = r1
                r1 = r10
                r10 = r8
            L4e:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$1
                java.lang.String r6 = r9.$hdName
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository r7 = r9.this$0
                r4.<init>(r1, r6, r7, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r9.L$0 = r1
                r9.label = r3
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r6)
                if (r10 != r0) goto L69
                return r0
            L69:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r9.L$0 = r5
                r9.label = r2
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)
                if (r10 != r0) goto L80
                return r0
            L80:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.C04682.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$2", f = "WatchFaceRepository.kt", i = {}, l = {594}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00912 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchThemeResp>>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00912(FlowCollector<? super WatchFaceState<List<WatchThemeResp>>> flowCollector, Continuation<? super C00912> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new C00912(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(null, 0, 3, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getWatchTheme(String str, Continuation<? super Flow<WatchFaceState<List<WatchThemeResp>>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04682(str, this, null)), new C04693(null)), Dispatchers.getIO()), new C04704(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$3, reason: invalid class name and case insensitive filesystem */
    static final class C04693 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchThemeResp>>>, Continuation<? super Unit>, Object> {
        int label;

        C04693(Continuation<? super C04693> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04693(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchThemeResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchThemeResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchThemeResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04693) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$4", f = "WatchFaceRepository.kt", i = {}, l = {599}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWatchTheme$4, reason: invalid class name and case insensitive filesystem */
    static final class C04704 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<List<? extends WatchThemeResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04704(Continuation<? super C04704> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchThemeResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchThemeResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchThemeResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04704 c04704 = new C04704(continuation);
            c04704.L$0 = flowCollector;
            return c04704.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void downloadWatchThemeImageFile(List<WatchThemeResp> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.isEmpty()) {
            return;
        }
        DownloadContext.QueueSet queueSet = new DownloadContext.QueueSet();
        File watchThemeDirFile = FileUtils.INSTANCE.getWatchThemeDirFile();
        queueSet.setParentPathFile(watchThemeDirFile);
        queueSet.setMinIntervalMillisCallbackProcess(1000);
        DownloadContext.Builder builderCommit = queueSet.commit();
        for (WatchThemeResp watchThemeResp : list) {
            String themeName = watchThemeResp.getThemeName();
            String str = ((String) StringsKt.split$default((CharSequence) themeName, new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + PictureMimeType.PNG;
            if (!FileUtils.INSTANCE.fileExists(watchThemeDirFile.getAbsolutePath() + '/' + str)) {
                builderCommit.bindSetTask(createThemeTask(watchThemeResp.getPreImageUrl(), str, themeName));
            }
        }
        builderCommit.setListener(new DownloadContextListener() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.downloadWatchThemeImageFile.1
            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void taskEnd(DownloadContext context, DownloadTask task, EndCause cause, Exception realCause, int remainCount) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(task, "task");
                Intrinsics.checkNotNullParameter(cause, "cause");
            }

            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void queueEnd(DownloadContext context) {
                Intrinsics.checkNotNullParameter(context, "context");
                XLog.i("end");
                QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(false);
                EventBus.getDefault().post(new WatchThemeRefreshEvent());
            }
        });
        builderCommit.build().startOnParallel(new QueueThemeImageListener());
    }

    private final DownloadTask createThemeTask(String url, String fileName, String tag) {
        DownloadTask task = new DownloadTask.Builder(url, FileUtils.INSTANCE.getWatchThemeDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).setPriority(10).setConnectionCount(1).setReadBufferSize(8192).build();
        task.setTag(tag);
        Intrinsics.checkNotNullExpressionValue(task, "task");
        return task;
    }

    private final DownloadTask createWallpaperTask(String url, String fileName, String tag) {
        DownloadTask task = new DownloadTask.Builder(url, FileUtils.INSTANCE.getWatchWallpaperDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).setPriority(10).setConnectionCount(1).setReadBufferSize(8192).build();
        task.setTag(tag);
        Intrinsics.checkNotNullExpressionValue(task, "task");
        return task;
    }

    public final void downloadWatchWallpaperImageFile(List<WatchWallpaperResp> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.isEmpty()) {
            XLog.i("---downloadWatchWallpaperImageFile");
            return;
        }
        DownloadContext.QueueSet queueSet = new DownloadContext.QueueSet();
        File watchWallpaperDirFile = FileUtils.INSTANCE.getWatchWallpaperDirFile();
        queueSet.setParentPathFile(watchWallpaperDirFile);
        queueSet.setMinIntervalMillisCallbackProcess(1000);
        DownloadContext.Builder builderCommit = queueSet.commit();
        for (WatchWallpaperResp watchWallpaperResp : list) {
            String wallpaperName = watchWallpaperResp.getWallpaperName();
            String str = ((String) StringsKt.split$default((CharSequence) wallpaperName, new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + PictureMimeType.PNG;
            if (!FileUtils.INSTANCE.fileExists(watchWallpaperDirFile.getAbsolutePath() + '/' + str)) {
                builderCommit.bindSetTask(createWallpaperTask(watchWallpaperResp.getWallpaperUrl(), str, wallpaperName));
            }
        }
        builderCommit.setListener(new DownloadContextListener() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.downloadWatchWallpaperImageFile.1
            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void taskEnd(DownloadContext context, DownloadTask task, EndCause cause, Exception realCause, int remainCount) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(task, "task");
                Intrinsics.checkNotNullParameter(cause, "cause");
            }

            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void queueEnd(DownloadContext context) {
                Intrinsics.checkNotNullParameter(context, "context");
                XLog.i("end");
                QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(false);
            }
        });
        builderCommit.build().startOnParallel(new QueueWallpaperImageListener());
    }

    public final WatchWallpaperFace queryWallpaperByName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.qcWatchWallpaperDao.queryWatchWallpaperByName(name, UserConfig.INSTANCE.getInstance().getHwVersion());
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2", f = "WatchFaceRepository.kt", i = {0, 1}, l = {715, 715, 737}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2, reason: invalid class name and case insensitive filesystem */
    static final class C04512 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hdName;
        final /* synthetic */ int $typeId;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WatchFaceRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04512(String str, int i, WatchFaceRepository watchFaceRepository, Continuation<? super C04512> continuation) {
            super(2, continuation);
            this.$hdName = str;
            this.$typeId = i;
            this.this$0 = watchFaceRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04512 c04512 = new C04512(this.$hdName, this.$typeId, this.this$0, continuation);
            c04512.L$0 = obj;
            return c04512;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04512) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$1", f = "WatchFaceRepository.kt", i = {}, l = {733}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends WatchWallpaperResp>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchWallpaperResp>>> $$this$flow;
            final /* synthetic */ String $hdName;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ WatchFaceRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, String str, WatchFaceRepository watchFaceRepository, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
                this.$hdName = str;
                this.this$0 = watchFaceRepository;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends WatchWallpaperResp> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<WatchWallpaperResp>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<WatchWallpaperResp> list, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, this.$hdName, this.this$0, continuation);
                anonymousClass1.L$0 = list;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        List<WatchWallpaperResp> list = (List) this.L$0;
                        String str = this.$hdName;
                        WatchFaceRepository watchFaceRepository = this.this$0;
                        for (WatchWallpaperResp watchWallpaperResp : list) {
                            WatchFaceRepository watchFaceRepository2 = watchFaceRepository;
                            watchFaceRepository2.qcWatchWallpaperDao.insert(new WatchWallpaperFace(watchWallpaperResp.getWallpaperName(), str, watchWallpaperResp.getWallpaperDesc(), watchWallpaperResp.getWallpaperUrl(), watchWallpaperResp.getWallpaperFileUrl(), watchWallpaperResp.getWallpaperTypeAppIndex(), watchWallpaperResp.getWallpaperAppIndex(), "", "", watchWallpaperResp.getWallpaperType(), watchWallpaperResp.getWallpaperTypeName()));
                            str = str;
                            watchFaceRepository = watchFaceRepository2;
                        }
                        this.label = 1;
                        if (this.$$this$flow.emit(new WatchFaceState<>(list, 0), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0081 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r11)
                goto L82
            L16:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1e:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L6b
            L26:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L50
            L2e:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.getService()
                java.lang.String r6 = r10.$hdName
                int r7 = r10.$typeId
                r8 = r10
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r10.L$0 = r11
                r10.label = r4
                java.lang.Object r1 = r1.watchWallpaperByType(r6, r7, r8)
                if (r1 != r0) goto L4d
                return r0
            L4d:
                r9 = r1
                r1 = r11
                r11 = r9
            L50:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$1
                java.lang.String r6 = r10.$hdName
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository r7 = r10.this$0
                r4.<init>(r1, r6, r7, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r10
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r10.L$0 = r1
                r10.label = r3
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r11, r4, r6)
                if (r11 != r0) goto L6b
                return r0
            L6b:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11
                com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r10.L$0 = r5
                r10.label = r2
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r11, r3, r1)
                if (r11 != r0) goto L82
                return r0
            L82:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository.C04512.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: WatchFaceRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$2", f = "WatchFaceRepository.kt", i = {}, l = {738}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00872 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<WatchFaceState<List<WatchWallpaperResp>>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00872(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Continuation<? super C00872> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new C00872(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.$$this$flow.emit(new WatchFaceState<>(null, 0, 3, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object getWallpaperByType(String str, int i, Continuation<? super Flow<WatchFaceState<List<WatchWallpaperResp>>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04512(str, i, this, null)), new C04523(null)), Dispatchers.getIO()), new C04534(null));
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$3", f = "WatchFaceRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$3, reason: invalid class name and case insensitive filesystem */
    static final class C04523 extends SuspendLambda implements Function2<FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>>, Continuation<? super Unit>, Object> {
        int label;

        C04523(Continuation<? super C04523> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04523(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04523) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$4", f = "WatchFaceRepository.kt", i = {}, l = {743}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository$getWallpaperByType$4, reason: invalid class name and case insensitive filesystem */
    static final class C04534 extends SuspendLambda implements Function3<FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04534(Continuation<? super C04534> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super WatchFaceState<List<? extends WatchWallpaperResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super WatchFaceState<List<WatchWallpaperResp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04534 c04534 = new C04534(continuation);
            c04534.L$0 = flowCollector;
            return c04534.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new WatchFaceState(null, RetCodeValue.ErrorCode_0, 1, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: WatchFaceRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WatchFaceRepository getGetInstance() {
            return (WatchFaceRepository) WatchFaceRepository.getInstance$delegate.getValue();
        }
    }
}
