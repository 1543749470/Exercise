package com.example.androidbchang;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jy.adapters.VpAdapter;
import com.jy.beans.RecentBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 收藏
     */
    private Button mShou;
    private ViewPager mVP;
    private ArrayList<View> list;
    private VpAdapter vpAdapter;
    private Banner mMyBanner;
    List<RecentBean> data;
    private int id;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
//        initVP();
    }

//    private void initVP() {
//        View view = LayoutInflater.from(this).inflate(R.layout.vp_item, null);
//        list = new ArrayList<>();
//        list.add(view);
//        vpAdapter = new VpAdapter(list);
//        mVP.setAdapter(vpAdapter);
//        ImageView img = view.findViewById(R.id.vp_img);
//        data = (RecentBean) getIntent().getSerializableExtra("data");
//        Glide.with(this).load(data.getThumbnail()).into(img);
//}
private static final String TAG = "Main2Activity";
    private void initView() {
        mShou = (Button) findViewById(R.id.shou);
        mShou.setOnClickListener(this);
//        mVP = (ViewPager) findViewById(R.id.VP);
        mMyBanner = (Banner) findViewById(R.id.myBanner);
        data = (List<RecentBean>) getIntent().getSerializableExtra("data");
        Log.e(TAG, "initView: "+data );
        id = getIntent().getIntExtra("id", 0);
        mMyBanner.setImages(data).setImageLoader(new MyLoader()).start();
        mMyBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                a=position;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.shou:
                DBUtils.insert(data.get(a));
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            RecentBean recentBean= (RecentBean) path;
            Glide.with(context).load(recentBean.getThumbnail())
                    .into(imageView);
        }
    }
}
