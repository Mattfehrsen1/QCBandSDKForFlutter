package com.realsil.customer.bbpro.g;

import com.realsil.customer.bbpro.llapt.LlAptScenarioGroupInfo;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/g/b.class */
public final class b {
    public LlAptScenarioGroupInfo a;

    public b(LlAptScenarioGroupInfo llAptScenarioGroupInfo) {
        this.a = llAptScenarioGroupInfo;
    }

    public static b a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.d("invalid packet");
            return null;
        }
        LlAptScenarioGroupInfo llAptScenarioGroupInfoBuilder = LlAptScenarioGroupInfo.builder(bArr);
        if (llAptScenarioGroupInfoBuilder == null) {
            return null;
        }
        return new b(llAptScenarioGroupInfoBuilder);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetLlAptScenarioChooseInfoRsp {");
        sb.append("\n\tgroupNum=" + this.a.getGroupNum());
        sb.append("\n\tgroupScenario=" + DataConverter.bytes2Hex(this.a.getGroupSenario()));
        sb.append("\n}");
        return sb.toString();
    }

    public LlAptScenarioGroupInfo a() {
        return this.a;
    }
}
