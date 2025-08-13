package com.realsil.sdk.bbpro.f;

import com.realsil.sdk.bbpro.model.KeyMmiSettings;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class a {
    public static List<KeyMmiSettings> a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length > 0) {
            int i = bArr[0] & 255;
            if (bArr.length >= (i * 4) + 1) {
                for (int i2 = 0; i2 < i; i2++) {
                    KeyMmiSettings keyMmiSettings = new KeyMmiSettings();
                    int i3 = i2 * 4;
                    keyMmiSettings.setBud(bArr[i3 + 1]);
                    keyMmiSettings.setScenario(bArr[i3 + 2]);
                    keyMmiSettings.setClickType(bArr[i3 + 3]);
                    keyMmiSettings.setMmiIndex(bArr[i3 + 4]);
                    arrayList.add(keyMmiSettings);
                }
            }
        }
        return arrayList;
    }
}
