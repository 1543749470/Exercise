package com.example.synthesize2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.jy.adapters.VpAdapter;

import java.util.ArrayList;

public class PagerActivity extends AppCompatActivity {

    private ViewPager mVP;
    private ArrayList<View> list;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        initView();
        initPager();
    }

    private void initPager() {
        LayoutInflater from = LayoutInflater.from(this);
        View view1 = from.inflate(R.layout.vp_item1, null);
        View view2 = from.inflate(R.layout.vp_item2, null);
        View view3 = from.inflate(R.layout.vp_item3, null);
        list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        vpAdapter = new VpAdapter(list);
        mVP.setAdapter(vpAdapter);
        view3.findViewById(R.id.vpBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PagerActivity.this,HomepageActivity.class));
            }
        });
    }

    private void initView() {
        mVP = (ViewPager) findViewById(R.id.VP);
    }
}
