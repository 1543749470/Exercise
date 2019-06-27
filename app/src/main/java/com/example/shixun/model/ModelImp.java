package com.example.shixun.model;

import android.util.Log;

import com.example.shixun.ApiService;
import com.jy.beans.NewBeans;
import com.jy.beans.RecentBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImp implements IModel{
    private static final String TAG = "ModelImp";
    @Override
    public void getModel(final CallModel callModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.getUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getU().enqueue(new Callback<NewBeans>() {
            @Override
            public void onResponse(Call<NewBeans> call, Response<NewBeans> response) {
                List<RecentBean> recent = response.body().getRecent();
                Log.e(TAG, "onResponse: "+recent );
                callModel.updateSccess(recent);
            }

            @Override
            public void onFailure(Call<NewBeans> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                callModel.updateFilade(t.getMessage());
            }
        });
    }
}
