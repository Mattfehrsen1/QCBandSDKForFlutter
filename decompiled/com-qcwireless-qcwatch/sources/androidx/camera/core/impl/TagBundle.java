package androidx.camera.core.impl;

import android.util.ArrayMap;
import android.util.Pair;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class TagBundle {
    private static final TagBundle EMPTY_TAGBUNDLE = new TagBundle(new ArrayMap());
    protected final Map<String, Integer> mTagMap;

    protected TagBundle(Map<String, Integer> tagMap) {
        this.mTagMap = tagMap;
    }

    public static TagBundle emptyBundle() {
        return EMPTY_TAGBUNDLE;
    }

    public static TagBundle create(Pair<String, Integer> source) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put((String) source.first, (Integer) source.second);
        return new TagBundle(arrayMap);
    }

    public static TagBundle from(TagBundle otherTagBundle) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : otherTagBundle.listKeys()) {
            arrayMap.put(str, otherTagBundle.getTag(str));
        }
        return new TagBundle(arrayMap);
    }

    public Integer getTag(String key) {
        return this.mTagMap.get(key);
    }

    public Set<String> listKeys() {
        return this.mTagMap.keySet();
    }
}
