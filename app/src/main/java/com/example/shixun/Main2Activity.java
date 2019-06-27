package com.example.shixun;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jy.adapters.VpAdapter;
import com.jy.beans.RecentBean;

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
    private ImageView Img;
    RecentBean data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initVP();
    }

    private void initVP() {
        View view = LayoutInflater.from(this).inflate(R.layout.vp_item, null);

        list = new ArrayList<>();
        list.add(view);
        vpAdapter = new VpAdapter(list);
        mVP.setAdapter(vpAdapter);
        Img = view.findViewById(R.id.vp_img);
        data = (RecentBean) getIntent().getSerializableExtra("data");
        Glide.with(this)
                .load(data.getThumbnail())
                .into(Img);
    }

    private void initView() {
        mShou = (Button) findViewById(R.id.shou);
        mShou.setOnClickListener(this);
        mVP = (ViewPager) findViewById(R.id.VP);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.shou:
                DBUtils.insert(data);
                Toast.makeText(this,"已收藏",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
