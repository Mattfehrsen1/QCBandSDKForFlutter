package com.qcwireless.qcwatch.ui.home.sport;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.work.WorkRequest;
import com.baidu.location.BDLocation;
import com.google.android.gms.fitness.FitnessActivities;
import com.hjq.permissions.Permission;
import com.luck.picture.lib.config.PictureConfig;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.PhoneSportReq;
import com.oudmon.ble.base.communication.responseImpl.DeviceSportNotifyListener;
import com.oudmon.ble.base.communication.rsp.AppSportRsp;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivitySportRunningBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.event.HealthItemRefreshEvent;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.base.view.gps.GpsEndView;
import com.qcwireless.qcwatch.ui.base.view.gps.LockView;
import com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.ws.WebSocketProtocol;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: SportRunningActivity.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0003012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0016J\u0012\u0010\"\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020 H\u0014J\u0010\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020(H\u0017J\b\u0010)\u001a\u00020 H\u0014J\b\u0010*\u001a\u00020 H\u0014J\u0006\u0010+\u001a\u00020 J\b\u0010,\u001a\u00020 H\u0002J\b\u0010-\u001a\u00020 H\u0002J\b\u0010.\u001a\u00020 H\u0002J\b\u0010/\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00060\u001eR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySportRunningBinding;", "durationTime", "", "fiveSecondTimeOutRunnable", "Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity$FiveSecondTimeOutRunnable;", "gpsResponse", "Lcom/oudmon/ble/base/communication/ICommandResponse;", "Lcom/oudmon/ble/base/communication/rsp/AppSportRsp;", "handler", "Landroid/os/Handler;", "myDeviceSportNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity$MyDeviceNotifyListener;", FitnessActivities.RUNNING, "sportDuration", "sportMap", "", "", "sportStatus", "sportType", "startAnim", "Landroid/view/animation/TranslateAnimation;", "startFlag", "", "startTime", "", "tenSecondTimeOutRunnable", "Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity$TenSecondTimeOutRunnable;", "endViewAnim", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "showGpsEnd", "showGpsExitDialog", "startViewAnim", "stopGps", "stopRun", "FiveSecondTimeOutRunnable", "MyDeviceNotifyListener", "TenSecondTimeOutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportRunningActivity extends BaseActivity {
    private ActivitySportRunningBinding binding;
    private int durationTime;
    private ICommandResponse<AppSportRsp> gpsResponse;
    private final Handler handler;
    private int running;
    private int sportDuration;
    private int sportStatus;
    private int sportType;
    private TranslateAnimation startAnim;
    private boolean startFlag;
    private long startTime;
    private final MyDeviceNotifyListener myDeviceSportNotifyListener = new MyDeviceNotifyListener();
    private Map<Integer, Integer[]> sportMap = new LinkedHashMap();
    private FiveSecondTimeOutRunnable fiveSecondTimeOutRunnable = new FiveSecondTimeOutRunnable();
    private TenSecondTimeOutRunnable tenSecondTimeOutRunnable = new TenSecondTimeOutRunnable();

    public SportRunningActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
        this.sportMap.put(4, new Integer[]{Integer.valueOf(R.string.qc_text_213), Integer.valueOf(R.mipmap.sport_buxing)});
        this.sportMap.put(5, new Integer[]{Integer.valueOf(R.string.qc_text_271), Integer.valueOf(R.mipmap.sport_tiaosheng)});
        this.sportMap.put(6, new Integer[]{Integer.valueOf(R.string.qc_text_338), Integer.valueOf(R.mipmap.sport_youyong)});
        this.sportMap.put(7, new Integer[]{Integer.valueOf(R.string.qc_text_295), Integer.valueOf(R.mipmap.sport_paobu)});
        Map<Integer, Integer[]> map = this.sportMap;
        Integer numValueOf = Integer.valueOf(R.mipmap.sport_tubu);
        map.put(8, new Integer[]{Integer.valueOf(R.string.qc_text_214), numValueOf});
        this.sportMap.put(9, new Integer[]{Integer.valueOf(R.string.qc_text_216), Integer.valueOf(R.mipmap.sport_qixing)});
        this.sportMap.put(10, new Integer[]{Integer.valueOf(R.string.qc_text_215), Integer.valueOf(R.mipmap.sport_duanlian)});
        this.sportMap.put(11, new Integer[]{Integer.valueOf(R.string.qc_text_217), Integer.valueOf(R.mipmap.sport_huipai)});
        this.sportMap.put(20, new Integer[]{Integer.valueOf(R.string.qc_text_339), numValueOf});
        this.sportMap.put(21, new Integer[]{Integer.valueOf(R.string.qc_text_340), Integer.valueOf(R.mipmap.sport_yumaoqiu)});
        this.sportMap.put(22, new Integer[]{Integer.valueOf(R.string.qc_text_341), Integer.valueOf(R.mipmap.sport_yujia)});
        this.sportMap.put(23, new Integer[]{Integer.valueOf(R.string.qc_text_342), Integer.valueOf(R.mipmap.sport_jianshencao)});
        this.sportMap.put(24, new Integer[]{Integer.valueOf(R.string.qc_text_343), Integer.valueOf(R.mipmap.sport_donggandanche)});
        this.sportMap.put(25, new Integer[]{Integer.valueOf(R.string.qc_text_344), Integer.valueOf(R.mipmap.sport_pihuating)});
        this.sportMap.put(26, new Integer[]{Integer.valueOf(R.string.qc_text_345), Integer.valueOf(R.mipmap.sport_tuoyuanji)});
        this.sportMap.put(27, new Integer[]{Integer.valueOf(R.string.qc_text_346), Integer.valueOf(R.mipmap.sport_huachuanji)});
        this.sportMap.put(28, new Integer[]{Integer.valueOf(R.string.qc_text_347), Integer.valueOf(R.mipmap.sport_pingpangq)});
        this.sportMap.put(29, new Integer[]{Integer.valueOf(R.string.qc_text_348), Integer.valueOf(R.mipmap.sport_wangqiu)});
        this.sportMap.put(30, new Integer[]{Integer.valueOf(R.string.qc_text_349), Integer.valueOf(R.mipmap.sport_gaoerfu)});
        this.sportMap.put(31, new Integer[]{Integer.valueOf(R.string.qc_text_350), Integer.valueOf(R.mipmap.sport_lanqiu)});
        this.sportMap.put(32, new Integer[]{Integer.valueOf(R.string.qc_text_351), Integer.valueOf(R.mipmap.sport_zuqiu)});
        this.sportMap.put(33, new Integer[]{Integer.valueOf(R.string.qc_text_352), Integer.valueOf(R.mipmap.sport_paiqiu)});
        this.sportMap.put(34, new Integer[]{Integer.valueOf(R.string.qc_text_353), Integer.valueOf(R.mipmap.sport_panyan)});
        this.sportMap.put(35, new Integer[]{Integer.valueOf(R.string.qc_text_354), Integer.valueOf(R.mipmap.sport_wudao)});
        this.sportMap.put(36, new Integer[]{Integer.valueOf(R.string.qc_text_355), Integer.valueOf(R.mipmap.sport_lunhua)});
        this.sportMap.put(40, new Integer[]{Integer.valueOf(R.string.qc_text_2590), Integer.valueOf(R.mipmap.ic_sport_40)});
        this.sportMap.put(41, new Integer[]{Integer.valueOf(R.string.qc_text_2615), Integer.valueOf(R.mipmap.ic_sport_41)});
        this.sportMap.put(42, new Integer[]{Integer.valueOf(R.string.qc_text_2654), Integer.valueOf(R.mipmap.ic_sport_42)});
        this.sportMap.put(43, new Integer[]{Integer.valueOf(R.string.qc_text_2576), Integer.valueOf(R.mipmap.ic_sport_43)});
        this.sportMap.put(44, new Integer[]{Integer.valueOf(R.string.qc_text_2526), Integer.valueOf(R.mipmap.ic_sport_44)});
        this.sportMap.put(45, new Integer[]{Integer.valueOf(R.string.qc_text_2567), Integer.valueOf(R.mipmap.ic_sport_45)});
        this.sportMap.put(50, new Integer[]{Integer.valueOf(R.string.qc_text_2557), Integer.valueOf(R.mipmap.ic_sport_50)});
        this.sportMap.put(51, new Integer[]{Integer.valueOf(R.string.qc_text_2618), Integer.valueOf(R.mipmap.ic_sport_51)});
        this.sportMap.put(52, new Integer[]{Integer.valueOf(R.string.qc_text_2613), Integer.valueOf(R.mipmap.ic_sport_52)});
        this.sportMap.put(53, new Integer[]{Integer.valueOf(R.string.qc_text_2645), Integer.valueOf(R.mipmap.ic_sport_53)});
        this.sportMap.put(55, new Integer[]{Integer.valueOf(R.string.qc_text_2651), Integer.valueOf(R.mipmap.ic_sport_55)});
        this.sportMap.put(56, new Integer[]{Integer.valueOf(R.string.qc_text_2620), Integer.valueOf(R.mipmap.ic_sport_56)});
        this.sportMap.put(57, new Integer[]{Integer.valueOf(R.string.qc_text_2597), Integer.valueOf(R.mipmap.ic_sport_57)});
        this.sportMap.put(58, new Integer[]{Integer.valueOf(R.string.qc_text_2559), Integer.valueOf(R.mipmap.ic_sport_58)});
        this.sportMap.put(60, new Integer[]{Integer.valueOf(R.string.qc_text_2558), Integer.valueOf(R.mipmap.ic_sport_60)});
        this.sportMap.put(61, new Integer[]{Integer.valueOf(R.string.qc_text_2537), Integer.valueOf(R.mipmap.ic_sport_61)});
        this.sportMap.put(62, new Integer[]{Integer.valueOf(R.string.qc_text_2535), Integer.valueOf(R.mipmap.ic_sport_62)});
        this.sportMap.put(63, new Integer[]{Integer.valueOf(R.string.qc_text_2528), Integer.valueOf(R.mipmap.ic_sport_63)});
        this.sportMap.put(64, new Integer[]{Integer.valueOf(R.string.qc_text_2560), Integer.valueOf(R.mipmap.ic_sport_64)});
        this.sportMap.put(65, new Integer[]{Integer.valueOf(R.string.qc_text_2591), Integer.valueOf(R.mipmap.ic_sport_65)});
        this.sportMap.put(66, new Integer[]{Integer.valueOf(R.string.qc_text_2610), Integer.valueOf(R.mipmap.ic_sport_66)});
        this.sportMap.put(67, new Integer[]{Integer.valueOf(R.string.qc_text_2653), Integer.valueOf(R.mipmap.ic_sport_67)});
        this.sportMap.put(68, new Integer[]{Integer.valueOf(R.string.qc_text_2607), Integer.valueOf(R.mipmap.ic_sport_68)});
        this.sportMap.put(69, new Integer[]{Integer.valueOf(R.string.qc_text_2622), Integer.valueOf(R.mipmap.ic_sport_69)});
        this.sportMap.put(70, new Integer[]{Integer.valueOf(R.string.qc_text_2584), Integer.valueOf(R.mipmap.ic_sport_70)});
        this.sportMap.put(71, new Integer[]{Integer.valueOf(R.string.qc_text_2657), Integer.valueOf(R.mipmap.ic_sport_71)});
        this.sportMap.put(80, new Integer[]{Integer.valueOf(R.string.qc_text_2589), Integer.valueOf(R.mipmap.ic_sport_80)});
        this.sportMap.put(81, new Integer[]{Integer.valueOf(R.string.qc_text_2627), Integer.valueOf(R.mipmap.ic_sport_81)});
        this.sportMap.put(82, new Integer[]{Integer.valueOf(R.string.qc_text_2563), Integer.valueOf(R.mipmap.ic_sport_82)});
        this.sportMap.put(83, new Integer[]{Integer.valueOf(R.string.qc_text_2525), Integer.valueOf(R.mipmap.ic_sport_83)});
        this.sportMap.put(84, new Integer[]{Integer.valueOf(R.string.qc_text_2553), Integer.valueOf(R.mipmap.ic_sport_84)});
        this.sportMap.put(85, new Integer[]{Integer.valueOf(R.string.qc_text_2573), Integer.valueOf(R.mipmap.ic_sport_85)});
        this.sportMap.put(86, new Integer[]{Integer.valueOf(R.string.qc_text_2617), Integer.valueOf(R.mipmap.ic_sport_86)});
        this.sportMap.put(87, new Integer[]{Integer.valueOf(R.string.qc_text_2638), Integer.valueOf(R.mipmap.ic_sport_87)});
        this.sportMap.put(88, new Integer[]{Integer.valueOf(R.string.qc_text_2582), Integer.valueOf(R.mipmap.ic_sport_88)});
        this.sportMap.put(89, new Integer[]{Integer.valueOf(R.string.qc_text_2566), Integer.valueOf(R.mipmap.ic_sport_89)});
        this.sportMap.put(90, new Integer[]{Integer.valueOf(R.string.qc_text_2672), Integer.valueOf(R.mipmap.ic_sport_90)});
        this.sportMap.put(91, new Integer[]{Integer.valueOf(R.string.qc_text_2605), Integer.valueOf(R.mipmap.ic_sport_91)});
        this.sportMap.put(92, new Integer[]{Integer.valueOf(R.string.qc_text_2634), Integer.valueOf(R.mipmap.ic_sport_92)});
        this.sportMap.put(93, new Integer[]{Integer.valueOf(R.string.qc_text_2580), Integer.valueOf(R.mipmap.ic_sport_93)});
        this.sportMap.put(94, new Integer[]{Integer.valueOf(R.string.qc_text_2596), Integer.valueOf(R.mipmap.ic_sport_94)});
        this.sportMap.put(95, new Integer[]{Integer.valueOf(R.string.qc_text_2530), Integer.valueOf(R.mipmap.ic_sport_95)});
        this.sportMap.put(96, new Integer[]{Integer.valueOf(R.string.qc_text_2625), Integer.valueOf(R.mipmap.ic_sport_96)});
        this.sportMap.put(97, new Integer[]{Integer.valueOf(R.string.qc_text_2656), Integer.valueOf(R.mipmap.ic_sport_97)});
        this.sportMap.put(98, new Integer[]{Integer.valueOf(R.string.qc_text_2570), Integer.valueOf(R.mipmap.ic_sport_98)});
        this.sportMap.put(99, new Integer[]{Integer.valueOf(R.string.qc_text_2595), Integer.valueOf(R.mipmap.ic_sport_99)});
        this.sportMap.put(100, new Integer[]{Integer.valueOf(R.string.qc_text_2628), Integer.valueOf(R.mipmap.ic_sport_100)});
        this.sportMap.put(110, new Integer[]{Integer.valueOf(R.string.qc_text_2550), Integer.valueOf(R.mipmap.ic_sport_110)});
        this.sportMap.put(111, new Integer[]{Integer.valueOf(R.string.qc_text_2574), Integer.valueOf(R.mipmap.ic_sport_111)});
        this.sportMap.put(112, new Integer[]{Integer.valueOf(R.string.qc_text_2539), Integer.valueOf(R.mipmap.ic_sport_112)});
        this.sportMap.put(113, new Integer[]{Integer.valueOf(R.string.qc_text_2516), Integer.valueOf(R.mipmap.ic_sport_113)});
        this.sportMap.put(114, new Integer[]{Integer.valueOf(R.string.qc_text_2575), Integer.valueOf(R.mipmap.ic_sport_114)});
        this.sportMap.put(115, new Integer[]{Integer.valueOf(R.string.qc_text_2673), Integer.valueOf(R.mipmap.ic_sport_115)});
        this.sportMap.put(116, new Integer[]{Integer.valueOf(R.string.qc_text_2579), Integer.valueOf(R.mipmap.ic_sport_116)});
        this.sportMap.put(117, new Integer[]{Integer.valueOf(R.string.qc_text_2577), Integer.valueOf(R.mipmap.ic_sport_117)});
        this.sportMap.put(118, new Integer[]{Integer.valueOf(R.string.qc_text_2642), Integer.valueOf(R.mipmap.ic_sport_118)});
        this.sportMap.put(119, new Integer[]{Integer.valueOf(R.string.qc_text_2548), Integer.valueOf(R.mipmap.ic_sport_119)});
        this.sportMap.put(120, new Integer[]{Integer.valueOf(R.string.qc_text_2592), Integer.valueOf(R.mipmap.ic_sport_120)});
        this.sportMap.put(121, new Integer[]{Integer.valueOf(R.string.qc_text_2588), Integer.valueOf(R.mipmap.ic_sport_121)});
        this.sportMap.put(122, new Integer[]{Integer.valueOf(R.string.qc_text_2641), Integer.valueOf(R.mipmap.ic_sport_122)});
        this.sportMap.put(123, new Integer[]{Integer.valueOf(R.string.qc_text_2643), Integer.valueOf(R.mipmap.ic_sport_123)});
        this.sportMap.put(124, new Integer[]{Integer.valueOf(R.string.qc_text_2533), Integer.valueOf(R.mipmap.ic_sport_124)});
        this.sportMap.put(125, new Integer[]{Integer.valueOf(R.string.qc_text_2633), Integer.valueOf(R.mipmap.ic_sport_125)});
        this.sportMap.put(Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), new Integer[]{Integer.valueOf(R.string.qc_text_2598), Integer.valueOf(R.mipmap.ic_sport_126)});
        this.sportMap.put(130, new Integer[]{Integer.valueOf(R.string.qc_text_2603), Integer.valueOf(R.mipmap.ic_sport_130)});
        this.sportMap.put(131, new Integer[]{Integer.valueOf(R.string.qc_text_2623), Integer.valueOf(R.mipmap.ic_sport_131)});
        this.sportMap.put(132, new Integer[]{Integer.valueOf(R.string.qc_text_2640), Integer.valueOf(R.mipmap.ic_sport_132)});
        this.sportMap.put(133, new Integer[]{Integer.valueOf(R.string.qc_text_2630), Integer.valueOf(R.mipmap.ic_sport_133)});
        this.sportMap.put(134, new Integer[]{Integer.valueOf(R.string.qc_text_2631), Integer.valueOf(R.mipmap.ic_sport_134)});
        this.sportMap.put(135, new Integer[]{Integer.valueOf(R.string.qc_text_2604), Integer.valueOf(R.mipmap.ic_sport_135)});
        this.sportMap.put(136, new Integer[]{Integer.valueOf(R.string.qc_text_2629), Integer.valueOf(R.mipmap.ic_sport_136)});
        this.sportMap.put(137, new Integer[]{Integer.valueOf(R.string.qc_text_2578), Integer.valueOf(R.mipmap.ic_sport_137)});
        this.sportMap.put(138, new Integer[]{Integer.valueOf(R.string.qc_text_2671), Integer.valueOf(R.mipmap.ic_sport_138)});
        this.sportMap.put(139, new Integer[]{Integer.valueOf(R.string.qc_text_2569), Integer.valueOf(R.mipmap.ic_sport_139)});
        this.sportMap.put(140, new Integer[]{Integer.valueOf(R.string.qc_text_2606), Integer.valueOf(R.mipmap.ic_sport_140)});
        Map<Integer, Integer[]> map2 = this.sportMap;
        Integer numValueOf2 = Integer.valueOf(R.string.qc_text_2568);
        map2.put(141, new Integer[]{numValueOf2, Integer.valueOf(R.mipmap.ic_sport_141)});
        this.sportMap.put(142, new Integer[]{numValueOf2, Integer.valueOf(R.mipmap.ic_sport_142)});
        this.sportMap.put(150, new Integer[]{Integer.valueOf(R.string.qc_text_2612), Integer.valueOf(R.mipmap.ic_sport_150)});
        this.sportMap.put(151, new Integer[]{Integer.valueOf(R.string.qc_text_2611), Integer.valueOf(R.mipmap.ic_sport_151)});
        this.sportMap.put(152, new Integer[]{Integer.valueOf(R.string.qc_text_2519), Integer.valueOf(R.mipmap.ic_sport_152)});
        this.sportMap.put(153, new Integer[]{Integer.valueOf(R.string.qc_text_2581), Integer.valueOf(R.mipmap.ic_sport_153)});
        this.sportMap.put(154, new Integer[]{Integer.valueOf(R.string.qc_text_2674), Integer.valueOf(R.mipmap.ic_sport_154)});
        this.sportMap.put(155, new Integer[]{Integer.valueOf(R.string.qc_text_2602), Integer.valueOf(R.mipmap.ic_sport_155)});
        this.sportMap.put(156, new Integer[]{Integer.valueOf(R.string.qc_text_2522), Integer.valueOf(R.mipmap.ic_sport_156)});
        this.sportMap.put(157, new Integer[]{Integer.valueOf(R.string.qc_text_2587), Integer.valueOf(R.mipmap.ic_sport_157)});
        this.sportMap.put(158, new Integer[]{Integer.valueOf(R.string.qc_text_2518), Integer.valueOf(R.mipmap.ic_sport_158)});
        this.sportMap.put(159, new Integer[]{Integer.valueOf(R.string.qc_text_2621), Integer.valueOf(R.mipmap.ic_sport_159)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeCoarseLocation), new Integer[]{Integer.valueOf(R.string.qc_text_2520), Integer.valueOf(R.mipmap.ic_sport_160)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeNetWorkLocation), new Integer[]{Integer.valueOf(R.string.qc_text_2585), Integer.valueOf(R.mipmap.ic_sport_161)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeServerDecryptError), new Integer[]{Integer.valueOf(R.string.qc_text_2599), Integer.valueOf(R.mipmap.ic_sport_162)});
        this.sportMap.put(163, new Integer[]{Integer.valueOf(R.string.qc_text_2658), Integer.valueOf(R.mipmap.ic_sport_163)});
        this.sportMap.put(164, new Integer[]{Integer.valueOf(R.string.qc_text_2632), Integer.valueOf(R.mipmap.ic_sport_164)});
        this.sportMap.put(165, new Integer[]{Integer.valueOf(R.string.qc_text_2540), Integer.valueOf(R.mipmap.ic_sport_165)});
        this.sportMap.put(166, new Integer[]{Integer.valueOf(R.string.qc_text_2626), Integer.valueOf(R.mipmap.ic_sport_166)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeServerError), new Integer[]{Integer.valueOf(R.string.qc_text_2524), Integer.valueOf(R.mipmap.ic_sport_167)});
        this.sportMap.put(168, new Integer[]{Integer.valueOf(R.string.qc_text_2571), Integer.valueOf(R.mipmap.ic_sport_168)});
        this.sportMap.put(169, new Integer[]{Integer.valueOf(R.string.qc_text_2619), Integer.valueOf(R.mipmap.ic_sport_169)});
        this.sportMap.put(170, new Integer[]{Integer.valueOf(R.string.qc_text_2608), Integer.valueOf(R.mipmap.ic_sport_170)});
        this.sportMap.put(171, new Integer[]{Integer.valueOf(R.string.qc_text_2532), Integer.valueOf(R.mipmap.ic_sport_171)});
        this.sportMap.put(172, new Integer[]{Integer.valueOf(R.string.qc_text_2562), Integer.valueOf(R.mipmap.ic_sport_172)});
        this.sportMap.put(173, new Integer[]{Integer.valueOf(R.string.qc_text_2531), Integer.valueOf(R.mipmap.ic_sport_173)});
        this.sportMap.put(174, new Integer[]{Integer.valueOf(R.string.qc_text_2515), Integer.valueOf(R.mipmap.ic_sport_174)});
        this.sportMap.put(175, new Integer[]{Integer.valueOf(R.string.qc_text_2593), Integer.valueOf(R.mipmap.ic_sport_175)});
        this.sportMap.put(180, new Integer[]{Integer.valueOf(R.string.qc_text_2556), Integer.valueOf(R.mipmap.ic_sport_180)});
        this.sportMap.put(181, new Integer[]{Integer.valueOf(R.string.qc_text_2541), Integer.valueOf(R.mipmap.ic_sport_181)});
        this.sportMap.put(182, new Integer[]{Integer.valueOf(R.string.qc_text_2583), Integer.valueOf(R.mipmap.ic_sport_182)});
        this.sportMap.put(183, new Integer[]{Integer.valueOf(R.string.qc_text_2527), Integer.valueOf(R.mipmap.ic_sport_183)});
        this.sportMap.put(184, new Integer[]{Integer.valueOf(R.string.qc_text_2545), Integer.valueOf(R.mipmap.ic_sport_184)});
        this.sportMap.put(185, new Integer[]{Integer.valueOf(R.string.qc_text_2561), Integer.valueOf(R.mipmap.ic_sport_185)});
        this.sportMap.put(186, new Integer[]{Integer.valueOf(R.string.qc_text_2572), Integer.valueOf(R.mipmap.ic_sport_186)});
        this.sportMap.put(187, new Integer[]{Integer.valueOf(R.string.qc_text_2616), Integer.valueOf(R.mipmap.ic_sport_187)});
        this.sportMap.put(Integer.valueOf(PictureConfig.CHOOSE_REQUEST), new Integer[]{Integer.valueOf(R.string.qc_text_2594), Integer.valueOf(R.mipmap.ic_sport_188)});
        this.sportMap.put(189, new Integer[]{Integer.valueOf(R.string.qc_text_2546), Integer.valueOf(R.mipmap.ic_sport_189)});
        this.sportMap.put(190, new Integer[]{Integer.valueOf(R.string.qc_text_2624), Integer.valueOf(R.mipmap.ic_sport_190)});
        this.sportMap.put(191, new Integer[]{Integer.valueOf(R.string.qc_text_2529), Integer.valueOf(R.mipmap.ic_sport_191)});
        this.sportMap.put(192, new Integer[]{Integer.valueOf(R.string.qc_text_2549), Integer.valueOf(R.mipmap.ic_sport_192)});
        this.sportMap.put(193, new Integer[]{Integer.valueOf(R.string.qc_text_2652), Integer.valueOf(R.mipmap.ic_sport_193)});
        this.sportMap.put(194, new Integer[]{Integer.valueOf(R.string.qc_text_2536), Integer.valueOf(R.mipmap.ic_sport_194)});
        this.sportMap.put(195, new Integer[]{Integer.valueOf(R.string.qc_text_2538), Integer.valueOf(R.mipmap.ic_sport_195)});
        this.sportMap.put(196, new Integer[]{Integer.valueOf(R.string.qc_text_2555), Integer.valueOf(R.mipmap.ic_sport_196)});
        this.sportMap.put(197, new Integer[]{Integer.valueOf(R.string.qc_text_2675), Integer.valueOf(R.mipmap.ic_sport_197)});
        this.sportMap.put(198, new Integer[]{Integer.valueOf(R.string.qc_text_2523), Integer.valueOf(R.mipmap.ic_sport_198)});
        this.sportMap.put(199, new Integer[]{Integer.valueOf(R.string.qc_text_2646), Integer.valueOf(R.mipmap.ic_sport_199)});
        this.sportMap.put(200, new Integer[]{Integer.valueOf(R.string.qc_text_2648), Integer.valueOf(R.mipmap.ic_sport_200)});
        this.sportMap.put(Integer.valueOf(CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE), new Integer[]{Integer.valueOf(R.string.qc_text_2647), Integer.valueOf(R.mipmap.ic_sport_201)});
        this.sportMap.put(202, new Integer[]{Integer.valueOf(R.string.qc_text_2649), Integer.valueOf(R.mipmap.ic_sport_202)});
        this.sportMap.put(210, new Integer[]{Integer.valueOf(R.string.qc_text_2554), Integer.valueOf(R.mipmap.ic_sport_210)});
        this.sportMap.put(211, new Integer[]{Integer.valueOf(R.string.qc_text_2544), Integer.valueOf(R.mipmap.ic_sport_211)});
        this.sportMap.put(Integer.valueOf(AudioEq.PARSE_EQ_DATA_LENGTH), new Integer[]{Integer.valueOf(R.string.qc_text_2543), Integer.valueOf(R.mipmap.ic_sport_212)});
        this.sportMap.put(213, new Integer[]{Integer.valueOf(R.string.qc_text_2542), Integer.valueOf(R.mipmap.ic_sport_213)});
        this.sportMap.put(214, new Integer[]{Integer.valueOf(R.string.qc_text_2517), Integer.valueOf(R.mipmap.ic_sport_214)});
        this.sportMap.put(215, new Integer[]{Integer.valueOf(R.string.qc_text_2534), Integer.valueOf(R.mipmap.ic_sport_215)});
        this.sportMap.put(216, new Integer[]{Integer.valueOf(R.string.qc_text_2586), Integer.valueOf(R.mipmap.ic_sport_216)});
        this.sportMap.put(217, new Integer[]{Integer.valueOf(R.string.qc_text_2601), Integer.valueOf(R.mipmap.ic_sport_217)});
        this.sportMap.put(218, new Integer[]{Integer.valueOf(R.string.qc_text_2609), Integer.valueOf(R.mipmap.ic_sport_218)});
        this.sportMap.put(219, new Integer[]{Integer.valueOf(R.string.qc_text_2659), Integer.valueOf(R.mipmap.ic_sport_219)});
        this.sportMap.put(220, new Integer[]{Integer.valueOf(R.string.qc_text_2635), Integer.valueOf(R.mipmap.ic_sport_220)});
        this.sportMap.put(221, new Integer[]{Integer.valueOf(R.string.qc_text_2521), Integer.valueOf(R.mipmap.ic_sport_221)});
        this.sportMap.put(222, new Integer[]{Integer.valueOf(R.string.qc_text_2636), Integer.valueOf(R.mipmap.ic_sport_222)});
        this.sportMap.put(223, new Integer[]{Integer.valueOf(R.string.qc_text_2514), Integer.valueOf(R.mipmap.ic_sport_223)});
        this.sportMap.put(224, new Integer[]{Integer.valueOf(R.string.qc_text_2650), Integer.valueOf(R.mipmap.ic_sport_224)});
        this.sportMap.put(225, new Integer[]{Integer.valueOf(R.string.qc_text_2655), Integer.valueOf(R.mipmap.ic_sport_225)});
        this.sportMap.put(230, new Integer[]{Integer.valueOf(R.string.qc_text_2552), Integer.valueOf(R.mipmap.ic_sport_230)});
        this.sportMap.put(231, new Integer[]{Integer.valueOf(R.string.qc_text_2639), Integer.valueOf(R.mipmap.ic_sport_231)});
        this.sportMap.put(232, new Integer[]{Integer.valueOf(R.string.qc_text_2551), Integer.valueOf(R.mipmap.ic_sport_232)});
        this.sportMap.put(233, new Integer[]{Integer.valueOf(R.string.qc_text_2670), Integer.valueOf(R.mipmap.ic_sport_233)});
        this.sportMap.put(234, new Integer[]{Integer.valueOf(R.string.qc_text_2600), Integer.valueOf(R.mipmap.ic_sport_234)});
        this.sportMap.put(235, new Integer[]{Integer.valueOf(R.string.qc_text_2637), Integer.valueOf(R.mipmap.ic_sport_235)});
        this.sportMap.put(236, new Integer[]{Integer.valueOf(R.string.qc_text_2614), Integer.valueOf(R.mipmap.ic_sport_236)});
        this.sportMap.put(237, new Integer[]{Integer.valueOf(R.string.qc_text_2547), Integer.valueOf(R.mipmap.ic_sport_237)});
        this.sportMap.put(238, new Integer[]{Integer.valueOf(R.string.qc_text_2565), Integer.valueOf(R.mipmap.ic_sport_238)});
        this.gpsResponse = new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                SportRunningActivity.m841gpsResponse$lambda7(this.f$0, (AppSportRsp) baseRspCmd);
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySportRunningBinding activitySportRunningBindingInflate = ActivitySportRunningBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySportRunningBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySportRunningBindingInflate;
        if (activitySportRunningBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBindingInflate = null;
        }
        ConstraintLayout root = activitySportRunningBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        ActivitySportRunningBinding activitySportRunningBinding = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("sportType")) : null;
        Intrinsics.checkNotNull(numValueOf);
        this.sportType = numValueOf.intValue();
        Bundle extras2 = getIntent().getExtras();
        Integer numValueOf2 = extras2 != null ? Integer.valueOf(extras2.getInt(FitnessActivities.RUNNING)) : null;
        Intrinsics.checkNotNull(numValueOf2);
        this.running = numValueOf2.intValue();
        Bundle extras3 = getIntent().getExtras();
        Integer numValueOf3 = extras3 != null ? Integer.valueOf(extras3.getInt("sportStatus")) : null;
        Intrinsics.checkNotNull(numValueOf3);
        this.sportStatus = numValueOf3.intValue();
        ActivitySportRunningBinding activitySportRunningBinding2 = this.binding;
        if (activitySportRunningBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding2 = null;
        }
        TextView textView = activitySportRunningBinding2.titleBar.tvTitle;
        Integer[] numArr = this.sportMap.get(Integer.valueOf(this.sportType));
        textView.setText(numArr != null ? getString(numArr[0].intValue()) : null);
        if (this.running >= 0) {
            CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 1, (byte) this.sportType), this.gpsResponse);
        }
        int i = this.sportType;
        if (i == 4 || i == 7 || i == 8) {
            ActivitySportRunningBinding activitySportRunningBinding3 = this.binding;
            if (activitySportRunningBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportRunningBinding3 = null;
            }
            ViewKt.visible(activitySportRunningBinding3.gpsDistance);
            ActivitySportRunningBinding activitySportRunningBinding4 = this.binding;
            if (activitySportRunningBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportRunningBinding4 = null;
            }
            ViewKt.visible(activitySportRunningBinding4.gpsStep);
        } else {
            ActivitySportRunningBinding activitySportRunningBinding5 = this.binding;
            if (activitySportRunningBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportRunningBinding5 = null;
            }
            ViewKt.gone(activitySportRunningBinding5.gpsDistance);
            ActivitySportRunningBinding activitySportRunningBinding6 = this.binding;
            if (activitySportRunningBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportRunningBinding6 = null;
            }
            ViewKt.gone(activitySportRunningBinding6.gpsStep);
        }
        ActivitySportRunningBinding activitySportRunningBinding7 = this.binding;
        if (activitySportRunningBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding7 = null;
        }
        activitySportRunningBinding7.imageStop.setListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportRunningActivity.m842setupViews$lambda1(this.f$0, view);
            }
        });
        ActivitySportRunningBinding activitySportRunningBinding8 = this.binding;
        if (activitySportRunningBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding8 = null;
        }
        activitySportRunningBinding8.imageEnd.setListener(new GpsEndView.GpsEndListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.ui.base.view.gps.GpsEndView.GpsEndListener
            public final void gpsEnd() {
                SportRunningActivity.m843setupViews$lambda2(this.f$0);
            }
        });
        ActivitySportRunningBinding activitySportRunningBinding9 = this.binding;
        if (activitySportRunningBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding9 = null;
        }
        activitySportRunningBinding9.imageStop.setEndListener(new LockView.EndListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.ui.base.view.gps.LockView.EndListener
            public final void onEnd() {
                SportRunningActivity.m844setupViews$lambda3(this.f$0);
            }
        });
        View[] viewArr = new View[4];
        ActivitySportRunningBinding activitySportRunningBinding10 = this.binding;
        if (activitySportRunningBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding10 = null;
        }
        viewArr[0] = activitySportRunningBinding10.titleBar.ivNavigateBefore;
        ActivitySportRunningBinding activitySportRunningBinding11 = this.binding;
        if (activitySportRunningBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding11 = null;
        }
        viewArr[1] = activitySportRunningBinding11.imageContinue;
        ActivitySportRunningBinding activitySportRunningBinding12 = this.binding;
        if (activitySportRunningBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding12 = null;
        }
        viewArr[2] = activitySportRunningBinding12.imageScreenLock;
        ActivitySportRunningBinding activitySportRunningBinding13 = this.binding;
        if (activitySportRunningBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding13 = null;
        }
        viewArr[3] = activitySportRunningBinding13.viewTopOfLayout;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity.setupViews.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivitySportRunningBinding activitySportRunningBinding14 = SportRunningActivity.this.binding;
                ActivitySportRunningBinding activitySportRunningBinding15 = null;
                if (activitySportRunningBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySportRunningBinding14 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activitySportRunningBinding14.titleBar.ivNavigateBefore)) {
                    SportRunningActivity.this.showGpsExitDialog();
                    return;
                }
                ActivitySportRunningBinding activitySportRunningBinding16 = SportRunningActivity.this.binding;
                if (activitySportRunningBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySportRunningBinding16 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activitySportRunningBinding16.imageContinue)) {
                    ActivitySportRunningBinding activitySportRunningBinding17 = SportRunningActivity.this.binding;
                    if (activitySportRunningBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySportRunningBinding17 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activitySportRunningBinding17.imageScreenLock)) {
                        ActivitySportRunningBinding activitySportRunningBinding18 = SportRunningActivity.this.binding;
                        if (activitySportRunningBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySportRunningBinding18 = null;
                        }
                        activitySportRunningBinding18.imageStop.setLock(true);
                        ActivitySportRunningBinding activitySportRunningBinding19 = SportRunningActivity.this.binding;
                        if (activitySportRunningBinding19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySportRunningBinding19 = null;
                        }
                        ViewKt.visible(activitySportRunningBinding19.tvLabelUnlock);
                        ActivitySportRunningBinding activitySportRunningBinding20 = SportRunningActivity.this.binding;
                        if (activitySportRunningBinding20 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySportRunningBinding20 = null;
                        }
                        activitySportRunningBinding20.imageStop.flipAnimator(1);
                        ActivitySportRunningBinding activitySportRunningBinding21 = SportRunningActivity.this.binding;
                        if (activitySportRunningBinding21 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySportRunningBinding21 = null;
                        }
                        ViewKt.gone(activitySportRunningBinding21.imageScreenLock);
                        ActivitySportRunningBinding activitySportRunningBinding22 = SportRunningActivity.this.binding;
                        if (activitySportRunningBinding22 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activitySportRunningBinding15 = activitySportRunningBinding22;
                        }
                        ViewKt.visible(activitySportRunningBinding15.viewTopOfLayout);
                        return;
                    }
                    ActivitySportRunningBinding activitySportRunningBinding23 = SportRunningActivity.this.binding;
                    if (activitySportRunningBinding23 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activitySportRunningBinding15 = activitySportRunningBinding23;
                    }
                    Intrinsics.areEqual(setOnClickListener, activitySportRunningBinding15.viewTopOfLayout);
                    return;
                }
                CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 3, (byte) SportRunningActivity.this.sportType), SportRunningActivity.this.gpsResponse);
                ActivitySportRunningBinding activitySportRunningBinding24 = SportRunningActivity.this.binding;
                if (activitySportRunningBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySportRunningBinding24 = null;
                }
                ViewKt.gone(activitySportRunningBinding24.tvLabelEnd);
                ActivitySportRunningBinding activitySportRunningBinding25 = SportRunningActivity.this.binding;
                if (activitySportRunningBinding25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySportRunningBinding15 = activitySportRunningBinding25;
                }
                ViewKt.visible(activitySportRunningBinding15.imageScreenLock);
                SportRunningActivity.this.startViewAnim();
            }
        });
        if (this.sportStatus == 2) {
            ActivitySportRunningBinding activitySportRunningBinding14 = this.binding;
            if (activitySportRunningBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySportRunningBinding = activitySportRunningBinding14;
            }
            activitySportRunningBinding.imageContinue.performClick();
        } else {
            this.handler.removeCallbacks(this.fiveSecondTimeOutRunnable);
            this.handler.removeCallbacks(this.tenSecondTimeOutRunnable);
            this.handler.postDelayed(this.fiveSecondTimeOutRunnable, 5000L);
            this.handler.postDelayed(this.tenSecondTimeOutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
        }
        ActivityCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m842setupViews$lambda1(SportRunningActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySportRunningBinding activitySportRunningBinding = this$0.binding;
        ActivitySportRunningBinding activitySportRunningBinding2 = null;
        if (activitySportRunningBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding = null;
        }
        activitySportRunningBinding.imageStop.setVisibility(4);
        ActivitySportRunningBinding activitySportRunningBinding3 = this$0.binding;
        if (activitySportRunningBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding3 = null;
        }
        ViewKt.visible(activitySportRunningBinding3.imageContinue);
        ActivitySportRunningBinding activitySportRunningBinding4 = this$0.binding;
        if (activitySportRunningBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding4 = null;
        }
        ViewKt.visible(activitySportRunningBinding4.imageEnd);
        ActivitySportRunningBinding activitySportRunningBinding5 = this$0.binding;
        if (activitySportRunningBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding5 = null;
        }
        ViewKt.gone(activitySportRunningBinding5.imageScreenLock);
        ActivitySportRunningBinding activitySportRunningBinding6 = this$0.binding;
        if (activitySportRunningBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportRunningBinding2 = activitySportRunningBinding6;
        }
        activitySportRunningBinding2.imageStop.setEnabled(false);
        this$0.endViewAnim();
        CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 2, (byte) this$0.sportType), this$0.gpsResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m843setupViews$lambda2(SportRunningActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.sportDuration / 60 < 1) {
            this$0.showGpsExitDialog();
        } else {
            this$0.stopRun();
            CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 4, (byte) this$0.sportType), this$0.gpsResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m844setupViews$lambda3(SportRunningActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySportRunningBinding activitySportRunningBinding = this$0.binding;
        ActivitySportRunningBinding activitySportRunningBinding2 = null;
        if (activitySportRunningBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding = null;
        }
        activitySportRunningBinding.imageStop.setLock(false);
        ActivitySportRunningBinding activitySportRunningBinding3 = this$0.binding;
        if (activitySportRunningBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding3 = null;
        }
        ViewKt.gone(activitySportRunningBinding3.tvLabelUnlock);
        ActivitySportRunningBinding activitySportRunningBinding4 = this$0.binding;
        if (activitySportRunningBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding4 = null;
        }
        ViewKt.visible(activitySportRunningBinding4.imageScreenLock);
        ActivitySportRunningBinding activitySportRunningBinding5 = this$0.binding;
        if (activitySportRunningBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportRunningBinding2 = activitySportRunningBinding5;
        }
        ViewKt.gone(activitySportRunningBinding2.viewTopOfLayout);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BleOperateManager.getInstance().addSportDeviceListener(120, this.myDeviceSportNotifyListener);
        ActivitySportRunningBinding activitySportRunningBinding = null;
        if (!BleOperateManager.getInstance().isConnected()) {
            ActivitySportRunningBinding activitySportRunningBinding2 = this.binding;
            if (activitySportRunningBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportRunningBinding2 = null;
            }
            activitySportRunningBinding2.imageBleStatus.setImageResource(R.mipmap.ble_disconnect);
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        ActivitySportRunningBinding activitySportRunningBinding3 = this.binding;
        if (activitySportRunningBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportRunningBinding = activitySportRunningBinding3;
        }
        activitySportRunningBinding.imageBleStatus.setImageResource(R.mipmap.ble_connect);
    }

    private final void stopRun() {
        finish();
    }

    /* compiled from: SportRunningActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceSportNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceSportNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceSportNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) {
            super.onDataResponse(resultEntity);
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0) {
                final int iBytes2Int = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[2], resultEntity.getLoadData()[3]});
                final int iBytes2Int2 = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[4]});
                final int iBytes2Int3 = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[5], resultEntity.getLoadData()[6], resultEntity.getLoadData()[7]});
                final int iBytes2Int4 = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[8], resultEntity.getLoadData()[9], resultEntity.getLoadData()[10]});
                final int iBytes2Int5 = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[11], resultEntity.getLoadData()[12], resultEntity.getLoadData()[13]});
                final int iBytes2Int6 = BLEDataFormatUtils.bytes2Int(new byte[]{resultEntity.getLoadData()[1]});
                if (iBytes2Int6 == 3) {
                    final SportRunningActivity sportRunningActivity = SportRunningActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<MyDeviceNotifyListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$MyDeviceNotifyListener$onDataResponse$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SportRunningActivity.MyDeviceNotifyListener myDeviceNotifyListener) {
                            invoke2(myDeviceNotifyListener);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SportRunningActivity.MyDeviceNotifyListener ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = sportRunningActivity.getString(R.string.qc_text_6666040);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_6666040)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                            sportRunningActivity.finish();
                        }
                    });
                }
                final SportRunningActivity sportRunningActivity2 = SportRunningActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<MyDeviceNotifyListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$MyDeviceNotifyListener$onDataResponse$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SportRunningActivity.MyDeviceNotifyListener myDeviceNotifyListener) {
                        invoke2(myDeviceNotifyListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SportRunningActivity.MyDeviceNotifyListener ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivitySportRunningBinding activitySportRunningBinding = sportRunningActivity2.binding;
                        if (activitySportRunningBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySportRunningBinding = null;
                        }
                        int i = iBytes2Int6;
                        SportRunningActivity sportRunningActivity3 = sportRunningActivity2;
                        int i2 = iBytes2Int;
                        int i3 = iBytes2Int4;
                        int i4 = iBytes2Int3;
                        int i5 = iBytes2Int5;
                        int i6 = iBytes2Int2;
                        if (i == 1 && !sportRunningActivity3.startFlag) {
                            sportRunningActivity3.startFlag = true;
                            activitySportRunningBinding.imageContinue.performClick();
                        }
                        activitySportRunningBinding.timeDuration.setText(DateUtil.minsToHHmmdd(i2));
                        sportRunningActivity3.sportDuration = i2;
                        try {
                            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                                String str = String.valueOf(new BigDecimal(String.valueOf((i3 * 1.0f) / 1000)).setScale(2, RoundingMode.HALF_UP));
                                QSportItemView qSportItemView = activitySportRunningBinding.gpsDistance;
                                Intrinsics.checkNotNullExpressionValue(str, "str");
                                qSportItemView.setItemTitle(str);
                                QSportItemView qSportItemView2 = activitySportRunningBinding.gpsDistance;
                                String string = sportRunningActivity3.getString(R.string.qc_text_88);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
                                qSportItemView2.setItemUnit(string);
                            } else {
                                String str2 = String.valueOf(new BigDecimal(String.valueOf(MetricUtilsKt.kmToIn((i3 * 1.0f) / 1000))).setScale(2, RoundingMode.HALF_UP));
                                QSportItemView qSportItemView3 = activitySportRunningBinding.gpsDistance;
                                Intrinsics.checkNotNullExpressionValue(str2, "str");
                                qSportItemView3.setItemTitle(str2);
                                QSportItemView qSportItemView4 = activitySportRunningBinding.gpsDistance;
                                String string2 = sportRunningActivity3.getString(R.string.qc_text_358);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
                                qSportItemView4.setItemUnit(string2);
                            }
                        } catch (Exception unused) {
                        }
                        if (i4 <= 0) {
                            activitySportRunningBinding.gpsStep.setItemTitle("--");
                        } else {
                            activitySportRunningBinding.gpsStep.setItemTitle(String.valueOf(i4));
                        }
                        int i7 = i5 / 1000;
                        if (i7 <= 0) {
                            activitySportRunningBinding.gpsCalorie.setItemTitle("--");
                        } else {
                            activitySportRunningBinding.gpsCalorie.setItemTitle(String.valueOf(i7));
                        }
                        if (i3 <= 0) {
                            activitySportRunningBinding.gpsDistance.setItemTitle("--");
                        }
                        if (i6 > 0) {
                            activitySportRunningBinding.gpsHeart.setItemTitle(String.valueOf(i6));
                        } else {
                            activitySportRunningBinding.gpsHeart.setItemTitle("--");
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showGpsExitDialog() {
        String string;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if (this.sportDuration / 60 < 1) {
            booleanRef.element = false;
            string = getString(R.string.qwatchpro_text_14);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qwatchpro_text_14)");
        } else {
            booleanRef.element = true;
            string = getString(R.string.qc_text_279);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_279)");
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_gps_exit);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_title);
        TextView textView3 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        TextView textView4 = (TextView) contentView.findViewById(R.id.tv_content);
        textView2.setText("");
        textView4.setText(string);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportRunningActivity.m845showGpsExitDialog$lambda4(objectRef, view);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportRunningActivity.m846showGpsExitDialog$lambda6(booleanRef, this, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showGpsExitDialog$lambda-4, reason: not valid java name */
    public static final void m845showGpsExitDialog$lambda4(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showGpsExitDialog$lambda-6, reason: not valid java name */
    public static final void m846showGpsExitDialog$lambda6(Ref.BooleanRef exitSave, final SportRunningActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(exitSave, "$exitSave");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        boolean z = exitSave.element;
        if (BleOperateManager.getInstance().isConnected()) {
            CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 4, (byte) this$0.sportType), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$$ExternalSyntheticLambda4
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    SportRunningActivity.m847showGpsExitDialog$lambda6$lambda5(this.f$0, (AppSportRsp) baseRspCmd);
                }
            });
        } else {
            this$0.finish();
        }
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showGpsExitDialog$lambda-6$lambda-5, reason: not valid java name */
    public static final void m847showGpsExitDialog$lambda6$lambda5(SportRunningActivity this$0, AppSportRsp appSportRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
        EventBus.getDefault().post(new HealthItemRefreshEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: gpsResponse$lambda-7, reason: not valid java name */
    public static final void m841gpsResponse$lambda7(SportRunningActivity this$0, AppSportRsp appSportRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (appSportRsp != null) {
            try {
                int gpsStatus = appSportRsp.getGpsStatus();
                if (gpsStatus == 2) {
                    ThreadExtKt.ktxRunOnUi(this$0, new Function1<SportRunningActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$gpsResponse$1$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SportRunningActivity sportRunningActivity) {
                            invoke2(sportRunningActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SportRunningActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivitySportRunningBinding activitySportRunningBinding = ktxRunOnUi.binding;
                            ActivitySportRunningBinding activitySportRunningBinding2 = null;
                            if (activitySportRunningBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySportRunningBinding = null;
                            }
                            activitySportRunningBinding.imageStop.setVisibility(4);
                            ActivitySportRunningBinding activitySportRunningBinding3 = ktxRunOnUi.binding;
                            if (activitySportRunningBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySportRunningBinding3 = null;
                            }
                            ViewKt.visible(activitySportRunningBinding3.imageContinue);
                            ActivitySportRunningBinding activitySportRunningBinding4 = ktxRunOnUi.binding;
                            if (activitySportRunningBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySportRunningBinding4 = null;
                            }
                            ViewKt.visible(activitySportRunningBinding4.imageEnd);
                            ActivitySportRunningBinding activitySportRunningBinding5 = ktxRunOnUi.binding;
                            if (activitySportRunningBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySportRunningBinding5 = null;
                            }
                            ViewKt.gone(activitySportRunningBinding5.imageScreenLock);
                            ActivitySportRunningBinding activitySportRunningBinding6 = ktxRunOnUi.binding;
                            if (activitySportRunningBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activitySportRunningBinding2 = activitySportRunningBinding6;
                            }
                            activitySportRunningBinding2.imageStop.setEnabled(false);
                            ktxRunOnUi.endViewAnim();
                        }
                    });
                    return;
                }
                if (gpsStatus != 3) {
                    if (gpsStatus != 4) {
                        if (gpsStatus != 6) {
                            return;
                        }
                        this$0.startTime = appSportRsp.getTimeStamp();
                        return;
                    } else {
                        if (this$0.sportDuration / 60 >= 1) {
                            ThreadExtKt.ktxRunOnUi(this$0, new Function1<SportRunningActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity$gpsResponse$1$2
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SportRunningActivity sportRunningActivity) {
                                    invoke2(sportRunningActivity);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SportRunningActivity ktxRunOnUi) {
                                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                    ktxRunOnUi.stopGps();
                                }
                            });
                            return;
                        }
                        return;
                    }
                }
                ActivitySportRunningBinding activitySportRunningBinding = this$0.binding;
                ActivitySportRunningBinding activitySportRunningBinding2 = null;
                if (activitySportRunningBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySportRunningBinding = null;
                }
                ViewKt.gone(activitySportRunningBinding.tvLabelEnd);
                ActivitySportRunningBinding activitySportRunningBinding3 = this$0.binding;
                if (activitySportRunningBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySportRunningBinding2 = activitySportRunningBinding3;
                }
                ViewKt.visible(activitySportRunningBinding2.imageScreenLock);
                this$0.startViewAnim();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopGps() {
        CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 6, (byte) this.sportType), this.gpsResponse);
        CommandHandle.getInstance().executeReqCmd(PhoneSportReq.getSportStatus((byte) 4, (byte) this.sportType), this.gpsResponse);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof BluetoothEvent) {
            try {
                ActivitySportRunningBinding activitySportRunningBinding = null;
                if (!((BluetoothEvent) messageEvent).getConnect()) {
                    this.startFlag = false;
                    ActivitySportRunningBinding activitySportRunningBinding2 = this.binding;
                    if (activitySportRunningBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySportRunningBinding2 = null;
                    }
                    activitySportRunningBinding2.imageBleStatus.setImageResource(R.mipmap.ble_disconnect);
                    String string = getString(R.string.qc_text_75);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    return;
                }
                this.handler.removeCallbacks(this.fiveSecondTimeOutRunnable);
                this.handler.removeCallbacks(this.tenSecondTimeOutRunnable);
                this.handler.postDelayed(this.fiveSecondTimeOutRunnable, 5000L);
                this.handler.postDelayed(this.tenSecondTimeOutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
                ActivitySportRunningBinding activitySportRunningBinding3 = this.binding;
                if (activitySportRunningBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySportRunningBinding = activitySportRunningBinding3;
                }
                activitySportRunningBinding.imageBleStatus.setImageResource(R.mipmap.ble_connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endViewAnim() {
        ActivitySportRunningBinding activitySportRunningBinding = this.binding;
        ActivitySportRunningBinding activitySportRunningBinding2 = null;
        if (activitySportRunningBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding = null;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(activitySportRunningBinding.imageStop.getMeasuredWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        ActivitySportRunningBinding activitySportRunningBinding3 = this.binding;
        if (activitySportRunningBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding3 = null;
        }
        activitySportRunningBinding3.imageEnd.startAnimation(translateAnimation);
        ActivitySportRunningBinding activitySportRunningBinding4 = this.binding;
        if (activitySportRunningBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding4 = null;
        }
        TranslateAnimation translateAnimation2 = new TranslateAnimation(-activitySportRunningBinding4.imageStop.getMeasuredWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity.endViewAnim.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ActivitySportRunningBinding activitySportRunningBinding5 = SportRunningActivity.this.binding;
                if (activitySportRunningBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySportRunningBinding5 = null;
                }
                ViewKt.visible(activitySportRunningBinding5.tvLabelEnd);
            }
        });
        translateAnimation2.setDuration(300L);
        ActivitySportRunningBinding activitySportRunningBinding5 = this.binding;
        if (activitySportRunningBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportRunningBinding2 = activitySportRunningBinding5;
        }
        activitySportRunningBinding2.imageContinue.startAnimation(translateAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startViewAnim() {
        ActivitySportRunningBinding activitySportRunningBinding = this.binding;
        ActivitySportRunningBinding activitySportRunningBinding2 = null;
        if (activitySportRunningBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding = null;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, activitySportRunningBinding.imageStop.getMeasuredWidth(), 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity.startViewAnim.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }
        });
        if (this.startAnim == null) {
            ActivitySportRunningBinding activitySportRunningBinding3 = this.binding;
            if (activitySportRunningBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportRunningBinding3 = null;
            }
            TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, -activitySportRunningBinding3.imageStop.getMeasuredWidth(), 0.0f, 0.0f);
            this.startAnim = translateAnimation2;
            Intrinsics.checkNotNull(translateAnimation2);
            translateAnimation2.setDuration(300L);
            TranslateAnimation translateAnimation3 = this.startAnim;
            Intrinsics.checkNotNull(translateAnimation3);
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportRunningActivity.startViewAnim.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    SportRunningActivity.this.showGpsEnd();
                }
            });
        }
        ActivitySportRunningBinding activitySportRunningBinding4 = this.binding;
        if (activitySportRunningBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding4 = null;
        }
        activitySportRunningBinding4.imageEnd.startAnimation(translateAnimation);
        ActivitySportRunningBinding activitySportRunningBinding5 = this.binding;
        if (activitySportRunningBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportRunningBinding2 = activitySportRunningBinding5;
        }
        activitySportRunningBinding2.imageContinue.startAnimation(this.startAnim);
    }

    public final void showGpsEnd() {
        ActivitySportRunningBinding activitySportRunningBinding = this.binding;
        ActivitySportRunningBinding activitySportRunningBinding2 = null;
        if (activitySportRunningBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding = null;
        }
        ViewKt.gone(activitySportRunningBinding.imageEnd);
        ActivitySportRunningBinding activitySportRunningBinding3 = this.binding;
        if (activitySportRunningBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding3 = null;
        }
        ViewKt.gone(activitySportRunningBinding3.imageContinue);
        ActivitySportRunningBinding activitySportRunningBinding4 = this.binding;
        if (activitySportRunningBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding4 = null;
        }
        ViewKt.visible(activitySportRunningBinding4.imageStop);
        ActivitySportRunningBinding activitySportRunningBinding5 = this.binding;
        if (activitySportRunningBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportRunningBinding2 = activitySportRunningBinding5;
        }
        activitySportRunningBinding2.imageStop.setEnabled(true);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ActivitySportRunningBinding activitySportRunningBinding = this.binding;
        if (activitySportRunningBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportRunningBinding = null;
        }
        if (activitySportRunningBinding.imageStop.isLock()) {
            String string = getString(R.string.qc_text_277);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_277)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        showGpsExitDialog();
    }

    /* compiled from: SportRunningActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity$FiveSecondTimeOutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class FiveSecondTimeOutRunnable implements Runnable {
        public FiveSecondTimeOutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SportRunningActivity sportRunningActivity = SportRunningActivity.this;
            sportRunningActivity.durationTime = sportRunningActivity.sportDuration;
        }
    }

    /* compiled from: SportRunningActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity$TenSecondTimeOutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/sport/SportRunningActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TenSecondTimeOutRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public TenSecondTimeOutRunnable() {
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeSportDeviceListener(120);
    }
}
