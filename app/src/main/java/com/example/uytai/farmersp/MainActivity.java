package com.example.uytai.farmersp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uytai.farmersp.codehelper.SectionsPagerAdapter;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    public static UserModel userModel;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    //An array containing your icons from the drawable directory
    final int[] ICONS = new int[]{
            R.drawable.ic_tab_feed,
            R.drawable.ic_tab_tutor,
            R.drawable.ic_tab_post,
            R.drawable.ic_tab_profile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setuptab();
        getUser();

    }

    private void getUser() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            userModel = (UserModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
        }
    }

    private void setuptab() {
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.getTabAt(3).setIcon(ICONS[3]);
    }

    private void init() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
