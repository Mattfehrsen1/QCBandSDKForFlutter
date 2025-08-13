package com.qcwireless.qcwatch.base.pref;

import android.content.Context;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserConfig.kt */
@Metadata(d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b_\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010\b\n\u0003\bÍ\u0001\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 Ú\u00022\u00020\u0001:\u0002Ú\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010×\u0002\u001a\u00030Ø\u0002J\b\u0010Ù\u0002\u001a\u00030Ø\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\u001a\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u001a\u0010\u001d\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\u001a\u0010 \u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\u001a\u0010#\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\u001a\u0010&\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\nR\u001a\u0010)\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u000e\"\u0004\b+\u0010\u0010R\u001a\u0010,\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u000e\"\u0004\b.\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u0010\u0004R\u001a\u00102\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\b\"\u0004\b4\u0010\nR\u001a\u00105\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\b\"\u0004\b7\u0010\nR\u001a\u00108\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u000e\"\u0004\b:\u0010\u0010R\u001a\u0010;\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u000e\"\u0004\b=\u0010\u0010R\u001a\u0010>\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\b\"\u0004\b@\u0010\nR\u001a\u0010A\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u000e\"\u0004\bC\u0010\u0010R\u001a\u0010D\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\u000e\"\u0004\bF\u0010\u0010R\u001a\u0010G\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u000e\"\u0004\bI\u0010\u0010R\u001a\u0010J\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u000e\"\u0004\bL\u0010\u0010R\u001a\u0010M\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010\b\"\u0004\bO\u0010\nR\u001a\u0010P\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010\b\"\u0004\bR\u0010\nR\u001a\u0010S\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\b\"\u0004\bU\u0010\nR\u001a\u0010V\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010\b\"\u0004\bX\u0010\nR\u001a\u0010Y\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\b\"\u0004\b[\u0010\nR\u001a\u0010\\\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010\b\"\u0004\b^\u0010\nR\u001a\u0010_\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\u000e\"\u0004\ba\u0010\u0010R\u001a\u0010b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\u000e\"\u0004\bd\u0010\u0010R\u001a\u0010e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\b\"\u0004\bg\u0010\nR\u001a\u0010h\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u000e\"\u0004\bj\u0010\u0010R\u001a\u0010k\u001a\u00020lX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR\u001a\u0010q\u001a\u00020lX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010n\"\u0004\bs\u0010pR\u001a\u0010t\u001a\u00020lX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010n\"\u0004\bv\u0010pR\u001a\u0010w\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\b\"\u0004\by\u0010\nR\u001a\u0010z\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\b\"\u0004\b|\u0010\nR\u001c\u0010}\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\u000e\"\u0004\b\u007f\u0010\u0010R\u001d\u0010\u0080\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010\b\"\u0005\b\u0082\u0001\u0010\nR\u001d\u0010\u0083\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010\b\"\u0005\b\u0085\u0001\u0010\nR\u001d\u0010\u0086\u0001\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010\u000e\"\u0005\b\u0088\u0001\u0010\u0010R \u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001d\u0010\u008f\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0001\u0010\b\"\u0005\b\u0090\u0001\u0010\nR\u001d\u0010\u0091\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0001\u0010\b\"\u0005\b\u0093\u0001\u0010\nR\u001d\u0010\u0094\u0001\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0001\u0010\u000e\"\u0005\b\u0096\u0001\u0010\u0010R \u0010\u0097\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0098\u0001\u0010\u008c\u0001\"\u0006\b\u0099\u0001\u0010\u008e\u0001R\u001d\u0010\u009a\u0001\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010n\"\u0005\b\u009c\u0001\u0010pR\u001d\u0010\u009d\u0001\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009e\u0001\u0010\u000e\"\u0005\b\u009f\u0001\u0010\u0010R\u001d\u0010 \u0001\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0001\u0010n\"\u0005\b¢\u0001\u0010pR\u001f\u0010£\u0001\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0001\u0010\u000e\"\u0005\b¥\u0001\u0010\u0010R \u0010¦\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b§\u0001\u0010\u008c\u0001\"\u0006\b¨\u0001\u0010\u008e\u0001R \u0010©\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bª\u0001\u0010\u008c\u0001\"\u0006\b«\u0001\u0010\u008e\u0001R\u001d\u0010¬\u0001\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0001\u0010n\"\u0005\b®\u0001\u0010pR\u001d\u0010¯\u0001\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b°\u0001\u0010n\"\u0005\b±\u0001\u0010pR\u001d\u0010²\u0001\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b³\u0001\u0010n\"\u0005\b´\u0001\u0010pR\u001d\u0010µ\u0001\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¶\u0001\u0010n\"\u0005\b·\u0001\u0010pR\u001d\u0010¸\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¹\u0001\u0010\b\"\u0005\bº\u0001\u0010\nR\u001d\u0010»\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¼\u0001\u0010\b\"\u0005\b½\u0001\u0010\nR\u001d\u0010¾\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¿\u0001\u0010\b\"\u0005\bÀ\u0001\u0010\nR \u0010Á\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÂ\u0001\u0010\u008c\u0001\"\u0006\bÃ\u0001\u0010\u008e\u0001R \u0010Ä\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÅ\u0001\u0010\u008c\u0001\"\u0006\bÆ\u0001\u0010\u008e\u0001R\u001d\u0010Ç\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÈ\u0001\u0010\b\"\u0005\bÉ\u0001\u0010\nR\u001d\u0010Ê\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bË\u0001\u0010\b\"\u0005\bÌ\u0001\u0010\nR \u0010Í\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÎ\u0001\u0010\u008c\u0001\"\u0006\bÏ\u0001\u0010\u008e\u0001R\u001d\u0010Ð\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÑ\u0001\u0010\b\"\u0005\bÒ\u0001\u0010\nR\u001d\u0010Ó\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÔ\u0001\u0010\b\"\u0005\bÕ\u0001\u0010\nR\u001d\u0010Ö\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b×\u0001\u0010\b\"\u0005\bØ\u0001\u0010\nR\u001d\u0010Ù\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÚ\u0001\u0010\b\"\u0005\bÛ\u0001\u0010\nR\u001d\u0010Ü\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÝ\u0001\u0010\b\"\u0005\bÞ\u0001\u0010\nR \u0010ß\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bà\u0001\u0010\u008c\u0001\"\u0006\bá\u0001\u0010\u008e\u0001R\u001d\u0010â\u0001\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bã\u0001\u0010\u000e\"\u0005\bä\u0001\u0010\u0010R\u001d\u0010å\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bæ\u0001\u0010\b\"\u0005\bç\u0001\u0010\nR\u001d\u0010è\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bé\u0001\u0010\b\"\u0005\bê\u0001\u0010\nR \u0010ë\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bì\u0001\u0010\u008c\u0001\"\u0006\bí\u0001\u0010\u008e\u0001R\u001d\u0010î\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bï\u0001\u0010\b\"\u0005\bð\u0001\u0010\nR\u001d\u0010ñ\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bò\u0001\u0010\b\"\u0005\bó\u0001\u0010\nR\u001d\u0010ô\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bõ\u0001\u0010\b\"\u0005\bö\u0001\u0010\nR \u0010÷\u0001\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bø\u0001\u0010\u008c\u0001\"\u0006\bù\u0001\u0010\u008e\u0001R\u001d\u0010ú\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bû\u0001\u0010\b\"\u0005\bü\u0001\u0010\nR\u001d\u0010ý\u0001\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bþ\u0001\u0010\b\"\u0005\bÿ\u0001\u0010\nR \u0010\u0080\u0002\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0081\u0002\u0010\u008c\u0001\"\u0006\b\u0082\u0002\u0010\u008e\u0001R\u001d\u0010\u0083\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0002\u0010\u000e\"\u0005\b\u0085\u0002\u0010\u0010R\u001d\u0010\u0086\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0002\u0010\b\"\u0005\b\u0088\u0002\u0010\nR\u001d\u0010\u0089\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0002\u0010\b\"\u0005\b\u008b\u0002\u0010\nR\u001d\u0010\u008c\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0002\u0010\u000e\"\u0005\b\u008e\u0002\u0010\u0010R\u001d\u0010\u008f\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0002\u0010\b\"\u0005\b\u0091\u0002\u0010\nR\u001d\u0010\u0092\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0002\u0010\b\"\u0005\b\u0094\u0002\u0010\nR\u001d\u0010\u0095\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0096\u0002\u0010\b\"\u0005\b\u0097\u0002\u0010\nR\u001d\u0010\u0098\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0002\u0010\u000e\"\u0005\b\u009a\u0002\u0010\u0010R\u001d\u0010\u009b\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009c\u0002\u0010\b\"\u0005\b\u009d\u0002\u0010\nR\u001d\u0010\u009e\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0002\u0010\b\"\u0005\b \u0002\u0010\nR \u0010¡\u0002\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¢\u0002\u0010\u008c\u0001\"\u0006\b£\u0002\u0010\u008e\u0001R\u001d\u0010¤\u0002\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0002\u0010n\"\u0005\b¦\u0002\u0010pR\u001d\u0010§\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¨\u0002\u0010\u000e\"\u0005\b©\u0002\u0010\u0010R\u001d\u0010ª\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b«\u0002\u0010\u000e\"\u0005\b¬\u0002\u0010\u0010R\u001d\u0010\u00ad\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b®\u0002\u0010\u000e\"\u0005\b¯\u0002\u0010\u0010R\u001d\u0010°\u0002\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b±\u0002\u0010n\"\u0005\b²\u0002\u0010pR \u0010³\u0002\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b´\u0002\u0010\u008c\u0001\"\u0006\bµ\u0002\u0010\u008e\u0001R\u001d\u0010¶\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b·\u0002\u0010\b\"\u0005\b¸\u0002\u0010\nR\u001d\u0010¹\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bº\u0002\u0010\b\"\u0005\b»\u0002\u0010\nR\u001d\u0010¼\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b½\u0002\u0010\b\"\u0005\b¾\u0002\u0010\nR\u001d\u0010¿\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÀ\u0002\u0010\b\"\u0005\bÁ\u0002\u0010\nR\u001d\u0010Â\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÃ\u0002\u0010\b\"\u0005\bÄ\u0002\u0010\nR\u001d\u0010Å\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÆ\u0002\u0010\b\"\u0005\bÇ\u0002\u0010\nR\u001d\u0010È\u0002\u001a\u00020lX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÉ\u0002\u0010n\"\u0005\bÊ\u0002\u0010pR\u001d\u0010Ë\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÌ\u0002\u0010\u000e\"\u0005\bÍ\u0002\u0010\u0010R \u0010Î\u0002\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÏ\u0002\u0010\u008c\u0001\"\u0006\bÐ\u0002\u0010\u008e\u0001R \u0010Ñ\u0002\u001a\u00030\u008a\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÒ\u0002\u0010\u008c\u0001\"\u0006\bÓ\u0002\u0010\u008e\u0001R\u001d\u0010Ô\u0002\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÕ\u0002\u0010\b\"\u0005\bÖ\u0002\u0010\n¨\u0006Û\u0002"}, d2 = {"Lcom/qcwireless/qcwatch/base/pref/UserConfig;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "albumSupport", "", "getAlbumSupport", "()Z", "setAlbumSupport", "(Z)V", "appScanKey", "", "getAppScanKey", "()Ljava/lang/String;", "setAppScanKey", "(Ljava/lang/String;)V", "appUpgradeTime", "getAppUpgradeTime", "setAppUpgradeTime", "battery", "getBattery", "setBattery", "batteryLow", "getBatteryLow", "setBatteryLow", "batteryWarmingOpen", "getBatteryWarmingOpen", "setBatteryWarmingOpen", "bindDevice", "getBindDevice", "setBindDevice", "bloodSugarSupport", "getBloodSugarSupport", "setBloodSugarSupport", "callPushEnable", "getCallPushEnable", "setCallPushEnable", "chinaApp", "getChinaApp", "setChinaApp", "classicBluetoothMac", "getClassicBluetoothMac", "setClassicBluetoothMac", "contactId", "getContactId", "setContactId", "getContext", "()Landroid/content/Context;", "setContext", "csChatLogin", "getCsChatLogin", "setCsChatLogin", "development", "getDevelopment", "setDevelopment", "deviceAddress", "getDeviceAddress", "setDeviceAddress", "deviceAddressNoClear", "getDeviceAddressNoClear", "setDeviceAddressNoClear", "deviceAvatarSupport", "getDeviceAvatarSupport", "setDeviceAvatarSupport", "deviceFunctionList", "getDeviceFunctionList", "setDeviceFunctionList", "deviceModel", "getDeviceModel", "setDeviceModel", "deviceName", "getDeviceName", "setDeviceName", "deviceNameNoClear", "getDeviceNameNoClear", "setDeviceNameNoClear", "deviceNotScreen", "getDeviceNotScreen", "setDeviceNotScreen", "deviceThemeSupport", "getDeviceThemeSupport", "setDeviceThemeSupport", "deviceWallpaperSupport", "getDeviceWallpaperSupport", "setDeviceWallpaperSupport", "disturbFlag", "getDisturbFlag", "setDisturbFlag", "ebookSupport", "getEbookSupport", "setEbookSupport", "firstUse", "getFirstUse", "setFirstUse", "fmVersion", "getFmVersion", "setFmVersion", "forwardPhoneNumber", "getForwardPhoneNumber", "setForwardPhoneNumber", "googleFit", "getGoogleFit", "setGoogleFit", "googleFitLastInfo", "getGoogleFitLastInfo", "setGoogleFitLastInfo", "gpsLocationTime", "", "getGpsLocationTime", "()J", "setGpsLocationTime", "(J)V", "gpsLocationTimeNew", "getGpsLocationTimeNew", "setGpsLocationTimeNew", "gpsPermission", "getGpsPermission", "setGpsPermission", "gpsSupport", "getGpsSupport", "setGpsSupport", "gpsWarmingDialog", "getGpsWarmingDialog", "setGpsWarmingDialog", "healthyModel", "getHealthyModel", "setHealthyModel", "heartSupport", "getHeartSupport", "setHeartSupport", "hrvSupport", "getHrvSupport", "setHrvSupport", "hwVersion", "getHwVersion", "setHwVersion", "indexApiUpdateTime", "", "getIndexApiUpdateTime", "()I", "setIndexApiUpdateTime", "(I)V", "isDebug", "setDebug", "jieLiMusic", "getJieLiMusic", "setJieLiMusic", "languageCurr", "getLanguageCurr", "setLanguageCurr", "lastAiAnalysisScore", "getLastAiAnalysisScore", "setLastAiAnalysisScore", "lastAnalyzeAiTime", "getLastAnalyzeAiTime", "setLastAnalyzeAiTime", "lastAnalyzeResultJson", "getLastAnalyzeResultJson", "setLastAnalyzeResultJson", "lastCollectionTime", "getLastCollectionTime", "setLastCollectionTime", "lastDeviceAddress", "getLastDeviceAddress", "setLastDeviceAddress", "lastLocationTime", "getLastLocationTime", "setLastLocationTime", "lastStep", "getLastStep", "setLastStep", "lastSyncFromServerTime", "getLastSyncFromServerTime", "setLastSyncFromServerTime", "lastSyncTodaySteps", "getLastSyncTodaySteps", "setLastSyncTodaySteps", "lastTenMinSyncTime", "getLastTenMinSyncTime", "setLastTenMinSyncTime", "lastUserPhotoModifyTime", "getLastUserPhotoModifyTime", "setLastUserPhotoModifyTime", "loginStatus", "getLoginStatus", "setLoginStatus", "lyricsSupport", "getLyricsSupport", "setLyricsSupport", "manualDND", "getManualDND", "setManualDND", "maxContacts", "getMaxContacts", "setMaxContacts", "maxWatchFace", "getMaxWatchFace", "setMaxWatchFace", "menstruationSetting", "getMenstruationSetting", "setMenstruationSetting", "messagePushEnable", "getMessagePushEnable", "setMessagePushEnable", "messagePushSupport", "getMessagePushSupport", "setMessagePushSupport", "metric", "getMetric", "setMetric", "musicSupport", "getMusicSupport", "setMusicSupport", "newSleepProtocol", "getNewSleepProtocol", "setNewSleepProtocol", "notSupportTakePhoto", "getNotSupportTakePhoto", "setNotSupportTakePhoto", "notificationGoTo", "getNotificationGoTo", "setNotificationGoTo", "notificationTime", "getNotificationTime", "setNotificationTime", "oneKeySupport", "getOneKeySupport", "setOneKeySupport", "otherSoftwarePush", "getOtherSoftwarePush", "setOtherSoftwarePush", "permissionDescFlag", "getPermissionDescFlag", "setPermissionDescFlag", "pictureUpdateTime", "getPictureUpdateTime", "setPictureUpdateTime", "pressureSupport", "getPressureSupport", "setPressureSupport", "roundScreen", "getRoundScreen", "setRoundScreen", "rtkMcuSupport", "getRtkMcuSupport", "setRtkMcuSupport", "serverMarketSize", "getServerMarketSize", "setServerMarketSize", "showBindGuideDialog", "getShowBindGuideDialog", "setShowBindGuideDialog", "showPlate", "getShowPlate", "setShowPlate", "skinType", "getSkinType", "setSkinType", "skusGoogle", "getSkusGoogle", "setSkusGoogle", "sleepSupport", "getSleepSupport", "setSleepSupport", "smsPushEnable", "getSmsPushEnable", "setSmsPushEnable", "smsQuickText", "getSmsQuickText", "setSmsQuickText", "support4G", "getSupport4G", "setSupport4G", "supportAiAnalyze", "getSupportAiAnalyze", "setSupportAiAnalyze", "supportContact", "getSupportContact", "setSupportContact", "supportCs", "getSupportCs", "setSupportCs", "supportRem", "getSupportRem", "setSupportRem", "temperature", "getTemperature", "setTemperature", "travelModel", "getTravelModel", "setTravelModel", "uid", "getUid", "setUid", "userEmail", "getUserEmail", "setUserEmail", "userPwd", "getUserPwd", "setUserPwd", "userToken", "getUserToken", "setUserToken", "wakeTime", "getWakeTime", "setWakeTime", "wallpaperApiUpdateTime", "getWallpaperApiUpdateTime", "setWallpaperApiUpdateTime", "warmingAutoStart", "getWarmingAutoStart", "setWarmingAutoStart", "warmingBatteryAllow", "getWarmingBatteryAllow", "setWarmingBatteryAllow", "warmingBatteryWhite", "getWarmingBatteryWhite", "setWarmingBatteryWhite", "warmingLock", "getWarmingLock", "setWarmingLock", "warmingNotification", "getWarmingNotification", "setWarmingNotification", "watchFaceApiFlag", "getWatchFaceApiFlag", "setWatchFaceApiFlag", "watchFaceMarketVersion", "getWatchFaceMarketVersion", "setWatchFaceMarketVersion", "weatherInfo", "getWeatherInfo", "setWeatherInfo", "weatherLastTime", "getWeatherLastTime", "setWeatherLastTime", "weatherToDeviceLastTime", "getWeatherToDeviceLastTime", "setWeatherToDeviceLastTime", "wechatSupport", "getWechatSupport", "setWechatSupport", "clearAll", "", "save", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static UserConfig instance;
    private boolean albumSupport;
    private String appScanKey;
    private String appUpgradeTime;
    private String battery;
    private boolean batteryLow;
    private boolean batteryWarmingOpen;
    private boolean bindDevice;
    private boolean bloodSugarSupport;
    private boolean callPushEnable;
    private boolean chinaApp;
    private String classicBluetoothMac;
    private String contactId;
    private Context context;
    private boolean csChatLogin;
    private boolean development;
    private String deviceAddress;
    private String deviceAddressNoClear;
    private boolean deviceAvatarSupport;
    private String deviceFunctionList;
    private String deviceModel;
    private String deviceName;
    private String deviceNameNoClear;
    private boolean deviceNotScreen;
    private boolean deviceThemeSupport;
    private boolean deviceWallpaperSupport;
    private boolean disturbFlag;
    private boolean ebookSupport;
    private boolean firstUse;
    private String fmVersion;
    private String forwardPhoneNumber;
    private boolean googleFit;
    private String googleFitLastInfo;
    private long gpsLocationTime;
    private long gpsLocationTimeNew;
    private long gpsPermission;
    private boolean gpsSupport;
    private boolean gpsWarmingDialog;
    private String healthyModel;
    private boolean heartSupport;
    private boolean hrvSupport;
    private String hwVersion;
    private int indexApiUpdateTime;
    private boolean isDebug;
    private boolean jieLiMusic;
    private String languageCurr;
    private int lastAiAnalysisScore;
    private long lastAnalyzeAiTime;
    private String lastAnalyzeResultJson;
    private long lastCollectionTime;
    private String lastDeviceAddress;
    private int lastLocationTime;
    private int lastStep;
    private long lastSyncFromServerTime;
    private long lastSyncTodaySteps;
    private long lastTenMinSyncTime;
    private long lastUserPhotoModifyTime;
    private boolean loginStatus;
    private boolean lyricsSupport;
    private boolean manualDND;
    private int maxContacts;
    private int maxWatchFace;
    private boolean menstruationSetting;
    private boolean messagePushEnable;
    private int messagePushSupport;
    private boolean metric;
    private boolean musicSupport;
    private boolean newSleepProtocol;
    private boolean notSupportTakePhoto;
    private boolean notificationGoTo;
    private int notificationTime;
    private String oneKeySupport;
    private boolean otherSoftwarePush;
    private boolean permissionDescFlag;
    private int pictureUpdateTime;
    private boolean pressureSupport;
    private boolean roundScreen;
    private boolean rtkMcuSupport;
    private int serverMarketSize;
    private boolean showBindGuideDialog;
    private boolean showPlate;
    private int skinType;
    private String skusGoogle;
    private boolean sleepSupport;
    private boolean smsPushEnable;
    private String smsQuickText;
    private boolean support4G;
    private boolean supportAiAnalyze;
    private boolean supportContact;
    private String supportCs;
    private boolean supportRem;
    private boolean temperature;
    private int travelModel;
    private long uid;
    private String userEmail;
    private String userPwd;
    private String userToken;
    private long wakeTime;
    private int wallpaperApiUpdateTime;
    private boolean warmingAutoStart;
    private boolean warmingBatteryAllow;
    private boolean warmingBatteryWhite;
    private boolean warmingLock;
    private boolean warmingNotification;
    private boolean watchFaceApiFlag;
    private long watchFaceMarketVersion;
    private String weatherInfo;
    private int weatherLastTime;
    private int weatherToDeviceLastTime;
    private boolean wechatSupport;

    public /* synthetic */ UserConfig(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private UserConfig(Context context) {
        this.deviceName = "";
        this.deviceAddress = "";
        this.hwVersion = "";
        this.fmVersion = "";
        this.deviceNameNoClear = "";
        this.deviceAddressNoClear = "";
        this.userToken = "15ef6eb5403406c1da0dc4a4defa2ea1";
        this.userEmail = "";
        this.userPwd = "";
        this.metric = true;
        this.messagePushEnable = true;
        this.weatherInfo = "";
        this.permissionDescFlag = true;
        this.languageCurr = "";
        this.oneKeySupport = "";
        this.lastUserPhotoModifyTime = new DateUtil().getUnixTimestamp();
        this.newSleepProtocol = true;
        this.googleFitLastInfo = "";
        this.maxWatchFace = 6;
        this.classicBluetoothMac = "";
        this.deviceFunctionList = "";
        this.skusGoogle = "";
        this.maxContacts = 20;
        this.travelModel = 2;
        this.appScanKey = "";
        this.heartSupport = true;
        this.sleepSupport = true;
        this.contactId = "";
        this.appUpgradeTime = "";
        this.supportCs = "[{\"deviceName\":\"S 53\",\"hardVersion\":\"F02B_V1.0\"},{\"deviceName\":\"O_P 73\",\"hardVersion\":\"M73J_V1.1\"},{\"deviceName\":\"P 73\",\"hardVersion\":\"M73J_V1.1\"}]";
        this.forwardPhoneNumber = "00000000000";
        this.smsQuickText = "";
        this.deviceModel = "";
        this.batteryWarmingOpen = true;
        this.notSupportTakePhoto = true;
        this.lastAnalyzeResultJson = "";
        this.context = context;
        UserConfigDAO.INSTANCE.readUserConfig(this);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    /* renamed from: isDebug, reason: from getter */
    public final boolean getIsDebug() {
        return this.isDebug;
    }

    public final void setDebug(boolean z) {
        this.isDebug = z;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceName = str;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddress = str;
    }

    public final String getHwVersion() {
        return this.hwVersion;
    }

    public final void setHwVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hwVersion = str;
    }

    public final String getFmVersion() {
        return this.fmVersion;
    }

    public final void setFmVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fmVersion = str;
    }

    public final String getBattery() {
        return this.battery;
    }

    public final void setBattery(String str) {
        this.battery = str;
    }

    public final String getLastDeviceAddress() {
        return this.lastDeviceAddress;
    }

    public final void setLastDeviceAddress(String str) {
        this.lastDeviceAddress = str;
    }

    public final String getHealthyModel() {
        return this.healthyModel;
    }

    public final void setHealthyModel(String str) {
        this.healthyModel = str;
    }

    public final String getDeviceNameNoClear() {
        return this.deviceNameNoClear;
    }

    public final void setDeviceNameNoClear(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceNameNoClear = str;
    }

    public final String getDeviceAddressNoClear() {
        return this.deviceAddressNoClear;
    }

    public final void setDeviceAddressNoClear(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddressNoClear = str;
    }

    public final boolean getLoginStatus() {
        return this.loginStatus;
    }

    public final void setLoginStatus(boolean z) {
        this.loginStatus = z;
    }

    public final String getUserToken() {
        return this.userToken;
    }

    public final void setUserToken(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userToken = str;
    }

    public final String getUserEmail() {
        return this.userEmail;
    }

    public final void setUserEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userEmail = str;
    }

    public final String getUserPwd() {
        return this.userPwd;
    }

    public final void setUserPwd(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userPwd = str;
    }

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final boolean getMetric() {
        return this.metric;
    }

    public final void setMetric(boolean z) {
        this.metric = z;
    }

    public final boolean getMenstruationSetting() {
        return this.menstruationSetting;
    }

    public final void setMenstruationSetting(boolean z) {
        this.menstruationSetting = z;
    }

    public final boolean getCallPushEnable() {
        return this.callPushEnable;
    }

    public final void setCallPushEnable(boolean z) {
        this.callPushEnable = z;
    }

    public final boolean getSmsPushEnable() {
        return this.smsPushEnable;
    }

    public final void setSmsPushEnable(boolean z) {
        this.smsPushEnable = z;
    }

    public final boolean getMessagePushEnable() {
        return this.messagePushEnable;
    }

    public final void setMessagePushEnable(boolean z) {
        this.messagePushEnable = z;
    }

    public final long getWakeTime() {
        return this.wakeTime;
    }

    public final void setWakeTime(long j) {
        this.wakeTime = j;
    }

    public final int getWeatherLastTime() {
        return this.weatherLastTime;
    }

    public final void setWeatherLastTime(int i) {
        this.weatherLastTime = i;
    }

    public final int getWeatherToDeviceLastTime() {
        return this.weatherToDeviceLastTime;
    }

    public final void setWeatherToDeviceLastTime(int i) {
        this.weatherToDeviceLastTime = i;
    }

    public final String getWeatherInfo() {
        return this.weatherInfo;
    }

    public final void setWeatherInfo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.weatherInfo = str;
    }

    public final boolean getPermissionDescFlag() {
        return this.permissionDescFlag;
    }

    public final void setPermissionDescFlag(boolean z) {
        this.permissionDescFlag = z;
    }

    public final boolean getGpsWarmingDialog() {
        return this.gpsWarmingDialog;
    }

    public final void setGpsWarmingDialog(boolean z) {
        this.gpsWarmingDialog = z;
    }

    public final String getLanguageCurr() {
        return this.languageCurr;
    }

    public final void setLanguageCurr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.languageCurr = str;
    }

    public final int getSkinType() {
        return this.skinType;
    }

    public final void setSkinType(int i) {
        this.skinType = i;
    }

    public final long getLastSyncFromServerTime() {
        return this.lastSyncFromServerTime;
    }

    public final void setLastSyncFromServerTime(long j) {
        this.lastSyncFromServerTime = j;
    }

    public final String getOneKeySupport() {
        return this.oneKeySupport;
    }

    public final void setOneKeySupport(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.oneKeySupport = str;
    }

    public final boolean getFirstUse() {
        return this.firstUse;
    }

    public final void setFirstUse(boolean z) {
        this.firstUse = z;
    }

    public final long getLastSyncTodaySteps() {
        return this.lastSyncTodaySteps;
    }

    public final void setLastSyncTodaySteps(long j) {
        this.lastSyncTodaySteps = j;
    }

    public final long getWatchFaceMarketVersion() {
        return this.watchFaceMarketVersion;
    }

    public final void setWatchFaceMarketVersion(long j) {
        this.watchFaceMarketVersion = j;
    }

    public final long getLastCollectionTime() {
        return this.lastCollectionTime;
    }

    public final void setLastCollectionTime(long j) {
        this.lastCollectionTime = j;
    }

    public final long getGpsPermission() {
        return this.gpsPermission;
    }

    public final void setGpsPermission(long j) {
        this.gpsPermission = j;
    }

    public final boolean getWarmingNotification() {
        return this.warmingNotification;
    }

    public final void setWarmingNotification(boolean z) {
        this.warmingNotification = z;
    }

    public final boolean getWarmingBatteryWhite() {
        return this.warmingBatteryWhite;
    }

    public final void setWarmingBatteryWhite(boolean z) {
        this.warmingBatteryWhite = z;
    }

    public final boolean getWarmingAutoStart() {
        return this.warmingAutoStart;
    }

    public final void setWarmingAutoStart(boolean z) {
        this.warmingAutoStart = z;
    }

    public final boolean getWarmingLock() {
        return this.warmingLock;
    }

    public final void setWarmingLock(boolean z) {
        this.warmingLock = z;
    }

    public final boolean getWarmingBatteryAllow() {
        return this.warmingBatteryAllow;
    }

    public final void setWarmingBatteryAllow(boolean z) {
        this.warmingBatteryAllow = z;
    }

    public final long getLastUserPhotoModifyTime() {
        return this.lastUserPhotoModifyTime;
    }

    public final void setLastUserPhotoModifyTime(long j) {
        this.lastUserPhotoModifyTime = j;
    }

    public final boolean getGoogleFit() {
        return this.googleFit;
    }

    public final void setGoogleFit(boolean z) {
        this.googleFit = z;
    }

    public final boolean getNewSleepProtocol() {
        return this.newSleepProtocol;
    }

    public final void setNewSleepProtocol(boolean z) {
        this.newSleepProtocol = z;
    }

    public final boolean getTemperature() {
        return this.temperature;
    }

    public final void setTemperature(boolean z) {
        this.temperature = z;
    }

    public final String getGoogleFitLastInfo() {
        return this.googleFitLastInfo;
    }

    public final void setGoogleFitLastInfo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.googleFitLastInfo = str;
    }

    public final int getMaxWatchFace() {
        return this.maxWatchFace;
    }

    public final void setMaxWatchFace(int i) {
        this.maxWatchFace = i;
    }

    public final boolean getOtherSoftwarePush() {
        return this.otherSoftwarePush;
    }

    public final void setOtherSoftwarePush(boolean z) {
        this.otherSoftwarePush = z;
    }

    public final boolean getSupportContact() {
        return this.supportContact;
    }

    public final void setSupportContact(boolean z) {
        this.supportContact = z;
    }

    public final long getLastTenMinSyncTime() {
        return this.lastTenMinSyncTime;
    }

    public final void setLastTenMinSyncTime(long j) {
        this.lastTenMinSyncTime = j;
    }

    public final int getServerMarketSize() {
        return this.serverMarketSize;
    }

    public final void setServerMarketSize(int i) {
        this.serverMarketSize = i;
    }

    public final String getClassicBluetoothMac() {
        return this.classicBluetoothMac;
    }

    public final void setClassicBluetoothMac(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.classicBluetoothMac = str;
    }

    public final String getDeviceFunctionList() {
        return this.deviceFunctionList;
    }

    public final void setDeviceFunctionList(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceFunctionList = str;
    }

    public final int getIndexApiUpdateTime() {
        return this.indexApiUpdateTime;
    }

    public final void setIndexApiUpdateTime(int i) {
        this.indexApiUpdateTime = i;
    }

    public final String getSkusGoogle() {
        return this.skusGoogle;
    }

    public final void setSkusGoogle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.skusGoogle = str;
    }

    public final int getLastLocationTime() {
        return this.lastLocationTime;
    }

    public final void setLastLocationTime(int i) {
        this.lastLocationTime = i;
    }

    public final int getMaxContacts() {
        return this.maxContacts;
    }

    public final void setMaxContacts(int i) {
        this.maxContacts = i;
    }

    public final boolean getWatchFaceApiFlag() {
        return this.watchFaceApiFlag;
    }

    public final void setWatchFaceApiFlag(boolean z) {
        this.watchFaceApiFlag = z;
    }

    public final boolean getMusicSupport() {
        return this.musicSupport;
    }

    public final void setMusicSupport(boolean z) {
        this.musicSupport = z;
    }

    public final boolean getRtkMcuSupport() {
        return this.rtkMcuSupport;
    }

    public final void setRtkMcuSupport(boolean z) {
        this.rtkMcuSupport = z;
    }

    public final boolean getEbookSupport() {
        return this.ebookSupport;
    }

    public final void setEbookSupport(boolean z) {
        this.ebookSupport = z;
    }

    public final boolean getChinaApp() {
        return this.chinaApp;
    }

    public final void setChinaApp(boolean z) {
        this.chinaApp = z;
    }

    public final boolean getWechatSupport() {
        return this.wechatSupport;
    }

    public final void setWechatSupport(boolean z) {
        this.wechatSupport = z;
    }

    public final boolean getManualDND() {
        return this.manualDND;
    }

    public final void setManualDND(boolean z) {
        this.manualDND = z;
    }

    public final boolean getBloodSugarSupport() {
        return this.bloodSugarSupport;
    }

    public final void setBloodSugarSupport(boolean z) {
        this.bloodSugarSupport = z;
    }

    public final boolean getDevelopment() {
        return this.development;
    }

    public final void setDevelopment(boolean z) {
        this.development = z;
    }

    public final boolean getLyricsSupport() {
        return this.lyricsSupport;
    }

    public final void setLyricsSupport(boolean z) {
        this.lyricsSupport = z;
    }

    public final boolean getAlbumSupport() {
        return this.albumSupport;
    }

    public final void setAlbumSupport(boolean z) {
        this.albumSupport = z;
    }

    public final boolean getGpsSupport() {
        return this.gpsSupport;
    }

    public final void setGpsSupport(boolean z) {
        this.gpsSupport = z;
    }

    public final int getTravelModel() {
        return this.travelModel;
    }

    public final void setTravelModel(int i) {
        this.travelModel = i;
    }

    public final boolean getJieLiMusic() {
        return this.jieLiMusic;
    }

    public final void setJieLiMusic(boolean z) {
        this.jieLiMusic = z;
    }

    public final long getGpsLocationTime() {
        return this.gpsLocationTime;
    }

    public final void setGpsLocationTime(long j) {
        this.gpsLocationTime = j;
    }

    public final long getGpsLocationTimeNew() {
        return this.gpsLocationTimeNew;
    }

    public final void setGpsLocationTimeNew(long j) {
        this.gpsLocationTimeNew = j;
    }

    public final int getMessagePushSupport() {
        return this.messagePushSupport;
    }

    public final void setMessagePushSupport(int i) {
        this.messagePushSupport = i;
    }

    public final boolean getDeviceAvatarSupport() {
        return this.deviceAvatarSupport;
    }

    public final void setDeviceAvatarSupport(boolean z) {
        this.deviceAvatarSupport = z;
    }

    public final boolean getNotificationGoTo() {
        return this.notificationGoTo;
    }

    public final void setNotificationGoTo(boolean z) {
        this.notificationGoTo = z;
    }

    public final int getPictureUpdateTime() {
        return this.pictureUpdateTime;
    }

    public final void setPictureUpdateTime(int i) {
        this.pictureUpdateTime = i;
    }

    public final String getAppScanKey() {
        return this.appScanKey;
    }

    public final void setAppScanKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.appScanKey = str;
    }

    public final boolean getHeartSupport() {
        return this.heartSupport;
    }

    public final void setHeartSupport(boolean z) {
        this.heartSupport = z;
    }

    public final boolean getSleepSupport() {
        return this.sleepSupport;
    }

    public final void setSleepSupport(boolean z) {
        this.sleepSupport = z;
    }

    public final boolean getHrvSupport() {
        return this.hrvSupport;
    }

    public final void setHrvSupport(boolean z) {
        this.hrvSupport = z;
    }

    public final boolean getPressureSupport() {
        return this.pressureSupport;
    }

    public final void setPressureSupport(boolean z) {
        this.pressureSupport = z;
    }

    public final boolean getDeviceNotScreen() {
        return this.deviceNotScreen;
    }

    public final void setDeviceNotScreen(boolean z) {
        this.deviceNotScreen = z;
    }

    public final String getContactId() {
        return this.contactId;
    }

    public final void setContactId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contactId = str;
    }

    public final boolean getDisturbFlag() {
        return this.disturbFlag;
    }

    public final void setDisturbFlag(boolean z) {
        this.disturbFlag = z;
    }

    public final boolean getBindDevice() {
        return this.bindDevice;
    }

    public final void setBindDevice(boolean z) {
        this.bindDevice = z;
    }

    public final String getAppUpgradeTime() {
        return this.appUpgradeTime;
    }

    public final void setAppUpgradeTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.appUpgradeTime = str;
    }

    public final String getSupportCs() {
        return this.supportCs;
    }

    public final void setSupportCs(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.supportCs = str;
    }

    public final boolean getShowBindGuideDialog() {
        return this.showBindGuideDialog;
    }

    public final void setShowBindGuideDialog(boolean z) {
        this.showBindGuideDialog = z;
    }

    public final boolean getCsChatLogin() {
        return this.csChatLogin;
    }

    public final void setCsChatLogin(boolean z) {
        this.csChatLogin = z;
    }

    public final String getForwardPhoneNumber() {
        return this.forwardPhoneNumber;
    }

    public final void setForwardPhoneNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.forwardPhoneNumber = str;
    }

    public final String getSmsQuickText() {
        return this.smsQuickText;
    }

    public final void setSmsQuickText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.smsQuickText = str;
    }

    public final boolean getSupport4G() {
        return this.support4G;
    }

    public final void setSupport4G(boolean z) {
        this.support4G = z;
    }

    public final boolean getSupportRem() {
        return this.supportRem;
    }

    public final void setSupportRem(boolean z) {
        this.supportRem = z;
    }

    public final boolean getShowPlate() {
        return this.showPlate;
    }

    public final void setShowPlate(boolean z) {
        this.showPlate = z;
    }

    public final boolean getRoundScreen() {
        return this.roundScreen;
    }

    public final void setRoundScreen(boolean z) {
        this.roundScreen = z;
    }

    public final boolean getDeviceThemeSupport() {
        return this.deviceThemeSupport;
    }

    public final void setDeviceThemeSupport(boolean z) {
        this.deviceThemeSupport = z;
    }

    public final boolean getDeviceWallpaperSupport() {
        return this.deviceWallpaperSupport;
    }

    public final void setDeviceWallpaperSupport(boolean z) {
        this.deviceWallpaperSupport = z;
    }

    public final int getWallpaperApiUpdateTime() {
        return this.wallpaperApiUpdateTime;
    }

    public final void setWallpaperApiUpdateTime(int i) {
        this.wallpaperApiUpdateTime = i;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final void setDeviceModel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceModel = str;
    }

    public final int getNotificationTime() {
        return this.notificationTime;
    }

    public final void setNotificationTime(int i) {
        this.notificationTime = i;
    }

    public final boolean getBatteryWarmingOpen() {
        return this.batteryWarmingOpen;
    }

    public final void setBatteryWarmingOpen(boolean z) {
        this.batteryWarmingOpen = z;
    }

    public final boolean getBatteryLow() {
        return this.batteryLow;
    }

    public final void setBatteryLow(boolean z) {
        this.batteryLow = z;
    }

    public final int getLastAiAnalysisScore() {
        return this.lastAiAnalysisScore;
    }

    public final void setLastAiAnalysisScore(int i) {
        this.lastAiAnalysisScore = i;
    }

    public final boolean getSupportAiAnalyze() {
        return this.supportAiAnalyze;
    }

    public final void setSupportAiAnalyze(boolean z) {
        this.supportAiAnalyze = z;
    }

    public final boolean getNotSupportTakePhoto() {
        return this.notSupportTakePhoto;
    }

    public final void setNotSupportTakePhoto(boolean z) {
        this.notSupportTakePhoto = z;
    }

    public final String getLastAnalyzeResultJson() {
        return this.lastAnalyzeResultJson;
    }

    public final void setLastAnalyzeResultJson(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lastAnalyzeResultJson = str;
    }

    public final int getLastStep() {
        return this.lastStep;
    }

    public final void setLastStep(int i) {
        this.lastStep = i;
    }

    public final long getLastAnalyzeAiTime() {
        return this.lastAnalyzeAiTime;
    }

    public final void setLastAnalyzeAiTime(long j) {
        this.lastAnalyzeAiTime = j;
    }

    public final void save() {
        UserConfigDAO.INSTANCE.saveUserConfig(this);
    }

    /* compiled from: UserConfig.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/base/pref/UserConfig$Companion;", "", "()V", "instance", "Lcom/qcwireless/qcwatch/base/pref/UserConfig;", "getInstance", "context", "Landroid/content/Context;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserConfig getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (UserConfig.instance == null) {
                synchronized (UserConfig.class) {
                    if (UserConfig.instance == null) {
                        Companion companion = UserConfig.INSTANCE;
                        UserConfig.instance = new UserConfig(context, null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            UserConfig userConfig = UserConfig.instance;
            Intrinsics.checkNotNull(userConfig);
            return userConfig;
        }

        public final UserConfig getInstance() {
            UserConfig userConfig = UserConfig.instance;
            Intrinsics.checkNotNull(userConfig);
            return userConfig;
        }
    }

    public final void clearAll() {
        UserConfigDAO.INSTANCE.clear(this);
        Companion companion = INSTANCE;
        instance = null;
        companion.getInstance(QCApplication.INSTANCE.getCONTEXT());
        UserConfigDAO.INSTANCE.readUserConfig(this);
    }
}
