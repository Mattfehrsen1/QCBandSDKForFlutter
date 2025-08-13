package com.qcwireless.qcwatch.ui.home.healthy.vm;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.bluetooth.spp.jieli.SppHandle;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.bean.ClassicBluetooth;
import com.oudmon.ble.base.communication.bigData.bean.ManualHeartRate;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataClassicBluetoothResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.file.SimpleCallback;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.HRVRsp;
import com.oudmon.ble.base.communication.rsp.PressureRsp;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.oudmon.ble.base.communication.rsp.ReadDetailSportDataRsp;
import com.oudmon.ble.base.communication.rsp.ReadHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.ReadSleepDetailsRsp;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.oudmon.ble.base.communication.rsp.TodaySportDataRsp;
import com.oudmon.ble.base.communication.sport.SportPlusEntity;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.DataSyncEvent;
import com.qcwireless.qcwatch.base.event.DeviceSyncTodayStepsAndDetailEvent;
import com.qcwireless.qcwatch.base.event.LoginSuccessEvent;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.TodayDataSyncEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceFunctionList;
import com.qcwireless.qcwatch.ui.base.bean.device.OneKeySupport;
import com.qcwireless.qcwatch.ui.base.bean.event.HealthItemRefreshEvent;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceResp;
import com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel;
import com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
import java.util.List;
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
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: HealthyViewModel.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0015\u0018\u0000 e2\u00020\u0001:\u0005defghBe\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u000e\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u001eJ\u0006\u0010<\u001a\u00020:J\b\u0010=\u001a\u00020:H\u0002J\u0010\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020\u001eH\u0002J\u0006\u0010@\u001a\u00020:J\u0006\u0010A\u001a\u00020:J\u0006\u0010B\u001a\u00020:J\u0006\u0010C\u001a\u00020:J\u0006\u0010D\u001a\u00020:J\b\u0010E\u001a\u00020:H\u0002J\b\u0010F\u001a\u00020:H\u0002J\b\u0010G\u001a\u00020:H\u0002J\u0006\u0010H\u001a\u00020:J\b\u0010I\u001a\u00020:H\u0002J\b\u0010J\u001a\u00020:H\u0002J\u0006\u0010K\u001a\u00020:J\b\u0010L\u001a\u00020:H\u0002J\b\u0010M\u001a\u00020:H\u0002J\b\u0010N\u001a\u00020:H\u0002J\b\u0010O\u001a\u00020:H\u0002J\b\u0010P\u001a\u00020:H\u0002J\b\u0010Q\u001a\u00020:H\u0002J\u000e\u0010R\u001a\u00020:2\u0006\u0010S\u001a\u00020TJ\u0006\u0010U\u001a\u00020:J\u0006\u0010V\u001a\u00020:J\u0006\u0010W\u001a\u00020:J\u0006\u0010X\u001a\u00020:J\u0006\u0010Y\u001a\u00020:J\u000e\u0010Z\u001a\u00020:2\u0006\u0010S\u001a\u00020TJ\u0006\u0010[\u001a\u00020:J\u0006\u0010\\\u001a\u00020:J\b\u0010]\u001a\u00020:H\u0002J\u0006\u0010^\u001a\u00020:J\u0006\u0010_\u001a\u00020:J\u0006\u0010`\u001a\u00020:J\b\u0010a\u001a\u00020:H\u0002J\u0006\u0010b\u001a\u00020:J\u0006\u0010c\u001a\u00020:R$\u0010\u001b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001e0\u001dj\b\u0012\u0004\u0012\u00020\u001e`\u001f0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u00060#R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010$\u001a\u00060%R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010&\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001e0\u001dj\b\u0012\u0004\u0012\u00020\u001e`\u001f0'8F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0012\u00103\u001a\u000604R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020!0'8F¢\u0006\u0006\u001a\u0004\b8\u0010)R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "healthyRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;", "stepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;", "sleepDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;", "heartDetailRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;", "sportPlusRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "bloodPressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;", "bloodOxygenRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;", "temperatureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;", "bloodSugarRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;", "pressureRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;", "hrvRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HealthyRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SleepDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HeartRateDetailRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodPressureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodOxygenRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/TemperatureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/BloodSugarRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/PressureRepository;Lcom/qcwireless/qcwatch/ui/base/repository/healthy/HRVRepository;)V", "_fileDismissStatue", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "_uiState", "Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$HealthUI;", "callback", "Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$Callback;", "detailBarRunnable", "Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$DetailBarStatus;", "fileDismissStatue", "Landroidx/lifecycle/LiveData;", "getFileDismissStatue", "()Landroidx/lifecycle/LiveData;", "handler", "Landroid/os/Handler;", "itemList", "", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "getItemList", "()Ljava/util/List;", "setItemList", "(Ljava/util/List;)V", "myRunnable", "Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$MyRunnable;", "support", "Lcom/qcwireless/qcwatch/ui/base/bean/device/OneKeySupport;", "uiState", "getUiState", "checkWatchFaceDownload", "", "version", "getTokenNotLogin", "getWatchDismissFile", "getWatchFaceFromService", "hardwareVersion", "initSupportFunction", "queryHealthData", "queryInitHealthData", "queryNotificationHealthData", "queryStepAndDetail", "syncBloodOxygen", "syncBloodPressure", "syncBloodSugar", "syncBpSingle", "syncHistoryData", "syncHistoryHeartDetail", "syncHistoryHrv", "syncHistoryPressure", "syncHistorySleepDetail", "syncHistoryStepDetail", "syncManualHeart", "syncTemperatureAuto", "syncTemperatureSingleCheck", "syncTodayBloodSugar", TypedValues.CycleType.S_WAVE_OFFSET, "", "syncTodayHeartRateDetail", "syncTodayHeartSingleData", "syncTodayHrv", "syncTodayPressure", "syncTodaySleepDetail", "syncTodaySpo2Single", "syncTodaySportPlusDetail", "syncTodaySportPlusDetailSingle", "syncTodayStep", "syncTodayStepDetail", "syncTodayStepDetailSingle", "syncTodayStepNotification", "syncTodayStepTotal", "syncTodayTemperatureSingle", "syncWatchData", "Callback", "Companion", "DetailBarStatus", "HealthUI", "MyRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HealthyViewModel extends BaseViewModel {
    public static final int Type_Sync_BloodOxygen = 10;
    public static final int Type_Sync_BloodPressure = 9;
    public static final int Type_Sync_File = 1;
    public static final int Type_Sync_Heart = 4;
    public static final int Type_Sync_HistoryHeart = 8;
    public static final int Type_Sync_HistoryHrv = 23;
    public static final int Type_Sync_HistoryPressure = 21;
    public static final int Type_Sync_HistorySleep = 7;
    public static final int Type_Sync_HistoryStep = 6;
    public static final int Type_Sync_Hrv = 22;
    public static final int Type_Sync_ManualHeart = 13;
    public static final int Type_Sync_Pressure = 20;
    public static final int Type_Sync_Sleep = 3;
    public static final int Type_Sync_SportPlus = 12;
    public static final int Type_Sync_Step = 2;
    public static final int Type_Sync_StepDetail = 5;
    public static final int Type_Sync_Temperature = 11;
    public static final int Type_Sync_TimeOut = 888;
    private final MutableLiveData<ArrayList<String>> _fileDismissStatue;
    private final MutableLiveData<HealthUI> _uiState;
    private final BloodOxygenRepository bloodOxygenRepository;
    private final BloodPressureRepository bloodPressureRepository;
    private final BloodSugarRepository bloodSugarRepository;
    private final Callback callback;
    private final DetailBarStatus detailBarRunnable;
    private final Handler handler;
    private final HealthyRepository healthyRepository;
    private final HeartRateDetailRepository heartDetailRepository;
    private final HRVRepository hrvRepository;
    private List<MultiItemEntity> itemList;
    private final MyRunnable myRunnable;
    private final PressureRepository pressureRepository;
    private final SleepDetailRepository sleepDetailRepository;
    private final SportPlusRepository sportPlusRepository;
    private final StepDetailRepository stepDetailRepository;
    private OneKeySupport support;
    private final TemperatureRepository temperatureRepository;
    private final WatchFaceRepository watchFaceRepository;

    public HealthyViewModel(HealthyRepository healthyRepository, StepDetailRepository stepDetailRepository, SleepDetailRepository sleepDetailRepository, HeartRateDetailRepository heartDetailRepository, SportPlusRepository sportPlusRepository, WatchFaceRepository watchFaceRepository, BloodPressureRepository bloodPressureRepository, BloodOxygenRepository bloodOxygenRepository, TemperatureRepository temperatureRepository, BloodSugarRepository bloodSugarRepository, PressureRepository pressureRepository, HRVRepository hrvRepository) {
        Intrinsics.checkNotNullParameter(healthyRepository, "healthyRepository");
        Intrinsics.checkNotNullParameter(stepDetailRepository, "stepDetailRepository");
        Intrinsics.checkNotNullParameter(sleepDetailRepository, "sleepDetailRepository");
        Intrinsics.checkNotNullParameter(heartDetailRepository, "heartDetailRepository");
        Intrinsics.checkNotNullParameter(sportPlusRepository, "sportPlusRepository");
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        Intrinsics.checkNotNullParameter(bloodPressureRepository, "bloodPressureRepository");
        Intrinsics.checkNotNullParameter(bloodOxygenRepository, "bloodOxygenRepository");
        Intrinsics.checkNotNullParameter(temperatureRepository, "temperatureRepository");
        Intrinsics.checkNotNullParameter(bloodSugarRepository, "bloodSugarRepository");
        Intrinsics.checkNotNullParameter(pressureRepository, "pressureRepository");
        Intrinsics.checkNotNullParameter(hrvRepository, "hrvRepository");
        this.healthyRepository = healthyRepository;
        this.stepDetailRepository = stepDetailRepository;
        this.sleepDetailRepository = sleepDetailRepository;
        this.heartDetailRepository = heartDetailRepository;
        this.sportPlusRepository = sportPlusRepository;
        this.watchFaceRepository = watchFaceRepository;
        this.bloodPressureRepository = bloodPressureRepository;
        this.bloodOxygenRepository = bloodOxygenRepository;
        this.temperatureRepository = temperatureRepository;
        this.bloodSugarRepository = bloodSugarRepository;
        this.pressureRepository = pressureRepository;
        this.hrvRepository = hrvRepository;
        this.itemList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this.callback = new Callback();
        this.detailBarRunnable = new DetailBarStatus();
        this._fileDismissStatue = new MutableLiveData<>();
        this.myRunnable = new MyRunnable();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) throws Throwable {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
                XLog.i("sync action:-->" + msg.what);
                if (BleOperateManager.getInstance().isConnected()) {
                    int i = msg.what;
                    if (i == 12) {
                        SyncStatus.INSTANCE.getGetInstance().setSyncSportPlus(false);
                        this.this$0.initSupportFunction();
                        this.this$0.syncHistoryStepDetail();
                        return;
                    }
                    if (i == 888) {
                        XLog.i("------Type_Sync_HistorySleep");
                        this.this$0.syncHistorySleepDetail();
                        return;
                    }
                    if (i == 21) {
                        this.this$0.syncHistoryHrv();
                        return;
                    }
                    if (i != 22) {
                        switch (i) {
                            case 1:
                                this.this$0.syncTodayStep();
                                break;
                            case 2:
                                this.this$0.syncTodayStepDetail();
                                break;
                            case 3:
                                this.this$0.syncTodayHeartRateDetail();
                                break;
                            case 4:
                                this.this$0.syncTodaySportPlusDetail();
                                break;
                            case 5:
                                this.this$0.syncTodaySleepDetail();
                                break;
                            case 6:
                                this.this$0.syncHistorySleepDetail();
                                break;
                            case 7:
                                this.this$0.syncHistoryHeartDetail();
                                break;
                        }
                    }
                    this.this$0.syncTodayHeartRateDetail();
                }
            }
        };
    }

    public final List<MultiItemEntity> getItemList() {
        return this.itemList;
    }

    public final void setItemList(List<MultiItemEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.itemList = list;
    }

    public final LiveData<HealthUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<ArrayList<String>> getFileDismissStatue() {
        return this._fileDismissStatue;
    }

    public final void syncWatchData() throws Throwable {
        LogToFile.getInstance().wtf("获取手表是否缺文件");
        getWatchDismissFile();
        this.handler.sendEmptyMessageDelayed(1, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncTodayStep() {
        CommandHandle.getInstance().executeReqCmd(new SetTimeReq(1), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) throws InterruptedException {
                HealthyViewModel.m738syncTodayStep$lambda1((SetTimeRsp) baseRspCmd);
            }
        });
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayStep.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.stepDetailRepository.syncTodayStep(new BaseDeviceResult<TodaySportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayStep$2$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, TodaySportDataRsp t) throws Throwable {
                        Intrinsics.checkNotNullParameter(t, "t");
                        ktxRunOnBgSingle.handler.removeMessages(2);
                        ktxRunOnBgSingle.syncTodayStepDetail();
                        LogToFile.getInstance().wtf("开始同步详情");
                        ktxRunOnBgSingle.handler.sendEmptyMessageDelayed(5, 3000L);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayStep$lambda-1, reason: not valid java name */
    public static final void m738syncTodayStep$lambda1(SetTimeRsp setTimeRsp) throws InterruptedException {
        if (setTimeRsp.mSupportContact) {
            XLog.i("支持通讯录功能");
            LargeDataHandler.getInstance().syncClassicBluetooth(new ILargeDataClassicBluetoothResponse() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda1
                @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataClassicBluetoothResponse
                public final void classicBluetooth(ClassicBluetooth classicBluetooth) {
                    HealthyViewModel.m739syncTodayStep$lambda1$lambda0(classicBluetooth);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayStep$lambda-1$lambda-0, reason: not valid java name */
    public static final void m739syncTodayStep$lambda1$lambda0(ClassicBluetooth classicBluetooth) {
        XLog.i(classicBluetooth);
        UserConfig companion = UserConfig.INSTANCE.getInstance();
        String deviceMac = classicBluetooth.getDeviceMac();
        Intrinsics.checkNotNullExpressionValue(deviceMac, "it1.deviceMac");
        companion.setClassicBluetoothMac(deviceMac);
        UserConfig.INSTANCE.getInstance().save();
        if (UserConfig.INSTANCE.getInstance().getMusicSupport() && !UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
            BluetoothDevice macSystemBond = BleOperateManager.getInstance().getMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
            if (macSystemBond != null) {
                if (UserConfig.INSTANCE.getInstance().getRtkMcuSupport()) {
                    XLog.i("---------connectRtkSPP");
                    BleOperateManager.getInstance().connectRtkSPP(macSystemBond);
                    return;
                } else if (UserConfig.INSTANCE.getInstance().getMusicSupport() && !UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                    BleOperateManager.getInstance().connectRtkSPP(macSystemBond);
                    return;
                } else {
                    if (UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                        SppHandle.getInstance().connect(macSystemBond);
                        return;
                    }
                    return;
                }
            }
            BleOperateManager.getInstance().classicBluetoothStartScan();
            return;
        }
        BleOperateManager.getInstance().classicBluetoothStartScan();
    }

    public final void syncTodayStepNotification() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayStepNotification.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (QCApplication.INSTANCE.getGetInstance().getUpdating() != 1 && QCApplication.INSTANCE.getGetInstance().getUpdating() != 2 && QCApplication.INSTANCE.getGetInstance().getUpdating() != 3 && QCApplication.INSTANCE.getGetInstance().getUpdating() != 4) {
                    StepDetailRepository unused = ktxRunOnBgSingle.stepDetailRepository;
                    ktxRunOnBgSingle.syncTodayStepTotal();
                } else {
                    XLog.i("正在固件升级");
                }
            }
        });
    }

    public final void syncTodayHrv() {
        this.hrvRepository.syncTodayHrv(new BaseDeviceResult<HRVRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayHrv.1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, HRVRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                HealthyViewModel.this.handler.removeMessages(22);
                HealthyViewModel.this.syncTodayHeartRateDetail();
                XLog.i("开始同步心率详情");
                HealthyViewModel.this.handler.sendEmptyMessageDelayed(4, 5000L);
            }
        });
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryHrv$1", f = "HealthyViewModel.kt", i = {}, l = {223}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryHrv$1, reason: invalid class name and case insensitive filesystem */
    static final class C06021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06021(Continuation<? super C06021> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06021(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HRVRepository hRVRepository = HealthyViewModel.this.hrvRepository;
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                final HealthyViewModel healthyViewModel = HealthyViewModel.this;
                this.label = 1;
                if (hRVRepository.syncHistoryHrvDetail(deviceAddressNoClear, new BaseDeviceResult<HRVRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncHistoryHrv.1.1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, HRVRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        healthyViewModel.handler.removeMessages(23);
                        XLog.i("同步压力历史数据");
                        healthyViewModel.syncHistoryPressure();
                        healthyViewModel.handler.sendEmptyMessageDelayed(21, 3000L);
                    }
                }, this) == coroutine_suspended) {
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

    public final void syncHistoryHrv() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06021(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryPressure$1", f = "HealthyViewModel.kt", i = {}, l = {237}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryPressure$1, reason: invalid class name and case insensitive filesystem */
    static final class C06031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06031(Continuation<? super C06031> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06031(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PressureRepository pressureRepository = HealthyViewModel.this.pressureRepository;
                String deviceAddress = DeviceManager.getInstance().getDeviceAddress();
                Intrinsics.checkNotNullExpressionValue(deviceAddress, "getInstance().deviceAddress");
                final HealthyViewModel healthyViewModel = HealthyViewModel.this;
                this.label = 1;
                if (pressureRepository.syncHistoryPressureDetail(deviceAddress, new BaseDeviceResult<PressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncHistoryPressure.1.1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, PressureRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        XLog.i("同步压力历史结束");
                        healthyViewModel.handler.removeMessages(21);
                        healthyViewModel.syncHistoryHeartDetail();
                        healthyViewModel.handler.sendEmptyMessageDelayed(8, 3000L);
                    }
                }, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncHistoryPressure() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06031(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncTodayStepTotal() {
        this.stepDetailRepository.syncTodayStep(new BaseDeviceResult<TodaySportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayStepTotal$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, TodaySportDataRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                try {
                    PreUtil.putInt(PreUtil.Action_Today_Steps, t.getSportTotal().getTotalSteps());
                    this.this$0.queryNotificationHealthData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public final void syncTodayStepDetail() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayStepDetail.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.stepDetailRepository.syncTodayStepDetail(0, new BaseDeviceResult<ReadDetailSportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayStepDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadDetailSportDataRsp t) throws Throwable {
                        Intrinsics.checkNotNullParameter(t, "t");
                        ktxRunOnBgSingle.handler.removeMessages(5);
                        ktxRunOnBgSingle.syncTodaySleepDetail();
                        LogToFile.getInstance().wtf("开始同步睡眠详情");
                        ktxRunOnBgSingle.handler.sendEmptyMessageDelayed(3, 3000L);
                        EventBus.getDefault().post(new DeviceSyncTodayStepsAndDetailEvent());
                    }
                });
            }
        });
    }

    public final void syncTodaySleepDetail() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodaySleepDetail.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.sleepDetailRepository.syncTodaySleepDetail(0, new BaseDeviceResult<ReadSleepDetailsRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodaySleepDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadSleepDetailsRsp t) throws Throwable {
                        Intrinsics.checkNotNullParameter(t, "t");
                        ktxRunOnBgSingle.handler.removeMessages(3);
                        if (UserConfig.INSTANCE.getInstance().getPressureSupport()) {
                            ktxRunOnBgSingle.syncTodayPressure();
                            ktxRunOnBgSingle.handler.sendEmptyMessageDelayed(20, 3000L);
                        } else {
                            ktxRunOnBgSingle.syncTodayHeartRateDetail();
                            LogToFile.getInstance().wtf("开始同步心率详情");
                            ktxRunOnBgSingle.handler.sendEmptyMessageDelayed(4, 3000L);
                        }
                    }
                });
            }
        });
    }

    public final void syncTodayPressure() {
        this.pressureRepository.syncTodayPressure(new BaseDeviceResult<PressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayPressure.1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, PressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                HealthyViewModel.this.handler.removeMessages(20);
                HealthyViewModel.this.syncTodayHrv();
                XLog.i("1111开始同步Hrv详情");
                HealthyViewModel.this.handler.sendEmptyMessageDelayed(22, 5000L);
            }
        });
    }

    public final void syncTodayHeartRateDetail() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayHeartRateDetail.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.heartDetailRepository.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartRateDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadHeartRateRsp t) throws Throwable {
                        Intrinsics.checkNotNullParameter(t, "t");
                        ktxRunOnBgSingle.handler.removeMessages(4);
                        ktxRunOnBgSingle.handler.sendEmptyMessageDelayed(12, 7000L);
                        ktxRunOnBgSingle.syncTodaySportPlusDetail();
                        LogToFile.getInstance().wtf("开始同步运动详情");
                        if (UserConfig.INSTANCE.getInstance().getGoogleFit()) {
                            GoogleFitSync.Companion.getGetInstance().syncGoogleFit();
                        }
                    }
                });
            }
        });
    }

    public final void initSupportFunction() throws Throwable {
        try {
            String oneKeySupport = UserConfig.INSTANCE.getInstance().getOneKeySupport();
            LogToFile.getInstance().wtf("查询手表支持的功能列表" + oneKeySupport);
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<OneKeySupport>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$initSupportFunction$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            OneKeySupport oneKeySupport2 = (OneKeySupport) jsonAdapterAdapter.fromJson(oneKeySupport);
            if (oneKeySupport2 != null) {
                XLog.i(oneKeySupport2);
                this.support = oneKeySupport2;
            }
            OneKeySupport oneKeySupport3 = this.support;
            if (oneKeySupport3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("support");
                oneKeySupport3 = null;
            }
            if (oneKeySupport3.getSupportBloodOxygen()) {
                syncBloodOxygen();
                LogToFile.getInstance().wtf("同步血氧");
            }
            OneKeySupport oneKeySupport4 = this.support;
            if (oneKeySupport4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("support");
                oneKeySupport4 = null;
            }
            if (oneKeySupport4.getSupportBloodPressure()) {
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        HealthyViewModel.m731initSupportFunction$lambda3(this.f$0);
                    }
                }, 500L);
            }
            OneKeySupport oneKeySupport5 = this.support;
            if (oneKeySupport5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("support");
                oneKeySupport5 = null;
            }
            if (oneKeySupport5.getSupportTemp()) {
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        HealthyViewModel.m732initSupportFunction$lambda4(this.f$0);
                    }
                }, 1000L);
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        HealthyViewModel.m733initSupportFunction$lambda5(this.f$0);
                    }
                }, 1500L);
            }
            OneKeySupport oneKeySupport6 = this.support;
            if (oneKeySupport6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("support");
                oneKeySupport6 = null;
            }
            if (oneKeySupport6.getSupportManualHeart()) {
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        HealthyViewModel.m734initSupportFunction$lambda6(this.f$0);
                    }
                }, 800L);
            }
            if (UserConfig.INSTANCE.getInstance().getBloodSugarSupport()) {
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        HealthyViewModel.m735initSupportFunction$lambda7(this.f$0);
                    }
                }, 1000L);
            }
            OneKeySupport oneKeySupport7 = this.support;
            if (oneKeySupport7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("support");
                oneKeySupport7 = null;
            }
            if (oneKeySupport7.getSupportLocation()) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass6(null), 3, null);
            }
            syncHistoryData();
            EventBus.getDefault().post(new DataSyncEvent(false));
            EventBus.getDefault().post(new TodayDataSyncEvent(false));
            XLog.i("当天数据同步结束");
            UserConfig.INSTANCE.getInstance().setLastTenMinSyncTime(600 + new DateUtil().getUnixTimestamp());
            UserConfig.INSTANCE.getInstance().save();
            this.handler.removeCallbacks(this.detailBarRunnable);
            this.handler.postDelayed(this.detailBarRunnable, 1000L);
            this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    HealthyViewModel.m736initSupportFunction$lambda8();
                }
            }, 4000L);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSupportFunction$lambda-3, reason: not valid java name */
    public static final void m731initSupportFunction$lambda3(HealthyViewModel this$0) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncBloodPressure();
        LogToFile.getInstance().wtf("同步血压");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSupportFunction$lambda-4, reason: not valid java name */
    public static final void m732initSupportFunction$lambda4(HealthyViewModel this$0) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncTemperatureAuto();
        LogToFile.getInstance().wtf("同步自动体温");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSupportFunction$lambda-5, reason: not valid java name */
    public static final void m733initSupportFunction$lambda5(HealthyViewModel this$0) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncTemperatureSingleCheck();
        LogToFile.getInstance().wtf("同步手动体温");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSupportFunction$lambda-6, reason: not valid java name */
    public static final void m734initSupportFunction$lambda6(HealthyViewModel this$0) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncManualHeart();
        LogToFile.getInstance().wtf("同步手动心率");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSupportFunction$lambda-7, reason: not valid java name */
    public static final void m735initSupportFunction$lambda7(HealthyViewModel this$0) throws Throwable {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncBloodSugar();
        LogToFile.getInstance().wtf("同步血糖数据");
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$initSupportFunction$6", f = "HealthyViewModel.kt", i = {}, l = {375, 376}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$initSupportFunction$6, reason: invalid class name */
    static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass6(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceLocation(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.initSupportFunction.6.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((MyLocationBean) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(MyLocationBean myLocationBean, Continuation<? super Unit> continuation) {
                    if (myLocationBean != null) {
                        String address = myLocationBean.getAddress();
                        byte[] bytes = address.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                        if (bytes.length > 100) {
                            address = MetricUtilsKt.getWholeText(address, 98);
                        }
                        String str = address;
                        if (str.length() > 0) {
                            LargeDataHandler.getInstance().writeLocation(myLocationBean.getLongitude(), myLocationBean.getLatitude(), str);
                        }
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSupportFunction$lambda-8, reason: not valid java name */
    public static final void m736initSupportFunction$lambda8() {
        SyncStatus.INSTANCE.getGetInstance().setSync(false);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$DetailBarStatus;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class DetailBarStatus implements Runnable {
        public DetailBarStatus() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HealthyViewModel.this.queryHealthData();
        }
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SyncStatus.INSTANCE.getGetInstance().setSync(false);
        }
    }

    public final void syncTodaySportPlusDetail() {
        SyncStatus.INSTANCE.getGetInstance().setSyncSportPlus(true);
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodaySportPlusDetail.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.sportPlusRepository.syncTodaySportPlus((BaseDeviceResult) new BaseDeviceResult<List<? extends SportPlusEntity>>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodaySportPlusDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, List<? extends SportPlusEntity> t) throws Throwable {
                        Intrinsics.checkNotNullParameter(t, "t");
                        XLog.i("运动同步结束");
                        ktxRunOnBgSingle.handler.removeMessages(12);
                        SyncStatus.INSTANCE.getGetInstance().setSyncSportPlus(false);
                        ktxRunOnBgSingle.initSupportFunction();
                        LogToFile.getInstance().wtf("运动同步结束");
                    }
                });
            }
        });
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryData$1", f = "HealthyViewModel.kt", i = {}, l = {441, 442}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryData$1, reason: invalid class name and case insensitive filesystem */
    static final class C06001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06001(Continuation<? super C06001> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06001(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getSyncHistoryDataInfo(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            final HealthyViewModel healthyViewModel = HealthyViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncHistoryData.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Long) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(Long l, Continuation<? super Unit> continuation) throws Throwable {
                    if (l == null || !new DateUtil(l.longValue(), true).isToday()) {
                        healthyViewModel.syncHistoryStepDetail();
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    private final void syncHistoryData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06001(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncHistoryStepDetail() throws Throwable {
        XLog.i("同步所有历史数据");
        LogToFile.getInstance().wtf("同步所有历史数据");
        SyncStatus.INSTANCE.getGetInstance().setSync(true);
        this.handler.postDelayed(this.myRunnable, 13000L);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06051(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryStepDetail$1", f = "HealthyViewModel.kt", i = {}, l = {465}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryStepDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C06051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06051(Continuation<? super C06051> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06051(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HealthyViewModel.this.handler.sendEmptyMessageDelayed(6, 4000L);
                StepDetailRepository stepDetailRepository = HealthyViewModel.this.stepDetailRepository;
                final HealthyViewModel healthyViewModel = HealthyViewModel.this;
                LogToFile.getInstance().wtf("同步历史步数");
                String deviceName = DeviceManager.getInstance().getDeviceName();
                Intrinsics.checkNotNullExpressionValue(deviceName, "getInstance().deviceName");
                String deviceAddress = DeviceManager.getInstance().getDeviceAddress();
                Intrinsics.checkNotNullExpressionValue(deviceAddress, "getInstance().deviceAddress");
                BaseDeviceResult<ReadDetailSportDataRsp> baseDeviceResult = new BaseDeviceResult<ReadDetailSportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryStepDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadDetailSportDataRsp t) throws Throwable {
                        Intrinsics.checkNotNullParameter(t, "t");
                        healthyViewModel.handler.removeMessages(6);
                        healthyViewModel.syncHistorySleepDetail();
                        LogToFile.getInstance().wtf("同步睡眠历史");
                        healthyViewModel.handler.sendEmptyMessageDelayed(7, 3000L);
                    }
                };
                this.label = 1;
                if (stepDetailRepository.syncHistoryStepDetail(deviceName, deviceAddress, baseDeviceResult, this) == coroutine_suspended) {
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

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistorySleepDetail$1", f = "HealthyViewModel.kt", i = {}, l = {484}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistorySleepDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C06041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06041(Continuation<? super C06041> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06041(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HealthyViewModel.this.handler.sendEmptyMessageDelayed(HealthyViewModel.Type_Sync_TimeOut, 3000L);
                SleepDetailRepository sleepDetailRepository = HealthyViewModel.this.sleepDetailRepository;
                final HealthyViewModel healthyViewModel = HealthyViewModel.this;
                String deviceName = DeviceManager.getInstance().getDeviceName();
                Intrinsics.checkNotNullExpressionValue(deviceName, "getInstance().deviceName");
                String deviceAddress = DeviceManager.getInstance().getDeviceAddress();
                Intrinsics.checkNotNullExpressionValue(deviceAddress, "getInstance().deviceAddress");
                BaseDeviceResult<ReadSleepDetailsRsp> baseDeviceResult = new BaseDeviceResult<ReadSleepDetailsRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistorySleepDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadSleepDetailsRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        healthyViewModel.handler.removeMessages(7);
                        healthyViewModel.handler.removeMessages(HealthyViewModel.Type_Sync_TimeOut);
                        XLog.i("-------------syncHistorySleepDetail");
                        if (!UserConfig.INSTANCE.getInstance().getHrvSupport()) {
                            healthyViewModel.syncHistoryHeartDetail();
                            healthyViewModel.handler.sendEmptyMessageDelayed(8, 3000L);
                        } else {
                            healthyViewModel.syncHistoryHrv();
                            healthyViewModel.handler.sendEmptyMessageDelayed(23, 3000L);
                        }
                    }
                };
                this.label = 1;
                if (sleepDetailRepository.syncSleepDetail(deviceName, deviceAddress, baseDeviceResult, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncHistorySleepDetail() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06041(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryHeartDetail$1", f = "HealthyViewModel.kt", i = {}, l = {TypedValues.PositionType.TYPE_PERCENT_Y}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryHeartDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C06011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06011(Continuation<? super C06011> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06011(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HeartRateDetailRepository heartRateDetailRepository = HealthyViewModel.this.heartDetailRepository;
                final HealthyViewModel healthyViewModel = HealthyViewModel.this;
                String deviceAddress = DeviceManager.getInstance().getDeviceAddress();
                Intrinsics.checkNotNullExpressionValue(deviceAddress, "getInstance().deviceAddress");
                BaseDeviceResult<ReadHeartRateRsp> baseDeviceResult = new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncHistoryHeartDetail$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadHeartRateRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        SyncStatus.INSTANCE.getGetInstance().setSync(false);
                        healthyViewModel.handler.removeMessages(8);
                        EventBus.getDefault().post(new DataSyncEvent(false));
                        EventBus.getDefault().post(new HealthItemRefreshEvent());
                        XLog.i("历史同步结束");
                        EventBus.getDefault().post(new LoginSuccessEvent(0));
                        if (BleOperateManager.getInstance().isConnected()) {
                            DeviceSettingRepository.INSTANCE.getGetInstance().saveSyncHistoryDataInfo(new DateUtil().getUnixTimestamp());
                        }
                        UserConfig.INSTANCE.getInstance().setLastTenMinSyncTime(600 + new DateUtil().getUnixTimestamp());
                        UserConfig.INSTANCE.getInstance().save();
                        if (UserConfig.INSTANCE.getInstance().getFmVersion().length() == 0) {
                            XLog.i("重新获取固件件版本号");
                            CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadFmRequest());
                        }
                    }
                };
                this.label = 1;
                if (heartRateDetailRepository.syncHistoryHeartDetail(deviceAddress, baseDeviceResult, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncHistoryHeartDetail() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06011(null), 3, null);
    }

    public final void syncTodayHeartSingleData() throws InterruptedException {
        final HeartRateDetailRepository heartRateDetailRepository = this.heartDetailRepository;
        String deviceFunctionList = UserConfig.INSTANCE.getInstance().getDeviceFunctionList();
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<DeviceFunctionList>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$lambda-10$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        DeviceFunctionList deviceFunctionList2 = (DeviceFunctionList) jsonAdapterAdapter.fromJson(deviceFunctionList);
        if (deviceFunctionList2 != null) {
            if (deviceFunctionList2.getManualHeart()) {
                heartRateDetailRepository.syncManualHeartRate(0, new ILargeDataManualHeartRateResponse() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$$ExternalSyntheticLambda2
                    @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse
                    public final void manualHeart(ManualHeartRate manualHeartRate) {
                        HealthyViewModel.m737syncTodayHeartSingleData$lambda10$lambda9(heartRateDetailRepository, manualHeartRate);
                    }
                });
                return;
            } else {
                heartRateDetailRepository.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$1$2
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadHeartRateRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel$syncTodayHeartSingleData$1$2, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$1$2$result$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel$syncTodayHeartSingleData$1$2 healthyViewModel$syncTodayHeartSingleData$1$2) {
                                invoke2(healthyViewModel$syncTodayHeartSingleData$1$2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(HealthyViewModel$syncTodayHeartSingleData$1$2 ktxRunOnBgSingle) {
                                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                EventBus.getDefault().post(new ManualRefreshEvent());
                            }
                        });
                    }
                });
                return;
            }
        }
        heartRateDetailRepository.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$1$3
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadHeartRateRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel$syncTodayHeartSingleData$1$3, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$1$3$result$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel$syncTodayHeartSingleData$1$3 healthyViewModel$syncTodayHeartSingleData$1$3) {
                        invoke2(healthyViewModel$syncTodayHeartSingleData$1$3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HealthyViewModel$syncTodayHeartSingleData$1$3 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncTodayHeartSingleData$lambda-10$lambda-9, reason: not valid java name */
    public static final void m737syncTodayHeartSingleData$lambda10$lambda9(HeartRateDetailRepository this_run, ManualHeartRate manualHeartRate) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.syncTodayHeartRate(new DateUtil(), new BaseDeviceResult<ReadHeartRateRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$1$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadHeartRateRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel$syncTodayHeartSingleData$1$1$1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayHeartSingleData$1$1$1$result$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel$syncTodayHeartSingleData$1$1$1 healthyViewModel$syncTodayHeartSingleData$1$1$1) {
                        invoke2(healthyViewModel$syncTodayHeartSingleData$1$1$1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HealthyViewModel$syncTodayHeartSingleData$1$1$1 ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        });
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBpSingle$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBpSingle$1, reason: invalid class name and case insensitive filesystem */
    static final class C05991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05991(Continuation<? super C05991> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C05991(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HealthyViewModel.this.bloodPressureRepository.syncAutoBp(new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBpSingle$1$1$1
                @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                public void result(int errorCode, ReadBlePressureRsp t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                    EventBus.getDefault().post(new ManualRefreshEvent());
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void syncBpSingle() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05991(null), 3, null);
    }

    public final void syncTodaySpo2Single(int offset) throws InterruptedException {
        this.bloodOxygenRepository.syncAutoBloodOxygen(offset, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodaySpo2Single$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadBlePressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    public final void syncTodayBloodSugar(int offset) throws InterruptedException {
        this.bloodSugarRepository.syncBloodSugar(offset, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayBloodSugar$1$1
            @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
            public void result(int errorCode, ReadBlePressureRsp t) {
                Intrinsics.checkNotNullParameter(t, "t");
                EventBus.getDefault().post(new ManualRefreshEvent());
            }
        });
    }

    public final void syncTodayStepDetailSingle() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodayStepDetailSingle.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.stepDetailRepository.syncTodayStepDetail(0, new BaseDeviceResult<ReadDetailSportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayStepDetailSingle$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, ReadDetailSportDataRsp t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        });
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodPressure$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodPressure$1, reason: invalid class name and case insensitive filesystem */
    static final class C05971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05971(Continuation<? super C05971> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C05971(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HealthyViewModel.this.bloodPressureRepository.syncAutoBp(new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodPressure$1$1$1
                @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                public void result(int errorCode, ReadBlePressureRsp t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                }
            });
            return Unit.INSTANCE;
        }
    }

    private final void syncBloodPressure() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05971(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodOxygen$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodOxygen$1, reason: invalid class name and case insensitive filesystem */
    static final class C05961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05961(Continuation<? super C05961> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C05961(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws InterruptedException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HealthyViewModel.this.bloodOxygenRepository.syncAutoBloodOxygen(255, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodOxygen$1$1$1
                @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                public void result(int errorCode, ReadBlePressureRsp t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                }
            });
            return Unit.INSTANCE;
        }
    }

    private final void syncBloodOxygen() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05961(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodSugar$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodSugar$1, reason: invalid class name and case insensitive filesystem */
    static final class C05981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05981(Continuation<? super C05981> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C05981(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws InterruptedException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HealthyViewModel.this.bloodSugarRepository.syncBloodSugar(255, new BaseDeviceResult<ReadBlePressureRsp>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncBloodSugar$1$1$1
                @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                public void result(int errorCode, ReadBlePressureRsp t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                }
            });
            return Unit.INSTANCE;
        }
    }

    private final void syncBloodSugar() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05981(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncManualHeart$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncManualHeart$1, reason: invalid class name and case insensitive filesystem */
    static final class C06061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06061(Continuation<? super C06061> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-1$lambda-0, reason: not valid java name */
        public static final void m740invokeSuspend$lambda1$lambda0(ManualHeartRate manualHeartRate) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06061(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws InterruptedException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HealthyViewModel.this.heartDetailRepository.syncManualHeartRate(new ILargeDataManualHeartRateResponse() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncManualHeart$1$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse
                public final void manualHeart(ManualHeartRate manualHeartRate) {
                    HealthyViewModel.C06061.m740invokeSuspend$lambda1$lambda0(manualHeartRate);
                }
            });
            return Unit.INSTANCE;
        }
    }

    private final void syncManualHeart() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06061(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTemperatureAuto$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTemperatureAuto$1, reason: invalid class name and case insensitive filesystem */
    static final class C06071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06071(Continuation<? super C06071> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06071(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TemperatureRepository temperatureRepository = HealthyViewModel.this.temperatureRepository;
            temperatureRepository.initData();
            XLog.i("---sync auto temp");
            temperatureRepository.syncAutoCheckTemperature();
            return Unit.INSTANCE;
        }
    }

    private final void syncTemperatureAuto() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06071(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTemperatureSingleCheck$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTemperatureSingleCheck$1, reason: invalid class name and case insensitive filesystem */
    static final class C06081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06081(Continuation<? super C06081> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06081(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HealthyViewModel.this.temperatureRepository.syncSingleCheckTemperature(6);
            XLog.i("---sync single check temp");
            return Unit.INSTANCE;
        }
    }

    private final void syncTemperatureSingleCheck() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06081(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayTemperatureSingle$1", f = "HealthyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayTemperatureSingle$1, reason: invalid class name and case insensitive filesystem */
    static final class C06181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06181(Continuation<? super C06181> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C06181(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final TemperatureRepository temperatureRepository = HealthyViewModel.this.temperatureRepository;
            HealthyViewModel healthyViewModel = HealthyViewModel.this;
            temperatureRepository.initData();
            XLog.i("---sync today temp");
            temperatureRepository.syncTodayTemperature();
            healthyViewModel.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodayTemperatureSingle$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    temperatureRepository.syncSingleCheckTemperature(0);
                }
            }, 800L);
            return Unit.INSTANCE;
        }
    }

    public final void syncTodayTemperatureSingle() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06181(null), 3, null);
    }

    public final void syncTodaySportPlusDetailSingle() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.syncTodaySportPlusDetailSingle.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.sportPlusRepository.syncTodaySportPlus((BaseDeviceResult) new BaseDeviceResult<List<? extends SportPlusEntity>>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$syncTodaySportPlusDetailSingle$1$1$1
                    @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                    public void result(int errorCode, List<? extends SportPlusEntity> t) {
                        Intrinsics.checkNotNullParameter(t, "t");
                        EventBus.getDefault().post(new ManualRefreshEvent());
                    }
                });
            }
        });
    }

    private final void getWatchDismissFile() throws InterruptedException {
        FileHandle.getInstance().initRegister();
        FileHandle.getInstance().removeCallback(this.callback);
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().start();
    }

    public final void queryHealthData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.queryHealthData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                HealthUI healthUI = new HealthUI();
                StepDetailRepository stepDetailRepository = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                healthUI.setTotalSteps(stepDetailRepository.queryStepTotal(y_m_d));
                StepDetailRepository stepDetailRepository2 = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                healthUI.setStepHourData(stepDetailRepository2.queryStepDetail(y_m_d2));
                healthUI.setRefreshType(1);
                ktxRunOnBgSingle._uiState.postValue(healthUI);
            }
        });
    }

    public final void queryInitHealthData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.queryInitHealthData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                HealthUI healthUI = new HealthUI();
                StepDetailRepository stepDetailRepository = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                healthUI.setTotalSteps(stepDetailRepository.queryStepTotal(y_m_d));
                StepDetailRepository stepDetailRepository2 = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                healthUI.setStepHourData(stepDetailRepository2.queryStepDetail(y_m_d2));
                healthUI.setRefreshType(2);
                ktxRunOnBgSingle._uiState.postValue(healthUI);
            }
        });
    }

    public final void queryNotificationHealthData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.queryNotificationHealthData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                HealthUI healthUI = new HealthUI();
                StepDetailRepository stepDetailRepository = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                healthUI.setTotalSteps(stepDetailRepository.queryStepTotal(y_m_d));
                StepDetailRepository stepDetailRepository2 = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                healthUI.setStepHourData(stepDetailRepository2.queryStepDetail(y_m_d2));
                healthUI.setRefreshType(3);
                ktxRunOnBgSingle._uiState.postValue(healthUI);
            }
        });
    }

    public final void queryStepAndDetail() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.queryStepAndDetail.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                HealthUI healthUI = new HealthUI();
                StepDetailRepository stepDetailRepository = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                healthUI.setTotalSteps(stepDetailRepository.queryStepTotal(y_m_d));
                StepDetailRepository stepDetailRepository2 = ktxRunOnBgSingle.stepDetailRepository;
                String y_m_d2 = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                healthUI.setStepHourData(stepDetailRepository2.queryStepDetail(y_m_d2));
                healthUI.setRefreshType(4);
                ktxRunOnBgSingle._uiState.postValue(healthUI);
            }
        });
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1", f = "HealthyViewModel.kt", i = {}, l = {770, 770}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $version;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$version = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new AnonymousClass1(this.$version, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: HealthyViewModel.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;", "", "emit", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01751<T> implements FlowCollector {
            final /* synthetic */ HealthyViewModel this$0;

            C01751(HealthyViewModel healthyViewModel) {
                this.this$0 = healthyViewModel;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState<java.lang.String> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.NumberFormatException {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$emit$1
                    if (r0 == 0) goto L14
                    r0 = r9
                    com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$emit$1 r0 = (com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L14
                    int r9 = r0.label
                    int r9 = r9 - r2
                    r0.label = r9
                    goto L19
                L14:
                    com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$emit$1 r0 = new com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$emit$1
                    r0.<init>(r7, r9)
                L19:
                    java.lang.Object r9 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L3f
                    if (r2 == r4) goto L35
                    if (r2 != r3) goto L2d
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L9c
                L2d:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r9)
                    throw r8
                L35:
                    int r8 = r0.I$0
                    java.lang.Object r2 = r0.L$0
                    com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1 r2 = (com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.AnonymousClass1.C01751) r2
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L85
                L3f:
                    kotlin.ResultKt.throwOnFailure(r9)
                    int r9 = r8.getRetCode()
                    if (r9 != 0) goto L9f
                    java.lang.Object r8 = r8.isSuccess()
                    java.lang.String r8 = java.lang.String.valueOf(r8)
                    int r8 = java.lang.Integer.parseInt(r8)
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r9 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r9 = r9.getInstance()
                    long r5 = (long) r8
                    r9.setWatchFaceMarketVersion(r5)
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r9 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r9 = r9.getInstance()
                    r9.save()
                    com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel r9 = r7.this$0
                    com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository r9 = com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.access$getHealthyRepository$p(r9)
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r2 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r2 = r2.getInstance()
                    java.lang.String r2 = r2.getDeviceAddressNoClear()
                    r0.L$0 = r7
                    r0.I$0 = r8
                    r0.label = r4
                    java.lang.Object r9 = r9.getLocalWatchFaceVersion(r2, r0)
                    if (r9 != r1) goto L84
                    return r1
                L84:
                    r2 = r7
                L85:
                    kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
                    com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$1 r4 = new com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$checkWatchFaceDownload$1$1$1
                    com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel r2 = r2.this$0
                    r4.<init>()
                    kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                    r8 = 0
                    r0.L$0 = r8
                    r0.label = r3
                    java.lang.Object r8 = r9.collect(r4, r0)
                    if (r8 != r1) goto L9c
                    return r1
                L9c:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                L9f:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.AnonymousClass1.C01751.emit(com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState, kotlin.coroutines.Continuation):java.lang.Object");
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((WatchFaceState<String>) obj, (Continuation<? super Unit>) continuation);
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = HealthyViewModel.this.watchFaceRepository.getWatchFaceServiceVersion(this.$version, this);
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
            if (((Flow) obj).collect(new C01751(HealthyViewModel.this), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkWatchFaceDownload(String version) {
        Intrinsics.checkNotNullParameter(version, "version");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(version, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getWatchFaceFromService(final String hardwareVersion) throws InterruptedException {
        ThreadExtKt.ktxRunOnBgSingleNetWork(this, new Function1<HealthyViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.getWatchFaceFromService.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: HealthyViewModel.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$getWatchFaceFromService$1$1", f = "HealthyViewModel.kt", i = {0, 0}, l = {798, 798}, m = "invokeSuspend", n = {"$this$launch", "version"}, s = {"L$0", "I$0"})
            /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$getWatchFaceFromService$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $hardwareVersion;
                final /* synthetic */ HealthyViewModel $this_ktxRunOnBgSingleNetWork;
                int I$0;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01781(HealthyViewModel healthyViewModel, String str, Continuation<? super C01781> continuation) {
                    super(2, continuation);
                    this.$this_ktxRunOnBgSingleNetWork = healthyViewModel;
                    this.$hardwareVersion = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C01781 c01781 = new C01781(this.$this_ktxRunOnBgSingleNetWork, this.$hardwareVersion, continuation);
                    c01781.L$0 = obj;
                    return c01781;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    final int watchFaceMarketVersion;
                    final CoroutineScope coroutineScope;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                        watchFaceMarketVersion = (int) UserConfig.INSTANCE.getInstance().getWatchFaceMarketVersion();
                        this.L$0 = coroutineScope2;
                        this.I$0 = watchFaceMarketVersion;
                        this.label = 1;
                        Object watchFaceFromServer = this.$this_ktxRunOnBgSingleNetWork.watchFaceRepository.getWatchFaceFromServer(this.$hardwareVersion, watchFaceMarketVersion, this);
                        if (watchFaceFromServer == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope = coroutineScope2;
                        obj = watchFaceFromServer;
                    } else {
                        if (i != 1) {
                            if (i == 2) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        watchFaceMarketVersion = this.I$0;
                        coroutineScope = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    final HealthyViewModel healthyViewModel = this.$this_ktxRunOnBgSingleNetWork;
                    this.L$0 = null;
                    this.label = 2;
                    if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.getWatchFaceFromService.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((WatchFaceState<List<WatchFaceResp>>) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(final WatchFaceState<List<WatchFaceResp>> watchFaceState, Continuation<? super Unit> continuation) {
                            if (watchFaceState.isSuccess() != null) {
                                CoroutineScope coroutineScope3 = coroutineScope;
                                final HealthyViewModel healthyViewModel2 = healthyViewModel;
                                final int i2 = watchFaceMarketVersion;
                                ThreadExtKt.ktxRunOnBgFix(coroutineScope3, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.getWatchFaceFromService.1.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope4) {
                                        invoke2(coroutineScope4);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(CoroutineScope ktxRunOnBgFix) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgFix, "$this$ktxRunOnBgFix");
                                        if (!QCApplication.INSTANCE.getGetInstance().getDownloadWatchFace()) {
                                            XLog.i("---准备下载表盘" + watchFaceState.isSuccess().size());
                                            healthyViewModel2.watchFaceRepository.downloadWatchFaceImageFile(watchFaceState.isSuccess(), i2);
                                            return;
                                        }
                                        XLog.i("---正在下载中");
                                    }
                                });
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HealthyViewModel healthyViewModel) {
                invoke2(healthyViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HealthyViewModel ktxRunOnBgSingleNetWork) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleNetWork, "$this$ktxRunOnBgSingleNetWork");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C01781(ktxRunOnBgSingleNetWork, hardwareVersion, null), 3, null);
            }
        });
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$getTokenNotLogin$1", f = "HealthyViewModel.kt", i = {}, l = {821, 821}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel$getTokenNotLogin$1, reason: invalid class name and case insensitive filesystem */
    static final class C05901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05901(Continuation<? super C05901> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthyViewModel.this.new C05901(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = HealthyViewModel.this.healthyRepository.getTokenNotLogin(this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel.getTokenNotLogin.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    UserConfig.INSTANCE.getInstance().setUserToken(String.valueOf(netState.isSuccess()));
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getTokenNotLogin() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05901(null), 3, null);
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel;)V", "onFileNames", "", "fileNames", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onFileNames(ArrayList<String> fileNames) throws Throwable {
            Intrinsics.checkNotNullParameter(fileNames, "fileNames");
            XLog.i(fileNames);
            HealthyViewModel.this.handler.removeMessages(1);
            if (!fileNames.isEmpty()) {
                HealthyViewModel.this.launchOnUI(new HealthyViewModel$Callback$onFileNames$1(HealthyViewModel.this, fileNames, null));
                return;
            }
            LogToFile.getInstance().wtf("手表不缺文件，开始同步总步数");
            FileHandle.getInstance().removeCallback(HealthyViewModel.this.callback);
            HealthyViewModel.this.syncTodayStep();
            HealthyViewModel.this.handler.sendEmptyMessageDelayed(2, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
            LogToFile.getInstance();
        }
    }

    /* compiled from: HealthyViewModel.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B%\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/vm/HealthyViewModel$HealthUI;", "", "()V", "errorCode", "", "total", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepTotal;", "stepHourData", "", "Lcom/qcwireless/qcwatch/ui/base/view/QStepBarChart$StepDataBean;", "(ILcom/qcwireless/qcwatch/ui/base/repository/entity/StepTotal;Ljava/util/List;)V", "getErrorCode", "()I", "setErrorCode", "(I)V", "refreshType", "getRefreshType", "setRefreshType", "getStepHourData", "()Ljava/util/List;", "setStepHourData", "(Ljava/util/List;)V", "totalSteps", "getTotalSteps", "()Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepTotal;", "setTotalSteps", "(Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepTotal;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class HealthUI {
        private int errorCode;
        private int refreshType;
        private List<? extends QStepBarChart.StepDataBean> stepHourData;
        private StepTotal totalSteps;

        public final int getErrorCode() {
            return this.errorCode;
        }

        public final void setErrorCode(int i) {
            this.errorCode = i;
        }

        public final StepTotal getTotalSteps() {
            return this.totalSteps;
        }

        public final void setTotalSteps(StepTotal stepTotal) {
            this.totalSteps = stepTotal;
        }

        public final List<QStepBarChart.StepDataBean> getStepHourData() {
            return this.stepHourData;
        }

        public final void setStepHourData(List<? extends QStepBarChart.StepDataBean> list) {
            this.stepHourData = list;
        }

        public final int getRefreshType() {
            return this.refreshType;
        }

        public final void setRefreshType(int i) {
            this.refreshType = i;
        }

        public HealthUI() {
        }

        public HealthUI(int i, StepTotal total, List<? extends QStepBarChart.StepDataBean> stepHourData) {
            Intrinsics.checkNotNullParameter(total, "total");
            Intrinsics.checkNotNullParameter(stepHourData, "stepHourData");
            this.errorCode = i;
            this.totalSteps = total;
            this.stepHourData = stepHourData;
        }
    }
}
