package org.koin.android.logger;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.logger.LoggerKt;

/* compiled from: AndroidLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0002J\u001c\u0010\n\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016¨\u0006\u000b"}, d2 = {"Lorg/koin/android/logger/AndroidLogger;", "Lorg/koin/core/logger/Logger;", "level", "Lorg/koin/core/logger/Level;", "(Lorg/koin/core/logger/Level;)V", "LogOnLevel", "", NotificationCompat.CATEGORY_MESSAGE, "", "Lorg/koin/core/logger/MESSAGE;", "log", "koin-android_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class AndroidLogger extends Logger {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Level.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Level.DEBUG.ordinal()] = 1;
            iArr[Level.INFO.ordinal()] = 2;
            iArr[Level.ERROR.ordinal()] = 3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AndroidLogger() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidLogger(Level level) {
        super(level);
        Intrinsics.checkParameterIsNotNull(level, "level");
    }

    public /* synthetic */ AndroidLogger(Level level, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Level.INFO : level);
    }

    @Override // org.koin.core.logger.Logger
    public void log(Level level, String msg) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (getLevel().compareTo(level) <= 0) {
            LogOnLevel(msg);
        }
    }

    private final void LogOnLevel(String msg) {
        int i = WhenMappings.$EnumSwitchMapping$0[getLevel().ordinal()];
        if (i == 1) {
            Log.d(LoggerKt.KOIN_TAG, msg);
        } else if (i == 2) {
            Log.i(LoggerKt.KOIN_TAG, msg);
        } else {
            if (i != 3) {
                return;
            }
            Log.e(LoggerKt.KOIN_TAG, msg);
        }
    }
}
