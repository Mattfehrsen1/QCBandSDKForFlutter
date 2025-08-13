package com.qcwireless.qcwatch.ui.device.vm;

import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.liulishuo.okdownload.DownloadTask;
import com.oudmon.ble.base.bluetooth.BleAction;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DevicePictureBean;
import com.qcwireless.qcwatch.ui.base.bean.response.device.FirmwareOtaResp;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.qcwireless.qcwatch.ui.home.healthy.bean.DFUInformationBean;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DeviceViewModel.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u001f !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0015J\u0006\u0010\u001a\u001a\u00020\u0011J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0006\u0010\u001e\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_picUiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel$DevicePictureUI;", "_uiState", "Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel$DeviceFragmentUI;", "picUiState", "Landroidx/lifecycle/LiveData;", "getPicUiState", "()Landroidx/lifecycle/LiveData;", "uiState", "getUiState", "checkOta", "", "createGlideTask", "Lcom/liulishuo/okdownload/DownloadTask;", "url", "", "fileName", "createTask", "devicePicture", "hardwareVersion", "init", "saveDeviceDfuInformation", "firmwareOtaResp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "unBindDevice", "DeviceFragmentUI", "DevicePictureUI", "MyReceiver", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceViewModel extends BaseViewModel {
    private final MutableLiveData<DevicePictureUI> _picUiState;
    private final MutableLiveData<DeviceFragmentUI> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;

    public DeviceViewModel(DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this._uiState = new MutableLiveData<>();
        this._picUiState = new MutableLiveData<>();
    }

    public final LiveData<DeviceFragmentUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DevicePictureUI> getPicUiState() {
        return this._picUiState;
    }

    public final void init() {
        IntentFilter intentFilter = BleAction.getIntentFilter();
        LocalBroadcastManager.getInstance(QCApplication.INSTANCE.getCONTEXT()).registerReceiver(new MyReceiver(), intentFilter);
    }

    public final void unBindDevice() {
        BleOperateManager.getInstance().disconnectRtkSPP();
        DeviceManager.getInstance().reSet();
        UserConfig.INSTANCE.getInstance().setLastDeviceAddress(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        UserConfig.INSTANCE.getInstance().setServerMarketSize(0);
        UserConfig.INSTANCE.getInstance().setDeviceName("");
        UserConfig.INSTANCE.getInstance().setDeviceAddress("");
        UserConfig.INSTANCE.getInstance().setHwVersion("");
        UserConfig.INSTANCE.getInstance().setFmVersion("");
        UserConfig.INSTANCE.getInstance().setBatteryLow(false);
        UserConfig.INSTANCE.getInstance().setRtkMcuSupport(false);
        UserConfig.INSTANCE.getInstance().setMusicSupport(false);
        UserConfig.INSTANCE.getInstance().setNotificationGoTo(false);
        UserConfig.INSTANCE.getInstance().setDeviceNotScreen(false);
        UserConfig.INSTANCE.getInstance().setSupportRem(false);
        UserConfig.INSTANCE.getInstance().setSupportContact(false);
        UserConfig.INSTANCE.getInstance().setShowPlate(false);
        UserConfig.INSTANCE.getInstance().setBattery("0");
        UserConfig.INSTANCE.getInstance().setBatteryWarmingOpen(true);
        UserConfig.INSTANCE.getInstance().setDeviceThemeSupport(false);
        UserConfig.INSTANCE.getInstance().setDeviceWallpaperSupport(false);
        UserConfig.INSTANCE.getInstance().setPictureUpdateTime(0);
        UserConfig.INSTANCE.getInstance().setSupportAiAnalyze(false);
        UserConfig.INSTANCE.getInstance().setNotSupportTakePhoto(true);
        PreUtil.putString(PreUtil.Action_Device_Address, "");
        UserConfig.INSTANCE.getInstance().setMaxWatchFace(6);
        FileHandle.getInstance().endAndRelease();
        BleOperateManager.getInstance().unBindDevice();
        QJavaApplication.getInstance().clear();
        ThreadExtKt.ktxRunOnUiDelay(this, TrackingService.Constant.FASTEST_UPDATE_INTERVAL, new Function1<DeviceViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.unBindDevice.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceViewModel deviceViewModel) {
                invoke2(deviceViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceViewModel ktxRunOnUiDelay) {
                Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                BleScannerHelper.getInstance().removeSystemBle();
                BleScannerHelper.getInstance().removeMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
            }
        });
        UserConfig.INSTANCE.getInstance().save();
        EventBus.getDefault().post(new UnbindDeviceEvent());
        new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
    }

    public final void checkOta() throws InterruptedException {
        XLog.i("----checkOta");
        ThreadExtKt.ktxRunOnBgSingleNetWork(this, new Function1<DeviceViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.checkOta.1

            /* compiled from: DeviceViewModel.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$checkOta$1$1", f = "DeviceViewModel.kt", i = {}, l = {117, 120, 129, 132}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$checkOta$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ DeviceViewModel $this_ktxRunOnBgSingleNetWork;
                Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01371(DeviceViewModel deviceViewModel, Continuation<? super C01371> continuation) {
                    super(2, continuation);
                    this.$this_ktxRunOnBgSingleNetWork = deviceViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01371(this.$this_ktxRunOnBgSingleNetWork, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:21:0x0076 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:27:0x00a9 A[RETURN] */
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
                        r2 = 0
                        r3 = 4
                        r4 = 3
                        r5 = 2
                        r6 = 1
                        if (r1 == 0) goto L33
                        if (r1 == r6) goto L2b
                        if (r1 == r5) goto L26
                        if (r1 == r4) goto L1e
                        if (r1 != r3) goto L16
                        goto L26
                    L16:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r0)
                        throw r9
                    L1e:
                        java.lang.Object r1 = r8.L$0
                        com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r1 = (com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel) r1
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L96
                    L26:
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto Laa
                    L2b:
                        java.lang.Object r1 = r8.L$0
                        com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r1 = (com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel) r1
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L63
                    L33:
                        kotlin.ResultKt.throwOnFailure(r9)
                        com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r9 = r8.$this_ktxRunOnBgSingleNetWork
                        com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r9 = com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.access$getDeviceSettingRepository$p(r9)
                        com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r1 = r8.$this_ktxRunOnBgSingleNetWork
                        boolean r7 = com.qcwireless.qc_utils.date.LanguageUtil.isChina()
                        if (r7 == 0) goto L77
                        com.qcwireless.qcwatch.base.pref.UserConfig$Companion r3 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                        com.qcwireless.qcwatch.base.pref.UserConfig r3 = r3.getInstance()
                        java.lang.String r3 = r3.getHwVersion()
                        com.qcwireless.qcwatch.base.pref.UserConfig$Companion r4 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                        com.qcwireless.qcwatch.base.pref.UserConfig r4 = r4.getInstance()
                        java.lang.String r4 = r4.getFmVersion()
                        r8.L$0 = r1
                        r8.label = r6
                        java.lang.Object r9 = r9.checkOtaFromServerChina(r3, r4, r8)
                        if (r9 != r0) goto L63
                        return r0
                    L63:
                        kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
                        com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$checkOta$1$1$1$1 r3 = new com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$checkOta$1$1$1$1
                        r3.<init>()
                        kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
                        r8.L$0 = r2
                        r8.label = r5
                        java.lang.Object r9 = r9.collect(r3, r8)
                        if (r9 != r0) goto Laa
                        return r0
                    L77:
                        com.qcwireless.qcwatch.base.pref.UserConfig$Companion r5 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                        com.qcwireless.qcwatch.base.pref.UserConfig r5 = r5.getInstance()
                        java.lang.String r5 = r5.getHwVersion()
                        com.qcwireless.qcwatch.base.pref.UserConfig$Companion r6 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                        com.qcwireless.qcwatch.base.pref.UserConfig r6 = r6.getInstance()
                        java.lang.String r6 = r6.getFmVersion()
                        r8.L$0 = r1
                        r8.label = r4
                        java.lang.Object r9 = r9.checkOtaFromServer(r5, r6, r8)
                        if (r9 != r0) goto L96
                        return r0
                    L96:
                        kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
                        com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$checkOta$1$1$1$2 r4 = new com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$checkOta$1$1$1$2
                        r4.<init>()
                        kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                        r8.L$0 = r2
                        r8.label = r3
                        java.lang.Object r9 = r9.collect(r4, r8)
                        if (r9 != r0) goto Laa
                        return r0
                    Laa:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.AnonymousClass1.C01371.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceViewModel deviceViewModel) {
                invoke2(deviceViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeviceViewModel ktxRunOnBgSingleNetWork) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleNetWork, "$this$ktxRunOnBgSingleNetWork");
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C01371(ktxRunOnBgSingleNetWork, null), 3, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveDeviceDfuInformation(FirmwareOtaResp firmwareOtaResp) {
        DFUInformationBean dFUInformationBean = new DFUInformationBean();
        dFUInformationBean.setHardwareVersion(firmwareOtaResp.getHardwareVersion());
        dFUInformationBean.setLastVersion(firmwareOtaResp.getVersion());
        if (firmwareOtaResp.getIsEnforceUpdate().length() == 0) {
            dFUInformationBean.setEnforceUpdate(1);
        } else {
            dFUInformationBean.setEnforceUpdate(Integer.parseInt(firmwareOtaResp.getIsEnforceUpdate()));
        }
        dFUInformationBean.setOpenOrNot(firmwareOtaResp.getOpenOrNot());
        dFUInformationBean.setDownloadUrl(firmwareOtaResp.getDownloadUrl());
        if (dFUInformationBean.getLastVersion().length() > 0) {
            int openOrNot = dFUInformationBean.getOpenOrNot();
            int isEnforceUpdate = dFUInformationBean.getIsEnforceUpdate();
            boolean development = UserConfig.INSTANCE.getInstance().getDevelopment();
            XLog.i(Boolean.valueOf(development));
            if (development) {
                if (openOrNot == 3) {
                    this._uiState.postValue(new DeviceFragmentUI(2, isEnforceUpdate, true, 0));
                    return;
                } else {
                    this._uiState.postValue(new DeviceFragmentUI(2, isEnforceUpdate, false, 0));
                    return;
                }
            }
            if (openOrNot == 2) {
                this._uiState.postValue(new DeviceFragmentUI(2, isEnforceUpdate, true, 0));
                return;
            } else {
                this._uiState.postValue(new DeviceFragmentUI(2, isEnforceUpdate, false, 0));
                return;
            }
        }
        this._uiState.postValue(new DeviceFragmentUI(2, 1, false, -1));
    }

    /* compiled from: DeviceViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1", f = "DeviceViewModel.kt", i = {}, l = {186, 187}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1, reason: invalid class name and case insensitive filesystem */
    static final class C05611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05611(String str, Continuation<? super C05611> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceViewModel.this.new C05611(this.$hardwareVersion, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceViewModel.this.deviceSettingRepository.getDevicePictureFromLocal(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            this.label = 2;
            if (((Flow) obj).collect(new C01381(DeviceViewModel.this, this.$hardwareVersion), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: DeviceViewModel.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DevicePictureBean;", "emit", "(Lcom/qcwireless/qcwatch/ui/base/bean/device/DevicePictureBean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01381<T> implements FlowCollector {
            final /* synthetic */ String $hardwareVersion;
            final /* synthetic */ DeviceViewModel this$0;

            C01381(DeviceViewModel deviceViewModel, String str) {
                this.this$0 = deviceViewModel;
                this.$hardwareVersion = str;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.bean.device.DevicePictureBean r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
                /*
                    r9 = this;
                    boolean r0 = r11 instanceof com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$emit$1
                    if (r0 == 0) goto L14
                    r0 = r11
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$emit$1 r0 = (com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L14
                    int r11 = r0.label
                    int r11 = r11 - r2
                    r0.label = r11
                    goto L19
                L14:
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$emit$1 r0 = new com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$emit$1
                    r0.<init>(r9, r11)
                L19:
                    java.lang.Object r11 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L3d
                    if (r2 == r4) goto L35
                    if (r2 != r3) goto L2d
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L98
                L2d:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r11)
                    throw r10
                L35:
                    java.lang.Object r10 = r0.L$0
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1 r10 = (com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.C05611.C01381) r10
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L7f
                L3d:
                    kotlin.ResultKt.throwOnFailure(r11)
                    if (r10 == 0) goto Lb1
                    java.lang.String r11 = r10.getLocalUrl()
                    java.lang.CharSequence r11 = (java.lang.CharSequence) r11
                    int r11 = r11.length()
                    if (r11 != 0) goto L50
                    r11 = 1
                    goto L51
                L50:
                    r11 = 0
                L51:
                    if (r11 == 0) goto L9b
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r11 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r11 = r11.getInstance()
                    int r11 = r11.getPictureUpdateTime()
                    long r5 = (long) r11
                    com.qcwireless.qc_utils.date.DateUtil r11 = new com.qcwireless.qc_utils.date.DateUtil
                    r11.<init>()
                    long r7 = r11.getUnixTimestamp()
                    int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                    if (r11 >= 0) goto L9b
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r10 = r9.this$0
                    com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r10 = com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.access$getDeviceSettingRepository$p(r10)
                    java.lang.String r11 = r9.$hardwareVersion
                    r0.L$0 = r9
                    r0.label = r4
                    java.lang.Object r11 = r10.getDevicePictureFromServer(r11, r0)
                    if (r11 != r1) goto L7e
                    return r1
                L7e:
                    r10 = r9
                L7f:
                    kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$1 r2 = new com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$devicePicture$1$1$1
                    java.lang.String r4 = r10.$hardwareVersion
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r10 = r10.this$0
                    r2.<init>()
                    kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                    r10 = 0
                    r0.L$0 = r10
                    r0.label = r3
                    java.lang.Object r10 = r11.collect(r2, r0)
                    if (r10 != r1) goto L98
                    return r1
                L98:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                L9b:
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel r11 = r9.this$0
                    androidx.lifecycle.MutableLiveData r11 = com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.access$get_picUiState$p(r11)
                    com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$DevicePictureUI r0 = new com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel$DevicePictureUI
                    java.lang.String r1 = r10.getPictureUrl()
                    java.lang.String r10 = r10.getLocalUrl()
                    r0.<init>(r1, r10)
                    r11.postValue(r0)
                Lb1:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel.C05611.C01381.emit(com.qcwireless.qcwatch.ui.base.bean.device.DevicePictureBean, kotlin.coroutines.Continuation):java.lang.Object");
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((DevicePictureBean) obj, (Continuation<? super Unit>) continuation);
            }
        }
    }

    public final void devicePicture(String hardwareVersion) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C05611(hardwareVersion, null), 3, null);
    }

    private final DownloadTask createTask(String url, String fileName) {
        DownloadTask downloadTaskBuild = new DownloadTask.Builder(url, FileUtils.INSTANCE.getBinDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).build();
        Intrinsics.checkNotNullExpressionValue(downloadTaskBuild, "Builder(url, parentFile)…lse)\n            .build()");
        return downloadTaskBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DownloadTask createGlideTask(String url, String fileName) {
        DownloadTask downloadTaskBuild = new DownloadTask.Builder(url, FileUtils.INSTANCE.getGuideDirFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).build();
        Intrinsics.checkNotNullExpressionValue(downloadTaskBuild, "Builder(url, parentFile)…lse)\n            .build()");
        return downloadTaskBuild;
    }

    /* compiled from: DeviceViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel$MyReceiver;", "Lcom/oudmon/ble/base/bluetooth/QCBluetoothCallbackCloneReceiver;", "(Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel;)V", "connectStatue", "", "device", "Landroid/bluetooth/BluetoothDevice;", "connected", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyReceiver extends QCBluetoothCallbackCloneReceiver {
        public MyReceiver() {
        }

        @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
        public void connectStatue(BluetoothDevice device, boolean connected) {
            if (device == null || device.getName() == null) {
                return;
            }
            MutableLiveData mutableLiveData = DeviceViewModel.this._uiState;
            String name = device.getName();
            Intrinsics.checkNotNullExpressionValue(name, "device.name");
            String address = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address, "device.address");
            mutableLiveData.postValue(new DeviceFragmentUI(1, connected, name, address));
        }
    }

    /* compiled from: DeviceViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel$DevicePictureUI;", "", "url", "", "localUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getLocalUrl", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class DevicePictureUI {
        private final String localUrl;
        private final String url;

        public static /* synthetic */ DevicePictureUI copy$default(DevicePictureUI devicePictureUI, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = devicePictureUI.url;
            }
            if ((i & 2) != 0) {
                str2 = devicePictureUI.localUrl;
            }
            return devicePictureUI.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLocalUrl() {
            return this.localUrl;
        }

        public final DevicePictureUI copy(String url, String localUrl) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(localUrl, "localUrl");
            return new DevicePictureUI(url, localUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DevicePictureUI)) {
                return false;
            }
            DevicePictureUI devicePictureUI = (DevicePictureUI) other;
            return Intrinsics.areEqual(this.url, devicePictureUI.url) && Intrinsics.areEqual(this.localUrl, devicePictureUI.localUrl);
        }

        public int hashCode() {
            return (this.url.hashCode() * 31) + this.localUrl.hashCode();
        }

        public String toString() {
            return "DevicePictureUI(url=" + this.url + ", localUrl=" + this.localUrl + ')';
        }

        public DevicePictureUI(String url, String localUrl) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(localUrl, "localUrl");
            this.url = url;
            this.localUrl = localUrl;
        }

        public final String getLocalUrl() {
            return this.localUrl;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    /* compiled from: DeviceViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0017\u0018\u00002\u00020\u0001B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rR\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel$DeviceFragmentUI;", "", "refreshType", "", "dfuType", "showDfuButton", "", "retCode", "(IIZI)V", "connect", "deviceName", "", "deviceAddress", "(IZLjava/lang/String;Ljava/lang/String;)V", "getConnect", "()Z", "setConnect", "(Z)V", "getDeviceAddress", "()Ljava/lang/String;", "setDeviceAddress", "(Ljava/lang/String;)V", "getDeviceName", "setDeviceName", "getDfuType", "()I", "setDfuType", "(I)V", "getRefreshType", "setRefreshType", "getRetCode", "setRetCode", "getShowDfuButton", "setShowDfuButton", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DeviceFragmentUI {
        private boolean connect;
        private String deviceAddress;
        private String deviceName;
        private int dfuType;
        private int refreshType;
        private int retCode;
        private boolean showDfuButton;

        public final int getRefreshType() {
            return this.refreshType;
        }

        public final void setRefreshType(int i) {
            this.refreshType = i;
        }

        public final boolean getConnect() {
            return this.connect;
        }

        public final void setConnect(boolean z) {
            this.connect = z;
        }

        public final String getDeviceName() {
            return this.deviceName;
        }

        public final void setDeviceName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.deviceName = str;
        }

        public final String getDeviceAddress() {
            return this.deviceAddress;
        }

        public final void setDeviceAddress(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.deviceAddress = str;
        }

        public final int getDfuType() {
            return this.dfuType;
        }

        public final void setDfuType(int i) {
            this.dfuType = i;
        }

        public final boolean getShowDfuButton() {
            return this.showDfuButton;
        }

        public final void setShowDfuButton(boolean z) {
            this.showDfuButton = z;
        }

        public final int getRetCode() {
            return this.retCode;
        }

        public final void setRetCode(int i) {
            this.retCode = i;
        }

        public DeviceFragmentUI(int i, int i2, boolean z, int i3) {
            this.deviceName = "--";
            this.deviceAddress = "--";
            this.retCode = -1;
            this.refreshType = i;
            this.dfuType = i2;
            this.showDfuButton = z;
            this.retCode = i3;
        }

        public DeviceFragmentUI(int i, boolean z, String deviceName, String deviceAddress) {
            Intrinsics.checkNotNullParameter(deviceName, "deviceName");
            Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
            this.deviceName = "--";
            this.deviceAddress = "--";
            this.retCode = -1;
            this.refreshType = i;
            this.connect = z;
            this.deviceName = deviceName;
            this.deviceAddress = deviceAddress;
        }
    }
}
