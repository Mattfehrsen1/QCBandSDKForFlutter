package androidx.core.os;

import android.os.Build;
import android.os.PersistableBundle;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* compiled from: PersistableBundle.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"persistableBundleOf", "Landroid/os/PersistableBundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/PersistableBundle;", "core-ktx_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PersistableBundleKt {
    public static final PersistableBundle persistableBundleOf(Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistableBundle persistableBundle = new PersistableBundle(pairs.length);
        int length = pairs.length;
        int i = 0;
        while (i < length) {
            Pair<String, ? extends Object> pair = pairs[i];
            i++;
            String strComponent1 = pair.component1();
            Object objComponent2 = pair.component2();
            if (objComponent2 == null) {
                persistableBundle.putString(strComponent1, null);
            } else if (objComponent2 instanceof Boolean) {
                if (Build.VERSION.SDK_INT >= 22) {
                    persistableBundle.putBoolean(strComponent1, ((Boolean) objComponent2).booleanValue());
                } else {
                    throw new IllegalArgumentException("Illegal value type boolean for key \"" + strComponent1 + Typography.quote);
                }
            } else if (objComponent2 instanceof Double) {
                persistableBundle.putDouble(strComponent1, ((Number) objComponent2).doubleValue());
            } else if (objComponent2 instanceof Integer) {
                persistableBundle.putInt(strComponent1, ((Number) objComponent2).intValue());
            } else if (objComponent2 instanceof Long) {
                persistableBundle.putLong(strComponent1, ((Number) objComponent2).longValue());
            } else if (objComponent2 instanceof String) {
                persistableBundle.putString(strComponent1, (String) objComponent2);
            } else if (objComponent2 instanceof boolean[]) {
                if (Build.VERSION.SDK_INT >= 22) {
                    persistableBundle.putBooleanArray(strComponent1, (boolean[]) objComponent2);
                } else {
                    throw new IllegalArgumentException("Illegal value type boolean[] for key \"" + strComponent1 + Typography.quote);
                }
            } else if (objComponent2 instanceof double[]) {
                persistableBundle.putDoubleArray(strComponent1, (double[]) objComponent2);
            } else if (objComponent2 instanceof int[]) {
                persistableBundle.putIntArray(strComponent1, (int[]) objComponent2);
            } else if (objComponent2 instanceof long[]) {
                persistableBundle.putLongArray(strComponent1, (long[]) objComponent2);
            } else if (objComponent2 instanceof Object[]) {
                Class<?> componentType = objComponent2.getClass().getComponentType();
                Intrinsics.checkNotNull(componentType);
                if (String.class.isAssignableFrom(componentType)) {
                    Objects.requireNonNull(objComponent2, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                    persistableBundle.putStringArray(strComponent1, (String[]) objComponent2);
                } else {
                    throw new IllegalArgumentException("Illegal value array type " + ((Object) componentType.getCanonicalName()) + " for key \"" + strComponent1 + Typography.quote);
                }
            } else {
                throw new IllegalArgumentException("Illegal value type " + ((Object) objComponent2.getClass().getCanonicalName()) + " for key \"" + strComponent1 + Typography.quote);
            }
        }
        return persistableBundle;
    }
}
