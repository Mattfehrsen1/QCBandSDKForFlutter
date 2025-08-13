package androidx.camera.core.impl.utils;

import androidx.work.WorkRequest;

/* loaded from: classes.dex */
final class LongRational {
    private final long mDenominator;
    private final long mNumerator;

    LongRational(long nominator, long denominator) {
        this.mNumerator = nominator;
        this.mDenominator = denominator;
    }

    LongRational(double value) {
        this((long) (value * 10000.0d), WorkRequest.MIN_BACKOFF_MILLIS);
    }

    long getNumerator() {
        return this.mNumerator;
    }

    long getDenominator() {
        return this.mDenominator;
    }

    double toDouble() {
        return this.mNumerator / this.mDenominator;
    }

    public String toString() {
        return this.mNumerator + "/" + this.mDenominator;
    }
}
