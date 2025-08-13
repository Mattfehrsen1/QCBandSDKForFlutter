package net.sourceforge.pinyin4j;

import java.io.BufferedInputStream;

/* loaded from: classes2.dex */
class ResourceHelper {
    ResourceHelper() {
    }

    static BufferedInputStream getResourceInputStream(String str) {
        return new BufferedInputStream(ResourceHelper.class.getResourceAsStream(str));
    }
}
