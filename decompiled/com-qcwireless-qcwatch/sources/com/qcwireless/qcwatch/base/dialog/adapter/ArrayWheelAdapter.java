package com.qcwireless.qcwatch.base.dialog.adapter;

import com.contrarywind.adapter.WheelAdapter;
import java.util.List;

/* loaded from: classes3.dex */
public class ArrayWheelAdapter<T> implements WheelAdapter {
    public static final int DEFAULT_LENGTH = 4;
    private List<T> items;
    private int length;

    public ArrayWheelAdapter(List<T> items, int length) {
        this.items = items;
        this.length = length;
    }

    public ArrayWheelAdapter(List<T> items) {
        this(items, 4);
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public Object getItem(int index) {
        return (index < 0 || index >= this.items.size()) ? "" : this.items.get(index);
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
