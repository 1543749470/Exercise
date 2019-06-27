package com.jy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.synthesize2.ApiService;
import com.example.synthesize2.DBUtils;
import com.example.synthesize2.R;
import com.jy.adapters.MyRecAdapter;
import com.jy.beans.NewBeanse;
import com.jy.beans.ResultsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView mMyRec;
    private SmartRefreshLayout mMySm;
    int pose=1;
    private MyRecAdapter myRecAdapter;
    int a;
    private List<ResultsBean> results;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, null);
        initView(view);
        initData();
        return view;
    }

    private static final String TAG = "HomeFragment";
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.getUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getU(pose).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewBeanse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(NewBeanse newBeanse) {
                        results = newBeanse.getResults();
                        Log.e(TAG, "onNext: "+results );
                        myRecAdapter.addDatas(results);
                        myRecAdapter.addData(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1,1,1,"删除");
        menu.add(2,2,2,"收藏");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(getContext(),"删除",Toast.LENGTH_SHORT).show();
                myRecAdapter.delete(a);
                break;
            case 2:
                Toast.makeText(getContext(),"收藏",Toast.LENGTH_SHORT).show();
                DBUtils.insert(results.get(a));
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMySm = (SmartRefreshLayout) view.findViewById(R.id.mySm);
        mMyRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        myRecAdapter = new MyRecAdapter(getActivity());
        mMyRec.setAdapter(myRecAdapter);
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
        mMySm.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pose=1;
                initData();
                mMySm.finishRefresh();
            }
        });
        mMySm.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pose++;
                initData();
                mMySm.finishLoadMore();
            }
        });
    }
}
