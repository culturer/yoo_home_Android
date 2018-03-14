package com.culturer.yoo_home.function.world.shows.show;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class ShowPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public ShowPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position%fragments.size());
    }

    @Override
    public long getItemId(int position) {
        return position%fragments.size();
    }

}
