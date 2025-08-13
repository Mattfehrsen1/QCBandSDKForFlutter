package androidx.camera.core.impl;

import android.util.ArrayMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MutableTagBundle extends TagBundle {
    private MutableTagBundle(Map<String, Integer> source) {
        super(source);
    }

    public static MutableTagBundle create() {
        return new MutableTagBundle(new ArrayMap());
    }

    public static MutableTagBundle from(TagBundle otherTagBundle) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : otherTagBundle.listKeys()) {
            arrayMap.put(str, otherTagBundle.getTag(str));
        }
        return new MutableTagBundle(arrayMap);
    }

    public void putTag(String key, Integer value) {
        this.mTagMap.put(key, value);
    }

    public void addTagBundle(TagBundle bundle) {
        if (this.mTagMap == null || bundle.mTagMap == null) {
            return;
        }
        this.mTagMap.putAll(bundle.mTagMap);
    }
}
