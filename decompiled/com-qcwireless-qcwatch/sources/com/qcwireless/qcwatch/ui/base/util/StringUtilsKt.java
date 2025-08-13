package com.qcwireless.qcwatch.ui.base.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: StringUtils.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0014\u0010\u0007\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\t0\b\u001a\u001a\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\f\u001a\u00020\r\u001a\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000f\u001a\u00020\u0001¨\u0006\u0014"}, d2 = {"floatArrayToString", "", "floatArray", "", "intArrayToString", "intArray", "", "intListToString", "", "", "listToString1", "list", "separator", "", "stringToFloatArray", "s", "stringToFloatList", "", "stringToIntArray", "stringToIntList", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StringUtilsKt {
    public static final String intArrayToString(int[] intArray) {
        Intrinsics.checkNotNullParameter(intArray, "intArray");
        if (intArray.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : intArray) {
            sb.append(i);
            sb.append(",");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        String strSubstring = string.substring(0, sb.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final int[] stringToIntArray(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        String str = s;
        if (TextUtils.isEmpty(str)) {
            return new int[0];
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        Object[] array = new Regex(",").split(str.subSequence(i, length + 1).toString(), 0).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        int[] iArr = new int[strArr.length];
        int length2 = strArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            String str2 = strArr[i2];
            int length3 = str2.length() - 1;
            int i3 = 0;
            boolean z3 = false;
            while (i3 <= length3) {
                boolean z4 = Intrinsics.compare((int) str2.charAt(!z3 ? i3 : length3), 32) <= 0;
                if (z3) {
                    if (!z4) {
                        break;
                    }
                    length3--;
                } else if (z4) {
                    i3++;
                } else {
                    z3 = true;
                }
            }
            iArr[i2] = (int) Float.parseFloat(str2.subSequence(i3, length3 + 1).toString());
        }
        return iArr;
    }

    public static final float[] stringToFloatArray(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        String str = s;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        Object[] array = new Regex(",").split(str.subSequence(i, length + 1).toString(), 0).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        float[] fArr = new float[strArr.length];
        int length2 = strArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            fArr[i2] = Float.parseFloat(strArr[i2]);
        }
        return fArr;
    }

    public static final String floatArrayToString(float[] floatArray) {
        Intrinsics.checkNotNullParameter(floatArray, "floatArray");
        StringBuilder sb = new StringBuilder();
        for (float f : floatArray) {
            sb.append(f);
            sb.append(",");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        String strSubstring = string.substring(0, sb.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final List<Float> stringToFloatList(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        String str = s;
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        Object[] array = new Regex(",").split(str.subSequence(i, length + 1).toString(), 0).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        ArrayList arrayList = new ArrayList();
        for (String str2 : (String[]) array) {
            arrayList.add(Float.valueOf(Float.parseFloat(str2)));
        }
        return arrayList;
    }

    public static final List<Integer> stringToIntList(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        String str = s;
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        Object[] array = new Regex(",").split(str.subSequence(i, length + 1).toString(), 0).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        ArrayList arrayList = new ArrayList();
        for (String str2 : (String[]) array) {
            arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
        }
        return arrayList;
    }

    public static final String intListToString(List<Integer> intArray) {
        Intrinsics.checkNotNullParameter(intArray, "intArray");
        if (intArray.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int size = intArray.size();
        for (int i = 0; i < size; i++) {
            sb.append(intArray.get(i).intValue());
            sb.append(",");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        String strSubstring = string.substring(0, sb.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final String listToString1(List<?> list, char c) {
        Intrinsics.checkNotNullParameter(list, "list");
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i));
            sb.append(c);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        String strSubstring = string.substring(0, sb.toString().length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
