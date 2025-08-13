package com.realsil.sdk.bbpro.a;

import com.realsil.sdk.bbpro.anc.AncScenarioGroupInfo;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;

/* loaded from: classes3.dex */
public final class a {
    public AncScenarioGroupInfo a;

    public a(AncScenarioGroupInfo ancScenarioGroupInfo) {
        this.a = ancScenarioGroupInfo;
    }

    public static a a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid packet");
            return null;
        }
        AncScenarioGroupInfo ancScenarioGroupInfoBuilder = AncScenarioGroupInfo.builder(bArr);
        if (ancScenarioGroupInfoBuilder == null) {
            return null;
        }
        return new a(ancScenarioGroupInfoBuilder);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetAncScenarioChooseInfoRsp {");
        sb.append("\n\tgroupNum=" + this.a.getGroupNum());
        sb.append("\n\tgroupScenario=" + DataConverter.bytes2Hex(this.a.getGroupScenario()));
        sb.append("\n}");
        return sb.toString();
    }

    public AncScenarioGroupInfo a() {
        return this.a;
    }
}
