package com.jy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbchang.DBUtils;
import com.example.androidbchang.R;
import com.jy.adapters.MyRecAdaptr;
import com.jy.beans.RecentBean;

import java.util.List;

public class Fragment2 extends Fragment {
    private View view;
    private RecyclerView mMyRec;
    private MyRecAdaptr myRecAdaptr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }

    private void initData() {
        List<RecentBean> recentBeans = DBUtils.queryAll();
        if (myRecAdaptr!=null){
            myRecAdaptr.addData(recentBeans);
        }
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMyRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        myRecAdaptr = new MyRecAdaptr(getActivity());
        mMyRec.setAdapter(myRecAdaptr);
    }
}
