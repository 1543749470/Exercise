package com.jy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.synthesize.DBUtils;
import com.example.synthesize.R;
import com.jy.adapters.MyRecAdapter;
import com.jy.beans.ResultsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

public class Fragment4 extends Fragment {
    private View view;
    private RecyclerView mMyRec;
    private SmartRefreshLayout mMySm;
    private MyRecAdapter myRecAdapter;
    private List<ResultsBean> resultsBeans;
    int a;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, null);
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
        resultsBeans = DBUtils.queryAll();
        if (myRecAdapter!=null){
            myRecAdapter.addData(resultsBeans);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1,1,1,"删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(getActivity(),"删除",Toast.LENGTH_SHORT).show();
                DBUtils.delete(resultsBeans.get(a));
                myRecAdapter.delete(a);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(1,1,1,"列表");
        menu.add(2,2,2,"网格");
        menu.add(3,3,3,"瀑布流");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(getActivity(),"列表",Toast.LENGTH_SHORT).show();
                mMyRec.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case 2:
                Toast.makeText(getActivity(),"网格",Toast.LENGTH_SHORT).show();
                mMyRec.setLayoutManager(new GridLayoutManager(getActivity(),3));
                break;
            case 3:
                Toast.makeText(getActivity(),"瀑布流",Toast.LENGTH_SHORT).show();
                mMyRec.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMySm = (SmartRefreshLayout) view.findViewById(R.id.mySm);
        mMyRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        myRecAdapter = new MyRecAdapter(getActivity());
        mMyRec.setAdapter(myRecAdapter);
        setHasOptionsMenu(true);
        registerForContextMenu(mMyRec);
        myRecAdapter.setOnItemClickListener(new MyRecAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int i) {

            }

            @Override
            public void onLongClickListener(int i) {
                a=i;
            }
        });
    }
}
