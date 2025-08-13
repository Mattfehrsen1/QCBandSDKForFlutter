package com.qcwireless.qcwatch.base.utils;

import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qc_utils.date.LanguageUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: QcDateUtil.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000fR&\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/QcDateUtil;", "", "()V", "monthShorthandList", "", "", "", "getMonthShorthandList", "()Ljava/util/Map;", "setMonthShorthandList", "(Ljava/util/Map;)V", "initStringArray", "", "localDateFormat", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "localDateFormatSport", "localDateNoDayFormat", "localDateNoYearFormat", "start", "end", "localDateYMDHMSFormat", "localDateYMDHMSFormatSport", "sportIsToday", "", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QcDateUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<QcDateUtil> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<QcDateUtil>() { // from class: com.qcwireless.qcwatch.base.utils.QcDateUtil$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final QcDateUtil invoke() {
            return new QcDateUtil();
        }
    });
    private Map<Integer, String> monthShorthandList;

    public QcDateUtil() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.monthShorthandList = linkedHashMap;
        linkedHashMap.put(1, GlobalKt.getString(R.string.qc_text_435));
        this.monthShorthandList.put(2, GlobalKt.getString(R.string.qc_text_436));
        this.monthShorthandList.put(3, GlobalKt.getString(R.string.qc_text_437));
        this.monthShorthandList.put(4, GlobalKt.getString(R.string.qc_text_438));
        this.monthShorthandList.put(5, GlobalKt.getString(R.string.qc_text_439));
        this.monthShorthandList.put(6, GlobalKt.getString(R.string.qc_text_440));
        this.monthShorthandList.put(7, GlobalKt.getString(R.string.qc_text_441));
        this.monthShorthandList.put(8, GlobalKt.getString(R.string.qc_text_442));
        this.monthShorthandList.put(9, GlobalKt.getString(R.string.qc_text_443));
        this.monthShorthandList.put(10, GlobalKt.getString(R.string.qc_text_444));
        this.monthShorthandList.put(11, GlobalKt.getString(R.string.qc_text_445));
        this.monthShorthandList.put(12, GlobalKt.getString(R.string.qc_text_446));
    }

    public final Map<Integer, String> getMonthShorthandList() {
        return this.monthShorthandList;
    }

    public final void setMonthShorthandList(Map<Integer, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.monthShorthandList = map;
    }

    public final void initStringArray() {
        this.monthShorthandList.put(1, GlobalKt.getString(R.string.qc_text_435));
        this.monthShorthandList.put(2, GlobalKt.getString(R.string.qc_text_436));
        this.monthShorthandList.put(3, GlobalKt.getString(R.string.qc_text_437));
        this.monthShorthandList.put(4, GlobalKt.getString(R.string.qc_text_438));
        this.monthShorthandList.put(5, GlobalKt.getString(R.string.qc_text_439));
        this.monthShorthandList.put(6, GlobalKt.getString(R.string.qc_text_440));
        this.monthShorthandList.put(7, GlobalKt.getString(R.string.qc_text_441));
        this.monthShorthandList.put(8, GlobalKt.getString(R.string.qc_text_442));
        this.monthShorthandList.put(9, GlobalKt.getString(R.string.qc_text_443));
        this.monthShorthandList.put(10, GlobalKt.getString(R.string.qc_text_444));
        this.monthShorthandList.put(11, GlobalKt.getString(R.string.qc_text_445));
        this.monthShorthandList.put(12, GlobalKt.getString(R.string.qc_text_446));
    }

    public final String localDateFormat(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        try {
            if (LanguageUtil.changeDateFormat()) {
                String d_m_y = dateUtil.getD_M_Y();
                Intrinsics.checkNotNullExpressionValue(d_m_y, "dateUtil.d_M_Y");
                List listSplit$default = StringsKt.split$default((CharSequence) d_m_y, new String[]{"-"}, false, 0, 6, (Object) null);
                return ((String) listSplit$default.get(0)) + ',' + this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1)))) + ((String) listSplit$default.get(2));
            }
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "{\n                dateUtil.y_M_D\n            }");
            return y_m_d;
        } catch (Exception unused) {
            String y_m_d2 = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d2, "dateUtil.y_M_D");
            return y_m_d2;
        }
    }

    public final String localDateNoYearFormat(DateUtil start, DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        try {
            if (LanguageUtil.changeDateFormat()) {
                String d_m_y = start.getD_M_Y();
                Intrinsics.checkNotNullExpressionValue(d_m_y, "start.d_M_Y");
                List listSplit$default = StringsKt.split$default((CharSequence) d_m_y, new String[]{"-"}, false, 0, 6, (Object) null);
                String d_m_y2 = end.getD_M_Y();
                Intrinsics.checkNotNullExpressionValue(d_m_y2, "end.d_M_Y");
                List listSplit$default2 = StringsKt.split$default((CharSequence) d_m_y2, new String[]{"-"}, false, 0, 6, (Object) null);
                return ((String) listSplit$default.get(0)) + ',' + this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1)))) + '~' + ((String) listSplit$default2.get(0)) + ',' + this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default2.get(1))));
            }
            return start.getMMddDate() + '~' + end.getMMddDate();
        } catch (Exception unused) {
            return start.getMMddDate() + '~' + end.getMMddDate();
        }
    }

    public final String localDateNoDayFormat(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        try {
            if (LanguageUtil.changeDateFormat()) {
                String d_m_y = dateUtil.getD_M_Y();
                Intrinsics.checkNotNullExpressionValue(d_m_y, "dateUtil.d_M_Y");
                List listSplit$default = StringsKt.split$default((CharSequence) d_m_y, new String[]{"-"}, false, 0, 6, (Object) null);
                return this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1)))) + ((String) listSplit$default.get(2));
            }
            String yyyyMMDate = dateUtil.getYyyyMMDate();
            Intrinsics.checkNotNullExpressionValue(yyyyMMDate, "{\n                dateUt….yyyyMMDate\n            }");
            return yyyyMMDate;
        } catch (Exception unused) {
            String yyyyMMDate2 = dateUtil.getYyyyMMDate();
            Intrinsics.checkNotNullExpressionValue(yyyyMMDate2, "dateUtil.yyyyMMDate");
            return yyyyMMDate2;
        }
    }

    public final String localDateYMDHMSFormat(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        try {
            if (LanguageUtil.changeDateFormat()) {
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "dateUtil.y_M_D");
                List listSplit$default = StringsKt.split$default((CharSequence) y_m_d, new String[]{"-"}, false, 0, 6, (Object) null);
                return ((String) listSplit$default.get(2)) + ',' + this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1)))) + ((String) listSplit$default.get(0)) + ' ' + dateUtil.getHHmmssDate();
            }
            String y_m_d_h_m_s = dateUtil.getY_M_D_H_M_S();
            Intrinsics.checkNotNullExpressionValue(y_m_d_h_m_s, "{\n                dateUt…y_M_D_H_M_S\n            }");
            return y_m_d_h_m_s;
        } catch (Exception unused) {
            String y_m_d_h_m_s2 = dateUtil.getY_M_D_H_M_S();
            Intrinsics.checkNotNullExpressionValue(y_m_d_h_m_s2, "dateUtil.y_M_D_H_M_S");
            return y_m_d_h_m_s2;
        }
    }

    public final String localDateYMDHMSFormatSport(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            if (LanguageUtil.changeDateFormat()) {
                String str = simpleDateFormat.format(dateUtil.toDate());
                Intrinsics.checkNotNullExpressionValue(str, "sdf.format(dateUtil.toDate())");
                List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
                return ((String) listSplit$default.get(2)) + ',' + this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1)))) + ((String) listSplit$default.get(0));
            }
            String str2 = simpleDateFormat.format(dateUtil.toDate());
            Intrinsics.checkNotNullExpressionValue(str2, "{\n                sdf.fo…l.toDate())\n            }");
            return str2;
        } catch (Exception unused) {
            String str3 = simpleDateFormat.format(dateUtil.toDate());
            Intrinsics.checkNotNullExpressionValue(str3, "sdf.format(dateUtil.toDate())");
            return str3;
        }
    }

    public final String localDateFormatSport(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            if (LanguageUtil.changeDateFormat()) {
                String str = simpleDateFormat.format(new Date(dateUtil.getTimestamp()));
                Intrinsics.checkNotNullExpressionValue(str, "sdf.format(Date(dateUtil.timestamp))");
                List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
                return ((String) listSplit$default.get(0)) + ',' + this.monthShorthandList.get(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1)))) + ((String) listSplit$default.get(2));
            }
            String str2 = simpleDateFormat.format(new Date(dateUtil.getTimestamp()));
            Intrinsics.checkNotNullExpressionValue(str2, "{\n                sdf.fo…timestamp))\n            }");
            return str2;
        } catch (Exception unused) {
            String str3 = simpleDateFormat.format(new Date(dateUtil.getTimestamp()));
            Intrinsics.checkNotNullExpressionValue(str3, "sdf.format(Date(dateUtil.timestamp))");
            return str3;
        }
    }

    public final boolean sportIsToday(DateUtil dateUtil) {
        Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(new Date(dateUtil.getTimestamp())).equals(new DateUtil().getY_M_D());
    }

    /* compiled from: QcDateUtil.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/base/utils/QcDateUtil$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/base/utils/QcDateUtil;", "getGetInstance", "()Lcom/qcwireless/qcwatch/base/utils/QcDateUtil;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QcDateUtil getGetInstance() {
            return (QcDateUtil) QcDateUtil.getInstance$delegate.getValue();
        }
    }
}
