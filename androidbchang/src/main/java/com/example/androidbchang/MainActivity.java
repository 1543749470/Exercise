package com.example.androidbchang;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jy.adapters.MyVpAdapter;
import com.jy.fragments.Fragment1;
import com.jy.fragments.Fragment2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * 图册
     */
    private TextView mMyToolbarTitle;
    private Toolbar mMyToolbar;
    private ViewPager mMyVp;
    private TabLayout mMyTab;
    private ArrayList<Fragment> list;
    private MyVpAdapter myVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        initTab();
    }

    private void initTab() {
        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        myVpAdapter = new MyVpAdapter(getSupportFragmentManager(), list);
        mMyVp.setAdapter(myVpAdapter);
        mMyTab.setupWithViewPager(mMyVp);
        mMyTab.getTabAt(0).setText("图册");
        mMyTab.getTabAt(1).setText("收藏");
        mMyTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        mMyToolbarTitle.setText("图册");
                        tr.show(new Fragment1());
                        tr.hide(new Fragment2());
                        break;

                    case 1:
                        mMyToolbarTitle.setText("收藏");
                        tr.show(new Fragment2());
                        tr.hide(new Fragment1());
                        break;
                }
                tr.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initToolbar() {
        mMyToolbar.setTitle("");
    }

    private void initView() {
        mMyToolbarTitle = (TextView) findViewById(R.id.myToolbarTitle);
        mMyToolbar = (Toolbar) findViewById(R.id.myToolbar);
        mMyVp = (ViewPager) findViewById(R.id.myVp);
        mMyTab = (TabLayout) findViewById(R.id.myTab);
    }
}
