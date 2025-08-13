package com.qcwireless.qcwatch.ui.base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class MapUtils {
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<Map.Entry> arrayList = new ArrayList(oriMap.entrySet());
        Collections.sort(arrayList, new MapValueComparator());
        for (Map.Entry entry : arrayList) {
            linkedHashMap.put((String) entry.getKey(), (Integer) entry.getValue());
        }
        return linkedHashMap;
    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {
        MapValueComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {
            return me2.getValue().compareTo(me1.getValue());
        }
    }
}
