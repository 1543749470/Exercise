package com.jy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbchang.R;
import com.example.androidbchang.presenter.PresenterImp;
import com.example.androidbchang.view.IView;
import com.jy.adapters.MyRecAdaptr;
import com.jy.beans.RecentBean;

import java.util.List;

public class Fragment1 extends Fragment implements IView {
    private View view;
    private RecyclerView mMyRec;
    private PresenterImp presenterImp;
    private MyRecAdaptr myRecAdaptr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);
        initView(view);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        presenterImp = new PresenterImp(this);
        presenterImp.getPresenter();
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMyRec.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mMyRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        myRecAdaptr = new MyRecAdaptr(getActivity());
        mMyRec.setAdapter(myRecAdaptr);
    }

    private static final String TAG = "Fragment1";
    @Override
    public void updateSccess(final List<RecentBean> recentBeans) {
        Log.e(TAG, "updateSccess: "+recentBeans );
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myRecAdaptr.addData(recentBeans);
            }
        });
    }

    @Override
    public void updateFilade(String error) {
        Log.e(TAG, "updateFilade: "+error );
    }
}
