package com.surhoo.sh.common.util;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NoCacheBaseViewpageAdapter extends FragmentPagerAdapter {
    FragmentManager fragmentManager;
    private List<Fragment> fragments; //创建一个List<Fragment>
    private List<String> titles;

    public NoCacheBaseViewpageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = fragments;
    }

    public NoCacheBaseViewpageAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
