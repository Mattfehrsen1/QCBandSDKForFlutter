package com.realsil.sdk.bbpro.vendor;

import android.content.Context;
import com.realsil.sdk.bbpro.apt.AptVolumeInfo;
import com.realsil.sdk.bbpro.apt.AptVolumeStatus;
import com.realsil.sdk.bbpro.apt.GetAptVolumeInfoReq;
import com.realsil.sdk.bbpro.core.BeeError;
import com.realsil.sdk.bbpro.core.protocol.CommandContract;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.bbpro.g.a;
import com.realsil.sdk.bbpro.g.d;
import com.realsil.sdk.bbpro.g.e;
import com.realsil.sdk.bbpro.i.c;
import com.realsil.sdk.bbpro.internal.ModelClient;
import com.realsil.sdk.bbpro.j.b;
import com.realsil.sdk.bbpro.llapt.LlAptBasicInfo;
import com.realsil.sdk.bbpro.llapt.LlAptBrightnessInfo;
import com.realsil.sdk.bbpro.llapt.LlAptBrightnessStatus;
import com.realsil.sdk.bbpro.model.AncGroup;
import com.realsil.sdk.bbpro.model.DeviceInfo;
import com.realsil.sdk.bbpro.model.DspCapability;
import com.realsil.sdk.bbpro.model.KeyMmiSettings;
import com.realsil.sdk.bbpro.multilink.MultilinkInfo;
import com.realsil.sdk.bbpro.params.Mmi;
import com.realsil.sdk.bbpro.profile.GetStatusReq;
import com.realsil.sdk.bbpro.profile.MmiReq;
import com.realsil.sdk.bbpro.vp.VpToneVolumeLevelSetRsp;
import com.realsil.sdk.bbpro.vp.VpToneVolumeStatusRsp;
import com.realsil.sdk.core.bluetooth.connection.le.GattClient;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class a extends ModelClient<VendorModelCallback> {
    public boolean g;
    public boolean h;
    public DeviceInfo i;

    public a(Context context) {
        super(context);
        this.g = true;
        this.h = true;
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

    public void clearDeviceInfo() {
        this.i = new DeviceInfo();
    }

    public DeviceInfo getDeviceInfo() {
        if (this.i == null) {
            this.i = new DeviceInfo();
        }
        return this.i;
    }

    public BeeError getKeyMmiMap() {
        return sendVendorData(CommandContract.buildPacket((short) 1795));
    }

    public BeeError getRwsKeyMmiMap() {
        return sendVendorData(CommandContract.buildPacket((short) 1800));
    }

    public BeeError getStatus(byte b) {
        return sendAppReq(new GetStatusReq.Builder(b).build());
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public int getVendorCmd() {
        return 0;
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public int getVendorEvent() {
        return 0;
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public boolean processAckPacket(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId == 2) {
            if (ModelClient.VDBG) {
                ZLogger.v(String.format("onCreateConnectionResponse: 0x%02X", Byte.valueOf(status)));
            }
            List<MCB> list = this.mCallbacks;
            if (list != 0 && list.size() > 0) {
                Iterator it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((VendorModelCallback) it.next()).onCreateConnectionResponse(status);
                }
            } else if (ModelClient.VDBG) {
                ZLogger.v("no callback registered");
            }
            return true;
        }
        if (toAckId == 3) {
            if (ModelClient.VDBG) {
                ZLogger.v(String.format("onDisconnectResponse: 0x%02X", Byte.valueOf(status)));
            }
            List<MCB> list2 = this.mCallbacks;
            if (list2 != 0 && list2.size() > 0) {
                Iterator it2 = this.mCallbacks.iterator();
                while (it2.hasNext()) {
                    ((VendorModelCallback) it2.next()).onDisconnectResponse(status);
                }
            } else if (ModelClient.VDBG) {
                ZLogger.v("no callback registered");
            }
            return true;
        }
        if (toAckId == 4) {
            if (status != 0) {
                ZLogger.d("CMD_MMI not supported");
            }
            notifyOperationComplete(29, status);
        } else if (toAckId == 23) {
            if (status == 2) {
                ZLogger.d(ModelClient.DBG, "CMD_GET_CFG_SETTINGS not supported");
            }
            notifyOperationComplete(23, status);
        } else if (toAckId == 24) {
            if (status == 2 || status == 1) {
                ZLogger.d("CMD_GET_STATUS not supported");
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
            }
            notifyOperationComplete(31, status);
        } else if (toAckId == 521) {
            if (status == 2) {
                ZLogger.v("CMD_DSP_SET_APT_GAIN not supported");
            }
            notifyOperationComplete(40, status);
        } else if (toAckId == 522) {
            if (status == 2) {
                ZLogger.v("CMD_SET_VOLUME not supported");
            }
            notifyOperationComplete(4, status);
        } else if (toAckId == 526) {
            if (status == 2) {
                ZLogger.v("CMD_APT_SET_VOLUME_OUT_LEVEL not supported");
            } else if (status == 0) {
                sendAppReq(new GetAptVolumeInfoReq.Builder(2).build());
            }
            notifyOperationComplete(40, status);
        } else if (toAckId == 527) {
            if (status == 2) {
                ZLogger.v("CMD_APT_GET_VOLUME_OUT_LEVEL not supported");
            }
            notifyOperationComplete(39, status);
        } else if (toAckId == 3115) {
            notifyOperationComplete(36, status);
        } else if (toAckId == 3116) {
            if (status == 2) {
                ZLogger.d("CMD_LISTENING_MODE_CYCLE_GET not supported");
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
            }
            notifyOperationComplete(37, status);
        } else {
            if (toAckId == 3585) {
                if (status == 2) {
                    ZLogger.v("CMD_GET_LOW_LATENCY_MODE_STATUS not supported");
                    getDeviceInfo().setGamingModeEnabled(false);
                    a(21);
                } else {
                    notifyOperationComplete(45, status);
                }
                return true;
            }
            if (toAckId == 3586) {
                if (status == 2) {
                    ZLogger.v("CMD_GET_EAR_DETECTION_STATUS not supported");
                    getDeviceInfo().setEarDetectionStatus((byte) 0);
                    a(55);
                } else {
                    notifyOperationComplete(35, status);
                }
                return true;
            }
            switch (toAckId) {
                case 12:
                    if (status == 2) {
                        ZLogger.d("CMD_INFO_REQ not supported");
                        getDeviceInfo().setCmdSetInfo(c.c);
                    }
                    notifyOperationComplete(30, status);
                    break;
                case 18:
                    notifyOperationComplete(26, status);
                    break;
                case 33:
                    if (status == 2) {
                        ZLogger.d("CMD_GET_BUD_INFO not supported");
                    }
                    notifyOperationComplete(54, status);
                    break;
                case 261:
                    if (status == 2) {
                        ZLogger.d("CMD_GET_LE_ADDR not supported");
                        getDeviceInfo().setLeAddr(null);
                    }
                    notifyOperationComplete(24, status);
                    break;
                case GattClient.STATE_PREPARE_PAIRING_REQUEST /* 534 */:
                    notifyOperationComplete(64, status);
                    break;
                case 776:
                    if (status != 0) {
                        notifyOperationComplete(63, status);
                    }
                    return true;
                case 783:
                    if (status == 2) {
                        ZLogger.d("CMD_GET_PACKAGE_ID not supported, cmdVersion=0");
                    }
                    notifyOperationComplete(34, status);
                    break;
                case 789:
                    if (status == 2) {
                        ZLogger.d("CMD_GET_NUM_OF_CONNECTION not supported");
                    }
                    notifyOperationComplete(55, status);
                    break;
                case 1536:
                    if (status == 2 || status == 1) {
                        getDeviceInfo().setSppOtaSupported(false);
                        a(14);
                    } else if (status == 0) {
                        getDeviceInfo().setSppOtaSupported(true);
                    }
                    notifyOperationComplete(25, status);
                    break;
                case 2832:
                    if (ModelClient.VDBG) {
                        ZLogger.v(String.format("onPlayRingtongResponse: 0x%02X", Byte.valueOf(status)));
                    }
                    List<MCB> list3 = this.mCallbacks;
                    if (list3 != 0 && list3.size() > 0) {
                        Iterator it3 = this.mCallbacks.iterator();
                        while (it3.hasNext()) {
                            ((VendorModelCallback) it3.next()).onPlayRingtongResponse(status);
                        }
                    } else if (ModelClient.VDBG) {
                        ZLogger.v("no callback registered");
                    }
                    return true;
                case 3588:
                    if (status == 2) {
                        ZLogger.v("CMD_SET_LOW_LATENCY_LEVEL not supported");
                        a(22);
                    } else {
                        notifyOperationComplete(46, status);
                    }
                    return true;
                default:
                    switch (toAckId) {
                        case 3118:
                            if (status != 0) {
                                ZLogger.v(ModelClient.VDBG, "CMD_APT_VOLUME_INFO not supported");
                                notifyOperationComplete(39, status);
                            }
                            return true;
                        case 3119:
                            if (status != 0) {
                                ZLogger.v(ModelClient.VDBG, "CMD_APT_VOLUME_SET not supported");
                                notifyOperationComplete(40, status);
                            }
                            return true;
                        case 3120:
                            if (status != 0) {
                                ZLogger.d(ModelClient.VDBG, "CMD_APT_VOLUME_STATUS not supported");
                                notifyOperationComplete(41, status);
                            }
                            return true;
                        case 3121:
                            if (status != 0) {
                                ZLogger.d(ModelClient.VDBG, "CMD_LLAPT_BRIGHTNESS_INFO not supported");
                                notifyOperationComplete(44, status);
                            }
                            return true;
                        case 3122:
                            if (status != 0) {
                                ZLogger.d(ModelClient.VDBG, "CMD_LLAPT_BRIGHTNESS_SET not supported");
                                notifyOperationComplete(42, status);
                            }
                            return true;
                        case 3123:
                            if (status != 0) {
                                ZLogger.d("CMD_LLAPT_BRIGHTNESS_STATUS not supported");
                                notifyOperationComplete(43, status);
                            }
                            return true;
                        case 3124:
                            if (status != 0) {
                                ZLogger.d(ModelClient.VDBG, "CMD_APT_SET_NR_ON_OFF not supported");
                                notifyOperationComplete(47, status);
                            }
                            return true;
                        case 3125:
                            if (status != 0) {
                                ZLogger.d(ModelClient.VDBG, "CMD_APT_GET_NR_ON_OFF not supported");
                                notifyOperationComplete(48, status);
                            }
                            return true;
                        case 3126:
                            if (status != 0) {
                                ZLogger.d("CMD_LLAPT_SCENARIO_CHOOSE_INFO not supported");
                                notifyOperationComplete(49, status);
                            }
                            return true;
                        case 3127:
                            if (status != 0) {
                                ZLogger.d("CMD_LLAPT_SCENARIO_CHOOSE_TRY not supported");
                                notifyOperationComplete(50, status);
                            }
                            return true;
                        case 3128:
                            if (status == 2) {
                                ZLogger.d("CMD_LLAPT_SCENARIO_CHOOSE_RESULT not supported");
                                notifyOperationComplete(51, status);
                            }
                            return true;
                        case 3129:
                            if (status != 0) {
                                ZLogger.v(ModelClient.VDBG, "CMD_APT_GET_POWER_ON_DELAY_TIME not supported");
                                notifyOperationComplete(52, status);
                            }
                            return true;
                        case 3130:
                            if (status != 0) {
                                ZLogger.v(ModelClient.VDBG, "CMD_APT_SET_POWER_ON_DELAY_TIME not supported");
                                notifyOperationComplete(53, status);
                            }
                            return true;
                        case 3131:
                            if (status != 0) {
                                notifyOperationComplete(38, status);
                            }
                            return true;
                        case 3132:
                            if (status == 2) {
                                ZLogger.d("CMD_LISTENING_MODE_STATUS not supported");
                                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                            }
                            return true;
                        default:
                            switch (toAckId) {
                                case 3140:
                                    if (status != 0) {
                                        ZLogger.d(ModelClient.DBG, "CMD_ANC_SCENARIO_CHOOSE_INFO not supported");
                                        notifyOperationComplete(57, status);
                                    }
                                    return true;
                                case 3141:
                                    if (status != 0) {
                                        ZLogger.d(ModelClient.DBG, "CMD_ANC_SCENARIO_CHOOSE_TRY not supported");
                                        notifyOperationComplete(58, status);
                                    }
                                    return true;
                                case 3142:
                                    if (status == 2) {
                                        ZLogger.d(ModelClient.DBG, "CMD_ANC_SCENARIO_CHOOSE_RESULT not supported");
                                        notifyOperationComplete(59, status);
                                    }
                                    return true;
                                default:
                                    switch (toAckId) {
                                        case 1792:
                                            if (status == 2) {
                                                ZLogger.v("CMD_GET_SUPPORTED_MMI_LIST not supported");
                                                getDeviceInfo().setSupportedMmi(null);
                                            }
                                            notifyOperationComplete(16, status);
                                            break;
                                        case 1793:
                                            if (status == 2) {
                                                ZLogger.v("CMD_GET_SUPPORTED_CLICK_TYPE not supported");
                                                getDeviceInfo().setSupportedClickType(null);
                                            }
                                            notifyOperationComplete(17, status);
                                            break;
                                        case 1794:
                                            if (status == 2) {
                                                ZLogger.v("CMD_GET_SUPPORTED_CALL_STATUS not supported");
                                                getDeviceInfo().setSupportedCallStatus(null);
                                            }
                                            notifyOperationComplete(18, status);
                                            break;
                                        case 1795:
                                            if (status == 2) {
                                                ZLogger.v(ModelClient.VDBG, "CMD_GET_KEY_MMI_MAP not supported");
                                                getDeviceInfo().setKeyMmiSettings(null);
                                            }
                                            notifyOperationComplete(19, status);
                                            break;
                                        case 1796:
                                            notifyOperationComplete(20, status);
                                            if (status == 0) {
                                                getKeyMmiMap();
                                            }
                                            return true;
                                        default:
                                            switch (toAckId) {
                                                case 1798:
                                                    notifyOperationComplete(1, status);
                                                    break;
                                                case 1799:
                                                    notifyOperationComplete(56, status);
                                                    break;
                                                case 1800:
                                                    if (status == 2) {
                                                        ZLogger.v(ModelClient.VDBG, "CMD_GET_RWS_KEY_MMI_MAP not supported");
                                                        getDeviceInfo().setRwsKeyMmiSettings(null);
                                                    }
                                                    notifyOperationComplete(60, status);
                                                    break;
                                                case 1801:
                                                    notifyOperationComplete(61, status);
                                                    if (status == 0) {
                                                        getRwsKeyMmiMap();
                                                    }
                                                    return true;
                                                case 1802:
                                                    notifyOperationComplete(62, status);
                                                    break;
                                                default:
                                                    switch (toAckId) {
                                                        case 1808:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_SET_ENABLE not supported");
                                                            }
                                                            notifyOperationComplete(6, status);
                                                            break;
                                                        case 1809:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_SET_DISENABLE not supported");
                                                            }
                                                            notifyOperationComplete(7, status);
                                                            break;
                                                        case 1810:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_TOGGLE not supported");
                                                            }
                                                            notifyOperationComplete(8, status);
                                                            break;
                                                        case 1811:
                                                            ZLogger.v("ACK-CMD_MOTOR_GET_STATUS");
                                                            if (status == 2 || status == 1) {
                                                                getDeviceInfo().setMotorFeature((byte) 0);
                                                                getDeviceInfo().setMotorStatus((byte) 0);
                                                            } else if (status == 0) {
                                                                getDeviceInfo().setMotorFeature((byte) 1);
                                                            }
                                                            notifyOperationComplete(10, status);
                                                            break;
                                                        case 1812:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_SET_MODE not supported");
                                                            }
                                                            notifyOperationComplete(13, status);
                                                            break;
                                                        case 1813:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_STOP_VIBRATION not supported");
                                                            }
                                                            notifyOperationComplete(9, status);
                                                            break;
                                                        case 1814:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_CHECK_VIBRATION not supported");
                                                            }
                                                            notifyOperationComplete(11, status);
                                                            break;
                                                        case 1815:
                                                            if (status == 2) {
                                                                ZLogger.v("CMD_MOTOR_GET_MODE_PARAMETER not supported");
                                                            }
                                                            notifyOperationComplete(12, status);
                                                            break;
                                                        default:
                                                            switch (toAckId) {
                                                                case 3104:
                                                                    if (status == 2) {
                                                                        ZLogger.d(ModelClient.DBG, "CMD_ANC_QUERY not supported");
                                                                        getDeviceInfo().setAncGroup(null);
                                                                        updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                                                                    }
                                                                    notifyOperationComplete(22, status);
                                                                    break;
                                                                case 3105:
                                                                    if (status == 2) {
                                                                        ZLogger.d(ModelClient.DBG, "CMD_ANC_SET_STATE not supported");
                                                                        getDeviceInfo().setAncStatus((byte) 0);
                                                                    }
                                                                    notifyOperationComplete(14, status);
                                                                    break;
                                                                case 3106:
                                                                    if (status == 2) {
                                                                        ZLogger.d(ModelClient.DBG, "CMD_LLAPT_QUERY not supported");
                                                                        getDeviceInfo().setLlAptStatus((byte) 0);
                                                                        updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, status);
                                                                    }
                                                                    notifyOperationComplete(32, status);
                                                                    break;
                                                                case 3107:
                                                                    if (status == 2) {
                                                                        ZLogger.d(ModelClient.DBG, "CMD_LLAPT_ENABLE_DISABLE not supported");
                                                                        getDeviceInfo().setLlAptStatus((byte) 0);
                                                                    }
                                                                    if (status != 0) {
                                                                        notifyOperationComplete(33, status);
                                                                    }
                                                                    return true;
                                                                default:
                                                                    return false;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return true;
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        byte[] parameters = transportLayerPacket.getParameters();
        transportLayerPacket.getPayload();
        if (opcode == 3) {
            if (ModelClient.VDBG) {
                ZLogger.v(">> EVENT_BT_CONNECT_STATUS");
            }
            if (parameters != null && parameters.length >= 7) {
                byte b = parameters[0];
                byte[] bArr = new byte[6];
                System.arraycopy(parameters, 1, bArr, 0, 6);
                List<MCB> list = this.mCallbacks;
                if (list != 0 && list.size() > 0) {
                    Iterator it = this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((VendorModelCallback) it.next()).onProfileConnected(b, bArr);
                    }
                } else if (ModelClient.VDBG) {
                    ZLogger.v("no callback registered");
                }
            }
            return true;
        }
        if (opcode == 4) {
            if (ModelClient.VDBG) {
                ZLogger.v(">> EVENT_BT_DISCONNECT_STATUS");
            }
            if (parameters != null && parameters.length >= 7) {
                byte b2 = parameters[0];
                byte[] bArr2 = new byte[6];
                System.arraycopy(parameters, 1, bArr2, 0, 6);
                List<MCB> list2 = this.mCallbacks;
                if (list2 != 0 && list2.size() > 0) {
                    Iterator it2 = this.mCallbacks.iterator();
                    while (it2.hasNext()) {
                        ((VendorModelCallback) it2.next()).onProfileDisconnected(b2, bArr2);
                    }
                } else if (ModelClient.VDBG) {
                    ZLogger.v("no callback registered");
                }
            }
            return true;
        }
        if (opcode == 521) {
            VpToneVolumeStatusRsp vpToneVolumeStatusRsp = VpToneVolumeStatusRsp.parse(parameters);
            if (vpToneVolumeStatusRsp != null) {
                if (ModelClient.DBG) {
                    ZLogger.d(vpToneVolumeStatusRsp.toString());
                }
                getDeviceInfo().setVpVolumeInfo(vpToneVolumeStatusRsp.toVpVolumeInfo());
                a(56);
            } else if (ModelClient.VDBG) {
                ZLogger.v(">> EVENT_VP_TONE_VOLUME_STATUS");
            }
            return true;
        }
        if (opcode == 522) {
            VpToneVolumeLevelSetRsp vpToneVolumeLevelSetRsp = VpToneVolumeLevelSetRsp.parse(parameters);
            if (vpToneVolumeLevelSetRsp != null) {
                if (ModelClient.DBG) {
                    ZLogger.v(vpToneVolumeLevelSetRsp.toString());
                }
                getDeviceInfo().setVpVolumeInfo(vpToneVolumeLevelSetRsp.toVpVolumeInfo());
                a(56);
            } else if (ModelClient.VDBG) {
                ZLogger.v(">> EVENT_VP_TONE_VOLUME_LEVEL_SET");
            }
            return true;
        }
        if (opcode == 3115 || opcode == 3116) {
            if (ModelClient.VDBG) {
                ZLogger.v(">> EVENT_LISTENING_MODE_CYCLE_SET/GET");
            }
            if (parameters != null && parameters.length > 0) {
                getDeviceInfo().setListeningModeCycle(parameters[0]);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(41);
            }
            return true;
        }
        if (opcode == 3131) {
            if (parameters != null && parameters.length > 0) {
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_LISTENING_MODE_SET");
                }
                if (parameters[0] == 0) {
                    notifyOperationComplete(38, (byte) 0);
                } else {
                    notifyOperationComplete(38, (byte) 5);
                }
            }
            return true;
        }
        if (opcode == 3132) {
            if (ModelClient.VDBG) {
                ZLogger.v(">> EVENT_LISTENING_MODE_STATUS");
            }
            com.realsil.sdk.bbpro.hearing.a aVarA = com.realsil.sdk.bbpro.hearing.a.a(parameters);
            if (aVarA != null) {
                if (ModelClient.VDBG) {
                    ZLogger.v(aVarA.toString());
                }
                getDeviceInfo().updateListeningModeStatus(aVarA);
                updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                a(48);
            }
            return true;
        }
        switch (opcode) {
            case 17:
                if (parameters != null && parameters.length >= 2) {
                    byte b3 = parameters[0];
                    if (b3 == 0) {
                        getDeviceInfo().setCmdSetInfo(c.a(parameters));
                        a(12);
                    } else if (b3 == 1 && parameters[1] == 0 && parameters.length >= 8) {
                        int length = parameters.length - 2;
                        byte[] bArr3 = new byte[length];
                        System.arraycopy(parameters, 2, bArr3, 0, length);
                        getDeviceInfo().setDspCapability(new DspCapability(bArr3));
                        a(13);
                    }
                }
                return true;
            case 24:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_REPORT_CFG_SETTINGS");
                }
                com.realsil.sdk.bbpro.i.a aVarA2 = com.realsil.sdk.bbpro.i.a.a(parameters);
                if (aVarA2 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aVarA2.toString());
                    }
                    if (aVarA2.i() == 0) {
                        getDeviceInfo().setLeName(aVarA2.e());
                        a(3);
                    } else if (1 == aVarA2.i()) {
                        getDeviceInfo().setBrEdrName(aVarA2.f());
                        a(2);
                    } else if (2 == aVarA2.i()) {
                        getDeviceInfo().setIcName(aVarA2.c());
                        a(58);
                    } else if (3 == aVarA2.i()) {
                        getDeviceInfo().setCompanyId(aVarA2.a());
                        getDeviceInfo().setModelId(aVarA2.g());
                        a(59);
                    } else if (4 == aVarA2.i()) {
                        getDeviceInfo().setDeviceId(aVarA2.b());
                        a(60);
                    } else if (5 == aVarA2.i()) {
                        getDeviceInfo().setLchSingleDeviceId(aVarA2.d());
                        a(61);
                    } else if (6 == aVarA2.i()) {
                        getDeviceInfo().setRchSingleDeviceId(aVarA2.h());
                        a(62);
                    }
                }
                return true;
            case 34:
                com.realsil.sdk.bbpro.j.a aVarA3 = com.realsil.sdk.bbpro.j.a.a(parameters);
                if (aVarA3 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aVarA3.toString());
                    }
                    getDeviceInfo().setDeviceStatusInfo(aVarA3.a());
                    a(39);
                }
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
                if (aptVolumeInfoBuilderV2 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aptVolumeInfoBuilderV2.toString());
                    }
                    getDeviceInfo().setAptVolumeInfo(aptVolumeInfoBuilderV2);
                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                    a(33);
                }
                return true;
            case 789:
                com.realsil.sdk.bbpro.h.a aVarA4 = com.realsil.sdk.bbpro.h.a.a(parameters);
                if (aVarA4 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aVarA4.toString());
                    }
                    MultilinkInfo multilinkInfo = new MultilinkInfo();
                    multilinkInfo.setConnNum(aVarA4.a);
                    a(multilinkInfo);
                }
                return true;
            case 1536:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_OTA_GET_DEVICE_INFO");
                }
                a(14);
                return true;
            case 1800:
                List<KeyMmiSettings> listA = com.realsil.sdk.bbpro.f.a.a(parameters);
                getDeviceInfo().setRwsKeyMmiSettings(listA);
                List<MCB> list3 = this.mCallbacks;
                if (list3 != 0 && list3.size() > 0) {
                    Iterator it3 = this.mCallbacks.iterator();
                    while (it3.hasNext()) {
                        ((VendorModelCallback) it3.next()).onRwsKeyMapSettingsReported(listA);
                    }
                } else if (ModelClient.VDBG) {
                    ZLogger.v("no callback registered");
                }
                return true;
            case 2313:
                b bVarA = b.a(parameters);
                if (bVarA != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(bVarA.toString());
                    }
                    getDeviceInfo().setPrimaryUiOtaVersion(bVarA.f);
                    a(63);
                }
                return true;
            case 2321:
                if (parameters != null && parameters.length >= 2) {
                    getDeviceInfo().setChipId(parameters[0]);
                    getDeviceInfo().setPackageId(parameters[1]);
                    a(11);
                }
                return true;
            case 2832:
                if (ModelClient.VDBG) {
                    ZLogger.v(">> EVENT_SOUND_PRESS_CALIBRATION");
                }
                if (parameters != null && parameters.length >= 1) {
                    byte b4 = parameters[0];
                    List<MCB> list4 = this.mCallbacks;
                    if (list4 != 0 && list4.size() > 0) {
                        Iterator it4 = this.mCallbacks.iterator();
                        while (it4.hasNext()) {
                            ((VendorModelCallback) it4.next()).onPlayRingtongResult(b4);
                        }
                    } else if (ModelClient.VDBG) {
                        ZLogger.v("no callback registered");
                    }
                }
                return true;
            case 3118:
                AptVolumeInfo aptVolumeInfoBuilder = AptVolumeInfo.builder(parameters);
                if (aptVolumeInfoBuilder != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aptVolumeInfoBuilder.toString());
                    }
                    getDeviceInfo().setAptVolumeInfo(aptVolumeInfoBuilder);
                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                    a(33);
                }
                return true;
            case 3119:
                com.realsil.sdk.bbpro.b.c cVarA = com.realsil.sdk.bbpro.b.c.a(parameters);
                if (cVarA != null) {
                    if (cVarA.b()) {
                        getDeviceInfo().setAptVolumeStatus(cVarA.a());
                        a(33);
                    } else {
                        notifyOperationComplete(40, (byte) 5);
                    }
                }
                return true;
            case 3120:
                AptVolumeStatus aptVolumeStatusBuilder = AptVolumeStatus.builder(parameters);
                if (aptVolumeStatusBuilder != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aptVolumeStatusBuilder.toString());
                    }
                    getDeviceInfo().setAptVolumeStatus(aptVolumeStatusBuilder);
                    a(33);
                }
                return true;
            case 3121:
                LlAptBrightnessInfo llAptBrightnessInfoBuilder = LlAptBrightnessInfo.builder(parameters);
                if (llAptBrightnessInfoBuilder != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(llAptBrightnessInfoBuilder.toString());
                    }
                    getDeviceInfo().setLlAptBrightnessInfo(llAptBrightnessInfoBuilder);
                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                    a(51);
                }
                return true;
            case 3122:
                com.realsil.sdk.bbpro.g.c cVarA2 = com.realsil.sdk.bbpro.g.c.a(parameters);
                if (cVarA2 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(cVarA2.toString());
                    }
                    if (cVarA2.b()) {
                        getDeviceInfo().setLlAptBrighenessStatus(cVarA2.a());
                        a(52);
                    } else {
                        notifyOperationComplete(42, (byte) 5);
                    }
                }
                return true;
            case 3123:
                LlAptBrightnessStatus llAptBrightnessStatusBuilder = LlAptBrightnessStatus.builder(parameters);
                if (llAptBrightnessStatusBuilder != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(llAptBrightnessStatusBuilder.toString());
                    }
                    getDeviceInfo().setLlAptBrighenessStatus(llAptBrightnessStatusBuilder);
                    a(52);
                }
                return true;
            case 3124:
                com.realsil.sdk.bbpro.b.a aVarA5 = com.realsil.sdk.bbpro.b.a.a(parameters);
                if (aVarA5 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aVarA5.toString());
                    }
                    getDeviceInfo().setAptNrEnabled(aVarA5.a());
                    a(32);
                }
                return true;
            case 3140:
                com.realsil.sdk.bbpro.a.a aVarA6 = com.realsil.sdk.bbpro.a.a.a(parameters);
                if (aVarA6 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aVarA6.toString());
                    }
                    getDeviceInfo().setAncScenarioChooseInfo(aVarA6.a());
                    a(57);
                }
                return true;
            case 3141:
                com.realsil.sdk.bbpro.a.c cVarA3 = com.realsil.sdk.bbpro.a.c.a(parameters);
                if (cVarA3 == null || !cVarA3.a()) {
                    notifyOperationComplete(58, (byte) 5);
                } else {
                    notifyOperationComplete(58, (byte) 0);
                }
                return true;
            case 3142:
                com.realsil.sdk.bbpro.a.b bVarA2 = com.realsil.sdk.bbpro.a.b.a(parameters);
                if (bVarA2 == null || !bVarA2.a()) {
                    notifyOperationComplete(59, (byte) 5);
                } else {
                    notifyOperationComplete(59, (byte) 0);
                    queryAnc((byte) 0);
                }
                return true;
            case 3584:
                com.realsil.sdk.bbpro.d.a aVarA7 = com.realsil.sdk.bbpro.d.a.a(parameters);
                if (aVarA7 != null) {
                    getDeviceInfo().updateLowLatencyInfo(aVarA7);
                    if (ModelClient.VDBG) {
                        ZLogger.v(aVarA7.toString());
                    }
                    a(21);
                }
                return true;
            case 3585:
                if (parameters != null && parameters.length > 0) {
                    getDeviceInfo().setEarDetectionStatus(parameters[0]);
                    if (ModelClient.VDBG) {
                        ZLogger.v(String.format("isEarDetectionOn=%b", Boolean.valueOf(getDeviceInfo().isEarDetectionOn())));
                    }
                    a(55);
                }
                return true;
            case 3586:
                com.realsil.sdk.bbpro.d.b bVarA3 = com.realsil.sdk.bbpro.d.b.a(parameters);
                if (bVarA3 != null) {
                    getDeviceInfo().updateLowLatencyLevelReport(bVarA3);
                    if (ModelClient.VDBG) {
                        ZLogger.v(bVarA3.toString());
                    }
                    a(22);
                }
                return true;
            default:
                switch (opcode) {
                    case 1792:
                        if (parameters != null && parameters.length > 0) {
                            int i = parameters[0] & 255;
                            if (parameters.length >= i + 1) {
                                byte[] bArr4 = new byte[i];
                                System.arraycopy(parameters, 1, bArr4, 0, i);
                                getDeviceInfo().setSupportedMmi(bArr4);
                                a(35);
                            }
                        }
                        return true;
                    case 1793:
                        if (parameters != null && parameters.length > 0) {
                            int i2 = parameters[0] & 255;
                            if (parameters.length >= i2 + 1) {
                                byte[] bArr5 = new byte[i2];
                                System.arraycopy(parameters, 1, bArr5, 0, i2);
                                getDeviceInfo().setSupportedClickType(bArr5);
                                a(36);
                            }
                        }
                        return true;
                    case 1794:
                        if (parameters != null && parameters.length > 0) {
                            int i3 = parameters[0] & 255;
                            if (parameters.length >= i3 + 1) {
                                byte[] bArr6 = new byte[i3];
                                System.arraycopy(parameters, 1, bArr6, 0, i3);
                                getDeviceInfo().setSupportedCallStatus(bArr6);
                                a(37);
                            }
                        }
                        return true;
                    case 1795:
                        List<KeyMmiSettings> listA2 = com.realsil.sdk.bbpro.f.a.a(parameters);
                        getDeviceInfo().setKeyMmiSettings(listA2);
                        List<MCB> list5 = this.mCallbacks;
                        if (list5 != 0 && list5.size() > 0) {
                            Iterator it5 = this.mCallbacks.iterator();
                            while (it5.hasNext()) {
                                ((VendorModelCallback) it5.next()).onKeyMapSettingsReported(listA2);
                            }
                        } else if (ModelClient.VDBG) {
                            ZLogger.v("no callback registered");
                        }
                        return true;
                    default:
                        switch (opcode) {
                            case 1808:
                                if (ModelClient.VDBG) {
                                    ZLogger.v(">> EVENT_REPORT_MOTOR_STATUS");
                                }
                                if (parameters != null && parameters.length > 0) {
                                    getDeviceInfo().setMotorStatus(parameters[0]);
                                    a(18);
                                }
                                return true;
                            case 1809:
                                if (ModelClient.VDBG) {
                                    ZLogger.v(">> EVENT_REPORT_MOTOR_VIBRATION_STATUS");
                                }
                                if (parameters != null && parameters.length > 0) {
                                    getDeviceInfo().setVibrationStatus(parameters[0]);
                                    a(20);
                                }
                                return true;
                            case 1810:
                                if (ModelClient.VDBG) {
                                    ZLogger.v(">> EVENT_REPORT_MOTOR_MODE_PARAMETERS");
                                }
                                if (parameters != null && parameters.length > 0) {
                                    List<MCB> list6 = this.mCallbacks;
                                    if (list6 != 0 && list6.size() > 0) {
                                        int i4 = ((parameters[1] << 8) | (parameters[0] & 255)) & 65535;
                                        int i5 = ((parameters[2] & 255) | (parameters[3] << 8)) & 65535;
                                        getDeviceInfo().setVibrateOnTime(i4);
                                        getDeviceInfo().setVibrateOffTime(i5);
                                        getDeviceInfo().setVibrateCount(parameters[4] & 255);
                                        a(19);
                                    } else if (ModelClient.VDBG) {
                                        ZLogger.v("no callback registered");
                                    }
                                }
                                return true;
                            default:
                                switch (opcode) {
                                    case 3072:
                                    case 3073:
                                    case 3074:
                                    case 3075:
                                    case 3076:
                                        if (ModelClient.VDBG) {
                                            ZLogger.v(">> onToneAndTalkKeyEventReport" + ((int) opcode));
                                        }
                                        List<MCB> list7 = this.mCallbacks;
                                        if (list7 != 0 && list7.size() > 0) {
                                            Iterator it6 = this.mCallbacks.iterator();
                                            while (it6.hasNext()) {
                                                ((VendorModelCallback) it6.next()).onKeyeventReported(opcode);
                                            }
                                        } else if (ModelClient.VDBG) {
                                            ZLogger.v("no callback registered");
                                        }
                                        return true;
                                    default:
                                        switch (opcode) {
                                            case 3104:
                                                if (parameters != null && parameters.length > 0) {
                                                    if (ModelClient.VDBG) {
                                                        ZLogger.v(">> EVENT_ANC_QUERY");
                                                    }
                                                    getDeviceInfo().setAncGroup(AncGroup.builder(parameters));
                                                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                                                    a(16);
                                                }
                                                return true;
                                            case 3105:
                                                if (parameters != null && parameters.length > 1) {
                                                    if (ModelClient.VDBG) {
                                                        ZLogger.v(">> EVENT_ANC_SET_STATE");
                                                    }
                                                    if (parameters[1] != 0) {
                                                        notifyOperationComplete(14, (byte) 5);
                                                    }
                                                }
                                                return true;
                                            case 3106:
                                                if (parameters != null && parameters.length > 0) {
                                                    if (ModelClient.VDBG) {
                                                        ZLogger.v(">> EVENT_LLAPT_QUERY");
                                                    }
                                                    getDeviceInfo().setLlaptBasicInfo(LlAptBasicInfo.builder(parameters));
                                                    updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
                                                    a(49);
                                                }
                                                return true;
                                            case 3107:
                                                if (parameters != null && parameters.length > 1) {
                                                    if (ModelClient.VDBG) {
                                                        ZLogger.v(">> EVENT_LLAPT_ENABLE_DISABLE");
                                                    }
                                                    if (parameters[1] != 0) {
                                                        notifyOperationComplete(33, (byte) 5);
                                                    }
                                                }
                                                return true;
                                            default:
                                                switch (opcode) {
                                                    case 3126:
                                                        com.realsil.sdk.bbpro.g.b bVarA4 = com.realsil.sdk.bbpro.g.b.a(parameters);
                                                        if (bVarA4 != null) {
                                                            if (ModelClient.VDBG) {
                                                                ZLogger.v(bVarA4.toString());
                                                            }
                                                            getDeviceInfo().setLlaptScenarioChooseInfo(bVarA4.a());
                                                            a(53);
                                                        }
                                                        return true;
                                                    case 3127:
                                                        e eVarA = e.a(parameters);
                                                        if (eVarA == null || !eVarA.a()) {
                                                            notifyOperationComplete(50, (byte) 5);
                                                        } else {
                                                            notifyOperationComplete(50, (byte) 0);
                                                        }
                                                        return true;
                                                    case 3128:
                                                        d dVarA = d.a(parameters);
                                                        if (dVarA == null || !dVarA.a()) {
                                                            notifyOperationComplete(51, (byte) 5);
                                                        } else {
                                                            notifyOperationComplete(51, (byte) 0);
                                                            queryLlApt((byte) 1);
                                                        }
                                                        return true;
                                                    case 3129:
                                                        com.realsil.sdk.bbpro.b.b bVarA5 = com.realsil.sdk.bbpro.b.b.a(parameters);
                                                        if (bVarA5 != null) {
                                                            if (ModelClient.VDBG) {
                                                                ZLogger.v(bVarA5.toString());
                                                            }
                                                            getDeviceInfo().setAptPowerOnDelayTime(bVarA5.a());
                                                            a(34);
                                                        }
                                                        return true;
                                                    default:
                                                        return false;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public boolean processStatusReport(byte b, byte[] bArr) {
        byte b2;
        byte b3;
        if (bArr.length > 2) {
            b2 = bArr[1];
            b3 = bArr[2];
        } else {
            b2 = bArr.length > 1 ? bArr[1] : (byte) 0;
            b3 = 0;
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
        if (b == 0) {
            if (!getDeviceInfo().isBudInfoReqSupported()) {
                getDeviceInfo().setRwsState(b2);
                updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
                a(5);
            }
            return true;
        }
        if (b == 1) {
            getDeviceInfo().setPrimaryRwsChannel((byte) ((b2 & Mmi.AU_MMI_START_ROLESWAP) >> 4));
            getDeviceInfo().setSecondaryRwsChannel((byte) (b2 & 15));
            a(6);
            return true;
        }
        if (b == 2) {
            getDeviceInfo().setPrimaryBatStatus(b2 & 255);
            getDeviceInfo().setSecondaryBatStatus(b3 & 255);
            updateUserTasks(ModelClient.UUID_QUERY_BUD_INFO, (byte) 0);
            a(8);
            return true;
        }
        if (b == 3) {
            getDeviceInfo().setAptStatus(b2);
            updateUserTasks(ModelClient.UUID_QUERY_LISTENING_MODE_INFO, (byte) 0);
            a(25);
            return true;
        }
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
                if (aptVolumeInfoBuilderV1 != null) {
                    if (ModelClient.VDBG) {
                        ZLogger.v(aptVolumeInfoBuilderV1.toString());
                    }
                    getDeviceInfo().setAptVolumeInfo(aptVolumeInfoBuilderV1);
                    a(33);
                }
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
                getDeviceInfo().setAncStatus(b2);
                a(17);
                if (b2 == 1) {
                    queryAnc((byte) 0);
                }
                return true;
            case 11:
                getDeviceInfo().setLlAptStatus(b2);
                a(25);
                if (b2 == 1) {
                    queryLlApt((byte) 1);
                }
                return true;
            case 12:
                getDeviceInfo().setPrimaryDefaultRwsChannel((byte) ((b2 & Mmi.AU_MMI_START_ROLESWAP) >> 4));
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

    public BeeError queryListeningModeStatus() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3132, null).eventId((short) 3132).build());
    }

    public BeeError queryLlApt(byte b) {
        return sendAppReq(new a.b(b).a());
    }

    public BeeError sendMmi(byte b) {
        return sendAppReq(new MmiReq.Builder(b).build());
    }

    public BeeError queryAnc(byte b) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3104, new byte[]{b}).eventId((short) 3104).build());
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
