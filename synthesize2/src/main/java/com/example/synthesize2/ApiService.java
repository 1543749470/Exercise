package com.example.synthesize2;

import com.jy.beans.NewBeanse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    String getUrl="https://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/20/{pose}")
    Observable<NewBeanse> getU(@Path("pose") int pose);

    //    文件上传接口：http://yun918.cn/study/public/file_upload.php
//    传输类型 post
//    参数：(String  key,String  file);
//    key    上传文件的文件夹（自己随意传）
//    file  固定的"file"参数里面放上传文件的流内容

    String getFile="http://yun918.cn/";
    @Multipart
    @POST("study/public/file_upload.php")
    Call<ResponseBody> getF(@Part("key") RequestBody requestBody, @Part MultipartBody.Part multipart);
}
