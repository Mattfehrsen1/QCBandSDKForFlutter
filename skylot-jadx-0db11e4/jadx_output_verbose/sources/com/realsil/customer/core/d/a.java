package com.realsil.customer.core.d;

import androidx.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/d/a.class */
public final class a {
    public static boolean a(UUID uuid, UUID uuid2, @Nullable UUID uuid3) {
        if (uuid3 == null) {
            return Objects.equals(uuid, uuid2);
        }
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long leastSignificantBits2 = uuid2.getLeastSignificantBits();
        long leastSignificantBits3 = uuid3.getLeastSignificantBits();
        if ((leastSignificantBits & leastSignificantBits3) == (leastSignificantBits2 & leastSignificantBits3)) {
            long mostSignificantBits = uuid.getMostSignificantBits();
            if ((mostSignificantBits & mostSignificantBits) == (uuid2.getMostSignificantBits() & uuid3.getMostSignificantBits())) {
                return true;
            }
        }
        return false;
    }
}
