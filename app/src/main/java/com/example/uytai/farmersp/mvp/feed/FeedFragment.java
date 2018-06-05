package com.example.uytai.farmersp.mvp.feed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.ThuMuaModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FeedFragment extends Fragment implements IFeed.View {
    @BindView(R.id.recyclerviewFeed)
    RecyclerView recyclerViewFeed;

    FeedAdapter feedAdapter;

    public FeedFragment() {
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
        View root = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, root);
        FeedPresenter feedPresenter = new FeedPresenter(this);
        feedPresenter.requestGetListFeed();
        initView();
        return root;
    }

    private void initView() {
        recyclerViewFeed.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFeed.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getListFeedSuccess(List<ThuMuaModel> thuMuaModels) {
        if(thuMuaModels !=null){
            feedAdapter = new FeedAdapter(thuMuaModels, getActivity().getApplicationContext());
            recyclerViewFeed.setAdapter(feedAdapter);
        }
    }

    @Override
    public void getListFeedFail() {
        Toast.makeText(getActivity().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }
}
