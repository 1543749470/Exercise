package com.example.synthesize2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jy.adapters.MyVpAdapter;
import com.jy.fragments.CollectFragment;
import com.jy.fragments.HomeFragment;
import com.jy.fragments.MvpFragment;
import com.jy.fragments.RequestFragment;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    /**
     * Fragment1
     */
    private TextView mMyToolbarTitle;
    private Toolbar mMyToolbar;
    private ViewPager mMyVp;
    private TabLayout mMyTab;
    private NavigationView mMyNa;
    private DrawerLayout mMyDl;
    private ArrayList<Fragment> list;
    private MyVpAdapter myVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
        initToolbar();
        initTab();
    }

    private void initTab() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new MvpFragment());
        list.add(new RequestFragment());
        list.add(new CollectFragment());
        myVpAdapter = new MyVpAdapter(getSupportFragmentManager(), list);
        mMyVp.setAdapter(myVpAdapter);
        mMyTab.setupWithViewPager(mMyVp);
        mMyTab.getTabAt(0).setText("首页").setIcon(R.drawable.home_tab);
        mMyTab.getTabAt(1).setText("MVP").setIcon(R.drawable.mvp_tab);
        mMyTab.getTabAt(2).setText("请求").setIcon(R.drawable.request_tab);
        mMyTab.getTabAt(3).setText("收藏").setIcon(R.drawable.collect_tab);
        mMyTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        mMyToolbarTitle.setText("首页");
                        tr.show(new HomeFragment());
                        tr.hide(new MvpFragment());
                        tr.hide(new RequestFragment());
                        tr.hide(new CollectFragment());
                        break;

                    case 1:
                        mMyToolbarTitle.setText("MVP");
                        tr.show(new MvpFragment());
                        tr.hide(new HomeFragment());
                        tr.hide(new RequestFragment());
                        tr.hide(new CollectFragment());
                        break;

                    case 2:
                        mMyToolbarTitle.setText("请求");
                        tr.show(new RequestFragment());
                        tr.hide(new MvpFragment());
                        tr.hide(new HomeFragment());
                        tr.hide(new CollectFragment());
                        break;


                    case 3:
                        mMyToolbarTitle.setText("收藏");
                        tr.show(new CollectFragment());
                        tr.hide(new MvpFragment());
                        tr.hide(new RequestFragment());
                        tr.hide(new HomeFragment());
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
        mMyToolbar.setSubtitle("副标题");
        mMyToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(mMyToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mMyDl, mMyToolbar, R.string.app_name, R.string.app_name);
        mMyDl.addDrawerListener(toggle);
        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toggle.syncState();
    }

    private void initView() {
        mMyToolbarTitle = (TextView) findViewById(R.id.myToolbarTitle);
        mMyToolbar = (Toolbar) findViewById(R.id.myToolbar);
        mMyVp = (ViewPager) findViewById(R.id.myVp);
        mMyTab = (TabLayout) findViewById(R.id.myTab);
        mMyNa = (NavigationView) findViewById(R.id.myNa);
        mMyDl = (DrawerLayout) findViewById(R.id.myDl);
    }
}
