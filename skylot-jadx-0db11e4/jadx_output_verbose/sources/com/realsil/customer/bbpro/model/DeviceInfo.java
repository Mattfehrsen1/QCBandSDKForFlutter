package com.realsil.customer.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.anc.AncScenarioGroupInfo;
import com.realsil.customer.bbpro.apt.AptVolumeInfo;
import com.realsil.customer.bbpro.apt.AptVolumeStatus;
import com.realsil.customer.bbpro.hearing.a;
import com.realsil.customer.bbpro.i.b;
import com.realsil.customer.bbpro.i.c;
import com.realsil.customer.bbpro.llapt.LlAptBasicInfo;
import com.realsil.customer.bbpro.llapt.LlAptBrightnessInfo;
import com.realsil.customer.bbpro.llapt.LlAptBrightnessStatus;
import com.realsil.customer.bbpro.llapt.LlAptScenarioGroupInfo;
import com.realsil.customer.bbpro.vp.VpVolumeInfo;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/DeviceInfo.class */
public class DeviceInfo implements Parcelable {
    public static final int INDICATOR_NA = 0;
    public static final int INDICATOR_NAME_BREDR = 2;
    public static final int INDICATOR_NAME_LE = 3;
    public static final int INDICATOR_ADDR_LE = 4;
    public static final int INDICATOR_STATUS_RWS_STATE = 5;
    public static final int INDICATOR_STATUS_RWS_CHANNEL = 6;
    public static final int INDICATOR_RWS_CHANNEL = 6;
    public static final int INDICATOR_RWS_DEFAULT_CHANNEL = 7;
    public static final int INDICATOR_STATUS_BATTERY_STATUS = 8;
    public static final int INDICATOR_BATTERY_STATUS = 8;
    public static final int INDICATOR_RWS_DEFAULT_ROLE = 9;
    public static final int INDICATOR_RWS_BUD_SIDE = 10;
    public static final int INDICATOR_PACKAGE_ID = 11;
    public static final int INDICATOR_CMD_SET_VERSION = 12;
    public static final int INDICATOR_DSP_CAPABILITY = 13;
    public static final int INDICATOR_SPP_OTA_DEVICE_INFO = 14;
    public static final int INDICATOR_ANC_GROUP = 16;
    public static final int INDICATOR_ANC_STATE = 17;
    public static final int INDICATOR_MOTOR_STATUS = 18;
    public static final int INDICATOR_MOTOR_MODE_PARAMETERS = 19;
    public static final int INDICATOR_VIBRATION_STATUS = 20;
    public static final int INDICATOR_STATUS_GAMING_MODE = 21;
    public static final int INDICATOR_GAMING_MODE = 21;
    public static final int INDICATOR_LOW_LATENCY_LEVEL = 22;
    public static final int INDICATOR_FIND_ME = 23;
    public static final int INDICATOR_APT_STATUS = 25;
    public static final int INDICATOR_STATUS_AUDIO_PASS_THROUGH_STATUS = 25;
    public static final int INDICATOR_APT_NR_STATUS = 32;
    public static final int INDICATOR_APT_VOLUME_INFO = 33;
    public static final int INDICATOR_APT_GAIN = 33;
    public static final int INDICATOR_APT_VOLUME_STATUS = 33;
    public static final int INDICATOR_APT_POWER_ON_DELAY_TIME = 34;
    public static final int INDICATOR_SUPPORTED_MMI_LIST = 35;
    public static final int INDICATOR_SUPPORTED_CLICK_TYPE = 36;
    public static final int INDICATOR_SUPPORTED_CALL_STATE = 37;
    public static final int INDICATOR_LOCK_BUTTON_STATUS = 38;
    public static final int INDICATOR_RWS = 39;
    public static final int INDICATOR_BUD_INFO = 39;
    public static final int INDICATOR_LISTENING_MODE_BASIC_INFO = 40;
    public static final int INDICATOR_LISTENING_MODE_CYCLE = 41;
    public static final int INDICATOR_LISTENING_MODE_STATUS = 48;
    public static final int INDICATOR_LLAPT_BASIC_INFO = 49;
    public static final int INDICATOR_LLAPT_STATE = 50;
    public static final int INDICATOR_LLAPT_BRIGHTNESS_INFO = 51;
    public static final int INDICATOR_LLAPT_BRIGHTNESS_STATUS = 52;
    public static final int INDICATOR_LLAPT_SCENARIO_CHOOSE_INFO = 53;
    public static final int INDICATOR_VOLUME = 54;
    public static final int INDICATOR_EAR_DETECTION_STATUS = 55;
    public static final int INDICATOR_VP_RINGTONE_STATUS = 56;
    public static final int INDICATOR_ANC_SCENARIO_CHOOSE_INFO = 57;
    public static final int INDICATOR_IC_NAME = 58;
    public static final int INDICATOR_COMPANY_MODEL_ID = 59;
    public static final int INDICATOR_DEVICE_ID = 60;
    public static final int INDICATOR_SINGLE_DEVICE_ID = 61;
    public static final int INDICATOR_SINGLE_DEVICE_ID_LCH = 61;
    public static final int INDICATOR_SINGLE_DEVICE_ID_RCH = 62;
    public static final int INDICATOR_FW_VERSION = 63;
    public static final int INDICATOR_SPATIAL_AUDIO_MODE = 64;
    public static final int INDICATOR_SPECIAL_ANC_GROUP = 65;
    public static final byte MOTOR_STATUS_DISABLE = 0;
    public static final byte MOTOR_STATUS_ENABLE = 1;
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.realsil.customer.bbpro.model.DeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }
    };
    public byte a;
    public byte b;
    public int c;
    public int d;
    public String e;
    public String f;
    public String g;
    public String h;
    public int i;
    public int j;
    public String k;
    public String l;
    public String m;
    public DeviceStatusInfo0 n;
    public DeviceStatusInfo o;
    public DspCapability p;
    public byte[] q;
    public byte[] r;
    public byte[] s;
    public List<KeyMmiSettings> t;
    public List<KeyMmiSettings> u;
    public byte v;
    public byte w;
    public int x;
    public int y;
    public boolean z;
    public int A;
    public int B;
    public int C;
    public byte D;
    public byte E;
    public byte F;
    public byte G;
    public int H;
    public int I;
    public int J;
    public byte K;
    public AptVolumeInfo L;
    public boolean M;
    public int N;
    public byte O;
    public AncGroup P;
    public AncGroup Q;
    public AncScenarioGroupInfo R;
    public byte S;
    public byte T;
    public LlAptBasicInfo U;
    public LlAptScenarioGroupInfo V;
    public LlAptBrightnessInfo W;
    public int X;
    public int Y;
    public byte Z;
    public VpVolumeInfo a0;
    public boolean b0;
    public ImageVersionInfo c0;
    public byte d0;

    public DeviceInfo() {
        this.c = 0;
        this.d = 0;
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = 0;
        this.j = 0;
        this.k = "";
        this.l = "";
        this.m = "";
        this.v = (byte) 0;
        this.w = (byte) 0;
        this.z = false;
        this.A = 0;
        this.B = -1;
        this.C = -1;
        this.D = (byte) 0;
        this.E = (byte) 0;
        this.F = (byte) 0;
        this.G = (byte) 0;
        this.S = (byte) 0;
        this.X = 0;
        this.Y = 0;
        this.Z = (byte) 0;
    }

    public byte getChipId() {
        return this.a;
    }

    public void setChipId(byte b) {
        this.a = b;
    }

    public byte getPackageId() {
        return this.b;
    }

    public void setPackageId(byte b) {
        this.b = b;
    }

    public int getCmdSetVersion() {
        return this.c;
    }

    public int getEqSpecVersion() {
        return this.d;
    }

    public void setCmdSetInfo(c cVar) {
        this.c = cVar.a();
        this.d = cVar.b();
        if (this.c == 0) {
            setDspCapability(null);
        }
    }

    public String getBrEdrName() {
        return this.e;
    }

    public void setBrEdrName(String str) {
        this.e = str;
    }

    public String getLeName() {
        return this.f;
    }

    public void setLeName(String str) {
        this.f = str;
    }

    public String getIcName() {
        return this.h;
    }

    public void setIcName(String str) {
        this.h = str;
    }

    public String getLeAddr() {
        return this.g;
    }

    public void setLeAddr(String str) {
        this.g = str;
    }

    public int getCompanyId() {
        return this.i;
    }

    public void setCompanyId(int i) {
        this.i = i;
    }

    public int getModelId() {
        return this.j;
    }

    public void setModelId(int i) {
        this.j = i;
    }

    public int getListeningModeCycle() {
        return this.Y;
    }

    public void setListeningModeCycle(int i) {
        this.Y = i;
    }

    public byte[] getSupportedListeningModeCycle() {
        byte[] bArr = new byte[5];
        bArr[0] = 0;
        bArr[1] = 1;
        int i = 3;
        bArr[2] = 2;
        if (this.c >= 263) {
            bArr[3] = 3;
            i = 4;
        }
        if (isDspAptSupported() && isAncAndDspAptListeningModeSupported()) {
            bArr[i] = 4;
            i++;
        }
        return Arrays.copyOfRange(bArr, 0, i);
    }

    public byte getListeningModeState() {
        if (isListeningModeReportSupported()) {
            return this.Z;
        }
        if (isDspAptSupported() && isDspAptEnabled()) {
            return (byte) 1;
        }
        if (isLlAptSupported() && isLlAptEnabled()) {
            return (byte) 3;
        }
        return (isAncSupported() && isAncEnabled()) ? (byte) 2 : (byte) 0;
    }

    public byte[] getSupportedListeningModes() {
        byte[] bArr = new byte[5];
        int i = 0;
        if (isDspAptSupported()) {
            i = 1;
            bArr[0] = 1;
            if (isAncAndDspAptListeningModeSupported()) {
                bArr[1] = 4;
                i = 2;
            }
        }
        if (isLlAptSupported()) {
            bArr[i] = 3;
            i++;
        }
        if (isAncSupported()) {
            bArr[i] = 2;
            i++;
        }
        if (i > 0) {
            bArr[i] = 0;
            i++;
        }
        return Arrays.copyOfRange(bArr, 0, i);
    }

    public byte[] getToggleableListeningModes() {
        byte[] bArr = new byte[5];
        int i = 0;
        if (isDspAptSupported() && this.X == 0) {
            i = 1;
            bArr[0] = 1;
            if (isAncAndDspAptListeningModeSupported()) {
                bArr[1] = 4;
                i = 2;
            }
        }
        if (isLlAptSupported() && this.X == 1) {
            bArr[i] = 3;
            i++;
        }
        if (isAncSupported()) {
            bArr[i] = 2;
            i++;
        }
        if (i > 0) {
            bArr[i] = 0;
            i++;
        }
        return Arrays.copyOfRange(bArr, 0, i);
    }

    public void updateListeningModeStatus(a aVar) {
        byte b = aVar.a;
        this.Z = b;
        if (b == 3) {
            this.S = (byte) 0;
            this.O = (byte) 0;
            this.T = (byte) 1;
            LlAptBasicInfo llAptBasicInfo = this.U;
            if (llAptBasicInfo != null) {
                llAptBasicInfo.setActiveGroupIndex(aVar.b);
                return;
            }
            return;
        }
        if (b == 1) {
            this.S = (byte) 1;
            this.O = (byte) 0;
            this.T = (byte) 0;
            return;
        }
        if (b == 2) {
            this.S = (byte) 0;
            this.O = (byte) 1;
            AncGroup ancGroup = this.P;
            if (ancGroup != null) {
                ancGroup.setActiveGroupIndex(aVar.b);
            }
            this.T = (byte) 0;
            return;
        }
        if (b != 4) {
            this.S = (byte) 0;
            this.O = (byte) 0;
            this.T = (byte) 0;
        } else {
            this.S = (byte) 1;
            this.O = (byte) 1;
            AncGroup ancGroup2 = this.Q;
            if (ancGroup2 != null) {
                ancGroup2.setActiveGroupIndex(aVar.b);
            }
            this.T = (byte) 0;
        }
    }

    public boolean isRws() {
        return wrapperRwsInfo().isRws;
    }

    public boolean isRwsEngaged() {
        return wrapperRwsInfo().isRwsEngaged();
    }

    public RwsInfo wrapperRwsInfo() {
        return isBudInfoReqSupported() ? getDeviceStatusInfo().toRwsInfo() : getDeviceStatusInfo0().toRwsInfo(this.a, this.b);
    }

    public boolean isBudInfoReqSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isBudInfoReqSupported();
    }

    public boolean isListeningModeReportSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isListeningModeReportSupported();
    }

    public DeviceStatusInfo0 getDeviceStatusInfo0() {
        if (this.n == null) {
            this.n = new DeviceStatusInfo0();
        }
        return this.n;
    }

    public DeviceStatusInfo getDeviceStatusInfo() {
        if (this.o == null) {
            this.o = new DeviceStatusInfo();
        }
        return this.o;
    }

    public void setDeviceStatusInfo(DeviceStatusInfo deviceStatusInfo) {
        this.o = deviceStatusInfo;
    }

    public void setRwsState(byte b) {
        getDeviceStatusInfo0().setRwsState(b);
    }

    public void setRwsDefaultRole(byte b) {
        getDeviceStatusInfo0().setRwsDefaultRole(b);
    }

    public void setPrimaryRwsChannel(byte b) {
        getDeviceStatusInfo0().setRwsPrimaryChannel(b);
    }

    public void setPrimaryDefaultRwsChannel(byte b) {
        getDeviceStatusInfo0().setRwsPrimaryDefaultChannel(b);
    }

    public void setPrimaryBatStatus(int i) {
        getDeviceStatusInfo0().setPrimaryBatStatus(i);
    }

    public void setSecondaryRwsChannel(byte b) {
        getDeviceStatusInfo0().setSecondaryRwsChannel(b);
    }

    public void setSecondaryDefaultRwsChannel(byte b) {
        getDeviceStatusInfo0().setSecondaryDefaultRwsChannel(b);
    }

    public void setSecondaryBatStatus(int i) {
        getDeviceStatusInfo0().setSecondaryBatStatus(i);
    }

    public void setRwsBudSide(byte b) {
        getDeviceStatusInfo0().setRwsBudSide(b);
    }

    public boolean isAptSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isDspAptSupported() || this.p.isLlAptSupported();
    }

    public boolean isAptTypeToggleSupported() {
        DspCapability dspCapability = this.p;
        return dspCapability != null && dspCapability.isLlAptSupported() && this.p.isDspAptSupported();
    }

    public boolean isDspAptSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isDspAptSupported();
    }

    public boolean isLlAptSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isLlAptSupported();
    }

    public boolean isAptEnabled() {
        int i = this.X;
        return i == 0 ? this.S == 1 : i == 1 && this.T == 1;
    }

    public boolean isDspAptEnabled() {
        return this.S == 1;
    }

    public boolean isLlAptEnabled() {
        return this.T == 1;
    }

    public int getAptVolumeVersion() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return 0;
        }
        if (dspCapability.isDspAptSupported()) {
            int i = this.c;
            if (i >= 262) {
                return 3;
            }
            if (i >= 260) {
                return 2;
            }
            return (i < 258 && i >= 1) ? 1 : 0;
        }
        if (!this.p.isLlAptSupported()) {
            return 0;
        }
        int i2 = this.c;
        if (i2 >= 262) {
            return 3;
        }
        return i2 >= 260 ? 2 : 0;
    }

    public boolean isAptEqSupported() {
        DspCapability dspCapability = this.p;
        return dspCapability != null && dspCapability.isDspAptSupported() && this.p.isAptEqSupported();
    }

    public boolean isAptEqBudSettingsSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAptEqBudSettingsSupported();
    }

    public boolean isHASupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isHASupported();
    }

    public boolean isMultiLinkSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isMultiLinkSupported();
    }

    public boolean isDurianSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isDurianSupported();
    }

    public boolean isAptNrSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAptNrSupported();
    }

    public boolean isAptPowerOnDelayTimeSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAptPowerOnDelayTimeSupported();
    }

    public boolean isLlaptScenarioEnableSettingsSupported() {
        return isLlaptScenarioGroupSettingsSupported();
    }

    public boolean isLlaptScenarioGroupSettingsSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isLlaptScenarioGroupSettingsSupported();
    }

    public boolean isAncScenarioGroupSettingsSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAncScenarioGroupSettingsSupported();
    }

    public boolean isEarDetectionSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isEarDetectionSupported();
    }

    public boolean isLocalPlaybackSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isLocalPlaybackSupported();
    }

    public int getAptFeatureType() {
        return this.X;
    }

    public void setAptFeatureType(int i) {
        this.X = i;
    }

    public boolean isLlAptVolumeHeSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isLlAptVolumeHeSupported();
    }

    public byte getAudioPassthroughStatus() {
        return this.S;
    }

    public byte getAptStatus() {
        return this.S;
    }

    public void setAptStatus(byte b) {
        this.S = b;
    }

    public DspCapability getDspCapability() {
        return this.p;
    }

    public void setDspCapability(DspCapability dspCapability) {
        this.p = dspCapability;
        if (dspCapability == null) {
            this.X = 0;
            return;
        }
        ZLogger.v(dspCapability.toString());
        if (dspCapability.isDspAptSupported()) {
            this.X = 0;
        } else if (dspCapability.isLlAptSupported()) {
            this.X = 1;
        } else {
            this.X = 255;
        }
    }

    public byte[] getSupportedMmi() {
        byte[] bArr = this.q;
        return bArr == null ? new byte[0] : bArr;
    }

    public void setSupportedMmi(byte[] bArr) {
        this.q = bArr;
    }

    public byte[] getSupportedClickType() {
        byte[] bArr = this.r;
        return bArr == null ? new byte[0] : bArr;
    }

    public void setSupportedClickType(byte[] bArr) {
        this.r = bArr;
    }

    public byte[] getSupportedCallStatus() {
        byte[] bArr = this.s;
        return bArr == null ? new byte[0] : bArr;
    }

    public void setSupportedCallStatus(byte[] bArr) {
        this.s = bArr;
    }

    public int getCurVolumeLevel() {
        return this.x;
    }

    public void setCurVolumeLevel(int i) {
        this.x = i;
    }

    public int getMaxVolumeLevel() {
        return this.y;
    }

    public void setMaxVolumeLevel(int i) {
        this.y = i;
    }

    public List<KeyMmiSettings> getKeyMmiSettings() {
        return this.t;
    }

    public void setKeyMmiSettings(List<KeyMmiSettings> list) {
        this.t = list;
    }

    public List<KeyMmiSettings> getRwsKeyMmiSettings() {
        return this.u;
    }

    public void setRwsKeyMmiSettings(List<KeyMmiSettings> list) {
        this.u = list;
    }

    public boolean isLeftBudFindMeEnabled() {
        return this.v == 1;
    }

    public void setLeftBudFindMeEnabled(byte b) {
        this.v = b;
    }

    public boolean isRightBudFindMeEnabled() {
        return this.w == 1;
    }

    public void setRightBudFindMeEnabled(byte b) {
        this.w = b;
    }

    public byte getMotorFeature() {
        return this.E;
    }

    public void setMotorFeature(byte b) {
        this.E = b;
    }

    public byte getMotorStatus() {
        return this.F;
    }

    public void setMotorStatus(byte b) {
        this.F = b;
    }

    public byte getVibrationStatus() {
        return this.G;
    }

    public void setVibrationStatus(byte b) {
        this.G = b;
    }

    public int getVibrateOnTime() {
        return this.H;
    }

    public void setVibrateOnTime(int i) {
        this.H = i;
    }

    public int getVibrateOffTime() {
        return this.I;
    }

    public void setVibrateOffTime(int i) {
        this.I = i;
    }

    public int getVibrateCount() {
        return this.J;
    }

    public void setVibrateCount(int i) {
        this.J = i;
    }

    public byte getLockButtonStatus() {
        return this.K;
    }

    public void setLockButtonStatus(byte b) {
        this.K = b;
    }

    public boolean isAptNrEnabled() {
        return this.M;
    }

    public void setAptNrEnabled(boolean z) {
        this.M = z;
    }

    public int getAptPowerOnDelayTime() {
        return this.N;
    }

    public void setAptPowerOnDelayTime(int i) {
        this.N = i;
    }

    public boolean isTtsSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isTtsSupported();
    }

    public boolean isEqSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isEqSupported();
    }

    public boolean isEqInCompatible() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null || !dspCapability.isEqSupported() || this.d <= com.realsil.customer.bbpro.c.c.a()) {
            return false;
        }
        ZLogger.v("app is too old, not support eq at present");
        return true;
    }

    public boolean isEqSettingsEnabled() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isEqSupported();
    }

    public boolean isEqPersistenceSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isEqPersistenceSupported();
    }

    public boolean isKeyMapSupported() {
        DspCapability dspCapability = this.p;
        return dspCapability != null ? dspCapability.isKeyMapSupported() : this.c == 1;
    }

    public boolean isKeyMappingResetSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isKeyMappingResetSupported();
        }
        return false;
    }

    public boolean isRwsKeyMappingSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isRwsKeymapConfigureSupported();
        }
        return false;
    }

    public boolean isKeyMappingResetByBudSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isKeyMappingResetByBudSupported();
        }
        return false;
    }

    public boolean isRwsChannelFeatureSupported() {
        DspCapability dspCapability = this.p;
        return dspCapability != null ? dspCapability.isRwsChannelSupported() : isRws();
    }

    public boolean isLanguageSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isLanguageSupported();
    }

    public boolean isLeNameSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isLeNameSupported();
    }

    public boolean isLegacyNameSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isBrEdrNameSupported();
    }

    public boolean isOtaSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isOtaSupported();
    }

    public boolean isAncSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAncSupported();
    }

    public boolean isAncEqSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAncEqSupported();
    }

    public boolean isAncEqConfigureSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAncEqConfigureSupported();
    }

    public boolean isAncEnabled() {
        return this.O == 1;
    }

    public boolean isNormalModeEqAllowed() {
        if ((b.a(this.a, this.b) || b.a(this.a)) && isAncEqSupported()) {
            return !isAncEnabled();
        }
        return true;
    }

    public boolean isListeningModeCycleSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isListeningModeCycleSupported();
    }

    public boolean isListeningModeToggleSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAncSupported() || this.p.isDspAptSupported() || this.p.isLlAptSupported();
    }

    public boolean isAncAndDspAptListeningModeSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAncAndDspAptListeningModeSupported();
    }

    public boolean isDeviceIdSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isDeviceIdSupported();
        }
        return false;
    }

    public boolean isGamingModeSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isGamingModeSupported();
        }
        return false;
    }

    public void updateLowLatencyInfo(@NonNull com.realsil.customer.bbpro.d.a aVar) {
        this.z = aVar.a;
        this.A = aVar.b;
        this.B = aVar.c;
        this.C = aVar.d;
    }

    public void updateLowLatencyLevelReport(@NonNull com.realsil.customer.bbpro.d.b bVar) {
        this.A = bVar.b;
        this.C = bVar.a;
    }

    public boolean isGamingModeEnabled() {
        return this.z;
    }

    public void setGamingModeEnabled(boolean z) {
        this.z = z;
    }

    public int getMaxLatencyLevel() {
        return this.B;
    }

    public int getCurrentLatencyLevel() {
        return this.C;
    }

    public int getLatencyValue() {
        return this.A;
    }

    public boolean isGamingModeEqSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isGamingModeEqSupported();
        }
        return false;
    }

    public boolean isAncScenarioSupported() {
        AncGroup ancGroup;
        DspCapability dspCapability = this.p;
        if (dspCapability == null || !dspCapability.isAncSupported() || (ancGroup = this.P) == null) {
            return false;
        }
        return ancGroup.isAncScenarioSupported();
    }

    public AncGroup getAncGroup() {
        return this.P;
    }

    public void setAncGroup(AncGroup ancGroup) {
        this.P = ancGroup;
        if (ancGroup != null) {
            this.O = ancGroup.getStatus();
        }
    }

    public AncGroup getSpecialAncGroup() {
        return this.Q;
    }

    public void setSpecialAncGroup(AncGroup ancGroup) {
        this.Q = ancGroup;
        if (ancGroup != null) {
            this.O = ancGroup.getStatus();
        }
    }

    public byte getAncStatus() {
        return this.O;
    }

    public void setAncStatus(byte b) {
        this.O = b;
    }

    public void setLlAptStatus(byte b) {
        this.T = b;
    }

    public LlAptBasicInfo getLlaptBasicInfo() {
        return this.U;
    }

    public void setLlaptBasicInfo(LlAptBasicInfo llAptBasicInfo) {
        this.U = llAptBasicInfo;
        if (llAptBasicInfo != null) {
            this.T = llAptBasicInfo.getStatus();
        }
    }

    public LlAptScenarioGroupInfo getLlaptScenarioChooseInfo() {
        return this.V;
    }

    public void setLlaptScenarioChooseInfo(LlAptScenarioGroupInfo llAptScenarioGroupInfo) {
        this.V = llAptScenarioGroupInfo;
    }

    public AncScenarioGroupInfo getAncScenarioChooseInfo() {
        return this.R;
    }

    public void setAncScenarioChooseInfo(AncScenarioGroupInfo ancScenarioGroupInfo) {
        this.R = ancScenarioGroupInfo;
    }

    public AptVolumeInfo getAptVolumeInfo() {
        return this.L;
    }

    public void setAptVolumeInfo(AptVolumeInfo aptVolumeInfo) {
        this.L = aptVolumeInfo;
    }

    public void setAptVolumeStatus(AptVolumeStatus aptVolumeStatus) {
        AptVolumeInfo aptVolumeInfo = this.L;
        if (aptVolumeInfo != null) {
            aptVolumeInfo.updateAptVolumeStatus(aptVolumeStatus);
        }
    }

    public boolean isAptVolumeRwsSyncSupported() {
        return isAptVolumeRwsSyncToggleSupported();
    }

    public boolean isAptVolumeRwsSyncToggleSupported() {
        AptVolumeInfo aptVolumeInfo;
        DspCapability dspCapability = this.p;
        if (dspCapability == null || dspCapability.isAptVolumeForceSyncSupported() || (aptVolumeInfo = this.L) == null) {
            return false;
        }
        return aptVolumeInfo.isRwsSyncSupported();
    }

    public boolean isAptVolumeIndividualAdjustSupported() {
        if (this.p == null) {
            return false;
        }
        return !r0.isAptVolumeForceSyncSupported();
    }

    public boolean isAptVolumeForceSyncSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return true;
        }
        return dspCapability.isAptVolumeForceSyncSupported();
    }

    public boolean isAptVolumeGainSaveFlashSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isAptVolumeGainSaveFlashSupported();
    }

    public boolean isLockButtonSupported() {
        DspCapability dspCapability = this.p;
        return dspCapability == null ? this.c == 1 : dspCapability.isLockButtonSupported();
    }

    public boolean isDurianMasterSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isDurianMasterSupported();
    }

    public void setAptVolumeSyncEnabled(boolean z) {
        AptVolumeInfo aptVolumeInfo = this.L;
        if (aptVolumeInfo != null) {
            aptVolumeInfo.setRwsSyncEnabled(z);
        }
    }

    public LlAptBrightnessInfo getLlAptBrightnessInfo() {
        return this.W;
    }

    public void setLlAptBrightnessInfo(LlAptBrightnessInfo llAptBrightnessInfo) {
        this.W = llAptBrightnessInfo;
    }

    public void setLlAptBrighenessStatus(LlAptBrightnessStatus llAptBrightnessStatus) {
        LlAptBrightnessInfo llAptBrightnessInfo = this.W;
        if (llAptBrightnessInfo != null) {
            llAptBrightnessInfo.updateLlAptBrighenessStatus(llAptBrightnessStatus);
        }
    }

    public boolean isSppOtaSupported() {
        return this.b0;
    }

    public void setSppOtaSupported(boolean z) {
        this.b0 = z;
    }

    public boolean isEarDetectionOn() {
        return this.D == 1;
    }

    public void setEarDetectionStatus(byte b) {
        this.D = b;
    }

    public boolean isVpRingtoneSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isVpRingtoneSupported();
    }

    public boolean isVpRwsSyncSupported() {
        if (wrapperRwsInfo().isRws) {
            return getVpVolumeInfo().rwsSyncSupported;
        }
        return false;
    }

    public VpVolumeInfo getVpVolumeInfo() {
        if (this.a0 == null) {
            this.a0 = VpVolumeInfo.DEFAULT;
        }
        return this.a0;
    }

    public void setVpVolumeInfo(VpVolumeInfo vpVolumeInfo) {
        this.a0 = vpVolumeInfo;
    }

    public String getDeviceId() {
        return this.k;
    }

    public void setDeviceId(String str) {
        this.k = str;
    }

    public String getLchSingleDeviceId() {
        return this.l;
    }

    public void setLchSingleDeviceId(String str) {
        this.l = str;
    }

    public String getRchSingleDeviceId() {
        return this.m;
    }

    public void setRchSingleDeviceId(String str) {
        this.m = str;
    }

    public boolean isSppCaptureV2Supported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isSppCaptureV2Supported();
    }

    public boolean isSpatialAudioSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isSpatialAudioSupported();
    }

    public boolean isUiOtaVersionSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isUiOtaVersionSupported();
    }

    public boolean isSpecialAncScenarioSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability == null) {
            return false;
        }
        return dspCapability.isSpecialAncScenarioSupported();
    }

    public ImageVersionInfo getPrimaryUiOtaVersion() {
        return this.c0;
    }

    public void setPrimaryUiOtaVersion(ImageVersionInfo imageVersionInfo) {
        this.c0 = imageVersionInfo;
    }

    public byte getSpatialAudioMode() {
        return this.d0;
    }

    public void setSpatialAudioMode(byte b) {
        this.d0 = b;
    }

    public boolean isDsp3BinScenarioToggleSupported() {
        DspCapability dspCapability = this.p;
        if (dspCapability != null) {
            return dspCapability.isDsp3BinScenarioToggleSupported();
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfo{");
        sb.append(String.format("\n\tCI%04XPI%04XCSV%04XESV%04X", Byte.valueOf(this.a), Byte.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)));
        sb.append(String.format("\n\tLE=%s(%s), BR/EDR=%s", this.f, this.g, this.e));
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tlisteningMode: state=%d", Byte.valueOf(getListeningModeState())));
        if (isListeningModeCycleSupported()) {
            sb.append(String.format(locale, ", cycle=%d", Integer.valueOf(this.Y)));
        }
        if (isAncSupported()) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(isAncEnabled());
            AncGroup ancGroup = this.P;
            objArr[1] = ancGroup != null ? ancGroup.toString() : "";
            sb.append(String.format(locale, "\n\tANC: enabled=%b, group=%s", objArr));
        }
        if (isAptSupported()) {
            sb.append(String.format(locale, "\n\tAPT: enabled=%b, NR=%b,type=0x%02X,toggleType=%b", Boolean.valueOf(isAptEnabled()), Boolean.valueOf(this.M), Integer.valueOf(this.X), Boolean.valueOf(isAptTypeToggleSupported())));
            if (this.X == 1) {
                Object[] objArr2 = new Object[1];
                LlAptBasicInfo llAptBasicInfo = this.U;
                objArr2[0] = llAptBasicInfo != null ? llAptBasicInfo.toString() : "null";
                sb.append(String.format(locale, "\n\t\tllaptBasicInfo=%s", objArr2));
            }
        }
        if (isSpatialAudioSupported()) {
            sb.append(String.format("\n\tspatialAudioMode=0x%02X", Byte.valueOf(this.d0)));
        }
        sb.append(String.format("\n\tfindMe: L=%b, R=%b", Boolean.valueOf(isLeftBudFindMeEnabled()), Boolean.valueOf(isRightBudFindMeEnabled())));
        sb.append(String.format(locale, "\n\tvolume=%d/%d", Integer.valueOf(this.x), Integer.valueOf(this.y)));
        sb.append(String.format("\n\tgamingModeEnabled=%b, earDetectionStatus=0x%02X", Boolean.valueOf(this.z), Byte.valueOf(this.D)));
        sb.append(String.format("\n\tmLockButtonStatus=0x%02X", Byte.valueOf(this.K)));
        sb.append(String.format("\n\tsppOtaSupported= %b", Boolean.valueOf(this.b0)));
        RwsInfo rwsInfoWrapperRwsInfo = wrapperRwsInfo();
        if (rwsInfoWrapperRwsInfo != null) {
            sb.append(String.format("\n\trwsInfo= %s", rwsInfoWrapperRwsInfo.toString()));
        }
        sb.append("\n}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeByte(this.a);
        parcel.writeByte(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeParcelable(this.n, i);
        parcel.writeParcelable(this.o, i);
        parcel.writeParcelable(this.p, i);
        parcel.writeByteArray(this.q);
        parcel.writeByteArray(this.r);
        parcel.writeByteArray(this.s);
        parcel.writeTypedList(this.t);
        parcel.writeTypedList(this.u);
        parcel.writeByte(this.v);
        parcel.writeByte(this.w);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
        parcel.writeByte(this.z ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.A);
        parcel.writeInt(this.B);
        parcel.writeInt(this.C);
        parcel.writeByte(this.D);
        parcel.writeByte(this.E);
        parcel.writeByte(this.F);
        parcel.writeByte(this.G);
        parcel.writeInt(this.H);
        parcel.writeInt(this.I);
        parcel.writeInt(this.J);
        parcel.writeByte(this.K);
        parcel.writeParcelable(this.L, i);
        parcel.writeByte(this.M ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.N);
        parcel.writeByte(this.O);
        parcel.writeParcelable(this.P, i);
        parcel.writeParcelable(this.R, i);
        parcel.writeByte(this.S);
        parcel.writeByte(this.T);
        parcel.writeParcelable(this.U, i);
        parcel.writeParcelable(this.V, i);
        parcel.writeParcelable(this.W, i);
        parcel.writeInt(this.X);
        parcel.writeInt(this.Y);
        parcel.writeByte(this.Z);
        parcel.writeParcelable(this.a0, i);
        parcel.writeByte(this.b0 ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.c0, i);
        parcel.writeByte(this.d0);
    }

    public void setAncStatus(boolean z, int i) {
        this.O = this.O;
        AncGroup ancGroup = this.P;
        if (ancGroup != null) {
            ancGroup.setActiveGroupIndex(i);
        }
    }

    public DeviceInfo(Parcel parcel) {
        this.c = 0;
        this.d = 0;
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = 0;
        this.j = 0;
        this.k = "";
        this.l = "";
        this.m = "";
        this.v = (byte) 0;
        this.w = (byte) 0;
        this.z = false;
        this.A = 0;
        this.B = -1;
        this.C = -1;
        this.D = (byte) 0;
        this.E = (byte) 0;
        this.F = (byte) 0;
        this.G = (byte) 0;
        this.S = (byte) 0;
        this.X = 0;
        this.Y = 0;
        this.Z = (byte) 0;
        this.a = parcel.readByte();
        this.b = parcel.readByte();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = (DeviceStatusInfo0) parcel.readParcelable(DeviceStatusInfo0.class.getClassLoader());
        this.o = (DeviceStatusInfo) parcel.readParcelable(DeviceStatusInfo.class.getClassLoader());
        this.p = (DspCapability) parcel.readParcelable(DspCapability.class.getClassLoader());
        this.q = parcel.createByteArray();
        this.r = parcel.createByteArray();
        this.s = parcel.createByteArray();
        Parcelable.Creator<KeyMmiSettings> creator = KeyMmiSettings.CREATOR;
        this.t = parcel.createTypedArrayList(creator);
        this.u = parcel.createTypedArrayList(creator);
        this.v = parcel.readByte();
        this.w = parcel.readByte();
        this.x = parcel.readInt();
        this.y = parcel.readInt();
        this.z = parcel.readByte() != 0;
        this.A = parcel.readInt();
        this.B = parcel.readInt();
        this.C = parcel.readInt();
        this.D = parcel.readByte();
        this.E = parcel.readByte();
        this.F = parcel.readByte();
        this.G = parcel.readByte();
        this.H = parcel.readInt();
        this.I = parcel.readInt();
        this.J = parcel.readInt();
        this.K = parcel.readByte();
        this.L = (AptVolumeInfo) parcel.readParcelable(AptVolumeInfo.class.getClassLoader());
        this.M = parcel.readByte() != 0;
        this.N = parcel.readInt();
        this.O = parcel.readByte();
        this.P = (AncGroup) parcel.readParcelable(AncGroup.class.getClassLoader());
        this.R = (AncScenarioGroupInfo) parcel.readParcelable(AncScenarioGroupInfo.class.getClassLoader());
        this.S = parcel.readByte();
        this.T = parcel.readByte();
        this.U = (LlAptBasicInfo) parcel.readParcelable(LlAptBasicInfo.class.getClassLoader());
        this.V = (LlAptScenarioGroupInfo) parcel.readParcelable(LlAptScenarioGroupInfo.class.getClassLoader());
        this.W = (LlAptBrightnessInfo) parcel.readParcelable(LlAptBrightnessInfo.class.getClassLoader());
        this.X = parcel.readInt();
        this.Y = parcel.readInt();
        this.Z = parcel.readByte();
        this.a0 = (VpVolumeInfo) parcel.readParcelable(VpVolumeInfo.class.getClassLoader());
        this.b0 = parcel.readByte() != 0;
        this.c0 = (ImageVersionInfo) parcel.readParcelable(ImageVersionInfo.class.getClassLoader());
        this.d0 = parcel.readByte();
    }
}
