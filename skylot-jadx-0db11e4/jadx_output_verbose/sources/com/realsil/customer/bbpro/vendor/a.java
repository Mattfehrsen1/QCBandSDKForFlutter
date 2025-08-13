package com.realsil.customer.bbpro.vendor;

import android.content.Context;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import com.realsil.customer.bbpro.apt.AptVolumeInfo;
import com.realsil.customer.bbpro.apt.AptVolumeStatus;
import com.realsil.customer.bbpro.apt.GetAptVolumeInfoReq;
import com.realsil.customer.bbpro.core.BeeError;
import com.realsil.customer.bbpro.core.protocol.CommandContract;
import com.realsil.customer.bbpro.core.protocol.EventContract;
import com.realsil.customer.bbpro.core.transportlayer.AckPacket;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.customer.bbpro.g.a;
import com.realsil.customer.bbpro.g.d;
import com.realsil.customer.bbpro.g.e;
import com.realsil.customer.bbpro.i.c;
import com.realsil.customer.bbpro.internal.ModelClient;
import com.realsil.customer.bbpro.j.b;
import com.realsil.customer.bbpro.llapt.LlAptBasicInfo;
import com.realsil.customer.bbpro.llapt.LlAptBrightnessInfo;
import com.realsil.customer.bbpro.llapt.LlAptBrightnessStatus;
import com.realsil.customer.bbpro.model.AncGroup;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.bbpro.model.DspCapability;
import com.realsil.customer.bbpro.model.KeyMmiSettings;
import com.realsil.customer.bbpro.multilink.MultilinkInfo;
import com.realsil.customer.bbpro.profile.GetStatusReq;
import com.realsil.customer.bbpro.profile.MmiReq;
import com.realsil.customer.bbpro.vp.VpToneVolumeLevelSetRsp;
import com.realsil.customer.bbpro.vp.VpToneVolumeStatusRsp;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/a.class */
public class a extends ModelClient<VendorModelCallback> {
    public boolean g;
    public boolean h;
    public DeviceInfo i;

    public a(Context context) {
        super(context);
        this.g = true;
        this.h = true;
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public int getVendorCmd() {
        return 0;
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public int getVendorEvent() {
        return 0;
    }

    public DeviceInfo getDeviceInfo() {
        if (this.i == null) {
            this.i = new DeviceInfo();
        }
        return this.i;
    }

    public void clearDeviceInfo() {
        this.i = new DeviceInfo();
    }

    public BeeError getStatus(byte b) {
        return sendAppReq(new GetStatusReq.Builder(b).build());
    }

    public BeeError sendMmi(byte b) {
        return sendAppReq(new MmiReq.Builder(b).build());
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public boolean processAckPacket(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId == 2) {
            if (ModelClient.VDBG) {
                ZLogger.v(String.format("onCreateConnectionResponse: 0x%02X", Byte.valueOf(status)));
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
                ((VendorModelCallback) it.next()).onCreateConnectionResponse(status);
            }
            return true;
        }
        if (toAckId == 3) {
            if (ModelClient.VDBG) {
                ZLogger.v(String.format("onDisconnectResponse: 0x%02X", Byte.valueOf(status)));
            }
            List<MCB> list2 = this.mCallbacks;
            if (list2 == 0 || list2.size() <= 0) {
                if (!ModelClient.VDBG) {
                    return true;
                }
                ZLogger.v("no callback registered");
                return true;
            }
            Iterator it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                ((VendorModelCallback) it2.next()).onDisconnectResponse(status);
            }
            return true;
        }
        if (toAckId == 4) {
            if (status != 0) {
                ZLogger.d("CMD_MMI not supported");
            }
            notifyOperationComplete(29, status);
            return true;
        }
        if (toAckId == 23) {
            if (status == 2) {
                ZLogger.d(ModelClient.DBG, "CMD_GET_CFG_SETTINGS not supported");
            }
            notifyOperationComplete(23, status);
            return true;
        }
        if (toAckId == 24) {
            if (status == 2 || status == 1) {
                ZLogger.d("CMD_GET_STATUS not supported");
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
            }
            notifyOperationComplete(31, status);
            return true;
        }
        if (toAckId == 521) {
            if (status == 2) {
                ZLogger.v("CMD_DSP_SET_APT_GAIN not supported");
            }
            notifyOperationComplete(40, status);
            return true;
        }
        if (toAckId == 522) {
            if (status == 2) {
                ZLogger.v("CMD_SET_VOLUME not supported");
            }
            notifyOperationComplete(4, status);
            return true;
        }
        if (toAckId == 526) {
            if (status == 2) {
                ZLogger.v("CMD_APT_SET_VOLUME_OUT_LEVEL not supported");
            } else if (status == 0) {
                sendAppReq(new GetAptVolumeInfoReq.Builder(2).build());
            }
            notifyOperationComplete(40, status);
            return true;
        }
        if (toAckId == 527) {
            if (status == 2) {
                ZLogger.v("CMD_APT_GET_VOLUME_OUT_LEVEL not supported");
            }
            notifyOperationComplete(39, status);
            return true;
        }
        if (toAckId == 3115) {
            notifyOperationComplete(36, status);
            return true;
        }
        if (toAckId == 3116) {
            if (status == 2) {
                ZLogger.d("CMD_LISTENING_MODE_CYCLE_GET not supported");
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
            }
            notifyOperationComplete(37, status);
            return true;
        }
        switch (toAckId) {
            case 12:
                if (status == 2) {
                    ZLogger.d("CMD_INFO_REQ not supported");
                    getDeviceInfo().setCmdSetInfo(c.c);
                }
                notifyOperationComplete(30, status);
                return true;
            case 18:
                notifyOperationComplete(26, status);
                return true;
            case 33:
                if (status == 2) {
                    ZLogger.d("CMD_GET_BUD_INFO not supported");
                }
                notifyOperationComplete(54, status);
                return true;
            case 261:
                if (status == 2) {
                    ZLogger.d("CMD_GET_LE_ADDR not supported");
                    getDeviceInfo().setLeAddr(null);
                }
                notifyOperationComplete(24, status);
                return true;
            case 534:
                notifyOperationComplete(64, status);
                return true;
            case 776:
                if (status == 0) {
                    return true;
                }
                notifyOperationComplete(63, status);
                return true;
            case CommandContract.CMD_GET_PACKAGE_ID /* 783 */:
                if (status == 2) {
                    ZLogger.d("CMD_GET_PACKAGE_ID not supported, cmdVersion=0");
                }
                notifyOperationComplete(34, status);
                return true;
            case 789:
                if (status == 2) {
                    ZLogger.d("CMD_GET_NUM_OF_CONNECTION not supported");
                }
                notifyOperationComplete(55, status);
                return true;
            case 1536:
                if (status == 2 || status == 1) {
                    getDeviceInfo().setSppOtaSupported(false);
                    a(14);
                } else if (status == 0) {
                    getDeviceInfo().setSppOtaSupported(true);
                }
                notifyOperationComplete(25, status);
                return true;
            case 2832:
                if (ModelClient.VDBG) {
                    ZLogger.v(String.format("onPlayRingtongResponse: 0x%02X", Byte.valueOf(status)));
                }
                List<MCB> list3 = this.mCallbacks;
                if (list3 == 0 || list3.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it3 = this.mCallbacks.iterator();
                while (it3.hasNext()) {
                    ((VendorModelCallback) it3.next()).onPlayRingtongResponse(status);
                }
                return true;
            case 3118:
                if (status == 0) {
                    return true;
                }
                ZLogger.v(ModelClient.VDBG, "CMD_APT_VOLUME_INFO not supported");
                notifyOperationComplete(39, status);
                return true;
            case 3119:
                if (status == 0) {
                    return true;
                }
                ZLogger.v(ModelClient.VDBG, "CMD_APT_VOLUME_SET not supported");
                notifyOperationComplete(40, status);
                return true;
            case 3120:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.VDBG, "CMD_APT_VOLUME_STATUS not supported");
                notifyOperationComplete(41, status);
                return true;
            case 3121:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.VDBG, "CMD_LLAPT_BRIGHTNESS_INFO not supported");
                notifyOperationComplete(44, status);
                return true;
            case 3122:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.VDBG, "CMD_LLAPT_BRIGHTNESS_SET not supported");
                notifyOperationComplete(42, status);
                return true;
            case 3123:
                if (status == 0) {
                    return true;
                }
                ZLogger.d("CMD_LLAPT_BRIGHTNESS_STATUS not supported");
                notifyOperationComplete(43, status);
                return true;
            case 3124:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.VDBG, "CMD_APT_SET_NR_ON_OFF not supported");
                notifyOperationComplete(47, status);
                return true;
            case 3125:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.VDBG, "CMD_APT_GET_NR_ON_OFF not supported");
                notifyOperationComplete(48, status);
                return true;
            case 3126:
                if (status == 0) {
                    return true;
                }
                ZLogger.d("CMD_LLAPT_SCENARIO_CHOOSE_INFO not supported");
                notifyOperationComplete(49, status);
                return true;
            case 3127:
                if (status == 0) {
                    return true;
                }
                ZLogger.d("CMD_LLAPT_SCENARIO_CHOOSE_TRY not supported");
                notifyOperationComplete(50, status);
                return true;
            case 3128:
                if (status != 2) {
                    return true;
                }
                ZLogger.d("CMD_LLAPT_SCENARIO_CHOOSE_RESULT not supported");
                notifyOperationComplete(51, status);
                return true;
            case 3129:
                if (status == 0) {
                    return true;
                }
                ZLogger.v(ModelClient.VDBG, "CMD_APT_GET_POWER_ON_DELAY_TIME not supported");
                notifyOperationComplete(52, status);
                return true;
            case 3130:
                if (status == 0) {
                    return true;
                }
                ZLogger.v(ModelClient.VDBG, "CMD_APT_SET_POWER_ON_DELAY_TIME not supported");
                notifyOperationComplete(53, status);
                return true;
            case 3131:
                if (status == 0) {
                    return true;
                }
                notifyOperationComplete(38, status);
                return true;
            case 3132:
                if (status != 2) {
                    return true;
                }
                ZLogger.d("CMD_LISTENING_MODE_STATUS not supported");
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                return true;
            case 3133:
                if (status == 0) {
                    return true;
                }
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                notifyOperationComplete(66, status);
                return true;
            case 3134:
                if (status == 0) {
                    return true;
                }
                notifyOperationComplete(67, status);
                return true;
            case 3140:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.DBG, "CMD_ANC_SCENARIO_CHOOSE_INFO not supported");
                notifyOperationComplete(57, status);
                return true;
            case 3141:
                if (status == 0) {
                    return true;
                }
                ZLogger.d(ModelClient.DBG, "CMD_ANC_SCENARIO_CHOOSE_TRY not supported");
                notifyOperationComplete(58, status);
                return true;
            case 3142:
                if (status != 2) {
                    return true;
                }
                ZLogger.d(ModelClient.DBG, "CMD_ANC_SCENARIO_CHOOSE_RESULT not supported");
                notifyOperationComplete(59, status);
                return true;
            case 3585:
                if (status != 2) {
                    notifyOperationComplete(45, status);
                    return true;
                }
                ZLogger.v("CMD_GET_LOW_LATENCY_MODE_STATUS not supported");
                getDeviceInfo().setGamingModeEnabled(false);
                a(21);
                return true;
            case 3586:
                if (status != 2) {
                    notifyOperationComplete(35, status);
                    return true;
                }
                ZLogger.v("CMD_GET_EAR_DETECTION_STATUS not supported");
                getDeviceInfo().setEarDetectionStatus((byte) 0);
                a(55);
                return true;
            case 3588:
                if (status != 2) {
                    notifyOperationComplete(46, status);
                    return true;
                }
                ZLogger.v("CMD_SET_LOW_LATENCY_LEVEL not supported");
                a(22);
                return true;
            default:
                switch (toAckId) {
                    case 1792:
                        if (status == 2) {
                            ZLogger.v("CMD_GET_SUPPORTED_MMI_LIST not supported");
                            getDeviceInfo().setSupportedMmi(null);
                        }
                        notifyOperationComplete(16, status);
                        return true;
                    case 1793:
                        if (status == 2) {
                            ZLogger.v("CMD_GET_SUPPORTED_CLICK_TYPE not supported");
                            getDeviceInfo().setSupportedClickType(null);
                        }
                        notifyOperationComplete(17, status);
                        return true;
                    case 1794:
                        if (status == 2) {
                            ZLogger.v("CMD_GET_SUPPORTED_CALL_STATUS not supported");
                            getDeviceInfo().setSupportedCallStatus(null);
                        }
                        notifyOperationComplete(18, status);
                        return true;
                    case 1795:
                        if (status == 2) {
                            ZLogger.v(ModelClient.VDBG, "CMD_GET_KEY_MMI_MAP not supported");
                            getDeviceInfo().setKeyMmiSettings(null);
                        }
                        notifyOperationComplete(19, status);
                        return true;
                    case 1796:
                        notifyOperationComplete(20, status);
                        if (status != 0) {
                            return true;
                        }
                        getKeyMmiMap();
                        return true;
                    default:
                        switch (toAckId) {
                            case 1798:
                                notifyOperationComplete(1, status);
                                return true;
                            case 1799:
                                notifyOperationComplete(56, status);
                                return true;
                            case 1800:
                                if (status == 2) {
                                    ZLogger.v(ModelClient.VDBG, "CMD_GET_RWS_KEY_MMI_MAP not supported");
                                    getDeviceInfo().setRwsKeyMmiSettings(null);
                                }
                                notifyOperationComplete(60, status);
                                return true;
                            case 1801:
                                notifyOperationComplete(61, status);
                                if (status != 0) {
                                    return true;
                                }
                                getRwsKeyMmiMap();
                                return true;
                            case 1802:
                                notifyOperationComplete(62, status);
                                return true;
                            default:
                                switch (toAckId) {
                                    case 1808:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_SET_ENABLE not supported");
                                        }
                                        notifyOperationComplete(6, status);
                                        return true;
                                    case 1809:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_SET_DISENABLE not supported");
                                        }
                                        notifyOperationComplete(7, status);
                                        return true;
                                    case 1810:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_TOGGLE not supported");
                                        }
                                        notifyOperationComplete(8, status);
                                        return true;
                                    case 1811:
                                        ZLogger.v("ACK-CMD_MOTOR_GET_STATUS");
                                        if (status == 2 || status == 1) {
                                            getDeviceInfo().setMotorFeature((byte) 0);
                                            getDeviceInfo().setMotorStatus((byte) 0);
                                        } else if (status == 0) {
                                            getDeviceInfo().setMotorFeature((byte) 1);
                                        }
                                        notifyOperationComplete(10, status);
                                        return true;
                                    case 1812:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_SET_MODE not supported");
                                        }
                                        notifyOperationComplete(13, status);
                                        return true;
                                    case 1813:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_STOP_VIBRATION not supported");
                                        }
                                        notifyOperationComplete(9, status);
                                        return true;
                                    case 1814:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_CHECK_VIBRATION not supported");
                                        }
                                        notifyOperationComplete(11, status);
                                        return true;
                                    case 1815:
                                        if (status == 2) {
                                            ZLogger.v("CMD_MOTOR_GET_MODE_PARAMETER not supported");
                                        }
                                        notifyOperationComplete(12, status);
                                        return true;
                                    default:
                                        switch (toAckId) {
                                            case 3104:
                                                if (status == 2) {
                                                    ZLogger.d(ModelClient.DBG, "CMD_ANC_QUERY not supported");
                                                    getDeviceInfo().setAncGroup(null);
                                                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                                                }
                                                notifyOperationComplete(22, status);
                                                return true;
                                            case 3105:
                                                if (status == 2) {
                                                    ZLogger.d(ModelClient.DBG, "CMD_ANC_SET_STATE not supported");
                                                    getDeviceInfo().setAncStatus((byte) 0);
                                                }
                                                notifyOperationComplete(14, status);
                                                return true;
                                            case 3106:
                                                if (status == 2) {
                                                    ZLogger.d(ModelClient.DBG, "CMD_LLAPT_QUERY not supported");
                                                    getDeviceInfo().setLlAptStatus((byte) 0);
                                                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                                                }
                                                notifyOperationComplete(32, status);
                                                return true;
                                            case 3107:
                                                if (status == 2) {
                                                    ZLogger.d(ModelClient.DBG, "CMD_LLAPT_ENABLE_DISABLE not supported");
                                                    getDeviceInfo().setLlAptStatus((byte) 0);
                                                }
                                                if (status == 0) {
                                                    return true;
                                                }
                                                notifyOperationComplete(33, status);
                                                return true;
                                            case 3108:
                                                if (status == 2) {
                                                    ZLogger.d(ModelClient.DBG, "CMD_SPECIFY_ANC_QUERY not supported");
                                                    getDeviceInfo().setSpecialAncGroup(null);
                                                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                                                }
                                                notifyOperationComplete(65, status);
                                                return true;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        byte[] parameters = transportLayerPacket.getParameters();
        transportLayerPacket.getPayload();
        switch (opcode) {
            case 3:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_BT_CONNECT_STATUS");
                }
                if (parameters == null || parameters.length < 7) {
                    return true;
                }
                byte b = parameters[0];
                byte[] bArr = new byte[6];
                System.arraycopy(parameters, 1, bArr, 0, 6);
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
                    ((VendorModelCallback) it.next()).onProfileConnected(b, bArr);
                }
                return true;
            case 4:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_BT_DISCONNECT_STATUS");
                }
                if (parameters == null || parameters.length < 7) {
                    return true;
                }
                byte b2 = parameters[0];
                byte[] bArr2 = new byte[6];
                System.arraycopy(parameters, 1, bArr2, 0, 6);
                List<MCB> list2 = this.mCallbacks;
                if (list2 == 0 || list2.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it2 = this.mCallbacks.iterator();
                while (it2.hasNext()) {
                    ((VendorModelCallback) it2.next()).onProfileDisconnected(b2, bArr2);
                }
                return true;
            case 17:
                if (parameters == null || parameters.length < 2) {
                    return true;
                }
                byte b3 = parameters[0];
                if (b3 == 0) {
                    getDeviceInfo().setCmdSetInfo(c.a(parameters));
                    a(12);
                    return true;
                }
                if (b3 != 1 || parameters[1] != 0 || parameters.length < 8) {
                    return true;
                }
                int length = parameters.length - 2;
                byte[] bArr3 = new byte[length];
                System.arraycopy(parameters, 2, bArr3, 0, length);
                getDeviceInfo().setDspCapability(new DspCapability(bArr3));
                a(13);
                return true;
            case 24:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_REPORT_CFG_SETTINGS");
                }
                com.realsil.customer.bbpro.i.a aVarA = com.realsil.customer.bbpro.i.a.a(parameters);
                if (aVarA == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA.toString());
                }
                if (aVarA.i() == 0) {
                    getDeviceInfo().setLeName(aVarA.e());
                    a(3);
                    return true;
                }
                if (1 == aVarA.i()) {
                    getDeviceInfo().setBrEdrName(aVarA.f());
                    a(2);
                    return true;
                }
                if (2 == aVarA.i()) {
                    getDeviceInfo().setIcName(aVarA.c());
                    a(58);
                    return true;
                }
                if (3 == aVarA.i()) {
                    getDeviceInfo().setCompanyId(aVarA.a());
                    getDeviceInfo().setModelId(aVarA.g());
                    a(59);
                    return true;
                }
                if (4 == aVarA.i()) {
                    getDeviceInfo().setDeviceId(aVarA.b());
                    a(60);
                    return true;
                }
                if (5 == aVarA.i()) {
                    getDeviceInfo().setLchSingleDeviceId(aVarA.d());
                    a(61);
                    return true;
                }
                if (6 != aVarA.i()) {
                    return true;
                }
                getDeviceInfo().setRchSingleDeviceId(aVarA.h());
                a(62);
                return true;
            case 34:
                com.realsil.customer.bbpro.j.a aVarA2 = com.realsil.customer.bbpro.j.a.a(parameters);
                if (aVarA2 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA2.toString());
                }
                getDeviceInfo().setDeviceStatusInfo(aVarA2.a());
                a(39);
                return true;
            case 260:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LE_ADDR");
                }
                getDeviceInfo().setLeAddr(BluetoothHelper.convertMac(parameters));
                a(4);
                return true;
            case 517:
                AptVolumeInfo aptVolumeInfoBuilderV2 = AptVolumeInfo.builderV2(parameters);
                if (aptVolumeInfoBuilderV2 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aptVolumeInfoBuilderV2.toString());
                }
                getDeviceInfo().setAptVolumeInfo(aptVolumeInfoBuilderV2);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(33);
                return true;
            case 521:
                VpToneVolumeStatusRsp vpToneVolumeStatusRsp = VpToneVolumeStatusRsp.parse(parameters);
                if (vpToneVolumeStatusRsp == null) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v(">> EVENT_VP_TONE_VOLUME_STATUS");
                    return true;
                }
                if (ModelClient.DBG) {
                    ZLogger.d(vpToneVolumeStatusRsp.toString());
                }
                getDeviceInfo().setVpVolumeInfo(vpToneVolumeStatusRsp.toVpVolumeInfo());
                a(56);
                return true;
            case 522:
                VpToneVolumeLevelSetRsp vpToneVolumeLevelSetRsp = VpToneVolumeLevelSetRsp.parse(parameters);
                if (vpToneVolumeLevelSetRsp == null) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v(">> EVENT_VP_TONE_VOLUME_LEVEL_SET");
                    return true;
                }
                if (ModelClient.DBG) {
                    ZLogger.v(vpToneVolumeLevelSetRsp.toString());
                }
                getDeviceInfo().setVpVolumeInfo(vpToneVolumeLevelSetRsp.toVpVolumeInfo());
                a(56);
                return true;
            case 789:
                com.realsil.customer.bbpro.h.a aVarA3 = com.realsil.customer.bbpro.h.a.a(parameters);
                if (aVarA3 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA3.toString());
                }
                MultilinkInfo multilinkInfo = new MultilinkInfo();
                multilinkInfo.setConnNum(aVarA3.a);
                a(multilinkInfo);
                return true;
            case 1536:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_OTA_GET_DEVICE_INFO");
                }
                a(14);
                return true;
            case 1792:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                int i = parameters[0] & 255;
                if (parameters.length < i + 1) {
                    return true;
                }
                byte[] bArr4 = new byte[i];
                System.arraycopy(parameters, 1, bArr4, 0, i);
                getDeviceInfo().setSupportedMmi(bArr4);
                a(35);
                return true;
            case 1793:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                int i2 = parameters[0] & 255;
                if (parameters.length < i2 + 1) {
                    return true;
                }
                byte[] bArr5 = new byte[i2];
                System.arraycopy(parameters, 1, bArr5, 0, i2);
                getDeviceInfo().setSupportedClickType(bArr5);
                a(36);
                return true;
            case 1794:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                int i3 = parameters[0] & 255;
                if (parameters.length < i3 + 1) {
                    return true;
                }
                byte[] bArr6 = new byte[i3];
                System.arraycopy(parameters, 1, bArr6, 0, i3);
                getDeviceInfo().setSupportedCallStatus(bArr6);
                a(37);
                return true;
            case 1795:
                List<KeyMmiSettings> listA = com.realsil.customer.bbpro.f.a.a(parameters);
                getDeviceInfo().setKeyMmiSettings(listA);
                List<MCB> list3 = this.mCallbacks;
                if (list3 == 0 || list3.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it3 = this.mCallbacks.iterator();
                while (it3.hasNext()) {
                    ((VendorModelCallback) it3.next()).onKeyMapSettingsReported(listA);
                }
                return true;
            case 1800:
                List<KeyMmiSettings> listA2 = com.realsil.customer.bbpro.f.a.a(parameters);
                getDeviceInfo().setRwsKeyMmiSettings(listA2);
                List<MCB> list4 = this.mCallbacks;
                if (list4 == 0 || list4.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it4 = this.mCallbacks.iterator();
                while (it4.hasNext()) {
                    ((VendorModelCallback) it4.next()).onRwsKeyMapSettingsReported(listA2);
                }
                return true;
            case 1808:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_REPORT_MOTOR_STATUS");
                }
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                getDeviceInfo().setMotorStatus(parameters[0]);
                a(18);
                return true;
            case 1809:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_REPORT_MOTOR_VIBRATION_STATUS");
                }
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                getDeviceInfo().setVibrationStatus(parameters[0]);
                a(20);
                return true;
            case 1810:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_REPORT_MOTOR_MODE_PARAMETERS");
                }
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                List<MCB> list5 = this.mCallbacks;
                if (list5 == 0 || list5.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                int i4 = ((parameters[1] << 8) | (parameters[0] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
                int i5 = ((parameters[3] << 8) | (parameters[2] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
                getDeviceInfo().setVibrateOnTime(i4);
                getDeviceInfo().setVibrateOffTime(i5);
                getDeviceInfo().setVibrateCount(parameters[4] & 255);
                a(19);
                return true;
            case 2313:
                b bVarA = b.a(parameters);
                if (bVarA == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(bVarA.toString());
                }
                getDeviceInfo().setPrimaryUiOtaVersion(bVarA.f);
                a(63);
                return true;
            case EventContract.EVENT_REPORT_PACKAGE_ID /* 2321 */:
                if (parameters == null || parameters.length < 2) {
                    return true;
                }
                getDeviceInfo().setChipId(parameters[0]);
                getDeviceInfo().setPackageId(parameters[1]);
                a(11);
                return true;
            case 2832:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_SOUND_PRESS_CALIBRATION");
                }
                if (parameters == null || parameters.length < 1) {
                    return true;
                }
                byte b4 = parameters[0];
                List<MCB> list6 = this.mCallbacks;
                if (list6 == 0 || list6.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it5 = this.mCallbacks.iterator();
                while (it5.hasNext()) {
                    ((VendorModelCallback) it5.next()).onPlayRingtongResult(b4);
                }
                return true;
            case 3072:
            case 3073:
            case 3074:
            case 3075:
            case 3076:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> onToneAndTalkKeyEventReport" + ((int) opcode));
                }
                List<MCB> list7 = this.mCallbacks;
                if (list7 == 0 || list7.size() <= 0) {
                    if (!ModelClient.VDBG) {
                        return true;
                    }
                    ZLogger.v("no callback registered");
                    return true;
                }
                Iterator it6 = this.mCallbacks.iterator();
                while (it6.hasNext()) {
                    ((VendorModelCallback) it6.next()).onKeyeventReported(opcode);
                }
                return true;
            case 3104:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_ANC_QUERY");
                }
                getDeviceInfo().setAncGroup(AncGroup.builder(parameters));
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(16);
                return true;
            case 3105:
                if (parameters == null || parameters.length <= 1) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_ANC_SET_STATE");
                }
                if (parameters[1] == 0) {
                    return true;
                }
                notifyOperationComplete(14, (byte) 5);
                return true;
            case 3106:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LLAPT_QUERY");
                }
                getDeviceInfo().setLlaptBasicInfo(LlAptBasicInfo.builder(parameters));
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(49);
                return true;
            case 3107:
                if (parameters == null || parameters.length <= 1) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LLAPT_ENABLE_DISABLE");
                }
                if (parameters[1] == 0) {
                    return true;
                }
                notifyOperationComplete(33, (byte) 5);
                return true;
            case 3108:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_SPECIFY_ANC_QUERY");
                }
                getDeviceInfo().setSpecialAncGroup(AncGroup.builder(parameters));
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(65);
                return true;
            case 3115:
            case 3116:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LISTENING_MODE_CYCLE_SET/GET");
                }
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                getDeviceInfo().setListeningModeCycle(parameters[0]);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(41);
                return true;
            case 3118:
                AptVolumeInfo aptVolumeInfoBuilder = AptVolumeInfo.builder(parameters);
                if (aptVolumeInfoBuilder == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aptVolumeInfoBuilder.toString());
                }
                getDeviceInfo().setAptVolumeInfo(aptVolumeInfoBuilder);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(33);
                return true;
            case 3119:
                com.realsil.customer.bbpro.b.c cVarA = com.realsil.customer.bbpro.b.c.a(parameters);
                if (cVarA == null) {
                    return true;
                }
                if (!cVarA.b()) {
                    notifyOperationComplete(40, (byte) 5);
                    return true;
                }
                getDeviceInfo().setAptVolumeStatus(cVarA.a());
                a(33);
                return true;
            case 3120:
                AptVolumeStatus aptVolumeStatusBuilder = AptVolumeStatus.builder(parameters);
                if (aptVolumeStatusBuilder == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aptVolumeStatusBuilder.toString());
                }
                getDeviceInfo().setAptVolumeStatus(aptVolumeStatusBuilder);
                a(33);
                return true;
            case 3121:
                LlAptBrightnessInfo llAptBrightnessInfoBuilder = LlAptBrightnessInfo.builder(parameters);
                if (llAptBrightnessInfoBuilder == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(llAptBrightnessInfoBuilder.toString());
                }
                getDeviceInfo().setLlAptBrightnessInfo(llAptBrightnessInfoBuilder);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(51);
                return true;
            case 3122:
                com.realsil.customer.bbpro.g.c cVarA2 = com.realsil.customer.bbpro.g.c.a(parameters);
                if (cVarA2 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(cVarA2.toString());
                }
                if (!cVarA2.b()) {
                    notifyOperationComplete(42, (byte) 5);
                    return true;
                }
                getDeviceInfo().setLlAptBrighenessStatus(cVarA2.a());
                a(52);
                return true;
            case 3123:
                LlAptBrightnessStatus llAptBrightnessStatusBuilder = LlAptBrightnessStatus.builder(parameters);
                if (llAptBrightnessStatusBuilder == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(llAptBrightnessStatusBuilder.toString());
                }
                getDeviceInfo().setLlAptBrighenessStatus(llAptBrightnessStatusBuilder);
                a(52);
                return true;
            case 3124:
                com.realsil.customer.bbpro.b.a aVarA4 = com.realsil.customer.bbpro.b.a.a(parameters);
                if (aVarA4 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA4.toString());
                }
                getDeviceInfo().setAptNrEnabled(aVarA4.a());
                a(32);
                return true;
            case 3126:
                com.realsil.customer.bbpro.g.b bVarA2 = com.realsil.customer.bbpro.g.b.a(parameters);
                if (bVarA2 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(bVarA2.toString());
                }
                getDeviceInfo().setLlaptScenarioChooseInfo(bVarA2.a());
                a(53);
                return true;
            case 3127:
                e eVarA = e.a(parameters);
                if (eVarA == null || !eVarA.a()) {
                    notifyOperationComplete(50, (byte) 5);
                    return true;
                }
                notifyOperationComplete(50, (byte) 0);
                return true;
            case 3128:
                d dVarA = d.a(parameters);
                if (dVarA == null || !dVarA.a()) {
                    notifyOperationComplete(51, (byte) 5);
                    return true;
                }
                notifyOperationComplete(51, (byte) 0);
                queryLlApt((byte) 1);
                return true;
            case 3129:
                com.realsil.customer.bbpro.b.b bVarA3 = com.realsil.customer.bbpro.b.b.a(parameters);
                if (bVarA3 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(bVarA3.toString());
                }
                getDeviceInfo().setAptPowerOnDelayTime(bVarA3.a());
                a(34);
                return true;
            case 3131:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LISTENING_MODE_SET");
                }
                if (parameters[0] == 0) {
                    notifyOperationComplete(38, (byte) 0);
                    return true;
                }
                notifyOperationComplete(38, (byte) 5);
                return true;
            case 3132:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LISTENING_MODE_STATUS");
                }
                com.realsil.customer.bbpro.hearing.a aVarA5 = com.realsil.customer.bbpro.hearing.a.a(parameters);
                if (aVarA5 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA5.toString());
                }
                getDeviceInfo().updateListeningModeStatus(aVarA5);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(48);
                return true;
            case 3133:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_APT_TYPE_QUERY");
                }
                if (parameters == null || parameters.length < 2) {
                    notifyOperationComplete(66, (byte) 5);
                    return true;
                }
                if (parameters[0] != 0) {
                    notifyOperationComplete(66, (byte) 5);
                    return true;
                }
                getDeviceInfo().setAptFeatureType(parameters[1]);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                notifyOperationComplete(66, (byte) 0);
                return true;
            case 3134:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_ASSIGN_APT_TYPE");
                }
                if (parameters == null || parameters.length < 1) {
                    notifyOperationComplete(67, (byte) 5);
                    return true;
                }
                getDeviceInfo().setAptFeatureType(parameters[0]);
                notifyOperationComplete(67, (byte) 0);
                return true;
            case 3140:
                com.realsil.customer.bbpro.a.a aVarA6 = com.realsil.customer.bbpro.a.a.a(parameters);
                if (aVarA6 == null) {
                    return true;
                }
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA6.toString());
                }
                getDeviceInfo().setAncScenarioChooseInfo(aVarA6.a());
                a(57);
                return true;
            case 3141:
                com.realsil.customer.bbpro.a.c cVarA3 = com.realsil.customer.bbpro.a.c.a(parameters);
                if (cVarA3 == null || !cVarA3.a()) {
                    notifyOperationComplete(58, (byte) 5);
                    return true;
                }
                notifyOperationComplete(58, (byte) 0);
                return true;
            case 3142:
                com.realsil.customer.bbpro.a.b bVarA4 = com.realsil.customer.bbpro.a.b.a(parameters);
                if (bVarA4 == null || !bVarA4.a()) {
                    notifyOperationComplete(59, (byte) 5);
                    return true;
                }
                notifyOperationComplete(59, (byte) 0);
                queryAnc((byte) 0);
                return true;
            case 3584:
                com.realsil.customer.bbpro.d.a aVarA7 = com.realsil.customer.bbpro.d.a.a(parameters);
                if (aVarA7 == null) {
                    return true;
                }
                getDeviceInfo().updateLowLatencyInfo(aVarA7);
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA7.toString());
                }
                a(21);
                return true;
            case 3585:
                if (parameters == null || parameters.length <= 0) {
                    return true;
                }
                getDeviceInfo().setEarDetectionStatus(parameters[0]);
                if (ModelClient.VDBG) {
                    ZLogger.v(String.format("isEarDetectionOn=%b", Boolean.valueOf(getDeviceInfo().isEarDetectionOn())));
                }
                a(55);
                return true;
            case 3586:
                com.realsil.customer.bbpro.d.b bVarA5 = com.realsil.customer.bbpro.d.b.a(parameters);
                if (bVarA5 == null) {
                    return true;
                }
                getDeviceInfo().updateLowLatencyLevelReport(bVarA5);
                if (ModelClient.VDBG) {
                    ZLogger.v(bVarA5.toString());
                }
                a(22);
                return true;
            case 8451:
                if (parameters == null || parameters.length < 1) {
                    return true;
                }
                getDeviceInfo().setBrEdrName(new String(parameters, 0, parameters.length, StandardCharsets.UTF_8).trim());
                a(2);
                return true;
            default:
                return false;
        }
    }

    @Override // com.realsil.customer.bbpro.internal.ModelClient
    public boolean processStatusReport(byte b, byte[] bArr) {
        byte b2 = 0;
        byte b3 = 0;
        if (bArr.length > 2) {
            b2 = bArr[1];
            b3 = bArr[2];
        } else if (bArr.length > 1) {
            b2 = bArr[1];
        }
        if (b == -95) {
            getDeviceInfo().setCurVolumeLevel(b2 & 255);
            getDeviceInfo().setMaxVolumeLevel(b3 & 255);
            a(54);
            return true;
        }
        if (b == 20) {
            getDeviceInfo().setSpatialAudioMode(b2);
            a(64);
            return true;
        }
        switch (b) {
            case 0:
                if (getDeviceInfo().isBudInfoReqSupported()) {
                    return true;
                }
                getDeviceInfo().setRwsState(b2);
                updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
                a(5);
                return true;
            case 1:
                getDeviceInfo().setPrimaryRwsChannel((byte) ((b2 & 240) >> 4));
                getDeviceInfo().setSecondaryRwsChannel((byte) (b2 & 15));
                a(6);
                return true;
            case 2:
                getDeviceInfo().setPrimaryBatStatus(b2 & 255);
                getDeviceInfo().setSecondaryBatStatus(b3 & 255);
                updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
                a(8);
                return true;
            case 3:
                getDeviceInfo().setAptStatus(b2);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(25);
                return true;
            default:
                switch (b) {
                    case 5:
                        getDeviceInfo().setRwsDefaultRole((byte) (b2 & 15));
                        updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
                        a(9);
                        return true;
                    case 6:
                        getDeviceInfo().setAptNrEnabled(b2 == 1);
                        a(32);
                        return true;
                    case 7:
                        AptVolumeInfo aptVolumeInfoBuilderV1 = AptVolumeInfo.builderV1(bArr);
                        if (aptVolumeInfoBuilderV1 == null) {
                            return true;
                        }
                        if (ModelClient.VDBG) {
                            ZLogger.v(aptVolumeInfoBuilderV1.toString());
                        }
                        getDeviceInfo().setAptVolumeInfo(aptVolumeInfoBuilderV1);
                        a(33);
                        return true;
                    case 8:
                        getDeviceInfo().setLockButtonStatus(b2);
                        a(38);
                        return true;
                    case 9:
                        getDeviceInfo().setLeftBudFindMeEnabled(b2);
                        getDeviceInfo().setRightBudFindMeEnabled(b3);
                        a(23);
                        return true;
                    case 10:
                        byte b4 = b2;
                        getDeviceInfo().setAncStatus(b2);
                        a(17);
                        if (b4 != 1) {
                            return true;
                        }
                        queryAnc((byte) 0);
                        return true;
                    case 11:
                        byte b5 = b2;
                        getDeviceInfo().setLlAptStatus(b2);
                        a(25);
                        if (b5 != 1) {
                            return true;
                        }
                        queryLlApt((byte) 1);
                        return true;
                    case 12:
                        getDeviceInfo().setPrimaryDefaultRwsChannel((byte) ((b2 & 240) >> 4));
                        getDeviceInfo().setSecondaryDefaultRwsChannel((byte) (b2 & 15));
                        updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
                        a(7);
                        return true;
                    case 13:
                        getDeviceInfo().setRwsBudSide((byte) (b2 & 15));
                        updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
                        a(10);
                        return true;
                    case 14:
                        getDeviceInfo().setAptVolumeSyncEnabled(b2 == 1);
                        a(33);
                        return true;
                    default:
                        return false;
                }
        }
    }

    public void a(int i) {
        List<MCB> list = this.mCallbacks;
        if (list == 0 || list.size() <= 0) {
            ZLogger.v(ModelClient.VDBG, "no callback registered");
            return;
        }
        ZLogger.d(ModelClient.DBG, String.format("dispatchDeviceInfoChanged: indicator=0x%02X", Integer.valueOf(i)));
        Iterator it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            ((VendorModelCallback) it.next()).onDeviceInfoChanged(i, getDeviceInfo());
        }
    }

    public BeeError queryAnc(byte b) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3104, new byte[]{b}).eventId((short) 3104).build());
    }

    public BeeError querySpecialAnc(byte b) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3108, new byte[]{b}).eventId((short) 3108).build());
    }

    public BeeError queryLlApt(byte b) {
        return sendAppReq(new a.b(b).a());
    }

    public BeeError queryListeningModeStatus() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3132, null).eventId((short) 3132).build());
    }

    public BeeError getKeyMmiMap() {
        return sendVendorData(CommandContract.buildPacket((short) 1795));
    }

    public BeeError getRwsKeyMmiMap() {
        return sendVendorData(CommandContract.buildPacket((short) 1800));
    }

    public void a(MultilinkInfo multilinkInfo) {
        List<MCB> list = this.mCallbacks;
        if (list != 0 && list.size() > 0) {
            ZLogger.d(ModelClient.DBG, String.format("dispatchMultilinkInfoChanged: %s", multilinkInfo.toString()));
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((VendorModelCallback) it.next()).onMultilinkInfoChanged(multilinkInfo);
            }
            return;
        }
        ZLogger.v(ModelClient.VDBG, "no callback registered");
    }
}
