package com.example.shixun;

import com.jy.beans.NewBeans;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String getUrl="http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Call<NewBeans> getU();
}
