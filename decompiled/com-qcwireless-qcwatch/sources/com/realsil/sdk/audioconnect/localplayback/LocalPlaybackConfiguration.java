package com.realsil.sdk.audioconnect.localplayback;

import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.File;

/* loaded from: classes3.dex */
public class LocalPlaybackConfiguration {
    public static final String HEADER_BIN_NAME = "header.bin";
    public static final String NAME_BIN_NAME = "name.bin";
    public static final int PLAY_LIST_MAX_NUM = 16;
    public static volatile LocalPlaybackConfiguration b;
    public String a;

    public static LocalPlaybackConfiguration getInstance() {
        if (b == null) {
            synchronized (LocalPlaybackConfiguration.class) {
                if (b == null) {
                    b = new LocalPlaybackConfiguration();
                }
            }
        }
        return b;
    }

    public String getCacheDirPath() {
        return this.a;
    }

    public void setCacheDirPath(String str) {
        if (TextUtils.isEmpty(str)) {
            ZLogger.e("set cache dir failed, input parameter can not be null");
            return;
        }
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            ZLogger.e("set cache dir failed, can not create dir in your specified position");
            return;
        }
        this.a = str;
        ZLogger.i("set cache dir: " + str);
    }
}
