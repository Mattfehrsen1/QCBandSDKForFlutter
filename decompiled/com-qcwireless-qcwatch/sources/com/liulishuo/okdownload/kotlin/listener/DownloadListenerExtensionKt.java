package com.liulishuo.okdownload.kotlin.listener;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.liulishuo.okdownload.core.listener.DownloadListener3;
import com.liulishuo.okdownload.core.listener.DownloadListener4;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadListenerExtension.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aÈ\u0007\u0010\u0000\u001a\u00020\u00012+\b\u0002\u0010\u0002\u001a%\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\u0004\u0018\u0001`\t2R\b\u0002\u0010\n\u001aL\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\u000bj\u0004\u0018\u0001`\u00102g\b\u0002\u0010\u0011\u001aa\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012j\u0004\u0018\u0001`\u00162U\b\u0002\u0010\u0017\u001aO\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012j\u0004\u0018\u0001`\u001c2@\b\u0002\u0010\u001d\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\b\u0018\u00010\u000bj\u0004\u0018\u0001`\u001e2g\b\u0002\u0010\u001f\u001aa\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012j\u0004\u0018\u0001`!2|\b\u0002\u0010\"\u001av\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b\u0018\u00010#j\u0004\u0018\u0001`$2U\b\u0002\u0010%\u001aO\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012j\u0004\u0018\u0001`(2U\b\u0002\u0010)\u001aO\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012j\u0004\u0018\u0001`+2U\b\u0002\u0010,\u001aO\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012j\u0004\u0018\u0001`-2Q\u0010.\u001aM\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u000100¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\b0\u0012j\u0002`2\u001a\n\u00103\u001a\u00020\u0001*\u00020\u0001*â\u0001\u0010\"\"n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b0#2n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b0#*¸\u0001\u0010\u001f\"Y\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\u00122Y\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\u0012*¸\u0001\u0010\u0011\"Y\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b0\u00122Y\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b0\u0012*\u008e\u0001\u0010\n\"D\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\u000b2D\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012%\u0012#\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\u000b*\u0094\u0001\u0010\u0017\"G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\b0\u00122G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\b0\u0012*j\u0010\u001d\"2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\b0\u000b22\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\b0\u000b*O\u0010,\"\u0002`(2G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\b0\u0012*\u0094\u0001\u0010)\"G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\b0\u00122G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\b0\u0012*\u0094\u0001\u0010%\"G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\b0\u00122G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110&¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\b0\u0012*\u0098\u0001\u0010.\"I\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u000100¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\b0\u00122I\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u000100¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\b0\u0012*@\u0010\u0002\"\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00032\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¨\u00064"}, d2 = {"createListener", "Lcom/liulishuo/okdownload/DownloadListener;", "onTaskStart", "Lkotlin/Function1;", "Lcom/liulishuo/okdownload/DownloadTask;", "Lkotlin/ParameterName;", "name", "task", "", "Lcom/liulishuo/okdownload/kotlin/listener/onTaskStart;", "onConnectTrialStart", "Lkotlin/Function2;", "", "", "", "requestHeaderFields", "Lcom/liulishuo/okdownload/kotlin/listener/onConnectTrialStart;", "onConnectTrialEnd", "Lkotlin/Function3;", "", "responseCode", "responseHeaderFields", "Lcom/liulishuo/okdownload/kotlin/listener/onConnectTrialEnd;", "onDownloadFromBeginning", "Lcom/liulishuo/okdownload/core/breakpoint/BreakpointInfo;", "info", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "cause", "Lcom/liulishuo/okdownload/kotlin/listener/onDownloadFromBeginning;", "onDownloadFromBreakpoint", "Lcom/liulishuo/okdownload/kotlin/listener/onDownloadFromBreakpoint;", "onConnectStart", "blockIndex", "Lcom/liulishuo/okdownload/kotlin/listener/onConnectStart;", "onConnectEnd", "Lkotlin/Function4;", "Lcom/liulishuo/okdownload/kotlin/listener/onConnectEnd;", "onFetchStart", "", "contentLength", "Lcom/liulishuo/okdownload/kotlin/listener/onFetchStart;", "onFetchProgress", "increaseBytes", "Lcom/liulishuo/okdownload/kotlin/listener/onFetchProgress;", "onFetchEnd", "Lcom/liulishuo/okdownload/kotlin/listener/onFetchEnd;", "onTaskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "Ljava/lang/Exception;", "realCause", "Lcom/liulishuo/okdownload/kotlin/listener/onTaskEnd;", "switchToExceptProgressListener", "kotlin_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class DownloadListenerExtensionKt {
    public static /* synthetic */ DownloadListener createListener$default(Function1 function1, Function2 function2, Function3 function3, Function3 function32, Function2 function22, Function3 function33, Function4 function4, Function3 function34, Function3 function35, Function3 function36, Function3 function37, int i, Object obj) {
        Function1 function12;
        Function2 function23;
        Function3 function38;
        Function3 function39;
        Function2 function24;
        Function3 function310;
        Function4 function42;
        Function3 function311;
        Function3 function312;
        Function3 function313;
        if ((i & 1) != 0) {
            function12 = null;
        } else {
            function12 = function1;
        }
        if ((i & 2) != 0) {
            function23 = null;
        } else {
            function23 = function2;
        }
        if ((i & 4) != 0) {
            function38 = null;
        } else {
            function38 = function3;
        }
        if ((i & 8) != 0) {
            function39 = null;
        } else {
            function39 = function32;
        }
        if ((i & 16) != 0) {
            function24 = null;
        } else {
            function24 = function22;
        }
        if ((i & 32) != 0) {
            function310 = null;
        } else {
            function310 = function33;
        }
        if ((i & 64) != 0) {
            function42 = null;
        } else {
            function42 = function4;
        }
        if ((i & 128) != 0) {
            function311 = null;
        } else {
            function311 = function34;
        }
        if ((i & 256) != 0) {
            function312 = null;
        } else {
            function312 = function35;
        }
        if ((i & 512) != 0) {
            function313 = null;
        } else {
            function313 = function36;
        }
        return createListener(function12, function23, function38, function39, function24, function310, function42, function311, function312, function313, function37);
    }

    public static final DownloadListener createListener(final Function1<? super DownloadTask, Unit> function1, final Function2<? super DownloadTask, ? super Map<String, ? extends List<String>>, Unit> function2, final Function3<? super DownloadTask, ? super Integer, ? super Map<String, ? extends List<String>>, Unit> function3, final Function3<? super DownloadTask, ? super BreakpointInfo, ? super ResumeFailedCause, Unit> function32, final Function2<? super DownloadTask, ? super BreakpointInfo, Unit> function22, final Function3<? super DownloadTask, ? super Integer, ? super Map<String, ? extends List<String>>, Unit> function33, final Function4<? super DownloadTask, ? super Integer, ? super Integer, ? super Map<String, ? extends List<String>>, Unit> function4, final Function3<? super DownloadTask, ? super Integer, ? super Long, Unit> function34, final Function3<? super DownloadTask, ? super Integer, ? super Long, Unit> function35, final Function3<? super DownloadTask, ? super Integer, ? super Long, Unit> function36, final Function3<? super DownloadTask, ? super EndCause, ? super Exception, Unit> onTaskEnd) {
        Intrinsics.checkParameterIsNotNull(onTaskEnd, "onTaskEnd");
        return new DownloadListener() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.createListener.1
            @Override // com.liulishuo.okdownload.DownloadListener
            public void taskStart(DownloadTask task) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Function1 function12 = function1;
                if (function12 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void connectTrialStart(DownloadTask task, Map<String, List<String>> requestHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(requestHeaderFields, "requestHeaderFields");
                Function2 function23 = function2;
                if (function23 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void connectTrialEnd(DownloadTask task, int responseCode, Map<String, List<String>> responseHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(responseHeaderFields, "responseHeaderFields");
                Function3 function37 = function3;
                if (function37 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void downloadFromBeginning(DownloadTask task, BreakpointInfo info, ResumeFailedCause cause) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(info, "info");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                Function3 function37 = function32;
                if (function37 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void downloadFromBreakpoint(DownloadTask task, BreakpointInfo info) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(info, "info");
                Function2 function23 = function22;
                if (function23 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void connectStart(DownloadTask task, int blockIndex, Map<String, List<String>> requestHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(requestHeaderFields, "requestHeaderFields");
                Function3 function37 = function33;
                if (function37 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void connectEnd(DownloadTask task, int blockIndex, int responseCode, Map<String, List<String>> responseHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(responseHeaderFields, "responseHeaderFields");
                Function4 function42 = function4;
                if (function42 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void fetchStart(DownloadTask task, int blockIndex, long contentLength) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Function3 function37 = function34;
                if (function37 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void fetchProgress(DownloadTask task, int blockIndex, long increaseBytes) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Function3 function37 = function35;
                if (function37 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void fetchEnd(DownloadTask task, int blockIndex, long contentLength) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Function3 function37 = function36;
                if (function37 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void taskEnd(DownloadTask task, EndCause cause, Exception realCause) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                onTaskEnd.invoke(task, cause, realCause);
            }
        };
    }

    public static final DownloadListener switchToExceptProgressListener(final DownloadListener switchToExceptProgressListener) {
        Intrinsics.checkParameterIsNotNull(switchToExceptProgressListener, "$this$switchToExceptProgressListener");
        if (switchToExceptProgressListener instanceof DownloadListener4WithSpeed) {
            return DownloadListener4WithSpeedExtensionKt.createListener4WithSpeed$default(new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    switchToExceptProgressListener.taskStart(it);
                }
            }, new Function3<DownloadTask, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.2
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Map<String, ? extends List<? extends String>> map) {
                    invoke(downloadTask, num.intValue(), (Map<String, ? extends List<String>>) map);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, Map<String, ? extends List<String>> requestHeaderFields) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(requestHeaderFields, "requestHeaderFields");
                    switchToExceptProgressListener.connectStart(task, i, requestHeaderFields);
                }
            }, new Function4<DownloadTask, Integer, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.3
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Integer num2, Map<String, ? extends List<? extends String>> map) {
                    invoke(downloadTask, num.intValue(), num2.intValue(), (Map<String, ? extends List<String>>) map);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, int i2, Map<String, ? extends List<String>> responseHeaderFields) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(responseHeaderFields, "responseHeaderFields");
                    switchToExceptProgressListener.connectEnd(task, i, i2, responseHeaderFields);
                }
            }, new Function4<DownloadTask, BreakpointInfo, Boolean, Listener4SpeedAssistExtend.Listener4SpeedModel, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.4
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, BreakpointInfo breakpointInfo, Boolean bool, Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
                    invoke(downloadTask, breakpointInfo, bool.booleanValue(), listener4SpeedModel);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, BreakpointInfo info, boolean z, Listener4SpeedAssistExtend.Listener4SpeedModel model) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(info, "info");
                    Intrinsics.checkParameterIsNotNull(model, "model");
                    ((DownloadListener4WithSpeed) switchToExceptProgressListener).infoReady(task, info, z, model);
                }
            }, new Function4<DownloadTask, Integer, Long, SpeedCalculator, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.5
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Long l, SpeedCalculator speedCalculator) {
                    invoke(downloadTask, num.intValue(), l.longValue(), speedCalculator);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, long j, SpeedCalculator blockSpeed) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(blockSpeed, "blockSpeed");
                    ((DownloadListener4WithSpeed) switchToExceptProgressListener).progressBlock(task, i, j, blockSpeed);
                }
            }, null, new Function4<DownloadTask, Integer, BlockInfo, SpeedCalculator, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.6
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, BlockInfo blockInfo, SpeedCalculator speedCalculator) {
                    invoke(downloadTask, num.intValue(), blockInfo, speedCalculator);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, BlockInfo info, SpeedCalculator blockSpeed) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(info, "info");
                    Intrinsics.checkParameterIsNotNull(blockSpeed, "blockSpeed");
                    ((DownloadListener4WithSpeed) switchToExceptProgressListener).blockEnd(task, i, info, blockSpeed);
                }
            }, new Function4<DownloadTask, EndCause, Exception, SpeedCalculator, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.7
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, EndCause endCause, Exception exc, SpeedCalculator speedCalculator) {
                    invoke2(downloadTask, endCause, exc, speedCalculator);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, EndCause cause, Exception exc, SpeedCalculator taskSpeed) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(cause, "cause");
                    Intrinsics.checkParameterIsNotNull(taskSpeed, "taskSpeed");
                    ((DownloadListener4WithSpeed) switchToExceptProgressListener).taskEnd(task, cause, exc, taskSpeed);
                }
            }, 32, null);
        }
        if (switchToExceptProgressListener instanceof DownloadListener4) {
            return DownloadListener4ExtensionKt.createListener4$default(new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.8
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    switchToExceptProgressListener.taskStart(it);
                }
            }, new Function3<DownloadTask, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.9
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Map<String, ? extends List<? extends String>> map) {
                    invoke(downloadTask, num.intValue(), (Map<String, ? extends List<String>>) map);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, Map<String, ? extends List<String>> requestHeaderFields) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(requestHeaderFields, "requestHeaderFields");
                    switchToExceptProgressListener.connectStart(task, i, requestHeaderFields);
                }
            }, new Function4<DownloadTask, Integer, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.10
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Integer num2, Map<String, ? extends List<? extends String>> map) {
                    invoke(downloadTask, num.intValue(), num2.intValue(), (Map<String, ? extends List<String>>) map);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, int i2, Map<String, ? extends List<String>> responseHeaderFields) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(responseHeaderFields, "responseHeaderFields");
                    switchToExceptProgressListener.connectEnd(task, i, i2, responseHeaderFields);
                }
            }, new Function4<DownloadTask, BreakpointInfo, Boolean, Listener4Assist.Listener4Model, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.11
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, BreakpointInfo breakpointInfo, Boolean bool, Listener4Assist.Listener4Model listener4Model) {
                    invoke(downloadTask, breakpointInfo, bool.booleanValue(), listener4Model);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, BreakpointInfo info, boolean z, Listener4Assist.Listener4Model model) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(info, "info");
                    Intrinsics.checkParameterIsNotNull(model, "model");
                    ((DownloadListener4) switchToExceptProgressListener).infoReady(task, info, z, model);
                }
            }, new Function3<DownloadTask, Integer, Long, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.12
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Long l) {
                    invoke(downloadTask, num.intValue(), l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, long j) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    ((DownloadListener4) switchToExceptProgressListener).progressBlock(task, i, j);
                }
            }, null, new Function3<DownloadTask, Integer, BlockInfo, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.13
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, BlockInfo blockInfo) {
                    invoke(downloadTask, num.intValue(), blockInfo);
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, BlockInfo info) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(info, "info");
                    ((DownloadListener4) switchToExceptProgressListener).blockEnd(task, i, info);
                }
            }, new Function4<DownloadTask, EndCause, Exception, Listener4Assist.Listener4Model, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.14
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Assist.Listener4Model listener4Model) {
                    invoke2(downloadTask, endCause, exc, listener4Model);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, EndCause cause, Exception exc, Listener4Assist.Listener4Model model) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(cause, "cause");
                    Intrinsics.checkParameterIsNotNull(model, "model");
                    ((DownloadListener4) switchToExceptProgressListener).taskEnd(task, cause, exc, model);
                }
            }, 32, null);
        }
        if (switchToExceptProgressListener instanceof DownloadListener3) {
            return DownloadListener3ExtensionKt.createListener3$default(new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.15
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    switchToExceptProgressListener.taskStart(it);
                }
            }, new Function4<DownloadTask, Integer, Long, Long, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.16
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Long l, Long l2) {
                    invoke(downloadTask, num.intValue(), l.longValue(), l2.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, long j, long j2) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    ((DownloadListener3) switchToExceptProgressListener).connected(task, i, j, j2);
                }
            }, null, new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.17
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    switchToExceptProgressListener.taskEnd(it, EndCause.COMPLETED, null);
                }
            }, new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.18
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    switchToExceptProgressListener.taskEnd(it, EndCause.CANCELED, null);
                }
            }, new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.19
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    switchToExceptProgressListener.taskEnd(it, EndCause.SAME_TASK_BUSY, null);
                }
            }, new Function2<DownloadTask, ResumeFailedCause, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.20
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, ResumeFailedCause resumeFailedCause) {
                    invoke2(downloadTask, resumeFailedCause);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, ResumeFailedCause cause) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(cause, "cause");
                    ((DownloadListener3) switchToExceptProgressListener).retry(task, cause);
                }
            }, new Function2<DownloadTask, Exception, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.21
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Exception exc) {
                    invoke2(downloadTask, exc);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, Exception e) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    switchToExceptProgressListener.taskEnd(task, EndCause.ERROR, e);
                }
            }, null, 260, null);
        }
        if (switchToExceptProgressListener instanceof DownloadListener1) {
            return DownloadListener1ExtensionKt.createListener1$default(new Function2<DownloadTask, Listener1Assist.Listener1Model, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.22
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Listener1Assist.Listener1Model listener1Model) {
                    invoke2(downloadTask, listener1Model);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, Listener1Assist.Listener1Model model) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(model, "model");
                    ((DownloadListener1) switchToExceptProgressListener).taskStart(task, model);
                }
            }, new Function2<DownloadTask, ResumeFailedCause, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.23
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, ResumeFailedCause resumeFailedCause) {
                    invoke2(downloadTask, resumeFailedCause);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, ResumeFailedCause cause) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(cause, "cause");
                    ((DownloadListener1) switchToExceptProgressListener).retry(task, cause);
                }
            }, new Function4<DownloadTask, Integer, Long, Long, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.24
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Long l, Long l2) {
                    invoke(downloadTask, num.intValue(), l.longValue(), l2.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DownloadTask task, int i, long j, long j2) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    ((DownloadListener1) switchToExceptProgressListener).connected(task, i, j, j2);
                }
            }, null, new Function4<DownloadTask, EndCause, Exception, Listener1Assist.Listener1Model, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.25
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener1Assist.Listener1Model listener1Model) {
                    invoke2(downloadTask, endCause, exc, listener1Model);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask task, EndCause cause, Exception exc, Listener1Assist.Listener1Model model) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    Intrinsics.checkParameterIsNotNull(cause, "cause");
                    Intrinsics.checkParameterIsNotNull(model, "model");
                    ((DownloadListener1) switchToExceptProgressListener).taskEnd(task, cause, exc, model);
                }
            }, 8, null);
        }
        return switchToExceptProgressListener instanceof DownloadListener2 ? switchToExceptProgressListener : createListener$default(new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.26
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                invoke2(downloadTask);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadTask it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                switchToExceptProgressListener.taskStart(it);
            }
        }, new Function2<DownloadTask, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.27
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Map<String, ? extends List<? extends String>> map) {
                invoke2(downloadTask, (Map<String, ? extends List<String>>) map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadTask task, Map<String, ? extends List<String>> requestFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(requestFields, "requestFields");
                switchToExceptProgressListener.connectTrialStart(task, requestFields);
            }
        }, new Function3<DownloadTask, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.28
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Map<String, ? extends List<? extends String>> map) {
                invoke(downloadTask, num.intValue(), (Map<String, ? extends List<String>>) map);
                return Unit.INSTANCE;
            }

            public final void invoke(DownloadTask task, int i, Map<String, ? extends List<String>> responseHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(responseHeaderFields, "responseHeaderFields");
                switchToExceptProgressListener.connectTrialEnd(task, i, responseHeaderFields);
            }
        }, new Function3<DownloadTask, BreakpointInfo, ResumeFailedCause, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.29
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
                invoke2(downloadTask, breakpointInfo, resumeFailedCause);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadTask task, BreakpointInfo info, ResumeFailedCause cause) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(info, "info");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                switchToExceptProgressListener.downloadFromBeginning(task, info, cause);
            }
        }, new Function2<DownloadTask, BreakpointInfo, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.30
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
                invoke2(downloadTask, breakpointInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadTask task, BreakpointInfo info) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(info, "info");
                switchToExceptProgressListener.downloadFromBreakpoint(task, info);
            }
        }, new Function3<DownloadTask, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.31
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Map<String, ? extends List<? extends String>> map) {
                invoke(downloadTask, num.intValue(), (Map<String, ? extends List<String>>) map);
                return Unit.INSTANCE;
            }

            public final void invoke(DownloadTask task, int i, Map<String, ? extends List<String>> requestHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(requestHeaderFields, "requestHeaderFields");
                switchToExceptProgressListener.connectStart(task, i, requestHeaderFields);
            }
        }, new Function4<DownloadTask, Integer, Integer, Map<String, ? extends List<? extends String>>, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.32
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Integer num2, Map<String, ? extends List<? extends String>> map) {
                invoke(downloadTask, num.intValue(), num2.intValue(), (Map<String, ? extends List<String>>) map);
                return Unit.INSTANCE;
            }

            public final void invoke(DownloadTask task, int i, int i2, Map<String, ? extends List<String>> responseHeaderFields) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(responseHeaderFields, "responseHeaderFields");
                switchToExceptProgressListener.connectEnd(task, i, i2, responseHeaderFields);
            }
        }, new Function3<DownloadTask, Integer, Long, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.33
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Long l) {
                invoke(downloadTask, num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(DownloadTask task, int i, long j) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                switchToExceptProgressListener.fetchStart(task, i, j);
            }
        }, null, new Function3<DownloadTask, Integer, Long, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.34
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, Integer num, Long l) {
                invoke(downloadTask, num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(DownloadTask task, int i, long j) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                switchToExceptProgressListener.fetchEnd(task, i, j);
            }
        }, new Function3<DownloadTask, EndCause, Exception, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListenerExtensionKt.switchToExceptProgressListener.35
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, EndCause endCause, Exception exc) {
                invoke2(downloadTask, endCause, exc);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadTask task, EndCause cause, Exception exc) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                switchToExceptProgressListener.taskEnd(task, cause, exc);
            }
        }, 256, null);
    }
}
