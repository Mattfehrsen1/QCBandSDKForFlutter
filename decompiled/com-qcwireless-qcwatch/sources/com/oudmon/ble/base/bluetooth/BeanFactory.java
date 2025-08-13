package com.oudmon.ble.base.bluetooth;

import com.oudmon.ble.base.communication.rsp.AppSportRsp;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.oudmon.ble.base.communication.rsp.BloodOxygenSettingRsp;
import com.oudmon.ble.base.communication.rsp.BpDataRsp;
import com.oudmon.ble.base.communication.rsp.BpSettingRsp;
import com.oudmon.ble.base.communication.rsp.CallForwardRsp;
import com.oudmon.ble.base.communication.rsp.CameraNotifyRsp;
import com.oudmon.ble.base.communication.rsp.DegreeSwitchRsp;
import com.oudmon.ble.base.communication.rsp.DeviceAvatarRsp;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.oudmon.ble.base.communication.rsp.DeviceSupportFunctionRsp;
import com.oudmon.ble.base.communication.rsp.DeviceThemeRsp;
import com.oudmon.ble.base.communication.rsp.DeviceWallpaperRsp;
import com.oudmon.ble.base.communication.rsp.DisplayTimeRsp;
import com.oudmon.ble.base.communication.rsp.DndRsp;
import com.oudmon.ble.base.communication.rsp.FindPhoneRsp;
import com.oudmon.ble.base.communication.rsp.HRVRsp;
import com.oudmon.ble.base.communication.rsp.HRVSettingRsp;
import com.oudmon.ble.base.communication.rsp.HeartRateSettingRsp;
import com.oudmon.ble.base.communication.rsp.MusicCommandRsp;
import com.oudmon.ble.base.communication.rsp.PackageLengthRsp;
import com.oudmon.ble.base.communication.rsp.PalmScreenRsp;
import com.oudmon.ble.base.communication.rsp.PhoneNotifyRsp;
import com.oudmon.ble.base.communication.rsp.PressureRsp;
import com.oudmon.ble.base.communication.rsp.PressureSettingRsp;
import com.oudmon.ble.base.communication.rsp.ReadAlarmRsp;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.oudmon.ble.base.communication.rsp.ReadDetailSportDataRsp;
import com.oudmon.ble.base.communication.rsp.ReadHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.ReadMessagePushRsp;
import com.oudmon.ble.base.communication.rsp.ReadSitLongRsp;
import com.oudmon.ble.base.communication.rsp.ReadSleepDetailsRsp;
import com.oudmon.ble.base.communication.rsp.RealTimeHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.oudmon.ble.base.communication.rsp.SimpleStatusRsp;
import com.oudmon.ble.base.communication.rsp.StartHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.TimeFormatRsp;
import com.oudmon.ble.base.communication.rsp.TodaySportDataRsp;
import com.oudmon.ble.base.communication.rsp.WeatherForecastRsp;

/* loaded from: classes3.dex */
public class BeanFactory {
    public static BaseRspCmd createBean(int i, int i2) {
        if (i == 1) {
            return new SetTimeRsp();
        }
        if (i == 2) {
            return new CameraNotifyRsp();
        }
        if (i == 3) {
            return new BatteryRsp();
        }
        if (i == 5) {
            return new PalmScreenRsp();
        }
        if (i == 6) {
            return new DndRsp();
        }
        if (i == 12) {
            return new BpSettingRsp();
        }
        if (i == 13) {
            return new BpDataRsp();
        }
        if (i == 25) {
            return new DegreeSwitchRsp();
        }
        if (i == 26) {
            return new WeatherForecastRsp();
        }
        if (i == 50) {
            return new DeviceAvatarRsp();
        }
        if (i == 51) {
            return new CallForwardRsp();
        }
        if (i == 60) {
            return new DeviceSupportFunctionRsp();
        }
        if (i == 61) {
            return new DeviceThemeRsp();
        }
        if (i == 67) {
            return new ReadDetailSportDataRsp();
        }
        if (i != 68) {
            switch (i) {
                case 10:
                    return new TimeFormatRsp();
                case 17:
                    return new PhoneNotifyRsp();
                case 34:
                    return new FindPhoneRsp();
                case 36:
                case 40:
                    return new ReadAlarmRsp();
                case 38:
                    return new ReadSitLongRsp();
                case 44:
                    return new BloodOxygenSettingRsp();
                case 47:
                    return new PackageLengthRsp();
                case 54:
                    return new PressureSettingRsp();
                case 55:
                    return new PressureRsp();
                case 56:
                    return new HRVSettingRsp();
                case 57:
                    return new HRVRsp();
                case 63:
                    return new DeviceWallpaperRsp();
                case 72:
                    return new TodaySportDataRsp();
                case 97:
                    return new ReadMessagePushRsp();
                case 105:
                    return new StartHeartRateRsp();
                case 114:
                    return new SimpleStatusRsp();
                case 115:
                case 120:
                    return new DeviceNotifyRsp();
                case 119:
                    return new AppSportRsp();
                default:
                    switch (i) {
                        case 20:
                            return new ReadBlePressureRsp();
                        case 21:
                            return new ReadHeartRateRsp();
                        case 22:
                            return new HeartRateSettingRsp();
                        default:
                            switch (i) {
                                case 29:
                                    return new MusicCommandRsp();
                                case 30:
                                    return new RealTimeHeartRateRsp();
                                case 31:
                                    return new DisplayTimeRsp();
                                default:
                                    return null;
                            }
                    }
            }
        }
        return new ReadSleepDetailsRsp();
    }
}
