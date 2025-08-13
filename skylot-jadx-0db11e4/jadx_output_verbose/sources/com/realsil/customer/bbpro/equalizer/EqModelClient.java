package com.realsil.customer.bbpro.equalizer;

import android.content.Context;
import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.DspConfig;
import com.realsil.customer.bbpro.core.BeeError;
import com.realsil.customer.bbpro.core.protocol.CommandContract;
import com.realsil.customer.bbpro.core.transportlayer.AckPacket;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.customer.bbpro.equalizer.GetEqEntryIndexReq;
import com.realsil.customer.bbpro.equalizer.GetEqIndexParameterReq;
import com.realsil.customer.bbpro.equalizer.GetEqInfoReq;
import com.realsil.customer.bbpro.equalizer.ResetEqDataReq;
import com.realsil.customer.bbpro.equalizer.SetEqEntryIndexReq;
import com.realsil.customer.bbpro.equalizer.SetEqIndexParameterReq;
import com.realsil.customer.bbpro.equalizer.a;
import com.realsil.customer.bbpro.equalizer.b;
import com.realsil.customer.bbpro.equalizer.f;
import com.realsil.customer.bbpro.i.d;
import com.realsil.customer.bbpro.internal.ModelClient;
import com.realsil.customer.bbpro.internal.UserTask;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.bbpro.vendor.VendorModelClient;
import com.realsil.customer.core.logger.ZLogger;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient.class */
public final class EqModelClient extends com.realsil.customer.bbpro.equalizer.e {
    public static volatile EqModelClient o;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$a.class */
    public class a extends UserTask {
        public a(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            ZLogger.v(ModelClient.DBG, "queryEqEntryNumber ...");
            startSubTask(false);
            if (EqModelClient.this.queryEqEntryNumber().code != 0) {
                stopSubTask();
                ZLogger.w("queryEqEntryNumber failed");
                return;
            }
            waitTaskComplete();
            ZLogger.v(ModelClient.DBG, String.format(Locale.US, "getMicEqEntryIndex(eqMode=%d) ...", 0));
            startSubTask(false);
            if (EqModelClient.this.getEqEntryIndex(new GetEqEntryIndexReq.Builder(1, 0).build()).code != 0) {
                stopSubTask();
                ZLogger.w("getMicEqEntryIndex failed");
            } else {
                waitTaskComplete();
                EqModelClient eqModelClient = EqModelClient.this;
                eqModelClient.b((byte) 0, 0, eqModelClient.getEqInfo());
                EqModelClient.this.removeTask(this);
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$b.class */
    public class b extends UserTask {
        public b(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            ZLogger.v(ModelClient.DBG, "queryEqBasicInfo ...");
            startSubTask(false);
            if (EqModelClient.this.sendAppReq(new f.b().a()).code != 0) {
                stopSubTask();
                ZLogger.w("queryEqBasicInfo failed");
            } else {
                waitTaskComplete();
                EqModelClient eqModelClient = EqModelClient.this;
                eqModelClient.b((byte) 0, 0, eqModelClient.getEqInfo());
                EqModelClient.this.removeTask(this);
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$c.class */
    public class c extends UserTask {
        public c(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            if (EqModelClient.this.isGamingModeSupported()) {
                ZLogger.v(ModelClient.DBG, "queryGamingModeState ...");
                startSubTask(false);
                if (EqModelClient.this.getGamingModeState().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryGamingModeState failed");
                    return;
                }
                waitTaskComplete();
            } else {
                ZLogger.v(ModelClient.DBG, "gaming mode not supported");
            }
            ZLogger.v(ModelClient.DBG, "queryEqEntryNumber and  ...");
            startSubTask(false);
            if (EqModelClient.this.getAudioEqSettingIndex().code != 0) {
                stopSubTask();
                ZLogger.w("queryEqEntryNumber failed");
                return;
            }
            waitTaskComplete();
            int i = (EqModelClient.this.isGamingModeEqFeatureSupported() && EqModelClient.this.isGamingModeEnabled()) ? 1 : 0;
            EqModelClient eqModelClient = EqModelClient.this;
            eqModelClient.a((byte) 0, i, eqModelClient.getEqInfo());
            EqModelClient.this.removeTask(this);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$d.class */
    public class d extends UserTask {
        public d(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            if (EqModelClient.this.isGamingModeSupported()) {
                ZLogger.v(ModelClient.DBG, "queryGamingModeState ...");
                startSubTask(false);
                if (EqModelClient.this.getGamingModeState().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryGamingModeState failed");
                    return;
                }
                waitTaskComplete();
            } else {
                ZLogger.v(ModelClient.DBG, "gaming mode not supported");
            }
            ZLogger.v(ModelClient.DBG, "queryEqState ...");
            startSubTask(false);
            if (EqModelClient.this.queryEqState().code != 0) {
                stopSubTask();
                ZLogger.w("queryEqState failed");
                return;
            }
            waitTaskComplete();
            ZLogger.v(ModelClient.DBG, "queryEqEntryNumber ...");
            startSubTask(false);
            if (EqModelClient.this.queryEqEntryNumber().code != 0) {
                stopSubTask();
                ZLogger.w("queryEqEntryNumber failed");
                return;
            }
            waitTaskComplete();
            boolean z = ModelClient.DBG;
            Locale locale = Locale.US;
            ZLogger.v(z, String.format(locale, "getEqEntryIndex(eqMode=%d) ...", 0));
            startSubTask(false);
            if (EqModelClient.this.getEqEntryIndex(new GetEqEntryIndexReq.Builder(0, 0).build()).code != 0) {
                stopSubTask();
                ZLogger.w("getEqEntryIndex failed");
                return;
            }
            waitTaskComplete();
            ZLogger.v(ModelClient.DBG, String.format(locale, "getEqEntryIndex(eqMode=%d) ...", 1));
            startSubTask(false);
            if (EqModelClient.this.getEqEntryIndex(new GetEqEntryIndexReq.Builder(0, 1).build()).code != 0) {
                stopSubTask();
                ZLogger.w("getEqEntryIndex failed");
                return;
            }
            waitTaskComplete();
            int i = (EqModelClient.this.isGamingModeEqFeatureSupported() && EqModelClient.this.isGamingModeEnabled()) ? 1 : 0;
            EqModelClient eqModelClient = EqModelClient.this;
            eqModelClient.a((byte) 0, i, eqModelClient.getEqInfo());
            EqModelClient.this.removeTask(this);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$e.class */
    public class e extends UserTask {
        public e(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            if (EqModelClient.this.isGamingModeSupported()) {
                ZLogger.v(ModelClient.DBG, "queryGamingModeState ...");
                startSubTask(false);
                if (EqModelClient.this.getGamingModeState().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryGamingModeState failed");
                    return;
                }
                waitTaskComplete();
            }
            ZLogger.v(ModelClient.DBG, "queryEqEntryNumber ...");
            startSubTask(false);
            if (EqModelClient.this.queryEqEntryNumber().code != 0) {
                stopSubTask();
                ZLogger.w("queryEqEntryNumber failed");
                return;
            }
            waitTaskComplete();
            boolean z = ModelClient.DBG;
            Locale locale = Locale.US;
            ZLogger.v(z, String.format(locale, "getSpkEqEntryIndex(eqMode=%d) ...", 0));
            startSubTask(false);
            if (EqModelClient.this.getEqEntryIndex(new GetEqEntryIndexReq.Builder(0, 0).build()).code != 0) {
                stopSubTask();
                ZLogger.w("getEqEntryIndex failed");
                return;
            }
            waitTaskComplete();
            if (EqModelClient.this.isGamingModeEqFeatureSupported()) {
                ZLogger.v(ModelClient.DBG, String.format(locale, "getSpkEqEntryIndex(eqMode=%d) ...", 1));
                startSubTask(false);
                if (EqModelClient.this.getEqEntryIndex(new GetEqEntryIndexReq.Builder(0, 1).build()).code != 0) {
                    stopSubTask();
                    ZLogger.w("getEqEntryIndex failed");
                    return;
                }
                waitTaskComplete();
            }
            if (EqModelClient.this.isAncEqSupported()) {
                ZLogger.v(ModelClient.DBG, String.format(locale, "getSpkEqEntryIndex(eqMode=%d) ...", 2));
                startSubTask(false);
                if (EqModelClient.this.getEqEntryIndex(new GetEqEntryIndexReq.Builder(0, 2).build()).code != 0) {
                    stopSubTask();
                    ZLogger.w("getEqEntryIndex failed");
                    return;
                }
                waitTaskComplete();
            }
            int iB = EqModelClient.this.b();
            EqModelClient eqModelClient = EqModelClient.this;
            eqModelClient.a((byte) 0, iB, eqModelClient.getEqInfo());
            EqModelClient.this.removeTask(this);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$f.class */
    public class f extends UserTask {
        public f(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            if (EqModelClient.this.isGamingModeSupported()) {
                ZLogger.v(ModelClient.DBG, "queryGamingModeState ...");
                startSubTask(false);
                if (EqModelClient.this.getGamingModeState().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryGamingModeState failed");
                    return;
                }
                waitTaskComplete();
            }
            ZLogger.v(ModelClient.DBG, "queryEqBasicInfo ...");
            startSubTask(false);
            if (EqModelClient.this.sendAppReq(new f.b().a()).code != 0) {
                stopSubTask();
                ZLogger.w("queryEqBasicInfo failed");
                return;
            }
            waitTaskComplete();
            int iB = EqModelClient.this.b();
            EqModelClient eqModelClient = EqModelClient.this;
            eqModelClient.a((byte) 0, iB, eqModelClient.getEqInfo());
            EqModelClient.this.removeTask(this);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$g.class */
    public class g extends UserTask {
        public g(int i, UUID uuid) {
            super(i, uuid);
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            if (EqModelClient.this.isGamingModeSupported()) {
                ZLogger.v(ModelClient.DBG, "queryGamingModeState ...");
                startSubTask(false);
                if (EqModelClient.this.getGamingModeState().code != 0) {
                    stopSubTask();
                    ZLogger.w("queryGamingModeState failed");
                    return;
                }
                waitTaskComplete();
            }
            if (EqModelClient.this.isGamingModeEqFeatureSupported()) {
                ZLogger.v(ModelClient.DBG, String.format(Locale.US, "querySpkEqBasicInfo(eqMode=%d, state, number, active index) ...", 1));
                startSubTask(false);
                if (EqModelClient.this.sendVendorData(new f.b().a(1).a().encode()).code != 0) {
                    stopSubTask();
                    ZLogger.w("queryEqBasicInfo failed");
                    return;
                }
                waitTaskComplete();
            }
            if (EqModelClient.this.isAncEqSupported()) {
                ZLogger.v(ModelClient.DBG, String.format(Locale.US, "querySpkEqBasicInfo(eqMode=%d, state, number, active index) ...", 2));
                startSubTask(false);
                if (EqModelClient.this.sendVendorData(new f.b().a(2).a().encode()).code != 0) {
                    stopSubTask();
                    ZLogger.w("queryEqBasicInfo failed");
                    return;
                }
                waitTaskComplete();
            }
            ZLogger.v(ModelClient.DBG, String.format(Locale.US, "querySpkEqBasicInfo(eqMode=%d, state, number, active index) ...", 0));
            startSubTask(false);
            if (EqModelClient.this.sendVendorData(new f.b().a(0).a().encode()).code != 0) {
                stopSubTask();
                ZLogger.w("queryEqBasicInfo failed");
                return;
            }
            waitTaskComplete();
            int iB = EqModelClient.this.b();
            EqModelClient eqModelClient = EqModelClient.this;
            eqModelClient.a((byte) 0, iB, eqModelClient.getEqInfo());
            EqModelClient.this.removeTask(this);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelClient$h.class */
    public class h extends UserTask {
        public final /* synthetic */ SetEqIndexParameterReq j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(int i, UUID uuid, SetEqIndexParameterReq setEqIndexParameterReq) {
            super(i, uuid);
            this.j = setEqIndexParameterReq;
        }

        @Override // com.realsil.customer.bbpro.internal.UserTask, java.lang.Runnable
        public void run() {
            super.run();
            startSubTask(true);
            if (EqModelClient.this.sendVendorData(this.j.encode(EqModelClient.this.getEqSpecVersion())).code != 0) {
                ZLogger.w("setEqIndexParameter failed");
                stopSubTask();
                EqModelClient.this.a((byte) 5, this.j.getEqType(), this.j.getEqMode(), this.j.getEqIndex());
            } else {
                waitTaskComplete();
                EqModelClient.this.a(getTaskStatus(), this.j.getEqType(), this.j.getEqMode(), this.j.getEqIndex());
            }
            EqModelClient.this.removeTask(this);
        }
    }

    public BeeError getDspParams() {
        return sendVendorData(CommandContract.buildPacket((short) 515));
    }

    public BeeError getDspAudioEQ() {
        return sendVendorData(CommandContract.buildPacket((short) 517));
    }

    public BeeError clearDspAudioEQ() {
        return sendVendorData(CommandContract.buildPacket((short) 518));
    }

    public BeeError queryEqEntryNumber() {
        this.n |= this.m;
        return sendAppReq(new GetEqInfoReq.Builder((byte) 1).build());
    }

    public BeeError getGamingModeState() {
        return sendAppReq(new d.b().a());
    }

    public BeeError setLowLatencyLevel(int i) {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 3588, new byte[]{(byte) (i & 255)}).eventId((short) 3586).build());
    }

    public BeeError getEqEntryIndex(@NonNull GetEqEntryIndexReq getEqEntryIndexReq) {
        return sendVendorData(getEqEntryIndexReq.encode(getEqSpecVersion()));
    }

    public BeeError setEqEntryIndex(@NonNull SetEqEntryIndexReq setEqEntryIndexReq) {
        return sendVendorData(setEqEntryIndexReq.encode(getEqSpecVersion()));
    }

    public BeeError enableEq() {
        return sendAppReq(new b.C0001b().a());
    }

    public BeeError disableEq() {
        return sendAppReq(new a.b().a());
    }

    public BeeError getAudioEqSettingIndex() {
        return sendVendorData(CommandContract.buildPacket((short) 519));
    }

    public BeeError getEqIndexParameter(byte b2) {
        return getEqIndexParameter(new GetEqIndexParameterReq.Builder(0, 0, b2).build());
    }

    public BeeError queryBasicInfo() {
        return queryBasicInfo(0);
    }

    public BeeError querySpkBasicInfo() {
        if (getVendorDeviceInfo() == null) {
            return new BeeError(16);
        }
        int eqSpecVersion = getEqSpecVersion();
        return (eqSpecVersion == 1 || eqSpecVersion == 2 || eqSpecVersion == 3) ? d() : (eqSpecVersion == 4 || eqSpecVersion == 256 || eqSpecVersion == 257 || eqSpecVersion == 258) ? e() : eqSpecVersion == 5 ? g() : eqSpecVersion >= 512 ? f() : c();
    }

    public BeeError queryMicBasicInfo() {
        return getVendorDeviceInfo() == null ? new BeeError(16) : getEqSpecVersion() >= 512 ? queryMicBasicInfoV20() : queryMicBasicInfoV0();
    }

    public BeeError queryMicBasicInfoV0() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        return vendorDeviceInfo == null ? new BeeError(16) : !vendorDeviceInfo.isAptEqSupported() ? new BeeError(49) : execute(new a(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_MIC_BASIC_INFO));
    }

    public BeeError queryMicBasicInfoV20() {
        DeviceInfo vendorDeviceInfo = getVendorDeviceInfo();
        return vendorDeviceInfo == null ? new BeeError(16) : !vendorDeviceInfo.isAptEqSupported() ? new BeeError(49) : execute(new b(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_MIC_BASIC_INFO));
    }

    public BeeError setEqIndexParameter(@NonNull SetEqIndexParameterReq setEqIndexParameterReq) {
        return execute(new h(0, com.realsil.customer.bbpro.equalizer.e.UUID_SET_EQ_INDEX_PARAMETER, setEqIndexParameterReq));
    }

    public BeeError toggleGamingMode() {
        return VendorModelClient.getInstance() == null ? new BeeError(48) : VendorModelClient.getInstance().toggleGamingMode();
    }

    public BeeError resetEqIndex(int i, int i2, int i3) {
        int eqSpecVersion = getEqSpecVersion();
        if (eqSpecVersion >= 512) {
            return sendAppReq(new ResetEqDataReq.Builder(i, i2, i3).build());
        }
        if (i == 1) {
            return getInstance().setEqEntryIndex(new SetEqEntryIndexReq.Builder(i, i2, i3).build());
        }
        if (eqSpecVersion < 4) {
            return new BeeError(49);
        }
        return getInstance().setEqEntryIndex(new SetEqEntryIndexReq.Builder(i, i2, i3).build());
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ byte[] calculateEq(double d2, int i, double[] dArr, double[] dArr2, double[] dArr3) {
        return super.calculateEq(d2, i, dArr, dArr2, dArr3);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ List calculateEqWrapper(double d2, int i, double[] dArr, double[] dArr2, double[] dArr3) {
        return super.calculateEqWrapper(d2, i, dArr, dArr2, dArr3);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ EqWrapper wrapperAudioEq(AudioEq audioEq) {
        return super.wrapperAudioEq(audioEq);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ BeeError queryEqState() {
        return super.queryEqState();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ AudioEq decodeAudioEq(byte b2, @NonNull byte[] bArr) {
        return super.decodeAudioEq(b2, bArr);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ void processDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
        super.processDeviceInfoChanged(deviceInfo, i);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        return super.processEventPacket(transportLayerPacket);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ boolean processAckPacket(AckPacket ackPacket) {
        return super.processAckPacket(ackPacket);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isAncEnabled() {
        return super.isAncEnabled();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isAncEqSupported() {
        return super.isAncEqSupported();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isGamingModeEqFeatureSupported() {
        return super.isGamingModeEqFeatureSupported();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isGamingModeEnabled() {
        return super.isGamingModeEnabled();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isGamingModeSupported() {
        return super.isGamingModeSupported();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ int getEqMechanism() {
        return super.getEqMechanism();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ int getEqSpecVersion() {
        return super.getEqSpecVersion();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isEqEnabled() {
        return super.isEqEnabled();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isEqOnOffSupported() {
        return super.isEqOnOffSupported();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ boolean isEqIndexFeatureSupported() {
        return super.isEqIndexFeatureSupported();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ EqInfo getEqInfo() {
        return super.getEqInfo();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ DspConfig getDspConfig() {
        return super.getDspConfig();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ int getVendorEvent() {
        return super.getVendorEvent();
    }

    @Override // com.realsil.customer.bbpro.equalizer.e, com.realsil.customer.bbpro.internal.ModelClient
    public /* bridge */ /* synthetic */ int getVendorCmd() {
        return super.getVendorCmd();
    }

    public EqModelClient(Context context) {
        super(context);
    }

    public static EqModelClient getInstance() {
        if (o == null) {
            ZLogger.w("please call setup(Context, BeeProManager) first");
        }
        return o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.bbpro.equalizer.EqModelClient>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static void initialize(Context context) {
        if (o == null) {
            ?? r0 = EqModelClient.class;
            synchronized (r0) {
                if (o == null) {
                    o = new EqModelClient(context.getApplicationContext());
                }
                r0 = r0;
            }
        }
    }

    public final BeeError c() {
        return execute(new c(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_BASIC_INFO));
    }

    public final BeeError d() {
        return execute(new d(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_BASIC_INFO));
    }

    public final int b() {
        if (isGamingModeEqFeatureSupported() && isGamingModeEnabled()) {
            return 1;
        }
        return (isAncEqSupported() && isAncEnabled()) ? 2 : 0;
    }

    public final BeeError e() {
        return execute(new e(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_BASIC_INFO));
    }

    public final BeeError f() {
        return execute(new f(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_BASIC_INFO));
    }

    public final BeeError g() {
        return execute(new g(0, com.realsil.customer.bbpro.equalizer.e.UUID_EQ_QUERY_BASIC_INFO));
    }

    public BeeError queryBasicInfo(int i) {
        return i == 0 ? querySpkBasicInfo() : i == 1 ? queryMicBasicInfo() : new BeeError(48);
    }

    public BeeError setEqIndexParameter(int i, int i2, byte b2, byte[] bArr) {
        return setEqIndexParameter(new SetEqIndexParameterReq.Builder(0, i2, b2).mode(i).eqData(bArr).build());
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ byte[] calculateEq(double d2, int i, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
        return super.calculateEq(d2, i, dArr, dArr2, dArr3, iArr);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ List calculateEqWrapper(double d2, int i, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
        return super.calculateEqWrapper(d2, i, dArr, dArr2, dArr3, iArr);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ AudioEq decodeAudioEq(byte b2, byte[] bArr, int i) {
        return super.decodeAudioEq(b2, bArr, i);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ byte[] calculateEq(int i, double[] dArr, double[] dArr2, double[] dArr3) {
        return super.calculateEq(i, dArr, dArr2, dArr3);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ List calculateEqWrapper(AudioEq audioEq) {
        return super.calculateEqWrapper(audioEq);
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ AudioEq decodeAudioEq(@NonNull byte[] bArr) {
        return super.decodeAudioEq(bArr);
    }

    public BeeError getEqIndexParameter(int i, byte b2) {
        return getEqIndexParameter(new GetEqIndexParameterReq.Builder(0, i, b2).build());
    }

    @Override // com.realsil.customer.bbpro.equalizer.e
    public /* bridge */ /* synthetic */ byte[] calculateEq(AudioEq audioEq) {
        return super.calculateEq(audioEq);
    }

    public BeeError getEqIndexParameter(@NonNull GetEqIndexParameterReq getEqIndexParameterReq) {
        return sendVendorData(getEqIndexParameterReq.encode(getEqSpecVersion()));
    }
}
