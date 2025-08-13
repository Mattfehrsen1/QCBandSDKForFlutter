package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class Quirks {
    private final List<Quirk> mQuirks;

    public Quirks(final List<Quirk> quirks) {
        this.mQuirks = new ArrayList(quirks);
    }

    public <T extends Quirk> T get(final Class<T> quirkClass) {
        Iterator<Quirk> it = this.mQuirks.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (t.getClass() == quirkClass) {
                return t;
            }
        }
        return null;
    }

    public boolean contains(Class<? extends Quirk> quirkClass) {
        Iterator<Quirk> it = this.mQuirks.iterator();
        while (it.hasNext()) {
            if (quirkClass.isAssignableFrom(it.next().getClass())) {
                return true;
            }
        }
        return false;
    }
}
