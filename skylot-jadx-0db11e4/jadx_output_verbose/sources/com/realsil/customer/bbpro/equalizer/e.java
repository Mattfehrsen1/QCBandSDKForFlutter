package com.realsil.customer.bbpro.equalizer;

import android.content.Context;
import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.DspConfig;
import com.realsil.customer.bbpro.core.BeeError;
import com.realsil.customer.bbpro.core.protocol.CommandContract;
import com.realsil.customer.bbpro.core.transportlayer.AckPacket;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.customer.bbpro.equalizer.GetEqInfoReq;
import com.realsil.customer.bbpro.internal.ModelClient;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.bbpro.model.DspParams;
import com.realsil.customer.core.bluetooth.utils.BluetoothUuid;
import com.realsil.customer.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/e.class */
public class e extends ModelClient<EqModelCallback> {
    public static final UUID UUID_EQ_QUERY_BASIC_INFO = BluetoothUuid.fromShortValue(769);
    public static final UUID UUID_EQ_QUERY_MIC_BASIC_INFO = BluetoothUuid.fromShortValue(770);
    public static final UUID UUID_SET_EQ_INDEX_PARAMETER = BluetoothUuid.fromShortValue(771);
    public static final int FS_441K = 44100;
    public static final int FS_48K = 48000;
    public static final int FS_96K = 96000;
    public DspConfig g;
    public EqInfo h;
    public byte i;
    public byte[] j;
    public int k;
    public int l;
    public int m;
    public int n;

    public e(Context context) {
        super(context);
        this.i = (byte) 0;
        this.k = 0;
        this.l = 1;
        this.m = 2;
        this.n = 0;
        getDspConfig();
    }

    public final boolean a(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId == 532) {
            a(status);
            return true;
        }
        switch (toAckId) {
            case 516:
                if (ModelClient.VDBG) {
                    ZLogger.v(String.format("onSetAudioEqIndexParamsResponse: 0x%02X", Byte.valueOf(status)));
                }
                updateUserTasks(UUID_SET_EQ_INDEX_PARAMETER, status);
                return true;
            case 517:
                if (status == 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(String.format("onGetAudioEqResponse: 0x%02X", Byte.valueOf(status)));
                }
                a(status, (byte) 0, (byte[]) null);
                return true;
            case 518:
                if (ModelClient.VDBG) {
                    ZLogger.v(String.format("onClearAudioEqResponse: 0x%02X", Byte.valueOf(status)));
                }
                List<MCB> list = this.mCallbacks;
                if (list == 0 || list.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((EqModelCallback) it.next()).onClearAudioEqResponse(status);
                }
                return true;
            case 519:
                if (status == 0) {
                    return true;
                }
                List<MCB> list2 = this.mCallbacks;
                if (list2 == 0 || list2.size() <= 0) {
                    ZLogger.v(ModelClient.VDBG, "no callback registered");
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v("not support eq index for old framework, just use realtime eq as default");
                }
                Iterator it2 = this.mCallbacks.iterator();
                while (it2.hasNext()) {
                    ((EqModelCallback) it2.next()).onAudioEqIndexReport((byte) 0, 512, 512);
                }
                return true;
            case 520:
                a(status, 0);
                return true;
            default:
                return false;
        }
    }

    public final boolean b(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId != 523) {
            if (toAckId == 524) {
                if (status == 0) {
                    return true;
                }
                updateUserTasks(UUID_EQ_QUERY_MIC_BASIC_INFO, status);
                a(status, new EqEntryIndex(1, 0, 0, 3));
                return true;
            }
            if (toAckId == 528) {
                if (status == 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(String.format("queryEqBasicInfo failed: 0x%02X", Byte.valueOf(status)));
                }
                updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, status);
                return true;
            }
            if (toAckId != 3587) {
                switch (toAckId) {
                    case 512:
                        if (status != 0) {
                            int i = this.n;
                            int i2 = this.l;
                            if ((i & i2) == i2) {
                                updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, status);
                                a(status, this.i);
                            }
                            int i3 = this.n;
                            int i4 = this.m;
                            if ((i3 & i4) == i4) {
                                updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, status);
                                updateUserTasks(UUID_EQ_QUERY_MIC_BASIC_INFO, status);
                                a(status, 0, 0);
                            }
                        }
                        this.n = this.k;
                        return true;
                    case 513:
                        if (ModelClient.VDBG) {
                            ZLogger.v(String.format("onEnableAudioEqResponse: 0x%02X", Byte.valueOf(status)));
                        }
                        if (status != 0) {
                            a(status, this.i);
                            return true;
                        }
                        if (ModelClient.VDBG) {
                            ZLogger.v("automate to query eq state after enable eq");
                        }
                        queryEqState();
                        return true;
                    case 514:
                        if (ModelClient.VDBG) {
                            ZLogger.v(String.format("onDisableAudioEqResponse: 0x%02X", Byte.valueOf(status)));
                        }
                        if (status != 0) {
                            a(status, this.i);
                            return true;
                        }
                        if (ModelClient.VDBG) {
                            ZLogger.v("automate to query eq state after disable eq");
                        }
                        queryEqState();
                        return true;
                    case CommandContract.CMD_DSP_GET_PARAM /* 515 */:
                        if (ModelClient.VDBG) {
                            ZLogger.v(String.format("onSetAudioEqIndexParamsResponse: 0x%02X", Byte.valueOf(status)));
                        }
                        updateUserTasks(UUID_SET_EQ_INDEX_PARAMETER, status);
                        return true;
                    case 516:
                        if (status == 0) {
                            return true;
                        }
                        a(status, (EqIndex) null);
                        return true;
                    case 517:
                        a(status, 0);
                        return true;
                    case 518:
                        if (status == 0) {
                            return true;
                        }
                        if (ModelClient.VDBG) {
                            ZLogger.v(String.format("onGetAudioEqIndexResponse: 0x%02X", Byte.valueOf(status)));
                        }
                        updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, status);
                        a(status, new EqEntryIndex(0, 0, 0, 3));
                        return true;
                    default:
                        return false;
                }
            }
            if (getEqSpecVersion() <= 3) {
                if (status == 0) {
                    return true;
                }
                updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, status);
                a(status, new EqEntryIndex(0, 1, 0, 3));
                return true;
            }
        }
        a(status, 1);
        return true;
    }

    public final int a(double d) {
        int i = (int) d;
        int i2 = i;
        if (i < -12) {
            i2 = -12;
        }
        if (i2 > 12) {
            i2 = 12;
        }
        return i2;
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public int getVendorCmd() {
        return 0;
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public int getVendorEvent() {
        return 0;
    }

    public DspConfig getDspConfig() {
        if (this.g == null) {
            this.g = new DspConfig();
        }
        return this.g;
    }

    public EqInfo getEqInfo() {
        if (this.h == null) {
            this.h = new EqInfo();
        }
        return this.h;
    }

    public boolean isEqIndexFeatureSupported() {
        return true;
    }

    public boolean isEqOnOffSupported() {
        return getEqSpecVersion() < 4;
    }

    public boolean isEqEnabled() {
        return this.i == 1;
    }

    public int getEqSpecVersion() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return 0;
        }
        return vendorDeviceInfo.getEqSpecVersion();
    }

    public int getEqMechanism() {
        int eqSpecVersion = getEqSpecVersion();
        if (eqSpecVersion >= 4) {
            return 2;
        }
        return eqSpecVersion >= 1 ? 1 : 0;
    }

    public boolean isGamingModeSupported() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return false;
        }
        return vendorDeviceInfo.isGamingModeSupported();
    }

    public boolean isGamingModeEnabled() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return false;
        }
        return vendorDeviceInfo.isGamingModeEnabled();
    }

    public boolean isGamingModeEqFeatureSupported() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return false;
        }
        return vendorDeviceInfo.isGamingModeEqSupported();
    }

    public boolean isAncEqSupported() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return false;
        }
        return vendorDeviceInfo.isAncEqConfigureSupported();
    }

    public boolean isAncEnabled() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        if (vendorDeviceInfo == null) {
            return false;
        }
        return vendorDeviceInfo.isAncEnabled();
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public boolean processAckPacket(AckPacket ackPacket) {
        int eqSpecVersion = getEqSpecVersion();
        return eqSpecVersion == 0 ? a(ackPacket) : eqSpecVersion >= 1 ? b(ackPacket) : a(ackPacket);
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        int eqSpecVersion = getEqSpecVersion();
        return eqSpecVersion == 0 ? a(transportLayerPacket) : eqSpecVersion >= 1 ? b(transportLayerPacket) : a(transportLayerPacket);
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public void processDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
        super.processDeviceInfoChanged(deviceInfo, i);
        if (i == 21) {
            boolean z = getEqInfo().gamingModeEnabled != deviceInfo.isGamingModeEnabled();
            getEqInfo().setGamingModeEnabled(deviceInfo.isGamingModeEnabled());
            updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, (byte) 0);
            if (z) {
                a(deviceInfo.isGamingModeEnabled());
            }
        }
    }

    public AudioEq decodeAudioEq(@NonNull byte[] bArr) {
        return decodeAudioEq((byte) 3, bArr, bArr.length);
    }

    public BeeError queryEqState() {
        this.n |= this.l;
        return sendAppReq(new GetEqInfoReq.Builder((byte) 0).build());
    }

    public EqWrapper wrapperAudioEq(AudioEq audioEq) {
        List<EqWrapper> listCalculateEqWrapper = calculateEqWrapper(audioEq.getGlobalGain(), audioEq.getStageNum(), audioEq.getGains(), audioEq.getFreq(), audioEq.getQ(), audioEq.getBiquadType());
        if (listCalculateEqWrapper == null || listCalculateEqWrapper.size() <= 0) {
            return null;
        }
        for (EqWrapper eqWrapper : listCalculateEqWrapper) {
            if (eqWrapper.sampleRate == audioEq.getSampleRate()) {
                return eqWrapper;
            }
        }
        return null;
    }

    public List<EqWrapper> calculateEqWrapper(AudioEq audioEq) {
        return calculateEqWrapper(audioEq.getGlobalGain(), audioEq.getStageNum(), audioEq.getGains(), audioEq.getFreq(), audioEq.getQ(), audioEq.getBiquadType());
    }

    public byte[] calculateEq(AudioEq audioEq) {
        return calculateEq(audioEq.getGlobalGain(), audioEq.getStageNum(), audioEq.getGains(), audioEq.getFreq(), audioEq.getQ(), audioEq.getBiquadType());
    }

    public AudioEq decodeAudioEq(byte b, @NonNull byte[] bArr) {
        return decodeAudioEq(b, bArr, bArr.length);
    }

    public List<EqWrapper> calculateEqWrapper(double d, int i, double[] dArr, double[] dArr2, double[] dArr3) {
        return calculateEqWrapper(d, i, dArr, dArr2, dArr3, AudioEq.BIQUAD_TYPE);
    }

    public byte[] calculateEq(int i, double[] dArr, double[] dArr2, double[] dArr3) {
        return calculateEq(0.0d, i, dArr, dArr2, dArr3, AudioEq.BIQUAD_TYPE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.realsil.customer.bbpro.equalizer.AudioEq decodeAudioEq(byte r14, byte[] r15, int r16) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.bbpro.equalizer.e.decodeAudioEq(byte, byte[], int):com.realsil.customer.bbpro.equalizer.AudioEq");
    }

    public List<EqWrapper> calculateEqWrapper(double d, int i, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        byte[] bArrCalculateEq = calculateEq(d, i, dArr, dArr2, dArr3, iArr);
        if (bArrCalculateEq != null && bArrCalculateEq.length > 0) {
            int length = (bArrCalculateEq.length / 3) - 12;
            if (length <= 0) {
                ZLogger.d("invalid eqDataLength: " + length);
                return arrayList;
            }
            byte[] bArr = new byte[length];
            byte[] bArr2 = new byte[length];
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArrCalculateEq, 12, bArr, 0, length);
            int i2 = 12 + length + 12;
            System.arraycopy(bArrCalculateEq, i2, bArr2, 0, length);
            System.arraycopy(bArrCalculateEq, i2 + length + 12, bArr3, 0, length);
            arrayList.add(new EqWrapper((byte) 3, bArr));
            arrayList.add(new EqWrapper((byte) 4, bArr2));
            arrayList.add(new EqWrapper((byte) 6, bArr3));
            return arrayList;
        }
        ZLogger.d("calculateEq failed");
        return arrayList;
    }

    public byte[] calculateEq(double d, int i, double[] dArr, double[] dArr2, double[] dArr3) {
        return calculateEq(d, i, dArr, dArr2, dArr3, AudioEq.BIQUAD_TYPE);
    }

    public byte[] calculateEq(double d, int i, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
        if (this.a == null) {
            ZLogger.w("please call setup(BaseBeeProManager) method first");
            return null;
        }
        if (i > 0 && dArr != null && dArr2 != null && dArr3 != null) {
            if (dArr.length < i || dArr2.length < i || dArr3.length < i) {
                ZLogger.w("invalid params length");
                return null;
            }
            if (d <= 12.0d && d >= -12.0d) {
                for (int i2 = 0; i2 < i; i2++) {
                    double d2 = dArr[i2];
                    double d3 = dArr3[i2];
                    double d4 = dArr2[i2];
                    if (d2 > 12.0d || d2 < -12.0d) {
                        ZLogger.w(String.format(Locale.US, "gain should between:[%d~%d]", -12, 12));
                        return null;
                    }
                    if (d3 <= 0.0d) {
                        ZLogger.w("q should between:(0, INF]");
                        return null;
                    }
                    if (d4 <= 0.0d) {
                        ZLogger.w("freq should between:(0, Fs/2]");
                        return null;
                    }
                }
                return getDspConfig().calculateEq(d, i, dArr, dArr2, dArr3, iArr);
            }
            ZLogger.w(String.format(Locale.US, "globalGain should between:[%d~%d]", -12, 12));
            return null;
        }
        ZLogger.w("invalid params");
        return null;
    }

    public final boolean a(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        byte[] parameters = transportLayerPacket.getParameters();
        byte[] payload = transportLayerPacket.getPayload();
        switch (opcode) {
            case 513:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_DSP_STATUS");
                }
                List<MCB> list = this.mCallbacks;
                if (list != 0 && list.size() > 0) {
                    Iterator it = this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((EqModelCallback) it.next()).onDspStatusChanged(parameters[0]);
                    }
                    return true;
                }
                if (!ModelClient.VDBG) {
                    return true;
                }
                ZLogger.v("no callback registered");
                return true;
            case 514:
            default:
                return false;
            case CommandContract.CMD_DSP_GET_PARAM /* 515 */:
                if (parameters == null || parameters.length <= 18) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.d(">> EVENT_DSP_REPORT_PARAM");
                }
                DspParams dspParams = new DspParams(parameters);
                List<MCB> list2 = this.mCallbacks;
                if (list2 != 0 && list2.size() > 0) {
                    Iterator it2 = this.mCallbacks.iterator();
                    while (it2.hasNext()) {
                        ((EqModelCallback) it2.next()).onDspParamsChanged(dspParams);
                    }
                    return true;
                }
                if (!ModelClient.VDBG) {
                    return true;
                }
                ZLogger.v("no callback registered");
                return true;
            case 516:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_DSP_REPORT_AUDIO_EQ");
                }
                a(parameters);
                return true;
            case 517:
                com.realsil.customer.bbpro.c.b bVarA = com.realsil.customer.bbpro.c.b.a(payload);
                if (bVarA == null) {
                    return true;
                }
                ZLogger.v(ModelClient.DBG, bVarA.toString());
                List<MCB> list3 = this.mCallbacks;
                if (list3 != 0 && list3.size() > 0) {
                    Iterator it3 = this.mCallbacks.iterator();
                    while (it3.hasNext()) {
                        ((EqModelCallback) it3.next()).onAudioEqIndexReport((byte) 0, bVarA.a, bVarA.b);
                    }
                    return true;
                }
                if (!ModelClient.VDBG) {
                    return true;
                }
                ZLogger.v("no callback registered");
                return true;
        }
    }

    public final boolean b(TransportLayerPacket transportLayerPacket) {
        EqWrapper eqWrapperWrapperAudioEq;
        transportLayerPacket.getPayload();
        short opcode = transportLayerPacket.getOpcode();
        byte[] parameters = transportLayerPacket.getParameters();
        switch (opcode) {
            case 512:
                if (parameters == null || parameters.length < 1) {
                    return true;
                }
                byte b = parameters[0];
                if (b == 0) {
                    if (parameters.length >= 2) {
                        this.i = parameters[1];
                        getEqInfo().setEqEnabled(parameters[1] == 1);
                        ZLogger.v(getEqInfo().toString());
                        updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, (byte) 0);
                    }
                    a((byte) 0, this.i);
                    return true;
                }
                if (b == 1) {
                    getEqInfo().updateEqConfigure1(parameters);
                    updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, (byte) 0);
                    updateUserTasks(UUID_EQ_QUERY_MIC_BASIC_INFO, (byte) 0);
                    a((byte) 0, getEqInfo().normalModeEntryNumber, getEqInfo().gamingModeEntryNumber);
                    return true;
                }
                ZLogger.v(ModelClient.VDBG, String.format(Locale.US, "Unknown query type: 0x%02X", Byte.valueOf(b)));
                return true;
            case 513:
                int eqSpecVersion = getEqSpecVersion();
                EqIndex eqIndex = EqIndex.parse(eqSpecVersion, parameters);
                if (eqIndex != null) {
                    if (eqSpecVersion == 2 || eqSpecVersion == 3 || eqSpecVersion == 4 || eqSpecVersion == 5) {
                        eqIndex.audioEq = decodeAudioEq(eqIndex.sampleRate, eqIndex.eqData);
                    } else if ((eqSpecVersion == 256 || eqSpecVersion == 257 || eqSpecVersion >= 258) && (eqWrapperWrapperAudioEq = wrapperAudioEq(eqIndex.audioEq)) != null) {
                        eqIndex.eqData = eqWrapperWrapperAudioEq.eqData;
                    }
                }
                a((byte) 0, eqIndex);
                return true;
            case 514:
                d dVarB = d.b(parameters);
                if (dVarB == null) {
                    return true;
                }
                getEqInfo().setEqActiveIndex(dVarB.a, dVarB.b, dVarB.c);
                ZLogger.v(getEqInfo().toString());
                updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, (byte) 0);
                a((byte) 0, dVarB.a());
                return true;
            case 518:
                d dVarA = d.a(parameters);
                if (dVarA == null) {
                    return true;
                }
                getEqInfo().setEqActiveIndex(dVarA.a, dVarA.b, dVarA.c);
                updateUserTasks(UUID_EQ_QUERY_MIC_BASIC_INFO, (byte) 0);
                a((byte) 0, dVarA.a());
                return true;
            case 520:
                break;
            case 3585:
                int eqSpecVersion2 = getEqSpecVersion();
                ZLogger.v(ModelClient.VDBG, String.format("eqSpecVersion=0x%04X", Integer.valueOf(eqSpecVersion2)));
                if (eqSpecVersion2 <= 3) {
                    ZLogger.v(ModelClient.VDBG, "process EVENT_GAMING_MODE_EQ_INDEX");
                    if (parameters == null || parameters.length < 1) {
                        return true;
                    }
                    getEqInfo().setEqActiveIndex(0, 1, parameters[0]);
                    updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, (byte) 0);
                    a((byte) 0, new EqEntryIndex(0, 1, parameters[0], 2));
                    return true;
                }
                ZLogger.v(ModelClient.VDBG, "ignore EVENT_GAMING_MODE_EQ_INDEX");
                break;
                break;
            default:
                return false;
        }
        c cVarA = c.a(getEqSpecVersion(), parameters);
        if (cVarA == null) {
            return true;
        }
        getEqInfo().setEqBasicInfo(getEqSpecVersion(), cVarA);
        updateUserTasks(UUID_EQ_QUERY_BASIC_INFO, (byte) 0);
        updateUserTasks(UUID_EQ_QUERY_MIC_BASIC_INFO, (byte) 0);
        return true;
    }

    public final void a(byte[] bArr) {
        com.realsil.customer.bbpro.c.a aVarA = com.realsil.customer.bbpro.c.a.a(bArr);
        if (aVarA == null) {
            this.j = null;
            return;
        }
        byte b = aVarA.b();
        if (b == -1) {
            this.j = null;
            a((byte) 0, aVarA.a(), aVarA.c());
        } else {
            if (b == 1) {
                this.j = aVarA.c();
                return;
            }
            if (b == 2) {
                this.j = aVarA.c(this.j);
            } else {
                if (b != 3) {
                    return;
                }
                a((byte) 0, aVarA.a(), aVarA.c(this.j));
                this.j = null;
            }
        }
    }

    public final void a(byte b, byte b2) {
        ZLogger.v(ModelClient.DBG, String.format("dispatchAudioEqStateChanged: 0x%02X, state=0x%02X", Byte.valueOf(b), Byte.valueOf(b2)));
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onAudioEqStateReport(b, b2);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public final void a(boolean z) {
        ZLogger.v(ModelClient.VDBG, String.format("dispatchGamingModeStateChanged:%b", Boolean.valueOf(z)));
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onGamingModeStatusChanged(z);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public final void a(byte b, int i, int i2) {
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onAudioEqEntryNumberReport((byte) 0, i, i2);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void a(byte b, int i, @NonNull EqInfo eqInfo) {
        if (ModelClient.VDBG) {
            ZLogger.v(String.format(Locale.US, "dispatchAudioEqBasicInfoReport: 0x%02X, mode=%d, eqInfo=%s", Byte.valueOf(b), Integer.valueOf(i), eqInfo.toString()));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onAudioEqBasicInfoReport(b, i, eqInfo);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void a(byte b, int i) {
        if (ModelClient.VDBG) {
            ZLogger.v(String.format(Locale.US, "dispatchSetAudioEqIndexResponse: 0x%02X, eqType=%02X", Byte.valueOf(b), Integer.valueOf(i)));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onSetAudioEqIndexResponse(b, i);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void a(byte b) {
        if (ModelClient.VDBG) {
            ZLogger.v(String.format(Locale.US, "dispatchResetAudioEqIndexResponse: 0x%02X", Byte.valueOf(b)));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onResetAudioEqIndexResponse(b);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void a(byte b, EqEntryIndex eqEntryIndex) {
        if (ModelClient.VDBG) {
            ZLogger.v(String.format(Locale.US, "dispatchAudioEqEntryIndexChanged: 0x%02X, %s", Byte.valueOf(b), eqEntryIndex.toString()));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onAudioEqEntryIndexReport(b, eqEntryIndex);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void a(byte b, EqIndex eqIndex) {
        if (ModelClient.VDBG) {
            Object[] objArr = new Object[2];
            objArr[0] = Byte.valueOf(b);
            objArr[1] = eqIndex != null ? eqIndex.toString() : "";
            ZLogger.v(String.format("dispatchAudioEqIndexParamsReport: 0x%02X, %s", objArr));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onAudioEqIndexParamsReport(b, eqIndex);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void a(byte b, int i, int i2, int i3) {
        if (ModelClient.VDBG) {
            ZLogger.v(String.format(Locale.US, "dispatchSetAudioEqIndexParamsResponse: 0x%02X, eqType=%d,eqMode=%d,eqIndex=%d", Byte.valueOf(b), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onSetAudioEqIndexParamsResponse(b, i, i2, i3);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public final void a(byte b, byte b2, byte[] bArr) {
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onDspAudioEqReport(b, b2, bArr);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }

    public void b(byte b, int i, @NonNull EqInfo eqInfo) {
        if (ModelClient.VDBG) {
            ZLogger.v(String.format(Locale.US, "dispatchMicAudioEqBasicInfoReport: 0x%02X, mode=%d, eqInfo=%s", Byte.valueOf(b), Integer.valueOf(i), eqInfo.toString()));
        }
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((EqModelCallback) it.next()).onMicAudioEqBasicInfoReport(b, i, eqInfo);
            }
        } else if (ModelClient.VDBG) {
            ZLogger.v("no callback registered");
        }
    }
}
