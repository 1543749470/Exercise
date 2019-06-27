package com.example.synthesize;

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
import com.jy.fragments.Fragment1;
import com.jy.fragments.Fragment2;
import com.jy.fragments.Fragment3;
import com.jy.fragments.Fragment4;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main4);
        initView();
        initToolbar();
        initTab();
    }

    private void initTab() {
        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        myVpAdapter = new MyVpAdapter(getSupportFragmentManager(), list);
        mMyVp.setAdapter(myVpAdapter);
        mMyTab.setupWithViewPager(mMyVp);
        mMyTab.getTabAt(0).setText("Fragment1");
        mMyTab.getTabAt(1).setText("Fragment2");
        mMyTab.getTabAt(2).setText("Fragment3");
        mMyTab.getTabAt(3).setText("Fragment4");
        mMyTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        mMyToolbarTitle.setText("Fragment1");
                        tr.show(new Fragment1());
                        tr.hide(new Fragment2());
                        tr.hide(new Fragment3());
                        tr.hide(new Fragment4());
                        break;

                    case 1:
                        mMyToolbarTitle.setText("Fragment1");
                        tr.show(new Fragment2());
                        tr.hide(new Fragment1());
                        tr.hide(new Fragment3());
                        tr.hide(new Fragment4());
                        break;


                    case 2:
                        mMyToolbarTitle.setText("Fragment1");
                        tr.show(new Fragment3());
                        tr.hide(new Fragment2());
                        tr.hide(new Fragment1());
                        tr.hide(new Fragment4());
                        break;

                    case 3:
                        mMyToolbarTitle.setText("Fragment1");
                        tr.show(new Fragment4());
                        tr.hide(new Fragment2());
                        tr.hide(new Fragment3());
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
        mMyToolbar.setLogo(R.mipmap.ic_launcher);
        mMyToolbar.setSubtitle("副标题");
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
