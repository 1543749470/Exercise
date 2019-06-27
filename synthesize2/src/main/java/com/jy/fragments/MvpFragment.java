package com.jy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.synthesize2.BaiDuActivity;
import com.example.synthesize2.R;
import com.jy.adapters.MyRecAdapter;
import com.jy.beans.ResultsBean;
import com.jy.fragments.presenter.PresenterImo;
import com.jy.fragments.view.IView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

public class MvpFragment extends Fragment implements IView {
    private View view;
    private RecyclerView mMyRec;
    private SmartRefreshLayout mMySm;
    private MyRecAdapter myRecAdapter;
    private PresenterImo presenterImo;
    int a;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, null);
        initView(view);
        initPresenter();

        return view;
    }

    private void initPopu() {
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.popu_item, null);
        final PopupWindow popupWindow = new PopupWindow(view1, 200, 200);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setAnimationStyle(R.style.PopAnimation);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        view1.findViewById(R.id.popu_shan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecAdapter.delete(a);
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(mMyRec, Gravity.CENTER,0,0);
    }

    private void initPresenter() {
        presenterImo = new PresenterImo(this);
        presenterImo.getPrensenter();
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMySm = (SmartRefreshLayout) view.findViewById(R.id.mySm);
        mMyRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        myRecAdapter = new MyRecAdapter(getActivity());
        mMyRec.setAdapter(myRecAdapter);
        myRecAdapter.setOnItemClickListener(new MyRecAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int i) {

                initPopu();
            }

            @Override
            public void onLongClickListener(int i) {
                a=i;
                startActivity(new Intent(getActivity(), BaiDuActivity.class));
            }
        });

    }

    private static final String TAG = "MvpFragment";
    @Override
    public void updateSccess(final List<ResultsBean> resultsBeans) {
        Log.e(TAG, "updateSccess: "+resultsBeans);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myRecAdapter.addData(resultsBeans);
            }
        });
    }

    @Override
    public void updateFilade(String error) {

    }
}
