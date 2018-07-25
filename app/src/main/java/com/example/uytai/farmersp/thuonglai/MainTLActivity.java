package com.example.uytai.farmersp.thuonglai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModelTL;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.mvp.login.LoginActivity;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.example.uytai.farmersp.thuonglai.TinDangTL.TinDaDangTLActivity;
import com.example.uytai.farmersp.thuonglai.nongsan.MapActivity;
import com.example.uytai.farmersp.thuonglai.profile.ProfileTLActivity;
import com.example.uytai.farmersp.thuonglai.thongbao.ThongBaoTLActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainTLActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    //    @BindView(R.id.listviewnongsan)
//    ListView listView;
    @BindView(R.id.recyclerviewFeedTL)
    RecyclerView recyclerView;
    //    @BindView(R.id.search_nongsan)
//    SearchView searchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swip_nongsan_tl)
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pDialog;
    @BindView(R.id.showmap)
    ImageView showmap;

    public static ThuongLaiModel thuongLaiModel;
    List<NongSanModelTL> listNongSan;
    MainTLAdapter mainTLAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tl);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        initView();
//        searchView.setOnQueryTextListener(this);
        setSupportActionBar(toolbar);
        swipeRefreshLayout.setOnRefreshListener(this);
        //
        getUser();
        //nav
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //header
        View header = navigationView.getHeaderView(0);
        TextView tvstatus = header.findViewById(R.id.status_haeder);
        TextView tvname = header.findViewById(R.id.name_header);
        CircleImageView imgavatar = header.findViewById(R.id.avatar_header);
        //set data header
        tvname.setText(thuongLaiModel.getTen());
        tvstatus.setText(thuongLaiModel.getStatus());
        Glide.with(getApplicationContext()).load(thuongLaiModel.getAvatar()).into(imgavatar);
        //----init----//
        listNongSan = new ArrayList<>();

        //--get data from server--//
        requestGetListNongSan();
    }

    //
    private void initView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getUser() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if (bundle != null) {
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
        SearchView searchView = (SearchView) menu.findItem(R.id.menusearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mainTLAdapter.filter(s.trim());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.item_nongsan) {
            startActivity(new Intent(MainTLActivity.this, MapActivity.class));
        } else if (id == R.id.item_thongbao) {
            startActivity(new Intent(MainTLActivity.this, ThongBaoTLActivity.class));
        } else if (id == R.id.item_canhan) {
            startActivity(new Intent(MainTLActivity.this, ProfileTLActivity.class));
        } else if (id == R.id.item_tdd_tl) {
            startActivity(new Intent(MainTLActivity.this, TinDaDangTLActivity.class));
        } else if (id == R.id.item_dangxuat) {
            startActivity(new Intent(MainTLActivity.this, LoginActivity.class));
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.showmap)
    void ShowMap() {
        startActivity(new Intent(MainTLActivity.this, MapActivity.class));
    }


    //------GET DATA FROM SERVER------//
    private void requestGetListNongSan() {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<NongSanModelTL>> call = thuonglaiService.getNongSanForTL();
        call.enqueue(new Callback<List<NongSanModelTL>>() {
            @Override
            public void onResponse(Call<List<NongSanModelTL>> call, Response<List<NongSanModelTL>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        listNongSan.clear();
                        listNongSan.addAll(response.body());
                        mainTLAdapter = new MainTLAdapter(MainTLActivity.this, listNongSan);
                        recyclerView.setAdapter(mainTLAdapter);
                        mainTLAdapter.notifyDataSetChanged();
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<NongSanModelTL>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Tải thông tin thất bại, xin thử lại sau!", Toast.LENGTH_SHORT).show();
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    //by id loai ns
    private void requestGetListNongSanbyidLoains(int idloains) {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<NongSanModelTL>> call = thuonglaiService.getNongSanbyIDLNSForTL(idloains);
        call.enqueue(new Callback<List<NongSanModelTL>>() {
            @Override
            public void onResponse(Call<List<NongSanModelTL>> call, Response<List<NongSanModelTL>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        listNongSan.clear();
                        listNongSan.addAll(response.body());
                        mainTLAdapter = new MainTLAdapter(MainTLActivity.this, listNongSan);
                        recyclerView.setAdapter(mainTLAdapter);
                        mainTLAdapter.notifyDataSetChanged();
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NongSanModelTL>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Tải thông tin thất bại, xin thử lại sau!", Toast.LENGTH_SHORT).show();
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGetListNongSan();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }


    //
    @OnClick(R.id.tatcat)
    void TatCa() {
        requestGetListNongSan();
    }

    @OnClick(R.id.anqua)
    void AnQua() {
        requestGetListNongSanbyidLoains(1);
    }

    @OnClick(R.id.congnghiep)
    void CongNghiep() {
        requestGetListNongSanbyidLoains(2);
    }

    @OnClick(R.id.khac)
    void Khac() {
        requestGetListNongSanbyidLoains(3);
    }
}
