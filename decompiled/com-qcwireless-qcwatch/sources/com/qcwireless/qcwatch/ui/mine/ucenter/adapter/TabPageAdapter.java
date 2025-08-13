package com.qcwireless.qcwatch.ui.mine.ucenter.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/* loaded from: classes3.dex */
public class TabPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public TabPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.fragments.size();
    }
}
