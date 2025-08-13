package com.qcwireless.qcwatch.ui.base.bean.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.king.zxing.util.CodeUtils;
import com.realsil.sdk.core.bluetooth.connection.le.GattClient;
import com.squareup.moshi.JsonClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceSetting.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00009\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0003\b\u0080\u0001\b\u0087\b\u0018\u00002\u00020\u0001Bï\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\b\u0012\b\b\u0002\u0010\u001d\u001a\u00020\b\u0012\b\b\u0002\u0010\u001e\u001a\u00020\b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\b\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\b\u0012\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#\u0012\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&\u0012\b\b\u0002\u0010(\u001a\u00020\b\u0012\b\b\u0002\u0010)\u001a\u00020\b\u0012\b\b\u0002\u0010*\u001a\u00020\b\u0012\b\b\u0002\u0010+\u001a\u00020\u0003¢\u0006\u0002\u0010,J\t\u0010~\u001a\u00020\u0003HÆ\u0003J\t\u0010\u007f\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0080\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0082\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0083\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0084\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0085\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0088\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008a\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0019HÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u008f\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0090\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0091\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0092\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0093\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0094\u0001\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020$0#HÆ\u0003¢\u0006\u0002\u0010RJ\u0010\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020'0&HÆ\u0003J\n\u0010\u0097\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0098\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0099\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u009a\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009b\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009c\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u009d\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u009e\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0003HÆ\u0003Jú\u0002\u0010¡\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\b2\b\b\u0002\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\b2\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\b2\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\b\b\u0002\u0010(\u001a\u00020\b2\b\b\u0002\u0010)\u001a\u00020\b2\b\b\u0002\u0010*\u001a\u00020\b2\b\b\u0002\u0010+\u001a\u00020\u0003HÆ\u0001¢\u0006\u0003\u0010¢\u0001J\u0015\u0010£\u0001\u001a\u00020\u00032\t\u0010¤\u0001\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010¥\u0001\u001a\u00020\bH\u0016J\n\u0010¦\u0001\u001a\u00020\u0019HÖ\u0001R \u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u0010*\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010(\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00102\"\u0004\b6\u00104R\u001a\u0010)\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00102\"\u0004\b8\u00104R\u001a\u0010+\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010:\"\u0004\b>\u0010<R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u00102\"\u0004\b@\u00104R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u00102\"\u0004\bB\u00104R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00102\"\u0004\bD\u00104R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010:\"\u0004\bF\u0010<R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010:\"\u0004\bH\u0010<R\u001a\u0010\u0017\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u00102\"\u0004\bJ\u00104R\u001a\u0010\u0015\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010:\"\u0004\bL\u0010<R\u001a\u0010\u0016\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u00102\"\u0004\bN\u00104R\u001a\u0010\u0014\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010:\"\u0004\bP\u0010<R\"\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0086\u000e¢\u0006\u0010\n\u0002\u0010U\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001a\u0010 \u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010:\"\u0004\bW\u0010<R\u001a\u0010!\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00102\"\u0004\bY\u00104R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010:\"\u0004\b_\u0010<R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010:\"\u0004\ba\u0010<R\u001a\u0010\u001e\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u00102\"\u0004\bc\u00104R\u001a\u0010\u001d\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u00102\"\u0004\be\u00104R\u001a\u0010\u001c\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u00102\"\u0004\bg\u00104R\u001a\u0010\u001b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010:\"\u0004\bi\u0010<R\u001a\u0010\u001f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u00102\"\u0004\bk\u00104R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010:\"\u0004\bm\u0010<R\u001a\u0010\u0013\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u00102\"\u0004\bo\u00104R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010:\"\u0004\bq\u0010<R\u001a\u0010\u001a\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u00102\"\u0004\bs\u00104R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010:\"\u0004\bu\u0010<R\u001a\u0010\u0011\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u00102\"\u0004\bw\u00104R\u001a\u0010\u0012\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u00102\"\u0004\by\u00104R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010:\"\u0004\b{\u0010<R\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u00102\"\u0004\b}\u00104¨\u0006§\u0001"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "", "callSwitch", "", "messagePushSwitch", "smsPushSwitch", "bpSwitch", "bpStart", "", "bpEnd", "bpInterval", "hrDetection", "bo2Detection", "hrvEnable", "pressureDetection", "wristSense", "wristSenseHand", "timeFormat", "weatherFormat", "metricUnit", "disturbSwitch", "disturbManualSwitch", "disturbStart", "disturbEnd", "firmwareVersion", "", "screenLight", "longSitSwitch", "longSitStart", "longSitEnd", "longSitDuring", "longSitWeek", "drinkSwitch", "drinkWeek", "drinkArray", "", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;", "alarmList", "", "Lcom/qcwireless/qcwatch/ui/base/bean/device/AlarmBean;", "avatarScreen", "avatarWidth", "avatarHeight", "batteryWarming", "(ZZZZIIIZZZZZIIIIZZIILjava/lang/String;IZIIIIZI[Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;Ljava/util/List;IIIZ)V", "getAlarmList", "()Ljava/util/List;", "setAlarmList", "(Ljava/util/List;)V", "getAvatarHeight", "()I", "setAvatarHeight", "(I)V", "getAvatarScreen", "setAvatarScreen", "getAvatarWidth", "setAvatarWidth", "getBatteryWarming", "()Z", "setBatteryWarming", "(Z)V", "getBo2Detection", "setBo2Detection", "getBpEnd", "setBpEnd", "getBpInterval", "setBpInterval", "getBpStart", "setBpStart", "getBpSwitch", "setBpSwitch", "getCallSwitch", "setCallSwitch", "getDisturbEnd", "setDisturbEnd", "getDisturbManualSwitch", "setDisturbManualSwitch", "getDisturbStart", "setDisturbStart", "getDisturbSwitch", "setDisturbSwitch", "getDrinkArray", "()[Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;", "setDrinkArray", "([Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;)V", "[Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;", "getDrinkSwitch", "setDrinkSwitch", "getDrinkWeek", "setDrinkWeek", "getFirmwareVersion", "()Ljava/lang/String;", "setFirmwareVersion", "(Ljava/lang/String;)V", "getHrDetection", "setHrDetection", "getHrvEnable", "setHrvEnable", "getLongSitDuring", "setLongSitDuring", "getLongSitEnd", "setLongSitEnd", "getLongSitStart", "setLongSitStart", "getLongSitSwitch", "setLongSitSwitch", "getLongSitWeek", "setLongSitWeek", "getMessagePushSwitch", "setMessagePushSwitch", "getMetricUnit", "setMetricUnit", "getPressureDetection", "setPressureDetection", "getScreenLight", "setScreenLight", "getSmsPushSwitch", "setSmsPushSwitch", "getTimeFormat", "setTimeFormat", "getWeatherFormat", "setWeatherFormat", "getWristSense", "setWristSense", "getWristSenseHand", "setWristSenseHand", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ZZZZIIIZZZZZIIIIZZIILjava/lang/String;IZIIIIZI[Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;Ljava/util/List;IIIZ)Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DeviceSetting {
    private List<AlarmBean> alarmList;
    private int avatarHeight;
    private int avatarScreen;
    private int avatarWidth;
    private boolean batteryWarming;
    private boolean bo2Detection;
    private int bpEnd;
    private int bpInterval;
    private int bpStart;
    private boolean bpSwitch;
    private boolean callSwitch;
    private int disturbEnd;
    private boolean disturbManualSwitch;
    private int disturbStart;
    private boolean disturbSwitch;
    private DrinkBean[] drinkArray;
    private boolean drinkSwitch;
    private int drinkWeek;
    private String firmwareVersion;
    private boolean hrDetection;
    private boolean hrvEnable;
    private int longSitDuring;
    private int longSitEnd;
    private int longSitStart;
    private boolean longSitSwitch;
    private int longSitWeek;
    private boolean messagePushSwitch;
    private int metricUnit;
    private boolean pressureDetection;
    private int screenLight;
    private boolean smsPushSwitch;
    private int timeFormat;
    private int weatherFormat;
    private boolean wristSense;
    private int wristSenseHand;

    public DeviceSetting() {
        this(false, false, false, false, 0, 0, 0, false, false, false, false, false, 0, 0, 0, 0, false, false, 0, 0, null, 0, false, 0, 0, 0, 0, false, 0, null, null, 0, 0, 0, false, -1, 7, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getCallSwitch() {
        return this.callSwitch;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getHrvEnable() {
        return this.hrvEnable;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getPressureDetection() {
        return this.pressureDetection;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getWristSense() {
        return this.wristSense;
    }

    /* renamed from: component13, reason: from getter */
    public final int getWristSenseHand() {
        return this.wristSenseHand;
    }

    /* renamed from: component14, reason: from getter */
    public final int getTimeFormat() {
        return this.timeFormat;
    }

    /* renamed from: component15, reason: from getter */
    public final int getWeatherFormat() {
        return this.weatherFormat;
    }

    /* renamed from: component16, reason: from getter */
    public final int getMetricUnit() {
        return this.metricUnit;
    }

    /* renamed from: component17, reason: from getter */
    public final boolean getDisturbSwitch() {
        return this.disturbSwitch;
    }

    /* renamed from: component18, reason: from getter */
    public final boolean getDisturbManualSwitch() {
        return this.disturbManualSwitch;
    }

    /* renamed from: component19, reason: from getter */
    public final int getDisturbStart() {
        return this.disturbStart;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getMessagePushSwitch() {
        return this.messagePushSwitch;
    }

    /* renamed from: component20, reason: from getter */
    public final int getDisturbEnd() {
        return this.disturbEnd;
    }

    /* renamed from: component21, reason: from getter */
    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    /* renamed from: component22, reason: from getter */
    public final int getScreenLight() {
        return this.screenLight;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getLongSitSwitch() {
        return this.longSitSwitch;
    }

    /* renamed from: component24, reason: from getter */
    public final int getLongSitStart() {
        return this.longSitStart;
    }

    /* renamed from: component25, reason: from getter */
    public final int getLongSitEnd() {
        return this.longSitEnd;
    }

    /* renamed from: component26, reason: from getter */
    public final int getLongSitDuring() {
        return this.longSitDuring;
    }

    /* renamed from: component27, reason: from getter */
    public final int getLongSitWeek() {
        return this.longSitWeek;
    }

    /* renamed from: component28, reason: from getter */
    public final boolean getDrinkSwitch() {
        return this.drinkSwitch;
    }

    /* renamed from: component29, reason: from getter */
    public final int getDrinkWeek() {
        return this.drinkWeek;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getSmsPushSwitch() {
        return this.smsPushSwitch;
    }

    /* renamed from: component30, reason: from getter */
    public final DrinkBean[] getDrinkArray() {
        return this.drinkArray;
    }

    public final List<AlarmBean> component31() {
        return this.alarmList;
    }

    /* renamed from: component32, reason: from getter */
    public final int getAvatarScreen() {
        return this.avatarScreen;
    }

    /* renamed from: component33, reason: from getter */
    public final int getAvatarWidth() {
        return this.avatarWidth;
    }

    /* renamed from: component34, reason: from getter */
    public final int getAvatarHeight() {
        return this.avatarHeight;
    }

    /* renamed from: component35, reason: from getter */
    public final boolean getBatteryWarming() {
        return this.batteryWarming;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getBpSwitch() {
        return this.bpSwitch;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBpStart() {
        return this.bpStart;
    }

    /* renamed from: component6, reason: from getter */
    public final int getBpEnd() {
        return this.bpEnd;
    }

    /* renamed from: component7, reason: from getter */
    public final int getBpInterval() {
        return this.bpInterval;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getHrDetection() {
        return this.hrDetection;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getBo2Detection() {
        return this.bo2Detection;
    }

    public final DeviceSetting copy(boolean callSwitch, boolean messagePushSwitch, boolean smsPushSwitch, boolean bpSwitch, int bpStart, int bpEnd, int bpInterval, boolean hrDetection, boolean bo2Detection, boolean hrvEnable, boolean pressureDetection, boolean wristSense, int wristSenseHand, int timeFormat, int weatherFormat, int metricUnit, boolean disturbSwitch, boolean disturbManualSwitch, int disturbStart, int disturbEnd, String firmwareVersion, int screenLight, boolean longSitSwitch, int longSitStart, int longSitEnd, int longSitDuring, int longSitWeek, boolean drinkSwitch, int drinkWeek, DrinkBean[] drinkArray, List<AlarmBean> alarmList, int avatarScreen, int avatarWidth, int avatarHeight, boolean batteryWarming) {
        Intrinsics.checkNotNullParameter(firmwareVersion, "firmwareVersion");
        Intrinsics.checkNotNullParameter(drinkArray, "drinkArray");
        Intrinsics.checkNotNullParameter(alarmList, "alarmList");
        return new DeviceSetting(callSwitch, messagePushSwitch, smsPushSwitch, bpSwitch, bpStart, bpEnd, bpInterval, hrDetection, bo2Detection, hrvEnable, pressureDetection, wristSense, wristSenseHand, timeFormat, weatherFormat, metricUnit, disturbSwitch, disturbManualSwitch, disturbStart, disturbEnd, firmwareVersion, screenLight, longSitSwitch, longSitStart, longSitEnd, longSitDuring, longSitWeek, drinkSwitch, drinkWeek, drinkArray, alarmList, avatarScreen, avatarWidth, avatarHeight, batteryWarming);
    }

    public String toString() {
        return "DeviceSetting(callSwitch=" + this.callSwitch + ", messagePushSwitch=" + this.messagePushSwitch + ", smsPushSwitch=" + this.smsPushSwitch + ", bpSwitch=" + this.bpSwitch + ", bpStart=" + this.bpStart + ", bpEnd=" + this.bpEnd + ", bpInterval=" + this.bpInterval + ", hrDetection=" + this.hrDetection + ", bo2Detection=" + this.bo2Detection + ", hrvEnable=" + this.hrvEnable + ", pressureDetection=" + this.pressureDetection + ", wristSense=" + this.wristSense + ", wristSenseHand=" + this.wristSenseHand + ", timeFormat=" + this.timeFormat + ", weatherFormat=" + this.weatherFormat + ", metricUnit=" + this.metricUnit + ", disturbSwitch=" + this.disturbSwitch + ", disturbManualSwitch=" + this.disturbManualSwitch + ", disturbStart=" + this.disturbStart + ", disturbEnd=" + this.disturbEnd + ", firmwareVersion=" + this.firmwareVersion + ", screenLight=" + this.screenLight + ", longSitSwitch=" + this.longSitSwitch + ", longSitStart=" + this.longSitStart + ", longSitEnd=" + this.longSitEnd + ", longSitDuring=" + this.longSitDuring + ", longSitWeek=" + this.longSitWeek + ", drinkSwitch=" + this.drinkSwitch + ", drinkWeek=" + this.drinkWeek + ", drinkArray=" + Arrays.toString(this.drinkArray) + ", alarmList=" + this.alarmList + ", avatarScreen=" + this.avatarScreen + ", avatarWidth=" + this.avatarWidth + ", avatarHeight=" + this.avatarHeight + ", batteryWarming=" + this.batteryWarming + ')';
    }

    public DeviceSetting(boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, int i3, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i4, int i5, int i6, int i7, boolean z10, boolean z11, int i8, int i9, String firmwareVersion, int i10, boolean z12, int i11, int i12, int i13, int i14, boolean z13, int i15, DrinkBean[] drinkArray, List<AlarmBean> alarmList, int i16, int i17, int i18, boolean z14) {
        Intrinsics.checkNotNullParameter(firmwareVersion, "firmwareVersion");
        Intrinsics.checkNotNullParameter(drinkArray, "drinkArray");
        Intrinsics.checkNotNullParameter(alarmList, "alarmList");
        this.callSwitch = z;
        this.messagePushSwitch = z2;
        this.smsPushSwitch = z3;
        this.bpSwitch = z4;
        this.bpStart = i;
        this.bpEnd = i2;
        this.bpInterval = i3;
        this.hrDetection = z5;
        this.bo2Detection = z6;
        this.hrvEnable = z7;
        this.pressureDetection = z8;
        this.wristSense = z9;
        this.wristSenseHand = i4;
        this.timeFormat = i5;
        this.weatherFormat = i6;
        this.metricUnit = i7;
        this.disturbSwitch = z10;
        this.disturbManualSwitch = z11;
        this.disturbStart = i8;
        this.disturbEnd = i9;
        this.firmwareVersion = firmwareVersion;
        this.screenLight = i10;
        this.longSitSwitch = z12;
        this.longSitStart = i11;
        this.longSitEnd = i12;
        this.longSitDuring = i13;
        this.longSitWeek = i14;
        this.drinkSwitch = z13;
        this.drinkWeek = i15;
        this.drinkArray = drinkArray;
        this.alarmList = alarmList;
        this.avatarScreen = i16;
        this.avatarWidth = i17;
        this.avatarHeight = i18;
        this.batteryWarming = z14;
    }

    public final boolean getCallSwitch() {
        return this.callSwitch;
    }

    public final void setCallSwitch(boolean z) {
        this.callSwitch = z;
    }

    public final boolean getMessagePushSwitch() {
        return this.messagePushSwitch;
    }

    public final void setMessagePushSwitch(boolean z) {
        this.messagePushSwitch = z;
    }

    public final boolean getSmsPushSwitch() {
        return this.smsPushSwitch;
    }

    public final void setSmsPushSwitch(boolean z) {
        this.smsPushSwitch = z;
    }

    public final boolean getBpSwitch() {
        return this.bpSwitch;
    }

    public final void setBpSwitch(boolean z) {
        this.bpSwitch = z;
    }

    public final int getBpStart() {
        return this.bpStart;
    }

    public final void setBpStart(int i) {
        this.bpStart = i;
    }

    public final int getBpEnd() {
        return this.bpEnd;
    }

    public final void setBpEnd(int i) {
        this.bpEnd = i;
    }

    public final int getBpInterval() {
        return this.bpInterval;
    }

    public final void setBpInterval(int i) {
        this.bpInterval = i;
    }

    public final boolean getHrDetection() {
        return this.hrDetection;
    }

    public final void setHrDetection(boolean z) {
        this.hrDetection = z;
    }

    public final boolean getBo2Detection() {
        return this.bo2Detection;
    }

    public final void setBo2Detection(boolean z) {
        this.bo2Detection = z;
    }

    public final boolean getHrvEnable() {
        return this.hrvEnable;
    }

    public final void setHrvEnable(boolean z) {
        this.hrvEnable = z;
    }

    public final boolean getPressureDetection() {
        return this.pressureDetection;
    }

    public final void setPressureDetection(boolean z) {
        this.pressureDetection = z;
    }

    public final boolean getWristSense() {
        return this.wristSense;
    }

    public final void setWristSense(boolean z) {
        this.wristSense = z;
    }

    public final int getWristSenseHand() {
        return this.wristSenseHand;
    }

    public final void setWristSenseHand(int i) {
        this.wristSenseHand = i;
    }

    public final int getTimeFormat() {
        return this.timeFormat;
    }

    public final void setTimeFormat(int i) {
        this.timeFormat = i;
    }

    public final int getWeatherFormat() {
        return this.weatherFormat;
    }

    public final void setWeatherFormat(int i) {
        this.weatherFormat = i;
    }

    public final int getMetricUnit() {
        return this.metricUnit;
    }

    public final void setMetricUnit(int i) {
        this.metricUnit = i;
    }

    public final boolean getDisturbSwitch() {
        return this.disturbSwitch;
    }

    public final void setDisturbSwitch(boolean z) {
        this.disturbSwitch = z;
    }

    public final boolean getDisturbManualSwitch() {
        return this.disturbManualSwitch;
    }

    public final void setDisturbManualSwitch(boolean z) {
        this.disturbManualSwitch = z;
    }

    public final int getDisturbStart() {
        return this.disturbStart;
    }

    public final void setDisturbStart(int i) {
        this.disturbStart = i;
    }

    public final int getDisturbEnd() {
        return this.disturbEnd;
    }

    public final void setDisturbEnd(int i) {
        this.disturbEnd = i;
    }

    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public final void setFirmwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.firmwareVersion = str;
    }

    public final int getScreenLight() {
        return this.screenLight;
    }

    public final void setScreenLight(int i) {
        this.screenLight = i;
    }

    public final boolean getLongSitSwitch() {
        return this.longSitSwitch;
    }

    public final void setLongSitSwitch(boolean z) {
        this.longSitSwitch = z;
    }

    public final int getLongSitStart() {
        return this.longSitStart;
    }

    public final void setLongSitStart(int i) {
        this.longSitStart = i;
    }

    public final int getLongSitEnd() {
        return this.longSitEnd;
    }

    public final void setLongSitEnd(int i) {
        this.longSitEnd = i;
    }

    public final int getLongSitDuring() {
        return this.longSitDuring;
    }

    public final void setLongSitDuring(int i) {
        this.longSitDuring = i;
    }

    public final int getLongSitWeek() {
        return this.longSitWeek;
    }

    public final void setLongSitWeek(int i) {
        this.longSitWeek = i;
    }

    public final boolean getDrinkSwitch() {
        return this.drinkSwitch;
    }

    public final void setDrinkSwitch(boolean z) {
        this.drinkSwitch = z;
    }

    public final int getDrinkWeek() {
        return this.drinkWeek;
    }

    public final void setDrinkWeek(int i) {
        this.drinkWeek = i;
    }

    public final DrinkBean[] getDrinkArray() {
        return this.drinkArray;
    }

    public final void setDrinkArray(DrinkBean[] drinkBeanArr) {
        Intrinsics.checkNotNullParameter(drinkBeanArr, "<set-?>");
        this.drinkArray = drinkBeanArr;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DeviceSetting(boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, int i3, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i4, int i5, int i6, int i7, boolean z10, boolean z11, int i8, int i9, String str, int i10, boolean z12, int i11, int i12, int i13, int i14, boolean z13, int i15, DrinkBean[] drinkBeanArr, List list, int i16, int i17, int i18, boolean z14, int i19, int i20, DefaultConstructorMarker defaultConstructorMarker) {
        int i21;
        boolean z15;
        boolean z16;
        boolean z17;
        int i22;
        int i23;
        DrinkBean[] drinkBeanArr2;
        boolean z18 = (i19 & 1) != 0 ? false : z;
        boolean z19 = (i19 & 2) != 0 ? true : z2;
        boolean z20 = (i19 & 4) != 0 ? false : z3;
        boolean z21 = (i19 & 8) != 0 ? false : z4;
        int i24 = (i19 & 16) != 0 ? 0 : i;
        int i25 = (i19 & 32) != 0 ? 23 : i2;
        int i26 = (i19 & 64) != 0 ? 1 : i3;
        boolean z22 = (i19 & 128) != 0 ? false : z5;
        boolean z23 = (i19 & 256) != 0 ? false : z6;
        boolean z24 = (i19 & 512) != 0 ? false : z7;
        boolean z25 = (i19 & 1024) != 0 ? false : z8;
        boolean z26 = (i19 & 2048) != 0 ? true : z9;
        int i27 = (i19 & 4096) != 0 ? 0 : i4;
        int i28 = (i19 & 8192) != 0 ? 0 : i5;
        int i29 = (i19 & 16384) != 0 ? 0 : i6;
        int i30 = (i19 & 32768) != 0 ? 0 : i7;
        boolean z27 = (i19 & 65536) != 0 ? false : z10;
        boolean z28 = (i19 & 131072) != 0 ? false : z11;
        int i31 = (i19 & 262144) != 0 ? 1320 : i8;
        int i32 = i29;
        int i33 = (i19 & 524288) != 0 ? CodeUtils.DEFAULT_REQ_WIDTH : i9;
        String str2 = (i19 & 1048576) != 0 ? "--" : str;
        int i34 = (i19 & 2097152) != 0 ? 5 : i10;
        boolean z29 = (i19 & 4194304) != 0 ? false : z12;
        int i35 = (i19 & 8388608) != 0 ? GattClient.STATE_READ_PROTOCOL_TYPE : i11;
        int i36 = (i19 & 16777216) != 0 ? 1080 : i12;
        int i37 = (i19 & 33554432) != 0 ? 60 : i13;
        int i38 = (i19 & 67108864) != 0 ? 128 : i14;
        boolean z30 = (i19 & 134217728) != 0 ? false : z13;
        int i39 = (i19 & 268435456) == 0 ? i15 : 128;
        if ((i19 & 536870912) != 0) {
            i21 = i28;
            i22 = i27;
            z16 = z25;
            z17 = z26;
            i23 = 0;
            z15 = z24;
            drinkBeanArr2 = new DrinkBean[]{new DrinkBean("", CodeUtils.DEFAULT_REQ_WIDTH, false), new DrinkBean("", GattClient.STATE_READ_PROTOCOL_TYPE, false), new DrinkBean("", 690, false), new DrinkBean("", 810, false), new DrinkBean("", 930, false), new DrinkBean("", 1050, false), new DrinkBean("", 1170, false), new DrinkBean("", 1215, false)};
        } else {
            i21 = i28;
            z15 = z24;
            z16 = z25;
            z17 = z26;
            i22 = i27;
            i23 = 0;
            drinkBeanArr2 = drinkBeanArr;
        }
        this(z18, z19, z20, z21, i24, i25, i26, z22, z23, z15, z16, z17, i22, i21, i32, i30, z27, z28, i31, i33, str2, i34, z29, i35, i36, i37, i38, z30, i39, drinkBeanArr2, (1073741824 & i19) != 0 ? new ArrayList() : list, (i19 & Integer.MIN_VALUE) == 0 ? i16 : i23, (i20 & 1) != 0 ? 84 : i17, (i20 & 2) == 0 ? i18 : 84, (i20 & 4) != 0 ? true : z14);
    }

    public final List<AlarmBean> getAlarmList() {
        return this.alarmList;
    }

    public final void setAlarmList(List<AlarmBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.alarmList = list;
    }

    public final int getAvatarScreen() {
        return this.avatarScreen;
    }

    public final void setAvatarScreen(int i) {
        this.avatarScreen = i;
    }

    public final int getAvatarWidth() {
        return this.avatarWidth;
    }

    public final void setAvatarWidth(int i) {
        this.avatarWidth = i;
    }

    public final int getAvatarHeight() {
        return this.avatarHeight;
    }

    public final void setAvatarHeight(int i) {
        this.avatarHeight = i;
    }

    public final boolean getBatteryWarming() {
        return this.batteryWarming;
    }

    public final void setBatteryWarming(boolean z) {
        this.batteryWarming = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Objects.requireNonNull(other, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting");
        DeviceSetting deviceSetting = (DeviceSetting) other;
        return this.callSwitch == deviceSetting.callSwitch && this.messagePushSwitch == deviceSetting.messagePushSwitch && this.smsPushSwitch == deviceSetting.smsPushSwitch && this.bpSwitch == deviceSetting.bpSwitch && this.bpStart == deviceSetting.bpStart && this.bpEnd == deviceSetting.bpEnd && this.bpInterval == deviceSetting.bpInterval && this.hrDetection == deviceSetting.hrDetection && this.wristSense == deviceSetting.wristSense && this.wristSenseHand == deviceSetting.wristSenseHand && this.timeFormat == deviceSetting.timeFormat && this.weatherFormat == deviceSetting.weatherFormat && this.metricUnit == deviceSetting.metricUnit && this.disturbSwitch == deviceSetting.disturbSwitch && this.disturbStart == deviceSetting.disturbStart && this.disturbEnd == deviceSetting.disturbEnd && Intrinsics.areEqual(this.firmwareVersion, deviceSetting.firmwareVersion) && this.screenLight == deviceSetting.screenLight && this.longSitSwitch == deviceSetting.longSitSwitch && this.longSitStart == deviceSetting.longSitStart && this.longSitEnd == deviceSetting.longSitEnd && this.longSitDuring == deviceSetting.longSitDuring && this.longSitWeek == deviceSetting.longSitWeek && this.drinkSwitch == deviceSetting.drinkSwitch && this.drinkWeek == deviceSetting.drinkWeek && Arrays.equals(this.drinkArray, deviceSetting.drinkArray) && Intrinsics.areEqual(this.alarmList, deviceSetting.alarmList);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((DeviceSetting$$ExternalSyntheticBackport0.m(this.callSwitch) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.messagePushSwitch)) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.smsPushSwitch)) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.bpSwitch)) * 31) + this.bpStart) * 31) + this.bpEnd) * 31) + this.bpInterval) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.hrDetection)) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.wristSense)) * 31) + this.wristSenseHand) * 31) + this.timeFormat) * 31) + this.weatherFormat) * 31) + this.metricUnit) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.disturbSwitch)) * 31) + this.disturbStart) * 31) + this.disturbEnd) * 31) + this.firmwareVersion.hashCode()) * 31) + this.screenLight) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.longSitSwitch)) * 31) + this.longSitStart) * 31) + this.longSitEnd) * 31) + this.longSitDuring) * 31) + this.longSitWeek) * 31) + DeviceSetting$$ExternalSyntheticBackport0.m(this.drinkSwitch)) * 31) + this.drinkWeek) * 31) + Arrays.hashCode(this.drinkArray)) * 31) + this.alarmList.hashCode();
    }
}
