package com.example.uytai.farmersp;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.uytai.farmersp.codehelper.SectionsPagerAdapter;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    public static NongDanModel nongDanModel;
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
            nongDanModel = (NongDanModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
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


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
