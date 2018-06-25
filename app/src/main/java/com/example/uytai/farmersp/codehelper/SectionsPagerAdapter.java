package com.example.uytai.farmersp.codehelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.uytai.farmersp.mvp.feed.FeedFragment;
import com.example.uytai.farmersp.mvp.mytrader.MyTraderFragment;
import com.example.uytai.farmersp.mvp.profile.ProfileFragment;
import com.example.uytai.farmersp.mvp.trader.TraderFragment;

/**
 * Created by uytai on 1/13/2018.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FeedFragment canbanFragment = new FeedFragment();
                return canbanFragment;
            case 1:
                 TraderFragment traderFragment = new TraderFragment();
                return traderFragment;
            case 2:
                MyTraderFragment myTraderFragment = new MyTraderFragment();
                return myTraderFragment;
            case 3:
                ProfileFragment profileFragment = new ProfileFragment();
                return  profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Thu mua";
            case 1:
                return "Thương lái";
            case 2:
                return "Thống kê";
            case 3:
                return "Cá nhân";
            default:
                return null;
        }
    }
}
