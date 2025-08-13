package com.qcwireless.qcwatch.ui.base.repository.device;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.device.DevicePictureBean;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.bean.response.device.DevicePictureResp;
import com.qcwireless.qcwatch.ui.base.bean.response.device.FirmwareOtaResp;
import com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean;
import com.qcwireless.qcwatch.ui.base.repository.base.DeviceSettingAction;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDeviceSettingDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.device.more.ecard.bean.SoftwareLinks;
import com.qcwireless.qcwatch.ui.home.healthy.bean.DFUInformationBean;
import com.qcwireless.qcwatch.ui.mine.privacy.WebUrl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import kotlin.text.Regex;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: DeviceSettingRepository.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ-\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nJ\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016J\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00070\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ'\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d0\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ%\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ%\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u00062\u0006\u0010(\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010*0\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u000e\u0010-\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016J!\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u000e\u00102\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016J\u0014\u00103\u001a\u00020\u00132\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ\u0010\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u0018H\u0002J\u0016\u00107\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\n2\u0006\u00108\u001a\u00020\"J\u000e\u00109\u001a\u00020\u00132\u0006\u00104\u001a\u00020$J\u000e\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020<J\u0010\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020/H\u0002J\u000e\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u000201R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "", "()V", "qcDeviceSettingDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcDeviceSettingDao;", "checkOtaFromServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "hardwareVersion", "", "romVersion", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkOtaFromServerChina", "compareVersion", "", "currentVersion", "remoteVersion", "deleteData", "", "getAppName", "context", "Landroid/content/Context;", "getAppUpgrade", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceConfig", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceContact", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "mac", "getDeviceDisplay", "getDeviceLocation", "Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;", "getDevicePictureFromLocal", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DevicePictureBean;", "getDevicePictureFromServer", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;", "getDeviceScanConfigFromServer", "app", "getDeviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "getFirmwareInfo", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/DFUInformationBean;", "getPackageName", "getSoftwareQrCode", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareLinks;", "getSyncHistoryDataInfo", "", "getVersionCode", "saveContact", "bean", "saveDeviceDisplay", "display", "saveDeviceLocation", "locationBean", "saveDevicePicture", "saveDeviceSetting", "deviceSettingEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;", "saveSoftwareQrCode", "links", "saveSyncHistoryDataInfo", "unixTime", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceSettingRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DeviceSettingRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DeviceSettingRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeviceSettingRepository invoke() {
            return new DeviceSettingRepository();
        }
    });
    private final QcDeviceSettingDao qcDeviceSettingDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcDeviceSettingDao();

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$2", f = "DeviceSettingRepository.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$2, reason: invalid class name and case insensitive filesystem */
    static final class C03232 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03232(String str, Continuation<? super C03232> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03232 c03232 = DeviceSettingRepository.this.new C03232(this.$mac, continuation);
            c03232.L$0 = obj;
            return c03232;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03232) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.WatchSetting), this) == coroutine_suspended) {
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

    public final Object getDeviceSetting(String str, Continuation<? super Flow<DeviceSetting>> continuation) {
        final Flow flow = FlowKt.flow(new C03232(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DeviceSetting>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super DeviceSetting> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$lambda-0$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$lambda-0$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03244(null)), Dispatchers.getIO()), new C03255(str, null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$4, reason: invalid class name and case insensitive filesystem */
    static final class C03244 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSetting>, Continuation<? super Unit>, Object> {
        int label;

        C03244(Continuation<? super C03244> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03244(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSetting> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03244) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$5", f = "DeviceSettingRepository.kt", i = {}, l = {67}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceSetting$5, reason: invalid class name and case insensitive filesystem */
    static final class C03255 extends SuspendLambda implements Function3<FlowCollector<? super DeviceSetting>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03255(String str, Continuation<? super C03255> continuation) {
            super(3, continuation);
            this.$mac = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DeviceSetting> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03255 c03255 = DeviceSettingRepository.this.new C03255(this.$mac, continuation);
            c03255.L$0 = flowCollector;
            return c03255.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                final DeviceSettingRepository deviceSettingRepository = DeviceSettingRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super DeviceSetting>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.getDeviceSetting.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super DeviceSetting> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super DeviceSetting> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        deviceSettingRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.WatchSetting, MoshiUtilsKt.toJson(new DeviceSetting(false, false, false, false, 0, 0, 0, false, false, false, false, false, 0, 0, 0, 0, false, false, 0, 0, null, 0, false, 0, 0, 0, 0, false, 0, null, null, 0, 0, 0, false, -1, 7, null))));
                    }
                });
                this.label = 1;
                if (flowCollector.emit(new DeviceSetting(false, false, false, false, 0, 0, 0, false, false, false, false, false, 0, 0, 0, 0, false, false, 0, 0, null, 0, false, 0, 0, 0, 0, false, 0, null, null, 0, 0, 0, false, -1, 7, null), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$2", f = "DeviceSettingRepository.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$2, reason: invalid class name and case insensitive filesystem */
    static final class C03262 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03262(String str, Continuation<? super C03262> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03262 c03262 = DeviceSettingRepository.this.new C03262(this.$mac, continuation);
            c03262.L$0 = obj;
            return c03262;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03262) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DFUInfo), this) == coroutine_suspended) {
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

    public final Object getFirmwareInfo(String str, Continuation<? super Flow<DFUInformationBean>> continuation) {
        final Flow flow = FlowKt.flow(new C03262(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DFUInformationBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super DFUInformationBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$lambda-1$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$lambda-1$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03274(null)), Dispatchers.getIO()), new C03285(str, null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/DFUInformationBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$4, reason: invalid class name and case insensitive filesystem */
    static final class C03274 extends SuspendLambda implements Function2<FlowCollector<? super DFUInformationBean>, Continuation<? super Unit>, Object> {
        int label;

        C03274(Continuation<? super C03274> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03274(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DFUInformationBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03274) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/DFUInformationBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$5", f = "DeviceSettingRepository.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getFirmwareInfo$5, reason: invalid class name and case insensitive filesystem */
    static final class C03285 extends SuspendLambda implements Function3<FlowCollector<? super DFUInformationBean>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03285(String str, Continuation<? super C03285> continuation) {
            super(3, continuation);
            this.$mac = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DFUInformationBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03285 c03285 = DeviceSettingRepository.this.new C03285(this.$mac, continuation);
            c03285.L$0 = flowCollector;
            c03285.L$1 = th;
            return c03285.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                XLog.i(Unit.INSTANCE);
                final DeviceSettingRepository deviceSettingRepository = DeviceSettingRepository.this;
                final String str = this.$mac;
                ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super DFUInformationBean>, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.getFirmwareInfo.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super DFUInformationBean> flowCollector2) {
                        invoke2(flowCollector2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCollector<? super DFUInformationBean> ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        deviceSettingRepository.qcDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.DFUInfo, MoshiUtilsKt.toJson(new DFUInformationBean())));
                    }
                });
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new DFUInformationBean(), this) == coroutine_suspended) {
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

    public final void saveDeviceSetting(final DeviceSettingEntity deviceSettingEntity) {
        Intrinsics.checkNotNullParameter(deviceSettingEntity, "deviceSettingEntity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveDeviceSetting.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(deviceSettingEntity);
            }
        });
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$2", f = "DeviceSettingRepository.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$2, reason: invalid class name and case insensitive filesystem */
    static final class C03322 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03322(String str, Continuation<? super C03322> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03322 c03322 = DeviceSettingRepository.this.new C03322(this.$mac, continuation);
            c03322.L$0 = obj;
            return c03322;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03322) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.LastSyncHistoryTimeStamp), this) == coroutine_suspended) {
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

    public final Object getSyncHistoryDataInfo(String str, Continuation<? super Flow<Long>> continuation) {
        final Flow flow = FlowKt.flow(new C03322(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<Long>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Long> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$lambda-2$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$lambda-2$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03334(null)), Dispatchers.getIO()), new C03345(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$4, reason: invalid class name and case insensitive filesystem */
    static final class C03334 extends SuspendLambda implements Function2<FlowCollector<? super Long>, Continuation<? super Unit>, Object> {
        int label;

        C03334(Continuation<? super C03334> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03334(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Long> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03334) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$5", f = "DeviceSettingRepository.kt", i = {}, l = {108}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSyncHistoryDataInfo$5, reason: invalid class name and case insensitive filesystem */
    static final class C03345 extends SuspendLambda implements Function3<FlowCollector<? super Long>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03345(Continuation<? super C03345> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Long> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03345 c03345 = new C03345(continuation);
            c03345.L$0 = flowCollector;
            return c03345.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(Boxing.boxLong(0L), this) == coroutine_suspended) {
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

    public final void saveSyncHistoryDataInfo(final long unixTime) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveSyncHistoryDataInfo.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.LastSyncHistoryTimeStamp, MoshiUtilsKt.toJson(Long.valueOf(unixTime))));
            }
        });
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$2", f = "DeviceSettingRepository.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$2, reason: invalid class name and case insensitive filesystem */
    static final class C03142 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03142(String str, Continuation<? super C03142> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03142 c03142 = DeviceSettingRepository.this.new C03142(this.$mac, continuation);
            c03142.L$0 = obj;
            return c03142;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03142) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DevicePicture), this) == coroutine_suspended) {
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

    public final Object getDevicePictureFromLocal(String str, Continuation<? super Flow<DevicePictureBean>> continuation) {
        final Flow flow = FlowKt.flow(new C03142(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DevicePictureBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super DevicePictureBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$lambda-3$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$lambda-3$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03154(null)), Dispatchers.getIO()), new C03165(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DevicePictureBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$4, reason: invalid class name and case insensitive filesystem */
    static final class C03154 extends SuspendLambda implements Function2<FlowCollector<? super DevicePictureBean>, Continuation<? super Unit>, Object> {
        int label;

        C03154(Continuation<? super C03154> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03154(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DevicePictureBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03154) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DevicePictureBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$5", f = "DeviceSettingRepository.kt", i = {}, l = {131}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromLocal$5, reason: invalid class name and case insensitive filesystem */
    static final class C03165 extends SuspendLambda implements Function3<FlowCollector<? super DevicePictureBean>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03165(Continuation<? super C03165> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DevicePictureBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03165 c03165 = new C03165(continuation);
            c03165.L$0 = flowCollector;
            return c03165.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new DevicePictureBean("", "", ""), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$2", f = "DeviceSettingRepository.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$2, reason: invalid class name and case insensitive filesystem */
    static final class C03112 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03112(Continuation<? super C03112> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03112 c03112 = DeviceSettingRepository.this.new C03112(continuation);
            c03112.L$0 = obj;
            return c03112;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03112) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(WebUrl.DefaultMac, DeviceSettingAction.DeviceLocation), this) == coroutine_suspended) {
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

    public final Object getDeviceLocation(String str, Continuation<? super Flow<MyLocationBean>> continuation) {
        final Flow flow = FlowKt.flow(new C03112(null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<MyLocationBean>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super MyLocationBean> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$lambda-4$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$lambda-4$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03124(null)), Dispatchers.getIO()), new C03135(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$4, reason: invalid class name and case insensitive filesystem */
    static final class C03124 extends SuspendLambda implements Function2<FlowCollector<? super MyLocationBean>, Continuation<? super Unit>, Object> {
        int label;

        C03124(Continuation<? super C03124> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03124(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super MyLocationBean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03124) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;", "t", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$5", f = "DeviceSettingRepository.kt", i = {}, l = {146}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceLocation$5, reason: invalid class name and case insensitive filesystem */
    static final class C03135 extends SuspendLambda implements Function3<FlowCollector<? super MyLocationBean>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C03135(Continuation<? super C03135> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super MyLocationBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03135 c03135 = new C03135(continuation);
            c03135.L$0 = flowCollector;
            c03135.L$1 = th;
            return c03135.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                XLog.i(Unit.INSTANCE);
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new MyLocationBean(0.0f, 0.0f, "en", "深圳市", ""), this) == coroutine_suspended) {
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

    public final void saveDeviceLocation(String mac, final MyLocationBean locationBean) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(locationBean, "locationBean");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveDeviceLocation.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(WebUrl.DefaultMac, DeviceSettingAction.DeviceLocation, MoshiUtilsKt.toJson(locationBean)));
            }
        });
    }

    public final void saveDevicePicture(final DevicePictureBean bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveDevicePicture.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.DevicePicture, MoshiUtilsKt.toJson(bean)));
            }
        });
    }

    public final void deleteData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.deleteData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.deleteDataWhereMacNotNull(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
            }
        });
    }

    public final void saveContact(final List<ContactsEntity> bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveContact.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.ContactSyncDevice, MoshiUtilsKt.toJson(bean)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveDeviceDisplay(final int display) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveDeviceDisplay.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.DeviceDisplay, MoshiUtilsKt.toJson(Integer.valueOf(display))));
            }
        });
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$2", f = "DeviceSettingRepository.kt", i = {}, l = {204}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$2, reason: invalid class name and case insensitive filesystem */
    static final class C03082 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03082(String str, Continuation<? super C03082> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03082 c03082 = DeviceSettingRepository.this.new C03082(this.$mac, continuation);
            c03082.L$0 = obj;
            return c03082;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03082) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DeviceDisplay), this) == coroutine_suspended) {
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

    public final Object getDeviceDisplay(String str, Continuation<? super Flow<Integer>> continuation) {
        final Flow flow = FlowKt.flow(new C03082(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<Integer>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Integer> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$lambda-5$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$lambda-5$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03094(null)), Dispatchers.getIO()), new C03105(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$4, reason: invalid class name and case insensitive filesystem */
    static final class C03094 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
        int label;

        C03094(Continuation<? super C03094> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03094(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Integer> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03094) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$5", f = "DeviceSettingRepository.kt", i = {}, l = {210}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceDisplay$5, reason: invalid class name and case insensitive filesystem */
    static final class C03105 extends SuspendLambda implements Function3<FlowCollector<? super Integer>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03105(Continuation<? super C03105> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Integer> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03105 c03105 = new C03105(continuation);
            c03105.L$0 = flowCollector;
            return c03105.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(Boxing.boxInt(0), this) == coroutine_suspended) {
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

    private final void saveSoftwareQrCode(final SoftwareLinks links) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingRepository, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.saveSoftwareQrCode.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingRepository deviceSettingRepository) {
                invoke2(deviceSettingRepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceSettingRepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.qcDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.DeviceSoftWareQrCode, MoshiUtilsKt.toJson(links)));
            }
        });
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$2", f = "DeviceSettingRepository.kt", i = {}, l = {228}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$2, reason: invalid class name and case insensitive filesystem */
    static final class C03292 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03292(String str, Continuation<? super C03292> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03292 c03292 = DeviceSettingRepository.this.new C03292(this.$mac, continuation);
            c03292.L$0 = obj;
            return c03292;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03292) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DeviceSoftWareQrCode), this) == coroutine_suspended) {
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

    public final Object getSoftwareQrCode(String str, Continuation<? super Flow<SoftwareLinks>> continuation) {
        final Flow flow = FlowKt.flow(new C03292(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<SoftwareLinks>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super SoftwareLinks> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$lambda-6$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$lambda-6$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03304(null)), Dispatchers.getIO()), new C03315(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareLinks;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$4, reason: invalid class name and case insensitive filesystem */
    static final class C03304 extends SuspendLambda implements Function2<FlowCollector<? super SoftwareLinks>, Continuation<? super Unit>, Object> {
        int label;

        C03304(Continuation<? super C03304> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03304(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super SoftwareLinks> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03304) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareLinks;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$5", f = "DeviceSettingRepository.kt", i = {}, l = {234}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getSoftwareQrCode$5, reason: invalid class name and case insensitive filesystem */
    static final class C03315 extends SuspendLambda implements Function3<FlowCollector<? super SoftwareLinks>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03315(Continuation<? super C03315> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super SoftwareLinks> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03315 c03315 = new C03315(continuation);
            c03315.L$0 = flowCollector;
            return c03315.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new SoftwareLinks(), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$2", f = "DeviceSettingRepository.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$2, reason: invalid class name and case insensitive filesystem */
    static final class C03052 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03052(String str, Continuation<? super C03052> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03052 c03052 = DeviceSettingRepository.this.new C03052(this.$mac, continuation);
            c03052.L$0 = obj;
            return c03052;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03052) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingRepository.this.qcDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.ContactSyncDevice), this) == coroutine_suspended) {
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

    public final Object getDeviceContact(String str, Continuation<? super Flow<? extends List<ContactsEntity>>> continuation) {
        final Flow flow = FlowKt.flow(new C03052(str, null));
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(new Flow<List<ContactsEntity>>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<ContactsEntity>> flowCollector, Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1$2", f = "DeviceSettingRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1$2$1 r0 = (com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1$2$1 r0 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1$2$1
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
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$lambda-7$$inlined$fromJson$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$lambda-7$$inlined$fromJson$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        }, new C03064(null)), Dispatchers.getIO()), new C03075(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$4", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$4, reason: invalid class name and case insensitive filesystem */
    static final class C03064 extends SuspendLambda implements Function2<FlowCollector<? super List<ContactsEntity>>, Continuation<? super Unit>, Object> {
        int label;

        C03064(Continuation<? super C03064> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03064(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super List<ContactsEntity>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03064) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$5", f = "DeviceSettingRepository.kt", i = {}, l = {246}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceContact$5, reason: invalid class name and case insensitive filesystem */
    static final class C03075 extends SuspendLambda implements Function3<FlowCollector<? super List<ContactsEntity>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03075(Continuation<? super C03075> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super List<ContactsEntity>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03075 c03075 = new C03075(continuation);
            c03075.L$0 = flowCollector;
            return c03075.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new ArrayList(), this) == coroutine_suspended) {
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

    public final boolean compareVersion(String currentVersion, String remoteVersion) {
        Intrinsics.checkNotNullParameter(currentVersion, "currentVersion");
        Intrinsics.checkNotNullParameter(remoteVersion, "remoteVersion");
        String str = currentVersion;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = remoteVersion;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String string = str.subSequence(i, length + 1).toString();
        int length2 = str2.length() - 1;
        int i2 = 0;
        boolean z3 = false;
        while (i2 <= length2) {
            boolean z4 = Intrinsics.compare((int) str2.charAt(!z3 ? i2 : length2), 32) <= 0;
            if (z3) {
                if (!z4) {
                    break;
                }
                length2--;
            } else if (z4) {
                i2++;
            } else {
                z3 = true;
            }
        }
        String string2 = str2.subSequence(i2, length2 + 1).toString();
        Object[] array = new Regex("_").split(string, 0).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        Object[] array2 = new Regex("_").split(string2, 0).toArray(new String[0]);
        Objects.requireNonNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr2 = (String[]) array2;
        try {
            return Integer.parseInt(strArr2[strArr2.length - 1]) > Integer.parseInt(strArr[strArr.length - 1]);
        } catch (Exception unused) {
            return false;
        }
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2", f = "DeviceSettingRepository.kt", i = {1, 2, 4, 5}, l = {270, 275, 276, 278, 282, 282, 284}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03172 extends SuspendLambda implements Function2<FlowCollector<? super NetState<DevicePictureResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03172(String str, Continuation<? super C03172> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03172 c03172 = new C03172(this.$hardwareVersion, continuation);
            c03172.L$0 = obj;
            return c03172;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03172) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00ac A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00c4 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00f6 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x010e A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instructions count: 298
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.C03172.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$1", f = "DeviceSettingRepository.kt", i = {}, l = {277}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, DevicePictureResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<DevicePictureResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, DevicePictureResp devicePictureResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = devicePictureResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DevicePictureResp devicePictureResp = (DevicePictureResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, devicePictureResp, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$2", f = "DeviceSettingRepository.kt", i = {}, l = {279}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00462 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<DevicePictureResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00462(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super C00462> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00462 c00462 = new C00462(this.$$this$flow, continuation);
                c00462.I$0 = i;
                return c00462.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$3", f = "DeviceSettingRepository.kt", i = {}, l = {283}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$3, reason: invalid class name */
        static final class AnonymousClass3 extends SuspendLambda implements Function3<CoroutineScope, DevicePictureResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<DevicePictureResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass3(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super AnonymousClass3> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, DevicePictureResp devicePictureResp, Continuation<? super Unit> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$$this$flow, continuation);
                anonymousClass3.L$0 = devicePictureResp;
                return anonymousClass3.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DevicePictureResp devicePictureResp = (DevicePictureResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, devicePictureResp, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$4", f = "DeviceSettingRepository.kt", i = {}, l = {285}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$2$4, reason: invalid class name */
        static final class AnonymousClass4 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<DevicePictureResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass4(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super AnonymousClass4> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$$this$flow, continuation);
                anonymousClass4.I$0 = i;
                return anonymousClass4.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object getDevicePictureFromServer(String str, Continuation<? super Flow<NetState<DevicePictureResp>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03172(str, null)), new C03183(null)), Dispatchers.getIO()), new C03194(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$3", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03183 extends SuspendLambda implements Function2<FlowCollector<? super NetState<DevicePictureResp>>, Continuation<? super Unit>, Object> {
        int label;

        C03183(Continuation<? super C03183> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03183(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03183) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$4", f = "DeviceSettingRepository.kt", i = {}, l = {294}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDevicePictureFromServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03194 extends SuspendLambda implements Function3<FlowCollector<? super NetState<DevicePictureResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03194(Continuation<? super C03194> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03194 c03194 = new C03194(continuation);
            c03194.L$0 = flowCollector;
            return c03194.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2", f = "DeviceSettingRepository.kt", i = {1, 2}, l = {302, 306, 306, 308}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2, reason: invalid class name and case insensitive filesystem */
    static final class C03022 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03022(String str, Continuation<? super C03022> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03022 c03022 = new C03022(this.$hardwareVersion, continuation);
            c03022.L$0 = obj;
            return c03022;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03022) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x009e A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                r16 = this;
                r1 = r16
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r1.label
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 0
                r7 = 1
                if (r2 == 0) goto L3f
                if (r2 == r7) goto L3b
                if (r2 == r5) goto L31
                if (r2 == r4) goto L27
                if (r2 != r3) goto L1f
                kotlin.ResultKt.throwOnFailure(r17)     // Catch: java.lang.Exception -> L1c
                goto Lb9
            L1c:
                r0 = move-exception
                goto Lb6
            L1f:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r2)
                throw r0
            L27:
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r17)     // Catch: java.lang.Exception -> L1c
                r4 = r17
                goto L9f
            L31:
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r17)     // Catch: java.lang.Exception -> L1c
                r5 = r17
                goto L88
            L3b:
                kotlin.ResultKt.throwOnFailure(r17)
                goto L6f
            L3f:
                kotlin.ResultKt.throwOnFailure(r17)
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                java.lang.String r8 = r1.$hardwareVersion
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                int r8 = r8.length()
                if (r8 != 0) goto L52
                r8 = 1
                goto L53
            L52:
                r8 = 0
            L53:
                if (r8 == 0) goto L72
                com.qcwireless.qcwatch.ui.base.repository.mine.NetState r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.NetState
                r10 = 0
                r11 = 0
                r12 = -10001(0xffffffffffffd8ef, float:NaN)
                r13 = 0
                r14 = 11
                r15 = 0
                r9 = r3
                r9.<init>(r10, r11, r12, r13, r14, r15)
                r4 = r1
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r1.label = r7
                java.lang.Object r2 = r2.emit(r3, r4)
                if (r2 != r0) goto L6f
                return r0
            L6f:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L72:
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r7 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.api.QcService r7 = r7.service()     // Catch: java.lang.Exception -> L1c
                java.lang.String r8 = r1.$hardwareVersion     // Catch: java.lang.Exception -> L1c
                r9 = r1
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch: java.lang.Exception -> L1c
                r1.L$0 = r2     // Catch: java.lang.Exception -> L1c
                r1.label = r5     // Catch: java.lang.Exception -> L1c
                java.lang.Object r5 = r7.getDeviceConfig(r8, r9)     // Catch: java.lang.Exception -> L1c
                if (r5 != r0) goto L88
                return r0
            L88:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r5 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r5     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$1 r7 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$1     // Catch: java.lang.Exception -> L1c
                r7.<init>(r2, r6)     // Catch: java.lang.Exception -> L1c
                kotlin.jvm.functions.Function3 r7 = (kotlin.jvm.functions.Function3) r7     // Catch: java.lang.Exception -> L1c
                r8 = r1
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch: java.lang.Exception -> L1c
                r1.L$0 = r2     // Catch: java.lang.Exception -> L1c
                r1.label = r4     // Catch: java.lang.Exception -> L1c
                java.lang.Object r4 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r5, r7, r8)     // Catch: java.lang.Exception -> L1c
                if (r4 != r0) goto L9f
                return r0
            L9f:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r4 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r4     // Catch: java.lang.Exception -> L1c
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$2 r5 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$2     // Catch: java.lang.Exception -> L1c
                r5.<init>(r2, r6)     // Catch: java.lang.Exception -> L1c
                kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5     // Catch: java.lang.Exception -> L1c
                r2 = r1
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> L1c
                r1.L$0 = r6     // Catch: java.lang.Exception -> L1c
                r1.label = r3     // Catch: java.lang.Exception -> L1c
                java.lang.Object r2 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r4, r5, r2)     // Catch: java.lang.Exception -> L1c
                if (r2 != r0) goto Lb9
                return r0
            Lb6:
                r0.printStackTrace()
            Lb9:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.C03022.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$1", f = "DeviceSettingRepository.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
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
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(i2), 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$2", f = "DeviceSettingRepository.kt", i = {}, l = {309}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00452 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00452(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00452> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00452 c00452 = new C00452(this.$$this$flow, continuation);
                c00452.I$0 = i;
                return c00452.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object getDeviceConfig(String str, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03022(str, null)), new C03033(null)), Dispatchers.getIO()), new C03044(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.getDeviceConfig.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<Integer>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation2) {
                Unit unit;
                Integer numIsSuccess = netState.isSuccess();
                if (numIsSuccess != null) {
                    DeviceSettingRepository.this.saveDeviceDisplay(numIsSuccess.intValue());
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$3", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$3, reason: invalid class name and case insensitive filesystem */
    static final class C03033 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C03033(Continuation<? super C03033> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03033(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03033) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$4", f = "DeviceSettingRepository.kt", i = {}, l = {TypedValues.AttributesType.TYPE_EASING}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceConfig$4, reason: invalid class name and case insensitive filesystem */
    static final class C03044 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03044(Continuation<? super C03044> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03044 c03044 = new C03044(continuation);
            c03044.L$0 = flowCollector;
            return c03044.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$2", f = "DeviceSettingRepository.kt", i = {1, 2}, l = {325, 333, 342, 344}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        final /* synthetic */ String $romVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
            this.$romVersion = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$hardwareVersion, this.$romVersion, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00cf A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 267
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "otaResp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$2$1", f = "DeviceSettingRepository.kt", i = {}, l = {343}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, FirmwareOtaResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, FirmwareOtaResp firmwareOtaResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = firmwareOtaResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FirmwareOtaResp firmwareOtaResp = (FirmwareOtaResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, firmwareOtaResp, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$2$2", f = "DeviceSettingRepository.kt", i = {}, l = {345}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00422 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00422(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super C00422> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00422 c00422 = new C00422(this.$$this$flow, continuation);
                c00422.I$0 = i;
                return c00422.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object checkOtaFromServer(String str, String str2, Continuation<? super Flow<NetState<FirmwareOtaResp>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(str, str2, null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$3", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$4", f = "DeviceSettingRepository.kt", i = {}, l = {353}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<FirmwareOtaResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$2", f = "DeviceSettingRepository.kt", i = {1, 2}, l = {359, 367, 376, 378}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$2, reason: invalid class name and case insensitive filesystem */
    static final class C02962 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        final /* synthetic */ String $romVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02962(String str, String str2, Continuation<? super C02962> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
            this.$romVersion = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02962 c02962 = new C02962(this.$hardwareVersion, this.$romVersion, continuation);
            c02962.L$0 = obj;
            return c02962;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C02962) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00cf A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 267
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.C02962.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "otaResp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$2$1", f = "DeviceSettingRepository.kt", i = {}, l = {377}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, FirmwareOtaResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, FirmwareOtaResp firmwareOtaResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = firmwareOtaResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FirmwareOtaResp firmwareOtaResp = (FirmwareOtaResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, firmwareOtaResp, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$2$2", f = "DeviceSettingRepository.kt", i = {}, l = {379}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00432 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00432(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super C00432> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00432 c00432 = new C00432(this.$$this$flow, continuation);
                c00432.I$0 = i;
                return c00432.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object checkOtaFromServerChina(String str, String str2, Continuation<? super Flow<NetState<FirmwareOtaResp>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C02962(str, str2, null)), new C02973(null)), Dispatchers.getIO()), new C02984(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$3", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$3, reason: invalid class name and case insensitive filesystem */
    static final class C02973 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        int label;

        C02973(Continuation<? super C02973> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02973(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C02973) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$4", f = "DeviceSettingRepository.kt", i = {}, l = {387}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$checkOtaFromServerChina$4, reason: invalid class name and case insensitive filesystem */
    static final class C02984 extends SuspendLambda implements Function3<FlowCollector<? super NetState<FirmwareOtaResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C02984(Continuation<? super C02984> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C02984 c02984 = new C02984(continuation);
            c02984.L$0 = flowCollector;
            return c02984.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2", f = "DeviceSettingRepository.kt", i = {0, 1}, l = {395, 395, 397}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C03202 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $app;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03202(String str, Continuation<? super C03202> continuation) {
            super(2, continuation);
            this.$app = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03202 c03202 = new C03202(this.$app, continuation);
            c03202.L$0 = obj;
            return c03202;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03202) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007d A[RETURN] */
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
                if (r1 == 0) goto L30
                if (r1 == r4) goto L28
                if (r1 == r3) goto L20
                if (r1 != r2) goto L18
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L81
            L16:
                r10 = move-exception
                goto L7e
            L18:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L20:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L67
            L28:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L50
            L30:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()     // Catch: java.lang.Exception -> L16
                java.lang.String r6 = r9.$app     // Catch: java.lang.Exception -> L16
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Exception -> L16
                r9.L$0 = r10     // Catch: java.lang.Exception -> L16
                r9.label = r4     // Catch: java.lang.Exception -> L16
                java.lang.Object r1 = r1.scanConfig(r6, r7)     // Catch: java.lang.Exception -> L16
                if (r1 != r0) goto L4d
                return r0
            L4d:
                r8 = r1
                r1 = r10
                r10 = r8
            L50:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$1     // Catch: java.lang.Exception -> L16
                r4.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4     // Catch: java.lang.Exception -> L16
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L16
                r9.L$0 = r1     // Catch: java.lang.Exception -> L16
                r9.label = r3     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r10, r4, r6)     // Catch: java.lang.Exception -> L16
                if (r10 != r0) goto L67
                return r0
            L67:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r10     // Catch: java.lang.Exception -> L16
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$2     // Catch: java.lang.Exception -> L16
                r3.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3     // Catch: java.lang.Exception -> L16
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Exception -> L16
                r9.L$0 = r5     // Catch: java.lang.Exception -> L16
                r9.label = r2     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r10, r3, r1)     // Catch: java.lang.Exception -> L16
                if (r10 != r0) goto L81
                return r0
            L7e:
                r10.printStackTrace()
            L81:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.C03202.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$1", f = "DeviceSettingRepository.kt", i = {}, l = {396}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
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
                    if (this.$$this$flow.emit(new NetState<>(false, str, 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$2", f = "DeviceSettingRepository.kt", i = {}, l = {398}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00472 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00472(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super C00472> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00472 c00472 = new C00472(this.$$this$flow, continuation);
                c00472.I$0 = i;
                return c00472.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object getDeviceScanConfigFromServer(String str, Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C03202(str, null)), new C03213(null)), Dispatchers.getIO()), new C03224(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$3", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C03213 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C03213(Continuation<? super C03213> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03213(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03213) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$4", f = "DeviceSettingRepository.kt", i = {}, l = {406}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getDeviceScanConfigFromServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C03224 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03224(Continuation<? super C03224> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03224 c03224 = new C03224(continuation);
            c03224.L$0 = flowCollector;
            return c03224.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2", f = "DeviceSettingRepository.kt", i = {0, 1}, l = {417, 417, 419}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2, reason: invalid class name and case insensitive filesystem */
    static final class C02992 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C02992(Continuation<? super C02992> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02992 c02992 = DeviceSettingRepository.this.new C02992(continuation);
            c02992.L$0 = obj;
            return c02992;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C02992) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00bc A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.String r0 = "getInstance().application"
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r10.label
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r2 == 0) goto L35
                if (r2 == r5) goto L2d
                if (r2 == r4) goto L24
                if (r2 != r3) goto L1c
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L19
                goto Lc0
            L19:
                r11 = move-exception
                goto Lbd
            L1c:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L24:
                java.lang.Object r0 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L19
                goto La6
            L2d:
                java.lang.Object r0 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L19
                goto L8f
            L35:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r2 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.this     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.QJavaApplication r7 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L19
                android.app.Application r7 = r7.getApplication()     // Catch: java.lang.Exception -> L19
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)     // Catch: java.lang.Exception -> L19
                android.content.Context r7 = (android.content.Context) r7     // Catch: java.lang.Exception -> L19
                java.lang.String r2 = r2.getAppName(r7)     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r7 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.this     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.QJavaApplication r8 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L19
                android.app.Application r8 = r8.getApplication()     // Catch: java.lang.Exception -> L19
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)     // Catch: java.lang.Exception -> L19
                android.content.Context r8 = (android.content.Context) r8     // Catch: java.lang.Exception -> L19
                int r7 = r7.getVersionCode(r8)     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.QJavaApplication r8 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L19
                android.app.Application r8 = r8.getApplication()     // Catch: java.lang.Exception -> L19
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)     // Catch: java.lang.Exception -> L19
                android.content.Context r8 = (android.content.Context) r8     // Catch: java.lang.Exception -> L19
                java.lang.String r0 = com.qcwireless.qcwatch.base.view.GlobalKt.getVersionName(r8)     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.ui.base.bean.request.app.AppVersionRequest r8 = new com.qcwireless.qcwatch.ui.base.bean.request.app.AppVersionRequest     // Catch: java.lang.Exception -> L19
                r8.<init>(r2, r7, r0)     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r0 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.ui.base.api.QcService r0 = r0.service()     // Catch: java.lang.Exception -> L19
                r2 = r10
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> L19
                r10.L$0 = r11     // Catch: java.lang.Exception -> L19
                r10.label = r5     // Catch: java.lang.Exception -> L19
                java.lang.Object r0 = r0.appLastVersion(r8, r2)     // Catch: java.lang.Exception -> L19
                if (r0 != r1) goto L8c
                return r1
            L8c:
                r9 = r0
                r0 = r11
                r11 = r9
            L8f:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$1 r2 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$1     // Catch: java.lang.Exception -> L19
                r2.<init>(r0, r6)     // Catch: java.lang.Exception -> L19
                kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2     // Catch: java.lang.Exception -> L19
                r5 = r10
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Exception -> L19
                r10.L$0 = r0     // Catch: java.lang.Exception -> L19
                r10.label = r4     // Catch: java.lang.Exception -> L19
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r11, r2, r5)     // Catch: java.lang.Exception -> L19
                if (r11 != r1) goto La6
                return r1
            La6:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11     // Catch: java.lang.Exception -> L19
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$2 r2 = new com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$2     // Catch: java.lang.Exception -> L19
                r2.<init>(r0, r6)     // Catch: java.lang.Exception -> L19
                kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2     // Catch: java.lang.Exception -> L19
                r0 = r10
                kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0     // Catch: java.lang.Exception -> L19
                r10.L$0 = r6     // Catch: java.lang.Exception -> L19
                r10.label = r3     // Catch: java.lang.Exception -> L19
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r11, r2, r0)     // Catch: java.lang.Exception -> L19
                if (r11 != r1) goto Lc0
                return r1
            Lbd:
                r11.printStackTrace()
            Lc0:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.C02992.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$1", f = "DeviceSettingRepository.kt", i = {}, l = {418}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
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
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(i2), 0, false, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: DeviceSettingRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$2", f = "DeviceSettingRepository.kt", i = {}, l = {TypedValues.CycleType.TYPE_EASING}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00442 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00442(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00442> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00442 c00442 = new C00442(this.$$this$flow, continuation);
                c00442.I$0 = i;
                return c00442.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, false, 11, null), this) == coroutine_suspended) {
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

    public final Object getAppUpgrade(Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C02992(null)), new C03003(null)), Dispatchers.getIO()), new C03014(null));
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$3", f = "DeviceSettingRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$3, reason: invalid class name and case insensitive filesystem */
    static final class C03003 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C03003(Continuation<? super C03003> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03003(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C03003) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$4", f = "DeviceSettingRepository.kt", i = {}, l = {428}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$getAppUpgrade$4, reason: invalid class name and case insensitive filesystem */
    static final class C03014 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03014(Continuation<? super C03014> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C03014 c03014 = new C03014(continuation);
            c03014.L$0 = flowCollector;
            return c03014.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, false, 11, null), this) == coroutine_suspended) {
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

    public final String getPackageName(Context context) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(context.packageName, 0)");
            String str = packageInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "packageInfo.packageName");
            return str;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "com.qc";
        }
    }

    public final String getAppName(Context context) throws Resources.NotFoundException, PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(context.packageName, 0)");
            String string = context.getResources().getString(packageInfo.applicationInfo.labelRes);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(labelRes)");
            return string;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "others";
        }
    }

    public final int getVersionCode(Context context) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(context.packageName, 0)");
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* compiled from: DeviceSettingRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeviceSettingRepository getGetInstance() {
            return (DeviceSettingRepository) DeviceSettingRepository.getInstance$delegate.getValue();
        }
    }
}
