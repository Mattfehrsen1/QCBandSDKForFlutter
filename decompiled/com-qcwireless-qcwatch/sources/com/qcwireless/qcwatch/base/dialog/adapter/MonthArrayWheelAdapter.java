package com.qcwireless.qcwatch.base.dialog.adapter;

import com.contrarywind.adapter.WheelAdapter;
import com.qcwireless.qc_utils.date.LanguageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MonthArrayWheelAdapter<T> implements WheelAdapter {
    public static final int DEFAULT_LENGTH = 4;
    private List<T> items;
    private int length;
    Map<Integer, String> map;

    public MonthArrayWheelAdapter(List<T> items, int length) {
        this.map = new HashMap();
        this.items = items;
        this.length = length;
    }

    public MonthArrayWheelAdapter(List<T> items, Map<Integer, String> map) {
        this(items, 4);
        this.map = map;
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public Object getItem(int index) {
        if (index < 0 || index >= this.items.size()) {
            return "";
        }
        if (LanguageUtil.changeDateFormat()) {
            return this.map.get(Integer.valueOf(index + 1));
        }
        return this.items.get(index);
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public int getItemsCount() {
        return this.items.size();
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public int indexOf(Object o) {
        return this.items.indexOf(o);
    }
}
