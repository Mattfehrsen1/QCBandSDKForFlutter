package com.realsil.customer.audioconnect.localplayback;

import android.text.TextUtils;
import com.realsil.customer.core.logger.ZLogger;
import java.io.File;

/* loaded from: qc_sdk_20250409.aar:libs/rtk-audioconnect-localplayback-1.0.9.jar:com/realsil/customer/audioconnect/localplayback/LocalPlaybackConfiguration.class */
public class LocalPlaybackConfiguration {
    public static final String HEADER_BIN_NAME = "header.bin";
    public static final String NAME_BIN_NAME = "name.bin";
    public static final int PLAY_LIST_MAX_NUM = 16;
    public static volatile LocalPlaybackConfiguration b;
    public String a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.realsil.customer.audioconnect.localplayback.LocalPlaybackConfiguration>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static LocalPlaybackConfiguration getInstance() {
        if (b == null) {
            ?? r0 = LocalPlaybackConfiguration.class;
            synchronized (r0) {
                if (b == null) {
                    b = new LocalPlaybackConfiguration();
                }
                r0 = r0;
            }
        }
        return b;
    }

    public void setCacheDirPath(String str) {
        if (TextUtils.isEmpty(str)) {
            ZLogger.e("set cache dir failed, input parameter can not be null");
            return;
        }
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            ZLogger.e("set cache dir failed, can not create dir in your specified position");
        } else {
            this.a = str;
            ZLogger.i("set cache dir: " + str);
        }
    }

    public String getCacheDirPath() {
        return this.a;
    }
}
