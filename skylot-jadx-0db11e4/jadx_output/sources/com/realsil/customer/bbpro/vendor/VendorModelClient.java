package com.realsil.customer.bbpro.vendor;

import android.content.Context;
import com.realsil.customer.bbpro.apt.AptVolumeInfo;
import com.realsil.customer.bbpro.apt.SetAptVolumeReq;
import com.realsil.customer.bbpro.core.BeeError;
import com.realsil.customer.bbpro.core.protocol.CommandContract;
import com.realsil.customer.bbpro.core.transportlayer.AckPacket;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.customer.bbpro.i.d;
import com.realsil.customer.bbpro.internal.ModelClient;
import com.realsil.customer.bbpro.internal.UserTask;
import com.realsil.customer.bbpro.llapt.LlAptBasicInfo;
import com.realsil.customer.bbpro.model.AncGroup;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.core.logger.ZLogger;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/VendorModelClient.class */
public class VendorModelClient extends com.realsil.customer.bbpro.vendor.a {
    public static volatile VendorModelClient j;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/VendorModelClient$a.class */
    public class a extends UserTask {
        public a(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            DeviceInfo vendorDeviceInfo = VendorModelClient.this.getVendorDeviceInfo();
            DeviceInfo vendorDeviceInfo2 = vendorDeviceInfo;
            if (vendorDeviceInfo.isListeningModeReportSupported()) {
                ZLogger.v(ModelClient.DBG, "load listening mode status");
                startSubTask(false);
                if (VendorModelClient.this.queryListeningModeStatus().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryListeningModeStatus failed");
                    return;
                }
                waitTaskComplete();
            }
            if (vendorDeviceInfo2.isListeningModeCycleSupported()) {
                ZLogger.v(ModelClient.DBG, "getListeningModeCycle");
                startSubTask(false);
                if (VendorModelClient.this.getListeningModeCycle().code != 0) {
                    stopSubTask();
                    ZLogger.w("getListeningModeCycle failed");
                    return;
                }
                waitTaskComplete();
            }
            if (vendorDeviceInfo2.isAptTypeToggleSupported()) {
                ZLogger.v(ModelClient.DBG, "load apt type");
                startSubTask(false);
                if (VendorModelClient.this.queryAptType().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryAptType failed");
                    return;
                } else {
                    waitTaskComplete();
                    vendorDeviceInfo2 = VendorModelClient.this.getVendorDeviceInfo();
                }
            }
            if (vendorDeviceInfo2.getAptFeatureType() == 0) {
                ZLogger.v(ModelClient.DBG, "load dsp-apt status");
                startSubTask(false);
                if (VendorModelClient.this.getStatus((byte) 3).code != 0) {
                    stopSubTask();
                    ZLogger.w("get STATUS_INDEX_APT_STATUS failed");
                    return;
                }
                waitTaskComplete();
            } else if (vendorDeviceInfo2.getAptFeatureType() == 1) {
                ZLogger.v(ModelClient.DBG, "load ll-apt status");
                startSubTask(false);
                if (VendorModelClient.this.queryLlApt((byte) 1).code != 0) {
                    stopSubTask();
                    ZLogger.w("get LLAPT_BASIC_INFO failed");
                    return;
                }
                waitTaskComplete();
            } else {
                ZLogger.d("un-supported AptFeatureType=" + vendorDeviceInfo2.getAptFeatureType());
            }
            if (vendorDeviceInfo2.isAncSupported()) {
                ZLogger.v(ModelClient.DBG, "load anc status and anc group");
                startSubTask(false);
                if (VendorModelClient.this.queryAnc((byte) 0).code != 0) {
                    stopSubTask();
                    ZLogger.w("get ANC_QUERY_TYPE_BASIC_INFO failed");
                    return;
                }
                waitTaskComplete();
            }
            if (vendorDeviceInfo2.isAncAndDspAptListeningModeSupported() && vendorDeviceInfo2.isSpecialAncScenarioSupported()) {
                ZLogger.v(ModelClient.DBG, "load anc+dsp apt status and anc+dsp apt group");
                startSubTask(false);
                if (VendorModelClient.this.querySpecialAnc((byte) 0).code != 0) {
                    stopSubTask();
                    ZLogger.w("querySpecialAnc failed");
                    return;
                }
                waitTaskComplete();
            }
            VendorModelClient.this.a(40);
            VendorModelClient.this.removeTask(this);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/VendorModelClient$b.class */
    public class b extends UserTask {
        public b(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            ZLogger.v(ModelClient.VDBG, "load RWS default role");
            startSubTask(false);
            if (VendorModelClient.this.getVendorClient().getStatus((byte) 5).code != 0) {
                stopSubTask();
                ZLogger.w("get STATUS_INDEX_RWS_DEFAULT_ROLE failed");
                return;
            }
            waitTaskComplete();
            DeviceInfo deviceInfo = VendorModelClient.this.getDeviceInfo();
            if (com.realsil.customer.bbpro.i.b.a(deviceInfo.getChipId(), deviceInfo.getPackageId()) || com.realsil.customer.bbpro.i.b.a(deviceInfo.getChipId())) {
                ZLogger.v(ModelClient.VDBG, "load RWS bud side");
                if (VendorModelClient.this.getVendorClient().getStatus((byte) 13).code != 0) {
                    stopSubTask();
                    ZLogger.w("get STATUS_INDEX_RWS_BUD_SIDE failed");
                    return;
                }
                waitTaskComplete();
            } else {
                ZLogger.v(ModelClient.VDBG, "load RWS default channel");
                if (VendorModelClient.this.getVendorClient().getStatus((byte) 12).code != 0) {
                    stopSubTask();
                    ZLogger.w("get RWS_DEFAULT_CHANNEL failed");
                    return;
                }
                waitTaskComplete();
            }
            ZLogger.v(ModelClient.VDBG, "load RWS state");
            if (VendorModelClient.this.getVendorClient().getStatus((byte) 0).code != 0) {
                stopSubTask();
                ZLogger.w("get RWS_STATE failed");
                return;
            }
            waitTaskComplete();
            ZLogger.v(ModelClient.VDBG, "load battery");
            if (VendorModelClient.this.getVendorClient().getStatus((byte) 2).code != 0) {
                stopSubTask();
                ZLogger.w("get BATTERY_STATUS failed");
            } else {
                waitTaskComplete();
                VendorModelClient.this.a(39);
                VendorModelClient.this.removeTask(this);
            }
        }
    }

    public VendorModelClient(Context context) {
        super(context);
    }

    public static VendorModelClient getInstance() {
        if (j != null) {
            return j;
        }
        ZLogger.w("please call setup(Context, BeeProManager) first");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.bbpro.vendor.VendorModelClient>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static void initialize(Context context) {
        if (j == null) {
            ?? r0 = VendorModelClient.class;
            synchronized (r0) {
                if (j == null) {
                    j = new VendorModelClient(context.getApplicationContext());
                }
                r0 = r0;
            }
        }
    }

    public final BeeError b(DeviceInfo deviceInfo) {
        if (deviceInfo.isAptEnabled()) {
            return new BeeError(48, "APT has already enabled");
        }
        ZLogger.v(ModelClient.VDBG, "enable APT");
        if (deviceInfo.getAptFeatureType() != 1) {
            return sendMmi((byte) 101);
        }
        LlAptBasicInfo llaptBasicInfo = deviceInfo.getLlaptBasicInfo();
        return setLlApt((byte) 1, llaptBasicInfo != null ? llaptBasicInfo.getActiveGroupIndex() : 0);
    }

    public final BeeError a(DeviceInfo deviceInfo) {
        ZLogger.v(ModelClient.VDBG, "enable ANC");
        AncGroup ancGroup = deviceInfo.getAncGroup();
        return setAnc((byte) 1, ancGroup != null ? ancGroup.getActiveGroupIndex() : 0);
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public void destroy() {
        super.destroy();
        j = null;
    }

    public BeeError createConnection(byte b2, byte b3, byte[] bArr) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = (byte) (b2 & 255);
        bArr2[1] = (byte) (b3 & 255);
        if (bArr != null && bArr.length == 6) {
            System.arraycopy(bArr, 0, bArr2, 2, 6);
        }
        return sendVendorData(CommandContract.buildPacket((short) 2, bArr2));
    }

    public BeeError disconnect(byte[] bArr, byte b2) {
        byte[] bArr2 = new byte[7];
        if (bArr != null && bArr.length == 6) {
            System.arraycopy(bArr, 0, bArr2, 0, 6);
        }
        bArr2[6] = (byte) (b2 & 255);
        return sendVendorData(CommandContract.buildPacket((short) 3, bArr2));
    }

    public BeeError playRingtoneDuringSoundPressCalibration() {
        return sendVendorData(CommandContract.buildPacket((short) 2832));
    }

    public BeeError getGamingModeStatus() {
        return sendAppReq(new d.b().a());
    }

    public BeeError getGamingModeState() {
        return getGamingModeStatus();
    }

    public BeeError toggleGamingMode() {
        return sendMmi(Byte.MIN_VALUE);
    }

    public BeeError getEarDetectionStatus() {
        return sendVendorData(CommandContract.buildPacket((short) 3586, (byte[]) null));
    }

    public BeeError toggleEarDetectionStatus() {
        return sendMmi((byte) -45);
    }

    public BeeError setFindMeState(byte b2, byte b3) {
        return sendVendorData(CommandContract.buildPacket((short) 1798, new byte[]{b2, b3}));
    }

    public BeeError setVolume(int i) {
        return sendVendorData(CommandContract.buildPacket((short) 522, new byte[]{(byte) (i & 255)}));
    }

    public BeeError getVolume() {
        return getStatus((byte) -95);
    }

    public BeeError volumeUp() {
        return sendMmi((byte) 48);
    }

    public BeeError volumeDown() {
        return sendMmi((byte) 49);
    }

    public BeeError aptVolumeUp() {
        int aptVolumeVersion = getDeviceInfo().getAptVolumeVersion();
        if (aptVolumeVersion == 1) {
            return sendMmi((byte) -123);
        }
        if (aptVolumeVersion != 2) {
            return new BeeError(49);
        }
        AptVolumeInfo aptVolumeInfo = getDeviceInfo().getAptVolumeInfo();
        if (aptVolumeInfo != null) {
            int mainVolumeLevel = aptVolumeInfo.getMainVolumeLevel() + 1;
            return mainVolumeLevel > aptVolumeInfo.getMainMaxVolumeLevel() ? new BeeError(17) : sendAppReq(new SetAptVolumeReq.Builder(aptVolumeVersion, (byte) 0).volumeLevel(mainVolumeLevel & 15).build());
        }
        ZLogger.d("please call GetAptVolumeReq first");
        return new BeeError(17);
    }

    public BeeError aptVolumeDown() {
        int aptVolumeVersion = getDeviceInfo().getAptVolumeVersion();
        if (aptVolumeVersion == 1) {
            return sendMmi((byte) -122);
        }
        if (aptVolumeVersion != 2) {
            return new BeeError(49);
        }
        AptVolumeInfo aptVolumeInfo = getDeviceInfo().getAptVolumeInfo();
        if (aptVolumeInfo != null) {
            int mainVolumeLevel = aptVolumeInfo.getMainVolumeLevel() - 1;
            return mainVolumeLevel < 0 ? new BeeError(17) : sendAppReq(new SetAptVolumeReq.Builder(aptVolumeVersion, (byte) 0).volumeLevel(mainVolumeLevel & 15).build());
        }
        ZLogger.d("please call GetAptVolumeReq first");
        return new BeeError(17);
    }

    public BeeError getSupportedMmiList() {
        return sendVendorData(CommandContract.buildPacket((short) 1792));
    }

    public BeeError getSupportedClickType() {
        return sendVendorData(CommandContract.buildPacket((short) 1793));
    }

    public BeeError getSupportedCallStatus() {
        return sendVendorData(CommandContract.buildPacket((short) 1794));
    }

    public BeeError setKeyMmiMap(byte[] bArr) {
        return setKeyMmiMap(false, bArr);
    }

    public BeeError getLockButtonState() {
        return getStatus((byte) 8);
    }

    public BeeError getFindMeStatus() {
        return getStatus((byte) 9);
    }

    public BeeError getAptNrStatus() {
        return getStatus((byte) 6);
    }

    public BeeError toggleAptNr() {
        return sendMmi((byte) -124);
    }

    public BeeError toggleLockButton() {
        return sendMmi((byte) -94);
    }

    public BeeError getVibratorStatus() {
        return sendVendorData(CommandContract.buildPacket((short) 1811));
    }

    public BeeError getMotorModeParameters() {
        return sendVendorData(CommandContract.buildPacket((short) 1815));
    }

    public BeeError setVibratorEnable() {
        return sendVendorData(CommandContract.buildPacket((short) 1808));
    }

    public BeeError setVibratorDisable() {
        return sendVendorData(CommandContract.buildPacket((short) 1809));
    }

    public BeeError toggleVibratorAndVp() {
        return sendVendorData(CommandContract.buildPacket((short) 1810));
    }

    public BeeError setVibratorModeParameters(int i, int i2, int i3) {
        return sendVendorData(CommandContract.buildPacket((short) 1812, new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) (i3 & 255)}));
    }

    public BeeError stopVibration() {
        return sendVendorData(CommandContract.buildPacket((short) 1813));
    }

    public BeeError checkVibration() {
        return sendVendorData(CommandContract.buildPacket((short) 1814));
    }

    public BeeError queryListeningModeInfo() {
        return getVendorDeviceInfo() == null ? new BeeError(16) : execute(new a(0, ModelClient.UUID_QUERY_LISTENING_MODE_INFO));
    }

    public BeeError queryAptBasicInfo2() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        return vendorDeviceInfo == null ? new BeeError(16) : vendorDeviceInfo.getAptFeatureType() == 0 ? getStatus((byte) 3) : vendorDeviceInfo.getAptFeatureType() == 1 ? queryLlApt((byte) 1) : new BeeError(48, "not support AptFeatureType=" + vendorDeviceInfo.getAptFeatureType());
    }

    public BeeError enableAncScenario(int i) {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (deviceInfo == null) {
            return new BeeError(16);
        }
        if (!deviceInfo.isListeningModeReportSupported()) {
            return setAnc((byte) 1, i);
        }
        byte[] bArr = new byte[2];
        if (deviceInfo.getListeningModeState() == 4) {
            bArr[0] = 4;
        } else {
            bArr[0] = 2;
        }
        bArr[1] = (byte) (i & 255);
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3131, bArr).eventId((short) 3131).build());
    }

    public BeeError enableAnc(int i) {
        return enableAncScenario(i);
    }

    public BeeError disableAnc() {
        BeeError anc = setAnc((byte) 0, 0);
        if (anc.code == 0) {
            this.g = false;
        }
        return anc;
    }

    public BeeError setAnc(byte b2, int i) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3105, new byte[]{b2, (byte) (i & 255)}).eventId((short) 3105).build());
    }

    public BeeError enableAptScenario(int i) {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo == null ? new BeeError(16) : deviceInfo.isListeningModeReportSupported() ? sendVendorData(new Command.Builder().writeType(2).packet((short) 3131, new byte[]{3, (byte) (i & 255)}).eventId((short) 3131).build()) : setLlApt((byte) 1, i);
    }

    public BeeError setLlApt(byte b2, int i) {
        return sendVendorData(CommandContract.buildPacket((short) 3107, new byte[]{b2, (byte) (i & 255)}));
    }

    public BeeError setListeningModeCycle(byte b2) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3115, new byte[]{b2}).eventId((short) 3115).build());
    }

    public BeeError getListeningModeCycle() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3116, null).eventId((short) 3116).build());
    }

    public BeeError setListeningMode(byte b2) {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (deviceInfo == null) {
            return new BeeError(16);
        }
        if (!deviceInfo.isListeningModeReportSupported()) {
            if (b2 != 0) {
                return b2 == 2 ? a(deviceInfo) : (b2 == 1 || b2 == 3) ? b(deviceInfo) : new BeeError(48);
            }
            if (deviceInfo.isAptEnabled()) {
                ZLogger.v(ModelClient.VDBG, "disable APT");
                return b();
            }
            if (deviceInfo.isAncEnabled()) {
                ZLogger.v(ModelClient.VDBG, "disable ANC");
                return disableAnc();
            }
            ZLogger.d(ModelClient.VDBG, "ANC & APT are all OFF");
            return new BeeError(48);
        }
        byte[] bArr = new byte[2];
        if (b2 == 0) {
            bArr[0] = 0;
        } else if (b2 == 2) {
            ZLogger.v(ModelClient.VDBG, "enable ANC");
            AncGroup ancGroup = deviceInfo.getAncGroup();
            int activeGroupIndex = ancGroup != null ? ancGroup.getActiveGroupIndex() : 0;
            bArr[0] = 2;
            bArr[1] = (byte) (activeGroupIndex & 255);
        } else if (b2 == 1) {
            ZLogger.v(ModelClient.VDBG, "enable DSP APT");
            bArr[0] = 1;
        } else if (b2 == 3) {
            ZLogger.v(ModelClient.VDBG, "enable LL-APT");
            if (deviceInfo.getAptFeatureType() == 1) {
                LlAptBasicInfo llaptBasicInfo = deviceInfo.getLlaptBasicInfo();
                int activeGroupIndex2 = llaptBasicInfo != null ? llaptBasicInfo.getActiveGroupIndex() : 0;
                bArr[0] = 3;
                bArr[1] = (byte) (activeGroupIndex2 & 255);
            } else {
                bArr[0] = 1;
            }
        } else {
            if (b2 != 4) {
                return new BeeError(48);
            }
            ZLogger.v(ModelClient.VDBG, "enable ANC + DSP APT");
            AncGroup specialAncGroup = deviceInfo.getSpecialAncGroup();
            int activeGroupIndex3 = specialAncGroup != null ? specialAncGroup.getActiveGroupIndex() : 0;
            bArr[0] = 4;
            bArr[1] = (byte) (activeGroupIndex3 & 255);
        }
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3131, bArr).eventId((short) 3131).build());
    }

    public BeeError queryAptType() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3133, new byte[]{0}).eventId((short) 3133).build());
    }

    public BeeError assignAptType(int i) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3134, new byte[]{(byte) (i & 255)}).eventId((short) 3134).build());
    }

    public BeeError getBudInfo() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo == null ? new BeeError(16) : deviceInfo.isBudInfoReqSupported() ? sendVendorData(CommandContract.buildPacket((short) 33)) : execute(new b(0, ModelClient.UUID_QUERY_BUD_INFO));
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError getRwsKeyMmiMap() {
        return super.getRwsKeyMmiMap();
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError getKeyMmiMap() {
        return super.getKeyMmiMap();
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError queryListeningModeStatus() {
        return super.queryListeningModeStatus();
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError queryLlApt(byte b2) {
        return super.queryLlApt(b2);
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError querySpecialAnc(byte b2) {
        return super.querySpecialAnc(b2);
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError queryAnc(byte b2) {
        return super.queryAnc(b2);
    }

    @Override // com.realsil.customer.bbpro.vendor.a, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ boolean processStatusReport(byte b2, byte[] bArr) {
        return super.processStatusReport(b2, bArr);
    }

    @Override // com.realsil.customer.bbpro.vendor.a, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        return super.processEventPacket(transportLayerPacket);
    }

    @Override // com.realsil.customer.bbpro.vendor.a, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ boolean processAckPacket(AckPacket ackPacket) {
        return super.processAckPacket(ackPacket);
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError sendMmi(byte b2) {
        return super.sendMmi(b2);
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ BeeError getStatus(byte b2) {
        return super.getStatus(b2);
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ void clearDeviceInfo() {
        super.clearDeviceInfo();
    }

    @Override // com.realsil.customer.bbpro.vendor.a
    public /* bridge */ /* synthetic */ DeviceInfo getDeviceInfo() {
        return super.getDeviceInfo();
    }

    @Override // com.realsil.customer.bbpro.vendor.a, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ int getVendorEvent() {
        return super.getVendorEvent();
    }

    @Override // com.realsil.customer.bbpro.vendor.a, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ int getVendorCmd() {
        return super.getVendorCmd();
    }

    public BeeError setKeyMmiMap(boolean z, byte[] bArr) {
        return sendVendorData(z ? CommandContract.buildPacket((short) 1801, bArr) : CommandContract.buildPacket((short) 1796, bArr));
    }

    public final BeeError b() {
        BeeError beeErrorSendMmi;
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return new BeeError(16);
        }
        if (!vendorDeviceInfo.isAptEnabled()) {
            return new BeeError(48, "APT has already disabled");
        }
        if (vendorDeviceInfo.getAptFeatureType() == 1) {
            BeeError llApt = setLlApt((byte) 0, 0);
            beeErrorSendMmi = llApt;
            if (llApt.code == 0) {
                this.h = false;
            }
        } else {
            beeErrorSendMmi = sendMmi((byte) 101);
        }
        return beeErrorSendMmi;
    }
}
