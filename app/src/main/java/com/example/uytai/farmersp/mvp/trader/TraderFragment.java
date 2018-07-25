package com.example.uytai.farmersp.mvp.trader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.Rating;
import com.example.uytai.farmersp.model.RationTL;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TraderFragment extends Fragment implements ITrader.View, SearchView.OnQueryTextListener {

    @BindView(R.id.recyclerviewTrader)
    RecyclerView recyclerviewTrader;
    @BindView(R.id.bar_trader)
    Toolbar toolbar;
    @BindView(R.id.search_trader)
    SearchView searchView;
    TraderAdapter traderAdapter;
    ProgressDialog pDialog;
    ArrayList<List<Rating>> listArrayList = new ArrayList<>();
    public static List<Float> srateList = new ArrayList<>();
    public static List<Integer> sidtlList = new ArrayList<>();
    List<ThuongLaiModel> modelsTL = new ArrayList<>();
    List<Rating> modelsRT = new ArrayList<>();
    NongDanService nongDanService;
    public static List<RationTL> rationTLs = new ArrayList<>();

    public TraderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_trader, container, false);
        ButterKnife.bind(this, root);
        //requestGetListRating();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pDialog = new ProgressDialog(getActivity());
        TraderPresenter traderPresenter = new TraderPresenter(this);
        traderPresenter.requestGetListTrader();
        searchView.setOnQueryTextListener(this);
        initView();
        ActionToolbar();
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thương lái");
    }


    private void initView() {
        recyclerviewTrader.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerviewTrader.setLayoutManager(linearLayoutManager);
    }




    @Override
    public void getListTraderSuccess(List<ThuongLaiModel> thuongLaiModel) {
        if(thuongLaiModel !=null){
            modelsTL.addAll(thuongLaiModel);
            //getAllListRating();
            traderAdapter = new TraderAdapter(thuongLaiModel, getActivity().getApplicationContext());
            recyclerviewTrader.setAdapter(traderAdapter);
//            for(int i=0; i<thuongLaiModel.size();i++){
//                requestGetRating(thuongLaiModel.get(i).getId());
//            }
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getListTraderFail() {
        Toast.makeText(getActivity().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogProgress() {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void dismissDialog() {
        if(pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        traderAdapter.filter(s);
        return true;
    }


//    //get allLis rating
//    private void requestGetListRating(){
//        nongDanService = ApiClient.getClient().create(NongDanService.class);
//        Call<List<Rating>> call = nongDanService.getRating();
//        call.enqueue(new Callback<List<Rating>>() {
//            @Override
//            public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
//                if(response.isSuccessful()){
//                    if(response.body()!=null){
//                        modelsRT.addAll(response.body());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Rating>> call, Throwable t) {
//
//            }
//        });
//    }


//    private void getAllListRating(){
//        if(modelsRT.size()>0 && modelsTL.size()>0){
//            for(int i=0; i<modelsTL.size(); i++){
//                for(int j=0; j<modelsRT.size(); j++){
//                    if(modelsTL.get(i).getId()==modelsRT.get(j).getId_user()){
//                        RationTL rationTL = new RationTL();
//                        rationTL.setRate(modelsRT.get(j).getRate());
//                        rationTL.setIdtl(modelsTL.get(i).getId());
//                        rationTLs.add(rationTL);
//                    }
//                }
//            }
//        }
//        Log.d("uytai", rationTLs.size()+"_rate");
//    }


//    //
//    private void requestGetRating(int idtl){
//        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
//        Call<List<Rating>> call = nongDanService.getratingtl(idtl);
//        call.enqueue(new Callback<List<Rating>>() {
//            @Override
//            public void onResponse(Call<List<Rating>> cal, Response<List<Rating>> response) {
//                if(response.isSuccessful()){
//                    if(response.body()!=null){
//                        //ratings.addAll(response.body());
//                        //handlerRating(response.body());
//                        listArrayList.add(response.body());
//                        Log.d("uytai123", listArrayList.get(0).get(0).getRate()+"_");
//                    }
//                }else{
//                    //Log.d("uytai123", "NOT");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Rating>> call, Throwable t) {
//                //Log.d("uytai123", "Fail");
//            }
//        });
////        if(listArrayList.size()==models.size()){
////            getRateONAllListRating(listArrayList);
////            Log.d("uytai123", listArrayList.size()+"_");
////        }
//    }

//    //
//    private void getRateONAllListRating(ArrayList<List<Rating>> lists){
//        for(int i=0; i<lists.size();i++){
//            handlerRating(lists.get(i));
//        }
//    }
//
//    //xu ly tinh trung binh cua moi bai dang
//    private void handlerRating(List<Rating> ratingList){
//        float sum = 0;
//        float rate = 0;
//        for(int i=0 ; i<ratingList.size(); i++){
//            sum+=ratingList.get(i).getRate();
//        }
//        rate = (sum/ratingList.size());
//        rate = Math.round(rate);
//        Log.d("uytai123", rate+"_rate");
//        srateList.add(rate);
//    }
}
