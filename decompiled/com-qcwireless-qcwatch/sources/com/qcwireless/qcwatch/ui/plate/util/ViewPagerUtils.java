package com.qcwireless.qcwatch.ui.plate.util;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager2.widget.ViewPager2;

/* loaded from: classes3.dex */
public class ViewPagerUtils {
    public static void setSupportsChangeAnimations(ViewPager2 viewPager, boolean enable) {
        for (int i = 0; i < viewPager.getChildCount(); i++) {
            View childAt = viewPager.getChildAt(i);
            if (childAt instanceof RecyclerView) {
                RecyclerView.ItemAnimator itemAnimator = ((RecyclerView) childAt).getItemAnimator();
                if (itemAnimator != null) {
                    ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(enable);
                    return;
                }
                return;
            }
        }
    }
}
