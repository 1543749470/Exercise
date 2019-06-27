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
import android.widget.Toast;

import com.example.synthesize.MainWebActivity;
import com.example.synthesize.R;
import com.jy.adapters.MyRecAdapter;
import com.jy.beans.ResultsBean;
import com.jy.fragments.presenter.PresenterImo;
import com.jy.fragments.view.IView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

public class Fragment2 extends Fragment implements IView {
    private View view;
    private RecyclerView mMyRec;
    private SmartRefreshLayout mMySm;
    private MyRecAdapter myRecAdapter;
    private static final String TAG = "Fragment2";
    private PresenterImo presenterImo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, null);
        initView(view);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        presenterImo = new PresenterImo(this);
        presenterImo.getPrensenter();
    }

    @Override
    public void updateSccess(final List<ResultsBean> resultsBeans) {
        Log.e(TAG, "updateSccess: "+resultsBeans );
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myRecAdapter.addData(resultsBeans);
            }
        });
    }

    @Override
    public void updateFilade(String error) {
        Log.e(TAG, "updateFilade: "+error );
    }

    private void initView(final View view) {

        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMySm = (SmartRefreshLayout) view.findViewById(R.id.mySm);
        mMyRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        myRecAdapter = new MyRecAdapter(getActivity());
        mMyRec.setAdapter(myRecAdapter);
        myRecAdapter.setOnItemClickListener(new MyRecAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(final int i) {
                Toast.makeText(getContext(),"点击",Toast.LENGTH_SHORT).show();
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.popu_item, null);
                final PopupWindow popupWindow = new PopupWindow(view1, 200, 200);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                view1.findViewById(R.id.popu_shan).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRecAdapter.delete(i);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(mMyRec, Gravity.CENTER,0,0);
            }

            @Override
            public void onLongClickListener(int i) {
                Toast.makeText(getContext(),"长按",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),MainWebActivity.class));
            }
        });
    }
}
