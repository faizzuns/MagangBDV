package com.example.user.magangbdv;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by User on 11/09/2017.
 */

public class TabNewsEventPagerAdapter extends FragmentPagerAdapter {

    String[] title = new String[]{
            "News","Events"
    };

    public TabNewsEventPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new NewsFragment();
                break;
            case 1:
                fragment = new EventsFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
