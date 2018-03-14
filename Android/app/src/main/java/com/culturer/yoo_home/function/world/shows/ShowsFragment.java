package com.culturer.yoo_home.function.world.shows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.world.shows.show.ShowActivity;


public class ShowsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View contentView;
    private ListView list_shows;

    private ShowsAdapter adapter ;

    public ShowsFragment() {
        // Required empty public constructor
    }

    public static ShowsFragment newInstance(String param1, String param2) {
        ShowsFragment fragment = new ShowsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView =  inflater.inflate(R.layout.fragment_shows, container, false);
        init();
        return contentView;
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){
        adapter = new ShowsAdapter(getContext());
    }

    private void initView(){
        list_shows = (ListView) findViewById(R.id.list_shows);
        list_shows.setAdapter(adapter);
        list_shows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ShowActivity.class);
                Bundle datas = new Bundle();
//                datas.putString("","");
                intent.putExtra("datas",datas);
                startActivity(intent);
            }
        });
    }

    private View findViewById(int id){
        return contentView.findViewById(id);
    }

}
