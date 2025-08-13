package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class MultiValueSet<C> {
    private Set<C> mSet = new HashSet();

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract MultiValueSet<C> mo2clone();

    public void addAll(List<C> value) {
        this.mSet.addAll(value);
    }

    public List<C> getAllItems() {
        return Collections.unmodifiableList(new ArrayList(this.mSet));
    }
}
