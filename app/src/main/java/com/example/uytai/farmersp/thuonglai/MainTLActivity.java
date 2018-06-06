package com.example.uytai.farmersp.thuonglai;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.thuonglai.TinDangTL.TinDaDangTLActivity;
import com.example.uytai.farmersp.thuonglai.nongsan.NongSanActivity;
import com.example.uytai.farmersp.thuonglai.profile.ProfileTLActivity;
import com.example.uytai.farmersp.thuonglai.thongbao.ThongBaoTLActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainTLActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ThuongLaiModel thuongLaiModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getUser();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView tvstatus = header.findViewById(R.id.status_haeder);
        TextView tvname = header.findViewById(R.id.name_header);
        CircleImageView imgavatar = header.findViewById(R.id.avatar_header);
        //
        tvname.setText(thuongLaiModel.getTen());
        tvstatus.setText(thuongLaiModel.getStatus());
        Glide.with(getApplicationContext()).load(thuongLaiModel.getAvatar()).into(imgavatar);
    }

    private void getUser() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            thuongLaiModel = (ThuongLaiModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_tl, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.item_nongsan) {
            startActivity(new Intent(MainTLActivity.this, NongSanActivity.class));
        } else if (id == R.id.item_thongbao) {
            startActivity(new Intent(MainTLActivity.this, ThongBaoTLActivity.class));
        } else if (id == R.id.item_canhan) {
            startActivity(new Intent(MainTLActivity.this, ProfileTLActivity.class));
        }else if(id==R.id.item_tdd_tl){
            startActivity(new Intent(MainTLActivity.this, TinDaDangTLActivity.class));
        } else if (id == R.id.item_dangxuat) {
            //
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
