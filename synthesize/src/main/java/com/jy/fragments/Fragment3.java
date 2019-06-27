package com.jy.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.synthesize.ApiService;
import com.example.synthesize.MyReceiver;
import com.example.synthesize.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment3 extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * okhttp上传文件
     */
    private Button mMyOk;
    /**
     * retrofit上传文件
     */
    private Button mMyRetrofit;
    /**
     * 下载
     */
    private Button mMyXia;
    /**
     * 广播测试
     */
    private Button mMyRe;
    /**
     * Synthesize
     */
    private TextView mResult;
    private ImageView mImg;
    private ProgressBar mMyPro;
    private MyReceiver myReceiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, null);
        initView(view);
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("aa");
        getActivity().registerReceiver(myReceiver,intentFilter);
        return view;
    }

    private void initView(View view) {
        mMyOk = (Button) view.findViewById(R.id.myOk);
        mMyOk.setOnClickListener(this);
        mMyRetrofit = (Button) view.findViewById(R.id.myRetrofit);
        mMyRetrofit.setOnClickListener(this);
        mMyXia = (Button) view.findViewById(R.id.myXia);
        mMyXia.setOnClickListener(this);
        mMyRe = (Button) view.findViewById(R.id.myRe);
        mMyRe.setOnClickListener(this);
        mResult = (TextView) view.findViewById(R.id.result);
        mImg = (ImageView) view.findViewById(R.id.img);
        mMyPro = (ProgressBar) view.findViewById(R.id.myPro);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.myOk:
                testOkHttp();
                break;
            case R.id.myRetrofit:
                testRetrofit();
                break;
            case R.id.myXia:
                testXia();

                break;
            case R.id.myRe:
                Intent aa = new Intent("aa");
                aa.putExtra("data","我的广播事件");
                getActivity().sendBroadcast(aa);
                break;
        }
    }

    private void testXia() {
        //文件下载
//http://yun918.cn/study/public/res/UnknowApp-1.0.apk
        String filepath = Environment.getExternalStorageDirectory() + File.separator + "test.apk";
        final File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/res/UnknowApp-1.0.apk")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                long contentLength = response.body().contentLength();
                mMyPro.setMax((int) contentLength);
                int readLength=0;
                int currLength=0;
                byte[] bytes = new byte[1024*4];
                while ((readLength=inputStream.read(bytes))!=-1){
                    fileOutputStream.write(bytes,0,readLength);
                    currLength+=readLength;
                    mMyPro.setProgress(currLength);
                    Log.e(TAG, "onResponse: "+currLength );
                    final int l = (int) (currLength * 100 / contentLength);
                    Log.e(TAG, "onResponse: "+l );
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mResult.setText(l+"%");
                        }
                    });
                }
            }
        });
    }

    private void testRetrofit() {
        final String filepath = Environment.getExternalStorageDirectory() + File.separator + "a.jpg";
        File file = new File(filepath);
        if (!file.exists()) {
            Log.e(TAG, "testRetrofit: 文件不存在");
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "H1811A");
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part multipart = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.getFile)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getF(requestBody, multipart).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    final String string = response.body().string();
                    Log.e(TAG, "onResponse: " + string);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mResult.setText(string);
                            Glide.with(getActivity()).load(filepath).into(mImg);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private static final String TAG = "Fragment3";

    private void testOkHttp() {
        //    文件上传接口：http://yun918.cn/study/public/file_upload.php
//    传输类型 post
//    参数：(String  key,String  file);
//    key    上传文件的文件夹（自己随意传）
//    file  固定的"file"参数里面放上传文件的流内容
        final String filepath = Environment.getExternalStorageDirectory() + File.separator + "a.jpg";
        File file = new File(filepath);
        if (!file.exists()) {
            Log.e(TAG, "testOkHttp: 文件不存在");
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "h1811a")
                .addFormDataPart("file", file.getName(), requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(multipartBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e(TAG, "onResponse: " + string);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResult.setText(string);
                        Glide.with(getActivity()).load(filepath).into(mImg);
                    }
                });
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myReceiver);
    }
}
