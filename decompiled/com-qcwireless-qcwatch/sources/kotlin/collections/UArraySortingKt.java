package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m1553partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM1180getw2LRezQ = UByteArray.m1180getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM1180getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m1180getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m1180getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM1180getw2LRezQ2 = UByteArray.m1180getw2LRezQ(bArr, i);
                UByteArray.m1185setVurrAj0(bArr, i, UByteArray.m1180getw2LRezQ(bArr, i2));
                UByteArray.m1185setVurrAj0(bArr, i2, bM1180getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m1557quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM1553partition4UcCI2c = m1553partition4UcCI2c(bArr, i, i2);
        int i3 = iM1553partition4UcCI2c - 1;
        if (i < i3) {
            m1557quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM1553partition4UcCI2c < i2) {
            m1557quickSort4UcCI2c(bArr, iM1553partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m1554partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM1440getMh2AYeg = UShortArray.m1440getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = sM1440getMh2AYeg & 65535;
                if (Intrinsics.compare(UShortArray.m1440getMh2AYeg(sArr, i) & 65535, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m1440getMh2AYeg(sArr, i2) & 65535, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM1440getMh2AYeg2 = UShortArray.m1440getMh2AYeg(sArr, i);
                UShortArray.m1445set01HTLdE(sArr, i, UShortArray.m1440getMh2AYeg(sArr, i2));
                UShortArray.m1445set01HTLdE(sArr, i2, sM1440getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m1558quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM1554partitionAa5vz7o = m1554partitionAa5vz7o(sArr, i, i2);
        int i3 = iM1554partitionAa5vz7o - 1;
        if (i < i3) {
            m1558quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM1554partitionAa5vz7o < i2) {
            m1558quickSortAa5vz7o(sArr, iM1554partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m1555partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM1258getpVg5ArA = UIntArray.m1258getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m1258getpVg5ArA(iArr, i), iM1258getpVg5ArA) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m1258getpVg5ArA(iArr, i2), iM1258getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM1258getpVg5ArA2 = UIntArray.m1258getpVg5ArA(iArr, i);
                UIntArray.m1263setVXSXFK8(iArr, i, UIntArray.m1258getpVg5ArA(iArr, i2));
                UIntArray.m1263setVXSXFK8(iArr, i2, iM1258getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m1559quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM1555partitionoBK06Vg = m1555partitionoBK06Vg(iArr, i, i2);
        int i3 = iM1555partitionoBK06Vg - 1;
        if (i < i3) {
            m1559quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM1555partitionoBK06Vg < i2) {
            m1559quickSortoBK06Vg(iArr, iM1555partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m1552partitionnroSd4(long[] jArr, int i, int i2) {
        long jM1336getsVKNKU = ULongArray.m1336getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m1336getsVKNKU(jArr, i), jM1336getsVKNKU) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m1336getsVKNKU(jArr, i2), jM1336getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM1336getsVKNKU2 = ULongArray.m1336getsVKNKU(jArr, i);
                ULongArray.m1341setk8EXiF4(jArr, i, ULongArray.m1336getsVKNKU(jArr, i2));
                ULongArray.m1341setk8EXiF4(jArr, i2, jM1336getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m1556quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM1552partitionnroSd4 = m1552partitionnroSd4(jArr, i, i2);
        int i3 = iM1552partitionnroSd4 - 1;
        if (i < i3) {
            m1556quickSortnroSd4(jArr, i, i3);
        }
        if (iM1552partitionnroSd4 < i2) {
            m1556quickSortnroSd4(jArr, iM1552partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m1561sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1557quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m1562sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1558quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m1563sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1559quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m1560sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1556quickSortnroSd4(array, i, i2 - 1);
    }
}
