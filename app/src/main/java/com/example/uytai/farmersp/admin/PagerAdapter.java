package com.example.uytai.farmersp.admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NongDanFragment nongDanFragment = new NongDanFragment();
                return nongDanFragment;
            case 1:
                ThuongLaiFragment thuongLaiFragment = new ThuongLaiFragment();
                return thuongLaiFragment;
            default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Nông dân";
            case 1:
                return "Thương lái";
            default:
                return null;
        }
    }
}
