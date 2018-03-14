                package com.culturer.yoo_home.function.world.recommend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armour8.yooplus.yooplus.R;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class RecommendFragment extends Fragment {

    private View contentView;
    public static final String EXTRA_TYPE = "type";
    private ShimmerRecyclerView shimmerRecycler;
    private CardAdapter mAdapter;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final int type = getType();
        RecyclerView.LayoutManager layoutManager;
        final DemoConfiguration demoConfiguration = BaseUtils.getDemoConfiguration(type, getContext());
        contentView = inflater.inflate(demoConfiguration.getLayoutResource(), container, false);
        layoutManager = demoConfiguration.getLayoutManager();
        shimmerRecycler = (ShimmerRecyclerView) findViewbyId(R.id.shimmer_recycler_view);
        if (demoConfiguration.getItemDecoration() != null) {
            shimmerRecycler.addItemDecoration(demoConfiguration.getItemDecoration());
        }
        mAdapter = new CardAdapter();
        mAdapter.setType(type);

        shimmerRecycler.setLayoutManager(layoutManager);
        shimmerRecycler.setAdapter(mAdapter);
        shimmerRecycler.showShimmerAdapter();
        shimmerRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCards();
            }
        }, 3000);

        initData();
        initView();

        return contentView;

    }

    private void initData(){
        initListData();
    }

    private void initView(){
        initBaseView();

        initRefresh();
    }

    private void initListData(){

    }

    private void initBaseView(){


    }

    private void initRefresh(){
        final RefreshLayout refreshLayout = contentView.findViewById(R.id.mrefreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                refresh(refreshlayout);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMore(refreshlayout);
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private View findViewbyId(int id){
        return contentView.findViewById(id);
    }
    private void loadCards() {
        int type = getType();
        mAdapter.setCards(BaseUtils.getCards(getResources(), type));
        shimmerRecycler.hideShimmerAdapter();
    }

    private int getType() {
        return BaseUtils.TYPE_LIST;
    }

    private void refresh(RefreshLayout refreshlayout){

    }

    private void loadMore(RefreshLayout refreshlayout){

    }
}
